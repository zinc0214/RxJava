package ayhan.com.rxjavapractice;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ayhan.com.rxjavapractice.databinding.BtnLayoutBinding;
import ayhan.com.rxjavapractice.observable.Connectable;
import ayhan.com.rxjavapractice.observable.ObservableClass;
import ayhan.com.rxjavapractice.observable.SingleClass;
import ayhan.com.rxjavapractice.observable.SubjectClass;

/**
 * Created by han-ayeon on 2018. 4. 2..
 */

/*
* - Observable 은 데이터 흐름에 맞게 알림을 보내 구독자가 데이터를 처리할 수 있도록 한다. */
public class ObservableActivity extends AppCompatActivity {

    private BtnLayoutBinding btnLayoutBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        btnLayoutBinding = DataBindingUtil.setContentView(this, R.layout.btn_layout);

        btnLayoutBinding.info.setText("Observable 은 데이터 흐름에 맞게 알림을 보내 구독자가 데이터를 처리할 수 있도록 한다.\n");

        btnLayoutBinding.layout.setBackgroundColor(getResources().getColor(R.color.chap1));

        btnLayoutBinding.firstBtn.setOnClickListener(l ->
                startActivity(new Intent(this, ObservableClass.class)));

        btnLayoutBinding.secondBtn.setOnClickListener(l ->
                startActivity(new Intent(this, SingleClass.class)));

        btnLayoutBinding.thirdBtn.setOnClickListener(l ->
                startActivity(new Intent(this, SubjectClass.class)));

        btnLayoutBinding.fourthBtn.setOnClickListener(l ->
                startActivity(new Intent(this, Connectable.class)));
    }
}
