def is_dependency_relation(variable_a1, variables_a1, variable_a2, variables_a2):
    if variable_a1 == variable_a2:
        return True
    if variable_a1 in variables_a2:
        return True
    if variable_a2 in variables_a1:
        return True
    return False



def add_to_relation(relation_dict, letter_a1, letter_a2):
    if letter_a1 in relation_dict:
        relation_dict[letter_a1].add(letter_a2)
    else:
        relation_dict[letter_a1] = {letter_a2}
    
    if letter_a2 in relation_dict:
        relation_dict[letter_a2].add(letter_a1)
    else:
        relation_dict[letter_a2] = {letter_a1}



def determine_relations(actions_dict, letters):
    dependency_relation = {}
    independance_relation = {}

    actions_list = list(actions_dict.items())
    for i in range(len(actions_list)):
        letter_a1, (variable_a1, variables_a1) = actions_list[i]
        for j in range(i+1, len(actions_list)):
            letter_a2, (variable_a2, variables_a2) = actions_list[j]
            if is_dependency_relation(variable_a1, variables_a1, variable_a2, variables_a2):
                add_to_relation(dependency_relation, letter_a1, letter_a2)
            else:
                add_to_relation(independance_relation, letter_a1, letter_a2)
    
    for letter in letters:
        add_to_relation(dependency_relation, letter, letter)

    return dependency_relation, independance_relation



def prepereD(dependency_relation):
    res = "D = {"
    for k, v in dependency_relation.items():
        for e in v:
            res += f"({k}, {e}),"
    res = res[:-1]
    res += "}\n"
    return res



def prepereI(independance_relation):
    res = "I = {"
    for k, v in independance_relation.items():
        for e in v:
            res += f"({k}, {e}),"
    res = res[:-1]
    res += "}\n"
    return res



def printD(dependency_relation):
    print(prepereD(dependency_relation))



def printI(independance_relation):
    print(prepereI(independance_relation))