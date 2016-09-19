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
import com.example.jo_shinichi1.mbisasewa.utils.Contact;
import com.example.jo_shinichi1.mbisasewa.activity.MainActivity;
import com.example.jo_shinichi1.mbisasewa.R;
import com.example.jo_shinichi1.mbisasewa.RecyclerViewAnimator;
import com.example.jo_shinichi1.mbisasewa.fragments.FragmentTransportasi;
import com.example.jo_shinichi1.mbisasewa.utils.Mysingleton;


import java.util.ArrayList;

/**
 * Created by jo_shinichi1 on 8/25/2016.
 */
public class TransportasiFragmentAdapter extends RecyclerView.Adapter<TransportasiFragmentAdapter.UserAdapterHolder> {
   ArrayList<Contact> contacts = new ArrayList<Contact>();
    FragmentTransportasi threeFragment;
    Fragment fragment;
    public ImageLoader imageLoader;
    private RecyclerViewAnimator mAnimator;

    public TransportasiFragmentAdapter(ArrayList<Contact> contacts, Fragment fragment, RecyclerView recyclerView){
        this.contacts = contacts;
        this.fragment = fragment;
        threeFragment = (FragmentTransportasi) fragment;
        mAnimator = new RecyclerViewAnimator(recyclerView);
        setHasStableIds(true);
    }


    @Override
    public UserAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_layout_tab_three,parent,false);
        UserAdapterHolder userAdapterHolder = new UserAdapterHolder(view,threeFragment,contacts);
        mAnimator.onCreateViewHolder(view);
        return userAdapterHolder;
    }

    @Override
    public void onBindViewHolder(UserAdapterHolder holder, int position) {
        Contact CON = contacts.get(position);
        imageLoader = Mysingleton.getMysingleton(threeFragment).getImageLoader();
        holder.tvName.setText(CON.getName());
        holder.tvEmail.setText(CON.getEmail());
        holder.thumbnail.setImageUrl(contacts.get(position).getImg_url(),imageLoader);
        mAnimator.onBindViewHolder(holder.itemView, position);


    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }


    public static class UserAdapterHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        NetworkImageView thumbnail;
        FragmentTransportasi threeFragment;
        CardView cardView;
        TextView tvName,tvEmail;
        ArrayList<Contact> getDataArrayList = new ArrayList<Contact>();
        public UserAdapterHolder(View itemView, final FragmentTransportasi threeFragment, ArrayList<Contact> contactArrayList) {
            super(itemView);
            this.getDataArrayList = contactArrayList;
            this.threeFragment = threeFragment;
            thumbnail = (NetworkImageView) itemView.findViewById(R.id.thumbnail);
            tvName = (TextView) itemView.findViewById(R.id.nama);
            tvEmail = (TextView) itemView.findViewById(R.id.email);
            cardView = (CardView) itemView.findViewById(R.id.cardViewTabTwo);
            thumbnail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Contact con = getDataArrayList.get(position);
                    Intent intent = new Intent(threeFragment.getActivity(),MainActivity.class);
                    intent.putExtra("key_img_id",con.getImg_url());
                    intent.putExtra("key_name_id",con.getName());
                    intent.putExtra("key_email_id",con.getEmail());
                    threeFragment.getActivity().startActivity(intent);
                }
            });
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Contact con = this.getDataArrayList.get(position);
            Intent intent = new Intent(threeFragment.getActivity(), MainActivity.class);
            intent.putExtra("key_img_id",con.getImg_url());
            intent.putExtra("key_name_id",con.getName());
            intent.putExtra("key_email_id",con.getEmail());
            threeFragment.getActivity().startActivity(intent);

        }
    }
}
