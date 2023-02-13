import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { timeout } from 'rxjs';
import { AdminUser } from 'src/app/shared/api/Response';
import { AdminService } from 'src/app/shared/services/admin.service';
import { AuthService } from 'src/app/shared/services/auth.service';

@Component({
  selector: 'app-admin-user',
  templateUrl: './admin-user.component.html',
  styleUrls: ['./admin-user.component.css']
})
export class AdminUserComponent implements OnInit {

  

  response!: AdminUser

  constructor(private admin: AdminService, private auth: AuthService, private router: Router) { }

  ngOnInit(): void {
    this.admin.getAllUser().subscribe(
      (data: AdminUser) => this.response = data
    )
  }

  isAdmin(role: string):boolean{
    return role === "ROLE_ADMIN"
  }

  info(id: any){
    this.admin.enableToggleUser(id).subscribe()
    timeout(.5)
    window.location.reload()
  }

}
