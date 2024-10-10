import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  constructor(private http:HttpClient) { }


  getAllBooks(page:number, size:number):Observable<any>{
    return this.http.get('http://localhost:8081/book/find/all?page='+page+'&size='+size);
  }

  getCategories():Observable<any>{
    return this.http.get('http://localhost:8081/book/categories');
  }

  postBook(obj:any):Observable<any>{
    return this.http.post('http://localhost:8081/book/add', obj);
  }

  getBookDetailsById(id:any):Observable<any>{
    return this.http.get('http://localhost:8081/book/find/'+id);
  }

  deleteBook(id:any):Observable<any>{
    return this.http.delete('http://localhost:8081/book/delete/'+id)
  }


}
