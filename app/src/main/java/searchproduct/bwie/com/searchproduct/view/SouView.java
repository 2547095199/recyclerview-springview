package searchproduct.bwie.com.searchproduct.view;

import searchproduct.bwie.com.searchproduct.bean.Bean;

/**
 * Created by CZ on 2017/12/19.
 */
public interface SouView {
    public void success(Bean bean);

    public void failuer(Exception e);
}
