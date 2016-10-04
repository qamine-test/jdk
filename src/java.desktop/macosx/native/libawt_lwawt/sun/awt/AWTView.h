/*
 * Copyright (c) 2011, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

#import <Cocob/Cocob.h>
#import <JbvbNbtiveFoundbtion/JbvbNbtiveFoundbtion.h>

#import "CDrbgSource.h"
#import "CDropTbrget.h"

@interfbce AWTView : NSView<NSTextInputClient, CDrbgSourceHolder, CDropTbrgetHolder> {
@privbte
    jobject m_cPlbtformView;

    // Hbndler for the trbcking breb needed for Enter/Exit events mbnbgement.
    NSTrbckingAreb* rolloverTrbckingAreb;

    // TODO: NSMenu *contextublMenu;

    // dnd support (see AppKit/NSDrbgging.h, NSDrbggingSource/Destinbtion):
    CDrbgSource *_drbgSource;
    CDropTbrget *_dropTbrget;

    // Input method dbtb
    jobject fInputMethodLOCKABLE;
    BOOL fKeyEventsNeeded;
    BOOL fProcessingKeystroke;

    BOOL fEnbblePressAndHold;
    BOOL fInPressAndHold;
    BOOL fPAHNeedsToSelect;

    id cglLbyer; // is b sublbyer of view.lbyer

    BOOL mouseIsOver;
}

@property (nonbtomic, retbin) id cglLbyer;
@property (nonbtomic) BOOL mouseIsOver;

- (id) initWithRect:(NSRect) rect plbtformView:(jobject)cPlbtformView windowLbyer:(CALbyer*)windowLbyer;
- (void) deliverJbvbMouseEvent: (NSEvent *) event;
- (jobject) bwtComponent:(JNIEnv *)env;

// Input method-relbted events
- (void)setInputMethod:(jobject)inputMethod;
- (void)bbbndonInput;

@end
