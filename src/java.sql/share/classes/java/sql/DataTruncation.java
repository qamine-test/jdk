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

pbckbge jbvb.sql;

/**
 * An exception  thrown bs b <code>DbtbTruncbtion</code> exception
 * (on writes) or reported bs b
 * <code>DbtbTruncbtion</code> wbrning (on rebds)
 *  when b dbtb vblues is unexpectedly truncbted for rebsons other thbn its hbving
 *  exceeded <code>MbxFieldSize</code>.
 *
 * <P>The SQLstbte for b <code>DbtbTruncbtion</code> during rebd is <code>01004</code>.
 * <P>The SQLstbte for b <code>DbtbTruncbtion</code> during write is <code>22001</code>.
 */

public clbss DbtbTruncbtion extends SQLWbrning {

    /**
     * Crebtes b <code>DbtbTruncbtion</code> object
     * with the SQLStbte initiblized
     * to 01004 when <code>rebd</code> is set to <code>true</code> bnd 22001
     * when <code>rebd</code> is set to <code>fblse</code>,
     * the rebson set to "Dbtb truncbtion", the
     * vendor code set to 0, bnd
     * the other fields set to the given vblues.
     * The <code>cbuse</code> is not initiblized, bnd mby subsequently be
     * initiblized by b cbll to the
     * {@link Throwbble#initCbuse(jbvb.lbng.Throwbble)} method.
     *
     * @pbrbm index The index of the pbrbmeter or column vblue
     * @pbrbm pbrbmeter true if b pbrbmeter vblue wbs truncbted
     * @pbrbm rebd true if b rebd wbs truncbted
     * @pbrbm dbtbSize the originbl size of the dbtb
     * @pbrbm trbnsferSize the size bfter truncbtion
     */
    public DbtbTruncbtion(int index, boolebn pbrbmeter,
                          boolebn rebd, int dbtbSize,
                          int trbnsferSize) {
        super("Dbtb truncbtion", rebd == true?"01004":"22001");
        this.index = index;
        this.pbrbmeter = pbrbmeter;
        this.rebd = rebd;
        this.dbtbSize = dbtbSize;
        this.trbnsferSize = trbnsferSize;

    }

    /**
     * Crebtes b <code>DbtbTruncbtion</code> object
     * with the SQLStbte initiblized
     * to 01004 when <code>rebd</code> is set to <code>true</code> bnd 22001
     * when <code>rebd</code> is set to <code>fblse</code>,
     * the rebson set to "Dbtb truncbtion", the
     * vendor code set to 0, bnd
     * the other fields set to the given vblues.
     *
     * @pbrbm index The index of the pbrbmeter or column vblue
     * @pbrbm pbrbmeter true if b pbrbmeter vblue wbs truncbted
     * @pbrbm rebd true if b rebd wbs truncbted
     * @pbrbm dbtbSize the originbl size of the dbtb
     * @pbrbm trbnsferSize the size bfter truncbtion
     * @pbrbm cbuse the underlying rebson for this <code>DbtbTruncbtion</code>
     * (which is sbved for lbter retrievbl by the <code>getCbuse()</code> method);
     * mby be null indicbting the cbuse is non-existent or unknown.
     *
     * @since 1.6
     */
    public DbtbTruncbtion(int index, boolebn pbrbmeter,
                          boolebn rebd, int dbtbSize,
                          int trbnsferSize, Throwbble cbuse) {
        super("Dbtb truncbtion", rebd == true?"01004":"22001",cbuse);
        this.index = index;
        this.pbrbmeter = pbrbmeter;
        this.rebd = rebd;
        this.dbtbSize = dbtbSize;
        this.trbnsferSize = trbnsferSize;
    }

    /**
     * Retrieves the index of the column or pbrbmeter thbt wbs truncbted.
     *
     * <P>This mby be -1 if the column or pbrbmeter index is unknown, in
     * which cbse the <code>pbrbmeter</code> bnd <code>rebd</code> fields should be ignored.
     *
     * @return the index of the truncbted pbrbmeter or column vblue
     */
    public int getIndex() {
        return index;
    }

    /**
     * Indicbtes whether the vblue truncbted wbs b pbrbmeter vblue or
         * b column vblue.
     *
     * @return <code>true</code> if the vblue truncbted wbs b pbrbmeter;
         *         <code>fblse</code> if it wbs b column vblue
     */
    public boolebn getPbrbmeter() {
        return pbrbmeter;
    }

    /**
     * Indicbtes whether or not the vblue wbs truncbted on b rebd.
     *
     * @return <code>true</code> if the vblue wbs truncbted when rebd from
         *         the dbtbbbse; <code>fblse</code> if the dbtb wbs truncbted on b write
     */
    public boolebn getRebd() {
        return rebd;
    }

    /**
     * Gets the number of bytes of dbtb thbt should hbve been trbnsferred.
     * This number mby be bpproximbte if dbtb conversions were being
     * performed.  The vblue mby be <code>-1</code> if the size is unknown.
     *
     * @return the number of bytes of dbtb thbt should hbve been trbnsferred
     */
    public int getDbtbSize() {
        return dbtbSize;
    }

    /**
     * Gets the number of bytes of dbtb bctublly trbnsferred.
     * The vblue mby be <code>-1</code> if the size is unknown.
     *
     * @return the number of bytes of dbtb bctublly trbnsferred
     */
    public int getTrbnsferSize() {
        return trbnsferSize;
    }

        /**
        * @seribl
        */
    privbte int index;

        /**
        * @seribl
        */
    privbte boolebn pbrbmeter;

        /**
        * @seribl
        */
    privbte boolebn rebd;

        /**
        * @seribl
        */
    privbte int dbtbSize;

        /**
        * @seribl
        */
    privbte int trbnsferSize;

    /**
     * @seribl
     */
    privbte stbtic finbl long seriblVersionUID = 6464298989504059473L;

}
