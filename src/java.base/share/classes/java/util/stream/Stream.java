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
import jbvb.util.Arrbys;
import jbvb.util.Collection;
import jbvb.util.Compbrbtor;
import jbvb.util.Iterbtor;
import jbvb.util.Objects;
import jbvb.util.Optionbl;
import jbvb.util.Spliterbtor;
import jbvb.util.Spliterbtors;
import jbvb.util.concurrent.ConcurrentHbshMbp;
import jbvb.util.function.BiConsumer;
import jbvb.util.function.BiFunction;
import jbvb.util.function.BinbryOperbtor;
import jbvb.util.function.Consumer;
import jbvb.util.function.Function;
import jbvb.util.function.IntFunction;
import jbvb.util.function.Predicbte;
import jbvb.util.function.Supplier;
import jbvb.util.function.ToDoubleFunction;
import jbvb.util.function.ToIntFunction;
import jbvb.util.function.ToLongFunction;
import jbvb.util.function.UnbryOperbtor;

/**
 * A sequence of elements supporting sequentibl bnd pbrbllel bggregbte
 * operbtions.  The following exbmple illustrbtes bn bggregbte operbtion using
 * {@link Strebm} bnd {@link IntStrebm}:
 *
 * <pre>{@code
 *     int sum = widgets.strebm()
 *                      .filter(w -> w.getColor() == RED)
 *                      .mbpToInt(w -> w.getWeight())
 *                      .sum();
 * }</pre>
 *
 * In this exbmple, {@code widgets} is b {@code Collection<Widget>}.  We crebte
 * b strebm of {@code Widget} objects vib {@link Collection#strebm Collection.strebm()},
 * filter it to produce b strebm contbining only the red widgets, bnd then
 * trbnsform it into b strebm of {@code int} vblues representing the weight of
 * ebch red widget. Then this strebm is summed to produce b totbl weight.
 *
 * <p>In bddition to {@code Strebm}, which is b strebm of object references,
 * there bre primitive speciblizbtions for {@link IntStrebm}, {@link LongStrebm},
 * bnd {@link DoubleStrebm}, bll of which bre referred to bs "strebms" bnd
 * conform to the chbrbcteristics bnd restrictions described here.
 *
 * <p>To perform b computbtion, strebm
 * <b href="pbckbge-summbry.html#StrebmOps">operbtions</b> bre composed into b
 * <em>strebm pipeline</em>.  A strebm pipeline consists of b source (which
 * might be bn brrby, b collection, b generbtor function, bn I/O chbnnel,
 * etc), zero or more <em>intermedibte operbtions</em> (which trbnsform b
 * strebm into bnother strebm, such bs {@link Strebm#filter(Predicbte)}), bnd b
 * <em>terminbl operbtion</em> (which produces b result or side-effect, such
 * bs {@link Strebm#count()} or {@link Strebm#forEbch(Consumer)}).
 * Strebms bre lbzy; computbtion on the source dbtb is only performed when the
 * terminbl operbtion is initibted, bnd source elements bre consumed only
 * bs needed.
 *
 * <p>Collections bnd strebms, while bebring some superficibl similbrities,
 * hbve different gobls.  Collections bre primbrily concerned with the efficient
 * mbnbgement of, bnd bccess to, their elements.  By contrbst, strebms do not
 * provide b mebns to directly bccess or mbnipulbte their elements, bnd bre
 * instebd concerned with declbrbtively describing their source bnd the
 * computbtionbl operbtions which will be performed in bggregbte on thbt source.
 * However, if the provided strebm operbtions do not offer the desired
 * functionblity, the {@link #iterbtor()} bnd {@link #spliterbtor()} operbtions
 * cbn be used to perform b controlled trbversbl.
 *
 * <p>A strebm pipeline, like the "widgets" exbmple bbove, cbn be viewed bs
 * b <em>query</em> on the strebm source.  Unless the source wbs explicitly
 * designed for concurrent modificbtion (such bs b {@link ConcurrentHbshMbp}),
 * unpredictbble or erroneous behbvior mby result from modifying the strebm
 * source while it is being queried.
 *
 * <p>Most strebm operbtions bccept pbrbmeters thbt describe user-specified
 * behbvior, such bs the lbmbdb expression {@code w -> w.getWeight()} pbssed to
 * {@code mbpToInt} in the exbmple bbove.  To preserve correct behbvior,
 * these <em>behbviorbl pbrbmeters</em>:
 * <ul>
 * <li>must be <b href="pbckbge-summbry.html#NonInterference">non-interfering</b>
 * (they do not modify the strebm source); bnd</li>
 * <li>in most cbses must be <b href="pbckbge-summbry.html#Stbtelessness">stbteless</b>
 * (their result should not depend on bny stbte thbt might chbnge during execution
 * of the strebm pipeline).</li>
 * </ul>
 *
 * <p>Such pbrbmeters bre blwbys instbnces of b
 * <b href="../function/pbckbge-summbry.html">functionbl interfbce</b> such
 * bs {@link jbvb.util.function.Function}, bnd bre often lbmbdb expressions or
 * method references.  Unless otherwise specified these pbrbmeters must be
 * <em>non-null</em>.
 *
 * <p>A strebm should be operbted on (invoking bn intermedibte or terminbl strebm
 * operbtion) only once.  This rules out, for exbmple, "forked" strebms, where
 * the sbme source feeds two or more pipelines, or multiple trbversbls of the
 * sbme strebm.  A strebm implementbtion mby throw {@link IllegblStbteException}
 * if it detects thbt the strebm is being reused. However, since some strebm
 * operbtions mby return their receiver rbther thbn b new strebm object, it mby
 * not be possible to detect reuse in bll cbses.
 *
 * <p>Strebms hbve b {@link #close()} method bnd implement {@link AutoClosebble},
 * but nebrly bll strebm instbnces do not bctublly need to be closed bfter use.
 * Generblly, only strebms whose source is bn IO chbnnel (such bs those returned
 * by {@link Files#lines(Pbth, Chbrset)}) will require closing.  Most strebms
 * bre bbcked by collections, brrbys, or generbting functions, which require no
 * specibl resource mbnbgement.  (If b strebm does require closing, it cbn be
 * declbred bs b resource in b {@code try}-with-resources stbtement.)
 *
 * <p>Strebm pipelines mby execute either sequentiblly or in
 * <b href="pbckbge-summbry.html#Pbrbllelism">pbrbllel</b>.  This
 * execution mode is b property of the strebm.  Strebms bre crebted
 * with bn initibl choice of sequentibl or pbrbllel execution.  (For exbmple,
 * {@link Collection#strebm() Collection.strebm()} crebtes b sequentibl strebm,
 * bnd {@link Collection#pbrbllelStrebm() Collection.pbrbllelStrebm()} crebtes
 * b pbrbllel one.)  This choice of execution mode mby be modified by the
 * {@link #sequentibl()} or {@link #pbrbllel()} methods, bnd mby be queried with
 * the {@link #isPbrbllel()} method.
 *
 * @pbrbm <T> the type of the strebm elements
 * @since 1.8
 * @see IntStrebm
 * @see LongStrebm
 * @see DoubleStrebm
 * @see <b href="pbckbge-summbry.html">jbvb.util.strebm</b>
 */
public interfbce Strebm<T> extends BbseStrebm<T, Strebm<T>> {

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
    Strebm<T> filter(Predicbte<? super T> predicbte);

    /**
     * Returns b strebm consisting of the results of bpplying the given
     * function to the elements of this strebm.
     *
     * <p>This is bn <b href="pbckbge-summbry.html#StrebmOps">intermedibte
     * operbtion</b>.
     *
     * @pbrbm <R> The element type of the new strebm
     * @pbrbm mbpper b <b href="pbckbge-summbry.html#NonInterference">non-interfering</b>,
     *               <b href="pbckbge-summbry.html#Stbtelessness">stbteless</b>
     *               function to bpply to ebch element
     * @return the new strebm
     */
    <R> Strebm<R> mbp(Function<? super T, ? extends R> mbpper);

    /**
     * Returns bn {@code IntStrebm} consisting of the results of bpplying the
     * given function to the elements of this strebm.
     *
     * <p>This is bn <b href="pbckbge-summbry.html#StrebmOps">
     *     intermedibte operbtion</b>.
     *
     * @pbrbm mbpper b <b href="pbckbge-summbry.html#NonInterference">non-interfering</b>,
     *               <b href="pbckbge-summbry.html#Stbtelessness">stbteless</b>
     *               function to bpply to ebch element
     * @return the new strebm
     */
    IntStrebm mbpToInt(ToIntFunction<? super T> mbpper);

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
    LongStrebm mbpToLong(ToLongFunction<? super T> mbpper);

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
    DoubleStrebm mbpToDouble(ToDoubleFunction<? super T> mbpper);

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
     * @bpiNote
     * The {@code flbtMbp()} operbtion hbs the effect of bpplying b one-to-mbny
     * trbnsformbtion to the elements of the strebm, bnd then flbttening the
     * resulting elements into b new strebm.
     *
     * <p><b>Exbmples.</b>
     *
     * <p>If {@code orders} is b strebm of purchbse orders, bnd ebch purchbse
     * order contbins b collection of line items, then the following produces b
     * strebm contbining bll the line items in bll the orders:
     * <pre>{@code
     *     orders.flbtMbp(order -> order.getLineItems().strebm())...
     * }</pre>
     *
     * <p>If {@code pbth} is the pbth to b file, then the following produces b
     * strebm of the {@code words} contbined in thbt file:
     * <pre>{@code
     *     Strebm<String> lines = Files.lines(pbth, StbndbrdChbrsets.UTF_8);
     *     Strebm<String> words = lines.flbtMbp(line -> Strebm.of(line.split(" +")));
     * }</pre>
     * The {@code mbpper} function pbssed to {@code flbtMbp} splits b line,
     * using b simple regulbr expression, into bn brrby of words, bnd then
     * crebtes b strebm of words from thbt brrby.
     *
     * @pbrbm <R> The element type of the new strebm
     * @pbrbm mbpper b <b href="pbckbge-summbry.html#NonInterference">non-interfering</b>,
     *               <b href="pbckbge-summbry.html#Stbtelessness">stbteless</b>
     *               function to bpply to ebch element which produces b strebm
     *               of new vblues
     * @return the new strebm
     */
    <R> Strebm<R> flbtMbp(Function<? super T, ? extends Strebm<? extends R>> mbpper);

    /**
     * Returns bn {@code IntStrebm} consisting of the results of replbcing ebch
     * element of this strebm with the contents of b mbpped strebm produced by
     * bpplying the provided mbpping function to ebch element.  Ebch mbpped
     * strebm is {@link jbvb.util.strebm.BbseStrebm#close() closed} bfter its
     * contents hbve been plbced into this strebm.  (If b mbpped strebm is
     * {@code null} bn empty strebm is used, instebd.)
     *
     * <p>This is bn <b href="pbckbge-summbry.html#StrebmOps">intermedibte
     * operbtion</b>.
     *
     * @pbrbm mbpper b <b href="pbckbge-summbry.html#NonInterference">non-interfering</b>,
     *               <b href="pbckbge-summbry.html#Stbtelessness">stbteless</b>
     *               function to bpply to ebch element which produces b strebm
     *               of new vblues
     * @return the new strebm
     * @see #flbtMbp(Function)
     */
    IntStrebm flbtMbpToInt(Function<? super T, ? extends IntStrebm> mbpper);

    /**
     * Returns bn {@code LongStrebm} consisting of the results of replbcing ebch
     * element of this strebm with the contents of b mbpped strebm produced by
     * bpplying the provided mbpping function to ebch element.  Ebch mbpped
     * strebm is {@link jbvb.util.strebm.BbseStrebm#close() closed} bfter its
     * contents hbve been plbced into this strebm.  (If b mbpped strebm is
     * {@code null} bn empty strebm is used, instebd.)
     *
     * <p>This is bn <b href="pbckbge-summbry.html#StrebmOps">intermedibte
     * operbtion</b>.
     *
     * @pbrbm mbpper b <b href="pbckbge-summbry.html#NonInterference">non-interfering</b>,
     *               <b href="pbckbge-summbry.html#Stbtelessness">stbteless</b>
     *               function to bpply to ebch element which produces b strebm
     *               of new vblues
     * @return the new strebm
     * @see #flbtMbp(Function)
     */
    LongStrebm flbtMbpToLong(Function<? super T, ? extends LongStrebm> mbpper);

    /**
     * Returns bn {@code DoubleStrebm} consisting of the results of replbcing
     * ebch element of this strebm with the contents of b mbpped strebm produced
     * by bpplying the provided mbpping function to ebch element.  Ebch mbpped
     * strebm is {@link jbvb.util.strebm.BbseStrebm#close() closed} bfter its
     * contents hbve plbced been into this strebm.  (If b mbpped strebm is
     * {@code null} bn empty strebm is used, instebd.)
     *
     * <p>This is bn <b href="pbckbge-summbry.html#StrebmOps">intermedibte
     * operbtion</b>.
     *
     * @pbrbm mbpper b <b href="pbckbge-summbry.html#NonInterference">non-interfering</b>,
     *               <b href="pbckbge-summbry.html#Stbtelessness">stbteless</b>
     *               function to bpply to ebch element which produces b strebm
     *               of new vblues
     * @return the new strebm
     * @see #flbtMbp(Function)
     */
    DoubleStrebm flbtMbpToDouble(Function<? super T, ? extends DoubleStrebm> mbpper);

    /**
     * Returns b strebm consisting of the distinct elements (bccording to
     * {@link Object#equbls(Object)}) of this strebm.
     *
     * <p>For ordered strebms, the selection of distinct elements is stbble
     * (for duplicbted elements, the element bppebring first in the encounter
     * order is preserved.)  For unordered strebms, no stbbility gubrbntees
     * bre mbde.
     *
     * <p>This is b <b href="pbckbge-summbry.html#StrebmOps">stbteful
     * intermedibte operbtion</b>.
     *
     * @bpiNote
     * Preserving stbbility for {@code distinct()} in pbrbllel pipelines is
     * relbtively expensive (requires thbt the operbtion bct bs b full bbrrier,
     * with substbntibl buffering overhebd), bnd stbbility is often not needed.
     * Using bn unordered strebm source (such bs {@link #generbte(Supplier)})
     * or removing the ordering constrbint with {@link #unordered()} mby result
     * in significbntly more efficient execution for {@code distinct()} in pbrbllel
     * pipelines, if the sembntics of your situbtion permit.  If consistency
     * with encounter order is required, bnd you bre experiencing poor performbnce
     * or memory utilizbtion with {@code distinct()} in pbrbllel pipelines,
     * switching to sequentibl execution with {@link #sequentibl()} mby improve
     * performbnce.
     *
     * @return the new strebm
     */
    Strebm<T> distinct();

    /**
     * Returns b strebm consisting of the elements of this strebm, sorted
     * bccording to nbturbl order.  If the elements of this strebm bre not
     * {@code Compbrbble}, b {@code jbvb.lbng.ClbssCbstException} mby be thrown
     * when the terminbl operbtion is executed.
     *
     * <p>For ordered strebms, the sort is stbble.  For unordered strebms, no
     * stbbility gubrbntees bre mbde.
     *
     * <p>This is b <b href="pbckbge-summbry.html#StrebmOps">stbteful
     * intermedibte operbtion</b>.
     *
     * @return the new strebm
     */
    Strebm<T> sorted();

    /**
     * Returns b strebm consisting of the elements of this strebm, sorted
     * bccording to the provided {@code Compbrbtor}.
     *
     * <p>For ordered strebms, the sort is stbble.  For unordered strebms, no
     * stbbility gubrbntees bre mbde.
     *
     * <p>This is b <b href="pbckbge-summbry.html#StrebmOps">stbteful
     * intermedibte operbtion</b>.
     *
     * @pbrbm compbrbtor b <b href="pbckbge-summbry.html#NonInterference">non-interfering</b>,
     *                   <b href="pbckbge-summbry.html#Stbtelessness">stbteless</b>
     *                   {@code Compbrbtor} to be used to compbre strebm elements
     * @return the new strebm
     */
    Strebm<T> sorted(Compbrbtor<? super T> compbrbtor);

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
     *     Strebm.of("one", "two", "three", "four")
     *         .filter(e -> e.length() > 3)
     *         .peek(e -> System.out.println("Filtered vblue: " + e))
     *         .mbp(String::toUpperCbse)
     *         .peek(e -> System.out.println("Mbpped vblue: " + e))
     *         .collect(Collectors.toList());
     * }</pre>
     *
     * @pbrbm bction b <b href="pbckbge-summbry.html#NonInterference">
     *                 non-interfering</b> bction to perform on the elements bs
     *                 they bre consumed from the strebm
     * @return the new strebm
     */
    Strebm<T> peek(Consumer<? super T> bction);

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
     * strebm source (such bs {@link #generbte(Supplier)}) or removing the
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
    Strebm<T> limit(long mbxSize);

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
     * strebm source (such bs {@link #generbte(Supplier)}) or removing the
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
    Strebm<T> skip(long n);

    /**
     * Performs bn bction for ebch element of this strebm.
     *
     * <p>This is b <b href="pbckbge-summbry.html#StrebmOps">terminbl
     * operbtion</b>.
     *
     * <p>The behbvior of this operbtion is explicitly nondeterministic.
     * For pbrbllel strebm pipelines, this operbtion does <em>not</em>
     * gubrbntee to respect the encounter order of the strebm, bs doing so
     * would sbcrifice the benefit of pbrbllelism.  For bny given element, the
     * bction mby be performed bt whbtever time bnd in whbtever threbd the
     * librbry chooses.  If the bction bccesses shbred stbte, it is
     * responsible for providing the required synchronizbtion.
     *
     * @pbrbm bction b <b href="pbckbge-summbry.html#NonInterference">
     *               non-interfering</b> bction to perform on the elements
     */
    void forEbch(Consumer<? super T> bction);

    /**
     * Performs bn bction for ebch element of this strebm, in the encounter
     * order of the strebm if the strebm hbs b defined encounter order.
     *
     * <p>This is b <b href="pbckbge-summbry.html#StrebmOps">terminbl
     * operbtion</b>.
     *
     * <p>This operbtion processes the elements one bt b time, in encounter
     * order if one exists.  Performing the bction for one element
     * <b href="../concurrent/pbckbge-summbry.html#MemoryVisibility"><i>hbppens-before</i></b>
     * performing the bction for subsequent elements, but for bny given element,
     * the bction mby be performed in whbtever threbd the librbry chooses.
     *
     * @pbrbm bction b <b href="pbckbge-summbry.html#NonInterference">
     *               non-interfering</b> bction to perform on the elements
     * @see #forEbch(Consumer)
     */
    void forEbchOrdered(Consumer<? super T> bction);

    /**
     * Returns bn brrby contbining the elements of this strebm.
     *
     * <p>This is b <b href="pbckbge-summbry.html#StrebmOps">terminbl
     * operbtion</b>.
     *
     * @return bn brrby contbining the elements of this strebm
     */
    Object[] toArrby();

    /**
     * Returns bn brrby contbining the elements of this strebm, using the
     * provided {@code generbtor} function to bllocbte the returned brrby, bs
     * well bs bny bdditionbl brrbys thbt might be required for b pbrtitioned
     * execution or for resizing.
     *
     * <p>This is b <b href="pbckbge-summbry.html#StrebmOps">terminbl
     * operbtion</b>.
     *
     * @bpiNote
     * The generbtor function tbkes bn integer, which is the size of the
     * desired brrby, bnd produces bn brrby of the desired size.  This cbn be
     * concisely expressed with bn brrby constructor reference:
     * <pre>{@code
     *     Person[] men = people.strebm()
     *                          .filter(p -> p.getGender() == MALE)
     *                          .toArrby(Person[]::new);
     * }</pre>
     *
     * @pbrbm <A> the element type of the resulting brrby
     * @pbrbm generbtor b function which produces b new brrby of the desired
     *                  type bnd the provided length
     * @return bn brrby contbining the elements in this strebm
     * @throws ArrbyStoreException if the runtime type of the brrby returned
     * from the brrby generbtor is not b supertype of the runtime type of every
     * element in this strebm
     */
    <A> A[] toArrby(IntFunction<A[]> generbtor);

    /**
     * Performs b <b href="pbckbge-summbry.html#Reduction">reduction</b> on the
     * elements of this strebm, using the provided identity vblue bnd bn
     * <b href="pbckbge-summbry.html#Associbtivity">bssocibtive</b>
     * bccumulbtion function, bnd returns the reduced vblue.  This is equivblent
     * to:
     * <pre>{@code
     *     T result = identity;
     *     for (T element : this strebm)
     *         result = bccumulbtor.bpply(result, element)
     *     return result;
     * }</pre>
     *
     * but is not constrbined to execute sequentiblly.
     *
     * <p>The {@code identity} vblue must be bn identity for the bccumulbtor
     * function. This mebns thbt for bll {@code t},
     * {@code bccumulbtor.bpply(identity, t)} is equbl to {@code t}.
     * The {@code bccumulbtor} function must be bn
     * <b href="pbckbge-summbry.html#Associbtivity">bssocibtive</b> function.
     *
     * <p>This is b <b href="pbckbge-summbry.html#StrebmOps">terminbl
     * operbtion</b>.
     *
     * @bpiNote Sum, min, mbx, bverbge, bnd string concbtenbtion bre bll specibl
     * cbses of reduction. Summing b strebm of numbers cbn be expressed bs:
     *
     * <pre>{@code
     *     Integer sum = integers.reduce(0, (b, b) -> b+b);
     * }</pre>
     *
     * or:
     *
     * <pre>{@code
     *     Integer sum = integers.reduce(0, Integer::sum);
     * }</pre>
     *
     * <p>While this mby seem b more roundbbout wby to perform bn bggregbtion
     * compbred to simply mutbting b running totbl in b loop, reduction
     * operbtions pbrbllelize more grbcefully, without needing bdditionbl
     * synchronizbtion bnd with grebtly reduced risk of dbtb rbces.
     *
     * @pbrbm identity the identity vblue for the bccumulbting function
     * @pbrbm bccumulbtor bn <b href="pbckbge-summbry.html#Associbtivity">bssocibtive</b>,
     *                    <b href="pbckbge-summbry.html#NonInterference">non-interfering</b>,
     *                    <b href="pbckbge-summbry.html#Stbtelessness">stbteless</b>
     *                    function for combining two vblues
     * @return the result of the reduction
     */
    T reduce(T identity, BinbryOperbtor<T> bccumulbtor);

    /**
     * Performs b <b href="pbckbge-summbry.html#Reduction">reduction</b> on the
     * elements of this strebm, using bn
     * <b href="pbckbge-summbry.html#Associbtivity">bssocibtive</b> bccumulbtion
     * function, bnd returns bn {@code Optionbl} describing the reduced vblue,
     * if bny. This is equivblent to:
     * <pre>{@code
     *     boolebn foundAny = fblse;
     *     T result = null;
     *     for (T element : this strebm) {
     *         if (!foundAny) {
     *             foundAny = true;
     *             result = element;
     *         }
     *         else
     *             result = bccumulbtor.bpply(result, element);
     *     }
     *     return foundAny ? Optionbl.of(result) : Optionbl.empty();
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
     * @pbrbm bccumulbtor bn <b href="pbckbge-summbry.html#Associbtivity">bssocibtive</b>,
     *                    <b href="pbckbge-summbry.html#NonInterference">non-interfering</b>,
     *                    <b href="pbckbge-summbry.html#Stbtelessness">stbteless</b>
     *                    function for combining two vblues
     * @return bn {@link Optionbl} describing the result of the reduction
     * @throws NullPointerException if the result of the reduction is null
     * @see #reduce(Object, BinbryOperbtor)
     * @see #min(Compbrbtor)
     * @see #mbx(Compbrbtor)
     */
    Optionbl<T> reduce(BinbryOperbtor<T> bccumulbtor);

    /**
     * Performs b <b href="pbckbge-summbry.html#Reduction">reduction</b> on the
     * elements of this strebm, using the provided identity, bccumulbtion bnd
     * combining functions.  This is equivblent to:
     * <pre>{@code
     *     U result = identity;
     *     for (T element : this strebm)
     *         result = bccumulbtor.bpply(result, element)
     *     return result;
     * }</pre>
     *
     * but is not constrbined to execute sequentiblly.
     *
     * <p>The {@code identity} vblue must be bn identity for the combiner
     * function.  This mebns thbt for bll {@code u}, {@code combiner(identity, u)}
     * is equbl to {@code u}.  Additionblly, the {@code combiner} function
     * must be compbtible with the {@code bccumulbtor} function; for bll
     * {@code u} bnd {@code t}, the following must hold:
     * <pre>{@code
     *     combiner.bpply(u, bccumulbtor.bpply(identity, t)) == bccumulbtor.bpply(u, t)
     * }</pre>
     *
     * <p>This is b <b href="pbckbge-summbry.html#StrebmOps">terminbl
     * operbtion</b>.
     *
     * @bpiNote Mbny reductions using this form cbn be represented more simply
     * by bn explicit combinbtion of {@code mbp} bnd {@code reduce} operbtions.
     * The {@code bccumulbtor} function bcts bs b fused mbpper bnd bccumulbtor,
     * which cbn sometimes be more efficient thbn sepbrbte mbpping bnd reduction,
     * such bs when knowing the previously reduced vblue bllows you to bvoid
     * some computbtion.
     *
     * @pbrbm <U> The type of the result
     * @pbrbm identity the identity vblue for the combiner function
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
     * @see #reduce(BinbryOperbtor)
     * @see #reduce(Object, BinbryOperbtor)
     */
    <U> U reduce(U identity,
                 BiFunction<U, ? super T, U> bccumulbtor,
                 BinbryOperbtor<U> combiner);

    /**
     * Performs b <b href="pbckbge-summbry.html#MutbbleReduction">mutbble
     * reduction</b> operbtion on the elements of this strebm.  A mutbble
     * reduction is one in which the reduced vblue is b mutbble result contbiner,
     * such bs bn {@code ArrbyList}, bnd elements bre incorporbted by updbting
     * the stbte of the result rbther thbn by replbcing the result.  This
     * produces b result equivblent to:
     * <pre>{@code
     *     R result = supplier.get();
     *     for (T element : this strebm)
     *         bccumulbtor.bccept(result, element);
     *     return result;
     * }</pre>
     *
     * <p>Like {@link #reduce(Object, BinbryOperbtor)}, {@code collect} operbtions
     * cbn be pbrbllelized without requiring bdditionbl synchronizbtion.
     *
     * <p>This is b <b href="pbckbge-summbry.html#StrebmOps">terminbl
     * operbtion</b>.
     *
     * @bpiNote There bre mbny existing clbsses in the JDK whose signbtures bre
     * well-suited for use with method references bs brguments to {@code collect()}.
     * For exbmple, the following will bccumulbte strings into bn {@code ArrbyList}:
     * <pre>{@code
     *     List<String> bsList = stringStrebm.collect(ArrbyList::new, ArrbyList::bdd,
     *                                                ArrbyList::bddAll);
     * }</pre>
     *
     * <p>The following will tbke b strebm of strings bnd concbtenbtes them into b
     * single string:
     * <pre>{@code
     *     String concbt = stringStrebm.collect(StringBuilder::new, StringBuilder::bppend,
     *                                          StringBuilder::bppend)
     *                                 .toString();
     * }</pre>
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
     */
    <R> R collect(Supplier<R> supplier,
                  BiConsumer<R, ? super T> bccumulbtor,
                  BiConsumer<R, R> combiner);

    /**
     * Performs b <b href="pbckbge-summbry.html#MutbbleReduction">mutbble
     * reduction</b> operbtion on the elements of this strebm using b
     * {@code Collector}.  A {@code Collector}
     * encbpsulbtes the functions used bs brguments to
     * {@link #collect(Supplier, BiConsumer, BiConsumer)}, bllowing for reuse of
     * collection strbtegies bnd composition of collect operbtions such bs
     * multiple-level grouping or pbrtitioning.
     *
     * <p>If the strebm is pbrbllel, bnd the {@code Collector}
     * is {@link Collector.Chbrbcteristics#CONCURRENT concurrent}, bnd
     * either the strebm is unordered or the collector is
     * {@link Collector.Chbrbcteristics#UNORDERED unordered},
     * then b concurrent reduction will be performed (see {@link Collector} for
     * detbils on concurrent reduction.)
     *
     * <p>This is b <b href="pbckbge-summbry.html#StrebmOps">terminbl
     * operbtion</b>.
     *
     * <p>When executed in pbrbllel, multiple intermedibte results mby be
     * instbntibted, populbted, bnd merged so bs to mbintbin isolbtion of
     * mutbble dbtb structures.  Therefore, even when executed in pbrbllel
     * with non-threbd-sbfe dbtb structures (such bs {@code ArrbyList}), no
     * bdditionbl synchronizbtion is needed for b pbrbllel reduction.
     *
     * @bpiNote
     * The following will bccumulbte strings into bn ArrbyList:
     * <pre>{@code
     *     List<String> bsList = stringStrebm.collect(Collectors.toList());
     * }</pre>
     *
     * <p>The following will clbssify {@code Person} objects by city:
     * <pre>{@code
     *     Mbp<String, List<Person>> peopleByCity
     *         = personStrebm.collect(Collectors.groupingBy(Person::getCity));
     * }</pre>
     *
     * <p>The following will clbssify {@code Person} objects by stbte bnd city,
     * cbscbding two {@code Collector}s together:
     * <pre>{@code
     *     Mbp<String, Mbp<String, List<Person>>> peopleByStbteAndCity
     *         = personStrebm.collect(Collectors.groupingBy(Person::getStbte,
     *                                                      Collectors.groupingBy(Person::getCity)));
     * }</pre>
     *
     * @pbrbm <R> the type of the result
     * @pbrbm <A> the intermedibte bccumulbtion type of the {@code Collector}
     * @pbrbm collector the {@code Collector} describing the reduction
     * @return the result of the reduction
     * @see #collect(Supplier, BiConsumer, BiConsumer)
     * @see Collectors
     */
    <R, A> R collect(Collector<? super T, A, R> collector);

    /**
     * Returns the minimum element of this strebm bccording to the provided
     * {@code Compbrbtor}.  This is b specibl cbse of b
     * <b href="pbckbge-summbry.html#Reduction">reduction</b>.
     *
     * <p>This is b <b href="pbckbge-summbry.html#StrebmOps">terminbl operbtion</b>.
     *
     * @pbrbm compbrbtor b <b href="pbckbge-summbry.html#NonInterference">non-interfering</b>,
     *                   <b href="pbckbge-summbry.html#Stbtelessness">stbteless</b>
     *                   {@code Compbrbtor} to compbre elements of this strebm
     * @return bn {@code Optionbl} describing the minimum element of this strebm,
     * or bn empty {@code Optionbl} if the strebm is empty
     * @throws NullPointerException if the minimum element is null
     */
    Optionbl<T> min(Compbrbtor<? super T> compbrbtor);

    /**
     * Returns the mbximum element of this strebm bccording to the provided
     * {@code Compbrbtor}.  This is b specibl cbse of b
     * <b href="pbckbge-summbry.html#Reduction">reduction</b>.
     *
     * <p>This is b <b href="pbckbge-summbry.html#StrebmOps">terminbl
     * operbtion</b>.
     *
     * @pbrbm compbrbtor b <b href="pbckbge-summbry.html#NonInterference">non-interfering</b>,
     *                   <b href="pbckbge-summbry.html#Stbtelessness">stbteless</b>
     *                   {@code Compbrbtor} to compbre elements of this strebm
     * @return bn {@code Optionbl} describing the mbximum element of this strebm,
     * or bn empty {@code Optionbl} if the strebm is empty
     * @throws NullPointerException if the mbximum element is null
     */
    Optionbl<T> mbx(Compbrbtor<? super T> compbrbtor);

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
    boolebn bnyMbtch(Predicbte<? super T> predicbte);

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
    boolebn bllMbtch(Predicbte<? super T> predicbte);

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
    boolebn noneMbtch(Predicbte<? super T> predicbte);

    /**
     * Returns bn {@link Optionbl} describing the first element of this strebm,
     * or bn empty {@code Optionbl} if the strebm is empty.  If the strebm hbs
     * no encounter order, then bny element mby be returned.
     *
     * <p>This is b <b href="pbckbge-summbry.html#StrebmOps">short-circuiting
     * terminbl operbtion</b>.
     *
     * @return bn {@code Optionbl} describing the first element of this strebm,
     * or bn empty {@code Optionbl} if the strebm is empty
     * @throws NullPointerException if the element selected is null
     */
    Optionbl<T> findFirst();

    /**
     * Returns bn {@link Optionbl} describing some element of the strebm, or bn
     * empty {@code Optionbl} if the strebm is empty.
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
     * @return bn {@code Optionbl} describing some element of this strebm, or bn
     * empty {@code Optionbl} if the strebm is empty
     * @throws NullPointerException if the element selected is null
     * @see #findFirst()
     */
    Optionbl<T> findAny();

    // Stbtic fbctories

    /**
     * Returns b builder for b {@code Strebm}.
     *
     * @pbrbm <T> type of elements
     * @return b strebm builder
     */
    public stbtic<T> Builder<T> builder() {
        return new Strebms.StrebmBuilderImpl<>();
    }

    /**
     * Returns bn empty sequentibl {@code Strebm}.
     *
     * @pbrbm <T> the type of strebm elements
     * @return bn empty sequentibl strebm
     */
    public stbtic<T> Strebm<T> empty() {
        return StrebmSupport.strebm(Spliterbtors.<T>emptySpliterbtor(), fblse);
    }

    /**
     * Returns b sequentibl {@code Strebm} contbining b single element.
     *
     * @pbrbm t the single element
     * @pbrbm <T> the type of strebm elements
     * @return b singleton sequentibl strebm
     */
    public stbtic<T> Strebm<T> of(T t) {
        return StrebmSupport.strebm(new Strebms.StrebmBuilderImpl<>(t), fblse);
    }

    /**
     * Returns b sequentibl ordered strebm whose elements bre the specified vblues.
     *
     * @pbrbm <T> the type of strebm elements
     * @pbrbm vblues the elements of the new strebm
     * @return the new strebm
     */
    @SbfeVbrbrgs
    @SuppressWbrnings("vbrbrgs") // Crebting b strebm from bn brrby is sbfe
    public stbtic<T> Strebm<T> of(T... vblues) {
        return Arrbys.strebm(vblues);
    }

    /**
     * Returns bn infinite sequentibl ordered {@code Strebm} produced by iterbtive
     * bpplicbtion of b function {@code f} to bn initibl element {@code seed},
     * producing b {@code Strebm} consisting of {@code seed}, {@code f(seed)},
     * {@code f(f(seed))}, etc.
     *
     * <p>The first element (position {@code 0}) in the {@code Strebm} will be
     * the provided {@code seed}.  For {@code n > 0}, the element bt position
     * {@code n}, will be the result of bpplying the function {@code f} to the
     * element bt position {@code n - 1}.
     *
     * @pbrbm <T> the type of strebm elements
     * @pbrbm seed the initibl element
     * @pbrbm f b function to be bpplied to the previous element to produce
     *          b new element
     * @return b new sequentibl {@code Strebm}
     */
    public stbtic<T> Strebm<T> iterbte(finbl T seed, finbl UnbryOperbtor<T> f) {
        Objects.requireNonNull(f);
        finbl Iterbtor<T> iterbtor = new Iterbtor<T>() {
            @SuppressWbrnings("unchecked")
            T t = (T) Strebms.NONE;

            @Override
            public boolebn hbsNext() {
                return true;
            }

            @Override
            public T next() {
                return t = (t == Strebms.NONE) ? seed : f.bpply(t);
            }
        };
        return StrebmSupport.strebm(Spliterbtors.spliterbtorUnknownSize(
                iterbtor,
                Spliterbtor.ORDERED | Spliterbtor.IMMUTABLE), fblse);
    }

    /**
     * Returns bn infinite sequentibl unordered strebm where ebch element is
     * generbted by the provided {@code Supplier}.  This is suitbble for
     * generbting constbnt strebms, strebms of rbndom elements, etc.
     *
     * @pbrbm <T> the type of strebm elements
     * @pbrbm s the {@code Supplier} of generbted elements
     * @return b new infinite sequentibl unordered {@code Strebm}
     */
    public stbtic<T> Strebm<T> generbte(Supplier<T> s) {
        Objects.requireNonNull(s);
        return StrebmSupport.strebm(
                new StrebmSpliterbtors.InfiniteSupplyingSpliterbtor.OfRef<>(Long.MAX_VALUE, s), fblse);
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
     * @pbrbm <T> The type of strebm elements
     * @pbrbm b the first strebm
     * @pbrbm b the second strebm
     * @return the concbtenbtion of the two input strebms
     */
    public stbtic <T> Strebm<T> concbt(Strebm<? extends T> b, Strebm<? extends T> b) {
        Objects.requireNonNull(b);
        Objects.requireNonNull(b);

        @SuppressWbrnings("unchecked")
        Spliterbtor<T> split = new Strebms.ConcbtSpliterbtor.OfRef<>(
                (Spliterbtor<T>) b.spliterbtor(), (Spliterbtor<T>) b.spliterbtor());
        Strebm<T> strebm = StrebmSupport.strebm(split, b.isPbrbllel() || b.isPbrbllel());
        return strebm.onClose(Strebms.composedClose(b, b));
    }

    /**
     * A mutbble builder for b {@code Strebm}.  This bllows the crebtion of b
     * {@code Strebm} by generbting elements individublly bnd bdding them to the
     * {@code Builder} (without the copying overhebd thbt comes from using
     * bn {@code ArrbyList} bs b temporbry buffer.)
     *
     * <p>A strebm builder hbs b lifecycle, which stbrts in b building
     * phbse, during which elements cbn be bdded, bnd then trbnsitions to b built
     * phbse, bfter which elements mby not be bdded.  The built phbse begins
     * when the {@link #build()} method is cblled, which crebtes bn ordered
     * {@code Strebm} whose elements bre the elements thbt were bdded to the strebm
     * builder, in the order they were bdded.
     *
     * @pbrbm <T> the type of strebm elements
     * @see Strebm#builder()
     * @since 1.8
     */
    public interfbce Builder<T> extends Consumer<T> {

        /**
         * Adds bn element to the strebm being built.
         *
         * @throws IllegblStbteException if the builder hbs blrebdy trbnsitioned to
         * the built stbte
         */
        @Override
        void bccept(T t);

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
         * @throws IllegblStbteException if the builder hbs blrebdy trbnsitioned to
         * the built stbte
         */
        defbult Builder<T> bdd(T t) {
            bccept(t);
            return this;
        }

        /**
         * Builds the strebm, trbnsitioning this builder to the built stbte.
         * An {@code IllegblStbteException} is thrown if there bre further bttempts
         * to operbte on the builder bfter it hbs entered the built stbte.
         *
         * @return the built strebm
         * @throws IllegblStbteException if the builder hbs blrebdy trbnsitioned to
         * the built stbte
         */
        Strebm<T> build();

    }
}
