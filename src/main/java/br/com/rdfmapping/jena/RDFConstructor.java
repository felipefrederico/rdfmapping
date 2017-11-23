package br.com.rdfmapping.jena;

import java.io.StringWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFWriter;
import org.apache.jena.rdf.model.Resource;
import br.com.rdfmapping.model.FilesModeling;
import br.com.rdfmapping.model.Mapeamento;
import br.com.rdfmapping.persistence.DBDAO;

public class RDFConstructor {

	public static void rdfBuild(Mapeamento mapeamento){


		Model model = ModelFactory.createDefaultModel();
		String fileName = mapeamento.getRdfTitle();
		model.setNsPrefix("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
		model.setNsPrefix("rdfs", "http://www.w3.org/2000/01/rdf-schema#");
		model.setNsPrefix("xsd", "http://www.w3.org/TR/xmlschema11-2/#");
		String ns = "http://localhost:8080/rdfmapping/rdf/"+fileName+"#";
		model.setNsPrefix("eg", ns);

		for (int i=0;i<mapeamento.getPrefix_namespaces().size();i++){
			model.setNsPrefix(mapeamento.getPrefix_namespaces().get(i),mapeamento.getUrl_namespaces().get(i));
		}

		Resource resource = null;
		Property property = null;
		Property propertyRDFType = GetPropertyNormalized(mapeamento.getRdf_type(),model);

		Property rdfProperty = model.createProperty(model.getNsPrefixURI("rdf")+"type");

		ResultSet rs = DBDAO.SelectAllFrom(mapeamento.getTable());
		try{	
			while (rs.next()){
				resource=(model.createResource(model.getNsPrefixURI("eg")+rs.getString(mapeamento.getPk()+1)));
				resource.addProperty(rdfProperty, propertyRDFType);
				for (int i=0;i<mapeamento.getColumns().size();i++){
					property = GetPropertyNormalized(mapeamento.getProperties().get(i), model);
					resource.addProperty(property, rs.getString(mapeamento.getColumns().get(i)));
				}

			}
		}catch(SQLException e){
			System.out.println(e);
		}

		for (int i = 0; i<mapeamento.getModel().size();i++){
			RDFWriter writer = model.getWriter(mapeamento.getModel().get(i));
			StringWriter out = new StringWriter();
			writer.setProperty("allowBadURIs","true");
			writer.setProperty("relativeURIs","");
			writer.setProperty("tab","0");
			writer.write(model, out, null);


			if (mapeamento.getModel().get(i).equals("RDF/XML")){
					String xml = "<?xml version='1.0'?>";	
					FilesModeling.newFile(fileName, "rdf", xml+out.toString());

			}
			if (mapeamento.getModel().get(i).equals("Turtle")){
					FilesModeling.newFile(fileName, "ttl", out.toString());
					
			}

		}


	} 

	public static Property GetPropertyNormalized(String str_property, Model model){
		Property property = null;
		if (str_property.contains(":") && !str_property.contains(":/") ){
			String prefix_rdfType = str_property.substring(0, str_property.indexOf(":"));
			String resource_rdfType = str_property.substring(str_property.indexOf(":")+1);
			property = model.createProperty(model.getNsPrefixURI(prefix_rdfType)+resource_rdfType);
		}else{
			property = model.createProperty(str_property);
		}

		return property;
	}
}
