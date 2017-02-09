package in.swapnilbhoite.projects.ilovezappos.network;


import in.swapnilbhoite.projects.ilovezappos.models.SearchResultPage;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface ZapposNetworkService {

    @GET(NetworkConstants.PATH_SEARCH)
    Call<SearchResultPage> search(@Query("term") String term, @Query("key") String key);
}
