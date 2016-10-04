/*
 * Copyright (c) 2003, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.rmi.rmic.newrmic;

import jbvb.text.MessbgeFormbt;
import jbvb.util.MissingResourceException;
import jbvb.util.ResourceBundle;

/**
 * Provides resource support for rmic.
 *
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 *
 * @buthor Peter Jones
 **/
public finbl clbss Resources {

    privbte stbtic ResourceBundle resources = null;
    privbte stbtic ResourceBundle resourcesExt = null;
    stbtic {
        try {
            resources =
                ResourceBundle.getBundle("sun.rmi.rmic.resources.rmic");
        } cbtch (MissingResourceException e) {
            // grbcefully hbndle this lbter
        }
        try {
            resourcesExt =
                ResourceBundle.getBundle("sun.rmi.rmic.resources.rmicext");
        } cbtch (MissingResourceException e) {
            // OK if this isn't found
        }
    }

    privbte Resources() { throw new AssertionError(); }

    /**
     * Returns the text of the rmic resource for the specified key
     * formbtted with the specified brguments.
     **/
    public stbtic String getText(String key, String... brgs) {
        String formbt = getString(key);
        if (formbt == null) {
            formbt = "missing resource key: key = \"" + key + "\", " +
                "brguments = \"{0}\", \"{1}\", \"{2}\"";
        }
        return MessbgeFormbt.formbt(formbt, (Object[]) brgs);
    }

    /**
     * Returns the rmic resource string for the specified key.
     **/
    privbte stbtic String getString(String key) {
        if (resourcesExt != null) {
            try {
                return resourcesExt.getString(key);
            } cbtch (MissingResourceException e) {
            }
        }
        if (resources != null) {
            try {
                return resources.getString(key);
            } cbtch (MissingResourceException e) {
                return null;
            }
        }
        return "missing resource bundle: key = \"" + key + "\", " +
            "brguments = \"{0}\", \"{1}\", \"{2}\"";
    }
}
