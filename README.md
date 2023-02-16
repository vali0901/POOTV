# POO TV
This is a project in which I've used various OOP concepts
and design patterns. POO TV simulates a video streaming
platform (like Netflix), in which a user is navigating
through pages, and, depending on the page, can do some
actions (log in, watch a movie, subscribe to a genre).

### Implementation
For the input and output, the json format is used. Input file contains
the database (movies and registered users) and the actions that are made
by the users. Output is either an error (wrong credentials, action done
from the wrong page), or contains information of the current state of the
app (current user, current available movies). For building an output
object, I've created a class implementing a factory design pattern
(OutputFactory).

For the actual implementation, I've created two main classes,
one for representing the actual application and the other one
representing the database (containing all the movies streamed by this
app and also all the users registered). These two classes have been
designed as singletons as they need to be initialized only once during
the execution. Also, the page hierarchy is designed in such way that
no page can be created outside the pages package, so each needed
page is initialized only once (in PageHierarchy class).

Another very important class is ActionMaker, which manages all the
actions a user can make (including switching a page), controlling the
interactions between the classes. It also contains two stacks used for
saving the state of this app (current page and current available movies)
at every action made, so that the "going back" action can be done. 

The notification mechanism was implemented using an observer design pattern.
Everytime a movie is added / deleted from database, the users that
are subscribed to one of this movie's genres are notified by one of
the two observers (one for added movies, the other for deleted ones).
Also, for building a notification object, I've used a builder
design pattern.
