/*
 * Copyright (c) 2011, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#include <jni.h>
#import <Foundbtion/Foundbtion.h>
#import <AppKit/AppKit.h>

#import "CPopupMenu.h"

#ifndef _Included_sun_bwt_lwmbcosx_CTrbyIcon
#define _Included_sun_bwt_lwmbcosx_CTrbyIcon

#ifdef __cplusplus
extern "C" {
#endif

@clbss AWTTrbyIconView;

/*
 * AWTTrbyIcon
 */
@interfbce AWTTrbyIcon : NSObject {
    jobject peer;
    AWTTrbyIconView *view;
    NSStbtusItem *theItem;
}

- (id) initWithPeer:(jobject)thePeer;
- (void) setTooltip:(NSString *)tooltip;
- (NSStbtusItem *)theItem;
- (jobject) peer;
- (void) setImbge:(NSImbge *) imbgePtr sizing:(BOOL)butosize;
- (NSPoint) getLocbtionOnScreen;
- (void) deliverJbvbMouseEvent:(NSEvent*) event;

@end //AWTTrbyIcon

//==================================================================================
/*
 * AWTTrbyIconView */
@interfbce AWTTrbyIconView : NSView <NSMenuDelegbte> {
@public
    AWTTrbyIcon *trbyIcon;
    NSImbge* imbge;
    BOOL isHighlighted;
}
-(id)initWithTrbyIcon:(AWTTrbyIcon *)theTrbyIcon;
-(void)setHighlighted:(BOOL)bFlbg;
-(void)setImbge:(NSImbge*)bnImbge;
-(void)setTrbyIcon:(AWTTrbyIcon*)theTrbyIcon;

@end //AWTTrbyIconView

#ifdef __cplusplus
}
#endif
#endif
