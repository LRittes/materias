// gcc castle.c -lglut -lGL -lGLU -lm -o snowman && ./snowman
// /home/joao/Downloads/boneco.c

#include <GL/glut.h>
#define AUMENTO 0.5f

// Rotation
static GLfloat yRot = 0.0f;

// Change viewing volume and viewport.  Called when window is resized
void ChangeSize(int w, int h)
{
    GLfloat fAspect;

    // Prevent a divide by zero
    if (h == 0)
        h = 1;

    // Set Viewport to window dimensions
    glViewport(0, 0, w, h);

    fAspect = (GLfloat)w / (GLfloat)h;

    // Reset coordinate system
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();

    // Produce the perspective projection
    gluPerspective(35.0f, fAspect, 1.0, 40.0);

    glMatrixMode(GL_MODELVIEW);
    glLoadIdentity();
}

// This function does any needed initialization on the rendering context.  Here it sets up and initializes the lighting for the scene.
void SetupRC()
{

    // Light values and coordinates
    GLfloat whiteLight[] = {0.05f, 0.05f, 0.05f, 1.0f};
    GLfloat sourceLight[] = {0.25f, 0.25f, 0.25f, 1.0f};
    GLfloat lightPos[] = {-10.f, 5.0f, 5.0f, 1.0f};

    glEnable(GL_DEPTH_TEST); // Hidden surface removal
    glFrontFace(GL_CCW);     // Counter clock-wise polygons face out
    glEnable(GL_CULL_FACE);  // Do not calculate inside

    // Enable lighting
    glEnable(GL_LIGHTING);

    // Setup and enable light 0
    glLightModelfv(GL_LIGHT_MODEL_AMBIENT, whiteLight);
    glLightfv(GL_LIGHT0, GL_AMBIENT, sourceLight);
    glLightfv(GL_LIGHT0, GL_DIFFUSE, sourceLight);
    glLightfv(GL_LIGHT0, GL_POSITION, lightPos);
    glEnable(GL_LIGHT0);

    // Enable color tracking
    glEnable(GL_COLOR_MATERIAL);

    // Set Material properties to follow glColor values
    glColorMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE);

    // Black blue background
    glClearColor(0.5f, 0.5f, 1.0f, 1.0f);
}

// Respond to arrow keys (rotate snowman)
void SpecialKeys(int key, int x, int y)
{

    if (key == GLUT_KEY_LEFT)
        yRot -= 5.0f;

    if (key == GLUT_KEY_RIGHT)
        yRot += 5.0f;

    yRot = (GLfloat)((const int)yRot % 360);

    // Refresh the Window
    glutPostRedisplay();
}

// Called to draw scene
void RenderScene(void)
{

    GLUquadricObj *pObj; // Quadric Object

    // Clear the window with current clearing color
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

    // Save the matrix state and do the rotations
    glPushMatrix();

    // Move object back and do in place rotation
    // localizacao e rotacao
    glTranslatef(0.0f, -2.0f, -15.0f);
    glRotatef(yRot, 1.0f, 0.0f, 0.0f);

    // Draw something
    pObj = gluNewQuadric();
    gluQuadricNormals(pObj, GLU_SMOOTH);

    // white
    glColor3f(1.0f, 1.0f, 1.0f);

    // Main Body
    //	gluSphere();  // Bottom

    // Mid section
    //	glPushMatrix();
    //		glTranslatef();
    //		gluSphere();
    //	glPopMatrix();

    // chao
    glColor3f(0.0f, 1.0f, 0.0f);
    glPushMatrix();
    glTranslatef(0.0f, -0.05f, -3.0f);
    glScalef(1.5f, 0.0f, 1.0f);
    glutSolidCube(12.0f);
    glPopMatrix();

    // chao castelo
    glColor3f(0.5f, 0.5f, 0.5f);
    glPushMatrix();
    glTranslatef(0.0f, 0.05f, -3.0f);
    glScalef(1.0f, 0.0f, 1.0f);
    glutSolidCube(6.0f);
    glPopMatrix();

    // torre 1
    glColor3f(0.5f, 0.5f, 0.5f);
    glPushMatrix();
    glTranslatef(-3.0f, 0.0f, 0.0f);
    glRotatef(-90.0f, 1.0f, 0.0f, 0.0f); // Rotacionar o cilindro para apontar para cima
    gluCylinder(pObj, 1.0f, 1.0f, 3.0f + AUMENTO, 26, 13);
    glPopMatrix();

    // cabeca torre 1
    glColor3f(0.5f, 0.5f, 0.5f);
    glPushMatrix();
    glTranslatef(-3.0f, 2.7f + AUMENTO, 0.0f);
    glRotatef(-90.0f, 1.0f, 0.0f, 0.0f); // Rotacionar o cilindro para apontar para cima
    gluCylinder(pObj, 1.3f, 1.3f, 0.8f, 26, 13);
    glPopMatrix();

    // telhado 1
    glColor3f(0.65f, 0.16f, 0.16f);
    glPushMatrix();
    glTranslatef(-3.0f, 3.45f + AUMENTO, 0.0f);
    glRotatef(-90.0f, 1.0f, 0.0f, 0.0f); // Rotacionar o cilindro para apontar para cima
    gluCylinder(pObj, 1.4f, 0.0f, 1.0f, 26, 13);
    glPopMatrix();

    // torre 2
    glColor3f(0.5f, 0.5f, 0.5f);
    glPushMatrix();
    glTranslatef(3.0f, 0.0f, 0.0f);
    glRotatef(-90.0f, 1.0f, 0.0f, 0.0f); // Rotacionar o cilindro para apontar para cima
    gluCylinder(pObj, 1.0f, 1.0f, 3.0f + AUMENTO, 26, 13);
    glPopMatrix();

    // cabeca torre 2
    glColor3f(0.5f, 0.5f, 0.5f);
    glPushMatrix();
    glTranslatef(3.0f, 2.7f + AUMENTO, 0.0f);
    glRotatef(-90.0f, 1.0f, 0.0f, 0.0f); // Rotacionar o cilindro para apontar para cima
    gluCylinder(pObj, 1.3f, 1.3f, 0.8f, 26, 13);
    glPopMatrix();

    // telhado 2
    glColor3f(0.65f, 0.16f, 0.16f);
    glPushMatrix();
    glTranslatef(3.0f, 3.45f + AUMENTO, 0.0f);
    glRotatef(-90.0f, 1.0f, 0.0f, 0.0f); // Rotacionar o cilindro para apontar para cima
    gluCylinder(pObj, 1.4f, 0.0f, 1.0f, 26, 13);
    glPopMatrix();

    // torre 3
    glColor3f(0.5f, 0.5f, 0.5f);
    glPushMatrix();
    glTranslatef(-3.0f, 0.0f, -6.0f);
    glRotatef(-90.0f, 1.0f, 0.0f, 0.0f); // Rotacionar o cilindro para apontar para cima
    gluCylinder(pObj, 1.0f, 1.0f, 3.0f + AUMENTO, 26, 13);
    glPopMatrix();

    // cabeca torre 3
    glColor3f(0.5f, 0.5f, 0.5f);
    glPushMatrix();
    glTranslatef(-3.0f, 2.7f + AUMENTO, -6.0f);
    glRotatef(-90.0f, 1.0f, 0.0f, 0.0f); // Rotacionar o cilindro para apontar para cima
    gluCylinder(pObj, 1.3f, 1.3f, 0.8f, 26, 13);
    glPopMatrix();

    // telhado 3
    glColor3f(0.65f, 0.16f, 0.16f);
    glPushMatrix();
    glTranslatef(-3.0f, 3.45f + AUMENTO, -6.0f);
    glRotatef(-90.0f, 1.0f, 0.0f, 0.0f); // Rotacionar o cilindro para apontar para cima
    gluCylinder(pObj, 1.4f, 0.0f, 1.0f, 26, 13);
    glPopMatrix();

    // torre 4
    glColor3f(0.5f, 0.5f, 0.5f);
    glPushMatrix();
    glTranslatef(3.0f, 0.0f, -6.0f);
    glRotatef(-90.0f, 1.0f, 0.0f, 0.0f); // Rotacionar o cilindro para apontar para cima
    gluCylinder(pObj, 1.0f, 1.0f, 3.0f + AUMENTO, 26, 13);
    glPopMatrix();

    // cabeca torre 4
    glColor3f(0.5f, 0.5f, 0.5f);
    glPushMatrix();
    glTranslatef(3.0f, 2.7f + AUMENTO, -6.0f);
    glRotatef(-90.0f, 1.0f, 0.0f, 0.0f); // Rotacionar o cilindro para apontar para cima
    gluCylinder(pObj, 1.3f, 1.3f, 0.8f, 26, 13);
    glPopMatrix();

    // telhado 4
    glColor3f(0.65f, 0.16f, 0.16f);
    glPushMatrix();
    glTranslatef(3.0f, 3.45f + AUMENTO, -6.0f);
    glRotatef(-90.0f, 1.0f, 0.0f, 0.0f); // Rotacionar o cilindro para apontar para cima
    gluCylinder(pObj, 1.4f, 0.0f, 1.0f, 26, 13);
    glPopMatrix();

    // parede entre 1 e 2
    glColor3f(0.5f, 0.5f, 0.5f);
    glPushMatrix();
    glTranslatef(0.0f, 1.2f, 0.0f);
    glRotatef(0.0f, 0.0f, 0.0f, 0.0f); // Rotacionar
    glScalef(3.35f, 2.0f, 0.3f);
    glutSolidCube(1.2f);
    glPopMatrix();

    // blocos parede entre 1 e 2
    for (int i = 0; i < 6; i++)
    {
        glPushMatrix();
        glTranslatef(-1.82f + i * 0.728f, 2.58f, 0.0f);
        glutSolidCube(0.36f);
        glPopMatrix();
    }

    // parede entre 3 e 4
    glPushMatrix();
    glTranslatef(0.0f, 1.2f, -6.0f);
    glRotatef(0.0f, 0.0f, 0.0f, 0.0f); // Rotacionar
    glScalef(3.35f, 2.0f, 0.3f);
    glutSolidCube(1.2f);
    glPopMatrix();

    // blocos parede entre 3 e 4
    for (int i = 0; i < 6; i++)
    {
        glPushMatrix();
        glTranslatef(-1.82f + i * 0.728f, 2.58f, -6.0f);
        glutSolidCube(0.36f);
        glPopMatrix();
    }

    // parede entre 1 e 3
    glPushMatrix();
    glTranslatef(-3.0f, 1.2f, -3.0f);
    glRotatef(90.0f, 0.0f, 1.0f, 0.0f); // Rotacionar
    glScalef(3.35f, 2.0f, 0.3f);
    glutSolidCube(1.2f);
    glPopMatrix();

    // blocos parede entre 1 e 3
    for (int i = 0; i < 6; i++)
    {
        glPushMatrix();
        glTranslatef(-3.0f, 2.58f, -1.18f + i * (-0.728f));
        glutSolidCube(0.36f);
        glPopMatrix();
    }

    // parede entre 2 e 4
    glPushMatrix();
    glTranslatef(3.0f, 1.2f, -3.0f);
    glRotatef(90.0f, 0.0f, 1.0f, 0.0f); // Rotacionar
    glScalef(3.35f, 2.0f, 0.3f);
    glutSolidCube(1.2f);
    glPopMatrix();

    // blocos parede entre 2 e 4
    for (int i = 0; i < 6; i++)
    {
        glPushMatrix();
        glTranslatef(3.0f, 2.58f, -1.18f + i * (-0.728f));
        glutSolidCube(0.36f);
        glPopMatrix();
    }

    // frente
    glPushMatrix();
    glTranslatef(0.0f, 1.0f, 0.92f);
    glScalef(1.5f, 2.0f, 1.5f);
    glutSolidCube(1.0f);
    glPopMatrix();

    // porta
    glColor3f(0.4f, 0.2f, 0.0f);
    glPushMatrix();
    glTranslatef(0.0f, 0.5f, 1.60f);
    glScalef(0.4f, 1.0f, 0.2f);
    glutSolidCube(1.0f);
    glPopMatrix();

    // telhado frente (octaedro)
    glPushMatrix(); // save transform matrix state
    glColor3f(0.65f, 0.16f, 0.16f);
    glTranslatef(0.0f, 2.0f, 0.92f);
    glRotatef(-45.0f, 0.0f, 1.0f, 0.0f); // rotacao
    glScalef(1.07f, 1.0f, 1.07f);
    glutSolidOctahedron();
    glPopMatrix(); // restore transform matrix state

    // Restore the matrix state
    glPopMatrix();

    // Buffer swap
    glutSwapBuffers();
}

int main(int argc, char *argv[])
{

    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB | GLUT_DEPTH);
    glutInitWindowSize(800, 600);
    glutCreateWindow("Modeling with Quadrics");
    glutReshapeFunc(ChangeSize);
    glutSpecialFunc(SpecialKeys);
    glutDisplayFunc(RenderScene);
    SetupRC();
    glutMainLoop();

    return 0;
}
