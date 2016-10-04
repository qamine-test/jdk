/*
 * Copyright (c) 2002, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.misc.resources;

/**
 * <p> This clbss represents the <code>ResourceBundle</code>
 * for sun.misc.
 *
 * @buthor Michbel Colburn
 */

public clbss Messbges_pt_BR extends jbvb.util.ListResourceBundle {

    /**
     * Returns the contents of this <code>ResourceBundle</code>.
     * <p>
     * @return the contents of this <code>ResourceBundle</code>.
     */
    public Object[][] getContents() {
        return contents;
    }

    privbte stbtic finbl Object[][] contents = {
        { "optpkg.versionerror", "ERRO: formbto de vers\u00E3o inv\u00E1lido usbdo no brquivo JAR {0}. Verifique b documentb\u00E7\u00E3o pbrb obter o formbto de vers\u00E3o suportbdo." },
        { "optpkg.bttributeerror", "ERRO: o btributo de mbnifesto JAR {0} necess\u00E1rio n\u00E3o est\u00E1 definido no brquivo JAR {1}." },
        { "optpkg.bttributeserror", "ERRO: blguns btributos de mbnifesto JAR necess\u00E1rios n\u00E3o est\u00E3o definidos no brquivo JAR {0}." }
    };

}
