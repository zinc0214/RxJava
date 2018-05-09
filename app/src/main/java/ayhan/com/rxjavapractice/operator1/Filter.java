package ayhan.com.rxjavapractice.operator1;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ayhan.com.rxjavapractice.R;
import ayhan.com.rxjavapractice.databinding.BaseLayoutBinding;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by han-ayeon on 2018. 4. 3..
 */

/*filter() 함수 : Observable 에서 원하는 데이터만을 걸러내는 역할을 한다.
필요없는 데이터는 제거하고 관심있는 (필요한) 데이터만 filter() 함수를 통과한다.
filter 함수에는 boolean 값을 리턴하는 Predicate 인터페이스를 인자로 넣는다.
Predicate 는 '진위판별' 이라는 뜻이 있으며 boolean 값을 리턴하는 특수한 함수형 인터페이스이다. */

public class Filter extends AppCompatActivity {

    private BaseLayoutBinding baseLayoutBinding;
    private String text1 = "";
    private String text2 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        baseLayoutBinding = DataBindingUtil.setContentView(this, R.layout.base_layout);

        baseLayoutBinding.info.setText("filter() 함수 : Observable 에서 원하는 데이터만을 걸러내는 역할을 한다.\n" +
                "필요없는 데이터는 제거하고 관심있는 (필요한) 데이터만 filter() 함수를 통과한다.\n" +
                "filter 함수에는 boolean 값을 리턴하는 Predicate 인터페이스를 인자로 넣는다.\n" +
                "Predicate 는 '진위판별' 이라는 뜻이 있으며 boolean 값을 리턴하는 특수한 함수형 인터페이스이다. ");
        simpleFilter();
        similarFilterMethod();
    }


    private void simpleFilter() {
        text1 = "simpleFilter : ";

        String[] objs = {"1 JaeHo", "2 JooWon", "3 HyunSoo", "4 JaeHo", "5 SiWan"};
        Observable<String> source = Observable.fromArray(objs)
                .filter(obj -> obj.endsWith("JaeHo"));
        source.subscribe(data -> text1 += data);

        baseLayoutBinding.text1.setText(text1);
    }

    private void similarFilterMethod() {
        Integer[] numbers = {100, 200, 300, 400, 500};
        Single<Integer> single;
        Observable<Integer> source;

        //1.first : 첫번째 값 리턴
        text2 += "1.first : 첫번째 값 리턴\n";

        single = Observable.fromArray(numbers).first(-1);
        single.subscribe(data -> text2 += data + "\n");

        //2.last : 마지막 값 리턴
        text2 += "\n2.last : 마지막 값 리턴\n";
        single = Observable.fromArray(numbers).last(999);
        single.subscribe(data -> text2 += data + "\n");

        //3.take(N) : 전체 데이터 중 원하는 개수만큼 가져온다
        text2 += "\n3.take(N) : 전체 데이터 중 원하는 개수만큼 가져온다\n";

        source = Observable.fromArray(numbers).take(3);
        source.subscribe(data -> text2 += "take 3 value : " + data + "\n");

        //4.takeLast(N) : 마지막 항목을 기준으로 N개의 데이터를 가져온다.
        text2 += "\n4.takeLast(N) : 마지막 항목을 기준으로 N개의 데이터를 가져온다.\n";

        source = Observable.fromArray(numbers).takeLast(1);
        source.subscribe(data -> text2 += "tekeLast1 value :" + data + "\n");

        //5.skip(N) : 처음 N 개 항목을 건너뛰고 그 다음 데이터부터 가져온다.
        text2 += "\n5.skip(N) : 처음 N 개 항목을 건너뛰고 그 다음 데이터부터 가져온다.\n";

        source = Observable.fromArray(numbers).skip(2);
        source.subscribe(data ->text2 += "skip2 value : " + data + "\n");

        //6.skipLast(N) : 마지막 항목을 기준으로 N 개의 항목을 제외한 데이터를 가져온다.
        text2 += "\n6.skipLast(N) : 마지막 항목을 기준으로 N 개의 항목을 제외한 데이터를 가져온다.\n";
        source = Observable.fromArray(numbers).skipLast(2);
        source.subscribe(data -> text2 += "skipLast 2 value:" + data + "\n");

        baseLayoutBinding.text2.setText(text2);
    }
}
