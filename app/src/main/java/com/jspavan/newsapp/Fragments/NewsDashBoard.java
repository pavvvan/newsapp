package com.jspavan.newsapp.Fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.jspavan.newsapp.Adapters.NewsListAdapter;
import com.jspavan.newsapp.R;
import com.jspavan.newsapp.modelClass.Article;
import com.jspavan.newsapp.modelClass.NewsResponse;
import com.jspavan.newsapp.modelClass.Source;
import com.jspavan.newsapp.presenter.NewsPresenter;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class NewsDashBoard extends Fragment implements NewsPresenter.NewsCallback {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private View pbLogin;
    private NewsPresenter newsPresenter;
    private Context context;
    public List<Article> newsData;
    private NewsListAdapter newsListAdapter;
    private RecyclerView newsList_rv;
    private String selectedCategory;
    private ShimmerFrameLayout mShimmerViewContainer;
    private Toolbar toolbar;

    public NewsDashBoard() {
        // Required empty public constructor
    }


    public static NewsDashBoard newInstance(String param1, String param2) {
        NewsDashBoard fragment = new NewsDashBoard();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            selectedCategory = getArguments().getString("category");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View newsDashView = inflater.inflate(R.layout.fragment_news_dash_board, container, false);
        initViews(newsDashView);
        workArea();

        return newsDashView;
    }


    public void goTOWebView(String url){
        Bundle bundle = new Bundle();
        bundle.putString("newsUrl",url);
        
        Fragment fragment = new NewsWebView();
        fragment.setArguments(bundle);
        getFragmentManager().beginTransaction().replace(R.id.fragmentContainer,fragment).addToBackStack(null).commit();

    }



    private void initViews(View newsDashView) {
        context = getActivity();
        newsData = new ArrayList<>();
        pbLogin= newsDashView.findViewById(R.id.pbLogin);
        newsPresenter = new NewsPresenter(this);
        mShimmerViewContainer = newsDashView.findViewById(R.id.shimmer_view_container);
        newsList_rv = newsDashView.findViewById(R.id.newsList_rv);
        toolbar = newsDashView.findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getContext().getResources().getColor(R.color.white));
        toolbar.setTitle(selectedCategory);
        newsList_rv.setHasFixedSize(true);
        newsList_rv.setNestedScrollingEnabled(true);
        newsList_rv.setItemAnimator(new DefaultItemAnimator());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        newsList_rv.setLayoutManager(layoutManager);
        
    }


    private void workArea() {
        newsPresenter.getNewsDetails("in",selectedCategory,"7061e798937642779d9dcc6588f13992", getContext());
    }





    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onSuccess(NewsResponse newsResponse) {
        Log.d(TAG, "onSuccess: "+newsResponse.getStatus());
        Log.d(TAG, "onSuccess: "+newsResponse.getTotalResults());

        String isSuccess = newsResponse.getStatus();

        if(isSuccess.equalsIgnoreCase("ok")){
            newsData.clear();
            ArrayList<Article> data = (ArrayList<Article>) newsResponse.getArticles();
            for(Article dat:data){
                newsData.add(dat);
            }
            if(newsData.size()>0){
                newsListAdapter = new NewsListAdapter(context, newsData,NewsDashBoard.this);
                newsList_rv.setAdapter(newsListAdapter);
            }
            mShimmerViewContainer.stopShimmerAnimation();
            mShimmerViewContainer.setVisibility(View.GONE);

        }


    }

    @Override
    public void onFailure(Throwable t) {
        Log.d(TAG, "onFailure: "+t.toString());
    }

    @Override
    public void onShowProgressbar() {
        mShimmerViewContainer.startShimmerAnimation();
        mShimmerViewContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressbar() {
        mShimmerViewContainer.stopShimmerAnimation();
        mShimmerViewContainer.setVisibility(View.GONE);
    }
}
