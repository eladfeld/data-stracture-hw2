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
    		while(left.getArrSize() <= i ) left = left.getPrev(i);
    		toInsert.setPrev(i, left);
    		while(right.getArrSize() <= i) right = right.getNext(i);
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
        //@TODO: implement
    }

    @Override
    public FloorsArrayLink lookup(double key) {
        //@TODO: implement
        return null;
    }

    @Override
    public double successor(FloorsArrayLink link) {
        //@TODO: implement
        return 0;
    }

    @Override
    public double predecessor(FloorsArrayLink link) {
        //@TODO: implement
        return 0;
    }

    @Override
    public double minimum() {
        //@TODO: implement
        return 0;
    }

    @Override
    public double maximum() {
        //@TODO: implement
        return 0;
    }
}