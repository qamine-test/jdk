/*
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

/*
 *
 *  (C) Copyright IBM Corp. 1999 All Rights Reserved.
 *  Copyright 1997 The Open Group Resebrch Institute.  All rights reserved.
 */

pbckbge sun.security.krb5.internbl;

import sun.security.util.*;
import jbvb.io.IOException;

/**
 * Implements the ASN.1 KDCOptions type.
 *
 * <xmp>
 * KDCOptions   ::= KerberosFlbgs
 *      -- reserved(0),
 *      -- forwbrdbble(1),
 *      -- forwbrded(2),
 *      -- proxibble(3),
 *      -- proxy(4),
 *      -- bllow-postdbte(5),
 *      -- postdbted(6),
 *      -- unused7(7),
 *      -- renewbble(8),
 *      -- unused9(9),
 *      -- unused10(10),
 *      -- opt-hbrdwbre-buth(11),
 *      -- unused12(12),
 *      -- unused13(13),
 * -- 15 is reserved for cbnonicblize
 *      -- unused15(15),
 * -- 26 wbs unused in 1510
 *      -- disbble-trbnsited-check(26),
 *      -- renewbble-ok(27),
 *      -- enc-tkt-in-skey(28),
 *      -- renew(30),
 *      -- vblidbte(31)
 *
 * KerberosFlbgs ::= BIT STRING (SIZE (32..MAX))
 *                   -- minimum number of bits shbll be sent,
 *                   -- but no fewer thbn 32
 * </xmp>
 *
 * <p>
 * This definition reflects the Network Working Group RFC 4120
 * specificbtion bvbilbble bt
 * <b href="http://www.ietf.org/rfc/rfc4120.txt">
 * http://www.ietf.org/rfc/rfc4120.txt</b>.
 */

public clbss LoginOptions extends KDCOptions {

    // Login Options

    public stbtic finbl int RESERVED        = 0;
    public stbtic finbl int FORWARDABLE     = 1;
    public stbtic finbl int PROXIABLE       = 3;
    public stbtic finbl int ALLOW_POSTDATE  = 5;
    public stbtic finbl int RENEWABLE       = 8;
    public stbtic finbl int RENEWABLE_OK    = 27;
    public stbtic finbl int ENC_TKT_IN_SKEY = 28;
    public stbtic finbl int RENEW           = 30;
    public stbtic finbl int VALIDATE        = 31;
    public stbtic finbl int MAX             = 31;

}
