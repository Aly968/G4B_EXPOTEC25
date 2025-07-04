import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.swing.JOptionPane;

public class ActualizarLogo {

    public String procesarNuevoLogo(File selectedFile, int idEmpresa) {
        String newLogoFileName = null;
        try {
            String projectRootPath = System.getProperty("user.dir");
            String originalFileName = selectedFile.getName();
            String fileExtension = "";

            int dotIndex = originalFileName.lastIndexOf('.');
            if (dotIndex > 0 && dotIndex < originalFileName.length() - 1) {
                fileExtension = originalFileName.substring(dotIndex);
            }

            newLogoFileName = "logoempresa_" + idEmpresa + fileExtension;
            File destinationFile = new File(projectRootPath + File.separator + newLogoFileName);

            Files.copy(selectedFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

            Actualizarinfoempr actualizarEmpresaDB = new Actualizarinfoempr();
            boolean dbUpdateSuccess = actualizarEmpresaDB.actualizarLogoEmpresa(idEmpresa, newLogoFileName);

            if (dbUpdateSuccess) {
                JOptionPane.showMessageDialog(null, "Logo copiado y ruta actualizada en la base de datos.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                return newLogoFileName;
            } else {
                JOptionPane.showMessageDialog(null, "Logo copiado, pero falló la actualización de la ruta en la base de datos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return null;
            }

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al copiar el archivo del logo: " + ex.getMessage(), "Error de Copia", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            return null;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al procesar el logo: " + ex.getMessage(), "Error de Procesamiento", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            return null;
        }
    }
}