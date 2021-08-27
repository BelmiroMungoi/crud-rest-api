/**
 * Author: Belmiro Mungoi
 */

function salvarUsuario() {
	var id = $("#id").val();
	var nomeCompleto = $("#nomeCompleto").val();
	var nrPhone = $("#cell").val();
	var bi = $("#bi").val();
	var idade = $("#idade").val();

	if (nomeCompleto == '') {
		alert("Preencha o campo nome completo!!!");
		return false;

	} else if (bi == '') {
		alert("Preencha o campo BI!!!");
		return false;

	} else if (idade == '') {
		alert("Preencha o campo Idade");
		return false;

	} else {
		$.ajax({
			method: "POST",
			url: "salvar",
			data: JSON.stringify({
				id: id,
				nomeCompleto: nomeCompleto,
				nrPhone : nrPhone,
				bi: bi,
				idade : idade
			}),
			contentType: "application/json; charset=utf-8",
			success: function(response) {
				$("#id").val(response.id);
				alert("Salvo com Sucesso");
			}
		}).fail(function(xhr, status, errorThrown) {
			alert("Erro ao salvar" + xhr.responseText);

		});
	}
	return true;
}

function pesquisarUser() {
	var nome = $("#nameBusca").val();
	if (nome != null) {

		$.ajax({
			method: "GET",
			url: "buscarPorNome",
			data: "name=" + nome,
			success: function(response) {
				$('#tabelaresultados > tbody > tr').remove();

				for (var i = 0; i < response.length; i++) {
					$('#tabelaresultados > tbody').append(
						'<tr id="' +  response[i].id + '"><td>'  + response[i].id
						+ '</td><td>' + response[i].nomeCompleto
						+ '</td><td>' + response[i].bi
						+ '</td><td>' + response[i].idade
						+ '</td><td>' + response[i].nrPhone
						+ '</td><td><button type="button" onclick="editar('
						+ response[i].id
						+ ')" class="btn btn-primary">Editar</button></td><td><button type="button" class="btn btn-danger" onclick="deletar('
						+ response[i].id
						+ ')">Delete</button></td></tr>');
				}

			}
		}).fail(function(xhr, status, errorThrown) {
			alert("Erro ao buscar usuario: " + xhr.responseText);
		});
	}
}


function editar(id) {
	$.ajax({
		method: "GET",
		url:  "buscarUsuario",
		data: "id=" + id,
		success: function(response) {

			$("#id").val(response.id);
			$("#nomeCompleto").val(response.nomeCompleto);
			$("#cell").val(response.nrPhone);
			$("#bi").val(response.bi);
			$("#idade").val(response.idade);

			$('#modalPesquisar').modal('hide');
		}
	}).fail(function(xhr, status, errorThrown) {
		alert("Erro ao buscar usuario por id: " + xhr.responseText);
	});
}

function deletar(id) {

	if (confirm('Deseja realmente excluir???')) {


		$.ajax({
			method: "DELETE",
			url: "deletar",
			data: "id=" + id,
			success: function(response) {

				$('#' + id).remove();

				alert(response);
			}
		}).fail(function(xhr, status, errorThrown) {
			alert("Erro ao deletar usuario por id: " + xhr.responseText);
		});

	}
}

function deletarUsuario() {
	var id = $('#id').val();

	if (id != null && id != "") {
		deletar(id);
		document.getElementById('formUser').reset();
	}
}