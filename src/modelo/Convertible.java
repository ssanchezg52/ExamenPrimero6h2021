package modelo;

public interface Convertible<T> {
	public String convertirAString(T t);
	public T convertirAObjeto(String string);
}
