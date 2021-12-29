package si.fri.prpo.ocenepolnilnice.v1.viri;

import com.kumuluz.ee.cors.annotations.CrossOrigin;
import si.fri.prpo.ocenepolnilnice.dtos.Ocena;
import si.fri.prpo.ocenepolnilnice.zrna.UpravljanjeOcen;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Logger;

@Path("/ocene")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@CrossOrigin(supportedMethods = "GET, POST, HEAD, DELETE, OPTIONS")
@ApplicationScoped
public class OceneViri {

    private static final Logger log = Logger.getLogger(OceneViri.class.getName());

    @Inject
    private UpravljanjeOcen upravljanjeOcen;

    @Path("/{pid}")
    @GET
    public Response pridobiOcene(@PathParam("pid") Long polnilnicaID) {
        return Response
                .status(Response.Status.OK)
                .entity(upravljanjeOcen.pridobiOcene(polnilnicaID))
                .build();
    }


    @Path("/{pid}")
    @POST
    public Response dodajOceno(@PathParam("pid") Long polnilnicaID, Ocena ocena) {
        return Response
                .status(Response.Status.OK)
                .entity(upravljanjeOcen.dodajOceno(polnilnicaID, ocena))
                .build();
    }


    @Path("/{pid}")
    @DELETE
    public Response odstraniOceno(@PathParam("pid") Long polnilnicaID, Ocena ocena) {
        return Response
                .status(Response.Status.OK)
                .entity(upravljanjeOcen.izbrisiOceno(polnilnicaID, ocena))
                .build();
    }
}
