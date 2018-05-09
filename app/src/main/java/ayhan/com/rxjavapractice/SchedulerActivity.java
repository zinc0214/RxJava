package ayhan.com.rxjavapractice;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import ayhan.com.rxjavapractice.Scheduler.SchedulerExam;
import ayhan.com.rxjavapractice.Scheduler.SchedulerIntro;
import ayhan.com.rxjavapractice.databinding.BtnLayoutBinding;

/**
 * Created by HanAYeon on 2018-04-29.
 */

public class SchedulerActivity extends AppCompatActivity {

    private BtnLayoutBinding btnLayoutBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        btnLayoutBinding = DataBindingUtil.setContentView(this, R.layout.btn_layout);

        btnLayoutBinding.info.setText("스케줄러 : 어떤 프로개름의 세부 일정을 주관하는 관리자. ");
        btnLayoutBinding.firstBtn.setText("Scheduler Intro");
        btnLayoutBinding.secondBtn.setText("Kind of Scheduler");
        btnLayoutBinding.layout.setBackgroundColor(getResources().getColor(R.color.chap4));

        btnLayoutBinding.thirdBtn.setVisibility(View.GONE);
        btnLayoutBinding.fourthBtn.setVisibility(View.GONE);

        btnLayoutBinding.firstBtn.setOnClickListener(l ->
                startActivity(new Intent(this, SchedulerIntro.class)));

        btnLayoutBinding.secondBtn.setOnClickListener(l ->
                startActivity(new Intent(this, SchedulerExam.class)));


    }
}
