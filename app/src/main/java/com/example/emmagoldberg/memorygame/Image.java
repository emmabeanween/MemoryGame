package com.example.emmagoldberg.memorygame;

public class Image {

    private int image;
    private boolean isVisible;


    public Image(int addImage, boolean visible){
        image = addImage;
        isVisible = visible;

    }

    public int getImage() {
        return image;
    }

    public boolean getVisibility(){

        return isVisible;
    }


    public void setVisible(boolean visible) {
        isVisible = visible;
    }
}


