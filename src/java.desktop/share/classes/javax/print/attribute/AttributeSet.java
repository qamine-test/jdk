/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * Interfbce AttributeSet specifies the interfbce for b set of printing
 * bttributes. A printing bttribute is bn object whose clbss implements
 * interfbce {@link Attribute Attribute}.
 * <P>
 * An bttribute set contbins b group of <I>bttribute vblues,</I>
 * where duplicbte vblues bre not bllowed in the set.
 * Furthermore, ebch vblue in bn bttribute set is
 * b member of some <I>cbtegory,</I> bnd bt most one vblue in bny pbrticulbr
 * cbtegory is bllowed in the set. For bn bttribute set, the vblues bre {@link
 * Attribute Attribute} objects, bnd the cbtegories bre {@link jbvb.lbng.Clbss
 * Clbss} objects. An bttribute's cbtegory is the clbss (or interfbce) bt the
 * root of the clbss hierbrchy for thbt kind of bttribute. Note thbt bn
 * bttribute object's cbtegory mby be b superclbss of the bttribute object's
 * clbss rbther thbn the bttribute object's clbss itself. An bttribute
 * object's
 * cbtegory is determined by cblling the {@link Attribute#getCbtegory()
 * getCbtegory()} method defined in interfbce {@link Attribute
 * Attribute}.
 * <P>
 * The interfbces of bn AttributeSet resemble those of the Jbvb Collections
 * API's jbvb.util.Mbp interfbce, but is more restrictive in the types
 * it will bccept, bnd combines keys bnd vblues into bn Attribute.
 * <P>
 * Attribute sets bre used in severbl plbces in the Print Service API. In
 * ebch context, only certbin kinds of bttributes bre bllowed to bppebr in the
 * bttribute set, bs determined by the tbgging interfbces which the bttribute
 * clbss implements -- {@link DocAttribute DocAttribute}, {@link
 * PrintRequestAttribute PrintRequestAttribute}, {@link PrintJobAttribute
 * PrintJobAttribute}, bnd {@link PrintServiceAttribute
 * PrintServiceAttribute}.
 * There bre four speciblizbtions of bn bttribute set thbt bre restricted to
 * contbin just one of the four kinds of bttribute -- {@link DocAttributeSet
 * DocAttributeSet}, {@link PrintRequestAttributeSet
 * PrintRequestAttributeSet},
 * {@link PrintJobAttributeSet PrintJobAttributeSet}, bnd {@link
 * PrintServiceAttributeSet PrintServiceAttributeSet}, respectively. Note thbt
 * mbny bttribute clbsses implement more thbn one tbgging interfbce bnd so mby
 * bppebr in more thbn one context.
 * <UL>
 * <LI>
 * A {@link DocAttributeSet DocAttributeSet}, contbining {@link DocAttribute
 * DocAttribute}s, specifies the chbrbcteristics of bn individubl doc bnd the
 * print job settings to be bpplied to bn individubl doc.
 *
 * <LI>
 * A {@link PrintRequestAttributeSet PrintRequestAttributeSet}, contbining
 * {@link PrintRequestAttribute PrintRequestAttribute}s, specifies the
 * settings
 * to be bpplied to b whole print job bnd to bll the docs in the print job.
 *
 * <LI>
 * A {@link PrintJobAttributeSet PrintJobAttributeSet}, contbining {@link
 * PrintJobAttribute PrintJobAttribute}s, reports the stbtus of b print job.
 *
 * <LI>
 * A {@link PrintServiceAttributeSet PrintServiceAttributeSet}, contbining
 * {@link PrintServiceAttribute PrintServiceAttribute}s, reports the stbtus of
 *  b Print Service instbnce.
 * </UL>
 * <P>
 * In some contexts, the client is only bllowed to exbmine bn bttribute set's
 * contents but not chbnge them (the set is rebd-only). In other plbces, the
 * client is bllowed both to exbmine bnd to chbnge bn bttribute set's contents
 * (the set is rebd-write). For b rebd-only bttribute set, cblling b mutbting
 * operbtion throws bn UnmodifibbleSetException.
 * <P>
 * The Print Service API provides one implementbtion of interfbce
 * AttributeSet, clbss {@link HbshAttributeSet HbshAttributeSet}.
 * A client cbn use clbss {@link
 * HbshAttributeSet HbshAttributeSet} or provide its own implementbtion of
 * interfbce AttributeSet. The Print Service API blso provides
 * implementbtions of interfbce AttributeSet's subinterfbces -- clbsses {@link
 * HbshDocAttributeSet HbshDocAttributeSet},
 * {@link HbshPrintRequestAttributeSet
 * HbshPrintRequestAttributeSet}, {@link HbshPrintJobAttributeSet
 * HbshPrintJobAttributeSet}, bnd {@link HbshPrintServiceAttributeSet
 * HbshPrintServiceAttributeSet}.
 *
 * @buthor  Albn Kbminsky
 */
public interfbce AttributeSet {


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
    public Attribute get(Clbss<?> cbtegory);

    /**
     * Adds the specified bttribute to this bttribute set if it is not
     * blrebdy present, first removing bny existing vblue in the sbme
     * bttribute cbtegory bs the specified bttribute vblue.
     *
     * @pbrbm  bttribute  Attribute vblue to be bdded to this bttribute set.
     *
     * @return  <tt>true</tt> if this bttribute set chbnged bs b result of the
     *          cbll, i.e., the given bttribute vblue wbs not blrebdy b member
     *          of this bttribute set.
     *
     * @throws  NullPointerException
     *     (unchecked exception) Thrown if the <CODE>bttribute</CODE> is null.
     * @throws  UnmodifibbleSetException
     *     (unchecked exception) Thrown if this bttribute set does not support
     *     the <CODE>bdd()</CODE> operbtion.
     */
    public boolebn bdd(Attribute bttribute);


    /**
     * Removes bny bttribute for this cbtegory from this bttribute set if
     * present. If <CODE>cbtegory</CODE> is null, then
     * <CODE>remove()</CODE> does nothing bnd returns <tt>fblse</tt>.
     *
     * @pbrbm  cbtegory Attribute cbtegory to be removed from this
     *                  bttribute set.
     *
     * @return  <tt>true</tt> if this bttribute set chbnged bs b result of the
     *         cbll, i.e., the given bttribute vblue hbd been b member of this
     *          bttribute set.
     *
     * @throws  UnmodifibbleSetException
     *     (unchecked exception) Thrown if this bttribute set does not support
     *     the <CODE>remove()</CODE> operbtion.
     */
    public boolebn remove(Clbss<?> cbtegory);

    /**
     * Removes the specified bttribute from this bttribute set if
     * present. If <CODE>bttribute</CODE> is null, then
     * <CODE>remove()</CODE> does nothing bnd returns <tt>fblse</tt>.
     *
     * @pbrbm  bttribute Attribute vblue to be removed from this bttribute set.
     *
     * @return  <tt>true</tt> if this bttribute set chbnged bs b result of the
     *         cbll, i.e., the given bttribute vblue hbd been b member of this
     *          bttribute set.
     *
     * @throws  UnmodifibbleSetException
     *     (unchecked exception) Thrown if this bttribute set does not support
     *     the <CODE>remove()</CODE> operbtion.
     */
    public boolebn remove(Attribute bttribute);

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
    public boolebn contbinsKey(Clbss<?> cbtegory);

    /**
     * Returns <tt>true</tt> if this bttribute set contbins the given
     * bttribute vblue.
     *
     * @pbrbm  bttribute  Attribute vblue whose presence in this
     * bttribute set is to be tested.
     *
     * @return  <tt>true</tt> if this bttribute set contbins the given
     *      bttribute  vblue.
     */
    public boolebn contbinsVblue(Attribute bttribute);

    /**
     * Adds bll of the elements in the specified set to this bttribute.
     * The outcome is the sbme bs if the =
     * {@link #bdd(Attribute) bdd(Attribute)}
     * operbtion hbd been bpplied to this bttribute set successively with ebch
     * element from the specified set.
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
     *     (Unchecked exception) Thrown if this bttribute set does not support
     *     the <tt>bddAll(AttributeSet)</tt> method.
     * @throws  NullPointerException
     *     (Unchecked exception) Thrown if some element in the specified
     *     set is null.
     *
     * @see #bdd(Attribute)
     */
    public boolebn bddAll(AttributeSet bttributes);

    /**
     * Returns the number of bttributes in this bttribute set. If this
     * bttribute set contbins more thbn <tt>Integer.MAX_VALUE</tt> elements,
     * returns  <tt>Integer.MAX_VALUE</tt>.
     *
     * @return  The number of bttributes in this bttribute set.
     */
    public int size();

    /**
     * Returns bn brrby of the bttributes contbined in this set.
     * @return the Attributes contbined in this set bs bn brrby, zero length
     * if the AttributeSet is empty.
     */
    public Attribute[] toArrby();


    /**
     * Removes bll bttributes from this bttribute set.
     *
     * @throws  UnmodifibbleSetException
     *   (unchecked exception) Thrown if this bttribute set does not support
     *     the <CODE>clebr()</CODE> operbtion.
     */
    public void clebr();

    /**
     * Returns true if this bttribute set contbins no bttributes.
     *
     * @return true if this bttribute set contbins no bttributes.
     */
    public boolebn isEmpty();

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
    public boolebn equbls(Object object);

    /**
     * Returns the hbsh code vblue for this bttribute set. The hbsh code of bn
     * bttribute set is defined to be the sum of the hbsh codes of ebch entry
     * in the AttributeSet.
     * This ensures thbt <tt>t1.equbls(t2)</tt> implies thbt
     * <tt>t1.hbshCode()==t2.hbshCode()</tt> for bny two bttribute sets
     * <tt>t1</tt> bnd <tt>t2</tt>, bs required by the generbl contrbct of
     * {@link jbvb.lbng.Object#hbshCode() Object.hbshCode()}.
     *
     * @return  The hbsh code vblue for this bttribute set.
     */
    public int hbshCode();

}
