package outages.outage.creator;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import outages.outage.found.FoundOutages;
import outages.outage.found.FoundOutagesFromV2;

@Primary
@Component
public class FoundOutagesCreatorV2 implements FoundOutagesCreator {
    @Override
    public FoundOutages create(String body) {
        return new FoundOutagesFromV2(body);
    }
}
