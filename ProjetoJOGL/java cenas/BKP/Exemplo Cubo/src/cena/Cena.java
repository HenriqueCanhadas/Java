package cena;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.util.awt.TextRenderer;
import java.awt.Color;
import java.awt.Font;

/**
 *
 * @author Kakugawa
 */

public class Cena implements GLEventListener{    
    private float xMin, xMax, yMin, yMax, zMin, zMax;    
    GLU glu;
    private TextRenderer textRenderer;

    public float horizontal, vertical;
    
    public float angulo;
    
    @Override
    public void init(GLAutoDrawable drawable) {
        //dados iniciais da cena
        glu = new GLU();
        GL2 gl = drawable.getGL().getGL2();
        
        //Estabelece as coordenadas do SRU (Sistema de Referencia do Universo)
        xMin = yMin = zMin = -100;
        xMax = yMax = zMax = 100;
        
        //Habilita o buffer de profundidade
        gl.glEnable(GL2.GL_DEPTH_TEST);
        
        textRenderer = new TextRenderer(new Font("Arial", Font.PLAIN, 38));
               
        horizontal = vertical = 0;
    }

    @Override
    public void display(GLAutoDrawable drawable) {  
        //obtem o contexto Opengl
        GL2 gl = drawable.getGL().getGL2();
        GLUT glut = new GLUT(); // objeto para desenho 3D
        
        //define a cor da janela (R, G, B, alpha)
        gl.glClearColor(1, 1, 1, 1);        
        //limpa a janela com a cor especificada
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);      
        gl.glLoadIdentity(); //lê a matriz identidade
            
        /*
            desenho da cena        
        *
        */
        
        desenhaTexto(gl, 240, 500, Color.BLUE, "CUBO!!");
        
        //denha um cubo
        gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_LINE);
        gl.glColor3f(0,0,1);
        gl.glRotated(horizontal, 0, 1,0);
        gl.glRotated(vertical, 1, 0,0);
        glut.glutSolidCube(50);
        
        gl.glFlush();      
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {    
        //obtem o contexto grafico Opengl
        GL2 gl = drawable.getGL().getGL2();  
        
        //evita a divisão por zero
        if(height == 0) height = 1;
        //calcula a proporção da janela (aspect ratio) da nova janela
        float aspect = (float) width / height;
        
        //seta o viewport para abranger a janela inteira
        gl.glViewport(0, 0, width, height);
                
        //ativa a matriz de projeção
        gl.glMatrixMode(GL2.GL_PROJECTION);      
        gl.glLoadIdentity(); //lê a matriz identidade
        
        //Projeção ortogonal
        //true:   aspect >= 1 configura a altura de -1 para 1 : com largura maior
        //false:  aspect < 1 configura a largura de -1 para 1 : com altura maior
        if(width >= height)            
            gl.glOrtho(xMin * aspect, xMax * aspect, yMin, yMax, zMin, zMax);
        else        
            gl.glOrtho(xMin, xMax, yMin / aspect, yMax / aspect, zMin, zMax);
                
        //ativa a matriz de modelagem
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity(); //lê a matriz identidade
        System.out.println("Reshape: " + width + ", " + height);
    }    
       
    @Override
    public void dispose(GLAutoDrawable drawable) {}
    
    public void desenhaTexto(GL2 gl, int xPosicao, int yPosicao, Color cor, String frase) {
	gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_FILL);
        //Retorna a largura e altura da janela
        textRenderer.beginRendering(Renderer.screenWidth, Renderer.screenHeight);       
        textRenderer.setColor(cor);
        textRenderer.draw(frase, xPosicao, yPosicao);
        textRenderer.endRendering();
    }

}
