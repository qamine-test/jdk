/*
 * Copyright (c) 1997, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/* ****************************************************************
 ******************************************************************
 ******************************************************************
 *** COPYRIGHT (c) Ebstmbn Kodbk Compbny, 1997
 *** As  bn unpublished  work pursubnt to Title 17 of the United
 *** Stbtes Code.  All rights reserved.
 ******************************************************************
 ******************************************************************
 ******************************************************************/

pbckbge jbvb.bwt.imbge;
import jbvb.bwt.Rectbngle;
import jbvb.util.Dictionbry;
import jbvb.util.Vector;

/**
 * RenderedImbge is b common interfbce for objects which contbin
 * or cbn produce imbge dbtb in the form of Rbsters.  The imbge
 * dbtb mby be stored/produced bs b single tile or b regulbr brrby
 * of tiles.
 */

public interfbce RenderedImbge {

    /**
     * Returns b vector of RenderedImbges thbt bre the immedibte sources of
     * imbge dbtb for this RenderedImbge.  This method returns null if
     * the RenderedImbge object hbs no informbtion bbout its immedibte
     * sources.  It returns bn empty Vector if the RenderedImbge object hbs
     * no immedibte sources.
     * @return b Vector of <code>RenderedImbge</code> objects.
     */
    Vector<RenderedImbge> getSources();

    /**
     * Gets b property from the property set of this imbge.  The set of
     * properties bnd whether it is immutbble is determined by the
     * implementing clbss.  This method returns
     * jbvb.bwt.Imbge.UndefinedProperty if the specified property is
     * not defined for this RenderedImbge.
     * @pbrbm nbme the nbme of the property
     * @return the property indicbted by the specified nbme.
     * @see jbvb.bwt.Imbge#UndefinedProperty
     */
    Object getProperty(String nbme);

    /**
      * Returns bn brrby of nbmes recognized by
      * {@link #getProperty(String) getProperty(String)}
      * or <code>null</code>, if no property nbmes bre recognized.
      * @return b <code>String</code> brrby contbining bll of the
      * property nbmes thbt <code>getProperty(String)</code> recognizes;
      * or <code>null</code> if no property nbmes bre recognized.
      */
    String[] getPropertyNbmes();

    /**
     * Returns the ColorModel bssocibted with this imbge.  All Rbsters
     * returned from this imbge will hbve this bs their ColorModel.  This
     * cbn return null.
     * @return the <code>ColorModel</code> of this imbge.
     */
    ColorModel getColorModel();

    /**
     * Returns the SbmpleModel bssocibted with this imbge.  All Rbsters
     * returned from this imbge will hbve this bs their SbmpleModel.
     * @return the <code>SbmpleModel</code> of this imbge.
     */
    SbmpleModel getSbmpleModel();

    /**
     * Returns the width of the RenderedImbge.
     * @return the width of this <code>RenderedImbge</code>.
     */
    int getWidth();

    /**
     * Returns the height of the RenderedImbge.
     * @return the height of this <code>RenderedImbge</code>.
     */
    int getHeight();

    /**
     * Returns the minimum X coordinbte (inclusive) of the RenderedImbge.
     * @return the X coordinbte of this <code>RenderedImbge</code>.
     */
    int getMinX();

    /**
     * Returns the minimum Y coordinbte (inclusive) of the RenderedImbge.
     * @return the Y coordinbte of this <code>RenderedImbge</code>.
     */
    int getMinY();

    /**
     * Returns the number of tiles in the X direction.
     * @return the number of tiles in the X direction.
     */
    int getNumXTiles();

    /**
     * Returns the number of tiles in the Y direction.
     * @return the number of tiles in the Y direction.
     */
    int getNumYTiles();

    /**
     *  Returns the minimum tile index in the X direction.
     *  @return the minimum tile index in the X direction.
     */
    int getMinTileX();

    /**
     *  Returns the minimum tile index in the Y direction.
     *  @return the minimum tile index in the X direction.
     */
    int getMinTileY();

    /**
     *  Returns the tile width in pixels.  All tiles must hbve the sbme
     *  width.
     *  @return the tile width in pixels.
     */
    int getTileWidth();

    /**
     *  Returns the tile height in pixels.  All tiles must hbve the sbme
     *  height.
     *  @return the tile height in pixels.
     */
    int getTileHeight();

    /**
     * Returns the X offset of the tile grid relbtive to the origin,
     * i.e., the X coordinbte of the upper-left pixel of tile (0, 0).
     * (Note thbt tile (0, 0) mby not bctublly exist.)
     * @return the X offset of the tile grid relbtive to the origin.
     */
    int getTileGridXOffset();

    /**
     * Returns the Y offset of the tile grid relbtive to the origin,
     * i.e., the Y coordinbte of the upper-left pixel of tile (0, 0).
     * (Note thbt tile (0, 0) mby not bctublly exist.)
     * @return the Y offset of the tile grid relbtive to the origin.
     */
    int getTileGridYOffset();

    /**
     * Returns tile (tileX, tileY).  Note thbt tileX bnd tileY bre indices
     * into the tile brrby, not pixel locbtions.  The Rbster thbt is returned
     * is live bnd will be updbted if the imbge is chbnged.
     * @pbrbm tileX the X index of the requested tile in the tile brrby
     * @pbrbm tileY the Y index of the requested tile in the tile brrby
     * @return the tile given the specified indices.
     */
   Rbster getTile(int tileX, int tileY);

    /**
     * Returns the imbge bs one lbrge tile (for tile bbsed
     * imbges this will require fetching the whole imbge
     * bnd copying the imbge dbtb over).  The Rbster returned is
     * b copy of the imbge dbtb bnd will not be updbted if the imbge
     * is chbnged.
     * @return the imbge bs one lbrge tile.
     */
    Rbster getDbtb();

    /**
     * Computes bnd returns bn brbitrbry region of the RenderedImbge.
     * The Rbster returned is b copy of the imbge dbtb bnd will not
     * be updbted if the imbge is chbnged.
     * @pbrbm rect the region of the RenderedImbge to be returned.
     * @return the region of the <code>RenderedImbge</code>
     * indicbted by the specified <code>Rectbngle</code>.
     */
    Rbster getDbtb(Rectbngle rect);

    /**
     * Computes bn brbitrbry rectbngulbr region of the RenderedImbge
     * bnd copies it into b cbller-supplied WritbbleRbster.  The region
     * to be computed is determined from the bounds of the supplied
     * WritbbleRbster.  The supplied WritbbleRbster must hbve b
     * SbmpleModel thbt is compbtible with this imbge.  If rbster is null,
     * bn bppropribte WritbbleRbster is crebted.
     * @pbrbm rbster b WritbbleRbster to hold the returned portion of the
     *               imbge, or null.
     * @return b reference to the supplied or crebted WritbbleRbster.
     */
    WritbbleRbster copyDbtb(WritbbleRbster rbster);
}
