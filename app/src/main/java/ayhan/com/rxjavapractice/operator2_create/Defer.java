package ayhan.com.rxjavapractice.operator2_create;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ayhan.com.rxjavapractice.R;
import ayhan.com.rxjavapractice.databinding.BaseLayoutBinding;


/**
 * Created by HanAYeon on 2018-04-08.
 */

/*
* defer() : timer() 함수와 비슷하지만 데이터 흐름 생성을 구독자가 subscribe() 함수를 호출할 때 까지 미룰 수 있다.
* 스케줄러가 없으므로 메인 스레드에서 실행된다. callable 객체이므로 구독자가 subscribe 를 호출할 때 까지 call() 메소드의 호출을 미룰 수 있다.*/
public class Defer extends AppCompatActivity {

    private BaseLayoutBinding baseLayoutBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        baseLayoutBinding = DataBindingUtil.setContentView(this, R.layout.base_layout);

        baseLayoutBinding.info.setText("defer() : timer() 함수와 비슷하지만 데이터 흐름 생성을 구독자가 subscribe() 함수를 호출할 때 까지 미룰 수 있다\n" +
                "스케줄러가 없으므로 메인 스레드에서 실행된다. callable 객체이므로 구독자가 subscribe 를 호출할 때 까지 call() 메소드의 호출을 미룰 수 있다.\n" +
                "subscribe() 함수를 호출할 때의 상황을 반영하여 데이터 흐름의 생성을 지연하는 효과를 보여준다.");

        baseLayoutBinding.text1.setText("");
    }
}
