package com.nollpointer.dates.fragments;


import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nollpointer.dates.MainActivity;
import com.nollpointer.dates.R;
import com.nollpointer.dates.adapters.GameCardsAdapter;


public class GameFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.fragment_game, container, false);

        RecyclerView recyclerView = mainView.findViewById(R.id.game_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3){

        });
        recyclerView.setAdapter(new GameCardsAdapter());

        int spacingInPixels = 16;
        recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));

        TextView instructions = mainView.findViewById(R.id.game_instructions);
        instructions.setText(Html.fromHtml("<li>Одна игра стоит один просмотр рекламы. Если вы выйдите из игры, не закончив ее, вам придется заново смотреть рекламу, чтобы попробовать заново</li>" +
                "    <li>В каждой игре можно перевернуть 3 карты, после чего вы увидите, выиграли ли вы или нет. </li>" +
                "    <li>Вы выигрываете, если получили 3 желтых звезды.</li>" +
                "    <li>В случае выигрыша у вас отключается реклама на следующие 12 часов.</li>" +
                "    <li>В игру можно играть сколько угодно, пока реклама прогружается</li>"));

        Toolbar toolbar = mainView.findViewById(R.id.game_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        return mainView;
    }

    @Override
    public void onStart() {
        super.onStart();
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.hideBottomNavigationView();
    }

    @Override
    public void onStop() {
        super.onStop();
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.showBottomNavigationView();
    }



    public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view,
                                   RecyclerView parent, RecyclerView.State state) {
            outRect.left = space/2;
            outRect.right = space/2;
            outRect.bottom = space;

        }
    }


}
