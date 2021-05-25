/**
 * Validação de formulario
@author Victor
 */

function validar(){
	let nome = frmContato.nome.value
	let mail = frmContato.email.value
	let senha = frmContato.senha.value
	let telefone = frmContato.telefone.value
	
	if(nome === ""){
		alert('Preencha o campo nome !');
		frmContato.nome.focus()
		return false
	}else if(mail === ""){
		alert('Preencha o campo mail !')
		frmContato.mail.focus()
		return false
	}else if(senha ===""){
		frmContato.senha.focus()
		return false
	}else if(telefone === ""){
		alert('Preencha o campo telefone');
		frmContato.telefone.focus()
		return false
		
	}else{
		document.forms["frmContato"].submit();
	}
	
}