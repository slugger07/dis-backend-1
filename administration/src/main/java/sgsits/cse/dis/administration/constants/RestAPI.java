package sgsits.cse.dis.administration.constants;

public class RestAPI {
	
	
	//LIBRARY API's
	
	//Books
	public static final String ADD_BOOK ="/addBook"; 
	public static final String GET_ALL_BOOKS ="/getAllBooks";
	public static final String GET_BOOK_BY_TITLE = "/getBookByTitle/{title}";
	public static final String GET_BOOK_BY_BOOK_ID = "/getBookByBookId/{bookId}";
	public static final String GET_BOOK_BY_AUTHOR_NAME = "/getBookByAuthorName/{authorName}";
	public static final String GET_SUBJECT_CATEGORY_LIST = "/getSubjectCatergoryAcronymList";
	public static final String UPDATE_BOOK = "/updateBook/{bookId}";
	public static final String DELETE_BOOK = "/deleteBook/{bookId}"; 
	
	//Thesis
	public static final String ADD_THESIS = "/addThesis"; 
	public static final String GET_ALL_THESIS ="/getAllThesis";
	public static final String GET_THESIS_BY_TITLE = "/getThesisByTitle/{title}";
	public static final String GET_THESIS_BY_SUBMITTED_BY = "/getThesisBySubmittedBy/{submittedBy}";
	public static final String GET_THESIS_BY_GUIDED_BY = "/getThesisByGuidedBy/{guidedBy}";
	public static final String GET_THESIS_BY_THESIS_ID = "/getThesisByThesisId/{thesisId}";
	public static final String GET_THESIS_BY_COURSE = "/getThesisByCourse/{course}";
	public static final String UPDATE_THESIS = "/updateThesis/{thesisId}";
	public static final String DELETE_THESIS = "/deleteThesis/{thesisId}"; 

}
