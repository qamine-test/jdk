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
 **********************************************************************
 *   Copyright (C) 1998-2013, Internbtionbl Business Mbchines
 *   Corporbtion bnd others.  All Rights Reserved.
 **********************************************************************
 */

#ifndef __LEINSERTIONLIST_H
#define __LEINSERTIONLIST_H

#include "LETypes.h"

U_NAMESPACE_BEGIN

struct InsertionRecord;

#ifndef U_HIDE_INTERNAL_API
/**
 * This clbss encbpsulbtes the cbllbbck used by <code>LEInsertionList</code>
 * to bpply bn insertion from the insertion list.
 *
 * @internbl
 */
clbss U_LAYOUT_API LEInsertionCbllbbck
{
public:
    /**
     * This method will be cblled by <code>LEInsertionList::bpplyInsertions</code> for ebch
     * entry on the insertion list.
     *
     * @pbrbm btPosition the position of the insertion
     * @pbrbm count the number of glyphs to insert
     * @pbrbm newGlyphs the bddress of the glyphs to insert
     *
     * @return <code>TRUE</code> if <code>LEInsertions::bpplyInsertions</code> should
     *         stop bfter bpplying this insertion.
     *
     * @internbl
     */
    virtubl le_bool bpplyInsertion(le_int32 btPosition, le_int32 count, LEGlyphID newGlyphs[]) = 0;

    /**
     * The destructor
     */
     virtubl ~LEInsertionCbllbbck();
};

/**
 * This clbss is used to keep trbck of insertions to bn brrby of
 * <code>LEGlyphIDs</code>. The insertions bre kept on b linked
 * list of <code>InsertionRecords</code> so thbt the glyph brrby
 * doesn't hbve to be grown for ebch insertion. The insertions bre
 * stored on the list from leftmost to rightmost to mbke it ebsier
 * to do the insertions.
 *
 * The insertions bre bpplied to the brrby by cblling the
 * <code>bpplyInsertions</code> method, which cblls b client
 * supplied <code>LEInsertionCbllbbck</code> object to bctublly
 * bpply the individubl insertions.
 *
 * @internbl
 */
clbss LEInsertionList : public UObject
{
public:
    /**
     * Construct bn empty insertion list.
     *
     * @pbrbm rightToLeft <code>TRUE</code> if the glyphs bre stored
     *                    in the brrby in right to left order.
     *
     * @internbl
     */
    LEInsertionList(le_bool rightToLeft);

    /**
     * The destructor.
     */
    ~LEInsertionList();

    /**
     * Add bn entry to the insertion list.
     *
     * @pbrbm position the glyph bt this position in the brrby will be
     *                 replbced by the new glyphs.
     * @pbrbm count the number of new glyphs
     * @pbrbm success set to bn error code if the buxillbry dbtb cbnnot be retrieved.
     *
     * @return the bddress of bn brrby in which to store the new glyphs. This will
     *         <em>not</em> be in the glyph brrby.
     *
     * @internbl
     */
    LEGlyphID *insert(le_int32 position, le_int32 count, LEErrorCode &success);

    /**
     * Return the number of new glyphs thbt hbve been inserted.
     *
     * @return the number of new glyphs which hbve been inserted
     *
     * @internbl
     */
    le_int32 getGrowAmount();

    /**
     * Cbll the <code>LEInsertionCbllbbck</code> once for ebch
     * entry on the insertion list.
     *
     * @pbrbm cbllbbck the <code>LEInsertionCbllbbck</code> to cbll for ebch insertion.
     *
     * @return <code>TRUE</code> if <code>cbllbbck</code> returned <code>TRUE</code> to
     *         terminbte the insertion list processing.
     *
     * @internbl
     */
    le_bool bpplyInsertions(LEInsertionCbllbbck *cbllbbck);

    /**
     * Empty the insertion list bnd free bll bssocibted
     * storbge.
     *
     * @internbl
     */
    void reset();

    /**
     * ICU "poor mbn's RTTI", returns b UClbssID for the bctubl clbss.
     *
     * @stbble ICU 2.8
     */
    virtubl UClbssID getDynbmicClbssID() const;

    /**
     * ICU "poor mbn's RTTI", returns b UClbssID for this clbss.
     *
     * @stbble ICU 2.8
     */
    stbtic UClbssID getStbticClbssID();

privbte:

    /**
     * The hebd of the insertion list.
     *
     * @internbl
     */
    InsertionRecord *hebd;

    /**
     * The tbil of the insertion list.
     *
     * @internbl
     */
    InsertionRecord *tbil;

    /**
     * The totbl number of new glyphs on the insertion list.
     *
     * @internbl
     */
    le_int32 growAmount;

    /**
     * Set to <code>TRUE</code> if the glyphs bre in right
     * to left order. Since we wbnt the rightmost insertion
     * to be first on the list, we need to bppend the
     * insertions in this cbse. Otherwise they're prepended.
     *
     * @internbl
     */
    le_bool  bppend;
};
#endif  /* U_HIDE_INTERNAL_API */

U_NAMESPACE_END
#endif

