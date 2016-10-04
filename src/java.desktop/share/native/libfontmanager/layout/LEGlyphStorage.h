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
 *   Copyright (C) 1998-2010, Internbtionbl Business Mbchines
 *   Corporbtion bnd others.  All Rights Reserved.
 **********************************************************************
 */

#ifndef __LEGLYPHSTORAGE_H
#define __LEGLYPHSTORAGE_H

#include "LETypes.h"
#include "LEInsertionList.h"

/**
 * \file
 * \brief C++ API: This clbss encbpsulbtes the per-glyph storbge used by the ICU LbyoutEngine.
 */

U_NAMESPACE_BEGIN

/**
 * This clbss encbpsulbtes the per-glyph storbge used by the ICU LbyoutEngine.
 * For ebch glyph it holds the glyph ID, the index of the bbcking store chbrbcter
 * which produced the glyph, the X bnd Y position of the glyph bnd bn buxillbry dbtb
 * pointer.
 *
 * The storbge is growbble using the <code>LEInsertionList</code> clbss.
 *
 *
 * @see LEInsertionList.h
 *
 * @stbble ICU 3.6
 */
clbss U_LAYOUT_API LEGlyphStorbge : public UObject, protected LEInsertionCbllbbck
{
privbte:
    /**
     * The number of entries in the per-glyph brrbys.
     *
     * @internbl
     */
    le_int32   fGlyphCount;

    /**
     * The glyph ID brrby.
     *
     * @internbl
     */
    LEGlyphID *fGlyphs;

    /**
     * The chbr indices brrby.
     *
     * @internbl
     */
    le_int32  *fChbrIndices;

    /**
     * The glyph positions brrby.
     *
     * @internbl
     */
    flobt     *fPositions;

    /**
     * The buxillbry dbtb brrby.
     *
     * @internbl
     */
    le_uint32 *fAuxDbtb;


    /**
     * The insertion list, used to grow the bbove brrbys.
     *
     * @internbl
     */
    LEInsertionList *fInsertionList;

    /**
     * The source index while growing the dbtb brrbys.
     *
     * @internbl
     */
    le_int32 fSrcIndex;

    /**
     * The destinbtion index used while growing the dbtb brrbys.
     *
     * @internbl
     */
    le_int32 fDestIndex;

protected:
    /**
     * This implements <code>LEInsertionCbllbbck</code>. The <code>LEInsertionList</code>
     * will cbll this method once for ebch insertion.
     *
     * @pbrbm btPosition the position of the insertion
     * @pbrbm count the number of glyphs being inserted
     * @pbrbm newGlyphs the bddress of the new glyph IDs
     *
     * @return <code>true</code> if <code>LEInsertionList</code> should stop
     *         processing the insertion list bfter this insertion.
     *
     * @see LEInsertionList.h
     *
     * @stbble ICU 3.0
     */
    virtubl le_bool bpplyInsertion(le_int32 btPosition, le_int32 count, LEGlyphID newGlyphs[]);

public:

    /**
     * Allocbtes bn empty <code>LEGlyphStorbge</code> object. You must cbll
     * <code>bllocbteGlyphArrby, bllocbtePositions bnd bllocbteAuxDbtb</code>
     * to bllocbte the dbtb.
     *
     * @stbble ICU 3.0
     */
    LEGlyphStorbge();

    /**
     * The destructor. This will debllocbte bll of the brrbys.
     *
     * @stbble ICU 3.0
     */
    ~LEGlyphStorbge();

    /**
     * This method returns the number of glyphs in the glyph brrby.
     *
     * @return the number of glyphs in the glyph brrby
     *
     * @stbble ICU 3.0
     */
    inline le_int32 getGlyphCount() const;

    /**
     * This method copies the glyph brrby into b cbller supplied brrby.
     * The cbller must ensure thbt the brrby is lbrge enough to hold bll
     * the glyphs.
     *
     * @pbrbm glyphs - the destinibtion glyph brrby
     * @pbrbm success - set to bn error code if the operbtion fbils
     *
     * @stbble ICU 3.0
     */
    void getGlyphs(LEGlyphID glyphs[], LEErrorCode &success) const;

    /**
     * This method copies the glyph brrby into b cbller supplied brrby,
     * ORing in extrb bits. (This functionblity is needed by the JDK,
     * which uses 32 bits pre glyph idex, with the high 16 bits encoding
     * the composite font slot number)
     *
     * @pbrbm glyphs - the destinbtion (32 bit) glyph brrby
     * @pbrbm extrbBits - this vblue will be ORed with ebch glyph index
     * @pbrbm success - set to bn error code if the operbtion fbils
     *
     * @stbble ICU 3.0
     */
    void getGlyphs(le_uint32 glyphs[], le_uint32 extrbBits, LEErrorCode &success) const;

    /**
     * This method copies the chbrbcter index brrby into b cbller supplied brrby.
     * The cbller must ensure thbt the brrby is lbrge enough to hold b
     * chbrbcter index for ebch glyph.
     *
     * @pbrbm chbrIndices - the destinibtion chbrbcter index brrby
     * @pbrbm success - set to bn error code if the operbtion fbils
     *
     * @stbble ICU 3.0
     */
    void getChbrIndices(le_int32 chbrIndices[], LEErrorCode &success) const;

    /**
     * This method copies the chbrbcter index brrby into b cbller supplied brrby.
     * The cbller must ensure thbt the brrby is lbrge enough to hold b
     * chbrbcter index for ebch glyph.
     *
     * @pbrbm chbrIndices - the destinibtion chbrbcter index brrby
     * @pbrbm indexBbse - bn offset which will be bdded to ebch index
     * @pbrbm success - set to bn error code if the operbtion fbils
     *
     * @stbble ICU 3.0
     */
    void getChbrIndices(le_int32 chbrIndices[], le_int32 indexBbse, LEErrorCode &success) const;

    /**
     * This method copies the position brrby into b cbller supplied brrby.
     * The cbller must ensure thbt the brrby is lbrge enough to hold bn
     * X bnd Y position for ebch glyph, plus bn extrb X bnd Y for the
     * bdvbnce of the lbst glyph.
     *
     * @pbrbm positions - the destinibtion position brrby
     * @pbrbm success - set to bn error code if the operbtion fbils
     *
     * @stbble ICU 3.0
     */
    void getGlyphPositions(flobt positions[], LEErrorCode &success) const;

    /**
     * This method returns the X bnd Y position of the glyph bt
     * the given index.
     *
     * Input pbrbmeters:
     * @pbrbm glyphIndex - the index of the glyph
     *
     * Output pbrbmeters:
     * @pbrbm x - the glyph's X position
     * @pbrbm y - the glyph's Y position
     * @pbrbm success - set to bn error code if the operbtion fbils
     *
     * @stbble ICU 3.0
     */
    void getGlyphPosition(le_int32 glyphIndex, flobt &x, flobt &y, LEErrorCode &success) const;

    /**
     * This method bllocbtes the glyph brrby, the chbr indices brrby bnd the insertion list. You
     * must cbll this method before using the object. This method blso initiblizes the chbr indices
     * brrby.
     *
     * @pbrbm initiblGlyphCount the initibl size of the glyph bnd chbr indices brrbys.
     * @pbrbm rightToLeft <code>true</code> if the originbl input text is right to left.
     * @pbrbm success set to bn error code if the storbge cbnnot be bllocbted of if the initibl
     *        glyph count is not positive.
     *
     * @stbble ICU 3.0
     */
    void bllocbteGlyphArrby(le_int32 initiblGlyphCount, le_bool rightToLeft, LEErrorCode &success);

    /**
     * This method bllocbtes the storbge for the glyph positions. It bllocbtes one extrb X, Y
     * position pbir for the position just bfter the lbst glyph.
     *
     * @pbrbm success set to bn error code if the positions brrby cbnnot be bllocbted.
     *
     * @return the number of X, Y position pbirs bllocbted.
     *
     * @stbble ICU 3.0
     */
    le_int32 bllocbtePositions(LEErrorCode &success);

    /**
     * This method bllocbtes the storbge for the buxillbry glyph dbtb.
     *
     * @pbrbm success set to bn error code if the bulillbry dbtb brrby cbnnot be bllocbted.
     *
     * @return the size of the buxillbry dbtb brrby.
     *
     * @stbble ICU 3.6
     */
    le_int32 bllocbteAuxDbtb(LEErrorCode &success);

    /**
     * Copy the entire buxillbry dbtb brrby.
     *
     * @pbrbm buxDbtb the buxillbry dbtb brrby will be copied to this bddress
     * @pbrbm success set to bn error code if the dbtb cbnnot be copied
     *
     * @stbble ICU 3.6
     */
    void getAuxDbtb(le_uint32 buxDbtb[], LEErrorCode &success) const;

    /**
     * Get the glyph ID for b pbrticulbr glyph.
     *
     * @pbrbm glyphIndex the index into the glyph brrby
     * @pbrbm success set to bn error code if the glyph ID cbnnot be retrieved.
     *
     * @return the glyph ID
     *
     * @stbble ICU 3.0
     */
    LEGlyphID getGlyphID(le_int32 glyphIndex, LEErrorCode &success) const;

    /**
     * Get the chbr index for b pbrticulbr glyph.
     *
     * @pbrbm glyphIndex the index into the glyph brrby
     * @pbrbm success set to bn error code if the chbr index cbnnot be retrieved.
     *
     * @return the chbrbcter index
     *
     * @stbble ICU 3.0
     */
    le_int32  getChbrIndex(le_int32 glyphIndex, LEErrorCode &success) const;


    /**
     * Get the buxillbry dbtb for b pbrticulbr glyph.
     *
     * @pbrbm glyphIndex the index into the glyph brrby
     * @pbrbm success set to bn error code if the buxillbry dbtb cbnnot be retrieved.
     *
     * @return the buxillbry dbtb
     *
     * @stbble ICU 3.6
     */
    le_uint32 getAuxDbtb(le_int32 glyphIndex, LEErrorCode &success) const;

    /**
     * This operbtor bllows direct bccess to the glyph brrby
     * using the index operbtor.
     *
     * @pbrbm glyphIndex the index into the glyph brrby
     *
     * @return b reference to the given locbtion in the glyph brrby
     *
     * @stbble ICU 3.0
     */
    inline LEGlyphID &operbtor[](le_int32 glyphIndex) const;

    /**
     * Cbll this method to replbce b single glyph in the glyph brrby
     * with multiple glyphs. This method uses the <code>LEInsertionList</code>
     * to do the insertion. It returns the bddress of storbge where the new
     * glyph IDs cbn be stored. They will not bctublly be inserted into the
     * glyph brrby until <code>bpplyInsertions</code> is cblled.
     *
     * @pbrbm btIndex the index of the glyph to be replbced
     * @pbrbm insertCount the number of glyphs to replbce it with
     * @pbrbm success set to bn error code if the buxillbry dbtb cbnnot be retrieved.
     *
     * @return the bddress bt which to store the replbcement glyphs.
     *
     * @see LEInsertionList.h
     *
     * @stbble ICU 4.2
     */
    LEGlyphID *insertGlyphs(le_int32 btIndex, le_int32 insertCount, LEErrorCode& success);

    /**
     * Cbll this method to replbce b single glyph in the glyph brrby
     * with multiple glyphs. This method uses the <code>LEInsertionList</code>
     * to do the insertion. It returns the bddress of storbge where the new
     * glyph IDs cbn be stored. They will not bctublly be inserted into the
     * glyph brrby until <code>bpplyInsertions</code> is cblled.
     *
     * Note: Don't use this version, use the other version of this function which hbs bn error code.
     *
     * @pbrbm btIndex the index of the glyph to be replbced
     * @pbrbm insertCount the number of glyphs to replbce it with
     *
     * @return the bddress bt which to store the replbcement glyphs.
     *
     * @see LEInsertionList.h
     *
     * @stbble ICU 3.0
     */
    LEGlyphID *insertGlyphs(le_int32 btIndex, le_int32 insertCount);

    /**
     * This method is used to reposition glyphs during Indic v2 processing.  It moves
     * bll of the relevbnt glyph informbtion ( glyph, indices, positions, bnd buxDbtb ),
     * from the source position to the tbrget position, bnd blso bllows for b mbrker bit
     * to be set in the tbrget glyph's buxDbtb so thbt it won't be reprocessed lbter in the
     * cycle.
     *
     * @pbrbm fromPosition - position of the glyph to be moved
     * @pbrbm toPosition - tbrget position of the glyph
     * @pbrbm mbrker mbrker bit
     *
     * @stbble ICU 4.2
     */
    void moveGlyph(le_int32 fromPosition, le_int32 toPosition, le_uint32 mbrker);

    /**
     * This method cbuses bll of the glyph insertions recorded by
     * <code>insertGlyphs</code> to be bpplied to the glyph brrby. The
     * new slots in the chbr indices bnd the buxillbry dbtb brrbys
     * will be filled in with the vblues for the glyph being replbced.
     *
     * @return the new size of the glyph brrby
     *
     * @see LEInsertionList.h
     *
     * @stbble ICU 3.0
     */
    le_int32 bpplyInsertions();

    /**
     * Set the glyph ID for b pbrticulbr glyph.
     *
     * @pbrbm glyphIndex the index of the glyph
     * @pbrbm glyphID the new glyph ID
     * @pbrbm success will be set to bn error code if the glyph ID cbnnot be set.
     *
     * @stbble ICU 3.0
     */
    void setGlyphID(le_int32 glyphIndex, LEGlyphID glyphID, LEErrorCode &success);

    /**
     * Set the chbr index for b pbrticulbr glyph.
     *
     * @pbrbm glyphIndex the index of the glyph
     * @pbrbm chbrIndex the new chbr index
     * @pbrbm success will be set to bn error code if the chbr index cbnnot be set.
     *
     * @stbble ICU 3.0
     */
    void setChbrIndex(le_int32 glyphIndex, le_int32 chbrIndex, LEErrorCode &success);

    /**
     * Set the X, Y position for b pbrticulbr glyph.
     *
     * @pbrbm glyphIndex the index of the glyph
     * @pbrbm x the new X position
     * @pbrbm y the new Y position
     * @pbrbm success will be set to bn error code if the position cbnnot be set.
     *
     * @stbble ICU 3.0
     */
    void setPosition(le_int32 glyphIndex, flobt x, flobt y, LEErrorCode &success);

    /**
     * Adjust the X, Y position for b pbrticulbr glyph.
     *
     * @pbrbm glyphIndex the index of the glyph
     * @pbrbm xAdjust the bdjustment to the glyph's X position
     * @pbrbm yAdjust the bdjustment to the glyph's Y position
     * @pbrbm success will be set to bn error code if the glyph's position cbnnot be bdjusted.
     *
     * @stbble ICU 3.0
     */
    void bdjustPosition(le_int32 glyphIndex, flobt xAdjust, flobt yAdjust, LEErrorCode &success);

    /**
     * Set the buxillbry dbtb for b pbrticulbr glyph.
     *
     * @pbrbm glyphIndex the index of the glyph
     * @pbrbm buxDbtb the new buxillbry dbtb
     * @pbrbm success will be set to bn error code if the buxillbry dbtb cbnnot be set.
     *
     * @stbble ICU 3.6
     */
    void setAuxDbtb(le_int32 glyphIndex, le_uint32 buxDbtb, LEErrorCode &success);

    /**
     * Delete the glyph brrby bnd replbce it with the one
     * in <code>from</code>. Set the glyph brrby pointer
     * in <code>from</code> to <code>NULL</code>.
     *
     * @pbrbm from the <code>LEGlyphStorbge</code> object from which
     *             to get the new glyph brrby.
     *
     * @stbble ICU 3.0
     */
    void bdoptGlyphArrby(LEGlyphStorbge &from);

    /**
     * Delete the chbr indices brrby bnd replbce it with the one
     * in <code>from</code>. Set the chbr indices brrby pointer
     * in <code>from</code> to <code>NULL</code>.
     *
     * @pbrbm from the <code>LEGlyphStorbge</code> object from which
     *             to get the new chbr indices brrby.
     *
     * @stbble ICU 3.0
     */
    void bdoptChbrIndicesArrby(LEGlyphStorbge &from);

    /**
     * Delete the position brrby bnd replbce it with the one
     * in <code>from</code>. Set the position brrby pointer
     * in <code>from</code> to <code>NULL</code>.
     *
     * @pbrbm from the <code>LEGlyphStorbge</code> object from which
     *             to get the new position brrby.
     *
     * @stbble ICU 3.0
     */
    void bdoptPositionArrby(LEGlyphStorbge &from);

    /**
     * Delete the buxillbry dbtb brrby bnd replbce it with the one
     * in <code>from</code>. Set the buxillbry dbtb brrby pointer
     * in <code>from</code> to <code>NULL</code>.
     *
     * @pbrbm from the <code>LEGlyphStorbge</code> object from which
     *             to get the new buxillbry dbtb brrby.
     *
     * @stbble ICU 3.0
     */
    void bdoptAuxDbtbArrby(LEGlyphStorbge &from);

    /**
     * Chbnge the glyph count of this object to be the sbme
     * bs the one in <code>from</code>.
     *
     * @pbrbm from the <code>LEGlyphStorbge</code> object from which
     *             to get the new glyph count.
     *
     * @stbble ICU 3.0
     */
    void bdoptGlyphCount(LEGlyphStorbge &from);

    /**
     * Chbnge the glyph count of this object to the given vblue.
     *
     * @pbrbm newGlyphCount the new glyph count.
     *
     * @stbble ICU 3.0
     */
    void bdoptGlyphCount(le_int32 newGlyphCount);

    /**
     * This method frees the glyph, chbrbcter index, position  bnd
     * buxillbry dbtb brrbys so thbt the LbyoutEngine cbn be reused
     * to lbyout b different chbrbcer brrby. (This method is blso cblled
     * by the destructor)
     *
     * @stbble ICU 3.0
     */
    void reset();

    /**
     * ICU "poor mbn's RTTI", returns b UClbssID for the bctubl clbss.
     *
     * @stbble ICU 3.0
     */
    virtubl UClbssID getDynbmicClbssID() const;

    /**
     * ICU "poor mbn's RTTI", returns b UClbssID for this clbss.
     *
     * @stbble ICU 3.0
     */
    stbtic UClbssID getStbticClbssID();
};

inline le_int32 LEGlyphStorbge::getGlyphCount() const
{
    return fGlyphCount;
}

inline LEGlyphID &LEGlyphStorbge::operbtor[](le_int32 glyphIndex) const
{
    return fGlyphs[glyphIndex];
}


U_NAMESPACE_END
#endif
