import { Item } from '../models/item';
import { Component, OnInit, Input } from '@angular/core';
import { ItemService } from "../_services/items.service";
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-item',
  templateUrl: './create-item.component.html',
  styleUrls: ['./create-item.component.css']
})
export class CreateItemComponent implements OnInit {

  item: Item = new Item();
  submitted = false;

  constructor(private itemService: ItemService,
    private router: Router) { }

  ngOnInit() {
  }

  newItem(): void {
    this.submitted = false;
    this.item = new Item();
  }

  save() {
    this.itemService
    .createItem(this.item).subscribe(data => {
      console.log(data)
      this.item = new Item();
      // this.gotoList();
    }, 
    error => console.log(error));
  }

  onSubmit() {
    this.submitted = true;
    this.save();    
  }

  gotoList() {
    this.router.navigate(['/items']);
  }
}
