package com.gambit.games.feature.playersandgames.model.player;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAllPlayersResponseModel implements Serializable
{

    @SerializedName("data")
    @Expose
    private List<GetAllPlayerInfo> data = null;
    @SerializedName("meta")
    @Expose
    private GetAllPlayerMeta meta;
    private final static long serialVersionUID = -2613125685515023398L;

    public List<GetAllPlayerInfo> getData() {
        return data;
    }

    public void setData(List<GetAllPlayerInfo> data) {
        this.data = data;
    }

    public GetAllPlayerMeta getMeta() {
        return meta;
    }

    public void setMeta(GetAllPlayerMeta meta) {
        this.meta = meta;
    }

}