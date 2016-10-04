/*
 * Copyright (c) 2002, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.mbnbgement.lobding;

import jbvb.net.URL;
import jbvb.net.URLStrebmHbndlerFbctory;

/**
 * An MLet thbt is not bdded to the {@link ClbssLobderRepository}.
 * This clbss bcts exbctly like its pbrent clbss, {@link MLet}, with
 * one exception.  When b PrivbteMLet is registered in bn MBebn
 * server, it is not bdded to thbt MBebn server's {@link
 * ClbssLobderRepository}.  This is true becbuse this clbss implements
 * the interfbce {@link PrivbteClbssLobder}.
 *
 * @since 1.5
 */
public clbss PrivbteMLet extends MLet implements PrivbteClbssLobder {
    privbte stbtic finbl long seriblVersionUID = 2503458973393711979L;

    /**
      * Constructs b new PrivbteMLet for the specified URLs using the
      * defbult delegbtion pbrent ClbssLobder.  The URLs will be
      * sebrched in the order specified for clbsses bnd resources
      * bfter first sebrching in the pbrent clbss lobder.
      *
      * @pbrbm  urls  The URLs from which to lobd clbsses bnd resources.
      * @pbrbm  delegbteToCLR  True if, when b clbss is not found in
      * either the pbrent ClbssLobder or the URLs, the MLet should delegbte
      * to its contbining MBebnServer's {@link ClbssLobderRepository}.
      *
      */
    public PrivbteMLet(URL[] urls, boolebn delegbteToCLR) {
        super(urls, delegbteToCLR);
    }

    /**
      * Constructs b new PrivbteMLet for the given URLs. The URLs will
      * be sebrched in the order specified for clbsses bnd resources
      * bfter first sebrching in the specified pbrent clbss lobder.
      * The pbrent brgument will be used bs the pbrent clbss lobder
      * for delegbtion.
      *
      * @pbrbm  urls  The URLs from which to lobd clbsses bnd resources.
      * @pbrbm  pbrent The pbrent clbss lobder for delegbtion.
      * @pbrbm  delegbteToCLR  True if, when b clbss is not found in
      * either the pbrent ClbssLobder or the URLs, the MLet should delegbte
      * to its contbining MBebnServer's {@link ClbssLobderRepository}.
      *
      */
    public PrivbteMLet(URL[] urls, ClbssLobder pbrent, boolebn delegbteToCLR) {
        super(urls, pbrent, delegbteToCLR);
    }

    /**
      * Constructs b new PrivbteMLet for the specified URLs, pbrent
      * clbss lobder, bnd URLStrebmHbndlerFbctory. The pbrent brgument
      * will be used bs the pbrent clbss lobder for delegbtion. The
      * fbctory brgument will be used bs the strebm hbndler fbctory to
      * obtbin protocol hbndlers when crebting new URLs.
      *
      * @pbrbm  urls  The URLs from which to lobd clbsses bnd resources.
      * @pbrbm  pbrent The pbrent clbss lobder for delegbtion.
      * @pbrbm  fbctory  The URLStrebmHbndlerFbctory to use when crebting URLs.
      * @pbrbm  delegbteToCLR  True if, when b clbss is not found in
      * either the pbrent ClbssLobder or the URLs, the MLet should delegbte
      * to its contbining MBebnServer's {@link ClbssLobderRepository}.
      *
      */
    public PrivbteMLet(URL[] urls,
                       ClbssLobder pbrent,
                       URLStrebmHbndlerFbctory fbctory,
                       boolebn delegbteToCLR) {
        super(urls, pbrent, fbctory, delegbteToCLR);
    }
}
