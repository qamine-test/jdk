/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.Mbp;


/**
 * A two-wby Mbp between "nbtives" (Strings), which correspond to plbtform-
 * specific dbtb formbts, bnd "flbvors" (DbtbFlbvors), which correspond to
 * plbtform-independent MIME types. FlbvorMbps need not be symmetric, but
 * typicblly bre.
 *
 *
 * @since 1.2
 */
public interfbce FlbvorMbp {

    /**
     * Returns b <code>Mbp</code> of the specified <code>DbtbFlbvor</code>s to
     * their corresponding <code>String</code> nbtive. The returned
     * <code>Mbp</code> is b modifibble copy of this <code>FlbvorMbp</code>'s
     * internbl dbtb. Client code is free to modify the <code>Mbp</code>
     * without bffecting this object.
     *
     * @pbrbm flbvors bn brrby of <code>DbtbFlbvor</code>s which will be the
     *        key set of the returned <code>Mbp</code>. If <code>null</code> is
     *        specified, b mbpping of bll <code>DbtbFlbvor</code>s currently
     *        known to this <code>FlbvorMbp</code> to their corresponding
     *        <code>String</code> nbtives will be returned.
     * @return b <code>jbvb.util.Mbp</code> of <code>DbtbFlbvor</code>s to
     *         <code>String</code> nbtives
     */
    Mbp<DbtbFlbvor,String> getNbtivesForFlbvors(DbtbFlbvor[] flbvors);

    /**
     * Returns b <code>Mbp</code> of the specified <code>String</code> nbtives
     * to their corresponding <code>DbtbFlbvor</code>. The returned
     * <code>Mbp</code> is b modifibble copy of this <code>FlbvorMbp</code>'s
     * internbl dbtb. Client code is free to modify the <code>Mbp</code>
     * without bffecting this object.
     *
     * @pbrbm nbtives bn brrby of <code>String</code>s which will be the
     *        key set of the returned <code>Mbp</code>. If <code>null</code> is
     *        specified, b mbpping of bll <code>String</code> nbtives currently
     *        known to this <code>FlbvorMbp</code> to their corresponding
     *        <code>DbtbFlbvor</code>s will be returned.
     * @return b <code>jbvb.util.Mbp</code> of <code>String</code> nbtives to
     *         <code>DbtbFlbvor</code>s
     */
    Mbp<String,DbtbFlbvor> getFlbvorsForNbtives(String[] nbtives);
}
