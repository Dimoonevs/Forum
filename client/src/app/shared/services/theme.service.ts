import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CommentsReq, SearchTeme, ThemeReq } from '../api/Request';
import { ThemeInfoResp, ThemeResp } from '../api/Response';

@Injectable({
  providedIn: 'root'
})

export class ThemeService {
 

  constructor(private http: HttpClient) { }
  getAllThemeUrl = "http://localhost:8080/api/v1/theme/get_all_theme"
  createThemeUrl = "http://localhost:8080/api/v1/theme/create_theme"
  getAllComentsByThemeIdUrl = "http://localhost:8080/api/v1/theme/get_comments_by_themeId"
  createCommentsUrl = "http://localhost:8080/api/v1/hello/add_comments"
  searchThemeUrl = "http://localhost:8080/api/v1/theme/search_theme"

 



  getAllTheme():Observable<ThemeResp>{
    return this.http.get<ThemeResp>(this.getAllThemeUrl);
  }
  createTheme(req: ThemeReq){
    return this.http.post(this.createThemeUrl,req);
  }
  getAllComentsByThemeId(id: any):Observable<ThemeInfoResp>{
    const headers = new HttpHeaders({
      "id": id
    })

    return this.http.get<ThemeInfoResp>(this.getAllComentsByThemeIdUrl, {headers: headers} )
  }
  searchThemeByName(name: string):Observable<ThemeResp>{
    
   return this.http.post<ThemeResp>(this.searchThemeUrl,{"name":name})
  }
 

}
