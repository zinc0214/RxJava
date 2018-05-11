package ayhan.com.rxjavapractice.volleyexam;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by HanAYeon on 2018. 5. 10..
 */

/*
* init() 메소드가 호출되면 Context 를 이용하여 RequestQueue 를 생성한다.
* 생성된 RequestQueue 는 getRequestQueue 메소드를 이용하여 가져올 수 있다. */
public class LocalVolley {

    private static RequestQueue requestQueue;

    private LocalVolley() { }

    public static void init(Context context) {
        requestQueue = Volley.newRequestQueue(context);
    }

    public static RequestQueue getRequestQueue() {
        if(requestQueue != null) {
            return requestQueue;
        } else {
            throw new IllegalStateException("Not inited...!");
        }
    }

}
