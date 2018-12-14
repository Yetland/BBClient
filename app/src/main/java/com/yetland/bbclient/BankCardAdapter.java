package com.yetland.bbclient;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.MessageFormat;
import java.util.List;

/**
 * @author YETLAND
 * @date 2018/12/14 10:52
 */
public class BankCardAdapter extends RecyclerView.Adapter<BankCardAdapter.BankCardViewHolder> {

    private List<MainActivity.MainBean> mMainBeanList;
    private OnItemClickListener mOnItemClickListener;

    BankCardAdapter(List<MainActivity.MainBean> mainBeanList) {
        mMainBeanList = mainBeanList;
    }

    @NonNull
    @Override
    public BankCardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_string, viewGroup, false);
        return new BankCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BankCardViewHolder viewHolder, int i) {
        viewHolder.mTvBankName.setText(mMainBeanList.get(i).getName());
        viewHolder.mTvBalance.setText(MessageFormat.format("¥-{0}", mMainBeanList.get(i).getBalance()));
        viewHolder.itemView.setOnClickListener(v -> {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onClick(i);
            }
        });
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return mMainBeanList.size();
    }

    public interface OnItemClickListener {
        /**
         * do something
         *
         * @param position position
         */
        void onClick(int position);
    }

    class BankCardViewHolder extends RecyclerView.ViewHolder {

        TextView mTvBankName;
        TextView mTvBalance;

        BankCardViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvBankName = itemView.findViewById(R.id.textView);
            mTvBalance = itemView.findViewById(R.id.balance);
        }
    }
}
