package com.raikwar.top_github.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.raikwar.top_github.R;
import com.raikwar.top_github.model.RepositoryInfo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<RepositoryInfo> dataModelArrayList;

    public RepositoryAdapter(Context ctx, ArrayList<RepositoryInfo> dataModelArrayList){

        inflater = LayoutInflater.from(ctx);
        this.dataModelArrayList = dataModelArrayList;
    }

    @Override
    public RepositoryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(RepositoryAdapter.MyViewHolder holder, int position) {

        Picasso.get().load(dataModelArrayList.get(position).getAvatar()).into(holder.iv);
        holder.userName.setText(dataModelArrayList.get(position).getUsername());
        holder.repoName.setText(dataModelArrayList.get(position).getName());
        holder.url.setText(dataModelArrayList.get(position).getUrl());
    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView repoName, userName, url;
        ImageView iv;

        public MyViewHolder(View itemView) {
            super(itemView);

            repoName = itemView.findViewById(R.id.name);
            userName = itemView.findViewById(R.id.userName);
            url = itemView.findViewById(R.id.url);
            iv = itemView.findViewById(R.id.avatarIV);
        }

    }
}

