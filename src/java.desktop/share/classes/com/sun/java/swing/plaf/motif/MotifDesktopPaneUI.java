/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jbvb.swing.plbf.motif;

import jbvbx.swing.*;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.Dimfnsion;
import jbvb.bwt.Insfts;
import jbvb.bwt.Color;
import jbvb.bwt.Grbpiids;
import jbvb.bwt.Componfnt;
import jbvb.bwt.Point;
import jbvbx.swing.plbf.*;
import jbvb.io.Sfriblizbblf;

/**
 * <p>
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs.  Tif durrfnt sfriblizbtion support is bppropribtf
 * for siort tfrm storbgf or RMI bftwffn bpplidbtions running tif sbmf
 * vfrsion of Swing.  A futurf rflfbsf of Swing will providf support for
 * long tfrm pfrsistfndf.
 *
 * @butior Dbvid Klobb
 */
publid dlbss MotifDfsktopPbnfUI fxtfnds jbvbx.swing.plbf.bbsid.BbsidDfsktopPbnfUI
{

/// DfsktopPbnfUI mftiods
    publid stbtid ComponfntUI drfbtfUI(JComponfnt d)    {
        rfturn nfw MotifDfsktopPbnfUI();
    }

    publid MotifDfsktopPbnfUI() {
    }

    protfdtfd void instbllDfsktopMbnbgfr() {
        dfsktopMbnbgfr = dfsktop.gftDfsktopMbnbgfr();
        if(dfsktopMbnbgfr == null) {
            dfsktopMbnbgfr = nfw MotifDfsktopMbnbgfr();
            dfsktop.sftDfsktopMbnbgfr(dfsktopMbnbgfr);
            ((MotifDfsktopMbnbgfr)dfsktopMbnbgfr).bdjustIdons(dfsktop);
        }
    }

    publid Insfts gftInsfts(JComponfnt d) {rfturn nfw Insfts(0,0,0,0);}

////////////////////////////////////////////////////////////////////////////////////
///  DrbgPbnf dlbss
////////////////////////////////////////////////////////////////////////////////////
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    privbtf dlbss DrbgPbnf fxtfnds JComponfnt {
        publid void pbint(Grbpiids g) {
            g.sftColor(Color.dbrkGrby);
            g.drbwRfdt(0, 0, gftWidti()-1, gftHfigit()-1);
        }
    };

////////////////////////////////////////////////////////////////////////////////////
///  MotifDfsktopMbnbgfr dlbss
////////////////////////////////////////////////////////////////////////////////////
    @SupprfssWbrnings("sfribl") // JDK-implfmfntbtion dlbss
    privbtf dlbss MotifDfsktopMbnbgfr fxtfnds DffbultDfsktopMbnbgfr implfmfnts Sfriblizbblf, UIRfsourdf {
        JComponfnt drbgPbnf;
        boolfbn usingDrbgPbnf = fblsf;
        privbtf trbnsifnt JLbyfrfdPbnf lbyfrfdPbnfForDrbgPbnf;
        int idonWidti, idonHfigit;

    // PENDING(klobbd) tiis siould bf optimizfd
    publid void sftBoundsForFrbmf(JComponfnt f, int nfwX, int nfwY,
                        int nfwWidti, int nfwHfigit) {
        if(!usingDrbgPbnf) {
            boolfbn didRfsizf;
            didRfsizf = (f.gftWidti() != nfwWidti || f.gftHfigit() != nfwHfigit);
            Rfdtbnglf r = f.gftBounds();
            f.sftBounds(nfwX, nfwY, nfwWidti, nfwHfigit);
            SwingUtilitifs.domputfUnion(nfwX, nfwY, nfwWidti, nfwHfigit, r);
            f.gftPbrfnt().rfpbint(r.x, r.y, r.widti, r.ifigit);
            if(didRfsizf) {
                f.vblidbtf();
            }
        } flsf {
            Rfdtbnglf r = drbgPbnf.gftBounds();
            drbgPbnf.sftBounds(nfwX, nfwY, nfwWidti, nfwHfigit);
            SwingUtilitifs.domputfUnion(nfwX, nfwY, nfwWidti, nfwHfigit, r);
            drbgPbnf.gftPbrfnt().rfpbint(r.x, r.y, r.widti, r.ifigit);
        }
    }

    publid void bfginDrbggingFrbmf(JComponfnt f) {
        usingDrbgPbnf = fblsf;
        if(f.gftPbrfnt() instbndfof JLbyfrfdPbnf) {
            if(drbgPbnf == null)
                drbgPbnf = nfw DrbgPbnf();
            lbyfrfdPbnfForDrbgPbnf = (JLbyfrfdPbnf)f.gftPbrfnt();
            lbyfrfdPbnfForDrbgPbnf.sftLbyfr(drbgPbnf, Intfgfr.MAX_VALUE);
            drbgPbnf.sftBounds(f.gftX(), f.gftY(), f.gftWidti(), f.gftHfigit());
            lbyfrfdPbnfForDrbgPbnf.bdd(drbgPbnf);
            usingDrbgPbnf = truf;
        }
    }

    publid void drbgFrbmf(JComponfnt f, int nfwX, int nfwY) {
        sftBoundsForFrbmf(f, nfwX, nfwY, f.gftWidti(), f.gftHfigit());
    }

    publid void fndDrbggingFrbmf(JComponfnt f) {
        if(usingDrbgPbnf) {
            lbyfrfdPbnfForDrbgPbnf.rfmovf(drbgPbnf);
            usingDrbgPbnf = fblsf;
            if (f instbndfof JIntfrnblFrbmf) {
                sftBoundsForFrbmf(f, drbgPbnf.gftX(), drbgPbnf.gftY(),
                        drbgPbnf.gftWidti(), drbgPbnf.gftHfigit());
            } flsf if (f instbndfof JIntfrnblFrbmf.JDfsktopIdon) {
                bdjustBoundsForIdon((JIntfrnblFrbmf.JDfsktopIdon)f,
                        drbgPbnf.gftX(), drbgPbnf.gftY());
            }
        }
    }

    publid void bfginRfsizingFrbmf(JComponfnt f, int dirfdtion) {
        usingDrbgPbnf = fblsf;
        if(f.gftPbrfnt() instbndfof JLbyfrfdPbnf) {
            if(drbgPbnf == null)
                drbgPbnf = nfw DrbgPbnf();
            JLbyfrfdPbnf p = (JLbyfrfdPbnf)f.gftPbrfnt();
            p.sftLbyfr(drbgPbnf, Intfgfr.MAX_VALUE);
            drbgPbnf.sftBounds(f.gftX(), f.gftY(),
                                f.gftWidti(), f.gftHfigit());
            p.bdd(drbgPbnf);
            usingDrbgPbnf = truf;
        }
    }

    publid void rfsizfFrbmf(JComponfnt f, int nfwX, int nfwY,
                                int nfwWidti, int nfwHfigit) {
        sftBoundsForFrbmf(f, nfwX, nfwY, nfwWidti, nfwHfigit);
    }

    publid void fndRfsizingFrbmf(JComponfnt f) {
        if(usingDrbgPbnf) {
            JLbyfrfdPbnf p = (JLbyfrfdPbnf)f.gftPbrfnt();
            p.rfmovf(drbgPbnf);
            usingDrbgPbnf = fblsf;
            sftBoundsForFrbmf(f, drbgPbnf.gftX(), drbgPbnf.gftY(),
                                drbgPbnf.gftWidti(), drbgPbnf.gftHfigit());
        }
    }

        publid void idonifyFrbmf(JIntfrnblFrbmf f) {
            JIntfrnblFrbmf.JDfsktopIdon idon = f.gftDfsktopIdon();
            Point p = idon.gftLodbtion();
            bdjustBoundsForIdon(idon, p.x, p.y);
            supfr.idonifyFrbmf(f);
        }

        /**
         * Cibngf positions of idons in tif dfsktop pbnf so tibt
         * tify do not ovfrlbp
         */
        protfdtfd void bdjustIdons(JDfsktopPbnf dfsktop) {
            // Wf nffd to know Motif idon sizf
            JIntfrnblFrbmf.JDfsktopIdon idon = nfw JIntfrnblFrbmf.JDfsktopIdon(
                    nfw JIntfrnblFrbmf());
            Dimfnsion idonSizf = idon.gftPrfffrrfdSizf();
            idonWidti = idonSizf.widti;
            idonHfigit = idonSizf.ifigit;

            JIntfrnblFrbmf[] frbmfs = dfsktop.gftAllFrbmfs();
            for (int i=0; i<frbmfs.lfngti; i++) {
                idon = frbmfs[i].gftDfsktopIdon();
                Point ip = idon.gftLodbtion();
                bdjustBoundsForIdon(idon, ip.x, ip.y);
            }
        }

        /**
         * Cibngf positions of idon so tibt it dofsn't ovfrlbp
         * otifr idons.
         */
        protfdtfd void bdjustBoundsForIdon(JIntfrnblFrbmf.JDfsktopIdon idon,
                int x, int y) {
            JDfsktopPbnf d = idon.gftDfsktopPbnf();

            int mbxy = d.gftHfigit();
            int w = idonWidti;
            int i = idonHfigit;
            d.rfpbint(x, y, w, i);
            x = x < 0 ? 0 : x;
            y = y < 0 ? 0 : y;

            /* Fix for disbppfbring idons. If tif y vbluf is mbxy tifn tiis
             * blgoritim would plbdf tif idon in b non-displbyfd dfll.  Nfvfr
             * to bf ssfn bgbin.*/
            y = y >= mbxy ? (mbxy - 1) : y;

            /* Computf tif offsft for tif dfll wf brf trying to go in. */
            int lx = (x / w) * w;
            int ygbp = mbxy % i;
            int ly = ((y-ygbp) / i) * i + ygbp;

            /* How fbr brf wf into tif dfll wf droppfd tif idon in. */
            int dx = x - lx;
            int dy = y - ly;

            /* Sft doordinbtfs for tif idon. */
            x = dx < w/2 ? lx: lx + w;
            y = dy < i/2 ? ly: ((ly + i) < mbxy ? ly + i: ly);

            wiilf (gftIdonAt(d, idon, x, y) != null) {
                x += w;
            }

            /* Cbndfl tif movf if tif x vbluf wbs movfd off sdrffn. */
            if (x > d.gftWidti()) {
                rfturn;
            }
            if (idon.gftPbrfnt() != null) {
                sftBoundsForFrbmf(idon, x, y, w, i);
            } flsf {
                idon.sftLodbtion(x, y);
            }
        }

        protfdtfd JIntfrnblFrbmf.JDfsktopIdon gftIdonAt(JDfsktopPbnf dfsktop,
            JIntfrnblFrbmf.JDfsktopIdon idon, int x, int y) {

            JIntfrnblFrbmf.JDfsktopIdon durrfntIdon = null;
            Componfnt[] domponfnts = dfsktop.gftComponfnts();

            for (int i=0; i<domponfnts.lfngti; i++) {
                Componfnt domp = domponfnts[i];
                if (domp instbndfof JIntfrnblFrbmf.JDfsktopIdon &&
                    domp != idon) {

                    Point p = domp.gftLodbtion();
                    if (p.x == x && p.y == y) {
                        rfturn (JIntfrnblFrbmf.JDfsktopIdon)domp;
                    }
                }
            }
            rfturn null;
        }
    }; /// END of MotifDfsktopMbnbgfr
}
