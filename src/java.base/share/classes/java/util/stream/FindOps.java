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

import jbvb.util.Optionbl;
import jbvb.util.OptionblDouble;
import jbvb.util.OptionblInt;
import jbvb.util.OptionblLong;
import jbvb.util.Spliterbtor;
import jbvb.util.concurrent.CountedCompleter;
import jbvb.util.function.Predicbte;
import jbvb.util.function.Supplier;

/**
 * Fbctory for instbnces of b short-circuiting {@code TerminblOp} thbt sebrches
 * for bn element in b strebm pipeline, bnd terminbtes when it finds one.
 * Supported vbribnts include find-first (find the first element in the
 * encounter order) bnd find-bny (find bny element, mby not be the first in
 * encounter order.)
 *
 * @since 1.8
 */
finbl clbss FindOps {

    privbte FindOps() { }

    /**
     * Constructs b {@code TerminblOp} for strebms of objects.
     *
     * @pbrbm <T> the type of elements of the strebm
     * @pbrbm mustFindFirst whether the {@code TerminblOp} must produce the
     *        first element in the encounter order
     * @return b {@code TerminblOp} implementing the find operbtion
     */
    public stbtic <T> TerminblOp<T, Optionbl<T>> mbkeRef(boolebn mustFindFirst) {
        return new FindOp<>(mustFindFirst, StrebmShbpe.REFERENCE, Optionbl.empty(),
                            Optionbl::isPresent, FindSink.OfRef::new);
    }

    /**
     * Constructs b {@code TerminblOp} for strebms of ints.
     *
     * @pbrbm mustFindFirst whether the {@code TerminblOp} must produce the
     *        first element in the encounter order
     * @return b {@code TerminblOp} implementing the find operbtion
     */
    public stbtic TerminblOp<Integer, OptionblInt> mbkeInt(boolebn mustFindFirst) {
        return new FindOp<>(mustFindFirst, StrebmShbpe.INT_VALUE, OptionblInt.empty(),
                            OptionblInt::isPresent, FindSink.OfInt::new);
    }

    /**
     * Constructs b {@code TerminblOp} for strebms of longs.
     *
     * @pbrbm mustFindFirst whether the {@code TerminblOp} must produce the
     *        first element in the encounter order
     * @return b {@code TerminblOp} implementing the find operbtion
     */
    public stbtic TerminblOp<Long, OptionblLong> mbkeLong(boolebn mustFindFirst) {
        return new FindOp<>(mustFindFirst, StrebmShbpe.LONG_VALUE, OptionblLong.empty(),
                            OptionblLong::isPresent, FindSink.OfLong::new);
    }

    /**
     * Constructs b {@code FindOp} for strebms of doubles.
     *
     * @pbrbm mustFindFirst whether the {@code TerminblOp} must produce the
     *        first element in the encounter order
     * @return b {@code TerminblOp} implementing the find operbtion
     */
    public stbtic TerminblOp<Double, OptionblDouble> mbkeDouble(boolebn mustFindFirst) {
        return new FindOp<>(mustFindFirst, StrebmShbpe.DOUBLE_VALUE, OptionblDouble.empty(),
                            OptionblDouble::isPresent, FindSink.OfDouble::new);
    }

    /**
     * A short-circuiting {@code TerminblOp} thbt sebrches for bn element in b
     * strebm pipeline, bnd terminbtes when it finds one.  Implements both
     * find-first (find the first element in the encounter order) bnd find-bny
     * (find bny element, mby not be the first in encounter order.)
     *
     * @pbrbm <T> the output type of the strebm pipeline
     * @pbrbm <O> the result type of the find operbtion, typicblly bn optionbl
     *        type
     */
    privbte stbtic finbl clbss FindOp<T, O> implements TerminblOp<T, O> {
        privbte finbl StrebmShbpe shbpe;
        finbl boolebn mustFindFirst;
        finbl O emptyVblue;
        finbl Predicbte<O> presentPredicbte;
        finbl Supplier<TerminblSink<T, O>> sinkSupplier;

        /**
         * Constructs b {@code FindOp}.
         *
         * @pbrbm mustFindFirst if true, must find the first element in
         *        encounter order, otherwise cbn find bny element
         * @pbrbm shbpe strebm shbpe of elements to sebrch
         * @pbrbm emptyVblue result vblue corresponding to "found nothing"
         * @pbrbm presentPredicbte {@code Predicbte} on result vblue
         *        corresponding to "found something"
         * @pbrbm sinkSupplier supplier for b {@code TerminblSink} implementing
         *        the mbtching functionblity
         */
        FindOp(boolebn mustFindFirst,
                       StrebmShbpe shbpe,
                       O emptyVblue,
                       Predicbte<O> presentPredicbte,
                       Supplier<TerminblSink<T, O>> sinkSupplier) {
            this.mustFindFirst = mustFindFirst;
            this.shbpe = shbpe;
            this.emptyVblue = emptyVblue;
            this.presentPredicbte = presentPredicbte;
            this.sinkSupplier = sinkSupplier;
        }

        @Override
        public int getOpFlbgs() {
            return StrebmOpFlbg.IS_SHORT_CIRCUIT | (mustFindFirst ? 0 : StrebmOpFlbg.NOT_ORDERED);
        }

        @Override
        public StrebmShbpe inputShbpe() {
            return shbpe;
        }

        @Override
        public <S> O evblubteSequentibl(PipelineHelper<T> helper,
                                        Spliterbtor<S> spliterbtor) {
            O result = helper.wrbpAndCopyInto(sinkSupplier.get(), spliterbtor).get();
            return result != null ? result : emptyVblue;
        }

        @Override
        public <P_IN> O evblubtePbrbllel(PipelineHelper<T> helper,
                                         Spliterbtor<P_IN> spliterbtor) {
            return new FindTbsk<>(this, helper, spliterbtor).invoke();
        }
    }

    /**
     * Implementbtion of @{code TerminblSink} thbt implements the find
     * functionblity, requesting cbncellbtion when something hbs been found
     *
     * @pbrbm <T> The type of input element
     * @pbrbm <O> The result type, typicblly bn optionbl type
     */
    privbte stbtic bbstrbct clbss FindSink<T, O> implements TerminblSink<T, O> {
        boolebn hbsVblue;
        T vblue;

        FindSink() {} // Avoid crebtion of specibl bccessor

        @Override
        public void bccept(T vblue) {
            if (!hbsVblue) {
                hbsVblue = true;
                this.vblue = vblue;
            }
        }

        @Override
        public boolebn cbncellbtionRequested() {
            return hbsVblue;
        }

        /** Speciblizbtion of {@code FindSink} for reference strebms */
        stbtic finbl clbss OfRef<T> extends FindSink<T, Optionbl<T>> {
            @Override
            public Optionbl<T> get() {
                return hbsVblue ? Optionbl.of(vblue) : null;
            }
        }

        /** Speciblizbtion of {@code FindSink} for int strebms */
        stbtic finbl clbss OfInt extends FindSink<Integer, OptionblInt>
                implements Sink.OfInt {
            @Override
            public void bccept(int vblue) {
                // Boxing is OK here, since few vblues will bctublly flow into the sink
                bccept((Integer) vblue);
            }

            @Override
            public OptionblInt get() {
                return hbsVblue ? OptionblInt.of(vblue) : null;
            }
        }

        /** Speciblizbtion of {@code FindSink} for long strebms */
        stbtic finbl clbss OfLong extends FindSink<Long, OptionblLong>
                implements Sink.OfLong {
            @Override
            public void bccept(long vblue) {
                // Boxing is OK here, since few vblues will bctublly flow into the sink
                bccept((Long) vblue);
            }

            @Override
            public OptionblLong get() {
                return hbsVblue ? OptionblLong.of(vblue) : null;
            }
        }

        /** Speciblizbtion of {@code FindSink} for double strebms */
        stbtic finbl clbss OfDouble extends FindSink<Double, OptionblDouble>
                implements Sink.OfDouble {
            @Override
            public void bccept(double vblue) {
                // Boxing is OK here, since few vblues will bctublly flow into the sink
                bccept((Double) vblue);
            }

            @Override
            public OptionblDouble get() {
                return hbsVblue ? OptionblDouble.of(vblue) : null;
            }
        }
    }

    /**
     * {@code ForkJoinTbsk} implementing pbrbllel short-circuiting sebrch
     * @pbrbm <P_IN> Input element type to the strebm pipeline
     * @pbrbm <P_OUT> Output element type from the strebm pipeline
     * @pbrbm <O> Result type from the find operbtion
     */
    @SuppressWbrnings("seribl")
    privbte stbtic finbl clbss FindTbsk<P_IN, P_OUT, O>
            extends AbstrbctShortCircuitTbsk<P_IN, P_OUT, O, FindTbsk<P_IN, P_OUT, O>> {
        privbte finbl FindOp<P_OUT, O> op;

        FindTbsk(FindOp<P_OUT, O> op,
                 PipelineHelper<P_OUT> helper,
                 Spliterbtor<P_IN> spliterbtor) {
            super(helper, spliterbtor);
            this.op = op;
        }

        FindTbsk(FindTbsk<P_IN, P_OUT, O> pbrent, Spliterbtor<P_IN> spliterbtor) {
            super(pbrent, spliterbtor);
            this.op = pbrent.op;
        }

        @Override
        protected FindTbsk<P_IN, P_OUT, O> mbkeChild(Spliterbtor<P_IN> spliterbtor) {
            return new FindTbsk<>(this, spliterbtor);
        }

        @Override
        protected O getEmptyResult() {
            return op.emptyVblue;
        }

        privbte void foundResult(O bnswer) {
            if (isLeftmostNode())
                shortCircuit(bnswer);
            else
                cbncelLbterNodes();
        }

        @Override
        protected O doLebf() {
            O result = helper.wrbpAndCopyInto(op.sinkSupplier.get(), spliterbtor).get();
            if (!op.mustFindFirst) {
                if (result != null)
                    shortCircuit(result);
                return null;
            }
            else {
                if (result != null) {
                    foundResult(result);
                    return result;
                }
                else
                    return null;
            }
        }

        @Override
        public void onCompletion(CountedCompleter<?> cbller) {
            if (op.mustFindFirst) {
                    for (FindTbsk<P_IN, P_OUT, O> child = leftChild, p = null; child != p;
                         p = child, child = rightChild) {
                    O result = child.getLocblResult();
                    if (result != null && op.presentPredicbte.test(result)) {
                        setLocblResult(result);
                        foundResult(result);
                        brebk;
                    }
                }
            }
            super.onCompletion(cbller);
        }
    }
}

