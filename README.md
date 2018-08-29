# Multi-threading in Java

Implementation of multiple threads to transfer fund from source account to destination account concurrently by using __Lock__. 
  
In multi-threading if multiple threads are accessing same resources concurrently then we might have issues like:  
    - Race condition: A race condition occurs when two or more threads can access shared data and they try to change it at the same time and the output will be unexpected and error-prone  
    - Starvation: Starvation occurs when one or more threads in a program are blocked from gaining access to a resource and, as a result, cannot make progress.  
    - Dead Lock: Dead Lock is the ultimate form of starvation, occurs when two or more threads are waiting on a condition that cannot be satisfied. Deadlock most often occurs when two (or more) threads are each waiting for the other(s) to do something.  
    - Live Lock: Live Lock occurs when a thread often acts in response to the action of another thread and unable to maje further progress. Threads are not blocked but they are simple too busy responding to each other to resume work.  
    
    
To overcome the issues we have locking in Java
    __Structured Lock__ and __Unstructured Lock__

__Structured Lock__
  - __Synchronized (Monitors)__  
  Synchronized block by synchronized on monitor object.  
  
__Unstructured Lock__  
  - __Reentrant Lock__    
  Trying to acquire Lock in working Thread and then release the Lock after working thread complete the process.  

  - __Atomic Reference__  
  Object reference that may be updated atomically only if reference object value changes.
