package com.example.application.backEnd.service.impl;

import com.example.application.backEnd.builder.UsersBuilder;
import com.example.application.backEnd.domain.CodeConfirmation;
import com.example.application.backEnd.domain.Users;
import com.example.application.backEnd.reporitory.CodeConfirmationRepository;
import com.example.application.backEnd.reporitory.UserRepository;
import com.example.application.backEnd.service.ResponseException;
import com.example.application.backEnd.service.UsersService;
import com.example.application.backEnd.utils.GenerateCodeHelper;
import com.example.application.backEnd.viewModel.UserViewModel;
import com.example.application.backEnd.viewModel.account.AuthViewModel;
import com.example.application.backEnd.viewModel.account.RegistrationViewModel;
import com.example.application.models.EnumType;
import com.example.application.translation.TranslationProvider;
import com.vaadin.flow.component.UI;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UsersService {

    UsersBuilder usersBuilder;
    UserRepository userRepository;
    CodeConfirmationRepository codeConfirmationRepository;
    private final MailSenderService mailSenderService;

    private final TranslationProvider translationProvider = new TranslationProvider();

    public List<UserViewModel> getAll() {
        List<Users> userList = userRepository.findAll();
        List<UserViewModel> userViewModels = new ArrayList<>();
        for (Users item : userList) {
            userViewModels.add(usersBuilder.build(item));
        }
        return userViewModels;
    }

    public void deleteById(Long id) {
        Optional<Users> userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        userRepository.deleteById(userOpt.get().getId());
        userRepository.deleteById(id);
    }

    public Users create(Users users) {
        usersBuilder.create(users);
        userRepository.save(users);
        return users;
    }

    public void update(Long id, Users users, UserViewModel request) {
        Optional<Users> userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (userOpt.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        usersBuilder.update(users, request);
    }

    public Optional<Users> getById(Long id) {
        var user = userRepository.findById(id);
        if (user.isEmpty()){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return user;

    }
    @Override
    public Users registration(RegistrationViewModel request) throws ResponseException {
        if (request.getPassword().length() < 6) {
            throw new ResponseException(this.translationProvider.getTranslation("shortPassword",
                    UI.getCurrent().getLocale()), this.
                    translationProvider.getTranslation("passwordLengthMustBeMoreThanFiveCharacters",
                            UI.getCurrent().getLocale()), 504);
        }

        var userLoginOpt = userRepository.findFirstByUsername(request.getUsername());
        if (userLoginOpt.isPresent()) {
            throw new ResponseException(this.translationProvider.getTranslation("thisCustomAlreadyExists",
                    UI.getCurrent().getLocale()), this.translationProvider.getTranslation("chooseAnotherName",
                    UI.getCurrent().getLocale()), 504);
        }

        var userEmailOpt = userRepository.findFirstByEmail(request.getEmail());
        if (userEmailOpt.isPresent()) {
            throw new ResponseException(this.translationProvider.getTranslation("userWithEmailAlreadyExists",
                    UI.getCurrent().getLocale()), this.translationProvider.getTranslation("chooseAnotherEmail",
                    UI.getCurrent().getLocale()), 504);
        }

        var entityUser = usersBuilder.regBuild(request);
        userRepository.save(entityUser);
        return userRepository.save(entityUser);
    }

    @Override
    public User auth(AuthViewModel request) throws ResponseException {
        Optional<Users> userOpt = userRepository.findFirstByUsernameAndPassword(request.getUsername(),
                DigestUtils.md5DigestAsHex(request.getPassword().getBytes()));

        var result = userOpt.map(users -> {
            var u = new User(
                    users.getUsername(),
                    users.getPassword(),
                    List.of(new SimpleGrantedAuthority("ROLE_USER"))
            );
            SecurityContext context = SecurityContextHolder.getContext();
            var authentication = new UsernamePasswordAuthenticationToken(
                    u.getUsername(),
                    u.getPassword(),
                    u.getAuthorities()
            );
            context.setAuthentication(authentication);
            return u;
        }).orElseThrow(() -> new ResponseException(this.translationProvider.getTranslation("wrongLoginOrPassword",
                UI.getCurrent().getLocale()), this.translationProvider.getTranslation("wrongLoginOrPassword",
                UI.getCurrent().getLocale()), 102));

        return result;
    }

    @Override
    public void changePassword(Users users, String oldPassword, String newPassword, String repeatPassword,
                               EnumType type)
            throws ResponseException {

        String hashOldPassword = DigestUtils.md5DigestAsHex(oldPassword.getBytes());
        if (EnumType.EDIT.equals(type)) {
            if (!hashOldPassword.equals(users.getPassword()) || !newPassword.equals(repeatPassword)) {
                throw new ResponseException("Ошибка", "Старый пароль неверный", 400);
            }
        } else if (EnumType.RECOVER.equals(type)) {
            if (!hashOldPassword.equals(users.getPassword())) {
                throw new ResponseException("Ошибка", "Старый пароль неверный", 400);
            }
        }

        users.setPassword(DigestUtils.md5DigestAsHex(newPassword.getBytes()));
        userRepository.save(users);
    }

    @Override
    public void emailVerification(String email, EnumType type) {
        CodeConfirmation codeConfirmation = CodeConfirmation.builder()
                .confirmation(false)
                .email(email)
                .code(GenerateCodeHelper.randomGenerateCode())
                .dateOfCreation(new Date())
                .build();

        if (EnumType.REG.equals(type)) {
            String text = String.format(
                    """
                            Ниже Ваша ссылка для регистрации
                            http://91.239.26.196:789/auth?email=%s&code=%s
                            """,
                    email,
                    codeConfirmation.getCode());
            mailSenderService.sendSimpleMessage(email, "Регистрация", text);

        } else {
            String text = String.format(
                    """
                            Ниже Ваша ссылка на восстановление пароля
                            http://91.239.26.196:789/recovery-password?email=%s&code=%s
                            """,
                    email,
                    codeConfirmation.getCode());
            mailSenderService.sendSimpleMessage(email, "Восстановление пароля", text);
        }

        codeConfirmationRepository.save(codeConfirmation);
    }

    @Override
    public boolean restorePassword(String email, String code, String password) {
        var codeOpt = codeConfirmationRepository.findByEmailAndCode(email, code);
        if (codeOpt.isPresent()) {
            var userOpt = userRepository.findFirstByEmail(codeOpt.get().getEmail());
            if (userOpt.isPresent()) {
                var user = userOpt.get();
                user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }
}

