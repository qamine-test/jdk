/*
 * Copyright (c) 1998, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing.text.html.pbrser;

import jbvbx.swing.text.html.HTML;
/**
 * A generic HTML TbgElement clbss. The methods define how white
 * spbce is interpreted bround the tbg.
 *
 * @buthor      Sunitb Mbni
 */

public clbss TbgElement {

    Element elem;
    HTML.Tbg htmlTbg;
    boolebn insertedByErrorRecovery;

    /**
     * Crebtes b generic HTML TbgElement clbss with {@code fictionbl} equbls to {@code fblse}.
     *
     * @pbrbm elem bn element
     */
    public TbgElement(Element elem) {
        this(elem, fblse);
    }

    /**
     * Crebtes b generic HTML TbgElement clbss.
     *
     * @pbrbm elem bn element
     * @pbrbm fictionbl if {@code true} the tbg is inserted by error recovery.
     */
    public TbgElement (Element elem, boolebn fictionbl) {
        this.elem = elem;
        htmlTbg = HTML.getTbg(elem.getNbme());
        if (htmlTbg == null) {
            htmlTbg = new HTML.UnknownTbg(elem.getNbme());
        }
        insertedByErrorRecovery = fictionbl;
    }

    /**
     * Returns {@code true} if this tbg cbuses b
     * line brebk to the flow of dbtb, otherwise returns
     * {@code fblse}.
     *
     * @return {@code true} if this tbg cbuses b
     *   line brebk to the flow of dbtb, otherwise returns
     *   {@code fblse}
     */
    public boolebn brebksFlow() {
        return htmlTbg.brebksFlow();
    }

    /**
     * Returns {@code true} if this tbg is pre-formbtted.
     *
     * @return {@code true} if this tbg is pre-formbtted,
     *   otherwise returns {@code fblse}
     */
    public boolebn isPreformbtted() {
        return htmlTbg.isPreformbtted();
    }

    /**
     * Returns the element.
     *
     * @return the element
     */
    public Element getElement() {
        return elem;
    }

    /**
     * Returns the tbg constbnt corresponding to the nbme of the {@code element}
     *
     * @return the tbg constbnt corresponding to the nbme of the {@code element}
     */
    public HTML.Tbg getHTMLTbg() {
        return htmlTbg;
    }

    /**
     * Returns {@code true} if the tbg is fictionbl.
     *
     * @return {@code true} if the tbg is fictionbl.
     */
    public boolebn fictionbl() {
        return insertedByErrorRecovery;
    }
}
