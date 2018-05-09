package ayhan.com.rxjavapractice.operator2_combine;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import ayhan.com.rxjavapractice.R;
import ayhan.com.rxjavapractice.common.CommonUtils;
import ayhan.com.rxjavapractice.databinding.BaseLayoutBinding;
import io.reactivex.Observable;
import io.reactivex.functions.Action;

/**
 * Created by HanAYeon on 2018. 4. 12..
 */

public class Concat extends AppCompatActivity {


    private BaseLayoutBinding baseLayoutBinding;
    private String text1 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        baseLayoutBinding = DataBindingUtil.setContentView(this, R.layout.base_layout);

        baseLayoutBinding.info.setText("concat() : Observalbe을 이어 붙여주는 함수.\n" +
                "첫 번째 Observable 에 onComplete 이벤트가 발생해야 두 번째 Observable 을 구독한다.\n" +
                "첫 번째 Observable 에 onComplete 이벤트가 발생하지 않으면 두 번째 Observable은 영원히 대기한다. " +
                "이는 잠재적인 메모리 누수를 야기하기 때문에 반드시 입력 Observable 을 완료시켜야 한다.");

        concat();

    }

    private void concat() {

        Action onCompleteAction = () -> Log.d("Concat", "onComplete");

        String[] data1 = {"RED", "GREEN", "BLUE"};
        String[] data2 = {"YELLOW", "SKY", "PUPPLE"};
        Observable<String> source1 = Observable.fromArray(data1)
                .doOnComplete(onCompleteAction);

        Observable<String> source2 = Observable.interval(100L, TimeUnit.MILLISECONDS)
                .map(Long::intValue)
                .map(idx -> data2[idx])
                .take(data2.length)
                .doOnComplete(onCompleteAction);

        Observable<String> source = Observable.concat(source1, source2)
                .doOnComplete(onCompleteAction);

        source.subscribe(data -> text1 += data + "\n");
        CommonUtils.sleep(1000);

        baseLayoutBinding.text1.setText(text1);

    }
}
