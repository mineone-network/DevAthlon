package de.mauricius17.devathlon.competitive.consumer;

public interface Consumer<T> {
	void accept(T result);
}
