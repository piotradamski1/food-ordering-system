package org.example.repository;

import java.util.List;

public interface BaseRepository<T> {

    List<T> findAll();

}
