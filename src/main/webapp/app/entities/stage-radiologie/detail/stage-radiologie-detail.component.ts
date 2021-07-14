import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IStageRadiologie } from '../stage-radiologie.model';

@Component({
  selector: 'jhi-stage-radiologie-detail',
  templateUrl: './stage-radiologie-detail.component.html',
})
export class StageRadiologieDetailComponent implements OnInit {
  stageRadiologie: IStageRadiologie | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ stageRadiologie }) => {
      this.stageRadiologie = stageRadiologie;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
