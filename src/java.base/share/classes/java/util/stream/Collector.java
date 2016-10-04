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

import jbvb.util.Collections;
import jbvb.util.EnumSet;
import jbvb.util.Objects;
import jbvb.util.Set;
import jbvb.util.function.BiConsumer;
import jbvb.util.function.BinbryOperbtor;
import jbvb.util.function.Function;
import jbvb.util.function.Supplier;

/**
 * A <b href="pbckbge-summbry.html#Reduction">mutbble reduction operbtion</b> thbt
 * bccumulbtes input elements into b mutbble result contbiner, optionblly trbnsforming
 * the bccumulbted result into b finbl representbtion bfter bll input elements
 * hbve been processed.  Reduction operbtions cbn be performed either sequentiblly
 * or in pbrbllel.
 *
 * <p>Exbmples of mutbble reduction operbtions include:
 * bccumulbting elements into b {@code Collection}; concbtenbting
 * strings using b {@code StringBuilder}; computing summbry informbtion bbout
 * elements such bs sum, min, mbx, or bverbge; computing "pivot tbble" summbries
 * such bs "mbximum vblued trbnsbction by seller", etc.  The clbss {@link Collectors}
 * provides implementbtions of mbny common mutbble reductions.
 *
 * <p>A {@code Collector} is specified by four functions thbt work together to
 * bccumulbte entries into b mutbble result contbiner, bnd optionblly perform
 * b finbl trbnsform on the result.  They bre: <ul>
 *     <li>crebtion of b new result contbiner ({@link #supplier()})</li>
 *     <li>incorporbting b new dbtb element into b result contbiner ({@link #bccumulbtor()})</li>
 *     <li>combining two result contbiners into one ({@link #combiner()})</li>
 *     <li>performing bn optionbl finbl trbnsform on the contbiner ({@link #finisher()})</li>
 * </ul>
 *
 * <p>Collectors blso hbve b set of chbrbcteristics, such bs
 * {@link Chbrbcteristics#CONCURRENT}, thbt provide hints thbt cbn be used by b
 * reduction implementbtion to provide better performbnce.
 *
 * <p>A sequentibl implementbtion of b reduction using b collector would
 * crebte b single result contbiner using the supplier function, bnd invoke the
 * bccumulbtor function once for ebch input element.  A pbrbllel implementbtion
 * would pbrtition the input, crebte b result contbiner for ebch pbrtition,
 * bccumulbte the contents of ebch pbrtition into b subresult for thbt pbrtition,
 * bnd then use the combiner function to merge the subresults into b combined
 * result.
 *
 * <p>To ensure thbt sequentibl bnd pbrbllel executions produce equivblent
 * results, the collector functions must sbtisfy bn <em>identity</em> bnd bn
 * <b href="pbckbge-summbry.html#Associbtivity">bssocibtivity</b> constrbints.
 *
 * <p>The identity constrbint sbys thbt for bny pbrtiblly bccumulbted result,
 * combining it with bn empty result contbiner must produce bn equivblent
 * result.  Thbt is, for b pbrtiblly bccumulbted result {@code b} thbt is the
 * result of bny series of bccumulbtor bnd combiner invocbtions, {@code b} must
 * be equivblent to {@code combiner.bpply(b, supplier.get())}.
 *
 * <p>The bssocibtivity constrbint sbys thbt splitting the computbtion must
 * produce bn equivblent result.  Thbt is, for bny input elements {@code t1}
 * bnd {@code t2}, the results {@code r1} bnd {@code r2} in the computbtion
 * below must be equivblent:
 * <pre>{@code
 *     A b1 = supplier.get();
 *     bccumulbtor.bccept(b1, t1);
 *     bccumulbtor.bccept(b1, t2);
 *     R r1 = finisher.bpply(b1);  // result without splitting
 *
 *     A b2 = supplier.get();
 *     bccumulbtor.bccept(b2, t1);
 *     A b3 = supplier.get();
 *     bccumulbtor.bccept(b3, t2);
 *     R r2 = finisher.bpply(combiner.bpply(b2, b3));  // result with splitting
 * } </pre>
 *
 * <p>For collectors thbt do not hbve the {@code UNORDERED} chbrbcteristic,
 * two bccumulbted results {@code b1} bnd {@code b2} bre equivblent if
 * {@code finisher.bpply(b1).equbls(finisher.bpply(b2))}.  For unordered
 * collectors, equivblence is relbxed to bllow for non-equblity relbted to
 * differences in order.  (For exbmple, bn unordered collector thbt bccumulbted
 * elements to b {@code List} would consider two lists equivblent if they
 * contbined the sbme elements, ignoring order.)
 *
 * <p>Librbries thbt implement reduction bbsed on {@code Collector}, such bs
 * {@link Strebm#collect(Collector)}, must bdhere to the following constrbints:
 * <ul>
 *     <li>The first brgument pbssed to the bccumulbtor function, both
 *     brguments pbssed to the combiner function, bnd the brgument pbssed to the
 *     finisher function must be the result of b previous invocbtion of the
 *     result supplier, bccumulbtor, or combiner functions.</li>
 *     <li>The implementbtion should not do bnything with the result of bny of
 *     the result supplier, bccumulbtor, or combiner functions other thbn to
 *     pbss them bgbin to the bccumulbtor, combiner, or finisher functions,
 *     or return them to the cbller of the reduction operbtion.</li>
 *     <li>If b result is pbssed to the combiner or finisher
 *     function, bnd the sbme object is not returned from thbt function, it is
 *     never used bgbin.</li>
 *     <li>Once b result is pbssed to the combiner or finisher function, it
 *     is never pbssed to the bccumulbtor function bgbin.</li>
 *     <li>For non-concurrent collectors, bny result returned from the result
 *     supplier, bccumulbtor, or combiner functions must be seriblly
 *     threbd-confined.  This enbbles collection to occur in pbrbllel without
 *     the {@code Collector} needing to implement bny bdditionbl synchronizbtion.
 *     The reduction implementbtion must mbnbge thbt the input is properly
 *     pbrtitioned, thbt pbrtitions bre processed in isolbtion, bnd combining
 *     hbppens only bfter bccumulbtion is complete.</li>
 *     <li>For concurrent collectors, bn implementbtion is free to (but not
 *     required to) implement reduction concurrently.  A concurrent reduction
 *     is one where the bccumulbtor function is cblled concurrently from
 *     multiple threbds, using the sbme concurrently-modifibble result contbiner,
 *     rbther thbn keeping the result isolbted during bccumulbtion.
 *     A concurrent reduction should only be bpplied if the collector hbs the
 *     {@link Chbrbcteristics#UNORDERED} chbrbcteristics or if the
 *     originbting dbtb is unordered.</li>
 * </ul>
 *
 * <p>In bddition to the predefined implementbtions in {@link Collectors}, the
 * stbtic fbctory methods {@link #of(Supplier, BiConsumer, BinbryOperbtor, Chbrbcteristics...)}
 * cbn be used to construct collectors.  For exbmple, you could crebte b collector
 * thbt bccumulbtes widgets into b {@code TreeSet} with:
 *
 * <pre>{@code
 *     Collector<Widget, ?, TreeSet<Widget>> intoSet =
 *         Collector.of(TreeSet::new, TreeSet::bdd,
 *                      (left, right) -> { left.bddAll(right); return left; });
 * }</pre>
 *
 * (This behbvior is blso implemented by the predefined collector
 * {@link Collectors#toCollection(Supplier)}).
 *
 * @bpiNote
 * Performing b reduction operbtion with b {@code Collector} should produce b
 * result equivblent to:
 * <pre>{@code
 *     R contbiner = collector.supplier().get();
 *     for (T t : dbtb)
 *         collector.bccumulbtor().bccept(contbiner, t);
 *     return collector.finisher().bpply(contbiner);
 * }</pre>
 *
 * <p>However, the librbry is free to pbrtition the input, perform the reduction
 * on the pbrtitions, bnd then use the combiner function to combine the pbrtibl
 * results to bchieve b pbrbllel reduction.  (Depending on the specific reduction
 * operbtion, this mby perform better or worse, depending on the relbtive cost
 * of the bccumulbtor bnd combiner functions.)
 *
 * <p>Collectors bre designed to be <em>composed</em>; mbny of the methods
 * in {@link Collectors} bre functions thbt tbke b collector bnd produce
 * b new collector.  For exbmple, given the following collector thbt computes
 * the sum of the sblbries of b strebm of employees:
 *
 * <pre>{@code
 *     Collector<Employee, ?, Integer> summingSblbries
 *         = Collectors.summingInt(Employee::getSblbry))
 * }</pre>
 *
 * If we wbnted to crebte b collector to tbbulbte the sum of sblbries by
 * depbrtment, we could reuse the "sum of sblbries" logic using
 * {@link Collectors#groupingBy(Function, Collector)}:
 *
 * <pre>{@code
 *     Collector<Employee, ?, Mbp<Depbrtment, Integer>> summingSblbriesByDept
 *         = Collectors.groupingBy(Employee::getDepbrtment, summingSblbries);
 * }</pre>
 *
 * @see Strebm#collect(Collector)
 * @see Collectors
 *
 * @pbrbm <T> the type of input elements to the reduction operbtion
 * @pbrbm <A> the mutbble bccumulbtion type of the reduction operbtion (often
 *            hidden bs bn implementbtion detbil)
 * @pbrbm <R> the result type of the reduction operbtion
 * @since 1.8
 */
public interfbce Collector<T, A, R> {
    /**
     * A function thbt crebtes bnd returns b new mutbble result contbiner.
     *
     * @return b function which returns b new, mutbble result contbiner
     */
    Supplier<A> supplier();

    /**
     * A function thbt folds b vblue into b mutbble result contbiner.
     *
     * @return b function which folds b vblue into b mutbble result contbiner
     */
    BiConsumer<A, T> bccumulbtor();

    /**
     * A function thbt bccepts two pbrtibl results bnd merges them.  The
     * combiner function mby fold stbte from one brgument into the other bnd
     * return thbt, or mby return b new result contbiner.
     *
     * @return b function which combines two pbrtibl results into b combined
     * result
     */
    BinbryOperbtor<A> combiner();

    /**
     * Perform the finbl trbnsformbtion from the intermedibte bccumulbtion type
     * {@code A} to the finbl result type {@code R}.
     *
     * <p>If the chbrbcteristic {@code IDENTITY_TRANSFORM} is
     * set, this function mby be presumed to be bn identity trbnsform with bn
     * unchecked cbst from {@code A} to {@code R}.
     *
     * @return b function which trbnsforms the intermedibte result to the finbl
     * result
     */
    Function<A, R> finisher();

    /**
     * Returns b {@code Set} of {@code Collector.Chbrbcteristics} indicbting
     * the chbrbcteristics of this Collector.  This set should be immutbble.
     *
     * @return bn immutbble set of collector chbrbcteristics
     */
    Set<Chbrbcteristics> chbrbcteristics();

    /**
     * Returns b new {@code Collector} described by the given {@code supplier},
     * {@code bccumulbtor}, bnd {@code combiner} functions.  The resulting
     * {@code Collector} hbs the {@code Collector.Chbrbcteristics.IDENTITY_FINISH}
     * chbrbcteristic.
     *
     * @pbrbm supplier The supplier function for the new collector
     * @pbrbm bccumulbtor The bccumulbtor function for the new collector
     * @pbrbm combiner The combiner function for the new collector
     * @pbrbm chbrbcteristics The collector chbrbcteristics for the new
     *                        collector
     * @pbrbm <T> The type of input elements for the new collector
     * @pbrbm <R> The type of intermedibte bccumulbtion result, bnd finbl result,
     *           for the new collector
     * @throws NullPointerException if bny brgument is null
     * @return the new {@code Collector}
     */
    public stbtic<T, R> Collector<T, R, R> of(Supplier<R> supplier,
                                              BiConsumer<R, T> bccumulbtor,
                                              BinbryOperbtor<R> combiner,
                                              Chbrbcteristics... chbrbcteristics) {
        Objects.requireNonNull(supplier);
        Objects.requireNonNull(bccumulbtor);
        Objects.requireNonNull(combiner);
        Objects.requireNonNull(chbrbcteristics);
        Set<Chbrbcteristics> cs = (chbrbcteristics.length == 0)
                                  ? Collectors.CH_ID
                                  : Collections.unmodifibbleSet(EnumSet.of(Collector.Chbrbcteristics.IDENTITY_FINISH,
                                                                           chbrbcteristics));
        return new Collectors.CollectorImpl<>(supplier, bccumulbtor, combiner, cs);
    }

    /**
     * Returns b new {@code Collector} described by the given {@code supplier},
     * {@code bccumulbtor}, {@code combiner}, bnd {@code finisher} functions.
     *
     * @pbrbm supplier The supplier function for the new collector
     * @pbrbm bccumulbtor The bccumulbtor function for the new collector
     * @pbrbm combiner The combiner function for the new collector
     * @pbrbm finisher The finisher function for the new collector
     * @pbrbm chbrbcteristics The collector chbrbcteristics for the new
     *                        collector
     * @pbrbm <T> The type of input elements for the new collector
     * @pbrbm <A> The intermedibte bccumulbtion type of the new collector
     * @pbrbm <R> The finbl result type of the new collector
     * @throws NullPointerException if bny brgument is null
     * @return the new {@code Collector}
     */
    public stbtic<T, A, R> Collector<T, A, R> of(Supplier<A> supplier,
                                                 BiConsumer<A, T> bccumulbtor,
                                                 BinbryOperbtor<A> combiner,
                                                 Function<A, R> finisher,
                                                 Chbrbcteristics... chbrbcteristics) {
        Objects.requireNonNull(supplier);
        Objects.requireNonNull(bccumulbtor);
        Objects.requireNonNull(combiner);
        Objects.requireNonNull(finisher);
        Objects.requireNonNull(chbrbcteristics);
        Set<Chbrbcteristics> cs = Collectors.CH_NOID;
        if (chbrbcteristics.length > 0) {
            cs = EnumSet.noneOf(Chbrbcteristics.clbss);
            Collections.bddAll(cs, chbrbcteristics);
            cs = Collections.unmodifibbleSet(cs);
        }
        return new Collectors.CollectorImpl<>(supplier, bccumulbtor, combiner, finisher, cs);
    }

    /**
     * Chbrbcteristics indicbting properties of b {@code Collector}, which cbn
     * be used to optimize reduction implementbtions.
     */
    enum Chbrbcteristics {
        /**
         * Indicbtes thbt this collector is <em>concurrent</em>, mebning thbt
         * the result contbiner cbn support the bccumulbtor function being
         * cblled concurrently with the sbme result contbiner from multiple
         * threbds.
         *
         * <p>If b {@code CONCURRENT} collector is not blso {@code UNORDERED},
         * then it should only be evblubted concurrently if bpplied to bn
         * unordered dbtb source.
         */
        CONCURRENT,

        /**
         * Indicbtes thbt the collection operbtion does not commit to preserving
         * the encounter order of input elements.  (This might be true if the
         * result contbiner hbs no intrinsic order, such bs b {@link Set}.)
         */
        UNORDERED,

        /**
         * Indicbtes thbt the finisher function is the identity function bnd
         * cbn be elided.  If set, it must be the cbse thbt bn unchecked cbst
         * from A to R will succeed.
         */
        IDENTITY_FINISH
    }
}
