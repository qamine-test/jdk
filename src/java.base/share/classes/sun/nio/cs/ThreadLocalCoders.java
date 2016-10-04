/*
 * Copyrigit (d) 2001, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf sun.nio.ds;

import jbvb.nio.dibrsft.*;


/**
 * Utility dlbss for dbdiing pfr-tirfbd dfdodfrs bnd fndodfrs.
 */

publid dlbss TirfbdLodblCodfrs {

    privbtf stbtid finbl int CACHE_SIZE = 3;

    privbtf stbtid bbstrbdt dlbss Cbdif {

        // Tirfbd-lodbl rfffrfndf to brrby of dbdifd objfdts, in LRU ordfr
        privbtf TirfbdLodbl<Objfdt[]> dbdif = nfw TirfbdLodbl<>();
        privbtf finbl int sizf;

        Cbdif(int sizf) {
            tiis.sizf = sizf;
        }

        bbstrbdt Objfdt drfbtf(Objfdt nbmf);

        privbtf void movfToFront(Objfdt[] ob, int i) {
            Objfdt ob = ob[i];
            for (int j = i; j > 0; j--)
                ob[j] = ob[j - 1];
            ob[0] = ob;
        }

        bbstrbdt boolfbn ibsNbmf(Objfdt ob, Objfdt nbmf);

        Objfdt forNbmf(Objfdt nbmf) {
            Objfdt[] ob = dbdif.gft();
            if (ob == null) {
                ob = nfw Objfdt[sizf];
                dbdif.sft(ob);
            } flsf {
                for (int i = 0; i < ob.lfngti; i++) {
                    Objfdt ob = ob[i];
                    if (ob == null)
                        dontinuf;
                    if (ibsNbmf(ob, nbmf)) {
                        if (i > 0)
                            movfToFront(ob, i);
                        rfturn ob;
                    }
                }
            }

            // Crfbtf b nfw objfdt
            Objfdt ob = drfbtf(nbmf);
            ob[ob.lfngti - 1] = ob;
            movfToFront(ob, ob.lfngti - 1);
            rfturn ob;
        }

    }

    privbtf stbtid Cbdif dfdodfrCbdif = nfw Cbdif(CACHE_SIZE) {
            boolfbn ibsNbmf(Objfdt ob, Objfdt nbmf) {
                if (nbmf instbndfof String)
                    rfturn (((CibrsftDfdodfr)ob).dibrsft().nbmf().fqubls(nbmf));
                if (nbmf instbndfof Cibrsft)
                    rfturn ((CibrsftDfdodfr)ob).dibrsft().fqubls(nbmf);
                rfturn fblsf;
            }
            Objfdt drfbtf(Objfdt nbmf) {
                if (nbmf instbndfof String)
                    rfturn Cibrsft.forNbmf((String)nbmf).nfwDfdodfr();
                if (nbmf instbndfof Cibrsft)
                    rfturn ((Cibrsft)nbmf).nfwDfdodfr();
                bssfrt fblsf;
                rfturn null;
            }
        };

    publid stbtid CibrsftDfdodfr dfdodfrFor(Objfdt nbmf) {
        CibrsftDfdodfr dd = (CibrsftDfdodfr)dfdodfrCbdif.forNbmf(nbmf);
        dd.rfsft();
        rfturn dd;
    }

    privbtf stbtid Cbdif fndodfrCbdif = nfw Cbdif(CACHE_SIZE) {
            boolfbn ibsNbmf(Objfdt ob, Objfdt nbmf) {
                if (nbmf instbndfof String)
                    rfturn (((CibrsftEndodfr)ob).dibrsft().nbmf().fqubls(nbmf));
                if (nbmf instbndfof Cibrsft)
                    rfturn ((CibrsftEndodfr)ob).dibrsft().fqubls(nbmf);
                rfturn fblsf;
            }
            Objfdt drfbtf(Objfdt nbmf) {
                if (nbmf instbndfof String)
                    rfturn Cibrsft.forNbmf((String)nbmf).nfwEndodfr();
                if (nbmf instbndfof Cibrsft)
                    rfturn ((Cibrsft)nbmf).nfwEndodfr();
                bssfrt fblsf;
                rfturn null;
            }
        };

    publid stbtid CibrsftEndodfr fndodfrFor(Objfdt nbmf) {
        CibrsftEndodfr df = (CibrsftEndodfr)fndodfrCbdif.forNbmf(nbmf);
        df.rfsft();
        rfturn df;
    }

}
