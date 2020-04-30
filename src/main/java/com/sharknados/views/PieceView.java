package com.sharknados.views;

import com.sharknados.models.AbstractSubject;
import com.sharknados.models.pieces.Piece;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PieceView extends AbstractObserver{

    public ImageView pieceImage;
    private Piece subject;

    public PieceView(AbstractSubject piece) throws FileNotFoundException {
        this.subject = (Piece) piece;
        this.subject.attach(this);

        //Initial Drawing
        int x = subject.getX();
        int z = subject.getZ();
        int radius = 22;
        double pixelX = 3.0/2.0*x;
        double pixelY = (Math.sqrt(3.0))/2.0*x + Math.sqrt(3.0)*z;


        Image image;
        String type = subject.getType().toString();
        switch(type){
            case "GREAT_WHITE":image = new Image(new FileInputStream("src/main/resources/Shark.png"));
            break;
            case "EAGLE_OWL": image = new Image(new FileInputStream("src/main/resources/Eagle.png"));
            break;
            default: image = new Image(new FileInputStream("src/main/resources/Shark.png"));
        }

        // Setting the image view
        this.pieceImage = new ImageView(image);

        // Setting the position of the image
        //todo fix these magic numbers
        pieceImage.setX(pixelX*2*radius + 10);
        pieceImage.setY(pixelY*2*radius - 32);

        // setting the fit height and width of the image view
        pieceImage.setFitHeight(3*radius);
        pieceImage.setFitWidth(3*radius);

        //stop the image blocking action listener of tile below
        pieceImage.setDisable(true);
        }

    @Override
    public AbstractSubject getSubject(){
        return this.subject;
    }

    @Override
    public void update() {
        //redraw
        int x = subject.getX();
        int z = subject.getZ();
        int radius = 22;
        double pixelX = 3.0/2.0*x;
        double pixelY = (Math.sqrt(3.0))/2.0*x + Math.sqrt(3.0)*z;

        // Setting the position of the image
        //todo fix these magic numbers
        pieceImage.setX(pixelX*2*radius + 10);
        pieceImage.setY(pixelY*2*radius - 32);

        // setting the fit height and width of the image view
        pieceImage.setFitHeight(3*radius);
        pieceImage.setFitWidth(3*radius);
    }
}
