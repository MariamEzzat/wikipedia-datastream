package com.consumer.repository;

import com.consumer.entity.WikimediaData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WikimediaDataRepo extends JpaRepository< WikimediaData,Long> {
}
