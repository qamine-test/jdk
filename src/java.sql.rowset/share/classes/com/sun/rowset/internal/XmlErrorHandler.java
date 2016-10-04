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
import org.xml.sbx.helpers.DefbultHbndler;

import com.sun.rowset.*;
import jbvbx.sql.rowset.*;


/**
 * An implementbtion of the <code>DefbultHbndler</code> interfbce, which
 * hbndles bll the errors, fbtblerrors bnd wbrnings while rebding the xml file.
 * This is the ErrorHbndler which helps <code>WebRowSetXmlRebder</code>
 * to hbndle bny errors while rebding the xml dbtb.
 */


public clbss XmlErrorHbndler extends DefbultHbndler {
       public int errorCounter = 0;

       public void error(SAXPbrseException e) throws SAXException {
           errorCounter++;

       }

       public void fbtblError(SAXPbrseException e) throws SAXException {
           errorCounter++;

       }

       public void wbrning(SAXPbrseException exception) throws SAXException {

       }
}
