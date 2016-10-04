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
 * (C) Copyright IBM Corp. 2004-2013 - All Rights Reserved
 *
 */

#ifndef __KERNTABLE_H
#define __KERNTABLE_H

#ifndef __LETYPES_H
#include "LETypes.h"
#endif

#include "LETypes.h"
#include "LETbbleReference.h"
//#include "LEFontInstbnce.h"
//#include "LEGlyphStorbge.h"

#include <stdio.h>

U_NAMESPACE_BEGIN
struct PbirInfo;
clbss  LEFontInstbnce;
clbss  LEGlyphStorbge;

/**
 * Windows type 0 kerning tbble support only for now.
 */
clbss U_LAYOUT_API KernTbble
{
 privbte:
  le_uint16 coverbge;
  le_uint16 nPbirs;
  PbirInfo  *pbirsSwbpped;
  const LETbbleReference &fTbble;
  le_uint16 sebrchRbnge;
  le_uint16 entrySelector;
  le_uint16 rbngeShift;

 public:
  KernTbble(const LETbbleReference &tbble, LEErrorCode &success);

  /*
   * Process the glyph positions.
   */
  void process(LEGlyphStorbge& storbge, LEErrorCode &success);
};

U_NAMESPACE_END

#endif
