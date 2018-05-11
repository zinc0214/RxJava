package ayhan.com.rxjavapractice.androidexam1;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import ayhan.com.rxjavapractice.R;
import ayhan.com.rxjavapractice.databinding.BaseLayoutBinding;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;

/**
 * Created by HanAYeon on 2018. 5. 9..
 */
public class RxLifeCycle extends RxAppCompatActivity {

    private BaseLayoutBinding baseLayoutBinding;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        baseLayoutBinding = DataBindingUtil.setContentView(this, R.layout.base_layout);

        unbinder = ButterKnife.bind(this);

        baseLayoutBinding.info.setText("RxLifCycle은 안드로이드의 액티비티와 프래그먼트의 라이프 사이클을 RxJava 에서 사용할 수 있게 한다." +
                "안드로이드와 UI 라이프 사이클을 대체 한다기 보다는 구독 할 때 발생할 수 있는 메모리 누수를 방지하기 위해 사용한다. " +
                "완료하지 못한 구독은 자동으로 해제한다." +
                "\n\nRxLifeCycle 컴포넌트 : RxActivity , RxDialogFragment, RxFragment, RxPreferenceFragment, RxAppCompatActivity," +
                "RxAppCompatDialogFragment , RxFragmentActivity");

        Observable.just("Hello, rx world.")
                .compose(bindToLifecycle())
                .subscribe(data -> baseLayoutBinding.text1.setText(data + "\n"));
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(unbinder != null) {
            unbinder.unbind();
        }
    }
}
