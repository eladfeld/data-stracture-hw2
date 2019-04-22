public class FloorsArrayList implements DynamicSet {
	private int size;
	private int maxSize;
	private FloorsArrayLink bottom;
	private FloorsArrayLink top;
	int longestArray;
	
	
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
    	if(size < maxSize & arrSize <= maxSize) {
    	
    	longestArray = Math.max(arrSize, longestArray);
    	FloorsArrayLink toInsert = new FloorsArrayLink(key,arrSize);
    	FloorsArrayLink comp = bottom;
    	//finds place for the key

    	for(int i = longestArray ; i >= 0 ; i--) {
    		while(comp.getNext(i).getKey()<key){comp = comp.getNext(i);}   		
    	}
    	FloorsArrayLink right = comp.getNext(0);
    	FloorsArrayLink left = comp;
    	for(int i = 0 ;i < arrSize; i++ ) { //changes the toInsert's array's pointers to the correct values.
    		while(left.getArrSize() <= i ) left = left.getPrev(i-1);
    		toInsert.setPrev(i, left);
    		while(right.getArrSize() <= i) right = right.getNext(i-1);
    		toInsert.setNext(i, right);
    	}
    	for(int i = arrSize -1; i >= 0; i--) { //change the surrounding links' array's pointer
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
