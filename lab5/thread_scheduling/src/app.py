import sys
import os
import re
import relations
import dependency_graph
import fnf



def parse_file(filename):
    action_regex = r"\(([a-z])\) ([a-z]) := ([a-z0-9\+\-\*\/ ]*)"
    alphabet_regex = r"A = {([a-z, ]*)}"
    letter_regex = r"[a-z]"
    variable_regex = r"[a-z]"
    word_regex = r"w = ([a-z]*)"

    with open(filename, "r") as file:
        content = file.read()
        
    action_match = re.findall(action_regex, content)
    if len(action_match) == 0:
        sys.exit("Error: actions in format `(<letter>) <action>` not found.")

    alphabet_match = re.findall(alphabet_regex, content)
    if len(alphabet_match) == 0:
        sys.exit("Error: alphabet in format `A = {<alphabet>}` not found.")

    letter_match = re.findall(letter_regex, alphabet_match[0])
    if len(letter_match) == 0:
        sys.exit("Error: letters in alphabet not found.")

    word_match = re.findall(word_regex, content)
    if len(word_match) == 0:
        sys.exit("Error: word in format `w = <word>` not found.")
    
    actions_dict = {}
    for (letter, variable, action) in action_match:
        used_variables = re.findall(variable_regex, action)
        actions_dict[letter] = (variable, set(used_variables))


    for letter in letter_match:
        if letter not in actions_dict:
            sys.exit(f"Undefined action error: no action defined for '{letter}'.")
    
    for letter in actions_dict.keys():
        if letter not in letter_match:
            sys.exit(f"Undefined letter error: action defined for letter '{letter}', which is not in the alphabet.")
    
    for letter in word_match[0]:
        if letter not in letter_match:
            sys.exit(f"Error: word contains symbol '{letter}' not in alphabet.")

    return actions_dict, letter_match, word_match[0]



def export_results(filename, dependency_relation, independance_relation, fnf_array, Graph, word):
    D = relations.prepereD(dependency_relation)
    I = relations.prepereI(independance_relation)
    fnf_res = fnf.prepere_fnf(fnf_array)
    graph_dot = dependency_graph.graph_to_dot(Graph, word)

    res_path = os.path.join("results", "res_" + filename)
    with open(res_path, "w") as file:
        file.write(D)
        file.write(I)
        file.write(fnf_res)
        file.write(graph_dot)



def main():
    if len(sys.argv) < 2:
        print("Usage: startapp <file_name>")
        print("Application will use file: example1.txt")
        filename = "example1.txt"
    else:
        filename = sys.argv[1]

    data_path = os.path.join("data", filename)

    if not os.path.exists(data_path):
        sys.exit(f"File: {filename} not found in data directory")
    
    actions_dict, letters, word = parse_file(data_path)
    dependency_relation, independance_relation = relations.determine_relations(actions_dict, letters)
    Graph, labels = dependency_graph.make_graph(dependency_relation, word)
    fnf_array = fnf.find(Graph, word)

    export_results(filename, dependency_relation, independance_relation, fnf_array, Graph, word)

    relations.printD(dependency_relation)
    relations.printI(independance_relation)
    fnf.print_fnf(fnf_array)
    dependency_graph.print_graph_dot(Graph, word)
    dependency_graph.draw_graph(Graph, labels, word)



if __name__ == "__main__":
    main()