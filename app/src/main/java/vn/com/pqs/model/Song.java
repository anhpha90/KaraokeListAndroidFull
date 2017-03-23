package vn.com.pqs.model;

import java.io.Serializable;

/**
 * Created by long on 18/03/2017.
 */

public class Song implements Serializable {
    String id,vol,mn,mnna,smn,singer,singerna,slyric,lyric,lyricna,composer,composerna;
    boolean like;
    int img;

    public Song() {
    }

    public Song(String id, String vol, String mn, String mnna, String smn, String singer, String singerna, String slyric, String lyric, String lyricna, String composer, String composerna, boolean like, int img) {
        this.id = id;
        this.vol = vol;
        this.mn = mn;
        this.mnna = mnna;
        this.smn = smn;
        this.singer = singer;
        this.singerna = singerna;
        this.slyric = slyric;
        this.lyric = lyric;
        this.lyricna = lyricna;
        this.composer = composer;
        this.composerna = composerna;
        this.like = like;
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVol() {
        return vol;
    }

    public void setVol(String vol) {
        this.vol = vol;
    }

    public String getMn() {
        return mn;
    }

    public void setMn(String mn) {
        this.mn = mn;
    }

    public String getMnna() {
        return mnna;
    }

    public void setMnna(String mnna) {
        this.mnna = mnna;
    }

    public String getSmn() {
        return smn;
    }

    public void setSmn(String smn) {
        this.smn = smn;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getSingerna() {
        return singerna;
    }

    public void setSingerna(String singerna) {
        this.singerna = singerna;
    }

    public String getSlyric() {
        return slyric;
    }

    public void setSlyric(String slyric) {
        this.slyric = slyric;
    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }

    public String getLyricna() {
        return lyricna;
    }

    public void setLyricna(String lyricna) {
        this.lyricna = lyricna;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public String getComposerna() {
        return composerna;
    }

    public void setComposerna(String composerna) {
        this.composerna = composerna;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
