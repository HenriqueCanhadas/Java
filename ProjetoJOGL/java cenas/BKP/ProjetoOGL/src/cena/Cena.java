package cena;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;
/**
 *
 * @author Kakugawa
 */
public class Cena implements GLEventListener{    
    private float xMin, xMax, yMin, yMax, zMin, zMax;    
    GLU glu;
    
    @Override
    public void init(GLAutoDrawable drawable) {
        //dados iniciais da cena
        glu = new GLU();
        //Estabelece as coordenadas do SRU (Sistema de Referencia do Universo)
        xMin = yMin = zMin = -1;
        xMax = yMax = zMax = 1;        
    }

    @Override
    public void display(GLAutoDrawable drawable) {  
        //obtem o contexto Opengl
        GL2 gl = drawable.getGL().getGL2();                
        //define a cor da janela (R, G, G, alpha)
        gl.glClearColor(0, 0, 0, 1);        
        //limpa a janela com a cor especificada
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);       
        gl.glLoadIdentity(); //lê a matriz identidade
        
        /*
            desenho da cena        
        *
        */
        
        gl.glColor3f(1,1,1); //cor branca        
              
        //INICIO ATIVIDADE
        
        //QUADRADOS
        
        gl.glColor3f(0.0f, 1.0f, 0.0f); // azul
        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex2f(0.6f, 0.3f); // inferior esquerdo
        gl.glVertex2f(0.7f, 0.3f); // inferior direito
        gl.glVertex2f(0.7f, 0.4f); // superior direito
        gl.glVertex2f(0.6f, 0.4f); // superior esquerdo
        gl.glEnd();
        
        gl.glColor3f(1.0f, 1.0f, 0.0f); // azul
        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex2f(0.7f, 0.2f); // inferior esquerdo
        gl.glVertex2f(0.8f, 0.2f); // inferior direito
        gl.glVertex2f(0.8f, 0.3f); // superior direito
        gl.glVertex2f(0.7f, 0.3f); // superior esquerdo
        gl.glEnd();
        
        gl.glColor3f(1.0f, 0.2f, 0.8f); // azul
        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex2f(0.8f, 0.1f); // inferior esquerdo
        gl.glVertex2f(0.9f, 0.1f); // inferior direito
        gl.glVertex2f(0.9f, 0.2f); // superior direito
        gl.glVertex2f(0.8f, 0.2f); // superior esquerdo
        gl.glEnd();
        
        
        //LIHAS
        
        gl.glColor3f(0.0f, 1.0f, 0.0f); // cor vermelha
        gl.glLineWidth(15.0f); // largura da linha
        
        gl.glBegin(GL2.GL_LINES);
        gl.glVertex2f(0.0f, 1.0f); // ponto inicial da primeira linha
        gl.glVertex2f(0.0f, 0.0f); // ponto final da primeira linha
        
        gl.glColor3f(1.0f, 0.0f, 0.0f); // cor vermelha
        
        gl.glBegin(GL2.GL_LINES);
        gl.glVertex2f(0.0f, 0.0f); // ponto inicial da segunda linha
        gl.glVertex2f(1.0f, 0.0f); // ponto final da segunda linha
        
        gl.glColor3f(0.0f, 0.0f, 1.0f); // cor azul
        
        gl.glBegin(GL2.GL_LINES);
        gl.glVertex2f(0.0f, 0.0f); // ponto inicial da terceira linha
        gl.glVertex2f(-1.0f, -1.0f); // ponto final da terceira linha
        gl.glEnd();
        
        //PONTOS
        
        gl.glColor3f(0.0f, 0.0f, 1.0f); // cor azul
        gl.glPointSize(4.0f); // tamanho dos pontos
        
        gl.glBegin(GL2.GL_POINTS);
        gl.glVertex2f(-0.9f, 0.7f); // ponto 1
        gl.glVertex2f(-0.9f, 0.9f); // ponto 2
        gl.glVertex2f(-0.7f, 0.9f); // ponto 3
        gl.glVertex2f(-0.7f, 0.7f); // ponto 4
        gl.glEnd();
        
        //LINE_LOOP
        
        gl.glColor3f(1.0f, 0.0f, 0.0f); // cor azul
        gl.glLineWidth(3.0f); // largura da linha
        
        gl.glBegin(GL2.GL_LINE_LOOP);
        gl.glVertex2f(-0.5f, 0.3f);
        gl.glVertex2f(-0.4f, 0.2f);
        gl.glVertex2f(-0.3f, 0.3f);
        gl.glVertex2f(-0.2f, 0.2f);
        gl.glVertex2f(-0.2f, 0.5f);
        gl.glVertex2f(-0.3f, 0.4f);
        gl.glVertex2f(-0.4f, 0.5f);
        gl.glEnd();
        
        
        //POLYGON
        
        
        gl.glColor3f(1.0f, 1.0f, 0.0f); // cor azul
        
        gl.glBegin(GL2.GL_POLYGON);
        gl.glVertex2f(-0.9f, -0.4f);
        gl.glVertex2f(-0.3f, -0.5f);
        gl.glVertex2f(-0.2f, -0.1f);
        gl.glVertex2f(-0.5f, -0.2f);
        gl.glVertex2f(-0.7f, -0.1f);
        gl.glEnd();
        
        //GL_TRIANGLE_FAN 
        
        gl.glColor3f(0.0f, 0.0f, 1.0f); // cor azul
        
        gl.glBegin(GL2.GL_TRIANGLE_FAN);
        gl.glVertex2f(0.2f, -0.9f); // ponto central
        gl.glVertex2f(0.7f, -0.8f);
        gl.glVertex2f(0.8f, -0.7f);
        gl.glVertex2f(0.6f, -0.6f);
        gl.glVertex2f(0.5f, -0.4f);
        gl.glVertex2f(0.2f, -0.5f);
        gl.glEnd();
        
        gl.glFlush();       
        
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
}