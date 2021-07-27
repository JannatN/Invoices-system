import { Component, OnInit } from '@angular/core';
// import { UserService } from '../_services/user.service';
import { LoginComponent } from '../login/login.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
  
})
export class HomeComponent implements OnInit { 
  content: string;
  constructor() { }
  ngOnInit() {
  
  }
}
