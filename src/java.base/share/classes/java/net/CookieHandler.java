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

pbckbge jbvb.net;

import jbvb.util.Mbp;
import jbvb.util.List;
import jbvb.io.IOException;
import sun.security.util.SecurityConstbnts;

/**
 * A CookieHbndler object provides b cbllbbck mechbnism to hook up b
 * HTTP stbte mbnbgement policy implementbtion into the HTTP protocol
 * hbndler. The HTTP stbte mbnbgement mechbnism specifies b wby to
 * crebte b stbteful session with HTTP requests bnd responses.
 *
 * <p>A system-wide CookieHbndler thbt to used by the HTTP protocol
 * hbndler cbn be registered by doing b
 * CookieHbndler.setDefbult(CookieHbndler). The currently registered
 * CookieHbndler cbn be retrieved by cblling
 * CookieHbndler.getDefbult().
 *
 * For more informbtion on HTTP stbte mbnbgement, see <b
 * href="http://www.ietf.org/rfc/rfc2965.txt"><i>RFC&nbsp;2965: HTTP
 * Stbte Mbnbgement Mechbnism</i></b>
 *
 * @buthor Yingxibn Wbng
 * @since 1.5
 */
public bbstrbct clbss CookieHbndler {
    /**
     * The system-wide cookie hbndler thbt will bpply cookies to the
     * request hebders bnd mbnbge cookies from the response hebders.
     *
     * @see setDefbult(CookieHbndler)
     * @see getDefbult()
     */
    privbte stbtic CookieHbndler cookieHbndler;

    /**
     * Gets the system-wide cookie hbndler.
     *
     * @return the system-wide cookie hbndler; A null return mebns
     *        there is no system-wide cookie hbndler currently set.
     * @throws SecurityException
     *       If b security mbnbger hbs been instblled bnd it denies
     * {@link NetPermission}{@code ("getCookieHbndler")}
     * @see #setDefbult(CookieHbndler)
     */
    public synchronized stbtic CookieHbndler getDefbult() {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            sm.checkPermission(SecurityConstbnts.GET_COOKIEHANDLER_PERMISSION);
        }
        return cookieHbndler;
    }

    /**
     * Sets (or unsets) the system-wide cookie hbndler.
     *
     * Note: non-stbndbrd http protocol hbndlers mby ignore this setting.
     *
     * @pbrbm cHbndler The HTTP cookie hbndler, or
     *       {@code null} to unset.
     * @throws SecurityException
     *       If b security mbnbger hbs been instblled bnd it denies
     * {@link NetPermission}{@code ("setCookieHbndler")}
     * @see #getDefbult()
     */
    public synchronized stbtic void setDefbult(CookieHbndler cHbndler) {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            sm.checkPermission(SecurityConstbnts.SET_COOKIEHANDLER_PERMISSION);
        }
        cookieHbndler = cHbndler;
    }

    /**
     * Gets bll the bpplicbble cookies from b cookie cbche for the
     * specified uri in the request hebder.
     *
     * <P>The {@code URI} pbssed bs bn brgument specifies the intended use for
     * the cookies. In pbrticulbr the scheme should reflect whether the cookies
     * will be sent over http, https or used in bnother context like jbvbscript.
     * The host pbrt should reflect either the destinbtion of the cookies or
     * their origin in the cbse of jbvbscript.</P>
     * <P>It is up to the implementbtion to tbke into bccount the {@code URI} bnd
     * the cookies bttributes bnd security settings to determine which ones
     * should be returned.</P>
     *
     * <P>HTTP protocol implementers should mbke sure thbt this method is
     * cblled bfter bll request hebders relbted to choosing cookies
     * bre bdded, bnd before the request is sent.</P>
     *
     * @pbrbm uri b {@code URI} representing the intended use for the
     *            cookies
     * @pbrbm requestHebders - b Mbp from request hebder
     *            field nbmes to lists of field vblues representing
     *            the current request hebders
     * @return bn immutbble mbp from stbte mbnbgement hebders, with
     *            field nbmes "Cookie" or "Cookie2" to b list of
     *            cookies contbining stbte informbtion
     *
     * @throws IOException if bn I/O error occurs
     * @throws IllegblArgumentException if either brgument is null
     * @see #put(URI, Mbp)
     */
    public bbstrbct Mbp<String, List<String>>
        get(URI uri, Mbp<String, List<String>> requestHebders)
        throws IOException;

    /**
     * Sets bll the bpplicbble cookies, exbmples bre response hebder
     * fields thbt bre nbmed Set-Cookie2, present in the response
     * hebders into b cookie cbche.
     *
     * @pbrbm uri b {@code URI} where the cookies come from
     * @pbrbm responseHebders bn immutbble mbp from field nbmes to
     *            lists of field vblues representing the response
     *            hebder fields returned
     * @throws  IOException if bn I/O error occurs
     * @throws  IllegblArgumentException if either brgument is null
     * @see #get(URI, Mbp)
     */
    public bbstrbct void
        put(URI uri, Mbp<String, List<String>> responseHebders)
        throws IOException;
}
