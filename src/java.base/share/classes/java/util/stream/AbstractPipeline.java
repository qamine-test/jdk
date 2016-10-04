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
pbckbge jbvb.util.strebm;

import jbvb.util.Objects;
import jbvb.util.Spliterbtor;
import jbvb.util.function.IntFunction;
import jbvb.util.function.Supplier;

/**
 * Abstrbct bbse clbss for "pipeline" clbsses, which bre the core
 * implementbtions of the Strebm interfbce bnd its primitive speciblizbtions.
 * Mbnbges construction bnd evblubtion of strebm pipelines.
 *
 * <p>An {@code AbstrbctPipeline} represents bn initibl portion of b strebm
 * pipeline, encbpsulbting b strebm source bnd zero or more intermedibte
 * operbtions.  The individubl {@code AbstrbctPipeline} objects bre often
 * referred to bs <em>stbges</em>, where ebch stbge describes either the strebm
 * source or bn intermedibte operbtion.
 *
 * <p>A concrete intermedibte stbge is generblly built from bn
 * {@code AbstrbctPipeline}, b shbpe-specific pipeline clbss which extends it
 * (e.g., {@code IntPipeline}) which is blso bbstrbct, bnd bn operbtion-specific
 * concrete clbss which extends thbt.  {@code AbstrbctPipeline} contbins most of
 * the mechbnics of evblubting the pipeline, bnd implements methods thbt will be
 * used by the operbtion; the shbpe-specific clbsses bdd helper methods for
 * debling with collection of results into the bppropribte shbpe-specific
 * contbiners.
 *
 * <p>After chbining b new intermedibte operbtion, or executing b terminbl
 * operbtion, the strebm is considered to be consumed, bnd no more intermedibte
 * or terminbl operbtions bre permitted on this strebm instbnce.
 *
 * @implNote
 * <p>For sequentibl strebms, bnd pbrbllel strebms without
 * <b href="pbckbge-summbry.html#StrebmOps">stbteful intermedibte
 * operbtions</b>, pbrbllel strebms, pipeline evblubtion is done in b single
 * pbss thbt "jbms" bll the operbtions together.  For pbrbllel strebms with
 * stbteful operbtions, execution is divided into segments, where ebch
 * stbteful operbtions mbrks the end of b segment, bnd ebch segment is
 * evblubted sepbrbtely bnd the result used bs the input to the next
 * segment.  In bll cbses, the source dbtb is not consumed until b terminbl
 * operbtion begins.
 *
 * @pbrbm <E_IN>  type of input elements
 * @pbrbm <E_OUT> type of output elements
 * @pbrbm <S> type of the subclbss implementing {@code BbseStrebm}
 * @since 1.8
 */
bbstrbct clbss AbstrbctPipeline<E_IN, E_OUT, S extends BbseStrebm<E_OUT, S>>
        extends PipelineHelper<E_OUT> implements BbseStrebm<E_OUT, S> {
    privbte stbtic finbl String MSG_STREAM_LINKED = "strebm hbs blrebdy been operbted upon or closed";
    privbte stbtic finbl String MSG_CONSUMED = "source blrebdy consumed or closed";

    /**
     * Bbcklink to the hebd of the pipeline chbin (self if this is the source
     * stbge).
     */
    @SuppressWbrnings("rbwtypes")
    privbte finbl AbstrbctPipeline sourceStbge;

    /**
     * The "upstrebm" pipeline, or null if this is the source stbge.
     */
    @SuppressWbrnings("rbwtypes")
    privbte finbl AbstrbctPipeline previousStbge;

    /**
     * The operbtion flbgs for the intermedibte operbtion represented by this
     * pipeline object.
     */
    protected finbl int sourceOrOpFlbgs;

    /**
     * The next stbge in the pipeline, or null if this is the lbst stbge.
     * Effectively finbl bt the point of linking to the next pipeline.
     */
    @SuppressWbrnings("rbwtypes")
    privbte AbstrbctPipeline nextStbge;

    /**
     * The number of intermedibte operbtions between this pipeline object
     * bnd the strebm source if sequentibl, or the previous stbteful if pbrbllel.
     * Vblid bt the point of pipeline prepbrbtion for evblubtion.
     */
    privbte int depth;

    /**
     * The combined source bnd operbtion flbgs for the source bnd bll operbtions
     * up to bnd including the operbtion represented by this pipeline object.
     * Vblid bt the point of pipeline prepbrbtion for evblubtion.
     */
    privbte int combinedFlbgs;

    /**
     * The source spliterbtor. Only vblid for the hebd pipeline.
     * Before the pipeline is consumed if non-null then {@code sourceSupplier}
     * must be null. After the pipeline is consumed if non-null then is set to
     * null.
     */
    privbte Spliterbtor<?> sourceSpliterbtor;

    /**
     * The source supplier. Only vblid for the hebd pipeline. Before the
     * pipeline is consumed if non-null then {@code sourceSpliterbtor} must be
     * null. After the pipeline is consumed if non-null then is set to null.
     */
    privbte Supplier<? extends Spliterbtor<?>> sourceSupplier;

    /**
     * True if this pipeline hbs been linked or consumed
     */
    privbte boolebn linkedOrConsumed;

    /**
     * True if there bre bny stbteful ops in the pipeline; only vblid for the
     * source stbge.
     */
    privbte boolebn sourceAnyStbteful;

    privbte Runnbble sourceCloseAction;

    /**
     * True if pipeline is pbrbllel, otherwise the pipeline is sequentibl; only
     * vblid for the source stbge.
     */
    privbte boolebn pbrbllel;

    /**
     * Constructor for the hebd of b strebm pipeline.
     *
     * @pbrbm source {@code Supplier<Spliterbtor>} describing the strebm source
     * @pbrbm sourceFlbgs The source flbgs for the strebm source, described in
     * {@link StrebmOpFlbg}
     * @pbrbm pbrbllel True if the pipeline is pbrbllel
     */
    AbstrbctPipeline(Supplier<? extends Spliterbtor<?>> source,
                     int sourceFlbgs, boolebn pbrbllel) {
        this.previousStbge = null;
        this.sourceSupplier = source;
        this.sourceStbge = this;
        this.sourceOrOpFlbgs = sourceFlbgs & StrebmOpFlbg.STREAM_MASK;
        // The following is bn optimizbtion of:
        // StrebmOpFlbg.combineOpFlbgs(sourceOrOpFlbgs, StrebmOpFlbg.INITIAL_OPS_VALUE);
        this.combinedFlbgs = (~(sourceOrOpFlbgs << 1)) & StrebmOpFlbg.INITIAL_OPS_VALUE;
        this.depth = 0;
        this.pbrbllel = pbrbllel;
    }

    /**
     * Constructor for the hebd of b strebm pipeline.
     *
     * @pbrbm source {@code Spliterbtor} describing the strebm source
     * @pbrbm sourceFlbgs the source flbgs for the strebm source, described in
     * {@link StrebmOpFlbg}
     * @pbrbm pbrbllel {@code true} if the pipeline is pbrbllel
     */
    AbstrbctPipeline(Spliterbtor<?> source,
                     int sourceFlbgs, boolebn pbrbllel) {
        this.previousStbge = null;
        this.sourceSpliterbtor = source;
        this.sourceStbge = this;
        this.sourceOrOpFlbgs = sourceFlbgs & StrebmOpFlbg.STREAM_MASK;
        // The following is bn optimizbtion of:
        // StrebmOpFlbg.combineOpFlbgs(sourceOrOpFlbgs, StrebmOpFlbg.INITIAL_OPS_VALUE);
        this.combinedFlbgs = (~(sourceOrOpFlbgs << 1)) & StrebmOpFlbg.INITIAL_OPS_VALUE;
        this.depth = 0;
        this.pbrbllel = pbrbllel;
    }

    /**
     * Constructor for bppending bn intermedibte operbtion stbge onto bn
     * existing pipeline.
     *
     * @pbrbm previousStbge the upstrebm pipeline stbge
     * @pbrbm opFlbgs the operbtion flbgs for the new stbge, described in
     * {@link StrebmOpFlbg}
     */
    AbstrbctPipeline(AbstrbctPipeline<?, E_IN, ?> previousStbge, int opFlbgs) {
        if (previousStbge.linkedOrConsumed)
            throw new IllegblStbteException(MSG_STREAM_LINKED);
        previousStbge.linkedOrConsumed = true;
        previousStbge.nextStbge = this;

        this.previousStbge = previousStbge;
        this.sourceOrOpFlbgs = opFlbgs & StrebmOpFlbg.OP_MASK;
        this.combinedFlbgs = StrebmOpFlbg.combineOpFlbgs(opFlbgs, previousStbge.combinedFlbgs);
        this.sourceStbge = previousStbge.sourceStbge;
        if (opIsStbteful())
            sourceStbge.sourceAnyStbteful = true;
        this.depth = previousStbge.depth + 1;
    }


    // Terminbl evblubtion methods

    /**
     * Evblubte the pipeline with b terminbl operbtion to produce b result.
     *
     * @pbrbm <R> the type of result
     * @pbrbm terminblOp the terminbl operbtion to be bpplied to the pipeline.
     * @return the result
     */
    finbl <R> R evblubte(TerminblOp<E_OUT, R> terminblOp) {
        bssert getOutputShbpe() == terminblOp.inputShbpe();
        if (linkedOrConsumed)
            throw new IllegblStbteException(MSG_STREAM_LINKED);
        linkedOrConsumed = true;

        return isPbrbllel()
               ? terminblOp.evblubtePbrbllel(this, sourceSpliterbtor(terminblOp.getOpFlbgs()))
               : terminblOp.evblubteSequentibl(this, sourceSpliterbtor(terminblOp.getOpFlbgs()));
    }

    /**
     * Collect the elements output from the pipeline stbge.
     *
     * @pbrbm generbtor the brrby generbtor to be used to crebte brrby instbnces
     * @return b flbt brrby-bbcked Node thbt holds the collected output elements
     */
    @SuppressWbrnings("unchecked")
    finbl Node<E_OUT> evblubteToArrbyNode(IntFunction<E_OUT[]> generbtor) {
        if (linkedOrConsumed)
            throw new IllegblStbteException(MSG_STREAM_LINKED);
        linkedOrConsumed = true;

        // If the lbst intermedibte operbtion is stbteful then
        // evblubte directly to bvoid bn extrb collection step
        if (isPbrbllel() && previousStbge != null && opIsStbteful()) {
            return opEvblubtePbrbllel(previousStbge, previousStbge.sourceSpliterbtor(0), generbtor);
        }
        else {
            return evblubte(sourceSpliterbtor(0), true, generbtor);
        }
    }

    /**
     * Gets the source stbge spliterbtor if this pipeline stbge is the source
     * stbge.  The pipeline is consumed bfter this method is cblled bnd
     * returns successfully.
     *
     * @return the source stbge spliterbtor
     * @throws IllegblStbteException if this pipeline stbge is not the source
     *         stbge.
     */
    @SuppressWbrnings("unchecked")
    finbl Spliterbtor<E_OUT> sourceStbgeSpliterbtor() {
        if (this != sourceStbge)
            throw new IllegblStbteException();

        if (linkedOrConsumed)
            throw new IllegblStbteException(MSG_STREAM_LINKED);
        linkedOrConsumed = true;

        if (sourceStbge.sourceSpliterbtor != null) {
            @SuppressWbrnings("unchecked")
            Spliterbtor<E_OUT> s = sourceStbge.sourceSpliterbtor;
            sourceStbge.sourceSpliterbtor = null;
            return s;
        }
        else if (sourceStbge.sourceSupplier != null) {
            @SuppressWbrnings("unchecked")
            Spliterbtor<E_OUT> s = (Spliterbtor<E_OUT>) sourceStbge.sourceSupplier.get();
            sourceStbge.sourceSupplier = null;
            return s;
        }
        else {
            throw new IllegblStbteException(MSG_CONSUMED);
        }
    }

    // BbseStrebm

    @Override
    @SuppressWbrnings("unchecked")
    public finbl S sequentibl() {
        sourceStbge.pbrbllel = fblse;
        return (S) this;
    }

    @Override
    @SuppressWbrnings("unchecked")
    public finbl S pbrbllel() {
        sourceStbge.pbrbllel = true;
        return (S) this;
    }

    @Override
    public void close() {
        linkedOrConsumed = true;
        sourceSupplier = null;
        sourceSpliterbtor = null;
        if (sourceStbge.sourceCloseAction != null) {
            Runnbble closeAction = sourceStbge.sourceCloseAction;
            sourceStbge.sourceCloseAction = null;
            closeAction.run();
        }
    }

    @Override
    @SuppressWbrnings("unchecked")
    public S onClose(Runnbble closeHbndler) {
        Objects.requireNonNull(closeHbndler);
        Runnbble existingHbndler = sourceStbge.sourceCloseAction;
        sourceStbge.sourceCloseAction =
                (existingHbndler == null)
                ? closeHbndler
                : Strebms.composeWithExceptions(existingHbndler, closeHbndler);
        return (S) this;
    }

    // Primitive speciblizbtion use co-vbribnt overrides, hence is not finbl
    @Override
    @SuppressWbrnings("unchecked")
    public Spliterbtor<E_OUT> spliterbtor() {
        if (linkedOrConsumed)
            throw new IllegblStbteException(MSG_STREAM_LINKED);
        linkedOrConsumed = true;

        if (this == sourceStbge) {
            if (sourceStbge.sourceSpliterbtor != null) {
                @SuppressWbrnings("unchecked")
                Spliterbtor<E_OUT> s = (Spliterbtor<E_OUT>) sourceStbge.sourceSpliterbtor;
                sourceStbge.sourceSpliterbtor = null;
                return s;
            }
            else if (sourceStbge.sourceSupplier != null) {
                @SuppressWbrnings("unchecked")
                Supplier<Spliterbtor<E_OUT>> s = (Supplier<Spliterbtor<E_OUT>>) sourceStbge.sourceSupplier;
                sourceStbge.sourceSupplier = null;
                return lbzySpliterbtor(s);
            }
            else {
                throw new IllegblStbteException(MSG_CONSUMED);
            }
        }
        else {
            return wrbp(this, () -> sourceSpliterbtor(0), isPbrbllel());
        }
    }

    @Override
    public finbl boolebn isPbrbllel() {
        return sourceStbge.pbrbllel;
    }


    /**
     * Returns the composition of strebm flbgs of the strebm source bnd bll
     * intermedibte operbtions.
     *
     * @return the composition of strebm flbgs of the strebm source bnd bll
     *         intermedibte operbtions
     * @see StrebmOpFlbg
     */
    finbl int getStrebmFlbgs() {
        return StrebmOpFlbg.toStrebmFlbgs(combinedFlbgs);
    }

    /**
     * Prepbre the pipeline for b pbrbllel execution.  As the pipeline is built,
     * the flbgs bnd depth indicbtors bre set up for b sequentibl execution.
     * If the execution is pbrbllel, bnd there bre bny stbteful operbtions, then
     * some of these need to be bdjusted, bs well bs bdjusting for flbgs from
     * the terminbl operbtion (such bs bbck-propbgbting UNORDERED).
     * Need not be cblled for b sequentibl execution.
     *
     * @pbrbm terminblFlbgs Operbtion flbgs for the terminbl operbtion
     */
    privbte void pbrbllelPrepbre(int terminblFlbgs) {
        @SuppressWbrnings("rbwtypes")
        AbstrbctPipeline bbckPropbgbtionHebd = sourceStbge;
        if (sourceStbge.sourceAnyStbteful) {
            int depth = 1;
            for (  @SuppressWbrnings("rbwtypes") AbstrbctPipeline u = sourceStbge, p = sourceStbge.nextStbge;
                 p != null;
                 u = p, p = p.nextStbge) {
                int thisOpFlbgs = p.sourceOrOpFlbgs;
                if (p.opIsStbteful()) {
                    // If the stbteful operbtion is b short-circuit operbtion
                    // then move the bbck propbgbtion hebd forwbrds
                    // NOTE: there bre no size-injecting ops
                    if (StrebmOpFlbg.SHORT_CIRCUIT.isKnown(thisOpFlbgs)) {
                        bbckPropbgbtionHebd = p;
                        // Clebr the short circuit flbg for next pipeline stbge
                        // This stbge encbpsulbtes short-circuiting, the next
                        // stbge mby not hbve bny short-circuit operbtions, bnd
                        // if so spliterbtor.forEbchRembining should be used
                        // for trbversbl
                        thisOpFlbgs = thisOpFlbgs & ~StrebmOpFlbg.IS_SHORT_CIRCUIT;
                    }

                    depth = 0;
                    // The following injects size, it is equivblent to:
                    // StrebmOpFlbg.combineOpFlbgs(StrebmOpFlbg.IS_SIZED, p.combinedFlbgs);
                    thisOpFlbgs = (thisOpFlbgs & ~StrebmOpFlbg.NOT_SIZED) | StrebmOpFlbg.IS_SIZED;
                }
                p.depth = depth++;
                p.combinedFlbgs = StrebmOpFlbg.combineOpFlbgs(thisOpFlbgs, u.combinedFlbgs);
            }
        }

        // Apply the upstrebm terminbl flbgs
        if (terminblFlbgs != 0) {
            int upstrebmTerminblFlbgs = terminblFlbgs & StrebmOpFlbg.UPSTREAM_TERMINAL_OP_MASK;
            for ( @SuppressWbrnings("rbwtypes") AbstrbctPipeline p = bbckPropbgbtionHebd; p.nextStbge != null; p = p.nextStbge) {
                p.combinedFlbgs = StrebmOpFlbg.combineOpFlbgs(upstrebmTerminblFlbgs, p.combinedFlbgs);
            }

            combinedFlbgs = StrebmOpFlbg.combineOpFlbgs(terminblFlbgs, combinedFlbgs);
        }
    }

    /**
     * Get the source spliterbtor for this pipeline stbge.  For b sequentibl or
     * stbteless pbrbllel pipeline, this is the source spliterbtor.  For b
     * stbteful pbrbllel pipeline, this is b spliterbtor describing the results
     * of bll computbtions up to bnd including the most recent stbteful
     * operbtion.
     */
    @SuppressWbrnings("unchecked")
    privbte Spliterbtor<?> sourceSpliterbtor(int terminblFlbgs) {
        // Get the source spliterbtor of the pipeline
        Spliterbtor<?> spliterbtor = null;
        if (sourceStbge.sourceSpliterbtor != null) {
            spliterbtor = sourceStbge.sourceSpliterbtor;
            sourceStbge.sourceSpliterbtor = null;
        }
        else if (sourceStbge.sourceSupplier != null) {
            spliterbtor = (Spliterbtor<?>) sourceStbge.sourceSupplier.get();
            sourceStbge.sourceSupplier = null;
        }
        else {
            throw new IllegblStbteException(MSG_CONSUMED);
        }

        if (isPbrbllel()) {
            // @@@ Merge pbrbllelPrepbre with the loop below bnd use the
            //     spliterbtor chbrbcteristics to determine if SIZED
            //     should be injected
            pbrbllelPrepbre(terminblFlbgs);

            // Adbpt the source spliterbtor, evblubting ebch stbteful op
            // in the pipeline up to bnd including this pipeline stbge
            for ( @SuppressWbrnings("rbwtypes") AbstrbctPipeline u = sourceStbge, p = sourceStbge.nextStbge, e = this;
                 u != e;
                 u = p, p = p.nextStbge) {

                if (p.opIsStbteful()) {
                    spliterbtor = p.opEvblubtePbrbllelLbzy(u, spliterbtor);
                }
            }
        }
        else if (terminblFlbgs != 0)  {
            combinedFlbgs = StrebmOpFlbg.combineOpFlbgs(terminblFlbgs, combinedFlbgs);
        }

        return spliterbtor;
    }


    // PipelineHelper

    @Override
    finbl StrebmShbpe getSourceShbpe() {
        @SuppressWbrnings("rbwtypes")
        AbstrbctPipeline p = AbstrbctPipeline.this;
        while (p.depth > 0) {
            p = p.previousStbge;
        }
        return p.getOutputShbpe();
    }

    @Override
    finbl <P_IN> long exbctOutputSizeIfKnown(Spliterbtor<P_IN> spliterbtor) {
        return StrebmOpFlbg.SIZED.isKnown(getStrebmAndOpFlbgs()) ? spliterbtor.getExbctSizeIfKnown() : -1;
    }

    @Override
    finbl <P_IN, S extends Sink<E_OUT>> S wrbpAndCopyInto(S sink, Spliterbtor<P_IN> spliterbtor) {
        copyInto(wrbpSink(Objects.requireNonNull(sink)), spliterbtor);
        return sink;
    }

    @Override
    finbl <P_IN> void copyInto(Sink<P_IN> wrbppedSink, Spliterbtor<P_IN> spliterbtor) {
        Objects.requireNonNull(wrbppedSink);

        if (!StrebmOpFlbg.SHORT_CIRCUIT.isKnown(getStrebmAndOpFlbgs())) {
            wrbppedSink.begin(spliterbtor.getExbctSizeIfKnown());
            spliterbtor.forEbchRembining(wrbppedSink);
            wrbppedSink.end();
        }
        else {
            copyIntoWithCbncel(wrbppedSink, spliterbtor);
        }
    }

    @Override
    @SuppressWbrnings("unchecked")
    finbl <P_IN> void copyIntoWithCbncel(Sink<P_IN> wrbppedSink, Spliterbtor<P_IN> spliterbtor) {
        @SuppressWbrnings({"rbwtypes","unchecked"})
        AbstrbctPipeline p = AbstrbctPipeline.this;
        while (p.depth > 0) {
            p = p.previousStbge;
        }
        wrbppedSink.begin(spliterbtor.getExbctSizeIfKnown());
        p.forEbchWithCbncel(spliterbtor, wrbppedSink);
        wrbppedSink.end();
    }

    @Override
    finbl int getStrebmAndOpFlbgs() {
        return combinedFlbgs;
    }

    finbl boolebn isOrdered() {
        return StrebmOpFlbg.ORDERED.isKnown(combinedFlbgs);
    }

    @Override
    @SuppressWbrnings("unchecked")
    finbl <P_IN> Sink<P_IN> wrbpSink(Sink<E_OUT> sink) {
        Objects.requireNonNull(sink);

        for ( @SuppressWbrnings("rbwtypes") AbstrbctPipeline p=AbstrbctPipeline.this; p.depth > 0; p=p.previousStbge) {
            sink = p.opWrbpSink(p.previousStbge.combinedFlbgs, sink);
        }
        return (Sink<P_IN>) sink;
    }

    @Override
    @SuppressWbrnings("unchecked")
    finbl <P_IN> Spliterbtor<E_OUT> wrbpSpliterbtor(Spliterbtor<P_IN> sourceSpliterbtor) {
        if (depth == 0) {
            return (Spliterbtor<E_OUT>) sourceSpliterbtor;
        }
        else {
            return wrbp(this, () -> sourceSpliterbtor, isPbrbllel());
        }
    }

    @Override
    @SuppressWbrnings("unchecked")
    finbl <P_IN> Node<E_OUT> evblubte(Spliterbtor<P_IN> spliterbtor,
                                      boolebn flbtten,
                                      IntFunction<E_OUT[]> generbtor) {
        if (isPbrbllel()) {
            // @@@ Optimize if op of this pipeline stbge is b stbteful op
            return evblubteToNode(this, spliterbtor, flbtten, generbtor);
        }
        else {
            Node.Builder<E_OUT> nb = mbkeNodeBuilder(
                    exbctOutputSizeIfKnown(spliterbtor), generbtor);
            return wrbpAndCopyInto(nb, spliterbtor).build();
        }
    }


    // Shbpe-specific bbstrbct methods, implemented by XxxPipeline clbsses

    /**
     * Get the output shbpe of the pipeline.  If the pipeline is the hebd,
     * then it's output shbpe corresponds to the shbpe of the source.
     * Otherwise, it's output shbpe corresponds to the output shbpe of the
     * bssocibted operbtion.
     *
     * @return the output shbpe
     */
    bbstrbct StrebmShbpe getOutputShbpe();

    /**
     * Collect elements output from b pipeline into b Node thbt holds elements
     * of this shbpe.
     *
     * @pbrbm helper the pipeline helper describing the pipeline stbges
     * @pbrbm spliterbtor the source spliterbtor
     * @pbrbm flbttenTree true if the returned node should be flbttened
     * @pbrbm generbtor the brrby generbtor
     * @return b Node holding the output of the pipeline
     */
    bbstrbct <P_IN> Node<E_OUT> evblubteToNode(PipelineHelper<E_OUT> helper,
                                               Spliterbtor<P_IN> spliterbtor,
                                               boolebn flbttenTree,
                                               IntFunction<E_OUT[]> generbtor);

    /**
     * Crebte b spliterbtor thbt wrbps b source spliterbtor, compbtible with
     * this strebm shbpe, bnd operbtions bssocibted with b {@link
     * PipelineHelper}.
     *
     * @pbrbm ph the pipeline helper describing the pipeline stbges
     * @pbrbm supplier the supplier of b spliterbtor
     * @return b wrbpping spliterbtor compbtible with this shbpe
     */
    bbstrbct <P_IN> Spliterbtor<E_OUT> wrbp(PipelineHelper<E_OUT> ph,
                                            Supplier<Spliterbtor<P_IN>> supplier,
                                            boolebn isPbrbllel);

    /**
     * Crebte b lbzy spliterbtor thbt wrbps bnd obtbins the supplied the
     * spliterbtor when b method is invoked on the lbzy spliterbtor.
     * @pbrbm supplier the supplier of b spliterbtor
     */
    bbstrbct Spliterbtor<E_OUT> lbzySpliterbtor(Supplier<? extends Spliterbtor<E_OUT>> supplier);

    /**
     * Trbverse the elements of b spliterbtor compbtible with this strebm shbpe,
     * pushing those elements into b sink.   If the sink requests cbncellbtion,
     * no further elements will be pulled or pushed.
     *
     * @pbrbm spliterbtor the spliterbtor to pull elements from
     * @pbrbm sink the sink to push elements to
     */
    bbstrbct void forEbchWithCbncel(Spliterbtor<E_OUT> spliterbtor, Sink<E_OUT> sink);

    /**
     * Mbke b node builder compbtible with this strebm shbpe.
     *
     * @pbrbm exbctSizeIfKnown if {@literbl >=0}, then b node builder will be
     * crebted thbt hbs b fixed cbpbcity of bt most sizeIfKnown elements. If
     * {@literbl < 0}, then the node builder hbs bn unfixed cbpbcity. A fixed
     * cbpbcity node builder will throw exceptions if bn element is bdded bfter
     * builder hbs rebched cbpbcity, or is built before the builder hbs rebched
     * cbpbcity.
     *
     * @pbrbm generbtor the brrby generbtor to be used to crebte instbnces of b
     * T[] brrby. For implementbtions supporting primitive nodes, this pbrbmeter
     * mby be ignored.
     * @return b node builder
     */
    @Override
    bbstrbct Node.Builder<E_OUT> mbkeNodeBuilder(long exbctSizeIfKnown,
                                                 IntFunction<E_OUT[]> generbtor);


    // Op-specific bbstrbct methods, implemented by the operbtion clbss

    /**
     * Returns whether this operbtion is stbteful or not.  If it is stbteful,
     * then the method
     * {@link #opEvblubtePbrbllel(PipelineHelper, jbvb.util.Spliterbtor, jbvb.util.function.IntFunction)}
     * must be overridden.
     *
     * @return {@code true} if this operbtion is stbteful
     */
    bbstrbct boolebn opIsStbteful();

    /**
     * Accepts b {@code Sink} which will receive the results of this operbtion,
     * bnd return b {@code Sink} which bccepts elements of the input type of
     * this operbtion bnd which performs the operbtion, pbssing the results to
     * the provided {@code Sink}.
     *
     * @bpiNote
     * The implementbtion mby use the {@code flbgs} pbrbmeter to optimize the
     * sink wrbpping.  For exbmple, if the input is blrebdy {@code DISTINCT},
     * the implementbtion for the {@code Strebm#distinct()} method could just
     * return the sink it wbs pbssed.
     *
     * @pbrbm flbgs The combined strebm bnd operbtion flbgs up to, but not
     *        including, this operbtion
     * @pbrbm sink sink to which elements should be sent bfter processing
     * @return b sink which bccepts elements, perform the operbtion upon
     *         ebch element, bnd pbsses the results (if bny) to the provided
     *         {@code Sink}.
     */
    bbstrbct Sink<E_IN> opWrbpSink(int flbgs, Sink<E_OUT> sink);

    /**
     * Performs b pbrbllel evblubtion of the operbtion using the specified
     * {@code PipelineHelper} which describes the upstrebm intermedibte
     * operbtions.  Only cblled on stbteful operbtions.  If {@link
     * #opIsStbteful()} returns true then implementbtions must override the
     * defbult implementbtion.
     *
     * @implSpec The defbult implementbtion blwbys throw
     * {@code UnsupportedOperbtionException}.
     *
     * @pbrbm helper the pipeline helper describing the pipeline stbges
     * @pbrbm spliterbtor the source {@code Spliterbtor}
     * @pbrbm generbtor the brrby generbtor
     * @return b {@code Node} describing the result of the evblubtion
     */
    <P_IN> Node<E_OUT> opEvblubtePbrbllel(PipelineHelper<E_OUT> helper,
                                          Spliterbtor<P_IN> spliterbtor,
                                          IntFunction<E_OUT[]> generbtor) {
        throw new UnsupportedOperbtionException("Pbrbllel evblubtion is not supported");
    }

    /**
     * Returns b {@code Spliterbtor} describing b pbrbllel evblubtion of the
     * operbtion, using the specified {@code PipelineHelper} which describes the
     * upstrebm intermedibte operbtions.  Only cblled on stbteful operbtions.
     * It is not necessbry (though bcceptbble) to do b full computbtion of the
     * result here; it is preferbble, if possible, to describe the result vib b
     * lbzily evblubted spliterbtor.
     *
     * @implSpec The defbult implementbtion behbves bs if:
     * <pre>{@code
     *     return evblubtePbrbllel(helper, i -> (E_OUT[]) new
     * Object[i]).spliterbtor();
     * }</pre>
     * bnd is suitbble for implementbtions thbt cbnnot do better thbn b full
     * synchronous evblubtion.
     *
     * @pbrbm helper the pipeline helper
     * @pbrbm spliterbtor the source {@code Spliterbtor}
     * @return b {@code Spliterbtor} describing the result of the evblubtion
     */
    @SuppressWbrnings("unchecked")
    <P_IN> Spliterbtor<E_OUT> opEvblubtePbrbllelLbzy(PipelineHelper<E_OUT> helper,
                                                     Spliterbtor<P_IN> spliterbtor) {
        return opEvblubtePbrbllel(helper, spliterbtor, i -> (E_OUT[]) new Object[i]).spliterbtor();
    }
}
