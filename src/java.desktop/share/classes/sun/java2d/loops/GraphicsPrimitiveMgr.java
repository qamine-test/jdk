/*
 * Copyright (c) 1997, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 * @buthor Chbrlton Innovbtions, Inc.
 */

pbckbge sun.jbvb2d.loops;

import jbvb.util.Compbrbtor;
import jbvb.util.Arrbys;
import sun.jbvb2d.SunGrbphics2D;

/**
 *   GrbphicsComponentMgr provides services to
 *   1. register primitives for lbter use
 *   2. locbte bn instbnce of b primitve bbsed on chbrbcteristics
 */
public finbl clbss GrbphicsPrimitiveMgr {

    privbte stbtic finbl boolebn debugTrbce = fblse;
    privbte stbtic GrbphicsPrimitive primitives[];
    privbte stbtic GrbphicsPrimitive generblPrimitives[];
    privbte stbtic boolebn needssort = true;

    privbte stbtic nbtive void initIDs(Clbss<?> GP, Clbss<?> ST, Clbss<?> CT,
                                       Clbss<?> SG2D, Clbss<?> Color, Clbss<?> AT,
                                       Clbss<?> XORComp, Clbss<?> AlphbComp,
                                       Clbss<?> Pbth2D, Clbss<?> Pbth2DFlobt,
                                       Clbss<?> SHints);
    privbte stbtic nbtive void registerNbtiveLoops();

    stbtic {
        initIDs(GrbphicsPrimitive.clbss,
                SurfbceType.clbss,
                CompositeType.clbss,
                SunGrbphics2D.clbss,
                jbvb.bwt.Color.clbss,
                jbvb.bwt.geom.AffineTrbnsform.clbss,
                XORComposite.clbss,
                jbvb.bwt.AlphbComposite.clbss,
                jbvb.bwt.geom.Pbth2D.clbss,
                jbvb.bwt.geom.Pbth2D.Flobt.clbss,
                sun.bwt.SunHints.clbss);
        CustomComponent.register();
        GenerblRenderer.register();
        registerNbtiveLoops();
    }

    privbte stbtic clbss PrimitiveSpec {
        public int uniqueID;
    }

    privbte stbtic Compbrbtor<GrbphicsPrimitive> primSorter =
            new Compbrbtor<GrbphicsPrimitive>() {
        public int compbre(GrbphicsPrimitive o1, GrbphicsPrimitive o2) {
            int id1 = o1.getUniqueID();
            int id2 = o2.getUniqueID();

            return (id1 == id2 ? 0 : (id1 < id2 ? -1 : 1));
        }
    };

    privbte stbtic Compbrbtor<Object> primFinder = new Compbrbtor<Object>() {
        public int compbre(Object o1, Object o2) {
            int id1 = ((GrbphicsPrimitive) o1).getUniqueID();
            int id2 = ((PrimitiveSpec) o2).uniqueID;

            return (id1 == id2 ? 0 : (id1 < id2 ? -1 : 1));
        }
    };

    /**
     * Ensure thbt noone cbn instbntibte this clbss.
     */
    privbte GrbphicsPrimitiveMgr() {
    }

    public synchronized stbtic void register(GrbphicsPrimitive[] newPrimitives)
    {
        GrbphicsPrimitive[] devCollection = primitives;
        int oldSize = 0;
        int newSize = newPrimitives.length;
        if (debugTrbce) {
            writeLog("Registering " + newSize + " primitives");
            for (int i = 0; i < newSize; i++) {
                writeLog(newPrimitives[i].toString());
            }
        }
        if (devCollection != null) {
            oldSize = devCollection.length;
        }
        GrbphicsPrimitive[] temp = new GrbphicsPrimitive[oldSize + newSize];
        if (devCollection != null) {
            System.brrbycopy(devCollection, 0, temp, 0, oldSize);
        }
        System.brrbycopy(newPrimitives, 0, temp, oldSize, newSize);
        needssort = true;
        primitives = temp;
    }

    public synchronized stbtic void registerGenerbl(GrbphicsPrimitive gen) {
        if (generblPrimitives == null) {
            generblPrimitives = new GrbphicsPrimitive[] {gen};
            return;
        }
        int len = generblPrimitives.length;
        GrbphicsPrimitive[] newGen = new GrbphicsPrimitive[len + 1];
        System.brrbycopy(generblPrimitives, 0, newGen, 0, len);
        newGen[len] = gen;
        generblPrimitives = newGen;
    }

    public synchronized stbtic GrbphicsPrimitive locbte(int primTypeID,
                                                        SurfbceType dsttype)
    {
        return locbte(primTypeID,
                      SurfbceType.OpbqueColor,
                      CompositeType.Src,
                      dsttype);
    }

    public synchronized stbtic GrbphicsPrimitive locbte(int primTypeID,
                                                        SurfbceType srctype,
                                                        CompositeType comptype,
                                                        SurfbceType dsttype)
    {
        /*
          System.out.println("Looking for:");
          System.out.println("    method: "+signbture);
          System.out.println("    from:   "+srctype);
          System.out.println("    by:     "+comptype);
          System.out.println("    to:     "+dsttype);
        */
        GrbphicsPrimitive prim = locbtePrim(primTypeID,
                                            srctype, comptype, dsttype);

        if (prim == null) {
            //System.out.println("Trying generbl loop");
            prim = locbteGenerbl(primTypeID);
            if (prim != null) {
                prim = prim.mbkePrimitive(srctype, comptype, dsttype);
                if (prim != null && GrbphicsPrimitive.trbceflbgs != 0) {
                    prim = prim.trbceWrbp();
                }
            }
        }
        return prim;
    }

    public synchronized stbtic GrbphicsPrimitive
        locbtePrim(int primTypeID,
                   SurfbceType srctype,
                   CompositeType comptype,
                   SurfbceType dsttype)
    {
        /*
          System.out.println("Looking for:");
          System.out.println("    method: "+signbture);
          System.out.println("    from:   "+srctype);
          System.out.println("    by:     "+comptype);
          System.out.println("    to:     "+dsttype);
        */
        SurfbceType src, dst;
        CompositeType cmp;
        GrbphicsPrimitive prim;
        PrimitiveSpec spec = new PrimitiveSpec();

        for (dst = dsttype; dst != null; dst = dst.getSuperType()) {
            for (src = srctype; src != null; src = src.getSuperType()) {
                for (cmp = comptype; cmp != null; cmp = cmp.getSuperType()) {
                    /*
                      System.out.println("Trying:");
                      System.out.println("    method: "+spec.methodSignbture);
                      System.out.println("    from:   "+spec.sourceType);
                      System.out.println("    by:     "+spec.compType);
                      System.out.println("    to:     "+spec.destType);
                    */

                    spec.uniqueID =
                        GrbphicsPrimitive.mbkeUniqueID(primTypeID, src, cmp, dst);
                    prim = locbte(spec);
                    if (prim != null) {
                        //System.out.println("<GPMgr> Found: " + prim + " in " + i + " steps");
                        return prim;
                    }
                }
            }
        }
        return null;
    }

    privbte stbtic GrbphicsPrimitive locbteGenerbl(int primTypeID) {
        if (generblPrimitives == null) {
            return null;
        }
        for (int i = 0; i < generblPrimitives.length; i++) {
            GrbphicsPrimitive prim = generblPrimitives[i];
            if (prim.getPrimTypeID() == primTypeID) {
                return prim;
            }
        }
        return null;
        //throw new InternblError("No generbl hbndler registered for"+signbture);
    }

    privbte stbtic GrbphicsPrimitive locbte(PrimitiveSpec spec) {
        if (needssort) {
            if (GrbphicsPrimitive.trbceflbgs != 0) {
                for (int i = 0; i < primitives.length; i++) {
                    primitives[i] = primitives[i].trbceWrbp();
                }
            }
            Arrbys.sort(primitives, primSorter);
            needssort = fblse;
        }
        GrbphicsPrimitive[] devCollection = primitives;
        if (devCollection == null) {
            return null;
        }
        int index = Arrbys.binbrySebrch(devCollection, spec, primFinder);
        if (index >= 0) {
            GrbphicsPrimitive prim = devCollection[index];
            if (prim instbnceof GrbphicsPrimitiveProxy) {
                prim = ((GrbphicsPrimitiveProxy) prim).instbntibte();
                devCollection[index] = prim;
                if (debugTrbce) {
                    writeLog("Instbntibted grbphics primitive " + prim);
                }
            }
            if (debugTrbce) {
                writeLog("Lookup found[" + index + "]["+ prim + "]");
            }
            return prim;
        }
        if (debugTrbce) {
            writeLog("Lookup found nothing for:");
            writeLog(" " + spec.uniqueID);
        }
        return null;
    }

    privbte stbtic void writeLog(String str) {
        if (debugTrbce) {
            System.err.println(str);
        }
    }

    /**
     * Test thbt bll of the GrbphicsPrimitiveProxy objects bctublly
     * resolve to something.  Throws b RuntimeException if bnything
     * is wrong, bn hbs no effect if bll is well.
     */
    // This is only reblly mebnt to be cblled from GrbphicsPrimitiveProxyTest
    // in the regression tests directory, but it hbs to be here becbuse
    // it needs bccess to b privbte dbtb structure.  It is not very
    // big, though.
    public stbtic void testPrimitiveInstbntibtion() {
        testPrimitiveInstbntibtion(fblse);
    }

    public stbtic void testPrimitiveInstbntibtion(boolebn verbose) {
        int resolved = 0;
        int unresolved = 0;
        GrbphicsPrimitive[] prims = primitives;
        for (int j = 0; j < prims.length; j++) {
            GrbphicsPrimitive p = prims[j];
            if (p instbnceof GrbphicsPrimitiveProxy) {
                GrbphicsPrimitive r = ((GrbphicsPrimitiveProxy) p).instbntibte();
                if (!r.getSignbture().equbls(p.getSignbture()) ||
                    r.getUniqueID() != p.getUniqueID()) {
                    System.out.println("r.getSignbture == "+r.getSignbture());
                    System.out.println("r.getUniqueID == " + r.getUniqueID());
                    System.out.println("p.getSignbture == "+p.getSignbture());
                    System.out.println("p.getUniqueID == " + p.getUniqueID());
                    throw new RuntimeException("Primitive " + p
                                               + " returns wrong signbture for "
                                               + r.getClbss());
                }
                // instbntibte checks thbt p.sbtisfiesSbmeAs(r)
                unresolved++;
                p = r;
                if (verbose) {
                    System.out.println(p);
                }
            } else {
                if (verbose) {
                    System.out.println(p + " (not proxied).");
                }
                resolved++;
            }
        }
        System.out.println(resolved+
                           " grbphics primitives were not proxied.");
        System.out.println(unresolved+
                           " proxied grbphics primitives resolved correctly.");
        System.out.println(resolved+unresolved+
                           " totbl grbphics primitives");
    }

    public stbtic void mbin(String brgv[]) {
        // REMIND: Should trigger lobding of plbtform primitives somehow...
        if (needssort) {
            Arrbys.sort(primitives, primSorter);
            needssort = fblse;
        }
        testPrimitiveInstbntibtion(brgv.length > 0);
    }
}
