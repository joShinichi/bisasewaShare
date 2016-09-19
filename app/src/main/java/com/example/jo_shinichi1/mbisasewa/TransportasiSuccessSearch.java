package com.example.jo_shinichi1.mbisasewa;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.jo_shinichi1.mbisasewa.adapter.KategoriAdapter;
import com.example.jo_shinichi1.mbisasewa.adapter.SuccessSearchActivityAdapter;
import com.example.jo_shinichi1.mbisasewa.adapter.SuccessSearchListViewAdapter;
import com.example.jo_shinichi1.mbisasewa.adapter.UmumFragmentAdapter;
import com.example.jo_shinichi1.mbisasewa.utils.Kategori;
import com.example.jo_shinichi1.mbisasewa.utils.KategoriUmum;
import com.example.jo_shinichi1.mbisasewa.utils.Mysingleton;
import com.example.jo_shinichi1.mbisasewa.utils.MysingletonCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TransportasiSuccessSearch extends AppCompatActivity {
    RecyclerView recyclerView,recyclerViewListview;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    Toolbar toolbar,toolbartwo;
    private Menu menu;
    public String josn_url = "http://192.168.0.38/jsontest.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transportasi_success_search);
        toolbar = (Toolbar) findViewById(R.id.toolbarSuccess);
        toolbar.inflateMenu(R.menu.menu_home);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbartwo = (Toolbar) findViewById(R.id.toolbarSuccesstwo);
        setSupportActionBar(toolbartwo);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if(id == R.id.action_photo){
                    Toast.makeText(TransportasiSuccessSearch.this, "pasang iklan", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
        initCustomSpinner();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerviewsuccesssearch);
        recyclerView.setLayoutManager(new LinearLayoutManager(TransportasiSuccessSearch.this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setHasFixedSize(true);
        getDataList();

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
                                adapter = new SuccessSearchActivityAdapter(umums,TransportasiSuccessSearch.this,recyclerView);
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
                Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        MysingletonCompat.getMysingleton(TransportasiSuccessSearch.this).addToRequestQue(jsonArrayRequest);
    }

    public void getDataList(){
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
                                adapter = new SuccessSearchListViewAdapter(umums,TransportasiSuccessSearch.this,recyclerView);
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
                Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        MysingletonCompat.getMysingleton(TransportasiSuccessSearch.this).addToRequestQue(jsonArrayRequest);
    }

    private void initCustomSpinner() {

        Spinner spinnerCustom= (Spinner) findViewById(R.id.spinnerCustomsuccess);
        // Spinner Drop down elements
        ArrayList<String> languages = new ArrayList<String>();
        languages.add("Transportasi, Mobil pribadi");
        languages.add("Transportasi, Mobil pribadi");
        languages.add("Transportasi, Mobil pribadi");
        languages.add("Transportasi, Mobil pribadi");
        languages.add("Transportasi, Mobil pribadi");
        CustomSpinnerAdapter customSpinnerAdapter=new CustomSpinnerAdapter(TransportasiSuccessSearch.this,languages);
        spinnerCustom.setAdapter(customSpinnerAdapter);
        spinnerCustom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String item = parent.getItemAtPosition(position).toString();

                Toast.makeText(parent.getContext(), "Android Custom Spinner Example Output..." + item, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu_success_search,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_list) {
            Toast.makeText(this, "You clicked on buka sewa", Toast.LENGTH_LONG).show();
            menu.getItem(0).setIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.listviewchange,null));
            menu.getItem(0).setEnabled(false);
            menu.getItem(1).setIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.icongrridviewdua,null));
            menu.getItem(1).setEnabled(true);
            getDataList();
            recyclerView.setLayoutManager(new LinearLayoutManager(TransportasiSuccessSearch.this,LinearLayoutManager.VERTICAL,false));
            recyclerView.setHasFixedSize(true);

        } else if (id == R.id.action_grid) {
            Toast.makeText(this, "You clicked on buka sewa", Toast.LENGTH_LONG).show();
            menu.getItem(0).setIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.iconlistview,null));
            menu.getItem(1).setIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.gridchange,null));
            menu.getItem(1).setEnabled(false);
            menu.getItem(0).setEnabled(true);
            getData();
            layoutManager = new GridLayoutManager(getApplicationContext(),2);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setHasFixedSize(true);
        }
        return super.onOptionsItemSelected(item);
    }

    public class CustomSpinnerAdapter extends BaseAdapter implements SpinnerAdapter {

        private final Context activity;
        private ArrayList<String> asr;

        public CustomSpinnerAdapter(Context context,ArrayList<String> asr) {
            this.asr=asr;
            activity = context;
        }



        public int getCount()
        {
            return asr.size();
        }

        public Object getItem(int i)
        {
            return asr.get(i);
        }

        public long getItemId(int i)
        {
            return (long)i;
        }



        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            TextView txt = new TextView(TransportasiSuccessSearch.this);
            txt.setPadding(16, 16, 16, 16);
            txt.setTextSize(14);
            txt.setGravity(Gravity.CENTER_VERTICAL);
            txt.setText(asr.get(position));
            txt.setTextColor(Color.parseColor("#000000"));
            return  txt;
        }

        public View getView(int i, View view, ViewGroup viewgroup) {
            TextView txt = new TextView(TransportasiSuccessSearch.this);
            txt.setGravity(Gravity.CENTER);
            txt.setPadding(16, 16, 16, 16);
            txt.setTextSize(14);
            txt.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_down, 0);
            txt.setText(asr.get(i));
            txt.setTextColor(Color.parseColor("#000000"));
            return  txt;
        }

    }
}
