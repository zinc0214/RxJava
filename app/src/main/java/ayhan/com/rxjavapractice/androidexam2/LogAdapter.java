package ayhan.com.rxjavapractice.androidexam2;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

import ayhan.com.rxjavapractice.R;

/**
 * Created by HanAYeon on 2018. 5. 10..
 */
public class LogAdapter extends ArrayAdapter {

    public LogAdapter(Context context, List<String> logs) {
        super(context, R.layout.textview_log, R.id.tv_log, logs);
    }
}
