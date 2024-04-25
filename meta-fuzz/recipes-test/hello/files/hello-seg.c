#include <stdio.h>

int data[15] = {0};

int main() {
    printf("Hello, World! data[15]=%d\n", data[0xffffffff000]);
    return 0;
}