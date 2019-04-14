package com.gambit.games.feature.playersandgames.model.games;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAllGamesResponseModel implements Serializable
{


        @SerializedName("data")
        @Expose
        private List<GetAllGamesInfo> data = null;
        @SerializedName("meta")
        @Expose
        private GetAllGamesMeta meta;
        private final static long serialVersionUID = -1252117602972629815L;

        public List<GetAllGamesInfo> getData() {
        return data;
    }

        public void setData(List<GetAllGamesInfo> data) {
        this.data = data;
    }

        public GetAllGamesMeta getMeta() {
        return meta;
    }

        public void setMeta(GetAllGamesMeta meta) {
        this.meta = meta;
    }
}