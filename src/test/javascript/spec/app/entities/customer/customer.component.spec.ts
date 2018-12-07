/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActivatedRoute, Data } from '@angular/router';

import { StripeCustomersListTestModule } from '../../../test.module';
import { CustomerComponent } from 'app/entities/customer/customer.component';
import { CustomerService } from 'app/entities/customer/customer.service';
import { Customer } from 'app/shared/model/customer.model';

describe('Component Tests', () => {
    describe('Customer Management Component', () => {
        let comp: CustomerComponent;
        let fixture: ComponentFixture<CustomerComponent>;
        let service: CustomerService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [StripeCustomersListTestModule],
                declarations: [CustomerComponent],
                providers: [
                    {
                        provide: ActivatedRoute,
                        useValue: {
                            data: {
                                subscribe: (fn: (value: Data) => void) =>
                                    fn({
                                        pagingParams: {
                                            predicate: 'id',
                                            reverse: false,
                                            page: 0
                                        }
                                    })
                            }
                        }
                    }
                ]
            })
                .overrideTemplate(CustomerComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CustomerComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CustomerService);
        });

        it('should not load a page is the page is the same as the previous page', () => {
            spyOn(service, 'query').and.callThrough();

            // WHEN
            comp.loadPage(0);

            // THEN
            expect(service.query).toHaveBeenCalledTimes(0);
        });

        it('should calculate the sort attribute for an id', () => {
            // WHEN
            const result = comp.sort();

            // THEN
            expect(result).toEqual(['id,desc']);
        });

        it('should calculate the sort attribute for a non-id attribute', () => {
            // GIVEN
            comp.predicate = 'name';

            // WHEN
            const result = comp.sort();

            // THEN
            expect(result).toEqual(['name,desc', 'id']);
        });
    });
});
