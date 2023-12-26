/**
 * 
 * @author Kevin Eguida
 *
 */

public class Notation {
	private static MyQueue<String> solutionQueue;
	private static MyStack<String> operationstack;

	/**
	 * convert infix to postfix
	 * 
	 * @param infix string to be converted
	 * @return string converted to postfix
	 * @throws InvalidNotationFormatException thrown if
	 * @throws StackUnderflowException
	 * @throws QueueOverflowException
	 */
	public static String convertInfixToPostfix(String infix)
			throws InvalidNotationFormatException, StackUnderflowException, QueueOverflowException {
		operationstack = new MyStack<String>(infix.length());
		solutionQueue = new MyQueue<String>(infix.length());

		for (String string : infix.split("")) {

			if (hasDigit(string)) {
				try {
					solutionQueue.enqueue(string);
				} catch (QueueOverflowException e) {
					e.printStackTrace();
				}
			}

			else if (string.equals("(")) {
				try {
					operationstack.push(string);
				} catch (StackOverflowException e) {
					e.printStackTrace();
				}
			} else if (hasOperator(string)) {
				while (!operationstack.isEmpty() && hasLowerPrecedence(string, (String)operationstack.top()) && !string.equals("(")) {
					try {
						solutionQueue.enqueue(operationstack.pop());

					} catch (StackUnderflowException | QueueOverflowException e) {

						e.printStackTrace();
					}
				}
				try {
					operationstack.push(string);
					if (operationstack.isEmpty());
					
				} catch (StackOverflowException e) {
					e.printStackTrace();
				}
			} else if (string.equals(")")) {

				while (!operationstack.isEmpty() && !operationstack.top().equals("(")) {
					solutionQueue.enqueue(operationstack.pop());
				}

				if (!operationstack.isEmpty())
					operationstack.pop();
				else
					throw new InvalidNotationFormatException();
			}
		}
		
		while (!operationstack.isEmpty()) {
			try {
				if (!operationstack.top().equals("(")) {
					solutionQueue.enqueue(operationstack.pop());
				}

			} catch (StackUnderflowException | QueueOverflowException e) {
				e.printStackTrace();
			}
		}

		return solutionQueue.toString();
	}

	
	public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException
	{
		MyStack<String> infixStack = new MyStack<String>(postfix.length());
		char[] operands = postfix.toCharArray();

		for (int i = 0; i < postfix.length(); i++)
		{
			if (operands[i] != ' ')
			{
				if (Character.isDigit(operands[i]) )
				{
					String s = "" + postfix.charAt(i);
					infixStack.push(s);
				}
				else if (operands[i] == '+' || operands[i] == '-' || operands[i] == '/' || operands[i] == '*')
				{
					if (infixStack.size() < 2)
					{
						throw new InvalidNotationFormatException();
					}
					else
					{
						String a = (String) infixStack.pop();
						String b = (String) infixStack.pop();
						String str = ( "(" + b + operands[i] + a + ")" ); 
						infixStack.push(str);
					}
				}
			}
		}
		if (infixStack.size() == 1)
		{
			String endStr = (String) infixStack.pop();
			return endStr;
		}
		else
		{
			throw new InvalidNotationFormatException();
		}
	}
	
	
	/**
	 * 
	 * @param postfixExpr post fix expression in String format to be evaluated
	 * @return result of post fix expression as double
	 * @throws InvalidNotationFormatException if the postfix expression format is invalid
	 */
	
	public static double evaluatePostfixExpression(String postfixExpr) throws InvalidNotationFormatException
	{
		MyStack<String> myStack = new MyStack<String>(postfixExpr.length());
		char[] charArra = postfixExpr.toCharArray();

		for (int i = 0; i < postfixExpr.length(); i++)
		{
			if (charArra[i] != ' ')
			{
				if (Character.isDigit(charArra[i]) || charArra[i] == '(')
				{
					String str = "" + postfixExpr.charAt(i);
					myStack.push(str);
				}
				else if (charArra[i] == '+' || charArra[i] == '-' || charArra[i] == '/' || charArra[i] == '*')
				{				
					if (myStack.size() < 2)
					{
						throw new InvalidNotationFormatException();
					}
					else
					{
						String p = (String) myStack.pop(); 
						String q = (String) myStack.pop(); 
						double x = Double.parseDouble(p);
						double y = Double.parseDouble(q);
						
						double result = 0;
						if (charArra[i] == '+')
						{					
							result = y + x;
						}
						else if (charArra[i] == '-')
						{
							result = y - x;
						}
						else if (charArra[i] == '/')
						{
							result = y / x;
						}
						else if (charArra[i] == '*')
						{
							result = y * x;
						}
						String str = Double.toString(result);
						myStack.push(str);
					}
				}
			}
		}
		if (myStack.size() == 1)
		{
			return Double.parseDouble((String) myStack.pop() ) ;
		}
		else
		{
			throw new InvalidNotationFormatException();
		}
	}
	
	
	/**
	 * @param str string to be checked
	 * @return true if the parameter is an operator
	 */
	public static boolean hasOperator(String str) {
		return str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/");
	}

	/**
	 * checks if the first operator has lower precedence than the second operator
	 * 
	 * @param str1 the first operator to be compared
	 * @param str2 the second operator to be compared
	 * @return true if the first operator has lower precedence than the second one
	 *         and returns false otherwise
	 */
	public static boolean hasLowerPrecedence(String str1, String str2) {
		return precedence(str1) < precedence(str2);
	}

	/**
	 * gives a precedence value to operators
	 * 
	 * @param operator the operator whose precedence value will be determined
	 * @return an integer precedence value for every operator
	 */
	public static int precedence(String operator) {
		return switch (operator) {
		case "(" -> 0;
		case "+", "-" -> 1;
		case "/", "*" -> 2;
		case "%" -> 3;
		default -> 4;
		};
	}
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	private static boolean hasDigit(String str) {
	try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}

