package ayhan.com.rxjavapractice.operator2_create;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import ayhan.com.rxjavapractice.R;
import ayhan.com.rxjavapractice.databinding.BaseLayoutBinding;
import io.reactivex.Observable;

/**
 * Created by HanAYeon on 2018-04-10.
 */

public class Repeat extends AppCompatActivity {

    private BaseLayoutBinding baseLayoutBinding;
    private String text1 = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        baseLayoutBinding = DataBindingUtil.setContentView(this, R.layout.base_layout);

        baseLayoutBinding.info.setText("repeat() : 단순히 반복 실행을 하는 함수. 서버와 통신 시 해당 서버가 잘 살아있는지 확인할 때 자주 사용한다.\n" +
        "repeat() 함수에 인자를 주지 않는 경우 영원히 반복 실행된다.");

        repeat();
    }

    private void repeat() {

        String[] balls = {"1", "3", "5"};
        Observable<String> source =Observable.fromArray(balls)
                .repeat(3);

        source.doOnComplete(() -> Log.d("repeat", "OnComplete")).subscribe(data -> text1 += data +"\n");

        baseLayoutBinding.text1.setText(text1);
    }
}
