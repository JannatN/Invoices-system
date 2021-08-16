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
export class AdminRoleGuard implements CanActivate {
    constructor(private router: Router, private tokenStorageService: TokenStorageService, private authService: AuthService) { }
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
        const isAuthorized = user.roles.includes('ROLE_ADMIN');
        if (!isAuthorized) {
            this.tokenStorageService.signOut();
            this.router.navigate(['/login']) 
            window.alert('you are not authorized');

        }

        return isAuthorized;
    }

}