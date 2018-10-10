package no.hiof.larseknu.playingwithgmaps;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

public class KittenLocation implements Parcelable {
    private String name;
    private LatLng latLng;

    public KittenLocation(String name, LatLng latLng) {
        this.name = name;
        this.latLng = latLng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeParcelable(this.latLng, flags);
    }

    protected KittenLocation(Parcel in) {
        this.name = in.readString();
        this.latLng = in.readParcelable(LatLng.class.getClassLoader());
    }

    public static final Parcelable.Creator<KittenLocation> CREATOR = new Parcelable.Creator<KittenLocation>() {
        @Override
        public KittenLocation createFromParcel(Parcel source) {
            return new KittenLocation(source);
        }

        @Override
        public KittenLocation[] newArray(int size) {
            return new KittenLocation[size];
        }
    };
}
