package ayhan.com.rxjavapractice.scheduler;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import ayhan.com.rxjavapractice.R;
import ayhan.com.rxjavapractice.common.CommonUtils;
import ayhan.com.rxjavapractice.databinding.BaseLayoutBinding;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by HanAYeon on 2018-05-01.
 */

public class OtherScheduler extends AppCompatActivity {

    private BaseLayoutBinding baseLayoutBinding;
    private String text1 = "";
    private String text2 = "\n\n";
    private String text3 = "";
    private String text4 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        baseLayoutBinding = DataBindingUtil.setContentView(this, R.layout.base_layout);
        baseLayoutBinding.info.setText("싱글 스레드 스케줄러 : RxJava 내부에서 단일 스레드를 별도로" +
                "생성하여 구독 작업을 처리한다.\n\n" +
                "Executor 변환 스케줄러 : java.util.current 패키지에서 제공하는 실행자를 변환하여 스케줄러를" +
                "생성할 수 있다" +
                "두 가지 스케줄러 모두 추천하는 방식은 아니다.");

        singleSchedulerExam();
        executorExam();

    }

    private void singleSchedulerExam() {
        Observable<Integer> numbers = Observable.range(100,5);
        Observable<String> chars = Observable.range(0, 5)
                .map(CommonUtils::numberToAlphabet);

        numbers.subscribeOn(Schedulers.single())
                .subscribe(data -> text1 += data  + "\n");
        chars.subscribeOn(Schedulers.single())
                .subscribe(data -> text2 += data + "\n");

        CommonUtils.sleep(500);
        CommonUtils.exampleComplete();
    }

    private void executorExam() {
        final  int THREAD_NUM = 10;

        String[] data = {"RED", "GREEN", "BLUE"};
        Observable<String> source = Observable.fromArray(data);
        Executor executor = Executors.newFixedThreadPool(THREAD_NUM);

        source.subscribeOn(Schedulers.from(executor))
                .subscribe(result -> text3 += result + "\n");

        source.subscribeOn(Schedulers.from(executor))
                .subscribe(result -> text4 += result + "\n");

        CommonUtils.sleep(500);
        CommonUtils.exampleComplete();
    }
}
