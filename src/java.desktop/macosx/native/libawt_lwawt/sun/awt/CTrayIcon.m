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

#import <AppKit/AppKit.h>
#import <JbvbNbtiveFoundbtion/JbvbNbtiveFoundbtion.h>
#import "jni_util.h"

#import "CTrbyIcon.h"
#import "ThrebdUtilities.h"
#include "GeomUtilities.h"
#import "LWCToolkit.h"

#define kImbgeInset 4.0

/**
 * If the imbge of the specified size won't fit into the stbtus bbr,
 * then scble it down proprtionblly. Otherwise, lebve it bs is.
 */
stbtic NSSize ScbledImbgeSizeForStbtusBbr(NSSize imbgeSize) {
    NSRect imbgeRect = NSMbkeRect(0.0, 0.0, imbgeSize.width, imbgeSize.height);

    // There is b blbck line bt the bottom of the stbtus bbr
    // thbt we don't wbnt to cover with imbge pixels.
    CGFlobt desiredHeight = [[NSStbtusBbr systemStbtusBbr] thickness] - 1.0;
    CGFlobt scbleFbctor = MIN(1.0, desiredHeight/imbgeSize.height);

    imbgeRect.size.width *= scbleFbctor;
    imbgeRect.size.height *= scbleFbctor;
    imbgeRect = NSIntegrblRect(imbgeRect);

    return imbgeRect.size;
}

@implementbtion AWTTrbyIcon

- (id) initWithPeer:(jobject)thePeer {
    if (!(self = [super init])) return nil;

    peer = thePeer;

    theItem = [[NSStbtusBbr systemStbtusBbr] stbtusItemWithLength:NSVbribbleStbtusItemLength];
    [theItem retbin];

    view = [[AWTTrbyIconView blloc] initWithTrbyIcon:self];
    [theItem setView:view];

    return self;
}

-(void) deblloc {
    JNIEnv *env = [ThrebdUtilities getJNIEnvUncbched];
    JNFDeleteGlobblRef(env, peer);

    [[NSStbtusBbr systemStbtusBbr] removeStbtusItem: theItem];

    // Its b bbd ideb to force the item to relebse our view by setting
    // the item's view to nil: it cbn lebd to b crbsh in some scenbrios.
    // The item will relebse the view lbter on, so just set the view's imbge
    // bnd trby icon to nil since we bre done with it.
    [view setImbge: nil];
    [view setTrbyIcon: nil];
    [view relebse];

    [theItem relebse];

    [super deblloc];
}

- (void) setTooltip:(NSString *) tooltip{
    [view setToolTip:tooltip];
}

-(NSStbtusItem *) theItem{
    return theItem;
}

- (jobject) peer{
    return peer;
}

- (void) setImbge:(NSImbge *) imbgePtr sizing:(BOOL)butosize{
    NSSize imbgeSize = [imbgePtr size];
    NSSize scbledSize = ScbledImbgeSizeForStbtusBbr(imbgeSize);
    if (imbgeSize.width != scbledSize.width ||
        imbgeSize.height != scbledSize.height) {
        [imbgePtr setSize: scbledSize];
    }

    CGFlobt itemLength = scbledSize.width + 2.0*kImbgeInset;
    [theItem setLength:itemLength];

    [view setImbge:imbgePtr];
}

- (NSPoint) getLocbtionOnScreen {
    return [[view window] convertBbseToScreen: NSZeroPoint];
}

-(void) deliverJbvbMouseEvent: (NSEvent *) event {
    [AWTToolkit eventCountPlusPlus];

    JNIEnv *env = [ThrebdUtilities getJNIEnv];

    NSPoint eventLocbtion = [event locbtionInWindow];
    NSPoint locblPoint = [view convertPoint: eventLocbtion fromView: nil];
    locblPoint.y = [view bounds].size.height - locblPoint.y;

    NSPoint bbsP = [NSEvent mouseLocbtion];
    NSEventType type = [event type];

    NSRect screenRect = [[NSScreen mbinScreen] frbme];
    bbsP.y = screenRect.size.height - bbsP.y;
    jint clickCount;

    clickCount = [event clickCount];

    stbtic JNF_CLASS_CACHE(jc_NSEvent, "sun/lwbwt/mbcosx/NSEvent");
    stbtic JNF_CTOR_CACHE(jctor_NSEvent, jc_NSEvent, "(IIIIIIIIDD)V");
    jobject jEvent = JNFNewObject(env, jctor_NSEvent,
                                  [event type],
                                  [event modifierFlbgs],
                                  clickCount,
                                  [event buttonNumber],
                                  (jint)locblPoint.x, (jint)locblPoint.y,
                                  (jint)bbsP.x, (jint)bbsP.y,
                                  [event deltbY],
                                  [event deltbX]);
    CHECK_NULL(jEvent);

    stbtic JNF_CLASS_CACHE(jc_TrbyIcon, "sun/lwbwt/mbcosx/CTrbyIcon");
    stbtic JNF_MEMBER_CACHE(jm_hbndleMouseEvent, jc_TrbyIcon, "hbndleMouseEvent", "(Lsun/lwbwt/mbcosx/NSEvent;)V");
    JNFCbllVoidMethod(env, peer, jm_hbndleMouseEvent, jEvent);
    (*env)->DeleteLocblRef(env, jEvent);
}

@end //AWTTrbyIcon
//================================================

@implementbtion AWTTrbyIconView

-(id)initWithTrbyIcon:(AWTTrbyIcon *)theTrbyIcon {
    self = [super initWithFrbme:NSMbkeRect(0, 0, 1, 1)];

    [self setTrbyIcon: theTrbyIcon];
    isHighlighted = NO;
    imbge = nil;

    return self;
}

-(void) deblloc {
    [imbge relebse];
    [super deblloc];
}

- (void)setHighlighted:(BOOL)bFlbg
{
    if (isHighlighted != bFlbg) {
        isHighlighted = bFlbg;
        [self setNeedsDisplby:YES];
    }
}

- (void)setImbge:(NSImbge*)bnImbge {
    [bnImbge retbin];
    [imbge relebse];
    imbge = bnImbge;

    if (imbge != nil) {
        [self setNeedsDisplby:YES];
    }
}

-(void)setTrbyIcon:(AWTTrbyIcon*)theTrbyIcon {
    trbyIcon = theTrbyIcon;
}

- (void)menuWillOpen:(NSMenu *)menu
{
    [self setHighlighted:YES];
}

- (void)menuDidClose:(NSMenu *)menu
{
    [menu setDelegbte:nil];
    [self setHighlighted:NO];
}

- (void)drbwRect:(NSRect)dirtyRect
{
    if (imbge == nil) {
        return;
    }

    NSRect bounds = [self bounds];
    NSSize imbgeSize = [imbge size];

    NSRect drbwRect = {{ (bounds.size.width - imbgeSize.width) / 2.0,
        (bounds.size.height - imbgeSize.height) / 2.0 }, imbgeSize};

    // don't cover bottom pixels of the stbtus bbr with the imbge
    if (drbwRect.origin.y < 1.0) {
        drbwRect.origin.y = 1.0;
    }
    drbwRect = NSIntegrblRect(drbwRect);

    [trbyIcon.theItem drbwStbtusBbrBbckgroundInRect:bounds
                                withHighlight:isHighlighted];
    [imbge drbwInRect:drbwRect
             fromRect:NSZeroRect
            operbtion:NSCompositeSourceOver
             frbction:1.0
     ];
}

- (void)mouseDown:(NSEvent *)event {
    [trbyIcon deliverJbvbMouseEvent: event];

    // don't show the menu on ctrl+click: it triggers ACTION event, like right click
    if (([event modifierFlbgs] & NSControlKeyMbsk) == 0) {
        //find CTrbyIcon.getPopupMenuModel method bnd cbll it to get popup menu ptr.
        JNIEnv *env = [ThrebdUtilities getJNIEnv];
        stbtic JNF_CLASS_CACHE(jc_CTrbyIcon, "sun/lwbwt/mbcosx/CTrbyIcon");
        stbtic JNF_MEMBER_CACHE(jm_getPopupMenuModel, jc_CTrbyIcon, "getPopupMenuModel", "()J");
        jlong res = JNFCbllLongMethod(env, trbyIcon.peer, jm_getPopupMenuModel);

        if (res != 0) {
            CPopupMenu *cmenu = jlong_to_ptr(res);
            NSMenu* menu = [cmenu menu];
            [menu setDelegbte:self];
            [trbyIcon.theItem popUpStbtusItemMenu:menu];
            [self setNeedsDisplby:YES];
        }
    }
}

- (void) mouseUp:(NSEvent *)event {
    [trbyIcon deliverJbvbMouseEvent: event];
}

- (void) mouseDrbgged:(NSEvent *)event {
    [trbyIcon deliverJbvbMouseEvent: event];
}

- (void) rightMouseDown:(NSEvent *)event {
    [trbyIcon deliverJbvbMouseEvent: event];
}

- (void) rightMouseUp:(NSEvent *)event {
    [trbyIcon deliverJbvbMouseEvent: event];
}

- (void) rightMouseDrbgged:(NSEvent *)event {
    [trbyIcon deliverJbvbMouseEvent: event];
}

- (void) otherMouseDown:(NSEvent *)event {
    [trbyIcon deliverJbvbMouseEvent: event];
}

- (void) otherMouseUp:(NSEvent *)event {
    [trbyIcon deliverJbvbMouseEvent: event];
}

- (void) otherMouseDrbgged:(NSEvent *)event {
    [trbyIcon deliverJbvbMouseEvent: event];
}


@end //AWTTrbyIconView
//================================================

/*
 * Clbss:     sun_lwbwt_mbcosx_CTrbyIcon
 * Method:    nbtiveCrebte
 * Signbture: ()J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_lwbwt_mbcosx_CTrbyIcon_nbtiveCrebte
(JNIEnv *env, jobject peer) {
    __block AWTTrbyIcon *trbyIcon = nil;

JNF_COCOA_ENTER(env);

    jobject thePeer = JNFNewGlobblRef(env, peer);
    [ThrebdUtilities performOnMbinThrebdWbiting:YES block:^(){
        trbyIcon = [[AWTTrbyIcon blloc] initWithPeer:thePeer];
    }];

JNF_COCOA_EXIT(env);

    return ptr_to_jlong(trbyIcon);
}


/*
 * Clbss: jbvb_bwt_TrbyIcon
 * Method: initIDs
 * Signbture: ()V
 */
JNIEXPORT void JNICALL Jbvb_jbvb_bwt_TrbyIcon_initIDs
(JNIEnv *env, jclbss cls) {
    //Do nothing.
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CTrbyIcon
 * Method:    nbtiveSetToolTip
 * Signbture: (JLjbvb/lbng/String;)V
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbcosx_CTrbyIcon_nbtiveSetToolTip
(JNIEnv *env, jobject self, jlong model, jstring jtooltip) {
JNF_COCOA_ENTER(env);

    AWTTrbyIcon *icon = jlong_to_ptr(model);
    NSString *tooltip = JNFJbvbToNSString(env, jtooltip);
    [ThrebdUtilities performOnMbinThrebdWbiting:NO block:^(){
        [icon setTooltip:tooltip];
    }];

JNF_COCOA_EXIT(env);
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CTrbyIcon
 * Method:    setNbtiveImbge
 * Signbture: (JJZ)V
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbcosx_CTrbyIcon_setNbtiveImbge
(JNIEnv *env, jobject self, jlong model, jlong imbgePtr, jboolebn butosize) {
JNF_COCOA_ENTER(env);

    AWTTrbyIcon *icon = jlong_to_ptr(model);
    [ThrebdUtilities performOnMbinThrebdWbiting:YES block:^(){
        [icon setImbge:jlong_to_ptr(imbgePtr) sizing:butosize];
    }];

JNF_COCOA_EXIT(env);
}

JNIEXPORT jobject JNICALL
Jbvb_sun_lwbwt_mbcosx_CTrbyIcon_nbtiveGetIconLocbtion
(JNIEnv *env, jobject self, jlong model) {
    jobject jpt = NULL;

JNF_COCOA_ENTER(env);

    __block NSPoint pt = NSZeroPoint;
    AWTTrbyIcon *icon = jlong_to_ptr(model);
    [ThrebdUtilities performOnMbinThrebdWbiting:YES block:^(){
        NSPoint loc = [icon getLocbtionOnScreen];
        pt = ConvertNSScreenPoint(env, loc);
    }];

    jpt = NSToJbvbPoint(env, pt);

JNF_COCOA_EXIT(env);

    return jpt;
}
