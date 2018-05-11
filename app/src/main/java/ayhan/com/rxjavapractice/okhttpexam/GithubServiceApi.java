package ayhan.com.rxjavapractice.okhttpexam;

import java.util.List;

import java.util.concurrent.Future;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by HanAYeon on 2018. 5. 10..
 */

//Retrofit 은 RxJava 를 정식으로 지원하고 있다..!
public interface GithubServiceApi {
    @GET("repos/{owner}/{repo}/contributors")
    Call<List<Contributor>> getCallContributors(@Path("owner") String owner, @Path("repo") String repo);


    @GET("repos/{owner}/{repo}/contributors")
    Observable<List<Contributor>> getObContributors(@Path("owner") String owner, @Path("repo") String repo);

    @Headers({"Accept: application/vnd.github.v3.full+json"})
    @GET("repos/{owner}/{repo}/contributors")
    Future<List<Contributor>> getFutureContributors(@Path("owner") String owner, @Path("repo") String repo);
}
