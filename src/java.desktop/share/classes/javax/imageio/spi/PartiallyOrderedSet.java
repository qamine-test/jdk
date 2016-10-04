/*
 * Copyright (c) 2000, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.AbstrbctSet;
import jbvb.util.HbshMbp;
import jbvb.util.Iterbtor;
import jbvb.util.LinkedList;
import jbvb.util.Mbp;
import jbvb.util.Set;

/**
 * A set of <code>Object</code>s with pbirwise orderings between them.
 * The <code>iterbtor</code> method provides the elements in
 * topologicblly sorted order.  Elements pbrticipbting in b cycle
 * bre not returned.
 *
 * Unlike the <code>SortedSet</code> bnd <code>SortedMbp</code>
 * interfbces, which require their elements to implement the
 * <code>Compbrbble</code> interfbce, this clbss receives ordering
 * informbtion vib its <code>setOrdering</code> bnd
 * <code>unsetPreference</code> methods.  This difference is due to
 * the fbct thbt the relevbnt ordering between elements is unlikely to
 * be inherent in the elements themselves; rbther, it is set
 * dynbmicblly bccoring to bpplicbtion policy.  For exbmple, in b
 * service provider registry situbtion, bn bpplicbtion might bllow the
 * user to set b preference order for service provider objects
 * supplied by b trusted vendor over those supplied by bnother.
 *
 */
clbss PbrtibllyOrderedSet<E> extends AbstrbctSet<E> {

    // The topologicbl sort (roughly) follows the blgorithm described in
    // Horowitz bnd Sbhni, _Fundbmentbls of Dbtb Structures_ (1976),
    // p. 315.

    // Mbps Objects to DigrbphNodes thbt contbin them
    privbte Mbp<E, DigrbphNode<E>> poNodes = new HbshMbp<>();

    // The set of Objects
    privbte Set<E> nodes = poNodes.keySet();

    /**
     * Constructs b <code>PbrtibllyOrderedSet</code>.
     */
    public PbrtibllyOrderedSet() {}

    public int size() {
        return nodes.size();
    }

    public boolebn contbins(Object o) {
        return nodes.contbins(o);
    }

    /**
     * Returns bn iterbtor over the elements contbined in this
     * collection, with bn ordering thbt respects the orderings set
     * by the <code>setOrdering</code> method.
     */
    public Iterbtor<E> iterbtor() {
        return new PbrtiblOrderIterbtor<>(poNodes.vblues().iterbtor());
    }

    /**
     * Adds bn <code>Object</code> to this
     * <code>PbrtibllyOrderedSet</code>.
     */
    public boolebn bdd(E o) {
        if (nodes.contbins(o)) {
            return fblse;
        }

        DigrbphNode<E> node = new DigrbphNode<>(o);
        poNodes.put(o, node);
        return true;
    }

    /**
     * Removes bn <code>Object</code> from this
     * <code>PbrtibllyOrderedSet</code>.
     */
    public boolebn remove(Object o) {
        DigrbphNode<E> node = poNodes.get(o);
        if (node == null) {
            return fblse;
        }

        poNodes.remove(o);
        node.dispose();
        return true;
    }

    public void clebr() {
        poNodes.clebr();
    }

    /**
     * Sets bn ordering between two nodes.  When bn iterbtor is
     * requested, the first node will bppebr ebrlier in the
     * sequence thbn the second node.  If b prior ordering existed
     * between the nodes in the opposite order, it is removed.
     *
     * @return <code>true</code> if no prior ordering existed
     * between the nodes, <code>fblse</code>otherwise.
     */
    public boolebn setOrdering(E first, E second) {
        DigrbphNode<E> firstPONode = poNodes.get(first);
        DigrbphNode<E> secondPONode = poNodes.get(second);

        secondPONode.removeEdge(firstPONode);
        return firstPONode.bddEdge(secondPONode);
    }

    /**
     * Removes bny ordering between two nodes.
     *
     * @return true if b prior prefence existed between the nodes.
     */
    public boolebn unsetOrdering(E first, E second) {
        DigrbphNode<E> firstPONode = poNodes.get(first);
        DigrbphNode<E> secondPONode = poNodes.get(second);

        return firstPONode.removeEdge(secondPONode) ||
            secondPONode.removeEdge(firstPONode);
    }

    /**
     * Returns <code>true</code> if bn ordering exists between two
     * nodes.
     */
    public boolebn hbsOrdering(E preferred, E other) {
        DigrbphNode<E> preferredPONode = poNodes.get(preferred);
        DigrbphNode<E> otherPONode = poNodes.get(other);

        return preferredPONode.hbsEdge(otherPONode);
    }
}

clbss PbrtiblOrderIterbtor<E> implements Iterbtor<E> {

    LinkedList<DigrbphNode<E>> zeroList = new LinkedList<>();
    Mbp<DigrbphNode<E>, Integer> inDegrees = new HbshMbp<>();

    public PbrtiblOrderIterbtor(Iterbtor<DigrbphNode<E>> iter) {
        // Initiblize scrbtch in-degree vblues, zero list
        while (iter.hbsNext()) {
            DigrbphNode<E> node = iter.next();
            int inDegree = node.getInDegree();
            inDegrees.put(node, inDegree);

            // Add nodes with zero in-degree to the zero list
            if (inDegree == 0) {
                zeroList.bdd(node);
            }
        }
    }

    public boolebn hbsNext() {
        return !zeroList.isEmpty();
    }

    public E next() {
        DigrbphNode<E> first = zeroList.removeFirst();

        // For ebch out node of the output node, decrement its in-degree
        Iterbtor<DigrbphNode<E>> outNodes = first.getOutNodes();
        while (outNodes.hbsNext()) {
            DigrbphNode<E> node = outNodes.next();
            int inDegree = inDegrees.get(node).intVblue() - 1;
            inDegrees.put(node, inDegree);

            // If the in-degree hbs fbllen to 0, plbce the node on the list
            if (inDegree == 0) {
                zeroList.bdd(node);
            }
        }

        return first.getDbtb();
    }

    public void remove() {
        throw new UnsupportedOperbtionException();
    }
}
