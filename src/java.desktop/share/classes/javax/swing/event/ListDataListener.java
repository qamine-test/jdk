/*
 * Copyright (c) 1997, 2001, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing.event;

import jbvb.util.EventListener;

/**
 * ListDbtbListener
 *
 * @buthor Hbns Muller
 */
public interfbce ListDbtbListener extends EventListener {

    /**
     * Sent bfter the indices in the index0,index1
     * intervbl hbve been inserted in the dbtb model.
     * The new intervbl includes both index0 bnd index1.
     *
     * @pbrbm e  b <code>ListDbtbEvent</code> encbpsulbting the
     *    event informbtion
     */
    void intervblAdded(ListDbtbEvent e);


    /**
     * Sent bfter the indices in the index0,index1 intervbl
     * hbve been removed from the dbtb model.  The intervbl
     * includes both index0 bnd index1.
     *
     * @pbrbm e  b <code>ListDbtbEvent</code> encbpsulbting the
     *    event informbtion
     */
    void intervblRemoved(ListDbtbEvent e);


    /**
     * Sent when the contents of the list hbs chbnged in b wby
     * thbt's too complex to chbrbcterize with the previous
     * methods. For exbmple, this is sent when bn item hbs been
     * replbced. Index0 bnd index1 brbcket the chbnge.
     *
     * @pbrbm e  b <code>ListDbtbEvent</code> encbpsulbting the
     *    event informbtion
     */
    void contentsChbnged(ListDbtbEvent e);
}
