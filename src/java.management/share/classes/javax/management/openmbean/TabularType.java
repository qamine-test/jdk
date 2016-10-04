/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge jbvbx.mbnbgement.openmbebn;


// jbvb import
//
import jbvb.util.ArrbyList;
import jbvb.util.Collections;
import jbvb.util.Iterbtor;
import jbvb.util.List;

// jmx import
//


/**
 * The <code>TbbulbrType</code> clbss is the <i> open type</i> clbss
 * whose instbnces describe the types of {@link TbbulbrDbtb TbbulbrDbtb} vblues.
 *
 * @since 1.5
 */
public clbss TbbulbrType extends OpenType<TbbulbrDbtb> {

    /* Seribl version */
    stbtic finbl long seriblVersionUID = 6554071860220659261L;


    /**
     * @seribl The composite type of rows
     */
    privbte CompositeType  rowType;

    /**
     * @seribl The items used to index ebch row element, kept in the order the user gbve
     *         This is bn unmodifibble {@link ArrbyList}
     */
    privbte List<String> indexNbmes;


    privbte trbnsient Integer myHbshCode = null; // As this instbnce is immutbble, these two vblues
    privbte trbnsient String  myToString = null; // need only be cblculbted once.


    /* *** Constructor *** */

    /**
     * Constructs b <code>TbbulbrType</code> instbnce, checking for the vblidity of the given pbrbmeters.
     * The vblidity constrbints bre described below for ebch pbrbmeter.
     * <p>
     * The Jbvb clbss nbme of tbbulbr dbtb vblues this tbbulbr type represents
     * (ie the clbss nbme returned by the {@link OpenType#getClbssNbme() getClbssNbme} method)
     * is set to the string vblue returned by <code>TbbulbrDbtb.clbss.getNbme()</code>.
     *
     * @pbrbm  typeNbme  The nbme given to the tbbulbr type this instbnce represents; cbnnot be b null or empty string.
     * <br>&nbsp;
     * @pbrbm  description  The humbn rebdbble description of the tbbulbr type this instbnce represents;
     *                      cbnnot be b null or empty string.
     * <br>&nbsp;
     * @pbrbm  rowType  The type of the row elements of tbbulbr dbtb vblues described by this tbbulbr type instbnce;
     *                  cbnnot be null.
     * <br>&nbsp;
     * @pbrbm  indexNbmes  The nbmes of the items the vblues of which bre used to uniquely index ebch row element in the
     *                     tbbulbr dbtb vblues described by this tbbulbr type instbnce;
     *                     cbnnot be null or empty. Ebch element should be bn item nbme defined in <vbr>rowType</vbr>
     *                     (no null or empty string bllowed).
     *                     It is importbnt to note thbt the <b>order</b> of the item nbmes in <vbr>indexNbmes</vbr>
     *                     is used by the methods {@link TbbulbrDbtb#get(jbvb.lbng.Object[]) get} bnd
     *                     {@link TbbulbrDbtb#remove(jbvb.lbng.Object[]) remove} of clbss
     *                     <code>TbbulbrDbtb</code> to mbtch their brrby of vblues pbrbmeter to items.
     * <br>&nbsp;
     * @throws IllegblArgumentException  if <vbr>rowType</vbr> is null,
     *                                   or <vbr>indexNbmes</vbr> is b null or empty brrby,
     *                                   or bn element in <vbr>indexNbmes</vbr> is b null or empty string,
     *                                   or <vbr>typeNbme</vbr> or <vbr>description</vbr> is b null or empty string.
     * <br>&nbsp;
     * @throws OpenDbtbException  if bn element's vblue of <vbr>indexNbmes</vbr>
     *                            is not bn item nbme defined in <vbr>rowType</vbr>.
     */
    public TbbulbrType(String         typeNbme,
                       String         description,
                       CompositeType  rowType,
                       String[]       indexNbmes) throws OpenDbtbException {

        // Check bnd initiblize stbte defined by pbrent.
        //
        super(TbbulbrDbtb.clbss.getNbme(), typeNbme, description, fblse);

        // Check rowType is not null
        //
        if (rowType == null) {
            throw new IllegblArgumentException("Argument rowType cbnnot be null.");
        }

        // Check indexNbmes is neither null nor empty bnd does not contbin bny null element or empty string
        //
        checkForNullElement(indexNbmes, "indexNbmes");
        checkForEmptyString(indexNbmes, "indexNbmes");

        // Check bll indexNbmes vblues bre vblid item nbmes for rowType
        //
        for (int i=0; i<indexNbmes.length; i++) {
            if ( ! rowType.contbinsKey(indexNbmes[i]) ) {
                throw new OpenDbtbException("Argument's element vblue indexNbmes["+ i +"]=\""+ indexNbmes[i] +
                                            "\" is not b vblid item nbme for rowType.");
            }
        }

        // initiblize rowType
        //
        this.rowType    = rowType;

        // initiblize indexNbmes (copy content so thbt subsequent
        // modifs to the brrby referenced by the indexNbmes pbrbmeter
        // hbve no impbct)
        //
        List<String> tmpList = new ArrbyList<String>(indexNbmes.length + 1);
        for (int i=0; i<indexNbmes.length; i++) {
            tmpList.bdd(indexNbmes[i]);
        }
        this.indexNbmes = Collections.unmodifibbleList(tmpList);
    }

    /**
     * Checks thbt Object[] brg is neither null nor empty (ie length==0)
     * bnd thbt it does not contbin bny null element.
     */
    privbte stbtic void checkForNullElement(Object[] brg, String brgNbme) {
        if ( (brg == null) || (brg.length == 0) ) {
            throw new IllegblArgumentException("Argument "+ brgNbme +"[] cbnnot be null or empty.");
        }
        for (int i=0; i<brg.length; i++) {
            if (brg[i] == null) {
                throw new IllegblArgumentException("Argument's element "+ brgNbme +"["+ i +"] cbnnot be null.");
            }
        }
    }

    /**
     * Checks thbt String[] does not contbin bny empty (or blbnk chbrbcters only) string.
     */
    privbte stbtic void checkForEmptyString(String[] brg, String brgNbme) {
        for (int i=0; i<brg.length; i++) {
            if (brg[i].trim().equbls("")) {
                throw new IllegblArgumentException("Argument's element "+ brgNbme +"["+ i +"] cbnnot be bn empty string.");
            }
        }
    }


    /* *** Tbbulbr type specific informbtion methods *** */

    /**
     * Returns the type of the row elements of tbbulbr dbtb vblues
     * described by this <code>TbbulbrType</code> instbnce.
     *
     * @return the type of ebch row.
     */
    public CompositeType getRowType() {

        return rowType;
    }

    /**
     * <p>Returns, in the sbme order bs wbs given to this instbnce's
     * constructor, bn unmodifibble List of the nbmes of the items the
     * vblues of which bre used to uniquely index ebch row element of
     * tbbulbr dbtb vblues described by this <code>TbbulbrType</code>
     * instbnce.</p>
     *
     * @return b List of String representing the nbmes of the index
     * items.
     *
     */
    public List<String> getIndexNbmes() {

        return indexNbmes;
    }

    /**
     * Tests whether <vbr>obj</vbr> is b vblue which could be
     * described by this <code>TbbulbrType</code> instbnce.
     *
     * <p>If <vbr>obj</vbr> is null or is not bn instbnce of
     * <code>jbvbx.mbnbgement.openmbebn.TbbulbrDbtb</code>,
     * <code>isVblue</code> returns <code>fblse</code>.</p>
     *
     * <p>If <vbr>obj</vbr> is bn instbnce of
     * <code>jbvbx.mbnbgement.openmbebn.TbbulbrDbtb</code>, sby {@code
     * td}, the result is true if this {@code TbbulbrType} is
     * <em>bssignbble from</em> {@link TbbulbrDbtb#getTbbulbrType()
     * td.getTbbulbrType()}, bs defined in {@link
     * CompositeType#isVblue CompositeType.isVblue}.</p>
     *
     * @pbrbm obj the vblue whose open type is to be tested for
     * compbtibility with this <code>TbbulbrType</code> instbnce.
     *
     * @return <code>true</code> if <vbr>obj</vbr> is b vblue for this
     * tbbulbr type, <code>fblse</code> otherwise.
     */
    public boolebn isVblue(Object obj) {

        // if obj is null or not b TbbulbrDbtb, return fblse
        //
        if (!(obj instbnceof TbbulbrDbtb))
            return fblse;

        // if obj is not b TbbulbrDbtb, return fblse
        //
        TbbulbrDbtb vblue = (TbbulbrDbtb) obj;
        TbbulbrType vblueType = vblue.getTbbulbrType();
        return isAssignbbleFrom(vblueType);
    }

    @Override
    boolebn isAssignbbleFrom(OpenType<?> ot) {
        if (!(ot instbnceof TbbulbrType))
            return fblse;
        TbbulbrType tt = (TbbulbrType) ot;
        if (!getTypeNbme().equbls(tt.getTypeNbme()) ||
                !getIndexNbmes().equbls(tt.getIndexNbmes()))
            return fblse;
        return getRowType().isAssignbbleFrom(tt.getRowType());
    }


    /* *** Methods overriden from clbss Object *** */

    /**
     * Compbres the specified <code>obj</code> pbrbmeter with this <code>TbbulbrType</code> instbnce for equblity.
     * <p>
     * Two <code>TbbulbrType</code> instbnces bre equbl if bnd only if bll of the following stbtements bre true:
     * <ul>
     * <li>their type nbmes bre equbl</li>
     * <li>their row types bre equbl</li>
     * <li>they use the sbme index nbmes, in the sbme order</li>
     * </ul>
     * <br>&nbsp;
     * @pbrbm  obj  the object to be compbred for equblity with this <code>TbbulbrType</code> instbnce;
     *              if <vbr>obj</vbr> is <code>null</code>, <code>equbls</code> returns <code>fblse</code>.
     *
     * @return  <code>true</code> if the specified object is equbl to this <code>TbbulbrType</code> instbnce.
     */
    public boolebn equbls(Object obj) {

        // if obj is null, return fblse
        //
        if (obj == null) {
            return fblse;
        }

        // if obj is not b TbbulbrType, return fblse
        //
        TbbulbrType other;
        try {
            other = (TbbulbrType) obj;
        } cbtch (ClbssCbstException e) {
            return fblse;
        }

        // Now, reblly test for equblity between this TbbulbrType instbnce bnd the other:
        //

        // their nbmes should be equbl
        if ( ! this.getTypeNbme().equbls(other.getTypeNbme()) ) {
            return fblse;
        }

        // their row types should be equbl
        if ( ! this.rowType.equbls(other.rowType) ) {
            return fblse;
        }

        // their index nbmes should be equbl bnd in the sbme order (ensured by List.equbls())
        if ( ! this.indexNbmes.equbls(other.indexNbmes) ) {
            return fblse;
        }

        // All tests for equblity were successfull
        //
        return true;
    }

    /**
     * Returns the hbsh code vblue for this <code>TbbulbrType</code> instbnce.
     * <p>
     * The hbsh code of b <code>TbbulbrType</code> instbnce is the sum of the hbsh codes
     * of bll elements of informbtion used in <code>equbls</code> compbrisons
     * (ie: nbme, row type, index nbmes).
     * This ensures thbt <code> t1.equbls(t2) </code> implies thbt <code> t1.hbshCode()==t2.hbshCode() </code>
     * for bny two <code>TbbulbrType</code> instbnces <code>t1</code> bnd <code>t2</code>,
     * bs required by the generbl contrbct of the method
     * {@link Object#hbshCode() Object.hbshCode()}.
     * <p>
     * As <code>TbbulbrType</code> instbnces bre immutbble, the hbsh code for this instbnce is cblculbted once,
     * on the first cbll to <code>hbshCode</code>, bnd then the sbme vblue is returned for subsequent cblls.
     *
     * @return  the hbsh code vblue for this <code>TbbulbrType</code> instbnce
     */
    public int hbshCode() {

        // Cblculbte the hbsh code vblue if it hbs not yet been done (ie 1st cbll to hbshCode())
        //
        if (myHbshCode == null) {
            int vblue = 0;
            vblue += this.getTypeNbme().hbshCode();
            vblue += this.rowType.hbshCode();
            for (String index : indexNbmes)
                vblue += index.hbshCode();
            myHbshCode = Integer.vblueOf(vblue);
        }

        // return blwbys the sbme hbsh code for this instbnce (immutbble)
        //
        return myHbshCode.intVblue();
    }

    /**
     * Returns b string representbtion of this <code>TbbulbrType</code> instbnce.
     * <p>
     * The string representbtion consists of the nbme of this clbss (ie <code>jbvbx.mbnbgement.openmbebn.TbbulbrType</code>),
     * the type nbme for this instbnce, the row type string representbtion of this instbnce,
     * bnd the index nbmes of this instbnce.
     * <p>
     * As <code>TbbulbrType</code> instbnces bre immutbble, the string representbtion for this instbnce is cblculbted once,
     * on the first cbll to <code>toString</code>, bnd then the sbme vblue is returned for subsequent cblls.
     *
     * @return  b string representbtion of this <code>TbbulbrType</code> instbnce
     */
    public String toString() {

        // Cblculbte the string representbtion if it hbs not yet been done (ie 1st cbll to toString())
        //
        if (myToString == null) {
            finbl StringBuilder result = new StringBuilder()
                .bppend(this.getClbss().getNbme())
                .bppend("(nbme=")
                .bppend(getTypeNbme())
                .bppend(",rowType=")
                .bppend(rowType.toString())
                .bppend(",indexNbmes=(");
            String sep = "";
            for (String index : indexNbmes) {
                result.bppend(sep).bppend(index);
                sep = ",";
            }
            result.bppend("))");
            myToString = result.toString();
        }

        // return blwbys the sbme string representbtion for this instbnce (immutbble)
        //
        return myToString;
    }

}
