package kmitl.lab07.supanat.lazyinstagram;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import kmitl.lab07.supanat.lazyinstagram.adapter.PostAdapter;
import kmitl.lab07.supanat.lazyinstagram.api.LazyinstagramApi;
import kmitl.lab07.supanat.lazyinstagram.api.UserProfile;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private int layoutCheck = 0;
    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = getIntent().getStringExtra("user");
        getUserProfile(user);




    }
    public void onLogout(View view){
        finish();
    }

    private void getUserProfile(final String userName) {
        OkHttpClient client = new OkHttpClient
                .Builder()
                .build();
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(LazyinstagramApi.Base_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LazyinstagramApi lazyinstagramApi = retrofit.create(LazyinstagramApi.class);

        Call<UserProfile> call = lazyinstagramApi.getProfile(userName);
        call.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                if (response.isSuccessful()){
                    display(response.body());

                }
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {

            }
        });
    }
    public void display(UserProfile userProfile){
        TextView textView = (TextView)findViewById(R.id.textName);
        textView.setText(userProfile.getUser());
        TextView textbio = (TextView) findViewById(R.id.textBio);
        textbio.setText(userProfile.getBio());
        TextView textpost = (TextView) findViewById(R.id.textPost);
        TextView textfollower = (TextView) findViewById(R.id.textFollower);
        TextView textfollowing = (TextView) findViewById(R.id.textFollowing);
        textpost.setText("Post\n"+userProfile.getPost());
        textfollower.setText("Follower\n"+userProfile.getFollower());
        textfollowing.setText("Following\n"+userProfile.getFollowing());
        ImageView imageView = (ImageView)findViewById(R.id.imageView);
        Glide.with(MainActivity.this).load(userProfile.getUrlProfile()).into(imageView);
        if(layoutCheck == 0) {
            PostAdapter postAdapter = new PostAdapter(this);
            RecyclerView recyclerView = findViewById(R.id.list);
            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
            postAdapter.setData(userProfile.getPosts());
            recyclerView.setAdapter(postAdapter);


        }
        else {
            PostAdapter postAdapter = new PostAdapter(this);
            RecyclerView recyclerView = findViewById(R.id.list);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            postAdapter.setData(userProfile.getPosts());
            recyclerView.setAdapter(postAdapter);
        }

        }
    public void onChange (View view){
        if(layoutCheck == 0){
           layoutCheck = 1;
            getUserProfile(user);

        }
        else {
            layoutCheck = 0;
            getUserProfile(user);
        }
    }

}
