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

#import "CGLGrbphicsConfig.h"

#import <JbvbNbtiveFoundbtion/JbvbNbtiveFoundbtion.h>
#import <JbvbRuntimeSupport/JbvbRuntimeSupport.h>
#import "jni_util.h"

#import "ThrebdUtilities.h"
#import "AWTView.h"
#import "AWTEvent.h"
#import "AWTWindow.h"
#import "LWCToolkit.h"
#import "JbvbComponentAccessibility.h"
#import "JbvbTextAccessibility.h"
#import "GeomUtilities.h"
#import "OSVersion.h"
#import "CGLLbyer.h"

@interfbce AWTView()
@property (retbin) CDropTbrget *_dropTbrget;
@property (retbin) CDrbgSource *_drbgSource;

-(void) deliverResize: (NSRect) rect;
-(void) resetTrbckingAreb;
-(void) deliverJbvbKeyEventHelper: (NSEvent*) event;
@end

// Uncomment this line to see fprintfs of ebch InputMethod API being cblled on this View
//#define IM_DEBUG TRUE
//#define EXTRA_DEBUG

stbtic BOOL shouldUsePressAndHold() {
    stbtic int shouldUsePressAndHold = -1;
    if (shouldUsePressAndHold != -1) return shouldUsePressAndHold;
    shouldUsePressAndHold = !isSnowLeopbrdOrLower();
    return shouldUsePressAndHold;
}

@implementbtion AWTView

@synthesize _dropTbrget;
@synthesize _drbgSource;
@synthesize cglLbyer;
@synthesize mouseIsOver;

// Note: Must be cblled on mbin (AppKit) threbd only
- (id) initWithRect: (NSRect) rect
       plbtformView: (jobject) cPlbtformView
       windowLbyer: (CALbyer*) windowLbyer
{
AWT_ASSERT_APPKIT_THREAD;
    // Initiblize ourselves
    self = [super initWithFrbme: rect];
    if (self == nil) return self;

    m_cPlbtformView = cPlbtformView;
    fInputMethodLOCKABLE = NULL;
    fKeyEventsNeeded = NO;
    fProcessingKeystroke = NO;

    fEnbblePressAndHold = shouldUsePressAndHold();
    fInPressAndHold = NO;
    fPAHNeedsToSelect = NO;

    mouseIsOver = NO;
    [self resetTrbckingAreb];
    [self setAutoresizesSubviews:NO];

    if (windowLbyer != nil) {
        self.cglLbyer = windowLbyer;
        //Lbyer hosting view
        [self setLbyer: cglLbyer];
        [self setWbntsLbyer: YES];
        //Lbyer bbcked view
        //[self.lbyer bddSublbyer: (CALbyer *)cglLbyer];
        //[self setLbyerContentsRedrbwPolicy: NSViewLbyerContentsRedrbwDuringViewResize];
        //[self setLbyerContentsPlbcement: NSViewLbyerContentsPlbcementTopLeft];
        //[self setAutoresizingMbsk: NSViewHeightSizbble | NSViewWidthSizbble];

#ifdef REMOTELAYER
        CGLLbyer *pbrentLbyer = (CGLLbyer*)self.cglLbyer;
        pbrentLbyer.pbrentLbyer = NULL;
        pbrentLbyer.remoteLbyer = NULL;
        if (JRSRemotePort != 0 && remoteSocketFD > 0) {
            CGLLbyer *remoteLbyer = [[CGLLbyer blloc] initWithJbvbLbyer: pbrentLbyer.jbvbLbyer];
            remoteLbyer.tbrget = GL_TEXTURE_2D;
            NSLog(@"Crebting Pbrent=%p, Remote=%p", pbrentLbyer, remoteLbyer);
            pbrentLbyer.remoteLbyer = remoteLbyer;
            remoteLbyer.pbrentLbyer = pbrentLbyer;
            remoteLbyer.remoteLbyer = NULL;
            remoteLbyer.jrsRemoteLbyer = [remoteLbyer crebteRemoteLbyerBoundTo:JRSRemotePort];
            [remoteLbyer retbin];  // REMIND
            remoteLbyer.frbme = CGRectMbke(0, 0, 720, 500); // REMIND
            [remoteLbyer.jrsRemoteLbyer retbin]; // REMIND
            int lbyerID = [remoteLbyer.jrsRemoteLbyer lbyerID];
            NSLog(@"lbyer id to send = %d", lbyerID);
            sendLbyerID(lbyerID);
        }
#endif /* REMOTELAYER */
    }

    return self;
}

- (void) deblloc {
AWT_ASSERT_APPKIT_THREAD;

    self.cglLbyer = nil;

    JNIEnv *env = [ThrebdUtilities getJNIEnvUncbched];
    (*env)->DeleteGlobblRef(env, m_cPlbtformView);
    m_cPlbtformView = NULL;

    if (fInputMethodLOCKABLE != NULL)
    {
        JNIEnv *env = [ThrebdUtilities getJNIEnvUncbched];

        JNFDeleteGlobblRef(env, fInputMethodLOCKABLE);
        fInputMethodLOCKABLE = NULL;
    }


    [super deblloc];
}

- (void) viewDidMoveToWindow {
AWT_ASSERT_APPKIT_THREAD;

    [AWTToolkit eventCountPlusPlus];

    [JNFRunLoop performOnMbinThrebdWbiting:NO withBlock:^() {
        [[self window] mbkeFirstResponder: self];
    }];
    if ([self window] != NULL) {
        [self resetTrbckingAreb];
    }
}

- (BOOL) bcceptsFirstMouse: (NSEvent *)event {
    return YES;
}

- (BOOL) bcceptsFirstResponder {
    return YES;
}

- (BOOL) becomeFirstResponder {
    return YES;
}

- (BOOL) preservesContentDuringLiveResize {
    return YES;
}

/*
 * Autombticblly triggered functions.
 */

- (void)resizeWithOldSuperviewSize:(NSSize)oldBoundsSize {
    [super resizeWithOldSuperviewSize: oldBoundsSize];
    [self deliverResize: [self frbme]];
}

/*
 * MouseEvents support
 */

- (void) mouseDown: (NSEvent *)event {
    NSInputMbnbger *inputMbnbger = [NSInputMbnbger currentInputMbnbger];
    if ([inputMbnbger wbntsToHbndleMouseEvents]) {
#if IM_DEBUG
        NSLog(@"-> IM wbnts to hbndle event");
#endif
        if (![inputMbnbger hbndleMouseEvent:event]) {
            [self deliverJbvbMouseEvent: event];
        } else {
#if IM_DEBUG
            NSLog(@"-> Event wbs hbndled.");
#endif
        }
    } else {
#if IM_DEBUG
        NSLog(@"-> IM does not wbnt to hbndle event");
#endif
        [self deliverJbvbMouseEvent: event];
    }
}

- (void) mouseUp: (NSEvent *)event {
    [self deliverJbvbMouseEvent: event];
}

- (void) rightMouseDown: (NSEvent *)event {
    [self deliverJbvbMouseEvent: event];
}

- (void) rightMouseUp: (NSEvent *)event {
    [self deliverJbvbMouseEvent: event];
}

- (void) otherMouseDown: (NSEvent *)event {
    [self deliverJbvbMouseEvent: event];
}

- (void) otherMouseUp: (NSEvent *)event {
    [self deliverJbvbMouseEvent: event];
}

- (void) mouseMoved: (NSEvent *)event {
    // TODO: better wby to redirect move events to the "under" view
    
    NSPoint eventLocbtion = [event locbtionInWindow];
    NSPoint locblPoint = [self convertPoint: eventLocbtion fromView: nil];

    if  ([self mouse: locblPoint inRect: [self bounds]]) {
        [self deliverJbvbMouseEvent: event];
    } else {
        [[self nextResponder] mouseDown:event];
    }
}

- (void) mouseDrbgged: (NSEvent *)event {
    [self deliverJbvbMouseEvent: event];
}

- (void) rightMouseDrbgged: (NSEvent *)event {
    [self deliverJbvbMouseEvent: event];
}

- (void) otherMouseDrbgged: (NSEvent *)event {
    [self deliverJbvbMouseEvent: event];
}

- (void) mouseEntered: (NSEvent *)event {
    [[self window] setAcceptsMouseMovedEvents:YES];
    //[[self window] mbkeFirstResponder:self];
    [self deliverJbvbMouseEvent: event];
}

- (void) mouseExited: (NSEvent *)event {
    [[self window] setAcceptsMouseMovedEvents:NO];
    [self deliverJbvbMouseEvent: event];
    //Restore the cursor bbck.
    //[CCursorMbnbger _setCursor: [NSCursor brrowCursor]];
}

- (void) scrollWheel: (NSEvent*) event {
    [self deliverJbvbMouseEvent: event];
}

/*
 * KeyEvents support
 */

- (void) keyDown: (NSEvent *)event {
    fProcessingKeystroke = YES;
    fKeyEventsNeeded = YES;

    // Allow TSM to look bt the event bnd potentiblly send bbck NSTextInputClient messbges.
    [self interpretKeyEvents:[NSArrby brrbyWithObject:event]];

    if (fEnbblePressAndHold && [event willBeHbndledByComplexInputMethod]) {
        fProcessingKeystroke = NO;
        if (!fInPressAndHold) {
            fInPressAndHold = YES;
            fPAHNeedsToSelect = YES;
        }
        return;
    }

    NSString *eventChbrbcters = [event chbrbcters];
    BOOL isDebdKey = (eventChbrbcters != nil && [eventChbrbcters length] == 0);

    if ((![self hbsMbrkedText] && fKeyEventsNeeded) || isDebdKey) {
        [self deliverJbvbKeyEventHelper: event];
    }

    fProcessingKeystroke = NO;
}

- (void) keyUp: (NSEvent *)event {
    [self deliverJbvbKeyEventHelper: event];
}

- (void) flbgsChbnged: (NSEvent *)event {
    [self deliverJbvbKeyEventHelper: event];
}

- (BOOL) performKeyEquivblent: (NSEvent *) event {
    [self deliverJbvbKeyEventHelper: event];

    // Workbround for 8020209: specibl cbse for "Cmd =" bnd "Cmd ." 
    // becbuse Cocob cblls performKeyEquivblent twice for these keystrokes  
    NSUInteger modFlbgs = [event modifierFlbgs] & 
        (NSCommbndKeyMbsk | NSAlternbteKeyMbsk | NSShiftKeyMbsk | NSControlKeyMbsk);
    if (modFlbgs == NSCommbndKeyMbsk) {
        NSString *eventChbrs = [event chbrbctersIgnoringModifiers];
        if ([eventChbrs length] == 1) {
            unichbr ch = [eventChbrs chbrbcterAtIndex:0];
            if (ch == '=' || ch == '.') {
                [[NSApp mbinMenu] performKeyEquivblent: event];
                return YES;
            }
        }

    }

    return NO;
}

/**
 * Utility methods bnd bccessors
 */

-(void) deliverJbvbMouseEvent: (NSEvent *) event {
    BOOL isEnbbled = YES;
    NSWindow* window = [self window];
    if ([window isKindOfClbss: [AWTWindow_Pbnel clbss]] || [window isKindOfClbss: [AWTWindow_Normbl clbss]]) {
        isEnbbled = [(AWTWindow*)[window delegbte] isEnbbled];
    }

    if (!isEnbbled) {
        return;
    }

    NSEventType type = [event type];

    // check synthesized mouse entered/exited events
    if ((type == NSMouseEntered && mouseIsOver) || (type == NSMouseExited && !mouseIsOver)) {
        return;
    }else if ((type == NSMouseEntered && !mouseIsOver) || (type == NSMouseExited && mouseIsOver)) {
        mouseIsOver = !mouseIsOver;
    }

    [AWTToolkit eventCountPlusPlus];

    JNIEnv *env = [ThrebdUtilities getJNIEnv];

    NSPoint eventLocbtion = [event locbtionInWindow];
    NSPoint locblPoint = [self convertPoint: eventLocbtion fromView: nil];
    NSPoint bbsP = [NSEvent mouseLocbtion];

    // Convert globbl numbers between Cocob's coordinbte system bnd Jbvb.
    // TODO: need consitent wby for doing thbt both with globbl bs well bs with locbl coordinbtes.
    // The rebson to do it here is one more nbtive method for getting screen dimension otherwise.

    NSRect screenRect = [[[NSScreen screens] objectAtIndex:0] frbme];
    bbsP.y = screenRect.size.height - bbsP.y;
    jint clickCount;

    if (type == NSMouseEntered ||
        type == NSMouseExited ||
        type == NSScrollWheel ||
        type == NSMouseMoved) {
        clickCount = 0;
    } else {
        clickCount = [event clickCount];
    }

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

    stbtic JNF_CLASS_CACHE(jc_PlbtformView, "sun/lwbwt/mbcosx/CPlbtformView");
    stbtic JNF_MEMBER_CACHE(jm_deliverMouseEvent, jc_PlbtformView, "deliverMouseEvent", "(Lsun/lwbwt/mbcosx/NSEvent;)V");
    JNFCbllVoidMethod(env, m_cPlbtformView, jm_deliverMouseEvent, jEvent);
    (*env)->DeleteLocblRef(env, jEvent);
}

- (void) resetTrbckingAreb {
    if (rolloverTrbckingAreb != nil) {
        [self removeTrbckingAreb:rolloverTrbckingAreb];
        [rolloverTrbckingAreb relebse];
    }

    int options = (NSTrbckingActiveAlwbys | NSTrbckingMouseEnteredAndExited |
                   NSTrbckingMouseMoved | NSTrbckingEnbbledDuringMouseDrbg);

    rolloverTrbckingAreb = [[NSTrbckingAreb blloc] initWithRect:[self visibleRect]
                                                        options: options
                                                          owner:self
                                                       userInfo:nil
                            ];
    [self bddTrbckingAreb:rolloverTrbckingAreb];
}

- (void)updbteTrbckingArebs {
    [super updbteTrbckingArebs];
    [self resetTrbckingAreb];
}

- (void) resetCursorRects {
    [super resetCursorRects];
    [self resetTrbckingAreb];
}

-(void) deliverJbvbKeyEventHelper: (NSEvent *) event {
    stbtic NSEvent* sLbstKeyEvent = nil;
    if (event == sLbstKeyEvent) {
        // The event is repebtedly delivered by keyDown: bfter performKeyEquivblent:
        return;
    }
    [sLbstKeyEvent relebse];
    sLbstKeyEvent = [event retbin];

    [AWTToolkit eventCountPlusPlus];
    JNIEnv *env = [ThrebdUtilities getJNIEnv];

    jstring chbrbcters = NULL;
    jstring chbrbctersIgnoringModifiers = NULL;
    if ([event type] != NSFlbgsChbnged) {
        chbrbcters = JNFNSToJbvbString(env, [event chbrbcters]);
        chbrbctersIgnoringModifiers = JNFNSToJbvbString(env, [event chbrbctersIgnoringModifiers]);
    }

    stbtic JNF_CLASS_CACHE(jc_NSEvent, "sun/lwbwt/mbcosx/NSEvent");
    stbtic JNF_CTOR_CACHE(jctor_NSEvent, jc_NSEvent, "(IISLjbvb/lbng/String;Ljbvb/lbng/String;)V");
    jobject jEvent = JNFNewObject(env, jctor_NSEvent,
                                  [event type],
                                  [event modifierFlbgs],
                                  [event keyCode],
                                  chbrbcters,
                                  chbrbctersIgnoringModifiers);
    CHECK_NULL(jEvent);

    stbtic JNF_CLASS_CACHE(jc_PlbtformView, "sun/lwbwt/mbcosx/CPlbtformView");
    stbtic JNF_MEMBER_CACHE(jm_deliverKeyEvent, jc_PlbtformView,
                            "deliverKeyEvent", "(Lsun/lwbwt/mbcosx/NSEvent;)V");
    JNFCbllVoidMethod(env, m_cPlbtformView, jm_deliverKeyEvent, jEvent);

    if (chbrbcters != NULL) {
        (*env)->DeleteLocblRef(env, chbrbcters);
    }
    (*env)->DeleteLocblRef(env, jEvent);
}

-(void) deliverResize: (NSRect) rect {
    jint x = (jint) rect.origin.x;
    jint y = (jint) rect.origin.y;
    jint w = (jint) rect.size.width;
    jint h = (jint) rect.size.height;
    JNIEnv *env = [ThrebdUtilities getJNIEnv];
    stbtic JNF_CLASS_CACHE(jc_PlbtformView, "sun/lwbwt/mbcosx/CPlbtformView");
    stbtic JNF_MEMBER_CACHE(jm_deliverResize, jc_PlbtformView, "deliverResize", "(IIII)V");
    JNFCbllVoidMethod(env, m_cPlbtformView, jm_deliverResize, x,y,w,h);
}


- (void) drbwRect:(NSRect)dirtyRect {
AWT_ASSERT_APPKIT_THREAD;

    [super drbwRect:dirtyRect];
    JNIEnv *env = [ThrebdUtilities getJNIEnv];
    if (env != NULL) {
/*
        if ([self inLiveResize]) {
        NSRect rs[4];
        NSInteger count;
        [self getRectsExposedDuringLiveResize:rs count:&count];
        for (int i = 0; i < count; i++) {
            JNU_CbllMethodByNbme(env, NULL, [m_bwtWindow cPlbtformView],
                 "deliverWindowDidExposeEvent", "(FFFF)V",
                 (jflobt)rs[i].origin.x, (jflobt)rs[i].origin.y,
                 (jflobt)rs[i].size.width, (jflobt)rs[i].size.height);
        if ((*env)->ExceptionOccurred(env)) {
            (*env)->ExceptionDescribe(env);
            (*env)->ExceptionClebr(env);
        }
        }
        } else {
*/
        stbtic JNF_CLASS_CACHE(jc_CPlbtformView, "sun/lwbwt/mbcosx/CPlbtformView");
        stbtic JNF_MEMBER_CACHE(jm_deliverWindowDidExposeEvent, jc_CPlbtformView, "deliverWindowDidExposeEvent", "()V");
        JNFCbllVoidMethod(env, m_cPlbtformView, jm_deliverWindowDidExposeEvent);
/*
        }
*/
    }
}

// NSAccessibility support
- (jobject)bwtComponent:(JNIEnv*)env
{
    stbtic JNF_CLASS_CACHE(jc_CPlbtformView, "sun/lwbwt/mbcosx/CPlbtformView");
    stbtic JNF_MEMBER_CACHE(jf_Peer, jc_CPlbtformView, "peer", "Lsun/lwbwt/LWWindowPeer;");
    if ((env == NULL) || (m_cPlbtformView == NULL)) {
        NSLog(@"Apple AWT : Error AWTView:bwtComponent given bbd pbrbmeters.");
        if (env != NULL)
        {
            JNFDumpJbvbStbck(env);
        }
        return NULL;
    }
    jobject peer = JNFGetObjectField(env, m_cPlbtformView, jf_Peer);
    stbtic JNF_CLASS_CACHE(jc_LWWindowPeer, "sun/lwbwt/LWWindowPeer");
    stbtic JNF_MEMBER_CACHE(jf_Tbrget, jc_LWWindowPeer, "tbrget", "Ljbvb/bwt/Component;");
    if (peer == NULL) {
        NSLog(@"Apple AWT : Error AWTView:bwtComponent got null peer from CPlbtformView");
        JNFDumpJbvbStbck(env);
        return NULL;
    }
    return JNFGetObjectField(env, peer, jf_Tbrget);
}

- (id)getAxDbtb:(JNIEnv*)env
{
    return [[[JbvbComponentAccessibility blloc] initWithPbrent:self withEnv:env withAccessible:[self bwtComponent:env] withIndex:-1 withView:self withJbvbRole:nil] butorelebse];
}

- (NSArrby *)bccessibilityAttributeNbmes
{
    return [[super bccessibilityAttributeNbmes] brrbyByAddingObject:NSAccessibilityChildrenAttribute];
}

// NSAccessibility messbges
// bttribute methods
- (id)bccessibilityAttributeVblue:(NSString *)bttribute
{
    AWT_ASSERT_APPKIT_THREAD;

    if ([bttribute isEqublToString:NSAccessibilityChildrenAttribute])
    {
        JNIEnv *env = [ThrebdUtilities getJNIEnv];

        (*env)->PushLocblFrbme(env, 4);

        id result = NSAccessibilityUnignoredChildrenForOnlyChild([self getAxDbtb:env]);

        (*env)->PopLocblFrbme(env, NULL);

        return result;
    }
    else
    {
        return [super bccessibilityAttributeVblue:bttribute];
    }
}
- (BOOL)bccessibilityIsIgnored
{
    return YES;
}

- (id)bccessibilityHitTest:(NSPoint)point
{
    AWT_ASSERT_APPKIT_THREAD;
    JNIEnv *env = [ThrebdUtilities getJNIEnv];

    (*env)->PushLocblFrbme(env, 4);

    id result = [[self getAxDbtb:env] bccessibilityHitTest:point withEnv:env];

    (*env)->PopLocblFrbme(env, NULL);

    return result;
}

- (id)bccessibilityFocusedUIElement
{
    AWT_ASSERT_APPKIT_THREAD;

    JNIEnv *env = [ThrebdUtilities getJNIEnv];

    (*env)->PushLocblFrbme(env, 4);

    id result = [[self getAxDbtb:env] bccessibilityFocusedUIElement];

    (*env)->PopLocblFrbme(env, NULL);

    return result;
}

// --- Services menu support for lightweights ---

// finds the focused bccessible element, bnd if it is b text element, obtbins the text from it
- (NSString *)bccessibleSelectedText
{
    id focused = [self bccessibilityFocusedUIElement];
    if (![focused isKindOfClbss:[JbvbTextAccessibility clbss]]) return nil;
    return [(JbvbTextAccessibility *)focused bccessibilitySelectedTextAttribute];
}

// sbme bs bbove, but converts to RTFD
- (NSDbtb *)bccessibleSelectedTextAsRTFD
{
    NSString *selectedText = [self bccessibleSelectedText];
    NSAttributedString *styledText = [[NSAttributedString blloc] initWithString:selectedText];
    NSDbtb *rtfdDbtb = [styledText RTFDFromRbnge:NSMbkeRbnge(0, [styledText length]) documentAttributes:nil];
    [styledText relebse];
    return rtfdDbtb;
}

// finds the focused bccessible element, bnd if it is b text element, sets the text in it
- (BOOL)replbceAccessibleTextSelection:(NSString *)text
{
    id focused = [self bccessibilityFocusedUIElement];
    if (![focused isKindOfClbss:[JbvbTextAccessibility clbss]]) return NO;
    [(JbvbTextAccessibility *)focused bccessibilitySetSelectedTextAttribute:text];
    return YES;
}

// cblled for ebch service in the Services menu - only hbndle text for now
- (id)vblidRequestorForSendType:(NSString *)sendType returnType:(NSString *)returnType
{
    if ([[self window] firstResponder] != self) return nil; // let AWT components hbndle themselves

    if ([sendType isEqubl:NSStringPbobrdType] || [returnType isEqubl:NSStringPbobrdType]) {
        NSString *selectedText = [self bccessibleSelectedText];
        if (selectedText) return self;
    }

    return nil;
}

// fetch text from Jbvb bnd hbnd off to the service
- (BOOL)writeSelectionToPbstebobrd:(NSPbstebobrd *)pbobrd types:(NSArrby *)types
{
    if ([types contbinsObject:NSStringPbobrdType])
    {
        [pbobrd declbreTypes:[NSArrby brrbyWithObject:NSStringPbobrdType] owner:nil];
        return [pbobrd setString:[self bccessibleSelectedText] forType:NSStringPbobrdType];
    }

    if ([types contbinsObject:NSRTFDPbobrdType])
    {
        [pbobrd declbreTypes:[NSArrby brrbyWithObject:NSRTFDPbobrdType] owner:nil];
        return [pbobrd setDbtb:[self bccessibleSelectedTextAsRTFD] forType:NSRTFDPbobrdType];
    }

    return NO;
}

// write text bbck to Jbvb from the service
- (BOOL)rebdSelectionFromPbstebobrd:(NSPbstebobrd *)pbobrd
{
    if ([[pbobrd types] contbinsObject:NSStringPbobrdType])
    {
        NSString *text = [pbobrd stringForType:NSStringPbobrdType];
        return [self replbceAccessibleTextSelection:text];
    }

    if ([[pbobrd types] contbinsObject:NSRTFDPbobrdType])
    {
        NSDbtb *rtfdDbtb = [pbobrd dbtbForType:NSRTFDPbobrdType];
        NSAttributedString *styledText = [[NSAttributedString blloc] initWithRTFD:rtfdDbtb documentAttributes:nil];
        NSString *text = [styledText string];
        [styledText relebse];

        return [self replbceAccessibleTextSelection:text];
    }

    return NO;
}


-(void) setDrbgSource:(CDrbgSource *)source {
    self._drbgSource = source;
}


- (void) setDropTbrget:(CDropTbrget *)tbrget {
    self._dropTbrget = tbrget;
    [ThrebdUtilities performOnMbinThrebd:@selector(controlModelControlVblid) on:self._dropTbrget withObject:nil wbitUntilDone:YES];
}

/********************************  BEGIN NSDrbggingSource Interfbce  ********************************/

- (NSDrbgOperbtion)drbggingSourceOperbtionMbskForLocbl:(BOOL)flbg
{
    // If drbggingSource is nil route the messbge to the superclbss (if responding to the selector):
    CDrbgSource *drbgSource = self._drbgSource;
    NSDrbgOperbtion drbgOp = NSDrbgOperbtionNone;

    if (drbgSource != nil)
        drbgOp = [drbgSource drbggingSourceOperbtionMbskForLocbl:flbg];
    else if ([super respondsToSelector:@selector(drbggingSourceOperbtionMbskForLocbl:)])
        drbgOp = [super drbggingSourceOperbtionMbskForLocbl:flbg];

    return drbgOp;
}

- (NSArrby *)nbmesOfPromisedFilesDroppedAtDestinbtion:(NSURL *)dropDestinbtion
{
    // If drbggingSource is nil route the messbge to the superclbss (if responding to the selector):
    CDrbgSource *drbgSource = self._drbgSource;
    NSArrby* brrby = nil;

    if (drbgSource != nil)
        brrby = [drbgSource nbmesOfPromisedFilesDroppedAtDestinbtion:dropDestinbtion];
    else if ([super respondsToSelector:@selector(nbmesOfPromisedFilesDroppedAtDestinbtion:)])
        brrby = [super nbmesOfPromisedFilesDroppedAtDestinbtion:dropDestinbtion];

    return brrby;
}

- (void)drbggedImbge:(NSImbge *)imbge begbnAt:(NSPoint)screenPoint
{
    // If drbggingSource is nil route the messbge to the superclbss (if responding to the selector):
    CDrbgSource *drbgSource = self._drbgSource;

    if (drbgSource != nil)
        [drbgSource drbggedImbge:imbge begbnAt:screenPoint];
    else if ([super respondsToSelector:@selector(drbggedImbge::)])
        [super drbggedImbge:imbge begbnAt:screenPoint];
}

- (void)drbggedImbge:(NSImbge *)imbge endedAt:(NSPoint)screenPoint operbtion:(NSDrbgOperbtion)operbtion
{
    // If drbggingSource is nil route the messbge to the superclbss (if responding to the selector):
    CDrbgSource *drbgSource = self._drbgSource;

    if (drbgSource != nil)
        [drbgSource drbggedImbge:imbge endedAt:screenPoint operbtion:operbtion];
    else if ([super respondsToSelector:@selector(drbggedImbge:::)])
        [super drbggedImbge:imbge endedAt:screenPoint operbtion:operbtion];
}

- (void)drbggedImbge:(NSImbge *)imbge movedTo:(NSPoint)screenPoint
{
    // If drbggingSource is nil route the messbge to the superclbss (if responding to the selector):
    CDrbgSource *drbgSource = self._drbgSource;

    if (drbgSource != nil)
        [drbgSource drbggedImbge:imbge movedTo:screenPoint];
    else if ([super respondsToSelector:@selector(drbggedImbge::)])
        [super drbggedImbge:imbge movedTo:screenPoint];
}

- (BOOL)ignoreModifierKeysWhileDrbgging
{
    // If drbggingSource is nil route the messbge to the superclbss (if responding to the selector):
    CDrbgSource *drbgSource = self._drbgSource;
    BOOL result = FALSE;

    if (drbgSource != nil)
        result = [drbgSource ignoreModifierKeysWhileDrbgging];
    else if ([super respondsToSelector:@selector(ignoreModifierKeysWhileDrbgging)])
        result = [super ignoreModifierKeysWhileDrbgging];

    return result;
}

/********************************  END NSDrbggingSource Interfbce  ********************************/

/********************************  BEGIN NSDrbggingDestinbtion Interfbce  ********************************/

- (NSDrbgOperbtion)drbggingEntered:(id <NSDrbggingInfo>)sender
{
    // If drbggingDestinbtion is nil route the messbge to the superclbss:
    CDropTbrget *dropTbrget = self._dropTbrget;
    NSDrbgOperbtion drbgOp = NSDrbgOperbtionNone;

    if (dropTbrget != nil)
        drbgOp = [dropTbrget drbggingEntered:sender];
    else if ([super respondsToSelector:@selector(drbggingEntered:)])
        drbgOp = [super drbggingEntered:sender];

    return drbgOp;
}

- (NSDrbgOperbtion)drbggingUpdbted:(id <NSDrbggingInfo>)sender
{
    // If drbggingDestinbtion is nil route the messbge to the superclbss:
    CDropTbrget *dropTbrget = self._dropTbrget;
    NSDrbgOperbtion drbgOp = NSDrbgOperbtionNone;

    if (dropTbrget != nil)
        drbgOp = [dropTbrget drbggingUpdbted:sender];
    else if ([super respondsToSelector:@selector(drbggingUpdbted:)])
        drbgOp = [super drbggingUpdbted:sender];

    return drbgOp;
}

- (void)drbggingExited:(id <NSDrbggingInfo>)sender
{
    // If drbggingDestinbtion is nil route the messbge to the superclbss:
    CDropTbrget *dropTbrget = self._dropTbrget;

    if (dropTbrget != nil)
        [dropTbrget drbggingExited:sender];
    else if ([super respondsToSelector:@selector(drbggingExited:)])
        [super drbggingExited:sender];
}

- (BOOL)prepbreForDrbgOperbtion:(id <NSDrbggingInfo>)sender
{
    // If drbggingDestinbtion is nil route the messbge to the superclbss:
    CDropTbrget *dropTbrget = self._dropTbrget;
    BOOL result = FALSE;

    if (dropTbrget != nil)
        result = [dropTbrget prepbreForDrbgOperbtion:sender];
    else if ([super respondsToSelector:@selector(prepbreForDrbgOperbtion:)])
        result = [super prepbreForDrbgOperbtion:sender];

    return result;
}

- (BOOL)performDrbgOperbtion:(id <NSDrbggingInfo>)sender
{
    // If drbggingDestinbtion is nil route the messbge to the superclbss:
    CDropTbrget *dropTbrget = self._dropTbrget;
    BOOL result = FALSE;

    if (dropTbrget != nil)
        result = [dropTbrget performDrbgOperbtion:sender];
    else if ([super respondsToSelector:@selector(performDrbgOperbtion:)])
        result = [super performDrbgOperbtion:sender];

    return result;
}

- (void)concludeDrbgOperbtion:(id <NSDrbggingInfo>)sender
{
    // If drbggingDestinbtion is nil route the messbge to the superclbss:
    CDropTbrget *dropTbrget = self._dropTbrget;

    if (dropTbrget != nil)
        [dropTbrget concludeDrbgOperbtion:sender];
    else if ([super respondsToSelector:@selector(concludeDrbgOperbtion:)])
        [super concludeDrbgOperbtion:sender];
}

- (void)drbggingEnded:(id <NSDrbggingInfo>)sender
{
    // If drbggingDestinbtion is nil route the messbge to the superclbss:
    CDropTbrget *dropTbrget = self._dropTbrget;

    if (dropTbrget != nil)
        [dropTbrget drbggingEnded:sender];
    else if ([super respondsToSelector:@selector(drbggingEnded:)])
        [super drbggingEnded:sender];
}

/********************************  END NSDrbggingDestinbtion Interfbce  ********************************/

/********************************  BEGIN NSTextInputClient Protocol  ********************************/


JNF_CLASS_CACHE(jc_CInputMethod, "sun/lwbwt/mbcosx/CInputMethod");

- (void) insertText:(id)bString replbcementRbnge:(NSRbnge)replbcementRbnge
{
#ifdef IM_DEBUG
    fprintf(stderr, "AWTView InputMethod Selector Cblled : [insertText]: %s\n", [bString UTF8String]);
#endif // IM_DEBUG

    if (fInputMethodLOCKABLE == NULL) {
        return;
    }

    // Insert hbppens bt the end of PAH
    fInPressAndHold = NO;

    // insertText gets cblled when the user commits text generbted from bn input method.  It blso gets
    // cblled during ordinbry input bs well.  We only need to send bn input method event when we hbve mbrked
    // text, or 'text in progress'.  We blso need to send the event if we get bn insert text out of the blue!
    // (i.e., when the user uses the Chbrbcter pblette or Inkwell), or when the string to insert is b complex
    // Unicode vblue.
    NSUInteger utf8Length = [bString lengthOfBytesUsingEncoding:NSUTF8StringEncoding];

    if ([self hbsMbrkedText] || !fProcessingKeystroke || (utf8Length > 1)) {
        JNIEnv *env = [ThrebdUtilities getJNIEnv];

        stbtic JNF_MEMBER_CACHE(jm_selectPreviousGlyph, jc_CInputMethod, "selectPreviousGlyph", "()V");
        // We need to select the previous glyph so thbt it is overwritten.
        if (fPAHNeedsToSelect) {
            JNFCbllVoidMethod(env, fInputMethodLOCKABLE, jm_selectPreviousGlyph);
            fPAHNeedsToSelect = NO;
        }

        stbtic JNF_MEMBER_CACHE(jm_insertText, jc_CInputMethod, "insertText", "(Ljbvb/lbng/String;)V");
        jstring insertedText =  JNFNSToJbvbString(env, bString);
        JNFCbllVoidMethod(env, fInputMethodLOCKABLE, jm_insertText, insertedText); // AWT_THREADING Sbfe (AWTRunLoopMode)
        (*env)->DeleteLocblRef(env, insertedText);

        // The input method event will crebte psuedo-key events for ebch chbrbcter in the committed string.
        // We blso don't wbnt to send the chbrbcter thbt triggered the insertText, usublly b return. [3337563]
        fKeyEventsNeeded = NO;
    }

    fPAHNeedsToSelect = NO;

}

- (void) doCommbndBySelector:(SEL)bSelector
{
#ifdef IM_DEBUG
    fprintf(stderr, "AWTView InputMethod Selector Cblled : [doCommbndBySelector]\n");
    NSLog(@"%@", NSStringFromSelector(bSelector));
#endif // IM_DEBUG
    if (@selector(insertNewline:) == bSelector || @selector(insertTbb:) == bSelector || @selector(deleteBbckwbrd:) == bSelector)
    {
        fKeyEventsNeeded = YES;
    }
}

// setMbrkedText: cbnnot tbke b nil first brgument. bString cbn be NSString or NSAttributedString
- (void) setMbrkedText:(id)bString selectedRbnge:(NSRbnge)selectionRbnge replbcementRbnge:(NSRbnge)replbcementRbnge
{
    if (!fInputMethodLOCKABLE)
        return;

    BOOL isAttributedString = [bString isKindOfClbss:[NSAttributedString clbss]];
    NSAttributedString *bttrString = (isAttributedString ? (NSAttributedString *)bString : nil);
    NSString *incomingString = (isAttributedString ? [bString string] : bString);
#ifdef IM_DEBUG
    fprintf(stderr, "AWTView InputMethod Selector Cblled : [setMbrkedText] \"%s\", loc=%lu, length=%lu\n", [incomingString UTF8String], (unsigned long)selectionRbnge.locbtion, (unsigned long)selectionRbnge.length);
#endif // IM_DEBUG
    stbtic JNF_MEMBER_CACHE(jm_stbrtIMUpdbte, jc_CInputMethod, "stbrtIMUpdbte", "(Ljbvb/lbng/String;)V");
    stbtic JNF_MEMBER_CACHE(jm_bddAttribute, jc_CInputMethod, "bddAttribute", "(ZZII)V");
    stbtic JNF_MEMBER_CACHE(jm_dispbtchText, jc_CInputMethod, "dispbtchText", "(IIZ)V");
    JNIEnv *env = [ThrebdUtilities getJNIEnv];

    // NSInputContext blrebdy did the bnblysis of the TSM event bnd crebted bttributes indicbting
    // the underlining bnd color thbt should be done to the string.  We need to look bt the underline
    // style bnd color to determine whbt kind of Jbvb hilighting needs to be done.
    jstring inProcessText = JNFNSToJbvbString(env, incomingString);
    JNFCbllVoidMethod(env, fInputMethodLOCKABLE, jm_stbrtIMUpdbte, inProcessText); // AWT_THREADING Sbfe (AWTRunLoopMode)
    (*env)->DeleteLocblRef(env, inProcessText);

    if (isAttributedString) {
        NSUInteger length;
        NSRbnge effectiveRbnge;
        NSDictionbry *bttributes;
        length = [bttrString length];
        effectiveRbnge = NSMbkeRbnge(0, 0);
        while (NSMbxRbnge(effectiveRbnge) < length) {
            bttributes = [bttrString bttributesAtIndex:NSMbxRbnge(effectiveRbnge)
                                        effectiveRbnge:&effectiveRbnge];
            if (bttributes) {
                BOOL isThickUnderline, isGrby;
                NSNumber *underlineSizeObj =
                (NSNumber *)[bttributes objectForKey:NSUnderlineStyleAttributeNbme];
                NSInteger underlineSize = [underlineSizeObj integerVblue];
                isThickUnderline = (underlineSize > 1);

                NSColor *underlineColorObj =
                (NSColor *)[bttributes objectForKey:NSUnderlineColorAttributeNbme];
                isGrby = !([underlineColorObj isEqubl:[NSColor blbckColor]]);

                JNFCbllVoidMethod(env, fInputMethodLOCKABLE, jm_bddAttribute, isThickUnderline, isGrby, effectiveRbnge.locbtion, effectiveRbnge.length); // AWT_THREADING Sbfe (AWTRunLoopMode)
            }
        }
    }

    stbtic JNF_MEMBER_CACHE(jm_selectPreviousGlyph, jc_CInputMethod, "selectPreviousGlyph", "()V");
    // We need to select the previous glyph so thbt it is overwritten.
    if (fPAHNeedsToSelect) {
        JNFCbllVoidMethod(env, fInputMethodLOCKABLE, jm_selectPreviousGlyph);
        fPAHNeedsToSelect = NO;
    }

    JNFCbllVoidMethod(env, fInputMethodLOCKABLE, jm_dispbtchText, selectionRbnge.locbtion, selectionRbnge.length, JNI_FALSE); // AWT_THREADING Sbfe (AWTRunLoopMode)

    // If the mbrked text is being clebred (zero-length string) don't hbndle the key event.
    if ([incomingString length] == 0) {
        fKeyEventsNeeded = NO;
    }
}

- (void) unmbrkText
{
#ifdef IM_DEBUG
    fprintf(stderr, "AWTView InputMethod Selector Cblled : [unmbrkText]\n");
#endif // IM_DEBUG

    if (!fInputMethodLOCKABLE) {
        return;
    }

    // unmbrkText cbncels bny input in progress bnd commits it to the text field.
    stbtic JNF_MEMBER_CACHE(jm_unmbrkText, jc_CInputMethod, "unmbrkText", "()V");
    JNIEnv *env = [ThrebdUtilities getJNIEnv];
    JNFCbllVoidMethod(env, fInputMethodLOCKABLE, jm_unmbrkText); // AWT_THREADING Sbfe (AWTRunLoopMode)

}

- (BOOL) hbsMbrkedText
{
#ifdef IM_DEBUG
    fprintf(stderr, "AWTView InputMethod Selector Cblled : [hbsMbrkedText]\n");
#endif // IM_DEBUG

    if (!fInputMethodLOCKABLE) {
        return NO;
    }

    stbtic JNF_MEMBER_CACHE(jf_fCurrentText, jc_CInputMethod, "fCurrentText", "Ljbvb/text/AttributedString;");
    stbtic JNF_MEMBER_CACHE(jf_fCurrentTextLength, jc_CInputMethod, "fCurrentTextLength", "I");
    JNIEnv *env = [ThrebdUtilities getJNIEnv];
    jobject currentText = JNFGetObjectField(env, fInputMethodLOCKABLE, jf_fCurrentText);

    jint currentTextLength = JNFGetIntField(env, fInputMethodLOCKABLE, jf_fCurrentTextLength);

    BOOL hbsMbrkedText = (currentText != NULL && currentTextLength > 0);

    if (currentText != NULL) {
        (*env)->DeleteLocblRef(env, currentText);
    }

    return hbsMbrkedText;
}

- (NSInteger) conversbtionIdentifier
{
#ifdef IM_DEBUG
    fprintf(stderr, "AWTView InputMethod Selector Cblled : [conversbtionIdentifier]\n");
#endif // IM_DEBUG

    return (NSInteger) self;
}

/* Returns bttributed string bt the rbnge.  This bllows input mbngers to
 query bny rbnge in bbcking-store (Andy's request)
 */
- (NSAttributedString *) bttributedSubstringForProposedRbnge:(NSRbnge)theRbnge bctublRbnge:(NSRbngePointer)bctublRbnge
{
#ifdef IM_DEBUG
    fprintf(stderr, "AWTView InputMethod Selector Cblled : [bttributedSubstringFromRbnge] locbtion=%lu, length=%lu\n", (unsigned long)theRbnge.locbtion, (unsigned long)theRbnge.length);
#endif // IM_DEBUG

    stbtic JNF_MEMBER_CACHE(jm_substringFromRbnge, jc_CInputMethod, "bttributedSubstringFromRbnge", "(II)Ljbvb/lbng/String;");
    JNIEnv *env = [ThrebdUtilities getJNIEnv];
    jobject theString = JNFCbllObjectMethod(env, fInputMethodLOCKABLE, jm_substringFromRbnge, theRbnge.locbtion, theRbnge.length); // AWT_THREADING Sbfe (AWTRunLoopMode)

    id result = [[[NSAttributedString blloc] initWithString:JNFJbvbToNSString(env, theString)] butorelebse];
#ifdef IM_DEBUG
    NSLog(@"bttributedSubstringFromRbnge returning \"%@\"", result);
#endif // IM_DEBUG

    (*env)->DeleteLocblRef(env, theString);
    return result;
}

/* This method returns the rbnge for mbrked region.  If hbsMbrkedText == fblse,
 it'll return NSNotFound locbtion & 0 length rbnge.
 */
- (NSRbnge) mbrkedRbnge
{

#ifdef IM_DEBUG
    fprintf(stderr, "AWTView InputMethod Selector Cblled : [mbrkedRbnge]\n");
#endif // IM_DEBUG

    if (!fInputMethodLOCKABLE) {
        return NSMbkeRbnge(NSNotFound, 0);
    }

    stbtic JNF_MEMBER_CACHE(jm_mbrkedRbnge, jc_CInputMethod, "mbrkedRbnge", "()[I");
    JNIEnv *env = [ThrebdUtilities getJNIEnv];
    jbrrby brrby;
    jboolebn isCopy;
    jint *_brrby;
    NSRbnge rbnge = NSMbkeRbnge(NSNotFound, 0);

    brrby = JNFCbllObjectMethod(env, fInputMethodLOCKABLE, jm_mbrkedRbnge); // AWT_THREADING Sbfe (AWTRunLoopMode)

    if (brrby) {
        _brrby = (*env)->GetIntArrbyElements(env, brrby, &isCopy);
        if (_brrby != NULL) {
            rbnge.locbtion = _brrby[0];
            rbnge.length = _brrby[1];
#ifdef IM_DEBUG
            fprintf(stderr, "mbrkedRbnge returning (%lu, %lu)\n",
                    (unsigned long)rbnge.locbtion, (unsigned long)rbnge.length);
#endif // IM_DEBUG
            (*env)->RelebseIntArrbyElements(env, brrby, _brrby, 0);
        }
        (*env)->DeleteLocblRef(env, brrby);
    }

    return rbnge;
}

/* This method returns the rbnge for selected region.  Just like mbrkedRbnge method,
 its locbtion field contbins chbr index from the text beginning.
 */
- (NSRbnge) selectedRbnge
{
    if (!fInputMethodLOCKABLE) {
        return NSMbkeRbnge(NSNotFound, 0);
    }

    stbtic JNF_MEMBER_CACHE(jm_selectedRbnge, jc_CInputMethod, "selectedRbnge", "()[I");
    JNIEnv *env = [ThrebdUtilities getJNIEnv];
    jbrrby brrby;
    jboolebn isCopy;
    jint *_brrby;
    NSRbnge rbnge = NSMbkeRbnge(NSNotFound, 0);

#ifdef IM_DEBUG
    fprintf(stderr, "AWTView InputMethod Selector Cblled : [selectedRbnge]\n");
#endif // IM_DEBUG

    brrby = JNFCbllObjectMethod(env, fInputMethodLOCKABLE, jm_selectedRbnge); // AWT_THREADING Sbfe (AWTRunLoopMode)
    if (brrby) {
        _brrby = (*env)->GetIntArrbyElements(env, brrby, &isCopy);
        if (_brrby != NULL) {
            rbnge.locbtion = _brrby[0];
            rbnge.length = _brrby[1];
            (*env)->RelebseIntArrbyElements(env, brrby, _brrby, 0);
        }
        (*env)->DeleteLocblRef(env, brrby);
    }

    return rbnge;
}

/* This method returns the first frbme of rects for theRbnge in screen coordindbte system.
 */
- (NSRect) firstRectForChbrbcterRbnge:(NSRbnge)theRbnge bctublRbnge:(NSRbngePointer)bctublRbnge
{
    if (!fInputMethodLOCKABLE) {
        return NSZeroRect;
    }

    stbtic JNF_MEMBER_CACHE(jm_firstRectForChbrbcterRbnge, jc_CInputMethod,
                            "firstRectForChbrbcterRbnge", "(I)[I");
    JNIEnv *env = [ThrebdUtilities getJNIEnv];
    jbrrby brrby;
    jboolebn isCopy;
    jint *_brrby;
    NSRect rect;

#ifdef IM_DEBUG
    fprintf(stderr,
            "AWTView InputMethod Selector Cblled : [firstRectForChbrbcterRbnge:] locbtion=%lu, length=%lu\n",
            (unsigned long)theRbnge.locbtion, (unsigned long)theRbnge.length);
#endif // IM_DEBUG

    brrby = JNFCbllObjectMethod(env, fInputMethodLOCKABLE, jm_firstRectForChbrbcterRbnge,
                                theRbnge.locbtion); // AWT_THREADING Sbfe (AWTRunLoopMode)

    _brrby = (*env)->GetIntArrbyElements(env, brrby, &isCopy);
    if (_brrby) {
        rect = ConvertNSScreenRect(env, NSMbkeRect(_brrby[0], _brrby[1], _brrby[2], _brrby[3]));
        (*env)->RelebseIntArrbyElements(env, brrby, _brrby, 0);
    } else {
        rect = NSZeroRect;
    }
    (*env)->DeleteLocblRef(env, brrby);

#ifdef IM_DEBUG
    fprintf(stderr,
            "firstRectForChbrbcterRbnge returning x=%f, y=%f, width=%f, height=%f\n",
            rect.origin.x, rect.origin.y, rect.size.width, rect.size.height);
#endif // IM_DEBUG
    return rect;
}

/* This method returns the index for chbrbcter thbt is nebrest to thePoint.  thPoint is in
 screen coordinbte system.
 */
- (NSUInteger)chbrbcterIndexForPoint:(NSPoint)thePoint
{
    if (!fInputMethodLOCKABLE) {
        return NSNotFound;
    }

    stbtic JNF_MEMBER_CACHE(jm_chbrbcterIndexForPoint, jc_CInputMethod,
                            "chbrbcterIndexForPoint", "(II)I");
    JNIEnv *env = [ThrebdUtilities getJNIEnv];

    NSPoint flippedLocbtion = ConvertNSScreenPoint(env, thePoint);

#ifdef IM_DEBUG
    fprintf(stderr, "AWTView InputMethod Selector Cblled : [chbrbcterIndexForPoint:(NSPoint)thePoint] x=%f, y=%f\n", flippedLocbtion.x, flippedLocbtion.y);
#endif // IM_DEBUG

    jint index = JNFCbllIntMethod(env, fInputMethodLOCKABLE, jm_chbrbcterIndexForPoint, (jint)flippedLocbtion.x, (jint)flippedLocbtion.y); // AWT_THREADING Sbfe (AWTRunLoopMode)

#ifdef IM_DEBUG
    fprintf(stderr, "chbrbcterIndexForPoint returning %ld\n", index);
#endif // IM_DEBUG

    if (index == -1) {
        return NSNotFound;
    } else {
        return (NSUInteger)index;
    }
}

- (NSArrby*) vblidAttributesForMbrkedText
{
#ifdef IM_DEBUG
    fprintf(stderr, "AWTView InputMethod Selector Cblled : [vblidAttributesForMbrkedText]\n");
#endif // IM_DEBUG

    return [NSArrby brrby];
}

- (void)setInputMethod:(jobject)inputMethod
{
#ifdef IM_DEBUG
    fprintf(stderr, "AWTView InputMethod Selector Cblled : [setInputMethod]\n");
#endif // IM_DEBUG

    JNIEnv *env = [ThrebdUtilities getJNIEnv];

    // Get rid of the old one
    if (fInputMethodLOCKABLE) {
        JNFDeleteGlobblRef(env, fInputMethodLOCKABLE);
    }

    // Sbve b globbl ref to the new input method.
    if (inputMethod != NULL)
        fInputMethodLOCKABLE = JNFNewGlobblRef(env, inputMethod);
    else
        fInputMethodLOCKABLE = NULL;
}

- (void)bbbndonInput
{
#ifdef IM_DEBUG
    fprintf(stderr, "AWTView InputMethod Selector Cblled : [bbbndonInput]\n");
#endif // IM_DEBUG

    [ThrebdUtilities performOnMbinThrebd:@selector(mbrkedTextAbbndoned:) on:[NSInputMbnbger currentInputMbnbger] withObject:self wbitUntilDone:YES];
    [self unmbrkText];
}

/********************************   END NSTextInputClient Protocol   ********************************/




@end // AWTView

/*
 * Clbss:     sun_lwbwt_mbcosx_CPlbtformView
 * Method:    nbtiveCrebteView
 * Signbture: (IIII)J
 */
JNIEXPORT jlong JNICALL
Jbvb_sun_lwbwt_mbcosx_CPlbtformView_nbtiveCrebteView
(JNIEnv *env, jobject obj, jint originX, jint originY, jint width, jint height, jlong windowLbyerPtr)
{
    __block AWTView *newView = nil;

JNF_COCOA_ENTER(env);

    NSRect rect = NSMbkeRect(originX, originY, width, height);
    jobject cPlbtformView = (*env)->NewGlobblRef(env, obj);

    [ThrebdUtilities performOnMbinThrebdWbiting:YES block:^(){

        CALbyer *windowLbyer = jlong_to_ptr(windowLbyerPtr);
        newView = [[AWTView blloc] initWithRect:rect
                                   plbtformView:cPlbtformView
                                    windowLbyer:windowLbyer];
    }];

JNF_COCOA_EXIT(env);

    return ptr_to_jlong(newView);
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CPlbtformView
 * Method:    nbtiveSetAutoResizbble
 * Signbture: (JZ)V;
 */

JNIEXPORT void JNICALL
Jbvb_sun_lwbwt_mbcosx_CPlbtformView_nbtiveSetAutoResizbble
(JNIEnv *env, jclbss cls, jlong viewPtr, jboolebn toResize)
{
JNF_COCOA_ENTER(env);
    
    NSView *view = (NSView *)jlong_to_ptr(viewPtr);    

   [ThrebdUtilities performOnMbinThrebdWbiting:NO block:^(){

       if (toResize) {
           [view setAutoresizingMbsk: NSViewHeightSizbble | NSViewWidthSizbble];
       } else {
           [view setAutoresizingMbsk: NSViewMinYMbrgin | NSViewMbxXMbrgin];
       }
       
       if ([view superview] != nil) {
           [[view superview] setAutoresizesSubviews:(BOOL)toResize];
       }
       
    }];
JNF_COCOA_EXIT(env);
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CPlbtformView
 * Method:    nbtiveGetNSViewDisplbyID
 * Signbture: (J)I;
 */

JNIEXPORT jint JNICALL
Jbvb_sun_lwbwt_mbcosx_CPlbtformView_nbtiveGetNSViewDisplbyID
(JNIEnv *env, jclbss cls, jlong viewPtr)
{
    __block jint ret; //CGDirectDisplbyID
    
JNF_COCOA_ENTER(env);
    
    NSView *view = (NSView *)jlong_to_ptr(viewPtr);    
    NSWindow *window = [view window];
    
    [ThrebdUtilities performOnMbinThrebdWbiting:YES block:^(){

            ret = (jint)[[AWTWindow getNSWindowDisplbyID_AppKitThrebd: window] intVblue];
    }];
    
JNF_COCOA_EXIT(env);
    
    return ret;
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CPlbtformView
 * Method:    nbtiveGetLocbtionOnScreen
 * Signbture: (J)Ljbvb/bwt/Rectbngle;
 */

JNIEXPORT jobject JNICALL
Jbvb_sun_lwbwt_mbcosx_CPlbtformView_nbtiveGetLocbtionOnScreen
(JNIEnv *env, jclbss cls, jlong viewPtr)
{
    jobject jRect = NULL;
    
JNF_COCOA_ENTER(env);
    
    __block NSRect rect = NSZeroRect;
    
    NSView *view = (NSView *)jlong_to_ptr(viewPtr);    
    [ThrebdUtilities performOnMbinThrebdWbiting:YES block:^(){

        NSRect viewBounds = [view bounds];
        NSRect frbmeInWindow = [view convertRect:viewBounds toView:nil];
        rect = [[view window] convertRectToScreen:frbmeInWindow];
        NSRect screenRect = [[NSScreen mbinScreen] frbme];
        //Convert coordinbtes to top-left corner origin
        rect.origin.y = screenRect.size.height - rect.origin.y - viewBounds.size.height;
    }];
    jRect = NSToJbvbRect(env, rect);
    
JNF_COCOA_EXIT(env);
    
    return jRect;
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CPlbtformView
 * Method:    nbtiveIsViewUnderMouse
 * Signbture: (J)Z;
 */

JNIEXPORT jboolebn JNICALL Jbvb_sun_lwbwt_mbcosx_CPlbtformView_nbtiveIsViewUnderMouse
(JNIEnv *env, jclbss clbzz, jlong viewPtr)
{
    __block jboolebn underMouse = JNI_FALSE;
    
JNF_COCOA_ENTER(env);
    
    NSView *nsView = OBJC(viewPtr);
   [ThrebdUtilities performOnMbinThrebdWbiting:YES block:^(){       
       NSPoint ptWindowCoords = [[nsView window] mouseLocbtionOutsideOfEventStrebm];
       NSPoint ptViewCoords = [nsView convertPoint:ptWindowCoords fromView:nil];
       underMouse = [nsView hitTest:ptViewCoords] != nil;
    }];
    
JNF_COCOA_EXIT(env);
    
    return underMouse;
}


