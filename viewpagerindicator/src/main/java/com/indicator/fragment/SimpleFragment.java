package com.indicator.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by yh on 2016/6/3.
 */
public class SimpleFragment extends Fragment {
    private static final String BUNDLE_TITLE="title";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        String title = "";
        if (bundle != null) {
            title = bundle.getString(BUNDLE_TITLE);
        }
        TextView textView = new TextView(getContext());
        textView.setText(title);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        return textView;
    }

    public static SimpleFragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_TITLE, title);
        SimpleFragment fragment = new SimpleFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
