#include <stdio.h>
#include <ctype.h>

int main()
{
    int letters[26] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    int finded[26] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    char sentence[120];
    scanf("%[^\n]", sentence);

    int isUnique = 1;

    for (int i = 0; sentence[i] != '\0'; i++)
    {
        for (int j = 0; j < 26; j++)
        {
            if (tolower(sentence[i]) == letters[j])
            {
                finded[j]++;
            }
        }
    }

    for (int j = 0; j < 26; j++)
    {
        if (finded[j] == 0)
        {
            printf("Não é um pangrama!\n");
            return 0;
        }
        if (finded[j] > 1)
        {
            isUnique = 0;
        }
    }

    printf("Pangrama verdadeiro e ");
    if (isUnique == 1)
    {
        printf("Perfeito");
    }
    else
    {
        printf("Imperfeito");
    }
    printf("\n");

    return 0;
}