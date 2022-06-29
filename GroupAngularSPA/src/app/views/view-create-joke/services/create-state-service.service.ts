import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CreateStateServiceService {
  needForm: boolean;
  constructor() { }

  get state() {
    return this.needForm;
  }

  set state(data:boolean) {
    this.needForm= data;
  }
}
