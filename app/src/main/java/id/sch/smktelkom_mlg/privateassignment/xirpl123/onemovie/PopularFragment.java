package id.sch.smktelkom_mlg.privateassignment.xirpl123.onemovie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.privateassignment.xirpl123.onemovie.Adapter.PopularAdapter;
import id.sch.smktelkom_mlg.privateassignment.xirpl123.onemovie.Model.Popular;
import id.sch.smktelkom_mlg.privateassignment.xirpl123.onemovie.Model.PopularResponse;
import id.sch.smktelkom_mlg.privateassignment.xirpl123.onemovie.Service.GsonGetRequest;
import id.sch.smktelkom_mlg.privateassignment.xirpl123.onemovie.Service.VolleySingleton;


/**
 * Created by NA on 5/13/2017.
 */

public class PopularFragment extends Fragment {

    ArrayList<Popular> popular_mList = new ArrayList<>();
    PopularAdapter popular_mAdapter;

    public PopularFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_popular, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RecyclerView recyclerView= (RecyclerView) getView().findViewById(R.id.popularecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        popular_mAdapter = new PopularAdapter(this.getActivity(),popular_mList);
        recyclerView.setAdapter(popular_mAdapter);

        downloadData();
    }

    private void downloadData()
    {
        String url="https://api.themoviedb.org/3/movie/popular?api_key=e7a095ca67a422c6b365856d5c949367";

        GsonGetRequest<PopularResponse> myRequest = new GsonGetRequest<PopularResponse>
                (url, PopularResponse.class, null, new Response.Listener<PopularResponse>()
                {

                    @Override
                    public void onResponse(PopularResponse response)
                    {
                        Log.d("FLOW", "onResponse: " + (new Gson().toJson(response)));
                        if (response.page != 0)
                        {
                            popular_mList.addAll(response.results);
                            popular_mAdapter.notifyDataSetChanged();
                        }
                    }

                }, new Response.ErrorListener()
                {

                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Log.e("FLOW", "onErrorResponse: ", error);
                    }
                });
        VolleySingleton.getInstance(this.getActivity()).addToRequestQueue(myRequest);
    }
}