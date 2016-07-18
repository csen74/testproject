
  public class Operation {
	    public static final int NONE =0;
	    public static final int INSERT =1;
	    public static final int EQUAL= 2;
	    public static final int DELETE =3;
	    public static final int CHANGE =4;
	    
	    private static final String[] str = {"NONE","INSERT","EQUAL","DELETE","CHANGE"};
	    public static String getOperationString(int o){
	    	return str[o];
	    }
	  }


