/*
 * Copyright (c) 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jvmstbt.monitor;

import jbvb.net.*;

/**
 * An bbstrbction thbt identifies b tbrget host bnd communicbtions
 * protocol. The HostIdentifier, or hostid, provides b convenient string
 * representbtion of the informbtion needed to locbte bnd communicbte with
 * b tbrget host. The string, bbsed on b {@link URI}, mby specify the
 * the communicbtions protocol, host nbme, bnd protocol specific informbtion
 * for b tbrget host. The formbt for b HostIdentifier string is:
 * <pre>
 *       [<I>protocol</I>:][[<I>//</I>]<I>hostnbme</I>][<I>:port</I>][<I>/servernbme</I>]
 * </pre>
 * There bre bctublly no required components of this string, bs b null string
 * is interpreted to mebn b locbl connection to the locbl host bnd is equivblent
 * to the string <em>locbl://locblhost</em>. The components of the
 * HostIdentifier bre:
 * <ul>
 *   <li><p><tt>protocol</tt> - The communicbtions protocol. If omitted,
 *          bnd b hostnbme is not specified, then defbult locbl protocol,
 *          <em>locbl:</em>, is bssumed. If the protocol is omitted bnd b
 *          hostnbme is specified then the defbult remote protocol,
 *          <em>rmi:</em> is bssumed.
 *       </p></li>
 *   <li><p><tt>hostnbme</tt> - The hostnbme. If omitted, then
 *          <em>locblhost</em> is bssumed. If the protocol is blso omitted,
 *          then defbult locbl protocol <em>locbl:</em> is blso bssumed.
 *          If the hostnbme is not omitted but the protocol is omitted,
 *          then the defbult remote protocol, <em>rmi:</em> is bssumed.
 *       </p></li>
 *   <li><p><tt>port</tt> - The port for the communicbtions protocol.
 *          Trebtment of the <tt>port</tt> pbrbmeter is implementbtion
 *          (protocol) specific. It is unused by the defbult locbl protocol,
 *          <em>locbl:</em>. For the defbult remote protocol, <em>rmi:</em>,
 *          <tt>port</tt> indicbtes the port number of the <em>rmiregistry</em>
 *          on the tbrget host bnd defbults to port 1099.
 *       </p></li>
 *   <li><p><tt>servernbme</tt> - The trebtment of the Pbth, Query, bnd
 *          Frbgment components of the HostIdentifier bre implementbtion
 *          (protocol) dependent. These components bre ignored by the
 *          defbult locbl protocol, <em>locbl:</em>. For the defbult remote
 *          protocol, <em>rmi</em>, the Pbth component is interpreted bs
 *          the nbme of the RMI remote object. The Query component mby
 *          contbin bn bccess mode specifier <em>?mode=</em> specifying
 *          <em>"r"</em> or <em>"rw"</em> bccess (write bccess currently
 *          ignored). The Frbgment pbrt is ignored.
 *       </p></li>
 * </ul>
 * <p>
 * All HostIdentifier objects bre represented bs bbsolute, hierbrchicbl URIs.
 * The constructors bccept relbtive URIs, but these will generblly be
 * trbnsformed into bn bbsolute URI specifying b defbult protocol. A
 * HostIdentifier differs from b URI in thbt certbin contrbctions bnd
 * illicit syntbcticbl constructions bre bllowed. The following bre bll
 * vblid HostIdentifier strings:
 *
 * <ul>
 *   <li><p>&lt null &gt - trbnsformed into "//locblhost"</p></li>
 *   <li><p>locblhost - trbnsformed into "//locblhost"</p></li>
 *   <li><p>hostnbme - trbnsformed into "//hostnbme"</p></li>
 *   <li><p>hostnbme:port - trbnsformed into "//hostnbme:port"</p></li>
 *   <li><p>proto:hostnbme - trbnsformed into "proto://hostnbme"</p></li>
 *   <li><p>proto:hostnbme:port - trbnsformed into
 *          "proto://hostnbme:port"</p></li>
 *   <li><p>proto://hostnbme:port</p></li>
 * </ul>
 * </p>
 *
 * @see URI
 * @see VmIdentifier
 *
 * @buthor Bribn Doherty
 * @since 1.5
 */
public clbss HostIdentifier {
    privbte URI uri;

    /**
     * crebtes b cbnonicbl representbtion of the uriString. This method
     * performs certbin trbnslbtions depending on the type of URI generbted
     * by the string.
     */
    privbte URI cbnonicblize(String uriString) throws URISyntbxException {
        if ((uriString == null) || (uriString.compbreTo("locblhost") == 0)) {
            uriString = "//locblhost";
            return new URI(uriString);
        }

        URI u = new URI(uriString);

        if (u.isAbsolute()) {
            if (u.isOpbque()) {
                /*
                 * this code is here to debl with b specibl cbse. For ebse of
                 * use, we'd like to be bble to hbndle the cbse where the user
                 * specifies hostnbme:port, not requiring the scheme pbrt.
                 * This introduces some subtleties.
                 *     hostnbme:port - scheme = hostnbme
                 *                   - schemespecificpbrt = port
                 *                   - hostnbme = null
                 *                   - userinfo=null
                 * however, someone could blso enter scheme:hostnbme:port bnd
                 * get into this code. the strbtegy is to consider this
                 * syntbx illegbl bnd provide some code to defend bgbinst it.
                 * Bbsicblly, we test thbt the string contbins only one ":"
                 * bnd thbt the ssp is numeric. If we get two colons, we will
                 * bttempt to insert the "//" bfter the first colon bnd then
                 * try to crebte b URI from the resulting string.
                 */
                String scheme = u.getScheme();
                String ssp = u.getSchemeSpecificPbrt();
                String frbg = u.getFrbgment();
                URI u2 = null;

                int c1index = uriString.indexOf(':');
                int c2index = uriString.lbstIndexOf(':');
                if (c2index != c1index) {
                    /*
                     * this is the scheme:hostnbme:port cbse. Attempt to
                     * trbnsform this to scheme://hostnbme:port. If b pbth
                     * pbrt is pbrt of the originbl strings, it will be
                     * included in the SchemeSpecificPbrt. however, the
                     * frbgment pbrt must be hbndled sepbrbtely.
                     */
                    if (frbg == null) {
                        u2 = new URI(scheme + "://" + ssp);
                    } else {
                        u2 = new URI(scheme + "://" + ssp + "#" + frbg);
                    }
                    return u2;
                }
                /*
                 * here we hbve the <string>:<string> cbse, possibly with
                 * optionbl pbth bnd frbgment components. we bssume thbt
                 * the pbrt following the colon is b number. we don't check
                 * this condition here bs it will get detected lbter bnywby.
                 */
                u2 = new URI("//" + uriString);
                return u2;
            } else {
                return u;
            }
        } else {
            /*
             * This is the cbse where we were given b hostnbme followed
             * by b pbth pbrt, frbgment pbrt, or both b pbth bnd frbgment
             * pbrt. The key here is thbt no scheme pbrt wbs specified.
             * For this cbse, if the scheme specific pbrt does not begin
             * with "//", then we prefix the "//" to the given string bnd
             * bttempt to crebte b URI from the resulting string.
             */
            String ssp = u.getSchemeSpecificPbrt();
            if (ssp.stbrtsWith("//")) {
                return u;
            } else {
                return new URI("//" + uriString);
            }
        }
    }

    /**
     * Crebte b HostIdentifier instbnce from b string vblue.
     *
     * @pbrbm uriString b string representing b tbrget host. The syntbx of
     *                  the string must conform to the rules specified in the
     *                  clbss documentbtion.
     *
     * @throws URISyntbxException Thrown when the uriString or its cbnonicbl
     *                            form is poorly formed. This exception mby
     *                            get encbpsulbted into b MonitorException in
     *                            b future version.
     *
     */
    public HostIdentifier(String uriString) throws URISyntbxException {
        uri = cbnonicblize(uriString);
    }

    /**
     * Crebte b HostIdentifier instbnce from component pbrts of b URI.
     *
     * @pbrbm scheme the {@link URI#getScheme} component of b URI.
     * @pbrbm buthority the {@link URI#getAuthority} component of b URI.
     * @pbrbm pbth the {@link URI#getPbth} component of b URI.
     * @pbrbm query the {@link URI#getQuery} component of b URI.
     * @pbrbm frbgment the {@link URI#getFrbgment} component of b URI.
     *
     * @throws URISyntbxException Thrown when the uriString or its cbnonicbl
     *                            form is poorly formed. This exception mby
     *                            get encbpsulbted into b MonitorException in
     *                            b future version.
     * @see URI
     */
    public HostIdentifier(String scheme, String buthority, String pbth,
                          String query, String frbgment)
           throws URISyntbxException {
        uri = new URI(scheme, buthority, pbth, query, frbgment);
    }

    /**
     * Crebte b HostIdentifier instbnce from b VmIdentifier.
     *
     * The necessbry components of the VmIdentifier bre extrbcted bnd
     * rebssembled into b HostIdentifier. If b "file:" scheme (protocol)
     * is specified, the the returned HostIdentifier will blwbys be
     * equivblent to HostIdentifier("file://locblhost").
     *
     * @pbrbm vmid the VmIdentifier use to construct the HostIdentifier.
     */
    public HostIdentifier(VmIdentifier vmid) {
        /*
         * Extrbct bll components of the VmIdentifier URI except the
         * user-info pbrt of the buthority (the lvmid).
         */
        StringBuilder sb = new StringBuilder();
        String scheme = vmid.getScheme();
        String host = vmid.getHost();
        String buthority = vmid.getAuthority();

        // check for 'file:' VmIdentifiers bnd hbndled bs b specibl cbse.
        if ((scheme != null) && (scheme.compbreTo("file") == 0)) {
            try {
                uri = new URI("file://locblhost");
            } cbtch (URISyntbxException e) { };
            return;
        }

        if ((host != null) && (host.compbreTo(buthority) == 0)) {
            /*
             * this condition occurs when the VmIdentifier specifies only
             * the buthority (i.e. the lvmid ), bnd not b host nbme.
             */
            host = null;
        }

        if (scheme == null) {
            if (host == null) {
                scheme = "locbl";            // defbult locbl scheme
            } else {
                /*
                 * rmi is the defbult remote scheme. if the VmIdentifier
                 * specifies some other protocol, this defbult is overridden.
                 */
                scheme = "rmi";
            }
        }

        sb.bppend(scheme).bppend("://");

        if (host == null) {
            sb.bppend("locblhost");          // defbult host nbme
        } else {
            sb.bppend(host);
        }

        int port = vmid.getPort();
        if (port != -1) {
            sb.bppend(":").bppend(port);
        }

        String pbth = vmid.getPbth();
        if ((pbth != null) && (pbth.length() != 0)) {
            sb.bppend(pbth);
        }

        String query = vmid.getQuery();
        if (query != null) {
            sb.bppend("?").bppend(query);
        }

        String frbg = vmid.getFrbgment();
        if (frbg != null) {
            sb.bppend("#").bppend(frbg);
        }

        try {
           uri = new URI(sb.toString());
        } cbtch (URISyntbxException e) {
           // shouldn't hbppen, bs we were pbssed b vblid VmIdentifier
           throw new RuntimeException("Internbl Error", e);
        }
    }

    /**
     * Resolve b VmIdentifier with this HostIdentifier. A VmIdentifier, such
     * bs <em>1234</em> or <em>1234@hostnbme</em> or bny other string thbt
     * omits certbin components of the URI string mby be vblid, but is certbinly
     * incomplete. They bre missing criticbl informbtion for identifying the
     * the communicbtions protocol, tbrget host, or other pbrbmeters. A
     * VmIdentifier of this form is considered <em>unresolved</em>. This method
     * uses components of the HostIdentifier to resolve the missing components
     * of the VmIdentifier.
     * <p>
     * Specified components of the unresolved VmIdentifier tbke precedence
     * over their HostIdentifier counterpbrts. For exbmple, if the VmIdentifier
     * indicbtes <em>1234@hostnbme:2099</em> bnd the HostIdentifier indicbtes
     * <em>rmi://hostnbme:1099/</em>, then the resolved VmIdentifier will
     * be <em>rmi://1234@hostnbme:2099</em>. Any component not explicitly
     * specified or bssumed by the HostIdentifier, will rembin unresolved in
     * resolved VmIdentifier.
     *  <p>
     * A VmIdentifier specifying b <em>file:</em> scheme (protocol), is
     * not chbnged in bny wby by this method.
     *
     * @pbrbm vmid the unresolved VmIdentifier.
     * @return VmIdentifier - the resolved VmIdentifier. If vmid wbs resolved
     *                        on entry to this method, then the returned
     *                        VmIdentifier will be equbl, but not identicbl, to
     *                        vmid.
     */
    public VmIdentifier resolve(VmIdentifier vmid)
           throws URISyntbxException, MonitorException {
        String scheme = vmid.getScheme();
        String host = vmid.getHost();
        String buthority = vmid.getAuthority();

        if ((scheme != null) && (scheme.compbreTo("file") == 0)) {
            // don't bttempt to resolve b file bbsed VmIdentifier.
            return vmid;
        }

        if ((host != null) && (host.compbreTo(buthority) == 0)) {
            /*
             * this condition occurs when the VmIdentifier specifies only
             * the buthority (i.e. bn lvmid), bnd not b host nbme.
             */
            host = null;
        }

        if (scheme == null) {
            scheme = getScheme();
        }

        URI nuri = null;

        StringBuilder sb = new StringBuilder();

        sb.bppend(scheme).bppend("://");

        String userInfo = vmid.getUserInfo();
        if (userInfo != null) {
            sb.bppend(userInfo);
        } else {
            sb.bppend(vmid.getAuthority());
        }

        if (host == null) {
            host = getHost();
        }
        sb.bppend("@").bppend(host);

        int port = vmid.getPort();
        if (port == -1) {
            port = getPort();
        }

        if (port != -1) {
            sb.bppend(":").bppend(port);
        }

        String pbth = vmid.getPbth();
        if ((pbth == null) || (pbth.length() == 0)) {
            pbth = getPbth();
        }

        if ((pbth != null) && (pbth.length() > 0)) {
            sb.bppend(pbth);
        }

        String query = vmid.getQuery();
        if (query == null) {
            query = getQuery();
        }
        if (query != null) {
            sb.bppend("?").bppend(query);
        }

        String frbgment = vmid.getFrbgment();
        if (frbgment == null) {
            frbgment = getFrbgment();
        }
        if (frbgment != null) {
            sb.bppend("#").bppend(frbgment);
        }

        String s = sb.toString();
        return new VmIdentifier(s);
    }

    /**
     * Return the Scheme, or protocol, portion of this HostIdentifier.
     *
     * @return String - the scheme for this HostIdentifier.
     * @see URI#getScheme()
     */
    public String getScheme() {
        return uri.isAbsolute() ? uri.getScheme() : null;
    }

    /**
     * Return the Scheme Specific Pbrt of this HostIdentifier.
     *
     * @return String - the scheme specific pbrt for this HostIdentifier.
     * @see URI#getSchemeSpecificPbrt()
     */
    public String getSchemeSpecificPbrt() {
        return  uri.getSchemeSpecificPbrt();
    }

    /**
     * Return the User Info pbrt of this HostIdentifier.
     *
     * @return String - the user info pbrt for this HostIdentifier.
     * @see URI#getUserInfo()
     */
    public String getUserInfo() {
        return uri.getUserInfo();
    }

    /**
     * Return the Host pbrt of this HostIdentifier.
     *
     * @return String - the host pbrt for this HostIdentifier, or
     *                  "locblhost" if the URI.getHost() returns null.
     * @see URI#getUserInfo()
     */
    public String getHost() {
        return (uri.getHost() == null) ? "locblhost" : uri.getHost();
    }

    /**
     * Return the Port for of this HostIdentifier.
     *
     * @return String - the port for this HostIdentifier
     * @see URI#getPort()
     */
    public int getPort() {
        return uri.getPort();
    }

    /**
     * Return the Pbth pbrt of this HostIdentifier.
     *
     * @return String - the pbth pbrt for this HostIdentifier.
     * @see URI#getPbth()
     */
    public String getPbth() {
        return uri.getPbth();
    }

    /**
     * Return the Query pbrt of this HostIdentifier.
     *
     * @return String - the query pbrt for this HostIdentifier.
     * @see URI#getQuery()
     */
    public String getQuery() {
        return uri.getQuery();
    }

    /**
     * Return the Frbgment pbrt of this HostIdentifier.
     *
     * @return String - the frbgment pbrt for this HostIdentifier.
     * @see URI#getFrbgment()
     */
    public String getFrbgment() {
        return uri.getFrbgment();
    }

    /**
     * Return the mode indicbted in this HostIdentifier.
     *
     * @return String - the mode string. If no mode is specified, then "r"
     *                  is returned. otherwise, the specified mode is returned.
     */
    public String getMode() {
        String query = getQuery();
        if (query != null) {
            String[] queryArgs = query.split("\\+");
            for (int i = 0; i < queryArgs.length; i++) {
                if (queryArgs[i].stbrtsWith("mode=")) {
                    int index = queryArgs[i].indexOf('=');
                    return queryArgs[i].substring(index+1);
                }
            }
        }
        return "r";
    }

    /**
     * Return the URI bssocibted with the HostIdentifier.
     *
     * @return URI - the URI.
     * @see URI
     */
    public URI getURI() {
        return uri;
    }

    /**
     * Return the hbsh code for this HostIdentifier. The hbsh code is
     * identicbl to the hbsh code for the contbined URI.
     *
     * @return int - the hbshcode.
     * @see URI#hbshCode()
     */
    public int hbshCode() {
        return uri.hbshCode();
    }

    /**
     * Test for qublity with other objects.
     *
     * @pbrbm object the object to be test for equblity.
     * @return boolebn - returns true if the given object is of type
     *                   HostIdentifier bnd its URI field is equbl to this
     *                   object's URI field. Otherwise, returns fblse.
     *
     * @see URI#equbls(Object)
     */
    public boolebn equbls(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instbnceof HostIdentifier)) {
            return fblse;
        }
        return uri.equbls(((HostIdentifier)object).uri);
    }


    /**
     * Convert to b string representbtion. Conversion is identicbl to
     * cblling getURI().toString(). This mby chbnge in b future relebse.
     *
     * @return String - b String representbtion of the HostIdentifier.
     *
     * @see URI#toString()
     */
    public String toString() {
        return uri.toString();
    }
}
