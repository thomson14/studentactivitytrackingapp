package com.example.studentactivitytrackingapp.habitTrackerNew;


import android.os.Build;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity(foreignKeys = @ForeignKey(
        entity = Habit.class,
        parentColumns = "id",
        childColumns = "habit_id",
        onDelete = ForeignKey.CASCADE
))
public class Event {
    @PrimaryKey(autoGenerate = true)
    long id;

    @ColumnInfo(name = "habit_id")
    long habitId;

    /**
     * An output of `System.currentTimeMillis()`
     */
    @ColumnInfo(name = "timestamp")
    long timestamp;

    // Returns true if the time was adjusted, false otherwise
    public boolean maybeAdjustTimestampToPreviousDay() {
        // If the time is between midnight and 3am, we want to record it for the
        // previous day at 11:59pm instead.
        LocalDateTime dt = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            dt = Instant.ofEpochMilli(this.timestamp)
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (dt.getHour() < 3) {
                dt = dt.minusHours(dt.getHour() + 1);
                dt = dt.plusMinutes(59 - dt.getMinute());
                this.timestamp = dt.atZone(ZoneId.systemDefault()).toEpochSecond() * 1000L;
                return true;
            }
        }
        return false;
    }

    @NonNull
    public static String csvHeader() {
        return "id,habitId,timestamp\n";
    }

    public String csv() {
        return id + "," + habitId + "," + timestamp;
    }
}
