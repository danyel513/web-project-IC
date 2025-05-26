import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {map, Observable} from 'rxjs';

export interface Outfit {
  id: bigint;
  image: string;
  description: string;
  style: string;
  available: bigint;
}

@Injectable({
  providedIn: 'root'
})
export class OutfitService {

  private apiUrl = 'http://localhost:8080/api/outfits'; // Adjust API URL if needed

  constructor(private http: HttpClient) {}

  getAllOutfits(): Observable<Outfit[]> {
    return this.http.get<{data: Outfit[]}>(this.apiUrl).pipe(
      map(response => response.data)
    );
  }
}
