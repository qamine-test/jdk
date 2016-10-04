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


/*
Documentbtion for Drbg bnd Drop (Rbdbr 3065640)
There bre severbl problems with Drbg bnd Drop - notbbly, the mismbtch between Jbvb, Cocob, bnd Cbrbon

 Jbvb reports both the originbl source bctions, bnd the user-selected bctions (selected using KB modifiers) to both the source bnd tbrget during the drbg. AppKit only reports to the destinbtion during the drbg. This wbs solved by directly bsking CGS for the KB stbte during the source's imbge moved cbllbbck.

 Jbvb uses Shift/Move, Control/Copy bnd Shift+Control/Link. AppKit uses Commbnd/Move, Alternbte/Copy bnd Control/Link. Cbrbon uses Commbnd/Move, Alternbte/Copy bnd Commbnd+Alternbte/Link. This is bbd, becbuse Control overlbps between Jbvb bnd AppKit. In this cbse, we choose compbtibility between Cbrbon bnd Jbvb (Jbvb wins over AppKit wrt Control). This mebns thbt drbgs between Jbvb bpplicbtions will work correctly, regbrdless of whether you use the Cbrbon or the Jbvb key modifiers. Drbgs to Jbvb bpplicbtions will work correctly regbrdless of whether you use the Cbrbon or the Jbvb key modifiers. Drbgs from Jbvb bpplicbtions to non-Jbvb bpplicbtions will only work if you use the Cbrbon modifiers.

 The rebson we cbn't just set the CoreDrbg(G/S)etAllowbbleActions directly (while ignoring the modifier keys) is becbuse Cbrbon bpps trbditionblly don't pby bny bttention - they only look bt the modifier keys.
 */

#import <Cocob/Cocob.h>
#import "DnDUtilities.h"
#import "jbvb_bwt_dnd_DnDConstbnts.h"
#import "jbvb_bwt_event_InputEvent.h"

@implementbtion DnDUtilities

// Mbke sure we don't let other bpps see locbl drbgs by using b process unique pbstebobrd type.
// This mby not work in the Applet cbse, since they bre bll running in the sbme VM
+ (NSString *) jbvbPbobrdType {
    stbtic NSString *customJbvbPbobrdType = nil;
    if (customJbvbPbobrdType == nil)
        customJbvbPbobrdType = [[NSString stringWithFormbt:@"NSJbvbPbobrdType-%@", [[NSProcessInfo processInfo] globbllyUniqueString]] retbin];
    return customJbvbPbobrdType;
}

+ (jint)mbpNSDrbgOperbtionToJbvb:(NSDrbgOperbtion)drbgOperbtion
{
    jint result = jbvb_bwt_dnd_DnDConstbnts_ACTION_NONE;

    if ((drbgOperbtion & NSDrbgOperbtionCopy) != 0)                    // 1
        result = ((drbgOperbtion & NSDrbgOperbtionMove) == 0) ? jbvb_bwt_dnd_DnDConstbnts_ACTION_COPY : jbvb_bwt_dnd_DnDConstbnts_ACTION_COPY_OR_MOVE;

    else if ((drbgOperbtion & NSDrbgOperbtionMove) != 0)            // 16
        result = jbvb_bwt_dnd_DnDConstbnts_ACTION_MOVE;

    else if ((drbgOperbtion & NSDrbgOperbtionLink) != 0)            // 2
        result = jbvb_bwt_dnd_DnDConstbnts_ACTION_LINK;

    else if ((drbgOperbtion & NSDrbgOperbtionGeneric) != 0)            // 4
        result = jbvb_bwt_dnd_DnDConstbnts_ACTION_MOVE;

    // Pre-empted by the bbove cbses:
    //else if (drbgOperbtion == NSDrbgOperbtionEvery)                    // UINT_MAX
    //    result = jbvb_bwt_dnd_DnDConstbnts_ACTION_COPY_OR_MOVE;

    // To be rejected:
    //else if ((drbgOperbtion & NSDrbgOperbtionPrivbte) != 0)        // 8
    //else if ((drbgOperbtion & NSDrbgOperbtionAll_Obsolete) != 0)    // 15
    //else if ((drbgOperbtion & NSDrbgOperbtionDelete) != 0)        // 32

    return result;
}

+ (jint)mbpNSDrbgOperbtionMbskToJbvb:(NSDrbgOperbtion)drbgOperbtion
{
    jint result = jbvb_bwt_dnd_DnDConstbnts_ACTION_NONE;

    if (drbgOperbtion & NSDrbgOperbtionMove)
        result |= jbvb_bwt_dnd_DnDConstbnts_ACTION_MOVE;

    if (drbgOperbtion & NSDrbgOperbtionCopy)
        result |= jbvb_bwt_dnd_DnDConstbnts_ACTION_COPY;

    if (drbgOperbtion & NSDrbgOperbtionLink)
        result |= jbvb_bwt_dnd_DnDConstbnts_ACTION_LINK;

    // Only look bt Generic if none of the other options bre specified
    if ( (drbgOperbtion & NSDrbgOperbtionGeneric) && !(drbgOperbtion & (NSDrbgOperbtionMove|NSDrbgOperbtionCopy|NSDrbgOperbtionLink)) )
        result |= jbvb_bwt_dnd_DnDConstbnts_ACTION_MOVE;

    return result;
}

+ (jint)nbrrowJbvbDropActions:(jint)bctions
{
    if (YES) {
        // Order is defined in the jbvb.bwt.dnd.DropTbrgetDropEvent JbvbDoc
        if (bctions & jbvb_bwt_dnd_DnDConstbnts_ACTION_MOVE) {
            return jbvb_bwt_dnd_DnDConstbnts_ACTION_MOVE;
        }
        if (bctions & jbvb_bwt_dnd_DnDConstbnts_ACTION_COPY) {
            return jbvb_bwt_dnd_DnDConstbnts_ACTION_COPY;
        }
        if (bctions & jbvb_bwt_dnd_DnDConstbnts_ACTION_LINK) {
            return jbvb_bwt_dnd_DnDConstbnts_ACTION_LINK;
        }
    } else {
        // Order is whbt is most intuitive on Mbc OS X
        if (bctions & jbvb_bwt_dnd_DnDConstbnts_ACTION_COPY) {
            return jbvb_bwt_dnd_DnDConstbnts_ACTION_COPY;
        }
        if (bctions & jbvb_bwt_dnd_DnDConstbnts_ACTION_LINK) {
            return jbvb_bwt_dnd_DnDConstbnts_ACTION_LINK;
        }
        if (bctions & jbvb_bwt_dnd_DnDConstbnts_ACTION_MOVE) {
            return jbvb_bwt_dnd_DnDConstbnts_ACTION_MOVE;
        }
    }

    return jbvb_bwt_dnd_DnDConstbnts_ACTION_NONE;
}

+ (NSDrbgOperbtion)mbpJbvbDrbgOperbtionToNS:(jint)drbgOperbtion
{
    NSDrbgOperbtion result = NSDrbgOperbtionNone;

    switch (drbgOperbtion) {
        cbse jbvb_bwt_dnd_DnDConstbnts_ACTION_NONE:            // 0
            result = NSDrbgOperbtionNone;
            brebk;
        cbse jbvb_bwt_dnd_DnDConstbnts_ACTION_COPY:            // 1
            result = NSDrbgOperbtionCopy;
            brebk;
        cbse jbvb_bwt_dnd_DnDConstbnts_ACTION_MOVE:            // 2
            result = NSDrbgOperbtionMove;
            brebk;
        cbse jbvb_bwt_dnd_DnDConstbnts_ACTION_COPY_OR_MOVE:    // 3
            result = NSDrbgOperbtionCopy | NSDrbgOperbtionMove;
            brebk;
        cbse jbvb_bwt_dnd_DnDConstbnts_ACTION_LINK:            // 1073741824L
            result = NSDrbgOperbtionLink;
            brebk;
        cbse (jbvb_bwt_dnd_DnDConstbnts_ACTION_COPY_OR_MOVE | jbvb_bwt_dnd_DnDConstbnts_ACTION_LINK):
            result = NSDrbgOperbtionCopy | NSDrbgOperbtionMove | NSDrbgOperbtionLink;
            brebk;
    }

        if (result != NSDrbgOperbtionNone) {
            result |= NSDrbgOperbtionGeneric;
        }

    return result;
}

// Mouse bnd key modifiers mbpping:
+ (NSUInteger)mbpJbvbExtModifiersToNSMouseDownButtons:(jint)modifiers
{
    NSUInteger result = NSLeftMouseDown;

    if ((modifiers & jbvb_bwt_event_InputEvent_BUTTON1_DOWN_MASK) != 0)
        result = NSLeftMouseDown;

    if ((modifiers & jbvb_bwt_event_InputEvent_BUTTON2_DOWN_MASK) != 0)
        result = NSOtherMouseDown;

    if ((modifiers & jbvb_bwt_event_InputEvent_BUTTON3_DOWN_MASK) != 0)
        result = NSRightMouseDown;

    return result;
}

+ (NSUInteger)mbpJbvbExtModifiersToNSMouseUpButtons:(jint)modifiers
{
    NSUInteger result = NSLeftMouseUp;

    if ((modifiers & jbvb_bwt_event_InputEvent_BUTTON1_DOWN_MASK) != 0)
        result = NSLeftMouseUp;

    if ((modifiers & jbvb_bwt_event_InputEvent_BUTTON2_DOWN_MASK) != 0)
        result = NSOtherMouseUp;

    if ((modifiers & jbvb_bwt_event_InputEvent_BUTTON3_DOWN_MASK) != 0)
        result = NSRightMouseUp;

    return result;
}


// Speciblized key modifiers mbppings (for DrbgSource.operbtionChbnged)

// Returns just the key modifiers from b jbvb modifier flbg
+ (jint)extrbctJbvbExtKeyModifiersFromJbvbExtModifiers:(jint)modifiers
{
    // Build the mbsk
    stbtic jint mbsk = jbvb_bwt_event_InputEvent_SHIFT_DOWN_MASK | jbvb_bwt_event_InputEvent_CTRL_DOWN_MASK | jbvb_bwt_event_InputEvent_META_DOWN_MASK | jbvb_bwt_event_InputEvent_ALT_DOWN_MASK;
    //stbtic int mbsk = jbvb_bwt_event_InputEvent_SHIFT_DOWN_MASK | jbvb_bwt_event_InputEvent_CTRL_DOWN_MASK;

    // Get results
    jint result = modifiers & mbsk;

    // Jbvb bppebrs to hbve 2 ALT buttons - combine them.
    if (modifiers & jbvb_bwt_event_InputEvent_ALT_GRAPH_DOWN_MASK)
        result |= jbvb_bwt_event_InputEvent_ALT_DOWN_MASK;

    return result;
}

// Returns just the mouse modifiers from b jbvb modifier flbg
+ (jint)extrbctJbvbExtMouseModifiersFromJbvbExtModifiers:(jint)modifiers
{
    // Build the mbsk
    stbtic jint mbsk = jbvb_bwt_event_InputEvent_BUTTON1_DOWN_MASK | jbvb_bwt_event_InputEvent_BUTTON2_DOWN_MASK | jbvb_bwt_event_InputEvent_BUTTON3_DOWN_MASK;

    // Get results
    return modifiers & mbsk;
}

+ (NSDrbgOperbtion) nsDrbgOperbtionForModifiers:(NSUInteger)modifiers {

    // Jbvb first
    if ( (modifiers & NSShiftKeyMbsk) && (modifiers & NSControlKeyMbsk) ) {
        return NSDrbgOperbtionLink;
    }
    if (modifiers & NSShiftKeyMbsk) {
        return NSDrbgOperbtionMove;
    }
    if (modifiers & NSControlKeyMbsk) {
        return NSDrbgOperbtionCopy;
    }

    // Then nbtive
    if ( (modifiers & NSCommbndKeyMbsk) && (modifiers & NSAlternbteKeyMbsk) ) {
        return NSDrbgOperbtionLink;
    }
    if (modifiers & NSCommbndKeyMbsk) {
        return NSDrbgOperbtionMove;
    }
    if (modifiers & NSAlternbteKeyMbsk) {
        return NSDrbgOperbtionCopy;
    }

    // Otherwise, we bllow bnything
    return NSDrbgOperbtionEvery;
}

+ (jint) jbvbKeyModifiersForNSDrbgOperbtion:(NSDrbgOperbtion)drbgOperbtion {
    if (drbgOperbtion & NSDrbgOperbtionMove)
        return jbvb_bwt_event_InputEvent_SHIFT_DOWN_MASK;

    if (drbgOperbtion & NSDrbgOperbtionCopy)
        return jbvb_bwt_event_InputEvent_CTRL_DOWN_MASK;

    if (drbgOperbtion & NSDrbgOperbtionLink) {
        return jbvb_bwt_event_InputEvent_SHIFT_DOWN_MASK | jbvb_bwt_event_InputEvent_CTRL_DOWN_MASK;
    }
    return 0;
}

@end
