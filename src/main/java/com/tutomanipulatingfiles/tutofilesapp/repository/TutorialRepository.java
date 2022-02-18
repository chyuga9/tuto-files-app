package com.tutomanipulatingfiles.tutofilesapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tutomanipulatingfiles.tutofilesapp.model.Tutorial;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
}
