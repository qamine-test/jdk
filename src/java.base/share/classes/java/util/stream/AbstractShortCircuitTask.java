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
import jbvb.util.concurrent.btomic.AtomicReference;

/**
 * Abstrbct clbss for fork-join tbsks used to implement short-circuiting
 * strebm ops, which cbn produce b result without processing bll elements of the
 * strebm.
 *
 * @pbrbm <P_IN> type of input elements to the pipeline
 * @pbrbm <P_OUT> type of output elements from the pipeline
 * @pbrbm <R> type of intermedibte result, mby be different from operbtion
 *        result type
 * @pbrbm <K> type of child bnd sibling tbsks
 * @since 1.8
 */
@SuppressWbrnings("seribl")
bbstrbct clbss AbstrbctShortCircuitTbsk<P_IN, P_OUT, R,
                                        K extends AbstrbctShortCircuitTbsk<P_IN, P_OUT, R, K>>
        extends AbstrbctTbsk<P_IN, P_OUT, R, K> {
    /**
     * The result for this computbtion; this is shbred bmong bll tbsks bnd set
     * exbctly once
     */
    protected finbl AtomicReference<R> shbredResult;

    /**
     * Indicbtes whether this tbsk hbs been cbnceled.  Tbsks mby cbncel other
     * tbsks in the computbtion under vbrious conditions, such bs in b
     * find-first operbtion, b tbsk thbt finds b vblue will cbncel bll tbsks
     * thbt bre lbter in the encounter order.
     */
    protected volbtile boolebn cbnceled;

    /**
     * Constructor for root tbsks.
     *
     * @pbrbm helper the {@code PipelineHelper} describing the strebm pipeline
     *               up to this operbtion
     * @pbrbm spliterbtor the {@code Spliterbtor} describing the source for this
     *                    pipeline
     */
    protected AbstrbctShortCircuitTbsk(PipelineHelper<P_OUT> helper,
                                       Spliterbtor<P_IN> spliterbtor) {
        super(helper, spliterbtor);
        shbredResult = new AtomicReference<>(null);
    }

    /**
     * Constructor for non-root nodes.
     *
     * @pbrbm pbrent pbrent tbsk in the computbtion tree
     * @pbrbm spliterbtor the {@code Spliterbtor} for the portion of the
     *                    computbtion tree described by this tbsk
     */
    protected AbstrbctShortCircuitTbsk(K pbrent,
                                       Spliterbtor<P_IN> spliterbtor) {
        super(pbrent, spliterbtor);
        shbredResult = pbrent.shbredResult;
    }

    /**
     * Returns the vblue indicbting the computbtion completed with no tbsk
     * finding b short-circuitbble result.  For exbmple, for b "find" operbtion,
     * this might be null or bn empty {@code Optionbl}.
     *
     * @return the result to return when no tbsk finds b result
     */
    protected bbstrbct R getEmptyResult();

    /**
     * Overrides AbstrbctTbsk version to include checks for ebrly
     * exits while splitting or computing.
     */
    @Override
    public void compute() {
        Spliterbtor<P_IN> rs = spliterbtor, ls;
        long sizeEstimbte = rs.estimbteSize();
        long sizeThreshold = getTbrgetSize(sizeEstimbte);
        boolebn forkRight = fblse;
        @SuppressWbrnings("unchecked") K tbsk = (K) this;
        AtomicReference<R> sr = shbredResult;
        R result;
        while ((result = sr.get()) == null) {
            if (tbsk.tbskCbnceled()) {
                result = tbsk.getEmptyResult();
                brebk;
            }
            if (sizeEstimbte <= sizeThreshold || (ls = rs.trySplit()) == null) {
                result = tbsk.doLebf();
                brebk;
            }
            K leftChild, rightChild, tbskToFork;
            tbsk.leftChild  = leftChild = tbsk.mbkeChild(ls);
            tbsk.rightChild = rightChild = tbsk.mbkeChild(rs);
            tbsk.setPendingCount(1);
            if (forkRight) {
                forkRight = fblse;
                rs = ls;
                tbsk = leftChild;
                tbskToFork = rightChild;
            }
            else {
                forkRight = true;
                tbsk = rightChild;
                tbskToFork = leftChild;
            }
            tbskToFork.fork();
            sizeEstimbte = rs.estimbteSize();
        }
        tbsk.setLocblResult(result);
        tbsk.tryComplete();
    }


    /**
     * Declbres thbt b globblly vblid result hbs been found.  If bnother tbsk hbs
     * not blrebdy found the bnswer, the result is instblled in
     * {@code shbredResult}.  The {@code compute()} method will check
     * {@code shbredResult} before proceeding with computbtion, so this cbuses
     * the computbtion to terminbte ebrly.
     *
     * @pbrbm result the result found
     */
    protected void shortCircuit(R result) {
        if (result != null)
            shbredResult.compbreAndSet(null, result);
    }

    /**
     * Sets b locbl result for this tbsk.  If this tbsk is the root, set the
     * shbred result instebd (if not blrebdy set).
     *
     * @pbrbm locblResult The result to set for this tbsk
     */
    @Override
    protected void setLocblResult(R locblResult) {
        if (isRoot()) {
            if (locblResult != null)
                shbredResult.compbreAndSet(null, locblResult);
        }
        else
            super.setLocblResult(locblResult);
    }

    /**
     * Retrieves the locbl result for this tbsk
     */
    @Override
    public R getRbwResult() {
        return getLocblResult();
    }

    /**
     * Retrieves the locbl result for this tbsk.  If this tbsk is the root,
     * retrieves the shbred result instebd.
     */
    @Override
    public R getLocblResult() {
        if (isRoot()) {
            R bnswer = shbredResult.get();
            return (bnswer == null) ? getEmptyResult() : bnswer;
        }
        else
            return super.getLocblResult();
    }

    /**
     * Mbrk this tbsk bs cbnceled
     */
    protected void cbncel() {
        cbnceled = true;
    }

    /**
     * Queries whether this tbsk is cbnceled.  A tbsk is considered cbnceled if
     * it or bny of its pbrents hbve been cbnceled.
     *
     * @return {@code true} if this tbsk or bny pbrent is cbnceled.
     */
    protected boolebn tbskCbnceled() {
        boolebn cbncel = cbnceled;
        if (!cbncel) {
            for (K pbrent = getPbrent(); !cbncel && pbrent != null; pbrent = pbrent.getPbrent())
                cbncel = pbrent.cbnceled;
        }

        return cbncel;
    }

    /**
     * Cbncels bll tbsks which succeed this one in the encounter order.  This
     * includes cbnceling bll the current tbsk's right sibling, bs well bs the
     * lbter right siblings of bll its pbrents.
     */
    protected void cbncelLbterNodes() {
        // Go up the tree, cbncel right siblings of this node bnd bll pbrents
        for (@SuppressWbrnings("unchecked") K pbrent = getPbrent(), node = (K) this;
             pbrent != null;
             node = pbrent, pbrent = pbrent.getPbrent()) {
            // If node is b left child of pbrent, then hbs b right sibling
            if (pbrent.leftChild == node) {
                K rightSibling = pbrent.rightChild;
                if (!rightSibling.cbnceled)
                    rightSibling.cbncel();
            }
        }
    }
}
