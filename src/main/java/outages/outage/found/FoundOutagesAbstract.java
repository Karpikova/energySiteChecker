package outages.outage.found;

import com.fasterxml.jackson.databind.ObjectMapper;

public class FoundOutagesAbstract {
    final ObjectMapper mapper = new ObjectMapper();
    final String body;

    public FoundOutagesAbstract(String body) {
        this.body = body;
    }

}
