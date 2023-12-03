package br.com.fiap.challenge.domain.service;

import org.jvnet.hk2.annotations.Contract;

import java.util.List;

@Contract
public interface Service<T, U> {

    List<T> findAll();

    T findById(U id);

    T persist(T t);

    T update(U id, T t);

    boolean delete(T t);

}