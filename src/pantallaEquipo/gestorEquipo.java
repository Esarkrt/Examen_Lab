package pantallaEquipo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class gestorEquipo {
    private byte[] datosmemoria;

    public void guardar(equipoGuardado equipoguardado) throws IOException {
        ByteArrayOutputStream flujoout = new ByteArrayOutputStream();
        try (ObjectOutputStream objetoout = new ObjectOutputStream(flujoout)) {
            objetoout.writeObject(equipoguardado);
            objetoout.flush();
            this.datosmemoria = flujoout.toByteArray();
        }
    }

    public equipoGuardado restaurar() throws IOException, ClassNotFoundException {
        if (this.datosmemoria == null) {
            return null;
        }
        ByteArrayInputStream flujoin = new ByteArrayInputStream(this.datosmemoria);
        try (ObjectInputStream objetoin = new ObjectInputStream(flujoin)) {
            return (equipoGuardado) objetoin.readObject();
        }
    }

    public boolean hayEquipoGuardado() {
        return this.datosmemoria != null;
    }
}
