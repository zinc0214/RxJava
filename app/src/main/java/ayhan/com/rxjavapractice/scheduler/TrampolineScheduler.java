package ayhan.com.rxjavapractice.scheduler;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ayhan.com.rxjavapractice.R;
import ayhan.com.rxjavapractice.common.CommonUtils;
import ayhan.com.rxjavapractice.databinding.BaseLayoutBinding;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by HanAYeon on 2018-05-01.
 */

public class TrampolineScheduler extends AppCompatActivity {

    private BaseLayoutBinding baseLayoutBinding;
    private String text1 = "";
    private String text2 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        baseLayoutBinding = DataBindingUtil.setContentView(this, R.layout.base_layout);
        baseLayoutBinding.info.setText("트램펄린 스케줄러 : 새로운 스레드를 생성하지 않고 현재 스레드에 무한한 크기의 대기 행렬을 생성하는 스케줄러이다.");

        trampolineExam();
    }

    private void trampolineExam() {
        String[] orgs = {"RED", "GREEN", "BLUE"};
        Observable<String> source = Observable.fromArray(orgs);

        source.subscribeOn(Schedulers.trampoline())
                .map(data-> "<<" + data + ">>\n")
                .subscribe(result -> text1 += result );

        source.subscribeOn(Schedulers.trampoline())
                .map(data -> "###" + data + "###\n")
                .subscribe(result -> text2 += result);

        CommonUtils.sleep(500);
        CommonUtils.exampleComplete();

        baseLayoutBinding.text1.setText(text1);
        baseLayoutBinding.text2.setText(text2);
    }
}
