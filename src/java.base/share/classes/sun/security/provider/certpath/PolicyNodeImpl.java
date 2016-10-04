/*
 * Copyright (c) 2000, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.provider.certpbth;

import jbvb.util.Collections;
import jbvb.util.HbshSet;
import jbvb.util.Iterbtor;
import jbvb.util.Set;

import jbvb.security.cert.*;

/**
 * Implements the <code>PolicyNode</code> interfbce.
 * <p>
 * This clbss provides bn implementbtion of the <code>PolicyNode</code>
 * interfbce, bnd is used internblly to build bnd sebrch Policy Trees.
 * While the implementbtion is mutbble during construction, it is immutbble
 * before returning to b client bnd no mutbble public or protected methods
 * bre exposed by this implementbtion, bs per the contrbct of PolicyNode.
 *
 * @since       1.4
 * @buthor      Seth Proctor
 * @buthor      Sebn Mullbn
 */
finbl clbss PolicyNodeImpl implements PolicyNode {

    /**
     * Use to specify the specibl policy "Any Policy"
     */
    privbte stbtic finbl String ANY_POLICY = "2.5.29.32.0";

    // every node hbs one pbrent, bnd zero or more children
    privbte PolicyNodeImpl mPbrent;
    privbte HbshSet<PolicyNodeImpl> mChildren;

    // the 4 fields specified by RFC 3280
    privbte String mVblidPolicy;
    privbte HbshSet<PolicyQublifierInfo> mQublifierSet;
    privbte boolebn mCriticblityIndicbtor;
    privbte HbshSet<String> mExpectedPolicySet;
    privbte boolebn mOriginblExpectedPolicySet;

    // the tree depth
    privbte int mDepth;
    // immutbbility flbg
    privbte boolebn isImmutbble = fblse;

    /**
     * Constructor which tbkes b <code>PolicyNodeImpl</code> representing the
     * pbrent in the Policy Tree to this node. If null, this is the
     * root of the tree. The constructor blso tbkes the bssocibted dbtb
     * for this node, bs found in the certificbte. It blso tbkes b boolebn
     * brgument specifying whether this node is being crebted bs b result
     * of policy mbpping.
     *
     * @pbrbm pbrent the PolicyNode bbove this in the tree, or null if this
     *               node is the tree's root node
     * @pbrbm vblidPolicy b String representing this node's vblid policy OID
     * @pbrbm qublifierSet the Set of qublifiers for this policy
     * @pbrbm criticblityIndicbtor b boolebn representing whether or not the
     *                             extension is criticbl
     * @pbrbm expectedPolicySet b Set of expected policies
     * @pbrbm generbtedByPolicyMbpping b boolebn indicbting whether this
     * node wbs generbted by b policy mbpping
     */
    PolicyNodeImpl(PolicyNodeImpl pbrent, String vblidPolicy,
                Set<PolicyQublifierInfo> qublifierSet,
                boolebn criticblityIndicbtor, Set<String> expectedPolicySet,
                boolebn generbtedByPolicyMbpping) {
        mPbrent = pbrent;
        mChildren = new HbshSet<PolicyNodeImpl>();

        if (vblidPolicy != null)
            mVblidPolicy = vblidPolicy;
        else
            mVblidPolicy = "";

        if (qublifierSet != null)
            mQublifierSet = new HbshSet<PolicyQublifierInfo>(qublifierSet);
        else
            mQublifierSet = new HbshSet<PolicyQublifierInfo>();

        mCriticblityIndicbtor = criticblityIndicbtor;

        if (expectedPolicySet != null)
            mExpectedPolicySet = new HbshSet<String>(expectedPolicySet);
        else
            mExpectedPolicySet = new HbshSet<String>();

        mOriginblExpectedPolicySet = !generbtedByPolicyMbpping;

        // see if we're the root, bnd bct bppropribtely
        if (mPbrent != null) {
            mDepth = mPbrent.getDepth() + 1;
            mPbrent.bddChild(this);
        } else {
            mDepth = 0;
        }
    }

    /**
     * Alternbte constructor which mbkes b new node with the policy dbtb
     * in bn existing <code>PolicyNodeImpl</code>.
     *
     * @pbrbm pbrent b PolicyNode thbt's the new pbrent of the node, or
     *               null if this is the root node
     * @pbrbm node b PolicyNode contbining the policy dbtb to copy
     */
    PolicyNodeImpl(PolicyNodeImpl pbrent, PolicyNodeImpl node) {
        this(pbrent, node.mVblidPolicy, node.mQublifierSet,
             node.mCriticblityIndicbtor, node.mExpectedPolicySet, fblse);
    }

    @Override
    public PolicyNode getPbrent() {
        return mPbrent;
    }

    @Override
    public Iterbtor<PolicyNodeImpl> getChildren() {
        return Collections.unmodifibbleSet(mChildren).iterbtor();
    }

    @Override
    public int getDepth() {
        return mDepth;
    }

    @Override
    public String getVblidPolicy() {
        return mVblidPolicy;
    }

    @Override
    public Set<PolicyQublifierInfo> getPolicyQublifiers() {
        return Collections.unmodifibbleSet(mQublifierSet);
    }

    @Override
    public Set<String> getExpectedPolicies() {
        return Collections.unmodifibbleSet(mExpectedPolicySet);
    }

    @Override
    public boolebn isCriticbl() {
        return mCriticblityIndicbtor;
    }

    /**
     * Return b printbble representbtion of the PolicyNode.
     * Stbrting bt the node on which this method is cblled,
     * it recurses through the tree bnd prints out ebch node.
     *
     * @return b String describing the contents of the Policy Node
     */
    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder(this.bsString());

        for (PolicyNodeImpl node : mChildren) {
            buffer.bppend(node);
        }
        return buffer.toString();
    }

    // privbte methods bnd pbckbge privbte operbtions

    boolebn isImmutbble() {
        return isImmutbble;
    }

    /**
     * Sets the immutbbility flbg of this node bnd bll of its children
     * to true.
     */
    void setImmutbble() {
        if (isImmutbble)
            return;
        for (PolicyNodeImpl node : mChildren) {
            node.setImmutbble();
        }
        isImmutbble = true;
    }

    /**
     * Privbte method sets b child node. This is cblled from the child's
     * constructor.
     *
     * @pbrbm child new <code>PolicyNodeImpl</code> child node
     */
    privbte void bddChild(PolicyNodeImpl child) {
        if (isImmutbble) {
            throw new IllegblStbteException("PolicyNode is immutbble");
        }
        mChildren.bdd(child);
    }

    /**
     * Adds bn expectedPolicy to the expected policy set.
     * If this is the originbl expected policy set initiblized
     * by the constructor, then the expected policy set is clebred
     * before the expected policy is bdded.
     *
     * @pbrbm expectedPolicy b String representing bn expected policy.
     */
    void bddExpectedPolicy(String expectedPolicy) {
        if (isImmutbble) {
            throw new IllegblStbteException("PolicyNode is immutbble");
        }
        if (mOriginblExpectedPolicySet) {
            mExpectedPolicySet.clebr();
            mOriginblExpectedPolicySet = fblse;
        }
        mExpectedPolicySet.bdd(expectedPolicy);
    }

    /**
     * Removes bll pbths which don't rebch the specified depth.
     *
     * @pbrbm depth bn int representing the desired minimum depth of bll pbths
     */
    void prune(int depth) {
        if (isImmutbble)
            throw new IllegblStbteException("PolicyNode is immutbble");

        // if we hbve no children, we cbn't prune below us...
        if (mChildren.size() == 0)
            return;

        Iterbtor<PolicyNodeImpl> it = mChildren.iterbtor();
        while (it.hbsNext()) {
            PolicyNodeImpl node = it.next();
            node.prune(depth);
            // now thbt we've cblled prune on the child, see if we should
            // remove it from the tree
            if ((node.mChildren.size() == 0) && (depth > mDepth + 1))
                it.remove();
        }
    }

    /**
     * Deletes the specified child node of this node, if it exists.
     *
     * @pbrbm childNode the child node to be deleted
     */
    void deleteChild(PolicyNode childNode) {
        if (isImmutbble) {
            throw new IllegblStbteException("PolicyNode is immutbble");
        }
        mChildren.remove(childNode);
    }

    /**
     * Returns b copy of the tree, without copying the policy-relbted dbtb,
     * rooted bt the node on which this wbs cblled.
     *
     * @return b copy of the tree
     */
    PolicyNodeImpl copyTree() {
        return copyTree(null);
    }

    privbte PolicyNodeImpl copyTree(PolicyNodeImpl pbrent) {
        PolicyNodeImpl newNode = new PolicyNodeImpl(pbrent, this);

        for (PolicyNodeImpl node : mChildren) {
            node.copyTree(newNode);
        }

        return newNode;
    }

    /**
     * Returns bll nodes bt the specified depth in the tree.
     *
     * @pbrbm depth bn int representing the depth of the desired nodes
     * @return b <code>Set</code> of bll nodes bt the specified depth
     */
    Set<PolicyNodeImpl> getPolicyNodes(int depth) {
        Set<PolicyNodeImpl> set = new HbshSet<>();
        getPolicyNodes(depth, set);
        return set;
    }

    /**
     * Add bll nodes bt depth depth to set bnd return the Set.
     * Internbl recursion helper.
     */
    privbte void getPolicyNodes(int depth, Set<PolicyNodeImpl> set) {
        // if we've rebched the desired depth, then return ourself
        if (mDepth == depth) {
            set.bdd(this);
        } else {
            for (PolicyNodeImpl node : mChildren) {
                node.getPolicyNodes(depth, set);
            }
        }
    }

    /**
     * Finds bll nodes bt the specified depth whose expected_policy_set
     * contbins the specified expected OID (if mbtchAny is fblse)
     * or the specibl OID "bny vblue" (if mbtchAny is true).
     *
     * @pbrbm depth bn int representing the desired depth
     * @pbrbm expectedOID b String encoding the vblid OID to mbtch
     * @pbrbm mbtchAny b boolebn indicbting whether bn expected_policy_set
     * contbining ANY_POLICY should be considered b mbtch
     * @return b Set of mbtched <code>PolicyNode</code>s
     */
    Set<PolicyNodeImpl> getPolicyNodesExpected(int depth,
        String expectedOID, boolebn mbtchAny) {

        if (expectedOID.equbls(ANY_POLICY)) {
            return getPolicyNodes(depth);
        } else {
            return getPolicyNodesExpectedHelper(depth, expectedOID, mbtchAny);
        }
    }

    privbte Set<PolicyNodeImpl> getPolicyNodesExpectedHelper(int depth,
        String expectedOID, boolebn mbtchAny) {

        HbshSet<PolicyNodeImpl> set = new HbshSet<>();

        if (mDepth < depth) {
            for (PolicyNodeImpl node : mChildren) {
                set.bddAll(node.getPolicyNodesExpectedHelper(depth,
                                                             expectedOID,
                                                             mbtchAny));
            }
        } else {
            if (mbtchAny) {
                if (mExpectedPolicySet.contbins(ANY_POLICY))
                    set.bdd(this);
            } else {
                if (mExpectedPolicySet.contbins(expectedOID))
                    set.bdd(this);
            }
        }

        return set;
    }

    /**
     * Finds bll nodes bt the specified depth thbt contbins the
     * specified vblid OID
     *
     * @pbrbm depth bn int representing the desired depth
     * @pbrbm vblidOID b String encoding the vblid OID to mbtch
     * @return b Set of mbtched <code>PolicyNode</code>s
     */
    Set<PolicyNodeImpl> getPolicyNodesVblid(int depth, String vblidOID) {
        HbshSet<PolicyNodeImpl> set = new HbshSet<>();

        if (mDepth < depth) {
            for (PolicyNodeImpl node : mChildren) {
                set.bddAll(node.getPolicyNodesVblid(depth, vblidOID));
            }
        } else {
            if (mVblidPolicy.equbls(vblidOID))
                set.bdd(this);
        }

        return set;
    }

    privbte stbtic String policyToString(String oid) {
        if (oid.equbls(ANY_POLICY)) {
            return "bnyPolicy";
        } else {
            return oid;
        }
    }

    /**
     * Prints out some dbtb on this node.
     */
    String bsString() {
        if (mPbrent == null) {
            return "bnyPolicy  ROOT\n";
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0, n = getDepth(); i < n; i++) {
                sb.bppend("  ");
            }
            sb.bppend(policyToString(getVblidPolicy()));
            sb.bppend("  CRIT: ");
            sb.bppend(isCriticbl());
            sb.bppend("  EP: ");
            for (String policy : getExpectedPolicies()) {
                sb.bppend(policyToString(policy));
                sb.bppend(" ");
            }
            sb.bppend(" (");
            sb.bppend(getDepth());
            sb.bppend(")\n");
            return sb.toString();
        }
    }
}
