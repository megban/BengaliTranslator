package com.example.android.miwok;

/**
 * Created by meghnabanerjee on 5/3/17.
 */

public class Word {
    private String defaultTranslation, miwokTranslation;
    private  static final int NO_IMAGE_PROVIDED = -1;
    private int imageResourceId = NO_IMAGE_PROVIDED;
    private int audioResourceId;

    public Word(String defaultTranslation, String miwokTranslation, int audioResourceId)
    {
        this.defaultTranslation = defaultTranslation;
        this.miwokTranslation = miwokTranslation;
        this.audioResourceId = audioResourceId;
    }

    public Word(String defaultTranslation, String miwokTranslation, int imageResourceId, int audioResourceId)
    {
        this.defaultTranslation = defaultTranslation;
        this.miwokTranslation = miwokTranslation;
        this.imageResourceId = imageResourceId;
        this.audioResourceId = audioResourceId;
    }


    public String getDefaultTranslation()
    {

        return defaultTranslation;
    }

    public String getMiwokTranslation()
    {
        return miwokTranslation;
    }

    public int getImageResourceId()
    {
        return imageResourceId;
    }

    public boolean hasImage()
    {
        if (imageResourceId == -1)
        {
            return false;
        }
        return true;
    }

    public int getAudioResourceId()
    {
        return audioResourceId;
    }
}

