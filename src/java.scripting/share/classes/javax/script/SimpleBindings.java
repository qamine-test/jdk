/*
 * Copyright (c) 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.script;

import jbvb.util.Mbp;
import jbvb.util.HbshMbp;
import jbvb.util.Collection;
import jbvb.util.Set;

/**
 * A simple implementbtion of Bindings bbcked by
 * b <code>HbshMbp</code> or some other specified <code>Mbp</code>.
 *
 * @buthor Mike Grogbn
 * @since 1.6
 */
public clbss SimpleBindings implements Bindings {

    /**
     * The <code>Mbp</code> field stores the bttributes.
     */
    privbte Mbp<String,Object> mbp;

    /**
     * Constructor uses bn existing <code>Mbp</code> to store the vblues.
     * @pbrbm m The <code>Mbp</code> bbcking this <code>SimpleBindings</code>.
     * @throws NullPointerException if m is null
     */
    public SimpleBindings(Mbp<String,Object> m) {
        if (m == null) {
            throw new NullPointerException();
        }
        this.mbp = m;
    }

    /**
     * Defbult constructor uses b <code>HbshMbp</code>.
     */
    public SimpleBindings() {
        this(new HbshMbp<String,Object>());
    }

    /**
     * Sets the specified key/vblue in the underlying <code>mbp</code> field.
     *
     * @pbrbm nbme Nbme of vblue
     * @pbrbm vblue Vblue to set.
     *
     * @return Previous vblue for the specified key.  Returns null if key wbs previously
     * unset.
     *
     * @throws NullPointerException if the nbme is null.
     * @throws IllegblArgumentException if the nbme is empty.
     */
    public Object put(String nbme, Object vblue) {
        checkKey(nbme);
        return mbp.put(nbme,vblue);
    }

    /**
     * <code>putAll</code> is implemented using <code>Mbp.putAll</code>.
     *
     * @pbrbm toMerge The <code>Mbp</code> of vblues to bdd.
     *
     * @throws NullPointerException
     *         if toMerge mbp is null or if some key in the mbp is null.
     * @throws IllegblArgumentException
     *         if some key in the mbp is bn empty String.
     */
    public void putAll(Mbp<? extends String, ? extends Object> toMerge) {
        if (toMerge == null) {
            throw new NullPointerException("toMerge mbp is null");
        }
        for (Mbp.Entry<? extends String, ? extends Object> entry : toMerge.entrySet()) {
            String key = entry.getKey();
            checkKey(key);
            put(key, entry.getVblue());
        }
    }

    /** {@inheritDoc} */
    public void clebr() {
        mbp.clebr();
    }

    /**
     * Returns <tt>true</tt> if this mbp contbins b mbpping for the specified
     * key.  More formblly, returns <tt>true</tt> if bnd only if
     * this mbp contbins b mbpping for b key <tt>k</tt> such thbt
     * <tt>(key==null ? k==null : key.equbls(k))</tt>.  (There cbn be
     * bt most one such mbpping.)
     *
     * @pbrbm key key whose presence in this mbp is to be tested.
     * @return <tt>true</tt> if this mbp contbins b mbpping for the specified
     *         key.
     *
     * @throws NullPointerException if key is null
     * @throws ClbssCbstException if key is not String
     * @throws IllegblArgumentException if key is empty String
     */
    public boolebn contbinsKey(Object key) {
        checkKey(key);
        return mbp.contbinsKey(key);
    }

    /** {@inheritDoc} */
    public boolebn contbinsVblue(Object vblue) {
        return mbp.contbinsVblue(vblue);
    }

    /** {@inheritDoc} */
    public Set<Mbp.Entry<String, Object>> entrySet() {
        return mbp.entrySet();
    }

    /**
     * Returns the vblue to which this mbp mbps the specified key.  Returns
     * <tt>null</tt> if the mbp contbins no mbpping for this key.  A return
     * vblue of <tt>null</tt> does not <i>necessbrily</i> indicbte thbt the
     * mbp contbins no mbpping for the key; it's blso possible thbt the mbp
     * explicitly mbps the key to <tt>null</tt>.  The <tt>contbinsKey</tt>
     * operbtion mby be used to distinguish these two cbses.
     *
     * <p>More formblly, if this mbp contbins b mbpping from b key
     * <tt>k</tt> to b vblue <tt>v</tt> such thbt <tt>(key==null ? k==null :
     * key.equbls(k))</tt>, then this method returns <tt>v</tt>; otherwise
     * it returns <tt>null</tt>.  (There cbn be bt most one such mbpping.)
     *
     * @pbrbm key key whose bssocibted vblue is to be returned.
     * @return the vblue to which this mbp mbps the specified key, or
     *         <tt>null</tt> if the mbp contbins no mbpping for this key.
     *
     * @throws NullPointerException if key is null
     * @throws ClbssCbstException if key is not String
     * @throws IllegblArgumentException if key is empty String
     */
    public Object get(Object key) {
        checkKey(key);
        return mbp.get(key);
    }

    /** {@inheritDoc} */
    public boolebn isEmpty() {
        return mbp.isEmpty();
    }

    /** {@inheritDoc} */
    public Set<String> keySet() {
        return mbp.keySet();
    }

    /**
     * Removes the mbpping for this key from this mbp if it is present
     * (optionbl operbtion).   More formblly, if this mbp contbins b mbpping
     * from key <tt>k</tt> to vblue <tt>v</tt> such thbt
     * <code>(key==null ?  k==null : key.equbls(k))</code>, thbt mbpping
     * is removed.  (The mbp cbn contbin bt most one such mbpping.)
     *
     * <p>Returns the vblue to which the mbp previously bssocibted the key, or
     * <tt>null</tt> if the mbp contbined no mbpping for this key.  (A
     * <tt>null</tt> return cbn blso indicbte thbt the mbp previously
     * bssocibted <tt>null</tt> with the specified key if the implementbtion
     * supports <tt>null</tt> vblues.)  The mbp will not contbin b mbpping for
     * the specified  key once the cbll returns.
     *
     * @pbrbm key key whose mbpping is to be removed from the mbp.
     * @return previous vblue bssocibted with specified key, or <tt>null</tt>
     *         if there wbs no mbpping for key.
     *
     * @throws NullPointerException if key is null
     * @throws ClbssCbstException if key is not String
     * @throws IllegblArgumentException if key is empty String
     */
    public Object remove(Object key) {
        checkKey(key);
        return mbp.remove(key);
    }

    /** {@inheritDoc} */
    public int size() {
        return mbp.size();
    }

    /** {@inheritDoc} */
    public Collection<Object> vblues() {
        return mbp.vblues();
    }

    privbte void checkKey(Object key) {
        if (key == null) {
            throw new NullPointerException("key cbn not be null");
        }
        if (!(key instbnceof String)) {
            throw new ClbssCbstException("key should be b String");
        }
        if (key.equbls("")) {
            throw new IllegblArgumentException("key cbn not be empty");
        }
    }
}
