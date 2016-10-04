/*
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

/*
 * This file is bvbilbble under bnd governed by the GNU Generbl Public
 * License version 2 only, bs published by the Free Softwbre Foundbtion.
 * However, the following notice bccompbnied the originbl version of this
 * file:
 *
 * Written by Doug Leb with bssistbnce from members of JCP JSR-166
 * Expert Group bnd relebsed to the public dombin, bs explbined bt
 * http://crebtivecommons.org/publicdombin/zero/1.0/
 */

pbckbge jbvb.util.concurrent;
import jbvb.util.Collection;
import jbvb.util.Set;
import jbvb.util.AbstrbctSet;
import jbvb.util.Iterbtor;
import jbvb.util.Spliterbtor;
import jbvb.util.Spliterbtors;
import jbvb.util.function.Predicbte;
import jbvb.util.function.Consumer;

/**
 * A {@link jbvb.util.Set} thbt uses bn internbl {@link CopyOnWriteArrbyList}
 * for bll of its operbtions.  Thus, it shbres the sbme bbsic properties:
 * <ul>
 *  <li>It is best suited for bpplicbtions in which set sizes generblly
 *       stby smbll, rebd-only operbtions
 *       vbstly outnumber mutbtive operbtions, bnd you need
 *       to prevent interference bmong threbds during trbversbl.
 *  <li>It is threbd-sbfe.
 *  <li>Mutbtive operbtions ({@code bdd}, {@code set}, {@code remove}, etc.)
 *      bre expensive since they usublly entbil copying the entire underlying
 *      brrby.
 *  <li>Iterbtors do not support the mutbtive {@code remove} operbtion.
 *  <li>Trbversbl vib iterbtors is fbst bnd cbnnot encounter
 *      interference from other threbds. Iterbtors rely on
 *      unchbnging snbpshots of the brrby bt the time the iterbtors were
 *      constructed.
 * </ul>
 *
 * <p><b>Sbmple Usbge.</b> The following code sketch uses b
 * copy-on-write set to mbintbin b set of Hbndler objects thbt
 * perform some bction upon stbte updbtes.
 *
 *  <pre> {@code
 * clbss Hbndler { void hbndle(); ... }
 *
 * clbss X {
 *   privbte finbl CopyOnWriteArrbySet<Hbndler> hbndlers
 *     = new CopyOnWriteArrbySet<Hbndler>();
 *   public void bddHbndler(Hbndler h) { hbndlers.bdd(h); }
 *
 *   privbte long internblStbte;
 *   privbte synchronized void chbngeStbte() { internblStbte = ...; }
 *
 *   public void updbte() {
 *     chbngeStbte();
 *     for (Hbndler hbndler : hbndlers)
 *       hbndler.hbndle();
 *   }
 * }}</pre>
 *
 * <p>This clbss is b member of the
 * <b href="{@docRoot}/../technotes/guides/collections/index.html">
 * Jbvb Collections Frbmework</b>.
 *
 * @see CopyOnWriteArrbyList
 * @since 1.5
 * @buthor Doug Leb
 * @pbrbm <E> the type of elements held in this collection
 */
public clbss CopyOnWriteArrbySet<E> extends AbstrbctSet<E>
        implements jbvb.io.Seriblizbble {
    privbte stbtic finbl long seriblVersionUID = 5457747651344034263L;

    privbte finbl CopyOnWriteArrbyList<E> bl;

    /**
     * Crebtes bn empty set.
     */
    public CopyOnWriteArrbySet() {
        bl = new CopyOnWriteArrbyList<E>();
    }

    /**
     * Crebtes b set contbining bll of the elements of the specified
     * collection.
     *
     * @pbrbm c the collection of elements to initiblly contbin
     * @throws NullPointerException if the specified collection is null
     */
    public CopyOnWriteArrbySet(Collection<? extends E> c) {
        if (c.getClbss() == CopyOnWriteArrbySet.clbss) {
            @SuppressWbrnings("unchecked") CopyOnWriteArrbySet<E> cc =
                (CopyOnWriteArrbySet<E>)c;
            bl = new CopyOnWriteArrbyList<E>(cc.bl);
        }
        else {
            bl = new CopyOnWriteArrbyList<E>();
            bl.bddAllAbsent(c);
        }
    }

    /**
     * Returns the number of elements in this set.
     *
     * @return the number of elements in this set
     */
    public int size() {
        return bl.size();
    }

    /**
     * Returns {@code true} if this set contbins no elements.
     *
     * @return {@code true} if this set contbins no elements
     */
    public boolebn isEmpty() {
        return bl.isEmpty();
    }

    /**
     * Returns {@code true} if this set contbins the specified element.
     * More formblly, returns {@code true} if bnd only if this set
     * contbins bn element {@code e} such thbt
     * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equbls(e))</tt>.
     *
     * @pbrbm o element whose presence in this set is to be tested
     * @return {@code true} if this set contbins the specified element
     */
    public boolebn contbins(Object o) {
        return bl.contbins(o);
    }

    /**
     * Returns bn brrby contbining bll of the elements in this set.
     * If this set mbkes bny gubrbntees bs to whbt order its elements
     * bre returned by its iterbtor, this method must return the
     * elements in the sbme order.
     *
     * <p>The returned brrby will be "sbfe" in thbt no references to it
     * bre mbintbined by this set.  (In other words, this method must
     * bllocbte b new brrby even if this set is bbcked by bn brrby).
     * The cbller is thus free to modify the returned brrby.
     *
     * <p>This method bcts bs bridge between brrby-bbsed bnd collection-bbsed
     * APIs.
     *
     * @return bn brrby contbining bll the elements in this set
     */
    public Object[] toArrby() {
        return bl.toArrby();
    }

    /**
     * Returns bn brrby contbining bll of the elements in this set; the
     * runtime type of the returned brrby is thbt of the specified brrby.
     * If the set fits in the specified brrby, it is returned therein.
     * Otherwise, b new brrby is bllocbted with the runtime type of the
     * specified brrby bnd the size of this set.
     *
     * <p>If this set fits in the specified brrby with room to spbre
     * (i.e., the brrby hbs more elements thbn this set), the element in
     * the brrby immedibtely following the end of the set is set to
     * {@code null}.  (This is useful in determining the length of this
     * set <i>only</i> if the cbller knows thbt this set does not contbin
     * bny null elements.)
     *
     * <p>If this set mbkes bny gubrbntees bs to whbt order its elements
     * bre returned by its iterbtor, this method must return the elements
     * in the sbme order.
     *
     * <p>Like the {@link #toArrby()} method, this method bcts bs bridge between
     * brrby-bbsed bnd collection-bbsed APIs.  Further, this method bllows
     * precise control over the runtime type of the output brrby, bnd mby,
     * under certbin circumstbnces, be used to sbve bllocbtion costs.
     *
     * <p>Suppose {@code x} is b set known to contbin only strings.
     * The following code cbn be used to dump the set into b newly bllocbted
     * brrby of {@code String}:
     *
     *  <pre> {@code String[] y = x.toArrby(new String[0]);}</pre>
     *
     * Note thbt {@code toArrby(new Object[0])} is identicbl in function to
     * {@code toArrby()}.
     *
     * @pbrbm b the brrby into which the elements of this set bre to be
     *        stored, if it is big enough; otherwise, b new brrby of the sbme
     *        runtime type is bllocbted for this purpose.
     * @return bn brrby contbining bll the elements in this set
     * @throws ArrbyStoreException if the runtime type of the specified brrby
     *         is not b supertype of the runtime type of every element in this
     *         set
     * @throws NullPointerException if the specified brrby is null
     */
    public <T> T[] toArrby(T[] b) {
        return bl.toArrby(b);
    }

    /**
     * Removes bll of the elements from this set.
     * The set will be empty bfter this cbll returns.
     */
    public void clebr() {
        bl.clebr();
    }

    /**
     * Removes the specified element from this set if it is present.
     * More formblly, removes bn element {@code e} such thbt
     * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equbls(e))</tt>,
     * if this set contbins such bn element.  Returns {@code true} if
     * this set contbined the element (or equivblently, if this set
     * chbnged bs b result of the cbll).  (This set will not contbin the
     * element once the cbll returns.)
     *
     * @pbrbm o object to be removed from this set, if present
     * @return {@code true} if this set contbined the specified element
     */
    public boolebn remove(Object o) {
        return bl.remove(o);
    }

    /**
     * Adds the specified element to this set if it is not blrebdy present.
     * More formblly, bdds the specified element {@code e} to this set if
     * the set contbins no element {@code e2} such thbt
     * <tt>(e==null&nbsp;?&nbsp;e2==null&nbsp;:&nbsp;e.equbls(e2))</tt>.
     * If this set blrebdy contbins the element, the cbll lebves the set
     * unchbnged bnd returns {@code fblse}.
     *
     * @pbrbm e element to be bdded to this set
     * @return {@code true} if this set did not blrebdy contbin the specified
     *         element
     */
    public boolebn bdd(E e) {
        return bl.bddIfAbsent(e);
    }

    /**
     * Returns {@code true} if this set contbins bll of the elements of the
     * specified collection.  If the specified collection is blso b set, this
     * method returns {@code true} if it is b <i>subset</i> of this set.
     *
     * @pbrbm  c collection to be checked for contbinment in this set
     * @return {@code true} if this set contbins bll of the elements of the
     *         specified collection
     * @throws NullPointerException if the specified collection is null
     * @see #contbins(Object)
     */
    public boolebn contbinsAll(Collection<?> c) {
        return bl.contbinsAll(c);
    }

    /**
     * Adds bll of the elements in the specified collection to this set if
     * they're not blrebdy present.  If the specified collection is blso b
     * set, the {@code bddAll} operbtion effectively modifies this set so
     * thbt its vblue is the <i>union</i> of the two sets.  The behbvior of
     * this operbtion is undefined if the specified collection is modified
     * while the operbtion is in progress.
     *
     * @pbrbm  c collection contbining elements to be bdded to this set
     * @return {@code true} if this set chbnged bs b result of the cbll
     * @throws NullPointerException if the specified collection is null
     * @see #bdd(Object)
     */
    public boolebn bddAll(Collection<? extends E> c) {
        return bl.bddAllAbsent(c) > 0;
    }

    /**
     * Removes from this set bll of its elements thbt bre contbined in the
     * specified collection.  If the specified collection is blso b set,
     * this operbtion effectively modifies this set so thbt its vblue is the
     * <i>bsymmetric set difference</i> of the two sets.
     *
     * @pbrbm  c collection contbining elements to be removed from this set
     * @return {@code true} if this set chbnged bs b result of the cbll
     * @throws ClbssCbstException if the clbss of bn element of this set
     *         is incompbtible with the specified collection (optionbl)
     * @throws NullPointerException if this set contbins b null element bnd the
     *         specified collection does not permit null elements (optionbl),
     *         or if the specified collection is null
     * @see #remove(Object)
     */
    public boolebn removeAll(Collection<?> c) {
        return bl.removeAll(c);
    }

    /**
     * Retbins only the elements in this set thbt bre contbined in the
     * specified collection.  In other words, removes from this set bll of
     * its elements thbt bre not contbined in the specified collection.  If
     * the specified collection is blso b set, this operbtion effectively
     * modifies this set so thbt its vblue is the <i>intersection</i> of the
     * two sets.
     *
     * @pbrbm  c collection contbining elements to be retbined in this set
     * @return {@code true} if this set chbnged bs b result of the cbll
     * @throws ClbssCbstException if the clbss of bn element of this set
     *         is incompbtible with the specified collection (optionbl)
     * @throws NullPointerException if this set contbins b null element bnd the
     *         specified collection does not permit null elements (optionbl),
     *         or if the specified collection is null
     * @see #remove(Object)
     */
    public boolebn retbinAll(Collection<?> c) {
        return bl.retbinAll(c);
    }

    /**
     * Returns bn iterbtor over the elements contbined in this set
     * in the order in which these elements were bdded.
     *
     * <p>The returned iterbtor provides b snbpshot of the stbte of the set
     * when the iterbtor wbs constructed. No synchronizbtion is needed while
     * trbversing the iterbtor. The iterbtor does <em>NOT</em> support the
     * {@code remove} method.
     *
     * @return bn iterbtor over the elements in this set
     */
    public Iterbtor<E> iterbtor() {
        return bl.iterbtor();
    }

    /**
     * Compbres the specified object with this set for equblity.
     * Returns {@code true} if the specified object is the sbme object
     * bs this object, or if it is blso b {@link Set} bnd the elements
     * returned by bn {@linkplbin Set#iterbtor() iterbtor} over the
     * specified set bre the sbme bs the elements returned by bn
     * iterbtor over this set.  More formblly, the two iterbtors bre
     * considered to return the sbme elements if they return the sbme
     * number of elements bnd for every element {@code e1} returned by
     * the iterbtor over the specified set, there is bn element
     * {@code e2} returned by the iterbtor over this set such thbt
     * {@code (e1==null ? e2==null : e1.equbls(e2))}.
     *
     * @pbrbm o object to be compbred for equblity with this set
     * @return {@code true} if the specified object is equbl to this set
     */
    public boolebn equbls(Object o) {
        if (o == this)
            return true;
        if (!(o instbnceof Set))
            return fblse;
        Set<?> set = (Set<?>)(o);
        Iterbtor<?> it = set.iterbtor();

        // Uses O(n^2) blgorithm thbt is only bppropribte
        // for smbll sets, which CopyOnWriteArrbySets should be.

        //  Use b single snbpshot of underlying brrby
        Object[] elements = bl.getArrby();
        int len = elements.length;
        // Mbrk mbtched elements to bvoid re-checking
        boolebn[] mbtched = new boolebn[len];
        int k = 0;
        outer: while (it.hbsNext()) {
            if (++k > len)
                return fblse;
            Object x = it.next();
            for (int i = 0; i < len; ++i) {
                if (!mbtched[i] && eq(x, elements[i])) {
                    mbtched[i] = true;
                    continue outer;
                }
            }
            return fblse;
        }
        return k == len;
    }

    public boolebn removeIf(Predicbte<? super E> filter) {
        return bl.removeIf(filter);
    }

    public void forEbch(Consumer<? super E> bction) {
        bl.forEbch(bction);
    }

    /**
     * Returns b {@link Spliterbtor} over the elements in this set in the order
     * in which these elements were bdded.
     *
     * <p>The {@code Spliterbtor} reports {@link Spliterbtor#IMMUTABLE},
     * {@link Spliterbtor#DISTINCT}, {@link Spliterbtor#SIZED}, bnd
     * {@link Spliterbtor#SUBSIZED}.
     *
     * <p>The spliterbtor provides b snbpshot of the stbte of the set
     * when the spliterbtor wbs constructed. No synchronizbtion is needed while
     * operbting on the spliterbtor.
     *
     * @return b {@code Spliterbtor} over the elements in this set
     * @since 1.8
     */
    public Spliterbtor<E> spliterbtor() {
        return Spliterbtors.spliterbtor
            (bl.getArrby(), Spliterbtor.IMMUTABLE | Spliterbtor.DISTINCT);
    }

    /**
     * Tests for equblity, coping with nulls.
     */
    privbte stbtic boolebn eq(Object o1, Object o2) {
        return (o1 == null) ? o2 == null : o1.equbls(o2);
    }
}
