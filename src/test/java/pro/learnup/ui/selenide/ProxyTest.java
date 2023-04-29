package pro.learnup.ui.selenide;

import com.browserup.bup.filters.RequestFilter;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.netty.handler.codec.DecoderResult;
import io.netty.handler.codec.http.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pro.learnup.ui.selenide.ext.UiTestsExt;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getSelenideProxy;

@ExtendWith(UiTestsExt.class)
public class ProxyTest {

    @Test
    void proxyTest() {
        Configuration.proxyEnabled = true;
        Configuration.proxyHost = "localhost";
        Configuration.proxyPort = 9999;

        open("http://localhost:3000/");

        getSelenideProxy().addRequestFilter("Replace product", (RequestFilter) (httpRequest, httpMessageContents, httpMessageInfo) -> {
            if (httpRequest.method().equals(HttpMethod.GET) && httpRequest.uri().contains("catalog")) {
                return new HttpResponse() {
                    @Override
                    public DecoderResult decoderResult() {
                        return null;
                    }

                    @Override
                    public void setDecoderResult(DecoderResult decoderResult) {

                    }

                    @Override
                    public DecoderResult getDecoderResult() {
                        return null;
                    }

                    @Override
                    public HttpResponseStatus getStatus() {
                        return HttpResponseStatus.valueOf(500);
                    }

                    @Override
                    public HttpResponseStatus status() {
                        return null;
                    }

                    @Override
                    public HttpResponse setStatus(HttpResponseStatus httpResponseStatus) {
                        return null;
                    }

                    @Override
                    public HttpVersion getProtocolVersion() {
                        return null;
                    }

                    @Override
                    public HttpVersion protocolVersion() {
                        return null;
                    }

                    @Override
                    public HttpResponse setProtocolVersion(HttpVersion httpVersion) {
                        return null;
                    }

                    @Override
                    public HttpHeaders headers() {
                        return null;
                    }
                };
            }
            return null;
        });

        Selenide.refresh();

        $(byText("No products found.")).shouldBe(Condition.visible);
    }
}
