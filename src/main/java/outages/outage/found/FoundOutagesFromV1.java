package outages.outage.found;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import outages.pojo.OutageV1;

import java.util.List;

public final class FoundOutagesFromV1 extends FoundOutagesAbstract implements FoundOutages<OutageV1> {

    public FoundOutagesFromV1(String body) {
        super(body);
    }

    @Override
    public List<OutageV1> outages() throws JsonProcessingException {
        return mapper.readValue(body, new TypeReference<>() {
        });
    }
}
