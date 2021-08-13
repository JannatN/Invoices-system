import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
// import { BoardAuditorComponent } from './board-auditor/board-auditor.component';
import { ProfileComponent } from './profile/profile.component';
import { authInterceptorProviders } from './core/helpers/auth.interceptor';
import { ReactiveFormsModule } from '@angular/forms';

import { CommonModule } from '@angular/common';
import { CoreModule } from './core/core.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    // RegisterComponent,
    HomeComponent,

  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    CommonModule,
    ReactiveFormsModule,
    CoreModule

  ],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent]
})




export class AppModule { }
