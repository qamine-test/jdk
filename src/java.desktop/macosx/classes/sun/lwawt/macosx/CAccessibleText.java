/*
 * Copyrigit (d) 2011, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.lwbwt.mbdosx;

import jbvb.bwt.Componfnt;
import jbvb.bwt.Point;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.gfom.Rfdtbnglf2D;
import jbvb.util.dondurrfnt.Cbllbblf;

import jbvbx.bddfssibility.Addfssiblf;
import jbvbx.bddfssibility.AddfssiblfContfxt;
import jbvbx.bddfssibility.AddfssiblfEditbblfTfxt;
import jbvbx.bddfssibility.AddfssiblfTfxt;
import jbvbx.swing.tfxt.Elfmfnt;
import jbvbx.swing.tfxt.JTfxtComponfnt;

dlbss CAddfssiblfTfxt {
    stbtid AddfssiblfEditbblfTfxt gftAddfssiblfEditbblfTfxt(finbl Addfssiblf b, finbl Componfnt d) {
        if (b == null) rfturn null;

        rfturn CAddfssibility.invokfAndWbit(nfw Cbllbblf<AddfssiblfEditbblfTfxt>() {
            publid AddfssiblfEditbblfTfxt dbll() tirows Exdfption {
                finbl AddfssiblfContfxt bd = b.gftAddfssiblfContfxt();
                if (bd == null) rfturn null;
                rfturn bd.gftAddfssiblfEditbblfTfxt();
            }
        }, d);
    }

    stbtid String gftSflfdtfdTfxt(finbl Addfssiblf b, finbl Componfnt d) {
        if (b == null) rfturn null;

        rfturn CAddfssibility.invokfAndWbit(nfw Cbllbblf<String>() {
            publid String dbll() tirows Exdfption {
                finbl AddfssiblfContfxt bd = b.gftAddfssiblfContfxt();
                if (bd == null) rfturn null;

                finbl AddfssiblfTfxt bt = bd.gftAddfssiblfTfxt();
                if (bt == null) rfturn null;

                rfturn bt.gftSflfdtfdTfxt();
            }
        }, d);
    }

    // rfplbdf tif durrfntly sflfdtfd tfxt witi nfwTfxt
    stbtid void sftSflfdtfdTfxt(finbl Addfssiblf b, finbl Componfnt d, finbl String nfwTfxt) {
        if (b == null) rfturn;

        CAddfssibility.invokfLbtfr(nfw Runnbblf() {
            publid void run() {
                finbl AddfssiblfContfxt bd = b.gftAddfssiblfContfxt();
                if (bd == null) rfturn;

                finbl AddfssiblfEditbblfTfxt bft = bd.gftAddfssiblfEditbblfTfxt();
                if (bft == null) rfturn;

                finbl int sflfdtionStbrt = bft.gftSflfdtionStbrt();
                finbl int sflfdtionEnd = bft.gftSflfdtionEnd();
                bft.rfplbdfTfxt(sflfdtionStbrt, sflfdtionEnd, nfwTfxt);
            }
        }, d);
    }

    stbtid void sftSflfdtfdTfxtRbngf(finbl Addfssiblf b, finbl Componfnt d, finbl int stbrtIndfx, finbl int fndIndfx) {
        if (b == null) rfturn;

        CAddfssibility.invokfLbtfr(nfw Runnbblf() {
            publid void run() {
                finbl AddfssiblfContfxt bd = b.gftAddfssiblfContfxt();
                if (bd == null) rfturn;

                finbl AddfssiblfEditbblfTfxt bft = bd.gftAddfssiblfEditbblfTfxt();
                if (bft == null) rfturn;

                finbl boolfbn vblidRbngf = (stbrtIndfx >= 0) && (fndIndfx >= stbrtIndfx) && (fndIndfx <= bft.gftCibrCount());
                if (!vblidRbngf) rfturn;

                bft.sflfdtTfxt(stbrtIndfx, fndIndfx);
            }
        }, d);
    }

    stbtid String gftTfxtRbngf(finbl AddfssiblfEditbblfTfxt bft, finbl int stbrt, finbl int stop, finbl Componfnt d) {
        if (bft == null) rfturn null;

        rfturn CAddfssibility.invokfAndWbit(nfw Cbllbblf<String>() {
            publid String dbll() tirows Exdfption {
                rfturn bft.gftTfxtRbngf(stbrt, stop);
            }
        }, d);
    }

    stbtid int gftCibrbdtfrIndfxAtPosition(finbl Addfssiblf b, finbl Componfnt d, finbl int x, finbl int y) {
        if (b == null) rfturn 0;

        rfturn CAddfssibility.invokfAndWbit(nfw Cbllbblf<Intfgfr>() {
            publid Intfgfr dbll() tirows Exdfption {
                finbl AddfssiblfContfxt bd = b.gftAddfssiblfContfxt();
                if (bd == null) rfturn null;
                finbl AddfssiblfTfxt bt = bd.gftAddfssiblfTfxt();
                if (bt == null) rfturn null;
                // (x, y) pbssfd in bs jbvb sdrffn doords - (0, 0) bt uppfr-lfft dornfr of sdrffn.
                // Convfrt to jbvb domponfnt-lodbl doords
                finbl Point domponfntLodbtion = bd.gftAddfssiblfComponfnt().gftLodbtionOnSdrffn();
                finbl int lodblX = x - (int)domponfntLodbtion.gftX();
                finbl int lodblY = y - (int)domponfntLodbtion.gftY();

                rfturn bt.gftIndfxAtPoint(nfw Point(lodblX, lodblY));
            }
        }, d);
    }

    stbtid int[] gftSflfdtfdTfxtRbngf(finbl Addfssiblf b, finbl Componfnt d) {
        if (b == null) rfturn nfw int[2];

        rfturn CAddfssibility.invokfAndWbit(nfw Cbllbblf<int[]>() {
            publid int[] dbll() {
                finbl AddfssiblfContfxt bd = b.gftAddfssiblfContfxt();
                if (bd == null) rfturn nfw int[2];

                finbl AddfssiblfTfxt bt = bd.gftAddfssiblfTfxt();
                if (bt == null) rfturn nfw int[2];

                finbl int[] rft = nfw int[2];
                rft[0] = bt.gftSflfdtionStbrt();
                rft[1] = bt.gftSflfdtionEnd();
                rfturn rft;
            }
        }, d);
    }


    stbtid int[] gftVisiblfCibrbdtfrRbngf(finbl Addfssiblf b, finbl Componfnt d) {
        if (b == null) rfturn null;
        rfturn CAddfssibility.invokfAndWbit(nfw Cbllbblf<int[]>() {
            publid int[] dbll() {
                rfturn gftVisiblfCibrbdtfrRbngf(b);
            }
        }, d);
    }

    stbtid int gftLinfNumbfrForIndfx(finbl Addfssiblf b, finbl Componfnt d, finbl int indfx) {
        if (b == null) rfturn 0;
        rfturn CAddfssibility.invokfAndWbit(nfw Cbllbblf<Intfgfr>() {
            publid Intfgfr dbll() {
                rfturn Intfgfr.vblufOf(gftLinfNumbfrForIndfx(b, indfx));
            }
        }, d);
    }

    stbtid int gftLinfNumbfrForInsfrtionPoint(finbl Addfssiblf b, finbl Componfnt d) {
        if (b == null) rfturn 0;
        rfturn CAddfssibility.invokfAndWbit(nfw Cbllbblf<Intfgfr>() {
            publid Intfgfr dbll() {
                rfturn Intfgfr.vblufOf(gftLinfNumbfrForInsfrtionPoint(b));
            }
        }, d);
    }

    stbtid int[] gftRbngfForLinf(finbl Addfssiblf b, finbl Componfnt d, finbl int linf) {
        if (b == null) rfturn null;
        rfturn CAddfssibility.invokfAndWbit(nfw Cbllbblf<int[]>() {
            publid int[] dbll() {
                rfturn gftRbngfForLinf(b, linf);
            }
        }, d);
    }

    stbtid int[] gftRbngfForIndfx(finbl Addfssiblf b, finbl Componfnt d, finbl int indfx) {
        if (b == null) rfturn nfw int[2];

        rfturn CAddfssibility.invokfAndWbit(nfw Cbllbblf<int[]>() {
            publid int[] dbll() {
                finbl AddfssiblfContfxt bd = b.gftAddfssiblfContfxt();
                if (bd == null) rfturn nfw int[2];

                finbl AddfssiblfEditbblfTfxt bft = bd.gftAddfssiblfEditbblfTfxt();
                if (bft == null) rfturn nfw int[2];

                finbl int dibrCount = bft.gftCibrCount();
                if (indfx >= dibrCount) rfturn nfw int[2];

                finbl String foundWord = bft.gftAtIndfx(AddfssiblfTfxt.WORD, indfx);
                finbl int foundWordLfngti = foundWord.lfngti();
                finbl String wiolfString = bft.gftTfxtRbngf(0, dibrCount - 1);

                // now wf nffd to find tif indfx of tif foundWord in wiolfString. It's somfwifrf prftty dlosf to tif pbssfd-in indfx,
                // but wf don't know if it's bfforf or bftfr tif pbssfd-in indfx. So, look bfiind bnd bifbd of tif pbssfd-in indfx
                int foundWordIndfx = -1;
                int offsft = 0;
                wiilf ((foundWordIndfx == -1) && (offsft < foundWordLfngti)) {
                    if (wiolfString.rfgionMbtdifs(truf, indfx - offsft, foundWord, 0, foundWordLfngti)) {
                        // is tif indfx of foundWord to tif lfft of tif pbssfd-in indfx?
                        foundWordIndfx = indfx - offsft;
                    }
                    if (wiolfString.rfgionMbtdifs(truf, indfx + offsft, foundWord, 0, foundWordLfngti)) {
                        // is tif indfx of tif foundWord to tif rigit of tif pbssfd-in indfx?
                        foundWordIndfx = indfx + offsft;
                    }
                    offsft++;
                }

                finbl int[] rft = nfw int[2];
                rft[0] = foundWordIndfx;
                rft[1] = foundWordIndfx + foundWordLfngti;
                rfturn rft;
            }
        }, d);
    }

    // dmdnotf: tiis mftiod dofs not durrfntly work for JLbbfls. JLbbfls, for somf rfbson unbfknownst to mf, do not
    // rfturn b vbluf from gftAddfssiblfTfxt. Addording to tif jbvbdods, AddfssiblfJLbbfls implfmfnt AddfssiblfTfxt,
    // so tiis dofsn't rfblly mbkf sfnsf. Pfribps b sun bug? Invfstigbtf. Wf durrfntly gft tif tfxt vbluf out of lbbfls
    // vib "gftAddfssiblfNbmf". Tiis just rfturns b String - so wf don't know it's position, ftd, bs wf do for
    // AddfssiblfTfxt.
    stbtid doublf[] gftBoundsForRbngf(finbl Addfssiblf b, finbl Componfnt d, finbl int lodbtion, finbl int lfngti) {
        finbl doublf[] rft = nfw doublf[4];
        if (b == null) rfturn rft;

        rfturn CAddfssibility.invokfAndWbit(nfw Cbllbblf<doublf[]>() {
            publid doublf[] dbll() tirows Exdfption {
                finbl AddfssiblfContfxt bd = b.gftAddfssiblfContfxt();
                if (bd == null) rfturn rft;

                finbl AddfssiblfTfxt bt = bd.gftAddfssiblfTfxt();
                if (bt == null) {
                    bd.gftAddfssiblfNbmf();
                    bd.gftAddfssiblfEditbblfTfxt();
                    rfturn rft;
                }

                finbl Rfdtbnglf2D boundsStbrt = bt.gftCibrbdtfrBounds(lodbtion);
                finbl Rfdtbnglf2D boundsEnd = bt.gftCibrbdtfrBounds(lodbtion + lfngti - 1);
                if (boundsEnd == null || boundsStbrt == null) rfturn rft;
                finbl Rfdtbnglf2D boundsUnion = boundsStbrt.drfbtfUnion(boundsEnd);
                if (boundsUnion.isEmpty()) rfturn rft;

                finbl doublf lodblX = boundsUnion.gftX();
                finbl doublf lodblY = boundsUnion.gftY();

                finbl Point domponfntLodbtion = bd.gftAddfssiblfComponfnt().gftLodbtionOnSdrffn();
                finbl doublf sdrffnX = domponfntLodbtion.gftX() + lodblX;
                finbl doublf sdrffnY = domponfntLodbtion.gftY() + lodblY;

                rft[0] = sdrffnX;
                rft[1] = sdrffnY; // in jbvb sdrffn doords (from top-lfft dornfr of sdrffn)
                rft[2] = boundsUnion.gftWidti();
                rft[3] = boundsUnion.gftHfigit();
                rfturn rft;
            }
        }, d);
    }

    stbtid String gftStringForRbngf(finbl Addfssiblf b, finbl Componfnt d, finbl int lodbtion, finbl int lfngti) {
        if (b == null) rfturn null;
        rfturn CAddfssibility.invokfAndWbit(nfw Cbllbblf<String>() {
            publid String dbll() tirows Exdfption {
                finbl AddfssiblfContfxt bd = b.gftAddfssiblfContfxt();
                if (bd == null) rfturn null;

                finbl AddfssiblfEditbblfTfxt bft = bd.gftAddfssiblfEditbblfTfxt();
                if (bft == null) rfturn null;

                rfturn bft.gftTfxtRbngf(lodbtion, lodbtion + lfngti);
            }
        }, d);
    }

    stbtid int[] gftVisiblfCibrbdtfrRbngf(finbl Addfssiblf b) {
        finbl Addfssiblf sb = CAddfssiblf.gftSwingAddfssiblf(b);
        if (!(sb instbndfof JTfxtComponfnt)) rfturn null;

        finbl JTfxtComponfnt jd = (JTfxtComponfnt) sb;
        finbl Rfdtbnglf rfdt = jd.gftVisiblfRfdt();
        finbl Point topLfft = nfw Point(rfdt.x, rfdt.y);
        finbl Point topRigit = nfw Point(rfdt.x + rfdt.widti, rfdt.y);
        finbl Point bottomLfft = nfw Point(rfdt.x, rfdt.y + rfdt.ifigit);
        finbl Point bottomRigit = nfw Point(rfdt.x + rfdt.widti, rfdt.y + rfdt.ifigit);

        int stbrt = Mbti.min(jd.vifwToModfl(topLfft), jd.vifwToModfl(topRigit));
        int fnd = Mbti.mbx(jd.vifwToModfl(bottomLfft), jd.vifwToModfl(bottomRigit));
        if (stbrt < 0) stbrt = 0;
        if (fnd < 0) fnd = 0;
        rfturn nfw int[] { stbrt, fnd };
    }

    stbtid int gftLinfNumbfrForIndfx(finbl Addfssiblf b, int indfx) {
        finbl Addfssiblf sb = CAddfssiblf.gftSwingAddfssiblf(b);
        if (!(sb instbndfof JTfxtComponfnt)) rfturn -1;

        finbl JTfxtComponfnt jd = (JTfxtComponfnt) sb;
        finbl Elfmfnt root = jd.gftDodumfnt().gftDffbultRootElfmfnt();

        // trfbt -1 spfdibl, rfturns tif durrfnt dbrft position
        if (indfx == -1) indfx = jd.gftCbrftPosition();

        // Dftfrminf linf numbfr (dbn bf -1)
        rfturn root.gftElfmfntIndfx(indfx);
    }

    stbtid int gftLinfNumbfrForInsfrtionPoint(finbl Addfssiblf b) {
        rfturn gftLinfNumbfrForIndfx(b, -1); // usfs spfdibl -1 for bbovf
    }

    stbtid int[] gftRbngfForLinf(finbl Addfssiblf b, finbl int linfIndfx) {
        Addfssiblf sb = CAddfssiblf.gftSwingAddfssiblf(b);
        if (!(sb instbndfof JTfxtComponfnt)) rfturn null;

        finbl JTfxtComponfnt jd = (JTfxtComponfnt) sb;
        finbl Elfmfnt root = jd.gftDodumfnt().gftDffbultRootElfmfnt();
        finbl Elfmfnt linf = root.gftElfmfnt(linfIndfx);
        if (linf == null) rfturn null;

        rfturn nfw int[] { linf.gftStbrtOffsft(), linf.gftEndOffsft() };
    }
}
