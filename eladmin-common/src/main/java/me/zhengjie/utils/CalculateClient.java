package me.zhengjie.utils;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

import javax.xml.namespace.QName;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalculateClient {

    public static void main(String[] args) {

//        // 指定调用WebService的URL（这里是我们发布后点击HelloWorld）
//        String url = "http://172.30.162.131:40001/zx_webServiceUrl";
//        //调用的方法
//        String method = "wasteWaterHourData";
//        //调用方法的参数列表
//        Calendar calendar1 = Calendar.getInstance();
//        calendar1.set(11, calendar1.get(11) - 72);
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String starttime = df.format(calendar1.getTime());
//        Calendar calendar2 = Calendar.getInstance();
//        String endtime = df.format(calendar2.getTime());
//        Object[] parms = new Object[]{starttime,endtime,"","",""};
//
//        CalculateClient calculateClient = new CalculateClient();
//        //调用方法
//        String svrAddResult = calculateClient.CallMethod(url, method, parms);
//
//        System.out.println(svrAddResult);

        try{
            Calendar calendar1 = Calendar.getInstance();
            calendar1.set(11, calendar1.get(11) - 72);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String starttime = df.format(calendar1.getTime());
            Calendar calendar2 = Calendar.getInstance();
            String endtime = df.format(calendar2.getTime());
            Object[] parms = new Object[]{starttime,endtime,"","",""};
            RPCServiceClient client = new RPCServiceClient();
            Options options = client.getOptions();
            String url1 = "http://172.30.162.131:40001/zx_webServiceUrl";
            EndpointReference end = new EndpointReference(url1);
            options.setTo(end);

            Class<?>[] classes = new Class[] { String.class };

            QName qname = new QName("http://tempuri.org/", "wasteWaterHourData");
            options.setAction("http://tempuri.org/WasteWaterHourData");
            String result = (String) client.invokeBlocking(qname, parms,classes)[0];
            System.out.println(result);
        }catch(AxisFault e){
            e.printStackTrace();
        }

    }

//    //实现WebService上发布的服务调用
//    public String CallMethod(String url, String method, Object[] args) {
//        String result = null;
//        QName qname =  new QName("http://tempuri.org/",method);
//
//        if(StringUtils.isEmpty(url)) {
//            return "url地址为空";
//        }
//        if(StringUtils.isEmpty(method)) {
//            return "method地址为空";
//        }
//
//        Call rpcCall = null;
//
//        try {
//
//            //实例websevice调用实例
//            Service webService = new Service();
//            rpcCall = (Call) webService.createCall();
//            rpcCall.setTargetEndpointAddress(url);
//            rpcCall.setOperationName(qname);
//
//            //执行webservice方法
//
//            result = (String) rpcCall.invoke(args);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return result;
//    }

}