package ayhan.com.rxjavapractice.operator2_create;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

import ayhan.com.rxjavapractice.R;
import ayhan.com.rxjavapractice.common.CommonUtils;
import ayhan.com.rxjavapractice.databinding.BaseLayoutBinding;
import io.reactivex.Observable;

/**
 * Created by HanAYeon on 2018-04-08.
 */

/*
* range() 함수 : 주어진 값(n) 부터 m 개의 Integer 객체를 발행한다.
* interval, timer 함수가 스케줄러에서 실행되는 반면 range 는 현재 스레드에서 실행된다.
* range() 함수는 for, while 문으로 교체할 수 있다.
* */
public class Range extends AppCompatActivity {

    private BaseLayoutBinding baseLayoutBinding;
    private String printText= "";
    private String printText2= "";
    private String printText3= "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        baseLayoutBinding = DataBindingUtil.setContentView(this, R.layout.base_layout);
        baseLayoutBinding.info.setText(
                "range() 함수 : 주어진 값(n) 부터 m 개의 Integer 객체를 발행한다.\n" +
                "interval, timer 함수가 스케줄러에서 실행되는 반면 range 는 현재 스레드에서 실행된다.\n" +
                "range() 함수는 for, while 문으로 교체할 수 있다.");

        range();
        intervalRange();
        makeWithInterval();
    }

    private void range() {
        Observable<Integer> source = Observable.range(1, 10)
                .filter(num -> num % 2 == 0);
        source.subscribe(data -> printText += data + "\n");

        baseLayoutBinding.text1.setText("range to 1-10 && 짝수 : \n" +  printText);
    }

    /*
    * interval() + range() . 일정한 시간 간격으로 값을 출력하되 range() 함수 처럼 시작 숫자 n 으로 부터
    * m개의 값만 생성한 후 onComplete 이벤트가 발생한다.
    * 즉 기본적인 interval() 함수처럼 무한히 데이터의 흐름을 발행하지 않는다.
    * */
    private void intervalRange() {
        Observable<Long> source = Observable.intervalRange(1, 5, 100L, 100L, TimeUnit.MILLISECONDS);
        source.subscribe(data -> printText2 += data + "\n");
        CommonUtils.sleep(1000);
        baseLayoutBinding.text2.setText("intervalRange : \n"+ printText2);
    }

    // interval 함수와 다른 함수를 조합하여 intervalRange 를 만들어 낼 수도 있다.
    private void makeWithInterval() {

        Observable<Long> source = Observable.interval(100L, TimeUnit.MILLISECONDS)
                .map(val -> val + 1)
                .take(5);
        source.subscribe(data -> printText3 += data + "\n" );
        CommonUtils.sleep(1000);

        baseLayoutBinding.text3.setText("make intervalRange :\n" + printText3);
    }
}
