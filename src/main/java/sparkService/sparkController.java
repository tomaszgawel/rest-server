package sparkService;
import com.google.gson.Gson;
import firebase.FirebaseDatabase;
import utilities.Password;
import utilities.Status;
import utilities.User;

import java.io.IOException;

import static spark.Spark.*;

public class sparkController {
    private FirebaseDatabase firebaseDatabase;
    public void start() throws IOException {
        firebaseDatabase = new FirebaseDatabase();

        post("/add/:login/:password", (req,res)->{
            User testUser = firebaseDatabase.login(req.params(":login"));
                if(testUser == null){
                    res.type("application/json");
                    User user = new Gson().fromJson(req.body(),User.class);
                    firebaseDatabase.register(user.getLogin(),user.getPassword());
                    return Status.SUCCESS;
                }
                else{
                   return Status.ERROR;
                }
        });

        get("get/:login", (req,res)->{
            User user = firebaseDatabase.login(req.params(":login"));
            if(user == null)
                return Status.ERROR;
            else return new Gson().toJson(user);
        });

        post("/addpassword/:login/:site/:password/:user", (req,res)->{
                res.type("application/json");
                Password password = new Gson().fromJson(req.body(),Password.class);
                firebaseDatabase.addPassword(password.getLogin(),password.getPassword(),
                        password.getSite(),password.getUser());
                return Status.SUCCESS;
        });
    }
}