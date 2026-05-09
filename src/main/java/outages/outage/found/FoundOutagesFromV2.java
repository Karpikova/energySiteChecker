package outages.outage.found;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import outages.pojo.OutageV2;

import java.util.List;

public class FoundOutagesFromV2 extends FoundOutagesAbstract implements FoundOutages <OutageV2> {
    public FoundOutagesFromV2(String body) {
        super(body);
    }

    @Override
    public List<OutageV2> outages() throws JsonProcessingException {
        return mapper.readValue(body, new TypeReference<>() {
        });
    }
}
