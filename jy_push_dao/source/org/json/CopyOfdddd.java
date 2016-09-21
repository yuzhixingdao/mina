package org.json;


public class CopyOfdddd {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			FitsDTO dto=new FitsDTO();
			dto.setBz("';';';';{}{}{][][][][][");
			dto.setMc("xsasxa");
			String a=new JSONObject(dto).toString();
			System.out.println(a);
//			System.out.println(JSONObject.quote(dto.getBz()));
			try {
				JSONObject x=new JSONObject(a);
				System.out.println(x.get("mc"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("xx");
			}
//			JSONObject json= new JSONObject(a);
//			System.out.println(json.get("mc"));
	}

}
