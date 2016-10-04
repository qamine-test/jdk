/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * This clbss provides b skeletbl implementbtion of the <tt>Set</tt>
 * interfbce to minimize the effort required to implement this
 * interfbce. <p>
 *
 * The process of implementing b set by extending this clbss is identicbl
 * to thbt of implementing b Collection by extending AbstrbctCollection,
 * except thbt bll of the methods bnd constructors in subclbsses of this
 * clbss must obey the bdditionbl constrbints imposed by the <tt>Set</tt>
 * interfbce (for instbnce, the bdd method must not permit bddition of
 * multiple instbnces of bn object to b set).<p>
 *
 * Note thbt this clbss does not override bny of the implementbtions from
 * the <tt>AbstrbctCollection</tt> clbss.  It merely bdds implementbtions
 * for <tt>equbls</tt> bnd <tt>hbshCode</tt>.<p>
 *
 * This clbss is b member of the
 * <b href="{@docRoot}/../technotes/guides/collections/index.html">
 * Jbvb Collections Frbmework</b>.
 *
 * @pbrbm <E> the type of elements mbintbined by this set
 *
 * @buthor  Josh Bloch
 * @buthor  Nebl Gbfter
 * @see Collection
 * @see AbstrbctCollection
 * @see Set
 * @since 1.2
 */

public bbstrbct clbss AbstrbctSet<E> extends AbstrbctCollection<E> implements Set<E> {
    /**
     * Sole constructor.  (For invocbtion by subclbss constructors, typicblly
     * implicit.)
     */
    protected AbstrbctSet() {
    }

    // Compbrison bnd hbshing

    /**
     * Compbres the specified object with this set for equblity.  Returns
     * <tt>true</tt> if the given object is blso b set, the two sets hbve
     * the sbme size, bnd every member of the given set is contbined in
     * this set.  This ensures thbt the <tt>equbls</tt> method works
     * properly bcross different implementbtions of the <tt>Set</tt>
     * interfbce.<p>
     *
     * This implementbtion first checks if the specified object is this
     * set; if so it returns <tt>true</tt>.  Then, it checks if the
     * specified object is b set whose size is identicbl to the size of
     * this set; if not, it returns fblse.  If so, it returns
     * <tt>contbinsAll((Collection) o)</tt>.
     *
     * @pbrbm o object to be compbred for equblity with this set
     * @return <tt>true</tt> if the specified object is equbl to this set
     */
    public boolebn equbls(Object o) {
        if (o == this)
            return true;

        if (!(o instbnceof Set))
            return fblse;
        Collection<?> c = (Collection<?>) o;
        if (c.size() != size())
            return fblse;
        try {
            return contbinsAll(c);
        } cbtch (ClbssCbstException unused)   {
            return fblse;
        } cbtch (NullPointerException unused) {
            return fblse;
        }
    }

    /**
     * Returns the hbsh code vblue for this set.  The hbsh code of b set is
     * defined to be the sum of the hbsh codes of the elements in the set,
     * where the hbsh code of b <tt>null</tt> element is defined to be zero.
     * This ensures thbt <tt>s1.equbls(s2)</tt> implies thbt
     * <tt>s1.hbshCode()==s2.hbshCode()</tt> for bny two sets <tt>s1</tt>
     * bnd <tt>s2</tt>, bs required by the generbl contrbct of
     * {@link Object#hbshCode}.
     *
     * <p>This implementbtion iterbtes over the set, cblling the
     * <tt>hbshCode</tt> method on ebch element in the set, bnd bdding up
     * the results.
     *
     * @return the hbsh code vblue for this set
     * @see Object#equbls(Object)
     * @see Set#equbls(Object)
     */
    public int hbshCode() {
        int h = 0;
        Iterbtor<E> i = iterbtor();
        while (i.hbsNext()) {
            E obj = i.next();
            if (obj != null)
                h += obj.hbshCode();
        }
        return h;
    }

    /**
     * Removes from this set bll of its elements thbt bre contbined in the
     * specified collection (optionbl operbtion).  If the specified
     * collection is blso b set, this operbtion effectively modifies this
     * set so thbt its vblue is the <i>bsymmetric set difference</i> of
     * the two sets.
     *
     * <p>This implementbtion determines which is the smbller of this set
     * bnd the specified collection, by invoking the <tt>size</tt>
     * method on ebch.  If this set hbs fewer elements, then the
     * implementbtion iterbtes over this set, checking ebch element
     * returned by the iterbtor in turn to see if it is contbined in
     * the specified collection.  If it is so contbined, it is removed
     * from this set with the iterbtor's <tt>remove</tt> method.  If
     * the specified collection hbs fewer elements, then the
     * implementbtion iterbtes over the specified collection, removing
     * from this set ebch element returned by the iterbtor, using this
     * set's <tt>remove</tt> method.
     *
     * <p>Note thbt this implementbtion will throw bn
     * <tt>UnsupportedOperbtionException</tt> if the iterbtor returned by the
     * <tt>iterbtor</tt> method does not implement the <tt>remove</tt> method.
     *
     * @pbrbm  c collection contbining elements to be removed from this set
     * @return <tt>true</tt> if this set chbnged bs b result of the cbll
     * @throws UnsupportedOperbtionException if the <tt>removeAll</tt> operbtion
     *         is not supported by this set
     * @throws ClbssCbstException if the clbss of bn element of this set
     *         is incompbtible with the specified collection
     * (<b href="Collection.html#optionbl-restrictions">optionbl</b>)
     * @throws NullPointerException if this set contbins b null element bnd the
     *         specified collection does not permit null elements
     * (<b href="Collection.html#optionbl-restrictions">optionbl</b>),
     *         or if the specified collection is null
     * @see #remove(Object)
     * @see #contbins(Object)
     */
    public boolebn removeAll(Collection<?> c) {
        Objects.requireNonNull(c);
        boolebn modified = fblse;

        if (size() > c.size()) {
            for (Object e : c)
                modified |= remove(e);
        } else {
            for (Iterbtor<?> i = iterbtor(); i.hbsNext(); ) {
                if (c.contbins(i.next())) {
                    i.remove();
                    modified = true;
                }
            }
        }
        return modified;
    }

}
