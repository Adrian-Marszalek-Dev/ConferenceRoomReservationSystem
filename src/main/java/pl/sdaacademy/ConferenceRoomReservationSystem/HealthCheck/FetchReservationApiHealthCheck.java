package pl.sdaacademy.ConferenceRoomReservationSystem.HealthCheck;

import org.springframework.boot.actuate.health.CompositeHealthContributor;
import org.springframework.boot.actuate.health.HealthContributor;
import org.springframework.boot.actuate.health.NamedContributor;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

@Component("FetchUsersAPI")
public class FetchReservationApiHealthCheck implements CompositeHealthContributor {

    private Map<String, HealthContributor>
        contributors = new LinkedHashMap<>();

    private DbHealthCheck dbHealthCheck;

    public FetchReservationApiHealthCheck(DbHealthCheck dbHealthCheck) {
        this.dbHealthCheck = dbHealthCheck;
        contributors.put("Database", dbHealthCheck);
    }

    @Override
    public HealthContributor getContributor(String name) {
        return getContributor(name);
    }

    @Override
    public Iterator<NamedContributor<HealthContributor>> iterator() {
        return contributors.entrySet().stream()
                .map((entry)->
                    NamedContributor.of(entry.getKey(), entry.getValue())).iterator();
    }
}
