package com.example.blog32;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import org.jsoup.select.Elements;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;



import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    private Context context;
    private List<Item>  items;

    public PostAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.post_item,parent,false);
        return new PostViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Item item=items.get(position);
        holder.postTitle.setText(item.getTitle());
        holder.postDescription.setText(item.getContent());


        Document document=Jsoup.parse(item.getContent());
        Elements elements=document.select("img");
        Log.d("CODE", "Image="+elements.get(0).attr("src"));
        Glide.with(context).load(elements.get(0).attr("src")).into(holder.postImage);


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder{
        ImageView postImage;
        TextView postTitle;
        TextView postDescription;
        public PostViewHolder(View itemView)
        {
            super(itemView);
            postImage=(ImageView) itemView.findViewById(R.id.postImage);
            postTitle=(TextView) itemView.findViewById(R.id.postTitle);
            postDescription=(TextView) itemView.findViewById(R.id.postDescription);
        }
    }
}
