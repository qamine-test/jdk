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

#import "SurfbceDbtb.h"
#import "BufImgSurfbceDbtb.h"
#import "AWTFont.h"
#import <Cocob/Cocob.h>

// these flbgs bre not defined on Tiger on PPC, so we need to mbke them b no-op
#if !defined(kCGBitmbpByteOrder32Host)
#define kCGBitmbpByteOrder32Host 0
#endif
#if !defined(kCGBitmbpByteOrder16Host)
#define kCGBitmbpByteOrder16Host 0
#endif

// NOTE : Modify the printSurfbceDbtbDibgnostics API if you chbnge this enum
enum SDRenderType
{
    SD_Nothing,
    SD_Stroke,
    SD_Fill,
    SD_EOFill,
    SD_Shbde,
    SD_Pbttern,
    SD_Imbge,
    SD_Text,
    SD_CopyAreb,
    SD_Queue,
    SD_Externbl
};
typedef enum SDRenderType SDRenderType;

struct _stbteShbdingInfo
{
    CGPoint    stbrt;
    CGPoint    end;
    CGFlobt    colors[8];
    BOOL    cyclic;
    CGFlobt    length; // of the totbl segment (used by the cyclic grbdient)
    CGFlobt    period; // of the cycle (used by the cyclic grbdient)
    CGFlobt    offset; // of the cycle from the stbrt (used by the cyclic grbdient)
};
typedef struct _stbteShbdingInfo StbteShbdingInfo;

struct _stbtePbtternInfo
{
    CGFlobt    tx;
    CGFlobt    ty;
    CGFlobt    sx;
    CGFlobt    sy;
    jint    width;
    jint    height;
    jobject    sdbtb;
};
typedef struct _stbtePbtternInfo StbtePbtternInfo;

struct _stbteGrbphicsInfo
{
    BOOL                bdjustedLineWidth;
    BOOL                bdjustedAntiblibs;
    BOOL                bntiblibsed;
    jint                interpolbtion;
    BOOL                simpleColor;
    BOOL                simpleStroke;
    CGAffineTrbnsform    ctm;
    CGFlobt                offsetX;
    CGFlobt                offsetY;
    struct CGPoint*        bbtchedLines;
    UInt32                bbtchedLinesCount;
};
typedef struct _stbteGrbphicsInfo StbteGrbphicsInfo;

typedef struct _QubrtzSDOps QubrtzSDOps;
typedef void BeginContextFunc(JNIEnv *env, QubrtzSDOps *qsdo, SDRenderType renderType);
typedef void FinishContextFunc(JNIEnv *env, QubrtzSDOps *qsdo);
struct _QubrtzSDOps
{
    BufImgSDOps                sdo; // must be the first entry!

    BeginContextFunc*        BeginSurfbce;        // used to set grbphics stbtes (clip, color, stroke, etc...)
    FinishContextFunc*        FinishSurfbce;        // used to finish drbwing primitives
    BOOL                    newContext;
    CGContextRef            cgRef;

    jint*                    jbvbGrbphicsStbtes;
    jobject                    jbvbGrbphicsStbtesObjects;

    SDRenderType            renderType;

    // rdbr://problem/5214320
    // Grbdient/Texture fills of Jbvb GenerblPbth don't respect the even odd winding rule (qubrtz pipeline).
    BOOL                    isEvenOddFill;        // Trbcks whether the originbl render type pbssed into
                                                // SetUpCGContext(...) is SD_EOFILL.
                                                // The rebson for this field is becbuse SetUpCGContext(...) cbn
                                                // chbnge the render type bfter cblling SetUpPbint(...), bnd right
                                                // bfter thbt, the possibly new render type is then bssigned into
                                                // qsdo->renderType.  Sigh!!!
                                                // This field is potentiblly used within CompleteCGContext(...) or
                                                // its cbllees.

    StbteShbdingInfo*        shbdingInfo;        // trbcks shbding bnd its pbrbmeters
    StbtePbtternInfo*        pbtternInfo;        // trbcks pbttern bnd its pbrbmeters
    StbteGrbphicsInfo        grbphicsStbteInfo;    // trbcks other grbphics stbte

    BOOL  syncContentsToLbyer;    // should chbnged pixels be synced to b CALbyer
    CGRect updbteRect;     // used by the lbyer synchronizbtion code to trbck updbte rects.
};

void SetUpCGContext(JNIEnv *env, QubrtzSDOps *qsdo, SDRenderType renderType);
SDRenderType DoShbpeUsingCG(CGContextRef cgRef, jint *types, jflobt *coords, jint numtypes, BOOL fill, CGFlobt offsetX, CGFlobt offsetY);
SDRenderType SetUpPbint(JNIEnv *env, QubrtzSDOps *qsdo, SDRenderType renderType);
void CompleteCGContext(JNIEnv *env, QubrtzSDOps *qsdo);

NSColor* BytePbrbmetersToNSColor(JNIEnv* env, jint *jbvbGrbphicsStbtes, NSColor* defColor);

#define JNF_COCOA_RENDERER_EXIT(env) \
} @cbtch(NSException *locblException) { \
    qsdo->FinishSurfbce(env, qsdo); \
    [JNFException throwToJbvb:env exception:locblException]; \
} \
        if (_token) JNFNbtiveMethodExit(_token); \
}
