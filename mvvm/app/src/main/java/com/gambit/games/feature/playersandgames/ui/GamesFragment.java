package com.gambit.games.feature.playersandgames.ui;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gambit.games.R;
import com.gambit.games.application.config.RemoteServiceConfig;
import com.gambit.games.base.PlayersAndGamesComponent;
import com.gambit.games.databinding.FragmentGamesBinding;
import com.gambit.games.feature.playersandgames.model.games.GetAllGamesInfo;
import com.gambit.games.feature.playersandgames.model.games.GetAllGamesResponseModel;
import com.gambit.games.feature.playersandgames.remote.games.GamesFragmentFactory;
import com.gambit.games.feature.playersandgames.remote.games.GamesFragmentGellAllGamesCallback;
import com.gambit.games.feature.playersandgames.remote.games.GamesFragmentService;
import com.gambit.games.feature.playersandgames.remote.games.GamesFragmentServiceImpl;
import com.gambit.games.feature.playersandgames.usercase.GamesFragmentModel;
import com.gambit.games.feature.playersandgames.usercase.GamesFragmentViewModel;

import java.io.File;
import java.util.List;
import java.util.Objects;


public class GamesFragment extends Fragment {

    private GamesFragmentAdapter gamesFragmentAdapter = null;
    private LinearLayoutManager linearLayoutManager = null;
    private FragmentGamesBinding fragmentGamesBinding = null;
    private View view = null;
    private GamesFragmentViewModel gamesFragmentViewModel;
    private GamesFragmentViewModel.GamesFragmentViewModelFactory gamesFragmentViewModelFactory;
    private GamesFragmentModel gamesFragmentModel = new GamesFragmentModel();


    public GamesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentGamesBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_games, container, false);
        view = fragmentGamesBinding.getRoot();
        getLifecycle().addObserver(new PlayersAndGamesComponent());

        gamesFragmentViewModelFactory = new GamesFragmentViewModel.GamesFragmentViewModelFactory(getActivity().getApplication());
        gamesFragmentViewModel = ViewModelProviders.of(this, gamesFragmentViewModelFactory)
                .get(GamesFragmentViewModel.class);


        gamesFragmentViewModel.getAllGames().observe(this, new Observer<List<GetAllGamesInfo>>() {
            @Override
            public void onChanged(@Nullable List<GetAllGamesInfo> getAllGamesInfos) {
                fragmentGamesBinding.gamesRecyclerView.setHasFixedSize(true);
                linearLayoutManager = new LinearLayoutManager(getActivity());
                fragmentGamesBinding.gamesRecyclerView.setLayoutManager(linearLayoutManager);
                gamesFragmentAdapter = new GamesFragmentAdapter(
                        Objects.requireNonNull(getActivity()),
                        getAllGamesInfos);
                fragmentGamesBinding.gamesRecyclerView.setAdapter(gamesFragmentAdapter);
                ((PlayersAndGamesActivity) Objects.requireNonNull(getActivity())).updateTabText(1,
                        String.valueOf(getAllGamesInfos.size()));

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                alertDialog.setTitle("Game Dialog");
                alertDialog.setMessage("Hello Sukumar...");
                alertDialog.show();


            }
        });

        gamesFragmentViewModel.getToastMessage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String message) {
                printToast(message);
            }
        });


        gamesFragmentViewModel.getCurrentTime().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                gamesFragmentModel.setTimer(s);
                fragmentGamesBinding.setGamesFragmentModel(gamesFragmentModel);
            }
        });


        return view;
    }

    public void printToast(String message){
        if(message!=null && message.length()>0){
            Toast.makeText(getActivity(), message,Toast.LENGTH_SHORT).show();
        }
    }
}
