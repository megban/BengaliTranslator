package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    ArrayList<Word> words = new ArrayList<Word>();
    MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion( MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };
    AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int i) {
            if(i==AudioManager.AUDIOFOCUS_GAIN)
            {
                mediaPlayer.seekTo(0);
                mediaPlayer.start();
            }
            else if(i==AudioManager.AUDIOFOCUS_LOSS)
            {
                mediaPlayer.stop();
                releaseMediaPlayer();
            }
            else if(i==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT)
            {
                mediaPlayer.pause();
            }
            else if(i==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK)
            {
                mediaPlayer.pause();
            }

        }
    };
    AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);


        words.add(new Word("Where are you going?", "Khotaye jacho?", R.raw.phrase1));
        words.add(new Word("What is your name?", "Tomar nam ki?", R.raw.phrase2));
        words.add(new Word("My name is...", "Amar naam..", R.raw.phrase3));
        words.add(new Word("How are you?", "Kemon acho?", R.raw.phrase4));
        words.add(new Word("I’m good.", "Ami bhalo achhi", R.raw.phrase5));
        words.add(new Word("Are you coming?", "Tumi aashcho?", R.raw.phrase6));
        words.add(new Word("Yes, I’m coming.", "Hai aashchi.", R.raw.phrase7));
        words.add(new Word("Let’s go.", "Cholo", R.raw.phrase8));
        words.add(new Word("I love you", "Ami tomake bhalo bashi", R.raw.phrase9));

        //int audioFocus = audioManager.requestAudioFocus(onAudioFocusChangeListener, AudioManager.STREAM_MUSIC,  AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
        //onAudioFocusChangeListener.onAudioFocusChange(audioFocus);

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_phrases);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                releaseMediaPlayer();
                audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);


                int audioFocus = audioManager.requestAudioFocus(onAudioFocusChangeListener, AudioManager.STREAM_MUSIC,  AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);


                if (audioFocus == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // Start playback
                    mediaPlayer = MediaPlayer.create(PhrasesActivity.this, words.get(position).getAudioResourceId());
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(onCompletionListener);
                }

            }
        });




        listView.setAdapter(adapter);

    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;
            audioManager.abandonAudioFocus(onAudioFocusChangeListener);
        }
    }


}
