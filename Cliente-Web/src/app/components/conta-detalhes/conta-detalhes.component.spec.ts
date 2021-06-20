import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContaDetalhesComponent } from './conta-detalhes.component';

describe('PessoaDetailsComponent', () => {
  let component: ContaDetalhesComponent;
  let fixture: ComponentFixture<ContaDetalhesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ContaDetalhesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ContaDetalhesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
