package ayhan.com.rxjavapractice.androidexam2;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ayhan.com.rxjavapractice.R;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;


/**
 * Created by HanAYeon on 2018. 5. 9..
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    private List<RecyclerItem> items = new ArrayList<>();
    private PublishSubject<RecyclerItem> publishSubject; // 뜨거운 Observable : 구독자가 없더라도 실시간 처리되어야 하는 click 이벤트의 특성때문.
    private ImageView image;
    private TextView title;
    private View view;

    RecyclerViewAdapter() {
        this.publishSubject = PublishSubject.create();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final RecyclerItem item = items.get(position);
        image.setImageDrawable(item.getImage());
        title.setText(item.getTitle());
        holder.getClickObserver(item).subscribe(publishSubject);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void updateItems(List<RecyclerItem> items) {
        items.addAll(items);
    }

    public void updateItems(RecyclerItem item) {
        items.add(item);
    }

    public PublishSubject<RecyclerItem> getItemPublishSubject() {
        return publishSubject;
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            image = view.findViewById(R.id.item_image);
            title = view.findViewById(R.id.item_title);
        }

        Observable<RecyclerItem> getClickObserver(RecyclerItem item) {
            return Observable.create(e -> itemView.setOnClickListener(view -> e.onNext(item)));
        }
    }
}
