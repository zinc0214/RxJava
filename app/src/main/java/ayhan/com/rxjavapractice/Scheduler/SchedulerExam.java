package ayhan.com.rxjavapractice.Scheduler;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import ayhan.com.rxjavapractice.R;
import ayhan.com.rxjavapractice.databinding.BtnLayoutBinding;

/**
 * Created by HanAYeon on 2018-04-30.
 */

public class SchedulerExam extends AppCompatActivity {

    private BtnLayoutBinding btnLayoutBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        btnLayoutBinding = DataBindingUtil.setContentView(this, R.layout.btn_layout);

        btnLayoutBinding.info.setText("스케줄러의 종류들 ");
        btnLayoutBinding.firstBtn.setText("new thread scheduler");
        btnLayoutBinding.secondBtn.setText("computation scheduler");
        btnLayoutBinding.thirdBtn.setText("IO scheduler");
        btnLayoutBinding.fourthBtn.setText("trampoline scheduler");
        btnLayoutBinding.fifthtBtn.setText("other scheduler");

        btnLayoutBinding.fifthtBtn.setVisibility(View.VISIBLE);

        btnLayoutBinding.layout.setBackgroundColor(getResources().getColor(R.color.chap4));

        btnLayoutBinding.firstBtn.setOnClickListener(l->
                startActivity(new Intent(this, NewThreadScheduler.class)));

        btnLayoutBinding.secondBtn.setOnClickListener(l ->
                startActivity(new Intent(this, ComputationScheduler.class)));

        btnLayoutBinding.thirdBtn.setOnClickListener(l ->
                startActivity(new Intent(this, IOScheduler.class)));

        btnLayoutBinding.fourthBtn.setOnClickListener(l ->
                startActivity(new Intent(this, TrampolineScheduler.class)));

        btnLayoutBinding.fifthtBtn.setOnClickListener(l ->
                startActivity(new Intent(this, OtherScheduler.class)));

    }
}
