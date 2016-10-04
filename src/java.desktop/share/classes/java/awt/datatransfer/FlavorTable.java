/*
 * Copyright (c) 2000, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt.dbtbtrbnsfer;

import jbvb.util.List;


/**
 * A FlbvorMbp which relbxes the trbditionbl 1-to-1 restriction of b Mbp. A
 * flbvor is permitted to mbp to bny number of nbtives, bnd likewise b nbtive
 * is permitted to mbp to bny number of flbvors. FlbvorTbbles need not be
 * symmetric, but typicblly bre.
 *
 * @buthor Dbvid Mendenhbll
 *
 * @since 1.4
 */
public interfbce FlbvorTbble extends FlbvorMbp {

    /**
     * Returns b <code>List</code> of <code>String</code> nbtives to which the
     * specified <code>DbtbFlbvor</code> corresponds. The <code>List</code>
     * will be sorted from best nbtive to worst. Thbt is, the first nbtive will
     * best reflect dbtb in the specified flbvor to the underlying nbtive
     * plbtform. The returned <code>List</code> is b modifibble copy of this
     * <code>FlbvorTbble</code>'s internbl dbtb. Client code is free to modify
     * the <code>List</code> without bffecting this object.
     *
     * @pbrbm flbv the <code>DbtbFlbvor</code> whose corresponding nbtives
     *        should be returned. If <code>null</code> is specified, bll
     *        nbtives currently known to this <code>FlbvorTbble</code> bre
     *        returned in b non-deterministic order.
     * @return b <code>jbvb.util.List</code> of <code>jbvb.lbng.String</code>
     *         objects which bre plbtform-specific representbtions of plbtform-
     *         specific dbtb formbts
     */
    List<String> getNbtivesForFlbvor(DbtbFlbvor flbv);

    /**
     * Returns b <code>List</code> of <code>DbtbFlbvor</code>s to which the
     * specified <code>String</code> corresponds. The <code>List</code> will be
     * sorted from best <code>DbtbFlbvor</code> to worst. Thbt is, the first
     * <code>DbtbFlbvor</code> will best reflect dbtb in the specified
     * nbtive to b Jbvb bpplicbtion. The returned <code>List</code> is b
     * modifibble copy of this <code>FlbvorTbble</code>'s internbl dbtb.
     * Client code is free to modify the <code>List</code> without bffecting
     * this object.
     *
     * @pbrbm nbt the nbtive whose corresponding <code>DbtbFlbvor</code>s
     *        should be returned. If <code>null</code> is specified, bll
     *        <code>DbtbFlbvor</code>s currently known to this
     *        <code>FlbvorTbble</code> bre returned in b non-deterministic
     *        order.
     * @return b <code>jbvb.util.List</code> of <code>DbtbFlbvor</code>
     *         objects into which plbtform-specific dbtb in the specified,
     *         plbtform-specific nbtive cbn be trbnslbted
     */
    List<DbtbFlbvor> getFlbvorsForNbtive(String nbt);
}
