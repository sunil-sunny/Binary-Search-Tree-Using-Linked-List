public class LowList{
	
	Node head;
	static class Node{
		
		/*
		 *  This is Node class where the property of node and its links are described.
		 * The Node is designed to match the concept of doubly linked list.
		 * Along with previous and next,the top and down connections which we use while linking two levels
		 */
		private Object  data;
		private Node next;
		private Node previous;
		private Node down;
		private Node Top;
		Node(String s){
			//Assigning the value to the node
			data=s;
			next=null;
		}
		/*
		 * Below methods are getters and setters to access instances of Node
		 */
		public Node getDown() {
			return down;
		}
		public void setDown(Node down) {
			this.down = down;
		}
		
		public Node getTop() {
			return Top;
		}
		public void setTop(Node top) {
			Top = top;
		}
		public Object getData() {
			return data;
		}
		public void setData(String data) {
			this.data = data;
		}
		public Node getNext() {
			return next;
		}
		public void setNext(Node next) {
			this.next = next;
		}
		public Node getPrevious() {
			return previous;
		}
		public void setPrevious(Node previous) {
			this.previous = previous;
		}
	}
	
	boolean isEmpty() {
		//This method is implemented whether to check if list is empty or not
		if(head==null) {
			return true;
		}
		return false;
	}
	void printLow() {
		/*
		 * This method is written for debugging purpose which shows the nodes and its links.
		 * We can see top and down links which replicates two levels of interlinked lists
		 */
		Node currentnode=head;
		while(currentnode!=null) {
			System.out.println(currentnode.getData()+"   and Top is  "+currentnode.getTop()+"  the down is  "+currentnode.getDown());
			currentnode=currentnode.next;
		}
	}
   boolean addLowList(String userArgument) {
	 
	// make the new node to insert into list
		Node newNode = new Node(userArgument);
		newNode.setNext(null);
		newNode.setPrevious(null);
		// first see if the list is empty

		if (head == null || head.getData().toString().compareToIgnoreCase(userArgument)>0)
		{
		    newNode.setNext(head);
            head= newNode; //insert the node for first time
			return true;
		
		}
		else if(head.getData().toString().compareToIgnoreCase(userArgument)==0)
         {
			 //filters the repetitive value when given immediately
			 System.out.println("Repeated element cant not be accepted");
			 return false;
         }
		 else
         {
            
              Node last =head;
              
              while(last.getNext() !=null && last.getNext().getData().toString().compareToIgnoreCase(userArgument) <= 0)
              {

                  if(last.getNext().getData().toString().compareToIgnoreCase(userArgument)==0)
                  {
                	  //filters the repetetive value if not given immediately
                      System.out.println("Repeated element cant not be accepted");
                      return false;
                  }
                  last=last.getNext();
                  
              }
              //insert the node at correct position after sorting
              newNode.setNext(last.getNext());
              if(last.getNext()!=null)
              {
                  newNode.next.previous=newNode;
              }
              last.setNext(newNode);
              newNode.setPrevious(last);
              return true;
              }
   }

}
