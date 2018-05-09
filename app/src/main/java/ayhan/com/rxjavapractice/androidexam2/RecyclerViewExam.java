package ayhan.com.rxjavapractice.androidexam2;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import ayhan.com.rxjavapractice.R;
import ayhan.com.rxjavapractice.databinding.RecyclerViewBinding;
import butterknife.BindView;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by HanAYeon on 2018. 5. 9..
 */
public class RecyclerViewExam extends AppCompatActivity {

    private RecyclerViewBinding recyclerViewBinding;
    private RecyclerViewAdapter recyclerViewAdapter;

    private RecyclerView recyclerView;

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        recyclerViewBinding = DataBindingUtil.setContentView(this, R.layout.recycler_view);

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        recyclerView = recyclerViewBinding.recyclerView;
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerViewAdapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.getItemPublishSubject().subscribe(s -> toast(s.getTitle()));

    }

    @Override
    public void onStart() {

        super.onStart();

        if(recyclerViewAdapter == null) {
            return;
        }

        getItemObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(item ->  {
                recyclerViewAdapter.updateItems(item);
                recyclerViewAdapter.notifyDataSetChanged();
                });
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
        if(unbinder != null) {
            unbinder.unbind();
        }

        unbinder = null;
    }

    private Observable<RecyclerItem> getItemObservable() {
        final PackageManager packageManager = getPackageManager();
        Intent i = new Intent(Intent.ACTION_MAIN, null);
        i.addCategory(Intent.CATEGORY_LAUNCHER);

        return Observable.fromIterable(packageManager.queryIntentActivities(i,0))
                .sorted(new ResolveInfo.DisplayNameComparator(packageManager))
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map(item -> {
                    Drawable image = item.activityInfo.loadIcon(packageManager);
                    String title = item.activityInfo.loadLabel(packageManager).toString();
                    return RecyclerItem.of(image, title);
                });
    }

    private void toast(String title) {
        Toast.makeText(getApplicationContext(), title, Toast.LENGTH_SHORT).show();
    }
}
