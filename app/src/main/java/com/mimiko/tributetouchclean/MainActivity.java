package com.mimiko.tributetouchclean;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private final String tributeUrl = "https://throne.com/all4mimiko/item/c4446944-e3ce-424d-aad7-72237aaafd13";

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WebView webView = new WebView(this);
        setContentView(webView);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
                // Inject JS to click Add to Cart
                view.evaluateJavascript("document.querySelector('#scrollableContainer > section.css-nhp7ao > main > div.chakra-container.css-15gl284 > div.css-11tw2d1 > div > div > div.chakra-stack.css-tl3ftk > div > div > div > div.css-1g68m89 > div > div.chakra-stack.css-8g8ihq > form > div > div > button')?.click();", null);

                // Delay before clicking Checkout
                view.postDelayed(() -> view.evaluateJavascript(
                    "document.evaluate(\"//*[@id='chakra-modal-cartdrawer']/div[2]/button/span[1]\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue?.click();", null
                ), 2500);

                // Delay before clicking Pay Now
                view.postDelayed(() -> view.evaluateJavascript(
                    "document.evaluate(\"//*[@id='__next']/div/div/div[1]/div/div[2]/div/div[3]/div/div/div[2]/form/div/div[4]/button\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue?.click();", null
                ), 5000);
            }
        });

        webView.loadUrl(tributeUrl);
    }
}
