public class ListHierarchy extends LowList{

	Coin randomSource;//instantiated to store the random source object
	LowList lowlist=new LowList();//Created two Linked list objects for two levels
	LowList firstlevel=new LowList();
	int i=0;//indexing variable to count number of insertions
	public ListHierarchy(Coin randomSource) {
		//grabbing the random resource object for using later in program
		this.randomSource=randomSource;
	}

	public boolean add(String userArgument) {
	
		if(userArgument==null || userArgument.trim().isEmpty()) {
			//This piece of code doesn't permit empty and null string to enter the linked list
			return false;
		}
		//implemented the addLowList function in LowList class where the elements will be added to list
	boolean b= lowlist.addLowList(userArgument);
	 if(i>0 && b) {//variable 'i' will be incremented as that the coin will be not be flipped for first entry
		 int r=randomSource.flip();
		 if(r==1) {
			 //The elements will be added to first level list when coin flips 1
			 firstlevel.addLowList(userArgument);
			
		 }
	 }
		i++;//increments every time to count number of elements added in ground level
	
		/*The below part of code links the two linked list with their respective top and down to
		 * implement the tree structure while searching
		*/
		Node leveltemp=firstlevel.head;//referring first level list
		while(leveltemp!=null){
			Node temp=lowlist.head;
			while(temp!=null) {
				
				if(leveltemp.getData()==temp.getData()) {
					//Linked two nodes when those two are same.
					temp.setTop(leveltemp);//assigning the top link to first level
					leveltemp.setDown(temp);//assigning the down link to bottom level list
					temp=temp.getNext();
					break;
				}
				temp=temp.getNext();
			}
			leveltemp=leveltemp.getNext();
		}
		

		return b;//will return the value if element is added to list.
	}
		
	public boolean find(String userArgument) {
		
		boolean flag=false;
		Node leveltemp=firstlevel.head;//assigning the first level list to temp node
	    while(leveltemp!=null) {
	    	int s;//stores the compared value of both string
	    	try {
	    		//This Code compares the input data and value of current node
	    		s=userArgument.compareToIgnoreCase(leveltemp.getData().toString());
	    	}
	    	catch(NullPointerException e) {
	    		//catches the NullPointerException if user tries to find null string
	    		break;
	    	}
		
			if(s==0) {
				flag=true;//return yes and break if element is found
				break;
				
			}
			else if(s<0){
				//control comes here is the given string is less than the currentnode
				leveltemp=leveltemp.getDown();//traversing to low level list by calling the Down pointer
				flag= findLowLevel(leveltemp,userArgument);//implement findLowLevel function to search in lowlist
				break;//breaks after getting the flag value
			}
			else {
				
				
				if(leveltemp.getNext()!=null) {
					
					//if the element is higher than the last node of first level list then it traverses to low list
					leveltemp=leveltemp.getNext();//points to next node in first level list
				}
				else {
					flag= findLowLevel(leveltemp.getDown(),userArgument);
					break;
				}
			}
			
	}
		
		return flag; 
	
	}
    boolean findLowLevel(Node n,String input) {
		
		boolean flag=false;
		int s=input.compareToIgnoreCase(n.getData().toString());//compares input and current data
		if(s==0) {
			
			flag= true;//throws true and breaks after finding
			return true;
		}
		else if(s>0) {
			//find next to the current node since the value is greater than current node value
			while(n!=null) {
				if(n.getData().toString().compareToIgnoreCase(input)==0) {
					flag=true;//throws true and breaks the loop after finding
					break;
				}
				n=n.getNext();//pointing to next node
			}
			
		}
		else {
			//find previous to the current node since the value is lower than current node value
			while(n!=null) {
				if(n.getData().toString().compareToIgnoreCase(input)==0) {
					flag=true;//throws true and breaks the loop after finding
					break;
				}
				n=n.getPrevious();//pointing to previous node
			}
		
		}
	
		return flag;//return boolean after finding the data
	}

	public void print() {
		
		//This snippet of code is written to see whether the two links are correctly linked ot not.
	    //user for debugging
		System.out.println("higher level");
	    lowlist.printLow();
	    System.out.println("next level");
	    firstlevel.printLow();
	


	}
	
}
