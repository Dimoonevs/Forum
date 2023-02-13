import { Component, OnInit } from '@angular/core';
import { AuthService } from './shared/services/auth.service';

@Component({
  selector: 'app-root',
  template: '<router-outlet></router-outlet>'
})
export class AppComponent implements OnInit{
  constructor(private auth: AuthService){}

  ngOnInit() {
      const potentialToken = localStorage.getItem('auth-token')
      const potentialRole = localStorage.getItem('auth-role')
      if (potentialToken !== null && potentialRole !== null) {
        this.auth.setToken(potentialToken);
        this.auth.setRole(potentialRole)
      }
  }
}