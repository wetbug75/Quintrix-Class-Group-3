import { Pipe, PipeTransform } from '@angular/core';

@Pipe({name: "boldKeyWord"})

export class BoldKeyWordPipe implements PipeTransform{
      begin : string;
     
      transform(value: string, regex): any {
       
        if(regex == ""){
            return value;
        }else{
            this.begin = regex+ " ";
            return value.replace(this.begin, '<b>' + this.begin + '</b>')
            
        }
      }
    
      
    
}