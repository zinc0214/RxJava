package ayhan.com.rxjavapractice.androidexam2;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ayhan.com.rxjavapractice.R;
import ayhan.com.rxjavapractice.databinding.BtnLayoutBinding;

/**
 * Created by HanAYeon on 2018. 5. 9..
 */
public class AndroidExam2Activity extends AppCompatActivity {

    private BtnLayoutBinding btnLayoutBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        btnLayoutBinding = DataBindingUtil.setContentView(this, R.layout.btn_layout);

        btnLayoutBinding.layout.setBackgroundColor(getResources().getColor(R.color.chap5));

        btnLayoutBinding.info.setText("리액티브 연산자 입문");

        btnLayoutBinding.firstBtn.setText("RecyclerView Exam");
        btnLayoutBinding.secondBtn.setText("AsyncTask Exam");
        btnLayoutBinding.thirdBtn.setText("TimerTask Exam");
        btnLayoutBinding.fourthBtn.setText("Timer To RxJava Exam");

        btnLayoutBinding.firstBtn.setOnClickListener(l -> startActivity(new Intent(this, RecyclerViewExam.class)));
        btnLayoutBinding.secondBtn.setOnClickListener(l -> startActivity(new Intent(this, AsyncTaskActivity.class)));
        btnLayoutBinding.thirdBtn.setOnClickListener(l-> startActivity(new Intent(this, TimerTaskActivity.class)));
        btnLayoutBinding.fourthBtn.setOnClickListener(l ->  startActivity(new Intent(this, TimerToRxActivity.class)));
    }

}