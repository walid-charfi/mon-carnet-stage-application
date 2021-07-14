import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IStageRadiologie, StageRadiologie } from '../stage-radiologie.model';
import { StageRadiologieService } from '../service/stage-radiologie.service';

@Injectable({ providedIn: 'root' })
export class StageRadiologieRoutingResolveService implements Resolve<IStageRadiologie> {
  constructor(protected service: StageRadiologieService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IStageRadiologie> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((stageRadiologie: HttpResponse<StageRadiologie>) => {
          if (stageRadiologie.body) {
            return of(stageRadiologie.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new StageRadiologie());
  }
}
