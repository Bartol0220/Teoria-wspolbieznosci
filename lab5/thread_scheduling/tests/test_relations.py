import relations



def test_relations_example_1():
    actions_dict = {
        'a': ('x', {'x', 'y'}),
        'b': ('y', {'y', 'z'}),
        'c': ('x', {'x', 'z'}),
        'd': ('z', {'y', 'z'})
        }
    letters = ["a", "b", "c", "d"]

    dependency_relation, independence_relation = relations.determine_relations(actions_dict, letters)

    correct_dependency_relation = {'a': {'a', 'b', 'c'}, 'b': {'a', 'b', 'd'}, 'c': {'a', 'c', 'd'}, 'd': {'b', 'c', 'd'}}
    correct_independence_relation = {'a': {'d'}, 'd': {'a'}, 'b': {'c'}, 'c': {'b'}}

    assert dependency_relation == correct_dependency_relation
    assert independence_relation == correct_independence_relation



def test_relations_example_2():
    actions_dict = {
        'a': ('x', {'x'}),
        'b': ('y', {'y', 'z'}),
        'c': ('x', {'x', 'z'}),
        'd': ('w', {'w', 'v'}),
        'e': ('z', {'y', 'z'}),
        'f': ('v', {'x', 'v'})
        }
    letters = ["a", "b", "c", "d", "e", "f"]

    dependency_relation, independence_relation = relations.determine_relations(actions_dict, letters)

    correct_dependency_relation = {'a': {'a', 'c', 'f'}, 'b': {'b', 'e'}, 'c': {'a', 'c', 'e', 'f'}, 'd': {'d', 'f'}, 'e': {'b', 'c', 'e'}, 'f': {'a', 'c', 'd', 'f'}}
    correct_independence_relation = {'a': {'b', 'd', 'e'}, 'b': {'a', 'c', 'd', 'f'}, 'c': {'b', 'd'}, 'd': {'a', 'b', 'c', 'e'}, 'e': {'a', 'd', 'f'}, 'f': {'b', 'e'}}

    assert dependency_relation == correct_dependency_relation
    assert independence_relation == correct_independence_relation