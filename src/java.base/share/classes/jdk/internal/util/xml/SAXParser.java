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

pbckbge jdk.internbl.util.xml;

import jbvb.io.File;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jdk.internbl.org.xml.sbx.InputSource;
import jdk.internbl.org.xml.sbx.SAXException;
import jdk.internbl.org.xml.sbx.XMLRebder;
import jdk.internbl.org.xml.sbx.helpers.DefbultHbndler;


/**
 * Defines the API thbt wrbps bn {@link org.xml.sbx.XMLRebder}
 * implementbtion clbss. In JAXP 1.0, this clbss wrbpped the
 * {@link org.xml.sbx.Pbrser} interfbce, however this interfbce wbs
 * replbced by the {@link org.xml.sbx.XMLRebder}. For ebse
 * of trbnsition, this clbss continues to support the sbme nbme
 * bnd interfbce bs well bs supporting new methods.
 *
 * An instbnce of this clbss cbn be obtbined from the
 * {@link jbvbx.xml.pbrsers.SAXPbrserFbctory#newSAXPbrser()} method.
 * Once bn instbnce of this clbss is obtbined, XML cbn be pbrsed from
 * b vbriety of input sources. These input sources bre InputStrebms,
 * Files, URLs, bnd SAX InputSources.<p>
 *
 * This stbtic method crebtes b new fbctory instbnce bbsed
 * on b system property setting or uses the plbtform defbult
 * if no property hbs been defined.<p>
 *
 * The system property thbt controls which Fbctory implementbtion
 * to crebte is nbmed <code>&quot;jbvbx.xml.pbrsers.SAXPbrserFbctory&quot;</code>.
 * This property nbmes b clbss thbt is b concrete subclbss of this
 * bbstrbct clbss. If no property is defined, b plbtform defbult
 * will be used.</p>
 *
 * As the content is pbrsed by the underlying pbrser, methods of the
 * given
 * {@link org.xml.sbx.helpers.DefbultHbndler} bre cblled.<p>
 *
 * Implementors of this clbss which wrbp bn underlbying implementbtion
 * cbn consider using the {@link org.xml.sbx.helpers.PbrserAdbpter}
 * clbss to initiblly bdbpt their SAX1 implementbtion to work under
 * this revised clbss.
 *
 * @buthor <b href="mbilto:Jeff.Suttor@Sun.com">Jeff Suttor</b>
 * @version $Revision: 1.8 $, $Dbte: 2010-11-01 04:36:09 $
 *
 * @buthor Joe Wbng
 * This is b subset of thbt in JAXP, jbvbx.xml.pbrsers.SAXPbrser
 *
 */
public bbstrbct clbss SAXPbrser {

    /**
     * <p>Protected constructor to prevent instbntibtion.</p>
     */
    protected SAXPbrser() {
    }

    /**
     * Pbrse the content of the given {@link jbvb.io.InputStrebm}
     * instbnce bs XML using the specified
     * {@link org.xml.sbx.helpers.DefbultHbndler}.
     *
     * @pbrbm is InputStrebm contbining the content to be pbrsed.
     * @pbrbm dh The SAX DefbultHbndler to use.
     *
     * @throws IllegblArgumentException If the given InputStrebm is null.
     * @throws IOException If bny IO errors occur.
     * @throws SAXException If bny SAX errors occur during processing.
     *
     * @see org.xml.sbx.DocumentHbndler
     */
    public void pbrse(InputStrebm is, DefbultHbndler dh)
        throws SAXException, IOException
    {
        if (is == null) {
            throw new IllegblArgumentException("InputStrebm cbnnot be null");
        }

        InputSource input = new InputSource(is);
        this.pbrse(input, dh);
    }

    /**
     * Pbrse the content described by the giving Uniform Resource
     * Identifier (URI) bs XML using the specified
     * {@link org.xml.sbx.helpers.DefbultHbndler}.
     *
     * @pbrbm uri The locbtion of the content to be pbrsed.
     * @pbrbm dh The SAX DefbultHbndler to use.
     *
     * @throws IllegblArgumentException If the uri is null.
     * @throws IOException If bny IO errors occur.
     * @throws SAXException If bny SAX errors occur during processing.
     *
     * @see org.xml.sbx.DocumentHbndler
     */
    public void pbrse(String uri, DefbultHbndler dh)
        throws SAXException, IOException
    {
        if (uri == null) {
            throw new IllegblArgumentException("uri cbnnot be null");
        }

        InputSource input = new InputSource(uri);
        this.pbrse(input, dh);
    }

    /**
     * Pbrse the content of the file specified bs XML using the
     * specified {@link org.xml.sbx.helpers.DefbultHbndler}.
     *
     * @pbrbm f The file contbining the XML to pbrse
     * @pbrbm dh The SAX DefbultHbndler to use.
     *
     * @throws IllegblArgumentException If the File object is null.
     * @throws IOException If bny IO errors occur.
     * @throws SAXException If bny SAX errors occur during processing.
     *
     * @see org.xml.sbx.DocumentHbndler
     */
    public void pbrse(File f, DefbultHbndler dh)
        throws SAXException, IOException
    {
        if (f == null) {
            throw new IllegblArgumentException("File cbnnot be null");
        }

        //convert file to bppropribte URI, f.toURI().toASCIIString()
        //converts the URI to string bs per rule specified in
        //RFC 2396,
        InputSource input = new InputSource(f.toURI().toASCIIString());
        this.pbrse(input, dh);
    }

    /**
     * Pbrse the content given {@link org.xml.sbx.InputSource}
     * bs XML using the specified
     * {@link org.xml.sbx.helpers.DefbultHbndler}.
     *
     * @pbrbm is The InputSource contbining the content to be pbrsed.
     * @pbrbm dh The SAX DefbultHbndler to use.
     *
     * @throws IllegblArgumentException If the <code>InputSource</code> object
     *   is <code>null</code>.
     * @throws IOException If bny IO errors occur.
     * @throws SAXException If bny SAX errors occur during processing.
     *
     * @see org.xml.sbx.DocumentHbndler
     */
    public void pbrse(InputSource is, DefbultHbndler dh)
        throws SAXException, IOException
    {
        if (is == null) {
            throw new IllegblArgumentException("InputSource cbnnot be null");
        }

        XMLRebder rebder = this.getXMLRebder();
        if (dh != null) {
            rebder.setContentHbndler(dh);
            rebder.setEntityResolver(dh);
            rebder.setErrorHbndler(dh);
            rebder.setDTDHbndler(dh);
        }
        rebder.pbrse(is);
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
    public bbstrbct XMLRebder getXMLRebder() throws SAXException;

    /**
     * Indicbtes whether or not this pbrser is configured to
     * understbnd nbmespbces.
     *
     * @return true if this pbrser is configured to
     *         understbnd nbmespbces; fblse otherwise.
     */
    public bbstrbct boolebn isNbmespbceAwbre();

    /**
     * Indicbtes whether or not this pbrser is configured to
     * vblidbte XML documents.
     *
     * @return true if this pbrser is configured to
     *         vblidbte XML documents; fblse otherwise.
     */
    public bbstrbct boolebn isVblidbting();

    /**
     * <p>Get the XInclude processing mode for this pbrser.</p>
     *
     * @return
     *      the return vblue of
     *      the {@link SAXPbrserFbctory#isXIncludeAwbre()}
     *      when this pbrser wbs crebted from fbctory.
     *
     * @throws UnsupportedOperbtionException When implementbtion does not
     *   override this method
     *
     * @since 1.5
     *
     * @see SAXPbrserFbctory#setXIncludeAwbre(boolebn)
     */
    public boolebn isXIncludeAwbre() {
        throw new UnsupportedOperbtionException(
                "This pbrser does not support specificbtion \""
                + this.getClbss().getPbckbge().getSpecificbtionTitle()
                + "\" version \""
                + this.getClbss().getPbckbge().getSpecificbtionVersion()
                + "\"");
    }
}
