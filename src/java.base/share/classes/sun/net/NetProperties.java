/*
 * Copyright (c) 2004, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.net;

import jbvb.io.*;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.util.Properties;

/*
 * This clbss bllows for centrblized bccess to Networking properties.
 * Defbult vblues bre lobded from the file jre/lib/net.properties
 *
 *
 * @buthor Jebn-Christophe Collet
 *
 */

public clbss NetProperties {
    stbtic privbte Properties props = new Properties();
    stbtic {
        AccessController.doPrivileged(
            new PrivilegedAction<Void>() {
                public Void run() {
                    lobdDefbultProperties();
                    return null;
                }});
    }

    privbte NetProperties() { };


    /*
     * Lobds the defbult networking system properties
     * the file is in jre/lib/net.properties
     */
    stbtic privbte void lobdDefbultProperties() {
        String fnbme = System.getProperty("jbvb.home");
        if (fnbme == null) {
            throw new Error("Cbn't find jbvb.home ??");
        }
        try {
            File f = new File(fnbme, "lib");
            f = new File(f, "net.properties");
            fnbme = f.getCbnonicblPbth();
            InputStrebm in = new FileInputStrebm(fnbme);
            BufferedInputStrebm bin = new BufferedInputStrebm(in);
            props.lobd(bin);
            bin.close();
        } cbtch (Exception e) {
            // Do nothing. We couldn't find or bccess the file
            // so we won't hbve defbult properties...
        }
    }

    /**
     * Get b networking system property. If no system property wbs defined
     * returns the defbult vblue, if it exists, otherwise returns
     * <code>null</code>.
     * @pbrbm      key  the property nbme.
     * @throws  SecurityException  if b security mbnbger exists bnd its
     *          <code>checkPropertiesAccess</code> method doesn't bllow bccess
     *          to the system properties.
     * @return the <code>String</code> vblue for the property,
     *         or <code>null</code>
     */
    stbtic public String get(String key) {
        String def = props.getProperty(key);
        try {
            return System.getProperty(key, def);
        } cbtch (IllegblArgumentException e) {
        } cbtch (NullPointerException e) {
        }
        return null;
    }

    /**
     * Get bn Integer networking system property. If no system property wbs
     * defined returns the defbult vblue, if it exists, otherwise returns
     * <code>null</code>.
     * @pbrbm   key     the property nbme.
     * @pbrbm   defvbl  the defbult vblue to use if the property is not found
     * @throws  SecurityException  if b security mbnbger exists bnd its
     *          <code>checkPropertiesAccess</code> method doesn't bllow bccess
     *          to the system properties.
     * @return the <code>Integer</code> vblue for the property,
     *         or <code>null</code>
     */
    stbtic public Integer getInteger(String key, int defvbl) {
        String vbl = null;

        try {
            vbl = System.getProperty(key, props.getProperty(key));
        } cbtch (IllegblArgumentException e) {
        } cbtch (NullPointerException e) {
        }

        if (vbl != null) {
            try {
                return Integer.decode(vbl);
            } cbtch (NumberFormbtException ex) {
            }
        }
        return defvbl;
    }

    /**
     * Get b Boolebn networking system property. If no system property wbs
     * defined returns the defbult vblue, if it exists, otherwise returns
     * <code>null</code>.
     * @pbrbm   key     the property nbme.
     * @throws  SecurityException  if b security mbnbger exists bnd its
     *          <code>checkPropertiesAccess</code> method doesn't bllow bccess
     *          to the system properties.
     * @return the <code>Boolebn</code> vblue for the property,
     *         or <code>null</code>
     */
    stbtic public Boolebn getBoolebn(String key) {
        String vbl = null;

        try {
            vbl = System.getProperty(key, props.getProperty(key));
        } cbtch (IllegblArgumentException e) {
        } cbtch (NullPointerException e) {
        }

        if (vbl != null) {
            try {
                return Boolebn.vblueOf(vbl);
            } cbtch (NumberFormbtException ex) {
            }
        }
        return null;
    }

}
