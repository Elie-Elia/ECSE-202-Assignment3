//Class contains enqueue/dequeue functions
public class Queue {
	//front and back nodes are initialized
	Node front;
	Node back;

	//Enqueue function used to add elements to the queue
	public void Enqueue(String cur_str)
	{
		//Create new node
		Node newNode = new Node(cur_str);
		//If the queue is empty
		if(isEmpty())
		{
			//Make the back and front equal to the new node
			front = newNode;
			back = newNode;
		}
		//if not
		else
		{
			//Set the previous node of the (old) back node to the new node
			back.next = newNode;
			//Set the new back to the node
			back = newNode;
		}
	}
	//Checks if the Queue is empty
	public boolean isEmpty()
	{
		//If the front of the queue is null
		if(front == null)
			//Return true
			return true;
		else
			//Otherwise return false
			return false;

	}

	//dequeue to serve the first item of the queue (front item)
	public String Dequeue()
	{
		//If there are no elements in the queue
		if(isEmpty())
		{
			System.out.println("ERROR: The queue is empty");
			//returns null
			return null;
		}
		//If not, the output is the data in the front
		else
		{
			//If there is something in the queue, the element at the front of the queue is copied to "frontValue"
			String frontValue = front.value;
			//The element at the front of the queue is changed for the element which was previously in second place (the previous "next" element)
			front = front.next;
			return frontValue;
		}
	}
}

