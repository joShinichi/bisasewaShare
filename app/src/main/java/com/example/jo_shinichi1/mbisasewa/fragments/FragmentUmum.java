package com.example.jo_shinichi1.mbisasewa.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.jo_shinichi1.mbisasewa.R;
import com.example.jo_shinichi1.mbisasewa.adapter.MotorFragmentAdapter;
import com.example.jo_shinichi1.mbisasewa.adapter.UmumFragmentAdapter;
import com.example.jo_shinichi1.mbisasewa.utils.KategoriMotor;
import com.example.jo_shinichi1.mbisasewa.utils.KategoriUmum;
import com.example.jo_shinichi1.mbisasewa.utils.Mysingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class FragmentUmum extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    public String josn_url = "http://192.168.0.38/jsontest.php";

    public FragmentUmum() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View V = inflater.inflate(R.layout.fragment_umum, container, false);
        recyclerView = (RecyclerView) V.findViewById(R.id.recyclerviewUmum);
//        layoutManager = new GridLayoutManager(getActivity(),2);
//
//        recyclerView.setLayoutManager(layoutManager);
        //make horizontal layout RecyceleView
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,true));
        recyclerView.setHasFixedSize(true);
        getData();
        return V;
    }


    public void getData(){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, josn_url, (String) null,
                new Response.Listener<JSONArray>() {
                    ArrayList<KategoriUmum> umums = new ArrayList<KategoriUmum>();
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e(this.getClass().getName(),response.toString());
                        for (int i = 0; i <= response.length(); i++){
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                KategoriUmum UM = new KategoriUmum();
                                UM.setName(jsonObject.getString("nama"));
                                UM.setEmail(jsonObject.getString("email"));
                                UM.setImg_url(jsonObject.getString("foto"));
                                umums.add(i,UM);
                                adapter = new UmumFragmentAdapter(umums,FragmentUmum.this,recyclerView);
                                recyclerView.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        Mysingleton.getMysingleton(FragmentUmum.this).addToRequestQue(jsonArrayRequest);
    }

}
