package pl.pwn.rest.client;

import com.bootcamp.bootcamp.model.CourseEdition;
import com.bootcamp.bootcamp.model.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

public class CourseRestClient {

    public static void main(String[] args) {


        getCourses();

        getCoursesById(1);
        User user = new User();
        user.setName("Robert");
        user.setSurname("Hau");
        user.setPassword("123");
        user.setEmail("hau@gmail.com");
        user.setPhoneNumber("123456789");
        getCreateUser(user, 1);


    }

    private static void getCreateUser(User user, int id) {

        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<Object> requestEntity = new HttpEntity<>(user, new HttpHeaders());

        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/client/pobierz/" + id, HttpMethod.POST, requestEntity,
                new ParameterizedTypeReference<String>() {
                });

        //w header sa dodatkowe rzeczy nie zwiazane z body. htttpentity sklada sie z body i headera

        String body = response.getBody();
        if (Objects.nonNull(body)) {
            if (HttpStatus.OK.equals(response.getStatusCode())) {
                System.out.println("kurs: \n" + body);
            } else {
                System.out.println("Nie ma kursu : " + response.getStatusCode() + "body: " + response.getBody());
            }
        }
    }

    private static void getCoursesById(int id) {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CourseEdition> response = restTemplate.getForEntity("http://localhost:8080/client/pobierz/" + id, CourseEdition.class);

        Object body = response.getBody();
        if(Objects.nonNull(body)){
            if(HttpStatus.OK.equals(response.getStatusCode())){
                System.out.println("Pojedynczy kurs: \n"+body);
            }
            else{
                System.out.println("Error status: "+response.getStatusCode()+"body: "+response.getBody());
            }
        }
    }

    private static void getCourses() {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<CourseEdition>> response = restTemplate.exchange("http://localhost:8080/client/kursy", HttpMethod.GET,
                new HttpEntity<>(new HttpHeaders()), new ParameterizedTypeReference<List<CourseEdition>>() {
                });
        List<CourseEdition> courseEditions = response.getBody();
        if(Objects.nonNull(courseEditions)&& !courseEditions.isEmpty()){

            System.out.println("All Course: " + courseEditions);
        }
        else
        {
            System.out.println("No course ...");
        }


    }


}
