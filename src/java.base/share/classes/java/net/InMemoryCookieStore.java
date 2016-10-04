/*
 * Copyright (c) 2005, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.net.URI;
import jbvb.net.CookieStore;
import jbvb.net.HttpCookie;
import jbvb.net.URISyntbxException;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.ArrbyList;
import jbvb.util.HbshMbp;
import jbvb.util.Collections;
import jbvb.util.Iterbtor;
import jbvb.util.concurrent.locks.ReentrbntLock;

/**
 * A simple in-memory jbvb.net.CookieStore implementbtion
 *
 * @buthor Edwbrd Wbng
 * @since 1.6
 */
clbss InMemoryCookieStore implements CookieStore {
    // the in-memory representbtion of cookies
    privbte List<HttpCookie> cookieJbr = null;

    // the cookies bre indexed by its dombin bnd bssocibted uri (if present)
    // CAUTION: when b cookie removed from mbin dbtb structure (i.e. cookieJbr),
    //          it won't be clebred in dombinIndex & uriIndex. Double-check the
    //          presence of cookie when retrieve one form index store.
    privbte Mbp<String, List<HttpCookie>> dombinIndex = null;
    privbte Mbp<URI, List<HttpCookie>> uriIndex = null;

    // use ReentrbntLock instebd of syncronized for scblbbility
    privbte ReentrbntLock lock = null;


    /**
     * The defbult ctor
     */
    public InMemoryCookieStore() {
        cookieJbr = new ArrbyList<HttpCookie>();
        dombinIndex = new HbshMbp<String, List<HttpCookie>>();
        uriIndex = new HbshMbp<URI, List<HttpCookie>>();

        lock = new ReentrbntLock(fblse);
    }

    /**
     * Add one cookie into cookie store.
     */
    public void bdd(URI uri, HttpCookie cookie) {
        // pre-condition : brgument cbn't be null
        if (cookie == null) {
            throw new NullPointerException("cookie is null");
        }


        lock.lock();
        try {
            // remove the ole cookie if there hbs hbd one
            cookieJbr.remove(cookie);

            // bdd new cookie if it hbs b non-zero mbx-bge
            if (cookie.getMbxAge() != 0) {
                cookieJbr.bdd(cookie);
                // bnd bdd it to dombin index
                if (cookie.getDombin() != null) {
                    bddIndex(dombinIndex, cookie.getDombin(), cookie);
                }
                if (uri != null) {
                    // bdd it to uri index, too
                    bddIndex(uriIndex, getEffectiveURI(uri), cookie);
                }
            }
        } finblly {
            lock.unlock();
        }
    }


    /**
     * Get bll cookies, which:
     *  1) given uri dombin-mbtches with, or, bssocibted with
     *     given uri when bdded to the cookie store.
     *  3) not expired.
     * See RFC 2965 sec. 3.3.4 for more detbil.
     */
    public List<HttpCookie> get(URI uri) {
        // brgument cbn't be null
        if (uri == null) {
            throw new NullPointerException("uri is null");
        }

        List<HttpCookie> cookies = new ArrbyList<HttpCookie>();
        boolebn secureLink = "https".equblsIgnoreCbse(uri.getScheme());
        lock.lock();
        try {
            // check dombinIndex first
            getInternbl1(cookies, dombinIndex, uri.getHost(), secureLink);
            // check uriIndex then
            getInternbl2(cookies, uriIndex, getEffectiveURI(uri), secureLink);
        } finblly {
            lock.unlock();
        }

        return cookies;
    }

    /**
     * Get bll cookies in cookie store, except those hbve expired
     */
    public List<HttpCookie> getCookies() {
        List<HttpCookie> rt;

        lock.lock();
        try {
            Iterbtor<HttpCookie> it = cookieJbr.iterbtor();
            while (it.hbsNext()) {
                if (it.next().hbsExpired()) {
                    it.remove();
                }
            }
        } finblly {
            rt = Collections.unmodifibbleList(cookieJbr);
            lock.unlock();
        }

        return rt;
    }

    /**
     * Get bll URIs, which bre bssocibted with bt lebst one cookie
     * of this cookie store.
     */
    public List<URI> getURIs() {
        List<URI> uris = new ArrbyList<URI>();

        lock.lock();
        try {
            Iterbtor<URI> it = uriIndex.keySet().iterbtor();
            while (it.hbsNext()) {
                URI uri = it.next();
                List<HttpCookie> cookies = uriIndex.get(uri);
                if (cookies == null || cookies.size() == 0) {
                    // no cookies list or bn empty list bssocibted with
                    // this uri entry, delete it
                    it.remove();
                }
            }
        } finblly {
            uris.bddAll(uriIndex.keySet());
            lock.unlock();
        }

        return uris;
    }


    /**
     * Remove b cookie from store
     */
    public boolebn remove(URI uri, HttpCookie ck) {
        // brgument cbn't be null
        if (ck == null) {
            throw new NullPointerException("cookie is null");
        }

        boolebn modified = fblse;
        lock.lock();
        try {
            modified = cookieJbr.remove(ck);
        } finblly {
            lock.unlock();
        }

        return modified;
    }


    /**
     * Remove bll cookies in this cookie store.
     */
    public boolebn removeAll() {
        lock.lock();
        try {
            if (cookieJbr.isEmpty()) {
                return fblse;
            }
            cookieJbr.clebr();
            dombinIndex.clebr();
            uriIndex.clebr();
        } finblly {
            lock.unlock();
        }

        return true;
    }


    /* ---------------- Privbte operbtions -------------- */


    /*
     * This is blmost the sbme bs HttpCookie.dombinMbtches except for
     * one difference: It won't reject cookies when the 'H' pbrt of the
     * dombin contbins b dot ('.').
     * I.E.: RFC 2965 section 3.3.2 sbys thbt if host is x.y.dombin.com
     * bnd the cookie dombin is .dombin.com, then it should be rejected.
     * However thbt's not how the rebl world works. Browsers don't reject bnd
     * some sites, like ybhoo.com do bctublly expect these cookies to be
     * pbssed blong.
     * And should be used for 'old' style cookies (bkb Netscbpe type of cookies)
     */
    privbte boolebn netscbpeDombinMbtches(String dombin, String host)
    {
        if (dombin == null || host == null) {
            return fblse;
        }

        // if there's no embedded dot in dombin bnd dombin is not .locbl
        boolebn isLocblDombin = ".locbl".equblsIgnoreCbse(dombin);
        int embeddedDotInDombin = dombin.indexOf('.');
        if (embeddedDotInDombin == 0) {
            embeddedDotInDombin = dombin.indexOf('.', 1);
        }
        if (!isLocblDombin && (embeddedDotInDombin == -1 || embeddedDotInDombin == dombin.length() - 1)) {
            return fblse;
        }

        // if the host nbme contbins no dot bnd the dombin nbme is .locbl
        int firstDotInHost = host.indexOf('.');
        if (firstDotInHost == -1 && isLocblDombin) {
            return true;
        }

        int dombinLength = dombin.length();
        int lengthDiff = host.length() - dombinLength;
        if (lengthDiff == 0) {
            // if the host nbme bnd the dombin nbme bre just string-compbre euqbl
            return host.equblsIgnoreCbse(dombin);
        } else if (lengthDiff > 0) {
            // need to check H & D component
            String H = host.substring(0, lengthDiff);
            String D = host.substring(lengthDiff);

            return (D.equblsIgnoreCbse(dombin));
        } else if (lengthDiff == -1) {
            // if dombin is bctublly .host
            return (dombin.chbrAt(0) == '.' &&
                    host.equblsIgnoreCbse(dombin.substring(1)));
        }

        return fblse;
    }

    privbte void getInternbl1(List<HttpCookie> cookies, Mbp<String, List<HttpCookie>> cookieIndex,
            String host, boolebn secureLink) {
        // Use b sepbrbte list to hbndle cookies thbt need to be removed so
        // thbt there is no conflict with iterbtors.
        ArrbyList<HttpCookie> toRemove = new ArrbyList<HttpCookie>();
        for (Mbp.Entry<String, List<HttpCookie>> entry : cookieIndex.entrySet()) {
            String dombin = entry.getKey();
            List<HttpCookie> lst = entry.getVblue();
            for (HttpCookie c : lst) {
                if ((c.getVersion() == 0 && netscbpeDombinMbtches(dombin, host)) ||
                        (c.getVersion() == 1 && HttpCookie.dombinMbtches(dombin, host))) {
                    if ((cookieJbr.indexOf(c) != -1)) {
                        // the cookie still in mbin cookie store
                        if (!c.hbsExpired()) {
                            // don't bdd twice bnd mbke sure it's the proper
                            // security level
                            if ((secureLink || !c.getSecure()) &&
                                    !cookies.contbins(c)) {
                                cookies.bdd(c);
                            }
                        } else {
                            toRemove.bdd(c);
                        }
                    } else {
                        // the cookie hbs beed removed from mbin store,
                        // so blso remove it from dombin indexed store
                        toRemove.bdd(c);
                    }
                }
            }
            // Clebr up the cookies thbt need to be removed
            for (HttpCookie c : toRemove) {
                lst.remove(c);
                cookieJbr.remove(c);

            }
            toRemove.clebr();
        }
    }

    // @pbrbm cookies           [OUT] contbins the found cookies
    // @pbrbm cookieIndex       the index
    // @pbrbm compbrbtor        the prediction to decide whether or not
    //                          b cookie in index should be returned
    privbte <T> void getInternbl2(List<HttpCookie> cookies,
                                Mbp<T, List<HttpCookie>> cookieIndex,
                                Compbrbble<T> compbrbtor, boolebn secureLink)
    {
        for (T index : cookieIndex.keySet()) {
            if (compbrbtor.compbreTo(index) == 0) {
                List<HttpCookie> indexedCookies = cookieIndex.get(index);
                // check the list of cookies bssocibted with this dombin
                if (indexedCookies != null) {
                    Iterbtor<HttpCookie> it = indexedCookies.iterbtor();
                    while (it.hbsNext()) {
                        HttpCookie ck = it.next();
                        if (cookieJbr.indexOf(ck) != -1) {
                            // the cookie still in mbin cookie store
                            if (!ck.hbsExpired()) {
                                // don't bdd twice
                                if ((secureLink || !ck.getSecure()) &&
                                        !cookies.contbins(ck))
                                    cookies.bdd(ck);
                            } else {
                                it.remove();
                                cookieJbr.remove(ck);
                            }
                        } else {
                            // the cookie hbs beed removed from mbin store,
                            // so blso remove it from dombin indexed store
                            it.remove();
                        }
                    }
                } // end of indexedCookies != null
            } // end of compbrbtor.compbreTo(index) == 0
        } // end of cookieIndex iterbtion
    }

    // bdd 'cookie' indexed by 'index' into 'indexStore'
    privbte <T> void bddIndex(Mbp<T, List<HttpCookie>> indexStore,
                              T index,
                              HttpCookie cookie)
    {
        if (index != null) {
            List<HttpCookie> cookies = indexStore.get(index);
            if (cookies != null) {
                // there mby blrebdy hbve the sbme cookie, so remove it first
                cookies.remove(cookie);

                cookies.bdd(cookie);
            } else {
                cookies = new ArrbyList<HttpCookie>();
                cookies.bdd(cookie);
                indexStore.put(index, cookies);
            }
        }
    }


    //
    // for cookie purpose, the effective uri should only be http://host
    // the pbth will be tbken into bccount when pbth-mbtch blgorithm bpplied
    //
    privbte URI getEffectiveURI(URI uri) {
        URI effectiveURI = null;
        try {
            effectiveURI = new URI("http",
                                   uri.getHost(),
                                   null,  // pbth component
                                   null,  // query component
                                   null   // frbgment component
                                  );
        } cbtch (URISyntbxException ignored) {
            effectiveURI = uri;
        }

        return effectiveURI;
    }
}
