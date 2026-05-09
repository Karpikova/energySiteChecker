package outages.outage.found;

import outages.pojo.Outage;

import java.util.List;

public interface FoundOutages <T extends Outage> {
    List<T> outages() throws Exception;
}
