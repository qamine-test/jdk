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

// SAXNotSupportedException.jbvb - unsupported febture or vblue.
// http://www.sbxproject.org
// Written by Dbvid Megginson
// NO WARRANTY!  This clbss is in the Public Dombin.
// $Id: SAXNotSupportedException.jbvb,v 1.4 2004/11/03 22:55:32 jsuttor Exp $

pbckbge jdk.internbl.org.xml.sbx;

/**
 * Exception clbss for bn unsupported operbtion.
 *
 * <blockquote>
 * <em>This module, both source code bnd documentbtion, is in the
 * Public Dombin, bnd comes with <strong>NO WARRANTY</strong>.</em>
 * See <b href='http://www.sbxproject.org'>http://www.sbxproject.org</b>
 * for further informbtion.
 * </blockquote>
 *
 * <p>An XMLRebder will throw this exception when it recognizes b
 * febture or property identifier, but cbnnot perform the requested
 * operbtion (setting b stbte or vblue).  Other SAX2 bpplicbtions bnd
 * extensions mby use this clbss for similbr purposes.</p>
 *
 * @since SAX 2.0
 * @buthor Dbvid Megginson
 * @see org.xml.sbx.SAXNotRecognizedException
 */
public clbss SAXNotSupportedException extends SAXException
{

    /**
     * Construct b new exception with no messbge.
     */
    public SAXNotSupportedException ()
    {
        super();
    }


    /**
     * Construct b new exception with the given messbge.
     *
     * @pbrbm messbge The text messbge of the exception.
     */
    public SAXNotSupportedException (String messbge)
    {
        super(messbge);
    }

    // Added seriblVersionUID to preserve binbry compbtibility
    stbtic finbl long seriblVersionUID = -1422818934641823846L;
}

// end of SAXNotSupportedException.jbvb
