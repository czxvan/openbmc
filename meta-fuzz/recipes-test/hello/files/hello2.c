#include <stdio.h>

int main(void)
{
    char s[256];
    while (1) {
        gets(s);
        puts(s);
    }
    return 0;
}