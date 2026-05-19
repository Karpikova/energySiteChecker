package outages.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.UUID;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OutageV1 implements Outage {
    private String type;
    private UUID id;
    private Properties properties;
    private Geometry geometry;

    @Override
    public Boolean valid() {
        return properties!=null;
    }

    @Override
    public String printableView() {
        return type + System.lineSeparator() +
                getId() + System.lineSeparator() +
                properties.getBalloonContentHeader() + System.lineSeparator() +
                properties.getBalloonContentBody() + System.lineSeparator() +
                properties.getBalloonContentFooter() + System.lineSeparator();
    }

    @Override
    public String address() {
        return properties.getBalloonContentHeader();
    }

    @Override
    public Geometry geometry() {
        return geometry();
    }

    @Override
    public Boolean isCancelled() {
        return false;
    }

    @Override
    public UUID id() {
        return id;
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

        public OutageV1 build() {
            OutageV1 outageV1 = new OutageV1();
            outageV1.setId(id);
            outageV1.setType(type);
            outageV1.setProperties(properties);
            outageV1.setGeometry(geometry);
            return outageV1;
        }
    }
}