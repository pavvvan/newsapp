package com.jspavan.newsapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.jspavan.newsapp.Fragments.SelectCategory;
import com.jspavan.newsapp.ImageCache.ImageLoader;
import com.jspavan.newsapp.ProjectUtility;
import com.jspavan.newsapp.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.MyViewHolder> {
    private final Context context;
    private final SelectCategory selectCategory;
    private  ArrayList catImages,catNames;
    private ImageLoader imgLoader;


    public CategoryListAdapter(Context context, ArrayList images, ArrayList category, SelectCategory selectCategory){
        this.context = context;
        this.catImages = images;
        this.catNames  = category;
        this.selectCategory = selectCategory;

    }


    @NonNull
    @Override
    public CategoryListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_item_view, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NotNull CategoryListAdapter.MyViewHolder holder, final int position) {

        if(catNames.size()>0){

         holder.categoryName_tv1.setText(""+catNames.get(position));
            imgLoader = new ImageLoader(context);
            String url=""+catImages.get(position);
            imgLoader.DisplayImage(url, holder.categoryImage_iv1);

            holder.categoryImage_iv1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                String selectedCategory = ""+catNames.get(position);
                    Toast.makeText(context,selectedCategory,Toast.LENGTH_SHORT).show();

                    ProjectUtility.getUtilityInstance().setPreference(context,"",selectedCategory);


             selectCategory.gotoDashBoard(selectedCategory);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return catNames.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView categoryName_tv1;

        ImageView categoryImage_iv1;
        public MyViewHolder(View itemView) {
            super(itemView);
            categoryName_tv1 =  itemView.findViewById(R.id.categoryName_tv2);
            categoryImage_iv1 = itemView.findViewById(R.id.categoryImage_iv);
        }
    }
}

