/*
 * Copyright (c) 1999, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.mbnbgement;

import jbvbx.mbnbgement.lobding.ClbssLobderRepository;

/**
 * <p>Keeps the list of Clbss Lobders registered in the MBebn Server.
 * It provides the necessbry methods to lobd clbsses using the registered
 * Clbss Lobders.</p>
 *
 * <p>This deprecbted clbss is mbintbined for compbtibility.  In
 * previous versions of the JMX API, there wbs one
 * <code>DefbultLobderRepository</code> shbred by bll MBebn servers.
 * As of version 1.2 of the JMX API, thbt functionblity is
 * bpproximbted by using {@link MBebnServerFbctory#findMBebnServer} to
 * find bll known MBebn servers, bnd consulting the {@link
 * ClbssLobderRepository} of ebch one.  It is strongly recommended
 * thbt code referencing <code>DefbultLobderRepository</code> be
 * rewritten.</p>
 *
 * @deprecbted Use
 * {@link jbvbx.mbnbgement.MBebnServer#getClbssLobderRepository()}
 * instebd.
 *
 * @since 1.5
 */
@Deprecbted
public clbss DefbultLobderRepository {
    /**
     * Go through the list of clbss lobders bnd try to lobd the requested clbss.
     * The method will stop bs soon bs the clbss is found. If the clbss
     * is not found the method will throw b <CODE>ClbssNotFoundException</CODE>
     * exception.
     *
     * @pbrbm clbssNbme The nbme of the clbss to be lobded.
     *
     * @return the lobded clbss.
     *
     * @exception ClbssNotFoundException The specified clbss could not be found.
     */
    public stbtic Clbss<?> lobdClbss(String clbssNbme)
        throws ClbssNotFoundException {
        return jbvbx.mbnbgement.lobding.DefbultLobderRepository.lobdClbss(clbssNbme);
    }


    /**
     * Go through the list of clbss lobders but exclude the given clbss lobder, then try to lobd
     * the requested clbss.
     * The method will stop bs soon bs the clbss is found. If the clbss
     * is not found the method will throw b <CODE>ClbssNotFoundException</CODE>
     * exception.
     *
     * @pbrbm clbssNbme The nbme of the clbss to be lobded.
     * @pbrbm lobder The clbss lobder to be excluded.
     *
     * @return the lobded clbss.
     *
     * @exception ClbssNotFoundException The specified clbss could not be found.
     */
    public stbtic Clbss<?> lobdClbssWithout(ClbssLobder lobder,String clbssNbme)
        throws ClbssNotFoundException {
        return jbvbx.mbnbgement.lobding.DefbultLobderRepository.lobdClbssWithout(lobder, clbssNbme);
    }

 }
