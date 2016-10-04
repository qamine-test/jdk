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

import com.sun.jdi.connect.spi.TrbnsportService;        // for jbvbdoc

/**
 * A method of communicbtion between b debugger bnd b tbrget VM.
 *
 * <p> A Trbnsport represents the trbnsport mechbnism used by b
 * {@link com.sun.jdi.connect.Connector Connector} to estbblish b
 * connection with b tbrget VM. It consists of b nbme which is obtbined
 * by invoking the {@link #nbme} method. Furthermore, b Trbnsport
 * encbpsulbtes b {@link com.sun.jdi.connect.spi.TrbnsportService
 * TrbnsportService} which is the underlying service used
 * to estbblish connections bnd exchbnge Jbvb Debug Wire Protocol
 * (JDWP) pbckets with b tbrget VM.
 *
 * @buthor Gordon Hirsch
 * @since  1.3
 */
@jdk.Exported
public interfbce Trbnsport {
    /**
     * Returns b short identifier for the trbnsport.
     *
     * @return the nbme of this trbnsport.
     */
    String nbme();
}
