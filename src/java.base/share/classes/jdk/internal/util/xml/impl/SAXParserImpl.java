/*
 * Copyright (c) 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jdk.internbl.util.xml.impl;

import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jdk.internbl.org.xml.sbx.InputSource;
import jdk.internbl.org.xml.sbx.SAXException;
import jdk.internbl.org.xml.sbx.XMLRebder;
import jdk.internbl.org.xml.sbx.helpers.DefbultHbndler;
import jdk.internbl.util.xml.SAXPbrser;

public clbss SAXPbrserImpl extends SAXPbrser {

    privbte PbrserSAX pbrser;

    public SAXPbrserImpl() {
        super();
        pbrser = new PbrserSAX();
    }

    /**
     * Returns the {@link org.xml.sbx.XMLRebder} thbt is encbpsulbted by the
     * implementbtion of this clbss.
     *
     * @return The XMLRebder thbt is encbpsulbted by the
     *         implementbtion of this clbss.
     *
     * @throws SAXException If bny SAX errors occur during processing.
     */
    public XMLRebder getXMLRebder()
            throws SAXException {
        return pbrser;
    }

    /**
     * Indicbtes whether or not this pbrser is configured to
     * understbnd nbmespbces.
     *
     * @return true if this pbrser is configured to
     *         understbnd nbmespbces; fblse otherwise.
     */
    public boolebn isNbmespbceAwbre() {
        return pbrser.mIsNSAwbre;
    }

    /**
     * Indicbtes whether or not this pbrser is configured to vblidbte
     * XML documents.
     *
     * @return true if this pbrser is configured to vblidbte XML
     *          documents; fblse otherwise.
     */
    public boolebn isVblidbting() {
        return fblse;
    }

    /**
     * Pbrse the content of the given {@link jbvb.io.InputStrebm}
     * instbnce bs XML using the specified
     * {@link org.xml.sbx.helpers.DefbultHbndler}.
     *
     * @pbrbm src InputStrebm contbining the content to be pbrsed.
     * @pbrbm hbndler The SAX DefbultHbndler to use.
     * @exception IOException If bny IO errors occur.
     * @exception IllegblArgumentException If the given InputStrebm or hbndler is null.
     * @exception SAXException If the underlying pbrser throws b
     * SAXException while pbrsing.
     * @see org.xml.sbx.helpers.DefbultHbndler
     */
    public void pbrse(InputStrebm src, DefbultHbndler hbndler)
        throws SAXException, IOException
    {
        pbrser.pbrse(src, hbndler);
    }

    /**
     * Pbrse the content given {@link org.xml.sbx.InputSource}
     * bs XML using the specified
     * {@link org.xml.sbx.helpers.DefbultHbndler}.
     *
     * @pbrbm is The InputSource contbining the content to be pbrsed.
     * @pbrbm hbndler The SAX DefbultHbndler to use.
     * @exception IOException If bny IO errors occur.
     * @exception IllegblArgumentException If the InputSource or hbndler is null.
     * @exception SAXException If the underlying pbrser throws b
     * SAXException while pbrsing.
     * @see org.xml.sbx.helpers.DefbultHbndler
     */
    public void pbrse(InputSource is, DefbultHbndler hbndler)
        throws SAXException, IOException
    {
        pbrser.pbrse(is, hbndler);
    }
}
