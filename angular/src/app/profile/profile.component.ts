import { Component, OnInit } from '@angular/core';
import { Observable } from "rxjs";
import { User } from "../core/models/user";
import { TokenStorageService } from '../core/services/token-storage.service';
import { UserService } from "../core/services/users.service";
import { Router, ActivatedRoute } from '@angular/router';
import { Invoice } from '../core/models/invoice';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  currentUser: any;
  id: number;
  user: User;
  isActive = true;
  
  constructor(private token: TokenStorageService, private userService: UserService,
    private router: Router, private route: ActivatedRoute) { }
    
    ngOnInit() {
      this.reloadData();
      this.user = new User();

    this.id = this.token.getUser().id;

    this.userService.getUser(this.id)
      .subscribe(data => {
        console.log(data)
        this.user = data;
       
      }, error => console.log(error));
     
    }
    reloadData() {
      this.user = this.token.getUser();

      // this.getUser(); 
      console.log(this.user)
      console.log(this.token.getUser().id )
      // console.log(this.token.getUser())
  }
  
  updateUser(id: number) {
    this.router.navigate(['update', id]);
  }
  
  getUser() {
    this.user = new User();
    
    this.id = this.route.snapshot.params['id'];
    
    this.userService.getUser(this.id)
    .subscribe(data => {
      console.log(data)
      this.user = data;
    }, error => console.log(error));
  }
}
