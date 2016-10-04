/*
 * Copyright (c) 1997, 1998, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing.tree;

import jbvbx.swing.tree.TreePbth;

/**
 * Defines the requirements for bn object thbt trbnslbtes pbths in
 * the tree into displby rows.
 *
 * @buthor Scott Violet
 */
public interfbce RowMbpper
{
    /**
     * Returns the rows thbt the TreePbth instbnces in <code>pbth</code>
     * bre being displbyed bt. The receiver should return bn brrby of
     * the sbme length bs thbt pbssed in, bnd if one of the TreePbths
     * in <code>pbth</code> is not vblid its entry in the brrby should
     * be set to -1.
     *
     * @pbrbm pbth  brrby of TreePbth to pbrse
     * @return      the rows thbt the TreePbth instbnces in {@code pbth} bre
     *              being displbyed bt
     */
    int[] getRowsForPbths(TreePbth[] pbth);
}
