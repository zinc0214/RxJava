package ayhan.com.rxjavapractice.observable;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import ayhan.com.rxjavapractice.R;
import ayhan.com.rxjavapractice.databinding.BaseLayoutBinding;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by han-ayeon on 2018. 4. 2..
 */

/*
* Single Class 는 데이터를 무한하게 발행할 수 있는 Observable 클래스와 달리 오직 1개의 데이터만을 발행할 수 있다.
* 데이터 하나가 발행과 동시에 종료된다. 생명주기 관점으로 보았을 때 onNext() 와 onComplete() 함수가 onSuccess()함수로 통합된 것과 같다
* 따라서 Single 클래스의 라이프 사이클 함수는 onSuccess(T value), onError() 로 이루어져있다.
* Single 객체에 값을 여러개 넣으면 에러가 발생하게 된다!
* */
public class SingleClass extends AppCompatActivity {

    private BaseLayoutBinding baseLayoutBinding;
    private String text1 = "";
    private String text2 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        baseLayoutBinding = DataBindingUtil.setContentView(this, R.layout.base_layout);

        baseLayoutBinding.info.setText("* Single Class 는 데이터를 무한하게 발행할 수 있는 Observable 클래스와 달리 오직 1개의 데이터만을 발행할 수 있다.\n" +
                "데이터 하나가 발행과 동시에 종료된다. 생명주기 관점으로 보았을 때 onNext() 와 onComplete() 함수가 onSuccess()함수로 통합된 것과 같다\n" +
                "따라서 Single 클래스의 라이프 사이클 함수는 onSuccess(T value), onError() 로 이루어져있다.\n" +
                "Single 객체에 값을 여러개 넣으면 에러가 발생하게 된다!");
        just();
        singleExam();

    }

    /*
    * just() : Observable 클래스와 유사하다.
    * */
    private void just() {
        text1 += "just() : Observable 클래스와 유사하다. \n";
        Single<String> source = Single.just("Hello Single, Just");
        source.subscribe(data -> text1 += data + "\n");

        baseLayoutBinding.text1.setText(text1);
    }

    /*
    * Single은 Observable 에서 변환할 수 있다. 아래는 다양한 예제이다.
    * */
    private void singleExam() {

        // 1. 기존 Observable 에서 Single 객체로 변환
        Observable<String> source = Observable.just("Observable to Single");
        Single.fromObservable(source).subscribe(data -> Log.i("SingleClass", "singleExam1() :" + data ));

        //2. single() 함수를 호출해 Single 객체 생성
        Observable.just("Call Single")
                .single("default Item")
                .subscribe(data -> Log.i("SingleClass", "singleExam2() :" + data ));

        //3. first() 힘수를 호출해 Single 객체 생성
        /*
        * first 함수를 호출하면 Observable 이 Single 객체로 변환된다. 하나 이상의 데이터를 발행하더라도 첫 번째 데이터 발행 후 onSuccess 이벤트가 발생한다.
        * */
        String[] movies = {"The Merciless", "CallByName", "ReadyPlayerOne"};
        Observable.fromArray(movies)
                .first("default Value")
                .subscribe(data -> Log.i("SingleClass", "singleExam3() :" + data ));

        //4. empty Observable에서 Single 객체 생성
        Observable.empty()
                .single("default Item")
                .subscribe(data -> Log.i("SingleClass", "singleExam4() :" + data ));

        //5. take() 함수에서 Single 객체 생성
        Observable.just(new Order("drug_blue"), new Order("drug_red"))
                .take(1)
                .single(new Order("default order"))
                .subscribe(data -> Log.i("SingleClass", "singleExam5() :" + data ));

    }

}
