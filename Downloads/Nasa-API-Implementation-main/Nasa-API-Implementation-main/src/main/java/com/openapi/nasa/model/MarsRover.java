package com.openapi.nasa.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MarsRover {
    // define fields //

    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String roverName;

    @JsonProperty("landing_date")
    private String roverLandingDate;

    @JsonProperty("launch_date")
    private String roverLaunchDate;

    @JsonProperty("status")
    private String roverStatus;

    @JsonProperty("max_sol")
    private long maxSol;

    @JsonProperty("max_date")
    private String maxDate;

    @JsonProperty("total_photos")
    private long totalPhotos;

    @JsonProperty("cameras")
    private List<MarsRoverCamera> roverCameras;

    // define gettters and setters //

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoverName() {
        return roverName;
    }

    public void setRoverName(String roverName) {
        this.roverName = roverName;
    }

    public String getRoverLandingDate() {
        return roverLandingDate;
    }

    public void setRoverLandingDate(String roverLandingDate) {
        this.roverLandingDate = roverLandingDate;
    }

    public String getRoverLaunchDate() {
        return roverLaunchDate;
    }

    public void setRoverLaunchDate(String roverLaunchDate) {
        this.roverLaunchDate = roverLaunchDate;
    }

    public String getRoverStatus() {
        return roverStatus;
    }

    public void setRoverStatus(String roverStatus) {
        this.roverStatus = roverStatus;
    }

    public long getMaxSol() {
        return maxSol;
    }

    public void setMaxSol(long maxSol) {
        this.maxSol = maxSol;
    }

    public String getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(String maxDate) {
        this.maxDate = maxDate;
    }

    public long getTotalPhotos() {
        return totalPhotos;
    }

    public void setTotalPhotos(long totalPhotos) {
        this.totalPhotos = totalPhotos;
    }

    public List<MarsRoverCamera> getRoverCameras() {
        return roverCameras;
    }

    public void setRoverCameras(List<MarsRoverCamera> roverCameras) {
        this.roverCameras = roverCameras;
    }

    // define consts //

    public MarsRover(int id, String roverName, String roverLandingDate, String roverLaunchDate, String roverStatus, long maxSol, String maxDate, long totalPhotos, List<MarsRoverCamera> roverCameras) {
        this.id = id;
        this.roverName = roverName;
        this.roverLandingDate = roverLandingDate;
        this.roverLaunchDate = roverLaunchDate;
        this.roverStatus = roverStatus;
        this.maxSol = maxSol;
        this.maxDate = maxDate;
        this.totalPhotos = totalPhotos;
        this.roverCameras = roverCameras;
    }

    public MarsRover()
    {

    }
}
