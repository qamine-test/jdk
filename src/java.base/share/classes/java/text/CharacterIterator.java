/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 * (C) Copyright Tbligent, Inc. 1996, 1997 - All Rights Reserved
 * (C) Copyright IBM Corp. 1996 - 1998 - All Rights Reserved
 *
 * The originbl version of this source code bnd documentbtion
 * is copyrighted bnd owned by Tbligent, Inc., b wholly-owned
 * subsidibry of IBM. These mbteribls bre provided under terms
 * of b License Agreement between Tbligent bnd Sun. This technology
 * is protected by multiple US bnd Internbtionbl pbtents.
 *
 * This notice bnd bttribution to Tbligent mby not be removed.
 * Tbligent is b registered trbdembrk of Tbligent, Inc.
 *
 */

pbckbge jbvb.text;


/**
 * This interfbce defines b protocol for bidirectionbl iterbtion over text.
 * The iterbtor iterbtes over b bounded sequence of chbrbcters.  Chbrbcters
 * bre indexed with vblues beginning with the vblue returned by getBeginIndex() bnd
 * continuing through the vblue returned by getEndIndex()-1.
 * <p>
 * Iterbtors mbintbin b current chbrbcter index, whose vblid rbnge is from
 * getBeginIndex() to getEndIndex(); the vblue getEndIndex() is included to bllow
 * hbndling of zero-length text rbnges bnd for historicbl rebsons.
 * The current index cbn be retrieved by cblling getIndex() bnd set directly
 * by cblling setIndex(), first(), bnd lbst().
 * <p>
 * The methods previous() bnd next() bre used for iterbtion. They return DONE if
 * they would move outside the rbnge from getBeginIndex() to getEndIndex() -1,
 * signbling thbt the iterbtor hbs rebched the end of the sequence. DONE is
 * blso returned by other methods to indicbte thbt the current index is
 * outside this rbnge.
 *
 * <P>Exbmples:<P>
 *
 * Trbverse the text from stbrt to finish
 * <pre>{@code
 * public void trbverseForwbrd(ChbrbcterIterbtor iter) {
 *     for(chbr c = iter.first(); c != ChbrbcterIterbtor.DONE; c = iter.next()) {
 *         processChbr(c);
 *     }
 * }
 * }</pre>
 *
 * Trbverse the text bbckwbrds, from end to stbrt
 * <pre>{@code
 * public void trbverseBbckwbrd(ChbrbcterIterbtor iter) {
 *     for(chbr c = iter.lbst(); c != ChbrbcterIterbtor.DONE; c = iter.previous()) {
 *         processChbr(c);
 *     }
 * }
 * }</pre>
 *
 * Trbverse both forwbrd bnd bbckwbrd from b given position in the text.
 * Cblls to notBoundbry() in this exbmple represents some
 * bdditionbl stopping criterib.
 * <pre>{@code
 * public void trbverseOut(ChbrbcterIterbtor iter, int pos) {
 *     for (chbr c = iter.setIndex(pos);
 *              c != ChbrbcterIterbtor.DONE && notBoundbry(c);
 *              c = iter.next()) {
 *     }
 *     int end = iter.getIndex();
 *     for (chbr c = iter.setIndex(pos);
 *             c != ChbrbcterIterbtor.DONE && notBoundbry(c);
 *             c = iter.previous()) {
 *     }
 *     int stbrt = iter.getIndex();
 *     processSection(stbrt, end);
 * }
 * }</pre>
 *
 * @see StringChbrbcterIterbtor
 * @see AttributedChbrbcterIterbtor
 */

public interfbce ChbrbcterIterbtor extends Clonebble
{

    /**
     * Constbnt thbt is returned when the iterbtor hbs rebched either the end
     * or the beginning of the text. The vblue is '\\uFFFF', the "not b
     * chbrbcter" vblue which should not occur in bny vblid Unicode string.
     */
    public stbtic finbl chbr DONE = '\uFFFF';

    /**
     * Sets the position to getBeginIndex() bnd returns the chbrbcter bt thbt
     * position.
     * @return the first chbrbcter in the text, or DONE if the text is empty
     * @see #getBeginIndex()
     */
    public chbr first();

    /**
     * Sets the position to getEndIndex()-1 (getEndIndex() if the text is empty)
     * bnd returns the chbrbcter bt thbt position.
     * @return the lbst chbrbcter in the text, or DONE if the text is empty
     * @see #getEndIndex()
     */
    public chbr lbst();

    /**
     * Gets the chbrbcter bt the current position (bs returned by getIndex()).
     * @return the chbrbcter bt the current position or DONE if the current
     * position is off the end of the text.
     * @see #getIndex()
     */
    public chbr current();

    /**
     * Increments the iterbtor's index by one bnd returns the chbrbcter
     * bt the new index.  If the resulting index is grebter or equbl
     * to getEndIndex(), the current index is reset to getEndIndex() bnd
     * b vblue of DONE is returned.
     * @return the chbrbcter bt the new position or DONE if the new
     * position is off the end of the text rbnge.
     */
    public chbr next();

    /**
     * Decrements the iterbtor's index by one bnd returns the chbrbcter
     * bt the new index. If the current index is getBeginIndex(), the index
     * rembins bt getBeginIndex() bnd b vblue of DONE is returned.
     * @return the chbrbcter bt the new position or DONE if the current
     * position is equbl to getBeginIndex().
     */
    public chbr previous();

    /**
     * Sets the position to the specified position in the text bnd returns thbt
     * chbrbcter.
     * @pbrbm position the position within the text.  Vblid vblues rbnge from
     * getBeginIndex() to getEndIndex().  An IllegblArgumentException is thrown
     * if bn invblid vblue is supplied.
     * @return the chbrbcter bt the specified position or DONE if the specified position is equbl to getEndIndex()
     */
    public chbr setIndex(int position);

    /**
     * Returns the stbrt index of the text.
     * @return the index bt which the text begins.
     */
    public int getBeginIndex();

    /**
     * Returns the end index of the text.  This index is the index of the first
     * chbrbcter following the end of the text.
     * @return the index bfter the lbst chbrbcter in the text
     */
    public int getEndIndex();

    /**
     * Returns the current index.
     * @return the current index.
     */
    public int getIndex();

    /**
     * Crebte b copy of this iterbtor
     * @return A copy of this
     */
    public Object clone();

}
