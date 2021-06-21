import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { IStageRadiologie, StageRadiologie } from '../stage-radiologie.model';
import { StageRadiologieService } from '../service/stage-radiologie.service';
import { IUser } from 'app/entities/user/user.model';
import { UserService } from 'app/entities/user/user.service';

@Component({
  selector: 'jhi-stage-radiologie-update',
  templateUrl: './stage-radiologie-update.component.html',
})
export class StageRadiologieUpdateComponent implements OnInit {
  isSaving = false;

  usersSharedCollection: IUser[] = [];

  editForm = this.fb.group({
    id: [],
    anneeEtude: [null, [Validators.required]],
    dateDebut: [],
    dateFin: [],
    hopital: [null, [Validators.required]],
    chefService: [],
    semestre: [],
    groupe: [],
    objectif1: [],
    objectif1Enseigne: [],
    noteObjectif1: [],
    user: [],
  });

  constructor(
    protected stageRadiologieService: StageRadiologieService,
    protected userService: UserService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ stageRadiologie }) => {
      this.updateForm(stageRadiologie);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const stageRadiologie = this.createFromForm();
    if (stageRadiologie.id !== undefined) {
      this.subscribeToSaveResponse(this.stageRadiologieService.update(stageRadiologie));
    } else {
      this.subscribeToSaveResponse(this.stageRadiologieService.create(stageRadiologie));
    }
  }

  trackUserById(index: number, item: IUser): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IStageRadiologie>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(stageRadiologie: IStageRadiologie): void {
    this.editForm.patchValue({
      id: stageRadiologie.id,
      anneeEtude: stageRadiologie.anneeEtude,
      dateDebut: stageRadiologie.dateDebut,
      dateFin: stageRadiologie.dateFin,
      hopital: stageRadiologie.hopital,
      chefService: stageRadiologie.chefService,
      semestre: stageRadiologie.semestre,
      groupe: stageRadiologie.groupe,
      objectif1: stageRadiologie.objectif1,
      objectif1Enseigne: stageRadiologie.objectif1Enseigne,
      noteObjectif1: stageRadiologie.noteObjectif1,
      user: stageRadiologie.user,
    });

    this.usersSharedCollection = this.userService.addUserToCollectionIfMissing(this.usersSharedCollection, stageRadiologie.user);
  }

  protected loadRelationshipsOptions(): void {
    this.userService
      .query()
      .pipe(map((res: HttpResponse<IUser[]>) => res.body ?? []))
      .pipe(map((users: IUser[]) => this.userService.addUserToCollectionIfMissing(users, this.editForm.get('user')!.value)))
      .subscribe((users: IUser[]) => (this.usersSharedCollection = users));
  }

  protected createFromForm(): IStageRadiologie {
    return {
      ...new StageRadiologie(),
      id: this.editForm.get(['id'])!.value,
      anneeEtude: this.editForm.get(['anneeEtude'])!.value,
      dateDebut: this.editForm.get(['dateDebut'])!.value,
      dateFin: this.editForm.get(['dateFin'])!.value,
      hopital: this.editForm.get(['hopital'])!.value,
      chefService: this.editForm.get(['chefService'])!.value,
      semestre: this.editForm.get(['semestre'])!.value,
      groupe: this.editForm.get(['groupe'])!.value,
      objectif1: this.editForm.get(['objectif1'])!.value,
      objectif1Enseigne: this.editForm.get(['objectif1Enseigne'])!.value,
      noteObjectif1: this.editForm.get(['noteObjectif1'])!.value,
      user: this.editForm.get(['user'])!.value,
    };
  }
}
