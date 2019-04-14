package com.gambit.games.feature.playersandgames.model.player;
import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAllPlayerMeta implements Serializable
{

    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    @SerializedName("current_page")
    @Expose
    private Integer currentPage;
    @SerializedName("next_page")
    @Expose
    private Integer nextPage;
    @SerializedName("per_page")
    @Expose
    private Integer perPage;
    @SerializedName("total_count")
    @Expose
    private Integer totalCount;
    private final static long serialVersionUID = 6864611862379922761L;

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getNextPage() {
        return nextPage;
    }

    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }

    public Integer getPerPage() {
        return perPage;
    }

    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

}
