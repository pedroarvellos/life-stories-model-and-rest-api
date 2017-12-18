package br.com.lifestories.model.entity;

import br.com.lifestories.model.base.BaseEntity;


public class Gravacao extends BaseEntity{
    private Byte audio;

    public Byte getAudio() {
        return audio;
    }

    public void setAudio(Byte audio) {
        this.audio = audio;
    }
}
