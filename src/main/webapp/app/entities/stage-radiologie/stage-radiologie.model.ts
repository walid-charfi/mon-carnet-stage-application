import * as dayjs from 'dayjs';
import { IUser } from 'app/entities/user/user.model';
import { AnneeEtude } from 'app/entities/enumerations/annee-etude.model';
import { Hopital } from 'app/entities/enumerations/hopital.model';
import { EvaluationEtudiant } from 'app/entities/enumerations/evaluation-etudiant.model';
import { NoteEncadrantReferent } from 'app/entities/enumerations/note-encadrant-referent.model';

export interface IStageRadiologie {
  id?: number;
  anneeEtude?: AnneeEtude;
  dateDebut?: dayjs.Dayjs | null;
  dateFin?: dayjs.Dayjs | null;
  hopital?: Hopital;
  chefService?: string | null;
  semestre?: string | null;
  groupe?: string | null;
  evaluationObjectif1Etudiant?: EvaluationEtudiant | null;
  noteObjectif1EncadrantReferent?: NoteEncadrantReferent | null;
  user?: IUser | null;
}

export class StageRadiologie implements IStageRadiologie {
  constructor(
    public id?: number,
    public anneeEtude?: AnneeEtude,
    public dateDebut?: dayjs.Dayjs | null,
    public dateFin?: dayjs.Dayjs | null,
    public hopital?: Hopital,
    public chefService?: string | null,
    public semestre?: string | null,
    public groupe?: string | null,
    public evaluationObjectif1Etudiant?: EvaluationEtudiant | null,
    public noteObjectif1EncadrantReferent?: NoteEncadrantReferent | null,
    public user?: IUser | null
  ) {}
}

export function getStageRadiologieIdentifier(stageRadiologie: IStageRadiologie): number | undefined {
  return stageRadiologie.id;
}
