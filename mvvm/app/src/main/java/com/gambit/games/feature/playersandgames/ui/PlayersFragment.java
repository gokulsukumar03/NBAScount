package com.gambit.games.feature.playersandgames.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gambit.games.R;
import com.gambit.games.application.config.RemoteServiceConfig;
import com.gambit.games.databinding.FragmentPlayersBinding;
import com.gambit.games.feature.playersandgames.constant.PlayersAndGamesConstant;
import com.gambit.games.feature.playersandgames.model.player.GetAllPlayerInfo;
import com.gambit.games.feature.playersandgames.model.player.GetAllPlayersResponseModel;
import com.gambit.games.feature.playersandgames.remote.players.PlayersFragmentFactory;
import com.gambit.games.feature.playersandgames.remote.players.PlayersFragmentGetAllPlayersCallback;
import com.gambit.games.feature.playersandgames.remote.players.PlayersFragmentSearchCallback;
import com.gambit.games.feature.playersandgames.remote.players.PlayersFragmentService;
import com.gambit.games.feature.playersandgames.remote.players.PlayersFragmentServiceImpl;
import com.gambit.games.feature.playersandgames.usercase.OnLoadMoreListener;

import java.io.File;
import java.util.List;
import java.util.Objects;


public class PlayersFragment extends Fragment {

    private PlayersFragmentService playersFragmentService = null;
    private PlayersFragmentFactory playersFragmentFactory = null;

    private PlayersFragmentAdapter playersFragmentAdapter = null;
    private LinearLayoutManager linearLayoutManager = null;


    private FragmentPlayersBinding fragmentPlayersBinding = null;
    private View view = null;
    private File cacheFile = null;

    private int totalPages=0;
    private int currentPage = 0;
    private int nextPage = 0;
    private int totalCount=0;
    private int pageNumber = PlayersAndGamesConstant.DEFAULT_PAGE_NUMBER;
    private int itemPerPage = PlayersAndGamesConstant.ITEM_PER_PAGE_LIMIT;


    private List<GetAllPlayerInfo> getAllPlayerInfoList;


    public PlayersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentPlayersBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_players, container, false);
        view = fragmentPlayersBinding.getRoot();

        searchAllPlayers();
        getAllPlayers();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        fragmentPlayersBinding.playersSearchView.onActionViewExpanded();
        fragmentPlayersBinding.playersSearchView.clearFocus();
        fragmentPlayersBinding.playersRecyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        fragmentPlayersBinding.playersRecyclerView.setLayoutManager(linearLayoutManager);
        playersFragmentAdapter = new PlayersFragmentAdapter(
                fragmentPlayersBinding.playersRecyclerView,
                Objects.requireNonNull(getActivity()));


        playersFragmentAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore() {
                    if (getAllPlayerInfoList != null && getAllPlayerInfoList.size() > 0) {
                        if (totalPages == currentPage) {
                            Toast.makeText(getActivity(), "No more data..." , Toast.LENGTH_SHORT).show();
                        }
                        else {
                        cacheFile = new File(getActivity().getCacheDir(), "responses");
                        playersFragmentFactory = new RemoteServiceConfig<PlayersFragmentFactory>().createApiService(PlayersFragmentFactory.class, cacheFile);
                        playersFragmentService = new PlayersFragmentServiceImpl();
                        playersFragmentService.getAllPlayers(
                                nextPage,
                                itemPerPage,
                                playersFragmentFactory,
                                new PlayersFragmentGetAllPlayersCallback() {
                                    @Override
                                    public void onResponse(GetAllPlayersResponseModel getAllPlayersResponseModel) {
                                        if (getAllPlayersResponseModel.getData().size() > 0) {

                                                totalPages = getAllPlayersResponseModel.getMeta().getTotalPages();
                                                currentPage = getAllPlayersResponseModel.getMeta().getCurrentPage();
                                                nextPage = getAllPlayersResponseModel.getMeta().getNextPage();
                                                itemPerPage = getAllPlayersResponseModel.getMeta().getPerPage();
                                                totalCount = getAllPlayersResponseModel.getMeta().getTotalCount();
                                                getAllPlayerInfoList.addAll(getAllPlayersResponseModel.getData());
                                                ((PlayersAndGamesActivity) Objects.requireNonNull(getActivity())).updateTabText(0, String.valueOf(getAllPlayerInfoList.size()));
                                                playersFragmentAdapter.notifyDataSetChanged();
                                                playersFragmentAdapter.setLoaded();
                                                if(getActivity()!=null)
                                                    Toast.makeText(getActivity(), "More data fetched...", Toast.LENGTH_SHORT).show();

                                        }
                                    }

                                    @Override
                                    public void onError(String errorMessage) {
                                        Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
                                    }
                                }
                        );
                    }

                    }

                }
            });




    }
    private void getAllPlayers() {
        if(getActivity()!=null)
            Toast.makeText(getActivity(), "Fetching games...", Toast.LENGTH_SHORT).show();
        cacheFile = new File(getActivity().getCacheDir(), "responses");
        playersFragmentFactory = new RemoteServiceConfig<PlayersFragmentFactory>().createApiService(PlayersFragmentFactory.class, cacheFile);
        playersFragmentService = new PlayersFragmentServiceImpl();
        playersFragmentService.getAllPlayers(
                pageNumber,
                itemPerPage,
                playersFragmentFactory,
                new PlayersFragmentGetAllPlayersCallback() {
                    @Override
                    public void onResponse(GetAllPlayersResponseModel getAllPlayersResponseModel) {

                        totalPages = getAllPlayersResponseModel.getMeta().getTotalPages();
                        currentPage = getAllPlayersResponseModel.getMeta().getCurrentPage();
                        nextPage = getAllPlayersResponseModel.getMeta().getNextPage();
                        itemPerPage = getAllPlayersResponseModel.getMeta().getPerPage();
                        totalCount = getAllPlayersResponseModel.getMeta().getTotalCount();

                        if (getAllPlayersResponseModel.getData().size() > 0) {
                            getAllPlayerInfoList = getAllPlayersResponseModel.getData();
                            playersFragmentAdapter.loadPlayerData(getAllPlayersResponseModel.getData());
                            playersFragmentAdapter.setLazyLoading(true);
                            ((PlayersAndGamesActivity) Objects.requireNonNull(getActivity())).updateTabText(0, String.valueOf(getAllPlayersResponseModel.getData().size()));
                            fragmentPlayersBinding.playersRecyclerView.setAdapter(playersFragmentAdapter);
                        }
                    }

                    @Override
                    public void onError(String errorMessage) {
                        Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
                    }
                }
        );

    }

    private void searchAllPlayers() {
        fragmentPlayersBinding.playersSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Log.d(" !!!  finish", s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Log.d(" !!!  ongoing", s);
                if (s.length() > 2) {
                    cacheFile = new File(getActivity().getCacheDir(), "responses");
                    playersFragmentFactory = new RemoteServiceConfig<PlayersFragmentFactory>().createApiService(PlayersFragmentFactory.class, cacheFile);
                    playersFragmentService = new PlayersFragmentServiceImpl();
                    playersFragmentService.getAllSearchPlayers(s, playersFragmentFactory, new PlayersFragmentSearchCallback() {
                        @Override
                        public void onResponse(GetAllPlayersResponseModel getAllPlayersResponseModel) {
                            if (getAllPlayersResponseModel.getData().size() > 0) {

                                    ((PlayersAndGamesActivity) Objects.requireNonNull(getActivity())).updateTabText(0, String.valueOf(getAllPlayersResponseModel.getData().size()));
                                    playersFragmentAdapter.notifyDataSetChanged();
                                    playersFragmentAdapter.loadPlayerData(getAllPlayersResponseModel.getData());
                                    playersFragmentAdapter.setLazyLoading(false);
                                    fragmentPlayersBinding.playersRecyclerView.setAdapter(playersFragmentAdapter);
                                    Toast.makeText(getActivity(), "Search completed...", Toast.LENGTH_SHORT).show();
                                }
                        }

                        @Override
                        public void onError(String errorMessage) {

                        }

                    });
                }
                return false;
            }
        });

    }


}
