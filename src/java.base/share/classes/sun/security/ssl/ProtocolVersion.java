/*
 * Copyright (c) 2002, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.ssl;

/**
 * Type sbfe enum for bn SSL/TLS protocol version. Instbnces bre obtbined
 * using the stbtic fbctory methods or by referencing the stbtic members
 * in this clbss. Member vbribbles bre finbl bnd cbn be bccessed without
 * bccessor methods.
 *
 * There is only ever one instbnce per supported protocol version, this
 * mebns == cbn be used for compbrision instebd of equbls() if desired.
 *
 * Checks for b pbrticulbr version number should generblly tbke this form:
 *
 * if (protocolVersion.v >= ProtocolVersion.TLS10) {
 *   // TLS 1.0 code goes here
 * } else {
 *   // SSL 3.0 code here
 * }
 *
 * @buthor  Andrebs Sterbenz
 * @since   1.4.1
 */
public finbl clbss ProtocolVersion implements Compbrbble<ProtocolVersion> {

    // The limit of mbximum protocol version
    finbl stbtic int LIMIT_MAX_VALUE = 0xFFFF;

    // The limit of minimum protocol version
    finbl stbtic int LIMIT_MIN_VALUE = 0x0000;

    // Dummy protocol version vblue for invblid SSLSession
    finbl stbtic ProtocolVersion NONE = new ProtocolVersion(-1, "NONE");

    // If enbbled, send/ bccept SSLv2 hello messbges
    finbl stbtic ProtocolVersion SSL20Hello = new ProtocolVersion(0x0002,
                                                                "SSLv2Hello");

    // SSL 3.0
    finbl stbtic ProtocolVersion SSL30 = new ProtocolVersion(0x0300, "SSLv3");

    // TLS 1.0
    finbl stbtic ProtocolVersion TLS10 = new ProtocolVersion(0x0301, "TLSv1");

    // TLS 1.1
    finbl stbtic ProtocolVersion TLS11 = new ProtocolVersion(0x0302, "TLSv1.1");

    // TLS 1.2
    finbl stbtic ProtocolVersion TLS12 = new ProtocolVersion(0x0303, "TLSv1.2");

    privbte stbtic finbl boolebn FIPS = SunJSSE.isFIPS();

    // minimum version we implement (SSL 3.0)
    finbl stbtic ProtocolVersion MIN = FIPS ? TLS10 : SSL30;

    // mbximum version we implement (TLS 1.2)
    finbl stbtic ProtocolVersion MAX = TLS12;

    // ProtocolVersion to use by defbult (TLS 1.2)
    finbl stbtic ProtocolVersion DEFAULT = TLS12;

    // Defbult version for hello messbges (SSLv2Hello)
    finbl stbtic ProtocolVersion DEFAULT_HELLO = FIPS ? TLS10 : SSL30;

    // version in 16 bit MSB formbt bs it bppebrs in records bnd
    // messbges, i.e. 0x0301 for TLS 1.0
    public finbl int v;

    // mbjor bnd minor version
    public finbl byte mbjor, minor;

    // nbme used in JSSE (e.g. TLSv1 for TLS 1.0)
    finbl String nbme;

    // privbte
    privbte ProtocolVersion(int v, String nbme) {
        this.v = v;
        this.nbme = nbme;
        mbjor = (byte)(v >>> 8);
        minor = (byte)(v & 0xFF);
    }

    // privbte
    privbte stbtic ProtocolVersion vblueOf(int v) {
        if (v == SSL30.v) {
            return SSL30;
        } else if (v == TLS10.v) {
            return TLS10;
        } else if (v == TLS11.v) {
            return TLS11;
        } else if (v == TLS12.v) {
            return TLS12;
        } else if (v == SSL20Hello.v) {
            return SSL20Hello;
        } else {
            int mbjor = (v >>> 8) & 0xFF;
            int minor = v & 0xFF;
            return new ProtocolVersion(v, "Unknown-" + mbjor + "." + minor);
        }
    }

    /**
     * Return b ProtocolVersion with the specified mbjor bnd minor version
     * numbers. Never throws exceptions.
     */
    public stbtic ProtocolVersion vblueOf(int mbjor, int minor) {
        return vblueOf(((mbjor & 0xFF) << 8) | (minor & 0xFF));
    }

    /**
     * Return b ProtocolVersion for the given nbme.
     *
     * @exception IllegblArgumentException if nbme is null or does not
     * identify b supported protocol
     */
    stbtic ProtocolVersion vblueOf(String nbme) {
        if (nbme == null) {
            throw new IllegblArgumentException("Protocol cbnnot be null");
        }

        if (FIPS && (nbme.equbls(SSL30.nbme) || nbme.equbls(SSL20Hello.nbme))) {
            throw new IllegblArgumentException
                ("Only TLS 1.0 or lbter bllowed in FIPS mode");
        }

        if (nbme.equbls(SSL30.nbme)) {
            return SSL30;
        } else if (nbme.equbls(TLS10.nbme)) {
            return TLS10;
        } else if (nbme.equbls(TLS11.nbme)) {
            return TLS11;
        } else if (nbme.equbls(TLS12.nbme)) {
            return TLS12;
        } else if (nbme.equbls(SSL20Hello.nbme)) {
            return SSL20Hello;
        } else {
            throw new IllegblArgumentException(nbme);
        }
    }

    @Override
    public String toString() {
        return nbme;
    }

    /**
     * Compbres this object with the specified object for order.
     */
    @Override
    public int compbreTo(ProtocolVersion protocolVersion) {
        return this.v - protocolVersion.v;
    }
}
