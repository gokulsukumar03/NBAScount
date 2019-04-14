package com.gambit.games.feature.playersandgames.ui;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.TooltipCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gambit.games.R;
import com.gambit.games.databinding.AdapterGamesFragmentBinding;
import com.gambit.games.databinding.AdapterPlayersFragmentBinding;
import com.gambit.games.feature.playersandgames.model.games.GetAllGamesInfo;
import com.gambit.games.feature.playersandgames.model.player.GetAllPlayerInfo;

import java.util.List;

public class GamesFragmentAdapter extends RecyclerView.Adapter<GamesFragmentAdapter.ViewHolder> {

    private Activity activity;
    private List<GetAllGamesInfo> getAllGamesInfoList;

    private AdapterGamesFragmentBinding adapterGamesFragmentBinding;
    private LayoutInflater layoutInflater;


    public GamesFragmentAdapter(@NonNull Activity activity, @NonNull List<GetAllGamesInfo> getAllPlayerInfo){
        this.activity = activity;
        this.getAllGamesInfoList =getAllPlayerInfo;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(layoutInflater==null)
            layoutInflater = LayoutInflater.from(activity);
        adapterGamesFragmentBinding = DataBindingUtil.inflate(layoutInflater,
                    R.layout.adapter_games_fragment,viewGroup,false);

            return new GamesFragmentAdapter.ViewHolder(adapterGamesFragmentBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.adapterGamesFragmentBinding.gamesAdapterHomeAbbreviation.setText(getAllGamesInfoList.get(position).getHomeTeam().getAbbreviation());
        holder.adapterGamesFragmentBinding.gamesAdapterHomeFullName.setText(getAllGamesInfoList.get(position).getHomeTeam().getFullName());
        holder.adapterGamesFragmentBinding.gamesAdapterVisitorAbbreviation.setText(getAllGamesInfoList.get(position).getVisitorTeam().getAbbreviation());
        holder.adapterGamesFragmentBinding.gamesAdapterVisitorFullName.setText(getAllGamesInfoList.get(position).getVisitorTeam().getFullName());


    }

    @Override
    public int getItemCount() {
        return getAllGamesInfoList ==null  ? 0 : getAllGamesInfoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private AdapterGamesFragmentBinding adapterGamesFragmentBinding;

        public ViewHolder(@NonNull AdapterGamesFragmentBinding adapterGamesFragmentBinding) {
            super(adapterGamesFragmentBinding.getRoot());
            this.adapterGamesFragmentBinding = adapterGamesFragmentBinding;
        }
    }
}
