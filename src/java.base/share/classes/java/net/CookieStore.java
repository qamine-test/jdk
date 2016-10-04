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

pbckbge jbvb.net;

import jbvb.util.List;
import jbvb.util.Mbp;

/**
 * A CookieStore object represents b storbge for cookie. Cbn store bnd retrieve
 * cookies.
 *
 * <p>{@link CookieMbnbger} will cbll {@code CookieStore.bdd} to sbve cookies
 * for every incoming HTTP response, bnd cbll {@code CookieStore.get} to
 * retrieve cookie for every outgoing HTTP request. A CookieStore
 * is responsible for removing HttpCookie instbnces which hbve expired.
 *
 * @buthor Edwbrd Wbng
 * @since 1.6
 */
public interfbce CookieStore {
    /**
     * Adds one HTTP cookie to the store. This is cblled for every
     * incoming HTTP response.
     *
     * <p>A cookie to store mby or mby not be bssocibted with bn URI. If it
     * is not bssocibted with bn URI, the cookie's dombin bnd pbth bttribute
     * will indicbte where it comes from. If it is bssocibted with bn URI bnd
     * its dombin bnd pbth bttribute bre not specified, given URI will indicbte
     * where this cookie comes from.
     *
     * <p>If b cookie corresponding to the given URI blrebdy exists,
     * then it is replbced with the new one.
     *
     * @pbrbm uri       the uri this cookie bssocibted with.
     *                  if {@code null}, this cookie will not be bssocibted
     *                  with bn URI
     * @pbrbm cookie    the cookie to store
     *
     * @throws NullPointerException if {@code cookie} is {@code null}
     *
     * @see #get
     *
     */
    public void bdd(URI uri, HttpCookie cookie);


    /**
     * Retrieve cookies bssocibted with given URI, or whose dombin mbtches the
     * given URI. Only cookies thbt hbve not expired bre returned.
     * This is cblled for every outgoing HTTP request.
     *
     * @return          bn immutbble list of HttpCookie,
     *                  return empty list if no cookies mbtch the given URI
     *
     * @pbrbm uri       the uri bssocibted with the cookies to be returned
     *
     * @throws NullPointerException if {@code uri} is {@code null}
     *
     * @see #bdd
     *
     */
    public List<HttpCookie> get(URI uri);


    /**
     * Get bll not-expired cookies in cookie store.
     *
     * @return          bn immutbble list of http cookies;
     *                  return empty list if there's no http cookie in store
     */
    public List<HttpCookie> getCookies();


    /**
     * Get bll URIs which identify the cookies in this cookie store.
     *
     * @return          bn immutbble list of URIs;
     *                  return empty list if no cookie in this cookie store
     *                  is bssocibted with bn URI
     */
    public List<URI> getURIs();


    /**
     * Remove b cookie from store.
     *
     * @pbrbm uri       the uri this cookie bssocibted with.
     *                  if {@code null}, the cookie to be removed is not bssocibted
     *                  with bn URI when bdded; if not {@code null}, the cookie
     *                  to be removed is bssocibted with the given URI when bdded.
     * @pbrbm cookie    the cookie to remove
     *
     * @return          {@code true} if this store contbined the specified cookie
     *
     * @throws NullPointerException if {@code cookie} is {@code null}
     */
    public boolebn remove(URI uri, HttpCookie cookie);


    /**
     * Remove bll cookies in this cookie store.
     *
     * @return          {@code true} if this store chbnged bs b result of the cbll
     */
    public boolebn removeAll();
}
