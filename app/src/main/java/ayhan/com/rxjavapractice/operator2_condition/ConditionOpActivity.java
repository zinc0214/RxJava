package ayhan.com.rxjavapractice.operator2_condition;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ayhan.com.rxjavapractice.R;
import ayhan.com.rxjavapractice.databinding.BtnLayoutBinding;

/**
 * Created by HanAYeon on 2018. 4. 16..
 */
public class ConditionOpActivity extends AppCompatActivity {

    private BtnLayoutBinding btnLayoutBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        btnLayoutBinding = DataBindingUtil.setContentView(this, R.layout.btn_layout);

        btnLayoutBinding.info.setText("조건연산자 : Observable의 흐름을 제어하는 역할. 지금까지의 흐름을 어떻게 제어할 것인지에 초점을 맞춘다. \n" +
                "amb() 함수 : 둘 중에 어느것이든 먼저 나오는 Observable 을 채택한다.\n" +
                "takeUtil(other) 힘수 : other Observable에서 데이터가 발행되기 전까지만 현재 Observable 을 채택한다.\n" +
                "skipUtil(other) 함수 : takeUtil(other) 함수와 반대로 other Observable 에서 데이터가 발행될 때 까지 현재 Observable 에서 발행하는 값을 무시한다. \n" +
                "all() 함수 : Observable 에 입력되는 겂이 모두 특정 조건에 맞을 때만 true를 발행한다. 만약 조건이 맞지 않으면 바로 false를 발행한다.");

        btnLayoutBinding.firstBtn.setText("Amb Method");
        btnLayoutBinding.secondBtn.setText("TakeUtil method");
        btnLayoutBinding.thirdBtn.setText("SkipUtil method");
        btnLayoutBinding.fourthBtn.setText("All method");
        btnLayoutBinding.fifthtBtn.setText("Etc method");
        btnLayoutBinding.layout.setBackgroundColor(getResources().getColor(R.color.chap3));

        btnLayoutBinding.firstBtn.setOnClickListener( l -> {
            startActivity(new Intent(this, Amb.class));
        });

        btnLayoutBinding.secondBtn.setOnClickListener( l -> {
            startActivity(new Intent(this, TakeUtil.class));
        });


        btnLayoutBinding.thirdBtn.setOnClickListener( l -> {
            startActivity(new Intent(this, SkipUtil.class));
        });

        btnLayoutBinding.fourthBtn.setOnClickListener( l -> {
            startActivity(new Intent(this, All.class));
        });

        btnLayoutBinding.fifthtBtn.setOnClickListener( l -> {
            startActivity(new Intent(this, Etc.class));
        });

    }
}
