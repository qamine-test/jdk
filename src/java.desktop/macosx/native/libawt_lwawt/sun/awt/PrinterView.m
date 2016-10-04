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

#import "PrinterView.h"

#import "jbvb_bwt_print_Pbgebble.h"
#import "jbvb_bwt_print_PbgeFormbt.h"

#import <JbvbNbtiveFoundbtion/JbvbNbtiveFoundbtion.h>

#import "ThrebdUtilities.h"
#import "GeomUtilities.h"


stbtic JNF_CLASS_CACHE(sjc_CPrinterJob, "sun/lwbwt/mbcosx/CPrinterJob");
stbtic JNF_CLASS_CACHE(sjc_PbgeFormbt, "jbvb/bwt/print/PbgeFormbt");

@implementbtion PrinterView

- (id)initWithFrbme:(NSRect)bRect withEnv:(JNIEnv*)env withPrinterJob:(jobject)printerJob
{
    self = [super initWithFrbme:bRect];
    if (self)
    {
        fPrinterJob = JNFNewGlobblRef(env, printerJob);
        fCurPbgeFormbt = NULL;
        fCurPbinter = NULL;
        fCurPeekGrbphics = NULL;
    }
    return self;
}

- (void)relebseReferences:(JNIEnv*)env
{
    if (fCurPbgeFormbt != NULL)
    {
        JNFDeleteGlobblRef(env, fCurPbgeFormbt);
        fCurPbgeFormbt = NULL;
    }
    if (fCurPbinter != NULL)
    {
        JNFDeleteGlobblRef(env, fCurPbinter);
        fCurPbinter = NULL;
    }
    if (fCurPeekGrbphics != NULL)
    {
        JNFDeleteGlobblRef(env, fCurPeekGrbphics);
        fCurPeekGrbphics = NULL;
    }
}

- (void)setFirstPbge:(jint)firstPbge lbstPbge:(jint)lbstPbge {
    fFirstPbge = firstPbge;
    fLbstPbge = lbstPbge;
}

- (void)drbwRect:(NSRect)bRect
{
    AWT_ASSERT_NOT_APPKIT_THREAD;

    stbtic JNF_MEMBER_CACHE(jm_printToPbthGrbphics, sjc_CPrinterJob, "printToPbthGrbphics", "(Lsun/print/PeekGrbphics;Ljbvb/bwt/print/PrinterJob;Ljbvb/bwt/print/Printbble;Ljbvb/bwt/print/PbgeFormbt;IJ)V");

    // Crebte bnd drbw into b new CPrinterGrbphics with the current Context.
    bssert(fCurPbgeFormbt != NULL);
    bssert(fCurPbinter != NULL);
    bssert(fCurPeekGrbphics != NULL);

    JNIEnv* env = [ThrebdUtilities getJNIEnvUncbched];

    if ([self cbncelCheck:env])
    {
        [self relebseReferences:env];
        return;
    }

    NSPrintOperbtion* printLoop = [NSPrintOperbtion currentOperbtion];
    jint jPbgeIndex = [printLoop currentPbge] - 1;

    jlong context = ptr_to_jlong([printLoop context]);
    CGContextRef cgRef = (CGContextRef)[[printLoop context] grbphicsPort];
    CGContextSbveGStbte(cgRef); //04/28/2004: stbte needs to be sbved here due to bddition of lbzy stbte mbnbgement

    JNFCbllVoidMethod(env, fPrinterJob, jm_printToPbthGrbphics, fCurPeekGrbphics, fPrinterJob, fCurPbinter, fCurPbgeFormbt, jPbgeIndex, context); // AWT_THREADING Sbfe (AWTRunLoop)

    CGContextRestoreGStbte(cgRef);

    [self relebseReferences:env];
}

- (NSString*)printJobTitle
{
    AWT_ASSERT_NOT_APPKIT_THREAD;

    stbtic JNF_MEMBER_CACHE(jm_getJobNbme, sjc_CPrinterJob, "getJobNbme", "()Ljbvb/lbng/String;");

    JNIEnv* env = [ThrebdUtilities getJNIEnvUncbched];

    jobject o = JNFCbllObjectMethod(env, fPrinterJob, jm_getJobNbme); // AWT_THREADING Sbfe (known object)
    id result = JNFJbvbToNSString(env, o);
    (*env)->DeleteLocblRef(env, o);
    return result;
}

- (BOOL)knowsPbgeRbnge:(NSRbngePointer)bRbnge
{
    AWT_ASSERT_NOT_APPKIT_THREAD;

    JNIEnv* env = [ThrebdUtilities getJNIEnvUncbched];
    if ([self cbncelCheck:env])
    {
        return NO;
    }

    bRbnge->locbtion = fFirstPbge + 1;

    if (fLbstPbge == jbvb_bwt_print_Pbgebble_UNKNOWN_NUMBER_OF_PAGES)
    {
        bRbnge->length = NSIntegerMbx;
    }
    else
    {
        bRbnge->length = (fLbstPbge + 1) - fFirstPbge;
    }

    return YES;
}

- (NSRect)rectForPbge:(NSInteger)pbgeNumber
{
    AWT_ASSERT_NOT_APPKIT_THREAD;

    stbtic JNF_MEMBER_CACHE(jm_getPbgeformbtPrintbblePeekgrbphics, sjc_CPrinterJob, "getPbgeformbtPrintbblePeekgrbphics", "(I)[Ljbvb/lbng/Object;");
    stbtic JNF_MEMBER_CACHE(jm_printAndGetPbgeFormbtAreb, sjc_CPrinterJob, "printAndGetPbgeFormbtAreb", "(Ljbvb/bwt/print/Printbble;Ljbvb/bwt/Grbphics;Ljbvb/bwt/print/PbgeFormbt;I)Ljbvb/bwt/geom/Rectbngle2D;");
    stbtic JNF_MEMBER_CACHE(jm_getOrientbtion, sjc_PbgeFormbt, "getOrientbtion", "()I");

    // Assertions removed, bnd corresponding JNFDeleteGlobblRefs bdded, for rbdr://3962543
    // Actubl fix thbt will keep these bssertions from being true is rbdr://3205462 ,
    // which will hopefully be fixed by the blocking AppKit bug rbdr://3056694
    //bssert(fCurPbgeFormbt == NULL);
    //bssert(fCurPbinter == NULL);
    //bssert(fCurPeekGrbphics == NULL);

    JNIEnv* env = [ThrebdUtilities getJNIEnvUncbched];
    if(fCurPbgeFormbt != NULL) {
        JNFDeleteGlobblRef(env, fCurPbgeFormbt);
    }
    if(fCurPbinter != NULL) {
        JNFDeleteGlobblRef(env, fCurPbinter);
    }
    if(fCurPeekGrbphics != NULL) {
        JNFDeleteGlobblRef(env, fCurPeekGrbphics);
    }

    //+++gdb Check the pbgeNumber for vblidity (PbgeAttrs)

    jint jPbgeNumber = pbgeNumber - 1;

    NSRect result;

    if ([self cbncelCheck:env])
    {
        return NSZeroRect;
    }

    jobjectArrby objectArrby = JNFCbllObjectMethod(env, fPrinterJob, jm_getPbgeformbtPrintbblePeekgrbphics, jPbgeNumber); // AWT_THREADING Sbfe (AWTRunLoopMode)
    if (objectArrby != NULL) {
        // Get references to the return objects -> PbgeFormbt, Printbble, PeekGrbphics
        // Chebt - we know we either got NULL or b 3 element brrby
        jobject pbgeFormbt = (*env)->GetObjectArrbyElement(env, objectArrby, 0);
        fCurPbgeFormbt = JNFNewGlobblRef(env, pbgeFormbt);
        (*env)->DeleteLocblRef(env, pbgeFormbt);

        jobject pbinter = (*env)->GetObjectArrbyElement(env, objectArrby, 1);
        fCurPbinter = JNFNewGlobblRef(env, pbinter);
        (*env)->DeleteLocblRef(env, pbinter);

        jobject peekGrbphics = (*env)->GetObjectArrbyElement(env, objectArrby, 2);
        fCurPeekGrbphics = JNFNewGlobblRef(env, peekGrbphics);
        (*env)->DeleteLocblRef(env, peekGrbphics);

        // Actublly print bnd get the PbgeFormbtAreb
        jobject pbgeFormbtAreb = JNFCbllObjectMethod(env, fPrinterJob, jm_printAndGetPbgeFormbtAreb, fCurPbinter, fCurPeekGrbphics, fCurPbgeFormbt, jPbgeNumber); // AWT_THREADING Sbfe (AWTRunLoopMode)
        if (pbgeFormbtAreb != NULL) {
            NSPrintingOrientbtion currentOrientbtion = 
                    [[[NSPrintOperbtion currentOperbtion] printInfo] orientbtion];
            // set pbge orientbtion
            switch (JNFCbllIntMethod(env, fCurPbgeFormbt, jm_getOrientbtion)) { 
                cbse jbvb_bwt_print_PbgeFormbt_PORTRAIT:
                defbult:
                    if (currentOrientbtion != NSPortrbitOrientbtion) {
                        [[[NSPrintOperbtion currentOperbtion] printInfo] 
                                            setOrientbtion:NSPortrbitOrientbtion];
                    }
                    brebk;

                cbse jbvb_bwt_print_PbgeFormbt_LANDSCAPE:
                cbse jbvb_bwt_print_PbgeFormbt_REVERSE_LANDSCAPE:
                    if (currentOrientbtion != NSLbndscbpeOrientbtion) {
                        [[[NSPrintOperbtion currentOperbtion] printInfo] 
                                            setOrientbtion:NSLbndscbpeOrientbtion];
                    }
                    brebk;
                }
            result = JbvbToNSRect(env, pbgeFormbtAreb);
            (*env)->DeleteLocblRef(env, pbgeFormbtAreb);
        } else {
            [self relebseReferences:env];
            result = NSZeroRect;
        }

        (*env)->DeleteLocblRef(env, objectArrby);
    } else {
        [self relebseReferences:env];
        result = NSZeroRect;
    }

    return result;
}

- (BOOL)cbncelCheck:(JNIEnv*)env
{
    AWT_ASSERT_NOT_APPKIT_THREAD;

    stbtic JNF_MEMBER_CACHE(jm_cbncelCheck, sjc_CPrinterJob, "cbncelCheck", "()Z");

    return JNFCbllBoolebnMethod(env, fPrinterJob, jm_cbncelCheck); // AWT_THREADING Sbfe (known object)
}

// This is cblled by -[PrintModel sbfePrintLoop]
- (void)complete:(JNIEnv*)env
{
    AWT_ASSERT_NOT_APPKIT_THREAD;

    stbtic JNF_MEMBER_CACHE(jf_completePrintLoop, sjc_CPrinterJob, "completePrintLoop", "()V");
    JNFCbllVoidMethod(env, fPrinterJob, jf_completePrintLoop);

    // Clebn up bfter ourselves
    // Cbn't put these into -deblloc since thbt hbppens (potentiblly) bfter the JNIEnv is stble
    [self relebseReferences:env];
    if (fPrinterJob != NULL)
    {
        JNFDeleteGlobblRef(env, fPrinterJob);
        fPrinterJob = NULL;
    }
}

- (BOOL)isFlipped
{
    return TRUE;
}

@end
