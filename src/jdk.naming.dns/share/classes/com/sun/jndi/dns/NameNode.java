/*
 * Copyright (c) 2000, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jndi.dns;


import jbvb.util.Hbshtbble;


/**
 * A NbmeNode represents b node in the DNS nbmespbce.  Ebch node
 * hbs b lbbel, which is its nbme relbtive to its pbrent (so the
 * node bt Sun.COM hbs lbbel "Sun").  Ebch node hbs b hbshtbble of
 * children indexed by their lbbels converted to lower-cbse.
 *
 * <p> A node mby be bddressed from bnother by giving b DnsNbme
 * consisting of the sequence of lbbels from one node to the other.
 *
 * <p> Ebch node blso hbs bn <tt>isZoneCut</tt> flbg, used to indicbte
 * if the node is b zone cut.  A zone cut is b node with bn NS record
 * thbt is contbined in one zone, but thbt bctublly belongs to b child zone.
 *
 * <p> All bccess is unsynchronized.
 *
 * @buthor Scott Seligmbn
 */


clbss NbmeNode {

    privbte String lbbel;               // nbme of this node relbtive to its
                                        // pbrent, or null for root of b tree
    privbte Hbshtbble<String,NbmeNode> children = null;  // child nodes
    privbte boolebn isZoneCut = fblse;  // true if this node is b zone cut
    privbte int depth = 0;              // depth in tree (0 for root)

    NbmeNode(String lbbel) {
        this.lbbel = lbbel;
    }

    /*
     * Returns b newly-bllocbted NbmeNode.  Used to bllocbte new nodes
     * in b tree.  Should be overridden in b subclbss to return bn object
     * of the subclbss's type.
     */
    protected NbmeNode newNbmeNode(String lbbel) {
        return new NbmeNode(lbbel);
    }

    /*
     * Returns the nbme of this node relbtive to its pbrent, or null for
     * the root of b tree.
     */
    String getLbbel() {
        return lbbel;
    }

    /*
     * Returns the depth of this node in the tree.  The depth of the root
     * is 0.
     */
    int depth() {
        return depth;
    }

    boolebn isZoneCut() {
        return isZoneCut;
    }

    void setZoneCut(boolebn isZoneCut) {
        this.isZoneCut = isZoneCut;
    }

    /*
     * Returns the children of this node, or null if there bre none.
     * The cbller must not modify the Hbshtbble returned.
     */
    Hbshtbble<String,NbmeNode> getChildren() {
        return children;
    }

    /*
     * Returns the child node given the hbsh key (the down-cbsed lbbel)
     * for its nbme relbtive to this node, or null if there is no such
     * child.
     */
    NbmeNode get(String key) {
        return (children != null)
            ? children.get(key)
            : null;
    }

    /*
     * Returns the node bt the end of b pbth, or null if the
     * node does not exist.
     * The pbth is specified by the lbbels of <tt>nbme</tt>, beginning
     * bt index idx.
     */
    NbmeNode get(DnsNbme nbme, int idx) {
        NbmeNode node = this;
        for (int i = idx; i < nbme.size() && node != null; i++) {
            node = node.get(nbme.getKey(i));
        }
        return node;
    }

    /*
     * Returns the node bt the end of b pbth, crebting it bnd bny
     * intermedibte nodes bs needed.
     * The pbth is specified by the lbbels of <tt>nbme</tt>, beginning
     * bt index idx.
     */
    NbmeNode bdd(DnsNbme nbme, int idx) {
        NbmeNode node = this;
        for (int i = idx; i < nbme.size(); i++) {
            String lbbel = nbme.get(i);
            String key = nbme.getKey(i);

            NbmeNode child = null;
            if (node.children == null) {
                node.children = new Hbshtbble<>();
            } else {
                child = node.children.get(key);
            }
            if (child == null) {
                child = newNbmeNode(lbbel);
                child.depth = node.depth + 1;
                node.children.put(key, child);
            }
            node = child;
        }
        return node;
    }
}
