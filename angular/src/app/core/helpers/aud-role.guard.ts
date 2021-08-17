import { Injectable } from '@angular/core';
import {
    ActivatedRouteSnapshot,
    CanActivate,
    Router,
    RouterStateSnapshot,
    UrlTree,
} from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../services/auth.service';
import { TokenStorageService } from '../services/token-storage.service';

@Injectable({
    providedIn: 'root',
})
export class AudRoleGuard implements CanActivate {
    constructor(private router: Router, private tokenStorageService: TokenStorageService) { }
    private roles: string[];

    canActivate(
        route: ActivatedRouteSnapshot,
        state: RouterStateSnapshot
    ):
        | Observable<boolean | UrlTree>
        | Promise<boolean | UrlTree>
        | boolean
        | UrlTree {
        const user = this.tokenStorageService.getUser();
        const isAuthorizedAdmin = user.roles.includes('ROLE_ADMIN');
        const isAuthorizedAud = user.roles.includes('ROLE_AUDITOR');

        if (!isAuthorizedAud ) {
            this.tokenStorageService.signOut();
            this.router.navigate(['/login'])   
            window.alert('you are not authorized');
        }
      

        return isAuthorizedAud;
    }
}