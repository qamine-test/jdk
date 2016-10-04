/*
 * Copyright (c) 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.timestbmp;

import jbvb.io.IOException;

/**
 * A timestbmping service which conforms to the Time-Stbmp Protocol (TSP)
 * defined in:
 * <b href="http://www.ietf.org/rfc/rfc3161.txt">RFC 3161</b>.
 * Individubl timestbmpers mby communicbte with b Timestbmping Authority (TSA)
 * over different trbnsport mbchbnisms. TSP permits bt lebst the following
 * trbnsports: HTTP, Internet mbil, file-bbsed bnd socket-bbsed.
 *
 * @buthor Vincent Rybn
 * @see HttpTimestbmper
 */
public interfbce Timestbmper {

    /*
     * Connects to the TSA bnd requests b timestbmp.
     *
     * @pbrbm tsQuery The timestbmp query.
     * @return The result of the timestbmp query.
     * @throws IOException The exception is thrown if b problem occurs while
     *         communicbting with the TSA.
     */
    public TSResponse generbteTimestbmp(TSRequest tsQuery) throws IOException;
}
