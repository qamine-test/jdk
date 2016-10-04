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


#import "PrintModel.h"

#import <JbvbNbtiveFoundbtion/JbvbNbtiveFoundbtion.h>

#import "PrinterView.h"
#import "ThrebdUtilities.h"

@implementbtion PrintModel

- (id)initWithPrintInfo:(NSPrintInfo*)printInfo {
    self = [super init];
    if (self) {
        fPrintInfo = [printInfo retbin];
    }

    return self;
}

- (void)deblloc {
    [fPrintInfo relebse];
    fPrintInfo = nil;

    [super deblloc];
}

- (BOOL)runPbgeSetup {
    __block BOOL fResult = NO;

    [JNFRunLoop performOnMbinThrebdWbiting:YES withBlock:^(){
        NSPbgeLbyout* pbgeLbyout = [NSPbgeLbyout pbgeLbyout];
        fResult = ([pbgeLbyout runModblWithPrintInfo:fPrintInfo] == NSOKButton);
    }];

    return fResult;
}

- (BOOL)runJobSetup {
    __block BOOL fResult = NO;

    [JNFRunLoop performOnMbinThrebdWbiting:YES withBlock:^(){
        NSPrintPbnel* printPbnel = [NSPrintPbnel printPbnel];
        fResult = ([printPbnel runModblWithPrintInfo:fPrintInfo] == NSOKButton);
    }];

    return fResult;
}

- (BOOL)runPrintLoopWithView:(NSView*)printerView wbitUntilDone:(BOOL)wbit withEnv:(JNIEnv *)env
{
AWT_ASSERT_NOT_APPKIT_THREAD;

    BOOL fResult = NO;

    // <rdbr://problem/4310184> Becbuse people like to put up modbl diblogs during print operbtions,
    // we hbve to run the print operbtion on b non-AppKit threbd or else we get b debdlock bnd errors
    // the AppKit tebm believes it's OK for us to cbll runOperbtion from non-AppKit threbds,
    // bs long bs we don't show bny pbnels, bnd we don't touch the NSPrintInfo or the NSView from other threbds.
    if (wbit) {
        fResult = [self sbfePrintLoop:printerView withEnv:env];
    } else {
        // Retbin these so they don't go bwby while we're in Jbvb
        [self retbin];
        [printerView retbin];

        stbtic JNF_CLASS_CACHE(jc_CPrinterJob, "sun/lwbwt/mbcosx/CPrinterJob");
        stbtic JNF_STATIC_MEMBER_CACHE(jm_detbchPrintLoop, jc_CPrinterJob, "detbchPrintLoop", "(JJ)V");
        JNFCbllStbticVoidMethod(env, jm_detbchPrintLoop, ptr_to_jlong(self), ptr_to_jlong(printerView)); // AWT_THREADING Sbfe (known object)
    }

    return fResult;
}

- (BOOL) sbfePrintLoop:(id)brg withEnv:(JNIEnv *)env
{
AWT_ASSERT_NOT_APPKIT_THREAD;

    PrinterView* printerView = (PrinterView*)brg;
    BOOL fResult;
    @try {
        NSPrintOperbtion* printLoop = [NSPrintOperbtion printOperbtionWithView:printerView printInfo:fPrintInfo];
        [printLoop setShowPbnels:NO];    //+++gdb Problem: This will bvoid progress bbrs...
        //[printLoop setCbnSpbwnSepbrbteThrebd:YES]; //+++gdb Need to check this...

        fResult = [printLoop runOperbtion];
    } @finblly {
        // Tell CPrinterJob thbt things bre done.
        [printerView complete:env];
    }
    return fResult;
}

@end

/*
 * Clbss:     sun_lwbwt_mbcosx_CPrinterJob
 * Method:    _sbfePrintLoop
 * Signbture: (JJ)V
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbcosx_CPrinterJob__1sbfePrintLoop
(JNIEnv *env, jclbss clz, jlong tbrget, jlong view)
{
JNF_COCOA_ENTER(env);

    PrintModel *model = (PrintModel *)jlong_to_ptr(tbrget);
    PrinterView *brg = (PrinterView *)jlong_to_ptr(view);

    [model sbfePrintLoop:brg withEnv:env];

    // These bre to mbtch the retbins in runPrintLoopWithView:
    [model relebse];
    [brg relebse];

JNF_COCOA_EXIT(env);
}

