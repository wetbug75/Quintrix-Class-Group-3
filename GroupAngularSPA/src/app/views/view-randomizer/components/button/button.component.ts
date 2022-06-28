import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';


@Component({
  selector: 'app-button',
  templateUrl: './button.component.html',
  styleUrls: ['./button.component.css']
})
export class ButtonComponent implements OnInit {
  @Input() text: string;
  @Output() btnClick = new EventEmitter();

  constructor() { }

  ngOnInit(): void {
    this.keyPress();
  }

  onClick(){
    this.btnClick.emit();
  }

  keyPress(){
    document.addEventListener('keydown', (e)=>{ 
      console.log(e.key);
      switch(e.key){
        case 'Enter':
        case 'ArrowRight':
        case 'ArrowLeft':
          this.onClick();
      }
    })
  }
}
