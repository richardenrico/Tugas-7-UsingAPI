package com.tugas.usingapi.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tugas.usingapi.R;
import com.tugas.usingapi.models.Upcoming;
import com.tugas.usingapi.networks.Const;

import java.util.List;

public class UpcomingAdapter extends RecyclerView.Adapter<UpcomingAdapter.ViewHolder> {
    private List<Upcoming> models;
    private OnItemClick onItemClick;

    public UpcomingAdapter(List<Upcoming> models, OnItemClick onItemClick) {
        this.models = models;
        this.onItemClick = onItemClick;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_grid, parent, false);
        return new ViewHolder(view, onItemClick);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext())
                .load(Const.IMG_URL_300 + models.get(position).getImgUrl())
                .into(holder.ivItemimg);
        holder.tvItemTitle.setText(models.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public interface OnItemClick {
        void onClick(int pos);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        OnItemClick onItemClick;
        ImageView ivItemimg;
        TextView tvItemTitle;

        public ViewHolder(View view, OnItemClick onItemClick) {
            super(view);
            itemView.setOnClickListener(this);
            ivItemimg = view.findViewById(R.id.iv_item_image);
            tvItemTitle = view.findViewById(R.id.tv_item_title);
            this.onItemClick = onItemClick;
        }

        @Override
        public void onClick(View v) {
            onItemClick.onClick(getBindingAdapterPosition());
        }
    }
}
