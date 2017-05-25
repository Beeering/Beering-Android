package io.beering.beering;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import cz.msebera.android.httpclient.Header;
import io.beering.beering.Proxy.Proxy;

public class LoginActivity extends AppCompatActivity {
    CallbackManager callbackManager;
    Button facebookBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // 페북 로그인 커스텀 버튼
        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {


                Proxy.getUserId("/users/get", Profile.getCurrentProfile().getId(), new AsyncHttpResponseHandler() {
                    String str = "";

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        try {
                            str = new String(responseBody, "UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        Log.d("유저겟-------성공-----------", str);

                        // str 내용: {"resultCode":0,"info":[]}
                        // info가 빈배열이면 없는 유저로 판단. 신규유저로 등록
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        try {
                            str = new String(responseBody, "UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        Log.d("유저겟---------실패---------", str);
                    }
                });



                Toast.makeText(getApplicationContext(), "성공: "+Profile.getCurrentProfile().getId(), Toast.LENGTH_LONG).show();

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), "취소", Toast.LENGTH_LONG).show();
                Log.d("Cancel!!", "Login");
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(), "에러", Toast.LENGTH_LONG).show();
                Log.d("Error!!!", "Login");
            }
        });

    }

    public void onClickFaceBookLogin(View v) {
        LoginManager.getInstance().logInWithReadPermissions(
                this,
                Arrays.asList("user_photos", "email", "user_birthday", "public_profile")
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
