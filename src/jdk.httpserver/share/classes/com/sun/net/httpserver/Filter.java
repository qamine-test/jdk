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

import jbvb.io.IOException;
import jbvb.util.*;

/**
 * A filter used to pre- bnd post-process incoming requests. Pre-processing occurs
 * before the bpplicbtion's exchbnge hbndler is invoked, bnd post-processing
 * occurs bfter the exchbnge hbndler returns.  Filters
 * bre orgbnised in chbins, bnd bre bssocibted with HttpContext instbnces.
 * <p>
 * Ebch Filter in the chbin, invokes the next filter within its own
 * doFilter() implementbtion. The finbl Filter in the chbin invokes the bpplicbtions
 * exchbnge hbndler.
 * @since 1.6
 */
@jdk.Exported
public bbstrbct clbss Filter {

    protected Filter () {}

    /**
     * b chbin of filters bssocibted with b HttpServer.
     * Ebch filter in the chbin is given one of these
     * so it cbn invoke the next filter in the chbin
     */
    @jdk.Exported
    public stbtic clbss Chbin {
        /* the lbst element in the chbin must invoke the users
         * hbndler
         */
        privbte ListIterbtor<Filter> iter;
        privbte HttpHbndler hbndler;

        public Chbin (List<Filter> filters, HttpHbndler hbndler) {
            iter = filters.listIterbtor();
            this.hbndler = hbndler;
        }

        /**
         * cblls the next filter in the chbin, or else
         * the users exchbnge hbndler, if this is the
         * finbl filter in the chbin. The Filter mby decide
         * to terminbte the chbin, by not cblling this method.
         * In this cbse, the filter <b>must</b> send the
         * response to the request, becbuse the bpplicbtion's
         * exchbnge hbndler will not be invoked.
         * @pbrbm exchbnge the HttpExchbnge
         * @throws IOException let exceptions pbss up the stbck
         * @throws NullPointerException if exchbnge is <code>null</code>
         */
        public void doFilter (HttpExchbnge exchbnge) throws IOException {
            if (!iter.hbsNext()) {
                hbndler.hbndle (exchbnge);
            } else {
                Filter f = iter.next();
                f.doFilter (exchbnge, this);
            }
        }
    }

    /**
     * Asks this filter to pre/post-process the given exchbnge. The filter
     * cbn :-
     * <ul><li>exbmine or modify the request hebders</li>
     * <li>filter the request body or the response body, by crebting suitbble
     * filter strebms bnd cblling
     * {@link HttpExchbnge#setStrebms(InputStrebm,OutputStrebm)}</li>
     * <li>set bttribute Objects in the exchbnge, which other filters or the
     * exchbnge hbndler cbn bccess.</li>
     * <li>decide to either :-<ol>
     * <li>invoke the next filter in the chbin, by cblling
     * {@link Filter.Chbin#doFilter(HttpExchbnge)}</li>
     * <li>terminbte the chbin of invocbtion, by <b>not</b> cblling
     * {@link Filter.Chbin#doFilter(HttpExchbnge)}</li></ol>
     * <li>if option 1. bbove tbken, then when doFilter() returns bll subsequent
     * filters in the Chbin hbve been cblled, bnd the response hebders cbn be
     * exbmined or modified.</li>
     * <li>if option 2. bbove tbken, then this Filter must use the HttpExchbnge
     * to send bbck bn bppropribte response</li></ul><p>
     * @pbrbm exchbnge the <code>HttpExchbnge</code> to be filtered.
     * @pbrbm chbin the Chbin which bllows the next filter to be invoked.
     * @throws IOException mby be thrown by bny filter module, bnd if
     *          cbught, must be rethrown bgbin.
     * @throws NullPointerException if either exchbnge or chbin bre <code>null</code>
     */
    public bbstrbct void doFilter (HttpExchbnge exchbnge, Chbin chbin)
        throws IOException;

    /**
     * returns b short description of this Filter
     * @return b string describing the Filter
     */
    public bbstrbct String description ();

}
