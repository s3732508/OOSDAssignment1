package com.sharknados.views;


import com.sharknados.models.Subject;
import com.sharknados.models.pieces.Piece;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PieceView implements Observer {

    public ImageView pieceImage;
    public Text selectedPieceStats;
    private Piece subject;

    public PieceView(Subject piece) throws FileNotFoundException {
        this.subject = (Piece) piece;
        this.subject.attach(this);

        Image image;
        //Image for type of piece
        String type = subject.getClass().getSimpleName();
        image = new Image(new FileInputStream("src/main/resources/" + type + "2.png"));

        // Setting the image view
        this.pieceImage = new ImageView(image);

        //text
        this.selectedPieceStats = new Text();
        Font font = new Font("ARIAL", 16);
        selectedPieceStats.setFont(font);
        selectedPieceStats.setStyle("-fx-font-weight: bold;");
        selectedPieceStats.setTranslateY(10);
        selectedPieceStats.setTranslateX(10);


        //Initial Drawing
        update();

        //stop the image or text blocking action listener of tile below
        pieceImage.setDisable(true);
        selectedPieceStats.setDisable(true);
        }


    @Override
    public Subject getSubject(){
        return this.subject;
    }

    @Override
    public void update() {
        int x = subject.getX();
        int z = subject.getZ();
        int radius = 22;
        double pixelX = 3.0/2.0*x;
        double pixelY = (Math.sqrt(3.0))/2.0*x + Math.sqrt(3.0)*z;

        // Setting the position of the image
        //todo fix these magic numbers
        pieceImage.setX(pixelX*2*radius + radius);
        pieceImage.setY(pixelY*2*radius - 3*radius);

        // setting the fit height and width of the image view
        pieceImage.setFitHeight(2*radius);
        pieceImage.setFitWidth(2*radius);

        String pieceStats = "";
        if(subject.isSelected()) {
            String pieceName = subject.getClass().getSimpleName();
            String pieceHealth = "HP: " + subject.getHealth();
            String pieceAttack = "ATK: " + subject.getAttack();
            String pieceDefense = "DEF: " + subject.getDefence();
            String pieceMove = "MOV: " + subject.getMovement();
            String pieceRange = "RNG: " + subject.getAttackRange();
            String pieceMode = "MODE: " + subject.getMode().getName();
            pieceStats += pieceName + " | " + pieceHealth + " | " + pieceAttack + " | " + pieceDefense + " | " + pieceMove + " | " + pieceRange + " | " + pieceMode;
        }
        selectedPieceStats.setText(pieceStats);
    }
}
