import { Component, Input, OnInit } from '@angular/core';
import { LoadingService } from 'src/app/core/services/Loading/loading.service';

@Component({
  selector: 'app-loading',
  templateUrl: './loading.component.html',
  styleUrls: ['./loading.component.css']
})
export class LoadingComponent implements OnInit {
  @Input() message : String;
  constructor(loadingService: LoadingService) { }

  ngOnInit(): void { }
}
