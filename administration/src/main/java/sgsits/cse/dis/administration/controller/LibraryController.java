package sgsits.cse.dis.administration.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import sgsits.cse.dis.administration.constants.RestAPI;
import sgsits.cse.dis.administration.exception.ConflictException;
import sgsits.cse.dis.administration.exception.EventDoesNotExistException;
import sgsits.cse.dis.administration.feignClient.AcademicsClient;
import sgsits.cse.dis.administration.request.AddBookForm;
import sgsits.cse.dis.administration.request.AddThesisForm;
import sgsits.cse.dis.administration.response.AddBookResponse;
import sgsits.cse.dis.administration.response.AddThesisResponse;
import sgsits.cse.dis.administration.response.LibraryBookRecordsResponse;
import sgsits.cse.dis.administration.serviceImpl.LibraryServicesImpl;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/library")
@Api(value = "Library")
public class LibraryController {
	
	@Autowired
	LibraryServicesImpl libraryServicesImpl;
	
	@Autowired
	AcademicsClient academicsClient;
	
	@ApiOperation(value="Add a book", response = AddBookResponse.class, httpMethod = "POST", produces = "application/json")
	@PostMapping(path=RestAPI.ADD_BOOK, produces = "application/json")
	public AddBookResponse addBook(@RequestBody AddBookForm addBookForm) throws ConflictException {
		String bookId;
		try{
			bookId=libraryServicesImpl.addBook(addBookForm);
		}catch(ConflictException e){
			e.printStackTrace();
			throw new ConflictException("No records updated. This due to conflict in information on client side.");
		}
			return new AddBookResponse(" Book added successfully. Please note book's id ",bookId);
	}
	
	@ApiOperation(value="Get all books", response = LibraryBookRecordsResponse.class, httpMethod = "GET", produces = "application/json")
	@GetMapping(path=RestAPI.GET_ALL_BOOKS, produces = "application/json")
	public List<LibraryBookRecordsResponse> getAllBooks(){
		return libraryServicesImpl.getAllBooks();	
	}
	
	@ApiOperation(value="Get books by title", response = LibraryBookRecordsResponse.class, httpMethod = "GET", produces = "application/json")
	@GetMapping(path=RestAPI.GET_BOOK_BY_TITLE, produces = "application/json")
	public List<LibraryBookRecordsResponse> getBookByTitle(@PathVariable("title") String title) throws EventDoesNotExistException{
		List<LibraryBookRecordsResponse> libraryBookRecordsResponses = new ArrayList<LibraryBookRecordsResponse>();
		try {
			libraryBookRecordsResponses = libraryServicesImpl.getBookByTitle(title);
		} catch (EventDoesNotExistException e) {
			e.printStackTrace();
			throw new EventDoesNotExistException("Book with Title ["+title+"] doesn't exist.");
		}
		return libraryBookRecordsResponses;
	}
	
	
	@ApiOperation(value="Get book by author name", response = LibraryBookRecordsResponse.class, httpMethod = "GET", produces = "application/json")
	@GetMapping(path=RestAPI.GET_BOOK_BY_AUTHOR_NAME, produces = "application/json")
	public List<LibraryBookRecordsResponse> getBookByAuthorName(@PathVariable("authorName") String authorName) throws EventDoesNotExistException{
		List<LibraryBookRecordsResponse> libraryBookRecordsResponses = new ArrayList<LibraryBookRecordsResponse>();
		try {
			libraryBookRecordsResponses = libraryServicesImpl.getBookByAuthorName(authorName);
		} catch (EventDoesNotExistException e) {
			e.printStackTrace();
			throw new EventDoesNotExistException("Book with author name ["+authorName+"] doesn't exist.");
		}
				
		return libraryBookRecordsResponses;
	}
	
	@ApiOperation(value="Get subject categry acronyms", response = String.class, httpMethod = "GET", produces = "application/json")
	@GetMapping(path=RestAPI.GET_SUBJECT_CATEGORY_LIST, produces = "application/json")
	public List<String> getSubjectCatergoryAcronymList(){
		return academicsClient.getAllSubjectAcronym();
	}
	
	@ApiOperation(value="Update a book", response = AddBookResponse.class, httpMethod = "PUT", produces = "application/json")
	@PutMapping(path=RestAPI.UPDATE_BOOK, produces = "application/json")
	public AddBookResponse updateBook(@PathVariable("bookId") String bookId,@RequestBody AddBookForm addBookForm) throws ConflictException{

		try{
			libraryServicesImpl.updateBook(addBookForm,bookId);
		}catch(ConflictException e){
			e.printStackTrace();
			throw new ConflictException("No records updated. This due to conflict in information on client side.");
		}
			return new AddBookResponse(" Book updated successfully. Please note book's id ",bookId);
	}
	
	@ApiOperation(value="delete a book", response = AddBookResponse.class, httpMethod = "DELETE", produces = "application/json")
	@DeleteMapping(path=RestAPI.DELETE_BOOK, produces = "application/json")
	public ResponseEntity<String> deleteBook(@PathVariable("bookId") String bookId) throws EventDoesNotExistException{

		try{
			libraryServicesImpl.deleteBook(bookId);
		}catch(EventDoesNotExistException e){
			e.printStackTrace();
			throw new EventDoesNotExistException("Unable to delete book "+bookId+".");
		}
			return new ResponseEntity<String>(new String("Book ["+bookId+"] deleted successfully. "),HttpStatus.OK);
	}
	
	
	//THESIS Services
	@ApiOperation(value="Add a thesis", response= AddThesisResponse.class, httpMethod = "POST", produces="application/json")
	@PostMapping(path=RestAPI.ADD_THESIS, produces= "application/json")
	public AddThesisResponse addThesis(@RequestBody AddThesisForm addThesisForm) throws ConflictException {
		
		Long thesisId; 
		try {
			thesisId = libraryServicesImpl.addThesis(addThesisForm);
		} catch (ConflictException e)
		{
			e.printStackTrace();
			throw new ConflictException("No records updated. This is due to conflict in information on client side.");
		}
		    return new AddThesisResponse("Thesis added successfully. Please note Thesis id ", thesisId);
		
	}
	
	
}
