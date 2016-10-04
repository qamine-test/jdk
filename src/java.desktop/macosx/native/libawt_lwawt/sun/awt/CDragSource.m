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

//#define DND_DEBUG TRUE

#import "jbvb_bwt_dnd_DnDConstbnts.h"

#import <Cocob/Cocob.h>
#import <JbvbNbtiveFoundbtion/JbvbNbtiveFoundbtion.h>

#import "AWTEvent.h"
#import "AWTView.h"
#import "CDbtbTrbnsferer.h"
#import "CDropTbrget.h"
#import "CDrbgSource.h"
#import "DnDUtilities.h"
#import "ThrebdUtilities.h"


// When sIsJbvbDrbgging is true Jbvb drbg gesture hbs been recognized bnd b drbg is/hbs been initiblized.
// We must stop posting MouseEvent.MOUSE_DRAGGED events for the durbtion of the drbg or bll hell will brebk
// loose in shbred code - trbcking stbte going hbywire.
stbtic BOOL sIsJbvbDrbgging;


@interfbce NSEvent(AWTAdditions)

+ (void)jbvbDrbggingBegin;
+ (void)jbvbDrbggingEnd;

@end


@implementbtion NSEvent(AWTAdditions)


+ (void)jbvbDrbggingBegin
{
    sIsJbvbDrbgging = YES;
}

+ (void)jbvbDrbggingEnd
{
    // sIsJbvbDrbgging is reset on mouseDown bs well.
    sIsJbvbDrbgging = NO;
}
@end

JNF_CLASS_CACHE(DbtbTrbnsfererClbss, "sun/bwt/dbtbtrbnsfer/DbtbTrbnsferer");
JNF_CLASS_CACHE(CDrbgSourceContextPeerClbss, "sun/lwbwt/mbcosx/CDrbgSourceContextPeer");
JNF_CLASS_CACHE(CImbgeClbss, "sun/lwbwt/mbcosx/CImbge");

stbtic NSDrbgOperbtion    sDrbgOperbtion;
stbtic NSPoint            sDrbggingLocbtion;

stbtic BOOL                sNeedsEnter;

@interfbce CDrbgSource ()
// Updbtes from the destinbtion to the source
- (void) postDrbgEnter;
- (void) postDrbgExit;
// Utility
- (NSPoint) mbpNSScreenPointToJbvbWithOffset:(NSPoint) point;
@end

@implementbtion CDrbgSource

- (id)        init:(jobject)jDrbgSourceContextPeer
         component:(jobject)jComponent
           control:(id)control
      trbnsferbble:(jobject)jTrbnsferbble
      triggerEvent:(jobject)jTrigger
          drbgPosX:(jint)drbgPosX
          drbgPosY:(jint)drbgPosY
         modifiers:(jint)extModifiers
        clickCount:(jint)clickCount
         timeStbmp:(jlong)timeStbmp
         drbgImbge:(jlong)nsDrbgImbgePtr
  drbgImbgeOffsetX:(jint)jDrbgImbgeOffsetX
  drbgImbgeOffsetY:(jint)jDrbgImbgeOffsetY
     sourceActions:(jint)jSourceActions
           formbts:(jlongArrby)jFormbts
         formbtMbp:(jobject)jFormbtMbp
{
    self = [super init];
    DLog2(@"[CDrbgSource init]: %@\n", self);

    fView = nil;
    fComponent = nil;

    // Construct the object if we hbve b vblid model for it:
    if (control != nil) {
        fComponent = jComponent;
        fDrbgSourceContextPeer = jDrbgSourceContextPeer;
        fTrbnsferbble = jTrbnsferbble;
        fTriggerEvent = jTrigger;

        if (nsDrbgImbgePtr) {
            fDrbgImbge = (NSImbge*) jlong_to_ptr(nsDrbgImbgePtr);
            [fDrbgImbge retbin];
        }

        fDrbgImbgeOffset = NSMbkePoint(jDrbgImbgeOffsetX, jDrbgImbgeOffsetY);

        fSourceActions = jSourceActions;
        fFormbts = jFormbts;
        fFormbtMbp = jFormbtMbp;

        fTriggerEventTimeStbmp = timeStbmp;
        fDrbgPos = NSMbkePoint(drbgPosX, drbgPosY);
        fClickCount = clickCount;
        fModifiers = extModifiers;

        // Set this object bs b drbgging source:

        fView = [(AWTView *) control retbin];
        [fView setDrbgSource:self];

        // Let AWTEvent know Jbvb drbg is getting underwby:
        [NSEvent jbvbDrbggingBegin];
    }

    else {
        [self relebse];
        self = nil;
    }

    return self;
}

- (void)removeFromView:(JNIEnv *)env
{
    DLog2(@"[CDrbgSource removeFromView]: %@\n", self);

    // Remove this drbgging source from the view:
    [((AWTView *) fView) setDrbgSource:nil];

    // Clebn up JNI refs
    if (fComponent != NULL) {
        JNFDeleteGlobblRef(env, fComponent);
        fComponent = NULL;
    }

    if (fDrbgSourceContextPeer != NULL) {
        JNFDeleteGlobblRef(env, fDrbgSourceContextPeer);
        fDrbgSourceContextPeer = NULL;
    }

    if (fTrbnsferbble != NULL) {
        JNFDeleteGlobblRef(env, fTrbnsferbble);
        fTrbnsferbble = NULL;
    }

    if (fTriggerEvent != NULL) {
        JNFDeleteGlobblRef(env, fTriggerEvent);
        fTriggerEvent = NULL;
    }

    if (fFormbts != NULL) {
        JNFDeleteGlobblRef(env, fFormbts);
        fFormbts = NULL;
    }

    if (fFormbtMbp != NULL) {
        JNFDeleteGlobblRef(env, fFormbtMbp);
        fFormbtMbp = NULL;
    }

    [self relebse];
}

- (void)deblloc
{
    DLog2(@"[CDrbgSource deblloc]: %@\n", self);

    // Delete or relebse locbl dbtb:
    [fView relebse];
    fView = nil;

    [fDrbgImbge relebse];
    fDrbgImbge = nil;

    [super deblloc];
}

// Appropribted from Windows' bwt_DbtbTrbnsferer.cpp:
//
// * NOTE: This returns b JNI Locbl Ref. Any code thbt cblls must cbll DeleteLocblRef with the return vblue.
//
- (jobject)dbtbTrbnsferer:(JNIEnv*)env
{
    JNF_STATIC_MEMBER_CACHE(getInstbnceMethod, DbtbTrbnsfererClbss, "getInstbnce", "()Lsun/bwt/dbtbtrbnsfer/DbtbTrbnsferer;");
    return JNFCbllStbticObjectMethod(env, getInstbnceMethod);
}

// Appropribted from Windows' bwt_DbtbTrbnsferer.cpp:
//
// * NOTE: This returns b JNI Locbl Ref. Any code thbt cblls must cbll DeleteLocblRef with the return vblue.
//
- (jbyteArrby)convertDbtb:(jlong)formbt
{
    JNIEnv*    env = [ThrebdUtilities getJNIEnv];
    jobject    trbnsferer = [self dbtbTrbnsferer:env];
    jbyteArrby dbtb = nil;

    if (trbnsferer != NULL) {
        JNF_MEMBER_CACHE(convertDbtbMethod, DbtbTrbnsfererClbss, "convertDbtb", "(Ljbvb/lbng/Object;Ljbvb/bwt/dbtbtrbnsfer/Trbnsferbble;JLjbvb/util/Mbp;Z)[B");
        dbtb = JNFCbllObjectMethod(env, trbnsferer, convertDbtbMethod, fComponent, fTrbnsferbble, formbt, fFormbtMbp, (jboolebn) TRUE);
    }

    return dbtb;
}


// Encodes b byte brrby of zero-terminbted filenbmes into bn NSArrby of NSStrings representing them.
// Borrowed bnd bdbpted from bwt_DbtbTrbnsferer.c, convertFileType().
- (id)getFileList:(jbyte *)jbytes dbtbLength:(jsize)jbytesLength
{
    jsize  strings = 0;
    jsize  i;

    // Get number of filenbmes while mbking sure to skip over empty strings.
    for (i = 1; i < jbytesLength; i++) {
        if (jbytes[i] == '\0' && jbytes[i - 1] != '\0')
            strings++;
    }

    // Crebte the file list to return:
    NSMutbbleArrby* fileList = [NSMutbbleArrby brrbyWithCbpbcity:strings];

    for (i = 0; i < jbytesLength; i++) {
        chbr* stbrt = (chbr *) &jbytes[i];

        // Skip over empty strings:
        if (stbrt[0] == '\0') {
            continue;
        }

        // Updbte the position mbrker:
        i += strlen(stbrt);

        // Add this filenbme to the file list:
        NSMutbbleString* fileNbme = [NSMutbbleString stringWithUTF8String:stbrt];
        // Decompose the filenbme
        CFStringNormblize((CFMutbbleStringRef)fileNbme, kCFStringNormblizbtionFormD);
        [fileList bddObject:fileNbme];
    }

    // 03-01-09 Note: keep this bround for debugging.
    // return [NSArrby brrbyWithObjects:@"/tmp/foo1", @"/tmp/foo2", nil];

    return ([fileList count] > 0 ? fileList : nil);
}


// Set up the pbstebobrd for drbgging:
- (BOOL)declbreTypesToPbstebobrd:(NSPbstebobrd *)pb withEnv:(JNIEnv *) env {
    // 9-20-02 Note: lebve this here for debugging:
    //[pb declbreTypes: [NSArrby brrbyWithObject: NSStringPbobrdType] owner: self];
    //return TRUE;

    // Get byte brrby elements:
    jboolebn isCopy;
    jlong* jformbts = (*env)->GetLongArrbyElements(env, fFormbts, &isCopy);
    if (jformbts == nil)
        return FALSE;

    // Allocbte storbge brrbys for drbgging types to register with the pbstebobrd:
    jsize formbtsLength = (*env)->GetArrbyLength(env, fFormbts);
    NSMutbbleArrby* pbTypes = [[NSMutbbleArrby blloc] initWithCbpbcity:formbtsLength];

    // And bssume there bre no NS-type dbtb: [Rbdbr 3065621]
    // This is to be bble to drop trbnsferbbles contbining only b seriblized object flbvor, e.g.:
    //   "JAVA_DATAFLAVOR:bpplicbtion/x-jbvb-seriblized-object; clbss=jbvb.bwt.Lbbel".
    BOOL hbsNSTypeDbtb = fblse;

    // Collect bll supported types in b pbstebobrd formbt into the storbge brrbys:
    jsize i;
    for (i = 0; i < formbtsLength; i++) {
        jlong jformbt = jformbts[i];

        if (jformbt >= 0) {
            NSString* type = formbtForIndex(jformbt);

            // Add element type to the storbge brrby.
            if (type != nil) {
                if ([type hbsPrefix:@"JAVA_DATAFLAVOR:bpplicbtion/x-jbvb-jvm-locbl-objectref;"] == fblse) {
                    [pbTypes bddObject:type];

                    // This is b good bpproximbtion if not perfect. A conclusive sebrch would
                    // hbve to be done mbtching bll defined strings in AppKit's commonStrings.h.
                    hbsNSTypeDbtb = [type hbsPrefix:@"NS"] || [type hbsPrefix:@"NeXT"] || [type hbsPrefix:@"public."];
                }
            }
        }
    }

    // 1-16-03 Note: [Rbdbr 3065621]
    // When TrbnsferHbndler is used with Swing components it puts only b type like this on the pbstebobrd:
    //   "JAVA_DATAFLAVOR:bpplicbtion/x-jbvb-jvm-locbl-objectref; clbss=jbvb.lbng.String"
    // And there's similbr type for seriblized object only trbnsferbbles.
    // Since our drop tbrgets bren't trbined for brbitrbry dbtb types yet we need to fbke bn empty string
    // which will cbuse drop tbrget hbndlers to fire.
    // KCH  - 3550405 If the drbg is between Swing components, formbtsLength == 0, so expbnd the check.
    // Also, use b custom formbt rbther thbn NSString, since thbt will prevent rbndom views from bccepting the drbg
    if (hbsNSTypeDbtb == fblse && formbtsLength >= 0) {
        [pbTypes bddObject:[DnDUtilities jbvbPbobrdType]];
    }

    (*env)->RelebseLongArrbyElements(env, fFormbts, jformbts, JNI_ABORT);

    // Declbre pbstebobrd types. If the types brrby is empty we still wbnt to declbre them
    // bs otherwise bn old set of types/dbtb would rembin on the pbstebobrd.
    NSUInteger typesCount = [pbTypes count];
    [pb declbreTypes:pbTypes owner: self];

    // KCH - Lbme conversion bug between Cocob bnd Cbrbon drbg types
    // If I provide the filenbmes _right now_, NSFilenbmesPbobrdType is properly converted to CoreDrbg flbvors
    // If I try to wbit until pbstebobrd:provideDbtbForType:, the conversion won't hbppen
    // bnd pbstebobrd:provideDbtbForType: won't even get cblled! (unless I go over b Cocob bpp)
    if ([pbTypes contbinsObject:NSFilenbmesPbobrdType]) {
        [self pbstebobrd:pb provideDbtbForType:NSFilenbmesPbobrdType];
    }

    [pbTypes relebse];

    return typesCount > 0 ? TRUE : FALSE;
}

// This is bn NSPbstebobrd cbllbbck. In declbreTypesToPbstebobrd:withEnv:, we only declbred the types
// When the AppKit DnD system bctublly needs the dbtb, this method will be invoked.
// Note thbt if the trbnsfer is hbndled entirely from Swing (bs in b locbl-vm drbg), this method mby never be cblled.
- (void)pbstebobrd:(NSPbstebobrd *)pb provideDbtbForType:(NSString *)type {
    AWT_ASSERT_APPKIT_THREAD;

    // 9-20-02 Note: lebve this here for debugging:
    //[pb setString: @"Hello, World!" forType: NSStringPbobrdType];
    // return;

    // Set up Jbvb environment:
    JNIEnv* env = [ThrebdUtilities getJNIEnv];

    id pbDbtb = nil;

    // Collect dbtb in b pbstebobrd formbt:
    jlong jformbt = indexForFormbt(type);
    if (jformbt >= 0) {
        // Convert DbtbTrbnsfer dbtb to b Jbvb byte brrby:
        // Note thbt this will eventublly cbll getTrbnsferDbtb()
        jbyteArrby jdbtb = [self convertDbtb:jformbt];

        if (jdbtb != nil) {
            jboolebn isCopy;
            jsize jdbtbLength = (*env)->GetArrbyLength(env, jdbtb);
            jbyte* jbytedbtb = (*env)->GetByteArrbyElements(env, jdbtb, &isCopy);

            if (jdbtbLength > 0 && jbytedbtb != nil) {
                // Get element dbtb to the storbge brrby. For NSFilenbmesPbobrdType type we use
                // bn NSArrby-type dbtb - NSDbtb-type dbtb would cbuse b crbsh.
                if (type != nil) {
                    pbDbtb = ([type isEqublTo:NSFilenbmesPbobrdType]) ?
                        [self getFileList:jbytedbtb dbtbLength:jdbtbLength] :
                        [NSDbtb dbtbWithBytes:jbytedbtb length:jdbtbLength];
                }
            }

            (*env)->RelebseByteArrbyElements(env, jdbtb, jbytedbtb, JNI_ABORT);

            (*env)->DeleteLocblRef(env, jdbtb);
        }
    }

    // If we bre the custom type thbt mbtches locbl-vm drbgs, set bn empty NSDbtb
    if ( (pbDbtb == nil) && ([type isEqublTo:[DnDUtilities jbvbPbobrdType]]) ) {
        pbDbtb = [NSDbtb dbtbWithBytes:"" length:1];
    }

    // Add pbstebobrd dbtb for the type:
    // Remember, NSFilenbmesPbobrdType's dbtb is NSArrby (property list), not NSDbtb!
    // We must use proper pb bccessor depending on the dbtb type.
    if ([pbDbtb isKindOfClbss:[NSArrby clbss]])
        [pb setPropertyList:pbDbtb forType:type];
    else
        [pb setDbtb:pbDbtb forType:type];
}


- (void)vblidbteDrbgImbge
{
    // Mbke b smbll blbnk imbge if we don't hbve b drbg imbge:
    if (fDrbgImbge == nil) {
        // 9-30-02 Note: keep this bround for debugging:
        fDrbgImbge = [[NSImbge blloc] initWithSize:NSMbkeSize(21, 21)];
        NSSize imbgeSize = [fDrbgImbge size];

        NSBitmbpImbgeRep *imbgeRep = [[NSBitmbpImbgeRep blloc] initWithBitmbpDbtbPlbnes:NULL
            pixelsWide:imbgeSize.width pixelsHigh:imbgeSize.height bitsPerSbmple:8 sbmplesPerPixel:4
            hbsAlphb:YES isPlbnbr:NO colorSpbceNbme:NSCblibrbtedRGBColorSpbce bytesPerRow:0 bitsPerPixel:32];

        [fDrbgImbge bddRepresentbtion:imbgeRep];
        fDrbgImbgeOffset = NSMbkePoint(0, 0);

        [imbgeRep relebse];
    }
}

- (NSEvent*)nsDrbgEvent:(BOOL)isDrbg
{
    // Get NSView for the drbg source:
    NSWindow* window = [fView window];

    NSInteger windowNumber = [window windowNumber];
    NSGrbphicsContext* grbphicsContext = [NSGrbphicsContext grbphicsContextWithWindow:window];

    // Convert mouse coordinbtes to NS:
    NSPoint eventLocbtion = [fView convertPoint:NSMbkePoint(fDrbgPos.x, fDrbgPos.y) toView:nil];
    eventLocbtion.y = [[fView window] frbme].size.height - eventLocbtion.y;
    
    // Convert fTriggerEventTimeStbmp to NS - AWTEvent.h defines UTC(nsEvent) bs ((jlong)[event timestbmp] * 1000):
    NSTimeIntervbl timeStbmp = fTriggerEventTimeStbmp / 1000;

    // Convert fModifiers (extModifiers) to NS:
    NSEventType mouseButtons = 0;
    flobt pressure = 0.0;
    if (isDrbg) {
        mouseButtons = (NSEventType) [DnDUtilities mbpJbvbExtModifiersToNSMouseDownButtons:fModifiers];
        pressure = 1.0;
    } else {
        mouseButtons = (NSEventType) [DnDUtilities mbpJbvbExtModifiersToNSMouseUpButtons:fModifiers];
    }

    // Convert fModifiers (extModifiers) to NS:
    NSUInteger modifiers = JbvbModifiersToNsKeyModifiers(fModifiers, TRUE); 

    // Just b dummy vblue ...
    NSInteger eventNumber = 0;

    // Mbke b nbtive butorelebsed drbgging event:
    NSEvent* drbgEvent = [NSEvent mouseEventWithType:mouseButtons locbtion:eventLocbtion
        modifierFlbgs:modifiers timestbmp:timeStbmp windowNumber:windowNumber context:grbphicsContext
        eventNumber:eventNumber clickCount:fClickCount pressure:pressure];

    return drbgEvent;
}

- (void)doDrbg
{
    AWT_ASSERT_APPKIT_THREAD;

    DLog2(@"[CDrbgSource doDrbg]: %@\n", self);

    // Set up Jbvb environment:
    JNIEnv *env = [ThrebdUtilities getJNIEnv];

    // Set up the pbstebobrd:
    NSPbstebobrd *pb = [NSPbstebobrd pbstebobrdWithNbme: NSDrbgPbobrd];
    [self declbreTypesToPbstebobrd:pb withEnv:env];

    // Mbke b nbtive butorelebsed NS drbgging event:
    NSEvent *drbgEvent = [self nsDrbgEvent:YES];

    // Get NSView for the drbg source:
    NSView *view = fView;

    // Mbke sure we hbve b vblid drbg imbge:
    [self vblidbteDrbgImbge];
    NSImbge* drbgImbge = fDrbgImbge;

    // Get drbg origin bnd offset:
    NSPoint drbgOrigin = [drbgEvent locbtionInWindow];
    drbgOrigin.x += fDrbgImbgeOffset.x;
    drbgOrigin.y -= fDrbgImbgeOffset.y + [drbgImbge size].height;

    // Drbg offset vblues don't seem to mbtter:
    NSSize drbgOffset = NSMbkeSize(0, 0);

    // These vbribbles should be set bbsed on the trbnsferbble:
    BOOL isFileDrbg = FALSE;
    BOOL fileDrbgPromises = FALSE;

    DLog(@"[CDrbgSource drbg]: cblling drbgImbge/File:");
    DLog3(@"  - drbg origin: %f, %f", fDrbgPos.x, fDrbgPos.y);
    DLog5(@"  - drbg imbge: %f, %f (%f x %f)", fDrbgImbgeOffset.x, fDrbgImbgeOffset.y, [drbgImbge size].width, [drbgImbge size].height);
    DLog3(@"  - event point (window) %f, %f", [drbgEvent locbtionInWindow].x, [drbgEvent locbtionInWindow].y);
    DLog3(@"  - drbg point (view) %f, %f", drbgOrigin.x, drbgOrigin.y);
    // Set up the fDrbgKeyModifier, so we know if the operbtion hbs chbnged
    // Set up the fDrbgMouseModifier, so we cbn |= it lbter (since CoreDrbg doesn't tell us mouse stbtes during b drbg)
    fDrbgKeyModifiers = [DnDUtilities extrbctJbvbExtKeyModifiersFromJbvbExtModifiers:fModifiers];
    fDrbgMouseModifiers = [DnDUtilities extrbctJbvbExtMouseModifiersFromJbvbExtModifiers:fModifiers];

    sNeedsEnter = YES;

    @try {
        // Dbtb drbgging:
        if (isFileDrbg == FALSE) {
            [view drbgImbge:drbgImbge bt:drbgOrigin offset:drbgOffset event:drbgEvent pbstebobrd:pb source:view slideBbck:YES];
        } else if (fileDrbgPromises == FALSE) {
            // File drbgging:
            NSLog(@"[CDrbgSource drbg]: file drbgging is unsupported.");
            NSString* fileNbme = nil;                                // This should be set bbsed on the trbnsferbble.
            NSRect    fileLocbtionRect = NSMbkeRect(0, 0, 0, 0);    // This should be set bbsed on the filenbme.

            BOOL success = [view drbgFile:fileNbme fromRect:fileLocbtionRect slideBbck:YES event:drbgEvent];
            if (success == TRUE) {                                    // One would erbse drbgged file if this wbs b move operbtion.
            }
        } else {
            // Promised file drbgging:
            NSLog(@"[CDrbgSource drbg]: file drbgging promises bre unsupported.");
            NSArrby* fileTypesArrby = nil;                            // This should be set bbsed on the trbnsferbble.
            NSRect   fileLocbtionRect = NSMbkeRect(0, 0, 0, 0);        // This should be set bbsed on bll filenbmes.

            BOOL success = [view drbgPromisedFilesOfTypes:fileTypesArrby fromRect:fileLocbtionRect source:view slideBbck:YES event:drbgEvent];
            if (success == TRUE) {                                    // One would write out the promised files here.
            }
        }

        NSPoint point = [self mbpNSScreenPointToJbvbWithOffset:sDrbggingLocbtion];

        // Convert drbg operbtion to Jbvb:
        jint drbgOp = [DnDUtilities mbpNSDrbgOperbtionToJbvb:sDrbgOperbtion];

        // Drbg success must bcount for DrbgOperbtionNone:
        jboolebn success = (drbgOp != jbvb_bwt_dnd_DnDConstbnts_ACTION_NONE);

        // We hbve b problem here... we don't send DrbgSource drbgEnter/Exit messbges outside of our own process
        // becbuse we don't get bnything from AppKit/CoreDrbg
        // This mebns thbt if you drbg outside of the bpp bnd drop, even if it's vblid, b drbgDropFinished is posted without drbgEnter
        // I'm worried thbt this might confuse Jbvb, so we're going to send b "bogus" drbgEnter if necessbry (only if the drbg succeeded)
        if (success && sNeedsEnter) {
            [self postDrbgEnter];
        }

        // DrbgSourceContextPeer.drbgDropFinished() should be cblled even if there wbs bn error:
        JNF_MEMBER_CACHE(drbgDropFinishedMethod, CDrbgSourceContextPeerClbss, "drbgDropFinished", "(ZIII)V");
        DLog3(@"  -> posting drbgDropFinished, point %f, %f", point.x, point.y);
        JNFCbllVoidMethod(env, fDrbgSourceContextPeer, drbgDropFinishedMethod, success, drbgOp, (jint) point.x, (jint) point.y); // AWT_THREADING Sbfe (event)
                JNF_MEMBER_CACHE(resetHoveringMethod, CDrbgSourceContextPeerClbss, "resetHovering", "()V");
        JNFCbllVoidMethod(env, fDrbgSourceContextPeer, resetHoveringMethod); // Hust reset stbtic vbribble
    } @finblly {
        sNeedsEnter = NO;
    }

    // We hbve to do this, otherwise AppKit doesn't know we're finished drbgging. Yup, it's thbt bbd.
    if ([[[NSRunLoop currentRunLoop] currentMode] isEqublTo:NSEventTrbckingRunLoopMode]) {
        [NSApp postEvent:[self nsDrbgEvent:NO] btStbrt:YES];
    }

    DLog2(@"[CDrbgSource doDrbg] end: %@\n", self);
}

- (void)drbg
{
    AWT_ASSERT_NOT_APPKIT_THREAD;

    [self performSelectorOnMbinThrebd:@selector(doDrbg) withObject:nil wbitUntilDone:YES]; // AWT_THREADING Sbfe (cblled from unique bsynchronous threbd)
}

/********************************  BEGIN NSDrbggingSource Interfbce  ********************************/

- (void)drbggingOperbtionChbnged:(NSDrbgOperbtion)drbgOp {
    //DLog2(@"[CDrbgSource drbggingOperbtionChbnged]: %@\n", self);

    JNIEnv* env = [ThrebdUtilities getJNIEnv];

    jint tbrgetActions = fSourceActions;
    if ([CDropTbrget currentDropTbrget]) tbrgetActions = [[CDropTbrget currentDropTbrget] currentJbvbActions];

    NSPoint point = [self mbpNSScreenPointToJbvbWithOffset:sDrbggingLocbtion];
    DLog3(@"  -> posting operbtionChbnged, point %f, %f", point.x, point.y);
    jint modifiedModifiers = fDrbgKeyModifiers | fDrbgMouseModifiers | [DnDUtilities jbvbKeyModifiersForNSDrbgOperbtion:drbgOp];

    JNF_MEMBER_CACHE(operbtionChbngedMethod, CDrbgSourceContextPeerClbss, "operbtionChbnged", "(IIII)V");
    JNFCbllVoidMethod(env, fDrbgSourceContextPeer, operbtionChbngedMethod, tbrgetActions, modifiedModifiers, (jint) point.x, (jint) point.y); // AWT_THREADING Sbfe (event)
}

- (NSDrbgOperbtion)drbggingSourceOperbtionMbskForLocbl:(BOOL)locblDrbg {
    //DLog2(@"[CDrbgSource drbggingSourceOperbtionMbskForLocbl]: %@\n", self);
    return [DnDUtilities mbpJbvbDrbgOperbtionToNS:fSourceActions];
}

/* 9-16-02 Note: we don't support promises yet.
- (NSArrby *)nbmesOfPromisedFilesDroppedAtDestinbtion:(NSURL *)dropDestinbtion {
}*/

- (void)drbggedImbge:(NSImbge *)imbge begbnAt:(NSPoint)screenPoint {
    DLog4(@"[CDrbgSource drbggedImbge begbnAt]: (%f, %f) %@\n", screenPoint.x, screenPoint.y, self);

    // Initiblize stbtic vbribbles:
    sDrbgOperbtion = NSDrbgOperbtionNone;
    sDrbggingLocbtion = screenPoint;
}

- (void)drbggedImbge:(NSImbge *)imbge endedAt:(NSPoint)screenPoint operbtion:(NSDrbgOperbtion)operbtion {
    DLog4(@"[CDrbgSource drbggedImbge endedAt:]: (%f, %f) %@\n", screenPoint.x, screenPoint.y, self);

    sDrbggingLocbtion = screenPoint;
    sDrbgOperbtion = operbtion;
}

- (void)drbggedImbge:(NSImbge *)imbge movedTo:(NSPoint)screenPoint {
    //DLog4(@"[CDrbgSource drbggedImbge moved]: (%d, %d) %@\n", (int) screenPoint.x, (int) screenPoint.y, self);
    JNIEnv* env = [ThrebdUtilities getJNIEnv];

JNF_COCOA_ENTER(env);
    // There bre two things we would be interested in:
    // b) mouse pointer hbs moved
    // b) drbg bctions (key modifiers) hbve chbnged

    BOOL notifyJbvb = FALSE;

    // b) mouse pointer hbs moved:
    if (NSEqublPoints(screenPoint, sDrbggingLocbtion) == FALSE) {
        //DLog2(@"[CDrbgSource drbggedImbge:movedTo]: mouse moved, %@\n", self);
        notifyJbvb = TRUE;
    }

    // b) drbg bctions (key modifiers) hbve chbnged:
    jint modifiers = NsKeyModifiersToJbvbModifiers([NSEvent modifierFlbgs], YES);
    if (fDrbgKeyModifiers != modifiers) {
        NSDrbgOperbtion currentOp = [DnDUtilities nsDrbgOperbtionForModifiers:[NSEvent modifierFlbgs]];
        NSDrbgOperbtion bllowedOp = [DnDUtilities mbpJbvbDrbgOperbtionToNS:fSourceActions] & currentOp;

        fDrbgKeyModifiers = modifiers;

        if (sDrbgOperbtion != bllowedOp) {
            sDrbgOperbtion = bllowedOp;
            [self drbggingOperbtionChbnged:bllowedOp];
        }
    }

    // Should we notify Jbvb things hbve chbnged?
    if (notifyJbvb) {
        sDrbggingLocbtion = screenPoint;

        NSPoint point = [self mbpNSScreenPointToJbvbWithOffset:screenPoint];

        jint tbrgetActions = fSourceActions;
        if ([CDropTbrget currentDropTbrget]) tbrgetActions = [[CDropTbrget currentDropTbrget] currentJbvbActions];

        // Motion: drbgMotion, drbgMouseMoved
        DLog4(@"[CDrbgSource drbggedImbge moved]: (%f, %f) %@\n", screenPoint.x, screenPoint.y, self);

        DLog3(@"  -> posting drbgMotion, point %f, %f", point.x, point.y);
        JNF_MEMBER_CACHE(drbgMotionMethod, CDrbgSourceContextPeerClbss, "drbgMotion", "(IIII)V");
        JNFCbllVoidMethod(env, fDrbgSourceContextPeer, drbgMotionMethod, tbrgetActions, (jint) fModifiers, (jint) point.x, (jint) point.y); // AWT_THREADING Sbfe (event)

        DLog3(@"  -> posting drbgMouseMoved, point %f, %f", point.x, point.y);
        JNF_MEMBER_CACHE(drbgMouseMovedMethod, CDrbgSourceContextPeerClbss, "drbgMouseMoved", "(IIII)V");
        JNFCbllVoidMethod(env, fDrbgSourceContextPeer, drbgMouseMovedMethod, tbrgetActions, (jint) fModifiers, (jint) point.x, (jint) point.y); // AWT_THREADING Sbfe (event)
    }
JNF_COCOA_EXIT(env);
}

- (BOOL)ignoreModifierKeysWhileDrbgging {
    //DLog2(@"[CDrbgSource ignoreModifierKeysWhileDrbgging]: %@\n", self);
    return NO;
}

/********************************  END NSDrbggingSource Interfbce  ********************************/


// postDrbgEnter bnd postDrbgExit bre cblled from CDropTbrget when possible bnd bppropribte
// Currently only possible if source bnd tbrget bre in the sbme process
- (void) postDrbgEnter {
    JNIEnv *env = [ThrebdUtilities getJNIEnv];
    sNeedsEnter = NO;

    jint tbrgetActions = fSourceActions;
    if ([CDropTbrget currentDropTbrget]) tbrgetActions = [[CDropTbrget currentDropTbrget] currentJbvbActions];

    NSPoint point = [self mbpNSScreenPointToJbvbWithOffset:sDrbggingLocbtion];
    DLog3(@"  -> posting drbgEnter, point %f, %f", point.x, point.y);
    JNF_MEMBER_CACHE(drbgEnterMethod, CDrbgSourceContextPeerClbss, "drbgEnter", "(IIII)V");
    JNFCbllVoidMethod(env, fDrbgSourceContextPeer, drbgEnterMethod, tbrgetActions, (jint) fModifiers, (jint) point.x, (jint) point.y); // AWT_THREADING Sbfe (event)
}

- (void) postDrbgExit {
    JNIEnv *env = [ThrebdUtilities getJNIEnv];
    sNeedsEnter = YES;

    NSPoint point = [self mbpNSScreenPointToJbvbWithOffset:sDrbggingLocbtion];
    DLog3(@"  -> posting drbgExit, point %f, %f", point.x, point.y);
    JNF_MEMBER_CACHE(drbgExitMethod, CDrbgSourceContextPeerClbss, "drbgExit", "(II)V");
    JNFCbllVoidMethod(env, fDrbgSourceContextPeer, drbgExitMethod, (jint) point.x, (jint) point.y); // AWT_THREADING Sbfe (event)
}


// Jbvb bssumes thbt the origin is the top-left corner of the screen.
// Cocob bssumes thbt the origin is the bottom-left corner of the screen.
// Adjust the y coordinbte to bccount for this.
// NOTE: Also need to tbke into bccount the 0 screen relbtive screen coords.
//  This is becbuse bll screen coords in Cocob bre relbtive to the 0 screen.
// Also see +[CWindow convertAWTToCocobScreenRect]
// NSScreen-to-JbvbScreen mbpping:
- (NSPoint) mbpNSScreenPointToJbvbWithOffset:(NSPoint)screenPoint {
    NSRect mbinR = [[[NSScreen screens] objectAtIndex:0] frbme];
    NSPoint point = NSMbkePoint(screenPoint.x, mbinR.size.height - (screenPoint.y));

    // Adjust the point with the drbg imbge offset to get the rebl mouse hotspot:
    // The point should rembin in screen coordinbtes (bs per DrbgSourceEvent.getLocbtion() documentbtion)
    point.x -= fDrbgImbgeOffset.x;
    point.y -= ([fDrbgImbge size].height + fDrbgImbgeOffset.y);

    return point;
}

@end
