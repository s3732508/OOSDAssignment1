package shapes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Eagle {
	public ImageView eagle;

	public Eagle(int x, int y, double size2) throws FileNotFoundException {

		Image image = new Image(
				new FileInputStream("C:\\Users\\Vikas\\Documents\\New folder\\Assignmemt1\\src\\shapes\\Eagle.PNG"));
		// Setting the image view
		eagle = new ImageView(image);

		// Setting the position of the image
		eagle.setX((5.91 + (4 * x - 2 * y)) * size2);
		eagle.setY((4.0 + (3 * y)) * size2);

		// setting the fit height and width of the image view
		eagle.setFitHeight(25);
		eagle.setFitWidth(50);

	}

}
