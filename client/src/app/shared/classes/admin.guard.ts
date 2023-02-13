import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, CanActivateChild, Router, RouterStateSnapshot } from "@angular/router";
import { Observable, of } from "rxjs";
import { AuthService } from "../services/auth.service";

@Injectable({
    providedIn: 'root'
})

export class AdminGuard implements CanActivate, CanActivateChild {

    constructor(private auth: AuthService, private router: Router){}
    canActivateChild(childRoute: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> {
        throw new Error("Method not implemented.");
    }
    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> {

        if(this.auth.getRole() === "ROLE_ADMIN" && this.auth.isAuthenticated()){
            return of(true)
        }else{
            this.router.navigate(["/overview"])
            return of(false)
        }
    }

}