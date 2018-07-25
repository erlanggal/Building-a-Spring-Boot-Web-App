package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData()
    {
        Publisher publisher = new Publisher();
        publisher.setName("Erlangga");
        publisher.setAddress("Tangerang");
        this.publisherRepository.save(publisher);

        Author naruto = new Author("Naruto", "Uzumaki");
        Book narutoBook = new Book("Bleach", "1234", publisher);
        naruto.getBooks().add(narutoBook);
        narutoBook.getAuthors().add(naruto);
        this.authorRepository.save(naruto);
        this.bookRepository.save(narutoBook);

        Author sasuke = new Author("Sasuke", "Uchiha");
        Book sasukeBook = new Book("One Pieace", "456", publisher);
        sasuke.getBooks().add(sasukeBook);
        sasukeBook.getAuthors().add(sasuke);
        this.authorRepository.save(sasuke);
        this.bookRepository.save(sasukeBook);
    }
}
