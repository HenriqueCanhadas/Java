package cena;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import com.jogamp.opengl.util.awt.TextRenderer;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.Random;

import static validations.Collisions.*;

public class Cena implements GLEventListener {
    private float xMin, xMax, yMin, yMax, zMin, zMax;
    private int paddleWidth = 10;
    private int paddleHeight = 80;
    public int paddle1Y = 0;
    public int paddle2Y = 0;
    private int ballSize = 10;
    private int ballX = 0;
    private int ballY = 0;
    private int ballDX = 2;
    private int ballDY = 2;
    private int player1Score = 0;
    private int computer = 0;
    private TextRenderer textRenderer;
    private GLU glu;
    private Random rand = new Random();

    // Adicionado para suporte à textura
    private Texture texturaBola;

    public Cena() {
        xMin = -100;
        xMax = 100;
        yMin = -100;
        yMax = 100;
        ballX = (int) ((xMax - xMin) / 2);
        ballY = (int) ((yMax - yMin) / 2);
        textRenderer = new TextRenderer(new Font("Arial", Font.BOLD, 24));
        glu = new GLU();
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor(0, 0, 0, 1);

        // Carrega a textura da bola
        try {
            texturaBola = TextureIO.newTexture(getClass().getResource("/caminho/para/sua/textura.jpg"), true, "jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        gl.glLoadIdentity();
        gl.glOrtho(xMin, xMax, yMin, yMax, -1, 1);
        gl.glColor3f(1, 1, 1);

        gl.glRecti(-95, paddle1Y - paddleHeight / 2, -95 + paddleWidth, paddle1Y + paddleHeight / 2);
        gl.glRecti(95 - paddleWidth, paddle2Y - paddleHeight / 2, 95, paddle2Y + paddleHeight / 2);

        // Desenha a bola com textura
        if (texturaBola != null) {
            texturaBola.enable(gl);
            texturaBola.bind(gl);
            GLUquadric quadric = glu.gluNewQuadric();
            glu.gluQuadricTexture(quadric, true);
            glu.gluQuadricDrawStyle(quadric, GLU.GLU_FILL);
            glu.gluQuadricNormals(quadric, GLU.GLU_SMOOTH);
            gl.glPushMatrix();
            gl.glTranslatef(ballX, ballY, 0);
            glu.gluSphere(quadric, ballSize / 2.0, 20, 20);
            gl.glPopMatrix();
            texturaBola.disable(gl);
        }

        String scoreText = "Score: \nPlayer:" + player1Score + " - Computer:" + computer;
        textRenderer.beginRendering(drawable.getSurfaceWidth(), drawable.getSurfaceHeight());
        textRenderer.setColor(Color.WHITE);
        textRenderer.draw(scoreText, 10, drawable.getSurfaceHeight() - 30);
        textRenderer.endRendering();

        update();
    }

    private void update() {
        // Lógica de atualização simplificada para brevidade
        ballX += ballDX;
        ballY += ballDY;
        // Implemente a lógica de atualização e detecção de colisão aqui
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        // Implementação do método reshape
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
        // Chamado quando o drawable é destruído
    }
}
