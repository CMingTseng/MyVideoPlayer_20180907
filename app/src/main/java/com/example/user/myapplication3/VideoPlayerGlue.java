package com.example.user.myapplication3;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v17.leanback.media.PlaybackTransportControlGlue;
import android.support.v17.leanback.media.PlayerAdapter;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.PlaybackControlsRow;
import java.util.concurrent.TimeUnit;

/**
 * PlayerGlue for video playback
 * @param <T>
 */
public class VideoPlayerGlue<T extends PlayerAdapter> extends PlaybackTransportControlGlue<T>{

    private static final long TEN_SECONDS = TimeUnit.SECONDS.toMillis(30);

    // rewind, fast forward
    // <<           >>
    private PlaybackControlsRow.RewindAction mRewindAction;
    private PlaybackControlsRow.FastForwardAction mFastForwardAction;


    public VideoPlayerGlue(Context context, T impl) {
        super(context, impl);

        mRewindAction = new PlaybackControlsRow.RewindAction(context);
        mFastForwardAction = new PlaybackControlsRow.FastForwardAction(context);

    }

    @Override
    protected void onCreatePrimaryActions(ArrayObjectAdapter adapter) {
        super.onCreatePrimaryActions(adapter);
        adapter.add(mRewindAction);
        adapter.add(mFastForwardAction);

    }

/*
    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {

        Log.d("KeyCode",""+keyCode);
        Log.d("KeyEvent",""+event);

        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_UP:
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN:
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:

                if(event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    fastForward();
                    break;
                } else {
                    break;
                }

            case KeyEvent.KEYCODE_DPAD_LEFT:

                if(event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    rewind();
                    break;
                } else {
                    break;
                }
        }

        return false;
    }
*/

    /** Skips backwards 10 seconds. */
    private void rewind() {
        long newPosition = getCurrentPosition() - TEN_SECONDS;
        newPosition = (newPosition < 0) ? 0 : newPosition;
        getPlayerAdapter().seekTo(newPosition);
    }


    /** Skips forward 10 seconds. */
    private void fastForward() {
        if (getDuration() > -1) {
            long newPosition = getCurrentPosition() + TEN_SECONDS;
            newPosition = (newPosition > getDuration()) ? getDuration() : newPosition;
            getPlayerAdapter().seekTo(newPosition);
        }
    }

}

