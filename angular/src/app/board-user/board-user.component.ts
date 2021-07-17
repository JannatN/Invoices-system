// import { Component, OnInit } from '@angular/core';
// import { UserService } from '../_services/user.service';
import { InvoiceService } from '../_services/invoices.service';
import { Invoice } from '../models/invoice';

// import { InvoiceService } from 'src/app/_services/invoice.service';
// @Component({
//   selector: 'app-board-user',
//   templateUrl: './board-user.component.html',
//   styleUrls: ['./board-user.component.css']
// })
// export class BoardUserComponent implements OnInit {
//   // content = '';

//   // constructor(private userService: UserService) { }

//   // ngOnInit() {
//   //   this.userService.getUserBoard().subscribe(
//   //     data => {
//   //       this.content = data;
//   //     },
//   //     err => {
//   //       this.content = JSON.parse(err.error).message;
//   //     }
//   //   );
//   // }
//   invoices?: Invoice[];
//   currentInvoice?: Invoice;
//   currentIndex = -1;
//   title = '';

//   constructor(private invoiceService: InvoiceService) { }

//   ngOnInit(): void {
//     this.retrieveInvoices();
//   }

//   retrieveInvoices(): void {
//     this.invoiceService.getAll()
//       .subscribe(
//         data => {
//           this.invoices = data;
//           console.log(data);
//         },
//         error => {
//           console.log(error);
//         });
//   }
// }



// import { UserDetailsComponent } from '../user-details/user-details.component';
import { Observable } from "rxjs";
// import { UserService } from "../_services/users.service";
// import { User } from "../user";
import { Component, OnInit } from "@angular/core";
import { Router } from '@angular/router';
import { TokenStorageService } from "../_services/token-storage.service";
@Component({
  selector: 'app-board-user',
  templateUrl: './board-user.component.html',
  styleUrls: ['./board-user.component.css']
})
export class BoardUserComponent implements OnInit {
  invoices: Observable<Invoice[]>;
  text: String;

  constructor(private invoiceService: InvoiceService,
    private router: Router, private tokenStorageService: TokenStorageService) { }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    // this.invoices = this.invoiceService.getInvoicesList();
    this.text = this.tokenStorageService.getToken();
  }


  // userDetails(id: number){
  //   this.router.navigate(['details', id]);
  // }

  // updateUser(id: number){
  //   this.router.navigate(['update', id]);
  // }
}


