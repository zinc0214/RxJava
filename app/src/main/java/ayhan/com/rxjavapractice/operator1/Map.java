package ayhan.com.rxjavapractice.operator1;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ayhan.com.rxjavapractice.R;
import ayhan.com.rxjavapractice.databinding.BaseLayoutBinding;
import io.reactivex.Observable;
import io.reactivex.functions.Function;


/**
 * Created by han-ayeon on 2018. 4. 3..
 */

/*
* map 함수 : 입력값을 어떤 함수에 넣어서 원하는 값으로 변환하는 함수.*/
public class Map extends AppCompatActivity {

    private BaseLayoutBinding baseLayoutBinding;
    private String text1 = "";
    private String text2 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        baseLayoutBinding = DataBindingUtil.setContentView(this, R.layout.base_layout);

        baseLayoutBinding.info.setText("map 함수 : 입력값을 어떤 함수에 넣어서 원하는 값으로 변환하는 함수. \n 람다에서는 데이터 타입을 자동으로 변환하며, 타입 변환을 명시하지 않아도 된다.");
        map();
        lambdaMap();
    }

    private void map() {
        text1 = "map : \n";

        String[] balls = {"1", "2", "3", "4"};
        Observable<String> source = Observable.fromArray(balls)
                .map(ball -> ball + "◇");

        source.subscribe(data -> text1 += data + "\n");
        baseLayoutBinding.text1.setText(text1);
    }

    // 람다에서는 데이터 타입을 자동으로 변환하며, 타입 변환을 명시하지 않아도 된다.
    private void lambdaMap() {
        text2 = "lambdaMap : \n";
        Function<String, Integer> ballToIndex = ball -> {
            switch (ball) {
                case "RED" : return 1;
                case "YELLOW" : return 2;
                case "GREEN" : return 3;
                default: return -1;
            }
        };

        String[] balls = {"RED", "YELLOW", "GREEN"};
        Observable<Integer> source = Observable.fromArray(balls).map(ballToIndex);
        source.subscribe(data -> text2+=data + "\n");

        baseLayoutBinding.text2.setText(text2);
    }
}
