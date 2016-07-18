

public class DiffRow {
	private int id;
	private int operation = Operation.NONE;
	public int getOperation() {
		// EQUALDELETE -> 이거는 같다고 봐야함.
		if(operation==Operation.CHANGE && this.opStrPath.equals("EQUALDELETE")) {
			return Operation.EQUAL;
		}
		return operation;
	}

	private int orgLineNumber;
	private int revLineNumber;

	public void setText(String text) {
		this.text = new StringBuffer(text);
	}

	public String getItext() {
		return itext.toString();
	}

	public void setItext(String itext) {
		this.itext = new StringBuffer(itext);
	}

	public String getDtext() {
		return dtext.toString();
	}

	public void setDtext(String dtext) {
		this.dtext = new StringBuffer(dtext);
	}

	public String getEtext() {
		return etext.toString();
	}

	public void setEtext(String etext) {
		this.etext = new StringBuffer(etext);
	}

	private StringBuffer text = new StringBuffer();
	private StringBuffer itext = new StringBuffer();
	private StringBuffer dtext = new StringBuffer();
	private StringBuffer etext = new StringBuffer();
	
	public DiffRow( int id,int operation, int orgLineNumber, int revLineNumber ){
		this.id = id;
		this.operation = operation;
		this.orgLineNumber = orgLineNumber;
		this.revLineNumber = revLineNumber;
	}
	
	public void addChar(String c){
		text.append(c);
	}
	public void addChar(int o, char c){
		text.append(c);
		if ( o == Operation.INSERT){
			itext.append(c);
		}else if ( o == Operation.DELETE){
			dtext.append(c);
		}else if ( o == Operation.EQUAL){
			etext.append(c);
		}
	}
	
	private String opStrPath ="";
	private String befOpStr = "";
	public void setOperation(int o){
		 
		
		String  opStr  = Operation.getOperationString(o);
		if ( !befOpStr.equals(opStr)){
			
			opStrPath += opStr;
		}
		befOpStr = opStr;
		this.operation = o;
	}
	public String getOperationPath(){
		 return opStrPath;
	}
	public int getOrgLineNumber( ){
	 
		return this.orgLineNumber;
	}
	public void setOrgLineNumber(int lineNumber){
		this.orgLineNumber = lineNumber;
	}
	public int getRevLineNumber(){
		 
		return this.revLineNumber;
	}
	public void setRevLineNumber(int lineNumber){
		this.revLineNumber = lineNumber;
	}
	public int getId(){
		return this.id;
	}
	
	public boolean isSplitTarget(){
		if ( this.etext.length() ==0 ){
			if ( this.itext.length() > 0 && this.dtext.length() > 0 ){
				return true;
			}
		}
		return false;
	}
	
	public void MarkChange(){
		if ( this.etext.length() > 0 ){
			if ( this.itext.length() > 0 || this.dtext.length() > 0 ){
				this.operation = Operation.CHANGE;
			}
		}
		
	}
	public String toString(){
	
		return  this.id+"["+opStrPath+"]["+this.operation +":" + this.getOrgLineNumber() +"," + this.getRevLineNumber() +"][" + this.text +"]["+ this.dtext+"]["+ this.etext+"]["+ this.itext+"]"; 
	}
	
}
