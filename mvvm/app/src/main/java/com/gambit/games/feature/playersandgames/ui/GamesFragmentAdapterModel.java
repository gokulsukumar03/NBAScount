package com.gambit.games.feature.playersandgames.ui;

public class GamesFragmentAdapterModel {

    private String homeAbbreviation;
    private String homeFullName;

    private String visitorAbbreviation;
    private String visitorFullName;


    public String getHomeAbbreviation() {
        return homeAbbreviation;
    }

    public void setHomeAbbreviation(String homeAbbreviation) {
        this.homeAbbreviation = homeAbbreviation;    }

    public String getHomeFullName() {
        return homeFullName;
    }

    public void setHomeFullName(String homeFullName) {
        this.homeFullName = homeFullName;
    }

    public String getVisitorAbbreviation() {
        return visitorAbbreviation;
    }

    public void setVisitorAbbreviation(String visitorAbbreviation) {
        this.visitorAbbreviation = visitorAbbreviation;
    }

    public String getVisitorFullName() {
        return visitorFullName;
    }

    public void setVisitorFullName(String visitorFullName) {
        this.visitorFullName = visitorFullName;
    }
}
