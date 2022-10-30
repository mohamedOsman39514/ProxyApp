package com.example.proxy.rest.handler;

import com.example.proxy.model.PasswordResetToken;
import com.example.proxy.model.User;
import com.example.proxy.rest.dto.UserDto;
import com.example.proxy.rest.exception.SQLException;
import com.example.proxy.rest.exception.ResourceNotFound;
import com.example.proxy.rest.exception.Response;
import com.example.proxy.rest.mapper.UserMapper;
import com.example.proxy.security.PasswordUtil;
import com.example.proxy.service.PasswordTokenService;
import com.example.proxy.service.UserService;
import com.example.proxy.utils.email.EmailDetails;
import com.example.proxy.utils.email.EmailService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
@Log4j2
public class UserHandler {
//    @Autowired
    private UserMapper userMapper;

//    @Autowired
    private UserService userService;

//    @Autowired
    private PasswordTokenService passwordTokenService;

//    @Autowired
    private EmailService emailService;

//    @Autowired
    private PasswordUtil passwordUtil;

//    @Autowired
    private SQLException psqlException;

    public ResponseEntity<?> register(UserDto userDto) {
        try {
            log.info("create a new user");
            User user = userMapper.toUser(userDto);
            userService.register(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    new Response(psqlException.getError(ex)));
        }
    }

    public ResponseEntity<?> update(Long id, UserDto userDto) throws ResourceNotFound {
        User user = userMapper.toUser(userDto);
        User userById = userService.findById(id)
                .orElseThrow(() -> new ResourceNotFound("user of id " + id + " Not Found"));
        userById.setName(user.getName() != null ? user.getName() : userById.getName());
        userById.setEmail(user.getEmail() != null ? user.getEmail() : userById.getEmail());
        userById.setNationalId(user.getNationalId() != null ? user.getNationalId() : userById.getNationalId());
        userById.setPhone(user.getPhone() != null ? user.getPhone() : userById.getPhone());
        userById.setJob(user.getJob() != null ? user.getJob() : userById.getJob());
        userById.setParty(user.getParty() != null ? user.getParty() : userById.getParty());
        userById.setAddress(user.getAddress() != null ? user.getAddress() : userById.getAddress());
        userService.save(userById);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userById);
    }

    public ResponseEntity<?> getById(Long id) throws ResourceNotFound {
        User user = userService.findById(id)
                .orElseThrow(() -> new ResourceNotFound("The user of id " + id + " Not Found"));
        UserDto userDto = userMapper.toUserDto(user);
        return ResponseEntity.ok(userDto);
    }

    public ResponseEntity<List<?>> getAll() {
        List<?> userDtoList = userMapper.toUserDtos(userService.findAll());
        return ResponseEntity.ok(userDtoList);
    }

    public ResponseEntity<?> delete(Long id) throws ResourceNotFound {
        User serviceType = userService.findById(id)
                .orElseThrow(() -> new ResourceNotFound("user of id " + id + " Not Found"));
        userService.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("deleted"));
    }

    public ResponseEntity<?> forgetPassword(UserDto userDto, HttpServletRequest request ) throws ResourceNotFound {
        User user = userMapper.toUser(userDto);
        User userByEmail = userService.findByEmail(user.getEmail());
        if (userByEmail == null) {
            throw new ResourceNotFound("user of email: "+ user.getEmail() +" Not Found");
        }
        String token = UUID.randomUUID().toString();
        passwordTokenService.createPasswordResetTokenForUser(userByEmail, token);
        String appUrl = "http://"+ request.getServerName() + ":"
                + request.getServerPort() +"/user/resetpassword/"+token;
        EmailDetails details = new EmailDetails();
        details.setRecipient(user.getEmail());
        details.setSubject("Reset your password");
        details.setMsgBody("Forgot your password?" +
                " Submit a PATCH request with your new password and to:" + appUrl +
                "\nIf you didn't forget your password, please ignore this email!");
        String status = emailService.sendSimpleMail(details);
        return ResponseEntity.ok().body(new Response("Mail sent Successfully"));
    }

    public ResponseEntity<?> resetPassword(UserDto userDto, String resetToken){
        User user = userMapper.toUser(userDto);
        System.out.println("resetToken:  "+resetToken);
        PasswordResetToken token = passwordTokenService.getResetToken(resetToken);
        System.out.println("token:  "+token);
        if(token.getToken().isEmpty()){
            return ResponseEntity.status(404).body(new Response("This reset token not found......"));
        }
        String result = passwordUtil.validatePasswordResetToken(resetToken);
        if(result != null){
            return  ResponseEntity.status(401).body(new Response("Reset Token Expired......"));
        }
        String email = token.getUser().getEmail();
        userService.updatePassword(email,user.getPassword());
        return ResponseEntity.status(200).body(new Response("the password changed....."));
    }

    public ResponseEntity<?> changeUserPassword(UserDto userDto, String newPassword) {
        User user = userMapper.toUser(userDto);
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User userId = userService.findByEmail(email);
        BCryptPasswordEncoder passwordEncoder =new BCryptPasswordEncoder();
        if(passwordEncoder.matches(user.getPassword(),userId.getPassword())){
            userService.updatePassword(email,newPassword);
            return ResponseEntity.status(200).body(new Response("successfully updated......"));
        }
        else return ResponseEntity.status(403).body(new Response("incorrect password"));
    }

}
