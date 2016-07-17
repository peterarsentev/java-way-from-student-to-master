package ru.parsentev.services;

import com.google.common.base.Joiner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.repository.CrudRepository;
import ru.parsentev.models.*;
import ru.parsentev.repositories.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * TODO: comment
 *
 * @author parsentev
 * @since 17.07.2016
 */
public class DbInit {
    private static final Logger log = getLogger(DbInit.class);

    private final String name;

    public DbInit(final String name) {
        this.name = name;
    }

    public void process() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-context.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(Records.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Records records = (Records) jaxbUnmarshaller.unmarshal(new File("records.xml"));
        this.imports(context.getBean(RoleRepository.class), records.getRoles());
        this.imports(context.getBean(UserRepository.class), records.getUsers());
        this.imports(context.getBean(PetTypeRepository.class), records.getTypes());
        this.imports(context.getBean(PetRepository.class), records.getPets());
        this.imports(context.getBean(MessageRepository.class), records.getMessages());
    }

    private <T extends Base> void imports(CrudRepository<T, Integer> repository, List<T> models) {
        for (T model : models) {
            if (!repository.exists(model.getId())) {
                repository.save(model);
            }
        }
    }

    @XmlRootElement
    public static final class Records {
        private List<Role> roles = new ArrayList<>();
        private List<Message> messages = new ArrayList<>();
        private List<Pet> pets = new ArrayList<>();
        private List<PetType> types = new ArrayList<>();
        private List<User> users = new ArrayList<>();

        @XmlElementWrapper(name="roles")
        @XmlElement(name="role")
        public List<Role> getRoles() {
            return roles;
        }

        public void setRoles(List<Role> roles) {
            this.roles = roles;
        }

        @XmlElementWrapper(name="messages")
        @XmlElement(name="message")
        public List<Message> getMessages() {
            return messages;
        }

        public void setMessages(List<Message> messages) {
            this.messages = messages;
        }

        @XmlElementWrapper(name="pets")
        @XmlElement(name="pet")
        public List<Pet> getPets() {
            return pets;
        }

        public void setPets(List<Pet> pets) {
            this.pets = pets;
        }

        @XmlElementWrapper(name="types")
        @XmlElement(name="type")
        public List<PetType> getTypes() {
            return types;
        }

        public void setTypes(List<PetType> types) {
            this.types = types;
        }

        @XmlElementWrapper(name="users")
        @XmlElement(name="user")
        public List<User> getUsers() {
            return users;
        }

        public void setUsers(List<User> users) {
            this.users = users;
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length > 0) {
            new DbInit(args[0]).process();
        } else {
            System.out.println(
                    Joiner.on("\r\n").join(
                            "Error, You miss a data source file. ",
                            "Usage: java -jar dbtool.jar ./records.xml"
                    )
            );
        }
    }
}
