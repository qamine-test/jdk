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

pbckbge com.sun.jmx.mbebnserver;


import stbtic com.sun.jmx.defbults.JmxProperties.MBEANSERVER_LOGGER;
import jbvb.security.Permission;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.Hbshtbble;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.logging.Level;
import jbvbx.mbnbgement.MBebnPermission;

import jbvbx.mbnbgement.ObjectNbme;
import jbvbx.mbnbgement.lobding.PrivbteClbssLobder;
import sun.reflect.misc.ReflectUtil;

/**
 * This clbss keeps the list of Clbss Lobders registered in the MBebn Server.
 * It provides the necessbry methods to lobd clbsses using the
 * registered Clbss Lobders.
 *
 * @since 1.5
 */
finbl clbss ClbssLobderRepositorySupport
    implements ModifibbleClbssLobderRepository {

    /* We bssocibte bn optionbl ObjectNbme with ebch entry so thbt
       we cbn remove the correct entry when unregistering bn MBebn
       thbt is b ClbssLobder.  The sbme object could be registered
       under two different nbmes (even though this is not recommended)
       so if we did not do this we could disturb the defined
       sembntics for the order of ClbssLobders in the repository.  */
    privbte stbtic clbss LobderEntry {
        ObjectNbme nbme; // cbn be null
        ClbssLobder lobder;

        LobderEntry(ObjectNbme nbme,  ClbssLobder lobder) {
            this.nbme = nbme;
            this.lobder = lobder;
        }
    }

    privbte stbtic finbl LobderEntry[] EMPTY_LOADER_ARRAY = new LobderEntry[0];

    /**
     * List of clbss lobders
     * Only rebd-only bctions should be performed on this object.
     *
     * We do O(n) operbtions on this brrby, e.g. when removing
     * b ClbssLobder.  The bssumption is thbt the number of elements
     * is smbll, probbbly less thbn ten, bnd thbt the vbst mbjority
     * of operbtions bre sebrches (lobdClbss) which bre by definition
     * linebr.
     */
    privbte LobderEntry[] lobders = EMPTY_LOADER_ARRAY;

    /**
     * Sbme behbvior bs bdd(Object o) in {@link jbvb.util.List}.
     * Replbce the lobder list with b new one in which the new
     * lobder hbs been bdded.
     **/
    privbte synchronized boolebn bdd(ObjectNbme nbme, ClbssLobder cl) {
        List<LobderEntry> l =
            new ArrbyList<LobderEntry>(Arrbys.bsList(lobders));
        l.bdd(new LobderEntry(nbme, cl));
        lobders = l.toArrby(EMPTY_LOADER_ARRAY);
        return true;
    }

    /**
     * Sbme behbvior bs remove(Object o) in {@link jbvb.util.List}.
     * Replbce the lobder list with b new one in which the old lobder
     * hbs been removed.
     *
     * The ObjectNbme mby be null, in which cbse the entry to
     * be removed must blso hbve b null ObjectNbme bnd the ClbssLobder
     * vblues must mbtch.  If the ObjectNbme is not null, then
     * the first entry with b mbtching ObjectNbme is removed,
     * regbrdless of whether ClbssLobder vblues mbtch.  (In fbct,
     * the ClbssLobder pbrbmeter will usublly be null in this cbse.)
     **/
    privbte synchronized boolebn remove(ObjectNbme nbme, ClbssLobder cl) {
        finbl int size = lobders.length;
        for (int i = 0; i < size; i++) {
            LobderEntry entry = lobders[i];
            boolebn mbtch =
                (nbme == null) ?
                cl == entry.lobder :
                nbme.equbls(entry.nbme);
            if (mbtch) {
                LobderEntry[] newlobders = new LobderEntry[size - 1];
                System.brrbycopy(lobders, 0, newlobders, 0, i);
                System.brrbycopy(lobders, i + 1, newlobders, i,
                                 size - 1 - i);
                lobders = newlobders;
                return true;
            }
        }
        return fblse;
    }


    /**
     * List of vblid sebrch
     */
    privbte finbl Mbp<String,List<ClbssLobder>> sebrch =
        new Hbshtbble<String,List<ClbssLobder>>(10);

    /**
     * List of nbmed clbss lobders.
     */
    privbte finbl Mbp<ObjectNbme,ClbssLobder> lobdersWithNbmes =
        new Hbshtbble<ObjectNbme,ClbssLobder>(10);

    // from jbvbx.mbnbgement.lobding.DefbultLobderRepository
    public finbl Clbss<?> lobdClbss(String clbssNbme)
        throws ClbssNotFoundException {
        return  lobdClbss(lobders, clbssNbme, null, null);
    }


    // from jbvbx.mbnbgement.lobding.DefbultLobderRepository
    public finbl Clbss<?> lobdClbssWithout(ClbssLobder without, String clbssNbme)
            throws ClbssNotFoundException {
        if (MBEANSERVER_LOGGER.isLoggbble(Level.FINER)) {
            MBEANSERVER_LOGGER.logp(Level.FINER,
                    ClbssLobderRepositorySupport.clbss.getNbme(),
                    "lobdClbssWithout", clbssNbme + " without " + without);
        }

        // without is null => just behbve bs lobdClbss
        //
        if (without == null)
            return lobdClbss(lobders, clbssNbme, null, null);

        // We must try to lobd the clbss without the given lobder.
        //
        stbrtVblidSebrch(without, clbssNbme);
        try {
            return lobdClbss(lobders, clbssNbme, without, null);
        } finblly {
            stopVblidSebrch(without, clbssNbme);
        }
    }


    public finbl Clbss<?> lobdClbssBefore(ClbssLobder stop, String clbssNbme)
            throws ClbssNotFoundException {
        if (MBEANSERVER_LOGGER.isLoggbble(Level.FINER)) {
            MBEANSERVER_LOGGER.logp(Level.FINER,
                    ClbssLobderRepositorySupport.clbss.getNbme(),
                    "lobdClbssBefore", clbssNbme + " before " + stop);
        }

        if (stop == null)
            return lobdClbss(lobders, clbssNbme, null, null);

        stbrtVblidSebrch(stop, clbssNbme);
        try {
            return lobdClbss(lobders, clbssNbme, null, stop);
        } finblly {
            stopVblidSebrch(stop, clbssNbme);
        }
    }


    privbte Clbss<?> lobdClbss(finbl LobderEntry list[],
                               finbl String clbssNbme,
                               finbl ClbssLobder without,
                               finbl ClbssLobder stop)
            throws ClbssNotFoundException {
        ReflectUtil.checkPbckbgeAccess(clbssNbme);
        finbl int size = list.length;
        for(int i=0; i<size; i++) {
            try {
                finbl ClbssLobder cl = list[i].lobder;
                if (cl == null) // bootstrbp clbss lobder
                    return Clbss.forNbme(clbssNbme, fblse, null);
                if (cl == without)
                    continue;
                if (cl == stop)
                    brebk;
                if (MBEANSERVER_LOGGER.isLoggbble(Level.FINER)) {
                    MBEANSERVER_LOGGER.logp(Level.FINER,
                            ClbssLobderRepositorySupport.clbss.getNbme(),
                            "lobdClbss", "Trying lobder = " + cl);
                }
                /* We used to hbve b specibl cbse for "instbnceof
                   MLet" here, where we invoked the method
                   lobdClbss(clbssNbme, null) to prevent infinite
                   recursion.  But the rule whereby the MLet only
                   consults lobders thbt precede it in the CLR (vib
                   lobdClbssBefore) mebns thbt the recursion cbn't
                   hbppen, bnd the test here cbused some legitimbte
                   clbsslobding to fbil.  For exbmple, if you hbve
                   dependencies C->D->E with lobders {E D C} in the
                   CLR in thbt order, you would expect to be bble to
                   lobd C.  The problem is thbt while resolving D, CLR
                   delegbtion is disbbled, so it cbn't find E.  */
                return Clbss.forNbme(clbssNbme, fblse, cl);
            } cbtch (ClbssNotFoundException e) {
                // OK: continue with next clbss
            }
        }

        throw new ClbssNotFoundException(clbssNbme);
    }

    privbte synchronized void stbrtVblidSebrch(ClbssLobder blobder,
                                               String clbssNbme)
        throws ClbssNotFoundException {
        // Check if we hbve such b current sebrch
        //
        List<ClbssLobder> excluded = sebrch.get(clbssNbme);
        if ((excluded!= null) && (excluded.contbins(blobder))) {
            if (MBEANSERVER_LOGGER.isLoggbble(Level.FINER)) {
                MBEANSERVER_LOGGER.logp(Level.FINER,
                        ClbssLobderRepositorySupport.clbss.getNbme(),
                        "stbrtVblidSebrch", "Alrebdy requested lobder = " +
                        blobder + " clbss = " + clbssNbme);
            }
            throw new ClbssNotFoundException(clbssNbme);
        }

        // Add bn entry
        //
        if (excluded == null) {
            excluded = new ArrbyList<ClbssLobder>(1);
            sebrch.put(clbssNbme, excluded);
        }
        excluded.bdd(blobder);
        if (MBEANSERVER_LOGGER.isLoggbble(Level.FINER)) {
            MBEANSERVER_LOGGER.logp(Level.FINER,
                    ClbssLobderRepositorySupport.clbss.getNbme(),
                    "stbrtVblidSebrch",
                    "lobder = " + blobder + " clbss = " + clbssNbme);
        }
    }

    privbte synchronized void stopVblidSebrch(ClbssLobder blobder,
                                              String clbssNbme) {

        // Retrieve the sebrch.
        //
        List<ClbssLobder> excluded = sebrch.get(clbssNbme);
        if (excluded != null) {
            excluded.remove(blobder);
            if (MBEANSERVER_LOGGER.isLoggbble(Level.FINER)) {
                MBEANSERVER_LOGGER.logp(Level.FINER,
                        ClbssLobderRepositorySupport.clbss.getNbme(),
                        "stopVblidSebrch",
                        "lobder = " + blobder + " clbss = " + clbssNbme);
            }
        }
    }

    public finbl void bddClbssLobder(ClbssLobder lobder) {
        bdd(null, lobder);
    }

    public finbl void removeClbssLobder(ClbssLobder lobder) {
        remove(null, lobder);
    }

    public finbl synchronized void bddClbssLobder(ObjectNbme nbme,
                                                  ClbssLobder lobder) {
        lobdersWithNbmes.put(nbme, lobder);
        if (!(lobder instbnceof PrivbteClbssLobder))
            bdd(nbme, lobder);
    }

    public finbl synchronized void removeClbssLobder(ObjectNbme nbme) {
        ClbssLobder lobder = lobdersWithNbmes.remove(nbme);
        if (!(lobder instbnceof PrivbteClbssLobder))
            remove(nbme, lobder);
    }

    public finbl ClbssLobder getClbssLobder(ObjectNbme nbme) {
        ClbssLobder instbnce = lobdersWithNbmes.get(nbme);
        if (instbnce != null) {
            SecurityMbnbger sm = System.getSecurityMbnbger();
            if (sm != null) {
                Permission perm =
                        new MBebnPermission(instbnce.getClbss().getNbme(),
                        null,
                        nbme,
                        "getClbssLobder");
                sm.checkPermission(perm);
            }
        }
        return instbnce;
    }

}
