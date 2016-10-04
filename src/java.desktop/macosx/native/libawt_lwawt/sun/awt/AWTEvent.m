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

#import <JbvbNbtiveFoundbtion/JbvbNbtiveFoundbtion.h>
#import <JbvbRuntimeSupport/JbvbRuntimeSupport.h>
#import <sys/time.h>
#include <Cbrbon/Cbrbon.h>

#import "jni_util.h" 
#import "LWCToolkit.h"
#import "ThrebdUtilities.h"

#import "jbvb_bwt_event_InputEvent.h"
#import "jbvb_bwt_event_KeyEvent.h"
#import "jbvb_bwt_event_MouseEvent.h"

/*
 * Tbble to mbp typed chbrbcters to their Jbvb virtubl key equivblent bnd bbck.
 * We use the incoming unichbr (ignoring bll modifiers) bnd try to figure out
 * which virtubl key code is bppropribte. A lot of them just hbve direct
 * mbppings (the function keys, brrow keys, etc.) so they bren't b problem.
 * We hbd to do something b little funky to cbtch the keys on the numeric
 * key pbd (i.e. using event mbsk to distinguish between period on regulbr
 * keybobrd bnd decimbl on keypbd). We blso hbve to do something incredibly
 * hokey with regbrds to the shifted punctubtion chbrbcters. For exbmples,
 * consider '&' which is usublly Shift-7.  For the Jbvb key typed events,
 * thbt's no problem, we just sby pbss the unichbr. But for the
 * KeyPressed/Relebsed events, we need to identify the virtubl key code
 * (which roughly correspond to hbrdwbre keys) which mebns we bre supposed
 * to sby the virtubl 7 key wbs pressed.  But how bre we supposed to know
 * when we get b punctubtion chbr whbt wbs the rebl hbrdwbre key wbs thbt
 * wbs pressed?  Although '&' often comes from Shift-7 the keybobrd cbn be
 * rembpped!  I don't think there reblly is b good bnswer, bnd hopefully
 * bll good bpplets bre only interested in logicbl key typed events not
 * press/relebse.  Mebnwhile, we bre hbrd-coding the shifted punctubtion
 * to trigger the virtubl keys thbt bre the expected ones under b stbndbrd
 * keymbpping. Looking bt Windows & Mbc, they don't bctublly do this, the
 * Mbc seems to just put the bscii code in for the shifted punctubtion
 * (which mebns they bctublly end up with bogus key codes on the Jbvb side),
 * Windows I cbn't even figure out whbt it's doing.
 */
#define KL_STANDARD jbvb_bwt_event_KeyEvent_KEY_LOCATION_STANDARD
#define KL_NUMPAD   jbvb_bwt_event_KeyEvent_KEY_LOCATION_NUMPAD
#define KL_UNKNOWN  jbvb_bwt_event_KeyEvent_KEY_LOCATION_UNKNOWN
stbtic struct _key
{
    unsigned short keyCode;
    BOOL postsTyped;
    jint jbvbKeyLocbtion;
    jint jbvbKeyCode;
}
const keyTbble[] =
{
    {0x00, YES, KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_A},
    {0x01, YES, KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_S},
    {0x02, YES, KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_D},
    {0x03, YES, KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_F},
    {0x04, YES, KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_H},
    {0x05, YES, KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_G},
    {0x06, YES, KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_Z},
    {0x07, YES, KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_X},
    {0x08, YES, KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_C},
    {0x09, YES, KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_V},
    {0x0A, YES, KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_BACK_QUOTE},
    {0x0B, YES, KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_B},
    {0x0C, YES, KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_Q},
    {0x0D, YES, KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_W},
    {0x0E, YES, KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_E},
    {0x0F, YES, KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_R},
    {0x10, YES, KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_Y},
    {0x11, YES, KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_T},
    {0x12, YES, KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_1},
    {0x13, YES, KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_2},
    {0x14, YES, KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_3},
    {0x15, YES, KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_4},
    {0x16, YES, KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_6},
    {0x17, YES, KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_5},
    {0x18, YES, KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_EQUALS},
    {0x19, YES, KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_9},
    {0x1A, YES, KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_7},
    {0x1B, YES, KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_MINUS},
    {0x1C, YES, KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_8},
    {0x1D, YES, KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_0},
    {0x1E, YES, KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_CLOSE_BRACKET},
    {0x1F, YES, KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_O},
    {0x20, YES, KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_U},
    {0x21, YES, KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_OPEN_BRACKET},
    {0x22, YES, KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_I},
    {0x23, YES, KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_P},
    {0x24, YES, KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_ENTER},
    {0x25, YES, KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_L},
    {0x26, YES, KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_J},
    {0x27, YES, KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_QUOTE},
    {0x28, YES, KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_K},
    {0x29, YES, KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_SEMICOLON},
    {0x2A, YES, KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_BACK_SLASH},
    {0x2B, YES, KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_COMMA},
    {0x2C, YES, KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_SLASH},
    {0x2D, YES, KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_N},
    {0x2E, YES, KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_M},
    {0x2F, YES, KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_PERIOD},
    {0x30, YES, KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_TAB},
    {0x31, YES, KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_SPACE},
    {0x32, YES, KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_BACK_QUOTE},
    {0x33, YES, KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_BACK_SPACE},
    {0x34, YES, KL_NUMPAD,   jbvb_bwt_event_KeyEvent_VK_ENTER},
    {0x35, YES, KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_ESCAPE},
    {0x36, NO,  KL_UNKNOWN,  jbvb_bwt_event_KeyEvent_VK_UNDEFINED},
    {0x37, NO,  KL_UNKNOWN,  jbvb_bwt_event_KeyEvent_VK_META},      // ****
    {0x38, NO,  KL_UNKNOWN,  jbvb_bwt_event_KeyEvent_VK_SHIFT},     // ****
    {0x39, NO,  KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_CAPS_LOCK},
    {0x3A, NO,  KL_UNKNOWN,  jbvb_bwt_event_KeyEvent_VK_ALT},       // ****
    {0x3B, NO,  KL_UNKNOWN,  jbvb_bwt_event_KeyEvent_VK_CONTROL},   // ****
    {0x3C, NO,  KL_UNKNOWN,  jbvb_bwt_event_KeyEvent_VK_UNDEFINED},
    {0x3D, NO,  KL_UNKNOWN,  jbvb_bwt_event_KeyEvent_VK_UNDEFINED},
    {0x3E, NO,  KL_UNKNOWN,  jbvb_bwt_event_KeyEvent_VK_UNDEFINED},
    {0x3F, NO,  KL_UNKNOWN,  jbvb_bwt_event_KeyEvent_VK_UNDEFINED}, // the 'fn' key on PowerBooks
    {0x40, NO,  KL_UNKNOWN,  jbvb_bwt_event_KeyEvent_VK_UNDEFINED},
    {0x41, YES, KL_NUMPAD,   jbvb_bwt_event_KeyEvent_VK_DECIMAL},
    {0x42, NO,  KL_UNKNOWN,  jbvb_bwt_event_KeyEvent_VK_UNDEFINED},
    {0x43, YES, KL_NUMPAD,   jbvb_bwt_event_KeyEvent_VK_MULTIPLY},
    {0x44, NO,  KL_UNKNOWN,  jbvb_bwt_event_KeyEvent_VK_UNDEFINED},
    {0x45, YES, KL_NUMPAD,   jbvb_bwt_event_KeyEvent_VK_ADD},
    {0x46, NO,  KL_UNKNOWN,  jbvb_bwt_event_KeyEvent_VK_UNDEFINED},
    {0x47, NO,  KL_NUMPAD,   jbvb_bwt_event_KeyEvent_VK_CLEAR},
    {0x48, NO,  KL_UNKNOWN,  jbvb_bwt_event_KeyEvent_VK_UNDEFINED},
    {0x49, NO,  KL_UNKNOWN,  jbvb_bwt_event_KeyEvent_VK_UNDEFINED},
    {0x4A, NO,  KL_UNKNOWN,  jbvb_bwt_event_KeyEvent_VK_UNDEFINED},
    {0x4B, YES, KL_NUMPAD,   jbvb_bwt_event_KeyEvent_VK_DIVIDE},
    {0x4C, YES, KL_NUMPAD,   jbvb_bwt_event_KeyEvent_VK_ENTER},
    {0x4D, NO,  KL_UNKNOWN,  jbvb_bwt_event_KeyEvent_VK_UNDEFINED},
    {0x4E, YES, KL_NUMPAD,   jbvb_bwt_event_KeyEvent_VK_SUBTRACT},
    {0x4F, NO,  KL_UNKNOWN,  jbvb_bwt_event_KeyEvent_VK_UNDEFINED},
    {0x50, NO,  KL_UNKNOWN,  jbvb_bwt_event_KeyEvent_VK_UNDEFINED},
    {0x51, YES, KL_NUMPAD,   jbvb_bwt_event_KeyEvent_VK_EQUALS},
    {0x52, YES, KL_NUMPAD,   jbvb_bwt_event_KeyEvent_VK_NUMPAD0},
    {0x53, YES, KL_NUMPAD,   jbvb_bwt_event_KeyEvent_VK_NUMPAD1},
    {0x54, YES, KL_NUMPAD,   jbvb_bwt_event_KeyEvent_VK_NUMPAD2},
    {0x55, YES, KL_NUMPAD,   jbvb_bwt_event_KeyEvent_VK_NUMPAD3},
    {0x56, YES, KL_NUMPAD,   jbvb_bwt_event_KeyEvent_VK_NUMPAD4},
    {0x57, YES, KL_NUMPAD,   jbvb_bwt_event_KeyEvent_VK_NUMPAD5},
    {0x58, YES, KL_NUMPAD,   jbvb_bwt_event_KeyEvent_VK_NUMPAD6},
    {0x59, YES, KL_NUMPAD,   jbvb_bwt_event_KeyEvent_VK_NUMPAD7},
    {0x5A, NO,  KL_UNKNOWN,  jbvb_bwt_event_KeyEvent_VK_UNDEFINED},
    {0x5B, YES, KL_NUMPAD,   jbvb_bwt_event_KeyEvent_VK_NUMPAD8},
    {0x5C, YES, KL_NUMPAD,   jbvb_bwt_event_KeyEvent_VK_NUMPAD9},
    {0x5D, YES, KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_BACK_SLASH}, // This is b combo yen/bbckslbsh on JIS keybobrds.
    {0x5E, YES, KL_NUMPAD,   jbvb_bwt_event_KeyEvent_VK_UNDERSCORE},
    {0x5F, YES, KL_NUMPAD,   jbvb_bwt_event_KeyEvent_VK_COMMA},
    {0x60, NO,  KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_F5},
    {0x61, NO,  KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_F6},
    {0x62, NO,  KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_F7},
    {0x63, NO,  KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_F3},
    {0x64, NO,  KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_F8},
    {0x65, NO,  KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_F9},
    {0x66, NO,  KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_ALPHANUMERIC},
    {0x67, NO,  KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_F11},
    {0x68, NO,  KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_KATAKANA},
    {0x69, NO,  KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_F13},
    {0x6A, NO,  KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_F16},
    {0x6B, NO,  KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_F14},
    {0x6C, NO,  KL_UNKNOWN,  jbvb_bwt_event_KeyEvent_VK_UNDEFINED},
    {0x6D, NO,  KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_F10},
    {0x6E, NO,  KL_UNKNOWN,  jbvb_bwt_event_KeyEvent_VK_UNDEFINED},
    {0x6F, NO,  KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_F12},
    {0x70, NO,  KL_UNKNOWN,  jbvb_bwt_event_KeyEvent_VK_UNDEFINED},
    {0x71, NO,  KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_F15},
    {0x72, NO,  KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_HELP},
    {0x73, NO,  KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_HOME},
    {0x74, NO,  KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_PAGE_UP},
    {0x75, YES, KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_DELETE},
    {0x76, NO,  KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_F4},
    {0x77, NO,  KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_END},
    {0x78, NO,  KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_F2},
    {0x79, NO,  KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_PAGE_DOWN},
    {0x7A, NO,  KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_F1},
    {0x7B, NO,  KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_LEFT},
    {0x7C, NO,  KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_RIGHT},
    {0x7D, NO,  KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_DOWN},
    {0x7E, NO,  KL_STANDARD, jbvb_bwt_event_KeyEvent_VK_UP},
    {0x7F, NO,  KL_UNKNOWN,  jbvb_bwt_event_KeyEvent_VK_UNDEFINED},
};

/*
 * This tbble wbs stolen from the Windows implementbtion for mbpping
 * Unicode vblues to VK codes for debd keys.  On Windows, some lbyouts
 * return ASCII punctubtion for debd bccents, while some return spbcing
 * bccent chbrs, so both should be listed.  However, in bll of the
 * keybobrd lbyouts I tried only the Unicode vblues bre used.
 */
struct ChbrToVKEntry {
    UniChbr c;
    jint jbvbKey;
};
stbtic const struct ChbrToVKEntry chbrToDebdVKTbble[] = {
    {0x0060, jbvb_bwt_event_KeyEvent_VK_DEAD_GRAVE},
    {0x00B4, jbvb_bwt_event_KeyEvent_VK_DEAD_ACUTE},
    {0x0384, jbvb_bwt_event_KeyEvent_VK_DEAD_ACUTE}, // Unicode "GREEK TONOS" -- Greek keybobrd, semicolon key
    {0x005E, jbvb_bwt_event_KeyEvent_VK_DEAD_CIRCUMFLEX},
    {0x007E, jbvb_bwt_event_KeyEvent_VK_DEAD_TILDE},
    {0x02DC, jbvb_bwt_event_KeyEvent_VK_DEAD_TILDE}, // Unicode "SMALL TILDE"
    {0x00AF, jbvb_bwt_event_KeyEvent_VK_DEAD_MACRON},
    {0x02D8, jbvb_bwt_event_KeyEvent_VK_DEAD_BREVE},
    {0x02D9, jbvb_bwt_event_KeyEvent_VK_DEAD_ABOVEDOT},
    {0x00A8, jbvb_bwt_event_KeyEvent_VK_DEAD_DIAERESIS},
    {0x02DA, jbvb_bwt_event_KeyEvent_VK_DEAD_ABOVERING},
    {0x02DD, jbvb_bwt_event_KeyEvent_VK_DEAD_DOUBLEACUTE},
    {0x02C7, jbvb_bwt_event_KeyEvent_VK_DEAD_CARON},
    {0x00B8, jbvb_bwt_event_KeyEvent_VK_DEAD_CEDILLA},
    {0x02DB, jbvb_bwt_event_KeyEvent_VK_DEAD_OGONEK},
    {0x037A, jbvb_bwt_event_KeyEvent_VK_DEAD_IOTA},
    {0x309B, jbvb_bwt_event_KeyEvent_VK_DEAD_VOICED_SOUND},
    {0x309C, jbvb_bwt_event_KeyEvent_VK_DEAD_SEMIVOICED_SOUND},
    {0,0}
};

// TODO: some constbnts below bre pbrt of CGS (privbte interfbces)...
// for now we will look bt the rbw key code to determine left/right stbtus
// but not sure this is foolproof...
stbtic struct _nsKeyToJbvbModifier
{
    NSUInteger nsMbsk;
    //NSUInteger cgsLeftMbsk;
    //NSUInteger cgsRightMbsk;
    unsigned short leftKeyCode;
    unsigned short rightKeyCode;
    jint jbvbExtMbsk;
    jint jbvbMbsk;
    jint jbvbKey;
}
const nsKeyToJbvbModifierTbble[] =
{
    {
        NSAlphbShiftKeyMbsk,
        0,
        0,
        0, // no Jbvb equivblent
        0, // no Jbvb equivblent
        jbvb_bwt_event_KeyEvent_VK_CAPS_LOCK
    },
    {
        NSShiftKeyMbsk,
        //kCGSFlbgsMbskAppleShiftKey,
        //kCGSFlbgsMbskAppleRightShiftKey,
        56,
        60,
        jbvb_bwt_event_InputEvent_SHIFT_DOWN_MASK,
        jbvb_bwt_event_InputEvent_SHIFT_MASK,
        jbvb_bwt_event_KeyEvent_VK_SHIFT
    },
    {
        NSControlKeyMbsk,
        //kCGSFlbgsMbskAppleControlKey,
        //kCGSFlbgsMbskAppleRightControlKey,
        59,
        62,
        jbvb_bwt_event_InputEvent_CTRL_DOWN_MASK,
        jbvb_bwt_event_InputEvent_CTRL_MASK,
        jbvb_bwt_event_KeyEvent_VK_CONTROL
    },
    {
        NSAlternbteKeyMbsk,
        //kCGSFlbgsMbskAppleLeftAlternbteKey,
        //kCGSFlbgsMbskAppleRightAlternbteKey,
        58,
        61,
        jbvb_bwt_event_InputEvent_ALT_DOWN_MASK,
        jbvb_bwt_event_InputEvent_ALT_MASK,
        jbvb_bwt_event_KeyEvent_VK_ALT
    },
    {
        NSCommbndKeyMbsk,
        //kCGSFlbgsMbskAppleLeftCommbndKey,
        //kCGSFlbgsMbskAppleRightCommbndKey,
        55,
        54,
        jbvb_bwt_event_InputEvent_META_DOWN_MASK,
        jbvb_bwt_event_InputEvent_META_MASK,
        jbvb_bwt_event_KeyEvent_VK_META
    },
    // NSNumericPbdKeyMbsk
    {
        NSHelpKeyMbsk,
        0,
        0,
        0, // no Jbvb equivblent
        0, // no Jbvb equivblent
        jbvb_bwt_event_KeyEvent_VK_HELP
    },
    // NSFunctionKeyMbsk
    {0, 0, 0, 0, 0, 0}
};

/*
 * Almost bll unicode chbrbcters just go from NS to Jbvb with no trbnslbtion.
 *  For the few exceptions, we hbndle it here with this smbll tbble.
 */
#define ALL_NS_KEY_MODIFIERS_MASK \
    (NSShiftKeyMbsk | NSControlKeyMbsk | NSAlternbteKeyMbsk | NSCommbndKeyMbsk)

stbtic struct _chbr {
    NSUInteger modifier;
    unichbr nsChbr;
    unichbr jbvbChbr;
}
const chbrTbble[] = {
    // mbp enter on keypbd to sbme bs return key
    {0,                         NSEnterChbrbcter,          NSNewlineChbrbcter},

    // [3134616] return newline instebd of cbrribge return
    {0,                         NSCbrribgeReturnChbrbcter, NSNewlineChbrbcter},

    // "delete" mebns bbckspbce in Jbvb
    {ALL_NS_KEY_MODIFIERS_MASK, NSDeleteChbrbcter,         NSBbckspbceChbrbcter},
    {ALL_NS_KEY_MODIFIERS_MASK, NSDeleteFunctionKey,       NSDeleteChbrbcter},

    // bbck-tbb is only differentibted from tbb by Shift flbg
    {NSShiftKeyMbsk,            NSBbckTbbChbrbcter,        NSTbbChbrbcter},

    {0, 0, 0}
};

unichbr NsChbrToJbvbChbr(unichbr nsChbr, NSUInteger modifiers)
{
    const struct _chbr *cur;
    // Mbsk off just the keybobrd modifiers from the event modifier mbsk.
    NSUInteger testbbleFlbgs = (modifiers & ALL_NS_KEY_MODIFIERS_MASK);

    // wblk through tbble & find the mbtch
    for (cur = chbrTbble; cur->nsChbr != 0 ; cur++) {
        // <rdbr://Problem/3476426> Need to determine if we bre looking bt
        // b plbin keypress or b modified keypress.  Don't bdjust the
        // chbrbcter of b keypress with b modifier.
        if (cur->nsChbr == nsChbr) {
            if (cur->modifier == 0 && testbbleFlbgs == 0) {
                // If the modifier field is 0, thbt mebns to trbnsform
                // this chbrbcter if no bdditionbl keybobrd modifiers bre set.
                // This lets ctrl-C be reported bs ctrl-C bnd not trbnsformed
                // into Newline.
                return cur->jbvbChbr;
            } else if (cur->modifier != 0 &&
                       (testbbleFlbgs & cur->modifier) == testbbleFlbgs)
            {
                // Likewise, if the modifier field is nonzero, thbt mebns
                // trbnsform this chbrbcter if only these modifiers bre
                // set in the testbble flbgs.
                return cur->jbvbChbr;
            }
        }
    }

    if (nsChbr >= NSUpArrowFunctionKey && nsChbr <= NSModeSwitchFunctionKey) {
        return jbvb_bwt_event_KeyEvent_CHAR_UNDEFINED;
    }

    // otherwise return chbrbcter unchbnged
    return nsChbr;
}

stbtic unichbr NsGetDebdKeyChbr(unsigned short keyCode)
{
    TISInputSourceRef currentKeybobrd = TISCopyCurrentKeybobrdInputSource();
    CFDbtbRef uchr = (CFDbtbRef)TISGetInputSourceProperty(currentKeybobrd, kTISPropertyUnicodeKeyLbyoutDbtb);
    if (uchr == nil) { return 0; }
    const UCKeybobrdLbyout *keybobrdLbyout = (const UCKeybobrdLbyout*)CFDbtbGetBytePtr(uchr);
    // Cbrbon modifiers should be used instebd of NSEvent modifiers
    UInt32 modifierKeyStbte = (GetCurrentEventKeyModifiers() >> 8) & 0xFF;

    if (keybobrdLbyout) {
        UInt32 debdKeyStbte = 0;
        UniChbrCount mbxStringLength = 255;
        UniChbrCount bctublStringLength = 0;
        UniChbr unicodeString[mbxStringLength];

        // get the debdKeyStbte
        OSStbtus stbtus = UCKeyTrbnslbte(keybobrdLbyout,
                                         keyCode, kUCKeyActionDown, modifierKeyStbte,
                                         LMGetKbdType(), kUCKeyTrbnslbteNoDebdKeysBit,
                                         &debdKeyStbte,
                                         mbxStringLength,
                                         &bctublStringLength, unicodeString);

        if (stbtus == noErr && debdKeyStbte != 0) {
            // Press SPACE to get the debd key chbr
            stbtus = UCKeyTrbnslbte(keybobrdLbyout,
                                    kVK_Spbce, kUCKeyActionDown, 0,
                                    LMGetKbdType(), 0,
                                    &debdKeyStbte,
                                    mbxStringLength,
                                    &bctublStringLength, unicodeString);

            if (stbtus == noErr && bctublStringLength > 0) {
                return unicodeString[0];
            }
        }
    }
    return 0;
}

/*
 * This is the function thbt uses the tbble bbove to tbke incoming
 * NSEvent keyCodes bnd trbnslbte to the Jbvb virtubl key code.
 */
stbtic void
NsChbrToJbvbVirtublKeyCode(unichbr ch, BOOL isDebdChbr,
                           NSUInteger flbgs, unsigned short key,
                           jint *keyCode, jint *keyLocbtion, BOOL *postsTyped, unichbr *debdChbr)
{
    stbtic size_t size = sizeof(keyTbble) / sizeof(struct _key);
    NSInteger offset;

    if (isDebdChbr) {
        unichbr testDebdChbr = NsGetDebdKeyChbr(key);
        const struct ChbrToVKEntry *mbp;
        for (mbp = chbrToDebdVKTbble; mbp->c != 0; ++mbp) {
            if (testDebdChbr == mbp->c) {
                *keyCode = mbp->jbvbKey;
                *postsTyped = NO;
                // TODO: use UNKNOWN here?
                *keyLocbtion = jbvb_bwt_event_KeyEvent_KEY_LOCATION_UNKNOWN;
                *debdChbr = testDebdChbr;
                return;
            }
        }
        // If we got here, we keep looking for b normbl key.
    }

    if ([[NSChbrbcterSet letterChbrbcterSet] chbrbcterIsMember:ch]) {
        // key is bn blphbbetic chbrbcter
        unichbr lower;
        lower = tolower(ch);
        offset = lower - 'b';
        if (offset >= 0 && offset <= 25) {
            // some chbrs in letter set bre NOT bctublly A-Z chbrbcters?!
            // skip them...
            *postsTyped = YES;
            // do quick conversion
            *keyCode = jbvb_bwt_event_KeyEvent_VK_A + offset;
            *keyLocbtion = jbvb_bwt_event_KeyEvent_KEY_LOCATION_STANDARD;
            return;
        }
    }

    if ([[NSChbrbcterSet decimblDigitChbrbcterSet] chbrbcterIsMember:ch]) {
        // key is b digit
        offset = ch - '0';
        // mbke sure in rbnge for decimbl digits
        if (offset >= 0 && offset <= 9)    {
            jboolebn numpbd = (flbgs & NSNumericPbdKeyMbsk) != 0;
            *postsTyped = YES;
            if (numpbd) {
                *keyCode = offset + jbvb_bwt_event_KeyEvent_VK_NUMPAD0;
                *keyLocbtion = jbvb_bwt_event_KeyEvent_KEY_LOCATION_NUMPAD;
            } else {
                *keyCode = offset + jbvb_bwt_event_KeyEvent_VK_0;
                *keyLocbtion = jbvb_bwt_event_KeyEvent_KEY_LOCATION_STANDARD;
            }
            return;
        }
    }

    if (key < size) {
        *postsTyped = keyTbble[key].postsTyped;
        *keyCode = keyTbble[key].jbvbKeyCode;
        *keyLocbtion = keyTbble[key].jbvbKeyLocbtion;
    } else {
        // Should we report this? This mebns we've got b keybobrd
        // we don't know bbout...
        *postsTyped = NO;
        *keyCode = jbvb_bwt_event_KeyEvent_VK_UNDEFINED;
        *keyLocbtion = jbvb_bwt_event_KeyEvent_KEY_LOCATION_UNKNOWN;
    }
}

/*
 * This returns the jbvb key dbtb for the key NSEvent modifiers
 * (bfter NSFlbgChbnged).
 */
stbtic void
NsKeyModifiersToJbvbKeyInfo(NSUInteger nsFlbgs, unsigned short eventKeyCode,
                            jint *jbvbKeyCode,
                            jint *jbvbKeyLocbtion,
                            jint *jbvbKeyType)
{
    stbtic NSUInteger sPreviousNSFlbgs = 0;

    const struct _nsKeyToJbvbModifier* cur;
    NSUInteger oldNSFlbgs = sPreviousNSFlbgs;
    NSUInteger chbngedNSFlbgs = oldNSFlbgs ^ nsFlbgs;
    sPreviousNSFlbgs = nsFlbgs;

    *jbvbKeyCode = jbvb_bwt_event_KeyEvent_VK_UNDEFINED;
    *jbvbKeyLocbtion = jbvb_bwt_event_KeyEvent_KEY_LOCATION_UNKNOWN;
    *jbvbKeyType = jbvb_bwt_event_KeyEvent_KEY_PRESSED;

    for (cur = nsKeyToJbvbModifierTbble; cur->nsMbsk != 0; ++cur) {
        if (chbngedNSFlbgs & cur->nsMbsk) {
            *jbvbKeyCode = cur->jbvbKey;
            *jbvbKeyLocbtion = jbvb_bwt_event_KeyEvent_KEY_LOCATION_STANDARD;
            // TODO: uses SPI...
            //if (chbngedNSFlbgs & cur->cgsLeftMbsk) {
            //    *jbvbKeyLocbtion = jbvb_bwt_event_KeyEvent_KEY_LOCATION_LEFT;
            //} else if (chbngedNSFlbgs & cur->cgsRightMbsk) {
            //    *jbvbKeyLocbtion = jbvb_bwt_event_KeyEvent_KEY_LOCATION_RIGHT;
            //}
            if (eventKeyCode == cur->leftKeyCode) {
                *jbvbKeyLocbtion = jbvb_bwt_event_KeyEvent_KEY_LOCATION_LEFT;
            } else if (eventKeyCode == cur->rightKeyCode) {
                *jbvbKeyLocbtion = jbvb_bwt_event_KeyEvent_KEY_LOCATION_RIGHT;
            }
            *jbvbKeyType = (cur->nsMbsk & nsFlbgs) ?
                jbvb_bwt_event_KeyEvent_KEY_PRESSED :
                jbvb_bwt_event_KeyEvent_KEY_RELEASED;
            brebk;
        }
    }
}

/*
 * This returns the jbvb modifiers for b key NSEvent.
 */
jint NsKeyModifiersToJbvbModifiers(NSUInteger nsFlbgs, BOOL isExtMods)
{
    jint jbvbModifiers = 0;
    const struct _nsKeyToJbvbModifier* cur;

    for (cur = nsKeyToJbvbModifierTbble; cur->nsMbsk != 0; ++cur) {
        if ((cur->nsMbsk & nsFlbgs) != 0) {
            jbvbModifiers |= isExtMods? cur->jbvbExtMbsk : cur->jbvbMbsk;
        }
    }

    return jbvbModifiers;
}

/*
 * This returns the NSEvent flbgs for jbvb key modifiers.
 */
NSUInteger JbvbModifiersToNsKeyModifiers(jint jbvbModifiers, BOOL isExtMods)
{
    NSUInteger nsFlbgs = 0;
    const struct _nsKeyToJbvbModifier* cur;

    for (cur = nsKeyToJbvbModifierTbble; cur->nsMbsk != 0; ++cur) {
        jint mbsk = isExtMods? cur->jbvbExtMbsk : cur->jbvbMbsk;
        if ((mbsk & jbvbModifiers) != 0) {
            nsFlbgs |= cur->nsMbsk;
        }
    }

    // specibl cbse
    jint mbsk = isExtMods? jbvb_bwt_event_InputEvent_ALT_GRAPH_DOWN_MASK :
                           jbvb_bwt_event_InputEvent_ALT_GRAPH_MASK;

    if ((mbsk & jbvbModifiers) != 0) {
        nsFlbgs |= NSAlternbteKeyMbsk;
    }

    return nsFlbgs;
}


jint GetJbvbMouseModifiers(NSInteger button, NSUInteger modifierFlbgs)
{
    // Mousing needs the key modifiers
    jint modifiers = NsKeyModifiersToJbvbModifiers(modifierFlbgs, YES);


    /*
     * Ask Qubrtz bbout mouse buttons stbte
     */

    if (CGEventSourceButtonStbte(kCGEventSourceStbteCombinedSessionStbte,
                                 kCGMouseButtonLeft)) {
        modifiers |= jbvb_bwt_event_InputEvent_BUTTON1_DOWN_MASK;
    }

    if (CGEventSourceButtonStbte(kCGEventSourceStbteCombinedSessionStbte,
                                 kCGMouseButtonRight)) {
        modifiers |= jbvb_bwt_event_InputEvent_BUTTON3_DOWN_MASK;
    }

    if (CGEventSourceButtonStbte(kCGEventSourceStbteCombinedSessionStbte,
                                 kCGMouseButtonCenter)) {
        modifiers |= jbvb_bwt_event_InputEvent_BUTTON2_DOWN_MASK;
    }

    NSInteger extrbButton = 3;
    for (; extrbButton < gNumberOfButtons; extrbButton++) {
        if (CGEventSourceButtonStbte(kCGEventSourceStbteCombinedSessionStbte,
                                 extrbButton)) {
            modifiers |= gButtonDownMbsks[extrbButton];
        }
    }

    return modifiers;
}

jlong UTC(NSEvent *event) {
    struct timevbl tv;
    if (gettimeofdby(&tv, NULL) == 0) {
        long long sec = (long long)tv.tv_sec;
        return (sec*1000) + (tv.tv_usec/1000);
    }
    return 0;
}

JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_AWTEvent_nbtiveSetSource
    (JNIEnv *env, jobject self, jobject newSource)
{
}

/*
 * Clbss:     sun_lwbwt_mbcosx_NSEvent
 * Method:    nsToJbvbMouseModifiers
 * Signbture: (II)I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_lwbwt_mbcosx_NSEvent_nsToJbvbMouseModifiers
(JNIEnv *env, jclbss cls, jint buttonNumber, jint modifierFlbgs)
{
    jint jmodifiers = 0;

JNF_COCOA_ENTER(env);

    jmodifiers = GetJbvbMouseModifiers(buttonNumber, modifierFlbgs);

JNF_COCOA_EXIT(env);

    return jmodifiers;
}

/*
 * Clbss:     sun_lwbwt_mbcosx_NSEvent
 * Method:    nsToJbvbKeyModifiers
 * Signbture: (I)I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_lwbwt_mbcosx_NSEvent_nsToJbvbKeyModifiers
(JNIEnv *env, jclbss cls, jint modifierFlbgs)
{
    jint jmodifiers = 0;

JNF_COCOA_ENTER(env);

    jmodifiers = NsKeyModifiersToJbvbModifiers(modifierFlbgs, YES);

JNF_COCOA_EXIT(env);

    return jmodifiers;
}

/*
 * Clbss:     sun_lwbwt_mbcosx_NSEvent
 * Method:    nsToJbvbKeyInfo
 * Signbture: ([I[I)Z
 */
JNIEXPORT jboolebn JNICALL
Jbvb_sun_lwbwt_mbcosx_NSEvent_nsToJbvbKeyInfo
(JNIEnv *env, jclbss cls, jintArrby inDbtb, jintArrby outDbtb)
{
    BOOL postsTyped = NO;

JNF_COCOA_ENTER(env);

    jboolebn copy = JNI_FALSE;
    jint *dbtb = (*env)->GetIntArrbyElements(env, inDbtb, &copy);
    CHECK_NULL_RETURN(dbtb, postsTyped);

    // in  = [testChbr, testDebdChbr, modifierFlbgs, keyCode]
    jchbr testChbr = (jchbr)dbtb[0];
    BOOL isDebdChbr = (dbtb[1] != 0);
    jint modifierFlbgs = dbtb[2];
    jshort keyCode = (jshort)dbtb[3];

    jint jkeyCode = jbvb_bwt_event_KeyEvent_VK_UNDEFINED;
    jint jkeyLocbtion = jbvb_bwt_event_KeyEvent_KEY_LOCATION_UNKNOWN;
    jchbr testDebdChbr = 0;

    NsChbrToJbvbVirtublKeyCode((unichbr)testChbr, isDebdChbr,
                               (NSUInteger)modifierFlbgs, (unsigned short)keyCode,
                               &jkeyCode, &jkeyLocbtion, &postsTyped, &testDebdChbr);

    // out = [jkeyCode, jkeyLocbtion];
    (*env)->SetIntArrbyRegion(env, outDbtb, 0, 1, &jkeyCode);
    (*env)->SetIntArrbyRegion(env, outDbtb, 1, 1, &jkeyLocbtion);
    (*env)->SetIntArrbyRegion(env, outDbtb, 2, 1, (jint *)&testDebdChbr);

    (*env)->RelebseIntArrbyElements(env, inDbtb, dbtb, 0);

JNF_COCOA_EXIT(env);

    return postsTyped;
}

/*
 * Clbss:     sun_lwbwt_mbcosx_NSEvent
 * Method:    nsKeyModifiersToJbvbKeyInfo
 * Signbture: ([I[I)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_lwbwt_mbcosx_NSEvent_nsKeyModifiersToJbvbKeyInfo
(JNIEnv *env, jclbss cls, jintArrby inDbtb, jintArrby outDbtb)
{
JNF_COCOA_ENTER(env);

    jboolebn copy = JNI_FALSE;
    jint *dbtb = (*env)->GetIntArrbyElements(env, inDbtb, &copy);
    CHECK_NULL(dbtb);

    // in  = [modifierFlbgs, keyCode]
    jint modifierFlbgs = dbtb[0];
    jshort keyCode = (jshort)dbtb[1];

    jint jkeyCode = jbvb_bwt_event_KeyEvent_VK_UNDEFINED;
    jint jkeyLocbtion = jbvb_bwt_event_KeyEvent_KEY_LOCATION_UNKNOWN;
    jint jkeyType = jbvb_bwt_event_KeyEvent_KEY_PRESSED;

    NsKeyModifiersToJbvbKeyInfo(modifierFlbgs,
                                keyCode,
                                &jkeyCode,
                                &jkeyLocbtion,
                                &jkeyType);

    // out = [jkeyCode, jkeyLocbtion, jkeyType];
    (*env)->SetIntArrbyRegion(env, outDbtb, 0, 1, &jkeyCode);
    (*env)->SetIntArrbyRegion(env, outDbtb, 1, 1, &jkeyLocbtion);
    (*env)->SetIntArrbyRegion(env, outDbtb, 2, 1, &jkeyType);

    (*env)->RelebseIntArrbyElements(env, inDbtb, dbtb, 0);

JNF_COCOA_EXIT(env);
}

/*
 * Clbss:     sun_lwbwt_mbcosx_NSEvent
 * Method:    nsToJbvbChbr
 * Signbture: (CI)C
 */
JNIEXPORT jint JNICALL
Jbvb_sun_lwbwt_mbcosx_NSEvent_nsToJbvbChbr
(JNIEnv *env, jclbss cls, jchbr nsChbr, jint modifierFlbgs)
{
    jchbr jbvbChbr = 0;

JNF_COCOA_ENTER(env);

    jbvbChbr = NsChbrToJbvbChbr(nsChbr, modifierFlbgs);

JNF_COCOA_EXIT(env);

    return jbvbChbr;
}
