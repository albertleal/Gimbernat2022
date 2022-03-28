package com.albertleal.gimbernat.dasources;

import androidx.annotation.NonNull;

import com.albertleal.gimbernat.helpers.Callback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.*;

public class SessionDataSource {
    public static SessionDataSource shared = new SessionDataSource();

    public void signOut() {
        FirebaseAuth.getInstance().signOut();
    }

    public void getCurrentUser(final Callback callback) {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        callback.onSuccess(currentUser);
    }

    public  Boolean isUserLogedIn() {
        return FirebaseAuth.getInstance().getCurrentUser() != null;
    }

    public void signIn(String email, String password, final Callback callback) {
        FirebaseAuth.getInstance().signOut();
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    callback.onSuccess(FirebaseAuth.getInstance().getCurrentUser());
                }else {
                    callback.onError();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                callback.onError();
            }
        });;
    }
    public void signInAnonymously(final Callback callback) {
        FirebaseAuth.getInstance().signOut();
        FirebaseAuth.getInstance().signInAnonymously().addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    callback.onSuccess(FirebaseAuth.getInstance().getCurrentUser());
                }else {
                    callback.onError();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                callback.onError();
            }
        });

    }

}
