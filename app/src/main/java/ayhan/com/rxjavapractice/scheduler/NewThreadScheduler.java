package ayhan.com.rxjavapractice.scheduler;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

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

public class NewThreadScheduler extends AppCompatActivity {

    private BaseLayoutBinding baseLayoutBinding;
    private String text1 = "";
    private String text2 = "\n\n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        baseLayoutBinding = DataBindingUtil.setContentView(this, R.layout.base_layout);
        baseLayoutBinding.info.setText("뉴 스레드 스케줄러 : 새로운 스레드를 생성한다. \n" +
                "새로운 스레드를 만들어 동작을 실행하고 싶을 때 Schedulers.newThread() 를 인자로 넣어주면 된다.");

        newThreadSchedulerExam();
    }

    private void newThreadSchedulerExam() {
        String[] orgs = {RED, GREEN, BLUE};
        Observable.fromArray(orgs)
                .doOnNext(data -> text1 += "Original Data1 :" + data + "\n\n")
                .map(data -> text1 += "<" + data + ">\n")
                .subscribeOn(Schedulers.newThread())
                .subscribe(result -> text1 += result + "\n");

        CommonUtils.sleep(5000);

        Observable.fromArray(orgs)
                .doOnNext(data -> text2 += "Original Data2 : " + data + "\n\n")
                .map(data -> text2 +="###" + data + "###\n")
                .subscribeOn(Schedulers.newThread())
                .subscribe(data -> text2 += data + "\n");
        CommonUtils.sleep(5000);


        baseLayoutBinding.text1.setText(text1);
        baseLayoutBinding.text2.setText(text2);
    }
}
