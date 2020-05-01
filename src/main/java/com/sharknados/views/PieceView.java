package com.sharknados.views;

import com.sharknados.models.AbstractSubject;
import com.sharknados.models.pieces.Piece;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PieceView extends AbstractObserver{

    public ImageView pieceImage;
    public Text atkText;
    public Text defText;
    public Text hpText;
    private Piece subject;

    public PieceView(AbstractSubject piece) throws FileNotFoundException {
        this.subject = (Piece) piece;
        this.subject.attach(this);

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

        //text
        this.atkText = new Text();
        this.defText = new Text();
        this.hpText = new Text();

        //Initial Drawing
        draw(subject.getX(),subject.getZ());

        //stop the image blocking action listener of tile below
        pieceImage.setDisable(true);
        }

        public void draw(int x, int z){
            int radius = 22;
            double pixelX = 3.0/2.0*x;
            double pixelY = (Math.sqrt(3.0))/2.0*x + Math.sqrt(3.0)*z;

            // Setting the position of the image
            //todo fix these magic numbers
            pieceImage.setX(pixelX*2*radius + radius);
            pieceImage.setY(pixelY*2*radius - radius);

            // setting the fit height and width of the image view
            pieceImage.setFitHeight(2*radius);
            pieceImage.setFitWidth(2*radius);

            String atk = new String (new char[subject.getAttack()]).replace("\0","+");
            String def = new String (new char[subject.getDefence()]).replace("\0","+");
            String hp = new String (new char[subject.getHealth()]).replace("\0","+");
            Font font = new Font("ARIAL", 10);
            atkText.setText("Atk " + atk);
            atkText.setFont(font);
            atkText.setStyle("-fx-font-weight: bold;");
            atkText.setX(pixelX*2*radius + 20);
            atkText.setY(pixelY*2*radius - 28);
            defText.setText("Def " + def);
            defText.setFont(font);
            defText.setStyle("-fx-font-weight: bold;");
            defText.setX(pixelX*2*radius + 20);
            defText.setY(pixelY*2*radius - 20);
            hpText.setText("HP " + hp);
            hpText.setFont(font);
            hpText.setStyle("-fx-font-weight: bold;");
            hpText.setX(pixelX*2*radius + 20);
            hpText.setY(pixelY*2*radius + 32);
        }

    @Override
    public AbstractSubject getSubject(){
        return this.subject;
    }

    @Override
    public void update() {
        draw(subject.getX(),subject.getZ());
    }
}
