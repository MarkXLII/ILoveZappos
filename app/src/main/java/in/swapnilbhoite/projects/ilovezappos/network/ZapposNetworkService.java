package in.swapnilbhoite.projects.ilovezappos.network;


import in.swapnilbhoite.projects.ilovezappos.models.ProductDetail;
import in.swapnilbhoite.projects.ilovezappos.models.SearchResultPage;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

interface ZapposNetworkService {

    @GET(NetworkConstants.PATH_SEARCH)
    Call<SearchResultPage> search(@Query("term") String term, @Query("key") String key);

    @GET(NetworkConstants.PATH_PRODUCT + "{productId}")
    Call<ProductDetail> getProductDetails(@Path("productId") String productId,
                                          @Query("key") String key,
                                          @Query("includes") String includes);
}
