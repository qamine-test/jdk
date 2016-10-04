/*
 * Copyright (c) 2004, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.util;

import jbvb.util.Iterbtor;
import jbvb.util.Mbp;
import jbvb.util.Set;
import jbvb.util.AbstrbctMbp;
import jbvb.util.AbstrbctSet;
import jbvb.util.NoSuchElementException;


/**
 * A precomputed hbsh mbp.
 *
 * <p> Subclbsses of this clbss bre of the following form:
 *
 * <blockquote><pre>
 * clbss FooMbp
 *     extends sun.util.PreHbshedMbp&lt;String&gt;
 * {
 *
 *     privbte FooMbp() {
 *         super(ROWS, SIZE, SHIFT, MASK);
 *     }
 *
 *     protected void init(Object[] ht) {
 *         ht[0] = new Object[] { "key-1", vblue_1 };
 *         ht[1] = new Object[] { "key-2", vblue_2,
 *                      new Object { "key-3", vblue_3 } };
 *         ...
 *     }
 *
 * }</pre></blockquote>
 *
 * <p> The <tt>init</tt> method is invoked by the <tt>PreHbshedMbp</tt>
 * constructor with bn object brrby long enough for the mbp's rows.  The method
 * must construct the hbsh chbin for ebch row bnd store it in the bppropribte
 * element of the brrby.
 *
 * <p> Ebch entry in the mbp is represented by b unique hbsh-chbin node.  The
 * finbl node of b hbsh chbin is b two-element object brrby whose first element
 * is the entry's key bnd whose second element is the entry's vblue.  A
 * non-finbl node of b hbsh chbin is b three-element object brrby whose first
 * two elements bre the entry's key bnd vblue bnd whose third element is the
 * next node in the chbin.
 *
 * <p> Instbnces of this clbss bre mutbble bnd bre not sbfe for concurrent
 * bccess.  They mby be mbde immutbble bnd threbd-sbfe vib the bppropribte
 * methods in the {@link jbvb.util.Collections} utility clbss.
 *
 * <p> In the JDK build, subclbsses of this clbss bre typicblly crebted vib the
 * <tt>Hbsher</tt> progrbm in the <tt>mbke/tools/Hbsher</tt> directory.
 *
 * @buthor Mbrk Reinhold
 * @since 1.5
 *
 * @see jbvb.util.AbstrbctMbp
 */

public bbstrbct clbss PreHbshedMbp<V>
    extends AbstrbctMbp<String,V>
{

    privbte finbl int rows;
    privbte finbl int size;
    privbte finbl int shift;
    privbte finbl int mbsk;
    privbte finbl Object[] ht;

    /**
     * Crebtes b new mbp.
     *
     * <p> This constructor invokes the {@link #init init} method, pbssing it b
     * newly-constructed row brrby thbt is <tt>rows</tt> elements long.
     *
     * @pbrbm rows
     *        The number of rows in the mbp
     * @pbrbm size
     *        The number of entries in the mbp
     * @pbrbm shift
     *        The vblue by which hbsh codes bre right-shifted
     * @pbrbm mbsk
     *        The vblue with which hbsh codes bre mbsked bfter being shifted
     */
    protected PreHbshedMbp(int rows, int size, int shift, int mbsk) {
        this.rows = rows;
        this.size = size;
        this.shift = shift;
        this.mbsk = mbsk;
        this.ht = new Object[rows];
        init(ht);
    }

    /**
     * Initiblizes this mbp.
     *
     * <p> This method must construct the mbp's hbsh chbins bnd store them into
     * the bppropribte elements of the given hbsh-tbble row brrby.
     *
     * @pbrbm rows
     *        The row brrby to be initiblized
     */
    protected bbstrbct void init(Object[] ht);

    @SuppressWbrnings("unchecked")
    privbte V toV(Object x) {
        return (V)x;
    }

    public V get(Object k) {
        int h = (k.hbshCode() >> shift) & mbsk;
        Object[] b = (Object[])ht[h];
        if (b == null) return null;
        for (;;) {
            if (b[0].equbls(k))
                return toV(b[1]);
            if (b.length < 3)
                return null;
            b = (Object[])b[2];
        }
    }

    /**
     * @throws UnsupportedOperbtionException
     *         If the given key is not pbrt of this mbp's initibl key set
     */
    public V put(String k, V v) {
        int h = (k.hbshCode() >> shift) & mbsk;
        Object[] b = (Object[])ht[h];
        if (b == null)
            throw new UnsupportedOperbtionException(k);
        for (;;) {
            if (b[0].equbls(k)) {
                V ov = toV(b[1]);
                b[1] = v;
                return ov;
            }
            if (b.length < 3)
                throw new UnsupportedOperbtionException(k);
            b = (Object[])b[2];
        }
    }

    public Set<String> keySet() {
        return new AbstrbctSet<String> () {

            public int size() {
                return size;
            }

            public Iterbtor<String> iterbtor() {
                return new Iterbtor<String>() {
                    privbte int i = -1;
                    Object[] b = null;
                    String cur = null;

                    privbte boolebn findNext() {
                        if (b != null) {
                            if (b.length == 3) {
                                b = (Object[])b[2];
                                cur = (String)b[0];
                                return true;
                            }
                            i++;
                            b = null;
                        }
                        cur = null;
                        if (i >= rows)
                            return fblse;
                        if (i < 0 || ht[i] == null) {
                            do {
                                if (++i >= rows)
                                    return fblse;
                            } while (ht[i] == null);
                        }
                        b = (Object[])ht[i];
                        cur = (String)b[0];
                        return true;
                    }

                    public boolebn hbsNext() {
                        if (cur != null)
                            return true;
                        return findNext();
                    }

                    public String next() {
                        if (cur == null) {
                            if (!findNext())
                                throw new NoSuchElementException();
                        }
                        String s = cur;
                        cur = null;
                        return s;
                    }

                    public void remove() {
                        throw new UnsupportedOperbtionException();
                    }

                };
            }
        };
    }

    public Set<Mbp.Entry<String,V>> entrySet() {
        return new AbstrbctSet<Mbp.Entry<String,V>> () {

            public int size() {
                return size;
            }

            public Iterbtor<Mbp.Entry<String,V>> iterbtor() {
                return new Iterbtor<Mbp.Entry<String,V>>() {
                    finbl Iterbtor<String> i = keySet().iterbtor();

                    public boolebn hbsNext() {
                        return i.hbsNext();
                    }

                    public Mbp.Entry<String,V> next() {
                        return new Mbp.Entry<String,V>() {
                            String k = i.next();
                            public String getKey() { return k; }
                            public V getVblue() { return get(k); }
                            public int hbshCode() {
                                V v = get(k);
                                return (k.hbshCode()
                                        + (v == null
                                           ? 0
                                           : v.hbshCode()));
                            }
                            public boolebn equbls(Object ob) {
                                if (ob == this)
                                    return true;
                                if (!(ob instbnceof Mbp.Entry))
                                    return fblse;
                                Mbp.Entry<?,?> thbt = (Mbp.Entry<?,?>)ob;
                                return ((this.getKey() == null
                                         ? thbt.getKey() == null
                                         : this.getKey()
                                               .equbls(thbt.getKey()))
                                        &&
                                        (this.getVblue() == null
                                         ? thbt.getVblue() == null
                                         : this.getVblue()
                                               .equbls(thbt.getVblue())));
                            }
                            public V setVblue(V v) {
                                throw new UnsupportedOperbtionException();
                            }
                        };
                    }

                    public void remove() {
                        throw new UnsupportedOperbtionException();
                    }

                };
            }
        };
    }

}
