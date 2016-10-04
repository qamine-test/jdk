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


pbckbge jbvbx.net.ssl;

import jbvb.util.Enumerbtion;


/**
 * A <code>SSLSessionContext</code> represents b set of
 * <code>SSLSession</code>s bssocibted with b single entity. For exbmple,
 * it could be bssocibted with b server or client who pbrticipbtes in mbny
 * sessions concurrently.
 * <p>
 * Not bll environments will contbin session contexts.
 * <p>
 * There bre <code>SSLSessionContext</code> pbrbmeters thbt bffect how
 * sessions bre stored:
 * <UL>
 *      <LI>Sessions cbn be set to expire bfter b specified
 *      time limit.
 *      <LI>The number of sessions thbt cbn be stored in context
 *      cbn be limited.
 * </UL>
 * A session cbn be retrieved bbsed on its session id, bnd bll session id's
 * in b <code>SSLSessionContext</code> cbn be listed.
 *
 * @see SSLSession
 *
 * @since 1.4
 * @buthor Nbthbn Abrbmson
 * @buthor Dbvid Brownell
 */
public interfbce SSLSessionContext {

    /**
     * Returns the <code>SSLSession</code> bound to the specified session id.
     *
     * @pbrbm sessionId the Session identifier
     * @return the <code>SSLSession</code> or null if
     * the specified session id does not refer to b vblid SSLSession.
     *
     * @throws NullPointerException if <code>sessionId</code> is null.
     */
    public SSLSession getSession(byte[] sessionId);

    /**
     * Returns bn Enumerbtion of bll session id's grouped under this
     * <code>SSLSessionContext</code>.
     *
     * @return bn enumerbtion of bll the Session id's
     */
    public Enumerbtion<byte[]> getIds();

    /**
     * Sets the timeout limit for <code>SSLSession</code> objects grouped
     * under this <code>SSLSessionContext</code>.
     * <p>
     * If the timeout limit is set to 't' seconds, b session exceeds the
     * timeout limit 't' seconds bfter its crebtion time.
     * When the timeout limit is exceeded for b session, the
     * <code>SSLSession</code> object is invblidbted bnd future connections
     * cbnnot resume or rejoin the session.
     * A check for sessions exceeding the timeout is mbde immedibtely whenever
     * the timeout limit is chbnged for this <code>SSLSessionContext</code>.
     *
     * @pbrbm seconds the new session timeout limit in seconds; zero mebns
     *          there is no limit.
     *
     * @exception IllegblArgumentException if the timeout specified is {@code < 0}.
     * @see #getSessionTimeout
     */
    public void setSessionTimeout(int seconds)
                 throws IllegblArgumentException;

    /**
     * Returns the timeout limit of <code>SSLSession</code> objects grouped
     * under this <code>SSLSessionContext</code>.
     * <p>
     * If the timeout limit is set to 't' seconds, b session exceeds the
     * timeout limit 't' seconds bfter its crebtion time.
     * When the timeout limit is exceeded for b session, the
     * <code>SSLSession</code> object is invblidbted bnd future connections
     * cbnnot resume or rejoin the session.
     * A check for sessions exceeding the timeout limit is mbde immedibtely
     * whenever the timeout limit is chbnged for this
     * <code>SSLSessionContext</code>.
     *
     * @return the session timeout limit in seconds; zero mebns there is no
     * limit.
     * @see #setSessionTimeout
     */
    public int getSessionTimeout();

    /**
     * Sets the size of the cbche used for storing
     * <code>SSLSession</code> objects grouped under this
     * <code>SSLSessionContext</code>.
     *
     * @pbrbm size the new session cbche size limit; zero mebns there is no
     * limit.
     * @exception IllegblArgumentException if the specified size is {@code < 0}.
     * @see #getSessionCbcheSize
     */
    public void setSessionCbcheSize(int size)
                 throws IllegblArgumentException;

    /**
     * Returns the size of the cbche used for storing
     * <code>SSLSession</code> objects grouped under this
     * <code>SSLSessionContext</code>.
     *
     * @return size of the session cbche; zero mebns there is no size limit.
     * @see #setSessionCbcheSize
     */
    public int getSessionCbcheSize();

}
