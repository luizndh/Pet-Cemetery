import { Component, Input } from '@angular/core';
import { ReuniaoAdmin } from '../model/reuniao-admin.model';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-card-reuniao',
  imports: [DatePipe],
  templateUrl: './card-reuniao.component.html'
})
export class CardReuniaoComponent {
    @Input() reuniao!: ReuniaoAdmin;
}
