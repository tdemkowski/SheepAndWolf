# SheepAndWolf
October, 2016:

- Worked on:
	- Grid.java - Made the cells drawn in random shades of green
	- Gave all NPCs ability to see 5 squares away in any direction (including diagonals)
	- Created Random.java behaviour class
	- Created Patrol.java behaviour class
	- Created Chase.java behaviour class
	- Assigned sheep to move randomly when they can't see anyone else, and chases shepherd when sees it
	- Assigned wolf to patrol the field, and chases sheep if sees it
	- Demo.java was made to prove that Counter class is not thread safe. Which is due to decrement() 
        method not being synchronized; so run() doesn't decrement properly.
	- CounterAdaptor.java - adapted the Counter class so that it is thread-safe 


 

- Do you need another pattern when the variety gets greater? 

    Yes. As variety of behaviors increase, the number of stage methods tend to increase.
    So the implementation of the singleton pattern should be used for stage, so that 
    there will be only one instance of the class at any point in time.

- Is the strategy pattern the best one for this task?

    Yes, it is the most important pattern in this case, since it is designed to 'encapsulate'
    the interfaces to keep the Behaviour client as dynamic and simple as possible for any
    future features wished to be added.
