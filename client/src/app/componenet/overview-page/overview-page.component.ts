import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { timeout } from 'rxjs';
import { SearchTeme } from 'src/app/shared/api/Request';
import { ThemeArr, ThemeResp } from 'src/app/shared/api/Response';
import { Role } from 'src/app/shared/api/user';
import { MaterialService } from 'src/app/shared/classes/material.service';
import { AuthService } from 'src/app/shared/services/auth.service';
import { ThemeService } from 'src/app/shared/services/theme.service';

@Component({
  selector: 'app-overview-page',
  templateUrl: './overview-page.component.html',
  styleUrls: ['./overview-page.component.css']
})
export class OverviewPageComponent implements OnInit {
  themeResponse: ThemeResp | any;

  theme!: ThemeArr[]
  req!: SearchTeme
  
  
  

  constructor(private themeService: ThemeService, private router: Router, private rout: ActivatedRoute, private auth: AuthService) { }

  ngOnInit(): void {
    this.themeService.getAllTheme().subscribe(
      (response: ThemeResp) => {this.theme = response.data; }
    )
      this.rout.queryParams.subscribe((params: Params) => {
          if(params['themeCreated']){
            MaterialService.toast("Тема создана")
            
          }
      })
  }

  createTheme(){
    this.router.navigate(['create_theme'])
  }

  themeInfo(id: any){
    this.router.navigate(['theme', id])
  }
  values = '';

  onKey(event: any){ 
    this.values = event.target.value;
    if(this.values != ""){
      this.themeService.searchThemeByName(this.values).subscribe((resp: ThemeResp) => {this.theme = resp.data})
    }else{
      this.ngOnInit()
    }
  }


}

