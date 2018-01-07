package engineer.echo.oneactivity.core;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * FragmentMasterState
 * Created by Plucky<plucky@echo.engineer> on 2018/1/6 下午6:02.
 * more about me: http://www.1991th.com
 */
final class FragmentMasterState implements Parcelable {

    Bundle mFragments;

    boolean mAllowSwipeBack;

    boolean mHomeFragmentApplied;

    public FragmentMasterState() {
    }

    private FragmentMasterState(Parcel in) {
        mFragments = in.readBundle(getClass().getClassLoader());
        mAllowSwipeBack = in.readInt() == 0;
        mHomeFragmentApplied = in.readInt() == 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeBundle(mFragments);
        dest.writeInt(mAllowSwipeBack ? 0 : 1);
        dest.writeInt(mHomeFragmentApplied ? 0 : 1);
    }

    public static final Parcelable.Creator<FragmentMasterState> CREATOR
            = new Parcelable.Creator<FragmentMasterState>() {
        public FragmentMasterState createFromParcel(Parcel in) {
            return new FragmentMasterState(in);
        }

        public FragmentMasterState[] newArray(int size) {
            return new FragmentMasterState[size];
        }
    };
}
