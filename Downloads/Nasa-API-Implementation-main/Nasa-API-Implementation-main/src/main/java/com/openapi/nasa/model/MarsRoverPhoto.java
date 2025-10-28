package com.openapi.nasa.model;

import com.fasterxml.jackson.annotation.JsonProperty;

// MODEL CLASS FOR MARS ROVER PHOTO //
public class MarsRoverPhoto {
    // defined fields//
    @JsonProperty("id")
    private long id;

    @JsonProperty("sol")
    private long sol;

    @JsonProperty("camera")
    private MarsRoverCamera marsRoverCamera;

    @JsonProperty("img_src")
    private String imageSource;

    @JsonProperty("earth_date")
    private String earthDate;

    @JsonProperty("rover")
    private MarsRover marsRover;

    // defined getters and setters //

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSol() {
        return sol;
    }

    public void setSol(long sol) {
        this.sol = sol;
    }

    public MarsRoverCamera getMarsRoverCamera() {
        return marsRoverCamera;
    }

    public void setMarsRoverCamera(MarsRoverCamera marsRoverCamera) {
        this.marsRoverCamera = marsRoverCamera;
    }

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

    public String getEarthDate() {
        return earthDate;
    }

    public void setEarthDate(String earthDate) {
        this.earthDate = earthDate;
    }

    public MarsRover getMarsRover() {
        return marsRover;
    }

    public void setMarsRover(MarsRover marsRover) {
        this.marsRover = marsRover;
    }


    // define consts //

    public MarsRoverPhoto(long id, long sol, MarsRoverCamera marsRoverCamera, String imageSource, String earthDate, MarsRover marsRover) {
        this.id = id;
        this.sol = sol;
        this.marsRoverCamera = marsRoverCamera;
        this.imageSource = imageSource;
        this.earthDate = earthDate;
        this.marsRover = marsRover;
    }

    public MarsRoverPhoto()
    {

    }
}
