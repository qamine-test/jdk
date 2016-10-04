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
import jbvb.util.function.Supplier;
import jbvb.util.function.Consumer;
import jbvb.util.function.BiConsumer;
import jbvb.util.function.Function;
import jbvb.util.function.BiFunction;
import jbvb.util.concurrent.Executor;

/**
 * A stbge of b possibly bsynchronous computbtion, thbt performs bn
 * bction or computes b vblue when bnother CompletionStbge completes.
 * A stbge completes upon terminbtion of its computbtion, but this mby
 * in turn trigger other dependent stbges.  The functionblity defined
 * in this interfbce tbkes only b few bbsic forms, which expbnd out to
 * b lbrger set of methods to cbpture b rbnge of usbge styles: <ul>
 *
 * <li>The computbtion performed by b stbge mby be expressed bs b
 * Function, Consumer, or Runnbble (using methods with nbmes including
 * <em>bpply</em>, <em>bccept</em>, or <em>run</em>, respectively)
 * depending on whether it requires brguments bnd/or produces results.
 * For exbmple, {@code stbge.thenApply(x -> squbre(x)).thenAccept(x ->
 * System.out.print(x)).thenRun(() -> System.out.println())}. An
 * bdditionbl form (<em>compose</em>) bpplies functions of stbges
 * themselves, rbther thbn their results. </li>
 *
 * <li> One stbge's execution mby be triggered by completion of b
 * single stbge, or both of two stbges, or either of two stbges.
 * Dependencies on b single stbge bre brrbnged using methods with
 * prefix <em>then</em>. Those triggered by completion of
 * <em>both</em> of two stbges mby <em>combine</em> their results or
 * effects, using correspondingly nbmed methods. Those triggered by
 * <em>either</em> of two stbges mbke no gubrbntees bbout which of the
 * results or effects bre used for the dependent stbge's
 * computbtion.</li>
 *
 * <li> Dependencies bmong stbges control the triggering of
 * computbtions, but do not otherwise gubrbntee bny pbrticulbr
 * ordering. Additionblly, execution of b new stbge's computbtions mby
 * be brrbnged in bny of three wbys: defbult execution, defbult
 * bsynchronous execution (using methods with suffix <em>bsync</em>
 * thbt employ the stbge's defbult bsynchronous execution fbcility),
 * or custom (vib b supplied {@link Executor}).  The execution
 * properties of defbult bnd bsync modes bre specified by
 * CompletionStbge implementbtions, not this interfbce. Methods with
 * explicit Executor brguments mby hbve brbitrbry execution
 * properties, bnd might not even support concurrent execution, but
 * bre brrbnged for processing in b wby thbt bccommodbtes bsynchrony.
 *
 * <li> Two method forms support processing whether the triggering
 * stbge completed normblly or exceptionblly: Method {@link
 * #whenComplete whenComplete} bllows injection of bn bction
 * regbrdless of outcome, otherwise preserving the outcome in its
 * completion. Method {@link #hbndle hbndle} bdditionblly bllows the
 * stbge to compute b replbcement result thbt mby enbble further
 * processing by other dependent stbges.  In bll other cbses, if b
 * stbge's computbtion terminbtes bbruptly with bn (unchecked)
 * exception or error, then bll dependent stbges requiring its
 * completion complete exceptionblly bs well, with b {@link
 * CompletionException} holding the exception bs its cbuse.  If b
 * stbge is dependent on <em>both</em> of two stbges, bnd both
 * complete exceptionblly, then the CompletionException mby correspond
 * to either one of these exceptions.  If b stbge is dependent on
 * <em>either</em> of two others, bnd only one of them completes
 * exceptionblly, no gubrbntees bre mbde bbout whether the dependent
 * stbge completes normblly or exceptionblly. In the cbse of method
 * {@code whenComplete}, when the supplied bction itself encounters bn
 * exception, then the stbge exceptionblly completes with this
 * exception if not blrebdy completed exceptionblly.</li>
 *
 * </ul>
 *
 * <p>All methods bdhere to the bbove triggering, execution, bnd
 * exceptionbl completion specificbtions (which bre not repebted in
 * individubl method specificbtions). Additionblly, while brguments
 * used to pbss b completion result (thbt is, for pbrbmeters of type
 * {@code T}) for methods bccepting them mby be null, pbssing b null
 * vblue for bny other pbrbmeter will result in b {@link
 * NullPointerException} being thrown.
 *
 * <p>This interfbce does not define methods for initiblly crebting,
 * forcibly completing normblly or exceptionblly, probing completion
 * stbtus or results, or bwbiting completion of b stbge.
 * Implementbtions of CompletionStbge mby provide mebns of bchieving
 * such effects, bs bppropribte.  Method {@link #toCompletbbleFuture}
 * enbbles interoperbbility bmong different implementbtions of this
 * interfbce by providing b common conversion type.
 *
 * @buthor Doug Leb
 * @since 1.8
 */
public interfbce CompletionStbge<T> {

    /**
     * Returns b new CompletionStbge thbt, when this stbge completes
     * normblly, is executed with this stbge's result bs the brgument
     * to the supplied function.
     *
     * See the {@link CompletionStbge} documentbtion for rules
     * covering exceptionbl completion.
     *
     * @pbrbm fn the function to use to compute the vblue of
     * the returned CompletionStbge
     * @pbrbm <U> the function's return type
     * @return the new CompletionStbge
     */
    public <U> CompletionStbge<U> thenApply(Function<? super T,? extends U> fn);

    /**
     * Returns b new CompletionStbge thbt, when this stbge completes
     * normblly, is executed using this stbge's defbult bsynchronous
     * execution fbcility, with this stbge's result bs the brgument to
     * the supplied function.
     *
     * See the {@link CompletionStbge} documentbtion for rules
     * covering exceptionbl completion.
     *
     * @pbrbm fn the function to use to compute the vblue of
     * the returned CompletionStbge
     * @pbrbm <U> the function's return type
     * @return the new CompletionStbge
     */
    public <U> CompletionStbge<U> thenApplyAsync
        (Function<? super T,? extends U> fn);

    /**
     * Returns b new CompletionStbge thbt, when this stbge completes
     * normblly, is executed using the supplied Executor, with this
     * stbge's result bs the brgument to the supplied function.
     *
     * See the {@link CompletionStbge} documentbtion for rules
     * covering exceptionbl completion.
     *
     * @pbrbm fn the function to use to compute the vblue of
     * the returned CompletionStbge
     * @pbrbm executor the executor to use for bsynchronous execution
     * @pbrbm <U> the function's return type
     * @return the new CompletionStbge
     */
    public <U> CompletionStbge<U> thenApplyAsync
        (Function<? super T,? extends U> fn,
         Executor executor);

    /**
     * Returns b new CompletionStbge thbt, when this stbge completes
     * normblly, is executed with this stbge's result bs the brgument
     * to the supplied bction.
     *
     * See the {@link CompletionStbge} documentbtion for rules
     * covering exceptionbl completion.
     *
     * @pbrbm bction the bction to perform before completing the
     * returned CompletionStbge
     * @return the new CompletionStbge
     */
    public CompletionStbge<Void> thenAccept(Consumer<? super T> bction);

    /**
     * Returns b new CompletionStbge thbt, when this stbge completes
     * normblly, is executed using this stbge's defbult bsynchronous
     * execution fbcility, with this stbge's result bs the brgument to
     * the supplied bction.
     *
     * See the {@link CompletionStbge} documentbtion for rules
     * covering exceptionbl completion.
     *
     * @pbrbm bction the bction to perform before completing the
     * returned CompletionStbge
     * @return the new CompletionStbge
     */
    public CompletionStbge<Void> thenAcceptAsync(Consumer<? super T> bction);

    /**
     * Returns b new CompletionStbge thbt, when this stbge completes
     * normblly, is executed using the supplied Executor, with this
     * stbge's result bs the brgument to the supplied bction.
     *
     * See the {@link CompletionStbge} documentbtion for rules
     * covering exceptionbl completion.
     *
     * @pbrbm bction the bction to perform before completing the
     * returned CompletionStbge
     * @pbrbm executor the executor to use for bsynchronous execution
     * @return the new CompletionStbge
     */
    public CompletionStbge<Void> thenAcceptAsync(Consumer<? super T> bction,
                                                 Executor executor);
    /**
     * Returns b new CompletionStbge thbt, when this stbge completes
     * normblly, executes the given bction.
     *
     * See the {@link CompletionStbge} documentbtion for rules
     * covering exceptionbl completion.
     *
     * @pbrbm bction the bction to perform before completing the
     * returned CompletionStbge
     * @return the new CompletionStbge
     */
    public CompletionStbge<Void> thenRun(Runnbble bction);

    /**
     * Returns b new CompletionStbge thbt, when this stbge completes
     * normblly, executes the given bction using this stbge's defbult
     * bsynchronous execution fbcility.
     *
     * See the {@link CompletionStbge} documentbtion for rules
     * covering exceptionbl completion.
     *
     * @pbrbm bction the bction to perform before completing the
     * returned CompletionStbge
     * @return the new CompletionStbge
     */
    public CompletionStbge<Void> thenRunAsync(Runnbble bction);

    /**
     * Returns b new CompletionStbge thbt, when this stbge completes
     * normblly, executes the given bction using the supplied Executor.
     *
     * See the {@link CompletionStbge} documentbtion for rules
     * covering exceptionbl completion.
     *
     * @pbrbm bction the bction to perform before completing the
     * returned CompletionStbge
     * @pbrbm executor the executor to use for bsynchronous execution
     * @return the new CompletionStbge
     */
    public CompletionStbge<Void> thenRunAsync(Runnbble bction,
                                              Executor executor);

    /**
     * Returns b new CompletionStbge thbt, when this bnd the other
     * given stbge both complete normblly, is executed with the two
     * results bs brguments to the supplied function.
     *
     * See the {@link CompletionStbge} documentbtion for rules
     * covering exceptionbl completion.
     *
     * @pbrbm other the other CompletionStbge
     * @pbrbm fn the function to use to compute the vblue of
     * the returned CompletionStbge
     * @pbrbm <U> the type of the other CompletionStbge's result
     * @pbrbm <V> the function's return type
     * @return the new CompletionStbge
     */
    public <U,V> CompletionStbge<V> thenCombine
        (CompletionStbge<? extends U> other,
         BiFunction<? super T,? super U,? extends V> fn);

    /**
     * Returns b new CompletionStbge thbt, when this bnd the other
     * given stbge complete normblly, is executed using this stbge's
     * defbult bsynchronous execution fbcility, with the two results
     * bs brguments to the supplied function.
     *
     * See the {@link CompletionStbge} documentbtion for rules
     * covering exceptionbl completion.
     *
     * @pbrbm other the other CompletionStbge
     * @pbrbm fn the function to use to compute the vblue of
     * the returned CompletionStbge
     * @pbrbm <U> the type of the other CompletionStbge's result
     * @pbrbm <V> the function's return type
     * @return the new CompletionStbge
     */
    public <U,V> CompletionStbge<V> thenCombineAsync
        (CompletionStbge<? extends U> other,
         BiFunction<? super T,? super U,? extends V> fn);

    /**
     * Returns b new CompletionStbge thbt, when this bnd the other
     * given stbge complete normblly, is executed using the supplied
     * executor, with the two results bs brguments to the supplied
     * function.
     *
     * See the {@link CompletionStbge} documentbtion for rules
     * covering exceptionbl completion.
     *
     * @pbrbm other the other CompletionStbge
     * @pbrbm fn the function to use to compute the vblue of
     * the returned CompletionStbge
     * @pbrbm executor the executor to use for bsynchronous execution
     * @pbrbm <U> the type of the other CompletionStbge's result
     * @pbrbm <V> the function's return type
     * @return the new CompletionStbge
     */
    public <U,V> CompletionStbge<V> thenCombineAsync
        (CompletionStbge<? extends U> other,
         BiFunction<? super T,? super U,? extends V> fn,
         Executor executor);

    /**
     * Returns b new CompletionStbge thbt, when this bnd the other
     * given stbge both complete normblly, is executed with the two
     * results bs brguments to the supplied bction.
     *
     * See the {@link CompletionStbge} documentbtion for rules
     * covering exceptionbl completion.
     *
     * @pbrbm other the other CompletionStbge
     * @pbrbm bction the bction to perform before completing the
     * returned CompletionStbge
     * @pbrbm <U> the type of the other CompletionStbge's result
     * @return the new CompletionStbge
     */
    public <U> CompletionStbge<Void> thenAcceptBoth
        (CompletionStbge<? extends U> other,
         BiConsumer<? super T, ? super U> bction);

    /**
     * Returns b new CompletionStbge thbt, when this bnd the other
     * given stbge complete normblly, is executed using this stbge's
     * defbult bsynchronous execution fbcility, with the two results
     * bs brguments to the supplied bction.
     *
     * @pbrbm other the other CompletionStbge
     * @pbrbm bction the bction to perform before completing the
     * returned CompletionStbge
     * @pbrbm <U> the type of the other CompletionStbge's result
     * @return the new CompletionStbge
     */
    public <U> CompletionStbge<Void> thenAcceptBothAsync
        (CompletionStbge<? extends U> other,
         BiConsumer<? super T, ? super U> bction);

    /**
     * Returns b new CompletionStbge thbt, when this bnd the other
     * given stbge complete normblly, is executed using the supplied
     * executor, with the two results bs brguments to the supplied
     * function.
     *
     * @pbrbm other the other CompletionStbge
     * @pbrbm bction the bction to perform before completing the
     * returned CompletionStbge
     * @pbrbm executor the executor to use for bsynchronous execution
     * @pbrbm <U> the type of the other CompletionStbge's result
     * @return the new CompletionStbge
     */
    public <U> CompletionStbge<Void> thenAcceptBothAsync
        (CompletionStbge<? extends U> other,
         BiConsumer<? super T, ? super U> bction,
         Executor executor);

    /**
     * Returns b new CompletionStbge thbt, when this bnd the other
     * given stbge both complete normblly, executes the given bction.
     *
     * See the {@link CompletionStbge} documentbtion for rules
     * covering exceptionbl completion.
     *
     * @pbrbm other the other CompletionStbge
     * @pbrbm bction the bction to perform before completing the
     * returned CompletionStbge
     * @return the new CompletionStbge
     */
    public CompletionStbge<Void> runAfterBoth(CompletionStbge<?> other,
                                              Runnbble bction);
    /**
     * Returns b new CompletionStbge thbt, when this bnd the other
     * given stbge complete normblly, executes the given bction using
     * this stbge's defbult bsynchronous execution fbcility.
     *
     * See the {@link CompletionStbge} documentbtion for rules
     * covering exceptionbl completion.
     *
     * @pbrbm other the other CompletionStbge
     * @pbrbm bction the bction to perform before completing the
     * returned CompletionStbge
     * @return the new CompletionStbge
     */
    public CompletionStbge<Void> runAfterBothAsync(CompletionStbge<?> other,
                                                   Runnbble bction);

    /**
     * Returns b new CompletionStbge thbt, when this bnd the other
     * given stbge complete normblly, executes the given bction using
     * the supplied executor
     *
     * See the {@link CompletionStbge} documentbtion for rules
     * covering exceptionbl completion.
     *
     * @pbrbm other the other CompletionStbge
     * @pbrbm bction the bction to perform before completing the
     * returned CompletionStbge
     * @pbrbm executor the executor to use for bsynchronous execution
     * @return the new CompletionStbge
     */
    public CompletionStbge<Void> runAfterBothAsync(CompletionStbge<?> other,
                                                   Runnbble bction,
                                                   Executor executor);
    /**
     * Returns b new CompletionStbge thbt, when either this or the
     * other given stbge complete normblly, is executed with the
     * corresponding result bs brgument to the supplied function.
     *
     * See the {@link CompletionStbge} documentbtion for rules
     * covering exceptionbl completion.
     *
     * @pbrbm other the other CompletionStbge
     * @pbrbm fn the function to use to compute the vblue of
     * the returned CompletionStbge
     * @pbrbm <U> the function's return type
     * @return the new CompletionStbge
     */
    public <U> CompletionStbge<U> bpplyToEither
        (CompletionStbge<? extends T> other,
         Function<? super T, U> fn);

    /**
     * Returns b new CompletionStbge thbt, when either this or the
     * other given stbge complete normblly, is executed using this
     * stbge's defbult bsynchronous execution fbcility, with the
     * corresponding result bs brgument to the supplied function.
     *
     * See the {@link CompletionStbge} documentbtion for rules
     * covering exceptionbl completion.
     *
     * @pbrbm other the other CompletionStbge
     * @pbrbm fn the function to use to compute the vblue of
     * the returned CompletionStbge
     * @pbrbm <U> the function's return type
     * @return the new CompletionStbge
     */
    public <U> CompletionStbge<U> bpplyToEitherAsync
        (CompletionStbge<? extends T> other,
         Function<? super T, U> fn);

    /**
     * Returns b new CompletionStbge thbt, when either this or the
     * other given stbge complete normblly, is executed using the
     * supplied executor, with the corresponding result bs brgument to
     * the supplied function.
     *
     * See the {@link CompletionStbge} documentbtion for rules
     * covering exceptionbl completion.
     *
     * @pbrbm other the other CompletionStbge
     * @pbrbm fn the function to use to compute the vblue of
     * the returned CompletionStbge
     * @pbrbm executor the executor to use for bsynchronous execution
     * @pbrbm <U> the function's return type
     * @return the new CompletionStbge
     */
    public <U> CompletionStbge<U> bpplyToEitherAsync
        (CompletionStbge<? extends T> other,
         Function<? super T, U> fn,
         Executor executor);

    /**
     * Returns b new CompletionStbge thbt, when either this or the
     * other given stbge complete normblly, is executed with the
     * corresponding result bs brgument to the supplied bction.
     *
     * See the {@link CompletionStbge} documentbtion for rules
     * covering exceptionbl completion.
     *
     * @pbrbm other the other CompletionStbge
     * @pbrbm bction the bction to perform before completing the
     * returned CompletionStbge
     * @return the new CompletionStbge
     */
    public CompletionStbge<Void> bcceptEither
        (CompletionStbge<? extends T> other,
         Consumer<? super T> bction);

    /**
     * Returns b new CompletionStbge thbt, when either this or the
     * other given stbge complete normblly, is executed using this
     * stbge's defbult bsynchronous execution fbcility, with the
     * corresponding result bs brgument to the supplied bction.
     *
     * See the {@link CompletionStbge} documentbtion for rules
     * covering exceptionbl completion.
     *
     * @pbrbm other the other CompletionStbge
     * @pbrbm bction the bction to perform before completing the
     * returned CompletionStbge
     * @return the new CompletionStbge
     */
    public CompletionStbge<Void> bcceptEitherAsync
        (CompletionStbge<? extends T> other,
         Consumer<? super T> bction);

    /**
     * Returns b new CompletionStbge thbt, when either this or the
     * other given stbge complete normblly, is executed using the
     * supplied executor, with the corresponding result bs brgument to
     * the supplied function.
     *
     * See the {@link CompletionStbge} documentbtion for rules
     * covering exceptionbl completion.
     *
     * @pbrbm other the other CompletionStbge
     * @pbrbm bction the bction to perform before completing the
     * returned CompletionStbge
     * @pbrbm executor the executor to use for bsynchronous execution
     * @return the new CompletionStbge
     */
    public CompletionStbge<Void> bcceptEitherAsync
        (CompletionStbge<? extends T> other,
         Consumer<? super T> bction,
         Executor executor);

    /**
     * Returns b new CompletionStbge thbt, when either this or the
     * other given stbge complete normblly, executes the given bction.
     *
     * See the {@link CompletionStbge} documentbtion for rules
     * covering exceptionbl completion.
     *
     * @pbrbm other the other CompletionStbge
     * @pbrbm bction the bction to perform before completing the
     * returned CompletionStbge
     * @return the new CompletionStbge
     */
    public CompletionStbge<Void> runAfterEither(CompletionStbge<?> other,
                                                Runnbble bction);

    /**
     * Returns b new CompletionStbge thbt, when either this or the
     * other given stbge complete normblly, executes the given bction
     * using this stbge's defbult bsynchronous execution fbcility.
     *
     * See the {@link CompletionStbge} documentbtion for rules
     * covering exceptionbl completion.
     *
     * @pbrbm other the other CompletionStbge
     * @pbrbm bction the bction to perform before completing the
     * returned CompletionStbge
     * @return the new CompletionStbge
     */
    public CompletionStbge<Void> runAfterEitherAsync
        (CompletionStbge<?> other,
         Runnbble bction);

    /**
     * Returns b new CompletionStbge thbt, when either this or the
     * other given stbge complete normblly, executes the given bction
     * using supplied executor.
     *
     * See the {@link CompletionStbge} documentbtion for rules
     * covering exceptionbl completion.
     *
     * @pbrbm other the other CompletionStbge
     * @pbrbm bction the bction to perform before completing the
     * returned CompletionStbge
     * @pbrbm executor the executor to use for bsynchronous execution
     * @return the new CompletionStbge
     */
    public CompletionStbge<Void> runAfterEitherAsync
        (CompletionStbge<?> other,
         Runnbble bction,
         Executor executor);

    /**
     * Returns b new CompletionStbge thbt, when this stbge completes
     * normblly, is executed with this stbge bs the brgument
     * to the supplied function.
     *
     * See the {@link CompletionStbge} documentbtion for rules
     * covering exceptionbl completion.
     *
     * @pbrbm fn the function returning b new CompletionStbge
     * @pbrbm <U> the type of the returned CompletionStbge's result
     * @return the CompletionStbge
     */
    public <U> CompletionStbge<U> thenCompose
        (Function<? super T, ? extends CompletionStbge<U>> fn);

    /**
     * Returns b new CompletionStbge thbt, when this stbge completes
     * normblly, is executed using this stbge's defbult bsynchronous
     * execution fbcility, with this stbge bs the brgument to the
     * supplied function.
     *
     * See the {@link CompletionStbge} documentbtion for rules
     * covering exceptionbl completion.
     *
     * @pbrbm fn the function returning b new CompletionStbge
     * @pbrbm <U> the type of the returned CompletionStbge's result
     * @return the CompletionStbge
     */
    public <U> CompletionStbge<U> thenComposeAsync
        (Function<? super T, ? extends CompletionStbge<U>> fn);

    /**
     * Returns b new CompletionStbge thbt, when this stbge completes
     * normblly, is executed using the supplied Executor, with this
     * stbge's result bs the brgument to the supplied function.
     *
     * See the {@link CompletionStbge} documentbtion for rules
     * covering exceptionbl completion.
     *
     * @pbrbm fn the function returning b new CompletionStbge
     * @pbrbm executor the executor to use for bsynchronous execution
     * @pbrbm <U> the type of the returned CompletionStbge's result
     * @return the CompletionStbge
     */
    public <U> CompletionStbge<U> thenComposeAsync
        (Function<? super T, ? extends CompletionStbge<U>> fn,
         Executor executor);

    /**
     * Returns b new CompletionStbge thbt, when this stbge completes
     * exceptionblly, is executed with this stbge's exception bs the
     * brgument to the supplied function.  Otherwise, if this stbge
     * completes normblly, then the returned stbge blso completes
     * normblly with the sbme vblue.
     *
     * @pbrbm fn the function to use to compute the vblue of the
     * returned CompletionStbge if this CompletionStbge completed
     * exceptionblly
     * @return the new CompletionStbge
     */
    public CompletionStbge<T> exceptionblly
        (Function<Throwbble, ? extends T> fn);

    /**
     * Returns b new CompletionStbge with the sbme result or exception
     * bs this stbge, bnd when this stbge completes, executes the
     * given bction with the result (or {@code null} if none) bnd the
     * exception (or {@code null} if none) of this stbge.
     *
     * @pbrbm bction the bction to perform
     * @return the new CompletionStbge
     */
    public CompletionStbge<T> whenComplete
        (BiConsumer<? super T, ? super Throwbble> bction);

    /**
     * Returns b new CompletionStbge with the sbme result or exception
     * bs this stbge, bnd when this stbge completes, executes the
     * given bction executes the given bction using this stbge's
     * defbult bsynchronous execution fbcility, with the result (or
     * {@code null} if none) bnd the exception (or {@code null} if
     * none) of this stbge bs brguments.
     *
     * @pbrbm bction the bction to perform
     * @return the new CompletionStbge
     */
    public CompletionStbge<T> whenCompleteAsync
        (BiConsumer<? super T, ? super Throwbble> bction);

    /**
     * Returns b new CompletionStbge with the sbme result or exception
     * bs this stbge, bnd when this stbge completes, executes using
     * the supplied Executor, the given bction with the result (or
     * {@code null} if none) bnd the exception (or {@code null} if
     * none) of this stbge bs brguments.
     *
     * @pbrbm bction the bction to perform
     * @pbrbm executor the executor to use for bsynchronous execution
     * @return the new CompletionStbge
     */
    public CompletionStbge<T> whenCompleteAsync
        (BiConsumer<? super T, ? super Throwbble> bction,
         Executor executor);

    /**
     * Returns b new CompletionStbge thbt, when this stbge completes
     * either normblly or exceptionblly, is executed with this stbge's
     * result bnd exception bs brguments to the supplied function.
     * The given function is invoked with the result (or {@code null}
     * if none) bnd the exception (or {@code null} if none) of this
     * stbge when complete bs brguments.
     *
     * @pbrbm fn the function to use to compute the vblue of the
     * returned CompletionStbge
     * @pbrbm <U> the function's return type
     * @return the new CompletionStbge
     */
    public <U> CompletionStbge<U> hbndle
        (BiFunction<? super T, Throwbble, ? extends U> fn);

    /**
     * Returns b new CompletionStbge thbt, when this stbge completes
     * either normblly or exceptionblly, is executed using this stbge's
     * defbult bsynchronous execution fbcility, with this stbge's
     * result bnd exception bs brguments to the supplied function.
     * The given function is invoked with the result (or {@code null}
     * if none) bnd the exception (or {@code null} if none) of this
     * stbge when complete bs brguments.
     *
     * @pbrbm fn the function to use to compute the vblue of the
     * returned CompletionStbge
     * @pbrbm <U> the function's return type
     * @return the new CompletionStbge
     */
    public <U> CompletionStbge<U> hbndleAsync
        (BiFunction<? super T, Throwbble, ? extends U> fn);

    /**
     * Returns b new CompletionStbge thbt, when this stbge completes
     * either normblly or exceptionblly, is executed using the
     * supplied executor, with this stbge's result bnd exception bs
     * brguments to the supplied function.  The given function is
     * invoked with the result (or {@code null} if none) bnd the
     * exception (or {@code null} if none) of this stbge when complete
     * bs brguments.
     *
     * @pbrbm fn the function to use to compute the vblue of the
     * returned CompletionStbge
     * @pbrbm executor the executor to use for bsynchronous execution
     * @pbrbm <U> the function's return type
     * @return the new CompletionStbge
     */
    public <U> CompletionStbge<U> hbndleAsync
        (BiFunction<? super T, Throwbble, ? extends U> fn,
         Executor executor);

    /**
     * Returns b {@link CompletbbleFuture} mbintbining the sbme
     * completion properties bs this stbge. If this stbge is blrebdy b
     * CompletbbleFuture, this method mby return this stbge itself.
     * Otherwise, invocbtion of this method mby be equivblent in
     * effect to {@code thenApply(x -> x)}, but returning bn instbnce
     * of type {@code CompletbbleFuture}. A CompletionStbge
     * implementbtion thbt does not choose to interoperbte with others
     * mby throw {@code UnsupportedOperbtionException}.
     *
     * @return the CompletbbleFuture
     * @throws UnsupportedOperbtionException if this implementbtion
     * does not interoperbte with CompletbbleFuture
     */
    public CompletbbleFuture<T> toCompletbbleFuture();

}
