package ayhan.com.rxjavapractice.operator2_condition;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

import ayhan.com.rxjavapractice.R;
import ayhan.com.rxjavapractice.common.CommonUtils;
import ayhan.com.rxjavapractice.databinding.BaseLayoutBinding;
import io.reactivex.Observable;

import static ayhan.com.rxjavapractice.common.Shape.BLUE;
import static ayhan.com.rxjavapractice.common.Shape.GREEN;
import static ayhan.com.rxjavapractice.common.Shape.RED;
import static ayhan.com.rxjavapractice.common.Shape.SKY;
import static ayhan.com.rxjavapractice.common.Shape.YELLOW;
import static ayhan.com.rxjavapractice.common.Shape.rectangle;

/**
 * Created by HanAYeon on 2018. 4. 16..
 */
public class Amb extends AppCompatActivity {

    private BaseLayoutBinding baseLayoutBinding;
    private String text1 = "";
    private String text2 = "";
    private String text3 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        baseLayoutBinding = DataBindingUtil.setContentView(this, R.layout.base_layout);

        baseLayoutBinding.info.setText("amb() 함수 : ambiguous(모호한) 라는 영어 단어의 줄임말. \n" +
                "여러 개의 Observable 중에서 1개의 Observable 을 선택하는데, 선택 기준은 가장 먼저 데이터를 발행하는 Observable 이다. \n" +
                "이 외에 오는 모든 Observable 에서 발행되는 데이터는 무시한다.");

        ambExam();

    }

    private void ambExam() {
        String[] data1 = {RED, GREEN, BLUE};
        String[] data2 = {rectangle(YELLOW), rectangle(SKY)};

        List<Observable<String>> source = Arrays.asList(
                Observable.fromArray(data1).doOnComplete(() -> text1 = "Observable#1 : onComplete() \n"),
                Observable.fromArray(data2).doOnComplete(() -> text2 = "Observable#2 : onComplete() \n"));

        Observable.amb(source)
                .doOnComplete(() -> text3 = "Result : onComplete() \n")
                .subscribe(data -> text3 += data);

        CommonUtils.sleep(10000);
        CommonUtils.exampleComplete();

        baseLayoutBinding.text1.setText(text1 + text2 + text3);
     }
}
