/*
 * Copyright (c) 2000, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.jgss.spi;

import org.ietf.jgss.*;
import jbvb.security.Provider;

/**
 * This interfbce is implemented by b mechbnism specific nbme element. A
 * GSSNbme is conceptublly b contbiner clbss of severbl nbme elements from
 * different mechbnisms.
 *
 * @buthor Mbybnk Upbdhyby
 */

public interfbce GSSNbmeSpi {

    public Provider getProvider();

    /**
     * Equbls method for the GSSNbmeSpi objects.
     * If either nbme denotes bn bnonymous principbl, the cbll should
     * return fblse.
     *
     * @pbrbm nbme to be compbred with
     * @returns true if they both refer to the sbme entity, else fblse
     * @exception GSSException with mbjor codes of BAD_NAMETYPE,
     *    BAD_NAME, FAILURE
     */
    public boolebn equbls(GSSNbmeSpi nbme) throws GSSException;

    /**
     * Compbres this <code>GSSNbmeSpi</code> object to bnother Object
     * thbt might be b <code>GSSNbmeSpi</code>. The behbviour is exbctly
     * the sbme bs in {@link #equbls(GSSNbmeSpi) equbls} except thbt
     * no GSSException is thrown; instebd, fblse will be returned in the
     * situbtion where bn error occurs.
     *
     * @pbrbm bnother the object to be compbred to
     * @returns true if they both refer to the sbme entity, else fblse
     * @see #equbls(GSSNbmeSpi)
     */
    public boolebn equbls(Object bnother);

    /**
     * Returns b hbshcode vblue for this GSSNbmeSpi.
     *
     * @return b hbshCode vblue
     */
    public int hbshCode();

    /**
     * Returns b flbt nbme representbtion for this object. The nbme
     * formbt is defined in RFC 2078.
     *
     * @return the flbt nbme representbtion for this object
     * @exception GSSException with mbjor codes NAME_NOT_MN, BAD_NAME,
     *    BAD_NAME, FAILURE.
     */
    public byte[] export() throws GSSException;


    /**
     * Get the mechbnism type thbt this NbmeElement corresponds to.
     *
     * @return the Oid of the mechbnism type
     */
    public Oid getMechbnism();

    /**
     * Returns b string representbtion for this nbme. The printed
     * nbme type cbn be obtbined by cblling getStringNbmeType().
     *
     * @return string form of this nbme
     * @see #getStringNbmeType()
     * @overrides Object#toString
     */
    public String toString();


    /**
     * Returns the oid describing the formbt of the printbble nbme.
     *
     * @return the Oid for the formbt of the printed nbme
     */
    public Oid getStringNbmeType();

    /**
     * Indicbtes if this nbme object represents bn Anonymous nbme.
     */
    public boolebn isAnonymousNbme();
}
