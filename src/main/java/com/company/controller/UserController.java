package com.company.controller;

import com.company.container.ComponentContainer;
import com.company.model.StudentBook;
import com.company.service.BookService;
import com.company.service.StudentBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    @Autowired
    private BookService bookService;
    @Autowired
    private StudentBookService studentBookService;

    public void userMenu() {
        int action = 0;
        do {
            showUserMenu();
            System.out.print("Enter action: ");
            action = ComponentContainer.scannerNum.nextInt();

            switch (action) {
                case 1:
                    bookList();
                    break;
                case 2:
                    takenBook();
                    break;
                case 3:
                    takeBook();
                    break;
                case 4:
                    history();
                    break;
                case 5:
                    returnedBook();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Mazgi togri sonni tanlang");
            }

        } while (action != 0);
    }

    public static void showUserMenu() {
        System.out.println("*** Menu ***");
        System.out.println(" 1 - Book List");
        System.out.println(" 2 - Taken book");
        System.out.println(" 3 - Take book");
        System.out.println(" 4 - History");
        System.out.println(" 5 - Return book");
        System.out.println(" 0 - Back");
    }

    public void bookList() {
        bookService.bookList();
    }

    public void takenBook() {
        studentBookService.takkenBook();
    }

    public void takeBook() {
        System.out.print("Enter book id: ");
        int bid = ComponentContainer.scannerNum.nextInt();

        StudentBook studentBook = new StudentBook();
        studentBook.setBookId(bid);

        studentBook.setStudentId(3);
        studentBookService.takeBook(studentBook);
    }

    public void returnedBook() {
        System.out.print("Enter book id: ");
        int bid = ComponentContainer.scannerNum.nextInt();

        studentBookService.returnedBook(bid, 3);
    }

    public void history() {
        studentBookService.history();
    }

    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    public void setStudentBookService(StudentBookService studentBookService) {
        this.studentBookService = studentBookService;
    }
}
