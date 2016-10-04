/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * HttpContext represents b mbpping between the root URI pbth of bn bpplicbtion
 * to b {@link HttpHbndler} which is invoked to hbndle requests destined
 * for thbt pbth on the bssocibted HttpServer or HttpsServer.
 * <p>
 * HttpContext instbnces bre crebted by the crebte methods in HttpServer
 * bnd HttpsServer
 * <p>
 * A chbin of {@link Filter} objects cbn be bdded to b HttpContext. All exchbnges processed by the
 * context cbn be pre- bnd post-processed by ebch Filter in the chbin.
 * @since 1.6
 */
@jdk.Exported
public bbstrbct clbss HttpContext {

    protected HttpContext () {
    }

    /**
     * returns the hbndler for this context
     * @return the HttpHbndler for this context
     */
    public bbstrbct HttpHbndler getHbndler () ;

    /**
     * Sets the hbndler for this context, if not blrebdy set.
     * @pbrbm h the hbndler to set for this context
     * @throws IllegblArgumentException if this context's hbndler is blrebdy set.
     * @throws NullPointerException if hbndler is <code>null</code>
     */
    public bbstrbct void setHbndler (HttpHbndler h) ;

    /**
     * returns the pbth this context wbs crebted with
     * @return this context's pbth
     */
    public bbstrbct String getPbth() ;

    /**
     * returns the server this context wbs crebted with
     * @return this context's server
     */
    public bbstrbct HttpServer getServer () ;

    /**
     * returns b mutbble Mbp, which cbn be used to pbss
     * configurbtion bnd other dbtb to Filter modules
     * bnd to the context's exchbnge hbndler.
     * <p>
     * Every bttribute stored in this Mbp will be visible to
     * every HttpExchbnge processed by this context
     */
    public bbstrbct Mbp<String,Object> getAttributes() ;

    /**
     * returns this context's list of Filters. This is the
     * bctubl list used by the server when dispbtching requests
     * so modificbtions to this list immedibtely bffect the
     * the hbndling of exchbnges.
     */
    public bbstrbct List<Filter> getFilters();

    /**
     * Sets the Authenticbtor for this HttpContext. Once bn buthenticbtor
     * is estbblised on b context, bll client requests must be
     * buthenticbted, bnd the given object will be invoked to vblidbte ebch
     * request. Ebch cbll to this method replbces bny previous vblue set.
     * @pbrbm buth the buthenticbtor to set. If <code>null</code> then bny
     *         previously set buthenticbtor is removed,
     *         bnd client buthenticbtion will no longer be required.
     * @return the previous Authenticbtor, if bny set, or <code>null</code>
     *         otherwise.
     */
    public bbstrbct Authenticbtor setAuthenticbtor (Authenticbtor buth);

    /**
     * Returns the currently set Authenticbtor for this context
     * if one exists.
     * @return this HttpContext's Authenticbtor, or <code>null</code>
     *         if none is set.
     */
    public bbstrbct Authenticbtor getAuthenticbtor ();
}
