package com.nutsdev.annotationspresentation;

import android.Manifest;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.CheckResult;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.support.annotation.Size;
import android.support.annotation.WorkerThread;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // @CallSuper example
    @Override
    @CallSuper
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Теперь можно использовать наш парсер для тестовых классов.
        TestAnnotationParser parser = new TestAnnotationParser();
        try {
            parser.parse(Annotated.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Существующие аннотации из пакета android.support.annotation:

    // @NonNull example
    private void nonNull(@NonNull Long nonNullLong) { /* implementation */ }

    // @Nullable example
    @Nullable
    private Long nullable(@Nullable Long nullableLong) { return nullableLong; }

    // @WorkerThread example
    @WorkerThread
    private void workerThread() { /* implementation */ }

    // @Size example
    private void size(@Size(min = 2, max = 25) int[] locations) { /* implementation */ }

    // @IntRange example
    @IntRange(from = 1, to = 2)
    private int intRange(@IntRange(from = 0, to = 255) int location) { return 2; }

    // @FloatRange example
    @FloatRange(from = 0.0, to = 1.0, toInclusive = true)
    private int floatRange(float location) { return 1; }

    // @CheckResult example
    private @CheckResult String trim(String s) { return s.trim(); }
    // s.trim(); // this is probably an error
    // s = s.trim(); // ok

    // @ColorInt example
    private void colorInt(@ColorInt int color) { /* implementation */ }

    // @RequiresPermission examples
    @RequiresPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
    private Location getLastKnownLocation(String provider) { return new Location(provider); }

//    @RequiresPermission.Read(@RequiresPermission(READ_HISTORY_BOOKMARKS))
//    @RequiresPermission.Write(@RequiresPermission(WRITE_HISTORY_BOOKMARKS))
    public static final Uri BOOKMARKS_URI = Uri.parse("content://browser/bookmarks");

    @RequiresPermission(anyOf = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION})
    private Location getLastKnownLocationAny(String provider) { return new Location(provider); }

    @RequiresPermission(allOf = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION})
    public Location getLastKnownLocationAll(String provider) { return new Location(provider); }

}
