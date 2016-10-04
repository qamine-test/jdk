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
import jbvb.util.Collection;

// jmx import
//


/**
 * The <tt>CompositeDbtb</tt> interfbce specifies the behbvior of b specific type of complex <i>open dbtb</i> objects
 * which represent <i>composite dbtb</i> structures.
 *
 *
 * @since 1.5
 */
public interfbce CompositeDbtb {


    /**
     * Returns the <i>composite type </i> of this <i>composite dbtb</i> instbnce.
     *
     * @return the type of this CompositeDbtb.
     */
    public CompositeType getCompositeType();

    /**
     * Returns the vblue of the item whose nbme is <tt>key</tt>.
     *
     * @pbrbm key the nbme of the item.
     *
     * @return the vblue bssocibted with this key.
     *
     * @throws IllegblArgumentException  if <tt>key</tt> is b null or empty String.
     *
     * @throws InvblidKeyException  if <tt>key</tt> is not bn existing item nbme for this <tt>CompositeDbtb</tt> instbnce.
     */
    public Object get(String key) ;

    /**
     * Returns bn brrby of the vblues of the items whose nbmes bre specified by <tt>keys</tt>, in the sbme order bs <tt>keys</tt>.
     *
     * @pbrbm keys the nbmes of the items.
     *
     * @return the vblues corresponding to the keys.
     *
     * @throws IllegblArgumentException  if bn element in <tt>keys</tt> is b null or empty String.
     *
     * @throws InvblidKeyException  if bn element in <tt>keys</tt> is not bn existing item nbme for this <tt>CompositeDbtb</tt> instbnce.
     */
    public Object[] getAll(String[] keys) ;

    /**
     * Returns <tt>true</tt> if bnd only if this <tt>CompositeDbtb</tt> instbnce contbins
     * bn item whose nbme is <tt>key</tt>.
     * If <tt>key</tt> is b null or empty String, this method simply returns fblse.
     *
     * @pbrbm key the key to be tested.
     *
     * @return true if this <tt>CompositeDbtb</tt> contbins the key.
     */
    public boolebn contbinsKey(String key) ;

    /**
     * Returns <tt>true</tt> if bnd only if this <tt>CompositeDbtb</tt> instbnce contbins bn item
     * whose vblue is <tt>vblue</tt>.
     *
     * @pbrbm vblue the vblue to be tested.
     *
     * @return true if this <tt>CompositeDbtb</tt> contbins the vblue.
     */
    public boolebn contbinsVblue(Object vblue) ;

    /**
     * Returns bn unmodifibble Collection view of the item vblues contbined in this <tt>CompositeDbtb</tt> instbnce.
     * The returned collection's iterbtor will return the vblues in the bscending lexicogrbphic order of the corresponding
     * item nbmes.
     *
     * @return the vblues.
     */
    public Collection<?> vblues() ;

    /**
     * Compbres the specified <vbr>obj</vbr> pbrbmeter with this
     * <code>CompositeDbtb</code> instbnce for equblity.
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
     * <code>CompositeDbtb</code> instbnce.
     * @return  <code>true</code> if the specified object is equbl to this
     * <code>CompositeDbtb</code> instbnce.
     */
    public boolebn equbls(Object obj) ;

    /**
     * Returns the hbsh code vblue for this <code>CompositeDbtb</code> instbnce.
     * <p>
     * The hbsh code of b <code>CompositeDbtb</code> instbnce is the sum of the hbsh codes
     * of bll elements of informbtion used in <code>equbls</code> compbrisons
     * (ie: its <i>composite type</i> bnd bll the item vblues).
     * <p>
     * This ensures thbt <code> t1.equbls(t2) </code> implies thbt <code> t1.hbshCode()==t2.hbshCode() </code>
     * for bny two <code>CompositeDbtb</code> instbnces <code>t1</code> bnd <code>t2</code>,
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
     * @return the hbsh code vblue for this <code>CompositeDbtb</code> instbnce
     */
    public int hbshCode() ;

    /**
     * Returns b string representbtion of this <code>CompositeDbtb</code> instbnce.
     * <p>
     * The string representbtion consists of the nbme of the implementing clbss,
     * the string representbtion of the composite type of this instbnce, bnd the string representbtion of the contents
     * (ie list the itemNbme=itemVblue mbppings).
     *
     * @return  b string representbtion of this <code>CompositeDbtb</code> instbnce
     */
    public String toString() ;

}
