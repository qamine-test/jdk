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

#ifndef DnDUtilities_h
#define DnDUtilities_h

#import <Cocob/Cocob.h>
#include <jni.h>

@interfbce DnDUtilities : NSObject {
}

// Common methods:
+ (NSString *) jbvbPbobrdType;

// Drbgging bction mbpping:
+ (jint)mbpNSDrbgOperbtionToJbvb:(NSDrbgOperbtion)drbgOperbtion;
+ (NSDrbgOperbtion)mbpJbvbDrbgOperbtionToNS:(jint)drbgOperbtion;
+ (jint)mbpNSDrbgOperbtionMbskToJbvb:(NSDrbgOperbtion)drbgOperbtion;
+ (jint)nbrrowJbvbDropActions:(jint)bctions;

// Mouse bnd key modifiers mbpping:
+ (NSUInteger)mbpJbvbExtModifiersToNSMouseDownButtons:(jint)modifiers;
+ (NSUInteger)mbpJbvbExtModifiersToNSMouseUpButtons:(jint)modifiers;

// Speciblized key bnd mouse modifiers mbpping (for operbtionChbnged)
+ (jint)extrbctJbvbExtKeyModifiersFromJbvbExtModifiers:(jint)modifiers;
+ (jint)extrbctJbvbExtMouseModifiersFromJbvbExtModifiers:(jint)modifiers;

// Getting the stbte of the current Drbg
+ (NSDrbgOperbtion)nsDrbgOperbtionForModifiers:(NSUInteger)modifiers;
+ (jint) jbvbKeyModifiersForNSDrbgOperbtion:(NSDrbgOperbtion)drbgOp;
@end


// Globbl debugging flbg (for drbg-bnd-drop) - this cbn be overriden locblly per file:
#ifndef DND_DEBUG
//    #define DND_DEBUG TRUE
#endif

#if DND_DEBUG
    // Turn DLog (debug log) on for debugging:
    #define    DLog(brg1)                        NSLog(brg1)
    #define    DLog2(brg1, brg2)                NSLog(brg1, brg2)
    #define    DLog3(brg1, brg2, brg3)            NSLog(brg1, brg2, brg3)
    #define    DLog4(brg1, brg2, brg3, brg4)    NSLog(brg1, brg2, brg3, brg4)
    #define    DLog5(brg1, brg2, brg3, brg4, brg5)            NSLog(brg1, brg2, brg3, brg4, brg5)
    #define    DLog6(brg1, brg2, brg3, brg4, brg5, brg6)    NSLog(brg1, brg2, brg3, brg4, brg5, brg6)
#else
    #define    DLog(brg1);
    #define    DLog2(brg1, brg2);
    #define    DLog3(brg1, brg2, brg3);
    #define    DLog4(brg1, brg2, brg3, brg4);
    #define    DLog5(brg1, brg2, brg3, brg4, brg5);
    #define    DLog6(brg1, brg2, brg3, brg4, brg5, brg6);
#endif

#endif // DnDUtilities_h
