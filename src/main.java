
public class main {
	public static void main(String[] args) {
		FloorsArrayList elad = new FloorsArrayList(20);
		elad.insert(9, 9);
		elad.insert(4, 4);
		elad.insert(5, 2);
		elad.insert(6.5, 2);
		elad.insert(7,1);
		elad.insert(8, 5);
		elad.insert(2, 3);
		elad.insert(4.5, 15);
		System.out.println(elad.getLongestArray());
		elad.remove(elad.lookup(4.5));
		System.out.println(elad.getLongestArray());
		elad.remove(elad.lookup(9));
		elad.remove(elad.lookup(7));

		System.out.println(elad.getLongestArray());

		
		
		
		
		
		
		System.out.println("_______________________________________________________________________________________________________");
		FloorsArrayList test = new FloorsArrayList(20);
		test.insert(1, 1);
		//System.out.println(test.toString());
		
		test.insert(43, 1);
		test.insert(49, 1);
		test.insert(51, 4);
		test.insert(58, 2);
		test.insert(17, 1);
		test.insert(19, 2);
		test.insert(24, 2);
		test.insert(11, 1);
		test.insert(14, 4);
		test.insert(36, 1);
		test.insert(41, 2);
		test.insert(3, 2);
		test.insert(4, 3);
		test.insert(8, 1);
		test.insert(31, 3);

		System.out.println('\n' + "\n" + test.toString());
	
		test.remove(test.lookup(14));
		test.remove(test.lookup(11));		
		test.remove(test.lookup(19));
		test.remove(test.lookup(58));
		test.remove(test.lookup(31));
		test.remove(test.lookup(3));
		test.remove(test.lookup(8));
		test.remove(test.lookup(36));
		System.out.println(test.getLongestArray());
		test.remove(test.lookup(1));
		test.remove(test.lookup(4));
		test.remove(test.lookup(24));
		test.remove(test.lookup(51));
		test.remove(test.lookup(49));
		test.remove(test.lookup(43));
		test.remove(test.lookup(17));
		test.remove(test.lookup(41));
		
		System.out.println(test.toString());
		System.out.println(test.getLongestArray());

		
		


	}

}
