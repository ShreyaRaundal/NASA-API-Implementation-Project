
package com.openapi.nasa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Entity
@Table(name = "apod_db")
@Builder
public class NasaApod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Size(min = 1, message = "Is Required")
    @Column(name = "CopyRight")
    private String copyright;

    @NotNull(message = "Is Required")
    @Size(min = 1, message = "Is Required")
    @Column(name = "Date")
    private String date;

    @Lob
    @NotNull(message = "Is Required")
    @Size(min = 1, message = "Is Required")
    @Column(name = "Explanation", columnDefinition = "TEXT")
    private String explanation;

    @Size(min = 1, message = "Is Required")
    @Column(name = "HdUrl")
    private String hdurl;

    @NotNull(message = "Is Required")
    @Size(min = 1, message = "Is Required")
    @Column(name = "Title")
    private String title;

    @NotNull(message = "Is Required")
    @Size(min = 1, message = "Is Required")
    @Column(name = "URL")
    private String url;

    // === Required by JPA
    public NasaApod() {
    }

    // === Constructor without id (use this for creating new records)
    public NasaApod(String copyright,
                    String date,
                    String explanation,
                    String hdurl,
                    String title,
                    String url) {
        this.copyright = copyright;
        this.date = date;
        this.explanation = explanation;
        this.hdurl = hdurl;
        this.title = title;
        this.url = url;
    }

    // === Optional constructor with id (if you ever need to set id manually)
    public NasaApod(int id,
                    String copyright,
                    String date,
                    String explanation,
                    String hdurl,
                    String title,
                    String url) {
        this.id = id;
        this.copyright = copyright;
        this.date = date;
        this.explanation = explanation;
        this.hdurl = hdurl;
        this.title = title;
        this.url = url;
    }

    // === Getters & Setters ===
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCopyright() { return copyright; }
    public void setCopyright(String copyright) { this.copyright = copyright; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getExplanation() { return explanation; }
    public void setExplanation(String explanation) { this.explanation = explanation; }

    public String getHdurl() { return hdurl; }
    public void setHdurl(String hdurl) { this.hdurl = hdurl; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    @Override
    public String toString() {
        return "NasaApod{" +
                "id=" + id +
                ", copyright='" + copyright + '\'' +
                ", date='" + date + '\'' +
                ", explanation='" + explanation + '\'' +
                ", hdurl='" + hdurl + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
