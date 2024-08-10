from linked_list import LinkedList
""" 
Chelina Obiang
CSC120
Assignment -7
This program will is be responsible for implementing the application's 
primary functionality using the LinkedList and Node classes, 
specifically managing and querying friend relationships. Using
the main function, this program prints the common friends shared
by two input names.
"""
def main():
    '''
    This function directs the program to read friendship data, manage 
    it using a linked list data structure that's imported from the 
    separate python file, and upon user input (for file, and 2 names), 
    determine and prints any friends that two given names have in common.
    '''
    people = LinkedList()
    file = open(input("Input file:"), 'r')
    for line in file:
        name, friend = line.strip().split()
        #  Check that both name and friend exist in the main linked list
        person = people.find_match(name)
        if person:
            person._friend.add_name(friend)
        else:
            people.add_name(name)
            people.find_match(name)._friend.add_name(friend)
        person = people.find_match(friend)
        if person:
            person._friend.add_name(name)
        else:
            people.add_name(friend)
            people.find_match(friend)._friend.add_name(name)
    name1 = input(" Name 1: ")
    name2 = input("Name 2: ")

    node1 = people.find_match(name1)
    node2 = people.find_match(name2)
    # Error checks
    if node1 is None:
        print(f"ERROR: Unknown person {name1}")
        return
    elif node2 is None:
        print(f"ERROR: Unknown person {name2}")
        return
    mutual_friends = LinkedList()
    friends1 = node1._friend._head
    #  Compare friends of name1 to each friend of name2 to store mutual friends
    while friends1:
        friends2 = node2._friend._head
        while friends2:
            if friends1._name == friends2._name:
                mutual_friends.sorted_insert(friends1._name)
                break
            friends2 = friends2._next
        friends1 = friends1._next

    if mutual_friends._head:
        print("Friends in common:")
        current = mutual_friends._head
        while current:
            print(current._name)
            current = current._next

if __name__ == "__main__":
    main()