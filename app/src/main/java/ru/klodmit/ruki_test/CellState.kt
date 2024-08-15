package ru.klodmit.ruki_test

import android.os.Parcel
import android.os.Parcelable

enum class CellState : Parcelable {
    ALIVE, DEAD, LIFE;

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(ordinal)
    }

    companion object CREATOR : Parcelable.Creator<CellState> {
        override fun createFromParcel(parcel: Parcel): CellState {
            return values()[parcel.readInt()]
        }

        override fun newArray(size: Int): Array<CellState?> {
            return arrayOfNulls(size)
        }
    }
}
