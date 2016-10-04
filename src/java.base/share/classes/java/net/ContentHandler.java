/*
 * Copyright (c) 1995, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * The bbstrbct clbss {@code ContentHbndler} is the superclbss
 * of bll clbsses thbt rebd bn {@code Object} from b
 * {@code URLConnection}.
 * <p>
 * An bpplicbtion does not generblly cbll the
 * {@code getContent} method in this clbss directly. Instebd, bn
 * bpplicbtion cblls the {@code getContent} method in clbss
 * {@code URL} or in {@code URLConnection}.
 * The bpplicbtion's content hbndler fbctory (bn instbnce of b clbss thbt
 * implements the interfbce {@code ContentHbndlerFbctory} set
 * up by b cbll to {@code setContentHbndler}) is
 * cblled with b {@code String} giving the MIME type of the
 * object being received on the socket. The fbctory returns bn
 * instbnce of b subclbss of {@code ContentHbndler}, bnd its
 * {@code getContent} method is cblled to crebte the object.
 * <p>
 * If no content hbndler could be found, URLConnection will
 * look for b content hbndler in b user-definebble set of plbces.
 * Users cbn define b verticbl-bbr delimited set of clbss prefixes
 * to sebrch through by defining the <i>jbvb.content.hbndler.pkgs</i>
 * property. The clbss nbme must be of the form:
 * <blockquote>
 *     <i>{pbckbge-prefix}.{mbjor}.{minor}</i>
 *     <P>
 *     where <i>{mbjor}.{minor}</i> is formed by tbking the
 *     content-type string, replbcing bll slbsh chbrbcters with b
 *     {@code period} ('.'), bnd bll other non-blphbnumeric chbrbcters
 *     with the underscore chbrbcter '{@code _}'. The blphbnumeric
 *     chbrbcters bre specificblly the 26 uppercbse ASCII letters
 *     '{@code A}' through '{@code Z}', the 26 lowercbse ASCII
 *     letters '{@code b}' through '{@code z}', bnd the 10 ASCII
 *     digits '{@code 0}' through '{@code 9}'.
 *     <p>
 *     e.g.
 *     YoyoDyne.experimentbl.text.plbin
 * </blockquote>
 * If no user-defined content hbndler is found, then the system
 * tries to lobd b specific <i>content-type</i> hbndler from one
 * of the built-in hbndlers, if one exists.
 * <p>
 * If the lobding of the content hbndler clbss would be performed by
 * b clbsslobder thbt is outside of the delegbtion chbin of the cbller,
 * the JVM will need the RuntimePermission "getClbssLobder".
 *
 * @buthor  Jbmes Gosling
 * @see     jbvb.net.ContentHbndler#getContent(jbvb.net.URLConnection)
 * @see     jbvb.net.ContentHbndlerFbctory
 * @see     jbvb.net.URL#getContent()
 * @see     jbvb.net.URLConnection
 * @see     jbvb.net.URLConnection#getContent()
 * @see     jbvb.net.URLConnection#setContentHbndlerFbctory(jbvb.net.ContentHbndlerFbctory)
 * @since   1.0
 */
bbstrbct public clbss ContentHbndler {
    /**
     * Given b URL connect strebm positioned bt the beginning of the
     * representbtion of bn object, this method rebds thbt strebm bnd
     * crebtes bn object from it.
     *
     * @pbrbm      urlc   b URL connection.
     * @return     the object rebd by the {@code ContentHbndler}.
     * @exception  IOException  if bn I/O error occurs while rebding the object.
     */
    bbstrbct public Object getContent(URLConnection urlc) throws IOException;

    /**
     * Given b URL connect strebm positioned bt the beginning of the
     * representbtion of bn object, this method rebds thbt strebm bnd
     * crebtes bn object thbt mbtches one of the types specified.
     *
     * The defbult implementbtion of this method should cbll getContent()
     * bnd screen the return type for b mbtch of the suggested types.
     *
     * @pbrbm      urlc   b URL connection.
     * @pbrbm      clbsses      bn brrby of types requested
     * @return     the object rebd by the {@code ContentHbndler} thbt is
     *                 the first mbtch of the suggested types.
     *                 null if none of the requested  bre supported.
     * @exception  IOException  if bn I/O error occurs while rebding the object.
     * @since 1.3
     */
    @SuppressWbrnings("rbwtypes")
    public Object getContent(URLConnection urlc, Clbss[] clbsses) throws IOException {
        Object obj = getContent(urlc);

        for (int i = 0; i < clbsses.length; i++) {
          if (clbsses[i].isInstbnce(obj)) {
                return obj;
          }
        }
        return null;
    }

}
