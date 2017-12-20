package com.robert.dcm.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lcodecore.tkrefreshlayout.IHeaderView;
import com.lcodecore.tkrefreshlayout.OnAnimEndListener;
import com.robert.dcm.R;

/**
 * Created on 2017/12/18.
 *
 * @author cpf
 * @2227039052@qq.com
 */

public class ClassicRefreshHeader extends FrameLayout implements IHeaderView{

    private ImageView ivArrow;
    private ImageView ivSuccess;
    private TextView tvRefresh;
    private ProgressBar progressBar;
    private int mHeaderHeight;
    private Animation rotateUp;
    private Animation rotateDown;
    private boolean rotated = false;

    public ClassicRefreshHeader(@NonNull Context context) {
        super(context);
        init(context);
    }



    private void init(Context context) {
        mHeaderHeight = 1;
        rotateUp = AnimationUtils.loadAnimation(context, R.anim.rotate_up);
        rotateDown = AnimationUtils.loadAnimation(context, R.anim.rotate_down);
        View rootView = View.inflate(getContext(), R.layout.layout_classic_header, null);
        tvRefresh = (TextView) rootView.findViewById(R.id.tvRefresh);
        ivArrow = (ImageView) rootView.findViewById(R.id.ivArrow);
        ivSuccess = (ImageView) rootView.findViewById(R.id.ivSuccess);
        progressBar = (ProgressBar) rootView.findViewById(R.id.progressbar);
        addView(rootView);
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public void onPullingDown(float fraction, float maxHeadHeight, float headHeight) {
        ivArrow.setVisibility(VISIBLE);
        progressBar.setVisibility(GONE);
        ivSuccess.setVisibility(GONE);
        if (fraction > mHeaderHeight) {
            tvRefresh.setText("释放立即刷新");
            if (!rotated) {
                ivArrow.clearAnimation();
                ivArrow.startAnimation(rotateUp);
                rotated = true;
            }
        } else if (fraction < mHeaderHeight) {
            if (rotated) {
                ivArrow.clearAnimation();
                ivArrow.startAnimation(rotateDown);
                rotated = false;
            }
            tvRefresh.setText("下拉刷新");
        }
    }

    @Override
    public void onPullReleasing(float fraction, float maxHeadHeight, float headHeight) {
        ivArrow.setVisibility(VISIBLE);
        progressBar.setVisibility(GONE);
        ivSuccess.setVisibility(GONE);
        if (fraction > mHeaderHeight) {
            tvRefresh.setText("释放立即刷新");
            if (!rotated) {
                ivArrow.clearAnimation();
                ivArrow.startAnimation(rotateUp);
                rotated = true;
            }
        } else if (fraction < mHeaderHeight) {
            if (rotated) {
                ivArrow.clearAnimation();
                ivArrow.startAnimation(rotateDown);
                rotated = false;
            }
            tvRefresh.setText("下拉刷新");
        }
    }

    @Override
    public void startAnim(float maxHeadHeight, float headHeight) {
        ivSuccess.setVisibility(GONE);
        ivArrow.clearAnimation();
        ivArrow.setVisibility(GONE);
        progressBar.setVisibility(VISIBLE);
        tvRefresh.setText("正在刷新...");
    }

    @Override
    public void onFinish(OnAnimEndListener animEndListener) {
        animEndListener.onAnimEnd();
        rotated = false;
        ivSuccess.setVisibility(VISIBLE);
        ivArrow.clearAnimation();
        ivArrow.setVisibility(GONE);
        progressBar.setVisibility(GONE);
        tvRefresh.setText("刷新成功");
    }

    @Override
    public void reset() {
        rotated = false;
        ivSuccess.setVisibility(GONE);
        ivArrow.clearAnimation();
        ivArrow.setVisibility(GONE);
        progressBar.setVisibility(GONE);
    }
}
