package com.gambit.games.feature.playersandgames.ui;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gambit.games.R;
import com.gambit.games.databinding.AdapterPlayersFragmentBinding;
import com.gambit.games.feature.playersandgames.model.player.GetAllPlayerInfo;
import com.gambit.games.feature.playersandgames.usercase.OnLoadMoreListener;

import java.util.List;

public class PlayersFragmentAdapter extends RecyclerView.Adapter<PlayersFragmentAdapter.ViewHolder> {

    private Activity activity;
    private List<GetAllPlayerInfo> getAllPlayerInfoList;

    private AdapterPlayersFragmentBinding adapterPlayersFragmentBinding;
    private LayoutInflater layoutInflater;

    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    private boolean isLoading = false;
    private boolean scrollSearch = true;

    private OnLoadMoreListener onLoadMoreListener;


    public PlayersFragmentAdapter(@NonNull RecyclerView recyclerView, @NonNull Activity activity) {
        this.activity = activity;


        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (scrollSearch) {
                    if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                        if (onLoadMoreListener != null) {
                            onLoadMoreListener.onLoadMore();
                        }
                        isLoading = true;
                    }
                }

            }
        });
    }


    public void loadPlayerData(@NonNull List<GetAllPlayerInfo> getAllPlayerInfo) {
        this.getAllPlayerInfoList = getAllPlayerInfo;
    }

    public void setLazyLoading(boolean scrollSearch) {
        this.scrollSearch = scrollSearch;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.onLoadMoreListener = mOnLoadMoreListener;
    }

    public void setLoaded() {
        isLoading = false;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(activity);
        adapterPlayersFragmentBinding = DataBindingUtil.inflate(layoutInflater,
                R.layout.adapter_players_fragment, viewGroup, false);

        return new PlayersFragmentAdapter.ViewHolder(adapterPlayersFragmentBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.adapterPlayersFragmentBinding.playerAdapterFirstName.setText(getAllPlayerInfoList.get(position).getFirstName());
        holder.adapterPlayersFragmentBinding.playerAdapterLastName.setText(getAllPlayerInfoList.get(position).getLastName());
        holder.adapterPlayersFragmentBinding.playerAdapterTeamName.setText(getAllPlayerInfoList.get(position).getTeam().getName());
        holder.adapterPlayersFragmentBinding.playerAdapterTeamId.setText("#" + getAllPlayerInfoList.get(position).getTeam().getId());
        holder.adapterPlayersFragmentBinding.adapterPlayerPosition.setText("Position - " + getAllPlayerInfoList.get(position).getPosition());
        holder.adapterPlayersFragmentBinding.playerAdapterTeamFullName.setText(getAllPlayerInfoList.get(position).getTeam().getFullName());

    }

    @Override
    public int getItemCount() {
        return getAllPlayerInfoList == null ? 0 : getAllPlayerInfoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private AdapterPlayersFragmentBinding adapterPlayersFragmentBinding;

        public ViewHolder(@NonNull AdapterPlayersFragmentBinding adapterPlayersFragmentBinding) {
            super(adapterPlayersFragmentBinding.getRoot());
            this.adapterPlayersFragmentBinding = adapterPlayersFragmentBinding;
        }
    }
}
