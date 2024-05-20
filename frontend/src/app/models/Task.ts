export interface Task {
  id: string;
  title: string;
  numberOfSubtasks: number;
  priority: number;
  creationDate: Date;
  allConditionsMustBeSatisfied: boolean;
  organizationUnit: string;
  team: string;
  minimumExperienceLevel: number;
  maximumAgeInYears: number;
  isActive: boolean;
  modificationHistory: string[];
}
