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
* switchMap() : concatMap() 함수가 인터리빙이 발생할 수 있는 상황에서 동작의 순서를 보장해 준다면,
* SwitchMap 함수는 순서를 보장하기 위해 기존에 진행 중이던 작업을 바로 중단한다.
* 또한 여러개의 값이 발행되었을 때 가장 마지막에 들어온 값만 처리하고 싶을 때 사용한다.
* 즉 최종적인 값만을 사용할 때 좋다
* */
public class SwitchMap extends AppCompatActivity {

    private BaseLayoutBinding baseLayoutBinding;
    private String text1 ="";
    private String text2 ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        baseLayoutBinding = DataBindingUtil.setContentView(this, R.layout.base_layout);

        baseLayoutBinding.info.setText("switchMap() : concatMap() 함수가 인터리빙이 발생할 수 있는 상황에서 동작의 순서를 보장해 준다면, " +
                "\n SwitchMap 함수는 순서를 보장하기 위해 기존에 진행 중이던 작업을 바로 중단한다." +
                "\n 또한 여러개의 값이 발행되었을 때 가장 마지막에 들어온 값만 처리하고 싶을 때 사용한다." +
                "\n 즉 최종적인 값만을 사용할 때 좋다");

        switchMap();
        usingDoOnNext();
    }


    // concatMap -> reverse To switchMap
    private void switchMap(){
        text1 = "switchMap : ";

        CommonUtils.exampleStart();

        String[] balls = {"1", "3", "5"};
        Observable<String> source = Observable.interval(100L, TimeUnit.MILLISECONDS)
                .map(Long::intValue)
                .map(idx -> balls[idx])
                .take(balls.length)
                .switchMap(ball -> Observable.interval(200L, TimeUnit.MILLISECONDS)
                    .map(notUsed -> ball + "<>")
                    .take(2)
                );

        source.subscribe(data -> text1 += data + "\n");

        CommonUtils.sleep(2000);

        baseLayoutBinding.text1.setText(text1);
    }


    // 위의 메소드에서 내부적으로 어떻게 처리했는지 확인해보자..

    private void usingDoOnNext() {
        text2 = "\nusingDoOnNext : \n";
        CommonUtils.exampleStart();

        String[] balls = {"1", "3", "5"};
        Observable<String> source = Observable.interval(100L, TimeUnit.MILLISECONDS)
                .map(Long::intValue)
                .map(idx -> balls[idx])
                .take(balls.length)
                .doOnNext(data -> text2 += data + "\n")
                .switchMap(
                        ball -> Observable.interval(200L, TimeUnit.MILLISECONDS)
                        .map(notUsed -> ball + "<>")
                        .take(2)
                );
        source.subscribe(data -> text2 += "Result : " + data + "\n");
        CommonUtils.sleep(2000);

        baseLayoutBinding.text2.setText(text2);
    }
}
