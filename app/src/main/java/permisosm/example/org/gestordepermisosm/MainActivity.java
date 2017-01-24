package permisosm.example.org.gestordepermisosm;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Variables
    private static final String[] PERMISOS_INICIALES ={
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_CONTACTS
    };
    private static final String[] PERMISOS_CAMARA ={
            Manifest.permission.CAMERA
    };
    private static final String[] PERMISOS_CONTACTOS ={
            Manifest.permission.READ_CONTACTS
    };
    private static final String[] PERMISOS_LOCALIZACION ={
            Manifest.permission.ACCESS_FINE_LOCATION
    };
    private static final int PETICION_INICIAL=123;
    private static final int PETICION_CAMARA =PETICION_INICIAL+1;
    private static final int PETICION_CONTACTOS =PETICION_INICIAL+2;
    private static final int PETICION_LOCALIZACION =PETICION_INICIAL+3;
    private TextView localizacion;
    private TextView camara;
    private TextView internet;
    private TextView contactos;
    private TextView almacenamiento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        localizacion =(TextView)findViewById(R.id.location_value);
        camara =(TextView)findViewById(R.id.camera_value);
        internet=(TextView)findViewById(R.id.internet_value);
        contactos =(TextView)findViewById(R.id.contacts_value);
        almacenamiento =(TextView)findViewById(R.id.storage_value);
        if (!hayPermisoLocalizacion() || !hayPermisoContactos()) {
            ActivityCompat.requestPermissions(this,
                    PERMISOS_INICIALES, PETICION_INICIAL);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        actualizarTabla();
    }

    //Metodos
    private boolean hayPermiso(String perm) {
        return (ContextCompat.checkSelfPermission(this, perm) ==
                PackageManager.PERMISSION_GRANTED);
    }
    private boolean hayPermisoLocalizacion() {
        return(hayPermiso(Manifest.permission.ACCESS_FINE_LOCATION));
    }
    private boolean hayPermisoCamara() {
        return(hayPermiso(Manifest.permission.CAMERA));
    }
    private boolean hayPermisoContactos() {
        return(hayPermiso(Manifest.permission.READ_CONTACTS));
    }
    private void actualizarTabla() {
        localizacion.setText(String.valueOf(hayPermisoLocalizacion()));
        camara.setText(String.valueOf(hayPermisoCamara()));
        internet.setText(String.valueOf(hayPermiso(
                Manifest.permission.INTERNET)));
        contactos.setText(String.valueOf(hayPermisoContactos()));
        almacenamiento.setText(String.valueOf(hayPermiso(
                Manifest.permission.WRITE_EXTERNAL_STORAGE)));
    }
    private void error() {
        Toast.makeText(this, R.string.toast_error, Toast.LENGTH_LONG).show();
    }
    private void accionCamara() {
        Toast.makeText(this, R.string.toast_camara,
                Toast.LENGTH_SHORT).show();
    }
    private void accionContactos() {
        Toast.makeText(this, R.string.toast_contactos,
                Toast.LENGTH_SHORT).show();
    }
    private void accionLocalizacion() {
        Toast.makeText(this, R.string.toast_localizacion,
                Toast.LENGTH_SHORT).show();
    }

}
