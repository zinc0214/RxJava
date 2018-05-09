package ayhan.com.rxjavapractice.operator2_combine;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ayhan.com.rxjavapractice.R;
import ayhan.com.rxjavapractice.databinding.BtnLayoutBinding;

/**
 * Created by HanAYeon on 2018-04-11.
 */

public class CombineOpActivity extends AppCompatActivity {

    private BtnLayoutBinding btnLayoutBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        btnLayoutBinding = DataBindingUtil.setContentView(this, R.layout.btn_layout);

        btnLayoutBinding.info.setText("결합 연산자 : 1개의 Observable 이 아니라 여러 개의 Observable 을 조합할 수 있다\n" +
                "다수의 Observable을 하나로 합하는 방법을 제공한다.\n" +
                "zip() : 입력 Observable 에서 데이터를 모두 새로 발행했을 때 그것을 합해준다.\n" +
                "combineLatest() : 처음에 각 Observable 에서 데이터를 발행한 후에는 어디에서 값을 발행하던 최신 값으로 갱신한다. \n" +
                "merge() : 최신 데이터 여부와 상관없이 각 Observable 에서 발행하는 데이터를 그대로 출력한다. \n" +
                "concat() : 입력된 Observable 을 Observable 단위로 이어붙여준다. ");

        btnLayoutBinding.firstBtn.setText("Zip Method");
        btnLayoutBinding.secondBtn.setText("CombineLatest Method");
        btnLayoutBinding.thirdBtn.setText("Merge Method");
        btnLayoutBinding.fourthBtn.setText("Concat Method");

        btnLayoutBinding.layout.setBackgroundColor(getResources().getColor(R.color.chap3));

        btnLayoutBinding.firstBtn.setOnClickListener(l -> {
            startActivity(new Intent(this, Zip.class));
        });

        btnLayoutBinding.secondBtn.setOnClickListener(l -> {
            startActivity(new Intent(this, CombineLatest.class));
        });

        btnLayoutBinding.thirdBtn.setOnClickListener(l -> {
            startActivity(new Intent(this, Merge.class));
        });

        btnLayoutBinding.fourthBtn.setOnClickListener(l -> {
            startActivity(new Intent(this, Concat.class));
        });
    }

}

