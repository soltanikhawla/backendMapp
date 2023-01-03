package com.learnjwt.jwt_learn.Service;

import java.awt.image.DataBuffer;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import com.learnjwt.jwt_learn.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.learnjwt.jwt_learn.Dao.RoleDao;
import com.learnjwt.jwt_learn.Dao.UserDao;
import com.learnjwt.jwt_learn.Entity.Role;
import com.learnjwt.jwt_learn.Entity.User;

@Service
public class UserService {

    @Autowired
    private UserDao userdao;

    @Autowired
    private RoleDao roleDao;
    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
      public User create(User request){
          return userRepository.save(request);
      }
    public User registerNewUser(User user){
        Role role = roleDao.findById("User").get();
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRole(roles);
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));;
        return userdao.save(user);
    }

    public void initRoleandUser(){

        // Admin Role
        Role adminRole = new Role();
        adminRole.setRolename("Admin");
        adminRole.setRoleDescription("Admin fro administration");
        roleDao.save(adminRole);

        // User Role
        Role userRole = new Role();
        userRole.setRolename("User");
        userRole.setRoleDescription("default role for new User");
        roleDao.save(userRole);

        // admin registration
        User adminUser= new User();
        adminUser.setUsrFirstName("Admin");
        adminUser.setUserLastName("Admin");
        adminUser.setUserName("admin123");
        adminUser.setUserPassword(getEncodedPassword("admin@pass"));
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userdao.save(adminUser);
        

        // user regestration 
        User user= new User();
        user.setUsrFirstName("User");
        user.setUserLastName("User");
        user.setUserName("user123");
        user.setUserPassword(getEncodedPassword("user@pass"));
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);
        user.setRole(userRoles);
        userdao.save(user);
        

    }

    public String getEncodedPassword(String Password){
        return passwordEncoder.encode(Password);
    }



      /*
    public void uploadData(FilePart filePart){
        String CSV_SPLIT_REGEX = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";
        filePart.content()
                .map(DataBuffer::asInputStream)
                .map(inputStream ->{
                    try{
                        return inputStream.readALLBytes();
                    }catch(IOException e){ throw new RuntimeException(e);
                    }
                })
                .map(String::new)
                .flatMapIterable(data ->{
                    List<String> strings = Arrays.stream(data.split(CSV_SPLIT_REGEX).collect(Collectors.toList());
                    String header = strings.remove(0);
                    return strings;
                })
                .map(data -> data.trim().split(CSV_SPLIT_REGEX))
                .doOnNext(columns -> {
                    String Column_Name = columns[0];
                    String Description = columns [1];
                    String Column_Type= columns [2];
                    User user =new User(null,Column_Name,Description,Column_Type);
                    create(user);
                })
                .subscribe();       }


*/
}
