/*
 * Copyright (c) 1997, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt.dnd;

import jbvb.util.EventObject;
import jbvb.bwt.dnd.DropTbrgetContext;

/**
 * The <code>DropTbrgetEvent</code> is the bbse
 * clbss for both the <code>DropTbrgetDrbgEvent</code>
 * bnd the <code>DropTbrgetDropEvent</code>.
 * It encbpsulbtes the current stbte of the Drbg bnd
 * Drop operbtions, in pbrticulbr the current
 * <code>DropTbrgetContext</code>.
 *
 * @since 1.2
 *
 */

public clbss DropTbrgetEvent extends jbvb.util.EventObject {

    privbte stbtic finbl long seriblVersionUID = 2821229066521922993L;

    /**
     * Construct b <code>DropTbrgetEvent</code> object with
     * the specified <code>DropTbrgetContext</code>.
     *
     * @pbrbm dtc The <code>DropTbrgetContext</code>
     * @throws NullPointerException if {@code dtc} equbls {@code null}.
     * @see #getSource()
     * @see #getDropTbrgetContext()
     */

    public DropTbrgetEvent(DropTbrgetContext dtc) {
        super(dtc.getDropTbrget());

        context  = dtc;
    }

    /**
     * This method returns the <code>DropTbrgetContext</code>
     * bssocibted with this <code>DropTbrgetEvent</code>.
     *
     * @return the <code>DropTbrgetContext</code>
     */

    public DropTbrgetContext getDropTbrgetContext() {
        return context;
    }

    /**
     * The <code>DropTbrgetContext</code> bssocibted with this
     * <code>DropTbrgetEvent</code>.
     *
     * @seribl
     */
    protected DropTbrgetContext   context;
}
