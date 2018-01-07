package engineer.echo.oneactivity.cmpts.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * AnimatorBean
 * Created by Plucky<plucky@echo.engineer> on 2018/1/7 上午11:53.
 * more about me: http://www.1991th.com
 */

public class AnimatorBean implements Parcelable {

    private String title;
    private int color;
    private Class type;

    public AnimatorBean(String title, int color, Class type) {
        this.title = title;
        this.color = color;
        this.type = type;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeInt(this.color);
        dest.writeSerializable(this.type);
    }

    protected AnimatorBean(Parcel in) {
        this.title = in.readString();
        this.color = in.readInt();
        this.type = (Class) in.readSerializable();
    }

    public static final Parcelable.Creator<AnimatorBean> CREATOR = new Parcelable.Creator<AnimatorBean>() {
        @Override
        public AnimatorBean createFromParcel(Parcel source) {
            return new AnimatorBean(source);
        }

        @Override
        public AnimatorBean[] newArray(int size) {
            return new AnimatorBean[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public int getColor() {
        return color;
    }

    public Class getType() {
        return type;
    }
}
