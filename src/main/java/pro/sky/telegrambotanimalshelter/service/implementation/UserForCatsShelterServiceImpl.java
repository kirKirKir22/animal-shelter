package pro.sky.telegrambotanimalshelter.service.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambotanimalshelter.models.UserForCatsShelter;
import pro.sky.telegrambotanimalshelter.repository.UserForCatsShelterRepository;
import pro.sky.telegrambotanimalshelter.exceptions.UserForCatsShelterException;
import pro.sky.telegrambotanimalshelter.service.interfaces.UserForCatsShelterService;

import java.util.List;
import java.util.Optional;

@Service
public class UserForCatsShelterServiceImpl implements UserForCatsShelterService {

    private final UserForCatsShelterRepository userForCatsShelterRepository;
    private final Logger logger = LoggerFactory.getLogger(UserForCatsShelterServiceImpl.class);

    public UserForCatsShelterServiceImpl(UserForCatsShelterRepository userForCatsShelterRepository) {
        this.userForCatsShelterRepository = userForCatsShelterRepository;
    }

    public UserForCatsShelter add(UserForCatsShelter userForCatsShelter) {
        logger.info("Метод 'add' вызван с данными: " + userForCatsShelter);

        Optional<UserForCatsShelter> existingUser = userForCatsShelterRepository.findById(userForCatsShelter.getId());
        if (existingUser.isPresent()) {
            throw new UserForCatsShelterException.UserForCatsShelterConflictException("Такой пользователь уже существует в базе данных");
        }

        UserForCatsShelter savedUser = userForCatsShelterRepository.save(userForCatsShelter);
        logger.info("Метод 'add' вернул: " + savedUser);

        return savedUser;
    }

    public UserForCatsShelter read(long id) {
        logger.info("Метод 'read' вызван с данными: " + id);

        Optional<UserForCatsShelter> user = userForCatsShelterRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserForCatsShelterException.UserForCatsShelterNotFoundException("Пользователь в базе не найден");
        }

        UserForCatsShelter readUser = user.get();
        logger.info("Метод 'read' вернул: " + readUser);

        return readUser;
    }

    public UserForCatsShelter update(UserForCatsShelter userForCatsShelter) {
        logger.info("Метод 'update' вызван с данными: " + userForCatsShelter);

        if (!userForCatsShelterRepository.existsById(userForCatsShelter.getId())) {
            throw new UserForCatsShelterException.UserForCatsShelterNotFoundException("Пользователь в базе не найден");
        }

        UserForCatsShelter updatedUser = userForCatsShelterRepository.save(userForCatsShelter);
        logger.info("Метод 'update' вернул: " + updatedUser);

        return updatedUser;
    }

    public UserForCatsShelter delete(long id) {
        logger.info("Метод 'delete' вызван с данными: " + id);

        Optional<UserForCatsShelter> user = userForCatsShelterRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserForCatsShelterException.UserForCatsShelterNotFoundException("Пользователь в базе не найден");
        }

        UserForCatsShelter deletedUser = user.get();
        userForCatsShelterRepository.deleteById(id);
        logger.info("Метод 'delete' вернул: " + deletedUser);

        return deletedUser;
    }

    public List<UserForCatsShelter> findAll() {
        logger.info("Метод 'findAll' вызван");

        List<UserForCatsShelter> userList = userForCatsShelterRepository.findAll();

        logger.info("Метод 'findAll' вернул: " + userList);

        return userList;
    }

    public UserForCatsShelter saveNameAndPhone(long id, String name, String phone) {
        logger.info("Метод 'saveNameAndPhone' вызван с данными: id=" + id + ", name=" + name + ", phone=" + phone);

        Optional<UserForCatsShelter> userOptional = userForCatsShelterRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new UserForCatsShelterException.UserForCatsShelterNotFoundException("Пользователь в базе не найден");
        }

        UserForCatsShelter user = userOptional.get();
        user.setName(name);
        user.setPhone(phone);
        UserForCatsShelter updatedUser = userForCatsShelterRepository.save(user);

        logger.info("Метод 'saveNameAndPhone' вернул: " + updatedUser);

        return updatedUser;
    }

}
