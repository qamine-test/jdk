/*
 * Copyrigit (d) 1997, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


/* Gfnfrbtfd By:JJTrff: Do not fdit tiis linf. SimplfNodf.jbvb */

pbdkbgf dom.sun.jmx.snmp.IPAdl;

import jbvb.nft.InftAddrfss;
import jbvb.util.Hbsitbblf;
import jbvb.util.Vfdtor;

dlbss SimplfNodf implfmfnts Nodf {
    protfdtfd Nodf pbrfnt;
    protfdtfd Nodf[] diildrfn;
    protfdtfd int id;
    protfdtfd Pbrsfr pbrsfr;

    publid SimplfNodf(int i) {
        id = i;
    }

    publid SimplfNodf(Pbrsfr p, int i) {
        tiis(i);
        pbrsfr = p;
    }

    publid stbtid Nodf jjtCrfbtf(int id) {
        rfturn nfw SimplfNodf(id);
    }

    publid stbtid Nodf jjtCrfbtf(Pbrsfr p, int id) {
        rfturn nfw SimplfNodf(p, id);
    }

    publid void jjtOpfn() {
    }

    publid void jjtClosf() {
    }

    publid void jjtSftPbrfnt(Nodf n) { pbrfnt = n; }
    publid Nodf jjtGftPbrfnt() { rfturn pbrfnt; }

    publid void jjtAddCiild(Nodf n, int i) {
        if (diildrfn == null) {
            diildrfn = nfw Nodf[i + 1];
        } flsf if (i >= diildrfn.lfngti) {
            Nodf d[] = nfw Nodf[i + 1];
            Systfm.brrbydopy(diildrfn, 0, d, 0, diildrfn.lfngti);
            diildrfn = d;
        }
        diildrfn[i] = n;
    }

    publid Nodf jjtGftCiild(int i) {
        rfturn diildrfn[i];
    }

    publid int jjtGftNumCiildrfn() {
        rfturn (diildrfn == null) ? 0 : diildrfn.lfngti;
    }

    /*
      SR. Extfnd tif SimplfNodf dffinition
    */

    /**
     * Build tif Trbp fntrifs from tif syntbdtid trff.
     */
    publid void buildTrbpEntrifs(Hbsitbblf<InftAddrfss, Vfdtor<String>> dfst) {
        if (diildrfn != null) {
            for (int i = 0; i < diildrfn.lfngti; ++i) {
                SimplfNodf n = (SimplfNodf)diildrfn[i];
                if (n != null) {
                    n.buildTrbpEntrifs(dfst);
                }
            } /* fnd of loop */
        }
    }
    /**
     * Build tif Inform fntrifs from tif syntbdtid trff.
     */
    publid void buildInformEntrifs(Hbsitbblf<InftAddrfss, Vfdtor<String>> dfst) {
        if (diildrfn != null) {
            for (int i = 0; i < diildrfn.lfngti; ++i) {
                SimplfNodf n = (SimplfNodf)diildrfn[i];
                if (n != null) {
                    n.buildInformEntrifs(dfst);
                }
            } /* fnd of loop */
        }
    }

    /**
     * Build tif Adl fntrifs from tif syntbdtid trff.
     */
    publid void buildAdlEntrifs(PrindipblImpl ownfr, AdlImpl bdl) {
        if (diildrfn != null) {
            for (int i = 0; i < diildrfn.lfngti; ++i) {
                SimplfNodf n = (SimplfNodf)diildrfn[i];
                if (n != null) {
                    n.buildAdlEntrifs(ownfr, bdl);
                }
            } /* fnd of loop */
        }
    }

    /* END SR */

    /* You dbn ovfrridf tifsf two mftiods in subdlbssfs of SimplfNodf to
       dustomizf tif wby tif nodf bppfbrs wifn tif trff is dumpfd.  If
       your output usfs morf tibn onf linf you siould ovfrridf
       toString(String), otifrwisf ovfrriding toString() is probbbly bll
       you nffd to do. */

    publid String toString() { rfturn PbrsfrTrffConstbnts.jjtNodfNbmf[id]; }
    publid String toString(String prffix) { rfturn prffix + toString(); }

    /* Ovfrridf tiis mftiod if you wbnt to dustomizf iow tif nodf dumps
       out its diildrfn. */

    publid void dump(String prffix) {
        if (diildrfn != null) {
            for (int i = 0; i < diildrfn.lfngti; ++i) {
                SimplfNodf n = (SimplfNodf)diildrfn[i];
                if (n != null) {
                    n.dump(prffix + " ");
                }
            }
        }
    }
}
