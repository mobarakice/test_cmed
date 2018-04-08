package ice.mobarak.test.data.network;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Streaming;

public interface RetrofitInterface {

    @GET("intrvw/SampleVideo_1280x720_30mb.mp4")
    @Streaming
    Call<ResponseBody> downloadFile();
}