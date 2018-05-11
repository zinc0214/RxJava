package ayhan.com.rxjavapractice.androidexam2;

import android.graphics.drawable.Drawable;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * Created by HanAYeon on 2018. 5. 9..
 */

@AllArgsConstructor(staticName = "of")
@RequiredArgsConstructor
public class RecyclerItem {
    private Drawable image;
    private String title;

    public Drawable getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }
}

