package com.openapi.nasa.mvcController;

import com.openapi.nasa.entity.NasaApod;
import com.openapi.nasa.model.RoverPhotoRequests;
import com.openapi.nasa.service.NasaApiService;
import com.openapi.nasa.response.MarsRoverPhotosResponse;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/nasa")
@Validated
@EnableMethodSecurity
@Hidden
public class NasaMvcController {
    // injecting nasa api service //
    private NasaApiService theNasaApiService;
    // MARS ROVER API LINKS //
    @Value("${nasa.api.key}")
    private String apiKey;
    // save api-apod-url
    private String apiApodUrl="https://api.nasa.gov/planetary/apod?api_key={apiKey}";

    // save api-mars-rover-urls //
    private String baseUrl="https://api.nasa.gov/mars-photos/api/v1/rovers/";
    private String fullUrl="/photos?earth_date=";
    private String roverCamera="&camera=";

   // MARS ASTRONOMY PICTURE OF THE DAY( APOD ) CONTROLLERS //
    @Autowired
    public NasaMvcController(NasaApiService theNasaApiService) {
        this.theNasaApiService = theNasaApiService;
    }
    // convince method for save-apod->mvc //
    @GetMapping("/save-apod-mvc")
    public String saveApod()
    {
        // fetching nasaApod //
        NasaApod theNasaApod=theNasaApiService.getAstronomyPictureOfTheDay();
        theNasaApiService.save(theNasaApod);
        return "redirect:/nasa/list-apods";
    }

    @GetMapping("/mars-apod")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public String aPod(Model theModel)
    {
        NasaApod apod=theNasaApiService.getAstronomyPictureOfTheDay();
        theModel.addAttribute("apod",apod);
        return "nasa/apod";
    }



    @GetMapping("/list-apods")
    @PreAuthorize("hasRole('ADMIN')")
    public String fetchAllApods(Model theModel)
    {
        List<NasaApod> apods= theNasaApiService.fetchAllApodsMVC();
        theModel.addAttribute("apods",apods);
        return "nasa/list-apods";
    }

    @GetMapping("/showFormToUpdateApod")
    public String showFormToUpdateApod(@RequestParam("apodId") Integer apodId,Model theModel)
    {
        NasaApod foundNasaApod=theNasaApiService.findNasaApodById(apodId);
        theModel.addAttribute("apod",foundNasaApod);
        return "nasa/update-apod-form";
    }

    @PostMapping("/saveApodForm")
    public String saveApodForm(@Valid @ModelAttribute("apod") NasaApod theNasaApod, BindingResult theBindingResult)
    {
        if(theBindingResult.hasErrors())
        {
            return "nasa/update-apod-form";
        }
        else {
            theNasaApiService.save(theNasaApod);
            return "redirect:/nasa/list-apods";
        }
    }

    @GetMapping("/deleteApodMVC")
    public String deleteApodMVC(@RequestParam("apodId") Integer apodId)
    {
        theNasaApiService.deleteNasaApodById(apodId);
        return "redirect:/nasa/list-apods";
    }





    // MARS ROVER MVC CONTROLLERS //
    @GetMapping("/mars-rover")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public String marsRover(Model theModel)
    {
        RoverPhotoRequests roverPhotoRequest = new RoverPhotoRequests();

        List<String> roverCameras = Arrays.asList("&camera=fhaz", "&camera=rhaz", "&camera=mast", "&camera=chemcam", "&camera=mahli", "&camera=mardi", "&camera=navcam", "&camera=pancam", "&camera=minites");
        List<String> allCameras=Arrays.asList("fhaz", "rhaz", "mast", "chemcam", "mahli", "mardi", "navcam", "pancam", "minites");
        theModel.addAttribute("roverPhotoRequest",roverPhotoRequest);
        theModel.addAttribute("roverCameras", roverCameras);
        theModel.addAttribute("allCameras",allCameras);
        return "nasa/index";
    }

    @PostMapping("/show-photos")
    public String showPhotos(@ModelAttribute @Valid RoverPhotoRequests photoRequest,BindingResult theBindingResult, Model model) {
        if (theBindingResult.hasErrors()) {
            // Hanlding Validation errors //

            return "nasa/index";
        }


        String apiUrl= buildApiUrl(photoRequest);

        // Using RestTemplate to make a GET request to the NASA API
        RestTemplate restTemplate = new RestTemplate();
        MarsRoverPhotosResponse photosResponse = restTemplate.getForObject(apiUrl, MarsRoverPhotosResponse.class);

        // Populating the model with the fetched data
        model.addAttribute("roverPhotoRequest", photoRequest);
        model.addAttribute("allCameras", Arrays.asList("FHAZ", "RHAZ", "MAST", "CHEMCAM", "MAHLI", "MARDI", "NAVCAM", "PANCAM", "MINITES"));
        model.addAttribute("photos", photosResponse.getMarsRoverPhotosList());
        model.addAttribute("earthDate", photoRequest.getEarthDate());

        return "nasa/result";
    }

    // Convince method for api-url //
    private String buildApiUrl(RoverPhotoRequests photoRequest) {
        // CODE SNIPPET FOR SELECTING MULTIPLE CAMERAS url//
        List<String> selectedCameras = photoRequest.getRoverCameras();

        // Need to join selected cameras //
        String camerasParam = String.join("&camera=", selectedCameras);

        // Constructing api urls based on the selected cameras //
        return baseUrl + photoRequest.getRoverType() + fullUrl + photoRequest.getEarthDate() +
                roverCamera + camerasParam + "&api_key=" + apiKey;
    }

    // HOME PAGE //
    @GetMapping("/home-page")
    public String homePage()
    {
        return "nasa/home-page";
    }





}
