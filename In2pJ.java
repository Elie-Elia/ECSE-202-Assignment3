import java.util.StringTokenizer;
import acm.program.ConsoleProgram;

public class In2pJ extends ConsoleProgram {
	// Step 1 
	// Is the string an operator or not
	public boolean isOperator(String str)
	{
		if (str.compareToIgnoreCase("+")==0 || str.compareToIgnoreCase("-")==0 || str.compareToIgnoreCase("/")==0 || str.compareToIgnoreCase("^")==0 
				|| str.compareToIgnoreCase("*")==0 || str.compareToIgnoreCase(")")==0 || str.compareToIgnoreCase("(")==0)
			//If the input string is an operator, return true
			return true;
		else
		{
			//If the input string is not an operator, return false
			return false;
		}
	}

	// Step 2
	// Precedence table
	public int getPrecedence(String str)
	{
		{
			//Will return a value based on the precedence of the operators
			if(str.compareTo("^") == 0)
				return 3;
			if (str.compareToIgnoreCase("*")==0 || str.compareToIgnoreCase("/")==0)
				return 2;
			if (str.compareToIgnoreCase("+")==0 || str.compareToIgnoreCase("-")==0)
				return 1;
			if(str.compareToIgnoreCase("(") == 0)
				return -1;
			else
				return 0;
		} 
	}
	public void run() {
		//Prompts the user for an input
		String str = readLine("Enter string: ");
		//StringTokenizer object with recognized operators as delimiters
		StringTokenizer st = new StringTokenizer(str,"+-()*^/",true);
		//Initialize Queue
		Queue output = new Queue();
		//Initialize Stack
		Stack op_stack = new Stack();
		//As long as there is something in the String input, this while loop will run
		while (st.hasMoreTokens()) {

			String cur_str = st.nextToken();
			//Checks if the input is an operator or not
			if(isOperator(cur_str)==false)
			{
				//If it is not an operator, it will be added to the queue
				output.Enqueue(cur_str);
				continue;
			}
			//If the input string is a right bracket
			if(cur_str.compareTo(")") == 0){
				while(true){
					//While this is true, if the top of the stack is a left bracket
					if(op_stack.top.value.compareTo("(") == 0){
						//Pop the elements in the stack
						op_stack.pop();
						break;
					}
					else
					{
						//Otherwise, add the popped elements from the stack to the output Queue
						output.Enqueue(op_stack.pop());
					}
				}
				continue;
			}
			//If the stack is empty
			if(op_stack.top == null) {
				//The operator will be added to the stack
				op_stack.push(cur_str);
				continue;
			}
			//Variable int to store the value of precedence of the element in the string
			int StringPrec = getPrecedence(cur_str);
			//Variable int to store the value of precedence of the element in the stack
			int StackPrec = getPrecedence(op_stack.top.value);
			//If the element in the string has a higher precedence than the element in the stack, or the element at the top of the stack is a left bracket
			if(StringPrec>StackPrec ||cur_str.compareTo("(")==0) {
				//The operator in the string will be added to the stack
				op_stack.push(cur_str);
				continue;
			}
			//If not
			else{
				//While the stack is not empty and the Precedence of the element in the String <= precedence of element at the top of the stack
				while(op_stack.top != null && StringPrec <= getPrecedence(op_stack.top.value)){
					//Add the popped elements from the stack to the output Queue
					output.Enqueue(op_stack.pop());
				}
				//Push the current element in the string to the stack
				op_stack.push(cur_str);
			}
		}

		//As long as the stack is not empty
		while(op_stack.top != null) {
			//The stack is popped and added to the output queue
			output.Enqueue(op_stack.pop());
		}
		println("Postfix:");
		Node temp = output.front;
		//As long as there is an element in the front
		while(temp!=null) {
			//It will print the data stored in the front
			print(" "+temp.value);
			temp = temp.next;

		}
	}
}