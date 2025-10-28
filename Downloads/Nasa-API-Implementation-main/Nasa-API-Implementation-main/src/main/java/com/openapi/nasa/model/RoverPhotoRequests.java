package com.openapi.nasa.model;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public class RoverPhotoRequests {
    // PHOTO REQUEST FOR MVC CONTROLLER //
    private String roverType;
    private List<String> roverCameras;

    @NotNull(message = "is required")
    private String earthDate;

    // getters and setters //

    public String getRoverType() {
        return roverType;
    }

    public void setRoverType(String roverType) {
        this.roverType = roverType;
    }

    public List<String> getRoverCameras() {
        return roverCameras;
    }

    public void setRoverCameras(List<String> roverCameras) {
        this.roverCameras = roverCameras;
    }

    public String getEarthDate() {
        return earthDate;
    }

    public void setEarthDate(String earthDate) {
        this.earthDate = earthDate;
    }
}
