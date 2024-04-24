package cena;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;

public class Ball {

	// Método para desenhar a esfera (bola) usando triângulos
	void drawSphere(GL2 gl, float centerX, float centerY, float radius, int slices, int stacks) {
	    gl.glColor3f(1.0f, 1.0f, 1.0f);
	    drawCircle(gl, centerX, centerY, radius / 2, slices);
	}


    // Método para desenhar um círculo que representa a bola
	void drawCircle(GL2 gl, float centerX, float centerY, float radius, int numSegments) {
	    gl.glBegin(GL.GL_TRIANGLE_FAN);
	    gl.glVertex2f(centerX, centerY); // Centro do círculo

	    for (int i = 0; i <= numSegments; i++) {
	        double angle = 2.0 * Math.PI * i / numSegments;
	        float x = centerX + (float) (radius * Math.cos(angle));
	        float y = centerY + (float) (radius * Math.sin(angle));
	        gl.glVertex2f(x, y);
	    }

	    gl.glEnd();
	}
}