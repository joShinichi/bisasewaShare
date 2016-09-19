package com.example.jo_shinichi1.mbisasewa.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.jo_shinichi1.mbisasewa.R;
import com.example.jo_shinichi1.mbisasewa.TransportasiSuccessSearch;
import com.example.jo_shinichi1.mbisasewa.utils.Kategori;
import com.example.jo_shinichi1.mbisasewa.adapter.KategoriAdapter;
import com.example.jo_shinichi1.mbisasewa.utils.MysingletonCompat;
import com.rey.material.app.BottomSheetDialog;
import com.rey.material.app.Dialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeKategori extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    private DrawerLayout drawerLayout;
    private ViewPager viewPager;

    private NavigationView navigationView;

    public String josn_url = "http://192.168.0.38/homekategoriimage.php";
    Toolbar toolbar;
    ArrayList<Kategori> kategoris = new ArrayList<Kategori>();

    int[] image_id = {R.drawable.bannertransportasi,R.drawable.bannermesin,R.drawable.bannerhobi,
            R.drawable.bannerproperti
    };

    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_kategori);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        linearLayout = (LinearLayout) findViewById(R.id.containerKategori);
        navigationView = (NavigationView) findViewById(R.id.navigationView);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        ActionBarDrawerToggle mActionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.lbl_open, R.string.lbl_close);
        mActionBarDrawerToggle.syncState();
        drawerLayout.addDrawerListener(mActionBarDrawerToggle);
        // Set navigation item selected listener
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                String msgString = "";

                switch (menuItem.getItemId()) {
                    case R.id.iconpengaturan:
                        msgString = (String) menuItem.getTitle();
                        break;
                    case R.id.iconbantuan:
                        msgString = (String) menuItem.getTitle();
                        break;
                    case R.id.iconlaporkanmasalah:
                        msgString = (String) menuItem.getTitle();
                        break;
                    case R.id.iconkeluar:
                        msgString = (String) menuItem.getTitle();
                        break;
                }

                // Menu item clicked on, and close Drawerlayout
                menuItem.setChecked(true);
                drawerLayout.closeDrawers();

                Toast.makeText(getApplicationContext(), msgString, Toast.LENGTH_LONG).show();

                return true;
            }
        });

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final BottomSheetDialog mDialog = new BottomSheetDialog(HomeKategori.this);
                final View contentView = View.inflate(HomeKategori.this, R.layout.dialog, null);
                mDialog.applyStyle(R.style.DialogNoAnimation)
                        .contentView(contentView)
                        .heightParam(ViewGroup.LayoutParams.WRAP_CONTENT)
                        .inDuration(500)
                        .cancelable(true).show();

                LinearLayout Lin= (LinearLayout) contentView.findViewById(R.id.containerKategoriTwo);
                Button button = (Button) contentView.findViewById(R.id.btnCari);
                Spinner spinnerCustom= (Spinner) contentView.findViewById(R.id.spinnerCustom);
                Spinner spinnerCustomtwo= (Spinner) contentView.findViewById(R.id.spinnerCustomtwo);
                // Spinner Drop down elements
                ArrayList<String> languages = new ArrayList<String>();
                languages.add("Andorid");
                languages.add("IOS");
                languages.add("PHP");
                languages.add("Java");
                languages.add(".Net");
                CustomSpinnerAdapter customSpinnerAdapter=new CustomSpinnerAdapter(HomeKategori.this,languages);
                spinnerCustom.setAdapter(customSpinnerAdapter);
                spinnerCustomtwo.setAdapter(customSpinnerAdapter);
                Lin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mDialog.cancel();
                    }
                });
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        View content = View.inflate(HomeKategori.this, R.layout.dialog_failed_search,null);
                        Button btnKembali = (Button) content.findViewById(R.id.kembali);
//                        final Dialog dialog = new Dialog(HomeKategori.this);
//                        dialog.applyStyle(R.style.Material_App_Dialog_Simple)
//                         .contentView(content)
//                         .cancelable(true)
//                         .show();

                        final AlertDialog.Builder builder = new AlertDialog.Builder(HomeKategori.this);
                        builder.setView(content);
                        final AlertDialog d = builder.create(); //create Dialog
                        d.show(); //first show

                        DisplayMetrics metrics = new DisplayMetrics(); //get metrics of screen
                        HomeKategori.this.getWindowManager().getDefaultDisplay().getMetrics(metrics);
                        int height = (int) (metrics.heightPixels*0.5); //set height to 90% of total
                        int width = (int) (metrics.widthPixels*1); //set width to 90% of total

                        d.getWindow().setLayout(width, height); //set layout
                        btnKembali.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
//                                dialog.cancel();
//                                d.cancel();

                                Intent intent = new Intent(HomeKategori.this, TransportasiSuccessSearch.class);
                                startActivity(intent);
                            }
                        });
                    }
                });
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

                spinnerCustomtwo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        });

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(HomeKategori.this,LinearLayoutManager.VERTICAL,true));
        recyclerView.setHasFixedSize(true);
        getData();
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
            TextView txt = new TextView(HomeKategori.this);
            txt.setPadding(16, 16, 16, 16);
            txt.setTextSize(18);
            txt.setGravity(Gravity.LEFT);
            txt.setText(asr.get(position));
            txt.setTextColor(Color.parseColor("#000000"));
            return  txt;
        }

        public View getView(int i, View view, ViewGroup viewgroup) {
            TextView txt = new TextView(HomeKategori.this);
            txt.setGravity(Gravity.LEFT);
            txt.setPadding(16, 26, 16, 16);
            txt.setTextSize(16);
//            txt.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.pilih, 0);
            txt.setText(asr.get(i));
            txt.setTextColor(Color.parseColor("#000000"));
            return  txt;
        }

    }

    public void getData(){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, josn_url, (String) null,
                new Response.Listener<JSONArray>() {
                    ArrayList<Kategori> kategoris = new ArrayList<Kategori>();
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e(this.getClass().getName(),response.toString());
                        for (int i = 0; i <= response.length(); i++){
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                Kategori kategori = new Kategori();
                                kategori.setImage_url(jsonObject.getString("foto"));
                                kategoris.add(i,kategori);
                                adapter = new KategoriAdapter(kategoris,HomeKategori.this);
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
                Toast.makeText(getApplicationContext(),error.getMessage()+"error when request to network", Toast.LENGTH_SHORT).show();
            }
        });
        MysingletonCompat.getMysingleton(HomeKategori.this).addToRequestQue(jsonArrayRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_photo) {
            Toast.makeText(this, "You clicked on buka sewa", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
