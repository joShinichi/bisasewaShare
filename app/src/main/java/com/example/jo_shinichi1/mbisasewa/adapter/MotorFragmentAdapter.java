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
import com.example.jo_shinichi1.mbisasewa.utils.KategoriMotor;
import com.example.jo_shinichi1.mbisasewa.utils.Mysingleton;

import java.util.ArrayList;

/**
 * Created by jo_shinichi1 on 8/25/2016.
 */
public class MotorFragmentAdapter extends RecyclerView.Adapter<MotorFragmentAdapter.UserAdapterHolder> {
   ArrayList<KategoriMotor> motors = new ArrayList<KategoriMotor>();
    FragmentMotor fragmentMotor;
    Fragment fragment;
    public ImageLoader imageLoader;
    private RecyclerViewAnimator mAnimator;

    public MotorFragmentAdapter(ArrayList<KategoriMotor> motors, Fragment fragment, RecyclerView recyclerView){
        this.motors = motors;
        this.fragment = fragment;
        fragmentMotor = (FragmentMotor) fragment;
        mAnimator = new RecyclerViewAnimator(recyclerView);
        setHasStableIds(true);
    }

    @Override
    public UserAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_layout_tab_three,parent,false);
        UserAdapterHolder userAdapterHolder = new UserAdapterHolder(view,fragmentMotor,motors);
        mAnimator.onCreateViewHolder(view);
        return userAdapterHolder;
    }

    @Override
    public void onBindViewHolder(UserAdapterHolder holder, int position) {
        KategoriMotor MTR = motors.get(position);
        imageLoader = Mysingleton.getMysingleton(fragmentMotor).getImageLoader();
        holder.tvName.setText(MTR.getName());
        holder.tvEmail.setText(MTR.getEmail());
        holder.thumbnail.setImageUrl(motors.get(position).getImg_url(),imageLoader);
        mAnimator.onBindViewHolder(holder.itemView, position);


    }

    @Override
    public int getItemCount() {
        return motors.size();
    }

    public static class UserAdapterHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        NetworkImageView thumbnail;
        FragmentMotor fragmentMotor;
        CardView cardView;
        TextView tvName,tvEmail;
        ArrayList<KategoriMotor> getDataArrayList = new ArrayList<KategoriMotor>();
        public UserAdapterHolder(View itemView, final FragmentMotor fragmentMotor, ArrayList<KategoriMotor> kategoriMotors) {
            super(itemView);
            this.getDataArrayList = kategoriMotors;
            this.fragmentMotor = fragmentMotor;
            thumbnail = (NetworkImageView) itemView.findViewById(R.id.thumbnail);
            tvName = (TextView) itemView.findViewById(R.id.nama);
            tvEmail = (TextView) itemView.findViewById(R.id.email);
            cardView = (CardView) itemView.findViewById(R.id.cardViewTabTwo);
            thumbnail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    KategoriMotor MTR = getDataArrayList.get(position);
                    Intent intent = new Intent(fragmentMotor.getActivity(),MainActivity.class);
                    intent.putExtra("key_img_id",MTR.getImg_url());
                    intent.putExtra("key_name_id",MTR.getName());
                    intent.putExtra("key_email_id",MTR.getEmail());
                    fragmentMotor.getActivity().startActivity(intent);
                }
            });
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            KategoriMotor MTR = this.getDataArrayList.get(position);
            Intent intent = new Intent(fragmentMotor.getActivity(), MainActivity.class);
            intent.putExtra("key_img_id",MTR.getImg_url());
            intent.putExtra("key_name_id",MTR.getName());
            intent.putExtra("key_email_id",MTR.getEmail());
            fragmentMotor.getActivity().startActivity(intent);

        }
    }
}
