import { Component, OnInit } from '@angular/core';
import { timeout } from 'rxjs';
import { MessageResp } from 'src/app/shared/api/Response';
import { MaterialService } from 'src/app/shared/classes/material.service';
import { AdminService } from 'src/app/shared/services/admin.service';

@Component({
  selector: 'app-admin-message',
  templateUrl: './admin-message.component.html',
  styleUrls: ['./admin-message.component.css']
})
export class AdminMessageComponent implements OnInit {

  response!: MessageResp;

  

  constructor(private admin: AdminService) { }

  ngOnInit(): void {
    this.admin.getAllMesssage().subscribe((response)=>{this.response = response})
  }

  isNew(status:string, processed: boolean):boolean{
    return status == "NEW" && processed
  }

  processedMessage(id:any){
    this.admin.processedMessage(id).subscribe(()=>{
      MaterialService.toast("Обработано") 
      timeout(1)
      window.location.reload() 
    })
  }

}
