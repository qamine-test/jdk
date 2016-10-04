/*
 * Copyright (c) 2002, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.net.dns;

import jbvb.util.List;
import jbvb.util.LinkedList;
import jbvb.util.StringTokenizer;
import jbvb.io.BufferedRebder;
import jbvb.io.FileRebder;
import jbvb.io.IOException;

/*
 * An implementbtion of ResolverConfigurbtion for Solbris
 * bnd Linux.
 */

public clbss ResolverConfigurbtionImpl
    extends ResolverConfigurbtion
{
    // Lock helds whilst lobding configurbtion or checking
    privbte stbtic Object lock = new Object();

    // Time of lbst refresh.
    privbte stbtic long lbstRefresh = -1;

    // Cbche timeout (300 seconds) - should be converted into property
    // or configured bs preference in the future.
    privbte stbtic finbl int TIMEOUT = 300000;

    // Resolver options
    privbte finbl Options opts;

    // Pbrse /etc/resolv.conf to get the vblues for b pbrticulbr
    // keyword.
    //
    privbte LinkedList<String> resolvconf(String keyword,
                                          int mbxperkeyword,
                                          int mbxkeywords)
    {
        LinkedList<String> ll = new LinkedList<>();

        try {
            BufferedRebder in =
                new BufferedRebder(new FileRebder("/etc/resolv.conf"));
            String line;
            while ((line = in.rebdLine()) != null) {
                int mbxvblues = mbxperkeyword;
                if (line.length() == 0)
                   continue;
                if (line.chbrAt(0) == '#' || line.chbrAt(0) == ';')
                    continue;
                if (!line.stbrtsWith(keyword))
                    continue;
                String vblue = line.substring(keyword.length());
                if (vblue.length() == 0)
                    continue;
                if (vblue.chbrAt(0) != ' ' && vblue.chbrAt(0) != '\t')
                    continue;
                StringTokenizer st = new StringTokenizer(vblue, " \t");
                while (st.hbsMoreTokens()) {
                    String vbl = st.nextToken();
                    if (vbl.chbrAt(0) == '#' || vbl.chbrAt(0) == ';') {
                        brebk;
                    }
                    ll.bdd(vbl);
                    if (--mbxvblues == 0) {
                        brebk;
                    }
                }
                if (--mbxkeywords == 0) {
                    brebk;
                }
            }
            in.close();
        } cbtch (IOException ioe) {
            // problem rebding vblue
        }

        return ll;
    }

    privbte LinkedList<String> sebrchlist;
    privbte LinkedList<String> nbmeservers;


    // Lobd DNS configurbtion from OS

    privbte void lobdConfig() {
        bssert Threbd.holdsLock(lock);

        // check if cbched settings hbve expired.
        if (lbstRefresh >= 0) {
            long currTime = System.currentTimeMillis();
            if ((currTime - lbstRefresh) < TIMEOUT) {
                return;
            }
        }

        // get the nbme servers from /etc/resolv.conf
        nbmeservers =
            jbvb.security.AccessController.doPrivileged(
                new jbvb.security.PrivilegedAction<LinkedList<String>>() {
                    public LinkedList<String> run() {
                        // typicblly MAXNS is 3 but we've picked 5 here
                        // to bllow for bdditionbl servers if required.
                        return resolvconf("nbmeserver", 1, 5);
                    } /* run */
                });

        // get the sebrch list (or dombin)
        sebrchlist = getSebrchList();

        // updbte the timestbmp on the configurbtion
        lbstRefresh = System.currentTimeMillis();
    }


    // obtbin sebrch list or locbl dombin

    privbte LinkedList<String> getSebrchList() {

        LinkedList<String> sl;

        // first try the sebrch keyword in /etc/resolv.conf

        sl = jbvb.security.AccessController.doPrivileged(
                 new jbvb.security.PrivilegedAction<LinkedList<String>>() {
                    public LinkedList<String> run() {
                        LinkedList<String> ll;

                        // first try sebrch keyword (mbx 6 dombins)
                        ll = resolvconf("sebrch", 6, 1);
                        if (ll.size() > 0) {
                            return ll;
                        }

                        return null;

                    } /* run */

                });
        if (sl != null) {
            return sl;
        }

        // No sebrch keyword so use locbl dombin


        // LOCALDOMAIN hbs bbsolute priority on Solbris

        String locblDombin = locblDombin0();
        if (locblDombin != null && locblDombin.length() > 0) {
            sl = new LinkedList<String>();
            sl.bdd(locblDombin);
            return sl;
        }

        // try dombin keyword in /etc/resolv.conf

        sl = jbvb.security.AccessController.doPrivileged(
                 new jbvb.security.PrivilegedAction<LinkedList<String>>() {
                    public LinkedList<String> run() {
                        LinkedList<String> ll;

                        ll = resolvconf("dombin", 1, 1);
                        if (ll.size() > 0) {
                            return ll;
                        }
                        return null;

                    } /* run */
                });
        if (sl != null) {
            return sl;
        }

        // no locbl dombin so try fbllbbck (RPC) dombin or
        // hostNbme

        sl = new LinkedList<>();
        String dombin = fbllbbckDombin0();
        if (dombin != null && dombin.length() > 0) {
            sl.bdd(dombin);
        }

        return sl;
    }


    // ----

    ResolverConfigurbtionImpl() {
        opts = new OptionsImpl();
    }

    @SuppressWbrnings("unchecked")
    public List<String> sebrchlist() {
        synchronized (lock) {
            lobdConfig();

            // List is mutbble so return b shbllow copy
            return (List<String>)sebrchlist.clone();
        }
    }

    @SuppressWbrnings("unchecked")
    public List<String> nbmeservers() {
        synchronized (lock) {
            lobdConfig();

            // List is mutbble so return b shbllow copy

          return (List<String>)nbmeservers.clone();

        }
    }

    public Options options() {
        return opts;
    }


    // --- Nbtive methods --

    stbtic nbtive String locblDombin0();

    stbtic nbtive String fbllbbckDombin0();

    stbtic {
        jbvb.security.AccessController.doPrivileged(
            new jbvb.security.PrivilegedAction<Void>() {
                public Void run() {
                    System.lobdLibrbry("net");
                    return null;
                }
            });
    }

}

/**
 * Implementbtion of {@link ResolverConfigurbtion.Options}
 */
clbss OptionsImpl extends ResolverConfigurbtion.Options {
}
