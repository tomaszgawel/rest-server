package sparkService;
import com.google.gson.Gson;
import firebase.FirebaseDatabase;
import utilities.Status;
import utilities.User;

import java.io.IOException;

import static spark.Spark.*;

public class sparkController {
    private FirebaseDatabase firebaseDatabase;
    public void start() throws IOException {
        firebaseDatabase = new FirebaseDatabase();

        get("/hello",(req, res)->"Hello!");

        post("/add/:login/:password", (req,res)->{
            res.type("application/json");
            User user = new Gson().fromJson(req.body(),User.class);
            firebaseDatabase.register(user.getLogin(),user.getPassword());
            System.out.println("hey "+user.getLogin()+"!");
            return Status.SUCCESS;
        });

        get("get/:login", (req,res)->{
            User user = firebaseDatabase.login(req.params(":login"));
            if(user == null)
                return Status.ERROR;
            else return new Gson().toJson(user);
        });
    }
}