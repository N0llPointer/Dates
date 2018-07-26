package com.nollpointer.dates;

import android.database.Cursor;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class DatesCardsAdapter extends RecyclerView.Adapter<DatesCardsAdapter.ViewHolder>{
    private Cursor cursor;

    private List<Date> dates;

    private static final int DATE = 0;
    private static final int DATE_WITH_MARGIN = 1;
    public static final int DEFAULT_TEXT_SIZE = 14;
    private int mode;
    private TreeMap<Integer,String> main_top_texts = new TreeMap<>();
    private TreeMap<Integer,String> add_top_texts = new TreeMap<>();
    private int fontSize = 14;
    private Listener listener;

    public interface Listener{
        void onItemClick(int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private CardView mCardView;
        ViewHolder(CardView c){
            super(c);
            mCardView = c;
        }
    }

    public DatesCardsAdapter(List<Date> dates, int mode, String[] main_text_tops, String[] additional_text_tops){
        this.dates = dates;
        this.mode = mode;
        fill_top_texts(main_text_tops,additional_text_tops);
    }

    public DatesCardsAdapter(Cursor cursor,int mode,String[] main_text_tops,String[] additional_text_tops,int fons_size){
        this.cursor = cursor;
        this.mode = mode;
        fontSize = fons_size;
        fill_top_texts(main_text_tops,additional_text_tops);
    }

    public int getFontSize(){
        return fontSize;
    }

    @Override
    public DatesCardsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView c;
        switch (viewType) {
            case DATE:
                c = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.dates_cards,parent,false);
                break;
            default:
                c = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.dates_cards_top_text,parent,false);
        }
        return new ViewHolder(c);
    }

    @Override
    public void onBindViewHolder(DatesCardsAdapter.ViewHolder holder, final int position) {
        Date date = dates.get(position);
        final CardView cardView = holder.mCardView;
        TextView textView = cardView.findViewById(R.id.date_number);
        textView.setText(date.getDate());
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize + 4);
        textView = cardView.findViewById(R.id.date_event);
        textView.setText(date.getEvent());
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize);
        if (main_top_texts.containsKey(position)) {
            textView = cardView.findViewById(R.id.date_top_text);
            textView.setText(main_top_texts.get(Integer.valueOf(position)));
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize);
        }
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(position);
            }
        });
    }

    private void fill_top_texts(String[] texts,String[] add_texts){
            int[] positions_main = {0,21,41,76,107,147,195,242,284,334};
            int[] positions_easy = {0,48};
            for(int i=0;i<positions_main.length;i++)
                main_top_texts.put(positions_main[i],texts[i]);
            for(int i=0;i<positions_easy.length;i++)
                add_top_texts.put(positions_easy[i],add_texts[i]);
            if(mode==MainActivity.EASY_DATES_MODE)
                change_top_texts();
    }

    private void change_top_texts(){
        TreeMap<Integer,String> tree = main_top_texts;
        main_top_texts = add_top_texts;
        add_top_texts = tree;
    }

    public void refresh(Cursor cursor){
        change_top_texts();
        this.cursor = cursor;
        notifyDataSetChanged();
    }

    public void refresh(ArrayList<Date> dates){
        change_top_texts();
        this.dates = dates;
        notifyDataSetChanged();
    }


    public boolean changeFontSize(int m){
        fontSize += m;
        notifyDataSetChanged();
        if(fontSize == DEFAULT_TEXT_SIZE)
            return true;
        else
            return false;
    }

    @Override
    public int getItemCount() {
        return dates.size();
    }

    public void setListener(Listener listener){
        this.listener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        if(main_top_texts.containsKey(position))
            return DATE_WITH_MARGIN;
        else
            return DATE;
    }

}
