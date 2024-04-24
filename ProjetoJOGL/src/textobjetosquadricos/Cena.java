package textobjetosquadricos;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import com.jogamp.opengl.util.gl2.GLUT; //primitivas 3D
import textura.Textura;

/**
 *
 * @author Kakugawa
 */
public class Cena implements GLEventListener, KeyListener {
    private float angulo = 0;
    private GL2 gl;
    private GLU glu;
    private GLUT glut;
    private int tonalizacao = GL2.GL_SMOOTH;
    float luzR = 0.2f, luzG = 0.2f, luzB = 0.2f;
    private float incAngulo = 0;
    
    // Atributos    
    private float limite;
    float xMin, xMax, yMin, yMax, zMin, zMax;
    char tipo = 'e';

    //Referencia para classe Textura
    private Textura textura = null;
    //Quantidade de Texturas a ser carregada
    private int totalTextura = 1;


    //Constantes para identificar as imagens
    //public static final String FACE1 = "imagens/mapa.jpg";
    //public static final String FACE1 = "imagens/arvore.png";
    //public static final String FACE1 = "imagens/metal.gif";
    public static final String FACE1 = "imagens/download-removebg-preview.png";
    
    private int filtro = GL2.GL_LINEAR;
    private int wrap = GL2.GL_REPEAT;
    private int modo = GL2.GL_MODULATE;


    @Override
    public void init(GLAutoDrawable drawable) {
        //dados iniciais da cena
        GL2 gl = drawable.getGL().getGL2();
        
        angulo = 0;
        incAngulo = 0;
        
        //Cria uma instancia da Classe Textura indicando a quantidade de texturas
        textura = new Textura(totalTextura);
                
        //habilita o buffer de profundidade
        gl.glEnable(GL2.GL_DEPTH_TEST);
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        //obtem o contexto Opengl
        gl = drawable.getGL().getGL2();
        glut = new GLUT(); //objeto da biblioteca glut

        //define a cor da janela (R, G, G, alpha)
        gl.glClearColor(1, 1, 1, 0);
        //limpa a janela com a cor especificada
        //limpa o buffer de profundidade
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity(); //lê a matriz identidade

        /*
            desenho da cena        
        *
         */
        // criar a cena aqui....
         iluminacaoAmbiente();
        ligaLuz();

        gl.glRotatef(angulo, 1, 1, 1);
                
        //Aplica transformações na Textura
        
        gl.glMatrixMode(GL2.GL_TEXTURE);
            gl.glLoadIdentity();
            gl.glRotatef(180, 1, 0, 0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);

        //não é geração de textura automática
        textura.setAutomatica(false);
        
        //habilita os filtros
        textura.setFiltro(filtro);
        textura.setModo(GL2.GL_MODULATE);
        textura.setWrap(wrap);
        
        textura.gerarTextura(gl, FACE1, 0);
             
        gl.glPushMatrix();              
            if(tipo == 'e'){
            	solidSphere(60, 30, 30);
            }
            if(tipo == 'c'){
            	solidCylinder(30, 30, 80, 30, 30);
            }
            if(tipo == 'd'){
            	solidDisk(10, 50, 10, 5);
            }
            if(tipo == 'p'){
            	solidPartialDisk(0, 80, 5, 3, 0.0f, 90.0f);
            }
            
        gl.glPopMatrix();               
        
        //desabilita a textura indicando o índice
        textura.desabilitarTextura(gl, 0);
        
        desligaluz();
        
        //Rotacao do Cubo
        rotacionarCubo();
        
        gl.glPopMatrix();
        
     
        
        gl.glFlush();
    }

    public void desenhaTexto(GL2 gl, int x, int y, String frase) {
        glut = new GLUT(); //objeto da biblioteca glut
        gl.glRasterPos2f(x, y);
        glut.glutBitmapString(GLUT.BITMAP_HELVETICA_12, frase);
    }
    
    private void solidSphere(int raio, int stacks, int columns) 
    {   
        glu = new GLU();   

        GLUquadric quadObj = glu.gluNewQuadric();   
        glu.gluQuadricDrawStyle(quadObj, GLU.GLU_FILL);   
        glu.gluQuadricNormals(quadObj, GLU.GLU_SMOOTH);   
        glu.gluQuadricTexture(quadObj, true); //Habilita textura  
        glu.gluQuadricNormals(quadObj, GLU.GLU_SMOOTH);
        glu.gluSphere(quadObj, raio, stacks, columns);   
    }

    private void solidCylinder(float base, float topo, float altura, int slices, int stacks) 
    {   
        glu = new GLU();   

        GLUquadric quadObj = glu.gluNewQuadric();   
        glu.gluQuadricDrawStyle(quadObj, GLU.GLU_FILL);   
        glu.gluQuadricNormals(quadObj, GLU.GLU_SMOOTH);   
        glu.gluQuadricTexture(quadObj, true); //Habilita textura  
        glu.gluQuadricNormals(quadObj, GLU.GLU_SMOOTH);
        glu.gluCylinder(quadObj, base, topo, altura, slices, stacks);
    }
    
    private void solidDisk(float raioInterno, float raioExterno, int slices, int loops) 
    {   
        glu = new GLU();   

        GLUquadric quadObj = glu.gluNewQuadric();   
        glu.gluQuadricDrawStyle(quadObj, GLU.GLU_FILL);   
        glu.gluQuadricNormals(quadObj, GLU.GLU_SMOOTH);   
        glu.gluQuadricTexture(quadObj, true); //Habilita textura   
        glu.gluQuadricNormals(quadObj, GLU.GLU_SMOOTH);
        glu.gluDisk(quadObj, raioInterno, raioExterno, slices, loops);
    }
    
    private void solidPartialDisk(int raioInterno, int raioExterno, 
            int slices, int loops, float startAngle, float sweepAngle) 
    {   
        glu = new GLU();   

        GLUquadric quadObj = glu.gluNewQuadric();   
        glu.gluQuadricDrawStyle(quadObj, GLU.GLU_FILL);   
        glu.gluQuadricNormals(quadObj, GLU.GLU_SMOOTH);   
        glu.gluQuadricTexture(quadObj, true); //Habilita textura  
        glu.gluQuadricNormals(quadObj, GLU.GLU_SMOOTH);
        glu.gluPartialDisk(quadObj, raioInterno, raioExterno, slices, loops, startAngle, sweepAngle);
    }


    public void iluminacaoAmbiente() {
        float luzAmbiente[] = {0.2f, 0.2f, 0.2f, 1.0f}; //cor
        float posicaoLuz[] = {0.0f, 0.0f, 100.0f, 1.0f}; //pontual

        // define parametros de luz de número 0 (zero)
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, luzAmbiente, 0);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, posicaoLuz, 0);
    }

    public void ligaLuz() {
        // habilita a definição da cor do material a partir da cor corrente
        gl.glEnable(GL2.GL_COLOR_MATERIAL);

        // habilita o uso da iluminação na cena
        gl.glEnable(GL2.GL_LIGHTING);
        // habilita a luz de número 0
        gl.glEnable(GL2.GL_LIGHT0);
        //Especifica o Modelo de tonalizacao a ser utilizado 
        //GL_FLAT -> modelo de tonalizacao flat 
        //GL_SMOOTH -> modelo de tonalização GOURAUD (default)        
        gl.glShadeModel(tonalizacao);
    }

    public void desligaluz() {
        //desabilita o ponto de luz
        gl.glDisable(GL2.GL_LIGHT0);
        //desliga a iluminacao
        gl.glDisable(GL2.GL_LIGHTING);
    }

    // Animacao de rotacao do Cubo
    private void rotacionarCubo() {
        angulo = angulo + incAngulo;        
        if (angulo > 360f) {
            angulo = angulo - 360;
        }
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        // Obtem o contexto grafico OpenGL
        GL2 gl = drawable.getGL().getGL2();
    
        // Evita a divisão por zero
        if (height == 0) height = 1;
    
        // Calcula a proporção da janela (aspect ratio)
        float aspect = (float) width / height;
    
        // Ativa a matriz de projeção
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity(); // Carrega a matriz identidade
    
        // Projeção ortográfica ajustada ao aspecto da tela
        if (width >= height) {
            // janela mais larga que alta
            gl.glOrtho(-100.0 * aspect, 100.0 * aspect, -100.0, 100.0, -100.0, 100.0);
        } else {
            // janela mais alta que larga
            gl.glOrtho(-100.0, 100.0, -100.0 / aspect, 100.0 / aspect, -100.0, 100.0);
        }
    
        // Ativa a matriz de modelagem
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity(); // Carrega a matriz identidade
    
        System.out.println("Reshape: " + width + ", " + height);
    }
    

    @Override
    public void dispose(GLAutoDrawable drawable) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                break;
            //........
        }
        switch (e.getKeyChar()) {
            case KeyEvent.VK_ESCAPE:  /*  Escape Key */
                System.exit(0);
                break;

            case '1': //inicia animacao
                incAngulo = -3.0f;
                break;

            case '2': //para a animacao
                incAngulo = 0f;
                break;
                
            case 'e': 
                tipo = 'e';
                break;
            case 'p': 
                tipo = 'p';
                break;
            case 'd': 
                tipo = 'd';
                break;
            case 'c': 
                tipo = 'c';
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
