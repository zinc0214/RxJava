package ayhan.com.rxjavapractice.operator2_create;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ayhan.com.rxjavapractice.R;
import ayhan.com.rxjavapractice.databinding.BtnLayoutBinding;

/**
 * Created by HanAYeon on 2018-04-08.
 */

public class CreateOpActivity extends AppCompatActivity {

    private BtnLayoutBinding btnLayoutBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        btnLayoutBinding = DataBindingUtil.setContentView(this, R.layout.btn_layout);

        btnLayoutBinding.info.setText(
                "- 생성 연산자 : Observable 로 데이터 흐름을 만든다.\n" +
                "- just(), fromXXX(), create(), Interval(), range(), timer(), intervalRange(), defer(), repeat()\n");
        btnLayoutBinding.firstBtn.setText("Interval Method");
        btnLayoutBinding.secondBtn.setText("Timer Method");
        btnLayoutBinding.thirdBtn.setText("Range Method");
        btnLayoutBinding.fourthBtn.setText("Defer Method");
        btnLayoutBinding.fifthtBtn.setText("Repeat Method");

        btnLayoutBinding.layout.setBackgroundColor(getResources().getColor(R.color.chap3));


        btnLayoutBinding.firstBtn.setOnClickListener(l -> {
            startActivity(new Intent(this, Interval.class));
        });

        btnLayoutBinding.secondBtn.setOnClickListener(l -> {
            startActivity(new Intent(this, Timer.class));
        });

        btnLayoutBinding.thirdBtn.setOnClickListener(l -> {
            startActivity(new Intent(this, Range.class));
        });

        btnLayoutBinding.fourthBtn.setOnClickListener(l -> {
            startActivity(new Intent(this, Defer.class));
        });

        btnLayoutBinding.fifthtBtn.setOnClickListener(l -> {
            startActivity(new Intent(this, Repeat.class));
        });
    }
}
