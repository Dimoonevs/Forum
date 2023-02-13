import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { MaterialService } from 'src/app/shared/classes/material.service';
import { AuthService } from 'src/app/shared/services/auth.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit, OnDestroy {
  form!: FormGroup | any ;
  aSub!: Subscription;

  constructor(private fb: FormBuilder, private auth: AuthService, private router: Router, private rout: ActivatedRoute) { this._createForm(); }
  ngOnDestroy(): void {
    if (this.aSub){
      this.aSub.unsubscribe()
    }
  }

  ngOnInit(): void {

   
    this.rout.queryParams.subscribe((params: Params) => {
      if(params['registered']){
        MaterialService.toast('Теперь вы можете войти в сиситему используя свои данные')
      }else if (params['accessDenite']){
        MaterialService.toast('Для начало автороизуйтесь в системе')
      } else if (params['sassionFailed']){
        MaterialService.toast('Пожайлуста войдите в сиситему заново')
      }else if(params['messageSend']){
        MaterialService.toast('Сообщение отправлино, ждите ответ!')
      }
    }

    )
  
  }
  private _createForm() {
    this.form = this.fb.group({
      username: ['', [
        Validators.required,
        Validators.minLength(4)
      ]],
      password: [
        '',
        [ Validators.required,
          Validators.minLength(8)
        ]
      ]
    });
  }
  get  _username() {return this.form.get('username'); }
  get  _password() {return this.form.get('password'); }
  

  onSubmit() { 
    const user = {
      username: this.form.value.username,
      password: this.form.value.password
    }
    this.form.disable()
    this.aSub =  this.auth.login(user).subscribe(
        () => this.router.navigate(['/overview']),
        error => {
          MaterialService.toast(error.error.error.message)
          this.form.enable()
      }
    );
  }

}
