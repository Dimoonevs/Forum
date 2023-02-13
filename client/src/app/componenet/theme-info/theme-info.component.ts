import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { timeout } from 'rxjs';
import { CommentsReq } from 'src/app/shared/api/Request';
import { ThemeInfoResp } from 'src/app/shared/api/Response';
import { CommentsService } from 'src/app/shared/services/comments.service';
import { ThemeService } from 'src/app/shared/services/theme.service';

@Component({
  selector: 'app-theme-info',
  templateUrl: './theme-info.component.html',
  styleUrls: ['./theme-info.component.css']
})
export class ThemeInfoComponent implements OnInit {

  id: string| any;

  thmeInfoResp!: ThemeInfoResp;

  formComment: FormGroup |any
  comment: CommentsReq | any;

  

  constructor(private themeService: ThemeService, private rout: ActivatedRoute, private fb: FormBuilder,private router: Router, private commentService: CommentsService) {
    this._createForm()
   }

   private _createForm(){
    this.formComment = this.fb.group(
      {
        name:['',[
          Validators.required
        ]]
      }
    )
   }

   get _name() {return this.formComment.get('name'); }

  ngOnInit(): void {
    this.id = this.rout.snapshot.params["id"]
    this.themeService.getAllComentsByThemeId(this.id).subscribe(
      (response: ThemeInfoResp) => {
        this.thmeInfoResp = response;
      }
    );
  }

  createComments(){
    this.comment = {
      text: this.formComment.value.name
    }
    this.id =  this.rout.snapshot.params["id"]
    
    this.commentService.createComments(this.comment, this.id).subscribe((data => console.log(data)))
    timeout(.5)
    window.location.reload()
  }
}
