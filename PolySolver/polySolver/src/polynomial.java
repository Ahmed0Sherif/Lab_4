import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
      void clearPolynomial();
  
    /**
    * Evaluate the polynomial
    * @param poly: name of the polynomial
    * @param value: the polynomial constant value
    * @return the value of the polynomial
    */
    float evaluatePolynomial(float value);
  
    /**
    * Add two polynomials
    * @param poly1: first polynomial
    * @param poly2: second polynomial
    * @return the result polynomial
    */
    int[][] add(Polynomial p2);
  
    /**
    * Subtract two polynomials
    * @param poly1: first polynomial
    * @param poly2: second polynomial
    * @return the result polynomial*/
    int[][] subtract(Polynomial p2);
  
    /**
    * Multiply two polynomials
    * @param poly1: first polynomial
    * @param poly2: second polynomial
    * @return: the result polynomial
    */
    int[][] multiply(Polynomial p2);
}



class Term {
    int coefficient;
    int exponent;
    Term next = null;

    public Term(int coefficient, int exponent){
        this.coefficient = coefficient;
        this.exponent = exponent;
    }

    
    public Term(int coefficient){
        this.coefficient = coefficient;
    }
    
}


public class Polynomial implements IPolynomialSolver{
    // Invalid polynomial sequences
    ///////////////////////////////////          (|+|-)0x^*(|\\+|-)      ,       (|+|-)*x^0(|+|-)            ,            (|+|-)1x^*(|+|-)         ,         (|+|-)*x^1(|+|-)
    final String[] invalidSequences = {"(^|\\+|-)(0x\\^)(\\d{0,})($|\\+|-)", "(^|\\+|-)(\\d{0,})(x\\^0)($|\\+|-)", "(^|\\+|-)(1)(x\\^)(\\d{0,})($|\\+|-)", "(^|\\+|-)(\\d{0,})(x\\^1)($|\\+|-)"};
    final String[] replacements = {"$4", ("$1" + "$2" + "$4"), ("$1" + "$3" + "$4" + "$5"), ("$1" + "$2" + "x" + "$4")};
    final static String err = "Error";

    
    Term Head;
    int[][] arrayRepresentation;

    public Polynomial(Term head) {
        this.Head = head;
    }

    int size(){
        int size = 0;
        Term currentTerm = Head;
        while (currentTerm != null) {
            size++;
            currentTerm = currentTerm.next;
        }
        return size;
    }


// MAIN FUNCTION
    public static void main(String[] args) throws Exception {

        Polynomial mPolynomial = new Polynomial(null);// polynomial to call non-static functions
        Polynomial A = new Polynomial(null);
        Polynomial B = new Polynomial(null);
        Polynomial C = new Polynomial(null);
        Polynomial R = new Polynomial(null);
        Polynomial[] polyArr = {A, B, C, R};
        String command;

        Scanner scan = new Scanner(System.in);

        while (true) {
            // try to use the scanner
            // this will result in an error if the user closed the application
            try {
                command = scan.next();
            } catch (Exception e) {
                // if error occurs break the loop exiting the program
                break;
            }
            // The whole switch-case is inside a try-catch to catch any errors and print "Error"
            try {
            
                switch (command) {
                    
                    case "set":

                        String choiceSet = scan.next();// A, B, C
                        Polynomial targetPolySet = mPolynomial.charToPolynomial(choiceSet, polyArr);
                        String strValues = scan.next();
                        int[][] arrRepTarget = mPolynomial.getCoeffAndExpFromList(strValues);
                        targetPolySet.setPolynomial(arrRepTarget);

                        break;
                    case "print":

                        String choicePrint = scan.next();// A, B, C
                        Polynomial targetPolyPrint = mPolynomial.charToPolynomial(choicePrint, polyArr);
                        System.out.println(targetPolyPrint.print());

                        break;
                    case "clear":
                        String choiceClear = scan.next();// A, B, C
                        Polynomial targetPolyClear = mPolynomial.charToPolynomial(choiceClear, polyArr);
                        targetPolyClear.clearPolynomial();
                        System.out.println(targetPolyClear.print());
                        break;
                    case "eval":

                        String choiceEval = scan.next();// A, B, C
                        float valueToEval = scan.nextFloat();
                        Polynomial targetPolyEval = mPolynomial.charToPolynomial(choiceEval, polyArr);
                        float result = targetPolyEval.evaluatePolynomial(valueToEval);
                        if (result % 1 == 0) {
                            System.out.println((int) result);
                        } else {
                            System.out.println(result);
                        }

                        break;
                    case "add":

                        String chioceAdd1 = scan.next();
                        String chioceAdd2 = scan.next();
                        Polynomial targetAdd1 = mPolynomial.charToPolynomial(chioceAdd1, polyArr);
                        Polynomial targetAdd2 = mPolynomial.charToPolynomial(chioceAdd2, polyArr);
                        R.clearPolynomial();
                        R.arrayRepresentation = targetAdd1.add(targetAdd2);
                        R.setPolynomial(R.arrayRepresentation);
                        System.out.println(R.print());
                        
                        break;
                    case "sub":

                        String chioceSub1 = scan.next();
                        String chioceSub2 = scan.next();
                        Polynomial targetSub1 = mPolynomial.charToPolynomial(chioceSub1, polyArr);
                        Polynomial targetSub2 = mPolynomial.charToPolynomial(chioceSub2, polyArr);
                        R.clearPolynomial();
                        R.arrayRepresentation = targetSub1.subtract(targetSub2);
                        R.setPolynomial(R.arrayRepresentation);
                        System.out.println(R.print());

                        break;
                    case "mult":
                        
                        String chioceMult1 = scan.next();
                        String chioceMult2 = scan.next();
                        Polynomial targetMult1 = mPolynomial.charToPolynomial(chioceMult1, polyArr);
                        Polynomial targetMult2 = mPolynomial.charToPolynomial(chioceMult2, polyArr);
                        R.clearPolynomial();
                        R.arrayRepresentation = targetMult1.multiply(targetMult2);
                        R.setPolynomial(R.arrayRepresentation);
                        System.out.println(R.print());

                        break;
                    default:
                        System.out.println(err);
                        break;
                }
            } catch (Exception e) {
                System.out.println(err);
                break;
            }
        }
        
        

    }

    public int[] stringToIntArray(String s){
        if (s.equals("[]")) {
            int[] output = new int[0];
            return output;
        } else {
            String[] arrayOfStrings = s.trim().replace("[", "").replace("]", "").split(",");
            int len = arrayOfStrings.length;
            int[] output = new int[len];

            for (int i = 0; i < len; i++) {
                output[i] = Integer.valueOf(arrayOfStrings[i].trim());
            }
            return output;

        }
        
    }

    public Polynomial charToPolynomial(String p, Polynomial[] polyArr) {
        
        switch (p) {
            case "A":
                return polyArr[0];
            case "B":
                return polyArr[1];
            case "C":
                return polyArr[2];
            case "R":
                return polyArr[3];
            default:
                return null;
        }
    }


    public void negatePolynomial() {
        Term currentTerm = this.Head;
        while (currentTerm != null) {
            currentTerm.coefficient = -1 * currentTerm.coefficient;
            currentTerm = currentTerm.next;
        }
    }

    // Reverse a 1D array
    public int[] reverseArray(int[] ogArr) {
        int l = ogArr.length;
        int[] output = ogArr;
        for (int i = 0; i < Math.floor(l/2); i++) {
            int temp = output[i];
            output[i] = output[l-i-1];
            output[l-i-1] = temp;
        }
        
        return output;
    }

    // Reverse 2D array
    public int[][] reverseArray(int[][] ogArr) {
        int l = ogArr.length;
        int[][] output = ogArr;
        for (int i = 0; i < Math.floor(l/2); i++) {
            int[] temp = output[i];
            output[i] = output[l-i-1];
            output[l-i-1] = temp;
        }
        
        return output;
    }
    

    public String formatEquation(String equationInput) {
        String equation = equationInput;
        for (int i = 0; i < replacements.length; i++) {
            String invalidSequence = invalidSequences[i];
            String replacement = replacements[i];

            Pattern pattern = Pattern.compile(invalidSequence);
            Matcher matcher = pattern.matcher(equation);

            if (matcher.find()) {
                equation = matcher.replaceAll(replacement);
            }
        }

        return equation;
    }

    public int[][] getCoeffAndExpFromList(String strList){
        int[] intList = stringToIntArray(strList);
        int maxExponent = intList.length-1;
        int[][] output = new int[maxExponent+1][2];
        
        // LOOP TO SET COEFFICIENTS AND EXPONENTS
        for (int t = 0; t < maxExponent+1; t++) {
            // set the values of the coefficients
            output[t][0] = intList[t];
            // set the values of the exponents
            output[t][1] = maxExponent-t;
            
        }

        return output;
    }

    @Override
    public void setPolynomial(int[][] terms) {
        int numberOfTerms = terms.length;
        Term[] termsNodes= new Term[numberOfTerms];

        // Generate all terms
        for (int i = 0; i < numberOfTerms; i++) {
            termsNodes[i] = new Term(terms[i][0], terms[i][1]);
        }
        this.Head = termsNodes[0];
        this.arrayRepresentation = terms;

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
        if (this.size() == 0) {
            return "[]";
        } else {
            String[] strTerms = new String[this.size()];
            Term currTermNode = this.Head;
            String finalString = "";
            // populate strTerms
            for (int i = 0; i < strTerms.length; i++) {
                String strCurrCoeff = Integer.toString(currTermNode.coefficient);
                String strCurrExp = Integer.toString(currTermNode.exponent);
                if (strCurrExp == "0") {
                    strTerms[i] = strCurrCoeff;
                } else {
                    strTerms[i] = strCurrCoeff + "x^" + strCurrExp;
                } 
                currTermNode = currTermNode.next;
            }


            // put the terms in the final format
            for (int i = 0; i < strTerms.length; i++) {
                if (i == 0) {
                    finalString += strTerms[i];
                } else if(strTerms[i].charAt(0) == '-'){
                    finalString += strTerms[i];
                } else{
                    finalString += "+" + strTerms[i];
                }
            }
            
            
            return formatEquation(finalString);
        }
        
    }

    @Override
    public void clearPolynomial() {
        this.Head = null;
    }

    @Override
    public float evaluatePolynomial(float value) {
        float result = 0;
        Term currentTerm = Head;
        while (currentTerm != null) {
            result += currentTerm.coefficient * Math.pow(value, currentTerm.exponent);
            currentTerm = currentTerm.next;
        }
        return result;
    }

    @Override
    public int[][] add(Polynomial p2) {
        int additionSize = Math.max(this.size(), p2.size());
        int sizeDifference = Math.abs(this.size() - p2.size());
        int[][] additionResult = new int[additionSize][2];
        if (sizeDifference != 0) {
            int[][] dummyPolynomialArr = new int[sizeDifference][2];
            Polynomial dummyPolynomial = new Polynomial(null);
            // create zero padding
            dummyPolynomial.setPolynomial(dummyPolynomialArr);
            // determine which polynomial is smaller
            Polynomial smallerPolynomial =this.size() < p2.size() ? this : p2;
            
            // get tail of the dummy node
            Term lastDummyTerm = dummyPolynomial.Head;
            while (lastDummyTerm.next != null) {
                lastDummyTerm = lastDummyTerm.next;
            }
            // add the padding to the beginning of the smaller polynomial and store the result in the dummyPolynomial
            lastDummyTerm.next = smallerPolynomial.Head;

            // add p2 to the dummyPolynomial
            return dummyPolynomial.add(p2);

        }
        else{
            Term t1 = this.Head;
            Term t2 = p2.Head;
            for (int i = 0; i < additionSize; i++) {
                // get the coefficients and exponents of current iteration
                int currentCoeff = t1.coefficient + t2.coefficient;
                int currentExp = Math.max(t1.exponent, t2.exponent);
                // put the coefficient and exponent in their respective places
                additionResult[i][0] = currentCoeff;
                additionResult[i][1] = currentExp;
                // update the reference of the current terms
                t1 = t1.next;
                t2 = t2.next;

            }
            return additionResult;

        }
    }

    @Override
    public int[][] subtract(Polynomial p2) {
        p2.negatePolynomial();
        int[][] result = this.add(p2);
        p2.negatePolynomial();
        return result;
    }

    @Override
    public int[][] multiply(Polynomial p2) {
        int productSize = this.size() + p2.size() - 1;
        int[][] productArr = new int[productSize][2];
        
        int[] arrCoeff1 = new int[this.size()];
        int[] arrCoeff2 = new int[p2.size()];

        // populate arrCoeff1
        for (int i = 0; i < arrCoeff1.length; i++) {
            arrCoeff1[i] = this.arrayRepresentation[i][0];
        }

        // populate arrCoeff2
        for (int i = 0; i < arrCoeff2.length; i++) {
            arrCoeff2[i] = p2.arrayRepresentation[i][0];
        }

        // Reverse the arrays to make the first coefficient that of x^0
        arrCoeff1 = reverseArray(arrCoeff1);
        arrCoeff2 = reverseArray(arrCoeff2);

        // Populate the productArr with coefficients
        for (int c1 = 0; c1 < arrCoeff1.length; c1++) {
            for (int c2 = 0; c2 < arrCoeff2.length; c2++) {
                productArr[c1+c2][0] += arrCoeff1[c1] * arrCoeff2[c2];
            }
        }

        // Populate the productArr with exponents
        for (int i = 0; i < productSize; i++) {
            productArr[i][1] = i;
        }

        return reverseArray(productArr);
    }
}
