package credit.test.htxc;

import com.rsa.RSASignature;
import com.rsa.RSAUtil;
import net.sf.json.JSONObject;

import java.io.*;
import java.util.Date;

public class BankStageApplyTest {
	
	public static void main(String[] args) {
		// 加密数据
//		String data = "{\"pub\":{\"bankCode\":\"801420\",\"assurerNo\":\"X213m784Rk\",\"bankType\":\"GDICBC\",\"orderNo\":\"demo0018201701070009525261\",\"productType\":1,\"platNo\":\"hrkj\"},\"req\":{\"lender\":{\"idCard\":\"510227197312242248\",\"phoneNum\":\"\",\"userName\":\"张均\",\"pics\":[{\"picType\":0,\"picFileName\":\"e32632a34b8752a71f025dba2d81b314.jpg\",\"picClass\":1,\"picAddress\":\"http://hrfax.oss-cn-hangzhou.aliyuncs.com/carloan/e32632a34b8752a71f025dba2d81b314.jpg\",\"picId\":59612,\"picCode\":\"sfzzm\"},{\"picType\":0,\"picFileName\":\"d2c00e330e2b61d753ab9d609f906936.jpg\",\"picClass\":1,\"picAddress\":\"http://hrfax.oss-cn-hangzhou.aliyuncs.com/carloan/d2c00e330e2b61d753ab9d609f906936.jpg\",\"picId\":59613,\"picCode\":\"sfzfm\"},{\"picType\":0,\"picFileName\":\"e1e04ddd3ba28e76ae5250bcc5b290db.jpg\",\"picClass\":1,\"picAddress\":\"http://hrfax.oss-cn-hangzhou.aliyuncs.com/carloan/e1e04ddd3ba28e76ae5250bcc5b290db.jpg\",\"picId\":59614,\"picCode\":\"zxsqs\"},{\"picType\":0,\"picFileName\":\"db7bc982ca34a9310e0315dc70569290.jpg\",\"picClass\":1,\"picAddress\":\"http://hrfax.oss-cn-hangzhou.aliyuncs.com/carloan/db7bc982ca34a9310e0315dc70569290.jpg\",\"picId\":59615,\"picCode\":\"sjcxsqs\"},{\"picType\":0,\"picFileName\":\"058a32e1b97bb02062e5f00639125815.jpg\",\"picClass\":1,\"picAddress\":\"http://hrfax.oss-cn-hangzhou.aliyuncs.com/carloan/058a32e1b97bb02062e5f00639125815.jpg\",\"picId\":59616,\"picCode\":\"zxsqszp\"}]},\"spouse\":[]}}";
		String data = readFile(new File(System.getProperty("user.dir") + "/src/credit/test/htxc/stageApply.json"));
//		String data = "{\"pub\": {\"assurerNo\": \"ceshi12\",\"platNo\": \"zajk\",\"bankCode\": \"20100671\",\"bankType\": \"GDICBC\"},\"req\": {\"orderNo\":\"RmM5PmT3YfB1k\"}}";
		System.out.println(data);
		String dataPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCFZnUVz07wuQfI5kf3uOaaJcpq*W3yQhJnIX2k-EKwKZaSkyuXutk0TXqwT-GXxIQJqmkjLup*HN7H1uF7JMfxl00AnncHB82LqUQKQwf5wcdDTNhvKLQtjRoLE3ry6ARoYHu5AkZPKW7sMM4o*UegPlSr45p4ZsK0iVdjqmgZfwIDAQAB";
		String signPrivateKey = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAKOoelzwAU5Asw9zknkTYGvfZr0Ap6ZDL6NMSNRYZ2maVJd5xOfSRqTkEq1Ne*h2Qe3wCKdxo0SuCVWNjM-nd3af*fb4YcWdlDuHaA1s28I5hZtVp2sbF*nvgdeUwSz-X0hQGcaqVzcTKDH9l2XuMC**OEofyyosU2jvEIGdwqSNAgMBAAECgYAkojvxvc*tApKSbN5mt82nl-RZbmIYt4VcWmEbF0bevqsc1SccdVdW5a7AmE2aNY6AgnCNesR-RS3Vtr-Ech2tVfwMXypJsXN5hq0uyM6iDkE6kFhGL1zui72u9RQJvdB7CsNfEONIaFlX46MUOdF0fR2n-sGLMc1qzpj*L3k6QQJBAOJfQRF6ehE5d1Sm*7q9uObte1ubako89TSGZmCOk-3vpm9CRTey-18Ids98yMNg3Wy53M4oEzjwjdnnulX9PpUCQQC5E-NySYbigVCsO5Tjr*iAA1ykdGIgaRM45s2tvbMLYQdZYhnkPRjSj*Y7I915cp5klQ75T260InPYQqBkb2gZAkEAjRYtKcWZ*s5EL4B7eCHy8gqlTa0JjAd*FCSH-joexq-snX9CQLrRKtvNoPf28L6YgsE8e0jC4kQbROqGWj2iGQJBAKkXVUCBdL7UrsPs26b6PE1YxPdrbYt29Jz0Ic4ulro6t*AuBMHGIDugRRSbO*mNkrEKjlew-s*M*pIGrUuVjWECQQC3qMemXCmqp7lAaSqYy9Rk8HNVgEeDqJfhcIS4SrRH0DSExPE9yfhadaiC4IIYmmK5L*2V3dxIUI7KXbeO*ptz";
//		String assurerNo = "ceshi001";
//		String bankType = "ICBC";
//		String busiCode = "0001";
//		String platNo = "zajk";
		String assurerNo = "S36020729";//"S36026576";
		String bankType = "ICBC";
		String busiCode = "1002";
		String platNo = "htxc";
		
		JSONObject json = encryptData(data, dataPublicKey, signPrivateKey, assurerNo, bankType, busiCode, platNo);
		System.err.println(json);
		
		// 调用接口
		System.out.println("准备调用,现在是:"+ org.apache.http.client.utils.DateUtils.formatDate(new Date()));
		JSONObject result = HttpPostUtil.doPostRequestJSON("http://114.55.55.41:8998/bank/route", json);
//		JSONObject result = HttpPostUtil.doPostRequestJSON("http://114.55.55.41:8998/bank/route", json);
//		JSONObject result = HttpPostUtil.doPostRequestJSON("http://127.0.0.1:8080/bank/route", json);
		System.out.println("调用完了,现在是:"+ org.apache.http.client.utils.DateUtils.formatDate(new Date()));
		System.err.println(result);
	}
	

	public static JSONObject encryptData(String data, String dataPublicKey ,String signPrivateKey ,String assurerNo
			, String bankType, String busiCode, String platNo){
		JSONObject request = new JSONObject();
		String encryptData = RSAUtil.encrypt(data, dataPublicKey);
	    request.put("data", encryptData);
	    String signData = RSASignature.sign(data, signPrivateKey);     
	    request.put("sign", signData);
	    request.put("assurerNo", assurerNo);
	    request.put("bankType", bankType);
	    request.put("busiCode", busiCode);
	    request.put("platNo", platNo);
	    request.put("bankCode","0180400023");
	    request.put("orderNo","vx01804000011113333999900000001");
	    return request;
	}
	
	@SuppressWarnings("resource")
	public static String readFile(File file){
		BufferedReader reader = null;
		StringBuffer buffer = new StringBuffer(); 
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
		    String tempString = "";
			while (( tempString = reader.readLine()) != null) {  
		    	buffer.append(tempString); 
		    	buffer.append("\n"); 
		    }
		} catch (IOException e) {
		    e.printStackTrace();
		}
		return buffer.toString();  

	}
}
