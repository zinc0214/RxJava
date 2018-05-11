package ayhan.com.rxjavapractice.okhttpexam;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by HanAYeon on 2018. 5. 10..
 */
public class RestfulAdapter {

    private static final String BASE_URL = "https://api.github.com/";

    private RestfulAdapter() { }

    private static class Singleton {
        private static final RestfulAdapter instance = new RestfulAdapter();
    }

    public static RestfulAdapter getInstance() {
        return Singleton.instance;
    }

    public GithubServiceApi getServiceApi() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .baseUrl(BASE_URL)
                .build();

        return retrofit.create(GithubServiceApi.class);
    }


    public GithubServiceApi getSimpleAPi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(GithubServiceApi.class);
    }

    /*
    getServiceApi 와 getSimpleAPi 의 차이점은 REST API 스택의 디버깅이 가능한지에 대한 여부이다.
    getServiceApi 는 따로 OkHttpClient.Builder() 객체를 구성하여 로그를 위한 인터셉터를 설정한다.
    getSimpleAPi 는 Retrofit 에 포함된  OkHttpClient 를 사용한다. */
}
