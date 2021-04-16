package com.example.makecoding.makecoding.fragment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.example.makecoding.CPPanguage.FragmentCB;
import com.example.makecoding.CPPanguage.FragmentCC;
import com.example.makecoding.Python.advance;
import com.example.makecoding.Python.basiccs;
import com.example.makecoding.MainActivity;
import com.example.makecoding.R;
import com.example.makecoding.WebView.webview;
import com.example.makecoding.makecoding.GridView.GriadAdapter;
import com.example.makecoding.makecoding.GridView.GriadAdapter2;
import com.example.makecoding.makecoding.GridView.Item;
import com.example.makecoding.makecoding.GridView.Item2;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

public class Python extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<Item> arrayList;
    private GriadAdapter gridAdapter;
    private RecyclerView recyclerView2;
    private ArrayList<Item2> recyclerList2;
    private GriadAdapter2 gridAdapter2;
    private Menu menu;
    public View view;
    private AdView mAdView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_clanguage);

        recyclerView = (RecyclerView) findViewById(R.id.Cpp);
        arrayList = new ArrayList<>();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        setTitle("Python");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        arrayList.add(new Item( R.drawable.basiclogo ));
        arrayList.add(new Item( R.drawable.advancelogo));

        gridAdapter = new GriadAdapter(this, arrayList);
        recyclerView.setAdapter(gridAdapter);

        GridLayoutManager manager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        recyclerView2 = (RecyclerView) findViewById(R.id.Cpp2);
        recyclerList2 = new ArrayList<Item2>();
        recyclerList2.add(new Item2(R.drawable.qap));
        recyclerList2.add(new Item2(R.drawable.codinga));

        gridAdapter2 = new GriadAdapter2(Python.this,recyclerList2);
        recyclerView2.setAdapter(gridAdapter2);

        GridLayoutManager manager2 = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        recyclerView2.setLayoutManager(manager2);


        gridAdapter = new GriadAdapter(this, arrayList);
        recyclerView.setAdapter(gridAdapter);

        recyclerView2.addOnItemTouchListener(new CLanguage.RecyclerTouchListener(getApplicationContext(), recyclerView, new CLanguage.ClickListener() {

            @Override
            public void onClick(View view, int position) {
                switch (position) {
                    case 0:
                        Intent a1 = new Intent(Python.this, webview.class);
                        a1.putExtra("web", "file:///android_asset/python/que_ans/python_interview_que_ans.html");
                        startActivity(a1);
                        break;
                    case 1:
                        Intent a2 = new Intent(Python.this, webview.class);
                        a2.putExtra("web", "https://www.programiz.com/python-programming/online-compiler/");
                        startActivity(a2);
                        break;

                }

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        recyclerView.addOnItemTouchListener(new Python.RecyclerTouchListener(getApplicationContext(), recyclerView, new Python.ClickListener() {

            @Override
            public void onClick(View view, int position) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(Python.this, basiccs.class));
                        break;
                    case 1:
                        startActivity(new Intent(Python.this, advance.class));
                        break;

                }
            }
            @Override
            public void onLongClick(View view, int position) {
            }
        }));

        MobileAds.initialize(this, "ca-app-pub-3940256099942544/1033173712");

        view=getWindow().getDecorView().getRootView();

        Admob.createLoadBanner(getApplicationContext(), view);
        Admob.createLoadInterstitial(getApplicationContext(),null);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private Python.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final Python.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildAdapterPosition(child));
                    }
                }
            });
        }


        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildAdapterPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        }
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
