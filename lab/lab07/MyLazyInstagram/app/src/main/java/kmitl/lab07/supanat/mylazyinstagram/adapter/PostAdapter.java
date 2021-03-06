package kmitl.lab07.supanat.mylazyinstagram.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import kmitl.lab07.supanat.mylazyinstagram.R;
import kmitl.lab07.supanat.mylazyinstagram.api.PostModel;

/**
 * Created by mild supanat on 18/10/2560.
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.Holder>{


    private Context context;
    private List<PostModel> data;
    public PostAdapter(Context context){
        this.context = context;
        data = new ArrayList<>();

    }

    public void setData(List<PostModel> data) {
        this.data = data;

    }


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.post_item, null, false);
        Holder holder = new Holder(itemView);//เพื่อใช้เมมเท่าที่จะใช้
        return holder;
    }

    @Override
    public void onBindViewHolder(PostAdapter.Holder holder, int position) {
        ImageView image = holder.image;
        String imageUrl = data.get(position).getUrl();
        Glide.with(context).load(imageUrl).into(image);
        TextView like = holder.like;
        int numLike = data.get(position).getLike();
        like.setText(Integer.toString(numLike));
        TextView comment = holder.comment;
        int numComment = data.get(position).getComment();
        comment.setText(Integer.toString(numComment));

    }


    @Override
    public int getItemCount() {//บอกว่าเรามีข้อมูลเท่าไหร่
        return data.size();//จำนวนข้อมูล
    }


    static class Holder extends RecyclerView.ViewHolder{
        ImageView image;
        public TextView like;
        public TextView comment;
        public Holder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            like = (TextView) itemView.findViewById(R.id.like);
            comment = (TextView) itemView.findViewById(R.id.comment);
        }
    }
}

