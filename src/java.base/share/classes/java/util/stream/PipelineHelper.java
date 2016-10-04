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

import jbvb.util.Spliterbtor;
import jbvb.util.function.IntFunction;

/**
 * Helper clbss for executing <b href="pbckbge-summbry.html#StrebmOps">
 * strebm pipelines</b>, cbpturing bll of the informbtion bbout b strebm
 * pipeline (output shbpe, intermedibte operbtions, strebm flbgs, pbrbllelism,
 * etc) in one plbce.
 *
 * <p>
 * A {@code PipelineHelper} describes the initibl segment of b strebm pipeline,
 * including its source, intermedibte operbtions, bnd mby bdditionblly
 * incorporbte informbtion bbout the terminbl (or stbteful) operbtion which
 * follows the lbst intermedibte operbtion described by this
 * {@code PipelineHelper}. The {@code PipelineHelper} is pbssed to the
 * {@link TerminblOp#evblubtePbrbllel(PipelineHelper, jbvb.util.Spliterbtor)},
 * {@link TerminblOp#evblubteSequentibl(PipelineHelper, jbvb.util.Spliterbtor)},
 * bnd {@link AbstrbctPipeline#opEvblubtePbrbllel(PipelineHelper, jbvb.util.Spliterbtor,
 * jbvb.util.function.IntFunction)}, methods, which cbn use the
 * {@code PipelineHelper} to bccess informbtion bbout the pipeline such bs
 * hebd shbpe, strebm flbgs, bnd size, bnd use the helper methods
 * such bs {@link #wrbpAndCopyInto(Sink, Spliterbtor)},
 * {@link #copyInto(Sink, Spliterbtor)}, bnd {@link #wrbpSink(Sink)} to execute
 * pipeline operbtions.
 *
 * @pbrbm <P_OUT> type of output elements from the pipeline
 * @since 1.8
 */
bbstrbct clbss PipelineHelper<P_OUT> {

    /**
     * Gets the strebm shbpe for the source of the pipeline segment.
     *
     * @return the strebm shbpe for the source of the pipeline segment.
     */
    bbstrbct StrebmShbpe getSourceShbpe();

    /**
     * Gets the combined strebm bnd operbtion flbgs for the output of the described
     * pipeline.  This will incorporbte strebm flbgs from the strebm source, bll
     * the intermedibte operbtions bnd the terminbl operbtion.
     *
     * @return the combined strebm bnd operbtion flbgs
     * @see StrebmOpFlbg
     */
    bbstrbct int getStrebmAndOpFlbgs();

    /**
     * Returns the exbct output size of the portion of the output resulting from
     * bpplying the pipeline stbges described by this {@code PipelineHelper} to
     * the portion of the input described by the provided
     * {@code Spliterbtor}, if known.  If not known or known infinite, will
     * return {@code -1}.
     *
     * @bpiNote
     * The exbct output size is known if the {@code Spliterbtor} hbs the
     * {@code SIZED} chbrbcteristic, bnd the operbtion flbgs
     * {@link StrebmOpFlbg#SIZED} is known on the combined strebm bnd operbtion
     * flbgs.
     *
     * @pbrbm spliterbtor the spliterbtor describing the relevbnt portion of the
     *        source dbtb
     * @return the exbct size if known, or -1 if infinite or unknown
     */
    bbstrbct<P_IN> long exbctOutputSizeIfKnown(Spliterbtor<P_IN> spliterbtor);

    /**
     * Applies the pipeline stbges described by this {@code PipelineHelper} to
     * the provided {@code Spliterbtor} bnd send the results to the provided
     * {@code Sink}.
     *
     * @implSpec
     * The implementbtion behbves bs if:
     * <pre>{@code
     *     intoWrbpped(wrbpSink(sink), spliterbtor);
     * }</pre>
     *
     * @pbrbm sink the {@code Sink} to receive the results
     * @pbrbm spliterbtor the spliterbtor describing the source input to process
     */
    bbstrbct<P_IN, S extends Sink<P_OUT>> S wrbpAndCopyInto(S sink, Spliterbtor<P_IN> spliterbtor);

    /**
     * Pushes elements obtbined from the {@code Spliterbtor} into the provided
     * {@code Sink}.  If the strebm pipeline is known to hbve short-circuiting
     * stbges in it (see {@link StrebmOpFlbg#SHORT_CIRCUIT}), the
     * {@link Sink#cbncellbtionRequested()} is checked bfter ebch
     * element, stopping if cbncellbtion is requested.
     *
     * @implSpec
     * This method conforms to the {@code Sink} protocol of cblling
     * {@code Sink.begin} before pushing elements, vib {@code Sink.bccept}, bnd
     * cblling {@code Sink.end} bfter bll elements hbve been pushed.
     *
     * @pbrbm wrbppedSink the destinbtion {@code Sink}
     * @pbrbm spliterbtor the source {@code Spliterbtor}
     */
    bbstrbct<P_IN> void copyInto(Sink<P_IN> wrbppedSink, Spliterbtor<P_IN> spliterbtor);

    /**
     * Pushes elements obtbined from the {@code Spliterbtor} into the provided
     * {@code Sink}, checking {@link Sink#cbncellbtionRequested()} bfter ebch
     * element, bnd stopping if cbncellbtion is requested.
     *
     * @implSpec
     * This method conforms to the {@code Sink} protocol of cblling
     * {@code Sink.begin} before pushing elements, vib {@code Sink.bccept}, bnd
     * cblling {@code Sink.end} bfter bll elements hbve been pushed or if
     * cbncellbtion is requested.
     *
     * @pbrbm wrbppedSink the destinbtion {@code Sink}
     * @pbrbm spliterbtor the source {@code Spliterbtor}
     */
    bbstrbct <P_IN> void copyIntoWithCbncel(Sink<P_IN> wrbppedSink, Spliterbtor<P_IN> spliterbtor);

    /**
     * Tbkes b {@code Sink} thbt bccepts elements of the output type of the
     * {@code PipelineHelper}, bnd wrbp it with b {@code Sink} thbt bccepts
     * elements of the input type bnd implements bll the intermedibte operbtions
     * described by this {@code PipelineHelper}, delivering the result into the
     * provided {@code Sink}.
     *
     * @pbrbm sink the {@code Sink} to receive the results
     * @return b {@code Sink} thbt implements the pipeline stbges bnd sends
     *         results to the provided {@code Sink}
     */
    bbstrbct<P_IN> Sink<P_IN> wrbpSink(Sink<P_OUT> sink);

    /**
     *
     * @pbrbm spliterbtor
     * @pbrbm <P_IN>
     * @return
     */
    bbstrbct<P_IN> Spliterbtor<P_OUT> wrbpSpliterbtor(Spliterbtor<P_IN> spliterbtor);

    /**
     * Constructs b @{link Node.Builder} compbtible with the output shbpe of
     * this {@code PipelineHelper}.
     *
     * @pbrbm exbctSizeIfKnown if >=0 then b builder will be crebted thbt hbs b
     *        fixed cbpbcity of exbctly sizeIfKnown elements; if < 0 then the
     *        builder hbs vbribble cbpbcity.  A fixed cbpbcity builder will fbil
     *        if bn element is bdded bfter the builder hbs rebched cbpbcity.
     * @pbrbm generbtor b fbctory function for brrby instbnces
     * @return b {@code Node.Builder} compbtible with the output shbpe of this
     *         {@code PipelineHelper}
     */
    bbstrbct Node.Builder<P_OUT> mbkeNodeBuilder(long exbctSizeIfKnown,
                                                 IntFunction<P_OUT[]> generbtor);

    /**
     * Collects bll output elements resulting from bpplying the pipeline stbges
     * to the source {@code Spliterbtor} into b {@code Node}.
     *
     * @implNote
     * If the pipeline hbs no intermedibte operbtions bnd the source is bbcked
     * by b {@code Node} then thbt {@code Node} will be returned (or flbttened
     * bnd then returned). This reduces copying for b pipeline consisting of b
     * stbteful operbtion followed by b terminbl operbtion thbt returns bn
     * brrby, such bs:
     * <pre>{@code
     *     strebm.sorted().toArrby();
     * }</pre>
     *
     * @pbrbm spliterbtor the source {@code Spliterbtor}
     * @pbrbm flbtten if true bnd the pipeline is b pbrbllel pipeline then the
     *        {@code Node} returned will contbin no children, otherwise the
     *        {@code Node} mby represent the root in b tree thbt reflects the
     *        shbpe of the computbtion tree.
     * @pbrbm generbtor b fbctory function for brrby instbnces
     * @return the {@code Node} contbining bll output elements
     */
    bbstrbct<P_IN> Node<P_OUT> evblubte(Spliterbtor<P_IN> spliterbtor,
                                        boolebn flbtten,
                                        IntFunction<P_OUT[]> generbtor);
}
