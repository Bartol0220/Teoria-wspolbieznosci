import dependency_graph
import networkx as nx



def test_graph_example_1():
    dependency_relation = {'a': {'a', 'b', 'c'}, 'b': {'a', 'b', 'd'}, 'c': {'a', 'c', 'd'}, 'd': {'b', 'c', 'd'}}
    word = "baadcb"

    Graph, labels = dependency_graph.make_graph(dependency_relation, word)

    G = nx.DiGraph()
    G.add_edge(0, 1)
    G.add_edge(0, 3)
    G.add_edge(1, 2)
    G.add_edge(2, 4)
    G.add_edge(2, 5)
    G.add_edge(3, 4)
    G.add_edge(3, 5)

    assert labels == {0: 'b', 1: 'a', 2: 'a', 3: 'd', 4: 'c', 5: 'b'}
    assert G.number_of_nodes() == Graph.number_of_nodes()
    assert G.number_of_edges() == Graph.number_of_edges()
    for v in range(G.number_of_nodes()):
        for n in G.neighbors(v):
            assert n in Graph.neighbors(v)

        for n in Graph.neighbors(v):
            assert n in G.neighbors(v)



def test_graph_example_2():
    dependency_relation = {'a': {'a', 'c', 'f'}, 'b': {'b', 'e'}, 'c': {'a', 'c', 'e', 'f'}, 'd': {'d', 'f'}, 'e': {'b', 'c', 'e'}, 'f': {'a', 'c', 'd', 'f'}}
    word = "acdcfbbe"

    Graph, labels = dependency_graph.make_graph(dependency_relation, word)

    G = nx.DiGraph()
    G.add_edge(0, 1)
    G.add_edge(1, 3)
    G.add_edge(2, 4)
    G.add_edge(3, 4)
    G.add_edge(3, 7)
    G.add_edge(5, 6)
    G.add_edge(6, 7)

    assert labels == {0: 'a', 1: 'c', 2: 'd', 3: 'c', 4: 'f', 5: 'b', 6: 'b', 7: 'e'}
    assert G.number_of_nodes() == Graph.number_of_nodes()
    assert G.number_of_edges() == Graph.number_of_edges()
    for v in range(G.number_of_nodes()):
        for n in G.neighbors(v):
            assert n in Graph.neighbors(v)

        for n in Graph.neighbors(v):
            assert n in G.neighbors(v)