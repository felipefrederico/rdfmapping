package br.com.rdfmapping.model;

import java.util.ArrayList;
import java.util.List;

public class Mapeamento {
	
	private String rdfTitle;
	private List<String> prefix_namespaces = new ArrayList<String>();
	private List<String> url_namespaces = new ArrayList<String>();
	private String table;
	private String rdf_type;
	private List<String> columns = new ArrayList<String>();
	private List<String> properties = new ArrayList<String>();
	private List<String> dataType = new ArrayList<String>();
	private List<String> model = new ArrayList<String>();
	private int pk;
			
	public String getRdfTitle() {
		return rdfTitle;
	}
	
	public void setRdfTitle(String rdfTitle) {
		this.rdfTitle = rdfTitle;
	}
	public List<String> getPrefix_namespaces() {
		return prefix_namespaces;
	}
	public void setPrefix_namespaces(List<String> prefix_namespaces) {
		this.prefix_namespaces = prefix_namespaces;
	}
	public List<String> getUrl_namespaces() {
		return url_namespaces;
	}
	public void setUrl_namespaces(List<String> url_namespaces) {
		this.url_namespaces = url_namespaces;
	}
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}	
	public String getRdf_type() {
		return rdf_type;
	}
	public void setRdf_type(String rdf_type) {
		this.rdf_type = rdf_type;
	}
	public List<String> getColumns() {
		return columns;
	}
	public void setColumns(List<String> columns) {
		this.columns = columns;
	}
	public List<String> getProperties() {
		return properties;
	}
	public void setProperties(List<String> properties) {
		this.properties = properties;
	}
	public List<String> getDataType() {
		return dataType;
	}
	public void setDataType(List<String> dataType) {
		this.dataType = dataType;
	}	
	public List<String> getModel() {
		return model;
	}
	public void setModel(List<String> model) {
		this.model = model;
	}
	public int getPk() {
		return pk;
	}
	public void setPk(int pk) {
		this.pk = pk;
	}
}
