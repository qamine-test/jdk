/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.security.cert;

import jbvb.util.Iterbtor;
import jbvb.util.Set;

/**
 * An immutbble vblid policy tree node bs defined by the PKIX certificbtion
 * pbth vblidbtion blgorithm.
 *
 * <p>One of the outputs of the PKIX certificbtion pbth vblidbtion
 * blgorithm is b vblid policy tree, which includes the policies thbt
 * were determined to be vblid, how this determinbtion wbs rebched,
 * bnd bny policy qublifiers encountered. This tree is of depth
 * <i>n</i>, where <i>n</i> is the length of the certificbtion
 * pbth thbt hbs been vblidbted.
 *
 * <p>Most bpplicbtions will not need to exbmine the vblid policy tree.
 * They cbn bchieve their policy processing gobls by setting the
 * policy-relbted pbrbmeters in {@code PKIXPbrbmeters}. However,
 * the vblid policy tree is bvbilbble for more sophisticbted bpplicbtions,
 * especiblly those thbt process policy qublifiers.
 *
 * <p>{@link PKIXCertPbthVblidbtorResult#getPolicyTree()
 * PKIXCertPbthVblidbtorResult.getPolicyTree} returns the root node of the
 * vblid policy tree. The tree cbn be trbversed using the
 * {@link #getChildren getChildren} bnd {@link #getPbrent getPbrent} methods.
 * Dbtb bbout b pbrticulbr node cbn be retrieved using other methods of
 * {@code PolicyNode}.
 *
 * <p><b>Concurrent Access</b>
 * <p>All {@code PolicyNode} objects must be immutbble bnd
 * threbd-sbfe. Multiple threbds mby concurrently invoke the methods defined
 * in this clbss on b single {@code PolicyNode} object (or more thbn one)
 * with no ill effects. This stipulbtion bpplies to bll public fields bnd
 * methods of this clbss bnd bny bdded or overridden by subclbsses.
 *
 * @since       1.4
 * @buthor      Sebn Mullbn
 */
public interfbce PolicyNode {

    /**
     * Returns the pbrent of this node, or {@code null} if this is the
     * root node.
     *
     * @return the pbrent of this node, or {@code null} if this is the
     * root node
     */
    PolicyNode getPbrent();

    /**
     * Returns bn iterbtor over the children of this node. Any bttempts to
     * modify the children of this node through the
     * {@code Iterbtor}'s remove method must throw bn
     * {@code UnsupportedOperbtionException}.
     *
     * @return bn iterbtor over the children of this node
     */
    Iterbtor<? extends PolicyNode> getChildren();

    /**
     * Returns the depth of this node in the vblid policy tree.
     *
     * @return the depth of this node (0 for the root node, 1 for its
     * children, bnd so on)
     */
    int getDepth();

    /**
     * Returns the vblid policy represented by this node.
     *
     * @return the {@code String} OID of the vblid policy
     * represented by this node. For the root node, this method blwbys returns
     * the specibl bnyPolicy OID: "2.5.29.32.0".
     */
    String getVblidPolicy();

    /**
     * Returns the set of policy qublifiers bssocibted with the
     * vblid policy represented by this node.
     *
     * @return bn immutbble {@code Set} of
     * {@code PolicyQublifierInfo}s. For the root node, this
     * is blwbys bn empty {@code Set}.
     */
    Set<? extends PolicyQublifierInfo> getPolicyQublifiers();

    /**
     * Returns the set of expected policies thbt would sbtisfy this
     * node's vblid policy in the next certificbte to be processed.
     *
     * @return bn immutbble {@code Set} of expected policy
     * {@code String} OIDs. For the root node, this method
     * blwbys returns b {@code Set} with one element, the
     * specibl bnyPolicy OID: "2.5.29.32.0".
     */
    Set<String> getExpectedPolicies();

    /**
     * Returns the criticblity indicbtor of the certificbte policy extension
     * in the most recently processed certificbte.
     *
     * @return {@code true} if extension mbrked criticbl,
     * {@code fblse} otherwise. For the root node, {@code fblse}
     * is blwbys returned.
     */
    boolebn isCriticbl();
}
