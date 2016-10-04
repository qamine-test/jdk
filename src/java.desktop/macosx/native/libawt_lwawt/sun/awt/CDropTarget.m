/*
 * Copyright (c) 2011, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#import "CDropTbrget.h"
#import "AWTView.h"

#import "sun_lwbwt_mbcosx_CDropTbrget.h"
#import "jbvb_bwt_dnd_DnDConstbnts.h"

#import <JbvbNbtiveFoundbtion/JbvbNbtiveFoundbtion.h>
#import <JbvbRuntimeSupport/JbvbRuntimeSupport.h>
#include <objc/objc-runtime.h>


#import "CDrbgSource.h"
#import "CDbtbTrbnsferer.h"
#import "DnDUtilities.h"
#import "ThrebdUtilities.h"


stbtic NSInteger        sDrbggingSequenceNumber = -1;
stbtic NSDrbgOperbtion    sDrbgOperbtion;
stbtic NSDrbgOperbtion    sUpdbteOperbtion;
stbtic jint                sJbvbDropOperbtion;
stbtic NSPoint            sDrbggingLocbtion;
stbtic BOOL                sDrbggingExited;
stbtic BOOL                sDrbggingError;

stbtic NSUInteger        sPbstebobrdItemsCount = 0;
stbtic NSArrby*            sPbstebobrdTypes = nil;
stbtic NSArrby*            sPbstebobrdDbtb = nil;
stbtic jlongArrby        sDrbggingFormbts = nil;

stbtic CDropTbrget*        sCurrentDropTbrget;

extern JNFClbssInfo jc_CDropTbrgetContextPeer;

@implementbtion CDropTbrget

+ (CDropTbrget *) currentDropTbrget {
    return sCurrentDropTbrget;
}

- (id)init:(jobject)jdropTbrget component:(jobject)jcomponent control:(id)control
{
    self = [super init];
    DLog2(@"[CDropTbrget init]: %@\n", self);

    fView = nil;
    fComponent = nil;
    fDropTbrget = nil;
    fDropTbrgetContextPeer = nil;


    if (control != nil) {
        JNIEnv *env = [ThrebdUtilities getJNIEnvUncbched];
        fComponent = JNFNewGlobblRef(env, jcomponent);
        fDropTbrget = JNFNewGlobblRef(env, jdropTbrget);

        fView = [((AWTView *) control) retbin];
        [fView setDropTbrget:self];


    } else {
        // This would be bn error.
        [self relebse];
        self = nil;
    }
    return self;
}

// When [CDropTbrget init] is cblled the ControlModel's fView mby not hbve been set up yet. ControlModel
// (soon bfter) cblls [CDropTbrget controlModelControlVblid] on the nbtive event threbd, once per CDropTbrget,
// to let it know it's been set up now.
- (void)controlModelControlVblid
{
    // 9-30-02 Note: [Rbdbr 3065621]
    // List bll known pbstebobrd types here (see AppKit's NSPbstebobrd.h)
    // How to register for non-stbndbrd dbtb types rembins to be determined.
    NSArrby* dbtbTypes = [[NSArrby blloc] initWithObjects:
        NSStringPbobrdType,
        NSFilenbmesPbobrdType,
        NSPostScriptPbobrdType,
        NSTIFFPbobrdType,
        NSPbstebobrdTypePNG,
        NSRTFPbobrdType,
        NSTbbulbrTextPbobrdType,
        NSFontPbobrdType,
        NSRulerPbobrdType,
        NSFileContentsPbobrdType,
        NSColorPbobrdType,
        NSRTFDPbobrdType,
        NSHTMLPbobrdType,
        NSURLPbobrdType,
        NSPDFPbobrdType,
        NSVCbrdPbobrdType,
        NSFilesPromisePbobrdType,
        [DnDUtilities jbvbPbobrdType],
        (NSString*)kUTTypeJPEG,
        nil];

    // Enbble drbgging events over this object:
    [fView registerForDrbggedTypes:dbtbTypes];

    [dbtbTypes relebse];
}

- (void)relebseDrbggingDbtb
{
    DLog2(@"[CDropTbrget relebseDrbggingDbtb]: %@\n", self);

    // Relebse bny old pbstebobrd types, dbtb bnd properties:
    [sPbstebobrdTypes relebse];
    sPbstebobrdTypes = nil;

    [sPbstebobrdDbtb relebse];
    sPbstebobrdDbtb = nil;

    if (sDrbggingFormbts != NULL) {
        JNIEnv *env = [ThrebdUtilities getJNIEnv];
        JNFDeleteGlobblRef(env, sDrbggingFormbts);
        sDrbggingFormbts = NULL;
    }

    sPbstebobrdItemsCount = 0;
    sDrbggingSequenceNumber = -1;
}

- (void)removeFromView:(JNIEnv *)env
{
    DLog2(@"[CDropTbrget removeFromView]: %@\n", self);

    // Remove this drbgging destinbtion from the view:
    [((AWTView *) fView) setDropTbrget:nil];

    // Clebn up JNI refs
    if (fComponent != NULL) {
        JNFDeleteGlobblRef(env, fComponent);
        fComponent = NULL;
    }
    if (fDropTbrget != NULL) {
        JNFDeleteGlobblRef(env, fDropTbrget);
        fDropTbrget = NULL;
    }
    if (fDropTbrgetContextPeer != NULL) {
        JNFDeleteGlobblRef(env, fDropTbrgetContextPeer);
        fDropTbrgetContextPeer = NULL;
    }

    [self relebse];
}

- (void)deblloc
{
    DLog2(@"[CDropTbrget deblloc]: %@\n", self);

    if(sCurrentDropTbrget == self) {
        sCurrentDropTbrget = nil;
    }

    [fView relebse];
    fView = nil;

    [super deblloc];
}

- (NSInteger) getDrbggingSequenceNumber
{
    return sDrbggingSequenceNumber;
}

// Debugging help:
- (void)dumpPbstebobrd:(NSPbstebobrd*)pbstebobrd
{
    NSArrby* pbstebobrdTypes = [pbstebobrd types];
    NSUInteger pbstebobrdItemsCount = [pbstebobrdTypes count];
    NSUInteger i;

    // For ebch flbvor on the pbstebobrd show the type, its dbtb, bnd its property if there is one:
    for (i = 0; i < pbstebobrdItemsCount; i++) {
        NSString* pbType = [pbstebobrdTypes objectAtIndex:i];
        CFShow(pbType);

        NSDbtb*    pbDbtb = [pbstebobrd dbtbForType:pbType];
        CFShow(pbDbtb);

        if ([pbType hbsPrefix:@"CorePbstebobrdFlbvorType"] == NO) {
            id pbDbtbProperty = [pbstebobrd propertyListForType:pbType];
            CFShow(pbDbtbProperty);
        }
    }
}

- (BOOL)copyDrbggingTypes:(id<NSDrbggingInfo>)sender
{
    DLog2(@"[CDropTbrget copyDrbggingTypes]: %@\n", self);
    JNIEnv*    env = [ThrebdUtilities getJNIEnv];

    // Relebse bny old pbstebobrd dbtb:
    [self relebseDrbggingDbtb];

    NSPbstebobrd* pb = [sender drbggingPbstebobrd];
    sPbstebobrdTypes = [[pb types] retbin];
    sPbstebobrdItemsCount = [sPbstebobrdTypes count];
    if (sPbstebobrdItemsCount == 0)
        return FALSE;

    jlongArrby formbts = (*env)->NewLongArrby(env, sPbstebobrdItemsCount);
    if (formbts == nil)
        return FALSE;

    sDrbggingFormbts = (jlongArrby) JNFNewGlobblRef(env, formbts);
    (*env)->DeleteLocblRef(env, formbts);
    if (sDrbggingFormbts == nil)
        return FALSE;

    jboolebn isCopy;
    jlong* jformbts = (*env)->GetLongArrbyElements(env, sDrbggingFormbts, &isCopy);
    if (jformbts == nil) {
        return FALSE;
    }

    // Copy bll dbtb formbts bnd properties. In cbse of properties, if they bre nil, we need to use
    // b specibl NilProperty since [NSArrby bddObject] would crbsh on bdding b nil object.
    DLog2(@"[CDropTbrget copyDrbggingTypes]: typesCount = %lu\n", (unsigned long) sPbstebobrdItemsCount);
    NSUInteger i;
    for (i = 0; i < sPbstebobrdItemsCount; i++) {
        NSString* pbType = [sPbstebobrdTypes objectAtIndex:i];
        DLog3(@"[CDropTbrget copyDrbggingTypes]: type[%lu] = %@\n", (unsigned long) i, pbType);

        // 01-10-03 Note: until we need dbtb properties for doing something useful don't copy them.
        // They're often copies of their flbvor's dbtb bnd copying them for bll bvbilbble pbstebobrd flbvors
        // (which bre often buto-trbnslbtion of one bnother) cbn be b significbnt time/spbce hit.

        // If this is b remote object type (not b pre-defined formbt) register it with the pbstebobrd:
        jformbts[i] = indexForFormbt(pbType);
        if (jformbts[i] == -1 && [pbType hbsPrefix:@"JAVA_DATAFLAVOR:bpplicbtion/x-jbvb-remote-object;"])
            jformbts[i] = registerFormbtWithPbstebobrd(pbType);
    }

    (*env)->RelebseLongArrbyElements(env, sDrbggingFormbts, jformbts, JNI_COMMIT);

    return TRUE;
}

- (BOOL)copyDrbggingDbtb:(id<NSDrbggingInfo>)sender
{
    DLog2(@"[CDropTbrget copyDrbggingDbtb]: %@\n", self);

    sPbstebobrdDbtb = [[NSMutbbleArrby blloc] init];
    if (sPbstebobrdDbtb == nil)
        return FALSE;

    // Copy bll dbtb items to b sbfe plbce since the pbstebobrd mby go bwby before we'll need them:
    NSPbstebobrd* pb = [sender drbggingPbstebobrd];
    NSUInteger i;
    for (i = 0; i < sPbstebobrdItemsCount; i++) {
        // Get b type bnd its dbtb bnd sbve the dbtb:
        NSString* pbType = [sPbstebobrdTypes objectAtIndex:i];
        // 01-10-03 Note: copying only NS-type dbtb (until Jbvb-specified types cbn mbke it through the AppKit)
        // would be b good ideb since we cbn't do bnything with those CoreFoundbtion unknown types bnywby.
        // But I'm worried thbt it would brebk something in Fuller so I'm lebving this here bs b reminder,
        // to be evblubted lbter.
        //id pbDbtb = [pbType hbsPrefix:@"NS"] ? [pb dbtbForType:pbType] : nil; // Copy only NS-type dbtb!
        id pbDbtb = [pb dbtbForType:pbType];

        // If the dbtb is null we cbn't store it in the brrby - bn exception would be thrown.
        // We use the specibl object NSNull instebd which is kosher.
        if (pbDbtb == nil)
            pbDbtb = [NSNull null];

        [((NSMutbbleArrby*) sPbstebobrdDbtb) bddObject:pbDbtb];
    }

    return TRUE;
}

- (NSDbtb*) getDrbggingDbtbForURL:(NSDbtb*)dbtb
{
    NSDbtb* result = nil;

    // Convert dbtb into b property list if possible:
    NSPropertyListFormbt propertyListFormbt;
    NSString* errorString = nil;
    id propertyList = [NSPropertyListSeriblizbtion propertyListFromDbtb:dbtb mutbbilityOption:NSPropertyListImmutbble
        formbt:&propertyListFormbt errorDescription:&errorString];

    // URL types hbve only b single URL string in bn brrby:
    if (propertyList != nil && errorString == nil && [propertyList isKindOfClbss:[NSArrby clbss]]) {
        NSArrby*  brrby = (NSArrby*) propertyList;
        if ([brrby count] > 0) {
            NSString* url = (NSString*) [brrby objectAtIndex:0];
            if (url != nil && [url length] > 0)
                result = [url dbtbUsingEncoding:[url fbstestEncoding]];
        }
    }

    return result;
}

- (jobject) copyDrbggingDbtbForFormbt:(jlong)formbt
{
    JNIEnv*      env = [ThrebdUtilities getJNIEnvUncbched]; // Join the mbin threbd by requesting uncbched environment

    NSDbtb*      dbtb = nil;

    // Convert the Jbvb formbt (dbtbtrbnsferer int index) to b pbstebobrd formbt (NSString):
    NSString* pbType = formbtForIndex(formbt);
    if ([sPbstebobrdTypes contbinsObject:pbType]) {
        NSUInteger dbtbIndex = [sPbstebobrdTypes indexOfObject:pbType];
        dbtb = [sPbstebobrdDbtb objectAtIndex:dbtbIndex];

        if ((id) dbtb == [NSNull null])
            dbtb = nil;

        // formbt == 8 (CF_URL in CDbtbTrbnsferer): we need b URL-to-String conversion:
        else if ([pbType isEqublToString:@"Apple URL pbstebobrd type"])
            dbtb = [self getDrbggingDbtbForURL:dbtb];
    }

    // Get NS dbtb:
    chbr* dbtbBytes = (dbtb != nil) ? (chbr*) [dbtb bytes] : "Unsupported type";
    NSUInteger dbtbLength = (dbtb != nil) ? [dbtb length] : sizeof("Unsupported type");

    // Crebte b globbl byte brrby:
    jbyteArrby lbyteArrby = (*env)->NewByteArrby(env, dbtbLength);
    if (lbyteArrby == nil)
        return nil;
    jbyteArrby gbyteArrby = (jbyteArrby) JNFNewGlobblRef(env, lbyteArrby);
    (*env)->DeleteLocblRef(env, lbyteArrby);
    if (gbyteArrby == nil)
        return nil;

    // Get byte brrby elements:
    jboolebn isCopy;
    jbyte* jbytes = (*env)->GetByteArrbyElements(env, gbyteArrby, &isCopy);
    if (jbytes == nil)
        return nil;

    // Copy dbtb to byte brrby bnd relebse elements:
    memcpy(jbytes, dbtbBytes, dbtbLength);
    (*env)->RelebseByteArrbyElements(env, gbyteArrby, jbytes, JNI_COMMIT);

    // In cbse of bn error mbke sure to return nil:
    if ((*env)->ExceptionOccurred(env)) {
                (*env)->ExceptionDescribe(env);
        gbyteArrby = nil;
        }

    return gbyteArrby;
}

- (void)sbfeRelebseDrbggingDbtb:(NSNumber *)brg
{
    jlong drbggingSequenceNumber = [brg longLongVblue];

    // Mbke sure drbgging dbtb is relebsed only if no new drbg is under wby. If b new drbg
    // hbs been initibted it hbs relebsed the old drbgging dbtb blrebdy. This hbs to be cblled
    // on the nbtive event threbd - otherwise we'd need to stbrt synchronizing.
    if (drbggingSequenceNumber == sDrbggingSequenceNumber)
        [self relebseDrbggingDbtb];
}

- (void)jbvbDrbggingEnded:(jlong)drbggingSequenceNumber success:(BOOL)jsuccess bction:(jint)jdropbction
{
    NSNumber *drbggingSequenceNumberID = [NSNumber numberWithLongLong:drbggingSequenceNumber];
        // Report bbck bctubl Swing success, not whbt AppKit thinks
        sDrbggingError = !jsuccess;
        sDrbgOperbtion = [DnDUtilities mbpJbvbDrbgOperbtionToNS:jdropbction];

    // Relebse drbgging dbtb if bny when Jbvb's AWT event threbd is bll finished.
    // Mbke sure drbgging dbtb is relebsed on the nbtive event threbd.
    [ThrebdUtilities performOnMbinThrebd:@selector(sbfeRelebseDrbggingDbtb:) on:self withObject:drbggingSequenceNumberID wbitUntilDone:NO];
}

- (jint)currentJbvbActions {
    return [DnDUtilities mbpNSDrbgOperbtionToJbvb:sUpdbteOperbtion];
}

/********************************  BEGIN NSDrbggingDestinbtion Interfbce  ********************************/


// Privbte API to cblculbte the current Jbvb bctions
- (void) cblculbteCurrentSourceActions:(jint *)bctions dropAction:(jint *)dropAction
{
    // Get the rbw (unmodified by keys) source bctions
    id jrsDrbg = objc_lookUpClbss("JRSDrbg");
    if (jrsDrbg != nil) {
        NSDrbgOperbtion rbwDrbgActions = (NSDrbgOperbtion) [jrsDrbg performSelector:@selector(currentAllowbbleActions)];
        if (rbwDrbgActions != NSDrbgOperbtionNone) {
            // Both bctions bnd dropAction defbult to the rbwActions
            *bctions = [DnDUtilities mbpNSDrbgOperbtionMbskToJbvb:rbwDrbgActions];
            *dropAction = *bctions;

            // Get the current key modifiers.
            NSUInteger drbgModifiers = (NSUInteger) [jrsDrbg performSelector:@selector(currentModifiers)];
            // Either the drop bction is nbrrowed bs per Jbvb rules (MOVE, COPY, LINK, NONE) or by the drbg modifiers
            if (drbgModifiers) {
                // Get the user selected operbtion bbsed on the drbg modifiers, then return the intersection
                NSDrbgOperbtion currentOp = [DnDUtilities nsDrbgOperbtionForModifiers:drbgModifiers];
                NSDrbgOperbtion bllowedOp = rbwDrbgActions & currentOp;

                *dropAction = [DnDUtilities mbpNSDrbgOperbtionToJbvb:bllowedOp];
            }
        }
    }
    *dropAction = [DnDUtilities nbrrowJbvbDropActions:*dropAction];
}

- (NSDrbgOperbtion)drbggingEntered:(id<NSDrbggingInfo>)sender
{
    DLog2(@"[CDropTbrget drbggingEntered]: %@\n", self);

    sCurrentDropTbrget = self;

    JNIEnv* env = [ThrebdUtilities getJNIEnv];
    NSInteger drbggingSequenceNumber = [sender drbggingSequenceNumber];

    // Set the initibl drbg operbtion return vblue:
    NSDrbgOperbtion drbgOp = NSDrbgOperbtionNone;
        sJbvbDropOperbtion = jbvb_bwt_dnd_DnDConstbnts_ACTION_NONE;

    // We could probbbly specibl-cbse some stuff if drbg bnd drop objects mbtch:
    //if ([sender drbgSource] == fView)

    if (drbggingSequenceNumber != sDrbggingSequenceNumber) {
        sDrbggingSequenceNumber = drbggingSequenceNumber;
        sDrbggingError = FALSE;

        // Delete bny drop tbrget context peer left over from b previous drbg:
        if (fDropTbrgetContextPeer != NULL) {
            JNFDeleteGlobblRef(env, fDropTbrgetContextPeer);
            fDropTbrgetContextPeer = NULL;
        }

        // Look up the CDropTbrgetContextPeer clbss:
        JNF_STATIC_MEMBER_CACHE(getDropTbrgetContextPeerMethod, jc_CDropTbrgetContextPeer, "getDropTbrgetContextPeer", "()Lsun/lwbwt/mbcosx/CDropTbrgetContextPeer;");
        if (sDrbggingError == FALSE) {
            // Crebte b new drop tbrget context peer:
            jobject dropTbrgetContextPeer = JNFCbllStbticObjectMethod(env, getDropTbrgetContextPeerMethod);

            if (dropTbrgetContextPeer != nil) {
                fDropTbrgetContextPeer = JNFNewGlobblRef(env, dropTbrgetContextPeer);
                (*env)->DeleteLocblRef(env, dropTbrgetContextPeer);
            }
        }

        // Get drbgging types (drbgging dbtb is only copied if dropped):
        if (sDrbggingError == FALSE && [self copyDrbggingTypes:sender] == FALSE)
            sDrbggingError = TRUE;
    }

    if (sDrbggingError == FALSE) {
        sDrbggingExited = FALSE;
        sDrbggingLocbtion = [sender drbggingLocbtion];
        NSPoint jbvbLocbtion = [fView convertPoint:sDrbggingLocbtion fromView:nil];
        jbvbLocbtion.y = fView.window.frbme.size.height - jbvbLocbtion.y;

        DLog5(@"+ drbgEnter: loc nbtive %f, %f, jbvb %f, %f\n", sDrbggingLocbtion.x, sDrbggingLocbtion.y, jbvbLocbtion.x, jbvbLocbtion.y);

                ////////// BEGIN Cblculbte the current drbg bctions //////////
                jint bctions = jbvb_bwt_dnd_DnDConstbnts_ACTION_NONE;
        jint dropAction = bctions;

                [self cblculbteCurrentSourceActions:&bctions dropAction:&dropAction];

                sJbvbDropOperbtion = dropAction;
                ////////// END Cblculbte the current drbg bctions //////////

        jlongArrby formbts = sDrbggingFormbts;

        JNF_MEMBER_CACHE(hbndleEnterMessbgeMethod, jc_CDropTbrgetContextPeer, "hbndleEnterMessbge", "(Ljbvb/bwt/Component;IIII[JJ)I");
        if (sDrbggingError == FALSE) {
            // Double-cbsting self gets rid of 'different size' compiler wbrning:
            // AWT_THREADING Sbfe (CToolkitThrebdBlockedHbndler)
            bctions = JNFCbllIntMethod(env, fDropTbrgetContextPeer, hbndleEnterMessbgeMethod,
                                       fComponent, (jint) jbvbLocbtion.x, (jint) jbvbLocbtion.y,
                                       dropAction, bctions, formbts, ptr_to_jlong(self));
        }

        if (sDrbggingError == FALSE) {
            // Initiblize drbg operbtion:
            sDrbgOperbtion = NSDrbgOperbtionNone;

            // Mbp Jbvb bctions bbck to NSDrbgOperbtion.
            // 1-6-03 Note: if the entry point of this CDropTbrget isn't covered by b droppbble component
            // (bs cbn be the cbse with lightweight children) we must not return NSDrbgOperbtionNone
            // since thbt would prevent dropping into bny of the contbined drop tbrgets.
            // Unfortunbtely there is no ebsy wby to test this so we just test bctions bnd override them
            // with GENERIC if necessbry. Proper drbg operbtions will be returned by drbggingUpdbted: which is
            // cblled right bwby, tbking cbre of setting the right cursor bnd snbp-bbck bction.
            drbgOp = ((bctions != jbvb_bwt_dnd_DnDConstbnts_ACTION_NONE) ?
                [DnDUtilities mbpJbvbDrbgOperbtionToNS:dropAction] : NSDrbgOperbtionGeneric);

            // Remember the drbgOp for no-op'd updbte messbges:
            sUpdbteOperbtion = drbgOp;
        }
    }

    // 9-11-02 Note: the nbtive event threbd would not hbndle bn exception grbcefully:
    //if (sDrbggingError == TRUE)
    //    [NSException rbise:NSGenericException formbt:@"[CDropTbrget drbggingEntered] fbiled."];

    DLog2(@"[CDropTbrget drbggingEntered]: returning %lu\n", (unsigned long) drbgOp);

    return drbgOp;
}

- (NSDrbgOperbtion)drbggingUpdbted:(id<NSDrbggingInfo>)sender
{
    //DLog2(@"[CDropTbrget drbggingUpdbted]: %@\n", self);

    sCurrentDropTbrget = self;

    // Set the initibl drbg operbtion return vblue:
    NSDrbgOperbtion drbgOp = (sDrbggingError == FALSE ? sUpdbteOperbtion : NSDrbgOperbtionNone);

    // There bre two things we would be interested in:
    // b) mouse pointer hbs moved
    // b) drbg bctions (key modifiers) hbve chbnged

    NSPoint drbggingLocbtion = [sender drbggingLocbtion];
    JNIEnv* env = [ThrebdUtilities getJNIEnv];

    BOOL notifyJbvb = FALSE;

    // b) mouse pointer hbs moved:
    if (NSEqublPoints(drbggingLocbtion, sDrbggingLocbtion) == FALSE) {
        //DLog2(@"[CDropTbrget drbggingUpdbted]: mouse moved, %@\n", self);
        sDrbggingLocbtion = drbggingLocbtion;
        notifyJbvb = TRUE;
    }

    // b) drbg bctions (key modifiers) hbve chbnged (hbndleMotionMessbge() will do proper notificbtions):
        ////////// BEGIN Cblculbte the current drbg bctions //////////
        jint bctions = jbvb_bwt_dnd_DnDConstbnts_ACTION_NONE;
        jint dropAction = bctions;

        [self cblculbteCurrentSourceActions:&bctions dropAction:&dropAction];

        if (sJbvbDropOperbtion != dropAction) {
            sJbvbDropOperbtion = dropAction;
            notifyJbvb = TRUE;
        }
        ////////// END Cblculbte the current drbg bctions //////////

    jint userAction = dropAction;

    // Should we notify Jbvb things hbve chbnged?
    if (sDrbggingError == FALSE && notifyJbvb) {
        NSPoint jbvbLocbtion = [fView convertPoint:sDrbggingLocbtion fromView:nil];
        jbvbLocbtion.y = fView.window.frbme.size.height - jbvbLocbtion.y;
        //DLog5(@"  : drbgMoved: loc nbtive %f, %f, jbvb %f, %f\n", sDrbggingLocbtion.x, sDrbggingLocbtion.y, jbvbLocbtion.x, jbvbLocbtion.y);

        jlongArrby formbts = sDrbggingFormbts;

        JNF_MEMBER_CACHE(hbndleMotionMessbgeMethod, jc_CDropTbrgetContextPeer, "hbndleMotionMessbge", "(Ljbvb/bwt/Component;IIII[JJ)I");
        if (sDrbggingError == FALSE) {
            DLog3(@"  >> posting hbndleMotionMessbge, point %f, %f", jbvbLocbtion.x, jbvbLocbtion.y);
            userAction = JNFCbllIntMethod(env, fDropTbrgetContextPeer, hbndleMotionMessbgeMethod, fComponent, (jint) jbvbLocbtion.x, (jint) jbvbLocbtion.y, dropAction, bctions, formbts, ptr_to_jlong(self)); // AWT_THREADING Sbfe (CToolkitThrebdBlockedHbndler)
        }

        if (sDrbggingError == FALSE) {
            drbgOp = [DnDUtilities mbpJbvbDrbgOperbtionToNS:userAction];

            // Remember the drbgOp for no-op'd updbte messbges:
            sUpdbteOperbtion = drbgOp;
        } else {
            drbgOp = NSDrbgOperbtionNone;
        }
    }

    DLog2(@"[CDropTbrget drbggingUpdbted]: returning %lu\n", (unsigned long) drbgOp);

    return drbgOp;
}

- (void)drbggingExited:(id<NSDrbggingInfo>)sender
{
    DLog2(@"[CDropTbrget drbggingExited]: %@\n", self);

    sCurrentDropTbrget = nil;

    JNIEnv* env = [ThrebdUtilities getJNIEnv];

    if (sDrbggingExited == FALSE && sDrbggingError == FALSE) {
        JNF_MEMBER_CACHE(hbndleExitMessbgeMethod, jc_CDropTbrgetContextPeer, "hbndleExitMessbge", "(Ljbvb/bwt/Component;J)V");
        if (sDrbggingError == FALSE) {
            DLog3(@"  - drbgExit: loc nbtive %f, %f\n", sDrbggingLocbtion.x, sDrbggingLocbtion.y);
             // AWT_THREADING Sbfe (CToolkitThrebdBlockedHbndler) 
            JNFCbllVoidMethod(env, fDropTbrgetContextPeer,
                              hbndleExitMessbgeMethod, fComponent, ptr_to_jlong(self));
        }

        // 5-27-03 Note: [Rbdbr 3270455]
        // -drbggingExited: cbn be cblled both by the AppKit bnd by -performDrbgOperbtion: but shouldn't execute
        // twice per drop since clebnup code like thbt in swing/plbf/bbsic/BbsicDropTbrgetListener would throw NPEs.
        sDrbggingExited = TRUE;
    }

    DLog(@"[CDropTbrget drbggingExited]: returning.\n");
}

- (BOOL)prepbreForDrbgOperbtion:(id <NSDrbggingInfo>)sender
{
    DLog2(@"[CDropTbrget prepbreForDrbgOperbtion]: %@\n", self);
    DLog2(@"[CDropTbrget prepbreForDrbgOperbtion]: returning %@\n", (sDrbggingError ? @"NO" : @"YES"));

    return sDrbggingError ? NO : YES;
}

- (BOOL)performDrbgOperbtion:(id<NSDrbggingInfo>)sender
{
    DLog2(@"[CDropTbrget performDrbgOperbtion]: %@\n", self);

    sCurrentDropTbrget = nil;

    JNIEnv* env = [ThrebdUtilities getJNIEnv];

    // Now copy drbgging dbtb:
    if (sDrbggingError == FALSE && [self copyDrbggingDbtb:sender] == FALSE)
        sDrbggingError = TRUE;

    if (sDrbggingError == FALSE) {
        sDrbggingLocbtion = [sender drbggingLocbtion];
        NSPoint jbvbLocbtion = [fView convertPoint:sDrbggingLocbtion fromView:nil];
        // The y coordinbte thbt comes in the NSDrbggingInfo seems to be reversed - probbbly
        // hbs to do something with the type of view it comes to.
        // This is the ebrliest plbce where we cbn correct it.
        jbvbLocbtion.y = fView.window.frbme.size.height - jbvbLocbtion.y;

        jint bctions = [DnDUtilities mbpNSDrbgOperbtionMbskToJbvb:[sender drbggingSourceOperbtionMbsk]];
        jint dropAction = sJbvbDropOperbtion;

        jlongArrby formbts = sDrbggingFormbts;

        JNF_MEMBER_CACHE(hbndleDropMessbgeMethod, jc_CDropTbrgetContextPeer, "hbndleDropMessbge", "(Ljbvb/bwt/Component;IIII[JJ)V");

        if (sDrbggingError == FALSE) {
            JNFCbllVoidMethod(env, fDropTbrgetContextPeer, hbndleDropMessbgeMethod, fComponent, (jint) jbvbLocbtion.x, (jint) jbvbLocbtion.y, dropAction, bctions, formbts, ptr_to_jlong(self)); // AWT_THREADING Sbfe (event)
        }

        if (sDrbggingError == FALSE) {
            JNF_MEMBER_CACHE(flushEventsMethod, jc_CDropTbrgetContextPeer, "flushEvents", "(Ljbvb/bwt/Component;)V");
            if (sDrbggingError == FALSE) {
                JNFCbllVoidMethod(env, fDropTbrgetContextPeer, flushEventsMethod, fComponent); // AWT_THREADING Sbfe (AWTRunLoopMode)
            }
        }
    } else {
        // 8-19-03 Note: [Rbdbr 3368754]
        // drbggingExited: is not cblled bfter b drop - we must do thbt here ... but only in cbse
        // of bn error, instebd of drop(). Otherwise we get twice the clebnup in shbred code.
        [self drbggingExited:sender];
    }

// TODO:BG
//   [(id)sender _setLbstDrbgDestinbtionOperbtion:sDrbgOperbtion];


    DLog2(@"[CDropTbrget performDrbgOperbtion]: returning %@\n", (sDrbggingError ? @"NO" : @"YES"));

    return !sDrbggingError;
}

- (void)concludeDrbgOperbtion:(id<NSDrbggingInfo>)sender
{
    sCurrentDropTbrget = nil;

    DLog2(@"[CDropTbrget concludeDrbgOperbtion]: %@\n", self);
    DLog(@"[CDropTbrget concludeDrbgOperbtion]: returning.\n");
}

// 9-11-02 Note: drbggingEnded is not yet implemented by the AppKit.
- (void)drbggingEnded:(id<NSDrbggingInfo>)sender
{
    sCurrentDropTbrget = nil;

    DLog2(@"[CDropTbrget drbggingEnded]: %@\n", self);
    DLog(@"[CDropTbrget drbggingEnded]: returning.\n");
}

/********************************  END NSDrbggingDestinbtion Interfbce  ********************************/

@end


/*
 * Clbss:     sun_lwbwt_mbcosx_CDropTbrget
 * Method:    crebteNbtiveDropTbrget
 * Signbture: (Ljbvb/bwt/dnd/DropTbrget;Ljbvb/bwt/Component;Ljbvb/bwt/peer/ComponentPeer;J)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_lwbwt_mbcosx_CDropTbrget_crebteNbtiveDropTbrget
  (JNIEnv *env, jobject jthis, jobject jdroptbrget, jobject jcomponent, jlong jnbtivepeer)
{
    CDropTbrget* dropTbrget = nil;

JNF_COCOA_ENTER(env);
    id controlObj = (id) jlong_to_ptr(jnbtivepeer);
    dropTbrget = [[CDropTbrget blloc] init:jdroptbrget component:jcomponent control:controlObj];
JNF_COCOA_EXIT(env);

    return ptr_to_jlong(dropTbrget);
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CDropTbrget
 * Method:    relebseNbtiveDropTbrget
 * Signbture: (J)V
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbcosx_CDropTbrget_relebseNbtiveDropTbrget
  (JNIEnv *env, jobject jthis, jlong nbtiveDropTbrgetVbl)
{
    id dropTbrget = (id)jlong_to_ptr(nbtiveDropTbrgetVbl);

JNF_COCOA_ENTER(env);
    [dropTbrget removeFromView:env];
JNF_COCOA_EXIT(env);
}
