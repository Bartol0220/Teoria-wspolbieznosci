def find(G, word):
    fnf_array = []
    fnf_word_array = []
    n = G.number_of_nodes()
    visited = [False] * n


    def dfs(s, i):
        visited[s] = True
        if i < len(fnf_array):
            fnf_array[i].append(s)
            fnf_word_array[i].append(word[s])
        else:
            fnf_array.append([s])
            fnf_word_array.append([word[s]])
        for v in G.neighbors(s):
            if not visited[v]:
                dfs(v, i+1)


    for v in range(n):
        if not visited[v]:
            dfs(v, 0)

    return fnf_word_array



def prepere_fnf(fnf_word_array):
    res = "FNF([w]) = "
    
    for a in fnf_word_array:
        res += "("
        a_sort = sorted(a)
        for e in a_sort:
            res += e
        res += ")"
    return res + "\n"



def print_fnf(fnf_word_array):
    print(prepere_fnf(fnf_word_array))