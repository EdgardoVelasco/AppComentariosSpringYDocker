package com.netec.appvolumenesdocker.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.netec.appvolumenesdocker.entities.Comentario;

@Service
public class ComentarioServiceImpl implements IComentarioService {
	private static final Logger LOG=LoggerFactory.getLogger(ComentarioServiceImpl.class);
	
	private Path pathTemporal=Paths.get("./archivoT.txt");
	private Path pathPermanente=Paths.get("./archivoP.txt");
	
	@Override
	public void comentarioTemporal(Comentario comentario) {
		if(!Files.exists(pathTemporal)) {
		 try {
            
			Files.createFile(pathTemporal);
			insertarTexto(comentario, pathTemporal);
		
		 }catch(IOException ex) {
			 LOG.error("ErrorCrearArchivo: "+ex);
		 }
		 
		}else {
			insertarTexto(comentario,pathTemporal);
		}
		
	}
	
	private static boolean insertarTexto(Comentario comentario, Path direccion) {
		try {
			Files.write(direccion, (comentario.toString()+System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
			return true;
		}catch(IOException ex) {
			LOG.error("Algo paso: "+ex);
		}
		return false;
	}
	
	@Override
	public void comentarioPermanente(Comentario comentario) {
		if(!Files.exists(pathPermanente)) {
			try{
				Files.createFile(pathPermanente);
				insertarTexto(comentario, pathPermanente);
			}catch(IOException ex) {
				LOG.error("ErrorCrearArchivo: "+ex);
			}
		}else {
			insertarTexto(comentario, pathPermanente);
		}
		
	}
	@Override
	public List<Comentario> getTemporales() {
		try(Stream<String> archivo=Files.lines(pathTemporal)){
			return archivo.map(t->Comentario.toObject(t))
					.collect(Collectors.toList());
		}catch(IOException ex) {
			LOG.error("errorArchivosTemporales: "+ex);
		}
		return null;
	}
	@Override
	public List<Comentario> getPermanentes() {
		try(Stream<String>archivo=Files.lines(pathPermanente)){
			return archivo.map(t->Comentario.toObject(t))
					.collect(Collectors.toList());
		}catch(IOException ex) {
			LOG.error("errorArchivosPermanentes: "+ex);
		}
		return null;
	}
	
	
	
 
	

}
