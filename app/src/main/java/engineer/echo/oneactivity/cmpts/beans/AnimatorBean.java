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
    private int duration;


    public AnimatorBean(String title, int color, Class type, int duration) {
        this.title = title;
        this.color = color;
        this.type = type;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public int getColor() {
        return color;
    }

    public Class getType() {
        return type;
    }

    public int getDuration() {
        return duration;
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
        dest.writeInt(this.duration);
    }

    protected AnimatorBean(Parcel in) {
        this.title = in.readString();
        this.color = in.readInt();
        this.type = (Class) in.readSerializable();
        this.duration = in.readInt();
    }

    public static final Creator<AnimatorBean> CREATOR = new Creator<AnimatorBean>() {
        @Override
        public AnimatorBean createFromParcel(Parcel source) {
            return new AnimatorBean(source);
        }

        @Override
        public AnimatorBean[] newArray(int size) {
            return new AnimatorBean[size];
        }
    };
}
