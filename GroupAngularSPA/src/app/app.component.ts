import { Component, HostBinding, Input } from '@angular/core';


@Component({
	selector: 'body',//'app-root',   changed from 'app-root' to 'body' so I can add and remove classes from 'body'
	templateUrl: './app.component.html',
	styleUrls: ['./app.component.css']
  })
export class AppComponent {
  title = 'Joke Telling';

  @HostBinding('class.css-dark')
  public cssIsDark: boolean = false;

  @HostBinding('class.css-artsy')
  public cssIsArtsy: boolean = false;

  @HostBinding('class.css-blue-purple')
  public cssIsBP: boolean = false;

  setCssDefault() {
	this.cssIsDark = false;
	this.cssIsArtsy = false;
	this.cssIsBP = false;
  }
  setCssDark() {
	this.cssIsDark = true;
	this.cssIsArtsy = false;
	this.cssIsBP = false;
  }
  setCssArtsy() {
	this.cssIsDark = false;
	this.cssIsArtsy = true;
	this.cssIsBP = false;
  }
  setCssBP() {
	this.cssIsDark = false;
	this.cssIsArtsy = false;
	this.cssIsBP = true;
  }
}
