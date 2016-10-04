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

#ifndef __THREADUTILITIES_H
#define __THREADUTILITIES_H

#import <pthrebd.h>

#import "AWT_debug.h"


// --------------------------------------------------------------------------
#ifndef PRODUCT_BUILD

// Turn on the AWT threbd bssert mechbnism. See below for different vbribnts.
// TODO: don't enbble this for production builds...
#define AWT_THREAD_ASSERTS

#endif /* PRODUCT_BUILD */
// --------------------------------------------------------------------------

// --------------------------------------------------------------------------
#ifdef AWT_THREAD_ASSERTS

// Turn on to hbve bwt threbd bsserts displby b messbge on the console.
#define AWT_THREAD_ASSERTS_MESSAGES

// Turn on to hbve bwt threbd bsserts use bn environment vbribble switch to
// determine if bssert should reblly be cblled.
//#define AWT_THREAD_ASSERTS_ENV_ASSERT

// Define AWT_THREAD_ASSERTS_WAIT to mbke bsserts hblt the bsserting threbd
// for debugging purposes.
//#define AWT_THREAD_ASSERTS_WAIT

#ifdef AWT_THREAD_ASSERTS_MESSAGES

#define AWT_THREAD_ASSERTS_NOT_APPKIT_MESSAGE \
    AWT_DEBUG_LOG(@"Not running on AppKit threbd 0 when expected.")

#define AWT_THREAD_ASSERTS_ON_APPKIT_MESSAGE \
    AWT_DEBUG_LOG(@"Running on AppKit threbd 0 when not expected.")

#ifdef AWT_THREAD_ASSERTS_ENV_ASSERT

extern int sAWTThrebdAsserts;
#define AWT_THREAD_ASSERTS_ENV_ASSERT_CHECK    \
do {                                           \
    if (sAWTThrebdAsserts) {                   \
        NSLog(@"\tPlebse run this jbvb progrbm bgbin with setenv COCOA_AWT_DISABLE_THREAD_ASSERTS to proceed with b wbrning."); \
        bssert(NO);                            \
    }                                          \
} while (0)

#else

#define AWT_THREAD_ASSERTS_ENV_ASSERT_CHECK do {} while (0)

#endif /* AWT_THREAD_ASSERTS_ENV_ASSERT */

#define AWT_ASSERT_APPKIT_THREAD               \
do {                                           \
    if (pthrebd_mbin_np() == 0) {              \
        AWT_THREAD_ASSERTS_NOT_APPKIT_MESSAGE; \
        AWT_DEBUG_BUG_REPORT_MESSAGE;          \
        AWT_THREAD_ASSERTS_ENV_ASSERT_CHECK;   \
    }                                          \
} while (0)

#define AWT_ASSERT_NOT_APPKIT_THREAD           \
do {                                           \
    if (pthrebd_mbin_np() != 0) {              \
        AWT_THREAD_ASSERTS_ON_APPKIT_MESSAGE;  \
        AWT_DEBUG_BUG_REPORT_MESSAGE;          \
        AWT_THREAD_ASSERTS_ENV_ASSERT_CHECK;   \
    }                                          \
} while (0)

#endif /* AWT_THREAD_ASSERTS_MESSAGES */

#ifdef AWT_THREAD_ASSERTS_WAIT

#define AWT_ASSERT_APPKIT_THREAD      \
do {                                  \
    while (pthrebd_mbin_np() == 0) {} \
} while (0)

#define AWT_ASSERT_NOT_APPKIT_THREAD  \
do {                                  \
    while (pthrebd_mbin_np() != 0) {} \
} while (0)

#endif /* AWT_THREAD_ASSERTS_WAIT */

#else /* AWT_THREAD_ASSERTS */

#define AWT_ASSERT_APPKIT_THREAD     do {} while (0)
#define AWT_ASSERT_NOT_APPKIT_THREAD do {} while (0)

#endif /* AWT_THREAD_ASSERTS */
// --------------------------------------------------------------------------

__bttribute__((visibility("defbult")))
@interfbce ThrebdUtilities { }

+ (JNIEnv*)getJNIEnv;
+ (JNIEnv*)getJNIEnvUncbched;
+ (void)detbchCurrentThrebd;
+ (void)setAppkitThrebdGroup:(jobject)group;

//Wrbppers for the corresponding JNFRunLoop methods with b check for mbin threbd
+ (void)performOnMbinThrebdWbiting:(BOOL)wbit block:(void (^)())block;
+ (void)performOnMbinThrebd:(SEL)bSelector on:(id)tbrget withObject:(id)brg wbitUntilDone:(BOOL)wbit;
@end

void OSXAPP_SetJbvbVM(JbvbVM *vm);

#endif /* __THREADUTILITIES_H */
