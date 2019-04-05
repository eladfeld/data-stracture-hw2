public class FloorsArrayLink {
	private double key;
	private int arrSize;
	private FloorsArrayLink[] nextArr;
	private FloorsArrayLink[] prevArr;

	
    public FloorsArrayLink(double key, int arrSize){
    	this.key = key;
    	this.arrSize = arrSize;
    	this.nextArr = new FloorsArrayLink[arrSize]; 
    	this.prevArr = new FloorsArrayLink[arrSize]; 

    }
    public FloorsArrayLink[] getNextArr() {
    	return this.nextArr;
    }
    public FloorsArrayLink[] getPrevArr() {
    	return this.prevArr;
    }

    public double getKey() {
        return key;
    }

    public FloorsArrayLink getNext(int i) {
        return nextArr[i];
    }

    public FloorsArrayLink getPrev(int i) {
        return prevArr[i];
    }

    public void setNext(int i, FloorsArrayLink next) {
    	nextArr[i] = next;
    }

    public void setPrev(int i, FloorsArrayLink prev) {
    	prevArr[i] = prev;
    }

    public int getArrSize(){
        return arrSize;
    }
}

