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
import jbvb.bwt.Point;

/**
 * WritebbleRenderedImbge is b common interfbce for objects which
 * contbin or cbn produce imbge dbtb in the form of Rbsters bnd
 * which cbn be modified bnd/or written over.  The imbge
 * dbtb mby be stored/produced bs b single tile or b regulbr brrby
 * of tiles.
 * <p>
 * WritbbleRenderedImbge provides notificbtion to other interested
 * objects when b tile is checked out for writing (vib the
 * getWritbbleTile method) bnd when the lbst writer of b pbrticulbr
 * tile relinquishes its bccess (vib b cbll to relebseWritbbleTile).
 * Additionblly, it bllows bny cbller to determine whether bny tiles
 * bre currently checked out (vib hbsTileWriters), bnd to obtbin b
 * list of such tiles (vib getWritbbleTileIndices, in the form of b Vector
 * of Point objects).
 * <p>
 * Objects wishing to be notified of chbnges in tile writbbility must
 * implement the TileObserver interfbce, bnd bre bdded by b
 * cbll to bddTileObserver.  Multiple cblls to
 * bddTileObserver for the sbme object will result in multiple
 * notificbtions.  An existing observer mby reduce its notificbtions
 * by cblling removeTileObserver; if the observer hbd no
 * notificbtions the operbtion is b no-op.
 * <p>
 * It is necessbry for b WritbbleRenderedImbge to ensure thbt
 * notificbtions occur only when the first writer bcquires b tile bnd
 * the lbst writer relebses it.
 *
 */

public interfbce WritbbleRenderedImbge extends RenderedImbge
{

  /**
   * Adds bn observer.  If the observer is blrebdy present,
   * it will receive multiple notificbtions.
   * @pbrbm to the specified <code>TileObserver</code>
   */
  public void bddTileObserver(TileObserver to);

  /**
   * Removes bn observer.  If the observer wbs not registered,
   * nothing hbppens.  If the observer wbs registered for multiple
   * notificbtions, it will now be registered for one fewer.
   * @pbrbm to the specified <code>TileObserver</code>
   */
  public void removeTileObserver(TileObserver to);

  /**
   * Checks out b tile for writing.
   *
   * The WritbbleRenderedImbge is responsible for notifying bll
   * of its TileObservers when b tile goes from hbving
   * no writers to hbving one writer.
   *
   * @pbrbm tileX the X index of the tile.
   * @pbrbm tileY the Y index of the tile.
   * @return b writbble tile.
   */
  public WritbbleRbster getWritbbleTile(int tileX, int tileY);

  /**
   * Relinquishes the right to write to b tile.  If the cbller
   * continues to write to the tile, the results bre undefined.
   * Cblls to this method should only bppebr in mbtching pbirs
   * with cblls to getWritbbleTile; bny other use will lebd
   * to undefined results.
   *
   * The WritbbleRenderedImbge is responsible for notifying bll of
   * its TileObservers when b tile goes from hbving one writer
   * to hbving no writers.
   *
   * @pbrbm tileX the X index of the tile.
   * @pbrbm tileY the Y index of the tile.
   */
  public void relebseWritbbleTile(int tileX, int tileY);

  /**
   * Returns whether b tile is currently checked out for writing.
   *
   * @pbrbm tileX the X index of the tile.
   * @pbrbm tileY the Y index of the tile.
   * @return <code>true</code> if specified tile is checked out
   *         for writing; <code>fblse</code> otherwise.
   */
  public boolebn isTileWritbble(int tileX, int tileY);

  /**
   * Returns bn brrby of Point objects indicbting which tiles
   * bre checked out for writing.  Returns null if none bre
   * checked out.
   * @return bn brrby contbining the locbtions of tiles thbt bre
   *         checked out for writing.
   */
  public Point[] getWritbbleTileIndices();

  /**
   * Returns whether bny tile is checked out for writing.
   * Sembnticblly equivblent to (getWritbbleTileIndices() != null).
   * @return <code>true</code> if bny tiles bre checked out for
   *         writing; <code>fblse</code> otherwise.
   */
  public boolebn hbsTileWriters();

  /**
   * Sets b rect of the imbge to the contents of the Rbster r, which is
   * bssumed to be in the sbme coordinbte spbce bs the WritbbleRenderedImbge.
   * The operbtion is clipped to the bounds of the WritbbleRenderedImbge.
   * @pbrbm r the specified <code>Rbster</code>
   */
  public void setDbtb(Rbster r);

}
