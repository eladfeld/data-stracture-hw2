public class FloorsArrayList implements DynamicSet {
	
	
	private int size;
	private int maxSize;
	private FloorsArrayLink bottom;
	private FloorsArrayLink top;
	int longestArray;
	
	//The constructor initial the list with tow links holding minus infinity and infinity keys , both 
	//links are pointing at each other with every cell.
    public FloorsArrayList(int N){
    	this.longestArray = 0;
    	this.maxSize = N;
	    this.size = 0;
	    this.bottom = new FloorsArrayLink(Double.NEGATIVE_INFINITY,N + 1);
	    this.top = new FloorsArrayLink(Double.POSITIVE_INFINITY,N + 1);
	    for(int i=0 ; i < N + 1 ; i++){
	    	bottom.setNext(i, top);
	    	top.setPrev(i, bottom);
	    }
    }


    @Override
    public int getSize(){
        return size;
    }

    @Override
    public void insert(double key, int arrSize) {
    	//the insert method checks first if a new link can be add to the list.
    	if(size < maxSize & arrSize <= maxSize) {
    	
    	//The method set the list's longestArray field to be the max between the
    	//existing one and the new one.
    	longestArray = Math.max(arrSize, longestArray);
    	FloorsArrayLink toInsert = new FloorsArrayLink(key,arrSize);
    	
    	//The method look for the right place for the new link in the list to stay sorted.
    	//very similar to the lookup method
    	FloorsArrayLink comp = bottom;
    	for(int i = longestArray ; i >= 0 ; i--) 
    		while(comp.getNext(i).getKey()<key)comp = comp.getNext(i);   		
    	
    	FloorsArrayLink right = comp.getNext(0);
    	FloorsArrayLink left = comp;
    	
    	//The method sets the pointers of the link's arrays to the correct links from bottom to top
    	//if the links' arrays are too short it moves to the next link.
    	for(int i = 0 ;i < arrSize; i++ ) { 
    		while(left.getArrSize() <= i ) left = left.getPrev(i-1);
    		toInsert.setPrev(i, left);
    		while(right.getArrSize() <= i) right = right.getNext(i-1);
    		toInsert.setNext(i, right);
    	}
    	//The method changes the surrounding links pointers from the furthest links (the ones that the 
    	//last for loop ended with) to the closest one , moving from the top floor cell to the bottom.
    	for(int i = arrSize -1; i >= 0; i--) { 
    		while(left.getNext(i).getKey() < key) left = left.getNext(i);
    		left.setNext(i, toInsert);
    		while(right.getPrev(i).getKey() > key) right = right.getPrev(i);
    		right.setPrev(i, toInsert);    			    			    		
    	}   	
    size++;
    }
    }
    
    @Override
    public void remove(FloorsArrayLink toRemove) {  	
    	FloorsArrayLink right = toRemove.getNext(0);
    	FloorsArrayLink left = toRemove.getPrev(0);
    	int newLongestArray = 0;
    	
    	//The method replace all the pointers in the surrounding links from the bottom to the top 
    	//whenever a link is too short it step to the next link until it remove all of the pointers that
    	//point on the toRemove link and also set new longestArray size if the toRemove link had the longest
    	//array.
    	for(int i=0 ; i<toRemove.getArrSize() ; i++)
    	{
    		while(left.getArrSize() <= i) {
    			if(left != bottom & left.getArrSize() > newLongestArray)newLongestArray = left.getArrSize();
    			left = left.getPrev(0);
    			}
    		while(right.getArrSize() <= i) {
    			if(right != top & right.getArrSize() > newLongestArray)newLongestArray = right.getArrSize();
    			right = right.getNext(0);
    			}
    		left.setNext(i, right);
    		right.setPrev(i, left);
    	
    	}
    	
    	if(toRemove.getArrSize() == longestArray )longestArray = newLongestArray;
    	size--;
    	if(size==0) {longestArray = 0;}
    }

    @Override
    public FloorsArrayLink lookup(double key) {
    	FloorsArrayLink curr = bottom; 
    	//The method starts from the bottom link in the top cell that points to the Link 
    	//with the longest array and step down as long as the key of the pointer in the current 
    	//cell is bigger then the key , otherwise it move to that link from the same floor atc..
    	//
    	for(int i = longestArray ; i>=0 ; i--) {
    		while( curr.getNext(i).getKey() <= key) {
    			curr = curr.getNext(i);
    			}
    		if(curr.getKey() == key)return curr;	
    	}	
    	return null;
    	
    }

    @Override
    public double successor(FloorsArrayLink link) {
        return link.getNext(0).getKey();
    }

    @Override
    public double predecessor(FloorsArrayLink link) {
        return link.getPrev(0).getKey();
    }

    @Override
    public double minimum() {
        return successor(bottom);
    }

    @Override
    public double maximum() {
        return predecessor(top);
    }


	public int getLongestArray() {
		return longestArray;
	}


	public void setLongestArray(int longestArray) {
		this.longestArray = longestArray;
	}
    public String toString() {
    	String s = "";
    	FloorsArrayLink current = bottom;
    	while(current != top) {
    		
    		s = s + (Double)(current.getKey()) + " | ";
    		if(current != bottom) {
    			for(FloorsArrayLink link : current.getPrevArr()) {
    			s = s + link.getKey() + " ";
    			}
    		s = s + '\n' + "    | ";
    		}
    		for(FloorsArrayLink link : current.getNextArr()) {
    			s = s + link.getKey() + " ";
    		}
    		current = current.getNext(0);
    		s = s + '\n';
    	}
    	s = s + current.getKey() + " | ";
		for(FloorsArrayLink link : current.getPrevArr()) {
		s = s + link.getKey() + " ";
		}
    	return s;
    }
    
}
