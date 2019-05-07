package com.gambit.games.feature.playersandgames.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gambit.games.R;
import com.gambit.games.application.config.RemoteServiceConfig;
import com.gambit.games.databinding.FragmentGamesBinding;
import com.gambit.games.feature.playersandgames.model.games.GetAllGamesResponseModel;
import com.gambit.games.feature.playersandgames.remote.games.GamesFragmentFactory;
import com.gambit.games.feature.playersandgames.remote.games.GamesFragmentGellAllGamesCallback;
import com.gambit.games.feature.playersandgames.remote.games.GamesFragmentService;
import com.gambit.games.feature.playersandgames.remote.games.GamesFragmentServiceImpl;

import java.io.File;
import java.util.Objects;


public class GamesFragment extends Fragment {

    private GamesFragmentService gamesFragmentService = null;
    private GamesFragmentFactory gamesFragmentFactory = null;

    private GamesFragmentAdapter gamesFragmentAdapter = null;
    private LinearLayoutManager linearLayoutManager = null;


    private FragmentGamesBinding fragmentGamesBinding = null;
    private View view = null;
    private File cacheFile = null;

    public GamesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentGamesBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_games, container, false);
        view = fragmentGamesBinding.getRoot();
        getAllGames();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void getAllGames() {
        if(getActivity()!=null)
        Toast.makeText(getActivity(), "Fetching games...", Toast.LENGTH_SHORT).show();
        cacheFile = new File(getActivity().getCacheDir(), "responses");
        gamesFragmentFactory = new RemoteServiceConfig<GamesFragmentFactory>().createApiService(GamesFragmentFactory.class, cacheFile);
        gamesFragmentService = new GamesFragmentServiceImpl();

        gamesFragmentService.getAllGames(gamesFragmentFactory,
                new GamesFragmentGellAllGamesCallback() {
                    @Override
                    public void onSuccess(GetAllGamesResponseModel getAllGamesResponseModel) {


                            fragmentGamesBinding.gamesRecyclerView.setHasFixedSize(true);
                            linearLayoutManager = new LinearLayoutManager(getActivity());
                            fragmentGamesBinding.gamesRecyclerView.setLayoutManager(linearLayoutManager);
                            gamesFragmentAdapter = new GamesFragmentAdapter(
                                    Objects.requireNonNull(getActivity()),
                                    getAllGamesResponseModel.getData());
                            fragmentGamesBinding.gamesRecyclerView.setAdapter(gamesFragmentAdapter);
                            ((PlayersAndGamesActivity) Objects.requireNonNull(getActivity())).updateTabText(1, String.valueOf(getAllGamesResponseModel.getData().size()));


                    }

                    @Override
                    public void onError(String errorMessage) {
                        Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
                    }
                });

    }
}
