import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { firstValueFrom } from 'rxjs';
import { OutfitService } from '../services/outfit_item.service'; // Adjust path

interface Outfit {
  image: string;
  description: string;
  style: string;
  available: bigint;
}

@Component({
  selector: 'app-view-items',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './view-items.component.html',
  styleUrls: ['./view-items.component.css'],
})
export class ViewItemsComponent implements OnInit {
  outfits: Outfit[] = [];
  loading = false;
  error = '';

  constructor(private outfitService: OutfitService, private router: Router) {}

  async ngOnInit() {
    await this.loadOutfits();
  }

  async loadOutfits() {
    this.loading = true;
    this.error = '';
    try {
      const data = await firstValueFrom(this.outfitService.getAllOutfits());
      this.outfits = data as Outfit[];
      console.log("Outfits received");
    } catch (err) {
      console.error('Error loading outfits', err);
      this.error = 'Failed to load outfits.';
    } finally {
      this.loading = false;
    }
  }

  navigateToHome() {
    this.router.navigate(['/home']);
  }
}
