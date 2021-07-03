import { Component, ViewChild, OnInit } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { InvoiceService } from "../_services/invoices.service";
import { Invoice } from '../models/invoice';
import { Router } from '@angular/router';
import { Observable } from "rxjs";


@Component({
  selector: 'app-board-auditor',
  templateUrl: './board-auditor.component.html',
  styleUrls: ['./board-auditor.component.css']
})
export class BoardAuditorComponent implements OnInit {
  invoices: Observable<Invoice[]>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  public displayedColumns = ['id', 'dateCreated', 'dueDate', 'userID', 'company', 'type'];
  //dataSource: MatTableDataSource<any>;
  // dataSource = new MatTableDataSource();

  public dataSource = new MatTableDataSource<Invoice>();
  constructor(private invoiceService: InvoiceService, private router: Router) { }

  ngOnInit() {
    this.getList();
  }

  public getList = () => {
    this.invoiceService.getInvoicesList()
      .subscribe(res => {
        this.dataSource.data = res as Invoice[];
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


  // constructor(private userService: UserService) { }  
  // ngOnInit() {
  //   //return this._dataService.getData().subscribe(res => this.dataSource.data = res["0"]["data"]);
  //   // return this.userService.getUserList().subscribe(res => this.dataSource.data = res);
  //   this.dataSource = new MatTableDataSource(this.userService.getUserList);
  // }

  // ngAfterViewInit() {
  //   this.dataSource.paginator = this.paginator;
  //   this.dataSource.sort = this.sort;
  // }

  // applyFilter(filterValue: string) {
  //   filterValue = filterValue.trim(); // Remove whitespace
  //   filterValue = filterValue.toLowerCase(); // Datasource defaults to lowercase matches
  //   this.dataSource.filter = filterValue;
}
