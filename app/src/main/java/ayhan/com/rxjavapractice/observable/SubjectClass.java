package ayhan.com.rxjavapractice.observable;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ayhan.com.rxjavapractice.R;
import ayhan.com.rxjavapractice.databinding.BaseLayoutBinding;
import io.reactivex.Observable;
import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;

/**
 * Created by han-ayeon on 2018. 4. 2..
 */

/*
* 뜨거운 Observable 과 차가운 Observable
- 뜨거운 Observable
    - 구독자의 존재여부와 관계없이 데이터를 발행한다.
    - 구독한 시점부터 Observable 에서 발행한 값을 받는다.
    - ex. 마우스이벤트, 키보드이벤트, 시스템이벤트, 센서 데이터 등

- 차가운 Observable
    - Observable 을 선언하고 just() 등의 함수를 호출해도 옵저버가 subscribe() 함수를 호출하며 구독하지 않으면 데이터를 발행하지 않는다.
    - 웹 요청, 데이터베이스 쿼리와 피일 읽기 등 (Url 이나 데이터를 지정하면 그 때부터 서버나 데이터베이스의 서버에 요청을 보내고 결과를 받아온다.

* 구독자가 여럿 = 데이터의 원천이 한 곳이라도 내가 원하는 결과 데이터가 여러 종류인 경우 종류 하나가 각 구독자이다.
* */

/*
* 차가운 Observable 클래스를 뜨거운 Observable 클래스로 바꿔준다.
* Subject 클래스는 Observable의 속성과 구독자의 속성을 모두 가지고 있다.
* */
public class SubjectClass extends AppCompatActivity {

    private BaseLayoutBinding baseLayoutBinding;
    private String text1 = "";
    private String text2 = "";
    private String text3 = "";
    private String text4 = "";
    private String text5 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        baseLayoutBinding = DataBindingUtil.setContentView(this, R.layout.base_layout);

        baseLayoutBinding.info.setText("" +
                "뜨거운 Observable 과 차가운 Observable\n" +
                "- 뜨거운 Observable\n" +
                "    - 구독자의 존재여부와 관계없이 데이터를 발행한다.\n" +
                "    - 구독한 시점부터 Observable 에서 발행한 값을 받는다.\n" +
                "    - ex. 마우스이벤트, 키보드이벤트, 시스템이벤트, 센서 데이터 등\n" +
                "\n" +
                "- 차가운 Observable\n" +
                "    - Observable 을 선언하고 just() 등의 함수를 호출해도 옵저버가 subscribe() 함수를 호출하며 구독하지 않으면 데이터를 발행하지 않는다.\n" +
                "    - 웹 요청, 데이터베이스 쿼리와 피일 읽기 등 (Url 이나 데이터를 지정하면 그 때부터 서버나 데이터베이스의 서버에 요청을 보내고 결과를 받아온다.\n" +
                "\n" +
                "구독자가 여럿 = 데이터의 원천이 한 곳이라도 내가 원하는 결과 데이터가 여러 종류인 경우 종류 하나가 각 구독자이다.\n" +
                "\n" +
                "차가운 Observable 클래스를 뜨거운 Observable 클래스로 바꿔준다.\n" +
                "Subject 클래스는 Observable의 속성과 구독자의 속성을 모두 가지고 있다." );

        asyncSubject();
        asyncSubject2();
        behaviorSubject();
        publishSubject();
        replaySubject();
    }


    /*
    * Observable에서 발행한 마지막 데이터를 얻어올 수 있는 Subject 클래스
    * onComplete 가 호출되고나서야 마지막으로 입력된 데이터가 구독자에게 최종 전달된다.
    * 따라서 결과가 #1, #2 모두 5 가 된다.
    * */
    private void asyncSubject() {

        text1 += "AsyncSubject() : Observable에서 발행한 마지막 데이터를 얻어올 수 있는 Subject 클래스\n" +
                        "onComplete 가 호출되고나서야 마지막으로 입력된 데이터가 구독자에게 최종 전달된다.\n" +
                        "따라서 결과가 #1, #2 모두 5 가 된다.\n\n";

        AsyncSubject<String> subject = AsyncSubject.create();
        subject.subscribe(data -> text1 += "\nasyncSubscriber #1 :" + data);
        subject.onNext("1");
        subject.onNext("3");
        subject.subscribe(data -> text1 +="\nasyncSubscriber #2 :" + data);
        subject.onNext("5");
        subject.onComplete();

        baseLayoutBinding.text1.setText(text1);
    }


    private void asyncSubject2() {
        Float[] temparature = {10.1f, 13.4f, 12.5f};
        Observable<Float> source = Observable.fromArray(temparature);

        AsyncSubject<Float> subject = AsyncSubject.create();
        subject.subscribe(data -> text2 +=  "\nasyncSubscriber2 #1 :" + data);

        source.subscribe(subject);

        baseLayoutBinding.text2.setText(text2);

    }


    /*
    * BehaviorSubject Class : 구독자가 구독을 하면 가장 최근 값 또는 기본값을 넘겨주는 클래스.
    * AsyncSubject Class 와 달리 createDefault() 함수로 생성해야 한다. 구독자가 Subscribe 함수를 호출했을 때 그 전까지 발행한
    * 값이 없으면 기본 값을 대신 발행해야 하기 때문이다
    * */
    private void behaviorSubject() {
        text3 += "BehaviorSubject Class : 구독자가 구독을 하면 가장 최근 값 또는 기본값을 넘겨주는 클래스.\n" +
                "AsyncSubject Class 와 달리 createDefault() 함수로 생성해야 한다. 구독자가 Subscribe 함수를 호출했을 때 그 전까지 발행한\n" +
                "값이 없으면 기본 값을 대신 발행해야 하기 때문이다\n\n";

        BehaviorSubject<String> subject = BehaviorSubject.createDefault("6");
        subject.subscribe(data -> text3 += "\nbehaviorSubscriber #1 :" + data);
        subject.onNext("1");
        subject.onNext("3");
        subject.subscribe(data ->text3+="\nbehaviorSubscriber #2 :" + data);
        subject.onNext("5");
        subject.onComplete();

        baseLayoutBinding.text3.setText(text3);
    }


    /*
    * PublishSubject Class : 가장 평범한 Subject 클래스. 구독자가 subscribe() 메소드를 호출하면 값을 발행하기 시작한다.
    * 오직 해당 시간에 발생한 데이터를 그대로 구독자에게 전달한다.
    * */
    private void publishSubject() {
        text4 += "PublishSubject Class : 가장 평범한 Subject 클래스. 구독자가 subscribe() 메소드를 호출하면 값을 발행하기 시작한다.\n" +
                "오직 해당 시간에 발생한 데이터를 그대로 구독자에게 전달한다.";
        PublishSubject<String> subject = PublishSubject.create();
        subject.subscribe(data -> text4+="\npublishSubscriber #1 :" + data);
        subject.onNext("1");
        subject.onNext("3");
        subject.subscribe(data ->text4+= "\npublishSubscriber #2 :" + data);
        subject.onNext("5");
        subject.onComplete();

        baseLayoutBinding.text4.setText(text4);
    }

    /*
    * ReplaySubject Class : 가장 특이한 클래스. 차가운 Observable 처럼 동작한다.
    * 구독자가 새로 생기면 항상 데이터의 처음부터 끝까지 발행하는 것을 보장한다. 마치 녹음처럼.
    * 모든 데이터을 저장할 때 메모리 누수가 발생하는 것을 염두해야 한다.
    * */
    private void replaySubject() {
        text5 += "ReplaySubject Class : 가장 특이한 클래스. 차가운 Observable 처럼 동작한다.\n" +
                "구독자가 새로 생기면 항상 데이터의 처음부터 끝까지 발행하는 것을 보장한다. 마치 녹음처럼.\n" +
                "모든 데이터을 저장할 때 메모리 누수가 발생하는 것을 염두해야 한다.\n\n";

        ReplaySubject<String> subject = ReplaySubject.create();
        subject.subscribe(data -> text5 += "\nreplaySubscriber #1 :" + data);
        subject.onNext("1");
        subject.onNext("3");
        subject.subscribe(data ->text5 += "\nreplaySubscriber #2 :" + data);
        subject.onNext("5");
        subject.onComplete();

        baseLayoutBinding.text5.setText(text5);
    }

}
