package outages.outage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import outages.html.BodyHtml;
import outages.outage.creator.FoundOutagesCreator;
import outages.outage.filtered.FilteredOutages;
import outages.outage.filtered.FilteredOutagesMy;
import outages.pojo.Outage;

import java.net.http.HttpClient;
import java.util.List;

@Component
public final class OutageServiceMy implements OutageService {

    private final static Logger LOGGER = LogManager.getLogger(OutageServiceMy.class);

    @Autowired
    ProcessResult processResult;

    @Autowired
    private FoundOutagesCreator fCreator;

    @Value("${bot.url}")
    private String baseUrl;

    @Value("${bot.pointSearch}")
    private String pointSearch;

    @Value("${bot.commonSearch}")
    private String[] commonSearch;

    @Value("${bot.x}")
    private Float x;

    @Value("${bot.y}")
    private Float y;

    @Value("${bot.r}")
    private Float r;

    @Override
    public void checkNearBy(Long[] chatIds) {
        try {
            String body = new BodyHtml(HttpClient.newHttpClient(), baseUrl).body();
            FilteredOutages filtered = new FilteredOutagesMy(fCreator.create(body).outages());
            for (String point : splitByComaConsideringQuotes(pointSearch)) {
                List<Outage> filteredByText = filtered.filteredByStringInHeader(point);
                LOGGER.info("Found {} outages by text {}.", filteredByText.size(), point);
                sendOutage(filteredByText);
            }
            List<Outage> filteredByRadius = filtered.filteredByRadius(x, y, r);
            LOGGER.info("Found {} outages by radius {}.", filteredByRadius.size(), r);
            sendOutage(filteredByRadius);
        } catch (Exception e) {
            LOGGER.error("Ошибка верхнего уровня: ", e);
        }
    }

    @Override
    public void checkSizeAround(Long[] chatIds, int limitToText) {
        try {
            String body = new BodyHtml(HttpClient.newHttpClient(), baseUrl).body();
            for (String search : commonSearch) {
                FilteredOutages filtered = new FilteredOutagesMy(fCreator.create(body).outages());
                int filteredByTextSize = filtered.filteredByStringInHeader(search).size();
                LOGGER.info("Found {} outages by text {}.", filteredByTextSize, search);
                if (filteredByTextSize > limitToText) {
                    processResult.processPlainText(String.format("Отключений в %s: %s", search, filteredByTextSize));
                }
            }
        } catch (Exception e) {
            LOGGER.error("Ошибка верхнего уровня: ", e);
        }
    }

    private String[] splitByComaConsideringQuotes(String pointSearch) {
        String[] parts = pointSearch.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
        for (int i = 0; i < parts.length; i++) {
            String p = parts[i].trim();
            if (p.length() >= 2 && (p.startsWith("\"") && p.endsWith("\""))) {
                parts[i] = p.substring(1, p.length() - 1);
            } else {
                parts[i] = p;
            }
        }
        return parts;
    }

    private void sendOutage(List<Outage> outages) {
        for (Outage outage : outages) {
            processResult.processOutage(outage);
        }
    }
}
