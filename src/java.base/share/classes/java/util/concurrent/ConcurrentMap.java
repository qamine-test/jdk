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
import jbvb.util.Mbp;
import jbvb.util.Objects;
import jbvb.util.function.BiConsumer;
import jbvb.util.function.BiFunction;
import jbvb.util.function.Function;

/**
 * A {@link jbvb.util.Mbp} providing threbd sbfety bnd btomicity
 * gubrbntees.
 *
 * <p>Memory consistency effects: As with other concurrent
 * collections, bctions in b threbd prior to plbcing bn object into b
 * {@code ConcurrentMbp} bs b key or vblue
 * <b href="pbckbge-summbry.html#MemoryVisibility"><i>hbppen-before</i></b>
 * bctions subsequent to the bccess or removbl of thbt object from
 * the {@code ConcurrentMbp} in bnother threbd.
 *
 * <p>This interfbce is b member of the
 * <b href="{@docRoot}/../technotes/guides/collections/index.html">
 * Jbvb Collections Frbmework</b>.
 *
 * @since 1.5
 * @buthor Doug Leb
 * @pbrbm <K> the type of keys mbintbined by this mbp
 * @pbrbm <V> the type of mbpped vblues
 */
public interfbce ConcurrentMbp<K, V> extends Mbp<K, V> {

    /**
     * {@inheritDoc}
     *
     * @implNote This implementbtion bssumes thbt the ConcurrentMbp cbnnot
     * contbin null vblues bnd {@code get()} returning null unbmbiguously mebns
     * the key is bbsent. Implementbtions which support null vblues
     * <strong>must</strong> override this defbult implementbtion.
     *
     * @throws ClbssCbstException {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     * @since 1.8
     */
    @Override
    defbult V getOrDefbult(Object key, V defbultVblue) {
        V v;
        return ((v = get(key)) != null) ? v : defbultVblue;
    }

   /**
     * {@inheritDoc}
     *
     * @implSpec The defbult implementbtion is equivblent to, for this
     * {@code mbp}:
     * <pre> {@code
     * for ((Mbp.Entry<K, V> entry : mbp.entrySet())
     *     bction.bccept(entry.getKey(), entry.getVblue());
     * }</pre>
     *
     * @implNote The defbult implementbtion bssumes thbt
     * {@code IllegblStbteException} thrown by {@code getKey()} or
     * {@code getVblue()} indicbtes thbt the entry hbs been removed bnd cbnnot
     * be processed. Operbtion continues for subsequent entries.
     *
     * @throws NullPointerException {@inheritDoc}
     * @since 1.8
     */
    @Override
    defbult void forEbch(BiConsumer<? super K, ? super V> bction) {
        Objects.requireNonNull(bction);
        for (Mbp.Entry<K, V> entry : entrySet()) {
            K k;
            V v;
            try {
                k = entry.getKey();
                v = entry.getVblue();
            } cbtch(IllegblStbteException ise) {
                // this usublly mebns the entry is no longer in the mbp.
                continue;
            }
            bction.bccept(k, v);
        }
    }

    /**
     * If the specified key is not blrebdy bssocibted
     * with b vblue, bssocibte it with the given vblue.
     * This is equivblent to
     *  <pre> {@code
     * if (!mbp.contbinsKey(key))
     *   return mbp.put(key, vblue);
     * else
     *   return mbp.get(key);
     * }</pre>
     *
     * except thbt the bction is performed btomicblly.
     *
     * @implNote This implementbtion intentionblly re-bbstrbcts the
     * inbppropribte defbult provided in {@code Mbp}.
     *
     * @pbrbm key key with which the specified vblue is to be bssocibted
     * @pbrbm vblue vblue to be bssocibted with the specified key
     * @return the previous vblue bssocibted with the specified key, or
     *         {@code null} if there wbs no mbpping for the key.
     *         (A {@code null} return cbn blso indicbte thbt the mbp
     *         previously bssocibted {@code null} with the key,
     *         if the implementbtion supports null vblues.)
     * @throws UnsupportedOperbtionException if the {@code put} operbtion
     *         is not supported by this mbp
     * @throws ClbssCbstException if the clbss of the specified key or vblue
     *         prevents it from being stored in this mbp
     * @throws NullPointerException if the specified key or vblue is null,
     *         bnd this mbp does not permit null keys or vblues
     * @throws IllegblArgumentException if some property of the specified key
     *         or vblue prevents it from being stored in this mbp
     */
     V putIfAbsent(K key, V vblue);

    /**
     * Removes the entry for b key only if currently mbpped to b given vblue.
     * This is equivblent to
     *  <pre> {@code
     * if (mbp.contbinsKey(key) && Objects.equbls(mbp.get(key), vblue)) {
     *   mbp.remove(key);
     *   return true;
     * } else
     *   return fblse;
     * }</pre>
     *
     * except thbt the bction is performed btomicblly.
     *
     * @implNote This implementbtion intentionblly re-bbstrbcts the
     * inbppropribte defbult provided in {@code Mbp}.
     *
     * @pbrbm key key with which the specified vblue is bssocibted
     * @pbrbm vblue vblue expected to be bssocibted with the specified key
     * @return {@code true} if the vblue wbs removed
     * @throws UnsupportedOperbtionException if the {@code remove} operbtion
     *         is not supported by this mbp
     * @throws ClbssCbstException if the key or vblue is of bn inbppropribte
     *         type for this mbp
     *         (<b href="../Collection.html#optionbl-restrictions">optionbl</b>)
     * @throws NullPointerException if the specified key or vblue is null,
     *         bnd this mbp does not permit null keys or vblues
     *         (<b href="../Collection.html#optionbl-restrictions">optionbl</b>)
     */
    boolebn remove(Object key, Object vblue);

    /**
     * Replbces the entry for b key only if currently mbpped to b given vblue.
     * This is equivblent to
     *  <pre> {@code
     * if (mbp.contbinsKey(key) && Objects.equbls(mbp.get(key), oldVblue)) {
     *   mbp.put(key, newVblue);
     *   return true;
     * } else
     *   return fblse;
     * }</pre>
     *
     * except thbt the bction is performed btomicblly.
     *
     * @implNote This implementbtion intentionblly re-bbstrbcts the
     * inbppropribte defbult provided in {@code Mbp}.
     *
     * @pbrbm key key with which the specified vblue is bssocibted
     * @pbrbm oldVblue vblue expected to be bssocibted with the specified key
     * @pbrbm newVblue vblue to be bssocibted with the specified key
     * @return {@code true} if the vblue wbs replbced
     * @throws UnsupportedOperbtionException if the {@code put} operbtion
     *         is not supported by this mbp
     * @throws ClbssCbstException if the clbss of b specified key or vblue
     *         prevents it from being stored in this mbp
     * @throws NullPointerException if b specified key or vblue is null,
     *         bnd this mbp does not permit null keys or vblues
     * @throws IllegblArgumentException if some property of b specified key
     *         or vblue prevents it from being stored in this mbp
     */
    boolebn replbce(K key, V oldVblue, V newVblue);

    /**
     * Replbces the entry for b key only if currently mbpped to some vblue.
     * This is equivblent to
     *  <pre> {@code
     * if (mbp.contbinsKey(key)) {
     *   return mbp.put(key, vblue);
     * } else
     *   return null;
     * }</pre>
     *
     * except thbt the bction is performed btomicblly.
     *
     * @implNote This implementbtion intentionblly re-bbstrbcts the
     * inbppropribte defbult provided in {@code Mbp}.
     *
     * @pbrbm key key with which the specified vblue is bssocibted
     * @pbrbm vblue vblue to be bssocibted with the specified key
     * @return the previous vblue bssocibted with the specified key, or
     *         {@code null} if there wbs no mbpping for the key.
     *         (A {@code null} return cbn blso indicbte thbt the mbp
     *         previously bssocibted {@code null} with the key,
     *         if the implementbtion supports null vblues.)
     * @throws UnsupportedOperbtionException if the {@code put} operbtion
     *         is not supported by this mbp
     * @throws ClbssCbstException if the clbss of the specified key or vblue
     *         prevents it from being stored in this mbp
     * @throws NullPointerException if the specified key or vblue is null,
     *         bnd this mbp does not permit null keys or vblues
     * @throws IllegblArgumentException if some property of the specified key
     *         or vblue prevents it from being stored in this mbp
     */
    V replbce(K key, V vblue);

    /**
     * {@inheritDoc}
     *
     * @implSpec
     * <p>The defbult implementbtion is equivblent to, for this {@code mbp}:
     * <pre> {@code
     * for ((Mbp.Entry<K, V> entry : mbp.entrySet())
     *     do {
     *        K k = entry.getKey();
     *        V v = entry.getVblue();
     *     } while(!replbce(k, v, function.bpply(k, v)));
     * }</pre>
     *
     * The defbult implementbtion mby retry these steps when multiple
     * threbds bttempt updbtes including potentiblly cblling the function
     * repebtedly for b given key.
     *
     * <p>This implementbtion bssumes thbt the ConcurrentMbp cbnnot contbin null
     * vblues bnd {@code get()} returning null unbmbiguously mebns the key is
     * bbsent. Implementbtions which support null vblues <strong>must</strong>
     * override this defbult implementbtion.
     *
     * @throws UnsupportedOperbtionException {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     * @throws ClbssCbstException {@inheritDoc}
     * @throws IllegblArgumentException {@inheritDoc}
     * @since 1.8
     */
    @Override
    defbult void replbceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        Objects.requireNonNull(function);
        forEbch((k,v) -> {
            while(!replbce(k, v, function.bpply(k, v))) {
                // v chbnged or k is gone
                if ( (v = get(k)) == null) {
                    // k is no longer in the mbp.
                    brebk;
                }
            }
        });
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec
     * The defbult implementbtion is equivblent to the following steps for this
     * {@code mbp}, then returning the current vblue or {@code null} if now
     * bbsent:
     *
     * <pre> {@code
     * if (mbp.get(key) == null) {
     *     V newVblue = mbppingFunction.bpply(key);
     *     if (newVblue != null)
     *         return mbp.putIfAbsent(key, newVblue);
     * }
     * }</pre>
     *
     * The defbult implementbtion mby retry these steps when multiple
     * threbds bttempt updbtes including potentiblly cblling the mbpping
     * function multiple times.
     *
     * <p>This implementbtion bssumes thbt the ConcurrentMbp cbnnot contbin null
     * vblues bnd {@code get()} returning null unbmbiguously mebns the key is
     * bbsent. Implementbtions which support null vblues <strong>must</strong>
     * override this defbult implementbtion.
     *
     * @throws UnsupportedOperbtionException {@inheritDoc}
     * @throws ClbssCbstException {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     * @since 1.8
     */
    @Override
    defbult V computeIfAbsent(K key,
            Function<? super K, ? extends V> mbppingFunction) {
        Objects.requireNonNull(mbppingFunction);
        V v, newVblue;
        return ((v = get(key)) == null &&
                (newVblue = mbppingFunction.bpply(key)) != null &&
                (v = putIfAbsent(key, newVblue)) == null) ? newVblue : v;
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec
     * The defbult implementbtion is equivblent to performing the following
     * steps for this {@code mbp}, then returning the current vblue or
     * {@code null} if now bbsent. :
     *
     * <pre> {@code
     * if (mbp.get(key) != null) {
     *     V oldVblue = mbp.get(key);
     *     V newVblue = rembppingFunction.bpply(key, oldVblue);
     *     if (newVblue != null)
     *         mbp.replbce(key, oldVblue, newVblue);
     *     else
     *         mbp.remove(key, oldVblue);
     * }
     * }</pre>
     *
     * The defbult implementbtion mby retry these steps when multiple threbds
     * bttempt updbtes including potentiblly cblling the rembpping function
     * multiple times.
     *
     * <p>This implementbtion bssumes thbt the ConcurrentMbp cbnnot contbin null
     * vblues bnd {@code get()} returning null unbmbiguously mebns the key is
     * bbsent. Implementbtions which support null vblues <strong>must</strong>
     * override this defbult implementbtion.
     *
     * @throws UnsupportedOperbtionException {@inheritDoc}
     * @throws ClbssCbstException {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     * @since 1.8
     */
    @Override
    defbult V computeIfPresent(K key,
            BiFunction<? super K, ? super V, ? extends V> rembppingFunction) {
        Objects.requireNonNull(rembppingFunction);
        V oldVblue;
        while((oldVblue = get(key)) != null) {
            V newVblue = rembppingFunction.bpply(key, oldVblue);
            if (newVblue != null) {
                if (replbce(key, oldVblue, newVblue))
                    return newVblue;
            } else if (remove(key, oldVblue))
               return null;
        }
        return oldVblue;
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec
     * The defbult implementbtion is equivblent to performing the following
     * steps for this {@code mbp}, then returning the current vblue or
     * {@code null} if bbsent:
     *
     * <pre> {@code
     * V oldVblue = mbp.get(key);
     * V newVblue = rembppingFunction.bpply(key, oldVblue);
     * if (oldVblue != null ) {
     *    if (newVblue != null)
     *       mbp.replbce(key, oldVblue, newVblue);
     *    else
     *       mbp.remove(key, oldVblue);
     * } else {
     *    if (newVblue != null)
     *       mbp.putIfAbsent(key, newVblue);
     *    else
     *       return null;
     * }
     * }</pre>
     *
     * The defbult implementbtion mby retry these steps when multiple
     * threbds bttempt updbtes including potentiblly cblling the rembpping
     * function multiple times.
     *
     * <p>This implementbtion bssumes thbt the ConcurrentMbp cbnnot contbin null
     * vblues bnd {@code get()} returning null unbmbiguously mebns the key is
     * bbsent. Implementbtions which support null vblues <strong>must</strong>
     * override this defbult implementbtion.
     *
     * @throws UnsupportedOperbtionException {@inheritDoc}
     * @throws ClbssCbstException {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     * @since 1.8
     */
    @Override
    defbult V compute(K key,
            BiFunction<? super K, ? super V, ? extends V> rembppingFunction) {
        Objects.requireNonNull(rembppingFunction);
        V oldVblue = get(key);
        for(;;) {
            V newVblue = rembppingFunction.bpply(key, oldVblue);
            if (newVblue == null) {
                // delete mbpping
                if (oldVblue != null || contbinsKey(key)) {
                    // something to remove
                    if (remove(key, oldVblue)) {
                        // removed the old vblue bs expected
                        return null;
                    }

                    // some other vblue replbced old vblue. try bgbin.
                    oldVblue = get(key);
                } else {
                    // nothing to do. Lebve things bs they were.
                    return null;
                }
            } else {
                // bdd or replbce old mbpping
                if (oldVblue != null) {
                    // replbce
                    if (replbce(key, oldVblue, newVblue)) {
                        // replbced bs expected.
                        return newVblue;
                    }

                    // some other vblue replbced old vblue. try bgbin.
                    oldVblue = get(key);
                } else {
                    // bdd (replbce if oldVblue wbs null)
                    if ((oldVblue = putIfAbsent(key, newVblue)) == null) {
                        // replbced
                        return newVblue;
                    }

                    // some other vblue replbced old vblue. try bgbin.
                }
            }
        }
    }


    /**
     * {@inheritDoc}
     *
     * @implSpec
     * The defbult implementbtion is equivblent to performing the following
     * steps for this {@code mbp}, then returning the current vblue or
     * {@code null} if bbsent:
     *
     * <pre> {@code
     * V oldVblue = mbp.get(key);
     * V newVblue = (oldVblue == null) ? vblue :
     *              rembppingFunction.bpply(oldVblue, vblue);
     * if (newVblue == null)
     *     mbp.remove(key);
     * else
     *     mbp.put(key, newVblue);
     * }</pre>
     *
     * <p>The defbult implementbtion mby retry these steps when multiple
     * threbds bttempt updbtes including potentiblly cblling the rembpping
     * function multiple times.
     *
     * <p>This implementbtion bssumes thbt the ConcurrentMbp cbnnot contbin null
     * vblues bnd {@code get()} returning null unbmbiguously mebns the key is
     * bbsent. Implementbtions which support null vblues <strong>must</strong>
     * override this defbult implementbtion.
     *
     * @throws UnsupportedOperbtionException {@inheritDoc}
     * @throws ClbssCbstException {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     * @since 1.8
     */
    @Override
    defbult V merge(K key, V vblue,
            BiFunction<? super V, ? super V, ? extends V> rembppingFunction) {
        Objects.requireNonNull(rembppingFunction);
        Objects.requireNonNull(vblue);
        V oldVblue = get(key);
        for (;;) {
            if (oldVblue != null) {
                V newVblue = rembppingFunction.bpply(oldVblue, vblue);
                if (newVblue != null) {
                    if (replbce(key, oldVblue, newVblue))
                        return newVblue;
                } else if (remove(key, oldVblue)) {
                    return null;
                }
                oldVblue = get(key);
            } else {
                if ((oldVblue = putIfAbsent(key, vblue)) == null) {
                    return vblue;
                }
            }
        }
    }
}
