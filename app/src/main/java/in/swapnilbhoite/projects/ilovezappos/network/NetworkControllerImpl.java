package in.swapnilbhoite.projects.ilovezappos.network;


import retrofit2.Retrofit;

class NetworkControllerImpl {

    private static NetworkControllerImpl ourInstance = new NetworkControllerImpl();
    private final Retrofit retrofit;

    private NetworkControllerImpl() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(NetworkConstants.BASE_URL)
                .build();
    }

    public static synchronized NetworkControllerImpl getInstance() {
        return ourInstance;
    }

}
