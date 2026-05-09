package outages.pojo;

import java.util.UUID;

public interface Outage {
    Boolean valid();
    String printableView();
    String address();
    Geometry geometry();
    UUID id();
}
