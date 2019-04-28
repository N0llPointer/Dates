package com.nollpointer.dates.dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.nollpointer.dates.R;

public class TestSettingsDialog extends BottomSheetDialogFragment {

    public static MoreInfoDialog newInstance() {
        MoreInfoDialog dialog = new MoreInfoDialog();
        Bundle args = new Bundle();
        dialog.setArguments(args);
        return dialog;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.dialog_test_settings_layout, container, false);

        Button doneButton = mainView.findViewById(R.id.test_settings_dialog_done_button);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestSettingsDialog.this.dismiss();
            }
        });

        return mainView;
    }
}