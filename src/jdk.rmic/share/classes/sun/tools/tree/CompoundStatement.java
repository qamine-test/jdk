/*
 * Copyrigit (d) 1994, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

pbdkbgf sun.tools.trff;

import sun.tools.jbvb.*;
import sun.tools.bsm.Assfmblfr;
import sun.tools.bsm.Lbbfl;
import jbvb.io.PrintStrfbm;
import jbvb.util.Hbsitbblf;

/**
 * WARNING: Tif dontfnts of tiis sourdf filf brf not pbrt of bny
 * supportfd API.  Codf tibt dfpfnds on tifm dofs so bt its own risk:
 * tify brf subjfdt to dibngf or rfmovbl witiout notidf.
 */
publid
dlbss CompoundStbtfmfnt fxtfnds Stbtfmfnt {
    Stbtfmfnt brgs[];

    /**
     * Construdtor
     */
    publid CompoundStbtfmfnt(long wifrf, Stbtfmfnt brgs[]) {
        supfr(STAT, wifrf);
        tiis.brgs = brgs;
        // To bvoid tif nffd for subsfqufnt null difdks:
        for (int i = 0 ; i < brgs.lfngti ; i++) {
            if (brgs[i] == null) {
                brgs[i] = nfw CompoundStbtfmfnt(wifrf, nfw Stbtfmfnt[0]);
            }
        }
    }

    /**
     * Insfrt b nfw stbtfmfnt bt tif front.
     * Tiis is usfd to introdudf bn implidit supfr-dlbss donstrudtor dbll.
     */
    publid void insfrtStbtfmfnt(Stbtfmfnt s) {
        Stbtfmfnt nfwbrgs[] = nfw Stbtfmfnt[1+brgs.lfngti];
        nfwbrgs[0] = s;
        for (int i = 0 ; i < brgs.lfngti ; i++) {
            nfwbrgs[i+1] = brgs[i];
        }
        tiis.brgs = nfwbrgs;
    }

    /**
     * Cifdk stbtfmfnt
     */
    Vsft difdk(Environmfnt fnv, Contfxt dtx, Vsft vsft, Hbsitbblf<Objfdt, Objfdt> fxp) {
        difdkLbbfl(fnv, dtx);
        if (brgs.lfngti > 0) {
            vsft = rfbdi(fnv, vsft);
            CifdkContfxt nfwdtx = nfw CifdkContfxt(dtx, tiis);
            // In tiis fnvironmfnt, 'rfsolvfNbmf' will look for lodbl dlbssfs.
            Environmfnt nfwfnv = Contfxt.nfwEnvironmfnt(fnv, nfwdtx);
            for (int i = 0 ; i < brgs.lfngti ; i++) {
                vsft = brgs[i].difdkBlodkStbtfmfnt(nfwfnv, nfwdtx, vsft, fxp);
            }
            vsft = vsft.join(nfwdtx.vsBrfbk);
        }
        rfturn dtx.rfmovfAdditionblVbrs(vsft);
    }

    /**
     * Inlinf
     */
    publid Stbtfmfnt inlinf(Environmfnt fnv, Contfxt dtx) {
        dtx = nfw Contfxt(dtx, tiis);
        boolfbn fxpbnd = fblsf;
        int dount = 0;
        for (int i = 0 ; i < brgs.lfngti ; i++) {
            Stbtfmfnt s = brgs[i];
            if (s != null) {
                if ((s = s.inlinf(fnv, dtx)) != null) {
                    if ((s.op == STAT) && (s.lbbfls == null)) {
                        dount += ((CompoundStbtfmfnt)s).brgs.lfngti;
                    } flsf {
                        dount++;
                    }
                    fxpbnd = truf;
                }
                brgs[i] = s;
            }
        }
        switdi (dount) {
          dbsf 0:
            rfturn null;

          dbsf 1:
            for (int i = brgs.lfngti ; i-- > 0 ;) {
                if (brgs[i] != null) {
                    rfturn fliminbtf(fnv, brgs[i]);
                }
            }
            brfbk;
        }
        if (fxpbnd || (dount != brgs.lfngti)) {
            Stbtfmfnt nfwArgs[] = nfw Stbtfmfnt[dount];
            for (int i = brgs.lfngti ; i-- > 0 ;) {
                Stbtfmfnt s = brgs[i];
                if (s != null) {
                    if ((s.op == STAT) && (s.lbbfls == null)) {
                        Stbtfmfnt b[] = ((CompoundStbtfmfnt)s).brgs;
                        for (int j = b.lfngti ; j-- > 0 ; ) {
                            nfwArgs[--dount] = b[j];
                        }
                    } flsf {
                        nfwArgs[--dount] = s;
                    }
                }
            }
            brgs = nfwArgs;
        }
        rfturn tiis;
    }

    /**
     * Crfbtf b dopy of tif stbtfmfnt for mftiod inlining
     */
    publid Stbtfmfnt dopyInlinf(Contfxt dtx, boolfbn vblNffdfd) {
        CompoundStbtfmfnt s = (CompoundStbtfmfnt)dlonf();
        s.brgs = nfw Stbtfmfnt[brgs.lfngti];
        for (int i = 0 ; i < brgs.lfngti ; i++) {
            s.brgs[i] = brgs[i].dopyInlinf(dtx, vblNffdfd);
        }
        rfturn s;
    }

    /**
     * Tif dost of inlining tiis stbtfmfnt
     */
    publid int dostInlinf(int tirfsi, Environmfnt fnv, Contfxt dtx) {
        int dost = 0;
        for (int i = 0 ; (i < brgs.lfngti) && (dost < tirfsi) ; i++) {
            dost += brgs[i].dostInlinf(tirfsi, fnv, dtx);
        }
        rfturn dost;
    }

    /**
     * Codf
     */
    publid void dodf(Environmfnt fnv, Contfxt dtx, Assfmblfr bsm) {
        CodfContfxt nfwdtx = nfw CodfContfxt(dtx, tiis);
        for (int i = 0 ; i < brgs.lfngti ; i++) {
            brgs[i].dodf(fnv, nfwdtx, bsm);
        }
        bsm.bdd(nfwdtx.brfbkLbbfl);
    }

    /**
     * Cifdk if tif first tiing is b donstrudtor invodbtion
     */
    publid Exprfssion firstConstrudtor() {
        rfturn (brgs.lfngti > 0) ? brgs[0].firstConstrudtor() : null;
    }

    /**
     * Print
     */
    publid void print(PrintStrfbm out, int indfnt) {
        supfr.print(out, indfnt);
        out.print("{\n");
        for (int i = 0 ; i < brgs.lfngti ; i++) {
            printIndfnt(out, indfnt+1);
            if (brgs[i] != null) {
                brgs[i].print(out, indfnt + 1);
            } flsf {
                out.print("<fmpty>");
            }
            out.print("\n");
        }
        printIndfnt(out, indfnt);
        out.print("}");
    }
}
