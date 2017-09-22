import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
public class Main {
    public static void copiarArchivos(String pathSource,String pathOutcome, String sufix) throws IOException {
        File origen = new File(pathSource);
        if(origen.isDirectory()){
            String[] contenidoOrigen = origen.list();
            for(String string:contenidoOrigen) {
                File interno = new File(origen, string);
                if (interno.isDirectory()) {
                    copiarArchivos(interno.getPath(), pathOutcome, sufix);
                } else {
                    if (string.endsWith(sufix)) {
                        Path targetOutcome = Paths.get(pathOutcome + File.separator + interno.getName());
                        Path targetSource = Paths.get(interno.getPath());
                        copy(targetSource, targetOutcome);
                    }
                }
            }
        }else {
            if (origen.getName().endsWith(sufix)) {
                Path targetOutcome = Paths.get(pathOutcome + File.separator + origen.getName());
                Path targetSource = Paths.get(origen.getPath());
                copy(targetSource, targetOutcome);
            }
        }
    }
    public static void copy(Path source, Path outcome) throws IOException {
        Files.copy(source,outcome,REPLACE_EXISTING);
    }
    public static void main(String[] args) throws IOException {
        copiarArchivos("Vampiro_Mascarada","pruebasPDF",".pdf");
    }
}
