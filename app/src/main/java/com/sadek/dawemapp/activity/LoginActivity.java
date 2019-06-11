package com.sadek.dawemapp.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.sadek.dawemapp.R;
import com.sadek.dawemapp.adapter.SpinnerAdapter;
import com.sadek.dawemapp.dialog.CompleteSocialLoginDialog;
import com.sadek.dawemapp.interfaces.ActiveAccountInterface;
import com.sadek.dawemapp.model.body.LoginBody;
import com.sadek.dawemapp.model.body.SocialLoginBody;
import com.sadek.dawemapp.model.body.SocialSignupBody;
import com.sadek.dawemapp.model.response.ActiveAccountResponse;
import com.sadek.dawemapp.presentsers.LoginPresenter;
import com.sadek.dawemapp.utils.Common;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.paperdb.Paper;

public class LoginActivity extends BaseActitvty implements ActiveAccountInterface {

    Button btnSignup;
    Spinner spinnerDropDown;
    String[] cities = {
            "AR",
            "EN"
    };

    private static final int RC_SIGN_IN = 1010;

    @BindView(R.id.login_phone_number_txt)
    EditText login_phone_number_txt;
    @BindView(R.id.login_password_txt)
    EditText login_password_txt;

    @BindView(R.id.facebook_login_button)
    LoginButton btnFacebook;

    public static KProgressHUD dialog = null;


    LoginBody loginBody;
    CallbackManager callbackManager;
    String email = "", providerId, providerName;

    GoogleSignInClient mGoogleSignInClient;
    TwitterLoginButton mLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Paper.init(this);
        ButterKnife.bind(this);
        btnSignup = findViewById(R.id.btnSignup);
        dialog = new KProgressHUD(this);

        //facebook
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        Common.keyHash(this);

        // Get reference of SpinnerView from layout/main_activity.xml
        spinnerDropDown = (Spinner) findViewById(R.id.spinner1);
        List<String> stringList = new ArrayList<String>(Arrays.asList(cities));
        ArrayAdapter<String> adapter = new SpinnerAdapter(this,
                R.layout.item_login_language_spinner, stringList);

        spinnerDropDown.setAdapter(adapter);

        spinnerDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // Get select item
                int sid = spinnerDropDown.getSelectedItemPosition();
                Toast.makeText(getBaseContext(), "You have selected City : " + cities[sid],
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
                finish();
            }
        });

        facebookLogin();
        LoginManager.getInstance().logOut();

        socialLoginConfig();
    }

    private void socialLoginConfig() {

        configureGoogleSignIn();

        //TWITTER
        TwitterConfig config = new TwitterConfig.Builder(this)
                .logger(new DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(new TwitterAuthConfig(getString(R.string.twitter_consumer_key), getString(R.string.twitter_consumer_secret)))
                .debug(true)
                .build();
        Twitter.initialize(config);
        mLoginButton = findViewById(R.id.twitter_login_button);
        mLoginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                Log.d("Twitter", "twitterLogin:success" + result);
                handleTwitterSession(result.data);
            }

            @Override
            public void failure(TwitterException exception) {
                Log.w("Twitter", "twitterLogin:failure", exception);
//                updateUI(null);
            }
        });
    }

    private void handleTwitterSession(TwitterSession data) {

        providerName = "Twitter";
        //Do Login
        SocialLoginBody socialLoginBody = new SocialLoginBody();
        socialLoginBody.setProvidername(providerName);
        socialLoginBody.setProviderid(data.getUserId() + "");

        LoginPresenter.sendSocialLogin(getBaseContext(), socialLoginBody, LoginActivity.this);
    }

    private void configureGoogleSignIn() {
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()//request email id
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }


    private Boolean validate() {
        login_phone_number_txt.setError(null);
        login_password_txt.setError(null);
        String phone = login_phone_number_txt.getText().toString();
        String pass = login_password_txt.getText().toString();
        if (phone.isEmpty() || phone.length() < 11) {
            login_phone_number_txt.setError(getString(R.string.enter_valid_phone));
            return false;
        } else if (pass.isEmpty() || pass.length() < 8) {
            login_password_txt.setError(getString(R.string.enter_valid_password));
            return false;
        }

        loginBody = new LoginBody();
        loginBody.setLoginname(phone);
        loginBody.setPassword(pass);
        return true;

    }

    //btnLogin
    @OnClick(R.id.btnLogin)
    void btnLogin(View view) {
        providerId = null;
        if (!validate())
            return;
        LoginPresenter.sendLogin(this, loginBody, this);
    }

    @Override
    public void onActiveAccountSuccess(ActiveAccountResponse response) {
        LoginManager.getInstance().logOut();
        if (response.getAccountstate().equals(Common.ACCOUNT_STATUS_PENDING)) {
            startActivity(new Intent(LoginActivity.this, ConfirmCodeActivity.class));
            finish();
        } else if (response.getAccountstate().equals(Common.ACCOUNT_STATUS_ACTIVE)) {
            Paper.book().write(Common.USER_TOKEN, response.getToken());
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
    }

    @Override
    public void onErorr(String err) {
        LoginManager.getInstance().logOut();
        Toast.makeText(this, err + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUnauthorized(String accountStateMessage, String userId) {
        LoginManager.getInstance().logOut();
        if (accountStateMessage.equals(Common.ACCOUNT_STATUS_NOT_FOUND)) {
            if (providerId != null)
                updateUI();
        }
        Toast.makeText(this, accountStateMessage + "", Toast.LENGTH_SHORT).show();

        if (accountStateMessage.equals(Common.ACCOUNT_STATUS_PENDING)) {
            Intent activeCodeIntent = new Intent(this, ConfirmCodeActivity.class);
            activeCodeIntent.putExtra(Common.USER_ID, userId);
            activeCodeIntent.putExtra(Common.ACCOUNT_STATUS_PENDING, true);
            startActivity(activeCodeIntent);
            finish();
        }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            //method to handle google sign in result
            handleSignInResult(task);
        }
        facebookLogin();
        if (requestCode == 140)
            mLoginButton.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick(R.id.btnTwitter)
    public void btnTwitter(View view) {
        mLoginButton.performClick();
    }


    //google
    @OnClick(R.id.btnGoogle)
    public void btnGoogle(View view) {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    //facebook
    @OnClick(R.id.btnFacebook)
    public void onButtonFacebookClick(View view) {
        btnFacebook.performClick();
    }

    private void getUserProfile(AccessToken currentAccessToken) {
        GraphRequest request = GraphRequest.newMeRequest(
                currentAccessToken, new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
//                        Log.d("TAG", object.toString());
                        try {

                            if (response.toString().contains("email")) {
                                email = object.getString("email");
                            }
                            String name = object.getString("name");
                            providerId = object.getString("id");
                            providerName = "Facebook";
                            //Do Login
                            SocialLoginBody socialLoginBody = new SocialLoginBody();
                            socialLoginBody.setProvidername(providerName);
                            socialLoginBody.setProviderid(providerId);

//                            externalLoginBody.setName(name);
//                            if (email.isEmpty()) {
//                                externalLoginBody.setEmailAddress(id + "@facebook.com");
//                            } else {
//                                externalLoginBody.setEmailAddress(email);
//                            }

                            LoginPresenter.sendSocialLogin(getBaseContext(), socialLoginBody, LoginActivity.this);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "name,first_name,last_name,email,id");
        request.setParameters(parameters);
        request.executeAsync();

    }

    private void facebookLogin() {
        boolean loggedOut = AccessToken.getCurrentAccessToken() == null;
        if (!loggedOut) {
            getUserProfile(AccessToken.getCurrentAccessToken());
        }
        btnFacebook.setReadPermissions(Arrays.asList(
                "public_profile", "email"));

        callbackManager = CallbackManager.Factory.create();

        // Callback registration
        btnFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Log.v("LoginActivity", response.toString());

                                try {
                                    String email = object.getString("email");
                                    String name = object.getString("name");
                                    String id = object.getString("id");

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email");
                request.setParameters(parameters);
                request.executeAsync();


            }

            @Override
            public void onCancel() {
                // App code
                Log.v("LoginActivity", "cancel");
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Log.v("LoginActivity", exception.getCause().toString());
            }
        });


    }

    CompleteSocialLoginDialog completeSocialLoginDialog;

    private void updateUI() {


        completeSocialLoginDialog = new CompleteSocialLoginDialog(this, new CompleteSocialLoginDialog.DialogInterAction() {
            @Override
            public void onDismissed() {
                completeSocialLoginDialog.dismiss();
                Toast.makeText(LoginActivity.this, getString(R.string.error_not_completed), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSubmit(SocialSignupBody socialSignupBody) {
                socialSignupBody.setEmailaddress(email);
                socialSignupBody.setProviderid(providerId);
                socialSignupBody.setProvidername(providerName);
                LoginPresenter.sendSignupSocial(getBaseContext(), socialSignupBody, LoginActivity.this);
            }
        });
        completeSocialLoginDialog.show();

    }


    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            getProfileInformation(account);

            //show toast
            Toast.makeText(this, "Google Sign In Successful.", Toast.LENGTH_SHORT).show();

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.e("TAG", "signInResult:failed code=" + e.getStatusCode());

            //show toast
            Toast.makeText(this, "Failed to do Sign In : " + e.getStatusCode(), Toast.LENGTH_SHORT).show();

            //update Ui for this
            getProfileInformation(null);
        }
    }

    private void getProfileInformation(GoogleSignInAccount acct) {
        if (acct != null) {

            //user display name
            String personName = acct.getDisplayName();

            //user first name
            String personGivenName = acct.getGivenName();

            //user last name
            String personFamilyName = acct.getFamilyName();

            //user email id
            String personEmail = acct.getEmail();

            //user unique id
            String personId = acct.getId();

            //user profile pic
            Uri personPhoto = acct.getPhotoUrl();

            providerName = "Google";
            //Do Login
            SocialLoginBody socialLoginBody = new SocialLoginBody();
            socialLoginBody.setProvidername(providerName);
            socialLoginBody.setProviderid(personId);

            LoginPresenter.sendSocialLogin(getBaseContext(), socialLoginBody, LoginActivity.this);
        }
    }

}
