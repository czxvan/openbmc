#include <stdio.h>
#include <unistd.h>

int main(void)
{
    printf("hello from hello\n");
    int ret = fork();
    if(ret == 0) {
        printf("hello from hello child\n");
        char *args[] = {"/bin/ls", "-l", "/", NULL};
        char *envp[] = {NULL};
        if (execve(args[0], args, envp) == -1) {
            return -1;
        }
    } else {
        printf("hello from hello child\n");
    }
    return 0;
}