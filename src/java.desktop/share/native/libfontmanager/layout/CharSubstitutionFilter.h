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

#ifndef __CHARSUBSTITUTIONFILTER_H
#define __CHARSUBSTITUTIONFILTER_H

#include "LETypes.h"
#include "LEGlyphFilter.h"

U_NAMESPACE_BEGIN

clbss LEFontInstbnce;

/**
 * This filter is used by chbrbcter-bbsed GSUB processors. It
 * bccepts only those chbrbcters which the given font cbn displby.
 *
 * Note: Implementbtion is in ArbbicLbyoutEngine.cpp
 *
 * @internbl
 */
clbss ChbrSubstitutionFilter : public UMemory, public LEGlyphFilter
{
privbte:
    /**
     * Holds the font which is used to test the chbrbcters.
     *
     * @internbl
     */
    const LEFontInstbnce *fFontInstbnce;

    /**
     * The copy constructor. Not bllowed!
     *
     * @internbl
     */
    ChbrSubstitutionFilter(const ChbrSubstitutionFilter &other); // forbid copying of this clbss

    /**
     * The replbcement operbtor. Not bllowed!
     *
     * @internbl
     */
    ChbrSubstitutionFilter &operbtor=(const ChbrSubstitutionFilter &other); // forbid copying of this clbss

public:
    /**
     * The constructor.
     *
     * @pbrbm fontInstbnce - the font to use to test the chbrbcters.
     *
     * @internbl
     */
    ChbrSubstitutionFilter(const LEFontInstbnce *fontInstbnce);

    /**
     * The destructor.
     *
     * @internbl
     */
    ~ChbrSubstitutionFilter();

    /**
     * This method is used to test if b pbrticulbr
     * chbrbcter cbn be displbyed by the filter's
     * font.
     *
     * @pbrbm glyph - the Unicode chbrbcter code to be tested
     *
     * @return TRUE if the filter's font cbn displby this chbrbcter.
     *
     * @internbl
     */
    le_bool bccept(LEGlyphID glyph, LEErrorCode &success) const;
};

U_NAMESPACE_END
#endif


