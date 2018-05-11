package ayhan.com.rxjavapractice.androidexam2;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

import ayhan.com.rxjavapractice.R;
import ayhan.com.rxjavapractice.databinding.ButtonWithTextLayoutBinding;


/**
 * Created by HanAYeon on 2018. 5. 10..
 */
public class TimerTaskActivity extends AppCompatActivity {

    private ButtonWithTextLayoutBinding baseLayoutBinding;
    private int count;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        baseLayoutBinding = DataBindingUtil.setContentView(this, R.layout.button_with_text_layout);

        countDownTimer();
        initHandler();

        baseLayoutBinding.btn1.setOnClickListener(l -> startTimerTask());
        baseLayoutBinding.btn2.setOnClickListener(l -> startCountDownTask());
        baseLayoutBinding.btn3.setOnClickListener(l -> startHandler());


    }

    //일반적은 timer 클래스 활용 방법
    private final int DELAY = 0;
    private final int PERIOD = 1000;
    private Timer timer;

    private void timerStart() {
        count = 0;
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() { //scheduleAtFixedRate : 설정된 지연 시간 후 고정된 간격으로 주기적으로 실행. 정확한 간격으로 실행시켜야 할 때 좋다.
            @Override
            public void run() {
                runOnUiThread(() -> {
                    //해야할 일 적음
                    baseLayoutBinding.text1.setText(String.valueOf(count++));
                });
            }
        }, DELAY, PERIOD);
    }

    private void timerStop() {
        if(timer != null) {
            timer.cancel();
        }
    }



    //실행 횟수를 제한 해 둔 타이머
    private static final int MILLISINFUTURE =  11 * 1000;
    private static final int COUNT_DOWN_INTERVAL = 1000;
    private CountDownTimer countDownTimer;

    private void countDownTimerStart() {
        count = 10;
        countDownTimer.start();
    }

    private void countDownTimer() {
        countDownTimer = new CountDownTimer(MILLISINFUTURE, COUNT_DOWN_INTERVAL) {
            @Override
            public void onTick(long millisUntilFinished) { // 11초 동안 1초에 한 번씩 실행된다.
                baseLayoutBinding.text2.setText(String.valueOf(count--));
            }

            @Override
            public void onFinish() { // 시간만큼 실행된 후에는 onFinish() 메소드가 호출된다.
                baseLayoutBinding.text2.setText("Finish ...");
            }
        };
    }

    private void countDownTimerStop() {
        if(countDownTimer != null) {
            countDownTimer.cancel();
        }
    }


    //Handler 클래스를 사용하여 Timer 클래스를 대체하는 방법
    private Handler handler;

    private Runnable runnableTimer = new Runnable() {
        @Override
        public void run() {
            //반복 실행할 코드 작성
            baseLayoutBinding.text3.setText(String.valueOf(count++));
            handler.postDelayed(this, 1000);
        }
    };

    private void initHandler() {
        handler = new Handler();
    }

    private void handlerStart() {
        handler.postDelayed(runnableTimer,0);
    }

    private void handlerStop() {
        if(handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    private void stop() {
        timerStop();
        countDownTimerStop();
        handlerStop();
    }

    private void startTimerTask() {
        stop();
        timerStart();
    }

    private void startCountDownTask() {
        stop();
        countDownTimerStart();
    }

    private void startHandler() {
        stop();
        handlerStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stop();
    }
}