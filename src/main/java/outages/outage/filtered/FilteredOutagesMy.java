package outages.outage.filtered;


import outages.pojo.Outage;

import java.util.List;
import java.util.Locale;

public final class FilteredOutagesMy implements FilteredOutages {
    List<Outage> outage;

    public FilteredOutagesMy(List<Outage> outages) {
        this.outage = outages;
    }

    @Override
    public List<Outage> filteredByStringInHeader(String filterString) {
        return outage.stream().filter(outage -> outage.valid())
                .filter(outage -> outage.address().toLowerCase(Locale.ROOT)
                .contains(filterString.toLowerCase())).toList();
    }

    @Override
    public List<Outage> filteredByRadius(Float x, Float y, Float radius) {
        return outage.stream().filter(outage -> outage.valid())
                .filter(q -> (Math.abs(q.geometry().getX() - x) < radius
                && (Math.abs(q.geometry().getY() - y) < radius))).toList();
    }
}
