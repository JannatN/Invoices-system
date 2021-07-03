import { Component, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';

export interface IHeaders {
  id: string | number;
  name: string;
  age: number | string;
  gender: string;
  country: string;
}

@Component({
  selector: 'app-materialtable',
  templateUrl: './materialtable.component.html',
  styleUrls: ['./materialtable.component.css']
})
export class MaterialtableComponent {
  displayedColumns = ['id', 'name', 'age', 'gender', 'country'];
  dataSource: MatTableDataSource<IHeaders>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor() {
    const users: IHeaders[] = [];
    for (let i = 1; i <= 100; i++) { users.push(createNewUser(i)); }

    this.dataSource = new MatTableDataSource(users);
  }

  /**
   * Set the paginator and sort after the view init since this component will
   * be able to query its view for the initialized paginator and sort.
   */
  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  applyFilter(filterValue: string) {
    filterValue = filterValue.trim(); 
    filterValue = filterValue.toLowerCase(); 
    this.dataSource.filter = filterValue;
  }
}

function createNewUser(id: number): IHeaders {
  const coutries = ['USA', 'UK', 'India', 'Singapore', 'London'];
  const country = coutries[Math.floor(Math.random() * coutries.length)];

  const genders = ['Male', 'Female'];
  const gender = genders[Math.floor(Math.random() * genders.length)];
  return {
    id: id,
    name: 'Nami ' + id,
    age: 21 + Math.round(Math.random() * 10),
    gender,
    country
  };
}
