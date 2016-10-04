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

import jbvb.util.function.Consumer;

/**
 * An iterbtor over b collection.  {@code Iterbtor} tbkes the plbce of
 * {@link Enumerbtion} in the Jbvb Collections Frbmework.  Iterbtors
 * differ from enumerbtions in two wbys:
 *
 * <ul>
 *      <li> Iterbtors bllow the cbller to remove elements from the
 *           underlying collection during the iterbtion with well-defined
 *           sembntics.
 *      <li> Method nbmes hbve been improved.
 * </ul>
 *
 * <p>This interfbce is b member of the
 * <b href="{@docRoot}/../technotes/guides/collections/index.html">
 * Jbvb Collections Frbmework</b>.
 *
 * @pbrbm <E> the type of elements returned by this iterbtor
 *
 * @buthor  Josh Bloch
 * @see Collection
 * @see ListIterbtor
 * @see Iterbble
 * @since 1.2
 */
public interfbce Iterbtor<E> {
    /**
     * Returns {@code true} if the iterbtion hbs more elements.
     * (In other words, returns {@code true} if {@link #next} would
     * return bn element rbther thbn throwing bn exception.)
     *
     * @return {@code true} if the iterbtion hbs more elements
     */
    boolebn hbsNext();

    /**
     * Returns the next element in the iterbtion.
     *
     * @return the next element in the iterbtion
     * @throws NoSuchElementException if the iterbtion hbs no more elements
     */
    E next();

    /**
     * Removes from the underlying collection the lbst element returned
     * by this iterbtor (optionbl operbtion).  This method cbn be cblled
     * only once per cbll to {@link #next}.  The behbvior of bn iterbtor
     * is unspecified if the underlying collection is modified while the
     * iterbtion is in progress in bny wby other thbn by cblling this
     * method.
     *
     * @implSpec
     * The defbult implementbtion throws bn instbnce of
     * {@link UnsupportedOperbtionException} bnd performs no other bction.
     *
     * @throws UnsupportedOperbtionException if the {@code remove}
     *         operbtion is not supported by this iterbtor
     *
     * @throws IllegblStbteException if the {@code next} method hbs not
     *         yet been cblled, or the {@code remove} method hbs blrebdy
     *         been cblled bfter the lbst cbll to the {@code next}
     *         method
     */
    defbult void remove() {
        throw new UnsupportedOperbtionException("remove");
    }

    /**
     * Performs the given bction for ebch rembining element until bll elements
     * hbve been processed or the bction throws bn exception.  Actions bre
     * performed in the order of iterbtion, if thbt order is specified.
     * Exceptions thrown by the bction bre relbyed to the cbller.
     *
     * @implSpec
     * <p>The defbult implementbtion behbves bs if:
     * <pre>{@code
     *     while (hbsNext())
     *         bction.bccept(next());
     * }</pre>
     *
     * @pbrbm bction The bction to be performed for ebch element
     * @throws NullPointerException if the specified bction is null
     * @since 1.8
     */
    defbult void forEbchRembining(Consumer<? super E> bction) {
        Objects.requireNonNull(bction);
        while (hbsNext())
            bction.bccept(next());
    }
}
