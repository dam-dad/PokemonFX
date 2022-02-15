package dad.pokemonfx.fichero;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dad.pokemonfx.batalla.Pokemon;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class GenerarPDF {

	public static final String JRXML_FILE = "/reports/Pokemon.jrxml";
	public static final String PDF_FILE = "pdf/factura.pdf";

	public static void generarPdf(List<Pokemon> lista) throws JRException, IOException {

		// compila el informe
		JasperReport report = JasperCompileManager.compileReport(GenerarPDF.class.getResourceAsStream(JRXML_FILE));

		// mapa de parámetros para el informe
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("anyo", 2014); // no lo uso, pero se lo paso

		// generamos el informe (combinamos el informe compilado con los datos)
		JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters,
				new JRBeanCollectionDataSource(lista));

		// exporta el informe a un fichero PDF
		JasperExportManager.exportReportToPdfFile(jasperPrint, PDF_FILE);

		// Abre el archivo PDF generado con el programa predeterminado del sistema
		Desktop.getDesktop().open(new File(PDF_FILE));
	}

}
