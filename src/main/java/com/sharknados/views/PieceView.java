package com.sharknados.views;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PieceView{
    public ImageView piece;
    public PieceView(int x, int z, String name) throws FileNotFoundException {
        double pixelX = 3.0/2.0*x;
        double pixelY = (Math.sqrt(3.0))/2.0*x + Math.sqrt(3.0)*z;

        //todo pass in something and decide what image will be used for piece
        Image image = new Image(new FileInputStream(name));
//		Image image =  new Image(this.getClass().getResourceAsStream("/Shark.png"));

        // Setting the image view
        this.piece = new ImageView(image);

        // Setting the position of the image
        //todo fix these magic numbers
        piece.setX(pixelX*32 + 8);
        piece.setY(pixelY*32 - 20);

        // setting the fit height and width of the image view
        piece.setFitHeight(45);
        piece.setFitWidth(45);
        }
}
