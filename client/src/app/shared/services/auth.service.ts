import { Injectable } from '@angular/core';
import { User } from '../api/user';
import { HttpClient } from '@angular/common/http'
import { Observable, tap } from 'rxjs';
import { AuthResponse } from '../api/Response';
import { MessageReq } from '../api/Request';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private token : string | any = null;
  private userRole : string | any = null;

  constructor(private http: HttpClient) { }
  loginUrl = "http://localhost:8080/api/v1/auth/authenticate";
  registrationUrl = "http://localhost:8080/api/v1/auth/register"
  messageSendUtl = "http://localhost:8080/api/v1/message/add"

  registratio(user: User): Observable<AuthResponse>{
    return this.http.post<AuthResponse>(this.registrationUrl, user);
  }
  login(user: User): Observable<AuthResponse>{
    return this.http.post<AuthResponse>(this.loginUrl, user)
    .pipe(
      tap(
        (AuthResponse: AuthResponse) => {
          localStorage.setItem('auth-token', AuthResponse.data.token)
          localStorage.setItem('auth-role', AuthResponse.data.role)
          this.setToken(AuthResponse.data.token)
          this.setRole(AuthResponse.data.role);
        }
      )
    );
  }
  setToken(token: string | any){
    this.token =  token;
  }
  getToken(): string | any{
    return this.token;
  }
  isAuthenticated(): boolean{
    return !!this.token;
  }
  isAdmin():boolean{
    return this.getRole() === "ROLE_ADMIN"
  }
  
  logout(){
    this.setToken(null);
    localStorage.clear();
  }
  setRole(role:string | any){
    this.userRole = role;
  }

  getRole(): string | any{
    return this.userRole;
  }

  sengMessage(message: MessageReq){
    return this.http.post(this.messageSendUtl, message)
  }
}
