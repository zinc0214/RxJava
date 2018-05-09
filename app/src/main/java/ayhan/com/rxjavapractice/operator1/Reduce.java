package ayhan.com.rxjavapractice.operator1;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ayhan.com.rxjavapractice.R;
import ayhan.com.rxjavapractice.databinding.BaseLayoutBinding;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;


/**
 * Created by han-ayeon on 2018. 4. 3..
 */

/*
* reduce() 힘수 : 발행한 데이터를 모두 사용하여 어떤 최종 결과 데이터를 합성할 때 사용한다.
* 상황에 따라 발행된 데이터를 취합하여 어떠한 결과를 만들어 낼 때 사용한다.
* */

public class Reduce extends AppCompatActivity {

    private BaseLayoutBinding baseLayoutBinding;
    private String text1 = "";
    private String text2 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        baseLayoutBinding = DataBindingUtil.setContentView(this, R.layout.base_layout);

        baseLayoutBinding.info.setText("reduce() 힘수 : 발행한 데이터를 모두 사용하여 어떤 최종 결과 데이터를 합성할 때 사용한다.\n" +
                "상황에 따라 발행된 데이터를 취합하여 어떠한 결과를 만들어 낼 때 사용한다.");
        simpleReduce();
        biFunctionWithReduce();
    }

    /*
    * Maybe class : Single 클래스와 마찬가리로 최대 데이터를 하나 가질 수 있지만 데이터 발행 없이 데이터 발생을 완료 할수도 있다.
     * 즉 0개 또는 1개 완료할 수 있다.*/
    private void simpleReduce() {
        text1 = "Maybe class : Single 클래스와 마찬가리로 최대 데이터를 하나 가질 수 있지만 데이터 발행 없이 데이터 발생을 완료 할수도 있다.\n" +
                "즉 0개 또는 1개 완료할 수 있다\n\n" +
                "simeple Redece : ";

        String[] names = {"JaeHo", "HyunSoo", "GeumHak"};
        Maybe<String> source = Observable.fromArray(names)
                .reduce((name1, name2) -> name2 + "(" + name1 + ")" );
        source.subscribe(data -> text1 += data + "\n");

        baseLayoutBinding.text1.setText(text1);
    }

    private void biFunctionWithReduce() {
        text2 = "biFunctionWithReduce :\n";

        BiFunction<String, String, String> mergeNames =
                (name1, name2) -> name2 + "(" + name1 + ")";
        String[] names = {"JaeHo", "HyunSoo", "GeumHak"};

        Maybe<String> source = Observable.fromArray(names).reduce(mergeNames);
        source.subscribe(data -> text2 += data + "\n");

        baseLayoutBinding.text2.setText(text2);
    }
}
