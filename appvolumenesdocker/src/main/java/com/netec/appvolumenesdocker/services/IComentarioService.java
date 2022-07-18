package com.netec.appvolumenesdocker.services;

import java.util.List;

import com.netec.appvolumenesdocker.entities.Comentario;

public interface IComentarioService {

	void comentarioTemporal(Comentario comentario);
	
	void comentarioPermanente(Comentario comentario);
	
	List<Comentario> getTemporales();
	List<Comentario> getPermanentes();
}
