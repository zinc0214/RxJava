package ayhan.com.rxjavapractice;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import ayhan.com.rxjavapractice.databinding.BtnLayoutBinding;
import ayhan.com.rxjavapractice.operator1.Filter;
import ayhan.com.rxjavapractice.operator1.FlatMap;
import ayhan.com.rxjavapractice.operator1.Map;
import ayhan.com.rxjavapractice.operator1.QueryExample;
import ayhan.com.rxjavapractice.operator1.Reduce;


/**
 * Created by han-ayeon on 2018. 4. 2..
 */

public class Operator1Activity extends AppCompatActivity {

    private BtnLayoutBinding btnLayoutBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        btnLayoutBinding = DataBindingUtil.setContentView(this, R.layout.btn_layout);

        btnLayoutBinding.layout.setBackgroundColor(getResources().getColor(R.color.chap2));

        btnLayoutBinding.info.setText("리액티브 연산자 입문");
        btnLayoutBinding.firstBtn.setText("Map Method");
        btnLayoutBinding.secondBtn.setText("FlatMap Method");
        btnLayoutBinding.thirdBtn.setText("Filter Method");
        btnLayoutBinding.fourthBtn.setText("Reduce Method");
        btnLayoutBinding.fifthtBtn.setText("Itegration Exmaple");

        btnLayoutBinding.fifthtBtn.setVisibility(View.VISIBLE);


        btnLayoutBinding.firstBtn.setOnClickListener(l ->
                startActivity(new Intent(this, Map.class)));

        btnLayoutBinding.secondBtn.setOnClickListener(l ->
                startActivity(new Intent(this, FlatMap.class)));

        btnLayoutBinding.thirdBtn.setOnClickListener(l ->
                startActivity(new Intent(this, Filter.class)));

        btnLayoutBinding.fourthBtn.setOnClickListener(l ->
                startActivity(new Intent(this, Reduce.class)));

        btnLayoutBinding.fifthtBtn.setOnClickListener(l ->
                startActivity(new Intent(this, QueryExample.class)));


    }
}
