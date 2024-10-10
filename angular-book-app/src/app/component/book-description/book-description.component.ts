import { Component, OnInit } from '@angular/core';
import { NavbarComponent } from "../navbar/navbar.component";
import { BookService } from '../../service/book.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-book-description',
  standalone: true,
  imports: [NavbarComponent],
  templateUrl: './book-description.component.html',
  styleUrl: './book-description.component.css'
})
export class BookDescriptionComponent implements OnInit{

  id:any;
  description:string;

  constructor(private bookService:BookService, private actRoute:ActivatedRoute){
      this.id = this.actRoute.snapshot.paramMap.get('id');
  }

  ngOnInit(): void {
    this.bookService.getBookDetailsById(this.id).subscribe({
      next:(data)=>{
        this.description=data.description;
      },
      error:(err)=>{
        console.log(err);
      }
    })
  }

  

}
