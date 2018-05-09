package ayhan.com.rxjavapractice.operator2_condition;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ayhan.com.rxjavapractice.R;
import ayhan.com.rxjavapractice.common.Shape;
import ayhan.com.rxjavapractice.databinding.BaseLayoutBinding;
import io.reactivex.Observable;
import io.reactivex.Single;

import static ayhan.com.rxjavapractice.common.Shape.GREEN;
import static ayhan.com.rxjavapractice.common.Shape.RED;
import static ayhan.com.rxjavapractice.common.Shape.SKY;
import static ayhan.com.rxjavapractice.common.Shape.YELLOW;

/**
 * Created by HanAYeon on 2018-04-29.
 */

public class All extends AppCompatActivity {

    private BaseLayoutBinding baseLayoutBinding;
    private String text1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        baseLayoutBinding = DataBindingUtil.setContentView(this, R.layout.base_layout);
        baseLayoutBinding.info.setText("All() 함수 : 주어진 조건에 100% 맞을 때만 true 값을 발행하고 \n" +
                "조건에 맞지 않으면 바로 false 를 발행한다.");

        all();
    }

    private void all() {

        String[] data = {RED,YELLOW,GREEN,SKY};

        Single<Boolean> source = Observable.fromArray(data)
                .map(Shape::getShape)
                .all(Shape.BALL::equals);
        // .all(val -> Shape.BALL.equals(Shape.getShape(val));

        source.subscribe(result -> text1 += result + "\n");

        baseLayoutBinding.text1.setText(text1);
    }

}
