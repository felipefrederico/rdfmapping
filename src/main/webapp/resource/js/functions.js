function insertColTable(txt){
	txt = txt.slice(1, [txt.length-1]);
	txt = txt.split(",");
	var html ="<table id='table-columns' class='table table-hover table-striped'>"+
				"<thead>"+
					"<tr>"+
						"<th>Coluna</th>"+
						"<th>Chave Primária</th>"+
						"<th class='td-recurso'>Propriedade</th>"+
						"<th class='td-recurso'>Tipo de dado do Valor</th>"+
					"</tr>"+
				"</thead>" +
				"<tbody class='table-striped'>";
	
	for (i=0;i<txt.length;i++){
		txt[i]=txt[i].replace(/^\s+|\s+$/g,"");
		html = html+"<tr>"+
						"<td>"+
							"<input type='hidden' class='.rdf-inputs' id='hdd-"+i+"' value='"+txt[i]+"' name='mapeamento.columns[]' />"+
							txt[i]+
						"</td>"+
						"<td>"+
							"<input required class='form-control' type='radio' name='mapeamento.pk' id='"+txt[i]+"' value='"+i+"' OnChange='PKSelect();'/>"+
						"</td>"+
						"<td class='td-recurso'>" +
							"<input id='input-"+i+"' type='text' class='form-control rdf-inputs' name='mapeamento.properties[]' required />"+
						"</td>"+
						"<td>" +
							"<select name='mapeamento.dataType[]' id='selec-"+i+"' class='form-control rdf-inputs' required>"+
								"<optgroup label='String'><option value='string'>String</option><option value='token'>Token</option></optgroup>"+
								"<optgroup label='Date'><option value='date'>Date</option><option value='time'>Time</option><option value='dateTime'>dateTime</option></optgroup>"+
								"<optgroup label='Numeric'><option value='decimal'>Decimal</option><option value='integer'>Integer</option></optgroup>"+
								"<optgroup label='Miscellaneous'><option value='boolean'>Boolean</option><option value='anyURI'>URI</option></optgroup>"+
							"</select>"+
						"</td>"+
					"</tr>";		
	}
	html = html+"</tbody></table>";
	
	var checkBoxModel = "<div class='row' id='rdfModelLine'>" +
						"<div class='col-xs-3 col-xs-offset-9'>"+
							"<h4>Sintaxe</h4>"+
							"<small>RDF/XML</small> <input id='rdfModel' type='Checkbox' name='mapeamento.model[]' value='RDF/XML' /><br>"+
							"<small>Turtle</small> <input id='rdfModel' type='Checkbox' name='mapeamento.model[]' value='Turtle' />"+
							"</div>"+
					"</div>";
	
	var submitBTN = "<div class='row'>" +
						"<div class='col-xs-3 col-xs-offset-9'>"+
							"<input id='submitbtn' class='btn btn-success' type='submit' value='Mapear Dados' />"+
						"</div>"+
					"</div>";	
	document.getElementById("columns").innerHTML=html+checkBoxModel+submitBTN;
	$( "#columns" ).fadeIn( "slow" );
}

function insertInputRDFType(){
	var InputHTML = "<label>Insira o recurso primitivo (RDF:type)</label> <input type='text' class='form-control' name='mapeamento.rdf_type' required />";
	document.getElementById("RDFType").innerHTML=InputHTML;
	$( "#RDFType" ).fadeIn( "slow" );
}

function PKSelect(){
	$(".rdf-inputs").attr("disabled", false);
	$(".rdf-inputs").val("");
	$(".rdf-inputs").attr("required", true);
	$("#input-"+$("input:checked").val()).val("RDF:Resource");
	$("#input-"+$("input:checked").val()).attr("disabled", true);
	$("#input-"+$("input:checked").val()).attr("required", false);
	$("#selec-"+$("input:checked").val()).val(0);
	$("#selec-"+$("input:checked").val()).attr("disabled", true);
	$("#hdd-"+$("input:checked").val()).attr("disabled", true);
}

function defineRDFTitle(title){
	$("#ns-url").val("http://localhost:8080/resource/rdf/"+title+".rdf#");
}

function validaCheckbox(){
	if ($("input:checked[name='mapeamento.model[]']").val()==null){
		alert('Selecione ao menos uma sintaxe.');
		return false;
	}
	
}