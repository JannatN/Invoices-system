import { Component, OnInit } from '@angular/core';
import { User } from '../models/user';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../_services/users.service';
import { TokenStorageService } from '../_services/token-storage.service';
import {Location} from '@angular/common';

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.css']
})
export class UpdateUserComponent implements OnInit {
  id: number;
  user: User;

  constructor(private route: ActivatedRoute, private router: Router,
    private userService: UserService, private tokenStorageService: TokenStorageService,  private location: Location) { }

  ngOnInit() {
    this.user = new User();

    this.id = this.route.snapshot.params['id'];

    this.userService.getUser(this.id)
      .subscribe(data => {
        console.log(data)
        this.user = data;
      }, error => console.log(error));
  }

  updateUser() {
    this.userService.updateUser(this.id, this.user)
      .subscribe(data => {
        console.log(data);
        this.user = new User();
        this.back();
      }, error => console.log(error));

  }
  onSubmit() {
    this.updateUser();
  }

  // logout() {
  //   this.tokenStorageService.signOut();
  //   this.router.navigate(['/logout']);

  // }
  gotoProfile() {
    this.router.navigate(['/profile']);
  }

  back() {
    this.location.back();
  }

}
