package in.swapnilbhoite.projects.ilovezappos.network;


import android.support.annotation.NonNull;
import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.lang.ref.WeakReference;
import java.util.List;

import in.swapnilbhoite.projects.ilovezappos.models.Product;
import in.swapnilbhoite.projects.ilovezappos.models.ProductDetail;
import in.swapnilbhoite.projects.ilovezappos.models.SearchResultPage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkControllerImpl implements NetworkController {

    private final ZapposNetworkService networkService;

    public NetworkControllerImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NetworkConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.networkService = retrofit.create(ZapposNetworkService.class);
    }

    @Override
    public void search(@NonNull String term, NetworkResponse<List<Product>> listener) {
        Call<SearchResultPage> search = networkService.search(term, NetworkConstants.AUTH_KEY);
        final WeakReference<NetworkResponse<List<Product>>> listenerWeakReference =
                new WeakReference<>(listener);
        search.enqueue(new Callback<SearchResultPage>() {
            @Override
            public void onResponse(Call<SearchResultPage> call, Response<SearchResultPage> response) {
                NetworkResponse<List<Product>> listNetworkResponse = listenerWeakReference.get();
                if (listNetworkResponse != null) {
                    listNetworkResponse.onSuccess(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<SearchResultPage> call, Throwable t) {
                NetworkResponse<List<Product>> listNetworkResponse = listenerWeakReference.get();
                if (listNetworkResponse != null) {
                    listNetworkResponse.onFailure(t);
                }
            }
        });
    }

    @Override
    public void getProductDetails(@NotNull String productId, NetworkResponse<ProductDetail> listener) {
        Call<ProductDetail> productDetails = networkService.getProductDetails(productId, NetworkConstants.AUTH_KEY);
        Log.d("SWAP", productDetails.request().toString());
        final WeakReference<NetworkResponse<ProductDetail>> listenerWeakReference =
                new WeakReference<>(listener);
        productDetails.enqueue(new Callback<ProductDetail>() {
            @Override
            public void onResponse(Call<ProductDetail> call, Response<ProductDetail> response) {
                NetworkResponse<ProductDetail> listNetworkResponse = listenerWeakReference.get();
                if (listNetworkResponse != null) {
                    listNetworkResponse.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<ProductDetail> call, Throwable t) {
                NetworkResponse<ProductDetail> listNetworkResponse = listenerWeakReference.get();
                if (listNetworkResponse != null) {
                    listNetworkResponse.onFailure(t);
                }
            }
        });
    }
}
