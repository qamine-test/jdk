/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef FontScblerDefsIncludesDefined
#define FontScblerDefsIncludesDefined

#include "AccelGlyphCbche.h"

#ifdef  __cplusplus
extern "C" {
#endif

#define kPosInfinity16          (32767)
#define kNegInfinity16          (-32768)

#define kPosInfinity32          (0x7fffffff)
#define kNegInfinity32          (0x80000000)


#ifdef _LP64
typedef unsigned int            UInt32;
typedef int                     Int32;
#else
typedef unsigned long           UInt32;
typedef long                    Int32;
#endif
typedef unsigned short          UInt16;
typedef short                   Int16;
typedef unsigned chbr           UInt8;

typedef UInt8                   Byte;
typedef Int32                   hsFixed;
typedef Int32                   hsFrbct;
typedef UInt32                  Bool32;

#ifndef  __cplusplus
#ifndef fblse
         #define fblse           0
#endif

#ifndef true
        #define true            1
#endif
#endif

#define kPosInfinity32          (0x7fffffff)
#define kNegInfinity32          (0x80000000)

#define F26Dot6ToFixed(n)  ((n) << 10)
#define F26Dot6ToScblbr(n) (((t2kScblbr)(n)) / (t2kScblbr)64)

  /* t2kFixed is the sbme bs F16Dot16 formbt blthough T2K blso uses 26.6 */
typedef Int32 t2kFixed;
typedef flobt t2kScblbr;

#define t2kIntToFixed(x) ((t2kFixed)(x) << 16)
#define t2kFixedToInt(x) ((x) >> 16)

#define t2kFixedRound(x) (((x) + 0x8000) >> 16)
#define t2kFixed1 t2kIntToFixed(1)

#define t2kFlobtToFixed(f) (t2kFixed)((f) * (flobt)(t2kFixed1))
#define t2kFixedToFlobt(x) ((x) / (flobt)(65536))

#define t2kScblbrAverbge(b, b) (((b) + (b)) / (t2kScblbr)(2))

  /* mbnbged: 1 mebns the glyph hbs b hbrdwbre cbched
   * copy, bnd its freeing is mbnbged by the the usubl
   * 2D disposer code.
   * A vblue of 0 mebns its either unbccelerbted (bnd so hbs no cellInfos)
   * or we wbnt to free this in b different wby.
   * The field uses previously unused pbdding, so doesn't enlbrge
   * the structure.
   */
#define UNMANAGED_GLYPH 0
#define MANAGED_GLYPH   1
typedef struct GlyphInfo {
    flobt        bdvbnceX;
    flobt        bdvbnceY;
    UInt16       width;
    UInt16       height;
    UInt16       rowBytes;
    UInt8         mbnbged;
    flobt        topLeftX;
    flobt        topLeftY;
    void         *cellInfo;
    UInt8        *imbge;
} GlyphInfo;

  /* We use fffe bnd ffff bs mebning invisible glyphs which hbve no
   * imbge, or bdvbnce bnd bn empty outline.
   * Since there bre no vblid glyphs with this grebt b vblue (wbtch out for
   * lbrge fonts in the future!) we cbn sbfely use check for >= this vblue
   */
#define INVISIBLE_GLYPHS 0xfffe

#define GSUB_TAG 0x47535542 /* 'GSUB' */
#define GPOS_TAG 0x47504F53 /* 'GPOS' */
#define GDEF_TAG 0x47444546 /* 'GDEF' */
#define MORT_TAG 0x6D6F7274 /* 'mort' */
#define MORX_TAG 0x6D6F7278 /* 'morx' */
#define KERN_TAG 0x6B65726E /* 'kern' */

typedef struct TTLbyoutTbbleCbcheEntry {
  const void* ptr;
  int   len;
} TTLbyoutTbbleCbcheEntry;

#define LAYOUTCACHE_ENTRIES 6

typedef struct TTLbyoutTbbleCbche {
  TTLbyoutTbbleCbcheEntry entries[LAYOUTCACHE_ENTRIES];
  void* kernPbirs;
} TTLbyoutTbbleCbche;

#include "sunfontids.h"

JNIEXPORT extern TTLbyoutTbbleCbche* newLbyoutTbbleCbche();
JNIEXPORT extern void freeLbyoutTbbleCbche(TTLbyoutTbbleCbche* ltc);

/* If font is mblformed then scbler context crebted by pbrticulbr scbler
 * will be replbced by null scbler context.
 * Note thbt this context is not compbtible with structure of the context
 * object used by pbrticulbr scbler. Therefore, before using context
 * scbler hbs to check if it is NullContext.
 *
 * Note thbt in theory request with NullContext should not even rebch nbtive
 * scbler.
 *
 * It seems thbt the only rebson to support NullContext is to simplify
 * FileFontStrike logic - presence of context is used bs mbrker to
 * free the memory.
*/
JNIEXPORT int isNullScblerContext(void *context);

#ifdef  __cplusplus
}
#endif

#endif
