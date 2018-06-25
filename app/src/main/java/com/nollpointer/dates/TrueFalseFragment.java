package com.nollpointer.dates;


import android.app.Fragment;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.appodeal.ads.Appodeal;

import java.util.ArrayList;
import java.util.Random;

import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView;
import uk.co.deanwild.materialshowcaseview.shape.NoShape;

import static com.nollpointer.dates.PractiseConstants.BOUND;
import static com.nollpointer.dates.PractiseConstants.INFINITIVE;
import static com.nollpointer.dates.PractiseConstants.POSITION;
import static com.nollpointer.dates.PractiseConstants.TYPE;

public class TrueFalseFragment extends Fragment {
    private Cursor cursor;
    private TextView question_date,question_event;
    private TextView RightAnswers,WrongAnswers;
    private Handler mHandler;
    private Runnable rnb;
    private int[] position, bound;
    private boolean isCorrect;
    private String date, event;
    private int right_answers_count=0,wrong_answers_count=0;
    private GridLayout g;
    private Button trueButton,falseButton;
    private ArrayList<Integer> questions;
    private int bestResult = 0;
    private ProgressBar progressBar;


    public static TrueFalseFragment newInstance(ArrayList<Integer> arrayList,int picked_pos,int mode,boolean infinitive){
        TrueFalseFragment true_false = new TrueFalseFragment();
        int[] position,bound;
        if(mode == MainActivity.FULL_DATES_MODE) {
            if (arrayList.contains(10))
                for (int i = 0; i < 10; i++)
                    arrayList.add(i, i);
            arrayList.remove(Integer.valueOf(10));
            position = new int[arrayList.size()];
            bound = new int[arrayList.size()];
            for (int i = 0; i < arrayList.size(); i++) {
                switch (arrayList.get(i)) {
                    case 0:
                        position[i] = 0;
                        bound[i] = 21;
                        break;
                    case 1:
                        position[i] = 21;
                        bound[i] = 20;
                        break;
                    case 2:
                        position[i] = 41;
                        bound[i] = 35;
                        break;
                    case 3:
                        position[i] = 76;
                        bound[i] = 31;
                        break;
                    case 4:
                        position[i] = 107;
                        bound[i] = 40;
                        break;
                    case 5:
                        position[i] = 147;
                        bound[i] = 48;
                        break;
                    case 6:
                        position[i] = 195;
                        bound[i] = 48;
                        break;
                    case 7:
                        position[i] = 242;
                        bound[i] = 42;
                        break;
                    case 8:
                        position[i] = 284;
                        bound[i] = 50;
                        break;
                    case 9:
                        position[i] = 334;
                        bound[i] = 50;
                        break;
                }
            }
        }else{
            if (arrayList.contains(2))
                for (int i = 0; i < 2; i++)
                    arrayList.add(i, i);
            arrayList.remove(Integer.valueOf(2));
            position = new int[arrayList.size()];
            bound = new int[arrayList.size()];
            for (int i = 0; i < arrayList.size(); i++) {
                switch (arrayList.get(i)) {
                    case 0:
                        position[i] = 0;
                        bound[i] = 48;
                        break;
                    case 1:
                        position[i] = 48;
                        bound[i] = 47;
                        break;
                }
            }
        }

        Bundle bundle = new Bundle();
        bundle.putIntArray(POSITION,position);
        bundle.putIntArray(BOUND,bound);
        bundle.putBoolean(INFINITIVE,infinitive);
        true_false.setArguments(bundle);
        return true_false;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(Build.VERSION.SDK_INT == 19)
            g = (GridLayout) inflater.inflate(R.layout.fragment_true_false_low_api, container, false);
        else
            g =(GridLayout) inflater.inflate(R.layout.fragment_true_false, container, false);
        rnb = new Runnable() {
            @Override
            public void run() {
                question_date.setTextColor(getResources().getColor(android.R.color.black));
                question_event.setTextColor(getResources().getColor(android.R.color.black));
                setClickable(true);
                if(questions != null && (right_answers_count + wrong_answers_count == 20)) {
                    setResultScreen();
                }else {
                    setQuestions();
                }
            }
        };
        mHandler = new Handler();
        MainActivity mAc = (MainActivity) getActivity();
        trueButton = g.findViewById(R.id.true_button);
        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickResult(true);

            }
        });
        falseButton = g.findViewById(R.id.false_button);
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickResult(false);

            }
        });
        progressBar = g.findViewById(R.id.true_false_progressbar);
        Appodeal.setBannerViewId(R.id.appodealBannerView_true);
        question_date = g.findViewById(R.id.test_info_true_false_date);
        question_event = g.findViewById(R.id.test_info_true_false_event);
        RightAnswers = g.findViewById(R.id.right_answers_true_false);
        WrongAnswers = g.findViewById(R.id.wrong_answers_true_false);
        WrongAnswers.setText("0");
        RightAnswers.setText("0");
        mAc.getSupportActionBar().hide();
        cursor = mAc.getCursor();
        Bundle saved = getArguments();
        if(!saved.getBoolean(INFINITIVE)) {
            progressBar.setVisibility(View.VISIBLE);
            questions = new ArrayList<>();
        }
        bound = saved.getIntArray(BOUND);
        position = saved.getIntArray(POSITION);
        setTestInfo();
        setQuestions();
        if(mAc.isFirstTime(MainActivity.TRUE_FALSE))
            new MaterialShowcaseView.Builder(mAc)
                    .setTarget(g)
                    .setDelay(200)
                    .setContentText(R.string.tutorial_true_false)
                    .setDismissText(R.string.got_it)
                    .setDismissOnTouch(true)
                    .setDismissTextColor(Color.GREEN)
                    .setMaskColour(getResources().getColor(R.color.colorMask))
                    .setShape(new NoShape())
                    .show();
        return g;
    }

    public void setClickable(boolean b){
        trueButton.setClickable(b);
        falseButton.setClickable(b);
    }

    private void setTestInfo(){
        Random rnd = new Random();
        isCorrect = rnd.nextBoolean();
        int current_pick = rnd.nextInt(position.length);
        int Answer;
        if(questions == null)
            Answer = position[current_pick] + rnd.nextInt(bound[current_pick]);
        else {
            if(wrong_answers_count+right_answers_count!=20) {
                do {
                    Answer = position[current_pick] + rnd.nextInt(bound[current_pick]);
                } while (questions.contains(Answer));
                questions.add(Answer);
            }else{
                Answer = position[current_pick] + rnd.nextInt(bound[current_pick]);
            }
        }
        cursor.moveToPosition(Answer);
        date = cursor.getString(0).trim();
            if (!isCorrect) {
                do {
                    int sAnswer = position[current_pick] + rnd.nextInt(bound[current_pick]);
                    while (sAnswer == Answer)
                        sAnswer = position[current_pick] + rnd.nextInt(bound[current_pick]);
                    cursor.moveToPosition(sAnswer);
                }while (cursor.getString(0).trim().equals(date));
            }
            event = cursor.getString(1);
    }

    private void setQuestions(){
        question_date.setText(date);
        question_event.setText(event);
    }


    @Override
    public void onDetach() {
        super.onDetach();
        try {
            mHandler.removeCallbacks(rnb);
        }catch (Exception e){}
    }

    @Override
    public void onStop() {
        super.onStop();
        Appodeal.hide(getActivity(), Appodeal.BANNER_VIEW);
    }

    @Override
    public void onStart() {
        super.onStart();
        Appodeal.show(getActivity(), Appodeal.BANNER_VIEW);
    }



    public void setResultScreen(){
        int cur_best = bestResult;
        if(bestResult<right_answers_count)
            bestResult = right_answers_count;
        RightAnswers.setText(Integer.toString(right_answers_count));
        WrongAnswers.setText(Integer.toString(wrong_answers_count));
        trueButton.setText(R.string.reset_button);
        falseButton.setText(R.string.exit_button);
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getFragmentManager().popBackStack();
            }
        });
        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restartInfo();
            }
        });
        String mark;
        int color;
        if(right_answers_count < 5) {
            mark = getString(R.string.mark_2);
            color = getResources().getColor(android.R.color.holo_red_light);
        }else if (right_answers_count < 10) {
            mark = getString(R.string.mark_3);
            color = getResources().getColor(android.R.color.holo_red_light);
        }else if (right_answers_count < 15) {
            mark = getString(R.string.mark_4);
            color = getResources().getColor(android.R.color.holo_green_light);
        }else if (right_answers_count < 20) {
            mark = getString(R.string.mark_5);
            color = getResources().getColor(android.R.color.holo_green_light);
        }else if (right_answers_count==20) {
            mark = getString(R.string.mark_20);
            color = getResources().getColor(android.R.color.holo_green_light);
        }else {
            mark = "WOW";
            color = getResources().getColor(android.R.color.holo_green_light);
        }
        if(cur_best==0)
            question_date.setText(getString(R.string.current_result) + " " + right_answers_count +"\n\n"+ getString(R.string.cur_mark));
        else
            question_date.setText(getString(R.string.current_result) + " " + right_answers_count + "\n\n"
                    + getString(R.string.best_result) + " " + cur_best +"\n\n"+ getString(R.string.cur_mark));
        question_event.setTextColor(color);
        question_event.setText(mark);
    }

    public void restartInfo(){
        if(Appodeal.isLoaded(Appodeal.INTERSTITIAL))
            Appodeal.show(getActivity(),Appodeal.INTERSTITIAL);
        right_answers_count = wrong_answers_count = 0;
        RightAnswers.setText(Integer.toString(right_answers_count));
        WrongAnswers.setText(Integer.toString(wrong_answers_count));
        trueButton.setText(R.string.true_button);
        falseButton.setText(R.string.false_button);
        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickResult(true);
            }
        });
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickResult(false);
            }
        });
        questions.clear();
        question_event.setTextColor(getResources().getColor(android.R.color.black));
        progressBar.setProgress(0);
        setQuestions();
    }

    public void onClickResult(boolean button){
        boolean correct;
        if(button)
            correct = isCorrect;
        else
            correct = !isCorrect;
        if(correct){
            right_answers_count++;
            RightAnswers.setText(Integer.toString(right_answers_count));
            question_date.setTextColor(getResources().getColor(R.color.colorTrueButton));
            question_event.setTextColor(getResources().getColor(R.color.colorTrueButton));
        }else{
            wrong_answers_count++;
            WrongAnswers.setText(Integer.toString(wrong_answers_count));
            question_date.setTextColor(getResources().getColor(R.color.colorFalseButton));
            question_event.setTextColor(getResources().getColor(R.color.colorFalseButton));
        }
        if(questions != null)
            progressBar.incrementProgressBy(1);
        setClickable(false);
        setTestInfo();
        mHandler.postDelayed(rnb, 1000);
    }

}
