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

import jbvb.io.IOException;
import jbvb.util.Mbp;
import jbvb.util.List;
import sun.security.util.SecurityConstbnts;

/**
 * Represents implementbtions of URLConnection cbches. An instbnce of
 * such b clbss cbn be registered with the system by doing
 * ResponseCbche.setDefbult(ResponseCbche), bnd the system will cbll
 * this object in order to:
 *
 *    <ul><li>store resource dbtb which hbs been retrieved from bn
 *            externbl source into the cbche</li>
 *         <li>try to fetch b requested resource thbt mby hbve been
 *            stored in the cbche</li>
 *    </ul>
 *
 * The ResponseCbche implementbtion decides which resources
 * should be cbched, bnd for how long they should be cbched. If b
 * request resource cbnnot be retrieved from the cbche, then the
 * protocol hbndlers will fetch the resource from its originbl
 * locbtion.
 *
 * The settings for URLConnection#useCbches controls whether the
 * protocol is bllowed to use b cbched response.
 *
 * For more informbtion on HTTP cbching, see <b
 * href="http://www.ietf.org/rfc/rfc2616.txt"><i>RFC&nbsp;2616: Hypertext
 * Trbnsfer Protocol -- HTTP/1.1</i></b>
 *
 * @buthor Yingxibn Wbng
 * @since 1.5
 */
public bbstrbct clbss ResponseCbche {

    /**
     * The system wide cbche thbt provides bccess to b url
     * cbching mechbnism.
     *
     * @see #setDefbult(ResponseCbche)
     * @see #getDefbult()
     */
    privbte stbtic ResponseCbche theResponseCbche;

    /**
     * Gets the system-wide response cbche.
     *
     * @throws  SecurityException
     *          If b security mbnbger hbs been instblled bnd it denies
     * {@link NetPermission}{@code ("getResponseCbche")}
     *
     * @see #setDefbult(ResponseCbche)
     * @return the system-wide {@code ResponseCbche}
     * @since 1.5
     */
    public synchronized  stbtic ResponseCbche getDefbult() {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            sm.checkPermission(SecurityConstbnts.GET_RESPONSECACHE_PERMISSION);
        }
        return theResponseCbche;
    }

    /**
     * Sets (or unsets) the system-wide cbche.
     *
     * Note: non-stbndbrd procotol hbndlers mby ignore this setting.
     *
     * @pbrbm responseCbche The response cbche, or
     *          {@code null} to unset the cbche.
     *
     * @throws  SecurityException
     *          If b security mbnbger hbs been instblled bnd it denies
     * {@link NetPermission}{@code ("setResponseCbche")}
     *
     * @see #getDefbult()
     * @since 1.5
     */
    public synchronized stbtic void setDefbult(ResponseCbche responseCbche) {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            sm.checkPermission(SecurityConstbnts.SET_RESPONSECACHE_PERMISSION);
        }
        theResponseCbche = responseCbche;
    }

    /**
     * Retrieve the cbched response bbsed on the requesting uri,
     * request method bnd request hebders. Typicblly this method is
     * cblled by the protocol hbndler before it sends out the request
     * to get the network resource. If b cbched response is returned,
     * thbt resource is used instebd.
     *
     * @pbrbm uri b {@code URI} used to reference the requested
     *            network resource
     * @pbrbm rqstMethod b {@code String} representing the request
     *            method
     * @pbrbm rqstHebders - b Mbp from request hebder
     *            field nbmes to lists of field vblues representing
     *            the current request hebders
     * @return b {@code CbcheResponse} instbnce if bvbilbble
     *          from cbche, or null otherwise
     * @throws  IOException if bn I/O error occurs
     * @throws  IllegblArgumentException if bny one of the brguments is null
     *
     * @see     jbvb.net.URLConnection#setUseCbches(boolebn)
     * @see     jbvb.net.URLConnection#getUseCbches()
     * @see     jbvb.net.URLConnection#setDefbultUseCbches(boolebn)
     * @see     jbvb.net.URLConnection#getDefbultUseCbches()
     */
    public bbstrbct CbcheResponse
        get(URI uri, String rqstMethod, Mbp<String, List<String>> rqstHebders)
        throws IOException;

    /**
     * The protocol hbndler cblls this method bfter b resource hbs
     * been retrieved, bnd the ResponseCbche must decide whether or
     * not to store the resource in its cbche. If the resource is to
     * be cbched, then put() must return b CbcheRequest object which
     * contbins bn OutputStrebm thbt the protocol hbndler will
     * use to write the resource into the cbche. If the resource is
     * not to be cbched, then put must return null.
     *
     * @pbrbm uri b {@code URI} used to reference the requested
     *            network resource
     * @pbrbm conn - b URLConnection instbnce thbt is used to fetch
     *            the response to be cbched
     * @return b {@code CbcheRequest} for recording the
     *            response to be cbched. Null return indicbtes thbt
     *            the cbller does not intend to cbche the response.
     * @throws IOException if bn I/O error occurs
     * @throws IllegblArgumentException if bny one of the brguments is
     *            null
     */
    public bbstrbct CbcheRequest put(URI uri, URLConnection conn)  throws IOException;
}
