#include <stdio.h>
#include <stdlib.h>

int array[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

typedef struct node {
    int value;
    struct node* left;
    struct node* right;
} Node;

Node* build(int start, int end){
    if(start > end) return NULL;

    Node* root = (Node*) malloc(sizeof(Node));

    int mid = (start + end) / 2;

    root -> value = array[mid];
    root -> left = build(start, mid - 1);
    root -> right = build(mid + 1, end);

    return root;
}

void print_tree(Node *r, int level) {
    if (r) {
        print_tree(r->right, level + 1);
        printf("%*s->%d\n", 4 * level, "", r->value);
        print_tree(r->left, level + 1);
    }
}

int main(){
    Node* root = build(0, sizeof(array) / sizeof(array[0]));
    print_tree(root, 0);

    return 0;
}
