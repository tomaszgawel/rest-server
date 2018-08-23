package firebase;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import utilities.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class FirebaseDatabase {

    private Firestore db;
    private HashMap<String,String> hashMap;
    private DocumentReference documentReference;
    private ApiFuture<DocumentSnapshot> apiFuture;
    private CollectionReference collection;

    public FirebaseDatabase() throws IOException {
        FileInputStream serviceAccount = new FileInputStream("restServer_firebase.json");
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        FirebaseApp.initializeApp(options);
        db = FirestoreClient.getFirestore();
        hashMap = new HashMap<>();
    }

    public boolean register(String login, String password){
        hashMap.clear();
        hashMap.put("login",login);
        hashMap.put("password",password);
        db.collection("users").document().set(hashMap);
        return true;
    }

    public User login(String login) throws ExecutionException, InterruptedException {
        collection = db.collection("users");
        documentReference = db.collection("users").document(login);
        apiFuture = documentReference.get();
        DocumentSnapshot documentSnapshot = apiFuture.get();
        if(documentSnapshot.exists()){
            return new User(documentSnapshot.getData().get("login").toString(),
                    documentSnapshot.getData().get("password").toString());
        }
        return null;
    }
}
