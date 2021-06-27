// import { UserDetailsComponent } from '../user-details/user-details.component';
// import { Observable } from "rxjs";
// import { UserService } from "../_services/users.service";
// import { User } from "../user";
// import { Component, OnInit } from "@angular/core";
// import { Router } from '@angular/router';

// @Component({
//   selector: "app-board-admin",
//   templateUrl: "./board-admin.component.html",
//   styleUrls: ["./board-admin.component.css"]
// })
// export class BoardAdminComponent implements OnInit {
//   users: Observable<User[]>;

//   constructor(private userService: UserService,
//     private router: Router) { }

//   ngOnInit() {
//     this.reloadData();
//   }

//   reloadData() {
//     this.users = this.userService.getUserList();
//   }


//   userDetails(id: number) {
//     this.router.navigate(['details', id]);
//   }

//   updateUser(id: number) {
//     this.router.navigate(['update', id]);
//   }
  
//   gotoProfile() {
//     this.router.navigate(['/profile']);
//   }
// }
