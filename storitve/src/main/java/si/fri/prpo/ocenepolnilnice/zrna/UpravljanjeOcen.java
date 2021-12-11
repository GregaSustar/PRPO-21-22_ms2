package si.fri.prpo.ocenepolnilnice.zrna;

import si.fri.prpo.ocenepolnilnice.dtos.Ocena;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import java.util.*;
import java.util.logging.Logger;

@ApplicationScoped
public class UpravljanjeOcen {

    private Map<Long, List<Ocena>> ocene;

    private static final Logger log = Logger.getLogger(UpravljanjeOcen.class.getName());

    private static final UUID uuid = UUID.randomUUID();


    @PostConstruct
    private void init() {
        log.info("Initialized: " + UpravljanjeOcen.class.getName() + ", UUID: " + uuid);
        ocene = new HashMap<>();
    }

    @PreDestroy
    private void destroy() {
        log.info("Destroying: " + UpravljanjeOcen.class.getName() + ", UUID: " + uuid);
    }

    public List<Ocena> pridobiOcene(Long polnilnicaID) {
        return ocene.get(polnilnicaID);
    }

    public Ocena dodajOceno(Long polnilnicaID, Ocena ocena) {

        ocene.computeIfAbsent(polnilnicaID, k -> new ArrayList<>());
        List<Ocena> list = ocene.get(polnilnicaID);
        list.add(ocena);
        ocene.put(polnilnicaID, list);
        return ocena;
    }

    public boolean izbrisiOceno(Long polnilnicaID, Ocena ocena) {

        if(ocene.get(polnilnicaID) == null) {
            log.info("Ne morem izbrisati ocene. Polnilnica nima ocen.");
            return false;
        } else {
            List<Ocena> list = ocene.get(polnilnicaID);
            list.remove(ocena);
            ocene.put(polnilnicaID, list);
        }
        return true;
    }
}
