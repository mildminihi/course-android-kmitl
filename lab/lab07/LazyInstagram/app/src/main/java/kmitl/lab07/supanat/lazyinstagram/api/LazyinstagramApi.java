package kmitl.lab07.supanat.lazyinstagram.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by mild supanat on 6/10/2560.
 */

public interface LazyinstagramApi {
    public String Base_URL = "https://us-central1-retrofit-course.cloudfunctions.net";
    @GET("/getProfile")
    Call<UserProfile> getProfile(@Query("user") String userName);
}
