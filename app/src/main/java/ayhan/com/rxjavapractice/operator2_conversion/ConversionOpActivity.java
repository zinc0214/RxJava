package ayhan.com.rxjavapractice.operator2_conversion;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ayhan.com.rxjavapractice.R;
import ayhan.com.rxjavapractice.databinding.BtnLayoutBinding;

/**
 * Created by HanAYeon on 2018-04-11.
 */

public class ConversionOpActivity extends AppCompatActivity {

    private BtnLayoutBinding btnLayoutBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        btnLayoutBinding = DataBindingUtil.setContentView(this, R.layout.btn_layout);

        btnLayoutBinding.info.setText(
                "- 변환 연산자와 필터 연산자 : 데이트의 흐름을 내가 원하는 방식으로 변형한다.\n" +
                        "-  map(), flatMap(), concatMap(), switchMap(), groupBy(), scan(), buffer(), window()\n");

        btnLayoutBinding.firstBtn.setText("ConcatMap Method");
        btnLayoutBinding.secondBtn.setText("SwitchMap Method");
        btnLayoutBinding.thirdBtn.setText("GroupBy Method");
        btnLayoutBinding.fourthBtn.setText("Scan Method");

        btnLayoutBinding.layout.setBackgroundColor(getResources().getColor(R.color.chap3));

        btnLayoutBinding.firstBtn.setOnClickListener(l -> {
            startActivity(new Intent(this, ConcatMap.class));
        });

        btnLayoutBinding.secondBtn.setOnClickListener(l -> {
            startActivity(new Intent(this, SwitchMap.class));
        });

        btnLayoutBinding.thirdBtn.setOnClickListener(l -> {
            startActivity(new Intent(this, GroupBy.class));
        });

        btnLayoutBinding.fourthBtn.setOnClickListener(l -> {
            startActivity(new Intent(this, Scan.class));
        });
    }

}
