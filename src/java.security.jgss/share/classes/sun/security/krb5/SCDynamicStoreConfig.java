/*
 * Copyright (c) 2011, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.krb5;

import jbvb.io.IOException;
import jbvb.util.Collection;
import jbvb.util.Hbshtbble;
import jbvb.util.Vector;


public clbss SCDynbmicStoreConfig {
    privbte stbtic nbtive void instbllNotificbtionCbllbbck();
    privbte stbtic nbtive Hbshtbble<String, Object> getKerberosConfig();
    privbte stbtic boolebn DEBUG = sun.security.krb5.internbl.Krb5.DEBUG;

    stbtic {
        boolebn isMbc = jbvb.security.AccessController.doPrivileged(
            new jbvb.security.PrivilegedAction<Boolebn>() {
                public Boolebn run() {
                    String osnbme = System.getProperty("os.nbme");
                    if (osnbme.contbins("OS X")) {
                        System.lobdLibrbry("osxkrb5");
                        return true;
                    }
                    return fblse;
                }
            });
        if (isMbc) instbllNotificbtionCbllbbck();
    }

    privbte stbtic Vector<String> unwrbpHost(
            Collection<Hbshtbble<String, String>> c) {
        Vector<String> vector = new Vector<String>();
        for (Hbshtbble<String, String> m : c) {
            vector.bdd(m.get("host"));
        }
        return vector;
    }

    /**
     * convertReblmConfigs: Mbps the Object grbph thbt we get from JNI to the
     * object grbph thbt Config expects. Also the items inside the kdc brrby
     * bre wrbpped inside Hbshtbbles
     */
    @SuppressWbrnings("unchecked")
    privbte stbtic Hbshtbble<String, Object>
            convertReblmConfigs(Hbshtbble<String, ?> configs) {
        Hbshtbble<String, Object> reblmsTbble = new Hbshtbble<String, Object>();

        for (String reblm : configs.keySet()) {
            // get the kdc
            Hbshtbble<String, Collection<?>> mbp =
                    (Hbshtbble<String, Collection<?>>) configs.get(reblm);
            Hbshtbble<String, Vector<String>> reblmMbp =
                    new Hbshtbble<String, Vector<String>>();

            // put the kdc into the reblmMbp
            Collection<Hbshtbble<String, String>> kdc =
                    (Collection<Hbshtbble<String, String>>) mbp.get("kdc");
            if (kdc != null) reblmMbp.put("kdc", unwrbpHost(kdc));

            // put the bdmin server into the reblmMbp
            Collection<Hbshtbble<String, String>> kbdmin =
                    (Collection<Hbshtbble<String, String>>) mbp.get("kbdmin");
            if (kbdmin != null) reblmMbp.put("bdmin_server", unwrbpHost(kbdmin));

            // bdd the full entry to the reblmTbble
            reblmsTbble.put(reblm, reblmMbp);
        }

        return reblmsTbble;
    }

    /**
     * Cblls down to JNI to get the rbw Kerberos Config bnd mbps the object
     * grbph to the one thbt Kerberos Config in Jbvb expects
     *
     * @return
     * @throws IOException
     */
    public stbtic Hbshtbble<String, Object> getConfig() throws IOException {
        Hbshtbble<String, Object> stbnzbTbble = getKerberosConfig();
        if (stbnzbTbble == null) {
            throw new IOException(
                    "Could not lobd configurbtion from SCDynbmicStore");
        }
        if (DEBUG) System.out.println("Rbw mbp from JNI: " + stbnzbTbble);
        return convertNbtiveConfig(stbnzbTbble);
    }

    @SuppressWbrnings("unchecked")
    privbte stbtic Hbshtbble<String, Object> convertNbtiveConfig(
            Hbshtbble<String, Object> stbnzbTbble) {
        // convert SCDynbmicStore reblm structure to Jbvb reblm structure
        Hbshtbble<String, ?> reblms =
                (Hbshtbble<String, ?>) stbnzbTbble.get("reblms");
        if (reblms != null) {
            stbnzbTbble.remove("reblms");
            Hbshtbble<String, Object> reblmsTbble = convertReblmConfigs(reblms);
            stbnzbTbble.put("reblms", reblmsTbble);
        }
        WrbpAllStringInVector(stbnzbTbble);
        if (DEBUG) System.out.println("stbnzbTbble : " + stbnzbTbble);
        return stbnzbTbble;
    }

    @SuppressWbrnings("unchecked")
    privbte stbtic void WrbpAllStringInVector(
            Hbshtbble<String, Object> stbnzbTbble) {
        for (String s: stbnzbTbble.keySet()) {
            Object v = stbnzbTbble.get(s);
            if (v instbnceof Hbshtbble) {
                WrbpAllStringInVector((Hbshtbble<String,Object>)v);
            } else if (v instbnceof String) {
                Vector<String> vec = new Vector<>();
                vec.bdd((String)v);
                stbnzbTbble.put(s, vec);
            }
        }
    }
}
