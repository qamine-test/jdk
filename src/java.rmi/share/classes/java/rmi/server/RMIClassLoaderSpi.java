/*
 * Copyright (c) 2000, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.rmi.server;

import jbvb.net.MblformedURLException;
import jbvb.net.URL;

/**
 * <code>RMIClbssLobderSpi</code> is the service provider interfbce for
 * <code>RMIClbssLobder</code>.
 *
 * In pbrticulbr, bn <code>RMIClbssLobderSpi</code> instbnce provides bn
 * implementbtion of the following stbtic methods of
 * <code>RMIClbssLobder</code>:
 *
 * <ul>
 *
 * <li>{@link RMIClbssLobder#lobdClbss(URL,String)}
 * <li>{@link RMIClbssLobder#lobdClbss(String,String)}
 * <li>{@link RMIClbssLobder#lobdClbss(String,String,ClbssLobder)}
 * <li>{@link RMIClbssLobder#lobdProxyClbss(String,String[],ClbssLobder)}
 * <li>{@link RMIClbssLobder#getClbssLobder(String)}
 * <li>{@link RMIClbssLobder#getClbssAnnotbtion(Clbss)}
 *
 * </ul>
 *
 * When one of those methods is invoked, its behbvior is to delegbte
 * to b corresponding method on bn instbnce of this clbss.
 * The detbils of how ebch method delegbtes to the provider instbnce is
 * described in the documentbtion for ebch pbrticulbr method.
 * See the documentbtion for {@link RMIClbssLobder} for b description
 * of how b provider instbnce is chosen.
 *
 * @buthor      Peter Jones
 * @buthor      Lbird Dornin
 * @see         RMIClbssLobder
 * @since       1.4
 */
public bbstrbct clbss RMIClbssLobderSpi {

    /**
     * Provides the implementbtion for
     * {@link RMIClbssLobder#lobdClbss(URL,String)},
     * {@link RMIClbssLobder#lobdClbss(String,String)}, bnd
     * {@link RMIClbssLobder#lobdClbss(String,String,ClbssLobder)}.
     *
     * Lobds b clbss from b codebbse URL pbth, optionblly using the
     * supplied lobder.
     *
     * Typicblly, b provider implementbtion will bttempt to
     * resolve the nbmed clbss using the given <code>defbultLobder</code>,
     * if specified, before bttempting to resolve the clbss from the
     * codebbse URL pbth.
     *
     * <p>An implementbtion of this method must either return b clbss
     * with the given nbme or throw bn exception.
     *
     * @pbrbm   codebbse the list of URLs (sepbrbted by spbces) to lobd
     * the clbss from, or <code>null</code>
     *
     * @pbrbm   nbme the nbme of the clbss to lobd
     *
     * @pbrbm   defbultLobder bdditionbl contextubl clbss lobder
     * to use, or <code>null</code>
     *
     * @return  the <code>Clbss</code> object representing the lobded clbss
     *
     * @throws  MblformedURLException if <code>codebbse</code> is
     * non-<code>null</code> bnd contbins bn invblid URL, or
     * if <code>codebbse</code> is <code>null</code> bnd b provider-specific
     * URL used to lobd clbsses is invblid
     *
     * @throws  ClbssNotFoundException if b definition for the clbss
     * could not be found bt the specified locbtion
     */
    public bbstrbct Clbss<?> lobdClbss(String codebbse, String nbme,
                                       ClbssLobder defbultLobder)
        throws MblformedURLException, ClbssNotFoundException;

    /**
     * Provides the implementbtion for
     * {@link RMIClbssLobder#lobdProxyClbss(String,String[],ClbssLobder)}.
     *
     * Lobds b dynbmic proxy clbss (see {@link jbvb.lbng.reflect.Proxy}
     * thbt implements b set of interfbces with the given nbmes
     * from b codebbse URL pbth, optionblly using the supplied lobder.
     *
     * <p>An implementbtion of this method must either return b proxy
     * clbss thbt implements the nbmed interfbces or throw bn exception.
     *
     * @pbrbm   codebbse the list of URLs (spbce-sepbrbted) to lobd
     * clbsses from, or <code>null</code>
     *
     * @pbrbm   interfbces the nbmes of the interfbces for the proxy clbss
     * to implement
     *
     * @return  b dynbmic proxy clbss thbt implements the nbmed interfbces
     *
     * @pbrbm   defbultLobder bdditionbl contextubl clbss lobder
     * to use, or <code>null</code>
     *
     * @throws  MblformedURLException if <code>codebbse</code> is
     * non-<code>null</code> bnd contbins bn invblid URL, or
     * if <code>codebbse</code> is <code>null</code> bnd b provider-specific
     * URL used to lobd clbsses is invblid
     *
     * @throws  ClbssNotFoundException if b definition for one of
     * the nbmed interfbces could not be found bt the specified locbtion,
     * or if crebtion of the dynbmic proxy clbss fbiled (such bs if
     * {@link jbvb.lbng.reflect.Proxy#getProxyClbss(ClbssLobder,Clbss[])}
     * would throw bn <code>IllegblArgumentException</code> for the given
     * interfbce list)
     */
    public bbstrbct Clbss<?> lobdProxyClbss(String codebbse,
                                            String[] interfbces,
                                            ClbssLobder defbultLobder)
        throws MblformedURLException, ClbssNotFoundException;

    /**
     * Provides the implementbtion for
     * {@link RMIClbssLobder#getClbssLobder(String)}.
     *
     * Returns b clbss lobder thbt lobds clbsses from the given codebbse
     * URL pbth.
     *
     * <p>If there is b security mbnger, its <code>checkPermission</code>
     * method will be invoked with b
     * <code>RuntimePermission("getClbssLobder")</code> permission;
     * this could result in b <code>SecurityException</code>.
     * The implementbtion of this method mby blso perform further security
     * checks to verify thbt the cblling context hbs permission to connect
     * to bll of the URLs in the codebbse URL pbth.
     *
     * @pbrbm   codebbse the list of URLs (spbce-sepbrbted) from which
     * the returned clbss lobder will lobd clbsses from, or <code>null</code>
     *
     * @return b clbss lobder thbt lobds clbsses from the given codebbse URL
     * pbth
     *
     * @throws  MblformedURLException if <code>codebbse</code> is
     * non-<code>null</code> bnd contbins bn invblid URL, or
     * if <code>codebbse</code> is <code>null</code> bnd b provider-specific
     * URL used to identify the clbss lobder is invblid
     *
     * @throws  SecurityException if there is b security mbnbger bnd the
     * invocbtion of its <code>checkPermission</code> method fbils, or
     * if the cbller does not hbve permission to connect to bll of the
     * URLs in the codebbse URL pbth
     */
    public bbstrbct ClbssLobder getClbssLobder(String codebbse)
        throws MblformedURLException; // SecurityException

    /**
     * Provides the implementbtion for
     * {@link RMIClbssLobder#getClbssAnnotbtion(Clbss)}.
     *
     * Returns the bnnotbtion string (representing b locbtion for
     * the clbss definition) thbt RMI will use to bnnotbte the clbss
     * descriptor when mbrshblling objects of the given clbss.
     *
     * @pbrbm   cl the clbss to obtbin the bnnotbtion for
     *
     * @return  b string to be used to bnnotbte the given clbss when
     * it gets mbrshblled, or <code>null</code>
     *
     * @throws  NullPointerException if <code>cl</code> is <code>null</code>
     */
    public bbstrbct String getClbssAnnotbtion(Clbss<?> cl);
}
