package outages.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class OutageV2 implements Outage {
    private Geodata geodata;

    @Override
    public Boolean valid() {
        return geodata!=null;
    }

    @Override
    public String printableView() {
        return geodata.getType() + System.lineSeparator() +
                geodata.getId() + System.lineSeparator() +
                geodata.getProperties().getBalloonContentHeader() + System.lineSeparator() +
                geodata.getProperties().getBalloonContentBody() + System.lineSeparator() +
                geodata.getProperties().getBalloonContentFooter() + System.lineSeparator();
    }

    @Override
    public String address() {
        return geodata.getProperties().getBalloonContentHeader();
    }

    @Override
    public Geometry geometry() {
        return geodata.getGeometry();
    }

    @Override
    public UUID id() {
        return geodata.getId();
    }

    public static class Builder {
        private String type = "Feature";
        private UUID id;
        private Properties properties = new Properties();
        private Geometry geometry = new Geometry();

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public OutageV2 build() {
            OutageV2 outageV2 = new OutageV2();
            Geodata geodata1 = new Geodata();
            geodata1.setId(id);
            geodata1.setType(type);
            geodata1.setProperties(properties);
            geodata1.setGeometry(geometry);
            return outageV2;
        }
    }
}
