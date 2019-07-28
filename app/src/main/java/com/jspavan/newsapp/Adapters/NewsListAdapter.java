package com.jspavan.newsapp.Adapters;

import android.content.Context;

import android.support.annotation.NonNull;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jspavan.newsapp.Fragments.NewsDashBoard;
import com.jspavan.newsapp.ImageCache.ImageLoader;
import com.jspavan.newsapp.R;
import com.jspavan.newsapp.modelClass.Article;

import java.util.List;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.MyViewHolder> {


    private final Context context;
    private final List<Article> newsData;
    private final NewsDashBoard newsDashBoard;
    private ImageLoader imgLoader;
    private NewsDashBoard fragment;
    private Boolean arrowStat = false;

    public NewsListAdapter(Context context, List<Article> newsData, NewsDashBoard newsDashBoard1) {


        this.context = context;
        this.newsData = newsData;
        this.newsDashBoard = newsDashBoard1;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_list_item_view, null,false);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new NewsListAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {

        if(newsData.size()>0){
            myViewHolder.news_title.setText(newsData.get(i).getTitle());
            myViewHolder.news_desc.setText(newsData.get(i).getDescription());
            myViewHolder.news_content.setText(newsData.get(i).getContent());

            imgLoader = new ImageLoader(context);
            imgLoader.DisplayImage(newsData.get(i).getUrlToImage(), myViewHolder.news_Image);

            myViewHolder.news_cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println(newsData.get(i).getUrl());
                    newsDashBoard.goTOWebView(newsData.get(i).getUrl());
                }
            });

            myViewHolder.expand_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!arrowStat){
                        myViewHolder.news_content.setVisibility(View.VISIBLE);
                        myViewHolder.expand_button.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_keyboard_arrow_up));
                        arrowStat=true;

                    }
                    else{
                        myViewHolder.news_content.setVisibility(View.GONE);
                        myViewHolder.expand_button.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_expand_more_black_36dp));
                        arrowStat=false;

                    }


                }
            });

        }

    }


    @Override
    public int getItemCount() {
        return newsData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        private final LinearLayout news_cardView;
        private final ImageView news_Image,expand_button;
        private final TextView news_title,news_desc,news_content;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            news_cardView = itemView.findViewById(R.id.news_cardView);
            news_Image = itemView.findViewById(R.id.media_image);
            news_title = itemView.findViewById(R.id.primary_text);
            news_desc = itemView.findViewById(R.id.sub_text);
            news_content = itemView.findViewById(R.id.supporting_text);
            expand_button = itemView.findViewById(R.id.expand_button);


        }
    }
}
