// gcc robot.c -lglut -lGL -lGLU -lm -o snowman && ./snowman
// /home/joao/Downloads/boneco.c

#include <GL/glut.h>
#define AUMENTO 0.25f

// Rotation
static GLfloat yRot = 0.0f;

// Change viewing volume and viewport.  Called when window is resized  
void ChangeSize(int w, int h)  
    {  
    GLfloat fAspect;  
  
    // Prevent a divide by zero  
    if(h == 0)  
        h = 1;  
  
    // Set Viewport to window dimensions  
    glViewport(0, 0, w, h);  
  
    fAspect = (GLfloat)w/(GLfloat)h;  
  
    // Reset coordinate system  
    glMatrixMode(GL_PROJECTION);  
    glLoadIdentity();  
  
    // Produce the perspective projection  
    gluPerspective(35.0f, fAspect, 1.0, 40.0);  
  
    glMatrixMode(GL_MODELVIEW);  
    glLoadIdentity();  
    }  
  
  
// This function does any needed initialization on the rendering context.  Here it sets up and initializes the lighting for the scene.  
void SetupRC(){  

    // Light values and coordinates  
    GLfloat  whiteLight[] = { 0.05f, 0.05f, 0.05f, 1.0f };  
    GLfloat  sourceLight[] = { 0.25f, 0.25f, 0.25f, 1.0f };  
    GLfloat  lightPos[] = { -10.f, 5.0f, 5.0f, 1.0f };  
  
    glEnable(GL_DEPTH_TEST);    // Hidden surface removal  
    glFrontFace(GL_CCW);        // Counter clock-wise polygons face out  
    glEnable(GL_CULL_FACE);     // Do not calculate inside  
  
    // Enable lighting  
    glEnable(GL_LIGHTING);  
  
    // Setup and enable light 0  
    glLightModelfv(GL_LIGHT_MODEL_AMBIENT,whiteLight);  
    glLightfv(GL_LIGHT0,GL_AMBIENT,sourceLight);  
    glLightfv(GL_LIGHT0,GL_DIFFUSE,sourceLight);  
    glLightfv(GL_LIGHT0,GL_POSITION,lightPos);  
    glEnable(GL_LIGHT0);  
  
    // Enable color tracking  
    glEnable(GL_COLOR_MATERIAL);  
      
    // Set Material properties to follow glColor values  
    glColorMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE);  
  
    // Black blue background  
    glClearColor(0.5f, 0.5f, 1.0f, 1.0f);  

}  
  
// Respond to arrow keys (rotate snowman)
void SpecialKeys(int key, int x, int y){  

    if(key == GLUT_KEY_LEFT)  
        yRot -= 5.0f;  
  
    if(key == GLUT_KEY_RIGHT)  
        yRot += 5.0f;  
                  
    yRot = (GLfloat)((const int)yRot % 360);  
  
    // Refresh the Window  
    glutPostRedisplay();  
}
  
// Called to draw scene  
void RenderScene(void){  

    GLUquadricObj *pObj;    // Quadric Object  
      
    // Clear the window with current clearing color  
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);  
  
    // Save the matrix state and do the rotations  
    glPushMatrix();

	// Move object back and do in place rotation  
    //local no espaco,  visao
	glTranslatef(0.0f, 0.45f, -10.0f);  
	glRotatef(yRot, 0.0f, 1.0f, 0.0f);  

	// Draw something  
	pObj = gluNewQuadric();  
	gluQuadricNormals(pObj, GLU_SMOOTH);   

    //cabeca
    glColor3f(1.0f, 1.0f, 1.0f);
    glPushMatrix();
        glTranslatef(0.0f, 1.45f, 0.0f);
        glScalef(1.0f, 1.0f, 1.0f);
        glutSolidCube(1.0f);
    glPopMatrix();

    //painel rosto
    glColor3f(0.0f, 0.0f, 0.0f);
    glPushMatrix();
        glTranslatef(0.0f, 1.60f, 0.5f);
        glScalef(0.75f, 0.3f, 0.1f);
        glutSolidCube(1.0f);
    glPopMatrix();

    //olho esquerdo
    glColor3f(1.0f, 0.0f, 0.0f);
    glPushMatrix();
        glTranslatef(-0.2f, 1.6f, 0.5f);
        glScalef(1.0f, 1.0f, 1.0f);
        glutSolidCube(0.15f);
    glPopMatrix();

    //olho direito
    glColor3f(0.0f, 0.0f, 1.0f);
    glPushMatrix();
        glTranslatef(0.2f, 1.6f, 0.5f);
        glScalef(1.0f, 1.0f, 1.0f);
        glutSolidCube(0.15f);
    glPopMatrix();

    //pescoco 
    glColor3f(1.0f, 1.0f, 1.0f);
    glPushMatrix();
        glTranslatef(0.0f, 0.95f, 0.0f);
        glScalef(0.3f, 0.4f, 0.3f);
        glutSolidCube(1.0f);
    glPopMatrix();

    //suporte braco direito
    glColor3f(1.0f, 1.0f, 1.0f);
    glPushMatrix();
        glTranslatef(1.05f, 0.50f, 0.0f);
        glScalef(0.6f, 0.3f, 0.6f);
        glutSolidCube(1.0f);
    glPopMatrix();

    //braco direito
    glColor3f(1.0f, 1.0f, 1.0f);
    glPushMatrix();
        glTranslatef(1.20f, -0.15f, 0.0f);
        glScalef(0.3f, 1.0f, 0.45f);
        glutSolidCube(1.0f);
    glPopMatrix();    

    //suporte braco esquerdo
    glColor3f(1.0f, 1.0f, 1.0f);
    glPushMatrix();
        glTranslatef(-1.05f, 0.50f, 0.0f);
        glScalef(0.6f, 0.3f, 0.6f);
        glutSolidCube(1.0f);
    glPopMatrix();

    //braco esquerdo
    glColor3f(1.0f, 1.0f, 1.0f);
    glPushMatrix();
        glTranslatef(-1.20f, -0.15f, 0.0f);
        glScalef(0.3f, 1.0f, 0.45f);
        glutSolidCube(1.0f);
    glPopMatrix(); 

	//tronco
    glColor3f(1.0f, 1.0f, 1.0f);
    glPushMatrix();
        glTranslatef(0.0f, 0.0f, 0.0f);
        glScalef(1.6f, 1.5f, 1.5f);
        glutSolidCube(1.0f);
    glPopMatrix();

    //conexao parte inferior e superior
    glColor3f(1.0f, 1.0f, 1.0f);
    glPushMatrix();
        glTranslatef(0.0f, -0.8625f, 0.0f);
        glScalef(0.6f, 0.5f, 0.4f);
        glutSolidCube(1.0f);
    glPopMatrix();

    //suporte pernas
    glColor3f(1.0f, 1.0f, 1.0f);
    glPushMatrix();
        glTranslatef(0.0f, -1.2375f, 0.0f);
        glScalef(0.9f, 0.375f, 0.85f);
        glutSolidCube(1.0f);
    glPopMatrix();

    //perna esquerda superior
    glColor3f(1.0f, 1.0f, 1.0f);
    glPushMatrix();
        glTranslatef(-0.65f, -1.7375f, 0.0f + AUMENTO);
        glRotatef(-30.0f, 1.0f, 0.0f, 0.0f);
        glScalef(0.4f, 1.0f, 0.45f);
        glutSolidCube(1.0f);
    glPopMatrix();

    //joelho esquerdo
    glColor3f(1.0f, 1.0f, 1.0f);
    glPushMatrix();
        glTranslatef(-0.65f, -2.15f, 0.3f + AUMENTO);
        glScalef(2.0f, 0.5f, 0.75f);
        glutSolidCube(0.2f);
    glPopMatrix();
    /*
    glPushMatrix(); // save transform matrix state
		glTranslatef(-0.65f, -2.0f, 0.15f);
		gluSphere(pObj, 0.3f, 26, 13);
	glPopMatrix(); 
    */

    //perna esquerda inferior
    glColor3f(1.0f, 1.0f, 1.0f);
    glPushMatrix();
        glTranslatef(-0.65f, -2.5f, -0.1f + AUMENTO);
        glRotatef(30.0f, 1.0f, 0.0f, 0.0f);
        glScalef(0.4f, 1.0f, 0.45f);
        glutSolidCube(1.0f);
    glPopMatrix();

    //pe esquerdo
    glColor3f(1.0f, 1.0f, 1.0f);
    glPushMatrix();
        glTranslatef(-0.65f, -2.9f, 0.05f);
        glScalef(0.5f, 0.28f, 0.75f);
        glutSolidCube(1.0f);
    glPopMatrix();

    //perna direita superior
    glColor3f(1.0f, 1.0f, 1.0f);
    glPushMatrix();
        glTranslatef(0.65f, -1.7375f, 0.0f + AUMENTO);
        glRotatef(-30.0f, 1.0f, 0.0f, 0.0f);
        glScalef(0.4f, 1.0f, 0.45f);
        glutSolidCube(1.0f);
    glPopMatrix();

    //joelho direito
    glColor3f(1.0f, 1.0f, 1.0f);
    glPushMatrix();
        glTranslatef(0.65f, -2.15f, 0.3f + AUMENTO);
        glScalef(2.0f, 0.5f, 0.75f);
        glutSolidCube(0.2f);
    glPopMatrix();

    //perna direita inferior
    glColor3f(1.0f, 1.0f, 1.0f);
    glPushMatrix();
        glTranslatef(0.65f, -2.5f, -0.1f + AUMENTO);
        glRotatef(30.0f, 1.0f, 0.0f, 0.0f);
        glScalef(0.4f, 1.0f, 0.45f);
        glutSolidCube(1.0f);
    glPopMatrix();

    //pe direito
    glColor3f(1.0f, 1.0f, 1.0f);
    glPushMatrix();
        glTranslatef(0.65f, -2.9f, 0.05f);
        glScalef(0.5f, 0.28f, 0.75f);
        glutSolidCube(1.0f);
    glPopMatrix();

    // Restore the matrix state  
    glPopMatrix();  
  
    // Buffer swap  
    glutSwapBuffers();  

}    

int main(int argc, char *argv[]){

    glutInit(&argc, argv);  
    glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB | GLUT_DEPTH);  
    glutInitWindowSize(800, 600);  
    glutCreateWindow("Robozao");  
    glutReshapeFunc(ChangeSize);  
    glutSpecialFunc(SpecialKeys);  
    glutDisplayFunc(RenderScene);  
    SetupRC();  
    glutMainLoop();  
      
    return 0; 
}

