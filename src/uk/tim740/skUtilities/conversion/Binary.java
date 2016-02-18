package uk.tim740.skUtilities.conversion;

public class Binary {

    private String binary;
    public Binary(String toBinary) throws BinInvalid{
        binary = toBinary.trim();
         for (char character : binary.toCharArray())
            {
                if (character !='0' && character !='1' && character !=' ') throw new BinInvalid("Binary Strings can only contain 1's, 0's or spaces!");
            }
    }
    @Override
    public String toString(){
        return binary;
    }
    @SuppressWarnings("serial")
    class BinInvalid extends Exception {
          public BinInvalid() { super(); }
          public BinInvalid(String msg) { super(msg); }
          public BinInvalid(String msg, Throwable cause) { super(msg, cause); }
          public BinInvalid(Throwable cause) { super(cause); }
    }
}
