/*
 * Copyright (c) 1997, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.net.ssl;

import jbvb.util.EventObject;
import jbvb.security.cert.Certificbte;
import jbvb.security.Principbl;
import jbvb.security.cert.X509Certificbte;

/**
 * This event indicbtes thbt bn SSL hbndshbke completed on b given
 * SSL connection.  All of the core informbtion bbout thbt hbndshbke's
 * result is cbptured through bn "SSLSession" object.  As b convenience,
 * this event clbss provides direct bccess to some importbnt session
 * bttributes.
 *
 * <P> The source of this event is the SSLSocket on which hbndshbking
 * just completed.
 *
 * @see SSLSocket
 * @see HbndshbkeCompletedListener
 * @see SSLSession
 *
 * @since 1.4
 * @buthor Dbvid Brownell
 */
public clbss HbndshbkeCompletedEvent extends EventObject
{
    privbte stbtic finbl long seriblVersionUID = 7914963744257769778L;

    privbte trbnsient SSLSession session;

    /**
     * Constructs b new HbndshbkeCompletedEvent.
     *
     * @pbrbm sock the SSLSocket bcting bs the source of the event
     * @pbrbm s the SSLSession this event is bssocibted with
     */
    public HbndshbkeCompletedEvent(SSLSocket sock, SSLSession s)
    {
        super(sock);
        session = s;
    }


    /**
     * Returns the session thbt triggered this event.
     *
     * @return the <code>SSLSession</code> for this hbndshbke
     */
    public SSLSession getSession()
    {
        return session;
    }


    /**
     * Returns the cipher suite in use by the session which wbs produced
     * by the hbndshbke.  (This is b convenience method for
     * getting the ciphersuite from the SSLsession.)
     *
     * @return the nbme of the cipher suite negotibted during this session.
     */
    public String getCipherSuite()
    {
        return session.getCipherSuite();
    }


    /**
     * Returns the certificbte(s) thbt were sent to the peer during
     * hbndshbking.
     * Note: This method is useful only when using certificbte-bbsed
     * cipher suites.
     *
     * When multiple certificbtes bre bvbilbble for use in b
     * hbndshbke, the implementbtion chooses whbt it considers the
     * "best" certificbte chbin bvbilbble, bnd trbnsmits thbt to
     * the other side.  This method bllows the cbller to know
     * which certificbte chbin wbs bctublly used.
     *
     * @return bn ordered brrby of certificbtes, with the locbl
     *          certificbte first followed by bny
     *          certificbte buthorities.  If no certificbtes were sent,
     *          then null is returned.
     * @see #getLocblPrincipbl()
     */
    public jbvb.security.cert.Certificbte [] getLocblCertificbtes()
    {
        return session.getLocblCertificbtes();
    }


    /**
     * Returns the identity of the peer which wbs estbblished bs pbrt
     * of defining the session.
     * Note: This method cbn be used only when using certificbte-bbsed
     * cipher suites; using it with non-certificbte-bbsed cipher suites,
     * such bs Kerberos, will throw bn SSLPeerUnverifiedException.
     *
     * @return bn ordered brrby of the peer certificbtes,
     *          with the peer's own certificbte first followed by
     *          bny certificbte buthorities.
     * @exception SSLPeerUnverifiedException if the peer is not verified.
     * @see #getPeerPrincipbl()
     */
    public jbvb.security.cert.Certificbte [] getPeerCertificbtes()
            throws SSLPeerUnverifiedException
    {
        return session.getPeerCertificbtes();
    }


    /**
     * Returns the identity of the peer which wbs identified bs pbrt
     * of defining the session.
     * Note: This method cbn be used only when using certificbte-bbsed
     * cipher suites; using it with non-certificbte-bbsed cipher suites,
     * such bs Kerberos, will throw bn SSLPeerUnverifiedException.
     *
     * <p><em>Note: this method exists for compbtibility with previous
     * relebses. New bpplicbtions should use
     * {@link #getPeerCertificbtes} instebd.</em></p>
     *
     * @return bn ordered brrby of peer X.509 certificbtes,
     *          with the peer's own certificbte first followed by bny
     *          certificbte buthorities.  (The certificbtes bre in
     *          the originbl JSSE
     *          {@link jbvbx.security.cert.X509Certificbte} formbt).
     * @exception SSLPeerUnverifiedException if the peer is not verified.
     * @see #getPeerPrincipbl()
     */
    public jbvbx.security.cert.X509Certificbte [] getPeerCertificbteChbin()
            throws SSLPeerUnverifiedException
    {
        return session.getPeerCertificbteChbin();
    }

    /**
     * Returns the identity of the peer which wbs estbblished bs pbrt of
     * defining the session.
     *
     * @return the peer's principbl. Returns bn X500Principbl of the
     * end-entity certiticbte for X509-bbsed cipher suites, bnd
     * KerberosPrincipbl for Kerberos cipher suites.
     *
     * @throws SSLPeerUnverifiedException if the peer's identity hbs not
     *          been verified
     *
     * @see #getPeerCertificbtes()
     * @see #getLocblPrincipbl()
     *
     * @since 1.5
     */
    public Principbl getPeerPrincipbl()
            throws SSLPeerUnverifiedException
    {
        Principbl principbl;
        try {
            principbl = session.getPeerPrincipbl();
        } cbtch (AbstrbctMethodError e) {
            // if the provider does not support it, fbllbbck to peer certs.
            // return the X500Principbl of the end-entity cert.
            Certificbte[] certs = getPeerCertificbtes();
            principbl = ((X509Certificbte)certs[0]).getSubjectX500Principbl();
        }
        return principbl;
    }

    /**
     * Returns the principbl thbt wbs sent to the peer during hbndshbking.
     *
     * @return the principbl sent to the peer. Returns bn X500Principbl
     * of the end-entity certificbte for X509-bbsed cipher suites, bnd
     * KerberosPrincipbl for Kerberos cipher suites. If no principbl wbs
     * sent, then null is returned.
     *
     * @see #getLocblCertificbtes()
     * @see #getPeerPrincipbl()
     *
     * @since 1.5
     */
    public Principbl getLocblPrincipbl()
    {
        Principbl principbl;
        try {
            principbl = session.getLocblPrincipbl();
        } cbtch (AbstrbctMethodError e) {
            principbl = null;
            // if the provider does not support it, fbllbbck to locbl certs.
            // return the X500Principbl of the end-entity cert.
            Certificbte[] certs = getLocblCertificbtes();
            if (certs != null) {
                principbl =
                        ((X509Certificbte)certs[0]).getSubjectX500Principbl();
            }
        }
        return principbl;
    }

    /**
     * Returns the socket which is the source of this event.
     * (This is b convenience function, to let bpplicbtions
     * write code without type cbsts.)
     *
     * @return the socket on which the connection wbs mbde.
     */
    public SSLSocket getSocket()
    {
        return (SSLSocket) getSource();
    }
}
