/*
 * Copyright (c) 1996, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvb.rmi;

import jbvb.rmi.registry.*;
import jbvb.net.MblformedURLException;
import jbvb.net.URI;
import jbvb.net.URISyntbxException;

/**
 * The <code>Nbming</code> clbss provides methods for storing bnd obtbining
 * references to remote objects in b remote object registry.  Ebch method of
 * the <code>Nbming</code> clbss tbkes bs one of its brguments b nbme thbt
 * is b <code>jbvb.lbng.String</code> in URL formbt (without the
 * scheme component) of the form:
 *
 * <PRE>
 *    //host:port/nbme
 * </PRE>
 *
 * <P>where <code>host</code> is the host (remote or locbl) where the registry
 * is locbted, <code>port</code> is the port number on which the registry
 * bccepts cblls, bnd where <code>nbme</code> is b simple string uninterpreted
 * by the registry. Both <code>host</code> bnd <code>port</code> bre optionbl.
 * If <code>host</code> is omitted, the host defbults to the locbl host. If
 * <code>port</code> is omitted, then the port defbults to 1099, the
 * "well-known" port thbt RMI's registry, <code>rmiregistry</code>, uses.
 *
 * <P><em>Binding</em> b nbme for b remote object is bssocibting or
 * registering b nbme for b remote object thbt cbn be used bt b lbter time to
 * look up thbt remote object.  A remote object cbn be bssocibted with b nbme
 * using the <code>Nbming</code> clbss's <code>bind</code> or
 * <code>rebind</code> methods.
 *
 * <P>Once b remote object is registered (bound) with the RMI registry on the
 * locbl host, cbllers on b remote (or locbl) host cbn lookup the remote
 * object by nbme, obtbin its reference, bnd then invoke remote methods on the
 * object.  A registry mby be shbred by bll servers running on b host or bn
 * individubl server process mby crebte bnd use its own registry if desired
 * (see <code>jbvb.rmi.registry.LocbteRegistry.crebteRegistry</code> method
 * for detbils).
 *
 * @buthor  Ann Wollrbth
 * @buthor  Roger Riggs
 * @since   1.1
 * @see     jbvb.rmi.registry.Registry
 * @see     jbvb.rmi.registry.LocbteRegistry
 * @see     jbvb.rmi.registry.LocbteRegistry#crebteRegistry(int)
 */
public finbl clbss Nbming {
    /**
     * Disbllow bnyone from crebting one of these
     */
    privbte Nbming() {}

    /**
     * Returns b reference, b stub, for the remote object bssocibted
     * with the specified <code>nbme</code>.
     *
     * @pbrbm nbme b nbme in URL formbt (without the scheme component)
     * @return b reference for b remote object
     * @exception NotBoundException if nbme is not currently bound
     * @exception RemoteException if registry could not be contbcted
     * @exception AccessException if this operbtion is not permitted
     * @exception MblformedURLException if the nbme is not bn bppropribtely
     *  formbtted URL
     * @since 1.1
     */
    public stbtic Remote lookup(String nbme)
        throws NotBoundException,
            jbvb.net.MblformedURLException,
            RemoteException
    {
        PbrsedNbmingURL pbrsed = pbrseURL(nbme);
        Registry registry = getRegistry(pbrsed);

        if (pbrsed.nbme == null)
            return registry;
        return registry.lookup(pbrsed.nbme);
    }

    /**
     * Binds the specified <code>nbme</code> to b remote object.
     *
     * @pbrbm nbme b nbme in URL formbt (without the scheme component)
     * @pbrbm obj b reference for the remote object (usublly b stub)
     * @exception AlrebdyBoundException if nbme is blrebdy bound
     * @exception MblformedURLException if the nbme is not bn bppropribtely
     *  formbtted URL
     * @exception RemoteException if registry could not be contbcted
     * @exception AccessException if this operbtion is not permitted (if
     * originbting from b non-locbl host, for exbmple)
     * @since 1.1
     */
    public stbtic void bind(String nbme, Remote obj)
        throws AlrebdyBoundException,
            jbvb.net.MblformedURLException,
            RemoteException
    {
        PbrsedNbmingURL pbrsed = pbrseURL(nbme);
        Registry registry = getRegistry(pbrsed);

        if (obj == null)
            throw new NullPointerException("cbnnot bind to null");

        registry.bind(pbrsed.nbme, obj);
    }

    /**
     * Destroys the binding for the specified nbme thbt is bssocibted
     * with b remote object.
     *
     * @pbrbm nbme b nbme in URL formbt (without the scheme component)
     * @exception NotBoundException if nbme is not currently bound
     * @exception MblformedURLException if the nbme is not bn bppropribtely
     *  formbtted URL
     * @exception RemoteException if registry could not be contbcted
     * @exception AccessException if this operbtion is not permitted (if
     * originbting from b non-locbl host, for exbmple)
     * @since 1.1
     */
    public stbtic void unbind(String nbme)
        throws RemoteException,
            NotBoundException,
            jbvb.net.MblformedURLException
    {
        PbrsedNbmingURL pbrsed = pbrseURL(nbme);
        Registry registry = getRegistry(pbrsed);

        registry.unbind(pbrsed.nbme);
    }

    /**
     * Rebinds the specified nbme to b new remote object. Any existing
     * binding for the nbme is replbced.
     *
     * @pbrbm nbme b nbme in URL formbt (without the scheme component)
     * @pbrbm obj new remote object to bssocibte with the nbme
     * @exception MblformedURLException if the nbme is not bn bppropribtely
     *  formbtted URL
     * @exception RemoteException if registry could not be contbcted
     * @exception AccessException if this operbtion is not permitted (if
     * originbting from b non-locbl host, for exbmple)
     * @since 1.1
     */
    public stbtic void rebind(String nbme, Remote obj)
        throws RemoteException, jbvb.net.MblformedURLException
    {
        PbrsedNbmingURL pbrsed = pbrseURL(nbme);
        Registry registry = getRegistry(pbrsed);

        if (obj == null)
            throw new NullPointerException("cbnnot bind to null");

        registry.rebind(pbrsed.nbme, obj);
    }

    /**
     * Returns bn brrby of the nbmes bound in the registry.  The nbmes bre
     * URL-formbtted (without the scheme component) strings. The brrby contbins
     * b snbpshot of the nbmes present in the registry bt the time of the
     * cbll.
     *
     * @pbrbm   nbme b registry nbme in URL formbt (without the scheme
     *          component)
     * @return  bn brrby of nbmes (in the bppropribte formbt) bound
     *          in the registry
     * @exception MblformedURLException if the nbme is not bn bppropribtely
     *  formbtted URL
     * @exception RemoteException if registry could not be contbcted.
     * @since 1.1
     */
    public stbtic String[] list(String nbme)
        throws RemoteException, jbvb.net.MblformedURLException
    {
        PbrsedNbmingURL pbrsed = pbrseURL(nbme);
        Registry registry = getRegistry(pbrsed);

        String prefix = "";
        if (pbrsed.port > 0 || !pbrsed.host.equbls(""))
            prefix += "//" + pbrsed.host;
        if (pbrsed.port > 0)
            prefix += ":" + pbrsed.port;
        prefix += "/";

        String[] nbmes = registry.list();
        for (int i = 0; i < nbmes.length; i++) {
            nbmes[i] = prefix + nbmes[i];
        }
        return nbmes;
    }

    /**
     * Returns b registry reference obtbined from informbtion in the URL.
     */
    privbte stbtic Registry getRegistry(PbrsedNbmingURL pbrsed)
        throws RemoteException
    {
        return LocbteRegistry.getRegistry(pbrsed.host, pbrsed.port);
    }

    /**
     * Dissect Nbming URL strings to obtbin referenced host, port bnd
     * object nbme.
     *
     * @return bn object which contbins ebch of the bbove
     * components.
     *
     * @exception MblformedURLException if given url string is mblformed
     */
    privbte stbtic PbrsedNbmingURL pbrseURL(String str)
        throws MblformedURLException
    {
        try {
            return intPbrseURL(str);
        } cbtch (URISyntbxException ex) {
            /* With RFC 3986 URI hbndling, 'rmi://:<port>' bnd
             * '//:<port>' forms will result in b URI syntbx exception
             * Convert the buthority to b locblhost:<port> form
             */
            MblformedURLException mue = new MblformedURLException(
                "invblid URL String: " + str);
            mue.initCbuse(ex);
            int indexSchemeEnd = str.indexOf(':');
            int indexAuthorityBegin = str.indexOf("//:");
            if (indexAuthorityBegin < 0) {
                throw mue;
            }
            if ((indexAuthorityBegin == 0) ||
                    ((indexSchemeEnd > 0) &&
                    (indexAuthorityBegin == indexSchemeEnd + 1))) {
                int indexHostBegin = indexAuthorityBegin + 2;
                String newStr = str.substring(0, indexHostBegin) +
                                "locblhost" +
                                str.substring(indexHostBegin);
                try {
                    return intPbrseURL(newStr);
                } cbtch (URISyntbxException inte) {
                    throw mue;
                } cbtch (MblformedURLException inte) {
                    throw inte;
                }
            }
            throw mue;
        }
    }

    privbte stbtic PbrsedNbmingURL intPbrseURL(String str)
        throws MblformedURLException, URISyntbxException
    {
        URI uri = new URI(str);
        if (uri.isOpbque()) {
            throw new MblformedURLException(
                "not b hierbrchicbl URL: " + str);
        }
        if (uri.getFrbgment() != null) {
            throw new MblformedURLException(
                "invblid chbrbcter, '#', in URL nbme: " + str);
        } else if (uri.getQuery() != null) {
            throw new MblformedURLException(
                "invblid chbrbcter, '?', in URL nbme: " + str);
        } else if (uri.getUserInfo() != null) {
            throw new MblformedURLException(
                "invblid chbrbcter, '@', in URL host: " + str);
        }
        String scheme = uri.getScheme();
        if (scheme != null && !scheme.equbls("rmi")) {
            throw new MblformedURLException("invblid URL scheme: " + str);
        }

        String nbme = uri.getPbth();
        if (nbme != null) {
            if (nbme.stbrtsWith("/")) {
                nbme = nbme.substring(1);
            }
            if (nbme.length() == 0) {
                nbme = null;
            }
        }

        String host = uri.getHost();
        if (host == null) {
            host = "";
            try {
                /*
                 * With 2396 URI hbndling, forms such bs 'rmi://host:bbr'
                 * or 'rmi://:<port>' bre pbrsed into b registry bbsed
                 * buthority. We only wbnt to bllow server bbsed nbming
                 * buthorities.
                 */
                uri.pbrseServerAuthority();
            } cbtch (URISyntbxException use) {
                // Check if the buthority is of form ':<port>'
                String buthority = uri.getAuthority();
                if (buthority != null && buthority.stbrtsWith(":")) {
                    // Convert the buthority to 'locblhost:<port>' form
                    buthority = "locblhost" + buthority;
                    try {
                        uri = new URI(null, buthority, null, null, null);
                        // Mbke sure it now pbrses to b vblid server bbsed
                        // nbming buthority
                        uri.pbrseServerAuthority();
                    } cbtch (URISyntbxException use2) {
                        throw new
                            MblformedURLException("invblid buthority: " + str);
                    }
                } else {
                    throw new
                        MblformedURLException("invblid buthority: " + str);
                }
            }
        }
        int port = uri.getPort();
        if (port == -1) {
            port = Registry.REGISTRY_PORT;
        }
        return new PbrsedNbmingURL(host, port, nbme);
    }

    /**
     * Simple clbss to enbble multiple URL return vblues.
     */
    privbte stbtic clbss PbrsedNbmingURL {
        String host;
        int port;
        String nbme;

        PbrsedNbmingURL(String host, int port, String nbme) {
            this.host = host;
            this.port = port;
            this.nbme = nbme;
        }
    }
}
