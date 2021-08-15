import { Component, ViewChild, OnInit } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { InvoiceService } from "../_services/invoices.service";
import { Invoice } from '../models/invoice';
import { Router } from '@angular/router';
import { User } from '../models/user';
import { Observable, of } from "rxjs";
import { TokenStorageService } from '../_services/token-storage.service';
import { Item } from '../models/item';
import { InvoiceDataSource } from '../datasource/invoices.datasource';
import { tap } from 'rxjs/operators';




@Component({
  selector: 'app-board-auditor',
  templateUrl: './board-auditor.component.html',
  styleUrls: ['./board-auditor.component.css']
})
export class BoardAuditorComponent implements OnInit {
  invoices: Observable<Invoice[]>;
  items: Observable<Item[]>;
  user: User;

 


  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  public displayedColumns = ['id', 'date_created', 'due_date', 'userid', 'company', 'type', 'details'];
  invoiceDatasource: InvoiceDataSource;


  public dataSource = new MatTableDataSource<Invoice>();
  constructor(private invoiceService: InvoiceService, private router: Router, private token: TokenStorageService) { }

  ngOnInit() {
                    
    this.user = this.token.getUser();
    this.invoiceDatasource = new InvoiceDataSource(this.invoiceService);
    this.invoiceDatasource.loadInvoices();

  }

 


  ngAfterViewInit() {
    this.invoiceDatasource.counter$
      .pipe(
        tap((count) => {
          this.paginator.length = count;
        })
      )
      .subscribe();

    this.paginator.page
      .pipe(
        tap(() => this.loadTodos())
      )
      .subscribe();
  }

  loadTodos() {
    this.invoiceDatasource.loadInvoices(this.paginator.pageIndex, this.paginator.pageSize);
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
 
 




}
