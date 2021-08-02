package com.example.warcards.cards;

import android.os.Parcel;
import android.os.Parcelable;

public class Cards implements Parcelable {

    private String name;
    private int image;
    private int number;

    public Cards(String name, int image, int number) {
        this.name = name;
        this.image = image;
        this.number = number;
    }

    public Cards(Cards product) {
        this.name = product.name;
        this.image = product.image;
        this.number = product.number;
    }

    protected Cards(Parcel in) {
        name = in.readString();
        image = in.readInt();
        number = in.readInt();
    }


    public static final Parcelable.Creator<Cards> CREATOR = new Parcelable.Creator<Cards>() {
        @Override
        public Cards createFromParcel(Parcel in) {
            return new Cards(in);
        }

        @Override
        public Cards[] newArray(int size) {
            return new Cards[size];
        }
    };

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public int getNumber() {
        return number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setNumber(int num) {
        number = num;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(image);
        parcel.writeInt(number);
    }

}
