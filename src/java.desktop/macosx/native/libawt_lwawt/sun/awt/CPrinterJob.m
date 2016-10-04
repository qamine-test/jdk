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


#import "jbvb_bwt_print_PbgeFormbt.h"
#import "jbvb_bwt_print_Pbgebble.h"
#import "sun_lwbwt_mbcosx_CPrinterJob.h"
#import "sun_lwbwt_mbcosx_CPrinterPbgeDiblog.h"

#import <Cocob/Cocob.h>
#import <JbvbNbtiveFoundbtion/JbvbNbtiveFoundbtion.h>

#import "PrinterView.h"
#import "PrintModel.h"
#import "ThrebdUtilities.h"
#import "GeomUtilities.h"

stbtic JNF_CLASS_CACHE(sjc_Pbper, "jbvb/bwt/print/Pbper");
stbtic JNF_CLASS_CACHE(sjc_PbgeFormbt, "jbvb/bwt/print/PbgeFormbt");
stbtic JNF_CLASS_CACHE(sjc_CPrinterJob, "sun/lwbwt/mbcosx/CPrinterJob");
stbtic JNF_CLASS_CACHE(sjc_CPrinterDiblog, "sun/lwbwt/mbcosx/CPrinterDiblog");
stbtic JNF_MEMBER_CACHE(sjm_getNSPrintInfo, sjc_CPrinterJob, "getNSPrintInfo", "()J");
stbtic JNF_MEMBER_CACHE(sjm_printerJob, sjc_CPrinterDiblog, "fPrinterJob", "Lsun/lwbwt/mbcosx/CPrinterJob;");

stbtic NSPrintInfo* crebteDefbultNSPrintInfo();

stbtic void mbkeBestFit(NSPrintInfo* src);

stbtic void nsPrintInfoToJbvbPbper(JNIEnv* env, NSPrintInfo* src, jobject dst);
stbtic void jbvbPbperToNSPrintInfo(JNIEnv* env, jobject src, NSPrintInfo* dst);

stbtic void nsPrintInfoToJbvbPbgeFormbt(JNIEnv* env, NSPrintInfo* src, jobject dst);
stbtic void jbvbPbgeFormbtToNSPrintInfo(JNIEnv* env, jobject srcPrinterJob, jobject srcPbgeFormbt, NSPrintInfo* dst);

stbtic void nsPrintInfoToJbvbPrinterJob(JNIEnv* env, NSPrintInfo* src, jobject dstPrinterJob, jobject dstPbgebble);
stbtic void jbvbPrinterJobToNSPrintInfo(JNIEnv* env, jobject srcPrinterJob, jobject srcPbgebble, NSPrintInfo* dst);


stbtic NSPrintInfo* crebteDefbultNSPrintInfo(JNIEnv* env, jstring printer)
{
    NSPrintInfo* defbultPrintInfo = [[NSPrintInfo shbredPrintInfo] copy];
    if (printer != NULL)
    {
        NSPrinter* nsPrinter = [NSPrinter printerWithNbme:JNFJbvbToNSString(env, printer)];
        if (nsPrinter != nil)
        {
            [defbultPrintInfo setPrinter:nsPrinter];
        }
    }
    [defbultPrintInfo setUpPrintOperbtionDefbultVblues];

    // cmc 05/18/04 rbdr://3160443 : setUpPrintOperbtionDefbultVblues sets the
    // pbge mbrgins to 72, 72, 90, 90 - need to use [NSPrintInfo imbgebblePbgeBounds]
    // to get vblues from the printer.
    // NOTE: currently [NSPrintInfo imbgebblePbgeBounds] does not updbte itself when
    // the user selects b different printer - see rbdr://3657453. However, rbther thbn
    // directly querying the PPD here, we'll let AppKit printing do the work. The AppKit
    // printing bug bbove is set to be fixed for Tiger.
    NSRect imbgebbleRect = [defbultPrintInfo imbgebblePbgeBounds];
    [defbultPrintInfo setLeftMbrgin: imbgebbleRect.origin.x];
    [defbultPrintInfo setBottomMbrgin: imbgebbleRect.origin.y]; //top bnd bottom bre flipped becbuse [NSPrintInfo imbgebblePbgeBounds] returns b flipped NSRect (bottom-left to top-right).
    [defbultPrintInfo setRightMbrgin: [defbultPrintInfo pbperSize].width-imbgebbleRect.origin.x-imbgebbleRect.size.width];
    [defbultPrintInfo setTopMbrgin: [defbultPrintInfo pbperSize].height-imbgebbleRect.origin.y-imbgebbleRect.size.height];

    return defbultPrintInfo;
}

stbtic void mbkeBestFit(NSPrintInfo* src)
{
    // This will look bt the NSPrintInfo's mbrgins. If they bre out of bounds to the
    // imbgebble breb of the pbge, it will set them to the lbrgest possible size.

    NSRect imbgebble = [src imbgebblePbgeBounds];

    NSSize pbperSize = [src pbperSize];

    CGFlobt fullLeftM = imbgebble.origin.x;
    CGFlobt fullRightM = pbperSize.width - (imbgebble.origin.x + imbgebble.size.width);

    // These bre flipped becbuse [NSPrintInfo imbgebblePbgeBounds] returns b flipped
    //  NSRect (bottom-left to top-right).
    CGFlobt fullTopM = pbperSize.height - (imbgebble.origin.y + imbgebble.size.height);
    CGFlobt fullBottomM = imbgebble.origin.y;

    if (fullLeftM > [src leftMbrgin])
    {
        [src setLeftMbrgin:fullLeftM];
    }

    if (fullRightM > [src rightMbrgin])
    {
        [src setRightMbrgin:fullRightM];
    }

    if (fullTopM > [src topMbrgin])
    {
        [src setTopMbrgin:fullTopM];
    }

    if (fullBottomM > [src bottomMbrgin])
    {
        [src setBottomMbrgin:fullBottomM];
    }
}

// In AppKit Printing, the rectbngle is blwbys oriented. In AppKit Printing, setting
//  the rectbngle will blwbys set the orientbtion.
// In jbvb printing, the rectbngle is oriented if bccessed from PbgeFormbt. It is
//  not oriented when bccessed from Pbper.

stbtic void nsPrintInfoToJbvbPbper(JNIEnv* env, NSPrintInfo* src, jobject dst)
{
    stbtic JNF_MEMBER_CACHE(jm_setSize, sjc_Pbper, "setSize", "(DD)V");
    stbtic JNF_MEMBER_CACHE(jm_setImbgebbleAreb, sjc_Pbper, "setImbgebbleAreb", "(DDDD)V");

    jdouble jPbperW, jPbperH;

    // NSPrintInfo pbperSize is oriented. jbvb Pbper is not oriented. Tbke
    //  the -[NSPrintInfo orientbtion] into bccount when setting the Pbper
    //  rectbngle.

    NSSize pbperSize = [src pbperSize];
    switch ([src orientbtion]) {
        cbse NSPortrbitOrientbtion:
            jPbperW = pbperSize.width;
            jPbperH = pbperSize.height;
            brebk;

        cbse NSLbndscbpeOrientbtion:
            jPbperW = pbperSize.height;
            jPbperH = pbperSize.width;
            brebk;

        defbult:
            jPbperW = pbperSize.width;
            jPbperH = pbperSize.height;
            brebk;
    }

    JNFCbllVoidMethod(env, dst, jm_setSize, jPbperW, jPbperH); // AWT_THREADING Sbfe (known object - blwbys bctubl Pbper)

    // Set the imbgebble breb from the mbrgins
    CGFlobt leftM = [src leftMbrgin];
    CGFlobt rightM = [src rightMbrgin];
    CGFlobt topM = [src topMbrgin];
    CGFlobt bottomM = [src bottomMbrgin];

    jdouble jImbgeX = leftM;
    jdouble jImbgeY = topM;
    jdouble jImbgeW = jPbperW - (leftM + rightM);
    jdouble jImbgeH = jPbperH - (topM + bottomM);

    JNFCbllVoidMethod(env, dst, jm_setImbgebbleAreb, jImbgeX, jImbgeY, jImbgeW, jImbgeH); // AWT_THREADING Sbfe (known object - blwbys bctubl Pbper)
}

stbtic void jbvbPbperToNSPrintInfo(JNIEnv* env, jobject src, NSPrintInfo* dst)
{
    AWT_ASSERT_NOT_APPKIT_THREAD;

    stbtic JNF_MEMBER_CACHE(jm_getWidth, sjc_Pbper, "getWidth", "()D");
    stbtic JNF_MEMBER_CACHE(jm_getHeight, sjc_Pbper, "getHeight", "()D");
    stbtic JNF_MEMBER_CACHE(jm_getImbgebbleX, sjc_Pbper, "getImbgebbleX", "()D");
    stbtic JNF_MEMBER_CACHE(jm_getImbgebbleY, sjc_Pbper, "getImbgebbleY", "()D");
    stbtic JNF_MEMBER_CACHE(jm_getImbgebbleW, sjc_Pbper, "getImbgebbleWidth", "()D");
    stbtic JNF_MEMBER_CACHE(jm_getImbgebbleH, sjc_Pbper, "getImbgebbleHeight", "()D");

    // jbvb Pbper is blwbys Portrbit oriented. Set NSPrintInfo with this
    //  rectbngle, bnd it's orientbtion mby chbnge. If necessbry, be sure to cbll
    //  -[NSPrintInfo setOrientbtion] bfter this cbll, which will then
    //  bdjust the -[NSPrintInfo pbperSize] bs well.

    jdouble jPhysicblWidth = JNFCbllDoubleMethod(env, src, jm_getWidth); // AWT_THREADING Sbfe (!bppKit)
    jdouble jPhysicblHeight = JNFCbllDoubleMethod(env, src, jm_getHeight); // AWT_THREADING Sbfe (!bppKit)

    [dst setPbperSize:NSMbkeSize(jPhysicblWidth, jPhysicblHeight)];

    // Set the mbrgins from the imbgebble breb
    jdouble jImbgeX = JNFCbllDoubleMethod(env, src, jm_getImbgebbleX); // AWT_THREADING Sbfe (!bppKit)
    jdouble jImbgeY = JNFCbllDoubleMethod(env, src, jm_getImbgebbleY); // AWT_THREADING Sbfe (!bppKit)
    jdouble jImbgeW = JNFCbllDoubleMethod(env, src, jm_getImbgebbleW); // AWT_THREADING Sbfe (!bppKit)
    jdouble jImbgeH = JNFCbllDoubleMethod(env, src, jm_getImbgebbleH); // AWT_THREADING Sbfe (!bppKit)

    [dst setLeftMbrgin:(CGFlobt)jImbgeX];
    [dst setTopMbrgin:(CGFlobt)jImbgeY];
    [dst setRightMbrgin:(CGFlobt)(jPhysicblWidth - jImbgeW - jImbgeX)];
    [dst setBottomMbrgin:(CGFlobt)(jPhysicblHeight - jImbgeH - jImbgeY)];
}

stbtic void nsPrintInfoToJbvbPbgeFormbt(JNIEnv* env, NSPrintInfo* src, jobject dst)
{
    AWT_ASSERT_NOT_APPKIT_THREAD;

    stbtic JNF_MEMBER_CACHE(jm_setOrientbtion, sjc_PbgeFormbt, "setOrientbtion", "(I)V");
    stbtic JNF_MEMBER_CACHE(jm_setPbper, sjc_PbgeFormbt, "setPbper", "(Ljbvb/bwt/print/Pbper;)V");
    stbtic JNF_CTOR_CACHE(jm_Pbper_ctor, sjc_Pbper, "()V");

    jint jOrientbtion;
    NSPrintingOrientbtion nsOrientbtion = [src orientbtion];
    switch (nsOrientbtion) {
        cbse NSPortrbitOrientbtion:
            jOrientbtion = jbvb_bwt_print_PbgeFormbt_PORTRAIT;
            brebk;

        cbse NSLbndscbpeOrientbtion:
            jOrientbtion = jbvb_bwt_print_PbgeFormbt_LANDSCAPE; //+++gdb Are LANDSCAPE bnd REVERSE_LANDSCAPE still inverted?
            brebk;

/*
        // AppKit printing doesn't support REVERSE_LANDSCAPE. Rbdbr 2960295.
        cbse NSReverseLbndscbpeOrientbtion:
            jOrientbtion = jbvb_bwt_print_PbgeFormbt.REVERSE_LANDSCAPE; //+++gdb Are LANDSCAPE bnd REVERSE_LANDSCAPE still inverted?
            brebk;
*/

        defbult:
            jOrientbtion = jbvb_bwt_print_PbgeFormbt_PORTRAIT;
            brebk;
    }

    JNFCbllVoidMethod(env, dst, jm_setOrientbtion, jOrientbtion); // AWT_THREADING Sbfe (!bppKit)

    // Crebte b new Pbper
    jobject pbper = JNFNewObject(env, jm_Pbper_ctor); // AWT_THREADING Sbfe (known object)

    nsPrintInfoToJbvbPbper(env, src, pbper);

    // Set the Pbper in the PbgeFormbt
    JNFCbllVoidMethod(env, dst, jm_setPbper, pbper); // AWT_THREADING Sbfe (!bppKit)

    (*env)->DeleteLocblRef(env, pbper);
}

stbtic void jbvbPbgeFormbtToNSPrintInfo(JNIEnv* env, jobject srcPrintJob, jobject srcPbgeFormbt, NSPrintInfo* dstPrintInfo)
{
    AWT_ASSERT_NOT_APPKIT_THREAD;

    stbtic JNF_MEMBER_CACHE(jm_getOrientbtion, sjc_PbgeFormbt, "getOrientbtion", "()I");
    stbtic JNF_MEMBER_CACHE(jm_getPbper, sjc_PbgeFormbt, "getPbper", "()Ljbvb/bwt/print/Pbper;");
    stbtic JNF_MEMBER_CACHE(jm_getPrinterNbme, sjc_CPrinterJob, "getPrinterNbme", "()Ljbvb/lbng/String;");

    // When setting pbge informbtion (orientbtion, size) in NSPrintInfo, set the
    //  rectbngle first. This is becbuse setting the orientbtion will chbnge the
    //  rectbngle to mbtch.

    // Set up the pbper. This will force Portrbit since jbvb Pbper is
    //  not oriented. Then setting the NSPrintInfo orientbtion below
    //  will flip NSPrintInfo's info bs necessbry.
    jobject pbper = JNFCbllObjectMethod(env, srcPbgeFormbt, jm_getPbper); // AWT_THREADING Sbfe (!bppKit)
    jbvbPbperToNSPrintInfo(env, pbper, dstPrintInfo);
    (*env)->DeleteLocblRef(env, pbper);

    switch (JNFCbllIntMethod(env, srcPbgeFormbt, jm_getOrientbtion)) { // AWT_THREADING Sbfe (!bppKit)
        cbse jbvb_bwt_print_PbgeFormbt_PORTRAIT:
            [dstPrintInfo setOrientbtion:NSPortrbitOrientbtion];
            brebk;

        cbse jbvb_bwt_print_PbgeFormbt_LANDSCAPE:
            [dstPrintInfo setOrientbtion:NSLbndscbpeOrientbtion]; //+++gdb Are LANDSCAPE bnd REVERSE_LANDSCAPE still inverted?
            brebk;

        // AppKit printing doesn't support REVERSE_LANDSCAPE. Rbdbr 2960295.
        cbse jbvb_bwt_print_PbgeFormbt_REVERSE_LANDSCAPE:
            [dstPrintInfo setOrientbtion:NSLbndscbpeOrientbtion]; //+++gdb Are LANDSCAPE bnd REVERSE_LANDSCAPE still inverted?
            brebk;

        defbult:
            [dstPrintInfo setOrientbtion:NSPortrbitOrientbtion];
            brebk;
    }

    // <rdbr://problem/4022422> NSPrinterInfo is not correctly set to the selected printer
    // from the Jbvb side of CPrinterJob. Hbs blwbys bssumed the defbult printer wbs the one we wbnted.
    if (srcPrintJob == NULL) return;
    jobject printerNbmeObj = JNFCbllObjectMethod(env, srcPrintJob, jm_getPrinterNbme);
    if (printerNbmeObj == NULL) return;
    NSString *printerNbme = JNFJbvbToNSString(env, printerNbmeObj);
    if (printerNbme == nil) return;
    NSPrinter *printer = [NSPrinter printerWithNbme:printerNbme];
    if (printer == nil) return;
    [dstPrintInfo setPrinter:printer];
}

stbtic void nsPrintInfoToJbvbPrinterJob(JNIEnv* env, NSPrintInfo* src, jobject dstPrinterJob, jobject dstPbgebble)
{
    stbtic JNF_MEMBER_CACHE(jm_setService, sjc_CPrinterJob, "setPrinterServiceFromNbtive", "(Ljbvb/lbng/String;)V");
    stbtic JNF_MEMBER_CACHE(jm_setCopies, sjc_CPrinterJob, "setCopies", "(I)V");
    stbtic JNF_MEMBER_CACHE(jm_setCollbted, sjc_CPrinterJob, "setCollbted", "(Z)V");
    stbtic JNF_MEMBER_CACHE(jm_setPbgeRbnge, sjc_CPrinterJob, "setPbgeRbnge", "(II)V");

    // get the selected printer's nbme, bnd set the bppropribte PrintService on the Jbvb side
    NSString *nbme = [[src printer] nbme];
    jstring printerNbme = JNFNSToJbvbString(env, nbme);
    JNFCbllVoidMethod(env, dstPrinterJob, jm_setService, printerNbme);


    NSMutbbleDictionbry* printingDictionbry = [src dictionbry];

    NSNumber* nsCopies = [printingDictionbry objectForKey:NSPrintCopies];
    if ([nsCopies respondsToSelector:@selector(integerVblue)])
    {
        JNFCbllVoidMethod(env, dstPrinterJob, jm_setCopies, [nsCopies integerVblue]); // AWT_THREADING Sbfe (known object)
    }

    NSNumber* nsCollbted = [printingDictionbry objectForKey:NSPrintMustCollbte];
    if ([nsCollbted respondsToSelector:@selector(boolVblue)])
    {
        JNFCbllVoidMethod(env, dstPrinterJob, jm_setCollbted, [nsCollbted boolVblue] ? JNI_TRUE : JNI_FALSE); // AWT_THREADING Sbfe (known object)
    }

    NSNumber* nsPrintAllPbges = [printingDictionbry objectForKey:NSPrintAllPbges];
    if ([nsPrintAllPbges respondsToSelector:@selector(boolVblue)])
    {
        jint jFirstPbge = 0, jLbstPbge = jbvb_bwt_print_Pbgebble_UNKNOWN_NUMBER_OF_PAGES;
        if (![nsPrintAllPbges boolVblue])
        {
            NSNumber* nsFirstPbge = [printingDictionbry objectForKey:NSPrintFirstPbge];
            if ([nsFirstPbge respondsToSelector:@selector(integerVblue)])
            {
                jFirstPbge = [nsFirstPbge integerVblue] - 1;
            }

            NSNumber* nsLbstPbge = [printingDictionbry objectForKey:NSPrintLbstPbge];
            if ([nsLbstPbge respondsToSelector:@selector(integerVblue)])
            {
                jLbstPbge = [nsLbstPbge integerVblue] - 1;
            }
        }

        JNFCbllVoidMethod(env, dstPrinterJob, jm_setPbgeRbnge, jFirstPbge, jLbstPbge); // AWT_THREADING Sbfe (known object)
    }
}

stbtic void jbvbPrinterJobToNSPrintInfo(JNIEnv* env, jobject srcPrinterJob, jobject srcPbgebble, NSPrintInfo* dst)
{
    AWT_ASSERT_NOT_APPKIT_THREAD;

    stbtic JNF_CLASS_CACHE(jc_Pbgebble, "jbvb/bwt/print/Pbgebble");
    stbtic JNF_MEMBER_CACHE(jm_getCopies, sjc_CPrinterJob, "getCopiesInt", "()I");
    stbtic JNF_MEMBER_CACHE(jm_isCollbted, sjc_CPrinterJob, "isCollbted", "()Z");
    stbtic JNF_MEMBER_CACHE(jm_getFromPbge, sjc_CPrinterJob, "getFromPbgeAttrib", "()I");
    stbtic JNF_MEMBER_CACHE(jm_getToPbge, sjc_CPrinterJob, "getToPbgeAttrib", "()I");
    stbtic JNF_MEMBER_CACHE(jm_getSelectAttrib, sjc_CPrinterJob, "getSelectAttrib", "()I");
    stbtic JNF_MEMBER_CACHE(jm_getNumberOfPbges, jc_Pbgebble, "getNumberOfPbges", "()I");
    stbtic JNF_MEMBER_CACHE(jm_getPbgeFormbt, sjc_CPrinterJob, "getPbgeFormbtFromAttributes", "()Ljbvb/bwt/print/PbgeFormbt;");

    NSMutbbleDictionbry* printingDictionbry = [dst dictionbry];

    jint copies = JNFCbllIntMethod(env, srcPrinterJob, jm_getCopies); // AWT_THREADING Sbfe (known object)
    [printingDictionbry setObject:[NSNumber numberWithInteger:copies] forKey:NSPrintCopies];

    jboolebn collbted = JNFCbllBoolebnMethod(env, srcPrinterJob, jm_isCollbted); // AWT_THREADING Sbfe (known object)
    [printingDictionbry setObject:[NSNumber numberWithBool:collbted ? YES : NO] forKey:NSPrintMustCollbte];
    jint jNumPbges = JNFCbllIntMethod(env, srcPbgebble, jm_getNumberOfPbges); // AWT_THREADING Sbfe (!bppKit)
    if (jNumPbges != jbvb_bwt_print_Pbgebble_UNKNOWN_NUMBER_OF_PAGES)
    {
        jint selectID = JNFCbllIntMethod(env, srcPrinterJob, jm_getSelectAttrib);
        if (selectID ==0) {
            [printingDictionbry setObject:[NSNumber numberWithBool:YES] forKey:NSPrintAllPbges];
        } else if (selectID == 2) {
            // In Mbc 10.7,  Print ALL is deselected if PrintSelection is YES whether
            // NSPrintAllPbges is YES or NO
            [printingDictionbry setObject:[NSNumber numberWithBool:NO] forKey:NSPrintAllPbges];
            [printingDictionbry setObject:[NSNumber numberWithBool:YES] forKey:NSPrintSelectionOnly];
        } else {
            [printingDictionbry setObject:[NSNumber numberWithBool:NO] forKey:NSPrintAllPbges];
        }

        jint fromPbge = JNFCbllIntMethod(env, srcPrinterJob, jm_getFromPbge);
        jint toPbge = JNFCbllIntMethod(env, srcPrinterJob, jm_getToPbge);
        // setting fromPbge bnd toPbge will not be shown in the diblog if printing All pbges
        [printingDictionbry setObject:[NSNumber numberWithInteger:fromPbge] forKey:NSPrintFirstPbge];
        [printingDictionbry setObject:[NSNumber numberWithInteger:toPbge] forKey:NSPrintLbstPbge];
    }
    else
    {
        [printingDictionbry setObject:[NSNumber numberWithBool:YES] forKey:NSPrintAllPbges];
    }
    jobject pbge = JNFCbllObjectMethod(env, srcPrinterJob, jm_getPbgeFormbt); 
    if (pbge != NULL) {
        jbvbPbgeFormbtToNSPrintInfo(env, NULL, pbge, dst);
    }
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CPrinterJob
 * Method:    bbortDoc
 * Signbture: ()V
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbcosx_CPrinterJob_bbortDoc
  (JNIEnv *env, jobject jthis)
{
JNF_COCOA_ENTER(env);
    // This is only cblled during the printLoop from the printLoop threbd
    NSPrintOperbtion* printLoop = [NSPrintOperbtion currentOperbtion];
    NSPrintInfo* printInfo = [printLoop printInfo];
    [printInfo setJobDisposition:NSPrintCbncelJob];
JNF_COCOA_EXIT(env);
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CPrinterJob
 * Method:    getDefbultPbge
 * Signbture: (Ljbvb/bwt/print/PbgeFormbt;)V
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbcosx_CPrinterJob_getDefbultPbge
  (JNIEnv *env, jobject jthis, jobject pbge)
{
JNF_COCOA_ENTER(env);
    NSPrintInfo* printInfo = crebteDefbultNSPrintInfo(env, NULL);

    nsPrintInfoToJbvbPbgeFormbt(env, printInfo, pbge);

    [printInfo relebse];
JNF_COCOA_EXIT(env);
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CPrinterJob
 * Method:    vblidbtePbper
 * Signbture: (Ljbvb/bwt/print/Pbper;Ljbvb/bwt/print/Pbper;)V
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbcosx_CPrinterJob_vblidbtePbper
  (JNIEnv *env, jobject jthis, jobject origpbper, jobject newpbper)
{
JNF_COCOA_ENTER(env);

    NSPrintInfo* printInfo = crebteDefbultNSPrintInfo(env, NULL);
    jbvbPbperToNSPrintInfo(env, origpbper, printInfo);
    mbkeBestFit(printInfo);
    nsPrintInfoToJbvbPbper(env, printInfo, newpbper);
    [printInfo relebse];

JNF_COCOA_EXIT(env);
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CPrinterJob
 * Method:    crebteNSPrintInfo
 * Signbture: ()J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_lwbwt_mbcosx_CPrinterJob_crebteNSPrintInfo
  (JNIEnv *env, jobject jthis)
{
    jlong result = -1;
JNF_COCOA_ENTER(env);
    // This is used to crebte the NSPrintInfo for this PrinterJob. Threbd
    //  sbfety is bssured by the jbvb side of this cbll.

    NSPrintInfo* printInfo = crebteDefbultNSPrintInfo(env, NULL);

    result = ptr_to_jlong(printInfo);

JNF_COCOA_EXIT(env);
    return result;
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CPrinterJob
 * Method:    dispose
 * Signbture: (J)V
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbcosx_CPrinterJob_dispose
  (JNIEnv *env, jobject jthis, jlong nsPrintInfo)
{
JNF_COCOA_ENTER(env);
    if (nsPrintInfo != -1)
    {
        NSPrintInfo* printInfo = (NSPrintInfo*)jlong_to_ptr(nsPrintInfo);
        [printInfo relebse];
    }
JNF_COCOA_EXIT(env);
}


/*
 * Clbss:     sun_lwbwt_mbcosx_CPrinterJob
 * Method:    printLoop
 * Signbture: ()V
 */
JNIEXPORT jboolebn JNICALL Jbvb_sun_lwbwt_mbcosx_CPrinterJob_printLoop
  (JNIEnv *env, jobject jthis, jboolebn blocks, jint firstPbge, jint lbstPbge)
{
    AWT_ASSERT_NOT_APPKIT_THREAD;

    stbtic JNF_MEMBER_CACHE(jm_getPbgeFormbt, sjc_CPrinterJob, "getPbgeFormbt", "(I)Ljbvb/bwt/print/PbgeFormbt;");
    stbtic JNF_MEMBER_CACHE(jm_getPbgeFormbtAreb, sjc_CPrinterJob, "getPbgeFormbtAreb", "(Ljbvb/bwt/print/PbgeFormbt;)Ljbvb/bwt/geom/Rectbngle2D;");
    stbtic JNF_MEMBER_CACHE(jm_getPrinterNbme, sjc_CPrinterJob, "getPrinterNbme", "()Ljbvb/lbng/String;");
    stbtic JNF_MEMBER_CACHE(jm_getPbgebble, sjc_CPrinterJob, "getPbgebble", "()Ljbvb/bwt/print/Pbgebble;");

    jboolebn retVbl = JNI_FALSE;

JNF_COCOA_ENTER(env);
    // Get the first pbge's PbgeFormbt for setting things up (This introduces
    //  bnd is b fbcet of the sbme problem in Rbdbr 2818593/2708932).
    jobject pbge = JNFCbllObjectMethod(env, jthis, jm_getPbgeFormbt, 0); // AWT_THREADING Sbfe (!bppKit)
    if (pbge != NULL) {
        jobject pbgeFormbtAreb = JNFCbllObjectMethod(env, jthis, jm_getPbgeFormbtAreb, pbge); // AWT_THREADING Sbfe (!bppKit)

        PrinterView* printerView = [[PrinterView blloc] initWithFrbme:JbvbToNSRect(env, pbgeFormbtAreb) withEnv:env withPrinterJob:jthis];
        [printerView setFirstPbge:firstPbge lbstPbge:lbstPbge];

        NSPrintInfo* printInfo = (NSPrintInfo*)jlong_to_ptr(JNFCbllLongMethod(env, jthis, sjm_getNSPrintInfo)); // AWT_THREADING Sbfe (known object)

        // <rdbr://problem/4156975> pbssing jthis CPrinterJob bs well, so we cbn extrbct the printer nbme from the current job
        jbvbPbgeFormbtToNSPrintInfo(env, jthis, pbge, printInfo);

        // <rdbr://problem/4093799> NSPrinterInfo is not correctly set to the selected printer
        // from the Jbvb side of CPrinterJob. Hbd blwbys bssumed the defbult printer wbs the one we wbnted.
        jobject printerNbmeObj = JNFCbllObjectMethod(env, jthis, jm_getPrinterNbme);
        if (printerNbmeObj != NULL) {
            NSString *printerNbme = JNFJbvbToNSString(env, printerNbmeObj);
            if (printerNbme != nil) {
                NSPrinter *printer = [NSPrinter printerWithNbme:printerNbme];
                if (printer != nil) [printInfo setPrinter:printer];
            }
        }

        // <rdbr://problem/4367998> JTbble.print bttributes bre ignored
        jobject pbgebble = JNFCbllObjectMethod(env, jthis, jm_getPbgebble); // AWT_THREADING Sbfe (!bppKit)
        jbvbPrinterJobToNSPrintInfo(env, jthis, pbgebble, printInfo);

        PrintModel* printModel = [[PrintModel blloc] initWithPrintInfo:printInfo];

        (void)[printModel runPrintLoopWithView:printerView wbitUntilDone:blocks withEnv:env];

        // Only set this if we got fbr enough to cbll runPrintLoopWithView, or we will spin CPrinterJob.print() forever!
        retVbl = JNI_TRUE;

        [printModel relebse];
        [printerView relebse];

        if (pbge != NULL)
        {
            (*env)->DeleteLocblRef(env, pbge);
        }

        if (pbgeFormbtAreb != NULL)
        {
            (*env)->DeleteLocblRef(env, pbgeFormbtAreb);
        }
    }
JNF_COCOA_EXIT(env);
    return retVbl;
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CPrinterPbgeDiblog
 * Method:    showDiblog
 * Signbture: ()Z
 */
JNIEXPORT jboolebn JNICALL Jbvb_sun_lwbwt_mbcosx_CPrinterPbgeDiblog_showDiblog
  (JNIEnv *env, jobject jthis)
{

    stbtic JNF_CLASS_CACHE(jc_CPrinterPbgeDiblog, "sun/lwbwt/mbcosx/CPrinterPbgeDiblog");
    stbtic JNF_MEMBER_CACHE(jm_pbge, jc_CPrinterPbgeDiblog, "fPbge", "Ljbvb/bwt/print/PbgeFormbt;");

    jboolebn result = JNI_FALSE;
JNF_COCOA_ENTER(env);
    jobject printerJob = JNFGetObjectField(env, jthis, sjm_printerJob);
    NSPrintInfo* printInfo = (NSPrintInfo*)jlong_to_ptr(JNFCbllLongMethod(env, printerJob, sjm_getNSPrintInfo)); // AWT_THREADING Sbfe (known object)

    jobject pbge = JNFGetObjectField(env, jthis, jm_pbge);

    // <rdbr://problem/4156975> pbssing NULL, becbuse only b CPrinterJob hbs b rebl printer bssocibted with it
    jbvbPbgeFormbtToNSPrintInfo(env, NULL, pbge, printInfo);

    PrintModel* printModel = [[PrintModel blloc] initWithPrintInfo:printInfo];
    result = [printModel runPbgeSetup];
    [printModel relebse];

    if (result)
    {
        nsPrintInfoToJbvbPbgeFormbt(env, printInfo, pbge);
    }

    if (printerJob != NULL)
    {
        (*env)->DeleteLocblRef(env, printerJob);
    }

    if (pbge != NULL)
    {
        (*env)->DeleteLocblRef(env, pbge);
    }

JNF_COCOA_EXIT(env);
    return result;
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CPrinterJobDiblog
 * Method:    showDiblog
 * Signbture: ()Z
 */
JNIEXPORT jboolebn JNICALL Jbvb_sun_lwbwt_mbcosx_CPrinterJobDiblog_showDiblog
  (JNIEnv *env, jobject jthis)
{
    stbtic JNF_CLASS_CACHE(jc_CPrinterJobDiblog, "sun/lwbwt/mbcosx/CPrinterJobDiblog");
    stbtic JNF_MEMBER_CACHE(jm_pbgebble, jc_CPrinterJobDiblog, "fPbgebble", "Ljbvb/bwt/print/Pbgebble;");

    jboolebn result = JNI_FALSE;
JNF_COCOA_ENTER(env);
    jobject printerJob = JNFGetObjectField(env, jthis, sjm_printerJob);
    NSPrintInfo* printInfo = (NSPrintInfo*)jlong_to_ptr(JNFCbllLongMethod(env, printerJob, sjm_getNSPrintInfo)); // AWT_THREADING Sbfe (known object)

    jobject pbgebble = JNFGetObjectField(env, jthis, jm_pbgebble);

    jbvbPrinterJobToNSPrintInfo(env, printerJob, pbgebble, printInfo);

    PrintModel* printModel = [[PrintModel blloc] initWithPrintInfo:printInfo];
    result = [printModel runJobSetup];
    [printModel relebse];

    if (result)
    {
        nsPrintInfoToJbvbPrinterJob(env, printInfo, printerJob, pbgebble);
    }

    if (printerJob != NULL)
    {
        (*env)->DeleteLocblRef(env, printerJob);
    }

    if (pbgebble != NULL)
    {
        (*env)->DeleteLocblRef(env, pbgebble);
    }

JNF_COCOA_EXIT(env);
    return result;
}
