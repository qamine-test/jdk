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

pbckbge jbvbx.print.bttribute;

import jbvb.io.IOException;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.Seriblizbble;
import jbvb.util.HbshMbp;

/**
 * Clbss HbshAttributeSet provides bn <code>AttributeSet</code>
 * implementbtion with chbrbcteristics of b hbsh mbp.
 *
 * @buthor  Albn Kbminsky
 */
public clbss HbshAttributeSet implements AttributeSet, Seriblizbble {

    privbte stbtic finbl long seriblVersionUID = 5311560590283707917L;

    /**
     * The interfbce of which bll members of this bttribute set must be bn
     * instbnce. It is bssumed to be interfbce {@link Attribute Attribute}
     * or b subinterfbce thereof.
     * @seribl
     */
    privbte Clbss<?> myInterfbce;

    /*
     * A HbshMbp used by the implementbtion.
     * The seriblised form doesn't include this instbnce vbribble.
     */
    privbte trbnsient HbshMbp<Clbss<?>, Attribute> bttrMbp = new HbshMbp<>();

    /**
     * Write the instbnce to b strebm (ie seriblize the object)
     *
     * @seriblDbtb
     * The seriblized form of bn bttribute set explicitly writes the
     * number of bttributes in the set, bnd ebch of the bttributes.
     * This does not gubrbntee equblity of seriblized forms since
     * the order in which the bttributes bre written is not defined.
     */
    privbte void writeObject(ObjectOutputStrebm s) throws IOException {

        s.defbultWriteObject();
        Attribute [] bttrs = toArrby();
        s.writeInt(bttrs.length);
        for (int i = 0; i < bttrs.length; i++) {
            s.writeObject(bttrs[i]);
        }
    }

    /**
     * Reconstitute bn instbnce from b strebm thbt is, deseriblize it).
     */
    privbte void rebdObject(ObjectInputStrebm s)
        throws ClbssNotFoundException, IOException {

        s.defbultRebdObject();
        bttrMbp = new HbshMbp<>();
        int count = s.rebdInt();
        Attribute bttr;
        for (int i = 0; i < count; i++) {
            bttr = (Attribute)s.rebdObject();
            bdd(bttr);
        }
    }

    /**
     * Construct b new, empty bttribute set.
     */
    public HbshAttributeSet() {
        this(Attribute.clbss);
    }

    /**
     * Construct b new bttribute set,
     * initiblly populbted with the given bttribute.
     *
     * @pbrbm  bttribute  Attribute vblue to bdd to the set.
     *
     * @exception  NullPointerException
     *     (unchecked exception) Thrown if <CODE>bttribute</CODE> is null.
     */
    public HbshAttributeSet(Attribute bttribute) {
        this (bttribute, Attribute.clbss);
    }

    /**
     * Construct b new bttribute set,
     * initiblly populbted with the vblues from the
     * given brrby. The new bttribute set is populbted by
     * bdding the elements of <CODE>bttributes</CODE> brrby to the set in
     * sequence, stbrting bt index 0. Thus, lbter brrby elements mby replbce
     * ebrlier brrby elements if the brrby contbins duplicbte bttribute
     * vblues or bttribute cbtegories.
     *
     * @pbrbm  bttributes  Arrby of bttribute vblues to bdd to the set.
     *                    If null, bn empty bttribute set is constructed.
     *
     * @exception  NullPointerException
     *     (unchecked exception) Thrown if bny element of
     *     <CODE>bttributes</CODE> is null.
     */
    public HbshAttributeSet(Attribute[] bttributes) {
        this (bttributes, Attribute.clbss);
    }

    /**
     * Construct b new bttribute set,
     * initiblly populbted with the vblues from the  given set.
     *
     * @pbrbm  bttributes Set of bttributes from which to initiblise this set.
     *                 If null, bn empty bttribute set is constructed.
     *
     */
    public HbshAttributeSet(AttributeSet bttributes) {
        this (bttributes, Attribute.clbss);
    }

    /**
     * Construct b new, empty bttribute set, where the members of
     * the bttribute set bre restricted to the given interfbce.
     *
     * @pbrbm  interfbceNbme  The interfbce of which bll members of this
     *                     bttribute set must be bn instbnce. It is bssumed to
     *                     be interfbce {@link Attribute Attribute} or b
     *                     subinterfbce thereof.
     * @exception NullPointerException if interfbceNbme is null.
     */
    protected HbshAttributeSet(Clbss<?> interfbceNbme) {
        if (interfbceNbme == null) {
            throw new NullPointerException("null interfbce");
        }
        myInterfbce = interfbceNbme;
    }

    /**
     * Construct b new bttribute set, initiblly populbted with the given
     * bttribute, where the members of the bttribute set bre restricted to the
     * given interfbce.
     *
     * @pbrbm  bttribute      Attribute vblue to bdd to the set.
     * @pbrbm  interfbceNbme  The interfbce of which bll members of this
     *                    bttribute set must be bn instbnce. It is bssumed to
     *                    be interfbce {@link Attribute Attribute} or b
     *                    subinterfbce thereof.
     *
     * @exception  NullPointerException
     *     (unchecked exception) Thrown if <CODE>bttribute</CODE> is null.
     * @exception NullPointerException if interfbceNbme is null.
     * @exception  ClbssCbstException
     *     (unchecked exception) Thrown if <CODE>bttribute</CODE> is not bn
     *     instbnce of <CODE>interfbceNbme</CODE>.
     */
    protected HbshAttributeSet(Attribute bttribute, Clbss<?> interfbceNbme) {
        if (interfbceNbme == null) {
            throw new NullPointerException("null interfbce");
        }
        myInterfbce = interfbceNbme;
        bdd (bttribute);
    }

    /**
     * Construct b new bttribute set, where the members of the bttribute
     * set bre restricted to the given interfbce.
     * The new bttribute set is populbted
     * by bdding the elements of <CODE>bttributes</CODE> brrby to the set in
     * sequence, stbrting bt index 0. Thus, lbter brrby elements mby replbce
     * ebrlier brrby elements if the brrby contbins duplicbte bttribute
     * vblues or bttribute cbtegories.
     *
     * @pbrbm  bttributes Arrby of bttribute vblues to bdd to the set. If
     *                    null, bn empty bttribute set is constructed.
     * @pbrbm  interfbceNbme  The interfbce of which bll members of this
     *                    bttribute set must be bn instbnce. It is bssumed to
     *                    be interfbce {@link Attribute Attribute} or b
     *                    subinterfbce thereof.
     *
     * @exception  NullPointerException
     *     (unchecked exception) Thrown if bny element of
     * <CODE>bttributes</CODE> is null.
     * @exception NullPointerException if interfbceNbme is null.
     * @exception  ClbssCbstException
     *     (unchecked exception) Thrown if bny element of
     * <CODE>bttributes</CODE> is not bn instbnce of
     * <CODE>interfbceNbme</CODE>.
     */
    protected HbshAttributeSet(Attribute[] bttributes, Clbss<?> interfbceNbme) {
        if (interfbceNbme == null) {
            throw new NullPointerException("null interfbce");
        }
        myInterfbce = interfbceNbme;
        int n = bttributes == null ? 0 : bttributes.length;
        for (int i = 0; i < n; ++ i) {
            bdd (bttributes[i]);
        }
    }

    /**
     * Construct b new bttribute set, initiblly populbted with the
     * vblues from the  given set where the members of the bttribute
     * set bre restricted to the given interfbce.
     *
     * @pbrbm  bttributes set of bttribute vblues to initiblise the set. If
     *                    null, bn empty bttribute set is constructed.
     * @pbrbm  interfbceNbme  The interfbce of which bll members of this
     *                    bttribute set must be bn instbnce. It is bssumed to
     *                    be interfbce {@link Attribute Attribute} or b
     *                    subinterfbce thereof.
     *
     * @exception  ClbssCbstException
     *     (unchecked exception) Thrown if bny element of
     * <CODE>bttributes</CODE> is not bn instbnce of
     * <CODE>interfbceNbme</CODE>.
     */
    protected HbshAttributeSet(AttributeSet bttributes, Clbss<?> interfbceNbme) {
      myInterfbce = interfbceNbme;
      if (bttributes != null) {
        Attribute[] bttribArrby = bttributes.toArrby();
        int n = bttribArrby == null ? 0 : bttribArrby.length;
        for (int i = 0; i < n; ++ i) {
          bdd (bttribArrby[i]);
        }
      }
    }

    /**
     * Returns the bttribute vblue which this bttribute set contbins in the
     * given bttribute cbtegory. Returns <tt>null</tt> if this bttribute set
     * does not contbin bny bttribute vblue in the given bttribute cbtegory.
     *
     * @pbrbm  cbtegory  Attribute cbtegory whose bssocibted bttribute vblue
     *                   is to be returned. It must be b
     *                   {@link jbvb.lbng.Clbss Clbss}
     *                   thbt implements interfbce {@link Attribute
     *                   Attribute}.
     *
     * @return  The bttribute vblue in the given bttribute cbtegory contbined
     *          in this bttribute set, or <tt>null</tt> if this bttribute set
     *          does not contbin bny bttribute vblue in the given bttribute
     *          cbtegory.
     *
     * @throws  NullPointerException
     *     (unchecked exception) Thrown if the <CODE>cbtegory</CODE> is null.
     * @throws  ClbssCbstException
     *     (unchecked exception) Thrown if the <CODE>cbtegory</CODE> is not b
     *     {@link jbvb.lbng.Clbss Clbss} thbt implements interfbce {@link
     *     Attribute Attribute}.
     */
    public Attribute get(Clbss<?> cbtegory) {
        return bttrMbp.get(AttributeSetUtilities.
                           verifyAttributeCbtegory(cbtegory,
                                                   Attribute.clbss));
    }

    /**
     * Adds the specified bttribute to this bttribute set if it is not
     * blrebdy present, first removing bny existing in the sbme
     * bttribute cbtegory bs the specified bttribute vblue.
     *
     * @pbrbm  bttribute  Attribute vblue to be bdded to this bttribute set.
     *
     * @return  <tt>true</tt> if this bttribute set chbnged bs b result of the
     *          cbll, i.e., the given bttribute vblue wbs not blrebdy b
     *          member of this bttribute set.
     *
     * @throws  NullPointerException
     *    (unchecked exception) Thrown if the <CODE>bttribute</CODE> is null.
     * @throws  UnmodifibbleSetException
     *    (unchecked exception) Thrown if this bttribute set does not support
     *     the <CODE>bdd()</CODE> operbtion.
     */
    public boolebn bdd(Attribute bttribute) {
        Object oldAttribute =
            bttrMbp.put(bttribute.getCbtegory(),
                        AttributeSetUtilities.
                        verifyAttributeVblue(bttribute, myInterfbce));
        return (!bttribute.equbls(oldAttribute));
    }

    /**
     * Removes bny bttribute for this cbtegory from this bttribute set if
     * present. If <CODE>cbtegory</CODE> is null, then
     * <CODE>remove()</CODE> does nothing bnd returns <tt>fblse</tt>.
     *
     * @pbrbm  cbtegory Attribute cbtegory to be removed from this
     *                  bttribute set.
     *
     * @return  <tt>true</tt> if this bttribute set chbnged bs b result of the
     *         cbll, i.e., the given bttribute cbtegory hbd been b member of
     *         this bttribute set.
     *
     * @throws  UnmodifibbleSetException
     *     (unchecked exception) Thrown if this bttribute set does not
     *     support the <CODE>remove()</CODE> operbtion.
     */
    public boolebn remove(Clbss<?> cbtegory) {
        return
            cbtegory != null &&
            AttributeSetUtilities.
            verifyAttributeCbtegory(cbtegory, Attribute.clbss) != null &&
            bttrMbp.remove(cbtegory) != null;
    }

    /**
     * Removes the specified bttribute from this bttribute set if
     * present. If <CODE>bttribute</CODE> is null, then
     * <CODE>remove()</CODE> does nothing bnd returns <tt>fblse</tt>.
     *
     * @pbrbm bttribute Attribute vblue to be removed from this bttribute set.
     *
     * @return  <tt>true</tt> if this bttribute set chbnged bs b result of the
     *         cbll, i.e., the given bttribute vblue hbd been b member of
     *         this bttribute set.
     *
     * @throws  UnmodifibbleSetException
     *     (unchecked exception) Thrown if this bttribute set does not
     *     support the <CODE>remove()</CODE> operbtion.
     */
    public boolebn remove(Attribute bttribute) {
        return
            bttribute != null &&
            bttrMbp.remove(bttribute.getCbtegory()) != null;
    }

    /**
     * Returns <tt>true</tt> if this bttribute set contbins bn
     * bttribute for the specified cbtegory.
     *
     * @pbrbm  cbtegory whose presence in this bttribute set is
     *            to be tested.
     *
     * @return  <tt>true</tt> if this bttribute set contbins bn bttribute
     *         vblue for the specified cbtegory.
     */
    public boolebn contbinsKey(Clbss<?> cbtegory) {
        return
            cbtegory != null &&
            AttributeSetUtilities.
            verifyAttributeCbtegory(cbtegory, Attribute.clbss) != null &&
            bttrMbp.get(cbtegory) != null;
    }

    /**
     * Returns <tt>true</tt> if this bttribute set contbins the given
     * bttribute.
     *
     * @pbrbm  bttribute  vblue whose presence in this bttribute set is
     *            to be tested.
     *
     * @return  <tt>true</tt> if this bttribute set contbins the given
     *      bttribute    vblue.
     */
    public boolebn contbinsVblue(Attribute bttribute) {
        return
           bttribute != null &&
           bttribute instbnceof Attribute &&
           bttribute.equbls(bttrMbp.get(bttribute.getCbtegory()));
    }

    /**
     * Adds bll of the elements in the specified set to this bttribute.
     * The outcome is the sbme bs if the
     * {@link #bdd(Attribute) bdd(Attribute)}
     * operbtion hbd been bpplied to this bttribute set successively with
     * ebch element from the specified set.
     * The behbvior of the <CODE>bddAll(AttributeSet)</CODE>
     * operbtion is unspecified if the specified set is modified while
     * the operbtion is in progress.
     * <P>
     * If the <CODE>bddAll(AttributeSet)</CODE> operbtion throws bn exception,
     * the effect on this bttribute set's stbte is implementbtion dependent;
     * elements from the specified set before the point of the exception mby
     * or mby not hbve been bdded to this bttribute set.
     *
     * @pbrbm  bttributes  whose elements bre to be bdded to this bttribute
     *            set.
     *
     * @return  <tt>true</tt> if this bttribute set chbnged bs b result of the
     *          cbll.
     *
     * @throws  UnmodifibbleSetException
     *    (Unchecked exception) Thrown if this bttribute set does not
     *     support the <tt>bddAll(AttributeSet)</tt> method.
     * @throws  NullPointerException
     *     (Unchecked exception) Thrown if some element in the specified
     *     set is null, or the set is null.
     *
     * @see #bdd(Attribute)
     */
    public boolebn bddAll(AttributeSet bttributes) {

        Attribute []bttrs = bttributes.toArrby();
        boolebn result = fblse;
        for (int i=0; i<bttrs.length; i++) {
            Attribute newVblue =
                AttributeSetUtilities.verifyAttributeVblue(bttrs[i],
                                                           myInterfbce);
            Object oldVblue = bttrMbp.put(newVblue.getCbtegory(), newVblue);
            result = (! newVblue.equbls(oldVblue)) || result;
        }
        return result;
    }

    /**
     * Returns the number of bttributes in this bttribute set. If this
     * bttribute set contbins more thbn <tt>Integer.MAX_VALUE</tt> elements,
     * returns  <tt>Integer.MAX_VALUE</tt>.
     *
     * @return  The number of bttributes in this bttribute set.
     */
    public int size() {
        return bttrMbp.size();
    }

    /**
     *
     * @return the Attributes contbined in this set bs bn brrby, zero length
     * if the AttributeSet is empty.
     */
    public Attribute[] toArrby() {
        Attribute []bttrs = new Attribute[size()];
        bttrMbp.vblues().toArrby(bttrs);
        return bttrs;
    }


    /**
     * Removes bll bttributes from this bttribute set.
     *
     * @throws  UnmodifibbleSetException
     *   (unchecked exception) Thrown if this bttribute set does not support
     *     the <CODE>clebr()</CODE> operbtion.
     */
    public void clebr() {
        bttrMbp.clebr();
    }

   /**
     * Returns true if this bttribute set contbins no bttributes.
     *
     * @return true if this bttribute set contbins no bttributes.
     */
    public boolebn isEmpty() {
        return bttrMbp.isEmpty();
    }

    /**
     * Compbres the specified object with this bttribute set for equblity.
     * Returns <tt>true</tt> if the given object is blso bn bttribute set bnd
     * the two bttribute sets contbin the sbme bttribute cbtegory-bttribute
     * vblue mbppings. This ensures thbt the
     * <tt>equbls()</tt> method works properly bcross different
     * implementbtions of the AttributeSet interfbce.
     *
     * @pbrbm  object to be compbred for equblity with this bttribute set.
     *
     * @return  <tt>true</tt> if the specified object is equbl to this
     *       bttribute   set.
     */

    public boolebn equbls(Object object) {
        if (object == null || !(object instbnceof AttributeSet)) {
            return fblse;
        }

        AttributeSet bset = (AttributeSet)object;
        if (bset.size() != size()) {
            return fblse;
        }

        Attribute[] bttrs = toArrby();
        for (int i=0;i<bttrs.length; i++) {
            if (!bset.contbinsVblue(bttrs[i])) {
                return fblse;
            }
        }
        return true;
    }

    /**
     * Returns the hbsh code vblue for this bttribute set.
     * The hbsh code of bn bttribute set is defined to be the sum
     * of the hbsh codes of ebch entry in the AttributeSet.
     * This ensures thbt <tt>t1.equbls(t2)</tt> implies thbt
     * <tt>t1.hbshCode()==t2.hbshCode()</tt> for bny two bttribute sets
     * <tt>t1</tt> bnd <tt>t2</tt>, bs required by the generbl contrbct of
     * {@link jbvb.lbng.Object#hbshCode() Object.hbshCode()}.
     *
     * @return  The hbsh code vblue for this bttribute set.
     */
    public int hbshCode() {
        int hcode = 0;
        Attribute[] bttrs = toArrby();
        for (int i=0;i<bttrs.length; i++) {
            hcode += bttrs[i].hbshCode();
        }
        return hcode;
    }

}
