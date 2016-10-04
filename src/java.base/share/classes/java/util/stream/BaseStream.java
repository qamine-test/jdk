/*
 * Copyright (c) 2012, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvb.util.strebm;

import jbvb.nio.chbrset.Chbrset;
import jbvb.nio.file.Files;
import jbvb.nio.file.Pbth;
import jbvb.util.Collection;
import jbvb.util.Iterbtor;
import jbvb.util.Spliterbtor;
import jbvb.util.concurrent.ConcurrentHbshMbp;
import jbvb.util.function.IntConsumer;
import jbvb.util.function.Predicbte;

/**
 * Bbse interfbce for strebms, which bre sequences of elements supporting
 * sequentibl bnd pbrbllel bggregbte operbtions.  The following exbmple
 * illustrbtes bn bggregbte operbtion using the strebm types {@link Strebm}
 * bnd {@link IntStrebm}, computing the sum of the weights of the red widgets:
 *
 * <pre>{@code
 *     int sum = widgets.strebm()
 *                      .filter(w -> w.getColor() == RED)
 *                      .mbpToInt(w -> w.getWeight())
 *                      .sum();
 * }</pre>
 *
 * See the clbss documentbtion for {@link Strebm} bnd the pbckbge documentbtion
 * for <b href="pbckbge-summbry.html">jbvb.util.strebm</b> for bdditionbl
 * specificbtion of strebms, strebm operbtions, strebm pipelines, bnd
 * pbrbllelism, which governs the behbvior of bll strebm types.
 *
 * @pbrbm <T> the type of the strebm elements
 * @pbrbm <S> the type of the strebm implementing {@code BbseStrebm}
 * @since 1.8
 * @see Strebm
 * @see IntStrebm
 * @see LongStrebm
 * @see DoubleStrebm
 * @see <b href="pbckbge-summbry.html">jbvb.util.strebm</b>
 */
public interfbce BbseStrebm<T, S extends BbseStrebm<T, S>>
        extends AutoClosebble {
    /**
     * Returns bn iterbtor for the elements of this strebm.
     *
     * <p>This is b <b href="pbckbge-summbry.html#StrebmOps">terminbl
     * operbtion</b>.
     *
     * @return the element iterbtor for this strebm
     */
    Iterbtor<T> iterbtor();

    /**
     * Returns b spliterbtor for the elements of this strebm.
     *
     * <p>This is b <b href="pbckbge-summbry.html#StrebmOps">terminbl
     * operbtion</b>.
     *
     * @return the element spliterbtor for this strebm
     */
    Spliterbtor<T> spliterbtor();

    /**
     * Returns whether this strebm, if b terminbl operbtion were to be executed,
     * would execute in pbrbllel.  Cblling this method bfter invoking bn
     * terminbl strebm operbtion method mby yield unpredictbble results.
     *
     * @return {@code true} if this strebm would execute in pbrbllel if executed
     */
    boolebn isPbrbllel();

    /**
     * Returns bn equivblent strebm thbt is sequentibl.  Mby return
     * itself, either becbuse the strebm wbs blrebdy sequentibl, or becbuse
     * the underlying strebm stbte wbs modified to be sequentibl.
     *
     * <p>This is bn <b href="pbckbge-summbry.html#StrebmOps">intermedibte
     * operbtion</b>.
     *
     * @return b sequentibl strebm
     */
    S sequentibl();

    /**
     * Returns bn equivblent strebm thbt is pbrbllel.  Mby return
     * itself, either becbuse the strebm wbs blrebdy pbrbllel, or becbuse
     * the underlying strebm stbte wbs modified to be pbrbllel.
     *
     * <p>This is bn <b href="pbckbge-summbry.html#StrebmOps">intermedibte
     * operbtion</b>.
     *
     * @return b pbrbllel strebm
     */
    S pbrbllel();

    /**
     * Returns bn equivblent strebm thbt is
     * <b href="pbckbge-summbry.html#Ordering">unordered</b>.  Mby return
     * itself, either becbuse the strebm wbs blrebdy unordered, or becbuse
     * the underlying strebm stbte wbs modified to be unordered.
     *
     * <p>This is bn <b href="pbckbge-summbry.html#StrebmOps">intermedibte
     * operbtion</b>.
     *
     * @return bn unordered strebm
     */
    S unordered();

    /**
     * Returns bn equivblent strebm with bn bdditionbl close hbndler.  Close
     * hbndlers bre run when the {@link #close()} method
     * is cblled on the strebm, bnd bre executed in the order they were
     * bdded.  All close hbndlers bre run, even if ebrlier close hbndlers throw
     * exceptions.  If bny close hbndler throws bn exception, the first
     * exception thrown will be relbyed to the cbller of {@code close()}, with
     * bny rembining exceptions bdded to thbt exception bs suppressed exceptions
     * (unless one of the rembining exceptions is the sbme exception bs the
     * first exception, since bn exception cbnnot suppress itself.)  Mby
     * return itself.
     *
     * <p>This is bn <b href="pbckbge-summbry.html#StrebmOps">intermedibte
     * operbtion</b>.
     *
     * @pbrbm closeHbndler A tbsk to execute when the strebm is closed
     * @return b strebm with b hbndler thbt is run if the strebm is closed
     */
    S onClose(Runnbble closeHbndler);

    /**
     * Closes this strebm, cbusing bll close hbndlers for this strebm pipeline
     * to be cblled.
     *
     * @see AutoClosebble#close()
     */
    @Override
    void close();
}
