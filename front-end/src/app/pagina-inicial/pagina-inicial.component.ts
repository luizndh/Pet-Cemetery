import { Component, OnInit } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { LocalStorageService } from '../shared/service/local-storage.service';

@Component({
  selector: 'app-pagina-inicial',
  imports: [RouterLink],
  templateUrl: './pagina-inicial.component.html',
  styleUrl: './pagina-inicial.component.css'
})
export class PaginaInicialComponent implements OnInit {
  constructor(private tokenService: LocalStorageService, private router: Router) { }

  ngOnInit(): void {
    if(this.tokenService.hasToken()) {
      this.router.navigate(['/home']);
    }
  }

}
