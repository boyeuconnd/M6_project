package com.nancy.m6project.repositories.img;

import com.nancy.m6project.model.img.Img;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImgRepository extends CrudRepository<Img,Long> {
}
