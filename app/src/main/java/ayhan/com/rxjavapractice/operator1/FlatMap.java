package ayhan.com.rxjavapractice.operator1;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ayhan.com.rxjavapractice.R;
import ayhan.com.rxjavapractice.databinding.GugudanLayoutBinding;
import io.reactivex.Observable;
import io.reactivex.functions.Function;


/**
 * Created by han-ayeon on 2018. 4. 3..
 */

/*
 * flayMap() :  map() 함수를 좀 더 발전시킨 함수. map과 달리 결과가 Observable로 나온다.
 * map이 일대일 함수라면, flatMap() 함수는 일대다 또는 일대일 Observable 함수이다.
 * */
public class FlatMap extends AppCompatActivity {

    private GugudanLayoutBinding gugudanLayoutBinding;
    private String showText = "";
    private String text1 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gugudanLayoutBinding = DataBindingUtil.setContentView(this, R.layout.gugudan_layout);

        simpleFlatMapExam();

        gugudanLayoutBinding.info.setText("flayMap() :  map() 함수를 좀 더 발전시킨 함수. map과 달리 결과가 Observable로 나온다.\n" +
                "map이 일대일 함수라면, flatMap() 함수는 일대다 또는 일대일 Observable 함수이다.");
        gugudanLayoutBinding.gugudanGetBtn.setOnClickListener(l -> {
           // basicJavaGugudan();
           // observableGugudan();
           // observableGugudan2();
            observableGugudan3();
        });

    }


    private void simpleFlatMapExam() {
        text1 = "SwitchMap";

        Function<String, Observable<String>> getDoubleDiamonds =
                ball -> Observable.just(ball + "◇", ball + "◇");

        String[] balls = {"1", "3", "5"};

        Observable<String> source = Observable.fromArray(balls).flatMap(getDoubleDiamonds);
        source.subscribe(data -> text1 += data);

        gugudanLayoutBinding.text1.setText(text1);
    }


    private void basicJavaGugudan() {

        String editNum = "0";
        editNum = String.valueOf(gugudanLayoutBinding.editNum.getText());
        int dan = Integer.parseInt(editNum);

        for (int row = 1; row <= 9; ++row) {
            showText += dan + " * " + row + " = " + dan * row +"\n";
        }

        gugudanLayoutBinding.gugudanShowText.setText(showText);


    }

    private void observableGugudan() {

        String editNum = "0";
        editNum = String.valueOf(gugudanLayoutBinding.editNum.getText());
        int dan = Integer.parseInt(editNum);


        // range(start, end) - start 부터 end 까지.
        // 함수 안에 함수를 넣을 수 있다는 점!
        // num 대신 dan 을 넣게되면 문제가 생기는데 기존 just에서 dan을 쓰고 있기 때문에 자바 컴파일러가 혼동을 갖게되기 때문이다.
        Function<Integer, Observable<String>> gugudan = num ->
                Observable.range(1,9).map(row -> showText += num + " * " + row + " = " + num * row + "\n");
        Observable<String> source = Observable.just(dan).flatMap(gugudan);
        source.subscribe(data -> gugudanLayoutBinding.gugudanShowText.setText(showText));

    }

    private void observableGugudan2() {

        String editNum = "0";
        editNum = String.valueOf(gugudanLayoutBinding.editNum.getText());
        int dan = Integer.parseInt(editNum);

        Observable<String> source = Observable.just(dan)
                .flatMap(num -> Observable.range(1,9)
                .map(row -> showText += num + " * " + row + " = " + num * row + "\n" ));
        source.subscribe(data -> gugudanLayoutBinding.gugudanShowText.setText(showText));
    }

    private void observableGugudan3() {

        String editNum = "0";
        editNum = String.valueOf(gugudanLayoutBinding.editNum.getText());
        int dan = Integer.parseInt(editNum);

        // 2에서 더 심화하면 아래처럼 함수 안에 더 함수를 넣을 수 있다....

        Observable<String> source = Observable.just(dan)
                .flatMap(gugu -> Observable.range(1,9), (gugu, i) -> showText += gugu + " * " + i + " = " + gugu * i + "\n");
        source.subscribe(data -> gugudanLayoutBinding.gugudanShowText.setText(showText));
    }
}
