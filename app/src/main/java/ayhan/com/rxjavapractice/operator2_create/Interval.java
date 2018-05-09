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
 * Created by Administrator on 2018-04-08.
 */

/*
* inerval 함수 : 일정 시간 간격으로 데이터 흐름을 생성한다. 기본적으로 영원히 지속 실행되기 때문에 폴링 용도로 많이 사용된다.
*
* 두가지 원형
* 1. public static Observable<Long> interval(long period, TimeUnit unit)
* 2. public static Observable<Long> interval(long initialDelay, long period, TimeUnit unit)
*
* period = 일정시간. 해당 시간만큼 쉬었다가 데이터를 발행한다.
* initialDelay = 최초 지연 시간. 처음에 지연시간을 주고 데이터 발행을 시작한다.
*
* */

public class Interval extends AppCompatActivity {

    private BaseLayoutBinding baseLayoutBinding;
    private String printText= "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        baseLayoutBinding = DataBindingUtil.setContentView(this, R.layout.base_layout);

        baseLayoutBinding.info.setText(
                "inerval 함수 : 일정 시간 간격으로 데이터 흐름을 생성한다. 기본적으로 영원히 지속 실행되기 때문에 폴링 용도로 많이 사용된다.\n" +
                "*\n" +
                "두가지 원형\n" +
                "1. public static Observable<Long> interval(long period, TimeUnit unit)\n" +
                "2. public static Observable<Long> interval(long initialDelay, long period, TimeUnit unit)\n" +
                "\n" +
                "period = 일정시간. 해당 시간만큼 쉬었다가 데이터를 발행한다.\n" +
                "initialDelay = 최초 지연 시간. 처음에 지연시간을 주고 데이터 발행을 시작한다.\n");

        printNumbers();
    }
/*
* 100ms 간격으로 0 부터 데이터를 발행한 후 map() 함수를 호출하여 입력된 값에 + 1 을 한 후 100을 곱한다.
* 이후  take (5) 을 통해 최초 5개의 데이터 만을 발행시킨다.
* */
    private void printNumbers() {
        CommonUtils.exampleStart(); // 시작 시간을 표시하는 유틸리티 메소드.
        Observable<Long> source = Observable.interval(100L, TimeUnit.MILLISECONDS)
                .map(data -> (data + 1) * 100)
                .take(5);

        source.subscribe(data -> printText += data);
        CommonUtils.sleep(1000); // 다른 스레드에서 실행이 완료될 때 까지 기다리기 위해서 사용한다.

        baseLayoutBinding.text1.setText("printNumbers : " + printText);
    }



}
