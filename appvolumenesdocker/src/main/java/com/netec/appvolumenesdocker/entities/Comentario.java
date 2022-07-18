package com.netec.appvolumenesdocker.entities;

import java.util.stream.Stream;

public class Comentario {
	private String email;
	private String comentario;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	   @Override
	public String toString() {
		return "Comentario:[email: "+email+", comentario: "+comentario+"]";
	}
	   
    public static Comentario toObject(String mensaje) {
       return Stream.of(mensaje).map(t->t.substring(t.indexOf('['), t.indexOf(']')))
                                .map(t->t.split(", ")).map(t->{
								    	   Comentario com=new Comentario();
								    	   var correo=t[0].split(":")[1].strip();
								    	   var comentario=t[1].split(":")[1].strip();
								    	   com.setEmail(correo);
								    	   com.setComentario(comentario);
								    	   return com;
									 })
                                .findFirst().get();
									       
    }
    

}
