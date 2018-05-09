package ayhan.com.rxjavapractice;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import ayhan.com.rxjavapractice.databinding.BtnLayoutBinding;
import ayhan.com.rxjavapractice.operator2_combine.CombineOpActivity;
import ayhan.com.rxjavapractice.operator2_condition.ConditionOpActivity;
import ayhan.com.rxjavapractice.operator2_conversion.ConversionOpActivity;
import ayhan.com.rxjavapractice.operator2_create.CreateOpActivity;

/**
 * Created by HanAYeon on 2018-04-08.
 */

public class Operator2Activity extends AppCompatActivity {

    private BtnLayoutBinding btnLayoutBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        btnLayoutBinding = DataBindingUtil.setContentView(this, R.layout.btn_layout);

        btnLayoutBinding.layout.setBackgroundColor(getResources().getColor(R.color.chap3));

        btnLayoutBinding.firstBtn.setText("Create Opertator");
        btnLayoutBinding.secondBtn.setText("Conversion Operator");
        btnLayoutBinding.thirdBtn.setText("Combine Operator");
        btnLayoutBinding.fourthBtn.setText("Condition Operator");
        btnLayoutBinding.fifthtBtn.setVisibility(View.GONE);

        btnLayoutBinding.fifthtBtn.setVisibility(View.VISIBLE);

        btnLayoutBinding.firstBtn.setOnClickListener(l -> {
            startActivity(new Intent(this, CreateOpActivity.class));
        });

        btnLayoutBinding.secondBtn.setOnClickListener(l -> {
            startActivity(new Intent(this, ConversionOpActivity.class));
        });

        btnLayoutBinding.thirdBtn.setOnClickListener(l -> {
            startActivity(new Intent(this, CombineOpActivity.class));
        });

        btnLayoutBinding.fourthBtn.setOnClickListener(l -> {
            startActivity(new Intent(this, ConditionOpActivity.class));
        });

        btnLayoutBinding.info.setText("" +
                "리액티브 연산자를 카테고리 별로 알아보자.\n" +

                " <필터 연산자>\n" +
                " - filter(), take(), skip(), distinct()\n" +
                " <결합 연산자>\n" +
                " -  zip(), combineLatest(), merge(), concat()\n" +
                " <조건 연산자>\n" +
                " - amb(), takeUtil(), skipUtil(), all()\n" +
                " <에러처리 연산자>\n" +
                " - onErrorReturn(), onErrorResumeNext(), retry(), retryUtil()\n" +
                " <기타 연산자>\n" +
                "- subscribe(), subscribeOn(), observeOn(), reduce(), count()");
    }
}

