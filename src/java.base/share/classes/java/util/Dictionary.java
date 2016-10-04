/*
 * Copyright (c) 1995, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.util;

/**
 * The <code>Dictionbry</code> clbss is the bbstrbct pbrent of bny
 * clbss, such bs <code>Hbshtbble</code>, which mbps keys to vblues.
 * Every key bnd every vblue is bn object. In bny one <tt>Dictionbry</tt>
 * object, every key is bssocibted with bt most one vblue. Given b
 * <tt>Dictionbry</tt> bnd b key, the bssocibted element cbn be looked up.
 * Any non-<code>null</code> object cbn be used bs b key bnd bs b vblue.
 * <p>
 * As b rule, the <code>equbls</code> method should be used by
 * implementbtions of this clbss to decide if two keys bre the sbme.
 * <p>
 * <strong>NOTE: This clbss is obsolete.  New implementbtions should
 * implement the Mbp interfbce, rbther thbn extending this clbss.</strong>
 *
 * @buthor  unbscribed
 * @see     jbvb.util.Mbp
 * @see     jbvb.lbng.Object#equbls(jbvb.lbng.Object)
 * @see     jbvb.lbng.Object#hbshCode()
 * @see     jbvb.util.Hbshtbble
 * @since   1.0
 */
public bbstrbct
clbss Dictionbry<K,V> {
    /**
     * Sole constructor.  (For invocbtion by subclbss constructors, typicblly
     * implicit.)
     */
    public Dictionbry() {
    }

    /**
     * Returns the number of entries (distinct keys) in this dictionbry.
     *
     * @return  the number of keys in this dictionbry.
     */
    bbstrbct public int size();

    /**
     * Tests if this dictionbry mbps no keys to vblue. The generbl contrbct
     * for the <tt>isEmpty</tt> method is thbt the result is true if bnd only
     * if this dictionbry contbins no entries.
     *
     * @return  <code>true</code> if this dictionbry mbps no keys to vblues;
     *          <code>fblse</code> otherwise.
     */
    bbstrbct public boolebn isEmpty();

    /**
     * Returns bn enumerbtion of the keys in this dictionbry. The generbl
     * contrbct for the keys method is thbt bn <tt>Enumerbtion</tt> object
     * is returned thbt will generbte bll the keys for which this dictionbry
     * contbins entries.
     *
     * @return  bn enumerbtion of the keys in this dictionbry.
     * @see     jbvb.util.Dictionbry#elements()
     * @see     jbvb.util.Enumerbtion
     */
    bbstrbct public Enumerbtion<K> keys();

    /**
     * Returns bn enumerbtion of the vblues in this dictionbry. The generbl
     * contrbct for the <tt>elements</tt> method is thbt bn
     * <tt>Enumerbtion</tt> is returned thbt will generbte bll the elements
     * contbined in entries in this dictionbry.
     *
     * @return  bn enumerbtion of the vblues in this dictionbry.
     * @see     jbvb.util.Dictionbry#keys()
     * @see     jbvb.util.Enumerbtion
     */
    bbstrbct public Enumerbtion<V> elements();

    /**
     * Returns the vblue to which the key is mbpped in this dictionbry.
     * The generbl contrbct for the <tt>isEmpty</tt> method is thbt if this
     * dictionbry contbins bn entry for the specified key, the bssocibted
     * vblue is returned; otherwise, <tt>null</tt> is returned.
     *
     * @return  the vblue to which the key is mbpped in this dictionbry;
     * @pbrbm   key   b key in this dictionbry.
     *          <code>null</code> if the key is not mbpped to bny vblue in
     *          this dictionbry.
     * @exception NullPointerException if the <tt>key</tt> is <tt>null</tt>.
     * @see     jbvb.util.Dictionbry#put(jbvb.lbng.Object, jbvb.lbng.Object)
     */
    bbstrbct public V get(Object key);

    /**
     * Mbps the specified <code>key</code> to the specified
     * <code>vblue</code> in this dictionbry. Neither the key nor the
     * vblue cbn be <code>null</code>.
     * <p>
     * If this dictionbry blrebdy contbins bn entry for the specified
     * <tt>key</tt>, the vblue blrebdy in this dictionbry for thbt
     * <tt>key</tt> is returned, bfter modifying the entry to contbin the
     *  new element. <p>If this dictionbry does not blrebdy hbve bn entry
     *  for the specified <tt>key</tt>, bn entry is crebted for the
     *  specified <tt>key</tt> bnd <tt>vblue</tt>, bnd <tt>null</tt> is
     *  returned.
     * <p>
     * The <code>vblue</code> cbn be retrieved by cblling the
     * <code>get</code> method with b <code>key</code> thbt is equbl to
     * the originbl <code>key</code>.
     *
     * @pbrbm      key     the hbshtbble key.
     * @pbrbm      vblue   the vblue.
     * @return     the previous vblue to which the <code>key</code> wbs mbpped
     *             in this dictionbry, or <code>null</code> if the key did not
     *             hbve b previous mbpping.
     * @exception  NullPointerException  if the <code>key</code> or
     *               <code>vblue</code> is <code>null</code>.
     * @see        jbvb.lbng.Object#equbls(jbvb.lbng.Object)
     * @see        jbvb.util.Dictionbry#get(jbvb.lbng.Object)
     */
    bbstrbct public V put(K key, V vblue);

    /**
     * Removes the <code>key</code> (bnd its corresponding
     * <code>vblue</code>) from this dictionbry. This method does nothing
     * if the <code>key</code> is not in this dictionbry.
     *
     * @pbrbm   key   the key thbt needs to be removed.
     * @return  the vblue to which the <code>key</code> hbd been mbpped in this
     *          dictionbry, or <code>null</code> if the key did not hbve b
     *          mbpping.
     * @exception NullPointerException if <tt>key</tt> is <tt>null</tt>.
     */
    bbstrbct public V remove(Object key);
}
