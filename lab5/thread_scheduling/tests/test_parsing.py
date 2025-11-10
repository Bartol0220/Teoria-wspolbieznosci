import sys
import os
import app



# (a) x := x + y
# (b) y := y + 2z
# (c) x := 3x + z
# (d) z := y - z
# A = {a, b, c, d}
# w = baadcb
def test_parsing_example_1():
    data_path = os.path.join("data", "example1.txt")

    if not os.path.exists(data_path):
        sys.exit(f"File: example1.txt not found in data directory")
    
    actions_dict, letters, word = app.parse_file(data_path)

    correct_actions_dict = {
        'a': ('x', {'x', 'y'}),
        'b': ('y', {'y', 'z'}),
        'c': ('x', {'x', 'z'}),
        'd': ('z', {'y', 'z'})
        }
    assert word == "baadcb"
    assert letters == ["a", "b", "c", "d"]
    assert actions_dict == correct_actions_dict



# (a) x := x + 1
# (b) y := y + 2z
# (c) x := 3x + z
# (d) w := w + v
# (e) z := y - z
# (f) v := x + v
# A = {a, b, c, d, e, f}
# w = acdcfbbe
def test_parsing_example_2():
    data_path = os.path.join("data", "example2.txt")

    if not os.path.exists(data_path):
        sys.exit(f"File: example2.txt not found in data directory")
    
    actions_dict, letters, word = app.parse_file(data_path)

    correct_actions_dict = {
        'a': ('x', {'x'}),
        'b': ('y', {'y', 'z'}),
        'c': ('x', {'x', 'z'}),
        'd': ('w', {'w', 'v'}),
        'e': ('z', {'y', 'z'}),
        'f': ('v', {'x', 'v'})
        }
    assert word == "acdcfbbe"
    assert letters == ["a", "b", "c", "d", "e", "f"]
    assert actions_dict == correct_actions_dict



# (a) x := x + y
# (b) y := z - v
# (c) z := v * x
# (d) v := x + 2y
# (e) x := 3y + 2x
# (f) v := v - 2z
# A = {a,b,c,d,e,f}
# w = afaeffbcd
def test_parsing_example_3():
    data_path = os.path.join("data", "example3.txt")

    if not os.path.exists(data_path):
        sys.exit(f"File: example3.txt not found in data directory")
    
    actions_dict, letters, word = app.parse_file(data_path)

    correct_actions_dict = {
        'a': ('x', {'x', 'y'}),
        'b': ('y', {'z', 'v'}),
        'c': ('z', {'v', 'x'}),
        'd': ('v', {'x', 'y'}),
        'e': ('x', {'y', 'x'}),
        'f': ('v', {'v', 'z'})
        }
    assert word == "afaeffbcd"
    assert letters == ["a", "b", "c", "d", "e", "f"]
    assert actions_dict == correct_actions_dict