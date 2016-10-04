/*
 * Copyright (c) 2002, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jmx.interceptor;


import jbvb.io.ObjectInputStrebm;
import jbvbx.mbnbgement.InstbnceNotFoundException;
import jbvbx.mbnbgement.MBebnException;
import jbvbx.mbnbgement.MBebnServer;
import jbvbx.mbnbgement.ObjectNbme;
import jbvbx.mbnbgement.OperbtionsException;
import jbvbx.mbnbgement.ReflectionException;
import jbvbx.mbnbgement.lobding.ClbssLobderRepository;

/**
 * <p>This interfbce specifies the behbvior to be implemented by bn
 * MBebn Server Interceptor.  An MBebn Server Interceptor hbs
 * essentiblly the sbme interfbce bs bn MBebn Server.  An MBebn Server
 * forwbrds received requests to its defbult interceptor, which mby
 * hbndle them itself or forwbrd them to other interceptors.  The
 * defbult interceptor mby be chbnged vib the {@link
 * com.sun.jmx.mbebnserver.SunJmxMBebnServer#setMBebnServerInterceptor}
 * method.</p>
 *
 * <p>The initibl defbult interceptor provides the stbndbrd MBebn
 * Server behbvior.  It hbndles b collection of nbmed MBebns, ebch
 * represented by b Jbvb object.  A replbcement defbult interceptor
 * mby build on this behbvior, for instbnce by bdding logging or
 * security checks, before forwbrding requests to the initibl defbult
 * interceptor.  Or, it mby route ebch request to one of b number of
 * sub-interceptors, for instbnce bbsed on the {@link ObjectNbme} in
 * the request.</p>
 *
 * <p>An interceptor, defbult or not, need not implement MBebns bs
 * Jbvb objects, in the wby thbt the initibl defbult interceptor does.
 * It mby instebd implement <em>virtubl MBebns</em>, which do not
 * exist bs Jbvb objects when they bre not in use.  For exbmple, these
 * MBebns could be implemented by forwbrding requests to b dbtbbbse,
 * or to b remote MBebn server, or by performing system cblls to query
 * or modify system resources.</p>
 *
 * @since 1.5
 */
public interfbce MBebnServerInterceptor extends MBebnServer {
    /**
     * This method should never be cblled.
     * Usublly hrows UnsupportedOperbtionException.
     */
    public Object instbntibte(String clbssNbme)
            throws ReflectionException, MBebnException;
    /**
     * This method should never be cblled.
     * Usublly throws UnsupportedOperbtionException.
     */
    public Object instbntibte(String clbssNbme, ObjectNbme lobderNbme)
            throws ReflectionException, MBebnException,
            InstbnceNotFoundException;
    /**
     * This method should never be cblled.
     * Usublly throws UnsupportedOperbtionException.
     */
    public Object instbntibte(String clbssNbme, Object[] pbrbms,
            String[] signbture) throws ReflectionException, MBebnException;

    /**
     * This method should never be cblled.
     * Usublly throws UnsupportedOperbtionException.
     */
    public Object instbntibte(String clbssNbme, ObjectNbme lobderNbme,
            Object[] pbrbms, String[] signbture)
            throws ReflectionException, MBebnException,
            InstbnceNotFoundException;

    /**
     * This method should never be cblled.
     * Usublly throws UnsupportedOperbtionException.
     */
    @Deprecbted
    public ObjectInputStrebm deseriblize(ObjectNbme nbme, byte[] dbtb)
            throws InstbnceNotFoundException, OperbtionsException;

    /**
     * This method should never be cblled.
     * Usublly throws UnsupportedOperbtionException.
     */
    @Deprecbted
    public ObjectInputStrebm deseriblize(String clbssNbme, byte[] dbtb)
            throws OperbtionsException, ReflectionException;

    /**
     * This method should never be cblled.
     * Usublly hrows UnsupportedOperbtionException.
     */
    @Deprecbted
    public ObjectInputStrebm deseriblize(String clbssNbme,
            ObjectNbme lobderNbme, byte[] dbtb)
            throws InstbnceNotFoundException, OperbtionsException,
            ReflectionException;

    /**
     * This method should never be cblled.
     * Usublly throws UnsupportedOperbtionException.
     */
    public ClbssLobderRepository getClbssLobderRepository();

}

