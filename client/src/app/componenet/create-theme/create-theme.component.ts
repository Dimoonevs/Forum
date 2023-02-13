import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { ThemeService } from 'src/app/shared/services/theme.service';

@Component({
  selector: 'app-create-theme',
  templateUrl: './create-theme.component.html',
  styleUrls: ['./create-theme.component.css']
})
export class CreateThemeComponent implements OnInit {

  formTheme: FormGroup | any;
  aSub!: Subscription;

  constructor(private fb: FormBuilder, private themeService: ThemeService, private router: Router) { this._createForm();}

  ngOnInit(): void {
  }

  private _createForm(){
    this.formTheme = this.fb.group({
      name: ['',[
        Validators.required,
        Validators.maxLength(200)
      ]],
      text: ['',[
        Validators.required,
        Validators.maxLength(1000)
      ]]
  });
}

  get _name() {return this.formTheme.get('name'); }
  get _text() {return this.formTheme.get('text'); }


  onSubmit(){
    const themeReq = {
        name: this.formTheme.value.name,
        text: this.formTheme.value.text
    }
    this.themeService.createTheme(themeReq).subscribe(
    () => this.router.navigate(["/overview"],{
      queryParams:{
        themeCreated: true
      }
    }), error => console.log(error))
  }

}
