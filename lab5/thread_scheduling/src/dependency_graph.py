import networkx as nx
import matplotlib.pyplot as plt
from matplotlib.patches import FancyArrowPatch
import numpy as np



def draw_graph(G, labels, word):
    """
    Draws graph with nodes horizontal.
    """
    nodes_ordered = list(G)

    mul = len(word)//10 + 1
    pos = {node: (i*mul, 0) for i, node in enumerate(nodes_ordered)}

    _, ax = plt.subplots(figsize=(14, 7))

    num_nodes = len(nodes_ordered)
    colors = plt.cm.viridis(np.linspace(0, 1, num_nodes)) # different colors for different vertices
    color_map = {node: colors[i] for i, node in enumerate(nodes_ordered)}

    nx.draw_networkx_nodes(G, pos, node_color='#939e5d', node_size=500, ax=ax)
    nx.draw_networkx_labels(G, pos, labels=labels, font_size=10, font_color='white', ax=ax)

    arc_base_rad = 0.3
    for u, v in G.edges():
        start_pos, end_pos = pos[u], (pos[v][0] - 0.1*mul, pos[v][1])

        is_adjacent = abs(pos[v][0] - start_pos[0]) == 1*mul
        current_rad = 0 if is_adjacent else arc_base_rad if u%2 == 0 else -arc_base_rad # straight edge between neighbors
        
        edge_color = color_map[u]
        arrow = FancyArrowPatch(posA=start_pos,
                                posB=end_pos,
                                arrowstyle='->',
                                connectionstyle=f'arc3,rad={current_rad}',
                                color=edge_color,
                                mutation_scale=25,
                                lw=2)
        ax.add_patch(arrow)

    ax.set_xlim(-1, num_nodes)
    ax.set_ylim(-num_nodes/3, num_nodes/3)
    ax.set_aspect('equal')
    ax.axis('off')

    plt.title(f"Graph for word \"{word}\"", fontsize=16)
    plt.show()



def graph_to_dot(G, word):
    """
    Represents graph in dot format.
    """
    res = "digraph g{\n"

    for v in range (G.number_of_nodes()):
        for n in G.neighbors(v):
            res += f"{v + 1} -> {n + 1}\n"
    
    for v in range (G.number_of_nodes()):
        res += f"{v+1}[label={word[v]}]\n"

    res += "}\n"
    return res



def print_graph_dot(G, word):
    """
    Prints graph in dot format.
    """
    print(graph_to_dot(G, word))



def make_graph(dependency_relation, word):
    """
    Makes dependency graph using NetworkX.
    """
    labels = {}
    G = nx.DiGraph()

    for i, letter in enumerate(word):
        G.add_node(i)
        labels[i] = letter

    for i, letter in enumerate(word): # iterates over each letter
        for j in range(i+1, len(word)): # iterates over each letter following "letter"
            if word[j] in dependency_relation[letter]: # if dependency relationship => edge
                G.add_edge(i, j)

    G = nx.transitive_reduction(G) # reduces unnecessary edges
    return G, labels