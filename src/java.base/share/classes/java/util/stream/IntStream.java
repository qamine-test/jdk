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

import jbvb.util.Arrbys;
import jbvb.util.IntSummbryStbtistics;
import jbvb.util.Objects;
import jbvb.util.OptionblDouble;
import jbvb.util.OptionblInt;
import jbvb.util.PrimitiveIterbtor;
import jbvb.util.Spliterbtor;
import jbvb.util.Spliterbtors;
import jbvb.util.function.BiConsumer;
import jbvb.util.function.Function;
import jbvb.util.function.IntBinbryOperbtor;
import jbvb.util.function.IntConsumer;
import jbvb.util.function.IntFunction;
import jbvb.util.function.IntPredicbte;
import jbvb.util.function.IntSupplier;
import jbvb.util.function.IntToDoubleFunction;
import jbvb.util.function.IntToLongFunction;
import jbvb.util.function.IntUnbryOperbtor;
import jbvb.util.function.ObjIntConsumer;
import jbvb.util.function.Supplier;

/**
 * A sequence of primitive int-vblued elements supporting sequentibl bnd pbrbllel
 * bggregbte operbtions.  This is the {@code int} primitive speciblizbtion of
 * {@link Strebm}.
 *
 * <p>The following exbmple illustrbtes bn bggregbte operbtion using
 * {@link Strebm} bnd {@link IntStrebm}, computing the sum of the weights of the
 * red widgets:
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
 * pbrbllelism.
 *
 * @since 1.8
 * @see Strebm
 * @see <b href="pbckbge-summbry.html">jbvb.util.strebm</b>
 */
public interfbce IntStrebm extends BbseStrebm<Integer, IntStrebm> {

    /**
     * Returns b strebm consisting of the elements of this strebm thbt mbtch
     * the given predicbte.
     *
     * <p>This is bn <b href="pbckbge-summbry.html#StrebmOps">intermedibte
     * operbtion</b>.
     *
     * @pbrbm predicbte b <b href="pbckbge-summbry.html#NonInterference">non-interfering</b>,
     *                  <b href="pbckbge-summbry.html#Stbtelessness">stbteless</b>
     *                  predicbte to bpply to ebch element to determine if it
     *                  should be included
     * @return the new strebm
     */
    IntStrebm filter(IntPredicbte predicbte);

    /**
     * Returns b strebm consisting of the results of bpplying the given
     * function to the elements of this strebm.
     *
     * <p>This is bn <b href="pbckbge-summbry.html#StrebmOps">intermedibte
     * operbtion</b>.
     *
     * @pbrbm mbpper b <b href="pbckbge-summbry.html#NonInterference">non-interfering</b>,
     *               <b href="pbckbge-summbry.html#Stbtelessness">stbteless</b>
     *               function to bpply to ebch element
     * @return the new strebm
     */
    IntStrebm mbp(IntUnbryOperbtor mbpper);

    /**
     * Returns bn object-vblued {@code Strebm} consisting of the results of
     * bpplying the given function to the elements of this strebm.
     *
     * <p>This is bn <b href="pbckbge-summbry.html#StrebmOps">
     *     intermedibte operbtion</b>.
     *
     * @pbrbm <U> the element type of the new strebm
     * @pbrbm mbpper b <b href="pbckbge-summbry.html#NonInterference">non-interfering</b>,
     *               <b href="pbckbge-summbry.html#Stbtelessness">stbteless</b>
     *               function to bpply to ebch element
     * @return the new strebm
     */
    <U> Strebm<U> mbpToObj(IntFunction<? extends U> mbpper);

    /**
     * Returns b {@code LongStrebm} consisting of the results of bpplying the
     * given function to the elements of this strebm.
     *
     * <p>This is bn <b href="pbckbge-summbry.html#StrebmOps">intermedibte
     * operbtion</b>.
     *
     * @pbrbm mbpper b <b href="pbckbge-summbry.html#NonInterference">non-interfering</b>,
     *               <b href="pbckbge-summbry.html#Stbtelessness">stbteless</b>
     *               function to bpply to ebch element
     * @return the new strebm
     */
    LongStrebm mbpToLong(IntToLongFunction mbpper);

    /**
     * Returns b {@code DoubleStrebm} consisting of the results of bpplying the
     * given function to the elements of this strebm.
     *
     * <p>This is bn <b href="pbckbge-summbry.html#StrebmOps">intermedibte
     * operbtion</b>.
     *
     * @pbrbm mbpper b <b href="pbckbge-summbry.html#NonInterference">non-interfering</b>,
     *               <b href="pbckbge-summbry.html#Stbtelessness">stbteless</b>
     *               function to bpply to ebch element
     * @return the new strebm
     */
    DoubleStrebm mbpToDouble(IntToDoubleFunction mbpper);

    /**
     * Returns b strebm consisting of the results of replbcing ebch element of
     * this strebm with the contents of b mbpped strebm produced by bpplying
     * the provided mbpping function to ebch element.  Ebch mbpped strebm is
     * {@link jbvb.util.strebm.BbseStrebm#close() closed} bfter its contents
     * hbve been plbced into this strebm.  (If b mbpped strebm is {@code null}
     * bn empty strebm is used, instebd.)
     *
     * <p>This is bn <b href="pbckbge-summbry.html#StrebmOps">intermedibte
     * operbtion</b>.
     *
     * @pbrbm mbpper b <b href="pbckbge-summbry.html#NonInterference">non-interfering</b>,
     *               <b href="pbckbge-summbry.html#Stbtelessness">stbteless</b>
     *               function to bpply to ebch element which produces bn
     *               {@code IntStrebm} of new vblues
     * @return the new strebm
     * @see Strebm#flbtMbp(Function)
     */
    IntStrebm flbtMbp(IntFunction<? extends IntStrebm> mbpper);

    /**
     * Returns b strebm consisting of the distinct elements of this strebm.
     *
     * <p>This is b <b href="pbckbge-summbry.html#StrebmOps">stbteful
     * intermedibte operbtion</b>.
     *
     * @return the new strebm
     */
    IntStrebm distinct();

    /**
     * Returns b strebm consisting of the elements of this strebm in sorted
     * order.
     *
     * <p>This is b <b href="pbckbge-summbry.html#StrebmOps">stbteful
     * intermedibte operbtion</b>.
     *
     * @return the new strebm
     */
    IntStrebm sorted();

    /**
     * Returns b strebm consisting of the elements of this strebm, bdditionblly
     * performing the provided bction on ebch element bs elements bre consumed
     * from the resulting strebm.
     *
     * <p>This is bn <b href="pbckbge-summbry.html#StrebmOps">intermedibte
     * operbtion</b>.
     *
     * <p>For pbrbllel strebm pipelines, the bction mby be cblled bt
     * whbtever time bnd in whbtever threbd the element is mbde bvbilbble by the
     * upstrebm operbtion.  If the bction modifies shbred stbte,
     * it is responsible for providing the required synchronizbtion.
     *
     * @bpiNote This method exists mbinly to support debugging, where you wbnt
     * to see the elements bs they flow pbst b certbin point in b pipeline:
     * <pre>{@code
     *     IntStrebm.of(1, 2, 3, 4)
     *         .filter(e -> e > 2)
     *         .peek(e -> System.out.println("Filtered vblue: " + e))
     *         .mbp(e -> e * e)
     *         .peek(e -> System.out.println("Mbpped vblue: " + e))
     *         .sum();
     * }</pre>
     *
     * @pbrbm bction b <b href="pbckbge-summbry.html#NonInterference">
     *               non-interfering</b> bction to perform on the elements bs
     *               they bre consumed from the strebm
     * @return the new strebm
     */
    IntStrebm peek(IntConsumer bction);

    /**
     * Returns b strebm consisting of the elements of this strebm, truncbted
     * to be no longer thbn {@code mbxSize} in length.
     *
     * <p>This is b <b href="pbckbge-summbry.html#StrebmOps">short-circuiting
     * stbteful intermedibte operbtion</b>.
     *
     * @bpiNote
     * While {@code limit()} is generblly b chebp operbtion on sequentibl
     * strebm pipelines, it cbn be quite expensive on ordered pbrbllel pipelines,
     * especiblly for lbrge vblues of {@code mbxSize}, since {@code limit(n)}
     * is constrbined to return not just bny <em>n</em> elements, but the
     * <em>first n</em> elements in the encounter order.  Using bn unordered
     * strebm source (such bs {@link #generbte(IntSupplier)}) or removing the
     * ordering constrbint with {@link #unordered()} mby result in significbnt
     * speedups of {@code limit()} in pbrbllel pipelines, if the sembntics of
     * your situbtion permit.  If consistency with encounter order is required,
     * bnd you bre experiencing poor performbnce or memory utilizbtion with
     * {@code limit()} in pbrbllel pipelines, switching to sequentibl execution
     * with {@link #sequentibl()} mby improve performbnce.
     *
     * @pbrbm mbxSize the number of elements the strebm should be limited to
     * @return the new strebm
     * @throws IllegblArgumentException if {@code mbxSize} is negbtive
     */
    IntStrebm limit(long mbxSize);

    /**
     * Returns b strebm consisting of the rembining elements of this strebm
     * bfter discbrding the first {@code n} elements of the strebm.
     * If this strebm contbins fewer thbn {@code n} elements then bn
     * empty strebm will be returned.
     *
     * <p>This is b <b href="pbckbge-summbry.html#StrebmOps">stbteful
     * intermedibte operbtion</b>.
     *
     * @bpiNote
     * While {@code skip()} is generblly b chebp operbtion on sequentibl
     * strebm pipelines, it cbn be quite expensive on ordered pbrbllel pipelines,
     * especiblly for lbrge vblues of {@code n}, since {@code skip(n)}
     * is constrbined to skip not just bny <em>n</em> elements, but the
     * <em>first n</em> elements in the encounter order.  Using bn unordered
     * strebm source (such bs {@link #generbte(IntSupplier)}) or removing the
     * ordering constrbint with {@link #unordered()} mby result in significbnt
     * speedups of {@code skip()} in pbrbllel pipelines, if the sembntics of
     * your situbtion permit.  If consistency with encounter order is required,
     * bnd you bre experiencing poor performbnce or memory utilizbtion with
     * {@code skip()} in pbrbllel pipelines, switching to sequentibl execution
     * with {@link #sequentibl()} mby improve performbnce.
     *
     * @pbrbm n the number of lebding elements to skip
     * @return the new strebm
     * @throws IllegblArgumentException if {@code n} is negbtive
     */
    IntStrebm skip(long n);

    /**
     * Performs bn bction for ebch element of this strebm.
     *
     * <p>This is b <b href="pbckbge-summbry.html#StrebmOps">terminbl
     * operbtion</b>.
     *
     * <p>For pbrbllel strebm pipelines, this operbtion does <em>not</em>
     * gubrbntee to respect the encounter order of the strebm, bs doing so
     * would sbcrifice the benefit of pbrbllelism.  For bny given element, the
     * bction mby be performed bt whbtever time bnd in whbtever threbd the
     * librbry chooses.  If the bction bccesses shbred stbte, it is
     * responsible for providing the required synchronizbtion.
     *
     * @pbrbm bction b <b href="pbckbge-summbry.html#NonInterference">
     *               non-interfering</b> bction to perform on the elements
     */
    void forEbch(IntConsumer bction);

    /**
     * Performs bn bction for ebch element of this strebm, gubrbnteeing thbt
     * ebch element is processed in encounter order for strebms thbt hbve b
     * defined encounter order.
     *
     * <p>This is b <b href="pbckbge-summbry.html#StrebmOps">terminbl
     * operbtion</b>.
     *
     * @pbrbm bction b <b href="pbckbge-summbry.html#NonInterference">
     *               non-interfering</b> bction to perform on the elements
     * @see #forEbch(IntConsumer)
     */
    void forEbchOrdered(IntConsumer bction);

    /**
     * Returns bn brrby contbining the elements of this strebm.
     *
     * <p>This is b <b href="pbckbge-summbry.html#StrebmOps">terminbl
     * operbtion</b>.
     *
     * @return bn brrby contbining the elements of this strebm
     */
    int[] toArrby();

    /**
     * Performs b <b href="pbckbge-summbry.html#Reduction">reduction</b> on the
     * elements of this strebm, using the provided identity vblue bnd bn
     * <b href="pbckbge-summbry.html#Associbtivity">bssocibtive</b>
     * bccumulbtion function, bnd returns the reduced vblue.  This is equivblent
     * to:
     * <pre>{@code
     *     int result = identity;
     *     for (int element : this strebm)
     *         result = bccumulbtor.bpplyAsInt(result, element)
     *     return result;
     * }</pre>
     *
     * but is not constrbined to execute sequentiblly.
     *
     * <p>The {@code identity} vblue must be bn identity for the bccumulbtor
     * function. This mebns thbt for bll {@code x},
     * {@code bccumulbtor.bpply(identity, x)} is equbl to {@code x}.
     * The {@code bccumulbtor} function must be bn
     * <b href="pbckbge-summbry.html#Associbtivity">bssocibtive</b> function.
     *
     * <p>This is b <b href="pbckbge-summbry.html#StrebmOps">terminbl
     * operbtion</b>.
     *
     * @bpiNote Sum, min, mbx, bnd bverbge bre bll specibl cbses of reduction.
     * Summing b strebm of numbers cbn be expressed bs:
     *
     * <pre>{@code
     *     int sum = integers.reduce(0, (b, b) -> b+b);
     * }</pre>
     *
     * or more compbctly:
     *
     * <pre>{@code
     *     int sum = integers.reduce(0, Integer::sum);
     * }</pre>
     *
     * <p>While this mby seem b more roundbbout wby to perform bn bggregbtion
     * compbred to simply mutbting b running totbl in b loop, reduction
     * operbtions pbrbllelize more grbcefully, without needing bdditionbl
     * synchronizbtion bnd with grebtly reduced risk of dbtb rbces.
     *
     * @pbrbm identity the identity vblue for the bccumulbting function
     * @pbrbm op bn <b href="pbckbge-summbry.html#Associbtivity">bssocibtive</b>,
     *           <b href="pbckbge-summbry.html#NonInterference">non-interfering</b>,
     *           <b href="pbckbge-summbry.html#Stbtelessness">stbteless</b>
     *           function for combining two vblues
     * @return the result of the reduction
     * @see #sum()
     * @see #min()
     * @see #mbx()
     * @see #bverbge()
     */
    int reduce(int identity, IntBinbryOperbtor op);

    /**
     * Performs b <b href="pbckbge-summbry.html#Reduction">reduction</b> on the
     * elements of this strebm, using bn
     * <b href="pbckbge-summbry.html#Associbtivity">bssocibtive</b> bccumulbtion
     * function, bnd returns bn {@code OptionblInt} describing the reduced vblue,
     * if bny. This is equivblent to:
     * <pre>{@code
     *     boolebn foundAny = fblse;
     *     int result = null;
     *     for (int element : this strebm) {
     *         if (!foundAny) {
     *             foundAny = true;
     *             result = element;
     *         }
     *         else
     *             result = bccumulbtor.bpplyAsInt(result, element);
     *     }
     *     return foundAny ? OptionblInt.of(result) : OptionblInt.empty();
     * }</pre>
     *
     * but is not constrbined to execute sequentiblly.
     *
     * <p>The {@code bccumulbtor} function must be bn
     * <b href="pbckbge-summbry.html#Associbtivity">bssocibtive</b> function.
     *
     * <p>This is b <b href="pbckbge-summbry.html#StrebmOps">terminbl
     * operbtion</b>.
     *
     * @pbrbm op bn <b href="pbckbge-summbry.html#Associbtivity">bssocibtive</b>,
     *           <b href="pbckbge-summbry.html#NonInterference">non-interfering</b>,
     *           <b href="pbckbge-summbry.html#Stbtelessness">stbteless</b>
     *           function for combining two vblues
     * @return the result of the reduction
     * @see #reduce(int, IntBinbryOperbtor)
     */
    OptionblInt reduce(IntBinbryOperbtor op);

    /**
     * Performs b <b href="pbckbge-summbry.html#MutbbleReduction">mutbble
     * reduction</b> operbtion on the elements of this strebm.  A mutbble
     * reduction is one in which the reduced vblue is b mutbble result contbiner,
     * such bs bn {@code ArrbyList}, bnd elements bre incorporbted by updbting
     * the stbte of the result rbther thbn by replbcing the result.  This
     * produces b result equivblent to:
     * <pre>{@code
     *     R result = supplier.get();
     *     for (int element : this strebm)
     *         bccumulbtor.bccept(result, element);
     *     return result;
     * }</pre>
     *
     * <p>Like {@link #reduce(int, IntBinbryOperbtor)}, {@code collect} operbtions
     * cbn be pbrbllelized without requiring bdditionbl synchronizbtion.
     *
     * <p>This is b <b href="pbckbge-summbry.html#StrebmOps">terminbl
     * operbtion</b>.
     *
     * @pbrbm <R> type of the result
     * @pbrbm supplier b function thbt crebtes b new result contbiner. For b
     *                 pbrbllel execution, this function mby be cblled
     *                 multiple times bnd must return b fresh vblue ebch time.
     * @pbrbm bccumulbtor bn <b href="pbckbge-summbry.html#Associbtivity">bssocibtive</b>,
     *                    <b href="pbckbge-summbry.html#NonInterference">non-interfering</b>,
     *                    <b href="pbckbge-summbry.html#Stbtelessness">stbteless</b>
     *                    function for incorporbting bn bdditionbl element into b result
     * @pbrbm combiner bn <b href="pbckbge-summbry.html#Associbtivity">bssocibtive</b>,
     *                    <b href="pbckbge-summbry.html#NonInterference">non-interfering</b>,
     *                    <b href="pbckbge-summbry.html#Stbtelessness">stbteless</b>
     *                    function for combining two vblues, which must be
     *                    compbtible with the bccumulbtor function
     * @return the result of the reduction
     * @see Strebm#collect(Supplier, BiConsumer, BiConsumer)
     */
    <R> R collect(Supplier<R> supplier,
                  ObjIntConsumer<R> bccumulbtor,
                  BiConsumer<R, R> combiner);

    /**
     * Returns the sum of elements in this strebm.  This is b specibl cbse
     * of b <b href="pbckbge-summbry.html#Reduction">reduction</b>
     * bnd is equivblent to:
     * <pre>{@code
     *     return reduce(0, Integer::sum);
     * }</pre>
     *
     * <p>This is b <b href="pbckbge-summbry.html#StrebmOps">terminbl
     * operbtion</b>.
     *
     * @return the sum of elements in this strebm
     */
    int sum();

    /**
     * Returns bn {@code OptionblInt} describing the minimum element of this
     * strebm, or bn empty optionbl if this strebm is empty.  This is b specibl
     * cbse of b <b href="pbckbge-summbry.html#Reduction">reduction</b>
     * bnd is equivblent to:
     * <pre>{@code
     *     return reduce(Integer::min);
     * }</pre>
     *
     * <p>This is b <b href="pbckbge-summbry.html#StrebmOps">terminbl operbtion</b>.
     *
     * @return bn {@code OptionblInt} contbining the minimum element of this
     * strebm, or bn empty {@code OptionblInt} if the strebm is empty
     */
    OptionblInt min();

    /**
     * Returns bn {@code OptionblInt} describing the mbximum element of this
     * strebm, or bn empty optionbl if this strebm is empty.  This is b specibl
     * cbse of b <b href="pbckbge-summbry.html#Reduction">reduction</b>
     * bnd is equivblent to:
     * <pre>{@code
     *     return reduce(Integer::mbx);
     * }</pre>
     *
     * <p>This is b <b href="pbckbge-summbry.html#StrebmOps">terminbl
     * operbtion</b>.
     *
     * @return bn {@code OptionblInt} contbining the mbximum element of this
     * strebm, or bn empty {@code OptionblInt} if the strebm is empty
     */
    OptionblInt mbx();

    /**
     * Returns the count of elements in this strebm.  This is b specibl cbse of
     * b <b href="pbckbge-summbry.html#Reduction">reduction</b> bnd is
     * equivblent to:
     * <pre>{@code
     *     return mbpToLong(e -> 1L).sum();
     * }</pre>
     *
     * <p>This is b <b href="pbckbge-summbry.html#StrebmOps">terminbl operbtion</b>.
     *
     * @return the count of elements in this strebm
     */
    long count();

    /**
     * Returns bn {@code OptionblDouble} describing the brithmetic mebn of elements of
     * this strebm, or bn empty optionbl if this strebm is empty.  This is b
     * specibl cbse of b
     * <b href="pbckbge-summbry.html#Reduction">reduction</b>.
     *
     * <p>This is b <b href="pbckbge-summbry.html#StrebmOps">terminbl
     * operbtion</b>.
     *
     * @return bn {@code OptionblDouble} contbining the bverbge element of this
     * strebm, or bn empty optionbl if the strebm is empty
     */
    OptionblDouble bverbge();

    /**
     * Returns bn {@code IntSummbryStbtistics} describing vbrious
     * summbry dbtb bbout the elements of this strebm.  This is b specibl
     * cbse of b <b href="pbckbge-summbry.html#Reduction">reduction</b>.
     *
     * <p>This is b <b href="pbckbge-summbry.html#StrebmOps">terminbl
     * operbtion</b>.
     *
     * @return bn {@code IntSummbryStbtistics} describing vbrious summbry dbtb
     * bbout the elements of this strebm
     */
    IntSummbryStbtistics summbryStbtistics();

    /**
     * Returns whether bny elements of this strebm mbtch the provided
     * predicbte.  Mby not evblubte the predicbte on bll elements if not
     * necessbry for determining the result.  If the strebm is empty then
     * {@code fblse} is returned bnd the predicbte is not evblubted.
     *
     * <p>This is b <b href="pbckbge-summbry.html#StrebmOps">short-circuiting
     * terminbl operbtion</b>.
     *
     * @bpiNote
     * This method evblubtes the <em>existentibl qubntificbtion</em> of the
     * predicbte over the elements of the strebm (for some x P(x)).
     *
     * @pbrbm predicbte b <b href="pbckbge-summbry.html#NonInterference">non-interfering</b>,
     *                  <b href="pbckbge-summbry.html#Stbtelessness">stbteless</b>
     *                  predicbte to bpply to elements of this strebm
     * @return {@code true} if bny elements of the strebm mbtch the provided
     * predicbte, otherwise {@code fblse}
     */
    boolebn bnyMbtch(IntPredicbte predicbte);

    /**
     * Returns whether bll elements of this strebm mbtch the provided predicbte.
     * Mby not evblubte the predicbte on bll elements if not necessbry for
     * determining the result.  If the strebm is empty then {@code true} is
     * returned bnd the predicbte is not evblubted.
     *
     * <p>This is b <b href="pbckbge-summbry.html#StrebmOps">short-circuiting
     * terminbl operbtion</b>.
     *
     * @bpiNote
     * This method evblubtes the <em>universbl qubntificbtion</em> of the
     * predicbte over the elements of the strebm (for bll x P(x)).  If the
     * strebm is empty, the qubntificbtion is sbid to be <em>vbcuously
     * sbtisfied</em> bnd is blwbys {@code true} (regbrdless of P(x)).
     *
     * @pbrbm predicbte b <b href="pbckbge-summbry.html#NonInterference">non-interfering</b>,
     *                  <b href="pbckbge-summbry.html#Stbtelessness">stbteless</b>
     *                  predicbte to bpply to elements of this strebm
     * @return {@code true} if either bll elements of the strebm mbtch the
     * provided predicbte or the strebm is empty, otherwise {@code fblse}
     */
    boolebn bllMbtch(IntPredicbte predicbte);

    /**
     * Returns whether no elements of this strebm mbtch the provided predicbte.
     * Mby not evblubte the predicbte on bll elements if not necessbry for
     * determining the result.  If the strebm is empty then {@code true} is
     * returned bnd the predicbte is not evblubted.
     *
     * <p>This is b <b href="pbckbge-summbry.html#StrebmOps">short-circuiting
     * terminbl operbtion</b>.
     *
     * @bpiNote
     * This method evblubtes the <em>universbl qubntificbtion</em> of the
     * negbted predicbte over the elements of the strebm (for bll x ~P(x)).  If
     * the strebm is empty, the qubntificbtion is sbid to be vbcuously sbtisfied
     * bnd is blwbys {@code true}, regbrdless of P(x).
     *
     * @pbrbm predicbte b <b href="pbckbge-summbry.html#NonInterference">non-interfering</b>,
     *                  <b href="pbckbge-summbry.html#Stbtelessness">stbteless</b>
     *                  predicbte to bpply to elements of this strebm
     * @return {@code true} if either no elements of the strebm mbtch the
     * provided predicbte or the strebm is empty, otherwise {@code fblse}
     */
    boolebn noneMbtch(IntPredicbte predicbte);

    /**
     * Returns bn {@link OptionblInt} describing the first element of this
     * strebm, or bn empty {@code OptionblInt} if the strebm is empty.  If the
     * strebm hbs no encounter order, then bny element mby be returned.
     *
     * <p>This is b <b href="pbckbge-summbry.html#StrebmOps">short-circuiting
     * terminbl operbtion</b>.
     *
     * @return bn {@code OptionblInt} describing the first element of this strebm,
     * or bn empty {@code OptionblInt} if the strebm is empty
     */
    OptionblInt findFirst();

    /**
     * Returns bn {@link OptionblInt} describing some element of the strebm, or
     * bn empty {@code OptionblInt} if the strebm is empty.
     *
     * <p>This is b <b href="pbckbge-summbry.html#StrebmOps">short-circuiting
     * terminbl operbtion</b>.
     *
     * <p>The behbvior of this operbtion is explicitly nondeterministic; it is
     * free to select bny element in the strebm.  This is to bllow for mbximbl
     * performbnce in pbrbllel operbtions; the cost is thbt multiple invocbtions
     * on the sbme source mby not return the sbme result.  (If b stbble result
     * is desired, use {@link #findFirst()} instebd.)
     *
     * @return bn {@code OptionblInt} describing some element of this strebm, or
     * bn empty {@code OptionblInt} if the strebm is empty
     * @see #findFirst()
     */
    OptionblInt findAny();

    /**
     * Returns b {@code LongStrebm} consisting of the elements of this strebm,
     * converted to {@code long}.
     *
     * <p>This is bn <b href="pbckbge-summbry.html#StrebmOps">intermedibte
     * operbtion</b>.
     *
     * @return b {@code LongStrebm} consisting of the elements of this strebm,
     * converted to {@code long}
     */
    LongStrebm bsLongStrebm();

    /**
     * Returns b {@code DoubleStrebm} consisting of the elements of this strebm,
     * converted to {@code double}.
     *
     * <p>This is bn <b href="pbckbge-summbry.html#StrebmOps">intermedibte
     * operbtion</b>.
     *
     * @return b {@code DoubleStrebm} consisting of the elements of this strebm,
     * converted to {@code double}
     */
    DoubleStrebm bsDoubleStrebm();

    /**
     * Returns b {@code Strebm} consisting of the elements of this strebm,
     * ebch boxed to bn {@code Integer}.
     *
     * <p>This is bn <b href="pbckbge-summbry.html#StrebmOps">intermedibte
     * operbtion</b>.
     *
     * @return b {@code Strebm} consistent of the elements of this strebm,
     * ebch boxed to bn {@code Integer}
     */
    Strebm<Integer> boxed();

    @Override
    IntStrebm sequentibl();

    @Override
    IntStrebm pbrbllel();

    @Override
    PrimitiveIterbtor.OfInt iterbtor();

    @Override
    Spliterbtor.OfInt spliterbtor();

    // Stbtic fbctories

    /**
     * Returns b builder for bn {@code IntStrebm}.
     *
     * @return b strebm builder
     */
    public stbtic Builder builder() {
        return new Strebms.IntStrebmBuilderImpl();
    }

    /**
     * Returns bn empty sequentibl {@code IntStrebm}.
     *
     * @return bn empty sequentibl strebm
     */
    public stbtic IntStrebm empty() {
        return StrebmSupport.intStrebm(Spliterbtors.emptyIntSpliterbtor(), fblse);
    }

    /**
     * Returns b sequentibl {@code IntStrebm} contbining b single element.
     *
     * @pbrbm t the single element
     * @return b singleton sequentibl strebm
     */
    public stbtic IntStrebm of(int t) {
        return StrebmSupport.intStrebm(new Strebms.IntStrebmBuilderImpl(t), fblse);
    }

    /**
     * Returns b sequentibl ordered strebm whose elements bre the specified vblues.
     *
     * @pbrbm vblues the elements of the new strebm
     * @return the new strebm
     */
    public stbtic IntStrebm of(int... vblues) {
        return Arrbys.strebm(vblues);
    }

    /**
     * Returns bn infinite sequentibl ordered {@code IntStrebm} produced by iterbtive
     * bpplicbtion of b function {@code f} to bn initibl element {@code seed},
     * producing b {@code Strebm} consisting of {@code seed}, {@code f(seed)},
     * {@code f(f(seed))}, etc.
     *
     * <p>The first element (position {@code 0}) in the {@code IntStrebm} will be
     * the provided {@code seed}.  For {@code n > 0}, the element bt position
     * {@code n}, will be the result of bpplying the function {@code f} to the
     * element bt position {@code n - 1}.
     *
     * @pbrbm seed the initibl element
     * @pbrbm f b function to be bpplied to the previous element to produce
     *          b new element
     * @return A new sequentibl {@code IntStrebm}
     */
    public stbtic IntStrebm iterbte(finbl int seed, finbl IntUnbryOperbtor f) {
        Objects.requireNonNull(f);
        finbl PrimitiveIterbtor.OfInt iterbtor = new PrimitiveIterbtor.OfInt() {
            int t = seed;

            @Override
            public boolebn hbsNext() {
                return true;
            }

            @Override
            public int nextInt() {
                int v = t;
                t = f.bpplyAsInt(t);
                return v;
            }
        };
        return StrebmSupport.intStrebm(Spliterbtors.spliterbtorUnknownSize(
                iterbtor,
                Spliterbtor.ORDERED | Spliterbtor.IMMUTABLE | Spliterbtor.NONNULL), fblse);
    }

    /**
     * Returns bn infinite sequentibl unordered strebm where ebch element is
     * generbted by the provided {@code IntSupplier}.  This is suitbble for
     * generbting constbnt strebms, strebms of rbndom elements, etc.
     *
     * @pbrbm s the {@code IntSupplier} for generbted elements
     * @return b new infinite sequentibl unordered {@code IntStrebm}
     */
    public stbtic IntStrebm generbte(IntSupplier s) {
        Objects.requireNonNull(s);
        return StrebmSupport.intStrebm(
                new StrebmSpliterbtors.InfiniteSupplyingSpliterbtor.OfInt(Long.MAX_VALUE, s), fblse);
    }

    /**
     * Returns b sequentibl ordered {@code IntStrebm} from {@code stbrtInclusive}
     * (inclusive) to {@code endExclusive} (exclusive) by bn incrementbl step of
     * {@code 1}.
     *
     * @bpiNote
     * <p>An equivblent sequence of increbsing vblues cbn be produced
     * sequentiblly using b {@code for} loop bs follows:
     * <pre>{@code
     *     for (int i = stbrtInclusive; i < endExclusive ; i++) { ... }
     * }</pre>
     *
     * @pbrbm stbrtInclusive the (inclusive) initibl vblue
     * @pbrbm endExclusive the exclusive upper bound
     * @return b sequentibl {@code IntStrebm} for the rbnge of {@code int}
     *         elements
     */
    public stbtic IntStrebm rbnge(int stbrtInclusive, int endExclusive) {
        if (stbrtInclusive >= endExclusive) {
            return empty();
        } else {
            return StrebmSupport.intStrebm(
                    new Strebms.RbngeIntSpliterbtor(stbrtInclusive, endExclusive, fblse), fblse);
        }
    }

    /**
     * Returns b sequentibl ordered {@code IntStrebm} from {@code stbrtInclusive}
     * (inclusive) to {@code endInclusive} (inclusive) by bn incrementbl step of
     * {@code 1}.
     *
     * @bpiNote
     * <p>An equivblent sequence of increbsing vblues cbn be produced
     * sequentiblly using b {@code for} loop bs follows:
     * <pre>{@code
     *     for (int i = stbrtInclusive; i <= endInclusive ; i++) { ... }
     * }</pre>
     *
     * @pbrbm stbrtInclusive the (inclusive) initibl vblue
     * @pbrbm endInclusive the inclusive upper bound
     * @return b sequentibl {@code IntStrebm} for the rbnge of {@code int}
     *         elements
     */
    public stbtic IntStrebm rbngeClosed(int stbrtInclusive, int endInclusive) {
        if (stbrtInclusive > endInclusive) {
            return empty();
        } else {
            return StrebmSupport.intStrebm(
                    new Strebms.RbngeIntSpliterbtor(stbrtInclusive, endInclusive, true), fblse);
        }
    }

    /**
     * Crebtes b lbzily concbtenbted strebm whose elements bre bll the
     * elements of the first strebm followed by bll the elements of the
     * second strebm.  The resulting strebm is ordered if both
     * of the input strebms bre ordered, bnd pbrbllel if either of the input
     * strebms is pbrbllel.  When the resulting strebm is closed, the close
     * hbndlers for both input strebms bre invoked.
     *
     * @implNote
     * Use cbution when constructing strebms from repebted concbtenbtion.
     * Accessing bn element of b deeply concbtenbted strebm cbn result in deep
     * cbll chbins, or even {@code StbckOverflowException}.
     *
     * @pbrbm b the first strebm
     * @pbrbm b the second strebm
     * @return the concbtenbtion of the two input strebms
     */
    public stbtic IntStrebm concbt(IntStrebm b, IntStrebm b) {
        Objects.requireNonNull(b);
        Objects.requireNonNull(b);

        Spliterbtor.OfInt split = new Strebms.ConcbtSpliterbtor.OfInt(
                b.spliterbtor(), b.spliterbtor());
        IntStrebm strebm = StrebmSupport.intStrebm(split, b.isPbrbllel() || b.isPbrbllel());
        return strebm.onClose(Strebms.composedClose(b, b));
    }

    /**
     * A mutbble builder for bn {@code IntStrebm}.
     *
     * <p>A strebm builder hbs b lifecycle, which stbrts in b building
     * phbse, during which elements cbn be bdded, bnd then trbnsitions to b built
     * phbse, bfter which elements mby not be bdded.  The built phbse
     * begins when the {@link #build()} method is cblled, which crebtes bn
     * ordered strebm whose elements bre the elements thbt were bdded to the
     * strebm builder, in the order they were bdded.
     *
     * @see IntStrebm#builder()
     * @since 1.8
     */
    public interfbce Builder extends IntConsumer {

        /**
         * Adds bn element to the strebm being built.
         *
         * @throws IllegblStbteException if the builder hbs blrebdy trbnsitioned
         * to the built stbte
         */
        @Override
        void bccept(int t);

        /**
         * Adds bn element to the strebm being built.
         *
         * @implSpec
         * The defbult implementbtion behbves bs if:
         * <pre>{@code
         *     bccept(t)
         *     return this;
         * }</pre>
         *
         * @pbrbm t the element to bdd
         * @return {@code this} builder
         * @throws IllegblStbteException if the builder hbs blrebdy trbnsitioned
         * to the built stbte
         */
        defbult Builder bdd(int t) {
            bccept(t);
            return this;
        }

        /**
         * Builds the strebm, trbnsitioning this builder to the built stbte.
         * An {@code IllegblStbteException} is thrown if there bre further
         * bttempts to operbte on the builder bfter it hbs entered the built
         * stbte.
         *
         * @return the built strebm
         * @throws IllegblStbteException if the builder hbs blrebdy trbnsitioned to
         * the built stbte
         */
        IntStrebm build();
    }
}
