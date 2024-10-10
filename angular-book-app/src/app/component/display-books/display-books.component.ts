import { Component, OnInit } from '@angular/core';
import { NavbarComponent } from "../navbar/navbar.component";
import { NgFor, NgIf } from '@angular/common';
import { BookService } from '../../service/book.service';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-display-books',
  standalone: true,
  imports: [NavbarComponent, NgFor, RouterLink, NgIf],
  templateUrl: './display-books.component.html',
  styleUrl: './display-books.component.css'
})
export class DisplayBooksComponent implements OnInit {

  books: any[] = [];
  totalPages: number = 0;
  numArray: number[] = [];
  counter: number = 0;
  page: number = 0;
  size: number = 5;
  last: boolean = false;
  first: boolean = false;

  deleteMsg: string = undefined;

  constructor(private bookService: BookService, private router: Router) { }


  ngOnInit(): void {
    this.fetchData()
  }

  fetchData() {
    this.bookService.getAllBooks(this.page, this.size).subscribe({
      next: (data) => {
        this.books = data.content;
        this.totalPages = data.totalPages;
        this.first = data.first;
        this.last = data.last;
        if (this.counter === 0) {
          let i = 0;
          while (i < this.totalPages) {
            this.numArray.push(i);
            i++;
          };
        }

        this.counter = this.counter + 1;
      },
      error: (err) => {
        console.log(err);
      }

    })
  }

  onPageNumberClick(n: number) {

    this.page = n;
    this.fetchData();
  }

  onNext() {
    this.page = this.page + 1;
    this.fetchData();

  }

  onPrev() {
    this.page = this.page - 1;
    this.fetchData();
  }


  OnTitleClick(id: any) {
    this.router.navigateByUrl("book/description/"+id)
  }

  onAdd() {
    this.router.navigateByUrl("/book/add")
  }

  onEdit(id: any) {
    this.router.navigateByUrl("/book/edit/" + id)
  }

  onDelete(id: any) {
    this.bookService.deleteBook(id).subscribe({
      next: (data) => {
        this.books = this.books.filter(book => book.id !== id);
        this.deleteMsg = data.msg;
      },
      error: (err) => {
        console.log(err);
      }
    })
  }



}
