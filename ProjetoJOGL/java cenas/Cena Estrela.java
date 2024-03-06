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
        
        //DESENHAR GRADE
       
        gl.glColor3f(0.8f, 0.8f, 0.8f); // Cor cinza claro
        gl.glLineWidth(1.0f); // Largura da linha
        
        // Desenhar linhas horizontais
        for (float y = -1.0f; y <= 1.0f; y += 0.1f) {
            gl.glBegin(GL2.GL_LINES);
            gl.glVertex2f(-1.0f, y);
            gl.glVertex2f(1.0f, y);
            gl.glEnd();
        }
        
        // Desenhar linhas verticais
        for (float x = -1.0f; x <= 1.0f; x += 0.1f) {
            gl.glBegin(GL2.GL_LINES);
            gl.glVertex2f(x, -1.0f);
            gl.glVertex2f(x, 1.0f);
            gl.glEnd();
        }
        
        gl.glColor3f(0.0f, 0.0f, 1.0f); // cor vermelha
        gl.glLineWidth(3.0f); // largura da linha
        
        gl.glBegin(GL2.GL_LINES);
        gl.glVertex2f(-1.0f, 0.0f); // ponto inicial da primeira linha
        gl.glVertex2f(0.0f, 0.0f); // ponto final da primeira linha
        gl.glVertex2f(0.0f, 0.0f); // ponto inicial da segunda linha
        gl.glVertex2f(0.0f, -1.0f); // ponto final da segunda linha
        
        gl.glColor3f(0.0f, 0.0f, 1.0f); // cor vermelha
        gl.glLineWidth(3.0f); // largura da linha
        
        gl.glBegin(GL2.GL_LINES);
        gl.glVertex2f(0.0f, 1.0f); // ponto inicial da primeira linha
        gl.glVertex2f(0.0f, 0.0f); // ponto final da primeira linha
        gl.glVertex2f(0.0f, 0.0f); // ponto inicial da segunda linha
        gl.glVertex2f(1.0f, 0.0f); // ponto final da segunda linha
        gl.glEnd();
        gl.glFlush();
        
        //ESTRELA VERDE
        
        gl.glColor3f(0.0f, 1.0f, 0.0f); // cor verde
        gl.glBegin(GL2.GL_POLYGON);
        gl.glVertex2f(-0.2f, 0.4f);
        gl.glVertex2f(-0.7f, 0.4f);
        gl.glVertex2f(-0.3f, 0.2f);
        gl.glVertex2f(-0.7f, -0.5f);
        gl.glVertex2f(0.0f, -0.1f);
        gl.glVertex2f(0.7f, -0.5f);
        gl.glVertex2f(0.3f, 0.2f);
        gl.glVertex2f(0.7f, 0.4f);
        gl.glVertex2f(0.2f, 0.4f);
        gl.glVertex2f(0.0f, 0.8f);
        gl.glEnd();
        
        
        gl.glColor3f(0.0f, 0.0f, 1.0f); // cor verde
        gl.glPushMatrix();
            gl.glRotatef(80, 0, 1, 0);
            gl.glBegin(GL2.GL_POLYGON);
            gl.glVertex2f(-0.2f, 0.4f);
            gl.glVertex2f(-0.7f, 0.4f);
            gl.glVertex2f(-0.3f, 0.2f);
            gl.glVertex2f(-0.7f, -0.5f);
            gl.glVertex2f(0.0f, -0.1f);
            gl.glVertex2f(0.7f, -0.5f);
            gl.glVertex2f(0.3f, 0.2f);
            gl.glVertex2f(0.7f, 0.4f);
            gl.glVertex2f(0.2f, 0.4f);
            gl.glVertex2f(0.0f, 0.8f);
        gl.glEnd();
        gl.glPopMatrix();
        
        
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
