import { NgModule } from '@angular/core';

import { StripeCustomersListSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [StripeCustomersListSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [StripeCustomersListSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class StripeCustomersListSharedCommonModule {}
