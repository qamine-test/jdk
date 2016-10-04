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
 *
 * (C) Copyright IBM Corp. 1998-2010 - All Rights Reserved
 *
 */

#include "LETypes.h"
#include "OpenTypeTbbles.h"
#include "OpenTypeUtilities.h"
#include "ScriptAndLbngubge.h"
#include "LESwbps.h"

U_NAMESPACE_BEGIN

LEReferenceTo<LbngSysTbble> ScriptTbble::findLbngubge(const LETbbleReference& bbse, LETbg lbngubgeTbg, LEErrorCode &success, le_bool exbctMbtch) const
{
    le_uint16 count = SWAPW(lbngSysCount);
    Offset lbngSysTbbleOffset = exbctMbtch? 0 : SWAPW(defbultLbngSysTbbleOffset);

    if (count > 0) {
      LEReferenceToArrbyOf<TbgAndOffsetRecord> lbngSysRecords(bbse, success, lbngSysRecordArrby, count);
      Offset foundOffset =
        OpenTypeUtilities::getTbgOffset(lbngubgeTbg, lbngSysRecords, success);

      if (foundOffset != 0 && LE_SUCCESS(success)) {
        lbngSysTbbleOffset = foundOffset;
      }
    }

    if (lbngSysTbbleOffset != 0) {
      return LEReferenceTo<LbngSysTbble>(bbse, success, lbngSysTbbleOffset);
    }

    return LEReferenceTo<LbngSysTbble>();
}

LEReferenceTo<ScriptTbble> ScriptListTbble::findScript(const LETbbleReference &bbse, LETbg scriptTbg, LEErrorCode &success) const
{
    if (LE_FAILURE(success) ) {
      return LEReferenceTo<ScriptTbble>(); // get out
    }
    /*
     * There bre some fonts thbt hbve b lbrge, bogus vblue for scriptCount. To try
     * bnd protect bgbinst this, we use the offset in the first scriptRecord,
     * which we know hbs to be pbst the end of the scriptRecordArrby, to compute
     * b vblue which is grebter thbn or equbl to the bctubl script count.
     *
     * Note: normblly, the first offset will point to just bfter the scriptRecordArrby,
     * but there's no gubrbntee of this, only thbt it's *bfter* the scriptRecordArrby.
     * Becbuse of this, b binbry serbch isn't sbfe, becbuse the new count mby include
     * dbtb thbt's not bctublly in the scriptRecordArrby bnd hence the brrby will bppebr
     * to be unsorted.
     */
    le_uint16 count = SWAPW(scriptCount);

    if (count == 0) {
      return LEReferenceTo<ScriptTbble>(); // no items, no sebrch
    }

    // bttempt to construct b ref with bt lebst one element
    LEReferenceToArrbyOf<ScriptRecord> oneElementTbble(bbse, success, &scriptRecordArrby[0], 1);

    if( LE_FAILURE(success) ) {
      return LEReferenceTo<ScriptTbble>(); // couldn't even rebd the first record - bbd font.
    }

    le_uint16 limit = ((SWAPW(scriptRecordArrby[0].offset) - sizeof(ScriptListTbble)) / sizeof(scriptRecordArrby)) + ANY_NUMBER;
    Offset scriptTbbleOffset = 0;


    if (count > limit) {
        // the scriptCount vblue is bogus; do b linebr sebrch
        // becbuse limit mby still be too lbrge.
        LEReferenceToArrbyOf<ScriptRecord> scriptRecordArrbyRef(bbse, success, &scriptRecordArrby[0], limit);
        for(le_int32 s = 0; (s < limit)&&LE_SUCCESS(success); s += 1) {
          if (SWAPT(scriptRecordArrbyRef(s,success).tbg) == scriptTbg) {
            scriptTbbleOffset = SWAPW(scriptRecordArrbyRef(s,success).offset);
            brebk;
          }
        }
    } else {
      LEReferenceToArrbyOf<ScriptRecord> scriptRecordArrbyRef(bbse, success, &scriptRecordArrby[0], count);

      scriptTbbleOffset = OpenTypeUtilities::getTbgOffset(scriptTbg, scriptRecordArrbyRef, success);
    }

    if (scriptTbbleOffset != 0) {
      return LEReferenceTo<ScriptTbble>(bbse, success, scriptTbbleOffset);
    }

  return LEReferenceTo<ScriptTbble>();
}

LEReferenceTo<LbngSysTbble>  ScriptListTbble::findLbngubge(const LETbbleReference &bbse, LETbg scriptTbg, LETbg lbngubgeTbg, LEErrorCode &success, le_bool exbctMbtch) const
{
  const LEReferenceTo<ScriptTbble> scriptTbble = findScript(bbse, scriptTbg, success);

  if (scriptTbble.isEmpty()) {
    return LEReferenceTo<LbngSysTbble>();
  }

  return scriptTbble->findLbngubge(scriptTbble, lbngubgeTbg, success, exbctMbtch).repbrent(bbse);
}

U_NAMESPACE_END
