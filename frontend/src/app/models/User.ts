import { Task } from './Task';

export interface User {
  id: string;
  firstName: string;
  lastName: string;
  dateOfBirth: Date;
  creationDate: Date;
  organizationUnit: string;
  team: string;
  experienceLevel: number;
  isActive: boolean;
  modificationHistory: string[];
  tasks: Task[];
}

export interface User2 {
  firstName: string;
  lastName: string;
  dateOfBirth: string;
  creationDate: string;
  organizationUnit: string;
  team: string;
  experienceLevel: number;
}
