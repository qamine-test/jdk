/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.util.Iterbtor;
import jbvb.util.ServiceLobder;

/**
 * <code>RMIClbssLobder</code> comprises stbtic methods to support
 * dynbmic clbss lobding with RMI.  Included bre methods for lobding
 * clbsses from b network locbtion (one or more URLs) bnd obtbining
 * the locbtion from which bn existing clbss should be lobded by
 * remote pbrties.  These methods bre used by the RMI runtime when
 * mbrshblling bnd unmbrshblling clbsses contbined in the brguments
 * bnd return vblues of remote method cblls, bnd they blso mby be
 * invoked directly by bpplicbtions in order to mimic RMI's dynbmic
 * clbss lobding behbvior.
 *
 * <p>The implementbtion of the following stbtic methods
 *
 * <ul>
 *
 * <li>{@link #lobdClbss(URL,String)}
 * <li>{@link #lobdClbss(String,String)}
 * <li>{@link #lobdClbss(String,String,ClbssLobder)}
 * <li>{@link #lobdProxyClbss(String,String[],ClbssLobder)}
 * <li>{@link #getClbssLobder(String)}
 * <li>{@link #getClbssAnnotbtion(Clbss)}
 *
 * </ul>
 *
 * is provided by bn instbnce of {@link RMIClbssLobderSpi}, the
 * service provider interfbce for those methods.  When one of the
 * methods is invoked, its behbvior is to delegbte to b corresponding
 * method on the service provider instbnce.  The detbils of how ebch
 * method delegbtes to the provider instbnce is described in the
 * documentbtion for ebch pbrticulbr method.
 *
 * <p>The service provider instbnce is chosen bs follows:
 *
 * <ul>
 *
 * <li>If the system property
 * <code>jbvb.rmi.server.RMIClbssLobderSpi</code> is defined, then if
 * its vblue equbls the string <code>"defbult"</code>, the provider
 * instbnce will be the vblue returned by bn invocbtion of the {@link
 * #getDefbultProviderInstbnce()} method, bnd for bny other vblue, if
 * b clbss nbmed with the vblue of the property cbn be lobded by the
 * system clbss lobder (see {@link ClbssLobder#getSystemClbssLobder})
 * bnd thbt clbss is bssignbble to {@link RMIClbssLobderSpi} bnd hbs b
 * public no-brgument constructor, then thbt constructor will be
 * invoked to crebte the provider instbnce.  If the property is
 * defined but bny other of those conditions bre not true, then bn
 * unspecified <code>Error</code> will be thrown to code thbt bttempts
 * to use <code>RMIClbssLobder</code>, indicbting the fbilure to
 * obtbin b provider instbnce.
 *
 * <li>If b resource nbmed
 * <code>META-INF/services/jbvb.rmi.server.RMIClbssLobderSpi</code> is
 * visible to the system clbss lobder, then the contents of thbt
 * resource bre interpreted bs b provider-configurbtion file, bnd the
 * first clbss nbme specified in thbt file is used bs the provider
 * clbss nbme.  If b clbss with thbt nbme cbn be lobded by the system
 * clbss lobder bnd thbt clbss is bssignbble to {@link
 * RMIClbssLobderSpi} bnd hbs b public no-brgument constructor, then
 * thbt constructor will be invoked to crebte the provider instbnce.
 * If the resource is found but b provider cbnnot be instbntibted bs
 * described, then bn unspecified <code>Error</code> will be thrown to
 * code thbt bttempts to use <code>RMIClbssLobder</code>, indicbting
 * the fbilure to obtbin b provider instbnce.
 *
 * <li>Otherwise, the provider instbnce will be the vblue returned by
 * bn invocbtion of the {@link #getDefbultProviderInstbnce()} method.
 *
 * </ul>
 *
 * @buthor      Ann Wollrbth
 * @buthor      Peter Jones
 * @buthor      Lbird Dornin
 * @see         RMIClbssLobderSpi
 * @since       1.1
 */
public clbss RMIClbssLobder {

    /** "defbult" provider instbnce */
    privbte stbtic finbl RMIClbssLobderSpi defbultProvider =
        newDefbultProviderInstbnce();

    /** provider instbnce */
    privbte stbtic finbl RMIClbssLobderSpi provider =
        AccessController.doPrivileged(
            new PrivilegedAction<RMIClbssLobderSpi>() {
                public RMIClbssLobderSpi run() { return initiblizeProvider(); }
            });

    /*
     * Disbllow bnyone from crebting one of these.
     */
    privbte RMIClbssLobder() {}

    /**
     * Lobds the clbss with the specified <code>nbme</code>.
     *
     * <p>This method delegbtes to {@link #lobdClbss(String,String)},
     * pbssing <code>null</code> bs the first brgument bnd
     * <code>nbme</code> bs the second brgument.
     *
     * @pbrbm   nbme the nbme of the clbss to lobd
     *
     * @return  the <code>Clbss</code> object representing the lobded clbss
     *
     * @throws MblformedURLException if b provider-specific URL used
     * to lobd clbsses is invblid
     *
     * @throws  ClbssNotFoundException if b definition for the clbss
     * could not be found bt the codebbse locbtion
     *
     * @deprecbted replbced by <code>lobdClbss(String,String)</code> method
     * @see #lobdClbss(String,String)
     */
    @Deprecbted
    public stbtic Clbss<?> lobdClbss(String nbme)
        throws MblformedURLException, ClbssNotFoundException
    {
        return lobdClbss((String) null, nbme);
    }

    /**
     * Lobds b clbss from b codebbse URL.
     *
     * If <code>codebbse</code> is <code>null</code>, then this method
     * will behbve the sbme bs {@link #lobdClbss(String,String)} with b
     * <code>null</code> <code>codebbse</code> bnd the given clbss nbme.
     *
     * <p>This method delegbtes to the
     * {@link RMIClbssLobderSpi#lobdClbss(String,String,ClbssLobder)}
     * method of the provider instbnce, pbssing the result of invoking
     * {@link URL#toString} on the given URL (or <code>null</code> if
     * <code>codebbse</code> is null) bs the first brgument,
     * <code>nbme</code> bs the second brgument,
     * bnd <code>null</code> bs the third brgument.
     *
     * @pbrbm   codebbse the URL to lobd the clbss from, or <code>null</code>
     *
     * @pbrbm   nbme the nbme of the clbss to lobd
     *
     * @return  the <code>Clbss</code> object representing the lobded clbss
     *
     * @throws MblformedURLException if <code>codebbse</code> is
     * <code>null</code> bnd b provider-specific URL used
     * to lobd clbsses is invblid
     *
     * @throws  ClbssNotFoundException if b definition for the clbss
     * could not be found bt the specified URL
     */
    public stbtic Clbss<?> lobdClbss(URL codebbse, String nbme)
        throws MblformedURLException, ClbssNotFoundException
    {
        return provider.lobdClbss(
            codebbse != null ? codebbse.toString() : null, nbme, null);
    }

    /**
     * Lobds b clbss from b codebbse URL pbth.
     *
     * <p>This method delegbtes to the
     * {@link RMIClbssLobderSpi#lobdClbss(String,String,ClbssLobder)}
     * method of the provider instbnce, pbssing <code>codebbse</code>
     * bs the first brgument, <code>nbme</code> bs the second brgument,
     * bnd <code>null</code> bs the third brgument.
     *
     * @pbrbm   codebbse the list of URLs (sepbrbted by spbces) to lobd
     * the clbss from, or <code>null</code>
     *
     * @pbrbm   nbme the nbme of the clbss to lobd
     *
     * @return  the <code>Clbss</code> object representing the lobded clbss
     *
     * @throws MblformedURLException if <code>codebbse</code> is
     * non-<code>null</code> bnd contbins bn invblid URL, or if
     * <code>codebbse</code> is <code>null</code> bnd b provider-specific
     * URL used to lobd clbsses is invblid
     *
     * @throws  ClbssNotFoundException if b definition for the clbss
     * could not be found bt the specified locbtion
     *
     * @since   1.2
     */
    public stbtic Clbss<?> lobdClbss(String codebbse, String nbme)
        throws MblformedURLException, ClbssNotFoundException
    {
        return provider.lobdClbss(codebbse, nbme, null);
    }

    /**
     * Lobds b clbss from b codebbse URL pbth, optionblly using the
     * supplied lobder.
     *
     * This method should be used when the cbller would like to mbke
     * bvbilbble to the provider implementbtion bn bdditionbl contextubl
     * clbss lobder to consider, such bs the lobder of b cbller on the
     * stbck.  Typicblly, b provider implementbtion will bttempt to
     * resolve the nbmed clbss using the given <code>defbultLobder</code>,
     * if specified, before bttempting to resolve the clbss from the
     * codebbse URL pbth.
     *
     * <p>This method delegbtes to the
     * {@link RMIClbssLobderSpi#lobdClbss(String,String,ClbssLobder)}
     * method of the provider instbnce, pbssing <code>codebbse</code>
     * bs the first brgument, <code>nbme</code> bs the second brgument,
     * bnd <code>defbultLobder</code> bs the third brgument.
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
     * @throws MblformedURLException if <code>codebbse</code> is
     * non-<code>null</code> bnd contbins bn invblid URL, or if
     * <code>codebbse</code> is <code>null</code> bnd b provider-specific
     * URL used to lobd clbsses is invblid
     *
     * @throws  ClbssNotFoundException if b definition for the clbss
     * could not be found bt the specified locbtion
     *
     * @since   1.4
     */
    public stbtic Clbss<?> lobdClbss(String codebbse, String nbme,
                                     ClbssLobder defbultLobder)
        throws MblformedURLException, ClbssNotFoundException
    {
        return provider.lobdClbss(codebbse, nbme, defbultLobder);
    }

    /**
     * Lobds b dynbmic proxy clbss (see {@link jbvb.lbng.reflect.Proxy})
     * thbt implements b set of interfbces with the given nbmes
     * from b codebbse URL pbth.
     *
     * <p>The interfbces will be resolved similbr to clbsses lobded vib
     * the {@link #lobdClbss(String,String)} method using the given
     * <code>codebbse</code>.
     *
     * <p>This method delegbtes to the
     * {@link RMIClbssLobderSpi#lobdProxyClbss(String,String[],ClbssLobder)}
     * method of the provider instbnce, pbssing <code>codebbse</code>
     * bs the first brgument, <code>interfbces</code> bs the second brgument,
     * bnd <code>defbultLobder</code> bs the third brgument.
     *
     * @pbrbm   codebbse the list of URLs (spbce-sepbrbted) to lobd
     * clbsses from, or <code>null</code>
     *
     * @pbrbm   interfbces the nbmes of the interfbces for the proxy clbss
     * to implement
     *
     * @pbrbm   defbultLobder bdditionbl contextubl clbss lobder
     * to use, or <code>null</code>
     *
     * @return  b dynbmic proxy clbss thbt implements the nbmed interfbces
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
     *
     * @since   1.4
     */
    public stbtic Clbss<?> lobdProxyClbss(String codebbse, String[] interfbces,
                                          ClbssLobder defbultLobder)
        throws ClbssNotFoundException, MblformedURLException
    {
        return provider.lobdProxyClbss(codebbse, interfbces, defbultLobder);
    }

    /**
     * Returns b clbss lobder thbt lobds clbsses from the given codebbse
     * URL pbth.
     *
     * <p>The clbss lobder returned is the clbss lobder thbt the
     * {@link #lobdClbss(String,String)} method would use to lobd clbsses
     * for the sbme <code>codebbse</code> brgument.
     *
     * <p>This method delegbtes to the
     * {@link RMIClbssLobderSpi#getClbssLobder(String)} method
     * of the provider instbnce, pbssing <code>codebbse</code> bs the brgument.
     *
     * <p>If there is b security mbnger, its <code>checkPermission</code>
     * method will be invoked with b
     * <code>RuntimePermission("getClbssLobder")</code> permission;
     * this could result in b <code>SecurityException</code>.
     * The provider implementbtion of this method mby blso perform further
     * security checks to verify thbt the cblling context hbs permission to
     * connect to bll of the URLs in the codebbse URL pbth.
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
     *
     * @since   1.3
     */
    public stbtic ClbssLobder getClbssLobder(String codebbse)
        throws MblformedURLException, SecurityException
    {
        return provider.getClbssLobder(codebbse);
    }

    /**
     * Returns the bnnotbtion string (representing b locbtion for
     * the clbss definition) thbt RMI will use to bnnotbte the clbss
     * descriptor when mbrshblling objects of the given clbss.
     *
     * <p>This method delegbtes to the
     * {@link RMIClbssLobderSpi#getClbssAnnotbtion(Clbss)} method
     * of the provider instbnce, pbssing <code>cl</code> bs the brgument.
     *
     * @pbrbm   cl the clbss to obtbin the bnnotbtion for
     *
     * @return  b string to be used to bnnotbte the given clbss when
     * it gets mbrshblled, or <code>null</code>
     *
     * @throws  NullPointerException if <code>cl</code> is <code>null</code>
     *
     * @since   1.2
     */
    /*
     * REMIND: Should we sby thbt the returned clbss bnnotbtion will or
     * should be b (spbce-sepbrbted) list of URLs?
     */
    public stbtic String getClbssAnnotbtion(Clbss<?> cl) {
        return provider.getClbssAnnotbtion(cl);
    }

    /**
     * Returns the cbnonicbl instbnce of the defbult provider
     * for the service provider interfbce {@link RMIClbssLobderSpi}.
     * If the system property <code>jbvb.rmi.server.RMIClbssLobderSpi</code>
     * is not defined, then the <code>RMIClbssLobder</code> stbtic
     * methods
     *
     * <ul>
     *
     * <li>{@link #lobdClbss(URL,String)}
     * <li>{@link #lobdClbss(String,String)}
     * <li>{@link #lobdClbss(String,String,ClbssLobder)}
     * <li>{@link #lobdProxyClbss(String,String[],ClbssLobder)}
     * <li>{@link #getClbssLobder(String)}
     * <li>{@link #getClbssAnnotbtion(Clbss)}
     *
     * </ul>
     *
     * will use the cbnonicbl instbnce of the defbult provider
     * bs the service provider instbnce.
     *
     * <p>If there is b security mbnbger, its
     * <code>checkPermission</code> method will be invoked with b
     * <code>RuntimePermission("setFbctory")</code> permission; this
     * could result in b <code>SecurityException</code>.
     *
     * <p>The defbult service provider instbnce implements
     * {@link RMIClbssLobderSpi} bs follows:
     *
     * <blockquote>
     *
     * <p>The <b>{@link RMIClbssLobderSpi#getClbssAnnotbtion(Clbss)
     * getClbssAnnotbtion}</b> method returns b <code>String</code>
     * representing the codebbse URL pbth thbt b remote pbrty should
     * use to downlobd the definition for the specified clbss.  The
     * formbt of the returned string is b pbth of URLs sepbrbted by
     * spbces.
     *
     * The codebbse string returned depends on the defining clbss
     * lobder of the specified clbss:
     *
     * <ul>
     *
     * <li><p>If the clbss lobder is the system clbss lobder (see
     * {@link ClbssLobder#getSystemClbssLobder}), b pbrent of the
     * system clbss lobder such bs the lobder used for instblled
     * extensions, or the bootstrbp clbss lobder (which mby be
     * represented by <code>null</code>), then the vblue of the
     * <code>jbvb.rmi.server.codebbse</code> property (or possibly bn
     * ebrlier cbched vblue) is returned, or
     * <code>null</code> is returned if thbt property is not set.
     *
     * <li><p>Otherwise, if the clbss lobder is bn instbnce of
     * <code>URLClbssLobder</code>, then the returned string is b
     * spbce-sepbrbted list of the externbl forms of the URLs returned
     * by invoking the <code>getURLs</code> methods of the lobder.  If
     * the <code>URLClbssLobder</code> wbs crebted by this provider to
     * service bn invocbtion of its <code>lobdClbss</code> or
     * <code>lobdProxyClbss</code> methods, then no permissions bre
     * required to get the bssocibted codebbse string.  If it is bn
     * brbitrbry other <code>URLClbssLobder</code> instbnce, then if
     * there is b security mbnbger, its <code>checkPermission</code>
     * method will be invoked once for ebch URL returned by the
     * <code>getURLs</code> method, with the permission returned by
     * invoking <code>openConnection().getPermission()</code> on ebch
     * URL; if bny of those invocbtions throws b
     * <code>SecurityException</code> or bn <code>IOException</code>,
     * then the vblue of the <code>jbvb.rmi.server.codebbse</code>
     * property (or possibly bn ebrlier cbched vblue) is returned, or
     * <code>null</code> is returned if thbt property is not set.
     *
     * <li><p>Finblly, if the clbss lobder is not bn instbnce of
     * <code>URLClbssLobder</code>, then the vblue of the
     * <code>jbvb.rmi.server.codebbse</code> property (or possibly bn
     * ebrlier cbched vblue) is returned, or
     * <code>null</code> is returned if thbt property is not set.
     *
     * </ul>
     *
     * <p>For the implementbtions of the methods described below,
     * which bll tbke b <code>String</code> pbrbmeter nbmed
     * <code>codebbse</code> thbt is b spbce-sepbrbted list of URLs,
     * ebch invocbtion hbs bn bssocibted <i>codebbse lobder</i> thbt
     * is identified using the <code>codebbse</code> brgument in
     * conjunction with the current threbd's context clbss lobder (see
     * {@link Threbd#getContextClbssLobder()}).  When there is b
     * security mbnbger, this provider mbintbins bn internbl tbble of
     * clbss lobder instbnces (which bre bt lebst instbnces of {@link
     * jbvb.net.URLClbssLobder}) keyed by the pbir of their pbrent
     * clbss lobder bnd their codebbse URL pbth (bn ordered list of
     * URLs).  If the <code>codebbse</code> brgument is <code>null</code>,
     * the codebbse URL pbth is the vblue of the system property
     * <code>jbvb.rmi.server.codebbse</code> or possibly bn
     * ebrlier cbched vblue.  For b given codebbse URL pbth pbssed bs the
     * <code>codebbse</code> brgument to bn invocbtion of one of the
     * below methods in b given context, the codebbse lobder is the
     * lobder in the tbble with the specified codebbse URL pbth bnd
     * the current threbd's context clbss lobder bs its pbrent.  If no
     * such lobder exists, then one is crebted bnd bdded to the tbble.
     * The tbble does not mbintbin strong references to its contbined
     * lobders, in order to bllow them bnd their defined clbsses to be
     * gbrbbge collected when not otherwise rebchbble.  In order to
     * prevent brbitrbry untrusted code from being implicitly lobded
     * into b virtubl mbchine with no security mbnbger, if there is no
     * security mbnbger set, the codebbse lobder is just the current
     * threbd's context clbss lobder (the supplied codebbse URL pbth
     * is ignored, so remote clbss lobding is disbbled).
     *
     * <p>The <b>{@link RMIClbssLobderSpi#getClbssLobder(String)
     * getClbssLobder}</b> method returns the codebbse lobder for the
     * specified codebbse URL pbth.  If there is b security mbnbger,
     * then if the cblling context does not hbve permission to connect
     * to bll of the URLs in the codebbse URL pbth, b
     * <code>SecurityException</code> will be thrown.
     *
     * <p>The <b>{@link
     * RMIClbssLobderSpi#lobdClbss(String,String,ClbssLobder)
     * lobdClbss}</b> method bttempts to lobd the clbss with the
     * specified nbme bs follows:
     *
     * <blockquote>
     *
     * If the <code>defbultLobder</code> brgument is
     * non-<code>null</code>, it first bttempts to lobd the clbss with the
     * specified <code>nbme</code> using the
     * <code>defbultLobder</code>, such bs by evblubting
     *
     * <pre>
     *     Clbss.forNbme(nbme, fblse, defbultLobder)
     * </pre>
     *
     * If the clbss is successfully lobded from the
     * <code>defbultLobder</code>, thbt clbss is returned.  If bn
     * exception other thbn <code>ClbssNotFoundException</code> is
     * thrown, thbt exception is thrown to the cbller.
     *
     * <p>Next, the <code>lobdClbss</code> method bttempts to lobd the
     * clbss with the specified <code>nbme</code> using the codebbse
     * lobder for the specified codebbse URL pbth.
     * If there is b security mbnbger, then the cblling context
     * must hbve permission to connect to bll of the URLs in the
     * codebbse URL pbth; otherwise, the current threbd's context
     * clbss lobder will be used instebd of the codebbse lobder.
     *
     * </blockquote>
     *
     * <p>The <b>{@link
     * RMIClbssLobderSpi#lobdProxyClbss(String,String[],ClbssLobder)
     * lobdProxyClbss}</b> method bttempts to return b dynbmic proxy
     * clbss with the nbmed interfbce bs follows:
     *
     * <blockquote>
     *
     * <p>If the <code>defbultLobder</code> brgument is
     * non-<code>null</code> bnd bll of the nbmed interfbces cbn be
     * resolved through thbt lobder, then,
     *
     * <ul>
     *
     * <li>if bll of the resolved interfbces bre <code>public</code>,
     * then it first bttempts to obtbin b dynbmic proxy clbss (using
     * {@link
     * jbvb.lbng.reflect.Proxy#getProxyClbss(ClbssLobder,Clbss[])
     * Proxy.getProxyClbss}) for the resolved interfbces defined in
     * the codebbse lobder; if thbt bttempt throws bn
     * <code>IllegblArgumentException</code>, it then bttempts to
     * obtbin b dynbmic proxy clbss for the resolved interfbces
     * defined in the <code>defbultLobder</code>.  If both bttempts
     * throw <code>IllegblArgumentException</code>, then this method
     * throws b <code>ClbssNotFoundException</code>.  If bny other
     * exception is thrown, thbt exception is thrown to the cbller.
     *
     * <li>if bll of the non-<code>public</code> resolved interfbces
     * bre defined in the sbme clbss lobder, then it bttempts to
     * obtbin b dynbmic proxy clbss for the resolved interfbces
     * defined in thbt lobder.
     *
     * <li>otherwise, b <code>LinkbgeError</code> is thrown (becbuse b
     * clbss thbt implements bll of the specified interfbces cbnnot be
     * defined in bny lobder).
     *
     * </ul>
     *
     * <p>Otherwise, if bll of the nbmed interfbces cbn be resolved
     * through the codebbse lobder, then,
     *
     * <ul>
     *
     * <li>if bll of the resolved interfbces bre <code>public</code>,
     * then it bttempts to obtbin b dynbmic proxy clbss for the
     * resolved interfbces in the codebbse lobder.  If the bttempt
     * throws bn <code>IllegblArgumentException</code>, then this
     * method throws b <code>ClbssNotFoundException</code>.
     *
     * <li>if bll of the non-<code>public</code> resolved interfbces
     * bre defined in the sbme clbss lobder, then it bttempts to
     * obtbin b dynbmic proxy clbss for the resolved interfbces
     * defined in thbt lobder.
     *
     * <li>otherwise, b <code>LinkbgeError</code> is thrown (becbuse b
     * clbss thbt implements bll of the specified interfbces cbnnot be
     * defined in bny lobder).
     *
     * </ul>
     *
     * <p>Otherwise, b <code>ClbssNotFoundException</code> is thrown
     * for one of the nbmed interfbces thbt could not be resolved.
     *
     * </blockquote>
     *
     * </blockquote>
     *
     * @return  the cbnonicbl instbnce of the defbult service provider
     *
     * @throws  SecurityException if there is b security mbnbger bnd the
     * invocbtion of its <code>checkPermission</code> method fbils
     *
     * @since   1.4
     */
    public stbtic RMIClbssLobderSpi getDefbultProviderInstbnce() {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            sm.checkPermission(new RuntimePermission("setFbctory"));
        }
        return defbultProvider;
    }

    /**
     * Returns the security context of the given clbss lobder.
     *
     * @pbrbm   lobder b clbss lobder from which to get the security context
     *
     * @return  the security context
     *
     * @deprecbted no replbcement.  As of the Jbvb 2 plbtform v1.2, RMI no
     * longer uses this method to obtbin b clbss lobder's security context.
     * @see jbvb.lbng.SecurityMbnbger#getSecurityContext()
     */
    @Deprecbted
    public stbtic Object getSecurityContext(ClbssLobder lobder)
    {
        return sun.rmi.server.LobderHbndler.getSecurityContext(lobder);
    }

    /**
     * Crebtes bn instbnce of the defbult provider clbss.
     */
    privbte stbtic RMIClbssLobderSpi newDefbultProviderInstbnce() {
        return new RMIClbssLobderSpi() {
            public Clbss<?> lobdClbss(String codebbse, String nbme,
                                      ClbssLobder defbultLobder)
                throws MblformedURLException, ClbssNotFoundException
            {
                return sun.rmi.server.LobderHbndler.lobdClbss(
                    codebbse, nbme, defbultLobder);
            }

            public Clbss<?> lobdProxyClbss(String codebbse,
                                           String[] interfbces,
                                           ClbssLobder defbultLobder)
                throws MblformedURLException, ClbssNotFoundException
            {
                return sun.rmi.server.LobderHbndler.lobdProxyClbss(
                    codebbse, interfbces, defbultLobder);
            }

            public ClbssLobder getClbssLobder(String codebbse)
                throws MblformedURLException
            {
                return sun.rmi.server.LobderHbndler.getClbssLobder(codebbse);
            }

            public String getClbssAnnotbtion(Clbss<?> cl) {
                return sun.rmi.server.LobderHbndler.getClbssAnnotbtion(cl);
            }
        };
    }

    /**
     * Chooses provider instbnce, following bbove documentbtion.
     *
     * This method bssumes thbt it hbs been invoked in b privileged block.
     */
    privbte stbtic RMIClbssLobderSpi initiblizeProvider() {
        /*
         * First check for the system property being set:
         */
        String providerClbssNbme =
            System.getProperty("jbvb.rmi.server.RMIClbssLobderSpi");

        if (providerClbssNbme != null) {
            if (providerClbssNbme.equbls("defbult")) {
                return defbultProvider;
            }

            try {
                Clbss<? extends RMIClbssLobderSpi> providerClbss =
                    Clbss.forNbme(providerClbssNbme, fblse,
                                  ClbssLobder.getSystemClbssLobder())
                    .bsSubclbss(RMIClbssLobderSpi.clbss);
                return providerClbss.newInstbnce();

            } cbtch (ClbssNotFoundException e) {
                throw new NoClbssDefFoundError(e.getMessbge());
            } cbtch (IllegblAccessException e) {
                throw new IllegblAccessError(e.getMessbge());
            } cbtch (InstbntibtionException e) {
                throw new InstbntibtionError(e.getMessbge());
            } cbtch (ClbssCbstException e) {
                Error error = new LinkbgeError(
                    "provider clbss not bssignbble to RMIClbssLobderSpi");
                error.initCbuse(e);
                throw error;
            }
        }

        /*
         * Next look for b provider configurbtion file instblled:
         */
        Iterbtor<RMIClbssLobderSpi> iter =
            ServiceLobder.lobd(RMIClbssLobderSpi.clbss,
                               ClbssLobder.getSystemClbssLobder()).iterbtor();
        if (iter.hbsNext()) {
            try {
                return iter.next();
            } cbtch (ClbssCbstException e) {
                Error error = new LinkbgeError(
                    "provider clbss not bssignbble to RMIClbssLobderSpi");
                error.initCbuse(e);
                throw error;
            }
        }

        /*
         * Finblly, return the cbnonicbl instbnce of the defbult provider.
         */
        return defbultProvider;
    }
}
