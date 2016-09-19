package com.example.jo_shinichi1.mbisasewa.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.jo_shinichi1.mbisasewa.R;
import com.example.jo_shinichi1.mbisasewa.activity.Transportasi;
import com.example.jo_shinichi1.mbisasewa.utils.MysingletonCompat;
import com.example.jo_shinichi1.mbisasewa.utils.SingleDataTransportasi;

import java.util.ArrayList;

/**
 * Created by jo_shinichi1 on 9/2/2016.
 */
public class SingleDataTransportasiAdapter extends RecyclerView.Adapter<SingleDataTransportasiAdapter.SingleDataTransportasiHolder> {
    ArrayList<SingleDataTransportasi> kategoris = new ArrayList<SingleDataTransportasi>();
    Transportasi transportasi;
    Context context;
    public ImageLoader imageLoader;

    public SingleDataTransportasiAdapter(ArrayList<SingleDataTransportasi> kategoris, Context context){
        this.kategoris = kategoris;
        this.context = context;
        transportasi = (Transportasi) context;
    }

    @Override
    public SingleDataTransportasiHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_layout_header_transportasi,parent,false);
        SingleDataTransportasiHolder singleDataTransportasiHolder = new SingleDataTransportasiHolder(view,transportasi,kategoris);
        return singleDataTransportasiHolder;
    }

    @Override
    public void onBindViewHolder(SingleDataTransportasiHolder holder, int position) {
        SingleDataTransportasi KAT = kategoris.get(position);
        imageLoader = MysingletonCompat.getMysingleton(transportasi).getImageLoader();
        holder.imageView.setImageUrl(KAT.getImage_url(),imageLoader);
    }

    @Override
    public int getItemCount() {
        return kategoris.size();
    }


    public static class SingleDataTransportasiHolder extends RecyclerView.ViewHolder{
        NetworkImageView imageView;
        ArrayList<SingleDataTransportasi> getKategori = new ArrayList<SingleDataTransportasi>();
        CardView cardView;
        Transportasi transportasi;

        public SingleDataTransportasiHolder(View itemView,Transportasi transportasi,ArrayList<SingleDataTransportasi> kategoris) {
            super(itemView);
            this.getKategori = kategoris;
            this.transportasi = transportasi;
            imageView = (NetworkImageView) itemView.findViewById(R.id.imageTransportasi);
            cardView = (CardView) itemView.findViewById(R.id.cardviewSingleTransportasi);

        }
    }
}
