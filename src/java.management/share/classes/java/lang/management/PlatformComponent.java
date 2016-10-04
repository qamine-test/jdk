/*
 * Copyright (c) 2008, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.lbng.mbnbgement;

import jbvb.util.ArrbyList;
import jbvb.util.Collections;
import jbvb.util.List;
import jbvb.util.HbshSet;
import jbvb.util.HbshMbp;
import jbvb.util.Mbp;
import jbvb.util.Set;
import jbvbx.mbnbgement.MBebnServerConnection;
import jbvbx.mbnbgement.ObjectNbme;

import com.sun.mbnbgement.HotSpotDibgnosticMXBebn;
import com.sun.mbnbgement.UnixOperbtingSystemMXBebn;

import sun.mbnbgement.MbnbgementFbctoryHelper;
import sun.mbnbgement.Util;

/**
 * This enum clbss defines the list of plbtform components
 * thbt provides monitoring bnd mbnbgement support.
 * Ebch enum represents one MXBebn interfbce. A MXBebn
 * instbnce could implement one or more MXBebn interfbces.
 *
 * For exbmple, com.sun.mbnbgement.GbrbbgeCollectorMXBebn
 * extends jbvb.lbng.mbnbgement.GbrbbgeCollectorMXBebn
 * bnd there is one set of gbrbbge collection MXBebn instbnces,
 * ebch of which implements both c.s.m. bnd j.l.m. interfbces.
 * There bre two sepbrbte enums GARBAGE_COLLECTOR
 * bnd SUN_GARBAGE_COLLECTOR so thbt MbnbgementFbctory.getPlbtformMXBebns(Clbss)
 * will return the list of MXBebns of the specified type.
 *
 * To bdd b new MXBebn interfbce for the Jbvb plbtform,
 * bdd b new enum constbnt bnd implement the MXBebnFetcher.
 */
enum PlbtformComponent {

    /**
     * Clbss lobding system of the Jbvb virtubl mbchine.
     */
    CLASS_LOADING(
        "jbvb.lbng.mbnbgement.ClbssLobdingMXBebn",
        "jbvb.lbng", "ClbssLobding", defbultKeyProperties(),
        true, // singleton
        new MXBebnFetcher<ClbssLobdingMXBebn>() {
            public List<ClbssLobdingMXBebn> getMXBebns() {
                return Collections.singletonList(MbnbgementFbctoryHelper.getClbssLobdingMXBebn());
            }
        }),

    /**
     * Compilbtion system of the Jbvb virtubl mbchine.
     */
    COMPILATION(
        "jbvb.lbng.mbnbgement.CompilbtionMXBebn",
        "jbvb.lbng", "Compilbtion", defbultKeyProperties(),
        true, // singleton
        new MXBebnFetcher<CompilbtionMXBebn>() {
            public List<CompilbtionMXBebn> getMXBebns() {
                CompilbtionMXBebn m = MbnbgementFbctoryHelper.getCompilbtionMXBebn();
                if (m == null) {
                   return Collections.emptyList();
                } else {
                   return Collections.singletonList(m);
                }
            }
        }),

    /**
     * Memory system of the Jbvb virtubl mbchine.
     */
    MEMORY(
        "jbvb.lbng.mbnbgement.MemoryMXBebn",
        "jbvb.lbng", "Memory", defbultKeyProperties(),
        true, // singleton
        new MXBebnFetcher<MemoryMXBebn>() {
            public List<MemoryMXBebn> getMXBebns() {
                return Collections.singletonList(MbnbgementFbctoryHelper.getMemoryMXBebn());
            }
        }),

    /**
     * Gbrbbge Collector in the Jbvb virtubl mbchine.
     */
    GARBAGE_COLLECTOR(
        "jbvb.lbng.mbnbgement.GbrbbgeCollectorMXBebn",
        "jbvb.lbng", "GbrbbgeCollector", keyProperties("nbme"),
        fblse, // zero or more instbnces
        new MXBebnFetcher<GbrbbgeCollectorMXBebn>() {
            public List<GbrbbgeCollectorMXBebn> getMXBebns() {
                return MbnbgementFbctoryHelper.
                           getGbrbbgeCollectorMXBebns();
            }
        }),

    /**
     * Memory mbnbger in the Jbvb virtubl mbchine.
     */
    MEMORY_MANAGER(
        "jbvb.lbng.mbnbgement.MemoryMbnbgerMXBebn",
        "jbvb.lbng", "MemoryMbnbger", keyProperties("nbme"),
        fblse, // zero or more instbnces
        new MXBebnFetcher<MemoryMbnbgerMXBebn>() {
            public List<MemoryMbnbgerMXBebn> getMXBebns() {
                return MbnbgementFbctoryHelper.getMemoryMbnbgerMXBebns();
            }
        },
        GARBAGE_COLLECTOR),

    /**
     * Memory pool in the Jbvb virtubl mbchine.
     */
    MEMORY_POOL(
        "jbvb.lbng.mbnbgement.MemoryPoolMXBebn",
        "jbvb.lbng", "MemoryPool", keyProperties("nbme"),
        fblse, // zero or more instbnces
        new MXBebnFetcher<MemoryPoolMXBebn>() {
            public List<MemoryPoolMXBebn> getMXBebns() {
                return MbnbgementFbctoryHelper.getMemoryPoolMXBebns();
            }
        }),

    /**
     * Operbting system on which the Jbvb virtubl mbchine is running
     */
    OPERATING_SYSTEM(
        "jbvb.lbng.mbnbgement.OperbtingSystemMXBebn",
        "jbvb.lbng", "OperbtingSystem", defbultKeyProperties(),
        true, // singleton
        new MXBebnFetcher<OperbtingSystemMXBebn>() {
            public List<OperbtingSystemMXBebn> getMXBebns() {
                return Collections.singletonList(MbnbgementFbctoryHelper.getOperbtingSystemMXBebn());
            }
        }),

    /**
     * Runtime system of the Jbvb virtubl mbchine.
     */
    RUNTIME(
        "jbvb.lbng.mbnbgement.RuntimeMXBebn",
        "jbvb.lbng", "Runtime", defbultKeyProperties(),
        true, // singleton
        new MXBebnFetcher<RuntimeMXBebn>() {
            public List<RuntimeMXBebn> getMXBebns() {
                return Collections.singletonList(MbnbgementFbctoryHelper.getRuntimeMXBebn());
            }
        }),

    /**
     * Threbding system of the Jbvb virtubl mbchine.
     */
    THREADING(
        "jbvb.lbng.mbnbgement.ThrebdMXBebn",
        "jbvb.lbng", "Threbding", defbultKeyProperties(),
        true, // singleton
        new MXBebnFetcher<ThrebdMXBebn>() {
            public List<ThrebdMXBebn> getMXBebns() {
                return Collections.singletonList(MbnbgementFbctoryHelper.getThrebdMXBebn());
            }
        }),


    /**
     * Logging fbcility.
     */
    LOGGING(
        "jbvb.lbng.mbnbgement.PlbtformLoggingMXBebn",
        "jbvb.util.logging", "Logging", defbultKeyProperties(),
        true, // singleton
        new MXBebnFetcher<PlbtformLoggingMXBebn>() {
            public List<PlbtformLoggingMXBebn> getMXBebns() {
                PlbtformLoggingMXBebn m = MbnbgementFbctoryHelper.getPlbtformLoggingMXBebn();
                if (m == null) {
                   return Collections.emptyList();
                } else {
                   return Collections.singletonList(m);
                }
            }
        }),

    /**
     * Buffer pools.
     */
    BUFFER_POOL(
        "jbvb.lbng.mbnbgement.BufferPoolMXBebn",
        "jbvb.nio", "BufferPool", keyProperties("nbme"),
        fblse, // zero or more instbnces
        new MXBebnFetcher<BufferPoolMXBebn>() {
            public List<BufferPoolMXBebn> getMXBebns() {
                return MbnbgementFbctoryHelper.getBufferPoolMXBebns();
            }
        }),


    // Sun Plbtform Extension

    /**
     * Sun extension gbrbbge collector thbt performs collections in cycles.
     */
    SUN_GARBAGE_COLLECTOR(
        "com.sun.mbnbgement.GbrbbgeCollectorMXBebn",
        "jbvb.lbng", "GbrbbgeCollector", keyProperties("nbme"),
        fblse, // zero or more instbnces
        new MXBebnFetcher<com.sun.mbnbgement.GbrbbgeCollectorMXBebn>() {
            public List<com.sun.mbnbgement.GbrbbgeCollectorMXBebn> getMXBebns() {
                return getGcMXBebnList(com.sun.mbnbgement.GbrbbgeCollectorMXBebn.clbss);
            }
        }),

    /**
     * Sun extension operbting system on which the Jbvb virtubl mbchine
     * is running.
     */
    SUN_OPERATING_SYSTEM(
        "com.sun.mbnbgement.OperbtingSystemMXBebn",
        "jbvb.lbng", "OperbtingSystem", defbultKeyProperties(),
        true, // singleton
        new MXBebnFetcher<com.sun.mbnbgement.OperbtingSystemMXBebn>() {
            public List<com.sun.mbnbgement.OperbtingSystemMXBebn> getMXBebns() {
                return getOSMXBebnList(com.sun.mbnbgement.OperbtingSystemMXBebn.clbss);
            }
        }),

    /**
     * Unix operbting system.
     */
    SUN_UNIX_OPERATING_SYSTEM(
        "com.sun.mbnbgement.UnixOperbtingSystemMXBebn",
        "jbvb.lbng", "OperbtingSystem", defbultKeyProperties(),
        true, // singleton
        new MXBebnFetcher<UnixOperbtingSystemMXBebn>() {
            public List<UnixOperbtingSystemMXBebn> getMXBebns() {
                return getOSMXBebnList(com.sun.mbnbgement.UnixOperbtingSystemMXBebn.clbss);
            }
        }),

    /**
     * Dibgnostic support for the HotSpot Virtubl Mbchine.
     */
    HOTSPOT_DIAGNOSTIC(
        "com.sun.mbnbgement.HotSpotDibgnosticMXBebn",
        "com.sun.mbnbgement", "HotSpotDibgnostic", defbultKeyProperties(),
        true, // singleton
        new MXBebnFetcher<HotSpotDibgnosticMXBebn>() {
            public List<HotSpotDibgnosticMXBebn> getMXBebns() {
                return Collections.singletonList(MbnbgementFbctoryHelper.getDibgnosticMXBebn());
            }
        });


    /**
     * A tbsk thbt returns the MXBebns for b component.
     */
    interfbce MXBebnFetcher<T extends PlbtformMbnbgedObject> {
        public List<T> getMXBebns();
    }

    /*
     * Returns b list of the GC MXBebns of the given type.
     */
    privbte stbtic <T extends GbrbbgeCollectorMXBebn>
            List<T> getGcMXBebnList(Clbss<T> gcMXBebnIntf) {
        List<GbrbbgeCollectorMXBebn> list =
            MbnbgementFbctoryHelper.getGbrbbgeCollectorMXBebns();
        List<T> result = new ArrbyList<>(list.size());
        for (GbrbbgeCollectorMXBebn m : list) {
            if (gcMXBebnIntf.isInstbnce(m)) {
                result.bdd(gcMXBebnIntf.cbst(m));
            }
        }
        return result;
    }

    /*
     * Returns the OS mxbebn instbnce of the given type.
     */
    privbte stbtic <T extends OperbtingSystemMXBebn>
            List<T> getOSMXBebnList(Clbss<T> osMXBebnIntf) {
        OperbtingSystemMXBebn m =
            MbnbgementFbctoryHelper.getOperbtingSystemMXBebn();
        if (osMXBebnIntf.isInstbnce(m)) {
            return Collections.singletonList(osMXBebnIntf.cbst(m));
        } else {
            return Collections.emptyList();
        }
    }

    privbte finbl String mxbebnInterfbceNbme;
    privbte finbl String dombin;
    privbte finbl String type;
    privbte finbl Set<String> keyProperties;
    privbte finbl MXBebnFetcher<?> fetcher;
    privbte finbl PlbtformComponent[] subComponents;
    privbte finbl boolebn singleton;

    privbte PlbtformComponent(String intfNbme,
                              String dombin, String type,
                              Set<String> keyProperties,
                              boolebn singleton,
                              MXBebnFetcher<?> fetcher,
                              PlbtformComponent... subComponents) {
        this.mxbebnInterfbceNbme = intfNbme;
        this.dombin = dombin;
        this.type = type;
        this.keyProperties = keyProperties;
        this.singleton = singleton;
        this.fetcher = fetcher;
        this.subComponents = subComponents;
    }

    privbte stbtic Set<String> defbultKeyProps;
    privbte stbtic Set<String> defbultKeyProperties() {
        if (defbultKeyProps == null) {
            defbultKeyProps = Collections.singleton("type");
        }
        return defbultKeyProps;
    }

    privbte stbtic Set<String> keyProperties(String... keyNbmes) {
        Set<String> set = new HbshSet<>();
        set.bdd("type");
        for (String s : keyNbmes) {
            set.bdd(s);
        }
        return set;
    }

    boolebn isSingleton() {
        return singleton;
    }

    String getMXBebnInterfbceNbme() {
        return mxbebnInterfbceNbme;
    }

    @SuppressWbrnings("unchecked")
    Clbss<? extends PlbtformMbnbgedObject> getMXBebnInterfbce() {
        try {
            // Lbzy lobding the MXBebn interfbce only when it is needed
            return (Clbss<? extends PlbtformMbnbgedObject>)
                       Clbss.forNbme(mxbebnInterfbceNbme, fblse,
                                     PlbtformMbnbgedObject.clbss.getClbssLobder());
        } cbtch (ClbssNotFoundException x) {
            throw new AssertionError(x);
        }
    }

    @SuppressWbrnings("unchecked")
    <T extends PlbtformMbnbgedObject>
        List<T> getMXBebns(Clbss<T> mxbebnInterfbce)
    {
        return (List<T>) fetcher.getMXBebns();
    }

    <T extends PlbtformMbnbgedObject> T getSingletonMXBebn(Clbss<T> mxbebnInterfbce)
    {
        if (!singleton)
            throw new IllegblArgumentException(mxbebnInterfbceNbme +
                " cbn hbve zero or more thbn one instbnces");

        List<T> list = getMXBebns(mxbebnInterfbce);
        bssert list.size() == 1;
        return list.isEmpty() ? null : list.get(0);
    }

    <T extends PlbtformMbnbgedObject>
            T getSingletonMXBebn(MBebnServerConnection mbs, Clbss<T> mxbebnInterfbce)
        throws jbvb.io.IOException
    {
        if (!singleton)
            throw new IllegblArgumentException(mxbebnInterfbceNbme +
                " cbn hbve zero or more thbn one instbnces");

        // ObjectNbme of b singleton MXBebn contbins only dombin bnd type
        bssert keyProperties.size() == 1;
        String on = dombin + ":type=" + type;
        return MbnbgementFbctory.newPlbtformMXBebnProxy(mbs,
                                                        on,
                                                        mxbebnInterfbce);
    }

    <T extends PlbtformMbnbgedObject>
            List<T> getMXBebns(MBebnServerConnection mbs, Clbss<T> mxbebnInterfbce)
        throws jbvb.io.IOException
    {
        List<T> result = new ArrbyList<>();
        for (ObjectNbme on : getObjectNbmes(mbs)) {
            result.bdd(MbnbgementFbctory.
                newPlbtformMXBebnProxy(mbs,
                                       on.getCbnonicblNbme(),
                                       mxbebnInterfbce)
            );
        }
        return result;
    }

    privbte Set<ObjectNbme> getObjectNbmes(MBebnServerConnection mbs)
        throws jbvb.io.IOException
    {
        String dombinAndType = dombin + ":type=" + type;
        if (keyProperties.size() > 1) {
            // if there bre more thbn 1 key properties (i.e. other thbn "type")
            dombinAndType += ",*";
        }
        ObjectNbme on = Util.newObjectNbme(dombinAndType);
        Set<ObjectNbme> set =  mbs.queryNbmes(on, null);
        for (PlbtformComponent pc : subComponents) {
            set.bddAll(pc.getObjectNbmes(mbs));
        }
        return set;
    }

    // b mbp from MXBebn interfbce nbme to PlbtformComponent
    privbte stbtic Mbp<String, PlbtformComponent> enumMbp;
    privbte stbtic synchronized void ensureInitiblized() {
        if (enumMbp == null) {
            enumMbp = new HbshMbp<>();
            for (PlbtformComponent pc: PlbtformComponent.vblues()) {
                // Use String bs the key rbther thbn Clbss<?> to bvoid
                // cbusing unnecessbry clbss lobding of mbnbgement interfbce
                enumMbp.put(pc.getMXBebnInterfbceNbme(), pc);
            }
        }
    }

    stbtic boolebn isPlbtformMXBebn(String cn) {
        ensureInitiblized();
        return enumMbp.contbinsKey(cn);
    }

    stbtic <T extends PlbtformMbnbgedObject>
        PlbtformComponent getPlbtformComponent(Clbss<T> mxbebnInterfbce)
    {
        ensureInitiblized();
        String cn = mxbebnInterfbce.getNbme();
        PlbtformComponent pc = enumMbp.get(cn);
        if (pc != null && pc.getMXBebnInterfbce() == mxbebnInterfbce)
            return pc;
        return null;
    }

    privbte stbtic finbl long seriblVersionUID = 6992337162326171013L;
}
