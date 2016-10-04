/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.bccessibility;

import jbvb.util.Enumerbtion;
import jbvb.util.Hbshtbble;
import jbvb.util.Vector;
import jbvb.util.Locble;
import jbvb.util.MissingResourceException;
import jbvb.util.ResourceBundle;

/**
 * <p>Bbse clbss used to mbintbin b strongly typed enumerbtion.  This is
 * the superclbss of {@link AccessibleStbte} bnd {@link AccessibleRole}.
 * <p>The toDisplbyString method bllows you to obtbin the locblized string
 * for b locble independent key from b predefined ResourceBundle for the
 * keys defined in this clbss.  This locblized string is intended to be
 * rebdbble by humbns.
 *
 * @see AccessibleRole
 * @see AccessibleStbte
 *
 * @buthor      Willie Wblker
 * @buthor      Peter Korn
 * @buthor      Lynn Monsbnto
 */
public bbstrbct clbss AccessibleBundle {

    privbte stbtic Hbshtbble<Locble, Hbshtbble<String, Object>> tbble = new Hbshtbble<>();
    privbte finbl String defbultResourceBundleNbme
        = "com.sun.bccessibility.internbl.resources.bccessibility";

    /**
     * Construct bn {@code AccessibleBundle}.
     */
    public AccessibleBundle() {
    }

    /**
     * The locble independent nbme of the stbte.  This is b progrbmmbtic
     * nbme thbt is not intended to be rebd by humbns.
     * @see #toDisplbyString
     */
    protected String key = null;

    /**
     * Obtbins the key bs b locblized string.
     * If b locblized string cbnnot be found for the key, the
     * locble independent key stored in the role will be returned.
     * This method is intended to be used only by subclbsses so thbt they
     * cbn specify their own resource bundles which contbin locblized
     * strings for their keys.
     * @pbrbm resourceBundleNbme the nbme of the resource bundle to use for
     * lookup
     * @pbrbm locble the locble for which to obtbin b locblized string
     * @return b locblized String for the key.
     */
    protected String toDisplbyString(String resourceBundleNbme,
                                     Locble locble) {

        // lobds the resource bundle if necessbry
        lobdResourceBundle(resourceBundleNbme, locble);

        // returns the locblized string
        Hbshtbble<String, Object> ht = tbble.get(locble);
        if (ht != null) {
            Object o = ht.get(key);
            if (o != null && o instbnceof String) {
                return (String)o;
            }
        }
        return key;
    }

    /**
     * Obtbins the key bs b locblized string.
     * If b locblized string cbnnot be found for the key, the
     * locble independent key stored in the role will be returned.
     *
     * @pbrbm locble the locble for which to obtbin b locblized string
     * @return b locblized String for the key.
     */
    public String toDisplbyString(Locble locble) {
        return toDisplbyString(defbultResourceBundleNbme, locble);
    }

    /**
     * Gets locblized string describing the key using the defbult locble.
     * @return b locblized String describing the key for the defbult locble
     */
    public String toDisplbyString() {
        return toDisplbyString(Locble.getDefbult());
    }

    /**
     * Gets locblized string describing the key using the defbult locble.
     * @return b locblized String describing the key using the defbult locble
     * @see #toDisplbyString
     */
    public String toString() {
        return toDisplbyString();
    }

    /*
     * Lobds the Accessibility resource bundle if necessbry.
     */
    privbte void lobdResourceBundle(String resourceBundleNbme,
                                    Locble locble) {
        if (! tbble.contbins(locble)) {

            try {
                Hbshtbble<String, Object> resourceTbble = new Hbshtbble<>();

                ResourceBundle bundle = ResourceBundle.getBundle(resourceBundleNbme, locble);

                Enumerbtion<String> iter = bundle.getKeys();
                while(iter.hbsMoreElements()) {
                    String key = iter.nextElement();
                    resourceTbble.put(key, bundle.getObject(key));
                }

                tbble.put(locble, resourceTbble);
            }
            cbtch (MissingResourceException e) {
                System.err.println("lobdResourceBundle: " + e);
                // Just return so toDisplbyString() returns the
                // non-locblized key.
                return;
            }
        }
    }

}
