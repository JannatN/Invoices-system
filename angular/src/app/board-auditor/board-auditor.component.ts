import { Component, OnInit } from '@angular/core';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-board-auditor',
  templateUrl: './board-auditor.component.html',
  styleUrls: ['./board-auditor.component.css']
})
export class BoardAuditorComponent implements OnInit {
  content: any;

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.userService.getAuditorBoard().subscribe(
      data => {
        this.content = data;
      },
      err => {
        this.content = JSON.parse(err.error).message;
      }
    );
  }
}
