package engineer.echo.oneactivity.core;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import engineer.echo.oneactivity.R;
import engineer.echo.oneactivity.animator.FixedSpeedScroller;
import engineer.echo.oneactivity.animator.PageAnimator;

class FragmentMasterImpl extends FragmentMaster implements PagerController {
    private static final String TAG = "FragmentMasterImpl";

    // The id of fragments' real container.
    public final static int FRAGMENT_CONTAINER_ID = R.id.master_fragment_container;

    private FragmentsAdapter mAdapter;

    private FragmentMasterPager mViewPager;
    private FixedSpeedScroller mScroller;

    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.SimpleOnPageChangeListener() {
        @Override
        public void onPageScrollStateChanged(int state) {
            if (state == ViewPager.SCROLL_STATE_IDLE) {
                onScrollIdle();
            }
        }
    };

    private Runnable mCleanUpRunnable = new Runnable() {
        @Override
        public void run() {
            cleanUp();
        }
    };

    FragmentMasterImpl(FragmentActivity activity) {
        super(activity);
    }

    @Override
    protected void performInstall(ViewGroup container) {
        mAdapter = new FragmentsAdapter();
        mViewPager = new FragmentMasterPager(getActivity(), this);
        mViewPager.setId(FRAGMENT_CONTAINER_ID);
        mViewPager.setOffscreenPageLimit(Integer.MAX_VALUE);
        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(mOnPageChangeListener);
        mScroller = new FixedSpeedScroller(getActivity(), new LinearOutSlowInInterpolator());
        reflectScroller(mViewPager, mScroller);
        container.addView(mViewPager);
    }

    @Override
    protected int getFragmentContainerId() {
        return FRAGMENT_CONTAINER_ID;
    }

    @Override
    protected void onFragmentStarted(IMasterFragment fragment) {
        mAdapter.notifyDataSetChanged();
        int nextItem = mAdapter.getCount() - 1;
        // Perform "smooth scroll" if the page has a PageAnimator and more than
        // one item.
        boolean smoothScroll = hasPageAnimator() && nextItem > 0;
        setCurrentPageInner(nextItem, smoothScroll);
    }

    @Override
    protected void onFinishFragment(final IMasterFragment fragment,
                                    final int resultCode, final Request data) {
        final int index = indexOf(fragment);
        if (index != 0 && isPrimaryFragment(fragment)) {
            // If there's a PageAnimator, and the fragment to finish is the
            // primary fragment, scroll back smoothly.
            // When scrolling is stopped, real finish will be done by
            // cleanUp method.
            if (hasPageAnimator()) {
                setCurrentPageInner(index - 1, true);
                // If pager is scrolling, do real finish when cleanUp.
                deliverFragmentResult(fragment, resultCode, data);
                return;
            }
            // If there isn't a PageAnimator, this fragment will be finished immediately.
            // Before finish this fragment, finish fragments above it.
            List<IMasterFragment> fragments = new ArrayList<>(getFragments());
            for (int i = fragments.size() - 1; i > index; i--) {
                IMasterFragment f = fragments.get(i);
                if (isInFragmentMaster(f)) {
                    if (isFinishPending(f)) {
                        doFinishFragment(f);
                    } else {
                        f.finish();
                    }
                }
            }
            setCurrentPageInner(index - 1, false);
        }
        super.onFinishFragment(fragment, resultCode, data);
    }

    @Override
    protected void onFragmentFinished(IMasterFragment fragment) {
        mAdapter.notifyDataSetChanged();
    }

    private void onScrollIdle() {
        // When scrolling stopped, do cleanup.
        mViewPager.removeCallbacks(mCleanUpRunnable);
        mViewPager.post(mCleanUpRunnable);
    }

    /**
     * 当页面切换之前 设置页面切换时间
     *
     * @param index  int
     * @param smooth boolean
     */
    private void setCurrentPageInner(int index, boolean smooth) {
        PageAnimator animator = getPageAnimator();
        if (mScroller != null && animator != null) {
            mScroller.setPageDuration(animator.getDuraiton());
        }
        mViewPager.setCurrentItem(index, smooth);
    }

    /**
     * check whether there are any fragments above the primary fragment,
     * and finish them.
     */
    private void cleanUp() {
        List<IMasterFragment> fragments = new ArrayList<>(getFragments());
        IMasterFragment primaryFragment = getPrimaryFragment();
        // determine whether f is above primary fragment.
        boolean abovePrimary = true;
        for (int i = fragments.size() - 1; i >= 0; i--) {
            IMasterFragment f = fragments.get(i);
            if (f == primaryFragment) {
                abovePrimary = false;
            }

            if (abovePrimary) {
                // All fragments above primary fragment should be finished.
                if (isInFragmentMaster(f)) {
                    if (isFinishPending(f)) {
                        doFinishFragment(f);
                    } else {
                        f.finish();
                    }
                }
            } else {
                if (isFinishPending(f) && !mViewPager.isScrolling()) {
                    doFinishFragment(f);
                }
            }
        }
    }

    private class FragmentsAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return getFragments().size();
        }

        @Override
        public int getItemPosition(Object object) {
            IMasterFragment fragment = (IMasterFragment) object;
            int position = indexOf(fragment);
            return position == -1 ? POSITION_NONE : position;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            return getFragments().get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
        }

        @Override
        public void setPrimaryItem(ViewGroup container, int position,
                                   Object object) {
            setPrimaryFragment((IMasterFragment) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return ((IMasterFragment) object).getView() == view;
        }
    }

    /**
     * 通过反射更改ViewPager滑动速度
     *
     * @param scroller Scroller
     */
    private void reflectScroller(ViewPager pager, Scroller scroller) {
        try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            field.set(pager, scroller);
            Log.d(TAG, "reflectScroller: ");
        } catch (Exception e) {
            Log.e(TAG, "reflectScroller: ", e);
        }
    }
}
