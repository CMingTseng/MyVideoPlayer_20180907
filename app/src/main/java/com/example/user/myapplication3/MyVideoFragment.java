package com.example.user.myapplication3;

import android.net.Uri;
import android.os.Bundle;
import android.support.v17.leanback.app.VideoFragment;
import android.support.v17.leanback.app.VideoFragmentGlueHost;
import android.support.v17.leanback.media.MediaPlayerAdapter;
import android.support.v17.leanback.media.PlaybackTransportControlGlue;
import android.view.KeyEvent;

public class MyVideoFragment extends VideoFragment  {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setUpUI();

    }

    public void setUpUI() {
/*
        final PlaybackTransportControlGlue<MediaPlayerAdapter> playerGlue =
                new VideoPlayerGlue<>(getContext(),
                        new MediaPlayerAdapter(getContext()));
*/

        final PlaybackTransportControlGlue<MediaPlayerAdapter> playerGlue =
                new VideoPlayerGlue<>(getContext(),
                        new MediaPlayerAdapter(getContext()));

        playerGlue.setHost(new VideoFragmentGlueHost(this));
/*
        playerGlue.addPlayerCallback(new PlaybackGlue.PlayerCallback() {
            @Override
            public void onPreparedStateChanged(PlaybackGlue glue) {
                if (glue.isPrepared()) {
                    playerGlue.setSeekProvider(new PlaybackSeekMetadataDataProvider(context,video.getPath(),30000));
                    //playerGlue.play();
                }
            }
        });
*/
        playerGlue.setSubtitle("Leanback artist");
        playerGlue.setTitle("Leanback team at work");
        String uriPath = "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/Zeitgeist/Zeitgeist%202010_%20Year%20in%20Review.mp4";
        playerGlue.getPlayerAdapter().setDataSource(Uri.parse(uriPath));
        //裝載音頻
        playerGlue.playWhenPrepared();

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            getActivity().finish();
        }

        return false;
    }


}

