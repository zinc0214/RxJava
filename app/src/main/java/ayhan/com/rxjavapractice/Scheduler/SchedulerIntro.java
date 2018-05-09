package ayhan.com.rxjavapractice.Scheduler;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ayhan.com.rxjavapractice.R;
import ayhan.com.rxjavapractice.common.CommonUtils;
import ayhan.com.rxjavapractice.common.Shape;
import ayhan.com.rxjavapractice.databinding.BaseLayoutBinding;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import static ayhan.com.rxjavapractice.common.Shape.GREEN;
import static ayhan.com.rxjavapractice.common.Shape.RED;
import static ayhan.com.rxjavapractice.common.Shape.YELLOW;
import static ayhan.com.rxjavapractice.common.Shape.pentagon;
import static ayhan.com.rxjavapractice.common.Shape.star;
import static ayhan.com.rxjavapractice.common.Shape.triangle;

/**
 * Created by HanAYeon on 2018-04-29.
 */

public class SchedulerIntro extends AppCompatActivity {

    private BaseLayoutBinding baseLayoutBinding;
    private String text1;
    private String text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        baseLayoutBinding = DataBindingUtil.setContentView(this, R.layout.base_layout);
        baseLayoutBinding.info.setText("스케줄러는 스레드를 지정할 수 있게 해준다.\n" +
                "1. 스케줄러는 RxJava 코드를 어느 스레드에서 실행할지 지정할 수 있다.\n" +
                "2. subscribeOn() 함수와 observeOn 함수를 모두 지정하면 Observable 에서 데이터 " +
                "흐름이 발생하는 스레드와 처리된 결과를 구독자에게 발행하는 스레드를 분리할 수 있다. \n" +
                "3. subscribeOn() 함수만 호출하면 Observable 의 모든 흐름이 동일한 스레드에서 실행된다.\n" +
                "4. 스케줄러를 별도로 지정하지 않으면 현재 (main) 스레드에서 동작을 실행한다.");

        filpExam();
        observedOnRemovedExam();

    }

    private void filpExam() {
        String[] objs = {star(RED),triangle(YELLOW), pentagon(GREEN)};
        Observable<String> source = Observable.fromArray(objs)
                // doOnNext() : onNext 이벤트가 발생하면 실행되며 원래의 데이터값을 확인한다.
                .doOnNext(data -> text1 += "Original Data = " + data + "\n")
                //subscribeOn : 구독자가 Observable에 subscribe() 함수를 호출하여 구독할 때 실행되는 스레드를 지정한다.
                .subscribeOn(Schedulers.newThread())
                //observeOn : Observable 에서 생성한 데이터 흐름이 여러함수를 거치며 처리될 때 어느 스레드에서 일어나는지 지정할 수 있다.
                .observeOn(Schedulers.newThread()) // 새로운 스레드 생성
                .map(Shape::flip); // map 함수에 flip을 사용하여 도형을 뒤집는다.
        source.subscribe(result -> text1 += result + "\n");
        CommonUtils.sleep(1000);
        CommonUtils.exampleComplete();

        //최초의 데이터 흐름이 발생하는 스레드와 flip() 함수를 거쳐서 구독자에게 전달되는 스레드가 다르다

        baseLayoutBinding.text1.setText(text1);
    }

    private void observedOnRemovedExam() {
        String[] objs = {star(RED),triangle(YELLOW), pentagon(GREEN)};
        Observable<String> source = Observable.fromArray(objs)
                .doOnNext(data -> text2 += "Original Data " + data + "\n")
                .subscribeOn(Schedulers.newThread())
                .map(Shape::flip);

        //ObserveOn 함수를 지정하지 않으면 subscribeOn 함수로 지정한 스레드에서 모든 로직을 실행한다.
        source.subscribe(result -> text2 += result + "\n");
        CommonUtils.sleep(500);
    }
}
