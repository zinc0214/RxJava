package ayhan.com.rxjavapractice.androidexam1;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import ayhan.com.rxjavapractice.R;
import ayhan.com.rxjavapractice.databinding.BtnLayoutBinding;

/**
 * Created by HanAYeon on 2018. 5. 9..
 */
public class AndroidExam1Activity extends AppCompatActivity {

    private BtnLayoutBinding btnLayoutBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        btnLayoutBinding = DataBindingUtil.setContentView(this, R.layout.btn_layout);

        btnLayoutBinding.layout.setBackgroundColor(getResources().getColor(R.color.chap5));

        btnLayoutBinding.info.setText("리액티브 연산자 입문");

        btnLayoutBinding.firstBtn.setText("Hello World Exam");
        btnLayoutBinding.secondBtn.setText("Loop Exam");
        btnLayoutBinding.thirdBtn.setText("RxLifeCycle Exam");
        btnLayoutBinding.fourthBtn.setText("Listener Exam");
        btnLayoutBinding.fifthtBtn.setText("Search Exam");

        btnLayoutBinding.fifthtBtn.setVisibility(View.VISIBLE);

        btnLayoutBinding.firstBtn.setOnClickListener(l ->
                startActivity(new Intent(this, Helloworld.class)));

        btnLayoutBinding.secondBtn.setOnClickListener(l ->
                startActivity(new Intent(this, LoopActivity.class)));

        btnLayoutBinding.thirdBtn.setOnClickListener(l ->
                startActivity(new Intent(this, RxLifeCycle.class)));

        btnLayoutBinding.fourthBtn.setOnClickListener(l ->
                startActivity(new Intent(this, UiEventExam.class)));

        btnLayoutBinding.fifthtBtn.setOnClickListener(l ->
                startActivity(new Intent(this, DebounceSearchExam.class)));

    }
}

