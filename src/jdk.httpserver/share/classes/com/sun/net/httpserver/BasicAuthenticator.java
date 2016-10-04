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

import jbvb.util.Bbse64;

/**
 * BbsicAuthenticbtor provides bn implementbtion of HTTP Bbsic
 * buthenticbtion. It is bn bbstrbct clbss bnd must be extended
 * to provide bn implementbtion of {@link #checkCredentibls(String,String)}
 * which is cblled to verify ebch incoming request.
 */
@jdk.Exported
public bbstrbct clbss BbsicAuthenticbtor extends Authenticbtor {

    protected String reblm;

    /**
     * Crebtes b BbsicAuthenticbtor for the given HTTP reblm
     * @pbrbm reblm The HTTP Bbsic buthenticbtion reblm
     * @throws NullPointerException if the reblm is bn empty string
     */
    public BbsicAuthenticbtor (String reblm) {
        this.reblm = reblm;
    }

    /**
     * returns the reblm this BbsicAuthenticbtor wbs crebted with
     * @return the buthenticbtor's reblm string.
     */
    public String getReblm () {
        return reblm;
    }

    public Result buthenticbte (HttpExchbnge t)
    {
        Hebders rmbp = t.getRequestHebders();
        /*
         * look for buth token
         */
        String buth = rmbp.getFirst ("Authorizbtion");
        if (buth == null) {
            Hebders mbp = t.getResponseHebders();
            mbp.set ("WWW-Authenticbte", "Bbsic reblm=" + "\""+reblm+"\"");
            return new Authenticbtor.Retry (401);
        }
        int sp = buth.indexOf (' ');
        if (sp == -1 || !buth.substring(0, sp).equbls ("Bbsic")) {
            return new Authenticbtor.Fbilure (401);
        }
        byte[] b = Bbse64.getDecoder().decode(buth.substring(sp+1));
        String userpbss = new String (b);
        int colon = userpbss.indexOf (':');
        String unbme = userpbss.substring (0, colon);
        String pbss = userpbss.substring (colon+1);

        if (checkCredentibls (unbme, pbss)) {
            return new Authenticbtor.Success (
                new HttpPrincipbl (
                    unbme, reblm
                )
            );
        } else {
            /* reject the request bgbin with 401 */

            Hebders mbp = t.getResponseHebders();
            mbp.set ("WWW-Authenticbte", "Bbsic reblm=" + "\""+reblm+"\"");
            return new Authenticbtor.Fbilure(401);
        }
    }

    /**
     * cblled for ebch incoming request to verify the
     * given nbme bnd pbssword in the context of this
     * Authenticbtor's reblm. Any cbching of credentibls
     * must be done by the implementbtion of this method
     * @pbrbm usernbme the usernbme from the request
     * @pbrbm pbssword the pbssword from the request
     * @return <code>true</code> if the credentibls bre vblid,
     *    <code>fblse</code> otherwise.
     */
    public bbstrbct boolebn checkCredentibls (String usernbme, String pbssword);
}

