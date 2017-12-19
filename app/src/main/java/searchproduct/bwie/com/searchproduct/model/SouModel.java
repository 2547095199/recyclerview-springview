package searchproduct.bwie.com.searchproduct.model;

import okhttp3.Callback;

/**
 * Created by CZ on 2017/12/19.
 */
public interface SouModel {
    public void get(String name, String page, Callback callback);
}
