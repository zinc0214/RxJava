package ayhan.com.rxjavapractice.operator2_conversion;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

import ayhan.com.rxjavapractice.R;
import ayhan.com.rxjavapractice.common.CommonUtils;
import ayhan.com.rxjavapractice.databinding.BaseLayoutBinding;
import io.reactivex.Observable;

/**
 * Created by HanAYeon on 2018-04-11.
 */

/*
* concatMap () : flatMap() 함수와 매우 비슷하다. flatMap() 은 먼저 들어온 데이터를 처리하는 도중에
* 새로운 데이터가 들어오면 나중에 들어온 데이터의 처리 결과가 먼저 출력될 수도 있다. = 인터리빙(interleaving, 끼어들기)
* concatMap() 함수는 먼저 들어온 데이터 순서대로 처리해서 결과를 낼 수 있도록 보장해준다.*/
public class ConcatMap extends AppCompatActivity {

    private BaseLayoutBinding baseLayoutBinding;
    private String text1 = "";
    private String text2 ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        baseLayoutBinding = DataBindingUtil.setContentView(this, R.layout.base_layout);

        baseLayoutBinding.info.setText("concatMap () : flatMap() 함수와 매우 비슷하다. flatMap() 은 먼저 들어온 데이터를 처리하는 도중에" +
                "새로운 데이터가 들어오면 나중에 들어온 데이터의 처리 결과가 먼저 출력될 수도 있다. = 인터리빙(interleaving, 끼어들기)\n" +
                "concatMap() 함수는 먼저 들어온 데이터 순서대로 처리해서 결과를 낼 수 있도록 보장해준다.");


        concatMap();
      //  switchToFlatMap();
    }

    private void concatMap() {
        CommonUtils.exampleStart(); // 시간 측정을 위해서 호출

        String[] balls = {"1", "3", "5"};
        Observable<String> source = Observable.interval(100L, TimeUnit.MILLISECONDS) // 100ms의 간격으로 interval() 함수를 호출한다.
                // interval 함수 : 일정 시간 간격으로 데이터 흐름을 생성
                .map(Long::intValue) // long 객체를 Integer 객체 값으려 변환한다.
                .map(idx -> balls[idx]) // 숫자를 1, 3, 5 문자열로 변환한다.
                .take(balls.length) // balls의 개수 만큼만 take 해오므로 배열의 크기를 초과하지 않는다.
                .concatMap(ball -> Observable.interval(200L, TimeUnit.MILLISECONDS) // 200ms 간격으로 interval() 함수를 호출함으로 100ms 로 호출한 것과 순서가 바뀔 수 있다.
                                // 200ms 로 호출할 때 순서가 바뀌지 않도록 concatMap 을 사용한다.
                .map(notUsed -> ball + "<>")
                .take(2) // 각 항목 당 발행되는 수를 지정한다.
                );

        source.subscribe(data -> text1 += data + "\n");
        CommonUtils.sleep(2000);

        baseLayoutBinding.text1.setText(text1);
    }

    // concatMap() 보다 실행속도가 빠르다. 인터리빙을 허용하기 때문.
    private void switchToFlatMap() {

        CommonUtils.exampleStart();

        String[] balls = {"1", "3", "5"};
        Observable<String> source = Observable.interval(100L, TimeUnit.MILLISECONDS)
                .map(Long::intValue)
                .map(idx -> balls[idx])
                .flatMap(ball -> Observable.interval(200L, TimeUnit.MILLISECONDS)
                .map(notUsed -> ball + "<>")
                .take(2)
                );

        source.subscribe(data -> text2 += data +"\n");
        CommonUtils.sleep(2000);

        baseLayoutBinding.text2.setText(text2);
    }
}
