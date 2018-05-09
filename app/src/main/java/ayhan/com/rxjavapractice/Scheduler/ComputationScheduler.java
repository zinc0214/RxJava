package ayhan.com.rxjavapractice.Scheduler;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

import ayhan.com.rxjavapractice.R;
import ayhan.com.rxjavapractice.common.CommonUtils;
import ayhan.com.rxjavapractice.databinding.BaseLayoutBinding;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import static ayhan.com.rxjavapractice.common.Shape.BLUE;
import static ayhan.com.rxjavapractice.common.Shape.GREEN;
import static ayhan.com.rxjavapractice.common.Shape.RED;

/**
 * Created by HanAYeon on 2018-04-30.
 */

public class ComputationScheduler extends AppCompatActivity {

    private BaseLayoutBinding baseLayoutBinding;
    private String text1 = "";
    private String text2 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        baseLayoutBinding = DataBindingUtil.setContentView(this, R.layout.base_layout);
        baseLayoutBinding.info.setText("계산 스케줄러 : * interval 함수의 원형이기도 하다. \n " +
                "개발자가 원하는 스케줄러를 지정할 수 있으며, 내가 원하는 스케줄러에서 동작하도록 변경할 수도 있다.\n" +
                "계산 스케줄러는 CPU 에 대응하는 계산용 스케줄러이다. 또는 입출력을 하지 않는 스케줄러라고 볼 수도 있다." +
                "내부적으로 스레드 풀을 생성하며 스레드 개수를 기본적으로 프로세스 개수와 동일하다.");

        computationExam();
    }

    private void computationExam() {
        String[] orgs=  {RED,BLUE,GREEN};
        Observable<String> source = Observable.fromArray(orgs)
                .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS),
                        (a,b) -> a);

        //Subscription#1
        source.map(item -> "<< " + item + ">>")
                .subscribeOn(Schedulers.newThread())
                .subscribe(data -> text1+= data + "\n");

        //Subscription#2
        source.map(item -> "##" + item + "##")
                .subscribeOn(Schedulers.newThread())
                .subscribe(data -> text2 += data + "\n");


        CommonUtils.sleep(1000);
        CommonUtils.exampleComplete();

        baseLayoutBinding.text1.setText(text1);
        baseLayoutBinding.text2.setText(text2);
    }

}
