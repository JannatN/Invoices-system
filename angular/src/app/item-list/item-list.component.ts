import { Observable } from "rxjs";
import { ItemService } from "../_services/items.service";
import { Item } from "../models/item";
import { Component, OnInit } from "@angular/core";
import { Router } from '@angular/router';

@Component({
  selector: 'app-item-list',
  templateUrl: './item-list.component.html',
  styleUrls: ['./item-list.component.css']
})
export class ItemListComponent implements OnInit {
  items: Observable<Item[]>;

  constructor(private itemService: ItemService,
    private router: Router) { }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.items = this.itemService.getItemsList();
  }


  itemDetails(id: number) {
    this.router.navigate(['detailsItem', id]);
  }

  updateItem(id: number) {
    this.router.navigate(['updateItem', id]);
  }

  createItem() {
    this.router.navigate(['addItem']);
  }
  
  deleteItem(id: number) {
    this.itemService.deleteItem(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
  }
  
  list() {
    this.router.navigate(['admin']);
  }
}
