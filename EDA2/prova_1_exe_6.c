#include <stdio.h>
#include <string.h>

int min(int val1, int val2, int val3)
{
    int menor = val1;
    if (val2 < menor)
        menor = val2;
    if (val3 < menor)
        menor = val3;
    return menor;
}

int dist_levenshtein(char *str1, char *str2)
{
    int m = strlen(str1);
    int n = strlen(str2);

    int dp[m + 1][n + 1];

    for (int i = 0; i <= m; i++)
    {
        dp[i][0] = i;
    }

    for (int j = 0; j <= n; j++)
    {
        dp[0][j] = j;
    }

    for (int i = 1; i <= m; i++)
    {
        for (int j = 1; j <= n; j++)
        {
            if (str1[i - 1] == str2[j - 1])
            {
                dp[i][j] = dp[i - 1][j - 1];
            }
            else
            {
                dp[i][j] = min(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]) + 1;
            }
        }
    }

    return dp[m][n];
}

int main()
{
    char str1[100];
    char str2[100];

    printf("Digite a primeira string: ");
    scanf("%s", str1);

    printf("Digite a segunda string: ");
    scanf("%s", str2);

    int dist = dist_levenshtein(str1, str2);

    printf("Distância de edição: %d\n", dist);

    return 0;
}