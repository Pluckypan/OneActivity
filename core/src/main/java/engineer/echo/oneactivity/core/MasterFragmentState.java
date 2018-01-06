package engineer.echo.oneactivity.core;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * MasterFragmentState
 * Created by Plucky<plucky@echo.engineer> on 2018/1/6 下午6:09.
 * more about me: http://www.1991th.com
 */
class MasterFragmentState implements Parcelable {

    Request mRequest;

    int mSoftInputMode;

    boolean mIsSlideable;

    public MasterFragmentState(MasterFragmentDelegate mfd) {
        mRequest = mfd.mRequest;
        mSoftInputMode = mfd.mSoftInputMode;
        mIsSlideable = mfd.mIsSlideable;
    }

    public MasterFragmentState(Parcel in) {
        mRequest = in
                .readParcelable(MasterFragmentState.class.getClassLoader());
        mSoftInputMode = in.readInt();
        mIsSlideable = in.readInt() != 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(mRequest, flags);
        dest.writeInt(mSoftInputMode);
        dest.writeInt(mIsSlideable ? 1 : 0);
    }

    public static final Creator<MasterFragmentState> CREATOR
            = new Creator<MasterFragmentState>() {
        public MasterFragmentState createFromParcel(Parcel in) {
            return new MasterFragmentState(in);
        }

        public MasterFragmentState[] newArray(int size) {
            return new MasterFragmentState[size];
        }
    };

    public void restore(MasterFragmentDelegate mfd) {
        mfd.mRequest = mRequest;
        mfd.mSoftInputMode = mSoftInputMode;
    }
}
