import { UserDetailsComponent } from '../user-details/user-details.component';
import { Observable } from "rxjs";
import { UserService } from "../_services/users.service";
// import { userServices } from "../_services/user.service";

import { User } from "../user";
import { Component, OnInit } from "@angular/core";
import { Router } from '@angular/router';

@Component({
  selector: "app-user-list",
  templateUrl: "./user-list.component.html",
  styleUrls: ["./user-list.component.css"]
})
export class UserListComponent implements OnInit {
  users: Observable<User[]>;
  user?: User[];
  currentIndex = -1;
  username = '';
  // users: User[] = [];
  constructor(private userService: UserService,
    private router: Router) { }

  ngOnInit() {
    this.reloadData();
    // this.listusers();
  }

  reloadData() {
    this.users = this.userService.getUserList();
  }


  userDetails(id: number) {
    this.router.navigate(['details', id]);
  }

  updateUser(id: number) {
    this.router.navigate(['update', id]);
  }
  
  gotoProfile() {
    this.router.navigate(['/profile']);
  }

  searchUsername(): void {
    this.users = undefined;
    this.currentIndex = -1;

    this.users=this.userService.findByUsername(this.username)
      // .subscribe(
      //   data => {
      //     console.log(data);
      //   },
      //   error => {
      //     console.log(error);
      //   });
  }


 

//   filters = {
//     keyword: ''
//   }
//   // listnames() {
//   //   this.userService.getUserList().subscribe(
//   //     data => {this.users = this.filterUsers(data),this.users = this.filtername(data)}
     
//   //   )
//   // }
//   listusers() {
//     this.userService.getUserList().subscribe(
//       data => {this.users = this.filterUsers(data)}
     
//     )
//   }
//   i:number;
//   ch=[];
//   filterUsers(users: User[]) {
//     return users.filter((e) => {
//       // this.ch[1]=e.username;
//       // this.ch[2]=e.firstname;
//       // this.ch[3]=e.lastname;
//       // this.ch[4]=e.phoneNumber;
//       // this.ch[5]=e.email;
//       // this.ch[6]=e.address;

//       // console.log("this is "+this.filters.keyword)
//       // for( this.i=0;this.i<this.ch.length;this.i++){
//       //   console.log(this.ch.length);
//         return e.username.toLowerCase().includes(this.filters.keyword.toLowerCase());
//       // }

     
      
//     })
//   }

//   // filtername(users: User[]) {
//   //   return users.filter((e) => {
//   //     return e.address.toLowerCase().includes(this.filters.keyword.toLowerCase());
//   //   })
//   // }
}