package in.swapnilbhoite.projects.ilovezappos.network;

public interface NetworkResponse<T> {

    void onSuccess(T response);

    void onFailure(Throwable throwable);
}
