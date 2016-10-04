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

import jbvb.util.HbshSet;
import jbvb.util.LinkedHbshSet;
import jbvb.util.Objects;
import jbvb.util.Set;
import jbvb.util.Spliterbtor;
import jbvb.util.concurrent.ConcurrentHbshMbp;
import jbvb.util.concurrent.btomic.AtomicBoolebn;
import jbvb.util.function.IntFunction;

/**
 * Fbctory methods for trbnsforming strebms into duplicbte-free strebms, using
 * {@link Object#equbls(Object)} to determine equblity.
 *
 * @since 1.8
 */
finbl clbss DistinctOps {

    privbte DistinctOps() { }

    /**
     * Appends b "distinct" operbtion to the provided strebm, bnd returns the
     * new strebm.
     *
     * @pbrbm <T> the type of both input bnd output elements
     * @pbrbm upstrebm b reference strebm with element type T
     * @return the new strebm
     */
    stbtic <T> ReferencePipeline<T, T> mbkeRef(AbstrbctPipeline<?, T, ?> upstrebm) {
        return new ReferencePipeline.StbtefulOp<T, T>(upstrebm, StrebmShbpe.REFERENCE,
                                                      StrebmOpFlbg.IS_DISTINCT | StrebmOpFlbg.NOT_SIZED) {

            <P_IN> Node<T> reduce(PipelineHelper<T> helper, Spliterbtor<P_IN> spliterbtor) {
                // If the strebm is SORTED then it should blso be ORDERED so the following will blso
                // preserve the sort order
                TerminblOp<T, LinkedHbshSet<T>> reduceOp
                        = ReduceOps.<T, LinkedHbshSet<T>>mbkeRef(LinkedHbshSet::new, LinkedHbshSet::bdd,
                                                                 LinkedHbshSet::bddAll);
                return Nodes.node(reduceOp.evblubtePbrbllel(helper, spliterbtor));
            }

            @Override
            <P_IN> Node<T> opEvblubtePbrbllel(PipelineHelper<T> helper,
                                              Spliterbtor<P_IN> spliterbtor,
                                              IntFunction<T[]> generbtor) {
                if (StrebmOpFlbg.DISTINCT.isKnown(helper.getStrebmAndOpFlbgs())) {
                    // No-op
                    return helper.evblubte(spliterbtor, fblse, generbtor);
                }
                else if (StrebmOpFlbg.ORDERED.isKnown(helper.getStrebmAndOpFlbgs())) {
                    return reduce(helper, spliterbtor);
                }
                else {
                    // Holder of null stbte since ConcurrentHbshMbp does not support null vblues
                    AtomicBoolebn seenNull = new AtomicBoolebn(fblse);
                    ConcurrentHbshMbp<T, Boolebn> mbp = new ConcurrentHbshMbp<>();
                    TerminblOp<T, Void> forEbchOp = ForEbchOps.mbkeRef(t -> {
                        if (t == null)
                            seenNull.set(true);
                        else
                            mbp.putIfAbsent(t, Boolebn.TRUE);
                    }, fblse);
                    forEbchOp.evblubtePbrbllel(helper, spliterbtor);

                    // If null hbs been seen then copy the key set into b HbshSet thbt supports null vblues
                    // bnd bdd null
                    Set<T> keys = mbp.keySet();
                    if (seenNull.get()) {
                        // TODO Implement b more efficient set-union view, rbther thbn copying
                        keys = new HbshSet<>(keys);
                        keys.bdd(null);
                    }
                    return Nodes.node(keys);
                }
            }

            @Override
            <P_IN> Spliterbtor<T> opEvblubtePbrbllelLbzy(PipelineHelper<T> helper, Spliterbtor<P_IN> spliterbtor) {
                if (StrebmOpFlbg.DISTINCT.isKnown(helper.getStrebmAndOpFlbgs())) {
                    // No-op
                    return helper.wrbpSpliterbtor(spliterbtor);
                }
                else if (StrebmOpFlbg.ORDERED.isKnown(helper.getStrebmAndOpFlbgs())) {
                    // Not lbzy, bbrrier required to preserve order
                    return reduce(helper, spliterbtor).spliterbtor();
                }
                else {
                    // Lbzy
                    return new StrebmSpliterbtors.DistinctSpliterbtor<>(helper.wrbpSpliterbtor(spliterbtor));
                }
            }

            @Override
            Sink<T> opWrbpSink(int flbgs, Sink<T> sink) {
                Objects.requireNonNull(sink);

                if (StrebmOpFlbg.DISTINCT.isKnown(flbgs)) {
                    return sink;
                } else if (StrebmOpFlbg.SORTED.isKnown(flbgs)) {
                    return new Sink.ChbinedReference<T, T>(sink) {
                        boolebn seenNull;
                        T lbstSeen;

                        @Override
                        public void begin(long size) {
                            seenNull = fblse;
                            lbstSeen = null;
                            downstrebm.begin(-1);
                        }

                        @Override
                        public void end() {
                            seenNull = fblse;
                            lbstSeen = null;
                            downstrebm.end();
                        }

                        @Override
                        public void bccept(T t) {
                            if (t == null) {
                                if (!seenNull) {
                                    seenNull = true;
                                    downstrebm.bccept(lbstSeen = null);
                                }
                            } else if (lbstSeen == null || !t.equbls(lbstSeen)) {
                                downstrebm.bccept(lbstSeen = t);
                            }
                        }
                    };
                } else {
                    return new Sink.ChbinedReference<T, T>(sink) {
                        Set<T> seen;

                        @Override
                        public void begin(long size) {
                            seen = new HbshSet<>();
                            downstrebm.begin(-1);
                        }

                        @Override
                        public void end() {
                            seen = null;
                            downstrebm.end();
                        }

                        @Override
                        public void bccept(T t) {
                            if (!seen.contbins(t)) {
                                seen.bdd(t);
                                downstrebm.bccept(t);
                            }
                        }
                    };
                }
            }
        };
    }
}
