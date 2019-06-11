package com.sadek.dawemapp.service;

import com.sadek.dawemapp.model.body.ActiveAccountBody;
import com.sadek.dawemapp.model.body.LoginBody;
import com.sadek.dawemapp.model.body.SignupBody;
import com.sadek.dawemapp.model.body.SocialLoginBody;
import com.sadek.dawemapp.model.body.SocialSignupBody;
import com.sadek.dawemapp.model.response.ActiveAccountResponse;
import com.sadek.dawemapp.model.response.SignupResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Mahmoud Sadek on 1/11/2019.
 */

public interface Router {
    //Create User Account
    @POST("{lang}/api/User/CreateAccount")
    Call<SignupResponse> getCreateAccountResponse(@Body SignupBody signupBody, @Path("lang") String lang);

    //Active User Account
    @POST("{lang}/api/Auth/ActiveAccount")
    Call<ActiveAccountResponse> getActiveAccountResponse(@Body ActiveAccountBody activeAccountBody, @Path("lang") String lang);

    //Login
    @POST("api/Auth/Login")
    Call<ActiveAccountResponse> getLoginResponse(@Body LoginBody loginBody);

    //ReSendActivationCode
    @POST("{lang}/api/user/{userId}/ReSendActivationCode")
    Call<ActiveAccountResponse> getReSendActivationCodeResponse(@Path("lang") String lang, @Path("userId") String userId);

    //Login Social
    @POST("{lang}/api/Auth/OpenAuthentication/Login")
    Call<ActiveAccountResponse> getLoginSocialResponse(@Body SocialLoginBody socialLoginBody, @Path("lang") String lang);

    //CreateAccount Social
    @POST("{lang}/api/User/OpenAuthentication/CreateAccount")
    Call<ActiveAccountResponse> getCreateAccountSocialResponse(@Body SocialSignupBody socialSignupBody, @Path("lang") String lang);

}
