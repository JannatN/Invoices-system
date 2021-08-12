import { HttpErrorResponse, HTTP_INTERCEPTORS } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';

import { TokenStorageService } from '../_services/token-storage.service';
import { catchError, retry } from 'rxjs/operators';
import { Observable, of, throwError } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';

const TOKEN_HEADER_KEY = 'Authorization';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private token: TokenStorageService, private router: Router) { }

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    let authReq = req;
    const token = this.token.getToken();
    if (token != null) {
      // for Spring Boot back-end
      authReq = req.clone({ headers: req.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + token) });
    }
    return next.handle(authReq).pipe(catchError(x=> this.handleAuthError(x)))
    //   retry(1),
    //   catchError((error: HttpErrorResponse) => {
    //     let message = '';
    //     if (error.error instanceof ErrorEvent) {
    //       // handle client-side error
    //       message = `Error: ${error.error.message}`;
    //       if (error.status === 401) {
    //         // this.router.navigate(['home']);
    //         this.router.navigateByUrl(`/home`);
    //       }
    //       if (error.status === 404 || error.status == 0) {
    //         this.router.navigateByUrl(`/home`);
    //       }
    //     }
    //     else {
    //       // handle server-side error
    //       message = `Error Status: ${error.status}\nMessage: ${error.message}`;
    //     }
    //     console.log(message);
    //     return throwError(message);
    //   })
    // )
    
  }
    private handleAuthError(err: HttpErrorResponse): Observable<any> {
    //handle your auth error or rethrow
    if (err.status === 401 || err.status === 403) {
      //navigate /delete cookies or whatever
      this.router.navigateByUrl(`/login`);
      // if you've caught / handled the error, you don't want to rethrow it unless you also want downstream consumers to have to handle it as well.
      return of(err.message); // or EMPTY may be appropriate here
    }
    return throwError(err);
  }

}

export const authInterceptorProviders = [
  { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
];

      //if 401 logout
      //if 403
      //if 404
      //lazy loading
      //ngrx
      //gurd 
      //recative form
      //ng if for boards doone 
      //input() binding data between components
      // handle errors in getInvoice ...
      //environment