import { Injectable } from '@angular/core';

import {
  HttpRequest,
  HttpHandler,
  HttpInterceptor,
  HttpResponse
} from '@angular/common/http';
import { finalize } from 'rxjs/operators';
import { LoadingService } from '../Loading/loading.service';
import { of } from 'rxjs';

@Injectable()
export class LoadingInterceptor implements HttpInterceptor {
  loadingCutoff: number = 80; //determines what is the cut off before loading kicks in
  loadingTime : number = 500; //determines how long the loader will last on screen.
  timer: NodeJS.Timeout;
  constructor(private loadingService: LoadingService) { }

  intercept(request: HttpRequest<any>, next: HttpHandler) {
    if(this.timer){
        clearTimeout(this.timer);
    }

    //cut off limit before loading kicks in.
    this.timer = setTimeout(()=>this.loadingService.setLoading(true), this.loadingCutoff);

    return next.handle(request).pipe(
      finalize(
          ()=> {
                //how long loader will last for
                setTimeout(()=>{
                    this.loadingService.setLoading(false);
                },this.loadingTime);

                if(this.timer) {
                  clearTimeout(this.timer);
                }

      })
    );
  }
}
