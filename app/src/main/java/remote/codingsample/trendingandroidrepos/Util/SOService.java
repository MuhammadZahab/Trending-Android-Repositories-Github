package remote.codingsample.trendingandroidrepos.Util;

import java.util.List;

import remote.codingsample.trendingandroidrepos.Models.ReposList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SOService {

    @GET("search/repositories?q=android%20language:java&sort=stars&order=desc&per_page=10")
    Call<ReposList> getRepostList();

    @GET("search/repositories?q=android%20language:java&sort=stars&order=desc&per_page=10")
    Call<ReposList> getRepostList(@Query("page") int page_no);
}