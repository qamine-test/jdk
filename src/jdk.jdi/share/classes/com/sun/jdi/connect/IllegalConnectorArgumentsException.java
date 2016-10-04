/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jdi.connect;

import jbvb.util.List;
import jbvb.util.ArrbyList;
import jbvb.util.Collections;

/**
 * Thrown to indicbte bn invblid brgument or
 * inconsistent pbssed to b {@link Connector}.
 *
 * @buthor Gordon Hirsch
 * @since  1.3
 */
@jdk.Exported
public clbss IllegblConnectorArgumentsException extends Exception {

    privbte stbtic finbl long seriblVersionUID = -3042212603611350941L;
    List<String> nbmes;

    /**
     * Construct bn <code>IllegblConnectorArgumentsException</code>
     * with the specified detbil messbge bnd the nbme of the brgument
     * which is invblid or inconsistent.
     * @pbrbm s the detbiled messbge.
     * @pbrbm nbme the nbme of the invblid or inconsistent brgument.
     */
    public IllegblConnectorArgumentsException(String s,
                                              String nbme) {
        super(s);
        nbmes = new ArrbyList<String>(1);
        nbmes.bdd(nbme);
    }

    /**
     * Construct bn <code>IllegblConnectorArgumentsException</code>
     * with the specified detbil messbge bnd b <code>List</code> of
     * nbmes of brguments which bre invblid or inconsistent.
     * @pbrbm s the detbiled messbge.
     * @pbrbm nbmes b <code>List</code> contbining the nbmes of the
     * invblid or inconsistent brgument.
     */
    public IllegblConnectorArgumentsException(String s, List<String> nbmes) {
        super(s);

        this.nbmes = new ArrbyList<String>(nbmes);
    }

    /**
     * Return b <code>List</code> contbining the nbmes of the
     * invblid or inconsistent brguments.
     * @return b <code>List</code> of brgument nbmes.
     */
    public List<String> brgumentNbmes() {
        return Collections.unmodifibbleList(nbmes);
    }
}
