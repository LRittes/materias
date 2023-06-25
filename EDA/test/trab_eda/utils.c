#include "utils.h"


int sizeStr(char str[])
{
    int i;
    for (i = 0; str[i] != '\n'; i++)
        ;
    return i;
}

int sizeStrCustom(char str[])
{
    int i;
    for (i = 0; i < str[i] != ' ' && str[i + 1] != '#'; i++)
        ;
    return i;
}

int howManySpaceIn(char str[])
{
    int res = 0;
    for (int i = 0; str[i] != '#'; i++)
    {
        if (str[i] == ' ' && str[i + 1] != '#')
            res++;
    }
    return res;
}

void getWord(int *init, int *end, char txt[], char tmp[], ABB *a)
{
    (*init) = (*end);
    for (int i = (*end); txt[i] != '#'; i++)
    {
        if (txt[i] == ' ' && txt[i + 1] != '#')
        {
            (*end) = i + 1;
            break;
        }
        else if (txt[i] == ' ' && txt[i + 1] == '#')
            (*end) = i+1;
    }
    for (int i = 0; i + (*init) < (*end); i++)
    {
        if (i + (*init) == (*end) - 1)
        {
            tmp[i] = '\0';
        }
        else
        {
            tmp[i] = txt[i + (*init)];
        }
    }
}

int calculateIntStr(char str[])

{
    int res = 0;
    for (int i = 0; str[i] != '\0'; i++)
    {
        res += (i + 1) * (int)str[i];
    }
    return res;
}
