package searchproduct.bwie.com.searchproduct;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import searchproduct.bwie.com.searchproduct.adapter.MyAdapter;
import searchproduct.bwie.com.searchproduct.bean.Bean;
import searchproduct.bwie.com.searchproduct.persenter.SouPersenter;
import searchproduct.bwie.com.searchproduct.view.SouView;

public class MainActivity extends AppCompatActivity implements SouView {

    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.sousuo)
    Button sousuo;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.springview)
    SpringView springview;
    SouPersenter persenter = new SouPersenter(this, this);
    List<Bean.DataBean> list = new ArrayList<>();
    private boolean flag = true;
    private MyAdapter adapter;
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        springview.setHeader(new DefaultHeader(this));
        springview.setFooter(new DefaultFooter(this));
        //设置SpringView的刷新监听事件
        springview.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                springview.onFinishFreshAndLoad();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onLoadmore() {
                persenter.getData(name.getText().toString(), page + "");
                springview.onFinishFreshAndLoad();

            }
        });


    }

    @OnClick({R.id.image, R.id.sousuo})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image:
                if (flag == false) {
                    flag = true;
                    image.setImageResource(R.drawable.san);
                    LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
                    recyclerview.setLayoutManager(manager);
                    adapter = new MyAdapter(this, list, flag);
                    recyclerview.setAdapter(adapter);
                    setadapter();
                } else if (flag == true) {
                    flag = false;
                    image.setImageResource(R.drawable.si);
                    GridLayoutManager manager = new GridLayoutManager(this, 2);
                    recyclerview.setLayoutManager(manager);
                    adapter = new MyAdapter(this, list, flag);
                    recyclerview.setAdapter(adapter);
                    setadapter();
                }
                break;
            case R.id.sousuo:
                if (flag == true) {
                    LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
                    recyclerview.setLayoutManager(manager);
                    adapter = new MyAdapter(this, list, flag);
                    recyclerview.setAdapter(adapter);
                } else if (flag == false) {
                    GridLayoutManager manager = new GridLayoutManager(this, 2);
                    recyclerview.setLayoutManager(manager);
                    adapter = new MyAdapter(this, list, flag);
                    recyclerview.setAdapter(adapter);
                }
                persenter.getData(name.getText().toString(), "1");
                break;
        }
    }

    private void setadapter() {

        if (adapter != null) {
            adapter = new MyAdapter(this, list, flag);
            recyclerview.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void success(Bean bean) {
//        for (int i = 0; i <bean.getData().size() ; i++) {
//            list.add(bean.getData().get(i));
//        }
        list.addAll(bean.getData());
        adapter = new MyAdapter(this, list, flag);
        recyclerview.setAdapter(adapter);
    }

    @Override
    public void failuer(Exception e) {
        Toast.makeText(MainActivity.this, "错误", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        persenter.sasa();
    }
}
