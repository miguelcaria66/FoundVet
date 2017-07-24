package com.example.android.foundvet.login;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.example.android.foundvet.R;
import com.example.android.foundvet.pets.MyPetsActivity;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Firebase imports
//Facebook imports
//Google imports

public class LoginActivity extends AppCompatActivity implements
        GoogleApiClient.OnConnectionFailedListener {

    private int imageNr = 0;

    private EditText loginName;
    private EditText loginPassword;
    private TextView signUp;
    private TextView facebookLogin;
    private TextView googleLogin;
    private SQLiteDatabase db;

    //For Facebook
    private CallbackManager callbackManager;
    //For Google
    private GoogleSignInOptions gso;
    private GoogleApiClient googleApiClient;
    private static final int RC_SIGN_IN = 9001;

    private ImageSwitcher switcher;
    private ImageView arrowLeft;
    private ImageView arrowRight;
    private int[] images = {R.drawable.tutor, R.drawable.vet};

    //Firebase variables
//    private FirebaseAuth mAuth;
//    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LoginDataBase loginDb = new LoginDataBase(this);
        callbackManager = CallbackManager.Factory.create();

        switcher = (ImageSwitcher) findViewById(R.id.user_image_switcher);
        switcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(com.example.android.foundvet.login.LoginActivity.this);
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                return imageView;
            }
        });

        switcher.setImageResource(R.drawable.tutor);

        arrowLeft = (ImageView) findViewById(R.id.arrow_left);
        arrowLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imageNr == 0) {
                    switcher.setImageResource(R.drawable.vet);
                    imageNr++;
                } else {
                    switcher.setImageResource(R.drawable.tutor);
                    imageNr--;
                }
            }
        });
        arrowRight = (ImageView) findViewById(R.id.arrow_right);
        arrowRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imageNr == 0) {
                    switcher.setImageResource(R.drawable.vet);
                    imageNr++;
                } else {
                    switcher.setImageResource(R.drawable.tutor);
                    imageNr--;
                }
            }
        });

        signUp = (TextView) findViewById(R.id.signUp);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(com.example.android.foundvet.login.LoginActivity.this, CreateAccount.class);
                startActivity(intent);
            }
        });

        facebookLogin = (TextView) findViewById(R.id.login_facebook);
        facebookLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Facebook", "CLICKED");
                LoginManager.getInstance().logInWithReadPermissions(com.example.android.foundvet.login.LoginActivity.this, Arrays.asList("public_profile"));
            }
        });

        googleLogin = (TextView) findViewById(R.id.login_google);
        googleLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Google Plus", "CLICKED");
                signIn();
            }
        });

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(com.example.android.foundvet.login.LoginActivity.this, com.example.android.foundvet.login.LoginActivity.this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        db = loginDb.getWritableDatabase();
        insertFakeData();

        //Firebase
//        mAuth = FirebaseAuth.getInstance();
//        mAuthListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                FirebaseUser user = firebaseAuth.getCurrentUser();
//                if(user != null) {
//                    Log.d("SIGN IN", "onAuthStateChanged:signed_in:" + user.getUid());
//                } else {
//                    // User is signed out
//                    Log.d("SIGN OUT", "onAuthStateChanged:signed_out");
//                }
//                // ...
//            }
//        };
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        mAuth.addAuthStateListener(mAuthListener);
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        if (mAuthListener != null) {
//            mAuth.removeAuthStateListener(mAuthListener);
//        }
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        } else {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void pressedLogin(View view) {
        loginName = (EditText) findViewById(R.id.login_name);
        loginPassword = (EditText) findViewById(R.id.login_password);
        String name = loginName.getText().toString();
        String password = loginPassword.getText().toString();
        startActivity(new Intent(this, MyPetsActivity.class));
        Cursor cursor = db.query(
                LoginContract.LoginEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);

        while(cursor.moveToNext()) {
            if(name.equals(cursor.getString(cursor.getColumnIndex(LoginContract.LoginEntry.COLUMN_NAME)))) {
                if(password.equals(cursor.getString(cursor.getColumnIndex(LoginContract.LoginEntry.COLUMN_PASSOWORD)))) {
                    Log.d("pressedLogin", "IT'S OK");
                    cursor.close();
                    break;
                }
            }
        }
    }

//    private void createAccount(String email, String password) {
//        mAuth.createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        Log.d("CREATE USER", "createUserWithEmail:onComplete:" + task.isSuccessful());
//
//                        // If sign in fails, display a message to the user. If sign in succeeds
//                        // the auth state listener will be notified and logic to handle the
//                        // signed in user can be handled in the listener.
//                        if (!task.isSuccessful()) {
//                            Toast.makeText(LoginActivity.this, R.string.auth_failed,
//                                    Toast.LENGTH_SHORT).show();
//                        }
//
//                        // ...
//                    }
//                });
//    }
//
//    private void signIn(String email, String password) {
//        mAuth.signInWithEmailAndPassword(email, password)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        Log.d("SIGN IN", "signInWithEmail:onComplete:" + task.isSuccessful());
//
//                        // If sign in fails, display a message to the user. If sign in succeeds
//                        // the auth state listener will be notified and logic to handle the
//                        // signed in user can be handled in the listener.
//                        if (!task.isSuccessful()) {
//                            Log.w("SIGN IN FAIL", "signInWithEmail:failed", task.getException());
//                            Toast.makeText(LoginActivity.this, R.string.auth_failed,
//                                    Toast.LENGTH_SHORT).show();
//                        }
//
//                        // ...
//                    }
//                });
//    }

    private void insertFakeData() {
        List<ContentValues> list = new ArrayList<ContentValues>();

        ContentValues cv = new ContentValues();
        cv.put(LoginContract.LoginEntry.COLUMN_NAME, "John");
        cv.put(LoginContract.LoginEntry.COLUMN_PASSOWORD, "12345678");
        cv.put(LoginContract.LoginEntry.COLUMN_EMAIL, "cenas@nonsense.com");
        cv.put(LoginContract.LoginEntry.COLUMN_ADDRESS, "morada da rua das bananas");
        cv.put(LoginContract.LoginEntry.COLUMN_PHONE, "933339867");
        list.add(cv);

        cv.put(LoginContract.LoginEntry.COLUMN_NAME, "Pinho");
        cv.put(LoginContract.LoginEntry.COLUMN_PASSOWORD, "naovaisadivinhar");
        cv.put(LoginContract.LoginEntry.COLUMN_EMAIL, "danielpinho95@gmail.com");
        cv.put(LoginContract.LoginEntry.COLUMN_ADDRESS, "Rua Quinta das Oliveiras");
        cv.put(LoginContract.LoginEntry.COLUMN_PHONE, "963933263");
        list.add(cv);

        try {
            db.beginTransaction();
            db.delete(LoginContract.LoginEntry.TABLE_NAME, null, null);
            for (ContentValues c : list) {
                db.insert(LoginContract.LoginEntry.TABLE_NAME, null, c);
            }
            db.setTransactionSuccessful();
        } catch(SQLException e) {
            //do nothing for now
        } finally {
            db.endTransaction();
            Log.d("insertFakeData", "all done");
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d("GOOGLE SIGN IN SUCCESS", "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
        } else {
            // Signed out, show unauthenticated UI.
        }
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d("GOOGLE SIGN IN FAILED", "onConnectionFailed:" + connectionResult);
    }
}
