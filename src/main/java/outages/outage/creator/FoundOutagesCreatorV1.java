package outages.outage.creator;

import org.springframework.stereotype.Component;
import outages.outage.found.FoundOutages;
import outages.outage.found.FoundOutagesFromV1;

@Component
public class FoundOutagesCreatorV1 implements FoundOutagesCreator {
    @Override
    public FoundOutages create(String body) {
        return new FoundOutagesFromV1(body);
    }
}
