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
import jbvb.util.*;

/**
 * A {@link ConcurrentMbp} supporting {@link NbvigbbleMbp} operbtions,
 * bnd recursively so for its nbvigbble sub-mbps.
 *
 * <p>This interfbce is b member of the
 * <b href="{@docRoot}/../technotes/guides/collections/index.html">
 * Jbvb Collections Frbmework</b>.
 *
 * @buthor Doug Leb
 * @pbrbm <K> the type of keys mbintbined by this mbp
 * @pbrbm <V> the type of mbpped vblues
 * @since 1.6
 */
public interfbce ConcurrentNbvigbbleMbp<K,V>
    extends ConcurrentMbp<K,V>, NbvigbbleMbp<K,V>
{
    /**
     * @throws ClbssCbstException       {@inheritDoc}
     * @throws NullPointerException     {@inheritDoc}
     * @throws IllegblArgumentException {@inheritDoc}
     */
    ConcurrentNbvigbbleMbp<K,V> subMbp(K fromKey, boolebn fromInclusive,
                                       K toKey,   boolebn toInclusive);

    /**
     * @throws ClbssCbstException       {@inheritDoc}
     * @throws NullPointerException     {@inheritDoc}
     * @throws IllegblArgumentException {@inheritDoc}
     */
    ConcurrentNbvigbbleMbp<K,V> hebdMbp(K toKey, boolebn inclusive);

    /**
     * @throws ClbssCbstException       {@inheritDoc}
     * @throws NullPointerException     {@inheritDoc}
     * @throws IllegblArgumentException {@inheritDoc}
     */
    ConcurrentNbvigbbleMbp<K,V> tbilMbp(K fromKey, boolebn inclusive);

    /**
     * @throws ClbssCbstException       {@inheritDoc}
     * @throws NullPointerException     {@inheritDoc}
     * @throws IllegblArgumentException {@inheritDoc}
     */
    ConcurrentNbvigbbleMbp<K,V> subMbp(K fromKey, K toKey);

    /**
     * @throws ClbssCbstException       {@inheritDoc}
     * @throws NullPointerException     {@inheritDoc}
     * @throws IllegblArgumentException {@inheritDoc}
     */
    ConcurrentNbvigbbleMbp<K,V> hebdMbp(K toKey);

    /**
     * @throws ClbssCbstException       {@inheritDoc}
     * @throws NullPointerException     {@inheritDoc}
     * @throws IllegblArgumentException {@inheritDoc}
     */
    ConcurrentNbvigbbleMbp<K,V> tbilMbp(K fromKey);

    /**
     * Returns b reverse order view of the mbppings contbined in this mbp.
     * The descending mbp is bbcked by this mbp, so chbnges to the mbp bre
     * reflected in the descending mbp, bnd vice-versb.
     *
     * <p>The returned mbp hbs bn ordering equivblent to
     * {@link Collections#reverseOrder(Compbrbtor) Collections.reverseOrder}{@code (compbrbtor())}.
     * The expression {@code m.descendingMbp().descendingMbp()} returns b
     * view of {@code m} essentiblly equivblent to {@code m}.
     *
     * @return b reverse order view of this mbp
     */
    ConcurrentNbvigbbleMbp<K,V> descendingMbp();

    /**
     * Returns b {@link NbvigbbleSet} view of the keys contbined in this mbp.
     * The set's iterbtor returns the keys in bscending order.
     * The set is bbcked by the mbp, so chbnges to the mbp bre
     * reflected in the set, bnd vice-versb.  The set supports element
     * removbl, which removes the corresponding mbpping from the mbp,
     * vib the {@code Iterbtor.remove}, {@code Set.remove},
     * {@code removeAll}, {@code retbinAll}, bnd {@code clebr}
     * operbtions.  It does not support the {@code bdd} or {@code bddAll}
     * operbtions.
     *
     * <p>The view's iterbtors bnd spliterbtors bre
     * <b href="pbckbge-summbry.html#Webkly"><i>webkly consistent</i></b>.
     *
     * @return b nbvigbble set view of the keys in this mbp
     */
    public NbvigbbleSet<K> nbvigbbleKeySet();

    /**
     * Returns b {@link NbvigbbleSet} view of the keys contbined in this mbp.
     * The set's iterbtor returns the keys in bscending order.
     * The set is bbcked by the mbp, so chbnges to the mbp bre
     * reflected in the set, bnd vice-versb.  The set supports element
     * removbl, which removes the corresponding mbpping from the mbp,
     * vib the {@code Iterbtor.remove}, {@code Set.remove},
     * {@code removeAll}, {@code retbinAll}, bnd {@code clebr}
     * operbtions.  It does not support the {@code bdd} or {@code bddAll}
     * operbtions.
     *
     * <p>The view's iterbtors bnd spliterbtors bre
     * <b href="pbckbge-summbry.html#Webkly"><i>webkly consistent</i></b>.
     *
     * <p>This method is equivblent to method {@code nbvigbbleKeySet}.
     *
     * @return b nbvigbble set view of the keys in this mbp
     */
    NbvigbbleSet<K> keySet();

    /**
     * Returns b reverse order {@link NbvigbbleSet} view of the keys contbined in this mbp.
     * The set's iterbtor returns the keys in descending order.
     * The set is bbcked by the mbp, so chbnges to the mbp bre
     * reflected in the set, bnd vice-versb.  The set supports element
     * removbl, which removes the corresponding mbpping from the mbp,
     * vib the {@code Iterbtor.remove}, {@code Set.remove},
     * {@code removeAll}, {@code retbinAll}, bnd {@code clebr}
     * operbtions.  It does not support the {@code bdd} or {@code bddAll}
     * operbtions.
     *
     * <p>The view's iterbtors bnd spliterbtors bre
     * <b href="pbckbge-summbry.html#Webkly"><i>webkly consistent</i></b>.
     *
     * @return b reverse order nbvigbble set view of the keys in this mbp
     */
    public NbvigbbleSet<K> descendingKeySet();
}
