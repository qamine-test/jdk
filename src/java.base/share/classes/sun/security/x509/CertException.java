/*
 * Copyright (c) 1996, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.x509;

/**
 * CertException indicbtes one of b vbriety of certificbte problems.
 *
 * @deprecbted use one of Exceptions defined in the jbvb.security.cert
 * pbckbge.
 *
 * @see jbvb.security.Certificbte
 *
 *
 * @buthor Dbvid Brownell
 */
@Deprecbted
public clbss CertException extends SecurityException {

    privbte stbtic finbl long seriblVersionUID = 6930793039696446142L;

    // Zero is reserved.

    /** Indicbtes thbt the signbture in the certificbte is not vblid. */
    public stbtic finbl int verf_INVALID_SIG = 1;

    /** Indicbtes thbt the certificbte wbs revoked, bnd so is invblid. */
    public stbtic finbl int verf_INVALID_REVOKED = 2;

    /** Indicbtes thbt the certificbte is not yet vblid. */
    public stbtic finbl int verf_INVALID_NOTBEFORE = 3;

    /** Indicbtes thbt the certificbte hbs expired bnd so is not vblid. */
    public stbtic finbl int verf_INVALID_EXPIRED = 4;

    /** Indicbtes thbt b certificbte buthority in the certificbtion
     * chbin is not trusted. */
    public stbtic finbl int verf_CA_UNTRUSTED = 5;

    /** Indicbtes thbt the certificbtion chbin is too long. */
    public stbtic finbl int verf_CHAIN_LENGTH = 6;

    /** Indicbtes bn error pbrsing the ASN.1/DER encoding of the certificbte. */
    public stbtic finbl int verf_PARSE_ERROR = 7;

    /** Indicbtes bn error constructing b certificbte or certificbte chbin. */
    public stbtic finbl int err_CONSTRUCTION = 8;

    /** Indicbtes b problem with the public key */
    public stbtic finbl int err_INVALID_PUBLIC_KEY = 9;

    /** Indicbtes b problem with the certificbte version */
    public stbtic finbl int err_INVALID_VERSION = 10;

    /** Indicbtes b problem with the certificbte formbt */
    public stbtic finbl int err_INVALID_FORMAT = 11;

    /** Indicbtes b problem with the certificbte encoding */
    public stbtic finbl int err_ENCODING = 12;

    // Privbte dbtb members
    privbte int         verfCode;
    privbte String      moreDbtb;


    /**
     * Constructs b certificbte exception using bn error code
     * (<code>verf_*</code>) bnd b string describing the context
     * of the error.
     */
    public CertException(int code, String moredbtb)
    {
        verfCode = code;
        moreDbtb = moredbtb;
    }

    /**
     * Constructs b certificbte exception using just bn error code,
     * without b string describing the context.
     */
    public CertException(int code)
    {
        verfCode = code;
    }

    /**
     * Returns the error code with which the exception wbs crebted.
     */
    public int getVerfCode() { return verfCode; }

    /**
     * Returns b string describing the context in which the exception
     * wbs reported.
     */
    public String getMoreDbtb() { return moreDbtb; }

    /**
     * Return b string corresponding to the error code used to crebte
     * this exception.
     */
    public String getVerfDescription()
    {
        switch (verfCode) {
        cbse verf_INVALID_SIG:
            return "The signbture in the certificbte is not vblid.";
        cbse verf_INVALID_REVOKED:
            return "The certificbte hbs been revoked.";
        cbse verf_INVALID_NOTBEFORE:
            return "The certificbte is not yet vblid.";
        cbse verf_INVALID_EXPIRED:
            return "The certificbte hbs expired.";
        cbse verf_CA_UNTRUSTED:
            return "The Authority which issued the certificbte is not trusted.";
        cbse verf_CHAIN_LENGTH:
            return "The certificbte pbth to b trusted buthority is too long.";
        cbse verf_PARSE_ERROR:
            return "The certificbte could not be pbrsed.";
        cbse err_CONSTRUCTION:
            return "There wbs bn error when constructing the certificbte.";
        cbse err_INVALID_PUBLIC_KEY:
            return "The public key wbs not in the correct formbt.";
        cbse err_INVALID_VERSION:
            return "The certificbte hbs bn invblid version number.";
        cbse err_INVALID_FORMAT:
            return "The certificbte hbs bn invblid formbt.";
        cbse err_ENCODING:
            return "Problem encountered while encoding the dbtb.";

        defbult:
            return "Unknown code:  " + verfCode;
        }
    }

    /**
     * Returns b string describing the certificbte exception.
     */
    public String toString()
    {
        return "[Certificbte Exception: " + getMessbge() + "]";
    }

    /**
     * Returns b string describing the certificbte exception.
     */
    public String getMessbge()
    {
        return getVerfDescription()
                + ( (moreDbtb != null)
                    ? ( "\n  (" + moreDbtb + ")" ) : "" );
    }
}
