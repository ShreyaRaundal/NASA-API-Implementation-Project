package com.openapi.nasa.rest;
// Resful web services-API //
import com.openapi.nasa.entity.NasaApod;
import com.openapi.nasa.exceptionHandler.NasaNotFoundException;
import com.openapi.nasa.service.NasaApiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.openapi.nasa.response.MarsRoverPhotosResponse;

import java.awt.*;
import java.util.List;
@RestController
@RequestMapping("/api")
@Tag(name="Nasa Astronomy Picture Of The Day And Mars Rover Api")
public class NasaApiController {
    // injecting NasaApiService //
    private NasaApiService nasaApiService;

    @Autowired
    public NasaApiController(NasaApiService nasaApiService) {
        this.nasaApiService = nasaApiService;
    }

// APOD API SERVICE //
    @Operation(
            description = "Get endpoint for fetching Astronomy Picture Of The Day",
            summary="This endpoint will fetch the latest Astronomy Picture Of The Day",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized/Invalid Token",
                            responseCode = "403"
                    )
            }
    )
    @GetMapping("/apod")
    public NasaApod getApod()
    {
        return nasaApiService.getAstronomyPictureOfTheDay();
    }

    @Operation(
            description = "Get endpoint for fetching all Astronomy Pictures Of The Day",
            summary="This endpoint will fetch a list of Astronomy Pictures Of The Day",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized/Invalid Token",
                            responseCode = "403"
                    )
            }
    )
    @GetMapping(value = "/apods",produces= MediaType.APPLICATION_JSON_VALUE)
    public List<NasaApod> getAllApods()
    {
        return nasaApiService.fetchAllApods();
    }

    @Operation(
            description = "Get endpoint for saving Astronomy Picture Of The Day In The Database",
            summary="This endpoint will save the latest Astronomy Picture Of The Day In The Database",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized/Invalid Token",
                            responseCode = "403"
                    )
            }
    )
    @GetMapping("/save-apod")
    public String saveApod()
    {
        // fetching nasaApod //
        NasaApod theNasaApod=nasaApiService.getAstronomyPictureOfTheDay();
        nasaApiService.save(theNasaApod);
        return "Successfully Saved"+"\n"+"Title: "+theNasaApod.getTitle()+"\n"+"Date: "+theNasaApod.getDate();
    }

    @Operation(
            description = "Get endpoint for fetching Astronomy Picture Of The Day Using Id",
            summary="This endpoint will fetch the latest Astronomy Picture Of The Day based on the given Id",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized/Invalid Token",
                            responseCode = "403"
                    )
            }
    )
    @GetMapping("/apod/{apodId}")
    public NasaApod getApodById(@PathVariable Integer apodId)
    {
        return nasaApiService.findNasaApodById(apodId);
    }

    @Operation(
            description = "Delete endpoint for deleting Astronomy Picture Of The Day By Id",
            summary="This endpoint will delete the Astronomy Picture Of The Day based on the given Id",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized/Invalid Token",
                            responseCode = "403"
                    )
            }
    )
    @DeleteMapping("/apod/{apodId}")
    public String deleteApodById(@PathVariable Integer apodId)
    {
        nasaApiService.deleteNasaApodById(apodId);
        return "Delete Nasa Apod Id: "+apodId;
    }

    @Operation(
            description = "Put endpoint for updating Astronomy Picture Of The Day",
            summary="This endpoint will update the Astronomy Picture Of The Day based on the given Id",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized/Invalid Token",
                            responseCode = "403"
                    )
            }
    )
    @PutMapping("/apod/{apodId}")
    public String updateApod(@PathVariable Integer apodId,@RequestBody NasaApod theNasaApod)
    {
        NasaApod existingApod=nasaApiService.findNasaApodById(apodId);
        if(existingApod==null)
        {
            throw new NasaNotFoundException("Apod Not Found With Id: "+apodId);
        }
        else {
            existingApod.setTitle(theNasaApod.getTitle());
            existingApod.setExplanation(theNasaApod.getExplanation());
            nasaApiService.save(existingApod);
            return "Updated Nasa Apod Id: "+apodId;
        }
    }


    // MARS ROVER API SERVICE //
    // CAMERA LOGIC //
    // a boolean static flag for camera //
    private static boolean flag=false;

    public static void roverCameras(String roverCamera)
    {

        String roverCameras[]={"fhaz", "rhaz", "mast", "chemcam", "mahli", "mardi", "navcam", "pancam", "minites"};
        for(String itt:roverCameras)
        {
            if(itt.equalsIgnoreCase(roverCamera))
            {
                flag=true;
                break;
            }
        }

    }

   // endpoint for rovername, earthdate, rovercamera //
   @Operation(
           description = "This endpoint will fetch the Nasa's Mars Rover Photos.\nDetails are given below( have a look )\n" +
                   "A.) There are 3 Mars Rovers\n"+
                   "    1.) Curiosity\n" +
                   "    2.) Spirit\n" +
                   "    3.) Opportunity\n" +
                   "B.) There are 9 Cameras For These Rovers\n" +
                   "    1.) FHAZ-> Front Hazard Avoidance Camera\n" +
                   "    2.) RHAZ-> Rear Hazard Avoidance Camera\n" +
                   "    3.) MAST-> Mast Camera\n" +
                   "    4.) CHEMCAM-> Chemistry and Camera Complex\n" +
                   "    5.) MAHLI-> Mars Hand Lens Imager\n" +
                   "    6.) MARDI-> Mars Descent Imager\n" +
                   "    7.) NAVCAM-> Navigation Camera\n" +
                   "    8.) PANCAM-> Panoramic Camera\n" +
                   "    9.) MINITES-> Miniature Thermal Emission Spectrometer (Mini-TES)\n" +
                   "C.) Earth Date Is In The Form Of YYYY/MM/DD\n" +
                   "D.) You can fetch Mars photos based on the rovername, earthdate, rovercamera.\n" +
                   "    Ex:- /rover/curiosity/2015-06-03/fhaz",
           summary="Get endpoint for fetching Nasa's Mars Rover Photos",
           responses = {

                   @ApiResponse(
                           description = "Success",
                           responseCode = "200"
                   ),
                   @ApiResponse(
                           description = "Unauthorized/Invalid Token",
                           responseCode = "403"
                   )
           }
   )
    @GetMapping("/rover/{roverName}/{earthDate}/{roverCamera}")
    public MarsRoverPhotosResponse getRoverPhotos(@PathVariable("roverName")String roverName,@PathVariable("earthDate") String earthDate,@PathVariable("roverCamera")String roverCamera) {
        roverCameras(roverCamera);
        MarsRoverPhotosResponse theMarsRoverPhotosResponse = null;
        if (flag == true) {
            theMarsRoverPhotosResponse = nasaApiService.getRoverPhotos(roverName.toLowerCase(), earthDate, roverCamera);
            return theMarsRoverPhotosResponse;
        }
        else {
            throw new NasaNotFoundException(roverCamera+" Camera Does Not Exist");
        }

    }
}
