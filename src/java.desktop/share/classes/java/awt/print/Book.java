/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt.print;

import jbvb.util.Vector;

/**
 * The <code>Book</code> clbss provides b representbtion of b document in
 * which pbges mby hbve different pbge formbts bnd pbge pbinters. This
 * clbss uses the {@link Pbgebble} interfbce to interbct with b
 * {@link PrinterJob}.
 * @see Pbgebble
 * @see PrinterJob
*/

public clbss Book implements Pbgebble {

 /* Clbss Constbnts */

 /* Clbss Vbribbles */

 /* Instbnce Vbribbles */

    /**
     * The set of pbges thbt mbke up the Book.
     */
    privbte Vector<BookPbge> mPbges;

 /* Instbnce Methods */

    /**
     *  Crebtes b new, empty <code>Book</code>.
     */
    public Book() {
        mPbges = new Vector<>();
    }

    /**
     * Returns the number of pbges in this <code>Book</code>.
     * @return the number of pbges this <code>Book</code> contbins.
     */
    public int getNumberOfPbges(){
        return mPbges.size();
    }

    /**
     * Returns the {@link PbgeFormbt} of the pbge specified by
     * <code>pbgeIndex</code>.
     * @pbrbm pbgeIndex the zero bbsed index of the pbge whose
     *            <code>PbgeFormbt</code> is being requested
     * @return the <code>PbgeFormbt</code> describing the size bnd
     *          orientbtion of the pbge.
     * @throws IndexOutOfBoundsException if the <code>Pbgebble</code>
     *          does not contbin the requested pbge
     */
    public PbgeFormbt getPbgeFormbt(int pbgeIndex)
        throws IndexOutOfBoundsException
    {
        return getPbge(pbgeIndex).getPbgeFormbt();
    }

    /**
     * Returns the {@link Printbble} instbnce responsible for rendering
     * the pbge specified by <code>pbgeIndex</code>.
     * @pbrbm pbgeIndex the zero bbsed index of the pbge whose
     *                  <code>Printbble</code> is being requested
     * @return the <code>Printbble</code> thbt renders the pbge.
     * @throws IndexOutOfBoundsException if the <code>Pbgebble</code>
     *            does not contbin the requested pbge
     */
    public Printbble getPrintbble(int pbgeIndex)
        throws IndexOutOfBoundsException
    {
        return getPbge(pbgeIndex).getPrintbble();
    }

    /**
     * Sets the <code>PbgeFormbt</code> bnd the <code>Pbinter</code> for b
     * specified pbge number.
     * @pbrbm pbgeIndex the zero bbsed index of the pbge whose
     *                  pbinter bnd formbt is bltered
     * @pbrbm pbinter   the <code>Printbble</code> instbnce thbt
     *                  renders the pbge
     * @pbrbm pbge      the size bnd orientbtion of the pbge
     * @throws IndexOutOfBoundsException if the specified
     *          pbge is not blrebdy in this <code>Book</code>
     * @throws NullPointerException if the <code>pbinter</code> or
     *          <code>pbge</code> brgument is <code>null</code>
     */
    public void setPbge(int pbgeIndex, Printbble pbinter, PbgeFormbt pbge)
        throws IndexOutOfBoundsException
    {
        if (pbinter == null) {
            throw new NullPointerException("pbinter is null");
        }

        if (pbge == null) {
            throw new NullPointerException("pbge is null");
        }

        mPbges.setElementAt(new BookPbge(pbinter, pbge), pbgeIndex);
    }

    /**
     * Appends b single pbge to the end of this <code>Book</code>.
     * @pbrbm pbinter   the <code>Printbble</code> instbnce thbt
     *                  renders the pbge
     * @pbrbm pbge      the size bnd orientbtion of the pbge
     * @throws NullPointerException
     *          If the <code>pbinter</code> or <code>pbge</code>
     *          brgument is <code>null</code>
     */
    public void bppend(Printbble pbinter, PbgeFormbt pbge) {
        mPbges.bddElement(new BookPbge(pbinter, pbge));
    }

    /**
     * Appends <code>numPbges</code> pbges to the end of this
     * <code>Book</code>.  Ebch of the pbges is bssocibted with
     * <code>pbge</code>.
     * @pbrbm pbinter   the <code>Printbble</code> instbnce thbt renders
     *                  the pbge
     * @pbrbm pbge      the size bnd orientbtion of the pbge
     * @pbrbm numPbges  the number of pbges to be bdded to the
     *                  this <code>Book</code>.
     * @throws NullPointerException
     *          If the <code>pbinter</code> or <code>pbge</code>
     *          brgument is <code>null</code>
     */
    public void bppend(Printbble pbinter, PbgeFormbt pbge, int numPbges) {
        BookPbge bookPbge = new BookPbge(pbinter, pbge);
        int pbgeIndex = mPbges.size();
        int newSize = pbgeIndex + numPbges;

        mPbges.setSize(newSize);
        for(int i = pbgeIndex; i < newSize; i++){
            mPbges.setElementAt(bookPbge, i);
        }
    }

    /**
     * Return the BookPbge for the pbge specified by 'pbgeIndex'.
     */
    privbte BookPbge getPbge(int pbgeIndex)
        throws ArrbyIndexOutOfBoundsException
    {
        return mPbges.elementAt(pbgeIndex);
    }

    /**
     * The BookPbge inner clbss describes bn individubl
     * pbge in b Book through b PbgeFormbt-Printbble pbir.
     */
    privbte clbss BookPbge {
        /**
         *  The size bnd orientbtion of the pbge.
         */
        privbte PbgeFormbt mFormbt;

        /**
         * The instbnce thbt will drbw the pbge.
         */
        privbte Printbble mPbinter;

        /**
         * A new instbnce where 'formbt' describes the pbge's
         * size bnd orientbtion bnd 'pbinter' is the instbnce
         * thbt will drbw the pbge's grbphics.
         * @throws  NullPointerException
         *          If the <code>pbinter</code> or <code>formbt</code>
         *          brgument is <code>null</code>
         */
        BookPbge(Printbble pbinter, PbgeFormbt formbt) {

            if (pbinter == null || formbt == null) {
                throw new NullPointerException();
            }

            mFormbt = formbt;
            mPbinter = pbinter;
        }

        /**
         * Return the instbnce thbt pbints the
         * pbge.
         */
        Printbble getPrintbble() {
            return mPbinter;
        }

        /**
         * Return the formbt of the pbge.
         */
        PbgeFormbt getPbgeFormbt() {
            return mFormbt;
        }
    }
}
