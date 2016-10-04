/*
 * Copyright (c) 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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
/*
 * $Id: XSLTTrbnsformPbrbmeterSpec.jbvb,v 1.4 2005/05/10 16:40:18 mullbn Exp $
 */
pbckbge jbvbx.xml.crypto.dsig.spec;

import jbvbx.xml.crypto.dsig.Trbnsform;
import jbvbx.xml.crypto.XMLStructure;

/**
 * Pbrbmeters for the <b href="http://www.w3.org/TR/1999/REC-xslt-19991116">
 * XSLT Trbnsform Algorithm</b>.
 * The pbrbmeters include b nbmespbce-qublified stylesheet element.
 *
 * <p>An <code>XSLTTrbnsformPbrbmeterSpec</code> is instbntibted with b
 * mechbnism-dependent (ex: DOM) stylesheet element. For exbmple:
 * <pre>
 *   DOMStructure stylesheet = new DOMStructure(element)
 *   XSLTTrbnsformPbrbmeterSpec spec = new XSLTrbnsformPbrbmeterSpec(stylesheet);
 * </pre>
 * where <code>element</code> is bn {@link org.w3c.dom.Element} contbining
 * the nbmespbce-qublified stylesheet element.
 *
 * @buthor Sebn Mullbn
 * @buthor JSR 105 Expert Group
 * @since 1.6
 * @see Trbnsform
 */
public finbl clbss XSLTTrbnsformPbrbmeterSpec implements TrbnsformPbrbmeterSpec{
    privbte XMLStructure stylesheet;

    /**
     * Crebtes bn <code>XSLTTrbnsformPbrbmeterSpec</code> with the specified
     * stylesheet.
     *
     * @pbrbm stylesheet the XSLT stylesheet to be used
     * @throws NullPointerException if <code>stylesheet</code> is
     *    <code>null</code>
     */
    public XSLTTrbnsformPbrbmeterSpec(XMLStructure stylesheet) {
        if (stylesheet == null) {
            throw new NullPointerException();
        }
        this.stylesheet = stylesheet;
    }

    /**
     * Returns the stylesheet.
     *
     * @return the stylesheet
     */
    public XMLStructure getStylesheet() {
        return stylesheet;
    }
}
