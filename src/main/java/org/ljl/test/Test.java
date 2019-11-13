package org.ljl.test;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {

	private static String filePath = "D:\\L\\2.txt";

	public static void main(String[] args) {
		String url = "http://www.zj.10086.cn/shop/shop/sales/ajaxNumberInfos.do";
		Pattern phone = Pattern.compile("<h6>(\\d{11})</h6>");
		Matcher matcher = phone.matcher("");
		Set<String> numSet = new HashSet<>();
		Set<String> numSet2 = new HashSet<>();
		AtomicInteger n = new AtomicInteger(500);
		Set<Integer> failPageNum = Stream.generate(n::getAndIncrement).limit(1).collect(Collectors.toSet());

		CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        List<NameValuePair> formParams = new ArrayList<NameValuePair>();
        //表单参数
        formParams.add(new BasicNameValuePair("pageObject.totalPageNum", "555"));
        formParams.add(new BasicNameValuePair("pageObject.pageClick", "1"));
        formParams.add(new BasicNameValuePair("randomNum", "0"));
        formParams.add(null);
        try {
            while (true) {
                Set<Integer> set = new HashSet<>(500);
                for (int i : failPageNum) {
                    int count = 0;
                    formParams.set(3, new BasicNameValuePair("pageObject.currentPageNum", String.valueOf(i)));
                    UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formParams, "utf-8");
                    post.setEntity(entity);
                    CloseableHttpResponse response = httpClient.execute(post);
                    String result = EntityUtils.toString(response.getEntity());
                     System.out.println(result);

                    matcher.reset(result);
                    while (matcher.find()) {
                        String phoneNum = matcher.group(1);
                        numSet.add(phoneNum);
                        numSet2.add(phoneNum);
                        count++;
                    }

                    boolean fail = (count < 24 && i < 555) || (count < 1 && i == 555);
                    if (fail) {
                        set.add(i);
                        save(numSet2);
                        numSet2 = new HashSet<>(500);
                        httpClient.close();
                        TimeUnit.SECONDS.sleep(10);
                        httpClient = HttpClients.createDefault();
                    }

                    System.out.println("本次请求返回号码数量");
                    System.out.println("-----   " + count + "   ---------");
                    System.out.println("本次请求返回号码数量");
                    TimeUnit.MILLISECONDS.sleep(1000);
                }
                failPageNum = set;
                System.out.println("失败页码数量");
                System.out.println("-----   " + failPageNum.size() + "   ---------");
                System.out.println("失败页码数量");

                if (failPageNum.size() == 0) {
                    break;
                }
            }
        } catch (Exception e) {
		    e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
		System.out.println(numSet.size());
		System.out.println(numSet);
	}

	public static void save(Set<String> set) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            for (String i : set) {
                writer.write(String.valueOf(i));
                writer.write(",");
            }
            writer.newLine();
            System.out.println();
            System.out.println("写入数量：" + set.size());
            System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
