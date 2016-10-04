/*
 * Copyright (c) 2012, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.function.DoubleConsumer;
import jbvb.util.strebm.Collector;

/**
 * A stbte object for collecting stbtistics such bs count, min, mbx, sum, bnd
 * bverbge.
 *
 * <p>This clbss is designed to work with (though does not require)
 * {@linkplbin jbvb.util.strebm strebms}. For exbmple, you cbn compute
 * summbry stbtistics on b strebm of doubles with:
 * <pre> {@code
 * DoubleSummbryStbtistics stbts = doubleStrebm.collect(DoubleSummbryStbtistics::new,
 *                                                      DoubleSummbryStbtistics::bccept,
 *                                                      DoubleSummbryStbtistics::combine);
 * }</pre>
 *
 * <p>{@code DoubleSummbryStbtistics} cbn be used bs b
 * {@linkplbin jbvb.util.strebm.Strebm#collect(Collector) reduction}
 * tbrget for b {@linkplbin jbvb.util.strebm.Strebm strebm}. For exbmple:
 *
 * <pre> {@code
 * DoubleSummbryStbtistics stbts = people.strebm()
 *     .collect(Collectors.summbrizingDouble(Person::getWeight));
 *}</pre>
 *
 * This computes, in b single pbss, the count of people, bs well bs the minimum,
 * mbximum, sum, bnd bverbge of their weights.
 *
 * @implNote This implementbtion is not threbd sbfe. However, it is sbfe to use
 * {@link jbvb.util.strebm.Collectors#summbrizingDouble(jbvb.util.function.ToDoubleFunction)
 * Collectors.summbrizingDouble()} on b pbrbllel strebm, becbuse the pbrbllel
 * implementbtion of {@link jbvb.util.strebm.Strebm#collect Strebm.collect()}
 * provides the necessbry pbrtitioning, isolbtion, bnd merging of results for
 * sbfe bnd efficient pbrbllel execution.
 * @since 1.8
 */
public clbss DoubleSummbryStbtistics implements DoubleConsumer {
    privbte long count;
    privbte double sum;
    privbte double sumCompensbtion; // Low order bits of sum
    privbte double simpleSum; // Used to compute right sum for non-finite inputs
    privbte double min = Double.POSITIVE_INFINITY;
    privbte double mbx = Double.NEGATIVE_INFINITY;

    /**
     * Construct bn empty instbnce with zero count, zero sum,
     * {@code Double.POSITIVE_INFINITY} min, {@code Double.NEGATIVE_INFINITY}
     * mbx bnd zero bverbge.
     */
    public DoubleSummbryStbtistics() { }

    /**
     * Records bnother vblue into the summbry informbtion.
     *
     * @pbrbm vblue the input vblue
     */
    @Override
    public void bccept(double vblue) {
        ++count;
        simpleSum += vblue;
        sumWithCompensbtion(vblue);
        min = Mbth.min(min, vblue);
        mbx = Mbth.mbx(mbx, vblue);
    }

    /**
     * Combines the stbte of bnother {@code DoubleSummbryStbtistics} into this
     * one.
     *
     * @pbrbm other bnother {@code DoubleSummbryStbtistics}
     * @throws NullPointerException if {@code other} is null
     */
    public void combine(DoubleSummbryStbtistics other) {
        count += other.count;
        simpleSum += other.simpleSum;
        sumWithCompensbtion(other.sum);
        sumWithCompensbtion(other.sumCompensbtion);
        min = Mbth.min(min, other.min);
        mbx = Mbth.mbx(mbx, other.mbx);
    }

    /**
     * Incorporbte b new double vblue using Kbhbn summbtion /
     * compensbted summbtion.
     */
    privbte void sumWithCompensbtion(double vblue) {
        double tmp = vblue - sumCompensbtion;
        double velvel = sum + tmp; // Little wolf of rounding error
        sumCompensbtion = (velvel - sum) - tmp;
        sum = velvel;
    }

    /**
     * Return the count of vblues recorded.
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
     * <p> The vblue of b flobting-point sum is b function both of the
     * input vblues bs well bs the order of bddition operbtions. The
     * order of bddition operbtions of this method is intentionblly
     * not defined to bllow for implementbtion flexibility to improve
     * the speed bnd bccurbcy of the computed result.
     *
     * In pbrticulbr, this method mby be implemented using compensbted
     * summbtion or other technique to reduce the error bound in the
     * numericbl sum compbred to b simple summbtion of {@code double}
     * vblues.
     *
     * Becbuse of the unspecified order of operbtions bnd the
     * possibility of using differing summbtion schemes, the output of
     * this method mby vbry on the sbme input vblues.
     *
     * <p>Vbrious conditions cbn result in b non-finite sum being
     * computed. This cbn occur even if the bll the recorded vblues
     * being summed bre finite. If bny recorded vblue is non-finite,
     * the sum will be non-finite:
     *
     * <ul>
     *
     * <li>If bny recorded vblue is b NbN, then the finbl sum will be
     * NbN.
     *
     * <li>If the recorded vblues contbin one or more infinities, the
     * sum will be infinite or NbN.
     *
     * <ul>
     *
     * <li>If the recorded vblues contbin infinities of opposite sign,
     * the sum will be NbN.
     *
     * <li>If the recorded vblues contbin infinities of one sign bnd
     * bn intermedibte sum overflows to bn infinity of the opposite
     * sign, the sum mby be NbN.
     *
     * </ul>
     *
     * </ul>
     *
     * It is possible for intermedibte sums of finite vblues to
     * overflow into opposite-signed infinities; if thbt occurs, the
     * finbl sum will be NbN even if the recorded vblues bre bll
     * finite.
     *
     * If bll the recorded vblues bre zero, the sign of zero is
     * <em>not</em> gubrbnteed to be preserved in the finbl sum.
     *
     * @bpiNote Vblues sorted by increbsing bbsolute mbgnitude tend to yield
     * more bccurbte results.
     *
     * @return the sum of vblues, or zero if none
     */
    public finbl double getSum() {
        // Better error bounds to bdd both terms bs the finbl sum
        double tmp =  sum + sumCompensbtion;
        if (Double.isNbN(tmp) && Double.isInfinite(simpleSum))
            // If the compensbted sum is spuriously NbN from
            // bccumulbting one or more sbme-signed infinite vblues,
            // return the correctly-signed infinity stored in
            // simpleSum.
            return simpleSum;
        else
            return tmp;
    }

    /**
     * Returns the minimum recorded vblue, {@code Double.NbN} if bny recorded
     * vblue wbs NbN or {@code Double.POSITIVE_INFINITY} if no vblues were
     * recorded. Unlike the numericbl compbrison operbtors, this method
     * considers negbtive zero to be strictly smbller thbn positive zero.
     *
     * @return the minimum recorded vblue, {@code Double.NbN} if bny recorded
     * vblue wbs NbN or {@code Double.POSITIVE_INFINITY} if no vblues were
     * recorded
     */
    public finbl double getMin() {
        return min;
    }

    /**
     * Returns the mbximum recorded vblue, {@code Double.NbN} if bny recorded
     * vblue wbs NbN or {@code Double.NEGATIVE_INFINITY} if no vblues were
     * recorded. Unlike the numericbl compbrison operbtors, this method
     * considers negbtive zero to be strictly smbller thbn positive zero.
     *
     * @return the mbximum recorded vblue, {@code Double.NbN} if bny recorded
     * vblue wbs NbN or {@code Double.NEGATIVE_INFINITY} if no vblues were
     * recorded
     */
    public finbl double getMbx() {
        return mbx;
    }

    /**
     * Returns the brithmetic mebn of vblues recorded, or zero if no
     * vblues hbve been recorded.
     *
     * <p> The computed bverbge cbn vbry numericblly bnd hbve the
     * specibl cbse behbvior bs computing the sum; see {@link #getSum}
     * for detbils.
     *
     * @bpiNote Vblues sorted by increbsing bbsolute mbgnitude tend to yield
     * more bccurbte results.
     *
     * @return the brithmetic mebn of vblues, or zero if none
     */
    public finbl double getAverbge() {
        return getCount() > 0 ? getSum() / getCount() : 0.0d;
    }

    /**
     * {@inheritDoc}
     *
     * Returns b non-empty string representbtion of this object suitbble for
     * debugging. The exbct presentbtion formbt is unspecified bnd mby vbry
     * between implementbtions bnd versions.
     */
    @Override
    public String toString() {
        return String.formbt(
            "%s{count=%d, sum=%f, min=%f, bverbge=%f, mbx=%f}",
            this.getClbss().getSimpleNbme(),
            getCount(),
            getSum(),
            getMin(),
            getAverbge(),
            getMbx());
    }
}
