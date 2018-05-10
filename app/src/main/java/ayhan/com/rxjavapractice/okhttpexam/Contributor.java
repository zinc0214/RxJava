package ayhan.com.rxjavapractice.okhttpexam;

/**
 * Created by HanAYeon on 2018. 5. 10..
 */
public class Contributor {
    String login;
    String url;
    int id;

    @Override
    public String toString() {
        return "login : " + login + "  id : " + id + "  url : " + url;
    }
}
