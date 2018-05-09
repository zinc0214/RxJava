package ayhan.com.rxjavapractice.operator2_conversion;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ayhan.com.rxjavapractice.R;
import ayhan.com.rxjavapractice.common.CommonUtils;
import ayhan.com.rxjavapractice.databinding.BaseLayoutBinding;
import io.reactivex.Observable;

import static ayhan.com.rxjavapractice.common.Shape.BLUE;
import static ayhan.com.rxjavapractice.common.Shape.GREEN;
import static ayhan.com.rxjavapractice.common.Shape.RED;

/**
 * Created by HanAYeon on 2018-04-11.
 */

/*
* scan() : reduce() 함수와 비슷하다.
* reduce() 함수가 Observable에서 모든 데이터가 입력된 후 그것을 종합하여 마지막 1개의 데이터 만을 구독자에게 발행한다면
* scan() 함수는 실행될 때마다 입력값에 맞는 중간 결과 및 최종 결과를 구독자에게 발행한다.
* */

public class Scan extends AppCompatActivity {

    private BaseLayoutBinding baseLayoutBinding;
    private String text1 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        baseLayoutBinding = DataBindingUtil.setContentView(this, R.layout.base_layout);

        baseLayoutBinding.info.setText("scan() : reduce() 함수와 비슷하다. \n" +
                "reduce() 함수가 Observable에서 모든 데이터가 입력된 후 그것을 종합하여 마지막 1개의 데이터 만을 구독자에게 발행한다면," +
                "\n scan() 함수는 실행될 때마다 입력값에 맞는 중간 결과 및 최종 결과를 구독자에게 발행한다.");

        scan();
    }

    private void scan() {

        String[] balls = {RED, GREEN, BLUE};
        Observable<String> source = Observable.fromArray(balls)
                .scan((ball1, ball2) -> ball2 + "(" + ball1 + ")");
        source.subscribe(data -> text1 += data + "\n");

        CommonUtils.exampleComplete();

        baseLayoutBinding.text1.setText(text1);
    }
}
