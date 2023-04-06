package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import guru.springframework.spring6webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author : Lou
 * @since : 2023/04/06, Thu, 09:03
 **/

@Component // - spring stereotype to indicate that the Spring Context has to detect this.
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        // Uncle Bob
        Author bob = new Author();
        bob.setFirstName("Robert");
        bob.setLastName("Martin");

        Book cleanCode = new Book();
        cleanCode.setTitle("Clean Code");
        cleanCode.setIsbn("918273645");

        Book cleanCoder = new Book();
        cleanCoder.setTitle("The Clean Coder");
        cleanCoder.setIsbn("9182-5647-1100");

        Publisher prentice = new Publisher();
        prentice.setPublisherName("Prentice Hall");
        prentice.setAddress("501 Boylston Street, Suite 900");
        prentice.setCity("Boston");
        prentice.setState("MA");
        prentice.setZip("02116");

        // Save to DB - Persist
        Author savedBob = this.authorRepository.save(bob); // returns new Author
        Book savedCleanCode = this.bookRepository.save(cleanCode); // return new Book
        Book savedCoder = this.bookRepository.save(cleanCoder);
        Publisher savedPrentice = this.publisherRepository.save(prentice);

        System.out.println("BookID: " + savedCleanCode.getId());
        System.out.println("AuthorID: " + savedBob.getId());
        System.out.println("PublisherID: " + savedPrentice.getId());

        // Create Association: (DO BOTH WAYS)
        // Add Books to Author
        savedBob.getBooks().add(savedCleanCode);
        savedBob.getBooks().add(savedCoder);
        // Add Author(s) to Books
        savedCleanCode.getAuthors().add(savedBob);
        savedCoder.getAuthors().add(savedBob);
        // Add Publisher to Books
        savedCleanCode.setPublisher(savedPrentice);
        savedCoder.setPublisher(savedPrentice);
        // Add Books to Publisher
        savedPrentice.getBooks().add(savedCleanCode);
        savedPrentice.getBooks().add(savedCoder);

        // persist the newly created records
        this.authorRepository.save(savedBob);
        this.bookRepository.save(savedCleanCode);
        this.bookRepository.save(savedCoder);
        this.publisherRepository.save(savedPrentice);

        System.out.println("In Bootstrap");
        System.out.println("Author Count: " + this.authorRepository.count());
        System.out.println("Book Count: " + this.bookRepository.count());
        System.out.println("Publisher Count: " + this.publisherRepository.count());

        // Terry Pratchett
        Author terry = new Author();
        terry.setFirstName("Terry");;
        terry.setLastName("Pratchett");

        Book colourMagic = new Book();
        colourMagic.setTitle("Colour of Magic");
        colourMagic.setIsbn("111-222-333");

//        colourMagic.getAuthors().add(terry);

        Book lightFantastic = new Book();
        lightFantastic.setTitle("Light Fantastic");
        lightFantastic.setIsbn("333-222-111");

//        lightFantastic.getAuthors().add(terry);

//        terry.getBooks().add(colourMagic);
//        terry.getBooks().add(lightFantastic);

        Publisher transWorld = new Publisher();
        transWorld.setPublisherName("Transworld Publishers");
        transWorld.setAddress("61-63 Uxbridge Road");
        transWorld.setCity("London");

//        colourMagic.setPublisher(transWorld);
//        lightFantastic.setPublisher(transWorld);

//        transWorld.getBooks().add(colourMagic);
//        transWorld.getBooks().add(lightFantastic);

        Author savedTerry = this.authorRepository.save(terry);
        Book savedColour = this.bookRepository.save(colourMagic);
        Book savedLight = this.bookRepository.save(lightFantastic);
        Publisher savedTransWorld = this.publisherRepository.save(transWorld);

        savedTerry.getBooks().add(savedColour);
        savedTerry.getBooks().add(savedLight);

        savedColour.getAuthors().add(savedTerry);
        savedColour.setPublisher(savedTransWorld);
        savedLight.getAuthors().add(savedTerry);
        savedLight.setPublisher(savedTransWorld);

        savedTransWorld.getBooks().add(savedColour);
        savedTransWorld.getBooks().add(savedLight);

        //Be Persistent!!
        this.bookRepository.save(savedColour);
        this.bookRepository.save(savedLight);
        this.authorRepository.save(savedTerry);
        this.publisherRepository.save(transWorld);

        System.out.println("In Bootstrap");
        System.out.println("Author Count: " + this.authorRepository.count());
        System.out.println("Book Count: " + this.bookRepository.count());
        System.out.println("Publisher Count: " + this.publisherRepository.count());
        // below println causes StackOverflowException - due to toString...
//        System.out.println("Publisher BOoks: " + savedPrentice.getBooks().toString()); // this is null.  Why should we save it both ways?  Thought its linked with annotations.
//        System.out.println("Book Publisher: " + savedCleanCode.getPublisher());



    }
}
