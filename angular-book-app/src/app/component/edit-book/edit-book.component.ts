import { Component, OnInit } from '@angular/core';
import { NavbarComponent } from "../navbar/navbar.component";
import { BookService } from '../../service/book.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { NgFor, NgIf } from '@angular/common';

@Component({
  selector: 'app-edit-book',
  standalone: true,
  imports: [NavbarComponent, FormsModule, NgFor, NgIf],
  templateUrl: './edit-book.component.html',
  styleUrl: './edit-book.component.css'
})
export class EditBookComponent implements OnInit{

  id:any;
  title: string;
  description: string;
  category: string;
  price: number;
  qty: number;
  successMsg = undefined;
  errorMsg = undefined;

  categories: string[];

  constructor(private bookService:BookService, private router:Router, private actRoute:ActivatedRoute){
    this.id=this.actRoute.snapshot.paramMap.get('id');
  }
  
 ngOnInit(): void {
  this.bookService.getCategories().subscribe({
    next: (data) => {
      this.categories = data;
    }
  })
  
  this.bookService.getBookDetailsById(this.id).subscribe({
    next: (data)=>{
      console.log(data);
      this.title=data.title,
      this.description=data.description,
      this.category=data.category;
      this.price=data.price;
      this.qty=data.qty
    },
    error:(err)=>{
      console.log(err);
    }
  })

  }

  resetmsg() {
    this.successMsg=undefined;
    this.errorMsg=undefined;    
  }

  OnClickEdit() {
    this.bookService.postBook({
      "id":this.id,
      "title":this.title,
      "description":this.description,
      "category":this.category,
      "price":this.price,
      "qty":this.qty
  }).subscribe({
    next:(data)=>{
      this.successMsg = 'Book edited';
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


}

