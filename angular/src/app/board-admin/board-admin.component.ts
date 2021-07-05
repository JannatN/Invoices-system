import { Component, ViewChild, OnInit } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { InvoiceService } from "../_services/invoices.service";
import { Invoice } from '../models/invoice';
import { Item } from '../models/item';
import { User } from '../models/user';
import { Router } from '@angular/router';
import { Observable } from "rxjs";
import { TokenStorageService } from '../_services/token-storage.service';
import { ItemService } from "../_services/items.service";


@Component({
  selector: 'app-board-admin',
  templateUrl: './board-admin.component.html',
  styleUrls: ['./board-admin.component.css']
})
export class BoardAdminComponent implements OnInit {
  invoices: Observable<Invoice[]>;
  items: Observable<Item[]>;
  user: User;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  public displayedColumns = ['id', 'dateCreated', 'dueDate', 'userID', 'company', 'type', 'details', 'update', 'delete'];


  public dataSource = new MatTableDataSource<Invoice>();
  constructor(private invoiceService: InvoiceService, private router: Router, private token: TokenStorageService) { }

  ngOnInit() {
    this.getList();
    this.user = this.token.getUser();

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

  public redirectToDetails = (id: number) => {
    this.router.navigate(['detailsInvoice', id]);

  }
  public redirectToUpdate = (id: number) => {
    this.router.navigate(['updateInvoice', id]);

  }
  public redirectToDelete = (id: number) => {
    this.invoiceService.deleteInvoice(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
  }

  createInvoice() {
    this.router.navigate(['addInvoice']);
  }

  reloadData() {
    this.invoices = this.invoiceService.getInvoicesList();
  }



  
}
