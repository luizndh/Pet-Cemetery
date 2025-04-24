import { Routes } from '@angular/router';
import { PaginaInicialComponent } from './pagina-inicial/pagina-inicial.component';
import { LoginComponent } from './auth/login/login.component';
import { CadastroComponent } from './auth/cadastro/cadastro.component';
import { QuemSomosComponent } from './info-navbar/quem-somos/quem-somos.component';
import { PlanosComponent } from './info-navbar/planos/planos.component';
import { ContatoComponent } from './info-navbar/contato/contato.component';
import { HomeComponent } from './home/home.component';import { PaginaDoacaoComponent } from './doacoes/pagina-doacao/pagina-doacao.component';
import { MapaJazigoComponent } from './compra/mapa-jazigo/mapa-jazigo.component';
import { LembreteVisitaComponent } from './lembrete-visita/lembrete-visita.component';


export const routes: Routes = [
    {
        path: '',
        component: PaginaInicialComponent
    },
    {
        path: 'login',
        component: LoginComponent
    },
    {
        path: 'cadastro',
        component: CadastroComponent
    },
    {
        path: 'quem-somos',
        component: QuemSomosComponent
    },
    {
        path: 'planos',
        component: PlanosComponent
    },
    {
        path: 'contato',
        component: ContatoComponent
    },
    {
        path: 'home',
        component: HomeComponent,
    },
    {
        path: 'home/doacao',
        component: PaginaDoacaoComponent,
    },
    {
        path: 'home/mapa-jazigos',
        component: MapaJazigoComponent
    },
    {
        path: 'home/lembrete-visita',
        component: LembreteVisitaComponent
    },


    {
        path: '**',
        redirectTo: '',
        pathMatch: 'full'
    }

];
