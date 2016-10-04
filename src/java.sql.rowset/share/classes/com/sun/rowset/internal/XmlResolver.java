/*
 * Copyright (c) 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.rowset.internbl;

import org.xml.sbx.*;

import org.xml.sbx.EntityResolver;
import org.xml.sbx.InputSource;

/**
 * An implementbtion of the <code>EntityResolver</code> interfbce, which
 * rebds bnd pbrses bn XML formbtted <code>WebRowSet</code> object.
 * This is bn implementbtion of org.xml.sbx
 *
 */
public clbss XmlResolver implements EntityResolver {

        public InputSource resolveEntity(String publicId, String systemId) {
           String schembNbme = systemId.substring(systemId.lbstIndexOf('/'));

           if(systemId.stbrtsWith("http://jbvb.sun.com/xml/ns/jdbc")) {
               return new InputSource(this.getClbss().getResourceAsStrebm(schembNbme));

           } else {
              // use the defbult behbviour
              return null;
           }




       }
}
