package com.ecare.base;

import java.util.List;
import java.util.Optional;

public interface BaseData<T> {

    public Optional<T> get(long id);

    public List<T> get(int from, int number);

    public List<T> getAll();

    public T save(T value);

    public T update(T value);

    public void delete(T value);

    public long count();
}