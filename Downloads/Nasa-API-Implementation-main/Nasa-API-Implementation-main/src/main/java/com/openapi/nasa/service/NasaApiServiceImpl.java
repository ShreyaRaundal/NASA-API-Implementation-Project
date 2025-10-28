package com.openapi.nasa.service;

import com.openapi.nasa.daorepo.NasaRepository;
import com.openapi.nasa.entity.NasaApod;
import com.openapi.nasa.exceptionHandler.NasaNotFoundException;
import com.openapi.nasa.response.MarsRoverPhotosResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;


@Service
public class NasaApiServiceImpl implements NasaApiService{
    // save nasa-apikey in a container //
    @Value("${nasa.api.key}")
    private String apiKey;
    // save api-apod-url
    private String apiApodUrl="https://api.nasa.gov/planetary/apod?api_key={apiKey}";

    // save api-mars-rover-urls //
    private String baseUrl="https://api.nasa.gov/mars-photos/api/v1/rovers/";
    private String fullUrl="/photos?earth_date=";
    private String roverCamera="&camera=";





    // injecting ApodRepository and restTemplate //
    private NasaRepository theNasaRepository;
    private RestTemplate theRestTemplate;

    @Autowired
    public NasaApiServiceImpl(RestTemplate theRestTemplate, NasaRepository theNasaRepository) {
        this.theRestTemplate = theRestTemplate;
        this.theNasaRepository = theNasaRepository;
    }

    // Apod-Service Methods //
    @Override
    public NasaApod getAstronomyPictureOfTheDay() {
        return theRestTemplate.getForObject(apiApodUrl, NasaApod.class,apiKey);
    }

    @Override
    public void deleteAllApods() {
        theNasaRepository.deleteAll();
    }

    @Override
    @Transactional
    public void save(NasaApod theNasaApod) {
        theNasaRepository.save(theNasaApod);
    }

    @Override
    public List<NasaApod> fetchAllApods() {
        List<NasaApod> apods= theNasaRepository.findAll();
        if(apods.isEmpty())
        {
            throw new NasaNotFoundException("No Apods Found. Try Adding Apod To The Database");
        }
        else {
            return apods;
        }
    }

    @Override
    public NasaApod findNasaApodById(Integer id) {
        Optional<NasaApod> result= theNasaRepository.findById(id);
        NasaApod foundNasaApod=null;
        if(result.isPresent())
        {
            foundNasaApod=result.get();
        }
        else {
            throw new NasaNotFoundException("No Apod Found With Id: "+id);
        }
        return foundNasaApod;
    }

    @Override
    @Transactional
    public void deleteNasaApodById(Integer id) {
        Optional<NasaApod> result= theNasaRepository.findById(id);
        NasaApod foundNasaApod=null;
        if(result.isPresent())
        {
            foundNasaApod=result.get();
        }
        else {
            throw new NasaNotFoundException("No Apod Found With Id: "+id);
        }
        theNasaRepository.deleteById(id);
    }

    @Override
    public List<NasaApod> fetchAllApodsMVC() {
        return theNasaRepository.findAll();
    }

    @Override
    public MarsRoverPhotosResponse getRoverPhotos(String roverType, String earthDate, String camera) {

        String apiUrl=baseUrl+roverType+fullUrl+earthDate+roverCamera+camera+"&api_key="+apiKey;
        return theRestTemplate.getForObject(apiUrl, MarsRoverPhotosResponse.class);
    }


}
