//package onetomany.hobbies;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import java.util.Optional;
//
//public interface HobbiesRepository extends JpaRepository<Hobbies, Integer>{
//    Hobbies findById(int id);
//    void deleteById(int id);
//}
package onetomany.hobbies;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HobbiesRepository extends JpaRepository<Hobbies, Integer> {
    // No need to override findById and deleteById, they are already provided by JpaRepository
    Hobbies findByName(String Name);
}
