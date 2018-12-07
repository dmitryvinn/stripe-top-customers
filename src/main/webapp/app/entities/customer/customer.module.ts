import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { StripeCustomersListSharedModule } from 'app/shared';
import { CustomerComponent, customerRoute, customerPopupRoute } from './';

const ENTITY_STATES = [...customerRoute, ...customerPopupRoute];

@NgModule({
    imports: [StripeCustomersListSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [CustomerComponent],
    entryComponents: [CustomerComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class StripeCustomersListCustomerModule {}
