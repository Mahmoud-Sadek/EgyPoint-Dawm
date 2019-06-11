package com.sadek.dawemapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.bcgdv.asia.lib.ticktock.TickTockView;
import com.sadek.dawemapp.R;
import com.sadek.dawemapp.dialog.OrderTimeDialog;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class OrderTrakingFragment extends Fragment {

    Unbinder unbinder;
    @BindView(R.id.view_ticktock_countdown)
    TickTockView view_ticktock_countdown;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order_traking, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);

        initUi();
        CartDetailsCompleteFragment bottomSheetFragment = new CartDetailsCompleteFragment();
        bottomSheetFragment.show(getChildFragmentManager(), bottomSheetFragment.getTag());

    }

    private void initUi() {
        if (view_ticktock_countdown != null) {
            view_ticktock_countdown.setOnTickListener(new TickTockView.OnTickListener() {
                @Override
                public String getText(long timeRemaining) {
                    int seconds = (int) (timeRemaining / 1000) % 60;
                    int minutes = (int) ((timeRemaining / (1000 * 60)) % 60);
                    int hours = (int) ((timeRemaining / (1000 * 60 * 60)) % 24);
                    int days = (int) (timeRemaining / (1000 * 60 * 60 * 24));
                    boolean hasDays = days > 0;
                    return String.format("%1$02d%4$s %2$02d%5$s %3$02d%6$s",
                            hasDays ? days : hours,
                            hasDays ? hours : minutes,
                            hasDays ? minutes : seconds,
                            hasDays ? "d" : "h",
                            hasDays ? "h" : "m",
                            hasDays ? "m" : "s");
                }
            });
        }


    }


    @Override
    public void onStart() {
        super.onStart();
        Calendar end = Calendar.getInstance();
        end.add(Calendar.MINUTE, 4);
        end.add(Calendar.SECOND, 5);

        Calendar start = Calendar.getInstance();
        start.add(Calendar.MINUTE, -1);
        if (view_ticktock_countdown != null) {
            view_ticktock_countdown.start(start, end);
        }
    }

}
