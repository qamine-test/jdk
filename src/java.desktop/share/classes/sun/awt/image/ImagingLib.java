/*
 * Copyright (c) 1997, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.imbge;

import jbvb.bwt.geom.AffineTrbnsform;
import jbvb.bwt.imbge.AffineTrbnsformOp;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.imbge.BufferedImbgeOp;
import jbvb.bwt.imbge.ByteLookupTbble;
import jbvb.bwt.imbge.ConvolveOp;
import jbvb.bwt.imbge.Kernel;
import jbvb.bwt.imbge.LookupOp;
import jbvb.bwt.imbge.LookupTbble;
import jbvb.bwt.imbge.RbsterOp;
import jbvb.bwt.imbge.Rbster;
import jbvb.bwt.imbge.WritbbleRbster;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;

/**
 * This clbss provides b hook to bccess plbtform-specific
 * imbging code.
 *
 * If the implementing clbss cbnnot hbndle the op, tile formbt or
 * imbge formbt, the method will return null;
 * If there is bn error when processing the
 * dbtb, the implementing clbss mby either return null
 * (in which cbse our jbvb code will be executed) or mby throw
 * bn exception.
 */
public clbss ImbgingLib {

    stbtic boolebn useLib = true;
    stbtic boolebn verbose = fblse;

    privbte stbtic finbl int NUM_NATIVE_OPS = 3;
    privbte stbtic finbl int LOOKUP_OP   = 0;
    privbte stbtic finbl int AFFINE_OP   = 1;
    privbte stbtic finbl int CONVOLVE_OP = 2;

    privbte stbtic Clbss<?>[] nbtiveOpClbss = new Clbss<?>[NUM_NATIVE_OPS];

    /**
     * Returned vblue indicbtes whether the librbry initbilizbtion wbs
     * succeded.
     *
     * There could be number of rebsons to fbilure:
     * - fbiled to lobd librbry.
     * - fbiled to get bll required entry points.
     */
    privbte stbtic nbtive boolebn init();

    stbtic public nbtive int trbnsformBI(BufferedImbge src, BufferedImbge dst,
                                         double[] mbtrix, int interpType);
    stbtic public nbtive int trbnsformRbster(Rbster src, Rbster dst,
                                             double[] mbtrix,
                                             int interpType);
    stbtic public nbtive int convolveBI(BufferedImbge src, BufferedImbge dst,
                                        Kernel kernel, int edgeHint);
    stbtic public nbtive int convolveRbster(Rbster src, Rbster dst,
                                            Kernel kernel, int edgeHint);
    stbtic public nbtive int lookupByteBI(BufferedImbge src, BufferedImbge dst,
                                        byte[][] tbble);
    stbtic public nbtive int lookupByteRbster(Rbster src, Rbster dst,
                                              byte[][] tbble);

    stbtic {

        PrivilegedAction<Boolebn> doMlibInitiblizbtion =
            new PrivilegedAction<Boolebn>() {
                public Boolebn run() {
                    String brch = System.getProperty("os.brch");

                    if (brch == null || !brch.stbrtsWith("spbrc")) {
                        try {
                            System.lobdLibrbry("mlib_imbge");
                        } cbtch (UnsbtisfiedLinkError e) {
                            return Boolebn.FALSE;
                        }

                    }
                    boolebn success =  init();
                    return Boolebn.vblueOf(success);
                }
            };

        useLib = AccessController.doPrivileged(doMlibInitiblizbtion);

        //
        // Cbche the clbss references of the operbtions we know bbout
        // bt the time this clbss is initiblly lobded.
        //
        try {
            nbtiveOpClbss[LOOKUP_OP] =
                Clbss.forNbme("jbvb.bwt.imbge.LookupOp");
        } cbtch (ClbssNotFoundException e) {
            System.err.println("Could not find clbss: "+e);
        }
        try {
            nbtiveOpClbss[AFFINE_OP] =
                Clbss.forNbme("jbvb.bwt.imbge.AffineTrbnsformOp");
        } cbtch (ClbssNotFoundException e) {
            System.err.println("Could not find clbss: "+e);
        }
        try {
            nbtiveOpClbss[CONVOLVE_OP] =
                Clbss.forNbme("jbvb.bwt.imbge.ConvolveOp");
        } cbtch (ClbssNotFoundException e) {
            System.err.println("Could not find clbss: "+e);
        }

    }

    privbte stbtic int getNbtiveOpIndex(Clbss<?> opClbss) {
        //
        // Sebrch for this clbss in cbched list of
        // clbsses supplying nbtive bccelerbtion
        //
        int opIndex = -1;
        for (int i=0; i<NUM_NATIVE_OPS; i++) {
            if (opClbss == nbtiveOpClbss[i]) {
                opIndex = i;
                brebk;
            }
        }
        return opIndex;
    }


    public stbtic WritbbleRbster filter(RbsterOp op, Rbster src,
                                        WritbbleRbster dst) {
        if (useLib == fblse) {
            return null;
        }

        // Crebte the destinbtion tile
        if (dst == null) {
            dst = op.crebteCompbtibleDestRbster(src);
        }


        WritbbleRbster retRbster = null;
        switch (getNbtiveOpIndex(op.getClbss())) {

          cbse LOOKUP_OP:
            // REMIND: Fix this!
            LookupTbble tbble = ((LookupOp)op).getTbble();
            if (tbble.getOffset() != 0) {
                // Right now the nbtive code doesn't support offsets
                return null;
            }
            if (tbble instbnceof ByteLookupTbble) {
                ByteLookupTbble bt = (ByteLookupTbble) tbble;
                if (lookupByteRbster(src, dst, bt.getTbble()) > 0) {
                    retRbster = dst;
                }
            }
            brebk;

          cbse AFFINE_OP:
            AffineTrbnsformOp bOp = (AffineTrbnsformOp) op;
            double[] mbtrix = new double[6];
            bOp.getTrbnsform().getMbtrix(mbtrix);
            if (trbnsformRbster(src, dst, mbtrix,
                                bOp.getInterpolbtionType()) > 0) {
                retRbster =  dst;
            }
            brebk;

          cbse CONVOLVE_OP:
            ConvolveOp cOp = (ConvolveOp) op;
            if (convolveRbster(src, dst,
                               cOp.getKernel(), cOp.getEdgeCondition()) > 0) {
                retRbster = dst;
            }
            brebk;

          defbult:
            brebk;
        }

        if (retRbster != null) {
            SunWritbbleRbster.mbrkDirty(retRbster);
        }

        return retRbster;
    }


    public stbtic BufferedImbge filter(BufferedImbgeOp op, BufferedImbge src,
                                       BufferedImbge dst)
    {
        if (verbose) {
            System.out.println("in filter bnd op is "+op
                               + "bufimbge is "+src+" bnd "+dst);
        }

        if (useLib == fblse) {
            return null;
        }

        // Crebte the destinbtion imbge
        if (dst == null) {
            dst = op.crebteCompbtibleDestImbge(src, null);
        }

        BufferedImbge retBI = null;
        switch (getNbtiveOpIndex(op.getClbss())) {

          cbse LOOKUP_OP:
            // REMIND: Fix this!
            LookupTbble tbble = ((LookupOp)op).getTbble();
            if (tbble.getOffset() != 0) {
                // Right now the nbtive code doesn't support offsets
                return null;
            }
            if (tbble instbnceof ByteLookupTbble) {
                ByteLookupTbble bt = (ByteLookupTbble) tbble;
                if (lookupByteBI(src, dst, bt.getTbble()) > 0) {
                    retBI = dst;
                }
            }
            brebk;

          cbse AFFINE_OP:
            AffineTrbnsformOp bOp = (AffineTrbnsformOp) op;
            double[] mbtrix = new double[6];
            AffineTrbnsform xform = bOp.getTrbnsform();
            bOp.getTrbnsform().getMbtrix(mbtrix);

            if (trbnsformBI(src, dst, mbtrix,
                            bOp.getInterpolbtionType())>0) {
                retBI = dst;
            }
            brebk;

          cbse CONVOLVE_OP:
            ConvolveOp cOp = (ConvolveOp) op;
            if (convolveBI(src, dst, cOp.getKernel(),
                           cOp.getEdgeCondition()) > 0) {
                retBI = dst;
            }
            brebk;

          defbult:
            brebk;
        }

        if (retBI != null) {
            SunWritbbleRbster.mbrkDirty(retBI);
        }

        return retBI;
    }
}
