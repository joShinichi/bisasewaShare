package com.example.jo_shinichi1.mbisasewa.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.jo_shinichi1.mbisasewa.activity.HomeKategori;
import com.example.jo_shinichi1.mbisasewa.R;
import com.example.jo_shinichi1.mbisasewa.activity.MainActivity;
import com.example.jo_shinichi1.mbisasewa.activity.Transportasi;
import com.example.jo_shinichi1.mbisasewa.utils.Kategori;
import com.example.jo_shinichi1.mbisasewa.utils.MysingletonCompat;

import java.util.ArrayList;

/**
 * Created by jo_shinichi1 on 9/2/2016.
 */
public class KategoriAdapter extends RecyclerView.Adapter<KategoriAdapter.KategoriAdapterHolder> {
    ArrayList<Kategori> kategoris = new ArrayList<Kategori>();
    HomeKategori homeKategori;
    Context context;
    public ImageLoader imageLoader;

    public KategoriAdapter(ArrayList<Kategori> kategoris, Context context){
        this.kategoris = kategoris;
        this.context = context;
        homeKategori = (HomeKategori) context;
    }

    @Override
    public KategoriAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_layout_kategori,parent,false);
        KategoriAdapterHolder kategoriAdapterHolder = new KategoriAdapterHolder(view,homeKategori,kategoris);
        return kategoriAdapterHolder;
    }

    @Override
    public void onBindViewHolder(KategoriAdapterHolder holder, int position) {
        Kategori KAT = kategoris.get(position);
        imageLoader = MysingletonCompat.getMysingleton(homeKategori).getImageLoader();
        holder.imageView.setImageUrl(KAT.getImage_url(),imageLoader);

    }

    @Override
    public int getItemCount() {
        return kategoris.size();
    }


    public static class KategoriAdapterHolder extends RecyclerView.ViewHolder{
        NetworkImageView imageView;
        ArrayList<Kategori> getKategori = new ArrayList<Kategori>();
        CardView cardView;
        HomeKategori homeKategori;

        public KategoriAdapterHolder(View itemView, final HomeKategori homeKategori, ArrayList<Kategori> kategoris) {
            super(itemView);
            this.getKategori = kategoris;
            this.homeKategori = homeKategori;
            imageView = (NetworkImageView) itemView.findViewById(R.id.thumbnail);
            cardView = (CardView) itemView.findViewById(R.id.cardViewTabTwo);
            //set the ontouch listener
            imageView.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN: {
                            ImageView view = (ImageView) v;
                            //overlay is black with transparency of 0x77 (119)
                            view.getDrawable().setColorFilter(0x77000000,PorterDuff.Mode.SRC_ATOP);
                            view.invalidate();
                            break;
                        }
                        case MotionEvent.ACTION_UP:{
                            //menggunakan logika switch atau if dalam position adapter jika posisi recyclerview di touch
                            //di posisi tertentu
                            int position = getAdapterPosition();
                            if(position == 0){
                                Kategori kategori = getKategori.get(position);
                                Intent intent = new Intent(homeKategori.getApplicationContext(),Transportasi.class);
                                intent.putExtra("keyimageheaderkategori",kategori.getImage_url());
                                homeKategori.startActivity(intent);
                            }else if(position == 1){
                                Intent intent = new Intent(homeKategori.getApplicationContext(),MainActivity.class);
                                homeKategori.startActivity(intent);
                            }

                        }
                        case MotionEvent.ACTION_CANCEL: {
                            ImageView view = (ImageView) v;
                            //clear the overlay
                            view.getDrawable().clearColorFilter();
                            view.invalidate();
                            break;
                        }
                    }

                    return true;
                }
            });
        }
    }
}
