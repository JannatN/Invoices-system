import { Component, OnInit } from '@angular/core';
// import { UserService } from '../_services/user.service';
import { LoginComponent } from '../login/login.component';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';
import { InvoiceService } from '../_services/invoices.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
  
})
export class HomeComponent implements OnInit { 
  form: FormGroup;
  id: any;
  isAddMode: boolean;
  loading = false;
  submitted = false;

  constructor(
      private formBuilder: FormBuilder,
      private route: ActivatedRoute,
      private router: Router,
      private userService: InvoiceService,
      // private alertService: AlertService
  ) {}

  ngOnInit() {
      this.id = this.route.snapshot.params['id'];
      this.isAddMode = !this.id;
      
      // password not required in edit mode
      const passwordValidators = [Validators.minLength(6)];
      if (this.isAddMode) {
          passwordValidators.push(Validators.required);
      }

      this.form = this.formBuilder.group({
          title: ['', Validators.required],
          firstName: ['', Validators.required],
          lastName: ['', Validators.required],
          email: ['', [Validators.required, Validators.email]],
          role: ['', Validators.required],
          password: ['', [Validators.minLength(6), this.isAddMode ? Validators.required : Validators.nullValidator]],
          confirmPassword: ['', this.isAddMode ? Validators.required : Validators.nullValidator]
      }, {
          // validator: MustMatch('password', 'confirmPassword')
      });

      if (!this.isAddMode) {
          this.userService.getInvoice(this.id)
              .pipe(first())
              .subscribe(x => this.form.patchValue(x));
      }
  }

  // convenience getter for easy access to form fields
  get f() { return this.form.controls; }

  onSubmit() {
      this.submitted = true;

      // reset alerts on submit
      // this.alertService.clear();

      // stop here if form is invalid
      if (this.form.invalid) {
          return;
      }

      this.loading = true;
      if (this.isAddMode) {
          this.createUser();
      } else {
          this.updateUser();
      }
  }

  private createUser() {
      this.userService.createInvoice(this.form.value)
          .pipe(first())
          .subscribe({
              next: () => {
                  // this.alertService.success('User added', { keepAfterRouteChange: true });
                  // this.router.navigate(['../'], { relativeTo: this.route });
              },
              error: error => {
                  // this.alertService.error(error);
                  this.loading = false;
              }
          });
  }

  private updateUser() {
      this.userService.updateInvoice(this.id, this.form.value)
          .pipe(first())
          .subscribe({
              next: () => {
                  // this.alertService.success('User updated', { keepAfterRouteChange: true });
                  this.router.navigate(['../../'], { relativeTo: this.route });
              },
              error: error => {
                  // this.alertService.error(error);
                  this.loading = false;
              }
          });
  }
}
