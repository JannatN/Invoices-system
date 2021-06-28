import { Component, OnInit } from '@angular/core';
import { Observable } from "rxjs";
import { User } from "../models/user";
import { TokenStorageService } from '../_services/token-storage.service';
import { UserService } from "../_services/users.service";
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  currentUser: any;
  id: number;
  user: User;

  constructor(private token: TokenStorageService, private userService: UserService,
    private router: Router, private route: ActivatedRoute) { }

  ngOnInit() {
    this.reloadData();
  }
  reloadData() {
    this.user = this.token.getUser();
    this.id = this.route.snapshot.params['id'];
    this.userService.getUser(this.id);
    console.log(this.user.username)
  }

  updateUser(id: number) {
    this.router.navigate(['update', id]);
  }


}
