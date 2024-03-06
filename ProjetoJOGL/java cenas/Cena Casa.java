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
        gl.glClearColor(1, 1, 1,0);        
        //limpa a janela com a cor especificada
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);       
        gl.glLoadIdentity(); //lê a matriz identidade
        
        /*
            desenho da cena        
        *
        */           
        //INICIO DA ATIVIDADE
        
        //DESENHANDO A CASA
            

            //CEU
            gl.glColor3f(0.0f, 0.0f, 0.5f);
            gl.glBegin(GL2.GL_QUADS);
            gl.glVertex2f(-1.0f, 0.0f); // Vértice inferior esquerdo
            gl.glVertex2f(1.0f, 0.0f); // Vértice inferior direito
            gl.glVertex2f(1.0f, 1.0f); // Vértice superior direito
            gl.glVertex2f(-1.0f, 1.0f); // Vértice superior esquerdo
            gl.glEnd();
            

            //GRAMA
            gl.glColor3f(0.0f, 0.5f, 0.0f);
            gl.glBegin(GL2.GL_QUADS);
            gl.glVertex2f(-1.0f, -1.0f); // Vértice inferior esquerdo
            gl.glVertex2f(1.0f, -1.0f); // Vértice inferior direito
            gl.glVertex2f(1.0f, 0.0f); // Vértice superior direito
            gl.glVertex2f(-1.0f, 0.0f); // Vértice superior esquerdo
            gl.glEnd();
     

            //PAREDE
            gl.glColor3f(1.0f, 0.0f, 0.0f);
            gl.glBegin(GL2.GL_QUADS);
            gl.glVertex2f(-0.5f, -0.5f); // Vértice inferior esquerdo
            gl.glVertex2f(0.5f, -0.5f); // Vértice inferior direito
            gl.glVertex2f(0.5f, 0.4f); // Vértice superior direito
            gl.glVertex2f(-0.5f, 0.4f); // Vértice superior esquerdo
            gl.glEnd();
            
            
            //TELHADO
            gl.glColor3f(0, 0, 1); // Definindo a cor azul
            gl.glBegin(GL2.GL_TRIANGLES);
            gl.glVertex2f(-0.5f, 0.4f);
            gl.glVertex2f(0.5f, 0.4f);
            gl.glVertex2f(0.0f, 0.9f);
            gl.glEnd();
            

            //JANELA
            gl.glColor3f(0.0f, 0.0f,0.0f); // cor azul
            gl.glLineWidth(3.0f); // largura da linha
            gl.glBegin(GL2.GL_LINE_LOOP);
            gl.glVertex2f(0.0f, 0.0f);
            gl.glVertex2f(0.0f, 0.2f);
            gl.glVertex2f(0.4f, 0.2f);
            gl.glVertex2f(0.4f, 0.2f);
            gl.glVertex2f(0.4f, 0.0f);
            gl.glEnd();
            gl.glColor3f(0.0f, 0.0f,0.0f); // cor azul
            gl.glLineWidth(3.0f); // largura da linha
            gl.glBegin(GL2.GL_LINES);
            gl.glVertex2f(0.0f, 0.1f);
            gl.glVertex2f(0.4f, 0.1f);
            gl.glVertex2f(0.2f, 0.0f);
            gl.glVertex2f(0.2f, 0.2f);
            gl.glEnd();
            
            
            //PORTA
            gl.glColor3f(0.0f, 1.0f, 0.0f); // Definindo a cor verde
            gl.glBegin(GL2.GL_QUADS);
            gl.glVertex2f(-0.4f, -0.5f); // Vértice inferior esquerdo
            gl.glVertex2f(-0.1f, -0.5f); // Vértice inferior direito
            gl.glVertex2f(-0.1f, 0.2f); // Vértice superior direito
            gl.glVertex2f(-0.4f, 0.2f); // Vértice superior esquerdo
            gl.glEnd();      
            gl.glColor3f(0.0f, 0.0f, 0.0f); // cor azul
            gl.glPointSize(5.0f); // tamanho dos pontos
            gl.glBegin(GL2.GL_POINTS);
            gl.glVertex2f(-0.3f, -0.1f); // ponto 1
            gl.glEnd();
            
            
            //ENTRADA
            gl.glColor3f(1.0f, 1.0f, 0.0f); // Amarelo
            gl.glBegin(GL2.GL_QUADS);
            gl.glVertex2f(-0.7f, -1.0f); // Vértice inferior esquerdo
            gl.glVertex2f(0.2f, -1.0f); // Vértice inferior direito
            gl.glVertex2f(-0.1f, -0.5f); // Vértice superior direito
            gl.glVertex2f(-0.4f, -0.5f); // Vértice superior esquerdo
            gl.glEnd();

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
