package ayhan.com.rxjavapractice.operator1;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

import ayhan.com.rxjavapractice.R;
import ayhan.com.rxjavapractice.databinding.BaseLayoutBinding;
import io.reactivex.Maybe;
import io.reactivex.Observable;

/**
 * Created by han-ayeon on 2018. 4. 3..
 */

/*
* 가상의 상점 '불온상점' 의 오늘 매출은
* - TV : 2,500 // Camera : 300 // TV : 1600 / Phone : 800 이다.
* 오늘 매출 중 TV 매출의 총합을 계산하쟈.
*
* - 전체 매출 데이터 입력
* - 매출 데이터 중 TV 매출 필터링
* - 각 TV 매출의 합 구함*/
public class QueryExample extends AppCompatActivity {

    private BaseLayoutBinding baseLayoutBinding;
    private String result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        baseLayoutBinding = DataBindingUtil.setContentView(this, R.layout.base_layout);

        baseLayoutBinding.info.setText(
                "* 가상의 상점 '불온상점' 의 오늘 매출은\n" +
                "- TV : 2,500 // Camera : 300 // TV : 1600 / Phone : 800 이다.\n" +
                "* 오늘 매출 중 TV 매출의 총합을 계산하쟈.\n" +
                "*\n" +
                "- 전체 매출 데이터 입력\n" +
                "- 매출 데이터 중 TV 매출 필터링\n" +
                "- 각 TV 매출의 합 구함 \n");

        getTvSales();
    }

    private void getTvSales() {

        // 1. 데이터 입력
        List<Pair<String, Integer>> sales = new ArrayList<>();

        sales.add(Pair.of("TV", 2500));
        sales.add(Pair.of("Camera", 300));
        sales.add(Pair.of("TV", 1600));
        sales.add(Pair.of("Phone", 800));

        Maybe<Integer> tvSales = Observable.fromIterable(sales)
                //"TV" 인 경우만 필터
                .filter(sale -> "TV".equals(sale.getLeft()))
                .map(sale -> sale.getRight())
                //"TV" 판매량 합침
                .reduce((sale1, sale2) -> sale1 + sale2);

        tvSales.subscribe(total -> result += "getTvSales : $" + total);

        baseLayoutBinding.text1.setText(result);

    }

}
