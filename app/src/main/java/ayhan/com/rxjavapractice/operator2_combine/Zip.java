package ayhan.com.rxjavapractice.operator2_combine;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.apache.commons.lang3.tuple.Pair;

import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

import ayhan.com.rxjavapractice.R;
import ayhan.com.rxjavapractice.common.CommonUtils;
import ayhan.com.rxjavapractice.common.Shape;
import ayhan.com.rxjavapractice.databinding.BaseLayoutBinding;
import io.reactivex.Observable;

import static ayhan.com.rxjavapractice.common.Shape.BALL;
import static ayhan.com.rxjavapractice.common.Shape.PENTAGON;
import static ayhan.com.rxjavapractice.common.Shape.PUPPLE;
import static ayhan.com.rxjavapractice.common.Shape.SKY;
import static ayhan.com.rxjavapractice.common.Shape.STAR;
import static ayhan.com.rxjavapractice.common.Shape.YELLOW;
import static ayhan.com.rxjavapractice.common.Shape.triangle;
import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * Created by HanAYeon on 2018-04-11.
 */

public class Zip extends AppCompatActivity {

    private BaseLayoutBinding baseLayoutBinding;
    private String text1 = "";
    private String text2 = "";
    private String text3 = "";
    private String text4 = "";
    private String text5 = "";
    private String text6 = "";
    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        baseLayoutBinding = DataBindingUtil.setContentView(this, R.layout.base_layout);

        baseLayoutBinding.info.setText("zip(): 각각의 Observable을 모두 활용해 2개 혹은 그 이상의 Observable 을 결합한다. \n" +
                "2가의 Observable 을 결합하고자 하면 그2개의 Observable 에서 모두 데이터를 발행해야만 결합할 수 있다. 그 전까지는 발행을 기다린다.");

        zipExample();
        zipNumbers();
        zipInterval();
        calculateToElectricBill();
        calculateToElectricBill2();
        zipWithNumbers();

    }

    private void zipExample() {
        text1 = "zip Example : \n";

        String[] shapes = {BALL, PENTAGON, STAR};
        String[] colorTriangles = {triangle(YELLOW), triangle(PUPPLE), triangle(SKY)};

        Observable<String> source = Observable.zip(
                Observable.fromArray(shapes).map(Shape::getSuffix), // getSuffix 를 통해서 접미사를 가져온다.
                Observable.fromArray(colorTriangles).map(Shape::getColor), // colorTriangle 에서 값을 받아와 모양의 색상값을 반환한다.
                (suffix, color) -> color + suffix);

        source.subscribe(data -> text1 += data + "\n");
        CommonUtils.exampleComplete();

        baseLayoutBinding.text1.setText(text1);
    }


    private void zipNumbers() {
        text2 = "zip with num : \n";

        Observable<Integer> source = Observable.zip(
                Observable.just(100, 200, 300),
                Observable.just(10, 20, 30),
                Observable.just(1, 2, 3),
                (a, b, c) -> a + b + c);

        source.subscribe(data -> text2 += data + "\n");

        baseLayoutBinding.text2.setText(text2);
    }

    // 시간과 데이터를 결합한다. zipInterval 기법이라고도 한다.
    // 시간은 201 , 401, 601 처럼 200ms 간격으로 0, 1, 2 의  값을 발행한다.
    private void zipInterval() {
        text3 = "zipInterval : \n";

        Observable<String> source = Observable.zip(
                Observable.just("JaeHo", "HyunSoo", "ByungGab"),
                Observable.interval(200L, TimeUnit.MILLISECONDS),
                (value, time) -> value);

        CommonUtils.exampleStart();
        source.subscribe(data -> text3 += data + "\n");
        CommonUtils.sleep(2000);

        baseLayoutBinding.text3.setText(text3);
    }


    private void calculateToElectricBill() {
        text4 = "전력량 예제 \n 요금은 다음과 같다. \n" +
                "기본 요금 (원 / 호 ) | 전력량 요금 (원 / kWh) \n" +
                "200kWh 이하 사용 : 910 원 | 처음 200kWh까지 : 93.3 \n" +
                "201 ~ 400 kWh 사용 : 1600 원 | 다음 200kWh까지 : 187.9 \n" +
                "400kWh 초과 사용 : 7300 원 | 400kWh초과 : 280.6 \n\n\n";

        String[] data = {
                "100", // 910 + 93.3 * 100 = 10,240 원
                "300" // 1600 + 93.3 * 200 + 187.9 * 100 = 39,505원
        };

        Observable<Integer> basePrice = Observable.fromArray(data)
                .map(Integer :: parseInt)
                .map(val -> {
                    if(val <= 200) return  910;
                    if(val <= 400) return 1600;
                    return 7300;
                });

        Observable<Integer> usagePrice = Observable.fromArray(data)
                .map(Integer::parseInt)
                .map(val -> {
                    double series1 = min(200, val) * 93.3;
                    double series2 = min(200, max(val-200, 0)) * 187.9;
                    double series3 = min(0, max(val-400, 0)) * 280.6;
                    return (int)(series1 + series2 + series3);
                });

        Observable<Integer> source = Observable.zip(
                basePrice, usagePrice, (v1, v2) -> v1 + v2);


        source.map(val -> new DecimalFormat("#,###").format(val))
                .subscribe(val -> {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Usage :" + data[index] + "kWh => ");
                    stringBuilder.append("Prcie :" + val + "원");

                    text4 += stringBuilder.toString() + "\n";

                    index++;
                });

        baseLayoutBinding.text4.setText(text4);
    }


    //위의 예제에서 사용한 'index' 는 부수효과가 발생할 수도 있다. 때문에 함수형 프로그래밍 기본 원칙에 어긋나므로 아래와 같이 바꿀 수 있다.
    private void calculateToElectricBill2() {

        String[] data = {"100", "300"};

        Observable<Integer> basePrice = Observable.fromArray(data)
                .map(Integer::parseInt)
                .map( val -> {
                    if(val <= 200) return 910;
                    if(val <=400) return 1600;
                    return 7300;
                });

        Observable<Integer> usagePrice = Observable.fromArray(data)
                .map(Integer::parseInt)
                .map(val -> {
                    double series1 = min(200, val) * 93.3;
                    double series2 = min(200, max(val-200, 0)) * 187.9;
                    double series3 = min(0, max(val-400, 0)) * 280.6;
                    return (int)(series1 + series2 + series3 );
                });

        Observable<Pair<String, Integer>> source = Observable.zip(
                basePrice, usagePrice, Observable.fromArray(data),
                (v1, v2, i) -> Pair.of(i, v1+v2));

        source.map(val -> Pair.of(val.getLeft(),
                new DecimalFormat("#,###").format(val.getValue())))
                .subscribe(val -> {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Usage : " + val.getLeft() + " kWh => ");
                    sb.append("Price : " + val.getRight() + "원");

                    text5 += sb.toString() + "\n";
                });

        baseLayoutBinding.text5.setText(text5);
    }

    private void zipWithNumbers() {
        text6 = "zipWith : \n";
        Observable<Integer>  source = Observable.zip(
                Observable.just(100, 200, 300),
                Observable.just(10, 20, 30),
                (a, b) -> a+b).zipWith(Observable.just(1,2,3), (ab, c) -> ab + c);

        source.subscribe(data -> text6 += data + "\n");

        baseLayoutBinding.text6.setText(text6);
    }
}
