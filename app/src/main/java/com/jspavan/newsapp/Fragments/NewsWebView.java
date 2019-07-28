package com.jspavan.newsapp.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.jspavan.newsapp.R;


public class NewsWebView extends Fragment {



    private String newsUrl;
    private View view;
    private WebView newsWebView;
    private ProgressDialog progressDialog;

    public NewsWebView() {

    }


    public static NewsWebView newInstance(String param1, String param2) {
        NewsWebView fragment = new NewsWebView();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            newsUrl = getArguments().getString("newsUrl");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_news_web_view, container, false);

        newsWebView = view.findViewById(R.id.newsWebView);
        workArea();

        return view;
    }

    private void workArea() {


        newsWebView.getSettings().setLoadWithOverviewMode(true);
        newsWebView.getSettings().setUseWideViewPort(true);
        newsWebView.getSettings().setBuiltInZoomControls(true);
        newsWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
        newsWebView.getSettings().setLoadWithOverviewMode(true);
        newsWebView.getSettings().setDomStorageEnabled(true);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        newsWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(getContext(), "Error:" + description, Toast.LENGTH_SHORT).show();

            }
        });

        newsWebView.loadUrl(newsUrl);
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

}
