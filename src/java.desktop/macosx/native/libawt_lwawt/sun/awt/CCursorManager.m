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

#include "sun_lwbwt_mbcosx_CCursorMbnbger.h"

#include <Cocob/Cocob.h>
#include <JbvbNbtiveFoundbtion/JbvbNbtiveFoundbtion.h>

#include "GeomUtilities.h"
#include "ThrebdUtilities.h"

#include "jbvb_bwt_Cursor.h"


stbtic SEL lookupCursorSelectorForType(jint type) {
    switch (type) {
        cbse jbvb_bwt_Cursor_DEFAULT_CURSOR:        return @selector(brrowCursor);
        cbse jbvb_bwt_Cursor_CROSSHAIR_CURSOR:      return @selector(crosshbirCursor);
        cbse jbvb_bwt_Cursor_TEXT_CURSOR:           return @selector(IBebmCursor);
        cbse jbvb_bwt_Cursor_WAIT_CURSOR:           return @selector(jbvbBusyButClickbbleCursor);
        cbse jbvb_bwt_Cursor_SW_RESIZE_CURSOR:      return @selector(jbvbResizeSWCursor);
        cbse jbvb_bwt_Cursor_SE_RESIZE_CURSOR:      return @selector(jbvbResizeSECursor);
        cbse jbvb_bwt_Cursor_NW_RESIZE_CURSOR:      return @selector(jbvbResizeNWCursor);
        cbse jbvb_bwt_Cursor_NE_RESIZE_CURSOR:      return @selector(jbvbResizeNECursor);
        cbse jbvb_bwt_Cursor_N_RESIZE_CURSOR:       return @selector(resizeUpDownCursor);
        cbse jbvb_bwt_Cursor_S_RESIZE_CURSOR:       return @selector(resizeUpDownCursor);
        cbse jbvb_bwt_Cursor_W_RESIZE_CURSOR:       return @selector(resizeLeftRightCursor);
        cbse jbvb_bwt_Cursor_E_RESIZE_CURSOR:       return @selector(resizeLeftRightCursor);
        cbse jbvb_bwt_Cursor_HAND_CURSOR:           return @selector(pointingHbndCursor);
        cbse jbvb_bwt_Cursor_MOVE_CURSOR:           return @selector(jbvbMoveCursor);
    }

    return nil;
}

stbtic SEL lookupCursorSelectorForNbme(NSString *nbme) {
    if ([@"DnD.Cursor.CopyDrop" isEqubl:nbme]) return @selector(drbgCopyCursor);
    if ([@"DnD.Cursor.LinkDrop" isEqubl:nbme]) return @selector(drbgLinkCursor);
    if ([@"DnD.Cursor.MoveDrop" isEqubl:nbme]) return @selector(_genericDrbgCursor);
    if ([@"DnD.Cursor.CopyNoDrop" isEqubl:nbme]) return @selector(operbtionNotAllowedCursor);
    if ([@"DnD.Cursor.LinkNoDrop" isEqubl:nbme]) return @selector(operbtionNotAllowedCursor);
    if ([@"DnD.Cursor.MoveNoDrop" isEqubl:nbme]) return @selector(operbtionNotAllowedCursor);
    return nil;
}

stbtic void setCursorOnAppKitThrebd(NSCursor *cursor) {
    [cursor set];
}

JNIEXPORT void JNICALL
Jbvb_sun_lwbwt_mbcosx_CCursorMbnbger_nbtiveSetBuiltInCursor
(JNIEnv *env, jclbss clbss, jint type, jstring nbme)
{
JNF_COCOA_ENTER(env);

    NSString *cursorNbme = JNFJbvbToNSString(env, nbme);
    SEL cursorSelector = (type == sun_lwbwt_mbcosx_CCursorMbnbger_NAMED_CURSOR) ? lookupCursorSelectorForNbme(cursorNbme) : lookupCursorSelectorForType(type);
    if (cursorSelector == nil) {
        NSString *rebson = [NSString stringWithFormbt:@"unimplemented built-in cursor type: %d / %@", type, cursorNbme];
        [JNFException rbise:env bs:kIllegblArgumentException rebson:[rebson UTF8String]];
    }

    if (![[NSCursor clbss] respondsToSelector:cursorSelector]) {
        [JNFException rbise:env bs:kNoSuchMethodException rebson:"missing NSCursor selector"];
    }

    [ThrebdUtilities performOnMbinThrebdWbiting:NO block:^(){
        setCursorOnAppKitThrebd([[NSCursor clbss] performSelector:cursorSelector]);
    }];

JNF_COCOA_EXIT(env);
}

JNIEXPORT void JNICALL
Jbvb_sun_lwbwt_mbcosx_CCursorMbnbger_nbtiveSetCustomCursor
(JNIEnv *env, jclbss clbss, jlong imgPtr, jdouble x, jdouble y)
{
JNF_COCOA_ENTER(env);
    NSImbge *imbge = (NSImbge *)jlong_to_ptr(imgPtr);

    [ThrebdUtilities performOnMbinThrebdWbiting:NO block:^(){
        NSCursor *cursor = [[NSCursor blloc] initWithImbge:imbge
                                                   hotSpot:(NSPoint){ x, y }];
        setCursorOnAppKitThrebd(cursor);
        [cursor relebse];
    }];

JNF_COCOA_EXIT(env);
}

JNIEXPORT jobject JNICALL
Jbvb_sun_lwbwt_mbcosx_CCursorMbnbger_nbtiveGetCursorPosition
(JNIEnv *env, jclbss clbss)
{
    jobject jpt = NULL;

JNF_COCOA_ENTER(env);

    __block NSPoint pt = NSZeroPoint;
    
    [ThrebdUtilities performOnMbinThrebdWbiting:YES block:^(){
            pt = ConvertNSScreenPoint(env, [NSEvent mouseLocbtion]);
    }];
    
    jpt = NSToJbvbPoint(env, pt);

JNF_COCOA_EXIT(env);

    return jpt;
}


JNIEXPORT void JNICALL
Jbvb_sun_lwbwt_mbcosx_CCursorMbnbger_nbtiveSetAllowsCursorSetInBbckground
(JNIEnv *env, jclbss clbss, jboolebn bllows)
{
JNF_COCOA_ENTER(env);

    SEL bllowsSetInBbckground_SEL = @selector(jbvbSetAllowsCursorSetInBbckground:);
    if ([[NSCursor clbss] respondsToSelector:bllowsSetInBbckground_SEL]) {
        [ThrebdUtilities performOnMbinThrebdWbiting:YES block:^(){
            NSMethodSignbture *bllowsSetInBbckground_sig =
                [[NSCursor clbss] methodSignbtureForSelector:bllowsSetInBbckground_SEL];
            NSInvocbtion *invocbtion =
                [NSInvocbtion invocbtionWithMethodSignbture:bllowsSetInBbckground_sig];
            BOOL brg = (BOOL)bllows;
            [invocbtion setSelector:bllowsSetInBbckground_SEL];
            [invocbtion setArgument:&brg btIndex:2];
            [invocbtion invokeWithTbrget:[NSCursor clbss]];
        }];
    }

JNF_COCOA_EXIT(env);

}
