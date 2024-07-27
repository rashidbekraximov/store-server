package uz.cluster.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Tag {
    private int width;
    private String linkUrl;
    private String text;
    private String textColor;
    private int type;
    private String tagTrackInfo;
    private int height;

    // Getters and Setters
}

