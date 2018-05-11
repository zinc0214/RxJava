package ayhan.com.rxjavapractice;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ayhan.com.rxjavapractice.androidexam1.AndroidExam1Activity;
import ayhan.com.rxjavapractice.androidexam1.LoopActivity;
import ayhan.com.rxjavapractice.androidexam2.AndroidExam2Activity;
import ayhan.com.rxjavapractice.databinding.BtnLayoutBinding;
import ayhan.com.rxjavapractice.okhttpexam.OkhttpActivity;
import ayhan.com.rxjavapractice.volleyexam.VolleyActivity;

/**
 * Created by HanAYeon on 2018. 5. 9..
 */
public class AndroidExamActivity extends AppCompatActivity{

    private BtnLayoutBinding btnLayoutBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        btnLayoutBinding = DataBindingUtil.setContentView(this, R.layout.btn_layout);

        btnLayoutBinding.layout.setBackgroundColor(getResources().getColor(R.color.chap5));

        btnLayoutBinding.firstBtn.setText("Exam1");
        btnLayoutBinding.secondBtn.setText("Exam2");
        btnLayoutBinding.thirdBtn.setText("Volley Use Exam");
        btnLayoutBinding.fourthBtn.setText("OkHttp Exam");

        btnLayoutBinding.firstBtn.setOnClickListener(l ->
                startActivity(new Intent(this, AndroidExam1Activity.class)));

        btnLayoutBinding.secondBtn.setOnClickListener(l ->
                startActivity(new Intent(this, AndroidExam2Activity.class)));

        btnLayoutBinding.thirdBtn.setOnClickListener(l ->
                startActivity(new Intent(this, VolleyActivity.class)));

        btnLayoutBinding.fourthBtn.setOnClickListener(l ->
                startActivity(new Intent(this, OkhttpActivity.class)));

    }

}
