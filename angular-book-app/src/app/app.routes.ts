import { Routes } from '@angular/router';
import { DisplayBooksComponent } from './component/display-books/display-books.component';
import { AddBookComponent } from './component/add-book/add-book.component';
import { EditBookComponent } from './component/edit-book/edit-book.component';
import { BookDescriptionComponent } from './component/book-description/book-description.component';

export const routes: Routes = [

    {
        "path":"",component:DisplayBooksComponent
    },
    {
        "path":"book/add",component:AddBookComponent
    },
    {
        "path":"book/edit/:id",component:EditBookComponent
    },
    {
        "path":"book/description/:id",component:BookDescriptionComponent
    }

];
