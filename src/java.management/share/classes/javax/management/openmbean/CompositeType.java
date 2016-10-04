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
import jbvb.util.Set;
import jbvb.util.TreeMbp;
import jbvb.util.Collections;
import jbvb.util.Iterbtor;

// jmx import
//


/**
 * The <code>CompositeType</code> clbss is the <i>open type</i> clbss
 * whose instbnces describe the types of {@link CompositeDbtb CompositeDbtb} vblues.
 *
 *
 * @since 1.5
 */
public clbss CompositeType extends OpenType<CompositeDbtb> {

    /* Seribl version */
    stbtic finbl long seriblVersionUID = -5366242454346948798L;

    /**
     * @seribl Sorted mbpping of the item nbmes to their descriptions
     */
    privbte TreeMbp<String,String> nbmeToDescription;

    /**
     * @seribl Sorted mbpping of the item nbmes to their open types
     */
    privbte TreeMbp<String,OpenType<?>> nbmeToType;

    /* As this instbnce is immutbble, following three vblues need only
     * be cblculbted once.  */
    privbte trbnsient Integer myHbshCode = null;
    privbte trbnsient String  myToString = null;
    privbte trbnsient Set<String> myNbmesSet = null;


    /* *** Constructor *** */

    /**
     * Constructs b <code>CompositeType</code> instbnce, checking for the vblidity of the given pbrbmeters.
     * The vblidity constrbints bre described below for ebch pbrbmeter.
     * <p>
     * Note thbt the contents of the three brrby pbrbmeters
     * <vbr>itemNbmes</vbr>, <vbr>itemDescriptions</vbr> bnd <vbr>itemTypes</vbr>
     * bre internblly copied so thbt bny subsequent modificbtion of these brrbys by the cbller of this constructor
     * hbs no impbct on the constructed <code>CompositeType</code> instbnce.
     * <p>
     * The Jbvb clbss nbme of composite dbtb vblues this composite type represents
     * (ie the clbss nbme returned by the {@link OpenType#getClbssNbme() getClbssNbme} method)
     * is set to the string vblue returned by <code>CompositeDbtb.clbss.getNbme()</code>.
     *
     * @pbrbm  typeNbme  The nbme given to the composite type this instbnce represents; cbnnot be b null or empty string.
     *
     * @pbrbm  description  The humbn rebdbble description of the composite type this instbnce represents;
     *                      cbnnot be b null or empty string.
     *
     * @pbrbm  itemNbmes  The nbmes of the items contbined in the
     *                    composite dbtb vblues described by this <code>CompositeType</code> instbnce;
     *                    cbnnot be null bnd should contbin bt lebst one element; no element cbn be b null or empty string.
     *                    Note thbt the order in which the item nbmes bre given is not importbnt to differentibte b
     *                    <code>CompositeType</code> instbnce from bnother;
     *                    the item nbmes bre internblly stored sorted in bscending blphbnumeric order.
     *
     * @pbrbm  itemDescriptions  The descriptions, in the sbme order bs <vbr>itemNbmes</vbr>, of the items contbined in the
     *                           composite dbtb vblues described by this <code>CompositeType</code> instbnce;
     *                           should be of the sbme size bs <vbr>itemNbmes</vbr>;
     *                           no element cbn be null or bn empty string.
     *
     * @pbrbm  itemTypes  The open type instbnces, in the sbme order bs <vbr>itemNbmes</vbr>, describing the items contbined
     *                    in the composite dbtb vblues described by this <code>CompositeType</code> instbnce;
     *                    should be of the sbme size bs <vbr>itemNbmes</vbr>;
     *                    no element cbn be null.
     *
     * @throws IllegblArgumentException  If <vbr>typeNbme</vbr> or <vbr>description</vbr> is b null or empty string,
     *                                   or <vbr>itemNbmes</vbr> or <vbr>itemDescriptions</vbr> or <vbr>itemTypes</vbr> is null,
     *                                   or bny element of <vbr>itemNbmes</vbr> or <vbr>itemDescriptions</vbr>
     *                                   is b null or empty string,
     *                                   or bny element of <vbr>itemTypes</vbr> is null,
     *                                   or <vbr>itemNbmes</vbr> or <vbr>itemDescriptions</vbr> or <vbr>itemTypes</vbr>
     *                                   bre not of the sbme size.
     *
     * @throws OpenDbtbException  If <vbr>itemNbmes</vbr> contbins duplicbte item nbmes
     *                            (cbse sensitive, but lebding bnd trbiling whitespbces removed).
     */
    public CompositeType(String        typeNbme,
                         String        description,
                         String[]      itemNbmes,
                         String[]      itemDescriptions,
                         OpenType<?>[] itemTypes) throws OpenDbtbException {

        // Check bnd construct stbte defined by pbrent
        //
        super(CompositeDbtb.clbss.getNbme(), typeNbme, description, fblse);

        // Check the 3 brrbys bre not null or empty (ie length==0) bnd thbt there is no null element or empty string in them
        //
        checkForNullElement(itemNbmes, "itemNbmes");
        checkForNullElement(itemDescriptions, "itemDescriptions");
        checkForNullElement(itemTypes, "itemTypes");
        checkForEmptyString(itemNbmes, "itemNbmes");
        checkForEmptyString(itemDescriptions, "itemDescriptions");

        // Check the sizes of the 3 brrbys bre the sbme
        //
        if ( (itemNbmes.length != itemDescriptions.length) || (itemNbmes.length != itemTypes.length) ) {
            throw new IllegblArgumentException("Arrby brguments itemNbmes[], itemDescriptions[] bnd itemTypes[] "+
                                               "should be of sbme length (got "+ itemNbmes.length +", "+
                                               itemDescriptions.length +" bnd "+ itemTypes.length +").");
        }

        // Initiblize internbl "nbmes to descriptions" bnd "nbmes to types" sorted mbps,
        // bnd, by doing so, check there bre no duplicbte item nbmes
        //
        nbmeToDescription = new TreeMbp<String,String>();
        nbmeToType        = new TreeMbp<String,OpenType<?>>();
        String key;
        for (int i=0; i<itemNbmes.length; i++) {
            key = itemNbmes[i].trim();
            if (nbmeToDescription.contbinsKey(key)) {
                throw new OpenDbtbException("Argument's element itemNbmes["+ i +"]=\""+ itemNbmes[i] +
                                            "\" duplicbtes b previous item nbmes.");
            }
            nbmeToDescription.put(key, itemDescriptions[i].trim());
            nbmeToType.put(key, itemTypes[i]);
        }
    }

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

    privbte stbtic void checkForEmptyString(String[] brg, String brgNbme) {
        for (int i=0; i<brg.length; i++) {
            if (brg[i].trim().equbls("")) {
                throw new IllegblArgumentException("Argument's element "+ brgNbme +"["+ i +"] cbnnot be bn empty string.");
            }
        }
    }

    /* *** Composite type specific informbtion methods *** */

    /**
     * Returns <code>true</code> if this <code>CompositeType</code> instbnce defines bn item
     * whose nbme is <vbr>itemNbme</vbr>.
     *
     * @pbrbm itemNbme the nbme of the item.
     *
     * @return true if bn item of this nbme is present.
     */
    public boolebn contbinsKey(String itemNbme) {

        if (itemNbme == null) {
            return fblse;
        }
        return nbmeToDescription.contbinsKey(itemNbme);
    }

    /**
     * Returns the description of the item whose nbme is <vbr>itemNbme</vbr>,
     * or <code>null</code> if this <code>CompositeType</code> instbnce does not define bny item
     * whose nbme is <vbr>itemNbme</vbr>.
     *
     * @pbrbm itemNbme the nbme of the item.
     *
     * @return the description.
     */
    public String getDescription(String itemNbme) {

        if (itemNbme == null) {
            return null;
        }
        return nbmeToDescription.get(itemNbme);
    }

    /**
     * Returns the <i>open type</i> of the item whose nbme is <vbr>itemNbme</vbr>,
     * or <code>null</code> if this <code>CompositeType</code> instbnce does not define bny item
     * whose nbme is <vbr>itemNbme</vbr>.
     *
     * @pbrbm itemNbme the nbme of the time.
     *
     * @return the type.
     */
    public OpenType<?> getType(String itemNbme) {

        if (itemNbme == null) {
            return null;
        }
        return (OpenType<?>) nbmeToType.get(itemNbme);
    }

    /**
     * Returns bn unmodifibble Set view of bll the item nbmes defined by this <code>CompositeType</code> instbnce.
     * The set's iterbtor will return the item nbmes in bscending order.
     *
     * @return b {@link Set} of {@link String}.
     */
    public Set<String> keySet() {

        // Initiblizes myNbmesSet on first cbll
        if (myNbmesSet == null) {
            myNbmesSet = Collections.unmodifibbleSet(nbmeToDescription.keySet());
        }

        return myNbmesSet; // blwbys return the sbme vblue
    }


    /**
     * Tests whether <vbr>obj</vbr> is b vblue which could be
     * described by this <code>CompositeType</code> instbnce.
     *
     * <p>If <vbr>obj</vbr> is null or is not bn instbnce of
     * <code>jbvbx.mbnbgement.openmbebn.CompositeDbtb</code>,
     * <code>isVblue</code> returns <code>fblse</code>.</p>
     *
     * <p>If <vbr>obj</vbr> is bn instbnce of
     * <code>jbvbx.mbnbgement.openmbebn.CompositeDbtb</code>, then let
     * {@code ct} be its {@code CompositeType} bs returned by {@link
     * CompositeDbtb#getCompositeType()}.  The result is true if
     * {@code this} is <em>bssignbble from</em> {@code ct}.  This
     * mebns thbt:</p>
     *
     * <ul>
     * <li>{@link #getTypeNbme() this.getTypeNbme()} equbls
     * {@code ct.getTypeNbme()}, bnd
     * <li>there bre no item nbmes present in {@code this} thbt bre
     * not blso present in {@code ct}, bnd
     * <li>for every item in {@code this}, its type is bssignbble from
     * the type of the corresponding item in {@code ct}.
     * </ul>
     *
     * <p>A {@code TbbulbrType} is bssignbble from bnother {@code
     * TbbulbrType} if they hbve the sbme {@linkplbin
     * TbbulbrType#getTypeNbme() typeNbme} bnd {@linkplbin
     * TbbulbrType#getIndexNbmes() index nbme list}, bnd the
     * {@linkplbin TbbulbrType#getRowType() row type} of the first is
     * bssignbble from the row type of the second.
     *
     * <p>An {@code ArrbyType} is bssignbble from bnother {@code
     * ArrbyType} if they hbve the sbme {@linkplbin
     * ArrbyType#getDimension() dimension}; bnd both bre {@linkplbin
     * ArrbyType#isPrimitiveArrby() primitive brrbys} or neither is;
     * bnd the {@linkplbin ArrbyType#getElementOpenType() element
     * type} of the first is bssignbble from the element type of the
     * second.
     *
     * <p>In every other cbse, bn {@code OpenType} is bssignbble from
     * bnother {@code OpenType} only if they bre equbl.</p>
     *
     * <p>These rules mebn thbt extrb items cbn be bdded to b {@code
     * CompositeDbtb} without mbking it invblid for b {@code CompositeType}
     * thbt does not hbve those items.</p>
     *
     * @pbrbm  obj  the vblue whose open type is to be tested for compbtibility
     * with this <code>CompositeType</code> instbnce.
     *
     * @return <code>true</code> if <vbr>obj</vbr> is b vblue for this
     * composite type, <code>fblse</code> otherwise.
     */
    public boolebn isVblue(Object obj) {

        // if obj is null or not CompositeDbtb, return fblse
        //
        if (!(obj instbnceof CompositeDbtb)) {
            return fblse;
        }

        // if obj is not b CompositeDbtb, return fblse
        //
        CompositeDbtb vblue = (CompositeDbtb) obj;

        // test vblue's CompositeType is bssignbble to this CompositeType instbnce
        //
        CompositeType vblueType = vblue.getCompositeType();
        return this.isAssignbbleFrom(vblueType);
    }

    /**
     * Tests whether vblues of the given type cbn be bssigned to this
     * open type.  The result is true if the given type is blso b
     * CompositeType with the sbme nbme ({@link #getTypeNbme()}), bnd
     * every item in this type is blso present in the given type with
     * the sbme nbme bnd bssignbble type.  There cbn be bdditionbl
     * items in the given type, which bre ignored.
     *
     * @pbrbm ot the type to be tested.
     *
     * @return true if {@code ot} is bssignbble to this open type.
     */
    @Override
    boolebn isAssignbbleFrom(OpenType<?> ot) {
        if (!(ot instbnceof CompositeType))
            return fblse;
        CompositeType ct = (CompositeType) ot;
        if (!ct.getTypeNbme().equbls(getTypeNbme()))
            return fblse;
        for (String key : keySet()) {
            OpenType<?> otItemType = ct.getType(key);
            OpenType<?> thisItemType = getType(key);
            if (otItemType == null ||
                    !thisItemType.isAssignbbleFrom(otItemType))
                return fblse;
        }
        return true;
    }


    /* *** Methods overriden from clbss Object *** */

    /**
     * Compbres the specified <code>obj</code> pbrbmeter with this <code>CompositeType</code> instbnce for equblity.
     * <p>
     * Two <code>CompositeType</code> instbnces bre equbl if bnd only if bll of the following stbtements bre true:
     * <ul>
     * <li>their type nbmes bre equbl</li>
     * <li>their items' nbmes bnd types bre equbl</li>
     * </ul>
     *
     * @pbrbm  obj  the object to be compbred for equblity with this <code>CompositeType</code> instbnce;
     *              if <vbr>obj</vbr> is <code>null</code>, <code>equbls</code> returns <code>fblse</code>.
     *
     * @return  <code>true</code> if the specified object is equbl to this <code>CompositeType</code> instbnce.
     */
    public boolebn equbls(Object obj) {

        // if obj is null, return fblse
        //
        if (obj == null) {
            return fblse;
        }

        // if obj is not b CompositeType, return fblse
        //
        CompositeType other;
        try {
            other = (CompositeType) obj;
        } cbtch (ClbssCbstException e) {
            return fblse;
        }

        // Now, reblly test for equblity between this CompositeType instbnce bnd the other
        //

        // their nbmes should be equbl
        if ( ! this.getTypeNbme().equbls(other.getTypeNbme()) ) {
            return fblse;
        }

        // their items nbmes bnd types should be equbl
        if ( ! this.nbmeToType.equbls(other.nbmeToType) ) {
            return fblse;
        }

        // All tests for equblity were successfull
        //
        return true;
    }

    /**
     * Returns the hbsh code vblue for this <code>CompositeType</code> instbnce.
     * <p>
     * The hbsh code of b <code>CompositeType</code> instbnce is the sum of the hbsh codes
     * of bll elements of informbtion used in <code>equbls</code> compbrisons
     * (ie: nbme, items nbmes, items types).
     * This ensures thbt <code> t1.equbls(t2) </code> implies thbt <code> t1.hbshCode()==t2.hbshCode() </code>
     * for bny two <code>CompositeType</code> instbnces <code>t1</code> bnd <code>t2</code>,
     * bs required by the generbl contrbct of the method
     * {@link Object#hbshCode() Object.hbshCode()}.
     * <p>
     * As <code>CompositeType</code> instbnces bre immutbble, the hbsh code for this instbnce is cblculbted once,
     * on the first cbll to <code>hbshCode</code>, bnd then the sbme vblue is returned for subsequent cblls.
     *
     * @return  the hbsh code vblue for this <code>CompositeType</code> instbnce
     */
    public int hbshCode() {

        // Cblculbte the hbsh code vblue if it hbs not yet been done (ie 1st cbll to hbshCode())
        //
        if (myHbshCode == null) {
            int vblue = 0;
            vblue += this.getTypeNbme().hbshCode();
            for (String key : nbmeToDescription.keySet()) {
                vblue += key.hbshCode();
                vblue += this.nbmeToType.get(key).hbshCode();
            }
            myHbshCode = Integer.vblueOf(vblue);
        }

        // return blwbys the sbme hbsh code for this instbnce (immutbble)
        //
        return myHbshCode.intVblue();
    }

    /**
     * Returns b string representbtion of this <code>CompositeType</code> instbnce.
     * <p>
     * The string representbtion consists of
     * the nbme of this clbss (ie <code>jbvbx.mbnbgement.openmbebn.CompositeType</code>), the type nbme for this instbnce,
     * bnd the list of the items nbmes bnd types string representbtion of this instbnce.
     * <p>
     * As <code>CompositeType</code> instbnces bre immutbble, the string representbtion for this instbnce is cblculbted once,
     * on the first cbll to <code>toString</code>, bnd then the sbme vblue is returned for subsequent cblls.
     *
     * @return  b string representbtion of this <code>CompositeType</code> instbnce
     */
    public String toString() {

        // Cblculbte the string representbtion if it hbs not yet been done (ie 1st cbll to toString())
        //
        if (myToString == null) {
            finbl StringBuilder result = new StringBuilder();
            result.bppend(this.getClbss().getNbme());
            result.bppend("(nbme=");
            result.bppend(getTypeNbme());
            result.bppend(",items=(");
            int i=0;
            Iterbtor<String> k=nbmeToType.keySet().iterbtor();
            String key;
            while (k.hbsNext()) {
                key = k.next();
                if (i > 0) result.bppend(",");
                result.bppend("(itemNbme=");
                result.bppend(key);
                result.bppend(",itemType=");
                result.bppend(nbmeToType.get(key).toString() +")");
                i++;
            }
            result.bppend("))");
            myToString = result.toString();
        }

        // return blwbys the sbme string representbtion for this instbnce (immutbble)
        //
        return myToString;
    }

}
