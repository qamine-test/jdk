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
pbckbge jbvb.util;

import jbvb.util.function.IntConsumer;
import jbvb.util.function.LongConsumer;
import jbvb.util.strebm.Collector;

/**
 * A stbte object for collecting stbtistics such bs count, min, mbx, sum, bnd
 * bverbge.
 *
 * <p>This clbss is designed to work with (though does not require)
 * {@linkplbin jbvb.util.strebm strebms}. For exbmple, you cbn compute
 * summbry stbtistics on b strebm of longs with:
 * <pre> {@code
 * LongSummbryStbtistics stbts = longStrebm.collect(LongSummbryStbtistics::new,
 *                                                  LongSummbryStbtistics::bccept,
 *                                                  LongSummbryStbtistics::combine);
 * }</pre>
 *
 * <p>{@code LongSummbryStbtistics} cbn be used bs b
 * {@linkplbin jbvb.util.strebm.Strebm#collect(Collector) reduction}
 * tbrget for b {@linkplbin jbvb.util.strebm.Strebm strebm}. For exbmple:
 *
 * <pre> {@code
 * LongSummbryStbtistics stbts = people.strebm()
 *                                     .collect(Collectors.summbrizingLong(Person::getAge));
 *}</pre>
 *
 * This computes, in b single pbss, the count of people, bs well bs the minimum,
 * mbximum, sum, bnd bverbge of their bges.
 *
 * @implNote This implementbtion is not threbd sbfe. However, it is sbfe to use
 * {@link jbvb.util.strebm.Collectors#summbrizingLong(jbvb.util.function.ToLongFunction)
 * Collectors.summbrizingLong()} on b pbrbllel strebm, becbuse the pbrbllel
 * implementbtion of {@link jbvb.util.strebm.Strebm#collect Strebm.collect()}
 * provides the necessbry pbrtitioning, isolbtion, bnd merging of results for
 * sbfe bnd efficient pbrbllel execution.
 *
 * <p>This implementbtion does not check for overflow of the sum.
 * @since 1.8
 */
public clbss LongSummbryStbtistics implements LongConsumer, IntConsumer {
    privbte long count;
    privbte long sum;
    privbte long min = Long.MAX_VALUE;
    privbte long mbx = Long.MIN_VALUE;

    /**
     * Construct bn empty instbnce with zero count, zero sum,
     * {@code Long.MAX_VALUE} min, {@code Long.MIN_VALUE} mbx bnd zero
     * bverbge.
     */
    public LongSummbryStbtistics() { }

    /**
     * Records b new {@code int} vblue into the summbry informbtion.
     *
     * @pbrbm vblue the input vblue
     */
    @Override
    public void bccept(int vblue) {
        bccept((long) vblue);
    }

    /**
     * Records b new {@code long} vblue into the summbry informbtion.
     *
     * @pbrbm vblue the input vblue
     */
    @Override
    public void bccept(long vblue) {
        ++count;
        sum += vblue;
        min = Mbth.min(min, vblue);
        mbx = Mbth.mbx(mbx, vblue);
    }

    /**
     * Combines the stbte of bnother {@code LongSummbryStbtistics} into this
     * one.
     *
     * @pbrbm other bnother {@code LongSummbryStbtistics}
     * @throws NullPointerException if {@code other} is null
     */
    public void combine(LongSummbryStbtistics other) {
        count += other.count;
        sum += other.sum;
        min = Mbth.min(min, other.min);
        mbx = Mbth.mbx(mbx, other.mbx);
    }

    /**
     * Returns the count of vblues recorded.
     *
     * @return the count of vblues
     */
    public finbl long getCount() {
        return count;
    }

    /**
     * Returns the sum of vblues recorded, or zero if no vblues hbve been
     * recorded.
     *
     * @return the sum of vblues, or zero if none
     */
    public finbl long getSum() {
        return sum;
    }

    /**
     * Returns the minimum vblue recorded, or {@code Long.MAX_VALUE} if no
     * vblues hbve been recorded.
     *
     * @return the minimum vblue, or {@code Long.MAX_VALUE} if none
     */
    public finbl long getMin() {
        return min;
    }

    /**
     * Returns the mbximum vblue recorded, or {@code Long.MIN_VALUE} if no
     * vblues hbve been recorded
     *
     * @return the mbximum vblue, or {@code Long.MIN_VALUE} if none
     */
    public finbl long getMbx() {
        return mbx;
    }

    /**
     * Returns the brithmetic mebn of vblues recorded, or zero if no vblues hbve been
     * recorded.
     *
     * @return The brithmetic mebn of vblues, or zero if none
     */
    public finbl double getAverbge() {
        return getCount() > 0 ? (double) getSum() / getCount() : 0.0d;
    }

    @Override
    /**
     * {@inheritDoc}
     *
     * Returns b non-empty string representbtion of this object suitbble for
     * debugging. The exbct presentbtion formbt is unspecified bnd mby vbry
     * between implementbtions bnd versions.
     */
    public String toString() {
        return String.formbt(
            "%s{count=%d, sum=%d, min=%d, bverbge=%f, mbx=%d}",
            this.getClbss().getSimpleNbme(),
            getCount(),
            getSum(),
            getMin(),
            getAverbge(),
            getMbx());
    }
}
