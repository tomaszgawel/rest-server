package sparkService;
import firebase.FirebaseDatabase;

import java.io.IOException;

import static spark.Spark.*;

public class sparkController {
    private FirebaseDatabase firebaseDatabase;
    public void start() throws IOException {
        firebaseDatabase = new FirebaseDatabase();

        get("/hello",(req, res)->"Hello!");
        post("/add/:login/:pass", (req,res)->{
            firebaseDatabase.register(req.params(":login"),req.params(":pass"));
            System.out.println("hey, "+req.params(":login"));
            return "account "+req.params(":login")+" added";
        });
    }
}
