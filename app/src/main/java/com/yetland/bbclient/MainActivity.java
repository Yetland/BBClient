package com.yetland.bbclient;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.yetland.base.base.BasePresenter;
import com.yetland.bbclient.dagger.BaseAppDaggerActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Author: Yet_land
 * Date: 2018/9/29.
 *
 * @author YETLAND
 */
public class MainActivity extends BaseAppDaggerActivity {

    RecyclerView recyclerView;

    @Override
    protected String getTitleText() {
        return "登录";
    }

    @Override
    protected boolean isShowTitleBar() {
        return true;
    }

    List<MainBean> mainBeans = new ArrayList<>();

    @Override
    protected void init() {
        List<String> bankNameList = new ArrayList<>();
        bankNameList.add("中国银行");
        bankNameList.add("建设银行");
        bankNameList.add("工商银行");
        bankNameList.add("农业银行");
        bankNameList.add("交通银行");
        bankNameList.add("招商银行");
        bankNameList.add("浦发银行");
        bankNameList.add("广发银行");
        bankNameList.add("兴业银行");

        for (String bankName : bankNameList) {
            MainBean mainBean = new MainBean();
            mainBean.setName(bankName);
            mainBean.setBalance(new Random().nextInt(10000));
            mainBeans.add(mainBean);
        }
        BankCardAdapter adapter = new BankCardAdapter(mainBeans);
        recyclerView.setLayoutManager(new BankCardLayoutManager(100, mainBeans));
        recyclerView.setAdapter(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP |
                ItemTouchHelper.DOWN, ItemTouchHelper.START | ItemTouchHelper.END) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                int from = viewHolder.getAdapterPosition();
                int to = viewHolder1.getAdapterPosition();
                adapter.notifyItemMoved(from, to);
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                int position = viewHolder.getAdapterPosition();
                mainBeans.remove(position);
                adapter.notifyItemRemoved(position);
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);
        adapter.setOnItemClickListener(position -> {
            for (int i = 0; i < mainBeans.size(); i++) {
                if (i == position) {
                    mainBeans.get(position).setSelected(!mainBeans.get(position).isSelected());
                }else {
                    mainBeans.get(i).setSelected(false);
                }
            }
            adapter.notifyDataSetChanged();
        });
    }

    @Override
    protected void findViews() {
        recyclerView = findViewById(R.id.recyclerView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void injectActivity() {
        mActivityComponent.inject(this);
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    public static class MainBean {
        String name;
        boolean selected;
        int balance;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isSelected() {
            return selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }

        public int getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }

        @Override
        public String toString() {
            return "MainBean{" +
                    "name='" + name + '\'' +
                    ", selected=" + selected +
                    ", balance=" + balance +
                    '}';
        }
    }
}
