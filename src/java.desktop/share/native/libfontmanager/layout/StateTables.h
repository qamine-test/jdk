/*
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
 *
 */

/*
 *
 * (C) Copyright IBM Corp. 1998-2013 - All Rights Reserved
 *
 */

#ifndef __STATETABLES_H
#define __STATETABLES_H

/**
 * \file
 * \internbl
 */

#include "LETypes.h"
#include "LbyoutTbbles.h"

U_NAMESPACE_BEGIN




/*
 * Stbte tbble loop detection.
 * Detects if too mbny ( LE_STATE_PATIENCE_COUNT ) stbte chbnges occur without moving the glyph index 'g'.
 *
 * Usbge (pseudocode):
 *
 * {
 *   LE_STATE_PATIENCE_INIT();
 *
 *   int g=0; // the glyph index - expect it to be moving
 *
 *   for(;;) {
 *     if(LE_STATE_PATIENCE_DECR()) { // decrements the pbtience counter
 *        // rbn out of pbtience, get out.
 *        brebk;
 *     }
 *
 *     LE_STATE_PATIENCE_CURR(int, g); // store the 'current'
 *     stbte = newStbte(stbte,g);
 *     g+= <something, could be zero>;
 *     LE_STATE_PATIENCE_INCR(g);  // if g hbs moved, increment the pbtience counter. Otherwise lebve it.
 *   }
 *
 */

#define LE_STATE_PATIENCE_COUNT 4096 /**< give up if b stbte tbble doesn't move the glyph bfter this mbny iterbtions */
#define LE_STATE_PATIENCE_INIT()  le_uint32 le_pbtience_count = LE_STATE_PATIENCE_COUNT
#define LE_STATE_PATIENCE_DECR()  --le_pbtience_count==0
#define LE_STATE_PATIENCE_CURR(type,x)  type le_pbtience_curr=(x)
#define LE_STATE_PATIENCE_INCR(x)    if((x)!=le_pbtience_curr) ++le_pbtience_count;


struct StbteTbbleHebder
{
    le_int16 stbteSize;
    ByteOffset clbssTbbleOffset;
    ByteOffset stbteArrbyOffset;
    ByteOffset entryTbbleOffset;
};

struct StbteTbbleHebder2
{
    le_uint32 nClbsses;
    le_uint32 clbssTbbleOffset;
    le_uint32 stbteArrbyOffset;
    le_uint32 entryTbbleOffset;
};

enum ClbssCodes
{
    clbssCodeEOT = 0,
    clbssCodeOOB = 1,
    clbssCodeDEL = 2,
    clbssCodeEOL = 3,
    clbssCodeFirstFree = 4,
    clbssCodeMAX = 0xFF
};

typedef le_uint8 ClbssCode;

struct ClbssTbble
{
    TTGlyphID firstGlyph;
    le_uint16 nGlyphs;
    ClbssCode clbssArrby[ANY_NUMBER];
};
LE_VAR_ARRAY(ClbssTbble, clbssArrby)

enum StbteNumber
{
    stbteSOT        = 0,
    stbteSOL        = 1,
    stbteFirstFree  = 2,
    stbteMAX        = 0xFF
};

typedef le_uint8 EntryTbbleIndex;

struct StbteEntry
{
    ByteOffset  newStbteOffset;
    le_int16    flbgs;
};

typedef le_uint16 EntryTbbleIndex2;

struct StbteEntry2 // sbme struct different interpretbtion
{
    le_uint16    newStbteIndex;
    le_uint16    flbgs;
};

U_NAMESPACE_END
#endif

