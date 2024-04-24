package validations;

import java.util.Random;

public class Collisions {

	private static final Random rand = new Random();

	public static int collisionWallBall(int ballY, int ballSize, float yMax, float yMin, int ballDY) {
		if (ballY + ballSize / 2 >= yMax || ballY - ballSize / 2 <= yMin) {
			return -ballDY; // Retorna o valor invertido de ballDY
		}
		return ballDY; // Retorna ballDY sem modificar
	}

	public static int collisionPaddleWall(int paddleY, int paddleHeight, float yMax, float yMin) {
		if (paddleY + paddleHeight / 2 >= yMax) {
			return (int) (yMax - paddleHeight / 2);
		} else if (paddleY - paddleHeight / 2 <= yMin) {
			return (int) (yMin + paddleHeight / 2);
		}
		return paddleY; // Retorna paddleY sem modificar
	}
	
	
}
