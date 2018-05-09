package ayhan.com.rxjavapractice.observable;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

import ayhan.com.rxjavapractice.R;
import ayhan.com.rxjavapractice.common.CommonUtils;
import ayhan.com.rxjavapractice.databinding.BaseLayoutBinding;
import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

/**
 * Created by han-ayeon on 2018. 4. 2..
 */

/*
* 차가운 Observable 을 뜨거운 Observable 로 변환한다
* Observable 을 여러구독자에게 공유할 수 있으므로 원 데이터 하나를 여러 구독자에게 동시에 전달할 때 사용한다
* subscribe() 함수를 호출해도 아무 동작이 일어나지 않는다
* 새로 추가된 connect() 함수는 호출한 시전부터 subscribe() 함수를 호출한 구독자에게 데이터를 발행하기 때문이다
* */
public class Connectable extends AppCompatActivity {

    private BaseLayoutBinding baseLayoutBinding;
    private String printText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        baseLayoutBinding = DataBindingUtil.setContentView(this, R.layout.base_layout);

        baseLayoutBinding.info.setText("" +
                "* 차가운 Observable 을 뜨거운 Observable 로 변환한다\n" +
                "* Observable 을 여러구독자에게 공유할 수 있으므로 원 데이터 하나를 여러 구독자에게 동시에 전달할 때 사용한다\n" +
                "* subscribe() 함수를 호출해도 아무 동작이 일어나지 않는다\n" +
                "* 새로 추가된 connect() 함수는 호출한 시점부터 subscribe() 함수를 호출한 구독자에게 데이터를 발행하기 때문이다");
        connectableObservable();
    }

    /*
    * 결과 : 1, 1, 3, 3*/
    private void connectableObservable() {

        String[] task = {"1", "3", "5"};
        Observable<String> tasks = Observable.interval(100L, TimeUnit.MILLISECONDS)
                .map(Long::intValue)
                .map(i -> task[i])
                .take(task.length);

        ConnectableObservable<String> source = tasks.publish();

        source.subscribe(data -> printText += "Subscriber #1 : " + data +"\n");
        source.subscribe(data -> printText += "Subscriber #2 : " + data +"\n");

        source.connect();

        CommonUtils.sleep(250);


        source.subscribe(data -> printText += "Subscriber #3 : "  + data+"\n" );

        CommonUtils.sleep(150);

        baseLayoutBinding.text1.setText("connectableObservable : \n" + printText);
    }

}
