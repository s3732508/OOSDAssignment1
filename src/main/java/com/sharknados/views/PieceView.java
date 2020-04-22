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
    private int xDrift = 0;
    public PieceView(AbstractController controller, int x, int z, String type) throws FileNotFoundException {
        this.controller = (PieceController) controller;
        this.x = x;
        this.z = z;
        int radius = 22;
        double pixelX = 3.0/2.0*x;
        double pixelY = (Math.sqrt(3.0))/2.0*x + Math.sqrt(3.0)*z;

        //todo figure out math to update mask position the reenable this
/*        //Code for transparent tile sized mask for action listen to be able to select piece
        double size = 2*radius;
        for (int i = 0; i < 6; i++) {
            double angle = 2.0 * Math.PI *(i) / 6.0;
            double offsetX = size*Math.cos(angle) + size;
            double offsetY = size*Math.sin(angle);
            pieceBackground.getPoints().addAll(offsetX + pixelX*size, offsetY + pixelY*size);
            pieceBackground.setFill(Color.BLACK);
        }*/


        Image image;
        switch(type){
            case "GREAT_WHITE":image = new Image(new FileInputStream("src/main/resources/Shark.png"));
            break;
            case "EAGLE_OWL": image = new Image(new FileInputStream("src/main/resources/Eagle.png"));
            break;
            default: image = new Image(new FileInputStream("src/main/resources/Shark.png"));
        }
        //Image image = new Image(new FileInputStream("src/main/resources/Shark.png"));
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
        int radius = 22;
        double shiftX = 0;
        double shiftY = 0;
        //todo figure out the maths on z to y conversion to redraw tile mask
        //Update the X value and redraw
        if (evt.getPropertyName().equals(PieceController.X_PROPERTY)) {
            this.x = (int)evt.getNewValue();
/*            if(x > (int)evt.getOldValue()){
                shiftX = 3.0/2.0*(2*radius);
            }
            else if(x < (int)evt.getOldValue()){
                shiftX = -3.0/2.0*(2*radius);
            }
            else{

            }*/
        }
        if (evt.getPropertyName().equals(PieceController.Z_PROPERTY)) {
            this.z = (int)evt.getNewValue();
/*            if(z > (int)evt.getOldValue()){
                shiftY = Math.sqrt(3.0)*(2*radius);
            }
            else if(z < (int)evt.getOldValue()){
                shiftY = -(Math.sqrt(3.0)*(2*radius));
            }
            else{

            }*/
        }


        //redraw
        double pixelX = 3.0/2.0*x;
        double pixelY = (Math.sqrt(3.0))/2.0*x + Math.sqrt(3.0)*z;

        // Setting the position of the image
        //todo fix these magic numbers
        piece.setX(pixelX*2*radius + 10);
        piece.setY(pixelY*2*radius - 32);

        // setting the fit height and width of the image view
        piece.setFitHeight(3*radius);
        piece.setFitWidth(3*radius);

/*        pieceBackground.setLayoutX(pieceBackground.getLayoutX()+shiftX);
        pieceBackground.setLayoutY(pieceBackground.getLayoutY()+shiftY);*/


    }
}
