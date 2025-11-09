import fnf
import networkx as nx



def test_fnf_example_1():
    word = "baadcb"

    G = nx.DiGraph()
    G.add_edge(0, 1)
    G.add_edge(0, 3)
    G.add_edge(1, 2)
    G.add_edge(2, 4)
    G.add_edge(2, 5)
    G.add_edge(3, 4)
    G.add_edge(3, 5)
    
    fnf_array = fnf.find(G, word)

    for sublist in fnf_array:
        sublist.sort()

    assert fnf_array == [['b'], ['a', 'd'], ['a'], ['b', 'c']]



def test_fnf_example_2():
    word = "acdcfbbe"

    G = nx.DiGraph()
    G.add_edge(0, 1)
    G.add_edge(1, 3)
    G.add_edge(2, 4)
    G.add_edge(3, 4)
    G.add_edge(3, 7)
    G.add_edge(5, 6)
    G.add_edge(6, 7)
    
    fnf_array = fnf.find(G, word)

    for sublist in fnf_array:
        sublist.sort()

    assert fnf_array == [['a', 'b', 'd'], ['b', 'c'], ['c'], ['e', 'f']]