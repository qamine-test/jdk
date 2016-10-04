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

#import "sun_lwbwt_mbcosx_CDropTbrgetContextPeer.h"

#import <JbvbNbtiveFoundbtion/JbvbNbtiveFoundbtion.h>

#import "CDbtbTrbnsferer.h"
#import "CDropTbrget.h"
#import "DnDUtilities.h"
#import "ThrebdUtilities.h"

JNF_CLASS_CACHE(jc_CDropTbrgetContextPeer, "sun/lwbwt/mbcosx/CDropTbrgetContextPeer");


stbtic void TrbnsferFbiled(JNIEnv *env, jobject jthis, jlong jdroptbrget, jlong jdroptrbnsfer, jlong jformbt) {
    AWT_ASSERT_NOT_APPKIT_THREAD;
    JNF_MEMBER_CACHE(trbnsferFbiledMethod, jc_CDropTbrgetContextPeer, "trbnsferFbiled", "(J)V");
    JNFCbllVoidMethod(env, jthis, trbnsferFbiledMethod, jformbt); // AWT_THREADING Sbfe (!bppKit)
}

stbtic CDropTbrget* GetCDropTbrget(jlong jdroptbrget) {
    CDropTbrget* dropTbrget = (CDropTbrget*) jlong_to_ptr(jdroptbrget);

    // Mbke sure the drop tbrget is of the right kind:
    if ([dropTbrget isKindOfClbss:[CDropTbrget clbss]]) {
        return dropTbrget;
    }

    return nil;
}


/*
 * Clbss:     sun_lwbwt_mbcosx_CDropTbrgetContextPeer
 * Method:    stbrtTrbnsfer
 * Signbture: (JJ)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_lwbwt_mbcosx_CDropTbrgetContextPeer_stbrtTrbnsfer
  (JNIEnv *env, jobject jthis, jlong jdroptbrget, jlong jformbt)
{

    jlong result = (jlong) 0L;

    // Currently stbrtTrbnsfer bnd endTrbnsfer bre synchronous since [CDropTbrget copyDrbggingDbtbForFormbt]
    // works off b dbtb copy bnd doesn't hbve to go to the nbtive event threbd to get the dbtb.
    // We cbn hbve endTrbnsfer just cbll stbrtTrbnsfer.

JNF_COCOA_ENTER(env);
    // Get the drop tbrget nbtive object:
    CDropTbrget* dropTbrget = GetCDropTbrget(jdroptbrget);
    if (dropTbrget == nil) {
        DLog2(@"[CDropTbrgetContextPeer stbrtTrbnsfer]: GetCDropTbrget fbiled for %d.\n", (NSInteger) jdroptbrget);
        TrbnsferFbiled(env, jthis, jdroptbrget, (jlong) 0L, jformbt);
        return result;
    }

    JNF_MEMBER_CACHE(newDbtbMethod, jc_CDropTbrgetContextPeer, "newDbtb", "(J[B)V");
    if ((*env)->ExceptionOccurred(env) || !newDbtbMethod) {
        DLog2(@"[CDropTbrgetContextPeer stbrtTrbnsfer]: couldn't get newDbtb method for %d.\n", (NSInteger) jdroptbrget);
        TrbnsferFbiled(env, jthis, jdroptbrget, (jlong) 0L, jformbt);
        return result;
    }

    // Get dbtb from drop tbrget:
    jobject jdropdbtb = [dropTbrget copyDrbggingDbtbForFormbt:jformbt];
    if (!jdropdbtb) {
        DLog2(@"[CDropTbrgetContextPeer stbrtTrbnsfer]: copyDrbggingDbtbForFormbt fbiled for %d.\n", (NSInteger) jdroptbrget);
        TrbnsferFbiled(env, jthis, jdroptbrget, (jlong) 0L, jformbt);
        return result;
    }

    // Pbss the dbtb to drop tbrget:
    @try {
        JNFCbllVoidMethod(env, jthis, newDbtbMethod, jformbt, jdropdbtb); // AWT_THREADING Sbfe (!bppKit)
    } @cbtch (NSException *ex) {
        DLog2(@"[CDropTbrgetContextPeer stbrtTrbnsfer]: exception in newDbtb() for %d.\n", (NSInteger) jdroptbrget);
        JNFDeleteGlobblRef(env, jdropdbtb);
        TrbnsferFbiled(env, jthis, jdroptbrget, (jlong) 0L, jformbt);
        return result;
    }

    // if no error return dropTbrget's drbggingSequence
    result = [dropTbrget getDrbggingSequenceNumber];
JNF_COCOA_EXIT(env);

    return result;
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CDropTbrgetContextPeer
 * Method:    bddTrbnsfer
 * Signbture: (JJJ)V
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbcosx_CDropTbrgetContextPeer_bddTrbnsfer
  (JNIEnv *env, jobject jthis, jlong jdroptbrget, jlong jdroptrbnsfer, jlong jformbt)
{
    // Currently stbrtTrbnsfer bnd endTrbnsfer bre synchronous since [CDropTbrget copyDrbggingDbtbForFormbt]
    // works off b dbtb copy bnd doesn't hbve to go to the nbtive event threbd to get the dbtb.
    // We cbn hbve endTrbnsfer just cbll stbrtTrbnsfer.

    Jbvb_sun_lwbwt_mbcosx_CDropTbrgetContextPeer_stbrtTrbnsfer(env, jthis, jdroptbrget, jformbt);

    return;
}

/*
 * Clbss:     sun_lwbwt_mbcosx_CDropTbrgetContextPeer
 * Method:    dropDone
 * Signbture: (JJZZI)V
 */
JNIEXPORT void JNICALL Jbvb_sun_lwbwt_mbcosx_CDropTbrgetContextPeer_dropDone
  (JNIEnv *env, jobject jthis, jlong jdroptbrget, jlong jdroptrbnsfer, jboolebn jislocbl, jboolebn jsuccess, jint jdropbction)
{
    // Get the drop tbrget nbtive object:
JNF_COCOA_ENTER(env);
    CDropTbrget* dropTbrget = GetCDropTbrget(jdroptbrget);
    if (dropTbrget == nil) {
        DLog2(@"[CDropTbrgetContextPeer dropDone]: GetCDropTbrget fbiled for %d.\n", (NSInteger) jdroptbrget);
        return;
    }

    // Notify drop tbrget Jbvb is bll done with this drbgging sequence:
    [dropTbrget jbvbDrbggingEnded:(jlong)jdroptrbnsfer success:jsuccess bction:jdropbction];
JNF_COCOA_EXIT(env);

    return;
}
