package com.robert.dcm.widget;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lcodecore.tkrefreshlayout.IBottomView;
import com.robert.dcm.R;

import ag.yinglingedu.com.xlibrary.utils.LogUtil;

/**
 * Created on 2017/12/20.
 *
 * @author cpf
 * @2227039052@qq.com
 */

public class ClassicLoadMoreFooter extends FrameLayout implements IBottomView {
    private TextView tvLoadMore;
    private ImageView ivSuccess;
    private ProgressBar progressBar;

    private int mFooterHeight;

    public ClassicLoadMoreFooter(Context context) {
        super(context);
        mFooterHeight = 1;
        View rootView = View.inflate(getContext(), R.layout.layout_classic_footer, null);
        tvLoadMore = (TextView) rootView.findViewById(R.id.tvLoadMore);
        ivSuccess = (ImageView) rootView.findViewById(R.id.ivSuccess);
        progressBar = (ProgressBar) rootView.findViewById(R.id.progressbar);
        addView(rootView);
    }


    @Override
    public View getView() {
        return this;
    }

    @Override
    public void onPullingUp(float fraction, float maxBottomHeight, float bottomHeight) {
        LogUtil.e("["+fraction+"---"+maxBottomHeight+"---"+bottomHeight+"]");
        ivSuccess.setVisibility(GONE);
        progressBar.setVisibility(GONE);
        if (-fraction >= mFooterHeight) {
            tvLoadMore.setText("释放立即加载");
        } else {
            tvLoadMore.setText("上拉加载更多");
        }
    }

    @Override
    public void startAnim(float maxBottomHeight, float bottomHeight) {
        tvLoadMore.setText("加载中...");
        progressBar.setVisibility(VISIBLE);
    }

    @Override
    public void onPullReleasing(float fraction, float maxBottomHeight, float bottomHeight) {

    }

    @Override
    public void onFinish() {
        progressBar.setVisibility(GONE);
        ivSuccess.setVisibility(VISIBLE);
    }

    @Override
    public void reset() {
        ivSuccess.setVisibility(GONE);
    }
}
