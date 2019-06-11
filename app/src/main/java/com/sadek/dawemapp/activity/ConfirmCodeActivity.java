package com.sadek.dawemapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.mukesh.OnOtpCompletionListener;
import com.mukesh.OtpView;
import com.sadek.dawemapp.R;
import com.sadek.dawemapp.interfaces.ActiveAccountInterface;
import com.sadek.dawemapp.model.body.ActiveAccountBody;
import com.sadek.dawemapp.model.response.ActiveAccountResponse;
import com.sadek.dawemapp.presentsers.ActiveAccountPresenter;
import com.sadek.dawemapp.utils.Common;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.paperdb.Paper;

public class ConfirmCodeActivity extends BaseActitvty implements ActiveAccountInterface {


    Unbinder unbinder;

    @BindView(R.id.confirm_code_phone)
    TextView confirm_code_phone;
    @BindView(R.id.otp_view)
    OtpView otp_view;
    boolean reSendCode = false;
    String phoneNumber,userId;
    public static KProgressHUD dialog = null;

    ActiveAccountBody activeAccountBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_code);

        Paper.init(this);

        ButterKnife.bind(this);
        dialog = new KProgressHUD(this);

        userId = getIntent().getStringExtra(Common.USER_ID);
        reSendCode = getIntent().getBooleanExtra(Common.ACCOUNT_STATUS_PENDING, false);
        phoneNumber = getIntent().getStringExtra(Common.USER_PHONE);
        confirm_code_phone.setText(phoneNumber);
        otp_view.setOtpCompletionListener(new OnOtpCompletionListener() {
            @Override
            public void onOtpCompleted(String otp) {
                confirm_code_btnConfirm(null);
            }
        });
        if (reSendCode){
            ActiveAccountPresenter.sendReSendActivationCode(this, userId+"", this);
        }
    }

    private Boolean validate() {
        otp_view.setError(null);

        String code = otp_view.getText().toString();
        if (code.isEmpty() || code.length() < 6) {
            confirm_code_phone.setError(getString(R.string.enter_valid_code));
            return false;
        }

        activeAccountBody = new ActiveAccountBody();
        activeAccountBody.setCode(code);
        activeAccountBody.setUserid(userId + "");
        return true;

    }

    //confirm_code_btnConfirm
    @OnClick(R.id.confirm_code_btnConfirm)
    void confirm_code_btnConfirm(View view) {
        if (!validate())
            return;
        ActiveAccountPresenter.sendActiveAccount(this, activeAccountBody, this);
    }

    @Override
    public void onActiveAccountSuccess(ActiveAccountResponse response) {
        if (response.getAccountstate().equals(Common.ACCOUNT_STATUS_PENDING)) {
            startActivity(new Intent(ConfirmCodeActivity.this, ConfirmCodeActivity.class));
            finish();
        } else if (response.getAccountstate().equals(Common.ACCOUNT_STATUS_ACTIVE)) {
            Paper.book().write(Common.USER_TOKEN, response.getToken());
            startActivity(new Intent(ConfirmCodeActivity.this, MainActivity.class));
            finish();
        }
    }

    @Override
    public void onErorr(String err) {
        Toast.makeText(this, err + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUnauthorized(String accountStateMessage, String userId) {
        Toast.makeText(this, accountStateMessage + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress(Boolean isShowing) {
        try {
            if (isShowing)
                dialog.show();
            else dialog.dismiss();
        } catch (Exception e) {

        }
    }
}
