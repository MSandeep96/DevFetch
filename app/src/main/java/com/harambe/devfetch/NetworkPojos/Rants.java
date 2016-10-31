package com.harambe.devfetch.NetworkPojos;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Sandeep on 01-11-2016.
 */

public class Rants implements Parcelable{
    long id;
    String text;

    public long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.text);
    }

    public Rants() {
    }

    protected Rants(Parcel in) {
        this.id = in.readLong();
        this.text = in.readString();
    }

    public static final Creator<Rants> CREATOR = new Creator<Rants>() {
        @Override
        public Rants createFromParcel(Parcel source) {
            return new Rants(source);
        }

        @Override
        public Rants[] newArray(int size) {
            return new Rants[size];
        }
    };
}
