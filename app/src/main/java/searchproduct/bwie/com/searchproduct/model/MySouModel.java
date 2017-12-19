package searchproduct.bwie.com.searchproduct.model;

import java.util.HashMap;

import okhttp3.Callback;
import searchproduct.bwie.com.searchproduct.okhttp.OkHttp3Utils;

/**
 * Created by CZ on 2017/12/19.
 */
public class MySouModel implements SouModel {
    @Override
    public void get(String name, String page, Callback callback) {
        HashMap<String, String> map = new HashMap<>();
        map.put("keywords",name);
        map.put("page",page);
        OkHttp3Utils.doPost("http://120.27.23.105/product/searchProducts?source=android",map,callback);

    }
}
