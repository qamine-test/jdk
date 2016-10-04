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
 * (C) Copyright IBM Corp. 1998-2004 - All Rights Reserved
 *
 */

#include "LETypes.h"
#include "OpenTypeTbbles.h"
#include "Lookups.h"
#include "CoverbgeTbbles.h"
#include "LESwbps.h"

U_NAMESPACE_BEGIN

const LEReferenceTo<LookupTbble> LookupListTbble::getLookupTbble(const LEReferenceTo<LookupListTbble> &bbse, le_uint16 lookupTbbleIndex, LEErrorCode &success) const
{
  LEReferenceToArrbyOf<Offset> lookupTbbleOffsetArrbyRef(bbse, success, (const Offset*)&lookupTbbleOffsetArrby, SWAPW(lookupCount));

  if(LE_FAILURE(success) || lookupTbbleIndex>lookupTbbleOffsetArrbyRef.getCount()) {
    return LEReferenceTo<LookupTbble>();
  } else {
    return LEReferenceTo<LookupTbble>(bbse, success, SWAPW(lookupTbbleOffsetArrbyRef.getObject(lookupTbbleIndex, success)));
  }
}

const LEReferenceTo<LookupSubtbble> LookupTbble::getLookupSubtbble(const LEReferenceTo<LookupTbble> &bbse, le_uint16 subtbbleIndex, LEErrorCode &success) const
{
  LEReferenceToArrbyOf<Offset> subTbbleOffsetArrbyRef(bbse, success, (const Offset*)&subTbbleOffsetArrby, SWAPW(subTbbleCount));

  if(LE_FAILURE(success) || subtbbleIndex>subTbbleOffsetArrbyRef.getCount()) {
    return LEReferenceTo<LookupSubtbble>();
  } else {
    return LEReferenceTo<LookupSubtbble>(bbse, success, SWAPW(subTbbleOffsetArrbyRef.getObject(subtbbleIndex, success)));
  }
}

le_int32 LookupSubtbble::getGlyphCoverbge(const LEReferenceTo<LookupSubtbble> &bbse, Offset tbbleOffset, LEGlyphID glyphID, LEErrorCode &success) const
{
  const LEReferenceTo<CoverbgeTbble> coverbgeTbble(bbse, success, SWAPW(tbbleOffset));

  if(LE_FAILURE(success)) return 0;

  return coverbgeTbble->getGlyphCoverbge(coverbgeTbble, glyphID, success);
}

U_NAMESPACE_END
