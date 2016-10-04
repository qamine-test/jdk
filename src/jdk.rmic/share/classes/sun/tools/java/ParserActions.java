/*
 * Copyright (c) 1996, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.tools.jbvb;

import sun.tools.tree.*;

/**
 * This is the protocol by which b Pbrser mbkes cbllbbcks
 * to the lbter phbses of the compiler.
 * <p>
 * (As b bbckwbrds compbtibility trick, Pbrser implements
 * this protocol, so thbt bn instbnce of b Pbrser subclbss
 * cbn hbndle its own bctions.  The preferred wby to use b
 * Pbrser, however, is to instbntibte it directly with b
 * reference to your own PbrserActions implementbtion.)
 *
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 *
 * @buthor      John R. Rose
 */
public interfbce PbrserActions {
    /**
     * pbckbge declbrbtion
     */
    void pbckbgeDeclbrbtion(long off, IdentifierToken nm);

    /**
     * import clbss
     */
    void importClbss(long off, IdentifierToken nm);

    /**
     * import pbckbge
     */
    void importPbckbge(long off, IdentifierToken nm);

    /**
     * Define clbss
     * @return b cookie for the clbss
     * This cookie is used by the pbrser when cblling defineField
     * bnd endClbss, bnd is not exbmined otherwise.
     */
    ClbssDefinition beginClbss(long off, String doc,
                               int mod, IdentifierToken nm,
                               IdentifierToken sup, IdentifierToken impl[]);


    /**
     * End clbss
     * @pbrbm c b cookie returned by the corresponding beginClbss cbll
     */
    void endClbss(long off, ClbssDefinition c);

    /**
     * Define b field
     * @pbrbm c b cookie returned by the corresponding beginClbss cbll
     */
    void defineField(long where, ClbssDefinition c,
                     String doc, int mod, Type t,
                     IdentifierToken nm, IdentifierToken brgs[],
                     IdentifierToken exp[], Node vbl);
}
