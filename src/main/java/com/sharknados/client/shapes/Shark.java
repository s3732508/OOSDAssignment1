package shapes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Shark {

	public ImageView shark;

	public Shark(int x, int y, double size2) throws FileNotFoundException {

		Image image = new Image(
				new FileInputStream("C:\\Users\\Vikas\\Documents\\New folder\\Assignmemt1\\src\\shapes\\Shark.PNG"));
		// Setting the image view
		shark = new ImageView(image);

		// Setting the position of the image
		shark.setX((6.2 + (4 * x - 2 * y)) * size2);
		shark.setY((3.2 + (3 * y)) * size2);

		// setting the fit height and width of the image view
		shark.setFitHeight(40);
		shark.setFitWidth(40);

	}

}
