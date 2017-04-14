package io.beering.beering;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        Handler hd = new Handler();
        hd.postDelayed(new introhandler() , 2000); // 2초 후에 hd 실행
    }

    private class introhandler implements Runnable {
        public void run() {
            startActivity(new Intent(getApplication(), LoginActivity.class)); // 로딩이 끝난후 이동할 Activity
            IntroActivity.this.finish(); // 로딩페이지 Activity Stack에서 제거
        }
    }
}
