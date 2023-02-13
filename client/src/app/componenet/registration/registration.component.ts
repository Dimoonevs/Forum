import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { AuthResponse } from 'src/app/shared/api/Response';
import { MaterialService } from 'src/app/shared/classes/material.service';
import { AuthService } from 'src/app/shared/services/auth.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit, OnDestroy {
  form!: FormGroup | any;
  aSub!: Subscription;

  constructor(private fb: FormBuilder, private auth: AuthService, private router: Router) { this._createForm() }
  ngOnDestroy(): void {
    if (this.aSub){
      this.aSub.unsubscribe();
    }
  }

  ngOnInit(): void {
  }

  private _createForm(){
    this.form = this.fb.group({
      username: ['', [
        Validators.required,
        Validators.minLength(4)
      ]],
      password: ['', [
        Validators.required,
        Validators.minLength(8)
      ]]
    })
  }

  get  _username() {return this.form.get('username'); }
  get  _password() {return this.form.get('password'); }
  onSubmit(){
    const user = {
      username: this.form.value.username,
      password: this.form.value.password
    }
    this.form.disable()
    this.aSub =  this.auth.registratio(user).subscribe(
      (response: AuthResponse) => {
      this.router.navigate(['/login'], {
        queryParams: {
          registered: true
        }
      })
    },
      error => {
        MaterialService.toast(error.error.error.message)
        console.warn(error)
        this.form.enable()
    }
    );
  }

}
