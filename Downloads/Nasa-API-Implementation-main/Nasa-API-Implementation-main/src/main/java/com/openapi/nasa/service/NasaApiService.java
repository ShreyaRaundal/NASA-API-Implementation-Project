package com.openapi.nasa.service;

import com.openapi.nasa.entity.NasaApod;
import com.openapi.nasa.response.MarsRoverPhotosResponse;

import java.util.List;
// Nasa Api Services //
public interface NasaApiService {
// APOD API SERVICE //
    NasaApod getAstronomyPictureOfTheDay();
    void deleteAllApods();

    // saving apod CRUD's //
    void save(NasaApod theNasaApod);
    List<NasaApod> fetchAllApods();
    NasaApod findNasaApodById(Integer id);
    void deleteNasaApodById(Integer id);
    // convience method for fetchingAllApods for mvc controller //
    List<NasaApod> fetchAllApodsMVC();


// MARS ROVER API SERVICE //
    public MarsRoverPhotosResponse getRoverPhotos(String roverType, String earthDate, String camera);
}
