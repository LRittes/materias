#include <stdio.h>
#include <stdlib.h>

int interacoes = 0;

struct node {
    int d; // data
    int c; // 1-vermelho, 0-preto
    struct node* p; // pai
    struct node* r; // filho a direira
    struct node* l; // filho a esquerda
};

struct node* root = NULL; // raiz da arvore

struct node* bst(struct node* trav, struct node* temp)
{
    if (trav == NULL)
        return temp;

    if (temp->d < trav->d)
    {
        trav->l = bst(trav->l, temp);
        trav->l->p = trav;
    }
    else if (temp->d > trav->d)
    {
        trav->r = bst(trav->r, temp);
        trav->r->p = trav;
    }
    interacoes++;

    return trav;
}

void rightrotate(struct node* temp)
{
    struct node* left = temp->l;
    temp->l = left->r;
    if (temp->l)
        temp->l->p = temp;
    left->p = temp->p;
    if (!temp->p)
        root = left;
    else if (temp == temp->p->l)
        temp->p->l = left;
    else
        temp->p->r = left;
    left->r = temp;
    temp->p = left;
    interacoes++;
}

void leftrotate(struct node* temp)
{
    struct node* right = temp->r;
    temp->r = right->l;
    if (temp->r)
        temp->r->p = temp;
    right->p = temp->p;
    if (!temp->p)
        root = right;
    else if (temp == temp->p->l)
        temp->p->l = right;
    else
        temp->p->r = right;
    right->l = temp;
    temp->p = right;
    interacoes++;
}

void fixup(struct node* root, struct node* pt)
{
    struct node* parent_pt = NULL;
    struct node* grand_parent_pt = NULL;

    while ((pt != root) && (pt->c != 0) && (pt->p->c == 1))
    {
        interacoes++;
        parent_pt = pt->p;
        grand_parent_pt = pt->p->p;

        if (parent_pt == grand_parent_pt->l)
        {
            struct node* uncle_pt = grand_parent_pt->r;

            if (uncle_pt != NULL && uncle_pt->c == 1)
            {
                grand_parent_pt->c = 1;
                parent_pt->c = 0;
                uncle_pt->c = 0;
                pt = grand_parent_pt;
            }
            else {
                if (pt == parent_pt->r) {
                    leftrotate(parent_pt);
                    pt = parent_pt;
                    parent_pt = pt->p;
                }

                rightrotate(grand_parent_pt);
                int t = parent_pt->c;
                parent_pt->c = grand_parent_pt->c;
                grand_parent_pt->c = t;
                pt = parent_pt;
            }
        }
        else {
            struct node* uncle_pt = grand_parent_pt->l;

            if ((uncle_pt != NULL) && (uncle_pt->c == 1))
            {
                grand_parent_pt->c = 1;
                parent_pt->c = 0;
                uncle_pt->c = 0;
                pt = grand_parent_pt;
            }
            else {
                if (pt == parent_pt->l) {
                    rightrotate(parent_pt);
                    pt = parent_pt;
                    parent_pt = pt->p;
                }
                leftrotate(grand_parent_pt);
                int t = parent_pt->c;
                parent_pt->c = grand_parent_pt->c;
                grand_parent_pt->c = t;
                pt = parent_pt;
            }
        }
    }

    root->c = 0;
}

void inorder(struct node* trav)
{
    if (trav == NULL)
        return;
    inorder(trav->l);
    printf("%d ", trav->d);
    inorder(trav->r);
}

struct node* minValueNode(struct node* node)
{
    struct node* current = node;

    while (current->l != NULL)
        current = current->l;

    return current;
}

struct node* deleteNode(struct node* root, int key)
{
    if (root == NULL)
        return root;

    if (key < root->d) {
        root->l = deleteNode(root->l, key);
        interacoes++;
    }
    else if (key > root->d) {
        root->r = deleteNode(root->r, key);
        interacoes++;
    }
    else {
        if (root->l == NULL) {
            struct node* temp = root->r;
            free(root);
            return temp;
        }
        else if (root->r == NULL) {
            struct node* temp = root->l;
            free(root);
            return temp;
        }

        struct node* temp = minValueNode(root->r);

        root->d = temp->d;

        root->r = deleteNode(root->r, temp->d);
    }
    return root;
}

int main()
{
    int item;
    int tamAmostra = 63;
    int* vetor;

    vetor = malloc(tamAmostra * sizeof(int));
	int j, i;
	
    for ( j = 0; j < 10; j++) {
        printf("---------------------------------------------\n");
        printf("\t\tCaso medio\n\n");
        printf("Tamanho dos Conjuntos | Esforco Computacional\n");

        int tamAmostra = rand() % 100;
        vetor = realloc(vetor, tamAmostra * sizeof(int));
		
        for ( i = 0; i < tamAmostra; i++) {
            item = rand() % 1000;

            struct node* temp = (struct node*)malloc(sizeof(struct node));
            temp->r = NULL;
            temp->l = NULL;
            temp->p = NULL;
            temp->d = item;
            temp->c = 1;

            root = bst(root, temp);
        }

        printf("\t   %d         | \t%d\n", tamAmostra, interacoes);
        interacoes = 0;
        inorder(root);

        printf("\n");
        printf("---------------------------------------------\n");

        // Remove um nó aleatório da árvore
        int nodeToRemove = root->d;
        root = deleteNode(root, nodeToRemove);

        // Limpa a árvore para a próxima iteração
        root = NULL;
    }

    free(vetor);

    return 0;
}

