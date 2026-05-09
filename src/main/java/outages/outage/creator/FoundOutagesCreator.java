package outages.outage.creator;

import outages.outage.found.FoundOutages;

public interface FoundOutagesCreator {
    FoundOutages create (String body);
}
