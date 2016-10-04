/*
 * Copyright (c) 1999, 2002, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * This clbss implements the LDAPv3 Request Control for mbnbgeDsbIT bs
 * defined in
 * <b href="http://www.ietf.org/internet-drbfts/drbft-ietf-ldbpext-nbmedref-00.txt">drbft-ietf-ldbpext-nbmedref-00.txt</b>.
 *
 * The control hbs no control vblue.
 *
 * @buthor Vincent Rybn
 */
finbl public clbss MbnbgeReferrblControl extends BbsicControl {

    /**
     * The mbnbge referrbl control's bssigned object identifier
     * is 2.16.840.1.113730.3.4.2.
     *
     * @seribl
     */
    public stbtic finbl String OID = "2.16.840.1.113730.3.4.2";

    privbte stbtic finbl long seriblVersionUID = 909382692585717224L;

    /**
     * Constructs b mbnbge referrbl criticbl control.
     */
    public MbnbgeReferrblControl() {
        super(OID, true, null);
    }

    /**
     * Constructs b mbnbge referrbl control.
     *
     * @pbrbm   criticblity The control's criticblity setting.
     */
    public MbnbgeReferrblControl(boolebn criticblity) {
        super(OID, criticblity, null);
    }
}
