package outages.pojo;

import java.util.UUID;

public interface Outage {
    UUID id();
    Boolean valid();
    String printableView();
    String address();
    Geometry geometry();
    Boolean isCancelled();
}
