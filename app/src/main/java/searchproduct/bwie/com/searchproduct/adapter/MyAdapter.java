package searchproduct.bwie.com.searchproduct.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import searchproduct.bwie.com.searchproduct.R;
import searchproduct.bwie.com.searchproduct.bean.Bean;

/**
 * Created by CZ on 2017/12/19.
 */
public class MyAdapter extends RecyclerView.Adapter {
    Context context;
    List<Bean.DataBean> list;
    boolean flag;


    public MyAdapter(Context context, List<Bean.DataBean> list, boolean flag) {
        this.flag = flag;
        this.context = context;
        this.list = list;
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(context));
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (flag == true) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_adapter, null);
            return new MyViewHolder(view);
        } else if (flag == false) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_si, null);
            return new ViewHolder01(view);
        }
        return new MyViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
            MyViewHolder holder1 = (MyViewHolder) holder;
            holder1.title.setText(list.get(position).getTitle());
            holder1.youhui.setText("优惠价:" + list.get(position).getPrice());
            holder1.price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中间横线（删除线）
            holder1.price.setText("原价：￥" + list.get(position).getBargainPrice());
            String[] split = list.get(position).getImages().split("\\|");
            ImageLoader.getInstance().displayImage(split[0], holder1.image);
        }else if (holder instanceof ViewHolder01) {
            ViewHolder01 holder1 = (ViewHolder01) holder;
            holder1.title1.setText(list.get(position).getTitle());
            holder1.youhui1.setText("优惠价:" + list.get(position).getPrice());
            holder1.price1.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中间横线（删除线）
            holder1.price1.setText("原价：￥" + list.get(position).getBargainPrice());
            String[] split = list.get(position).getImages().split("\\|");
            ImageLoader.getInstance().displayImage(split[0], holder1.image1);
        }

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.youhui)
        TextView youhui;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    static class ViewHolder01 extends RecyclerView.ViewHolder {
        @BindView(R.id.image1)
        ImageView image1;
        @BindView(R.id.title1)
        TextView title1;
        @BindView(R.id.price1)
        TextView price1;
        @BindView(R.id.youhui1)
        TextView youhui1;

        ViewHolder01(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
