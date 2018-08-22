package firebase;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class FirebaseDatabase {

    private Firestore db;
    private ApiFuture<WriteResult> future;
    private HashMap<String,String> hashMap;
    private DocumentReference documentReference;
    private ApiFuture<DocumentSnapshot> apiFuture;

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
        future = db.collection("users").document().set(hashMap);
        return true;
    }

    public boolean login(String login, String password) throws ExecutionException, InterruptedException {
        documentReference = db.collection("users").document();
        apiFuture = documentReference.get();
        DocumentSnapshot documentSnapshot = apiFuture.get();
        if (documentSnapshot.contains(login)){
            return true;
        }
        return false;
    }
}
