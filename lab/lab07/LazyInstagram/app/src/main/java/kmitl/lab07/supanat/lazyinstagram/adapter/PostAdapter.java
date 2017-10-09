package kmitl.lab07.supanat.lazyinstagram.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import kmitl.lab07.supanat.lazyinstagram.R;

/**
 * Created by mild supanat on 6/10/2560.
 */

class Holder extends RecyclerView.ViewHolder {//ขั้นตอน recycler view ต้องมี holder
    public ImageView image;
    public Holder(View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.image);
    }
}

public class PostAdapter extends RecyclerView.Adapter<Holder>{

    String[] data = {
            "https://raw.githubusercontent.com/iangkub/gitdemo/master/cartoon/01.jpg",
            "https://raw.githubusercontent.com/iangkub/gitdemo/master/cartoon/02.jpg",
            "https://raw.githubusercontent.com/iangkub/gitdemo/master/cartoon/03.jpg",
            "https://raw.githubusercontent.com/iangkub/gitdemo/master/cartoon/04.jpg"
    };

    private Context context;
    public PostAdapter(Context context){
        this.context = context;
    }


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.post_item, null, false);
        Holder holder = new Holder(itemView);//เพื่อใช้เมมเท่าที่จะใช้
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {//บอกตำแหน่ง
        ImageView image = holder.image;
        Glide.with(context).load(data[position]).into(image);

    }

    @Override
    public int getItemCount() {//บอกว่าเรามีข้อมูลเท่าไหร่
        return data.length;//จำนวนข้อมูล
    }


}
