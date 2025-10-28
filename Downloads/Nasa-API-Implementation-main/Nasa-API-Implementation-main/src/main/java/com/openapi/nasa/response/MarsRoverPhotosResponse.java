package com.openapi.nasa.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.openapi.nasa.model.MarsRoverPhoto;

import java.util.List;

public class MarsRoverPhotosResponse {
    // response class for mars rover //

    // define field //
    @JsonProperty("photos")
    private List<MarsRoverPhoto>  marsRoverPhotosList;
    // getter and setter //


    public List<MarsRoverPhoto> getMarsRoverPhotosList() {
        return marsRoverPhotosList;
    }

    public void setMarsRoverPhotosList(List<MarsRoverPhoto> marsRoverPhotosList) {
        this.marsRoverPhotosList = marsRoverPhotosList;
    }

    // consts //

    public MarsRoverPhotosResponse(List<MarsRoverPhoto> marsRoverPhotosList) {
        this.marsRoverPhotosList = marsRoverPhotosList;
    }

    public MarsRoverPhotosResponse()
    {

    }



}
