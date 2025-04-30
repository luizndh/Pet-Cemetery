import { HttpEvent, HttpHandlerFn, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import { inject } from '@angular/core';
import { LocalStorageService } from '../service/local-storage.service';

export function authInterceptor(req: HttpRequest<unknown>, next: HttpHandlerFn): Observable<HttpEvent<unknown>>  {

    // injeta o servico do local storage para recuperar o token
    const tokenService = inject(LocalStorageService);

    // Verifica se a URL começa com "api/auth/"
    if (req.url.includes('api/auth/')) {
        return next(req); // Não adiciona o token para essas rotas
    }

    // Recupera o token do Local Storage
    const token = tokenService.getToken();

    // Clona a requisição e adiciona o cabeçalho Authorization, se o token existir
    if (token) {
        const clonedReq = req.clone({
        setHeaders: {
            Authorization: `Bearer ${token}`
        }
        });
        return next(clonedReq);
    }

    // Continua com a requisição original se não houver token
    return next(req);
}
