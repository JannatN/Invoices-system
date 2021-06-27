import { Observable } from "rxjs";
import { InvoiceService } from "../_services/invoices.service";
import { Invoice } from "../invoice";
import { Component, OnInit } from "@angular/core";
import { Router } from '@angular/router';

@Component({
  selector: "app-invoice-list",
  templateUrl: "./invoice-list.component.html",
  styleUrls: ["./invoice-list.component.css"]
})
export class InvoiceListComponent implements OnInit {
  invoices: Observable<Invoice[]>;

  constructor(private invoiceService: InvoiceService,
    private router: Router) { }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.invoices = this.invoiceService.getInvoicesList();
  }


  invoicesDetails(id: number) {
    this.router.navigate(['detailsInvoice', id]);
  }

  updateInvoice(id: number) {
    this.router.navigate(['updateInvoice', id]);
  }

  createInvoice() {
    this.router.navigate(['addInvoice']);
  }
  
  deleteInvoice(id: number) {
    this.invoiceService.deleteInvoice(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
  }
  
  list() {
    this.router.navigate(['/invoices']);
  }
}
