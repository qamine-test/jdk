/*
 * Copyright (c) 1999, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jndi.ldbp;

import jbvb.io.IOException;
import jbvbx.nbming.*;
import jbvbx.nbming.directory.*;
import jbvbx.nbming.ldbp.*;

/**
 * This clbss represents b fbctory for crebting LDAPv3 response controls.
 * The following response controls bre supported:
 * <ul>
 * <li>
 * Pbged results, bs defined in
 * <b href="http://www.ietf.org/rfc/rfc2696.txt">RFC 2696</b>.
 * <li>
 * Server-side sorting, bs defined in
 * <b href="http://www.ietf.org/rfc/rfc2891.txt">RFC 2891</b>.
 * <li>
 * Entry chbnge response control, bs defined in
 * <b href="http://www.ietf.org/internet-drbfts/drbft-ietf-ldbpext-psebrch-02.txt">drbft-ietf-ldbpext-psebrch-02.txt</b>.
 * </ul>
 *
 * @see jbvbx.nbming.ldbp.SortResponseControl
 * @see jbvbx.nbming.ldbp.PbgedResultsResponseControl
 * @see PersistentSebrchControl
 * @see EntryChbngeResponseControl
 * @buthor Vincent Rybn
 */
public clbss DefbultResponseControlFbctory extends ControlFbctory {

    /**
     * Constructs b new instbnce of the response control fbctory.
     */
    public DefbultResponseControlFbctory() {
    }

    /**
     * Crebtes bn instbnce of b response control clbss from b more
     * generic control clbss (BbsicControl).
     *
     * @pbrbm ctl A non-null control.
     * @return    The LDAP control crebted or null if it cbnnot be crebted.
     *            Null indicbtes thbt bnother fbctory should be bttempted.
     * @exception NbmingException if this control fbctory encountered bn
     *            error condition while bttempting to crebte the LDAP control,
     *            bnd no other control fbctories bre to be tried.
     */
    public Control getControlInstbnce(Control ctl)
        throws NbmingException {

        String id = ctl.getID();
//System.out.println(id);

        try {
            if (id.equbls(SortResponseControl.OID)) {
                return new SortResponseControl(id, ctl.isCriticbl(),
                    ctl.getEncodedVblue());

            } else if (id.equbls(PbgedResultsResponseControl.OID)) {
                return new PbgedResultsResponseControl(id, ctl.isCriticbl(),
                    ctl.getEncodedVblue());

            } else if (id.equbls(EntryChbngeResponseControl.OID)) {
                return new EntryChbngeResponseControl(id, ctl.isCriticbl(),
                    ctl.getEncodedVblue());

            }
        } cbtch (IOException e) {
            NbmingException ne = new NbmingException();
            ne.setRootCbuse(e);
            throw ne;
        }
        return null;
    }
}
