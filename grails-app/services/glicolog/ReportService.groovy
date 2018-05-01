package glicolog

import grails.plugins.jasper.JasperExportFormat
import grails.plugins.jasper.JasperReportDef
import org.apache.commons.io.FileUtils;

class ReportService {

	def jasperService

	public void relatorioPessoas() {
		def reportDef = new JasperReportDef(name:'glicologReport.jrxml', fileFormat:JasperExportFormat.PDF_FORMAT)
		FileUtils.writeByteArrayToFile(new File("/temp/test.pdf"), jasperService.generateReport(reportDef).toByteArray())
	}
}