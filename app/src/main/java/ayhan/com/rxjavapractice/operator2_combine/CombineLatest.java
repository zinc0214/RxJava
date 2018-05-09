package ayhan.com.rxjavapractice.operator2_combine;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import ayhan.com.rxjavapractice.R;
import ayhan.com.rxjavapractice.common.CommonUtils;
import ayhan.com.rxjavapractice.common.Shape;
import ayhan.com.rxjavapractice.databinding.BaseLayoutBinding;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.observables.ConnectableObservable;

import static ayhan.com.rxjavapractice.common.Shape.DIAMOND;
import static ayhan.com.rxjavapractice.common.Shape.ORANGE;
import static ayhan.com.rxjavapractice.common.Shape.PENTAGON;
import static ayhan.com.rxjavapractice.common.Shape.PUPPLE;
import static ayhan.com.rxjavapractice.common.Shape.SKY;
import static ayhan.com.rxjavapractice.common.Shape.STAR;
import static ayhan.com.rxjavapractice.common.Shape.YELLOW;


/**
 * Created by HanAYeon on 2018-04-12.
 */

/*
 * "combineLatest() : 2개 이상의 Observable을 기반으로 Observable 각각의 값이 변경되었을 때 갱신해 주는 함수.
 * 마지막 인자로는 Combiner 가 들어가는데 그것이 각 Observable 을 결합하여 어떠한 결과를 만들어 주는 역할을 한다.
 * zip() 함수의 zipper 인자와 동일하다.
 * ex. 첫 번째 Observable 과 두 번째 Observalbe 을 결합하는 기능을 만든다고 하면, 첫 번째 또는 두 번째 Observable 의 값이 변경되었을 때 자동으로 갱신해준다.
 * */

public class CombineLatest extends AppCompatActivity {

    private BaseLayoutBinding baseLayoutBinding;
    private String text1 = "";
    private String result = "";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        baseLayoutBinding = DataBindingUtil.setContentView(this, R.layout.base_layout);

        baseLayoutBinding.info.setText("combineLatest() : 2개 이상의 Observable을 기반으로 Observable 각각의 값이 변경되었을 때 갱신해 주는 함수.\n" +
                "마지막 인자로는 Combiner 가 들어가는데 그것이 각 Observable 을 결합하여 어떠한 결과를 만들어 주는 역할을 한다.\n" +
                "zip() 함수의 zipper 인자와 동일하다.\n" +
                "ex. 첫 번째 Observable 과 두 번째 Observalbe 을 결합하는 기능을 만든다고 하면, 첫 번째 또는 두 번째 Observable 의 값이 변경되었을 때 자동으로 갱신해준다.");

        combineLatestExam();
    }


    private void combineLatestExam() {

        text1 = "CombileLastestExam : \n";

        String[] data1 = {PUPPLE, ORANGE, SKY, YELLOW};
        String[] data2 = {DIAMOND, STAR, PENTAGON};

        Observable<String> source = Observable.combineLatest(
                Observable.fromArray(data1)
                        .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS),
                        (shape, notUsed) -> Shape.getColor(shape)),
                Observable.fromArray(data2)
                        .zipWith(Observable.interval(150L, 200L, TimeUnit.MILLISECONDS),
                                (shape, notUsed) -> Shape.getSuffix(shape)),
                (v1, v2) -> v1 + v2);

        source.subscribe(data -> text1 += data +"\n");
        CommonUtils.sleep(1000);
        CommonUtils.exampleComplete();

        baseLayoutBinding.text1.setText(text1);

    }


    //리액티브 연산자로 합계 구하기.
    //combineLatest() 함수의 대표적인 활용 예는 엑셀의 '셀' 시뮬레이션이다.
    //어떠한 셀에 =A+B 를 했을 때 바로 값이 변경되는 것.
    private void reactiveSum() {

        ConnectableObservable<String> source = userInput();
        Observable<Integer> a = source
                .filter(str -> str.startsWith("a:"))
                .map(str -> str.replace("a:", ""))
                .map(Integer::parseInt);

        Observable<Integer> b = source
                .filter(str -> str.startsWith("b:"))
                .map(str -> str.replace("b", ""))
                .map(Integer::parseInt);

        Observable.combineLatest(
                a.startWith(0),
                b.startWith(0),
                (x, y) -> x + y).subscribe(res -> result += "Result :" + res);

        source.connect();
    }

    private ConnectableObservable<String> userInput() {

        return Observable.create((ObservableEmitter<String> emitter) -> {

            Scanner in = new Scanner(System.in);
            while (true) {
                System.out.print("Input : ");
                String line = in.nextLine();
                emitter.onNext(line);

                if(line.indexOf("exit") >= 0) {
                    in.close();
                    break;
                }
            }
        }).publish();
    }
}