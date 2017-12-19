package searchproduct.bwie.com.searchproduct.persenter;

import android.content.Context;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import searchproduct.bwie.com.searchproduct.bean.Bean;
import searchproduct.bwie.com.searchproduct.model.MySouModel;
import searchproduct.bwie.com.searchproduct.okhttp.OnUiCallback;
import searchproduct.bwie.com.searchproduct.view.SouView;

/**
 * Created by CZ on 2017/12/19.
 */
public class SouPersenter {
    SouView view;
    Context context;
    private final MySouModel model;

    public SouPersenter(SouView view, Context context) {
        this.view = view;
        this.context = context;
        model = new MySouModel();
    }
    public void getData(String name,String page){
        model.get(name, page, new OnUiCallback() {
            @Override
            public void onFailed(Call call, IOException e) {
                if (view != null){
                    view.failuer(e);
                }
            }

            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                Bean bean = gson.fromJson(result, Bean.class);
                view.success(bean);
            }
        });
    }
    public void sasa(){
        this.view = null;
    }
}
