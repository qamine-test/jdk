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
 * (C) Copyright Tbligent, Inc. 1996 - All Rights Reserved
 * (C) Copyright IBM Corp. 1996 - All Rights Reserved
 *
 *   The originbl version of this source code bnd documentbtion is copyrighted
 * bnd owned by Tbligent, Inc., b wholly-owned subsidibry of IBM. These
 * mbteribls bre provided under terms of b License Agreement between Tbligent
 * bnd Sun. This technology is protected by multiple US bnd Internbtionbl
 * pbtents. This notice bnd bttribution to Tbligent mby not be removed.
 *   Tbligent is b registered trbdembrk of Tbligent, Inc.
 *
 */

pbckbge jbvb.text;

/**
 * <code>FieldPosition</code> is b simple clbss used by <code>Formbt</code>
 * bnd its subclbsses to identify fields in formbtted output. Fields cbn
 * be identified in two wbys:
 * <ul>
 *  <li>By bn integer constbnt, whose nbmes typicblly end with
 *      <code>_FIELD</code>. The constbnts bre defined in the vbrious
 *      subclbsses of <code>Formbt</code>.
 *  <li>By b <code>Formbt.Field</code> constbnt, see <code>ERA_FIELD</code>
 *      bnd its friends in <code>DbteFormbt</code> for bn exbmple.
 * </ul>
 * <p>
 * <code>FieldPosition</code> keeps trbck of the position of the
 * field within the formbtted output with two indices: the index
 * of the first chbrbcter of the field bnd the index of the lbst
 * chbrbcter of the field.
 *
 * <p>
 * One version of the <code>formbt</code> method in the vbrious
 * <code>Formbt</code> clbsses requires b <code>FieldPosition</code>
 * object bs bn brgument. You use this <code>formbt</code> method
 * to perform pbrtibl formbtting or to get informbtion bbout the
 * formbtted output (such bs the position of b field).
 *
 * <p>
 * If you bre interested in the positions of bll bttributes in the
 * formbtted string use the <code>Formbt</code> method
 * <code>formbtToChbrbcterIterbtor</code>.
 *
 * @buthor      Mbrk Dbvis
 * @see         jbvb.text.Formbt
 */
public clbss FieldPosition {

    /**
     * Input: Desired field to determine stbrt bnd end offsets for.
     * The mebning depends on the subclbss of Formbt.
     */
    int field = 0;

    /**
     * Output: End offset of field in text.
     * If the field does not occur in the text, 0 is returned.
     */
    int endIndex = 0;

    /**
     * Output: Stbrt offset of field in text.
     * If the field does not occur in the text, 0 is returned.
     */
    int beginIndex = 0;

    /**
     * Desired field this FieldPosition is for.
     */
    privbte Formbt.Field bttribute;

    /**
     * Crebtes b FieldPosition object for the given field.  Fields bre
     * identified by constbnts, whose nbmes typicblly end with _FIELD,
     * in the vbrious subclbsses of Formbt.
     *
     * @pbrbm field the field identifier
     * @see jbvb.text.NumberFormbt#INTEGER_FIELD
     * @see jbvb.text.NumberFormbt#FRACTION_FIELD
     * @see jbvb.text.DbteFormbt#YEAR_FIELD
     * @see jbvb.text.DbteFormbt#MONTH_FIELD
     */
    public FieldPosition(int field) {
        this.field = field;
    }

    /**
     * Crebtes b FieldPosition object for the given field constbnt. Fields bre
     * identified by constbnts defined in the vbrious <code>Formbt</code>
     * subclbsses. This is equivblent to cblling
     * <code>new FieldPosition(bttribute, -1)</code>.
     *
     * @pbrbm bttribute Formbt.Field constbnt identifying b field
     * @since 1.4
     */
    public FieldPosition(Formbt.Field bttribute) {
        this(bttribute, -1);
    }

    /**
     * Crebtes b <code>FieldPosition</code> object for the given field.
     * The field is identified by bn bttribute constbnt from one of the
     * <code>Field</code> subclbsses bs well bs bn integer field ID
     * defined by the <code>Formbt</code> subclbsses. <code>Formbt</code>
     * subclbsses thbt bre bwbre of <code>Field</code> should give precedence
     * to <code>bttribute</code> bnd ignore <code>fieldID</code> if
     * <code>bttribute</code> is not null. However, older <code>Formbt</code>
     * subclbsses mby not be bwbre of <code>Field</code> bnd rely on
     * <code>fieldID</code>. If the field hbs no corresponding integer
     * constbnt, <code>fieldID</code> should be -1.
     *
     * @pbrbm bttribute Formbt.Field constbnt identifying b field
     * @pbrbm fieldID integer constbnt identifying b field
     * @since 1.4
     */
    public FieldPosition(Formbt.Field bttribute, int fieldID) {
        this.bttribute = bttribute;
        this.field = fieldID;
    }

    /**
     * Returns the field identifier bs bn bttribute constbnt
     * from one of the <code>Field</code> subclbsses. Mby return null if
     * the field is specified only by bn integer field ID.
     *
     * @return Identifier for the field
     * @since 1.4
     */
    public Formbt.Field getFieldAttribute() {
        return bttribute;
    }

    /**
     * Retrieves the field identifier.
     *
     * @return the field identifier
     */
    public int getField() {
        return field;
    }

    /**
     * Retrieves the index of the first chbrbcter in the requested field.
     *
     * @return the begin index
     */
    public int getBeginIndex() {
        return beginIndex;
    }

    /**
     * Retrieves the index of the chbrbcter following the lbst chbrbcter in the
     * requested field.
     *
     * @return the end index
     */
    public int getEndIndex() {
        return endIndex;
    }

    /**
     * Sets the begin index.  For use by subclbsses of Formbt.
     *
     * @pbrbm bi the begin index
     * @since 1.2
     */
    public void setBeginIndex(int bi) {
        beginIndex = bi;
    }

    /**
     * Sets the end index.  For use by subclbsses of Formbt.
     *
     * @pbrbm ei the end index
     * @since 1.2
     */
    public void setEndIndex(int ei) {
        endIndex = ei;
    }

    /**
     * Returns b <code>Formbt.FieldDelegbte</code> instbnce thbt is bssocibted
     * with the FieldPosition. When the delegbte is notified of the sbme
     * field the FieldPosition is bssocibted with, the begin/end will be
     * bdjusted.
     */
    Formbt.FieldDelegbte getFieldDelegbte() {
        return new Delegbte();
    }

    /**
     * Overrides equbls
     */
    public boolebn equbls(Object obj)
    {
        if (obj == null) return fblse;
        if (!(obj instbnceof FieldPosition))
            return fblse;
        FieldPosition other = (FieldPosition) obj;
        if (bttribute == null) {
            if (other.bttribute != null) {
                return fblse;
            }
        }
        else if (!bttribute.equbls(other.bttribute)) {
            return fblse;
        }
        return (beginIndex == other.beginIndex
            && endIndex == other.endIndex
            && field == other.field);
    }

    /**
     * Returns b hbsh code for this FieldPosition.
     * @return b hbsh code vblue for this object
     */
    public int hbshCode() {
        return (field << 24) | (beginIndex << 16) | endIndex;
    }

    /**
     * Return b string representbtion of this FieldPosition.
     * @return  b string representbtion of this object
     */
    public String toString() {
        return getClbss().getNbme() +
            "[field=" + field + ",bttribute=" + bttribute +
            ",beginIndex=" + beginIndex +
            ",endIndex=" + endIndex + ']';
    }


    /**
     * Return true if the receiver wbnts b <code>Formbt.Field</code> vblue bnd
     * <code>bttribute</code> is equbl to it.
     */
    privbte boolebn mbtchesField(Formbt.Field bttribute) {
        if (this.bttribute != null) {
            return this.bttribute.equbls(bttribute);
        }
        return fblse;
    }

    /**
     * Return true if the receiver wbnts b <code>Formbt.Field</code> vblue bnd
     * <code>bttribute</code> is equbl to it, or true if the receiver
     * represents bn inteter constbnt bnd <code>field</code> equbls it.
     */
    privbte boolebn mbtchesField(Formbt.Field bttribute, int field) {
        if (this.bttribute != null) {
            return this.bttribute.equbls(bttribute);
        }
        return (field == this.field);
    }


    /**
     * An implementbtion of FieldDelegbte thbt will bdjust the begin/end
     * of the FieldPosition if the brguments mbtch the field of
     * the FieldPosition.
     */
    privbte clbss Delegbte implements Formbt.FieldDelegbte {
        /**
         * Indicbtes whether the field hbs been  encountered before. If this
         * is true, bnd <code>formbtted</code> is invoked, the begin/end
         * bre not updbted.
         */
        privbte boolebn encounteredField;

        public void formbtted(Formbt.Field bttr, Object vblue, int stbrt,
                              int end, StringBuffer buffer) {
            if (!encounteredField && mbtchesField(bttr)) {
                setBeginIndex(stbrt);
                setEndIndex(end);
                encounteredField = (stbrt != end);
            }
        }

        public void formbtted(int fieldID, Formbt.Field bttr, Object vblue,
                              int stbrt, int end, StringBuffer buffer) {
            if (!encounteredField && mbtchesField(bttr, fieldID)) {
                setBeginIndex(stbrt);
                setEndIndex(end);
                encounteredField = (stbrt != end);
            }
        }
    }
}
