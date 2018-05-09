package ayhan.com.rxjavapractice.operator2_combine;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

import ayhan.com.rxjavapractice.R;
import ayhan.com.rxjavapractice.common.CommonUtils;
import ayhan.com.rxjavapractice.databinding.BaseLayoutBinding;
import io.reactivex.Observable;

import static ayhan.com.rxjavapractice.common.Shape.GREEN;
import static ayhan.com.rxjavapractice.common.Shape.PUPPLE;
import static ayhan.com.rxjavapractice.common.Shape.RED;
import static ayhan.com.rxjavapractice.common.Shape.SKY;
import static ayhan.com.rxjavapractice.common.Shape.YELLOW;

/**
 * Created by HanAYeon on 2018-04-11.
 */

public class Merge extends AppCompatActivity {

    private BaseLayoutBinding baseLayoutBinding;
    private String text1 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        baseLayoutBinding = DataBindingUtil.setContentView(this, R.layout.base_layout);

        baseLayoutBinding.info.setText("merge() : 다른 함수와 비교했을 때 가장 단순한 결합 함수. \n" +
                "입력 Observable의 순서와 모든 Observable이 데이터를 발행하슨지 등에 관여하지않고 어느 것이든 업스트림에서 먼저 입력되는 데이터를 그대로 발행한다.");

        merge();
    }

    private void merge() {
        String[] data1 = {RED, GREEN}; // 1, 3
        String[] data2 = {YELLOW, SKY, PUPPLE}; // 2, 4, 6

        Observable<String> source1 = Observable.interval(0L, 100L, TimeUnit.MILLISECONDS)
                .map(Long::intValue)
                .map(idx -> data1[idx])
                .take(data1.length);

        Observable<String> source2 = Observable.interval(50L, TimeUnit.MILLISECONDS)
                .map(Long::intValue)
                .map(idx -> data2[idx])
                .take(data2.length);

        Observable<String> source = Observable.merge(source1, source2);

        source.subscribe(data -> text1 += data + "\n");
        CommonUtils.sleep(1000);

        baseLayoutBinding.text1.setText(text1);
    }

}
