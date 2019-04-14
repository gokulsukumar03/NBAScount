package com.gambit.games.feature.playersandgames.model.player;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GetAllPlayerTeam implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("abbreviation")
    @Expose
    private String abbreviation;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("conference")
    @Expose
    private String conference;
    @SerializedName("division")
    @Expose
    private String division;
    @SerializedName("full_name")
    @Expose
    private String fullName;
    @SerializedName("name")
    @Expose
    private String name;
    private final static long serialVersionUID = -7611527214809946647L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getConference() {
        return conference;
    }

    public void setConference(String conference) {
        this.conference = conference;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}