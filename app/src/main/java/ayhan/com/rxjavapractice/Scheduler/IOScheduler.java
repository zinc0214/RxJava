package ayhan.com.rxjavapractice.Scheduler;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.File;

import ayhan.com.rxjavapractice.R;
import ayhan.com.rxjavapractice.common.CommonUtils;
import ayhan.com.rxjavapractice.databinding.BaseLayoutBinding;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by HanAYeon on 2018-04-30.
 */

public class IOScheduler extends AppCompatActivity {

    private BaseLayoutBinding baseLayoutBinding;
    private String text1 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        baseLayoutBinding = DataBindingUtil.setContentView(this, R.layout.base_layout);
        baseLayoutBinding.info.setText("IO 스케줄러 : 계산 스케줄러와는 다르게 네트워크 상의 요청을 처리하거나 " +
                "각종 입, 출력 작업을 실행하기 위한 스레드. 계산 스케줄러와 다른 점은 기본으로 생성되는 스레드 개수가 다르다는 점이다." +
                "IO 스케줄러는 필요할 때 마다 스레드를 계속 생성한다. ");

     //   ioSchedulerExam();

    }

    private void ioSchedulerExam() {
        String root = "c:\\";
        File[] files = new File(root).listFiles();
        Observable<String> source = Observable.fromArray(files)
                .filter(f -> !f.isDirectory())
                .map(f -> f.getAbsolutePath())
                .subscribeOn(Schedulers.io());

        source.subscribe(data -> text1 += data + "\n");
        CommonUtils.sleep(5000);
        CommonUtils.exampleComplete();

        baseLayoutBinding.text1.setText(text1);
    }
}
