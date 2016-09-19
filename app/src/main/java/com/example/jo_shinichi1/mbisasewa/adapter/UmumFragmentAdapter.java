package com.example.jo_shinichi1.mbisasewa.adapter;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.jo_shinichi1.mbisasewa.R;
import com.example.jo_shinichi1.mbisasewa.RecyclerViewAnimator;
import com.example.jo_shinichi1.mbisasewa.activity.MainActivity;
import com.example.jo_shinichi1.mbisasewa.fragments.FragmentMotor;
import com.example.jo_shinichi1.mbisasewa.fragments.FragmentUmum;
import com.example.jo_shinichi1.mbisasewa.utils.KategoriMotor;
import com.example.jo_shinichi1.mbisasewa.utils.KategoriUmum;
import com.example.jo_shinichi1.mbisasewa.utils.Mysingleton;

import java.util.ArrayList;

/**
 * Created by jo_shinichi1 on 8/25/2016.
 */
public class UmumFragmentAdapter extends RecyclerView.Adapter<UmumFragmentAdapter.UserAdapterHolder> {
   ArrayList<KategoriUmum> umums = new ArrayList<KategoriUmum>();
    FragmentUmum fragmentUmum;
    Fragment fragment;
    public ImageLoader imageLoader;
    private RecyclerViewAnimator mAnimator;

    public UmumFragmentAdapter(ArrayList<KategoriUmum> umums, Fragment fragment, RecyclerView recyclerView){
        this.umums = umums;
        this.fragment = fragment;
        fragmentUmum = (FragmentUmum) fragment;
        mAnimator = new RecyclerViewAnimator(recyclerView);
        setHasStableIds(true);
    }

    @Override
    public UserAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_layout_tab_three,parent,false);
        UserAdapterHolder userAdapterHolder = new UserAdapterHolder(view,fragmentUmum,umums);
        mAnimator.onCreateViewHolder(view);
        return userAdapterHolder;
    }

    @Override
    public void onBindViewHolder(UserAdapterHolder holder, int position) {
        KategoriUmum UM = umums.get(position);
        imageLoader = Mysingleton.getMysingleton(fragmentUmum).getImageLoader();
        holder.tvName.setText(UM.getName());
        holder.tvEmail.setText(UM.getEmail());
        holder.thumbnail.setImageUrl(umums.get(position).getImg_url(),imageLoader);
        mAnimator.onBindViewHolder(holder.itemView, position);


    }

    @Override
    public int getItemCount() {
        return umums.size();
    }

    public static class UserAdapterHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        NetworkImageView thumbnail;
        FragmentUmum fragmentUmum;
        CardView cardView;
        TextView tvName,tvEmail;
        ArrayList<KategoriUmum> getDataArrayList = new ArrayList<KategoriUmum>();
        public UserAdapterHolder(View itemView, final FragmentUmum fragmentUmum, ArrayList<KategoriUmum> kategoriUma) {
            super(itemView);
            this.getDataArrayList = kategoriUma;
            this.fragmentUmum = fragmentUmum;
            thumbnail = (NetworkImageView) itemView.findViewById(R.id.thumbnail);
            tvName = (TextView) itemView.findViewById(R.id.nama);
            tvEmail = (TextView) itemView.findViewById(R.id.email);
            cardView = (CardView) itemView.findViewById(R.id.cardViewTabTwo);
            thumbnail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    KategoriUmum UM = getDataArrayList.get(position);
                    Intent intent = new Intent(fragmentUmum.getActivity(),MainActivity.class);
                    intent.putExtra("key_img_id",UM.getImg_url());
                    intent.putExtra("key_name_id",UM.getName());
                    intent.putExtra("key_email_id",UM.getEmail());
                    fragmentUmum.getActivity().startActivity(intent);
                }
            });
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            KategoriUmum UM = this.getDataArrayList.get(position);
            Intent intent = new Intent(fragmentUmum.getActivity(), MainActivity.class);
            intent.putExtra("key_img_id",UM.getImg_url());
            intent.putExtra("key_name_id",UM.getName());
            intent.putExtra("key_email_id",UM.getEmail());
            fragmentUmum.getActivity().startActivity(intent);

        }
    }
}
