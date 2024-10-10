import { Component } from '@angular/core';
import { NavbarComponent } from "../navbar/navbar.component";
import { BookService } from '../../service/book.service';
import { FormsModule } from '@angular/forms';
import { NgFor, NgIf } from '@angular/common';

@Component({
  selector: 'app-add-book',
  standalone: true,
  imports: [NavbarComponent, FormsModule, NgIf, NgFor],
  templateUrl: './add-book.component.html',
  styleUrl: './add-book.component.css'
})
export class AddBookComponent {


  title: string;
  description: string;
  category: string;
  price: number;
  qty: number;
  successMsg = undefined;
  errorMsg = undefined;

  categories: string[];

  constructor(private bookService: BookService) {
    this.bookService.getCategories().subscribe({
      next: (data) => {
        this.categories = data;
      }
    })
  }


  onClick() {
    this.bookService.postBook({
      "title":this.title,
      "description":this.description,
      "category":this.category,
      "price":this.price,
      "qty":this.qty
  }).subscribe({
    next:(data)=>{
      this.successMsg = 'Book added';
      this.errorMsg = undefined
    },
    error:(err)=>{
      this.successMsg = undefined;
      console.log(err)
      if(err.status==300){
        this.errorMsg = err.message
      }
    }
  })
  }
  resetmsg() {
    this.successMsg = undefined;
    this.errorMsg=undefined;
  }


}
