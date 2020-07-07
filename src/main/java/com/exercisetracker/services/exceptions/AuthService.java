package  com.exercisetracker.services.exceptions;

import java.util.Random;

import  com.exercisetracker.domain.User;
import  com.exercisetracker.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder pe;

    // @Autowired
    // private EmailService emailService;

    private Random random = new Random();

    public void sendNewPassword(String email) {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new ObjectNotFoundException("Email não encontrado");
        }

        String newPass = newPassword();
        user.setPassword(pe.encode(newPass));

        userRepository.save(user);
        // emailService.sendNewPasswordEmail(user, newPass);
    }

    private String newPassword(){
        char[] vet = new char[10];
        for(int i = 0; i<10; i++){
            vet[i] = randomChar();
        }
        return new String(vet);
    }

    private char randomChar() {
        int opt = random.nextInt(3);
        if (opt == 0) { // gera um digito
            return (char) (random.nextInt(10) + 48);
        } else if (opt == 1) { // Gera letra maiuscula
            return (char) (random.nextInt(26) + 65);
        } else { // Gera letra minuscula
            return (char) (random.nextInt(26) + 97);
        }
    }

}