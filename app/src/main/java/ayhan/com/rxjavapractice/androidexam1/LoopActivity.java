package ayhan.com.rxjavapractice.androidexam1;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Arrays;

import ayhan.com.rxjavapractice.R;
import ayhan.com.rxjavapractice.databinding.BaseLayoutBinding;
import butterknife.OnClick;
import io.reactivex.Observable;

/**
 * Created by HanAYeon on 2018. 5. 9..
 */
public class LoopActivity extends AppCompatActivity {

    private BaseLayoutBinding baseLayoutBinding;
    public static final String TAG = LoopActivity.class.getSimpleName();

    private String text1 = "";
    private String text2 = "";


    Iterable<String> samples = Arrays.asList(
            "jaeho", "hyunsoo", "byungab", "james", "sangjun", "insook"
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        baseLayoutBinding = DataBindingUtil.setContentView(this, R.layout.base_layout);

        loop();
        loop2();
    }

    //Java
    @OnClick(R.id.text1)
    void loop() {
        text1 += "get james :: in Java";
        for(String s : samples) {
            if(s.contains("james")) {
                baseLayoutBinding.text1.setText(text1);
                return;
            }
        }
    }

    //RxJava 2.x
    @OnClick(R.id.text2)
    void loop2() {
        text2 += "get james :: rx  2.x";
        Observable.fromIterable(samples)
                .filter(s -> s.contains("james")) // filter 함수를 통해 원하는 값이 아니면 무시한다.
                .first("Not Found") // filter 함수를 통과한 값은 first 함수로 첫 번째 값만 처리해 구독자에게 전달한다.
                .subscribe(data -> baseLayoutBinding.text2.setText(text2));
    }
}

