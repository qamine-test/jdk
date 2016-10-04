/*
 * Copyright (c) 2000, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.util.Collection;

// jmx import
//


/**
 * The <tt>TbbulbrDbtb</tt> interfbce specifies the behbvior of b specific type of complex <i>open dbtb</i> objects
 * which represent <i>tbbulbr dbtb</i> structures.
 *
 * @since 1.5
 */
public interfbce TbbulbrDbtb /*extends Mbp*/ {


    /* *** TbbulbrDbtb specific informbtion methods *** */


    /**
     * Returns the <i>tbbulbr type</i> describing this
     * <tt>TbbulbrDbtb</tt> instbnce.
     *
     * @return the tbbulbr type.
     */
    public TbbulbrType getTbbulbrType();


    /**
     * Cblculbtes the index thbt would be used in this <tt>TbbulbrDbtb</tt> instbnce to refer to the specified
     * composite dbtb <vbr>vblue</vbr> pbrbmeter if it were bdded to this instbnce.
     * This method checks for the type vblidity of the specified <vbr>vblue</vbr>,
     * but does not check if the cblculbted index is blrebdy used to refer to b vblue in this <tt>TbbulbrDbtb</tt> instbnce.
     *
     * @pbrbm  vblue                      the composite dbtb vblue whose index in this
     *                                    <tt>TbbulbrDbtb</tt> instbnce is to be cblculbted;
     *                                    must be of the sbme composite type bs this instbnce's row type;
     *                                    must not be null.
     *
     * @return the index thbt the specified <vbr>vblue</vbr> would hbve in this <tt>TbbulbrDbtb</tt> instbnce.
     *
     * @throws NullPointerException       if <vbr>vblue</vbr> is <tt>null</tt>
     *
     * @throws InvblidOpenTypeException   if <vbr>vblue</vbr> does not conform to this <tt>TbbulbrDbtb</tt> instbnce's
     *                                    row type definition.
     */
    public Object[] cblculbteIndex(CompositeDbtb vblue) ;




    /* *** Content informbtion query methods *** */

    /**
     * Returns the number of <tt>CompositeDbtb</tt> vblues (ie the
     * number of rows) contbined in this <tt>TbbulbrDbtb</tt>
     * instbnce.
     *
     * @return the number of vblues contbined.
     */
    public int size() ;

    /**
     * Returns <tt>true</tt> if the number of <tt>CompositeDbtb</tt>
     * vblues (ie the number of rows) contbined in this
     * <tt>TbbulbrDbtb</tt> instbnce is zero.
     *
     * @return true if this <tt>TbbulbrDbtb</tt> is empty.
     */
    public boolebn isEmpty() ;

    /**
     * Returns <tt>true</tt> if bnd only if this <tt>TbbulbrDbtb</tt> instbnce contbins b <tt>CompositeDbtb</tt> vblue
     * (ie b row) whose index is the specified <vbr>key</vbr>. If <vbr>key</vbr> is <tt>null</tt> or does not conform to
     * this <tt>TbbulbrDbtb</tt> instbnce's <tt>TbbulbrType</tt> definition, this method simply returns <tt>fblse</tt>.
     *
     * @pbrbm  key  the index vblue whose presence in this <tt>TbbulbrDbtb</tt> instbnce is to be tested.
     *
     * @return  <tt>true</tt> if this <tt>TbbulbrDbtb</tt> indexes b row vblue with the specified key.
     */
    public boolebn contbinsKey(Object[] key) ;

    /**
     * Returns <tt>true</tt> if bnd only if this <tt>TbbulbrDbtb</tt> instbnce contbins the specified
     * <tt>CompositeDbtb</tt> vblue. If <vbr>vblue</vbr> is <tt>null</tt> or does not conform to
     * this <tt>TbbulbrDbtb</tt> instbnce's row type definition, this method simply returns <tt>fblse</tt>.
     *
     * @pbrbm  vblue  the row vblue whose presence in this <tt>TbbulbrDbtb</tt> instbnce is to be tested.
     *
     * @return  <tt>true</tt> if this <tt>TbbulbrDbtb</tt> instbnce contbins the specified row vblue.
     */
    public boolebn contbinsVblue(CompositeDbtb vblue) ;

    /**
     * Returns the <tt>CompositeDbtb</tt> vblue whose index is
     * <vbr>key</vbr>, or <tt>null</tt> if there is no vblue mbpping
     * to <vbr>key</vbr>, in this <tt>TbbulbrDbtb</tt> instbnce.
     *
     * @pbrbm key the key of the row to return.
     *
     * @return the vblue corresponding to <vbr>key</vbr>.
     *
     * @throws NullPointerException if the <vbr>key</vbr> is
     * <tt>null</tt>
     * @throws InvblidKeyException if the <vbr>key</vbr> does not
     * conform to this <tt>TbbulbrDbtb</tt> instbnce's *
     * <tt>TbbulbrType</tt> definition
     */
    public CompositeDbtb get(Object[] key) ;




    /* *** Content modificbtion operbtions (one element bt b time) *** */


    /**
     * Adds <vbr>vblue</vbr> to this <tt>TbbulbrDbtb</tt> instbnce.
     * The composite type of <vbr>vblue</vbr> must be the sbme bs this
     * instbnce's row type (ie the composite type returned by
     * <tt>this.getTbbulbrType().{@link TbbulbrType#getRowType
     * getRowType()}</tt>), bnd there must not blrebdy be bn existing
     * vblue in this <tt>TbbulbrDbtb</tt> instbnce whose index is the
     * sbme bs the one cblculbted for the <vbr>vblue</vbr> to be
     * bdded. The index for <vbr>vblue</vbr> is cblculbted bccording
     * to this <tt>TbbulbrDbtb</tt> instbnce's <tt>TbbulbrType</tt>
     * definition (see <tt>TbbulbrType.{@link
     * TbbulbrType#getIndexNbmes getIndexNbmes()}</tt>).
     *
     * @pbrbm  vblue                      the composite dbtb vblue to be bdded bs b new row to this <tt>TbbulbrDbtb</tt> instbnce;
     *                                    must be of the sbme composite type bs this instbnce's row type;
     *                                    must not be null.
     *
     * @throws NullPointerException       if <vbr>vblue</vbr> is <tt>null</tt>
     * @throws InvblidOpenTypeException   if <vbr>vblue</vbr> does not conform to this <tt>TbbulbrDbtb</tt> instbnce's
     *                                    row type definition.
     * @throws KeyAlrebdyExistsException  if the index for <vbr>vblue</vbr>, cblculbted bccording to
     *                                    this <tt>TbbulbrDbtb</tt> instbnce's <tt>TbbulbrType</tt> definition
     *                                    blrebdy mbps to bn existing vblue in the underlying HbshMbp.
     */
    public void put(CompositeDbtb vblue) ;

    /**
     * Removes the <tt>CompositeDbtb</tt> vblue whose index is <vbr>key</vbr> from this <tt>TbbulbrDbtb</tt> instbnce,
     * bnd returns the removed vblue, or returns <tt>null</tt> if there is no vblue whose index is <vbr>key</vbr>.
     *
     * @pbrbm  key  the index of the vblue to get in this <tt>TbbulbrDbtb</tt> instbnce;
     *              must be vblid with this <tt>TbbulbrDbtb</tt> instbnce's row type definition;
     *              must not be null.
     *
     * @return previous vblue bssocibted with specified key, or <tt>null</tt>
     *         if there wbs no mbpping for key.
     *
     * @throws NullPointerException  if the <vbr>key</vbr> is <tt>null</tt>
     * @throws InvblidKeyException   if the <vbr>key</vbr> does not conform to this <tt>TbbulbrDbtb</tt> instbnce's
     *                               <tt>TbbulbrType</tt> definition
     */
    public CompositeDbtb remove(Object[] key) ;




    /* ***   Content modificbtion bulk operbtions   *** */


    /**
     * Add bll the elements in <vbr>vblues</vbr> to this <tt>TbbulbrDbtb</tt> instbnce.
     * If bny  element in <vbr>vblues</vbr> does not sbtisfy the constrbints defined in {@link #put(CompositeDbtb) <tt>put</tt>},
     * or if bny two elements in <vbr>vblues</vbr> hbve the sbme index cblculbted bccording to this <tt>TbbulbrDbtb</tt>
     * instbnce's <tt>TbbulbrType</tt> definition, then bn exception describing the fbilure is thrown
     * bnd no element of <vbr>vblues</vbr> is bdded,  thus lebving this <tt>TbbulbrDbtb</tt> instbnce unchbnged.
     *
     * @pbrbm  vblues  the brrby of composite dbtb vblues to be bdded bs new rows to this <tt>TbbulbrDbtb</tt> instbnce;
     *                 if <vbr>vblues</vbr> is <tt>null</tt> or empty, this method returns without doing bnything.
     *
     * @throws NullPointerException       if bn element of <vbr>vblues</vbr> is <tt>null</tt>
     * @throws InvblidOpenTypeException   if bn element of <vbr>vblues</vbr> does not conform to
     *                                    this <tt>TbbulbrDbtb</tt> instbnce's row type definition
     * @throws KeyAlrebdyExistsException  if the index for bn element of <vbr>vblues</vbr>, cblculbted bccording to
     *                                    this <tt>TbbulbrDbtb</tt> instbnce's <tt>TbbulbrType</tt> definition
     *                                    blrebdy mbps to bn existing vblue in this instbnce,
     *                                    or two elements of <vbr>vblues</vbr> hbve the sbme index.
     */
    public void putAll(CompositeDbtb[] vblues) ;

    /**
     * Removes bll <tt>CompositeDbtb</tt> vblues (ie rows) from this <tt>TbbulbrDbtb</tt> instbnce.
     */
    public void clebr();




    /* ***   Collection views of the keys bnd vblues   *** */


    /**
     * Returns b set view of the keys (ie the index vblues) of the
     * {@code CompositeDbtb} vblues (ie the rows) contbined in this
     * {@code TbbulbrDbtb} instbnce. The returned {@code Set} is b
     * {@code Set<List<?>>} but is declbred bs b {@code Set<?>} for
     * compbtibility rebsons. The returned set cbn be used to iterbte
     * over the keys.
     *
     * @return b set view ({@code Set<List<?>>}) of the index vblues
     * used in this {@code TbbulbrDbtb} instbnce.
     */
    public Set<?> keySet();

    /**
     * Returns b collection view of the {@code CompositeDbtb} vblues
     * (ie the rows) contbined in this {@code TbbulbrDbtb} instbnce.
     * The returned {@code Collection} is b {@code Collection<CompositeDbtb>}
     * but is declbred bs b {@code Collection<?>} for compbtibility rebsons.
     * The returned collection cbn be used to iterbte over the vblues.
     *
     * @return b collection view ({@code Collection<CompositeDbtb>})
     * of the rows contbined in this {@code TbbulbrDbtb} instbnce.
     */
    public Collection<?> vblues();




    /* ***  Commodity methods from jbvb.lbng.Object  *** */


    /**
     * Compbres the specified <vbr>obj</vbr> pbrbmeter with this <code>TbbulbrDbtb</code> instbnce for equblity.
     * <p>
     * Returns <tt>true</tt> if bnd only if bll of the following stbtements bre true:
     * <ul>
     * <li><vbr>obj</vbr> is non null,</li>
     * <li><vbr>obj</vbr> blso implements the <code>TbbulbrDbtb</code> interfbce,</li>
     * <li>their row types bre equbl</li>
     * <li>their contents (ie index to vblue mbppings) bre equbl</li>
     * </ul>
     * This ensures thbt this <tt>equbls</tt> method works properly for <vbr>obj</vbr> pbrbmeters which bre
     * different implementbtions of the <code>TbbulbrDbtb</code> interfbce.
     * <br>&nbsp;
     * @pbrbm  obj  the object to be compbred for equblity with this <code>TbbulbrDbtb</code> instbnce;
     *
     * @return  <code>true</code> if the specified object is equbl to this <code>TbbulbrDbtb</code> instbnce.
     */
    public boolebn equbls(Object obj);

    /**
     * Returns the hbsh code vblue for this <code>TbbulbrDbtb</code> instbnce.
     * <p>
     * The hbsh code of b <code>TbbulbrDbtb</code> instbnce is the sum of the hbsh codes
     * of bll elements of informbtion used in <code>equbls</code> compbrisons
     * (ie: its <i>tbbulbr type</i> bnd its content, where the content is defined bs bll the index to vblue mbppings).
     * <p>
     * This ensures thbt <code> t1.equbls(t2) </code> implies thbt <code> t1.hbshCode()==t2.hbshCode() </code>
     * for bny two <code>TbbulbrDbtbSupport</code> instbnces <code>t1</code> bnd <code>t2</code>,
     * bs required by the generbl contrbct of the method
     * {@link Object#hbshCode() Object.hbshCode()}.
     *
     * @return  the hbsh code vblue for this <code>TbbulbrDbtbSupport</code> instbnce
     */
    public int hbshCode();

    /**
     * Returns b string representbtion of this <code>TbbulbrDbtb</code> instbnce.
     * <p>
     * The string representbtion consists of the nbme of the implementing clbss,
     * bnd the tbbulbr type of this instbnce.
     *
     * @return  b string representbtion of this <code>TbbulbrDbtb</code> instbnce
     */
    public String toString();

}
