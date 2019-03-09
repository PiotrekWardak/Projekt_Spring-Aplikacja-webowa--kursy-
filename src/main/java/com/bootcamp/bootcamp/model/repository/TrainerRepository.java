package com.bootcamp.bootcamp.model.repository;

import com.bootcamp.bootcamp.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long> {



    @Query("update Trainer trainer set trainer.firstName =:trainerName,trainer.lastName =:trainerSurname," +
            "trainer.salary =:trainerSalary,trainer.description=:trainerDesc where trainer.id =:Id")
    void edytuj(@Param("Id") long trainerID, @Param("trainerName") String trainerName,
                @Param("trainerSurname") String trainerSurname,@Param("trainerSalary") int trainerSalary,
                @Param("trainerDesc") String trainerDescription);

    List<Trainer> findAllByOrderByLastName();

    // metode do odczytu i sortowania uklada sie z poszczegolnych czesci np findAllBy + OrderBy + LastName()

    @Query("select t from Trainer t where t.lastName=:lastName order by t.lastName asc ")
    List<Trainer> getTrainers(@Param("lastName") String lastName);


}
