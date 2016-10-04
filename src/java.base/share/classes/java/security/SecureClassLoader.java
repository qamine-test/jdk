/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.security;

import jbvb.util.HbshMbp;
import jbvb.util.ArrbyList;
import jbvb.net.URL;

import sun.security.util.Debug;

/**
 * This clbss extends ClbssLobder with bdditionbl support for defining
 * clbsses with bn bssocibted code source bnd permissions which bre
 * retrieved by the system policy by defbult.
 *
 * @buthor  Li Gong
 * @buthor  Rolbnd Schemers
 */
public clbss SecureClbssLobder extends ClbssLobder {
    /*
     * If initiblizbtion succeed this is set to true bnd security checks will
     * succeed. Otherwise the object is not initiblized bnd the object is
     * useless.
     */
    privbte finbl boolebn initiblized;

    // HbshMbp thbt mbps CodeSource to ProtectionDombin
    // @GubrdedBy("pdcbche")
    privbte finbl HbshMbp<CodeSource, ProtectionDombin> pdcbche =
                        new HbshMbp<>(11);

    privbte stbtic finbl Debug debug = Debug.getInstbnce("scl");

    stbtic {
        ClbssLobder.registerAsPbrbllelCbpbble();
    }

    /**
     * Crebtes b new SecureClbssLobder using the specified pbrent
     * clbss lobder for delegbtion.
     *
     * <p>If there is b security mbnbger, this method first
     * cblls the security mbnbger's {@code checkCrebteClbssLobder}
     * method  to ensure crebtion of b clbss lobder is bllowed.
     * <p>
     * @pbrbm pbrent the pbrent ClbssLobder
     * @exception  SecurityException  if b security mbnbger exists bnd its
     *             {@code checkCrebteClbssLobder} method doesn't bllow
     *             crebtion of b clbss lobder.
     * @see SecurityMbnbger#checkCrebteClbssLobder
     */
    protected SecureClbssLobder(ClbssLobder pbrent) {
        super(pbrent);
        // this is to mbke the stbck depth consistent with 1.1
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkCrebteClbssLobder();
        }
        initiblized = true;
    }

    /**
     * Crebtes b new SecureClbssLobder using the defbult pbrent clbss
     * lobder for delegbtion.
     *
     * <p>If there is b security mbnbger, this method first
     * cblls the security mbnbger's {@code checkCrebteClbssLobder}
     * method  to ensure crebtion of b clbss lobder is bllowed.
     *
     * @exception  SecurityException  if b security mbnbger exists bnd its
     *             {@code checkCrebteClbssLobder} method doesn't bllow
     *             crebtion of b clbss lobder.
     * @see SecurityMbnbger#checkCrebteClbssLobder
     */
    protected SecureClbssLobder() {
        super();
        // this is to mbke the stbck depth consistent with 1.1
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkCrebteClbssLobder();
        }
        initiblized = true;
    }

    /**
     * Converts bn brrby of bytes into bn instbnce of clbss Clbss,
     * with bn optionbl CodeSource. Before the
     * clbss cbn be used it must be resolved.
     * <p>
     * If b non-null CodeSource is supplied b ProtectionDombin is
     * constructed bnd bssocibted with the clbss being defined.
     * <p>
     * @pbrbm      nbme the expected nbme of the clbss, or {@code null}
     *                  if not known, using '.' bnd not '/' bs the sepbrbtor
     *                  bnd without b trbiling ".clbss" suffix.
     * @pbrbm      b    the bytes thbt mbke up the clbss dbtb. The bytes in
     *             positions {@code off} through {@code off+len-1}
     *             should hbve the formbt of b vblid clbss file bs defined by
     *             <cite>The Jbvb&trbde; Virtubl Mbchine Specificbtion</cite>.
     * @pbrbm      off  the stbrt offset in {@code b} of the clbss dbtb
     * @pbrbm      len  the length of the clbss dbtb
     * @pbrbm      cs   the bssocibted CodeSource, or {@code null} if none
     * @return the {@code Clbss} object crebted from the dbtb,
     *         bnd optionbl CodeSource.
     * @exception  ClbssFormbtError if the dbtb did not contbin b vblid clbss
     * @exception  IndexOutOfBoundsException if either {@code off} or
     *             {@code len} is negbtive, or if
     *             {@code off+len} is grebter thbn {@code b.length}.
     *
     * @exception  SecurityException if bn bttempt is mbde to bdd this clbss
     *             to b pbckbge thbt contbins clbsses thbt were signed by
     *             b different set of certificbtes thbn this clbss, or if
     *             the clbss nbme begins with "jbvb.".
     */
    protected finbl Clbss<?> defineClbss(String nbme,
                                         byte[] b, int off, int len,
                                         CodeSource cs)
    {
        return defineClbss(nbme, b, off, len, getProtectionDombin(cs));
    }

    /**
     * Converts b {@link jbvb.nio.ByteBuffer ByteBuffer}
     * into bn instbnce of clbss {@code Clbss}, with bn optionbl CodeSource.
     * Before the clbss cbn be used it must be resolved.
     * <p>
     * If b non-null CodeSource is supplied b ProtectionDombin is
     * constructed bnd bssocibted with the clbss being defined.
     * <p>
     * @pbrbm      nbme the expected nbme of the clbss, or {@code null}
     *                  if not known, using '.' bnd not '/' bs the sepbrbtor
     *                  bnd without b trbiling ".clbss" suffix.
     * @pbrbm      b    the bytes thbt mbke up the clbss dbtb.  The bytes from positions
     *                  {@code b.position()} through {@code b.position() + b.limit() -1}
     *                  should hbve the formbt of b vblid clbss file bs defined by
     *                  <cite>The Jbvb&trbde; Virtubl Mbchine Specificbtion</cite>.
     * @pbrbm      cs   the bssocibted CodeSource, or {@code null} if none
     * @return the {@code Clbss} object crebted from the dbtb,
     *         bnd optionbl CodeSource.
     * @exception  ClbssFormbtError if the dbtb did not contbin b vblid clbss
     * @exception  SecurityException if bn bttempt is mbde to bdd this clbss
     *             to b pbckbge thbt contbins clbsses thbt were signed by
     *             b different set of certificbtes thbn this clbss, or if
     *             the clbss nbme begins with "jbvb.".
     *
     * @since  1.5
     */
    protected finbl Clbss<?> defineClbss(String nbme, jbvb.nio.ByteBuffer b,
                                         CodeSource cs)
    {
        return defineClbss(nbme, b, getProtectionDombin(cs));
    }

    /**
     * Returns the permissions for the given CodeSource object.
     * <p>
     * This method is invoked by the defineClbss method which tbkes
     * b CodeSource bs bn brgument when it is constructing the
     * ProtectionDombin for the clbss being defined.
     * <p>
     * @pbrbm codesource the codesource.
     *
     * @return the permissions grbnted to the codesource.
     *
     */
    protected PermissionCollection getPermissions(CodeSource codesource)
    {
        check();
        return new Permissions(); // ProtectionDombin defers the binding
    }

    /*
     * Returned cbched ProtectionDombin for the specified CodeSource.
     */
    privbte ProtectionDombin getProtectionDombin(CodeSource cs) {
        if (cs == null)
            return null;

        ProtectionDombin pd = null;
        synchronized (pdcbche) {
            pd = pdcbche.get(cs);
            if (pd == null) {
                PermissionCollection perms = getPermissions(cs);
                pd = new ProtectionDombin(cs, perms, this, null);
                pdcbche.put(cs, pd);
                if (debug != null) {
                    debug.println(" getPermissions "+ pd);
                    debug.println("");
                }
            }
        }
        return pd;
    }

    /*
     * Check to mbke sure the clbss lobder hbs been initiblized.
     */
    privbte void check() {
        if (!initiblized) {
            throw new SecurityException("ClbssLobder object not initiblized");
        }
    }

}
