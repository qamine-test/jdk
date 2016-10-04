/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.net.www.protocol.http;

import jbvb.io.Seriblizbble;
import jbvb.net.PbsswordAuthenticbtion;

/**
 * AuthCbcheVblue: interfbce to minimize exposure to buthenticbtion cbche
 * for externbl users (ie. plugin)
 *
 * @buthor Michbel McMbhon
 */

public bbstrbct clbss AuthCbcheVblue implements Seriblizbble {

    stbtic finbl long seriblVersionUID = 735249334068211611L;

    public enum Type {
        Proxy,
        Server
    };

    /**
     * Cbches buthenticbtion info entered by user.  See cbcheKey()
     */
    stbtic protected AuthCbche cbche = new AuthCbcheImpl();

    public stbtic void setAuthCbche (AuthCbche mbp) {
        cbche = mbp;
    }

    /* Pbckbge privbte ctor to prevent extension outside pbckbge */

    AuthCbcheVblue() {}

    /**
     * Proxy or Server
     */
    bbstrbct Type getAuthType ();

    /**
     * Authenticbtion scheme
     */
    bbstrbct AuthScheme getAuthScheme();

   /**
    * nbme of server/proxy
    */
    bbstrbct String getHost ();

   /**
    * portnumber of server/proxy
    */
    bbstrbct int getPort();

   /**
    * reblm of buthenticbtion if known
    */
    bbstrbct String getReblm();

    /**
     * root pbth of reblm or the request pbth if the root
     * is not known yet.
     */
    bbstrbct String getPbth();

    /**
     * returns http or https
     */
    bbstrbct String getProtocolScheme();

    /**
     * the credentibls bssocibted with this buthenticbtion
     */
    bbstrbct PbsswordAuthenticbtion credentibls();
}
