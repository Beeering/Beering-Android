package io.beering.beering.Proxy;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by jisooyoon on 2017. 5. 17.
 * DESC: For server communication
 */

public class Proxy {
    public static final String BASE_URL = "54.191.148.7:3000";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void getBeer(String url, int beer_id, AsyncHttpResponseHandler responseHandler) {
        RequestParams params = new RequestParams();
        params.put("beer_id", beer_id);

        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void getBeerList(String url, AsyncHttpResponseHandler responseHandler) {

        client.get(getAbsoluteUrl(url), responseHandler);
    }

    public static void getBrewery(String url, int brewery_id, AsyncHttpResponseHandler responseHandler) {
        RequestParams params = new RequestParams();
        params.put("brewery_id", brewery_id);

        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void getBreweryList(String url, AsyncHttpResponseHandler responseHandler) {

        client.get(getAbsoluteUrl(url), responseHandler);
    }

    public static void getPub(String url, int pub_id, AsyncHttpResponseHandler responseHandler) {
        RequestParams params = new RequestParams();
        params.put("pub_id", pub_id);

        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void getPubList(String url, AsyncHttpResponseHandler responseHandler) {

        client.get(getAbsoluteUrl(url), responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
