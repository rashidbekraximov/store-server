package uz.cluster.services;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;

@Service
public class GetGoodService {

    public String fetchGoods() {
        try {
            // URL to send the request to
            String url = "https://mobile.yangkeduo.com/proxy/api/api/wizard/naga/selected/goods?pdduid=4548925868544&is_back=1";

            // Creating the payload
            String payload = "{\n" +
                    "\"anti_content\": \"0asWfqnF0joyj9vZ1XPZPdgaVw0y1j47KBTrmVcsVyBnrfxzuzfmBWxmhxHxT66izCpl9OkFk2w_NeO0g5Pgd6qgxVrN-XPt7fgS0luNrbLHJdycfIn_IoiNQHRKmZ1YIKItbifXO6dXgkg0AXVwcb4gq3lqn-5L5wbH7kxrm5aNPZk-udQSuYY6GRvLQkRpjJfJiWnHQ3tnTpF_eFtsLvztcKsN-6Zk029dVKdfB7Kt66DWGA-r3xhrM3oixTxPFuY9cn-tC1Pbl5SAfWZxt3Py_YGy_WmvRon-Tum7PAjTS5sAmalbx5pQgB2UZMM6HbOaCFwy2_2Se4APE9Se4za2L4EkdgqyzwGt3fShawHVPr76RIoSb9bU29bw8Lg0b9NtJ44qny62Q7rVwsi7P_TP1hl-pvH5rIerhmuKUxO0kjyYgR7G3C-trJ8r6XLMNxrMoT8oqRo0oifTaewUQCvQiYUWgbNeSZ_g6Ml8YLwaN47YL9DwvDGGjiet_R5E52fS83imz3i6SYNThjk9gCIKBNIzIrfhUI8nz-kUjwjrREYYs5QsvQotLI5_XsFm_oGe0GP2TmDjgjUX1fTntLmQjhcEibMhmx_4gq-T\",\n" +
                    "\"cid\": \"usercenter\",\n" +
                    "\"count\": 300,\n" +
                    "\"filter_coupon_goods\": false,\n" +
                    "\"limit_price\": 0,\n" +
                    "\"list_id\": \"13125-boutique_hub_monthly_card_0-0_m3jqf8mxtgl5s2v7\",\n" +
                    "\"offset\": 0,\n" +
                    "\"page_el_sn\": \"332128\",\n" +
                    "\"page_sn\": \"13125\",\n" +
                    "\"promotion_gray\": 0,\n" +
                    "\"scene_id\": 0,\n" +
                    "\"tab_id\": 1451,\n" +
                    "\"timeout\": 10000,\n" +
                    "\"use_qhj\": false\n" +
                    "}";

            // Open connection to the URL
            URL urlObj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

            // Set the request method to POST
            con.setRequestMethod("POST");

            // Set the request headers
            con.setRequestProperty("Accept", "application/json, text/plain, */*");
            con.setRequestProperty("Accept-Encoding", "gzip, deflate, br, zstd");
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.9,en-NZ;q=0.8");
            con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            con.setRequestProperty("Cookie", "api_uid=CigYCGaJka2KlwBXn5ieAg==; _nano_fp=XpmxX0PbXpmjX0doXT_nV5ZvDvsEMydYN1pZjAKf; webp=1; jrpl=zViRwgQCsnUpnt9zzpGUrPS9lGku5XLO; njrpl=zViRwgQCsnUpnt9zzpGUrPS9lGku5XLO; dilx=O4jEN1EmSMqkKIgsWNlSL; PDDAccessToken=6QHXOCLPQC442FMV7JZVPG3QKLIA5ZWAZE243GCI46HR7576O7QQ122006a; pdd_user_id=4548925868544; pdd_user_uin=NHBJAIRGBKAZMDMLJN5LBYXVL4_GEXDA; JSESSIONID=E9EBDF6D1FF0C1C818B4B5683FADA67D; pdd_vds=gaUJRHWFXqKCMJVYKCFzUJJqAqjpSzVFSMHFJFFkgZzYSvgZRqRYzWMWSZUC");
            con.setRequestProperty("Origin", "https://mobile.yangkeduo.com");
            con.setRequestProperty("Priority", "u=1, i");
            con.setRequestProperty("Sec-Ch-Ua", "\"Not/A)Brand\";v=\"8\", \"Chromium\";v=\"126\", \"Google Chrome\";v=\"126\"");
            con.setRequestProperty("Sec-Ch-Ua-Mobile", "?0");
            con.setRequestProperty("Sec-Ch-Ua-Platform", "\"Windows\"");
            con.setRequestProperty("Sec-Fetch-Dest", "empty");
            con.setRequestProperty("Sec-Fetch-Mode", "cors");
            con.setRequestProperty("Sec-Fetch-Site", "same-origin");
            con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36");
            con.setRequestProperty("Verifyauthtoken", "ZAv9-bk9tGyG3bcCao7PlQaabea93852789aebb");

            // Enable input/output streams
            con.setDoOutput(true);

            // Write the payload to the output stream
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = payload.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Read the response
            boolean isGzip = "gzip".equals(con.getContentEncoding());
            try (BufferedReader br = new BufferedReader(new InputStreamReader(
                    isGzip ? new GZIPInputStream(con.getInputStream()) : con.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }

                return response.toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
