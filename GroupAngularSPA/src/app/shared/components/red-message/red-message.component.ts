import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-red-message',
  templateUrl: './red-message.component.html',
  styleUrls: ['./red-message.component.css']
})
export class RedMessageComponent implements OnInit {
  @Input() redmessage: string = '';
  constructor() { }

  ngOnInit(): void { }
}
