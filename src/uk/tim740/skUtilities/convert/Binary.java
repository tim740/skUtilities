package uk.tim740.skUtilities.convert;
/**
 * Created by tim740/Headshot.
 */
public class Binary {

    private String binary;
    public Binary(String toBinary) throws BinInvalid{
        binary = toBinary.trim();
         for (char character : binary.toCharArray()){
                if (character !='0' && character !='1' && character !=' ') throw new BinInvalid("Binary Strings can only contain 1's, 0's or spaces!");
            }
    }
    @Override
    public String toString(){
        return binary;
    }
    class BinInvalid extends Exception {
          public BinInvalid(String msg) { super(msg); }
    }
}
