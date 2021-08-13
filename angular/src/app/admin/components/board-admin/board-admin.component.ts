import { Component, ViewChild, OnInit } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { InvoiceService } from "../../../core/services/invoices.service";
import { Invoice } from '../../../core/models/invoice';
import { User } from '../../../core/models/user';
import { Router } from '@angular/router';
import { Observable, of } from "rxjs";
import { TokenStorageService } from '../../../core/services/token-storage.service';
import { Item } from '../../../core/models/item';
import { InvoiceDataSource } from '../../../datasource/invoices.datasource';
import { catchError, finalize, map, tap } from 'rxjs/operators';
import { UploadFilesService } from '../../../core/services/upload-file.service';


@Component({
  selector: 'app-board-admin',
  templateUrl: './board-admin.component.html',
  styleUrls: ['./board-admin.component.css']
})
export class BoardAdminComponent implements OnInit {
  filters = {
    keyword: ''
  }

  filterValue: string = null;
  showAdminBoard = false;

  invoices: Observable<Invoice[]>;
  items: Observable<Item[]>;
  user: User;
  invoice: Invoice;
  invoicesArray: Invoice[];
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  public displayedColumns = ['id', 'date_created', 'due_date', 'userid', 'company', 'type', 'details', 'update', 'delete', 'create', 'attachFile'];
  invoiceDatasource: InvoiceDataSource;


  public dataSource = new MatTableDataSource<Invoice>();
  constructor(private invoiceService: InvoiceService,
    private router: Router, private token: TokenStorageService,) { }

  ngOnInit() {
    // this.getList();
    // this.getListBackend();
    this.user = this.token.getUser();
    this.invoiceDatasource = new InvoiceDataSource(this.invoiceService);
    this.invoiceDatasource.loadInvoices();


  }

  load(){
    this.invoiceDatasource.loadInvoices();
  }

  search(){
       this.invoiceDatasource.counter$
      .pipe(
        tap((count) => {
          this.paginator.length = count;
        })
      )
      .subscribe();
    if (this.filterValue != null) {
      console.log("ifffff")
      this.paginator.page
        .pipe(
          tap(() => this.loadInvoicesFilter(this.filterValue))
        )
        .subscribe();
    }
    
    // else {
    //   console.log("elseee")

    //   this.paginator.page
    //     .pipe(
    //       tap(() => this.loadInvoices())
    //     )
    //     .subscribe();
    // }
  }
  ngAfterViewInit() {
    this.invoiceDatasource.counter$
      .pipe(
        tap((count) => {
          this.paginator.length = count;
        })
      )
      .subscribe();
      this.search();}

      
  //   if (this.filterValue != null) {
  //     console.log("ifffff")
  //     this.paginator.page
  //       .pipe(
  //         tap(() => this.loadInvoicesFilter())
  //       )
  //       .subscribe();
  //   }
    
  //   else {
  //     console.log("elseee")

  //     this.paginator.page
  //       .pipe(
  //         tap(() => this.loadInvoices())
  //       )
  //       .subscribe();
  //   }
  // }

  loadInvoices() {
    this.invoiceDatasource.loadInvoices(this.paginator.pageIndex, this.paginator.pageSize);
  }

  loadInvoicesFilter(filter) {
    this.invoiceDatasource.loadInvoicesWithFilter(this.paginator.pageIndex, this.paginator.pageSize, filter);
    this.search();
  }

  // findBy(key: string) {
  //   this.invoiceDatasource.loadInvoicesWithFilter(this.paginator.pageIndex, this.paginator.pageSize, key);

  // }
  
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
          // this.reloadData();
        },
        error => console.log(error));
  }
  redirectToAttachFile(id: number) {
    this.router.navigate(['attachFile', id]);

  }
  createInvoice() {
    this.router.navigate(['addInvoice']);
  }
  ListFiles(){
    this.router.navigate(['listFiles']);

  }
  redirectToCreate(id: number) {
    this.router.navigate(['addItem', id]);
  }
  // reloadData() {
  //   this.invoices = this.invoiceService.getInvoicesList();
  // }




}

