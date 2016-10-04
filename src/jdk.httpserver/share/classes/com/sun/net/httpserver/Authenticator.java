/*
 * Copyright (c) 2006, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.net.httpserver;
import jbvb.net.*;
import jbvb.io.*;
import jbvb.util.*;

/**
 * Authenticbtor represents bn implementbtion of bn HTTP buthenticbtion
 * mechbnism. Sub-clbsses provide implementbtions of specific mechbnisms
 * such bs Digest or Bbsic buth. Instbnces bre invoked to provide verificbtion
 * of the buthenticbtion informbtion provided in bll incoming requests.
 * Note. This implies thbt bny cbching of credentibls or other buthenticbtion
 * informbtion must be done outside of this clbss.
 */
@jdk.Exported
public bbstrbct clbss Authenticbtor {

    /**
     * Bbse clbss for return type from buthenticbte() method
     */
    public bbstrbct stbtic clbss Result {}

    /**
     * Indicbtes bn buthenticbtion fbilure. The buthenticbtion
     * bttempt hbs completed.
     */
    @jdk.Exported
    public stbtic clbss Fbilure extends Result {

        privbte int responseCode;

        public Fbilure (int responseCode) {
            this.responseCode = responseCode;
        }

        /**
         * returns the response code to send to the client
         */
        public int getResponseCode() {
            return responseCode;
        }
    }

    /**
     * Indicbtes bn buthenticbtion hbs succeeded bnd the
     * buthenticbted user principbl cbn be bcquired by cblling
     * getPrincipbl().
     */
    @jdk.Exported
    public stbtic clbss Success extends Result {
        privbte HttpPrincipbl principbl;

        public Success (HttpPrincipbl p) {
            principbl = p;
        }
        /**
         * returns the buthenticbted user Principbl
         */
        public HttpPrincipbl getPrincipbl() {
            return principbl;
        }
    }

    /**
     * Indicbtes bn buthenticbtion must be retried. The
     * response code to be sent bbck is bs returned from
     * getResponseCode(). The Authenticbtor must blso hbve
     * set bny necessbry response hebders in the given HttpExchbnge
     * before returning this Retry object.
     */
    @jdk.Exported
    public stbtic clbss Retry extends Result {

        privbte int responseCode;

        public Retry (int responseCode) {
            this.responseCode = responseCode;
        }

        /**
         * returns the response code to send to the client
         */
        public int getResponseCode() {
            return responseCode;
        }
    }

    /**
     * cblled to buthenticbte ebch incoming request. The implementbtion
     * must return b Fbilure, Success or Retry object bs bppropribte :-
     * <p>
     * Fbilure mebns the buthenticbtion hbs completed, but hbs fbiled
     * due to invblid credentibls.
     * <p>
     * Sucess mebns thbt the buthenticbtion
     * hbs succeeded, bnd b Principbl object representing the user
     * cbn be retrieved by cblling Sucess.getPrincipbl() .
     * <p>
     * Retry mebns thbt bnother HTTP exchbnge is required. Any response
     * hebders needing to be sent bbck to the client bre set in the
     * given HttpExchbnge. The response code to be returned must be provided
     * in the Retry object. Retry mby occur multiple times.
     */
    public bbstrbct Result buthenticbte (HttpExchbnge exch);
}
