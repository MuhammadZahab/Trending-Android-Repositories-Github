package remote.codingsample.trendingandroidrepos.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import remote.codingsample.trendingandroidrepos.Activities.RepoDetailActivity;
import remote.codingsample.trendingandroidrepos.Adapters.ReposListAdapter;
import remote.codingsample.trendingandroidrepos.Models.Item;
import remote.codingsample.trendingandroidrepos.Models.ReposList;
import remote.codingsample.trendingandroidrepos.R;
import remote.codingsample.trendingandroidrepos.Util.ApiUtils;
import remote.codingsample.trendingandroidrepos.Util.SOService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ReposListFragment extends Fragment {

    /* Purpose to create this fragment instead of loading data directly into Activity is that this Fragment
    *  will be ReUseabel for other activities where we want to show the list of Android Repositories.
    *  We dont need to do this work done again and again just need to attach this Fragment in any Activity to load
    *  list of Android Repositories.*/

    private ReposListAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private SOService mService;
    private ProgressDialog progressDialog;



    public ReposListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view  =  inflater.inflate(R.layout.fragment_repos_list, container, false);
        mService         =  ApiUtils.getSOService();
        mRecyclerView    = (RecyclerView) view.findViewById(R.id.rv_repolist);
        progressDialog   =  new ProgressDialog(getActivity());

        manageRecylerViewAdapter();
        loadReposList();

        return  view;

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    private void manageRecylerViewAdapter(){
           mAdapter         =  new ReposListAdapter(getActivity(), new ArrayList<Item>(0), new ReposListAdapter.ReposItemListener() {

            @Override
            public void onRepoClick(Item item) {
                goToRepDetailsScreen(item);

            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);

    }

    private void loadReposList() {
        showProgressbar();

        mService.getRepostList().enqueue(new Callback<ReposList>() {
            @Override
            public void onResponse(Call<ReposList> call, Response<ReposList> response) {

                if(response.isSuccessful()) {
                    hideProgressbar();
                    mAdapter.updateReposList(response.body().getItems());

                }else {
                    // handle request errors depending on status code
                    hideProgressbar();
                    int statusCode  = response.code();
                }
            }

            @Override
            public void onFailure(Call<ReposList> call, Throwable t) {
                // Need to show Error message here
                hideProgressbar();

            }
        });
    }

    private void showProgressbar(){
        progressDialog.show();
        progressDialog.setMessage(getString(R.string.loading_message));
    }
    private void hideProgressbar(){
        progressDialog.hide();
        progressDialog.cancel();

    }

    private void goToRepDetailsScreen(Item item)
    {
        Intent intent = new Intent(getActivity(), RepoDetailActivity.class);
        intent.putExtra("parcel_data", item);

        startActivity(intent);
    }


}
