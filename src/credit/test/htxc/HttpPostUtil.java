package credit.test.htxc;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.Serializable;

public class HttpPostUtil implements Serializable {

    private static final long   serialVersionUID = -292336323378065582L;
    private static final String CHAR_SET         = "UTF-8";
    private static final String CONTENT_TYPE     = "application/json";

    
    /**
     * 请求接口
     * @param requestUrl 接口请求地址
     * @param json 
     * @return	UTF-8解码返回数据
     */
    @SuppressWarnings({ "deprecation", "resource" })
    public static JSONObject doPostRequestJSON(String requestUrl, JSONObject json) {
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(requestUrl);
        JSONObject response = null;
        try {
            StringEntity string = new StringEntity(json.toString(),CHAR_SET);
            string.setContentEncoding(CHAR_SET);
            string.setContentType(CONTENT_TYPE);
            post.setEntity(string);
            HttpResponse res = client.execute(post);
            System.err.println("http status is " + res.getStatusLine().getStatusCode());
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = res.getEntity();
                //UTF-8解码返回数据
                String result = EntityUtils.toString(entity,CHAR_SET);
                response = JSONObject.fromObject(result);
            }else{
                response = new JSONObject();
                try {
                    response.put("code", -1);
                    response.put("msg", "系统异常");
                } catch (JSONException e1) {}
            }
        } catch (IOException e) {
            response = new JSONObject();
            try {
            	response.put("code", -1);
                response.put("msg", "系统异常");
            } catch (JSONException e1) {}
            e.printStackTrace();
        } catch (JSONException e) {
            response = new JSONObject();
            try {
            	response.put("code", -1);
                response.put("msg", "系统异常");
            } catch (JSONException e1) {}
            e.printStackTrace();
        }
        return response;
    }

}
