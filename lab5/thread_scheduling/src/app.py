import sys
import os
import re

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
        print("Actions in format `(<letter>) <action>` not found.")
        exit(1)

    alphabet_match = re.findall(alphabet_regex, content)
    if len(alphabet_match) == 0:
        print("Alphabet in format `A = {<alphabet>}` not found.")
        exit(1)

    letter_match = re.findall(letter_regex, alphabet_match[0])
    if len(letter_match) == 0:
        print("Letters in alphabet not found.")
        exit(1)

    word_match = re.findall(word_regex, content)
    if len(word_match) == 0:
        print("Word in format `w = <word>` not found.")
        exit(1)
    
    actions_dict = {}
    for (letter, variable, action) in action_match:
        used_variables = re.findall(variable_regex, action)
        actions_dict[letter] = (variable, set(used_variables))

    return actions_dict, letter_match, word_match[0]



def main():
    if len(sys.argv) < 2:
        print("Usage: startapp <file_name>")
        print("Application will use file: example1.txt")
        filename = "example1.txt"
    else:
        filename = sys.argv[1]

    data_path = os.path.join("data", filename)

    if not os.path.exists(data_path):
        print(f"File: {filename} not found in data directory")
        sys.exit(1)
    
    parse_file(data_path)



if __name__ == "__main__":
    main()