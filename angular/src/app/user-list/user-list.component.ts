import { Component, ViewChild, OnInit } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { UserService } from "../_services/users.service";
import { User } from '../models/user';
import { Router } from '@angular/router';
import { Observable } from "rxjs";


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
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  public displayedColumns = ['id', 'username', 'email', 'details', 'update', 'delete'];


  public dataSource = new MatTableDataSource<User>();
  constructor(private userService: UserService, private router: Router) { }

  ngOnInit() {
    this.getList();
  }

  public getList = () => {
    this.userService.getUserList()
      .subscribe(res => {
        this.dataSource.data = res as User[];
      })
  }
  ngAfterViewInit(): void {
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
  }
  public doFilter = (value: string) => {
    // this.dataSource.filter = value.trim().toLocaleLowerCase();
    value = value.trim(); // Remove whitespace
    value = value.toLowerCase(); // Datasource defaults to lowercase matches
    this.dataSource.filter = value;
  }

  public redirectToDetails = (id: number) => {
    this.router.navigate(['details', id]);

  }
  public redirectToUpdate = (id: number) => {
    this.router.navigate(['update', id]);
  }

  gotoProfile() {
    this.router.navigate(['/profile']);
  }

  public redirectToDelete = (id: number) => {
    this.userService.deleteUser(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
  }
  reloadData() {
    this.users = this.userService.getUserList();
  }
  // searchUsername(): void {
  //   this.users = undefined;
  //   this.currentIndex = -1;

  //   this.users = this.userService.findByUsername(this.username)
  //   // .subscribe(
  //   //   data => {
  //   //     console.log(data);
  //   //   },
  //   //   error => {
  //   //     console.log(error);
  //   //   });
  // }




}
