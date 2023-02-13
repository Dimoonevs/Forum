import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { MessageReq } from 'src/app/shared/api/Request';
import { MaterialService } from 'src/app/shared/classes/material.service';
import { AdminService } from 'src/app/shared/services/admin.service';
import { AuthService } from 'src/app/shared/services/auth.service';

@Component({
  selector: 'app-message-send',
  templateUrl: './message-send.component.html',
  styleUrls: ['./message-send.component.css']
})
export class MessageSendComponent implements OnInit {
  formComment: FormGroup |any

  messageReq!: MessageReq;

  constructor(private auth: AdminService, private rout: ActivatedRoute, private fb: FormBuilder,private router: Router) { this._createForm()}
  private _createForm(){
    this.formComment = this.fb.group(
      {
        email:['',[
          Validators.required,
          Validators.email
        ]],
        message:['',[
          Validators.required
        ]]
      }
    )
   }

   get _email() {return this.formComment.get('email')}
   get _message(){return this.formComment.get('message')}

  ngOnInit(): void {
  }
  sendMessage(){
    this.messageReq = {
      email : this.formComment.value.email,
      message: this.formComment.value.message
    }
    this.auth.sengMessage(this.messageReq).subscribe(() => this.router.navigate(["/login"],{
      queryParams:{
        messageSend: true
      }
    }),
    (error)=> MaterialService.toast(error.error.error.message))
  }
}
