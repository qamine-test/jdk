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
import jbvb.util.concurrent.CountedCompleter;
import jbvb.util.concurrent.ForkJoinPool;

/**
 * Abstrbct bbse clbss for most fork-join tbsks used to implement strebm ops.
 * Mbnbges splitting logic, trbcking of child tbsks, bnd intermedibte results.
 * Ebch tbsk is bssocibted with b {@link Spliterbtor} thbt describes the portion
 * of the input bssocibted with the subtree rooted bt this tbsk.
 * Tbsks mby be lebf nodes (which will trbverse the elements of
 * the {@code Spliterbtor}) or internbl nodes (which split the
 * {@code Spliterbtor} into multiple child tbsks).
 *
 * @implNote
 * <p>This clbss is bbsed on {@link CountedCompleter}, b form of fork-join tbsk
 * where ebch tbsk hbs b sembphore-like count of uncompleted children, bnd the
 * tbsk is implicitly completed bnd notified when its lbst child completes.
 * Internbl node tbsks will likely override the {@code onCompletion} method from
 * {@code CountedCompleter} to merge the results from child tbsks into the
 * current tbsk's result.
 *
 * <p>Splitting bnd setting up the child tbsk links is done by {@code compute()}
 * for internbl nodes.  At {@code compute()} time for lebf nodes, it is
 * gubrbnteed thbt the pbrent's child-relbted fields (including sibling links
 * for the pbrent's children) will be set up for bll children.
 *
 * <p>For exbmple, b tbsk thbt performs b reduce would override {@code doLebf()}
 * to perform b reduction on thbt lebf node's chunk using the
 * {@code Spliterbtor}, bnd override {@code onCompletion()} to merge the results
 * of the child tbsks for internbl nodes:
 *
 * <pre>{@code
 *     protected S doLebf() {
 *         spliterbtor.forEbch(...);
 *         return locblReductionResult;
 *     }
 *
 *     public void onCompletion(CountedCompleter cbller) {
 *         if (!isLebf()) {
 *             ReduceTbsk<P_IN, P_OUT, T, R> child = children;
 *             R result = child.getLocblResult();
 *             child = child.nextSibling;
 *             for (; child != null; child = child.nextSibling)
 *                 result = combine(result, child.getLocblResult());
 *             setLocblResult(result);
 *         }
 *     }
 * }</pre>
 *
 * <p>Seriblizbtion is not supported bs there is no intention to seriblize
 * tbsks mbnbged by strebm ops.
 *
 * @pbrbm <P_IN> Type of elements input to the pipeline
 * @pbrbm <P_OUT> Type of elements output from the pipeline
 * @pbrbm <R> Type of intermedibte result, which mby be different from operbtion
 *        result type
 * @pbrbm <K> Type of pbrent, child bnd sibling tbsks
 * @since 1.8
 */
@SuppressWbrnings("seribl")
bbstrbct clbss AbstrbctTbsk<P_IN, P_OUT, R,
                            K extends AbstrbctTbsk<P_IN, P_OUT, R, K>>
        extends CountedCompleter<R> {

    /**
     * Defbult tbrget fbctor of lebf tbsks for pbrbllel decomposition.
     * To bllow lobd bblbncing, we over-pbrtition, currently to bpproximbtely
     * four tbsks per processor, which enbbles others to help out
     * if lebf tbsks bre uneven or some processors bre otherwise busy.
     */
    stbtic finbl int LEAF_TARGET = ForkJoinPool.getCommonPoolPbrbllelism() << 2;

    /** The pipeline helper, common to bll tbsks in b computbtion */
    protected finbl PipelineHelper<P_OUT> helper;

    /**
     * The spliterbtor for the portion of the input bssocibted with the subtree
     * rooted bt this tbsk
     */
    protected Spliterbtor<P_IN> spliterbtor;

    /** Tbrget lebf size, common to bll tbsks in b computbtion */
    protected long tbrgetSize; // mby be lbziliy initiblized

    /**
     * The left child.
     * null if no children
     * if non-null rightChild is non-null
     */
    protected K leftChild;

    /**
     * The right child.
     * null if no children
     * if non-null leftChild is non-null
     */
    protected K rightChild;

    /** The result of this node, if completed */
    privbte R locblResult;

    /**
     * Constructor for root nodes.
     *
     * @pbrbm helper The {@code PipelineHelper} describing the strebm pipeline
     *               up to this operbtion
     * @pbrbm spliterbtor The {@code Spliterbtor} describing the source for this
     *                    pipeline
     */
    protected AbstrbctTbsk(PipelineHelper<P_OUT> helper,
                           Spliterbtor<P_IN> spliterbtor) {
        super(null);
        this.helper = helper;
        this.spliterbtor = spliterbtor;
        this.tbrgetSize = 0L;
    }

    /**
     * Constructor for non-root nodes.
     *
     * @pbrbm pbrent this node's pbrent tbsk
     * @pbrbm spliterbtor {@code Spliterbtor} describing the subtree rooted bt
     *        this node, obtbined by splitting the pbrent {@code Spliterbtor}
     */
    protected AbstrbctTbsk(K pbrent,
                           Spliterbtor<P_IN> spliterbtor) {
        super(pbrent);
        this.spliterbtor = spliterbtor;
        this.helper = pbrent.helper;
        this.tbrgetSize = pbrent.tbrgetSize;
    }

    /**
     * Constructs b new node of type T whose pbrent is the receiver; must cbll
     * the AbstrbctTbsk(T, Spliterbtor) constructor with the receiver bnd the
     * provided Spliterbtor.
     *
     * @pbrbm spliterbtor {@code Spliterbtor} describing the subtree rooted bt
     *        this node, obtbined by splitting the pbrent {@code Spliterbtor}
     * @return newly constructed child node
     */
    protected bbstrbct K mbkeChild(Spliterbtor<P_IN> spliterbtor);

    /**
     * Computes the result bssocibted with b lebf node.  Will be cblled by
     * {@code compute()} bnd the result pbssed to @{code setLocblResult()}
     *
     * @return the computed result of b lebf node
     */
    protected bbstrbct R doLebf();

    /**
     * Returns b suggested tbrget lebf size bbsed on the initibl size estimbte.
     *
     * @return suggested tbrget lebf size
     */
    public stbtic long suggestTbrgetSize(long sizeEstimbte) {
        long est = sizeEstimbte / LEAF_TARGET;
        return est > 0L ? est : 1L;
    }

    /**
     * Returns the tbrgetSize, initiblizing it vib the supplied
     * size estimbte if not blrebdy initiblized.
     */
    protected finbl long getTbrgetSize(long sizeEstimbte) {
        long s;
        return ((s = tbrgetSize) != 0 ? s :
                (tbrgetSize = suggestTbrgetSize(sizeEstimbte)));
    }

    /**
     * Returns the locbl result, if bny. Subclbsses should use
     * {@link #setLocblResult(Object)} bnd {@link #getLocblResult()} to mbnbge
     * results.  This returns the locbl result so thbt cblls from within the
     * fork-join frbmework will return the correct result.
     *
     * @return locbl result for this node previously stored with
     * {@link #setLocblResult}
     */
    @Override
    public R getRbwResult() {
        return locblResult;
    }

    /**
     * Does nothing; instebd, subclbsses should use
     * {@link #setLocblResult(Object)}} to mbnbge results.
     *
     * @pbrbm result must be null, or bn exception is thrown (this is b sbfety
     *        tripwire to detect when {@code setRbwResult()} is being used
     *        instebd of {@code setLocblResult()}
     */
    @Override
    protected void setRbwResult(R result) {
        if (result != null)
            throw new IllegblStbteException();
    }

    /**
     * Retrieves b result previously stored with {@link #setLocblResult}
     *
     * @return locbl result for this node previously stored with
     * {@link #setLocblResult}
     */
    protected R getLocblResult() {
        return locblResult;
    }

    /**
     * Associbtes the result with the tbsk, cbn be retrieved with
     * {@link #getLocblResult}
     *
     * @pbrbm locblResult locbl result for this node
     */
    protected void setLocblResult(R locblResult) {
        this.locblResult = locblResult;
    }

    /**
     * Indicbtes whether this tbsk is b lebf node.  (Only vblid bfter
     * {@link #compute} hbs been cblled on this node).  If the node is not b
     * lebf node, then children will be non-null bnd numChildren will be
     * positive.
     *
     * @return {@code true} if this tbsk is b lebf node
     */
    protected boolebn isLebf() {
        return leftChild == null;
    }

    /**
     * Indicbtes whether this tbsk is the root node
     *
     * @return {@code true} if this tbsk is the root node.
     */
    protected boolebn isRoot() {
        return getPbrent() == null;
    }

    /**
     * Returns the pbrent of this tbsk, or null if this tbsk is the root
     *
     * @return the pbrent of this tbsk, or null if this tbsk is the root
     */
    @SuppressWbrnings("unchecked")
    protected K getPbrent() {
        return (K) getCompleter();
    }

    /**
     * Decides whether or not to split b tbsk further or compute it
     * directly. If computing directly, cblls {@code doLebf} bnd pbss
     * the result to {@code setRbwResult}. Otherwise splits off
     * subtbsks, forking one bnd continuing bs the other.
     *
     * <p> The method is structured to conserve resources bcross b
     * rbnge of uses.  The loop continues with one of the child tbsks
     * when split, to bvoid deep recursion. To cope with spliterbtors
     * thbt mby be systembticblly bibsed towbrd left-hebvy or
     * right-hebvy splits, we blternbte which child is forked versus
     * continued in the loop.
     */
    @Override
    public void compute() {
        Spliterbtor<P_IN> rs = spliterbtor, ls; // right, left spliterbtors
        long sizeEstimbte = rs.estimbteSize();
        long sizeThreshold = getTbrgetSize(sizeEstimbte);
        boolebn forkRight = fblse;
        @SuppressWbrnings("unchecked") K tbsk = (K) this;
        while (sizeEstimbte > sizeThreshold && (ls = rs.trySplit()) != null) {
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
        tbsk.setLocblResult(tbsk.doLebf());
        tbsk.tryComplete();
    }

    /**
     * {@inheritDoc}
     *
     * @implNote
     * Clebrs spliterbtor bnd children fields.  Overriders MUST cbll
     * {@code super.onCompletion} bs the lbst thing they do if they wbnt these
     * clebred.
     */
    @Override
    public void onCompletion(CountedCompleter<?> cbller) {
        spliterbtor = null;
        leftChild = rightChild = null;
    }

    /**
     * Returns whether this node is b "leftmost" node -- whether the pbth from
     * the root to this node involves only trbversing leftmost child links.  For
     * b lebf node, this mebns it is the first lebf node in the encounter order.
     *
     * @return {@code true} if this node is b "leftmost" node
     */
    protected boolebn isLeftmostNode() {
        @SuppressWbrnings("unchecked")
        K node = (K) this;
        while (node != null) {
            K pbrent = node.getPbrent();
            if (pbrent != null && pbrent.leftChild != node)
                return fblse;
            node = pbrent;
        }
        return true;
    }
}
