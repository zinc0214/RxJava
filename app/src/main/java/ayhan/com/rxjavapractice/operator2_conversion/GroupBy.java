package ayhan.com.rxjavapractice.operator2_conversion;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ayhan.com.rxjavapractice.R;
import ayhan.com.rxjavapractice.common.CommonUtils;
import ayhan.com.rxjavapractice.common.Shape;
import ayhan.com.rxjavapractice.databinding.BaseLayoutBinding;
import io.reactivex.Observable;
import io.reactivex.observables.GroupedObservable;

import static ayhan.com.rxjavapractice.common.Shape.PUPPLE;
import static ayhan.com.rxjavapractice.common.Shape.SKY;
import static ayhan.com.rxjavapractice.common.Shape.YELLOW;
import static ayhan.com.rxjavapractice.common.Shape.triangle;

/**
 * Created by HanAYeon on 2018-04-11.
 */

/*
* groupBy() : 어떤 기준(keySelector 인자)으로 단일 Observable 을 여러 개로 이루어진 Observable 그룹으로 만든다.*/
public class GroupBy extends AppCompatActivity {

    private BaseLayoutBinding baseLayoutBinding;
    private String text1 = "";
    private String text2 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        baseLayoutBinding = DataBindingUtil.setContentView(this, R.layout.base_layout);

        baseLayoutBinding.info.setText("groupBy() : 어떤 기준(keySelector 인자)으로 단일 Observable 을 여러 개로 이루어진 \n" +
                "Observable 그룹으로 만든다. \n\n" +
                "map() vs flatMap() vs groudBy() \n" +
                "map() : 1개의 데이터를 다른 값이나 다른 타입으로 변환 \n" +
                "flatMap() : 1개의 값을 받아서 여러 개의 데이터로 확장 \n" +
                "groupBy() : 값들을 받아서 어떤 기준에 맞는 새로운 Observable 다수를 생성 \n");

        groupBy();
        groupByFilter();
    }

    private void groupBy() {

        String[] objs = {PUPPLE,SKY,triangle(YELLOW),YELLOW,triangle(PUPPLE),triangle(SKY)};
        Observable<GroupedObservable<String, String>> source =
                Observable.fromArray(objs)
                .groupBy(Shape::getShape);

        source.subscribe( obj -> {
            obj.subscribe(val -> text1 += "GROUP :" + obj.getKey() + "\t Value :" + val + "\n");
        });


        CommonUtils.exampleComplete();

        baseLayoutBinding.text1.setText(text1);
    }

    private void groupByFilter() {

        String[] objs = {PUPPLE,SKY,triangle(YELLOW),YELLOW,triangle(PUPPLE),triangle(SKY)};
        Observable<GroupedObservable<String, String>> source =
                Observable.fromArray(objs)
                        .groupBy(Shape::getShape);

        source.subscribe( obj -> obj.filter(val -> obj.getKey().equals(Shape.BALL))
                .subscribe(val -> text2 += "GROUP :" + obj.getKey() + "\t Value :" + val + "\n"));

        CommonUtils.exampleComplete();

        baseLayoutBinding.text2.setText(text2);
    }
}
