interface IPolynomialSolver {
    /**
    * Set polynomial terms (coefficients & exponents)
    * @param poly: name of the polynomial
    * @param terms: array of [coefficients][exponents]
    */
    void setPolynomial(int[][] terms);
  
    /**
    * Print the polynomial in ordered human readable representation
    * @param poly: name of the polynomial
    * @return: polynomial in the form like 27x^2+x-1
    */
    String print();
  
    /**
    * Clear the polynomial
    * @param poly: name of the polynomial
    */
      void clearPolynomial(char poly);
  
    /**
    * Evaluate the polynomial
    * @param poly: name of the polynomial
    * @param value: the polynomial constant value
    * @return the value of the polynomial
    */
    float evaluatePolynomial(char poly, float value);
  
    /**
    * Add two polynomials
    * @param poly1: first polynomial
    * @param poly2: second polynomial
    * @return the result polynomial
    */
    int[][] add(char poly1, char poly2);
  
    /**
    * Subtract two polynomials
    * @param poly1: first polynomial
    * @param poly2: second polynomial
    * @return the result polynomial*/
    int[][] subtract(char poly1, char poly2);
  
    /**
    * Multiply two polynomials
    * @param poly1: first polynomial
    * @param poly2: second polynomial
    * @return: the result polynomial
    */
    int[][] multiply(char poly1, char poly2);
}



class Term extends Node {
    int coefficient;
    int exponent;

    public Term(int coefficient, int exponent){
        this.coefficient = coefficient;
        this.exponent = exponent;
    }

    
    public Term(int coefficient){
        this.coefficient = coefficient;
    }
    
}


public class polynomial extends SingleLinkedList implements IPolynomialSolver{
    
    Term Head;

    public polynomial(Term head) {
        super(head);
    }



    public static void main(String[] args) throws Exception {
        Term iniTerm = new Term(3, 5);
        Term secondTerm = new Term(3, 5);
        iniTerm.next = secondTerm;
        polynomial A = new polynomial(iniTerm);
        System.out.println(A.size());

    }


    public int[][] getCoeffAndExpFromList(String strList){
        int[] intList = SingleLinkedList.stringToIntArray(strList);
        int maxExponent = intList.length-1;
        int[][] output = new int[maxExponent+1][2];
        
        // LOOP TO SET COEFFICIENTS AND EXPONENTS
        for (int t = 0; t < maxExponent+1; t++) {
            // set the values of the coefficients
            output[t][0] = intList[t];
            // set the values of the exponents
            output[t][1] = maxExponent-t;
            
        }



        return null;
    }

    @Override
    public void setPolynomial(int[][] terms) {
        int numberOfTerms = terms.length;
        Term[] termsNodes= new Term[numberOfTerms];

        // Generate all terms
        for (int i = 0; i < numberOfTerms; i++) {
            termsNodes[i] = new Term(terms[i][0], terms[i][1]);
        }

        // Create relation between Terms
        for (int i = 0; i < numberOfTerms; i++) {
            if (i == numberOfTerms-1) {
                termsNodes[i].next = null;
            } else {
                termsNodes[i].next = termsNodes[i+1];
            }
        }
        
    }

    @Override
    public String print() {
        String[] strTerms = new String[this.size()];
        return null;
    }

    @Override
    public void clearPolynomial(char poly) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public float evaluatePolynomial(char poly, float value) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int[][] add(char poly1, char poly2) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int[][] subtract(char poly1, char poly2) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int[][] multiply(char poly1, char poly2) {
        // TODO Auto-generated method stub
        return null;
    }
}
