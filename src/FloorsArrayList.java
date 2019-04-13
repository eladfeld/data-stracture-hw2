public class FloorsArrayList implements DynamicSet {
	private int size;
	private FloorsArrayLink bottom;
	private FloorsArrayLink top;
	
	
    public FloorsArrayList(int N){
    this.size = 0;
    this.bottom = new FloorsArrayLink(Double.NEGATIVE_INFINITY,N + 1);
    this.top = new FloorsArrayLink(Double.POSITIVE_INFINITY,N + 1);
    for(int i=0 ; i < N + 1 ; i++){
    	bottom.setNext(i, top);
    	top.setPrev(i, bottom);
    }
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
    


    @Override
    public int getSize(){
        return size;
    }

    @Override
    public void insert(double key, int arrSize) {
    	FloorsArrayLink toInsert = new FloorsArrayLink(key,arrSize);
    	FloorsArrayLink comp = bottom;
    	//finds place for the key
    	while(comp.getKey() < toInsert.getKey()) {
    		comp=comp.getNext(0);
    	}
    	FloorsArrayLink right = comp;
    	FloorsArrayLink left = comp.getPrev(0);
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
    
    @Override
    public void remove(FloorsArrayLink toRemove) {
    	
    	FloorsArrayLink right = toRemove.getNext(0);
    	FloorsArrayLink left = toRemove.getPrev(0);

    	
    	for(int i=0 ; i<toRemove.getArrSize() ; i++)
    	{
    		while(left.getArrSize() <= i) {left.getPrev(0);}
    		while(right.getArrSize() <= i) {right.getNext(0);}
    		left.setNext(i, right);
    		right.setPrev(i, left);
    	
    	}
        //@TODO: implement
    }

    @Override
    public FloorsArrayLink lookup(double key) {
    	
    	FloorsArrayLink curr = bottom;
    	/*
    	int i = size - 1;
    	for( ; i>=0 ; i--)
    	{
    		if(curr.getNext(i).getKey()<key) { curr = curr.getNext(i); i++;}
    	}
    	if(curr.successor(0).equals(key)){return curr.getNext(0);}
    	else {return null;}
    	*/
    	
    	for(int j = size-1 ; j>=0 ; j--) {
    		while( curr.getKey() < key) {curr = curr.getNext(j);}
    		if(curr.getKey() == key)return curr;	
    	}	
    	return null;
    	
    	
    	
        //@TODO: implement
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
        return predecessor(top);
    }

    @Override
    public double maximum() {
        return successor(bottom);
    }
}
