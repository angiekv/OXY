# OXY
This project, entitled OXY is meant to represent an e-commerce mall.

For now, we have implemented the customer data repository :
When launching the executable jar, OXY.jar, the Product Owner, can see, add, update, or delete the customers.

In technical terms : 
His request is sent to the server which should be located in another virtual machine through a thread.
The thread is sent from the client socket to the server socket, which receives the request.
Then, the request is assigned to a Connection to the database located in another VM.
Finally, the server socket can answer the client's request.
