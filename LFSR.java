public class LFSR 
{
    private int   N;   //number of bits in the LFSR
    private int[] reg; //reg[i] = ith bit of LFSR, reg[0] is leftmost bit
    private int   tap; //index of the tap bit

    /** constructor to create LFSR with the given initial seed and tap */
    public LFSR(String seed, int tap)
    { 
       this.N = seed.length();
        this.reg = new int[N];

        //int j = 0;
        for (int i = 0; i < N; i++) { // i is going backward, j is going forward
            reg[i] = Integer.parseInt(String.valueOf(seed.charAt(i)));
            //j++;
        }

       this.tap = N-tap-1;
    }
  
    /** simulate one step and return the new bit as 0 or 1 */
    public int step() //this particular LFSR moves to the left
    {
    	int XORvalue1 = reg[0];
        int XORvalue2 = reg[tap];

        shiftLeft();

        int newValue = XORvalue1 ^ XORvalue2;
        //System.out.println(this.tap);
        //System.out.println(XORvalue1 + " " + XORvalue2 + " => " + newValue);
        //System.out.println("XOR-ED VAL - " + newValue + '\n');
        reg[N-1] = newValue;
        return newValue;
    }

    private void shiftLeft(){
        for (int i = 1; i < N; i++) {
            reg[i-1] = reg[i];
        }
    }
  
    /** simulate k steps and return k-bit integer */
    public int generate(int k) 
    {
    	int temp = 0;
        for (int i = 0; i < k; i++) {
            temp = 2*temp + step();
        }
    	
    	return temp;
    }

    @Override
    public String toString()  {
    	String toString = "";
        for (int bit : reg) {
            toString += bit;
        }
        return toString;
    }
   
  
    public static void main(String[] args)  
    {
    	//In Eclipse, Ctrl + / (front slash) toggles comments of highlighted portion
    	test01();
    	test02();
    	test03();
    	test04();
    }
    
    /** test toString() and constructor */
    public static void test01()
    {
    	LFSR lfsr = new LFSR("01101000010", 8);
    	System.out.println(lfsr + "\n"); 
    	
    	//should output: 01101000010
    }
    
    /** test step() method */
    public static void test02()
    {
    	LFSR lfsr = new LFSR("01101000010", 8);
    	
    	for (int i = 0; i < 10; i++) {
    	    int bit = lfsr.step();
    	    System.out.println(lfsr + " " + bit);
    	}
    	/*
    	   should output:
    	   
    	    11010000101 1
			10100001011 1
			01000010110 0
			10000101100 0
			00001011001 1
			00010110010 0
			00101100100 0
			01011001001 1
			10110010010 0
			01100100100 0
    	 */
    }
    
    /** test generate() method */
    public static void test03()
    {
    	System.out.println();

    	LFSR lfsr = new LFSR("01101000010", 8);
    	
    	for (int i = 0; i < 10; i++) {
    	    int r = lfsr.generate(5);
    	    System.out.println(lfsr + " " + r);
    	}
    	/*
    	   should output:
    	   
    	    00001011001 25
			01100100100 4
			10010011110 30
			01111011011 27
			01101110010 18
			11001011010 26
			01101011100 28
			01110011000 24
			01100010111 23
			01011111101 29
    	 */
    }
    
    /** test generate() method again */
    public static void test04()
    {
    	System.out.println();
    	
    	LFSR lfsr = new LFSR("01101000010100010000", 16);
    	
    	for (int i = 0; i < 10; i++) {
    	    int r = lfsr.generate(8);
    	    System.out.println(lfsr + " " + r);
    	}
    	/*
    	   should output:
    	    01010001000000101010 42
			00000010101011011001 217
			10101101100100010111 23
			10010001011111000001 193
			01111100000100011010 26
			00010001101010011100 156
			10101001110010011100 156
			11001001110011100111 231
			11001110011110000111 135
			01111000011110111101 189
		*/
    }
}
