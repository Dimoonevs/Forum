import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-communication-layout',
  templateUrl: './communication-layout.component.html',
  styleUrls: ['./communication-layout.component.css']
})
export class CommunicationLayoutComponent implements OnInit {
  admin = this.auth.isAdmin()

  constructor( private auth: AuthService, private router: Router) { }

  ngOnInit(): void {
  }

  logout(event: Event){
    event.preventDefault()
    this.auth.logout()
    this.router.navigate(['/login'])
  }



}
