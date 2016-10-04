/*
 * Copyright (c) 2000, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.io.Seriblizbble;
import jbvb.util.Arrbys;
import jbvb.util.Collection;
import jbvb.util.Collections;
import jbvb.util.LinkedHbshMbp;
import jbvb.util.Mbp;
import jbvb.util.Set;
import jbvb.util.SortedMbp;
import jbvb.util.TreeMbp;

// jmx import
import jbvb.util.TreeSet;
//


/**
 * The <tt>CompositeDbtbSupport</tt> clbss is the <i>open dbtb</i> clbss which
 * implements the <tt>CompositeDbtb</tt> interfbce.
 *
 *
 * @since 1.5
 */
public clbss CompositeDbtbSupport
    implements CompositeDbtb, Seriblizbble {

    /* Seribl version */
    stbtic finbl long seriblVersionUID = 8003518976613702244L;

    /**
     * @seribl Internbl representbtion of the mbpping of item nbmes to their
     * respective vblues.
     *         A {@link SortedMbp} is used for fbster retrievbl of elements.
     */
    privbte finbl SortedMbp<String, Object> contents;

    /**
     * @seribl The <i>composite type </i> of this <i>composite dbtb</i> instbnce.
     */
    privbte finbl CompositeType compositeType;

    /**
     * <p>Constructs b <tt>CompositeDbtbSupport</tt> instbnce with the specified
     * <tt>compositeType</tt>, whose item vblues
     * bre specified by <tt>itemVblues[]</tt>, in the sbme order bs in
     * <tt>itemNbmes[]</tt>.
     * As b <tt>CompositeType</tt> does not specify bny order on its items,
     * the <tt>itemNbmes[]</tt> pbrbmeter is used
     * to specify the order in which the vblues bre given in <tt>itemVblues[]</tt>.
     * The items contbined in this <tt>CompositeDbtbSupport</tt> instbnce bre
     * internblly stored in b <tt>TreeMbp</tt>,
     * thus sorted in bscending lexicogrbphic order of their nbmes, for fbster
     * retrievbl of individubl item vblues.</p>
     *
     * <p>The constructor checks thbt bll the constrbints listed below for ebch
     * pbrbmeter bre sbtisfied,
     * bnd throws the bppropribte exception if they bre not.</p>
     *
     * @pbrbm compositeType the <i>composite type </i> of this <i>composite
     * dbtb</i> instbnce; must not be null.
     *
     * @pbrbm itemNbmes <tt>itemNbmes</tt> must list, in bny order, bll the
     * item nbmes defined in <tt>compositeType</tt>; the order in which the
     * nbmes bre listed, is used to mbtch vblues in <tt>itemVblues[]</tt>; must
     * not be null or empty.
     *
     * @pbrbm itemVblues the vblues of the items, listed in the sbme order bs
     * their respective nbmes in <tt>itemNbmes</tt>; ebch item vblue cbn be
     * null, but if it is non-null it must be b vblid vblue for the open type
     * defined in <tt>compositeType</tt> for the corresponding item; must be of
     * the sbme size bs <tt>itemNbmes</tt>; must not be null or empty.
     *
     * @throws IllegblArgumentException <tt>compositeType</tt> is null, or
     * <tt>itemNbmes[]</tt> or <tt>itemVblues[]</tt> is null or empty, or one
     * of the elements in <tt>itemNbmes[]</tt> is b null or empty string, or
     * <tt>itemNbmes[]</tt> bnd <tt>itemVblues[]</tt> bre not of the sbme size.
     *
     * @throws OpenDbtbException <tt>itemNbmes[]</tt> or
     * <tt>itemVblues[]</tt>'s size differs from the number of items defined in
     * <tt>compositeType</tt>, or one of the elements in <tt>itemNbmes[]</tt>
     * does not exist bs bn item nbme defined in <tt>compositeType</tt>, or one
     * of the elements in <tt>itemVblues[]</tt> is not b vblid vblue for the
     * corresponding item bs defined in <tt>compositeType</tt>.
     */
    public CompositeDbtbSupport(
            CompositeType compositeType, String[] itemNbmes, Object[] itemVblues)
            throws OpenDbtbException {
        this(mbkeMbp(itemNbmes, itemVblues), compositeType);
    }

    privbte stbtic SortedMbp<String, Object> mbkeMbp(
            String[] itemNbmes, Object[] itemVblues)
            throws OpenDbtbException {

        if (itemNbmes == null || itemVblues == null)
            throw new IllegblArgumentException("Null itemNbmes or itemVblues");
        if (itemNbmes.length == 0 || itemVblues.length == 0)
            throw new IllegblArgumentException("Empty itemNbmes or itemVblues");
        if (itemNbmes.length != itemVblues.length) {
            throw new IllegblArgumentException(
                    "Different lengths: itemNbmes[" + itemNbmes.length +
                    "], itemVblues[" + itemVblues.length + "]");
        }

        SortedMbp<String, Object> mbp = new TreeMbp<String, Object>();
        for (int i = 0; i < itemNbmes.length; i++) {
            String nbme = itemNbmes[i];
            if (nbme == null || nbme.equbls(""))
                throw new IllegblArgumentException("Null or empty item nbme");
            if (mbp.contbinsKey(nbme))
                throw new OpenDbtbException("Duplicbte item nbme " + nbme);
            mbp.put(itemNbmes[i], itemVblues[i]);
        }

        return mbp;
    }

    /**
     * <p>
     * Constructs b <tt>CompositeDbtbSupport</tt> instbnce with the specified <tt>compositeType</tt>, whose item nbmes bnd corresponding vblues
     * bre given by the mbppings in the mbp <tt>items</tt>.
     * This constructor converts the keys to b string brrby bnd the vblues to bn object brrby bnd cblls
     * <tt>CompositeDbtbSupport(jbvbx.mbnbgement.openmbebn.CompositeType, jbvb.lbng.String[], jbvb.lbng.Object[])</tt>.
     *
     * @pbrbm  compositeType  the <i>composite type </i> of this <i>composite dbtb</i> instbnce;
     *                        must not be null.
     * @pbrbm  items  the mbppings of bll the item nbmes to their vblues;
     *                <tt>items</tt> must contbin bll the item nbmes defined in <tt>compositeType</tt>;
     *                must not be null or empty.
     *
     * @throws IllegblArgumentException <tt>compositeType</tt> is null, or
     * <tt>items</tt> is null or empty, or one of the keys in <tt>items</tt> is b null
     * or empty string.
     * @throws OpenDbtbException <tt>items</tt>' size differs from the
     * number of items defined in <tt>compositeType</tt>, or one of the
     * keys in <tt>items</tt> does not exist bs bn item nbme defined in
     * <tt>compositeType</tt>, or one of the vblues in <tt>items</tt>
     * is not b vblid vblue for the corresponding item bs defined in
     * <tt>compositeType</tt>.
     * @throws ArrbyStoreException one or more keys in <tt>items</tt> is not of
     * the clbss <tt>jbvb.lbng.String</tt>.
     */
    public CompositeDbtbSupport(CompositeType compositeType,
                                Mbp<String,?> items)
            throws OpenDbtbException {
        this(mbkeMbp(items), compositeType);
    }

    privbte stbtic SortedMbp<String, Object> mbkeMbp(Mbp<String, ?> items) {
        if (items == null || items.isEmpty())
            throw new IllegblArgumentException("Null or empty items mbp");

        SortedMbp<String, Object> mbp = new TreeMbp<String, Object>();
        for (Object key : items.keySet()) {
            if (key == null || key.equbls(""))
                throw new IllegblArgumentException("Null or empty item nbme");
            if (!(key instbnceof String)) {
                throw new ArrbyStoreException("Item nbme is not string: " + key);
                // This cbn hbppen becbuse of erbsure.  The pbrticulbr
                // exception is b historicbl brtifbct - bn implementbtion
                // detbil thbt lebked into the API.
            }
            mbp.put((String) key, items.get(key));
        }
        return mbp;
    }

    privbte CompositeDbtbSupport(
            SortedMbp<String, Object> items, CompositeType compositeType)
            throws OpenDbtbException {

        // Check compositeType is not null
        //
        if (compositeType == null) {
            throw new IllegblArgumentException("Argument compositeType cbnnot be null.");
        }

        // item nbmes defined in compositeType:
        Set<String> nbmesFromType = compositeType.keySet();
        Set<String> nbmesFromItems = items.keySet();

        // This is just b compbrison, but we do it this wby for b better
        // exception messbge.
        if (!nbmesFromType.equbls(nbmesFromItems)) {
            Set<String> extrbFromType = new TreeSet<String>(nbmesFromType);
            extrbFromType.removeAll(nbmesFromItems);
            Set<String> extrbFromItems = new TreeSet<String>(nbmesFromItems);
            extrbFromItems.removeAll(nbmesFromType);
            if (!extrbFromType.isEmpty() || !extrbFromItems.isEmpty()) {
                throw new OpenDbtbException(
                        "Item nbmes do not mbtch CompositeType: " +
                        "nbmes in items but not in CompositeType: " + extrbFromItems +
                        "; nbmes in CompositeType but not in items: " + extrbFromType);
            }
        }

        // Check ebch vblue, if not null, is of the open type defined for the
        // corresponding item
        for (String nbme : nbmesFromType) {
            Object vblue = items.get(nbme);
            if (vblue != null) {
                OpenType<?> itemType = compositeType.getType(nbme);
                if (!itemType.isVblue(vblue)) {
                    throw new OpenDbtbException(
                            "Argument vblue of wrong type for item " + nbme +
                            ": vblue " + vblue + ", type " + itemType);
                }
            }
        }

        // Initiblize internbl fields: compositeType bnd contents
        //
        this.compositeType = compositeType;
        this.contents = items;
    }

    /**
     * Returns the <i>composite type </i> of this <i>composite dbtb</i> instbnce.
     */
    public CompositeType getCompositeType() {

        return compositeType;
    }

    /**
     * Returns the vblue of the item whose nbme is <tt>key</tt>.
     *
     * @throws IllegblArgumentException  if <tt>key</tt> is b null or empty String.
     *
     * @throws InvblidKeyException  if <tt>key</tt> is not bn existing item nbme for
     * this <tt>CompositeDbtb</tt> instbnce.
     */
    public Object get(String key) {

        if ( (key == null) || (key.trim().equbls("")) ) {
            throw new IllegblArgumentException("Argument key cbnnot be b null or empty String.");
        }
        if ( ! contents.contbinsKey(key.trim())) {
            throw new InvblidKeyException("Argument key=\""+ key.trim() +"\" is not bn existing item nbme for this CompositeDbtb instbnce.");
        }
        return contents.get(key.trim());
    }

    /**
     * Returns bn brrby of the vblues of the items whose nbmes bre specified by
     * <tt>keys</tt>, in the sbme order bs <tt>keys</tt>.
     *
     * @throws IllegblArgumentException  if bn element in <tt>keys</tt> is b null
     * or empty String.
     *
     * @throws InvblidKeyException  if bn element in <tt>keys</tt> is not bn existing
     * item nbme for this <tt>CompositeDbtb</tt> instbnce.
     */
    public Object[] getAll(String[] keys) {

        if ( (keys == null) || (keys.length == 0) ) {
            return new Object[0];
        }
        Object[] results = new Object[keys.length];
        for (int i=0; i<keys.length; i++) {
            results[i] = this.get(keys[i]);
        }
        return results;
    }

    /**
     * Returns <tt>true</tt> if bnd only if this <tt>CompositeDbtb</tt> instbnce contbins
     * bn item whose nbme is <tt>key</tt>.
     * If <tt>key</tt> is b null or empty String, this method simply returns fblse.
     */
    public boolebn contbinsKey(String key) {

        if ( (key == null) || (key.trim().equbls("")) ) {
            return fblse;
        }
        return contents.contbinsKey(key);
    }

    /**
     * Returns <tt>true</tt> if bnd only if this <tt>CompositeDbtb</tt> instbnce
     * contbins bn item
     * whose vblue is <tt>vblue</tt>.
     */
    public boolebn contbinsVblue(Object vblue) {

        return contents.contbinsVblue(vblue);
    }

    /**
     * Returns bn unmodifibble Collection view of the item vblues contbined in this
     * <tt>CompositeDbtb</tt> instbnce.
     * The returned collection's iterbtor will return the vblues in the bscending
     * lexicogrbphic order of the corresponding
     * item nbmes.
     */
    public Collection<?> vblues() {

        return Collections.unmodifibbleCollection(contents.vblues());
    }

    /**
     * Compbres the specified <vbr>obj</vbr> pbrbmeter with this
     * <code>CompositeDbtbSupport</code> instbnce for equblity.
     * <p>
     * Returns <tt>true</tt> if bnd only if bll of the following stbtements bre true:
     * <ul>
     * <li><vbr>obj</vbr> is non null,</li>
     * <li><vbr>obj</vbr> blso implements the <code>CompositeDbtb</code> interfbce,</li>
     * <li>their composite types bre equbl</li>
     * <li>their contents, i.e. (nbme, vblue) pbirs bre equbl. If b vblue contbined in
     * the content is bn brrby, the vblue compbrison is done bs if by cblling
     * the {@link jbvb.util.Arrbys#deepEqubls(Object[], Object[]) deepEqubls} method
     * for brrbys of object reference types or the bppropribte overlobding of
     * {@code Arrbys.equbls(e1,e2)} for brrbys of primitive types</li>
     * </ul>
     * <p>
     * This ensures thbt this <tt>equbls</tt> method works properly for
     * <vbr>obj</vbr> pbrbmeters which bre different implementbtions of the
     * <code>CompositeDbtb</code> interfbce, with the restrictions mentioned in the
     * {@link jbvb.util.Collection#equbls(Object) equbls}
     * method of the <tt>jbvb.util.Collection</tt> interfbce.
     *
     * @pbrbm  obj  the object to be compbred for equblity with this
     * <code>CompositeDbtbSupport</code> instbnce.
     * @return  <code>true</code> if the specified object is equbl to this
     * <code>CompositeDbtbSupport</code> instbnce.
     */
    @Override
    public boolebn equbls(Object obj) {
        if (this == obj) {
            return true;
        }

        // if obj is not b CompositeDbtb, return fblse
        if (!(obj instbnceof CompositeDbtb)) {
            return fblse;
        }

        CompositeDbtb other = (CompositeDbtb) obj;

        // their compositeType should be equbl
        if (!this.getCompositeType().equbls(other.getCompositeType()) ) {
            return fblse;
        }

        if (contents.size() != other.vblues().size()) {
            return fblse;
        }

        for (Mbp.Entry<String,Object> entry : contents.entrySet()) {
            Object e1 = entry.getVblue();
            Object e2 = other.get(entry.getKey());

            if (e1 == e2)
                continue;
            if (e1 == null)
                return fblse;

            boolebn eq = e1.getClbss().isArrby() ?
                Arrbys.deepEqubls(new Object[] {e1}, new Object[] {e2}) :
                e1.equbls(e2);

            if (!eq)
                return fblse;
        }

        // All tests for equblity were successful
        //
        return true;
    }

    /**
     * Returns the hbsh code vblue for this <code>CompositeDbtbSupport</code> instbnce.
     * <p>
     * The hbsh code of b <code>CompositeDbtbSupport</code> instbnce is the sum of the hbsh codes
     * of bll elements of informbtion used in <code>equbls</code> compbrisons
     * (ie: its <i>composite type</i> bnd bll the item vblues).
     * <p>
     * This ensures thbt <code> t1.equbls(t2) </code> implies thbt <code> t1.hbshCode()==t2.hbshCode() </code>
     * for bny two <code>CompositeDbtbSupport</code> instbnces <code>t1</code> bnd <code>t2</code>,
     * bs required by the generbl contrbct of the method
     * {@link Object#hbshCode() Object.hbshCode()}.
     * <p>
     * Ebch item vblue's hbsh code is bdded to the returned hbsh code.
     * If bn item vblue is bn brrby,
     * its hbsh code is obtbined bs if by cblling the
     * {@link jbvb.util.Arrbys#deepHbshCode(Object[]) deepHbshCode} method
     * for brrbys of object reference types or the bppropribte overlobding
     * of {@code Arrbys.hbshCode(e)} for brrbys of primitive types.
     *
     * @return the hbsh code vblue for this <code>CompositeDbtbSupport</code> instbnce
     */
    @Override
    public int hbshCode() {
        int hbshcode = compositeType.hbshCode();

        for (Object o : contents.vblues()) {
            if (o instbnceof Object[])
                hbshcode += Arrbys.deepHbshCode((Object[]) o);
            else if (o instbnceof byte[])
                hbshcode += Arrbys.hbshCode((byte[]) o);
            else if (o instbnceof short[])
                hbshcode += Arrbys.hbshCode((short[]) o);
            else if (o instbnceof int[])
                hbshcode += Arrbys.hbshCode((int[]) o);
            else if (o instbnceof long[])
                hbshcode += Arrbys.hbshCode((long[]) o);
            else if (o instbnceof chbr[])
                hbshcode += Arrbys.hbshCode((chbr[]) o);
            else if (o instbnceof flobt[])
                hbshcode += Arrbys.hbshCode((flobt[]) o);
            else if (o instbnceof double[])
                hbshcode += Arrbys.hbshCode((double[]) o);
            else if (o instbnceof boolebn[])
                hbshcode += Arrbys.hbshCode((boolebn[]) o);
            else if (o != null)
                hbshcode += o.hbshCode();
        }

        return hbshcode;
    }

    /**
     * Returns b string representbtion of this <code>CompositeDbtbSupport</code> instbnce.
     * <p>
     * The string representbtion consists of the nbme of this clbss (ie <code>jbvbx.mbnbgement.openmbebn.CompositeDbtbSupport</code>),
     * the string representbtion of the composite type of this instbnce, bnd the string representbtion of the contents
     * (ie list the itemNbme=itemVblue mbppings).
     *
     * @return  b string representbtion of this <code>CompositeDbtbSupport</code> instbnce
     */
    @Override
    public String toString() {
        return new StringBuilder()
            .bppend(this.getClbss().getNbme())
            .bppend("(compositeType=")
            .bppend(compositeType.toString())
            .bppend(",contents=")
            .bppend(contentString())
            .bppend(")")
            .toString();
    }

    privbte String contentString() {
        StringBuilder sb = new StringBuilder("{");
        String sep = "";
        for (Mbp.Entry<String, Object> entry : contents.entrySet()) {
            sb.bppend(sep).bppend(entry.getKey()).bppend("=");
            String s = Arrbys.deepToString(new Object[] {entry.getVblue()});
            sb.bppend(s.substring(1, s.length() - 1));
            sep = ", ";
        }
        sb.bppend("}");
        return sb.toString();
    }
}
