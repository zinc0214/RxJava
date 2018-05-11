package ayhan.com.rxjavapractice.okhttpexam;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import ayhan.com.rxjavapractice.R;
import ayhan.com.rxjavapractice.androidexam2.LogAdapter;
import ayhan.com.rxjavapractice.databinding.OkhttpLayoutBinding;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by HanAYeon on 2018. 5. 11..
 */
public class OkhttpActivity extends AppCompatActivity {

    private OkhttpLayoutBinding okhttpLayoutBinding;
    private static final String sName = "jungjoonpark-pandora";
    private static final String sRepo = "rxAndroid";

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        okhttpLayoutBinding = DataBindingUtil.setContentView(this, R.layout.okhttp_layout);

        okhttpLayoutBinding.ohfBtnRetrofit.setOnClickListener(l -> startRetrofit());
        okhttpLayoutBinding.ohfBtnGetRetrofitOkhttp.setOnClickListener(l -> startOkHttp());
        okhttpLayoutBinding.ohfBtnGetRetrofitOkhttpRx.setOnClickListener(l -> startRx());


        setupLogger();

   }

   @Override
    public void onDestroy() {
       super.onDestroy();
       compositeDisposable.clear();
   }


   // retrofit + okhttp ( Call 의 내부 )
    private void startRetrofit() {
        GithubServiceApi service = RestfulAdapter.getInstance().getSimpleAPi();
        Call<List<Contributor>> call = service.getCallContributors(sName, sRepo);
        call.enqueue(new Callback<List<Contributor>>() {
            @Override
            public void onResponse(Call<List<Contributor>> call, Response<List<Contributor>> response) {
                if(response.isSuccessful()) {
                    List<Contributor> contributors = response.body();
                    for ( Contributor c : contributors) {
                        log(c.toString());
                    }
                } else {
                    log("not successful");
                }
            }

            @Override
            public void onFailure(Call<List<Contributor>> call, Throwable t) {
                log(t.getMessage());
            }
        });
    }


    //retrofit + okhttp
    private void startOkHttp() {
        GithubServiceApi service = RestfulAdapter.getInstance().getServiceApi();
        Call<List<Contributor>> call = service.getCallContributors(sName,sRepo);

        call.enqueue(new Callback<List<Contributor>>() {
            @Override
            public void onResponse(Call<List<Contributor>> call, Response<List<Contributor>> response) {
                if(response.isSuccessful()) {
                    List<Contributor> contributors = response.body();
                    for (Contributor c : contributors) {
                        log(c.toString());
                    }
                } else {
                    log("not successful");
                }
            }

            @Override
            public void onFailure(Call<List<Contributor>> call, Throwable t) {
                log(t.getMessage());
            }
        });
    }


    //retrofit + okHttp + RxJava
    private void startRx() {
        GithubServiceApi service = RestfulAdapter.getInstance().getServiceApi();
        Observable<List<Contributor>> observable = service.getObContributors(sName, sRepo);

        compositeDisposable.add(observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<Contributor>>() {

                    @Override
                    public void onNext(List<Contributor> contributors) {
                        for (Contributor c : contributors) {
                            log(c.toString());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        log(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        log("complete");
                    }
                })
        );
    }



    // Log
    private LogAdapter logAdapter;
    private List<String> logs;

    private void log(String log) {
        logs.add(log);
        logAdapter.clear();
        logAdapter.addAll(logs);
    }

    private void setupLogger() {
        logs = new ArrayList<>();
        logAdapter = new LogAdapter(this, new ArrayList<>());
        okhttpLayoutBinding.ohfLvLog.setAdapter(logAdapter);
    }
}
