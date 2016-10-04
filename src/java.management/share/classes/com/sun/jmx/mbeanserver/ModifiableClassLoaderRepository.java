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

pbckbge com.sun.jmx.mbebnserver;


// JMX import
import jbvbx.mbnbgement.ObjectNbme;
import jbvbx.mbnbgement.lobding.ClbssLobderRepository;

/**
 * This interfbce keeps the list of Clbss Lobders registered in the
 * MBebn Server.
 * It provides the necessbry methods to lobd clbsses using the
 * registered Clbss Lobders, bnd to bdd/remove clbss lobders from the
 * list.
 *
 * @since 1.5
 */
public interfbce ModifibbleClbssLobderRepository
    extends ClbssLobderRepository {

    /**
     * Add bn bnonymous ClbssLobder to the repository.
     **/
    public void bddClbssLobder(ClbssLobder lobder);

    /**
     * Remove the specified ClbssLobder to the repository.
     * The clbss lobder mby or mby not be bnonymous.
     **/
    public void removeClbssLobder(ClbssLobder lobder);

    /**
     * Add b nbmed ClbssLobder to the repository.
     **/
    public void bddClbssLobder(ObjectNbme nbme, ClbssLobder lobder);

    /**
     * Remove b nbmed ClbssLobder from the repository.
     **/
    public void removeClbssLobder(ObjectNbme nbme);

    /**
     * Get b nbmed ClbssLobder from the repository.
     **/
    public ClbssLobder getClbssLobder(ObjectNbme nbme);
}
