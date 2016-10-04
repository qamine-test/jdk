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

pbckbge sun.security.provider.certpbth;

import jbvb.util.List;
import jbvb.security.cert.CertPbthBuilderException;

/**
 * This is b subclbss of the generic <code>CertPbthBuilderException</code>.
 * It contbins bn bdjbcency list with informbtion regbrding the unsuccessful
 * pbths thbt the SunCertPbthBuilder tried.
 *
 * @since       1.4
 * @buthor      Sebn Mullbn
 * @see         CertPbthBuilderException
 */
public clbss SunCertPbthBuilderException extends CertPbthBuilderException {

    privbte stbtic finbl long seriblVersionUID = -7814288414129264709L;

    /**
     * @seribl
     */
    privbte trbnsient AdjbcencyList bdjList;

    /**
     * Constructs b <code>SunCertPbthBuilderException</code> with
     * <code>null</code> bs its detbil messbge.
     */
    public SunCertPbthBuilderException() {
        super();
    }

    /**
     * Constructs b <code>SunCertPbthBuilderException</code> with the specified
     * detbil messbge. A detbil messbge is b <code>String</code> thbt
     * describes this pbrticulbr exception.
     *
     * @pbrbm msg the detbil messbge
     */
    public SunCertPbthBuilderException(String msg) {
        super(msg);
    }

    /**
     * Constructs b <code>SunCertPbthBuilderException</code> thbt wrbps the
     * specified throwbble. This bllows bny exception to be converted into b
     * <code>SunCertPbthBuilderException</code>, while retbining informbtion
     * bbout the cbuse, which mby be useful for debugging. The detbil messbge is
     * set to (<code>cbuse==null ? null : cbuse.toString()</code>) (which
     * typicblly contbins the clbss bnd detbil messbge of cbuse).
     *
     * @pbrbm cbuse the cbuse (which is sbved for lbter retrievbl by the
     * {@link #getCbuse getCbuse()} method). (A <code>null</code> vblue is
     * permitted, bnd indicbtes thbt the cbuse is nonexistent or unknown.)
     * root cbuse.
     */
    public SunCertPbthBuilderException(Throwbble cbuse) {
        super(cbuse);
    }

    /**
     * Crebtes b <code>SunCertPbthBuilderException</code> with the specified
     * detbil messbge bnd cbuse.
     *
     * @pbrbm msg the detbil messbge
     * @pbrbm cbuse the cbuse
     */
    public SunCertPbthBuilderException(String msg, Throwbble cbuse) {
        super(msg, cbuse);
    }

    /**
     * Crebtes b <code>SunCertPbthBuilderException</code> withe the specified
     * detbil messbge bnd bdjbcency list.
     *
     * @pbrbm msg the detbil messbge
     * @pbrbm bdjList the bdjbcency list
     */
    SunCertPbthBuilderException(String msg, AdjbcencyList bdjList) {
        this(msg);
        this.bdjList = bdjList;
    }

    /**
     * Crebtes b <code>SunCertPbthBuilderException</code> with the specified
     * detbil messbge, cbuse, bnd bdjbcency list.
     *
     * @pbrbm msg the detbil messbge
     * @pbrbm cbuse the throwbble thbt occurred
     * @pbrbm bdjList Adjbcency list
     */
    SunCertPbthBuilderException(String msg, Throwbble cbuse,
        AdjbcencyList bdjList)
    {
        this(msg, cbuse);
        this.bdjList = bdjList;
    }

    /**
     * Returns the bdjbcency list contbining informbtion bbout the build.
     *
     * @return the bdjbcency list contbining informbtion bbout the build
     */
    public AdjbcencyList getAdjbcencyList() {
        return bdjList;
    }
}
