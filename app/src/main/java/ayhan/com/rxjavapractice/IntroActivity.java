package ayhan.com.rxjavapractice;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ayhan.com.rxjavapractice.databinding.MainLayoutBinding;


/**
 * Created by han-ayeon on 2018. 3. 22..
 */

public class IntroActivity extends AppCompatActivity {

    private MainLayoutBinding mainLayoutBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainLayoutBinding = DataBindingUtil.setContentView(this, R.layout.main_layout);

        mainLayoutBinding.observableActivity.setOnClickListener(l ->
            startActivity(new Intent(this, ObservableActivity.class)));

        mainLayoutBinding.operator1Activity.setOnClickListener(l ->
                startActivity(new Intent(this, Operator1Activity.class)));

        mainLayoutBinding.operator2Activity.setOnClickListener(l ->
                startActivity(new Intent(this, Operator2Activity.class)));

        mainLayoutBinding.schedulerActivity.setOnClickListener(l ->
                startActivity(new Intent(this, SchedulerActivity.class)));
    }

}
