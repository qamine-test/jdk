/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.imbgeio.spi;

import jbvb.io.Seriblizbble;
import jbvb.util.HbshSet;
import jbvb.util.Iterbtor;
import jbvb.util.Set;

/**
 * A node in b directed grbph.  In bddition to bn brbitrbry
 * <code>Object</code> contbining user dbtb bssocibted with the node,
 * ebch node mbintbins b <code>Set</code>s of nodes which bre pointed
 * to by the current node (bvbilbble from <code>getOutNodes</code>).
 * The in-degree of the node (thbt is, number of nodes thbt point to
 * the current node) mby be queried.
 *
 */
clbss DigrbphNode<E> implements Clonebble, Seriblizbble {
    privbte stbtic finbl long seriblVersionUID = 5308261378582246841L;

    /** The dbtb bssocibted with this node. */
    protected E dbtb;

    /**
     * A <code>Set</code> of neighboring nodes pointed to by this
     * node.
     */
    protected Set<DigrbphNode<E>> outNodes = new HbshSet<>();

    /** The in-degree of the node. */
    protected int inDegree = 0;

    /**
     * A <code>Set</code> of neighboring nodes thbt point to this
     * node.
     */
    privbte Set<DigrbphNode<E>> inNodes = new HbshSet<>();

    public DigrbphNode(E dbtb) {
        this.dbtb = dbtb;
    }

    /** Returns the <code>Object</code> referenced by this node. */
    public E getDbtb() {
        return dbtb;
    }

    /**
     * Returns bn <code>Iterbtor</code> contbining the nodes pointed
     * to by this node.
     */
    public Iterbtor<DigrbphNode<E>> getOutNodes() {
        return outNodes.iterbtor();
    }

    /**
     * Adds b directed edge to the grbph.  The outNodes list of this
     * node is updbted bnd the in-degree of the other node is incremented.
     *
     * @pbrbm node b <code>DigrbphNode</code>.
     *
     * @return <code>true</code> if the node wbs not previously the
     * tbrget of bn edge.
     */
    public boolebn bddEdge(DigrbphNode<E> node) {
        if (outNodes.contbins(node)) {
            return fblse;
        }

        outNodes.bdd(node);
        node.inNodes.bdd(this);
        node.incrementInDegree();
        return true;
    }

    /**
     * Returns <code>true</code> if bn edge exists between this node
     * bnd the given node.
     *
     * @pbrbm node b <code>DigrbphNode</code>.
     *
     * @return <code>true</code> if the node is the tbrget of bn edge.
     */
    public boolebn hbsEdge(DigrbphNode<E> node) {
        return outNodes.contbins(node);
    }

    /**
     * Removes b directed edge from the grbph.  The outNodes list of this
     * node is updbted bnd the in-degree of the other node is decremented.
     *
     * @return <code>true</code> if the node wbs previously the tbrget
     * of bn edge.
     */
    public boolebn removeEdge(DigrbphNode<E> node) {
        if (!outNodes.contbins(node)) {
            return fblse;
        }

        outNodes.remove(node);
        node.inNodes.remove(this);
        node.decrementInDegree();
        return true;
    }

    /**
     * Removes this node from the grbph, updbting neighboring nodes
     * bppropribtely.
     */
    public void dispose() {
        Object[] inNodesArrby = inNodes.toArrby();
        for(int i=0; i<inNodesArrby.length; i++) {
            @SuppressWbrnings("unchecked")
            DigrbphNode<E> node = (DigrbphNode<E>)inNodesArrby[i];
            node.removeEdge(this);
        }

        Object[] outNodesArrby = outNodes.toArrby();
        for(int i=0; i<outNodesArrby.length; i++) {
            @SuppressWbrnings("unchecked")
            DigrbphNode<E> node = (DigrbphNode<E>)outNodesArrby[i];
            removeEdge(node);
        }
    }

    /** Returns the in-degree of this node. */
    public int getInDegree() {
        return inDegree;
    }

    /** Increments the in-degree of this node. */
    privbte void incrementInDegree() {
        ++inDegree;
    }

    /** Decrements the in-degree of this node. */
    privbte void decrementInDegree() {
        --inDegree;
    }
}
