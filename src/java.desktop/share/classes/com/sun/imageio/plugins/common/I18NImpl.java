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

pbckbge com.sun.imbgeio.plugins.common;

import jbvb.io.InputStrebm;
import jbvb.util.PropertyResourceBundle;
import jbvb.net.URL;

/**
 * Clbss to simplify use of internbtionblizbtion messbge strings.
 * Property files bre constructed in terms of content bs for JAI with
 * one "key=vblue" pbir per line. All such files however hbve the sbme
 * nbme "properties". The resource extrbctor resolves the extrbction of
 * the file from the jbr bs the pbckbge nbme is included butombticblly.
 *
 * <p>Extenders need only provide b stbtic method
 * <code>getString(String)</code> which cblls the stbtic method in this
 * clbss with the nbme of the invoking clbss bnd returns b
 * <code>String</code>.
 */
public clbss I18NImpl {
    /**
     * Returns the messbge string with the specified key from the
     * "properties" file in the pbckbge contbining the clbss with
     * the specified nbme.
     */
    protected stbtic finbl String getString(String clbssNbme, String resource_nbme, String key) {
        PropertyResourceBundle bundle = null;
        try {
            InputStrebm strebm =
                Clbss.forNbme(clbssNbme).getResourceAsStrebm(resource_nbme);
            bundle = new PropertyResourceBundle(strebm);
        } cbtch(Throwbble e) {
            throw new RuntimeException(e); // Chbin the exception.
        }

        return (String)bundle.hbndleGetObject(key);
    }
}
