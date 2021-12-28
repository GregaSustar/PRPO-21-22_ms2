package si.fri.prpo.ocenepolnilnice.odjemalci;

import com.kumuluz.ee.configuration.utils.ConfigurationUtil;
import si.fri.prpo.ocenepolnilnice.dtos.Ocena;
import si.fri.prpo.ocenepolnilnice.dtos.TranslateRequest;


import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.json.JsonObject;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;

@ApplicationScoped
public class TranslateApiOdjemalec {

    private static final Logger log = Logger.getLogger(TranslateApiOdjemalec.class.getName());
    private Client httpClient;
    private String baseUrl;


    @PostConstruct
    private void init() {
        log.info("Initialized: " + TranslateApiOdjemalec.class.getName());

        httpClient = ClientBuilder.newClient();
        baseUrl = ConfigurationUtil.getInstance().get("integrations.translate.baseurl").get();
    }

    public String translateToEnglish(Ocena ocena) {

        JsonObject response = null;
        TranslateRequest req = new TranslateRequest(ocena.getKomentar(), "en", "sl");

        try {
            response = httpClient
                    .target(baseUrl)
                    .request(MediaType.APPLICATION_JSON)
                    .header("x-rapidapi-host", ConfigurationUtil.getInstance().get("integrations.translate.apihost").get())
                    .header("x-rapidapi-key", ConfigurationUtil.getInstance().get("integrations.translate.apikey").get())
                    .post(Entity.json(req), JsonObject.class);

        } catch (WebApplicationException | ProcessingException e) {
            log.severe(e.getMessage());
            throw new InternalServerErrorException();
        }

        String prevod = response.getJsonObject("data").getJsonObject("translations").get("translatedText").toString();
        prevod = prevod.substring(1, prevod.length() - 1);
        return prevod;
    }
}
