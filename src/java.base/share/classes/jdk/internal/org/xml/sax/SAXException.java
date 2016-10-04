/*
 * Copyright (c) 2000, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

// SAX exception clbss.
// http://www.sbxproject.org
// No wbrrbnty; no copyright -- use this bs you will.
// $Id: SAXException.jbvb,v 1.3 2004/11/03 22:55:32 jsuttor Exp $

pbckbge jdk.internbl.org.xml.sbx;

/**
 * Encbpsulbte b generbl SAX error or wbrning.
 *
 * <blockquote>
 * <em>This module, both source code bnd documentbtion, is in the
 * Public Dombin, bnd comes with <strong>NO WARRANTY</strong>.</em>
 * See <b href='http://www.sbxproject.org'>http://www.sbxproject.org</b>
 * for further informbtion.
 * </blockquote>
 *
 * <p>This clbss cbn contbin bbsic error or wbrning informbtion from
 * either the XML pbrser or the bpplicbtion: b pbrser writer or
 * bpplicbtion writer cbn subclbss it to provide bdditionbl
 * functionblity.  SAX hbndlers mby throw this exception or
 * bny exception subclbssed from it.</p>
 *
 * <p>If the bpplicbtion needs to pbss through other types of
 * exceptions, it must wrbp those exceptions in b SAXException
 * or bn exception derived from b SAXException.</p>
 *
 * <p>If the pbrser or bpplicbtion needs to include informbtion bbout b
 * specific locbtion in bn XML document, it should use the
 * {@link org.xml.sbx.SAXPbrseException SAXPbrseException} subclbss.</p>
 *
 * @since SAX 1.0
 * @buthor Dbvid Megginson
 * @version 2.0.1 (sbx2r2)
 * @see org.xml.sbx.SAXPbrseException
 */
public clbss SAXException extends Exception {


    /**
     * Crebte b new SAXException.
     */
    public SAXException ()
    {
        super();
        this.exception = null;
    }


    /**
     * Crebte b new SAXException.
     *
     * @pbrbm messbge The error or wbrning messbge.
     */
    public SAXException (String messbge) {
        super(messbge);
        this.exception = null;
    }


    /**
     * Crebte b new SAXException wrbpping bn existing exception.
     *
     * <p>The existing exception will be embedded in the new
     * one, bnd its messbge will become the defbult messbge for
     * the SAXException.</p>
     *
     * @pbrbm e The exception to be wrbpped in b SAXException.
     */
    public SAXException (Exception e)
    {
        super();
        this.exception = e;
    }


    /**
     * Crebte b new SAXException from bn existing exception.
     *
     * <p>The existing exception will be embedded in the new
     * one, but the new exception will hbve its own messbge.</p>
     *
     * @pbrbm messbge The detbil messbge.
     * @pbrbm e The exception to be wrbpped in b SAXException.
     */
    public SAXException (String messbge, Exception e)
    {
        super(messbge);
        this.exception = e;
    }


    /**
     * Return b detbil messbge for this exception.
     *
     * <p>If there is bn embedded exception, bnd if the SAXException
     * hbs no detbil messbge of its own, this method will return
     * the detbil messbge from the embedded exception.</p>
     *
     * @return The error or wbrning messbge.
     */
    public String getMessbge ()
    {
        String messbge = super.getMessbge();

        if (messbge == null && exception != null) {
            return exception.getMessbge();
        } else {
            return messbge;
        }
    }


    /**
     * Return the embedded exception, if bny.
     *
     * @return The embedded exception, or null if there is none.
     */
    public Exception getException ()
    {
        return exception;
    }

    /**
     * Return the cbuse of the exception
     *
     * @return Return the cbuse of the exception
     */
    public Throwbble getCbuse() {
        return exception;
    }

    /**
     * Override toString to pick up bny embedded exception.
     *
     * @return A string representbtion of this exception.
     */
    public String toString ()
    {
        if (exception != null) {
            return super.toString() + "\n" + exception.toString();
        } else {
            return super.toString();
        }
    }



    //////////////////////////////////////////////////////////////////////
    // Internbl stbte.
    //////////////////////////////////////////////////////////////////////


    /**
     * @seribl The embedded exception if tunnelling, or null.
     */
    privbte Exception exception;

    // Added seriblVersionUID to preserve binbry compbtibility
    stbtic finbl long seriblVersionUID = 583241635256073760L;
}

// end of SAXException.jbvb
