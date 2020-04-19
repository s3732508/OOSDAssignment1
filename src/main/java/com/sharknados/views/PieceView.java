package com.sharknados.views;

import com.sharknados.controllers.AbstractController;
import com.sharknados.controllers.PieceController;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import java.beans.PropertyChangeEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PieceView implements ViewImpl{
    private PieceController controller = null;
    public ImageView piece;
    public Polygon pieceBackground = new Polygon();
    private int x;
    private int z;
    public PieceView(AbstractController controller, int x, int z) throws FileNotFoundException {
        this.controller = (PieceController) controller;
        this.x = x;
        this.z = z;
        int radius = 22;
        double pixelX = 3.0/2.0*x;
        double pixelY = (Math.sqrt(3.0))/2.0*x + Math.sqrt(3.0)*z;

/*        //Code for transparent tile sized mask for action listen to be able to select piece
        double size = 2*radius;
        for (int i = 0; i < 6; i++) {
            double angle = 2.0 * Math.PI *(i) / 6.0;
            double offsetX = size*Math.cos(angle) + size;
            double offsetY = size*Math.sin(angle);
            pieceBackground.getPoints().addAll(offsetX + pixelX*size, offsetY + pixelY*size);
            pieceBackground.setFill(Color.BLACK);
        }*/


        //todo pass in something and decide what image will be used for piece
        Image image = new Image(new FileInputStream("src/main/resources/Shark.png"));
//		Image image =  new Image(this.getClass().getResourceAsStream("/Shark.png"));

        // Setting the image view
        this.piece = new ImageView(image);

        // Setting the position of the image
        //todo fix these magic numbers
        piece.setX(pixelX*2*radius + 10);
        piece.setY(pixelY*2*radius - 32);

        // setting the fit height and width of the image view
        piece.setFitHeight(3*radius);
        piece.setFitWidth(3*radius);
        }

    @Override
    public void modelPropertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue() == null) return;
        //Update the X value and redraw
        if (evt.getPropertyName().equals(PieceController.X_PROPERTY)) {
            this.x = (int)evt.getNewValue();
        }
        if (evt.getPropertyName().equals(PieceController.Z_PROPERTY)) {
            this.z = (int)evt.getNewValue();
        }

        //redraw
        int radius = 22;
        double pixelX = 3.0/2.0*x;
        double pixelY = (Math.sqrt(3.0))/2.0*x + Math.sqrt(3.0)*z;

        // Setting the position of the image
        //todo fix these magic numbers
        piece.setX(pixelX*2*radius + 10);
        piece.setY(pixelY*2*radius - 32);

        // setting the fit height and width of the image view
        piece.setFitHeight(3*radius);
        piece.setFitWidth(3*radius);

    }
}
