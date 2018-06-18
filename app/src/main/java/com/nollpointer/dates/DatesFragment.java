package com.nollpointer.dates;


import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DatesFragment extends Fragment{
    private MainActivity ctx;
    private DatesCardsAdapter adapter;
    private RecyclerView recycler;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        recycler = (RecyclerView) inflater.inflate(R.layout.fragment_dates, container, false);
        ctx = (MainActivity) getActivity();
        recycler.setLayoutManager(new LinearLayoutManager(ctx));
        Cursor cursor = ctx.getCursor();
        int mode = ctx.getMode();
        adapter = new DatesCardsAdapter(cursor,mode,ctx.getResources().getStringArray(R.array.centuries),ctx.getResources().getStringArray(R.array.centuries_easy));
        recycler.setAdapter(adapter);
        return recycler;
    }

    @Override
    public void onResume() {
        super.onResume();
        ctx.changeToolbarItemsVisibility(true,false);
        ctx.getSupportActionBar().setTitle(R.string.title_dates);
    }

    public void setStartPosition(){
        recycler.scrollToPosition(0);
    }

    public void refresh() {
        adapter.refresh(ctx.getCursor());
    }

    public boolean setAdapterFontSize(int m){
        return adapter.changeFontSize(m);
    }
}