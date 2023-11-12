package Model;

import java.util.Random;

public enum Shape {
	Circle,
	Square,
	Triangle,
	Joker;

	public boolean isSameShape(Shape comparedCard) {
		if (this == Joker || comparedCard == Joker) {
			return true;
		}
    return this == comparedCard;
	}

	public static Shape getRandomShape() {
		
		Random random = new Random();
		int shuffle = random.nextInt(4);
		
		switch (shuffle) {
			default:
			case 0:
				return Shape.Circle;
			
			case 1:
				return Shape.Square;
			
			case 2:
				return Shape.Triangle;
			}
		}
}

