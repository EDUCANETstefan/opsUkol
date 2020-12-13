package educanet.webik;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

 class User {
    String fullName = "";
    String username = "";
    String email = "";
    String password = "";

     public User(String fullName, String username, String email, String password) {
         this.fullName = fullName;
         this.username = username;
         this.email = email;
         this.password = password;
     }
 }

@Path("users")
public class UserResource {

     @Inject
     public Manager manager;

    public static ArrayList<User> users = new ArrayList<>();


    @POST
    public Response registerUser(@FormParam("fullName") String fullname, @FormParam("username") String username, @FormParam("email") String email, @FormParam("password") String password) {
        User userR = new User(fullname, username, email, password);
        users.add(userR);
        return Response.ok(userR).build();
    }

    @POST
    public Response loginUser(@FormParam("username") String username, @FormParam("password") String password) {
        for(int x = 0; x < users.size(); x++) {
            User user = users.get(x);
            if (user.username.equals(username) & user.password.equals(password)) {
                manager.user = user;
                return Response.ok("User logged in").build();
            }

        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    public Response getLoggedUser() {
        return Response.ok(manager.user).build();
    }

    @DELETE
    public Response logoutUser() {
        manager.user = null;
        return  Response.ok("User logged out.").build();
    }

}
