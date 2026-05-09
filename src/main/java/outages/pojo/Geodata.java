package outages.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Geodata {
    private Properties properties;
    private String type;
    private UUID id;
    private Geometry geometry;
}