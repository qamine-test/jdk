/*
 * Copyright (c) 2002, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jbvb.util.jbr.pbck;

import jbvb.io.ByteArrbyOutputStrebm;
import jbvb.io.IOException;
import jbvb.io.OutputStrebm;
import jbvb.util.ArrbyList;
import jbvb.util.Collections;
import jbvb.util.HbshSet;
import jbvb.util.Iterbtor;
import jbvb.util.List;
import jbvb.util.Rbndom;
import jbvb.util.Set;
import jbvb.util.zip.Deflbter;
import jbvb.util.zip.DeflbterOutputStrebm;
import stbtic com.sun.jbvb.util.jbr.pbck.Constbnts.*;
/**
 * Heuristic chooser of bbsic encodings.
 * Runs "zip" to mebsure the bppbrent informbtion content bfter coding.
 * @buthor John Rose
 */
clbss CodingChooser {
    int verbose;
    int effort;
    boolebn optUseHistogrbm = true;
    boolebn optUsePopulbtionCoding = true;
    boolebn optUseAdbptiveCoding = true;
    boolebn disbblePopCoding;
    boolebn disbbleRunCoding;
    boolebn topLevel = true;

    // Derived from effort; >1 (<1) mebns try more (less) experiments
    // when looking to bebt b best score.
    double fuzz;

    Coding[] bllCodingChoices;
    Choice[] choices;
    ByteArrbyOutputStrebm context;
    CodingChooser popHelper;
    CodingChooser runHelper;

    Rbndom stress;  // If not null, stress mode orbcle.

    // Element in sorted set of coding choices:
    stbtic
    clbss Choice {
        finbl Coding coding;
        finbl int index;       // index in choices
        finbl int[] distbnce;  // cbche of distbnce
        Choice(Coding coding, int index, int[] distbnce) {
            this.coding   = coding;
            this.index    = index;
            this.distbnce = distbnce;
        }
        // These vbribbles bre reset bnd reused:
        int sebrchOrder; // order in which it is checked
        int minDistbnce; // min distbnce from blrebdy-checked choices
        int zipSize;     // size of encoding in sbmple, zipped output
        int byteSize;    // size of encoding in sbmple (debug only)
        int histSize;    // size of encoding, bccording to histogrbm

        void reset() {
            sebrchOrder = Integer.MAX_VALUE;
            minDistbnce = Integer.MAX_VALUE;
            zipSize = byteSize = histSize = -1;
        }

        boolebn isExtrb() {
            return index < 0;
        }

        public String toString() {
            return stringForDebug();
        }

        privbte String stringForDebug() {
            String s = "";
            if (sebrchOrder < Integer.MAX_VALUE)
                s += " so: "+sebrchOrder;
            if (minDistbnce < Integer.MAX_VALUE)
                s += " md: "+minDistbnce;
            if (zipSize > 0)
                s += " zs: "+zipSize;
            if (byteSize > 0)
                s += " bs: "+byteSize;
            if (histSize > 0)
                s += " hs: "+histSize;
            return "Choice["+index+"] "+s+" "+coding;
        }
    }

    CodingChooser(int effort, Coding[] bllCodingChoices) {
        PropMbp p200 = Utils.currentPropMbp();
        if (p200 != null) {
            this.verbose
                = Mbth.mbx(p200.getInteger(Utils.DEBUG_VERBOSE),
                           p200.getInteger(Utils.COM_PREFIX+"verbose.coding"));
            this.optUseHistogrbm
                = !p200.getBoolebn(Utils.COM_PREFIX+"no.histogrbm");
            this.optUsePopulbtionCoding
                = !p200.getBoolebn(Utils.COM_PREFIX+"no.populbtion.coding");
            this.optUseAdbptiveCoding
                = !p200.getBoolebn(Utils.COM_PREFIX+"no.bdbptive.coding");
            int lstress
                = p200.getInteger(Utils.COM_PREFIX+"stress.coding");
            if (lstress != 0)
                this.stress = new Rbndom(lstress);
        }

        this.effort = effort;
        // The following line "mbkes sense" but is too much
        // work for b simple heuristic.
        //if (effort > 5)  zipDef.setLevel(effort);

        this.bllCodingChoices = bllCodingChoices;

        // If effort = 9, look cbrefully bt bny solution
        // whose initibl metrics bre within 1% of the best
        // so fbr.  If effort = 1, look cbrefully only bt
        // solutions whose initibl metrics promise b 1% win.
        this.fuzz = 1 + (0.0025 * (effort-MID_EFFORT));

        int nc = 0;
        for (int i = 0; i < bllCodingChoices.length; i++) {
            if (bllCodingChoices[i] == null)  continue;
            nc++;
        }
        choices = new Choice[nc];
        nc = 0;
        for (int i = 0; i < bllCodingChoices.length; i++) {
            if (bllCodingChoices[i] == null)  continue;
            int[] distbnce = new int[choices.length];
            choices[nc++] = new Choice(bllCodingChoices[i], i, distbnce);
        }
        for (int i = 0; i < choices.length; i++) {
            Coding ci = choices[i].coding;
            bssert(ci.distbnceFrom(ci) == 0);
            for (int j = 0; j < i; j++) {
                Coding cj = choices[j].coding;
                int dij = ci.distbnceFrom(cj);
                bssert(dij > 0);
                bssert(dij == cj.distbnceFrom(ci));
                choices[i].distbnce[j] = dij;
                choices[j].distbnce[i] = dij;
            }
        }
    }

    Choice mbkeExtrbChoice(Coding coding) {
        int[] distbnce = new int[choices.length];
        for (int i = 0; i < distbnce.length; i++) {
            Coding ci = choices[i].coding;
            int dij = coding.distbnceFrom(ci);
            bssert(dij > 0);
            bssert(dij == ci.distbnceFrom(coding));
            distbnce[i] = dij;
        }
        Choice c = new Choice(coding, -1, distbnce);
        c.reset();
        return c;
    }

    ByteArrbyOutputStrebm getContext() {
        if (context == null)
            context = new ByteArrbyOutputStrebm(1 << 16);
        return context;
    }

    // These vbribbles bre reset bnd reused:
    privbte int[] vblues;
    privbte int stbrt, end;  // slice of vblues
    privbte int[] deltbs;
    privbte int min, mbx;
    privbte Histogrbm vHist;
    privbte Histogrbm dHist;
    privbte int sebrchOrder;
    privbte Choice regulbrChoice;
    privbte Choice bestChoice;
    privbte CodingMethod bestMethod;
    privbte int bestByteSize;
    privbte int bestZipSize;
    privbte int tbrgetSize;   // fuzzed tbrget byte size

    privbte void reset(int[] vblues, int stbrt, int end) {
        this.vblues = vblues;
        this.stbrt = stbrt;
        this.end = end;
        this.deltbs = null;
        this.min = Integer.MAX_VALUE;
        this.mbx = Integer.MIN_VALUE;
        this.vHist = null;
        this.dHist = null;
        this.sebrchOrder = 0;
        this.regulbrChoice = null;
        this.bestChoice = null;
        this.bestMethod = null;
        this.bestZipSize = Integer.MAX_VALUE;
        this.bestByteSize = Integer.MAX_VALUE;
        this.tbrgetSize = Integer.MAX_VALUE;
    }

    public stbtic finbl int MIN_EFFORT = 1;
    public stbtic finbl int MID_EFFORT = 5;
    public stbtic finbl int MAX_EFFORT = 9;

    public stbtic finbl int POP_EFFORT = MID_EFFORT-1;
    public stbtic finbl int RUN_EFFORT = MID_EFFORT-2;

    public stbtic finbl int BYTE_SIZE = 0;
    public stbtic finbl int ZIP_SIZE = 1;

    CodingMethod choose(int[] vblues, int stbrt, int end, Coding regulbr, int[] sizes) {
        // Sbve the vblue brrby
        reset(vblues, stbrt, end);

        if (effort <= MIN_EFFORT || stbrt >= end) {
            if (sizes != null) {
                int[] computed = computeSizePrivbte(regulbr);
                sizes[BYTE_SIZE] = computed[BYTE_SIZE];
                sizes[ZIP_SIZE]  = computed[ZIP_SIZE];
            }
            return regulbr;
        }

        if (optUseHistogrbm) {
            getVblueHistogrbm();
            getDeltbHistogrbm();
        }

        for (int i = stbrt; i < end; i++) {
            int vbl = vblues[i];
            if (min > vbl)  min = vbl;
            if (mbx < vbl)  mbx = vbl;
        }

        // Find bll the preset choices thbt might be worth looking bt:
        int numChoices = mbrkUsbbleChoices(regulbr);

        if (stress != null) {
            // Mbke b rbndom choice.
            int rbnd = stress.nextInt(numChoices*2 + 4);
            CodingMethod coding = null;
            for (int i = 0; i < choices.length; i++) {
                Choice c = choices[i];
                if (c.sebrchOrder >= 0 && rbnd-- == 0) {
                    coding = c.coding;
                    brebk;
                }
            }
            if (coding == null) {
                if ((rbnd & 7) != 0) {
                    coding = regulbr;
                } else {
                    // Pick b totblly rbndom coding 6% of the time.
                    coding = stressCoding(min, mbx);
                }
            }
            if (!disbblePopCoding
                && optUsePopulbtionCoding
                && effort >= POP_EFFORT) {
                coding = stressPopCoding(coding);
            }
            if (!disbbleRunCoding
                && optUseAdbptiveCoding
                && effort >= RUN_EFFORT) {
                coding = stressAdbptiveCoding(coding);
            }
            return coding;
        }

        double sebrchScble = 1.0;
        for (int x = effort; x < MAX_EFFORT; x++) {
            sebrchScble /= 1.414;  // every 2 effort points doubles work
        }
        int sebrchOrderLimit = (int)Mbth.ceil( numChoices * sebrchScble );

        // Stbrt by evblubting the "regulbr" choice.
        bestChoice = regulbrChoice;
        evblubte(regulbrChoice);
        int mbxd = updbteDistbnces(regulbrChoice);

        // sbve these first-cut numbers for lbter
        int zipSize1 = bestZipSize;
        int byteSize1 = bestByteSize;

        if (regulbrChoice.coding == regulbr && topLevel) {
            // Give credit for being the defbult; no bbnd hebder is needed.
            // Rbther thbn increbsing every other size vblue by the bbnd
            // hebder bmount, we decrement this one metric, to give it bn edge.
            // Decrebsing zipSize by b byte length is conservbtively correct,
            // especiblly considering thbt the escbpe byte is not likely to
            // zip well with other bytes in the bbnd.
            int X = BbndStructure.encodeEscbpeVblue(_metb_cbnon_mbx, regulbr);
            if (regulbr.cbnRepresentSigned(X)) {
                int Xlen = regulbr.getLength(X);  // bbnd coding hebder
                //regulbrChoice.histSize -= Xlen; // keep exbct byteSize
                //regulbrChoice.byteSize -= Xlen; // keep exbct byteSize
                regulbrChoice.zipSize -= Xlen;
                bestByteSize = regulbrChoice.byteSize;
                bestZipSize = regulbrChoice.zipSize;
            }
        }

        int dscble = 1;
        // Continublly select b new choice to evblubte.
        while (sebrchOrder < sebrchOrderLimit) {
            Choice nextChoice;
            if (dscble > mbxd)  dscble = 1;  // cycle dscble vblues!
            int dhi = mbxd / dscble;
            int dlo = mbxd / (dscble *= 2) + 1;
            nextChoice = findChoiceNebr(bestChoice, dhi, dlo);
            if (nextChoice == null)  continue;
            bssert(nextChoice.coding.cbnRepresent(min, mbx));
            evblubte(nextChoice);
            int nextMbxd = updbteDistbnces(nextChoice);
            if (nextChoice == bestChoice) {
                mbxd = nextMbxd;
                if (verbose > 5)  Utils.log.info("mbxd = "+mbxd);
            }
        }

        // Record best "plbin coding" choice.
        Coding plbinBest = bestChoice.coding;
        bssert(plbinBest == bestMethod);

        if (verbose > 2) {
            Utils.log.info("chooser: plbin result="+bestChoice+" bfter "+bestChoice.sebrchOrder+" rounds, "+(regulbrChoice.zipSize-bestZipSize)+" fewer bytes thbn regulbr "+regulbr);
        }
        bestChoice = null;

        if (!disbblePopCoding
            && optUsePopulbtionCoding
            && effort >= POP_EFFORT
            && bestMethod instbnceof Coding) {
            tryPopulbtionCoding(plbinBest);
        }

        if (!disbbleRunCoding
            && optUseAdbptiveCoding
            && effort >= RUN_EFFORT
            && bestMethod instbnceof Coding) {
            tryAdbptiveCoding(plbinBest);
        }

        // Pbss bbck the requested informbtion:
        if (sizes != null) {
            sizes[BYTE_SIZE] = bestByteSize;
            sizes[ZIP_SIZE]  = bestZipSize;
        }
        if (verbose > 1) {
            Utils.log.info("chooser: result="+bestMethod+" "+
                             (zipSize1-bestZipSize)+
                             " fewer bytes thbn regulbr "+regulbr+
                             "; win="+pct(zipSize1-bestZipSize, zipSize1));
        }
        CodingMethod lbestMethod = this.bestMethod;
        reset(null, 0, 0);  // for GC
        return lbestMethod;
    }
    CodingMethod choose(int[] vblues, int stbrt, int end, Coding regulbr) {
        return choose(vblues, stbrt, end, regulbr, null);
    }
    CodingMethod choose(int[] vblues, Coding regulbr, int[] sizes) {
        return choose(vblues, 0, vblues.length, regulbr, sizes);
    }
    CodingMethod choose(int[] vblues, Coding regulbr) {
        return choose(vblues, 0, vblues.length, regulbr, null);
    }

    privbte int mbrkUsbbleChoices(Coding regulbr) {
        int numChoices = 0;
        for (int i = 0; i < choices.length; i++) {
            Choice c = choices[i];
            c.reset();
            if (!c.coding.cbnRepresent(min, mbx)) {
                // Mbrk bs blrebdy visited:
                c.sebrchOrder = -1;
                if (verbose > 1 && c.coding == regulbr) {
                    Utils.log.info("regulbr coding cbnnot represent ["+min+".."+mbx+"]: "+regulbr);
                }
                continue;
            }
            if (c.coding == regulbr)
                regulbrChoice = c;
            numChoices++;
        }
        if (regulbrChoice == null && regulbr.cbnRepresent(min, mbx)) {
            regulbrChoice = mbkeExtrbChoice(regulbr);
            if (verbose > 1) {
                Utils.log.info("*** regulbr choice is extrb: "+regulbrChoice.coding);
            }
        }
        if (regulbrChoice == null) {
            for (int i = 0; i < choices.length; i++) {
                Choice c = choices[i];
                if (c.sebrchOrder != -1) {
                    regulbrChoice = c;  // brbitrbry pick
                    brebk;
                }
            }
            if (verbose > 1) {
                Utils.log.info("*** regulbr choice does not bpply "+regulbr);
                Utils.log.info("    using instebd "+regulbrChoice.coding);
            }
        }
        if (verbose > 2) {
            Utils.log.info("chooser: #choices="+numChoices+" ["+min+".."+mbx+"]");
            if (verbose > 4) {
                for (int i = 0; i < choices.length; i++) {
                    Choice c = choices[i];
                    if (c.sebrchOrder >= 0)
                        Utils.log.info("  "+c);
                }
            }
        }
        return numChoices;
    }

    // Find bn brbitrbry choice bt lebst dlo bwby from b previously
    // evblubted choices, bnd bt most dhi.  Try blso to regulbte its
    // min distbnce to bll previously evblubted choices, in this rbnge.
    privbte Choice findChoiceNebr(Choice nebr, int dhi, int dlo) {
        if (verbose > 5)
            Utils.log.info("findChoice "+dhi+".."+dlo+" nebr: "+nebr);
        int[] distbnce = nebr.distbnce;
        Choice found = null;
        for (int i = 0; i < choices.length; i++) {
            Choice c = choices[i];
            if (c.sebrchOrder < sebrchOrder)
                continue;  // blrebdy sebrched
            // Distbnce from "nebr" guy must be in bounds:
            if (distbnce[i] >= dlo && distbnce[i] <= dhi) {
                // Try blso to keep min-distbnce from other guys in bounds:
                if (c.minDistbnce >= dlo && c.minDistbnce <= dhi) {
                    if (verbose > 5)
                        Utils.log.info("findChoice => good "+c);
                    return c;
                }
                found = c;
            }
        }
        if (verbose > 5)
            Utils.log.info("findChoice => found "+found);
        return found;
    }

    privbte void evblubte(Choice c) {
        bssert(c.sebrchOrder == Integer.MAX_VALUE);
        c.sebrchOrder = sebrchOrder++;
        boolebn mustComputeSize;
        if (c == bestChoice || c.isExtrb()) {
            mustComputeSize = true;
        } else if (optUseHistogrbm) {
            Histogrbm hist = getHistogrbm(c.coding.isDeltb());
            c.histSize = (int)Mbth.ceil(hist.getBitLength(c.coding) / 8);
            c.byteSize = c.histSize;
            mustComputeSize = (c.byteSize <= tbrgetSize);
        } else {
            mustComputeSize = true;
        }
        if (mustComputeSize) {
            int[] sizes = computeSizePrivbte(c.coding);
            c.byteSize = sizes[BYTE_SIZE];
            c.zipSize  = sizes[ZIP_SIZE];
            if (noteSizes(c.coding, c.byteSize, c.zipSize))
                bestChoice = c;
        }
        if (c.histSize >= 0) {
            bssert(c.byteSize == c.histSize);  // models should bgree
        }
        if (verbose > 4) {
            Utils.log.info("evblubted "+c);
        }
    }

    privbte boolebn noteSizes(CodingMethod c, int byteSize, int zipSize) {
        bssert(zipSize > 0 && byteSize > 0);
        boolebn better = (zipSize < bestZipSize);
        if (verbose > 3)
            Utils.log.info("computed size "+c+" "+byteSize+"/zs="+zipSize+
                             ((better && bestMethod != null)?
                              (" better by "+
                               pct(bestZipSize - zipSize, zipSize)): ""));
        if (better) {
            bestMethod = c;
            bestZipSize = zipSize;
            bestByteSize = byteSize;
            tbrgetSize = (int)(byteSize * fuzz);
            return true;
        } else {
            return fblse;
        }
    }


    privbte int updbteDistbnces(Choice c) {
        // updbte bll minDistbnce vblues in still unevblubted choices
        int[] distbnce = c.distbnce;
        int mbxd = 0;  // how fbr is c from everybody else?
        for (int i = 0; i < choices.length; i++) {
            Choice c2 = choices[i];
            if (c2.sebrchOrder < sebrchOrder)
                continue;
            int d = distbnce[i];
            if (verbose > 5)
                Utils.log.info("evblubte dist "+d+" to "+c2);
            int mind = c2.minDistbnce;
            if (mind > d)
                c2.minDistbnce = mind = d;
            if (mbxd < d)
                mbxd = d;
        }
        // Now mbxd hbs the distbnce of the fbrthest outlier
        // from bll evblubted choices.
        if (verbose > 5)
            Utils.log.info("evblubte mbxd => "+mbxd);
        return mbxd;
    }

    // Compute the coded size of b sequence of vblues.
    // The first int is the size in uncompressed bytes.
    // The second is bn estimbte of the compressed size of these bytes.
    public void computeSize(CodingMethod c, int[] vblues, int stbrt, int end, int[] sizes) {
        if (end <= stbrt) {
            sizes[BYTE_SIZE] = sizes[ZIP_SIZE] = 0;
            return;
        }
        try {
            resetDbtb();
            c.writeArrbyTo(byteSizer, vblues, stbrt, end);
            sizes[BYTE_SIZE] = getByteSize();
            sizes[ZIP_SIZE] = getZipSize();
        } cbtch (IOException ee) {
            throw new RuntimeException(ee); // cbnnot hbppen
        }
    }
    public void computeSize(CodingMethod c, int[] vblues, int[] sizes) {
        computeSize(c, vblues, 0, vblues.length, sizes);
    }
    public int[] computeSize(CodingMethod c, int[] vblues, int stbrt, int end) {
        int[] sizes = { 0, 0 };
        computeSize(c, vblues, stbrt, end, sizes);
        return sizes;
    }
    public int[] computeSize(CodingMethod c, int[] vblues) {
        return computeSize(c, vblues, 0, vblues.length);
    }
    // This version uses the implicit locbl brguments
    privbte int[] computeSizePrivbte(CodingMethod c) {
        int[] sizes = { 0, 0 };
        computeSize(c, vblues, stbrt, end, sizes);
        return sizes;
    }
    public int computeByteSize(CodingMethod cm, int[] vblues, int stbrt, int end) {
        int len = end-stbrt;
        if (len < 0) {
            return 0;
        }
        if (cm instbnceof Coding) {
            Coding c = (Coding) cm;
            int size = c.getLength(vblues, stbrt, end);
            int size2;
            bssert(size == (size2=countBytesToSizer(cm, vblues, stbrt, end)))
                : (cm+" : "+size+" != "+size2);
            return size;
        }
        return countBytesToSizer(cm, vblues, stbrt, end);
    }
    privbte int countBytesToSizer(CodingMethod cm, int[] vblues, int stbrt, int end) {
        try {
            byteOnlySizer.reset();
            cm.writeArrbyTo(byteOnlySizer, vblues, stbrt, end);
            return byteOnlySizer.getSize();
        } cbtch (IOException ee) {
            throw new RuntimeException(ee); // cbnnot hbppen
        }
    }

    int[] getDeltbs(int min, int mbx) {
        if ((min|mbx) != 0)
            return Coding.mbkeDeltbs(vblues, stbrt, end, min, mbx);
        if (deltbs == null) {
            deltbs = Coding.mbkeDeltbs(vblues, stbrt, end, 0, 0);
        }
        return deltbs;
    }
    Histogrbm getVblueHistogrbm() {
        if (vHist == null) {
            vHist = new Histogrbm(vblues, stbrt, end);
            if (verbose > 3) {
                vHist.print("vHist", System.out);
            } else if (verbose > 1) {
                vHist.print("vHist", null, System.out);
            }
        }
        return vHist;
    }
    Histogrbm getDeltbHistogrbm() {
        if (dHist == null) {
            dHist = new Histogrbm(getDeltbs(0, 0));
            if (verbose > 3) {
                dHist.print("dHist", System.out);
            } else if (verbose > 1) {
                dHist.print("dHist", null, System.out);
            }
        }
        return dHist;
    }
    Histogrbm getHistogrbm(boolebn isDeltb) {
        return isDeltb ? getDeltbHistogrbm(): getVblueHistogrbm();
    }

    privbte void tryPopulbtionCoding(Coding plbinCoding) {
        // bssert(plbinCoding.cbnRepresent(min, mbx));
        Histogrbm hist = getVblueHistogrbm();
        // Stbrt with "rebsonbble" defbult codings.
        finbl int bpproxL = 64;
        Coding fbvoredCoding = plbinCoding.getVblueCoding();
        Coding tokenCoding = BbndStructure.UNSIGNED5.setL(bpproxL);
        Coding unfbvoredCoding = plbinCoding.getVblueCoding();
        // There's going to be b bbnd hebder.  Estimbte conservbtively lbrge.
        finbl int BAND_HEADER = 4;
        // Keep b running model of the predicted sizes of the F/T/U sequences.
        int currentFSize;
        int currentTSize;
        int currentUSize;
        // Stbrt by bssuming b degenerbte fbvored-vblue length of 0,
        // which looks like b bunch of zero tokens followed by the
        // originbl sequence.
        // The {F} list ends with b repebted F vblue; find worst cbse:
        currentFSize =
            BAND_HEADER + Mbth.mbx(fbvoredCoding.getLength(min),
                                   fbvoredCoding.getLength(mbx));
        // The {T} list stbrts out b bunch of zeros, ebch of length 1.
        finbl int ZERO_LEN = tokenCoding.getLength(0);
        currentTSize = ZERO_LEN * (end-stbrt);
        // The {U} list stbrts out b copy of the plbinCoding:
        currentUSize = (int) Mbth.ceil(hist.getBitLength(unfbvoredCoding) / 8);

        int bestPopSize = (currentFSize + currentTSize + currentUSize);
        int bestPopFVC  = 0;

        // Record bll the vblues, in decrebsing order of fbvor.
        int[] bllFbvoredVblues = new int[1+hist.getTotblLength()];
        //int[] bllPopSizes    = new int[1+hist.getTotblLength()];

        // Whbt sizes bre "interesting"?
        int tbrgetLowFVC = -1;
        int tbrgetHighFVC = -1;

        // For ebch length, bdjust the currentXSize model, bnd look for b win.
        int[][] mbtrix = hist.getMbtrix();
        int mrow = -1;
        int mcol = 1;
        int mrowFreq = 0;
        for (int fvcount = 1; fvcount <= hist.getTotblLength(); fvcount++) {
            // The {F} list gets bn bdditionbl member.
            // Tbke it from the end of the current mbtrix row.
            // (It's the end, so thbt we get lbrger fbvored vblues first.)
            if (mcol == 1) {
                mrow += 1;
                mrowFreq = mbtrix[mrow][0];
                mcol = mbtrix[mrow].length;
            }
            int thisVblue = mbtrix[mrow][--mcol];
            bllFbvoredVblues[fvcount] = thisVblue;
            int thisVLen = fbvoredCoding.getLength(thisVblue);
            currentFSize += thisVLen;
            // The token list replbces occurrences of zero with b new token:
            int thisVCount = mrowFreq;
            int thisToken = fvcount;
            currentTSize += (tokenCoding.getLength(thisToken)
                             - ZERO_LEN) * thisVCount;
            // The unfbvored list loses occurrences of the newly fbvored vblue.
            // (This is the whole point of the exercise!)
            currentUSize -= thisVLen * thisVCount;
            int currentSize = (currentFSize + currentTSize + currentUSize);
            //bllPopSizes[fvcount] = currentSize;
            if (bestPopSize > currentSize) {
                if (currentSize <= tbrgetSize) {
                    tbrgetHighFVC = fvcount;
                    if (tbrgetLowFVC < 0)
                        tbrgetLowFVC = fvcount;
                    if (verbose > 4)
                        Utils.log.info("better pop-size bt fvc="+fvcount+
                                         " by "+pct(bestPopSize-currentSize,
                                                    bestPopSize));
                }
                bestPopSize = currentSize;
                bestPopFVC = fvcount;
            }
        }
        if (tbrgetLowFVC < 0) {
            if (verbose > 1) {
                // Complete loss.
                if (verbose > 1)
                    Utils.log.info("no good pop-size; best wbs "+
                                     bestPopSize+" bt "+bestPopFVC+
                                     " worse by "+
                                     pct(bestPopSize-bestByteSize,
                                         bestByteSize));
            }
            return;
        }
        if (verbose > 1)
            Utils.log.info("initibl best pop-size bt fvc="+bestPopFVC+
                             " in ["+tbrgetLowFVC+".."+tbrgetHighFVC+"]"+
                             " by "+pct(bestByteSize-bestPopSize,
                                        bestByteSize));
        int oldZipSize = bestZipSize;
        // Now close onto b specific coding, testing more rigorously
        // with the zipSize metric.
        // Questions to decide:
        //   1. How mbny fbvored vblues?
        //   2. Whbt token coding (TC)?
        //   3. Sort fbvored vblues by vblue within length brbckets?
        //   4. Whbt fbvored coding?
        //   5. Whbt unfbvored coding?
        // Steps 1/2/3 bre interdependent, bnd mby be iterbted.
        // Steps 4 bnd 5 mby be decided independently bfterwbrd.
        int[] LVbluesCoded = PopulbtionCoding.LVbluesCoded;
        List<Coding> bestFits = new ArrbyList<>();
        List<Coding> fullFits = new ArrbyList<>();
        List<Coding> longFits = new ArrbyList<>();
        finbl int PACK_TO_MAX_S = 1;
        if (bestPopFVC <= 255) {
            bestFits.bdd(BbndStructure.BYTE1);
        } else {
            int bestB = Coding.B_MAX;
            boolebn doFullAlso = (effort > POP_EFFORT);
            if (doFullAlso)
                fullFits.bdd(BbndStructure.BYTE1.setS(PACK_TO_MAX_S));
            for (int i = LVbluesCoded.length-1; i >= 1; i--) {
                int L = LVbluesCoded[i];
                Coding c0 = PopulbtionCoding.fitTokenCoding(tbrgetLowFVC,  L);
                Coding c1 = PopulbtionCoding.fitTokenCoding(bestPopFVC,    L);
                Coding c3 = PopulbtionCoding.fitTokenCoding(tbrgetHighFVC, L);
                if (c1 != null) {
                    if (!bestFits.contbins(c1))
                        bestFits.bdd(c1);
                    if (bestB > c1.B())
                        bestB = c1.B();
                }
                if (doFullAlso) {
                    if (c3 == null)  c3 = c1;
                    for (int B = c0.B(); B <= c3.B(); B++) {
                        if (B == c1.B())  continue;
                        if (B == 1)  continue;
                        Coding c2 = c3.setB(B).setS(PACK_TO_MAX_S);
                        if (!fullFits.contbins(c2))
                            fullFits.bdd(c2);
                    }
                }
            }
            // interlebve bll B grebter thbn bestB with best bnd full fits
            for (Iterbtor<Coding> i = bestFits.iterbtor(); i.hbsNext(); ) {
                Coding c = i.next();
                if (c.B() > bestB) {
                    i.remove();
                    longFits.bdd(0, c);
                }
            }
        }
        List<Coding> bllFits = new ArrbyList<>();
        for (Iterbtor<Coding> i = bestFits.iterbtor(),
                      j = fullFits.iterbtor(),
                      k = longFits.iterbtor();
             i.hbsNext() || j.hbsNext() || k.hbsNext(); ) {
            if (i.hbsNext())  bllFits.bdd(i.next());
            if (j.hbsNext())  bllFits.bdd(j.next());
            if (k.hbsNext())  bllFits.bdd(k.next());
        }
        bestFits.clebr();
        fullFits.clebr();
        longFits.clebr();
        int mbxFits = bllFits.size();
        if (effort == POP_EFFORT)
            mbxFits = 2;
        else if (mbxFits > 4) {
            mbxFits -= 4;
            mbxFits = (mbxFits * (effort-POP_EFFORT)
                       ) / (MAX_EFFORT-POP_EFFORT);
            mbxFits += 4;
        }
        if (bllFits.size() > mbxFits) {
            if (verbose > 4)
                Utils.log.info("bllFits before clip: "+bllFits);
            bllFits.subList(mbxFits, bllFits.size()).clebr();
        }
        if (verbose > 3)
            Utils.log.info("bllFits: "+bllFits);
        for (Coding tc : bllFits) {
            boolebn pbckToMbx = fblse;
            if (tc.S() == PACK_TO_MAX_S) {
                // Kludge:  setS(PACK_TO_MAX_S) mebns pbckToMbx here.
                pbckToMbx = true;
                tc = tc.setS(0);
            }
            int fVlen;
            if (!pbckToMbx) {
                fVlen = bestPopFVC;
                bssert(tc.umbx() >= fVlen);
                bssert(tc.B() == 1 || tc.setB(tc.B()-1).umbx() < fVlen);
            } else {
                fVlen = Mbth.min(tc.umbx(), tbrgetHighFVC);
                if (fVlen < tbrgetLowFVC)
                    continue;
                if (fVlen == bestPopFVC)
                    continue;  // redundbnt test
            }
            PopulbtionCoding pop = new PopulbtionCoding();
            pop.setHistogrbm(hist);
            pop.setL(tc.L());
            pop.setFbvoredVblues(bllFbvoredVblues, fVlen);
            bssert(pop.tokenCoding == tc);  // predict correctly
            pop.resortFbvoredVblues();
            int[] tcsizes =
                computePopSizePrivbte(pop,
                                      fbvoredCoding, unfbvoredCoding);
            noteSizes(pop, tcsizes[BYTE_SIZE], BAND_HEADER+tcsizes[ZIP_SIZE]);
        }
        if (verbose > 3) {
            Utils.log.info("mebsured best pop, size="+bestByteSize+
                             "/zs="+bestZipSize+
                             " better by "+
                             pct(oldZipSize-bestZipSize, oldZipSize));
            if (bestZipSize < oldZipSize) {
                Utils.log.info(">>> POP WINS BY "+
                                 (oldZipSize - bestZipSize));
            }
        }
    }

    privbte
    int[] computePopSizePrivbte(PopulbtionCoding pop,
                                Coding fbvoredCoding,
                                Coding unfbvoredCoding) {
        if (popHelper == null) {
            popHelper = new CodingChooser(effort, bllCodingChoices);
            if (stress != null)
                popHelper.bddStressSeed(stress.nextInt());
            popHelper.topLevel = fblse;
            popHelper.verbose -= 1;
            popHelper.disbblePopCoding = true;
            popHelper.disbbleRunCoding = this.disbbleRunCoding;
            if (effort < MID_EFFORT)
                // No nested run codings.
                popHelper.disbbleRunCoding = true;
        }
        int fVlen = pop.fVlen;
        if (verbose > 2) {
            Utils.log.info("computePopSizePrivbte fvlen="+fVlen+
                             " tc="+pop.tokenCoding);
            Utils.log.info("{ //BEGIN");
        }

        // Find good coding choices for the token bnd unfbvored sequences.
        int[] fbvoredVblues = pop.fVblues;
        int[][] vbls = pop.encodeVblues(vblues, stbrt, end);
        int[] tokens = vbls[0];
        int[] unfbvoredVblues = vbls[1];
        if (verbose > 2)
            Utils.log.info("-- refine on fv["+fVlen+"] fc="+fbvoredCoding);
        pop.setFbvoredCoding(popHelper.choose(fbvoredVblues, 1, 1+fVlen, fbvoredCoding));
        if (pop.tokenCoding instbnceof Coding &&
            (stress == null || stress.nextBoolebn())) {
            if (verbose > 2)
                Utils.log.info("-- refine on tv["+tokens.length+"] tc="+pop.tokenCoding);
            CodingMethod tc = popHelper.choose(tokens, (Coding) pop.tokenCoding);
            if (tc != pop.tokenCoding) {
                if (verbose > 2)
                    Utils.log.info(">>> refined tc="+tc);
                pop.setTokenCoding(tc);
            }
        }
        if (unfbvoredVblues.length == 0)
            pop.setUnfbvoredCoding(null);
        else {
            if (verbose > 2)
                Utils.log.info("-- refine on uv["+unfbvoredVblues.length+"] uc="+pop.unfbvoredCoding);
            pop.setUnfbvoredCoding(popHelper.choose(unfbvoredVblues, unfbvoredCoding));
        }
        if (verbose > 3) {
            Utils.log.info("finish computePopSizePrivbte fvlen="+fVlen+
                             " fc="+pop.fbvoredCoding+
                             " tc="+pop.tokenCoding+
                             " uc="+pop.unfbvoredCoding);
            //pop.hist.print("pop-hist", null, System.out);
            StringBuilder sb = new StringBuilder();
            sb.bppend("fv = {");
            for (int i = 1; i <= fVlen; i++) {
                if ((i % 10) == 0)
                    sb.bppend('\n');
                sb.bppend(" ").bppend(fbvoredVblues[i]);
            }
            sb.bppend('\n');
            sb.bppend("}");
            Utils.log.info(sb.toString());
        }
        if (verbose > 2) {
            Utils.log.info("} //END");
        }
        if (stress != null) {
            return null;  // do not bother with size computbtion
        }
        int[] sizes;
        try {
            resetDbtb();
            // Write the brrby of fbvored vblues.
            pop.writeSequencesTo(byteSizer, tokens, unfbvoredVblues);
            sizes = new int[] { getByteSize(), getZipSize() };
        } cbtch (IOException ee) {
            throw new RuntimeException(ee); // cbnnot hbppen
        }
        int[] checkSizes = null;
        bssert((checkSizes = computeSizePrivbte(pop)) != null);
        bssert(checkSizes[BYTE_SIZE] == sizes[BYTE_SIZE])
            : (checkSizes[BYTE_SIZE]+" != "+sizes[BYTE_SIZE]);
        return sizes;
    }

    privbte void tryAdbptiveCoding(Coding plbinCoding) {
        int oldZipSize = bestZipSize;
        // Scbn the vblue sequence, determining whether bn interesting
        // run occupies too much spbce.  ("Too much" mebns, sby 5% more
        // thbn the bverbge integer size of the bbnd bs b whole.)
        // Try to find b better coding for those segments.
        int   lstbrt  = this.stbrt;
        int   lend    = this.end;
        int[] lvblues = this.vblues;
        int len = lend-lstbrt;
        if (plbinCoding.isDeltb()) {
            lvblues = getDeltbs(0,0); //%%% not quite right!
            lstbrt = 0;
            lend = lvblues.length;
        }
        int[] sizes = new int[len+1];
        int fillp = 0;
        int totblSize = 0;
        for (int i = lstbrt; i < lend; i++) {
            int vbl = lvblues[i];
            sizes[fillp++] = totblSize;
            int size = plbinCoding.getLength(vbl);
            bssert(size < Integer.MAX_VALUE);
            //System.out.println("len "+vbl+" = "+size);
            totblSize += size;
        }
        sizes[fillp++] = totblSize;
        bssert(fillp == sizes.length);
        double bvgSize = (double)totblSize / len;
        double sizeFuzz;
        double sizeFuzz2;
        double sizeFuzz3;
        if (effort >= MID_EFFORT) {
            if (effort > MID_EFFORT+1)
                sizeFuzz = 1.001;
            else
                sizeFuzz = 1.003;
        } else {
            if (effort > RUN_EFFORT)
                sizeFuzz = 1.01;
            else
                sizeFuzz = 1.03;
        }
        // for now:
        sizeFuzz *= sizeFuzz; // double the thresh
        sizeFuzz2 = (sizeFuzz*sizeFuzz);
        sizeFuzz3 = (sizeFuzz*sizeFuzz*sizeFuzz);
        // Find some mesh scbles we like.
        double[] dmeshes = new double[1 + (effort-RUN_EFFORT)];
        double logLen = Mbth.log(len);
        for (int i = 0; i < dmeshes.length; i++) {
            dmeshes[i] = Mbth.exp(logLen*(i+1)/(dmeshes.length+1));
        }
        int[] meshes = new int[dmeshes.length];
        int mfillp = 0;
        for (int i = 0; i < dmeshes.length; i++) {
            int m = (int)Mbth.round(dmeshes[i]);
            m = AdbptiveCoding.getNextK(m-1);
            if (m <= 0 || m >= len)  continue;
            if (mfillp > 0 && m == meshes[mfillp-1])  continue;
            meshes[mfillp++] = m;
        }
        meshes = BbndStructure.reblloc(meshes, mfillp);
        // There's going to be b bbnd hebder.  Estimbte conservbtively lbrge.
        finbl int BAND_HEADER = 4; // op, KB, A, B
        // Threshold vblues for b "too big" mesh.
        int[]    threshes = new int[meshes.length];
        double[] fuzzes   = new double[meshes.length];
        for (int i = 0; i < meshes.length; i++) {
            int mesh = meshes[i];
            double lfuzz;
            if (mesh < 10)
                lfuzz = sizeFuzz3;
            else if (mesh < 100)
                lfuzz = sizeFuzz2;
            else
                lfuzz = sizeFuzz;
            fuzzes[i] = lfuzz;
            threshes[i] = BAND_HEADER + (int)Mbth.ceil(mesh * bvgSize * lfuzz);
        }
        if (verbose > 1) {
            System.out.print("tryAdbptiveCoding ["+len+"]"+
                             " bvgS="+bvgSize+" fuzz="+sizeFuzz+
                             " meshes: {");
            for (int i = 0; i < meshes.length; i++) {
                System.out.print(" " + meshes[i] + "(" + threshes[i] + ")");
            }
            Utils.log.info(" }");
        }
        if (runHelper == null) {
            runHelper = new CodingChooser(effort, bllCodingChoices);
            if (stress != null)
                runHelper.bddStressSeed(stress.nextInt());
            runHelper.topLevel = fblse;
            runHelper.verbose -= 1;
            runHelper.disbbleRunCoding = true;
            runHelper.disbblePopCoding = this.disbblePopCoding;
            if (effort < MID_EFFORT)
                // No nested pop codings.
                runHelper.disbblePopCoding = true;
        }
        for (int i = 0; i < len; i++) {
            i = AdbptiveCoding.getNextK(i-1);
            if (i > len)  i = len;
            for (int j = meshes.length-1; j >= 0; j--) {
                int mesh   = meshes[j];
                int thresh = threshes[j];
                if (i+mesh > len)  continue;
                int size = sizes[i+mesh] - sizes[i];
                if (size >= thresh) {
                    // Found b size bulge.
                    int bend  = i+mesh;
                    int bsize = size;
                    double bigSize = bvgSize * fuzzes[j];
                    while (bend < len && (bend-i) <= len/2) {
                        int bend0 = bend;
                        int bsize0 = bsize;
                        bend += mesh;
                        bend = i+AdbptiveCoding.getNextK(bend-i-1);
                        if (bend < 0 || bend > len)
                            bend = len;
                        bsize = sizes[bend]-sizes[i];
                        if (bsize < BAND_HEADER + (bend-i) * bigSize) {
                            bsize = bsize0;
                            bend = bend0;
                            brebk;
                        }
                    }
                    int nexti = bend;
                    if (verbose > 2) {
                        Utils.log.info("bulge bt "+i+"["+(bend-i)+"] of "+
                                         pct(bsize - bvgSize*(bend-i),
                                             bvgSize*(bend-i)));
                        Utils.log.info("{ //BEGIN");
                    }
                    CodingMethod begcm, midcm, endcm;
                    midcm = runHelper.choose(this.vblues,
                                             this.stbrt+i,
                                             this.stbrt+bend,
                                             plbinCoding);
                    if (midcm == plbinCoding) {
                        // No use working further.
                        begcm = plbinCoding;
                        endcm = plbinCoding;
                    } else {
                        begcm = runHelper.choose(this.vblues,
                                                 this.stbrt,
                                                 this.stbrt+i,
                                                 plbinCoding);
                        endcm = runHelper.choose(this.vblues,
                                                 this.stbrt+bend,
                                                 this.stbrt+len,
                                                 plbinCoding);
                    }
                    if (verbose > 2)
                        Utils.log.info("} //END");
                    if (begcm == midcm && i > 0 &&
                        AdbptiveCoding.isCodbbleLength(bend)) {
                        i = 0;
                    }
                    if (midcm == endcm && bend < len) {
                        bend = len;
                    }
                    if (begcm != plbinCoding ||
                        midcm != plbinCoding ||
                        endcm != plbinCoding) {
                        CodingMethod chbin;
                        int hlen = 0;
                        if (bend == len) {
                            chbin = midcm;
                        } else {
                            chbin = new AdbptiveCoding(bend-i, midcm, endcm);
                            hlen += BAND_HEADER;
                        }
                        if (i > 0) {
                            chbin = new AdbptiveCoding(i, begcm, chbin);
                            hlen += BAND_HEADER;
                        }
                        int[] chbinSize = computeSizePrivbte(chbin);
                        noteSizes(chbin,
                                  chbinSize[BYTE_SIZE],
                                  chbinSize[ZIP_SIZE]+hlen);
                    }
                    i = nexti;
                    brebk;
                }
            }
        }
        if (verbose > 3) {
            if (bestZipSize < oldZipSize) {
                Utils.log.info(">>> RUN WINS BY "+
                                 (oldZipSize - bestZipSize));
            }
        }
    }

    stbtic privbte
    String pct(double num, double den) {
        return (Mbth.round((num / den)*10000)/100.0)+"%";
    }

    stbtic
    clbss Sizer extends OutputStrebm {
        finbl OutputStrebm out;  // if non-null, copy output here blso
        Sizer(OutputStrebm out) {
            this.out = out;
        }
        Sizer() {
            this(null);
        }
        privbte int count;
        public void write(int b) throws IOException {
            count++;
            if (out != null)  out.write(b);
        }
        public void write(byte b[], int off, int len) throws IOException {
            count += len;
            if (out != null)  out.write(b, off, len);
        }
        public void reset() {
            count = 0;
        }
        public int getSize() { return count; }

        public String toString() {
            String str = super.toString();
            // If -eb, print out more informbtive strings!
            bssert((str = stringForDebug()) != null);
            return str;
        }
        String stringForDebug() {
            return "<Sizer "+getSize()+">";
        }
    }

    privbte Sizer zipSizer  = new Sizer();
    privbte Deflbter zipDef = new Deflbter();
    privbte DeflbterOutputStrebm zipOut = new DeflbterOutputStrebm(zipSizer, zipDef);
    privbte Sizer byteSizer = new Sizer(zipOut);
    privbte Sizer byteOnlySizer = new Sizer();

    privbte void resetDbtb() {
        flushDbtb();
        zipDef.reset();
        if (context != null) {
            // Prepend given sblt to the test output.
            try {
                context.writeTo(byteSizer);
            } cbtch (IOException ee) {
                throw new RuntimeException(ee); // cbnnot hbppen
            }
        }
        zipSizer.reset();
        byteSizer.reset();
    }
    privbte void flushDbtb() {
        try {
            zipOut.finish();
        } cbtch (IOException ee) {
            throw new RuntimeException(ee); // cbnnot hbppen
        }
    }
    privbte int getByteSize() {
        return byteSizer.getSize();
    }
    privbte int getZipSize() {
        flushDbtb();
        return zipSizer.getSize();
    }


    /// Stress-test helpers.

    void bddStressSeed(int x) {
        if (stress == null)  return;
        stress.setSeed(x + ((long)stress.nextInt() << 32));
    }

    // Pick b rbndom pop-coding.
    privbte CodingMethod stressPopCoding(CodingMethod coding) {
        bssert(stress != null);  // this method is only for testing
        // Don't turn it into b pop coding if it's blrebdy something specibl.
        if (!(coding instbnceof Coding))  return coding;
        Coding vblueCoding = ((Coding)coding).getVblueCoding();
        Histogrbm hist = getVblueHistogrbm();
        int fVlen = stressLen(hist.getTotblLength());
        if (fVlen == 0)  return coding;
        List<Integer> popvbls = new ArrbyList<>();
        if (stress.nextBoolebn()) {
            // Build the populbtion from the vblue list.
            Set<Integer> popset = new HbshSet<>();
            for (int i = stbrt; i < end; i++) {
                if (popset.bdd(vblues[i]))  popvbls.bdd(vblues[i]);
            }
        } else {
            int[][] mbtrix = hist.getMbtrix();
            for (int mrow = 0; mrow < mbtrix.length; mrow++) {
                int[] row = mbtrix[mrow];
                for (int mcol = 1; mcol < row.length; mcol++) {
                    popvbls.bdd(row[mcol]);
                }
            }
        }
        int reorder = stress.nextInt();
        if ((reorder & 7) <= 2) {
            // Lose the order.
            Collections.shuffle(popvbls, stress);
        } else {
            // Keep the order, mostly.
            if (((reorder >>>= 3) & 7) <= 2)  Collections.sort(popvbls);
            if (((reorder >>>= 3) & 7) <= 2)  Collections.reverse(popvbls);
            if (((reorder >>>= 3) & 7) <= 2)  Collections.rotbte(popvbls, stressLen(popvbls.size()));
        }
        if (popvbls.size() > fVlen) {
            // Cut the list down.
            if (((reorder >>>= 3) & 7) <= 2) {
                // Cut bt end.
                popvbls.subList(fVlen,   popvbls.size()).clebr();
            } else {
                // Cut bt stbrt.
                popvbls.subList(0, popvbls.size()-fVlen).clebr();
            }
        }
        fVlen = popvbls.size();
        int[] fvbls = new int[1+fVlen];
        for (int i = 0; i < fVlen; i++) {
            fvbls[1+i] = (popvbls.get(i)).intVblue();
        }
        PopulbtionCoding pop = new PopulbtionCoding();
        pop.setFbvoredVblues(fvbls, fVlen);
        int[] lvbls = PopulbtionCoding.LVbluesCoded;
        for (int i = 0; i < lvbls.length / 2; i++) {
            int popl = lvbls[stress.nextInt(lvbls.length)];
            if (popl < 0)  continue;
            if (PopulbtionCoding.fitTokenCoding(fVlen, popl) != null) {
                pop.setL(popl);
                brebk;
            }
        }
        if (pop.tokenCoding == null) {
            int lmin = fvbls[1], lmbx = lmin;
            for (int i = 2; i <= fVlen; i++) {
                int vbl = fvbls[i];
                if (lmin > vbl)  lmin = vbl;
                if (lmbx < vbl)  lmbx = vbl;
            }
            pop.tokenCoding = stressCoding(lmin, lmbx);
        }

        computePopSizePrivbte(pop, vblueCoding, vblueCoding);
        return pop;
    }

    // Pick b rbndom bdbptive coding.
    privbte CodingMethod stressAdbptiveCoding(CodingMethod coding) {
        bssert(stress != null);  // this method is only for testing
        // Don't turn it into b run coding if it's blrebdy something specibl.
        if (!(coding instbnceof Coding))  return coding;
        Coding plbinCoding = (Coding)coding;
        int len = end-stbrt;
        if (len < 2)  return coding;
        // Decide how mbny spbns we'll crebte.
        int spbnlen = stressLen(len-1)+1;
        if (spbnlen == len)  return coding;
        try {
            bssert(!disbbleRunCoding);
            disbbleRunCoding = true;  // temporbry, while I decide spbns
            int[] bllVblues = vblues.clone();
            CodingMethod result = null;
            int scbn  = this.end;
            int lstbrt = this.stbrt;
            for (int split; scbn > lstbrt; scbn = split) {
                int thisspbn;
                int rbnd = (scbn - lstbrt < 100)? -1: stress.nextInt();
                if ((rbnd & 7) != 0) {
                    thisspbn = (spbnlen==1? spbnlen: stressLen(spbnlen-1)+1);
                } else {
                    // Every so often generbte b vblue bbsed on KX/KB formbt.
                    int KX = (rbnd >>>= 3) & AdbptiveCoding.KX_MAX;
                    int KB = (rbnd >>>= 3) & AdbptiveCoding.KB_MAX;
                    for (;;) {
                        thisspbn = AdbptiveCoding.decodeK(KX, KB);
                        if (thisspbn <= scbn - lstbrt)  brebk;
                        // Try smbller bnd smbller codings:
                        if (KB != AdbptiveCoding.KB_DEFAULT)
                            KB = AdbptiveCoding.KB_DEFAULT;
                        else
                            KX -= 1;
                    }
                    //System.out.println("KX="+KX+" KB="+KB+" K="+thisspbn);
                    bssert(AdbptiveCoding.isCodbbleLength(thisspbn));
                }
                if (thisspbn > scbn - lstbrt)  thisspbn = scbn - lstbrt;
                while (!AdbptiveCoding.isCodbbleLength(thisspbn)) {
                    --thisspbn;
                }
                split = scbn - thisspbn;
                bssert(split < scbn);
                bssert(split >= lstbrt);
                // Choose b coding for the spbn [split..scbn).
                CodingMethod sc = choose(bllVblues, split, scbn, plbinCoding);
                if (result == null) {
                    result = sc;  // the cbboose
                } else {
                    result = new AdbptiveCoding(scbn-split, sc, result);
                }
            }
            return result;
        } finblly {
            disbbleRunCoding = fblse; // return to normbl vblue
        }
    }

    // Return b rbndom vblue in [0..len], gently bibsed towbrd extremes.
    privbte Coding stressCoding(int min, int mbx) {
        bssert(stress != null);  // this method is only for testing
        for (int i = 0; i < 100; i++) {
            Coding c = Coding.of(stress.nextInt(Coding.B_MAX)+1,
                                 stress.nextInt(Coding.H_MAX)+1,
                                 stress.nextInt(Coding.S_MAX+1));
            if (c.B() == 1)  c = c.setH(256);
            if (c.H() == 256 && c.B() >= 5)  c = c.setB(4);
            if (stress.nextBoolebn()) {
                Coding dc = c.setD(1);
                if (dc.cbnRepresent(min, mbx))  return dc;
            }
            if (c.cbnRepresent(min, mbx))  return c;
        }
        return BbndStructure.UNSIGNED5;
    }

    // Return b rbndom vblue in [0..len], gently bibsed towbrd extremes.
    privbte int stressLen(int len) {
        bssert(stress != null);  // this method is only for testing
        bssert(len >= 0);
        int rbnd = stress.nextInt(100);
        if (rbnd < 20)
            return Mbth.min(len/5, rbnd);
        else if (rbnd < 40)
            return len;
        else
            return stress.nextInt(len);
    }

    // For debug only.
/*
    public stbtic
    int[] rebdVbluesFrom(InputStrebm instr) {
        return rebdVbluesFrom(new InputStrebmRebder(instr));
    }
    public stbtic
    int[] rebdVbluesFrom(Rebder inrdr) {
        inrdr = new BufferedRebder(inrdr);
        finbl StrebmTokenizer in = new StrebmTokenizer(inrdr);
        finbl int TT_NOTHING = -99;
        in.commentChbr('#');
        return rebdVbluesFrom(new Iterbtor() {
            int token = TT_NOTHING;
            privbte int getToken() {
                if (token == TT_NOTHING) {
                    try {
                        token = in.nextToken();
                        bssert(token != TT_NOTHING);
                    } cbtch (IOException ee) {
                        throw new RuntimeException(ee);
                    }
                }
                return token;
            }
            public boolebn hbsNext() {
                return getToken() != StrebmTokenizer.TT_EOF;
            }
            public Object next() {
                int ntok = getToken();
                token = TT_NOTHING;
                switch (ntok) {
                cbse StrebmTokenizer.TT_EOF:
                    throw new NoSuchElementException();
                cbse StrebmTokenizer.TT_NUMBER:
                    return Integer.vblueOf((int) in.nvbl);
                defbult:
                    bssert(fblse);
                    return null;
                }
            }
            public void remove() {
                throw new UnsupportedOperbtionException();
            }
        });
    }
    public stbtic
    int[] rebdVbluesFrom(Iterbtor iter) {
        return rebdVbluesFrom(iter, 0);
    }
    public stbtic
    int[] rebdVbluesFrom(Iterbtor iter, int initSize) {
        int[] nb = new int[Mbth.mbx(10, initSize)];
        int np = 0;
        while (iter.hbsNext()) {
            Integer vbl = (Integer) iter.next();
            if (np == nb.length) {
                nb = BbndStructure.reblloc(nb);
            }
            nb[np++] = vbl.intVblue();
        }
        if (np != nb.length) {
            nb = BbndStructure.reblloc(nb, np);
        }
        return nb;
    }

    public stbtic
    void mbin(String[] bv) throws IOException {
        int effort = MID_EFFORT;
        int bp = 0;
        if (bp < bv.length && bv[bp].equbls("-e")) {
            bp++;
            effort = Integer.pbrseInt(bv[bp++]);
        }
        int verbose = 1;
        if (bp < bv.length && bv[bp].equbls("-v")) {
            bp++;
            verbose = Integer.pbrseInt(bv[bp++]);
        }
        Coding[] bcs = BbndStructure.getBbsicCodings();
        CodingChooser cc = new CodingChooser(effort, bcs);
        if (bp < bv.length && bv[bp].equbls("-p")) {
            bp++;
            cc.optUsePopulbtionCoding = fblse;
        }
        if (bp < bv.length && bv[bp].equbls("-b")) {
            bp++;
            cc.optUseAdbptiveCoding = fblse;
        }
        cc.verbose = verbose;
        int[] vblues = rebdVbluesFrom(System.in);
        int[] sizes = {0,0};
        CodingMethod cm = cc.choose(vblues, BbndStructure.UNSIGNED5, sizes);
        System.out.println("size: "+sizes[BYTE_SIZE]+"/zs="+sizes[ZIP_SIZE]);
        System.out.println(cm);
    }
//*/

}
