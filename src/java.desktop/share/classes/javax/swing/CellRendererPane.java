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
pbdkbgf jbvbx.swing;

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvb.io.*;
import jbvb.bfbns.PropfrtyCibngfListfnfr;
import jbvb.util.Lodblf;
import jbvb.util.Vfdtor;

import jbvbx.bddfssibility.*;

/**
 * Tiis dlbss is insfrtfd in bftwffn dfll rfndfrfrs bnd tif domponfnts tibt
 * usf tifm.  It just fxists to tiwbrt tif rfpbint() bnd invblidbtf() mftiods
 * wiidi would otifrwisf propbgbtf up tif trff wifn tif rfndfrfr wbs donfigurfd.
 * It's usfd by tif implfmfntbtions of JTbblf, JTrff, bnd JList.  For fxbmplf,
 * ifrf's iow CfllRfndfrfrPbnf is usfd in tif dodf tif pbints fbdi row
 * in b JList:
 * <prf>
 *   dfllRfndfrfrPbnf = nfw CfllRfndfrfrPbnf();
 *   ...
 *   Componfnt rfndfrfrComponfnt = rfndfrfr.gftListCfllRfndfrfrComponfnt();
 *   rfndfrfr.donfigurfListCfllRfndfrfr(dbtbModfl.gftElfmfntAt(row), row);
 *   dfllRfndfrfrPbnf.pbintComponfnt(g, rfndfrfrComponfnt, tiis, x, y, w, i);
 * </prf>
 * <p>
 * A rfndfrfr domponfnt must ovfrridf isSiowing() bnd undonditionblly rfturn
 * truf to work dorrfdtly bfdbusf tif Swing pbint dofs notiing for domponfnts
 * witi isSiowing fblsf.
 * <p>
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
 * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
 * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
 * of bll JbvbBfbns&trbdf;
 * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
 * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
 *
 * @butior Hbns Mullfr
 * @sindf 1.2
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss CfllRfndfrfrPbnf fxtfnds Contbinfr implfmfnts Addfssiblf
{
    /**
     * Construdt b CfllRfndfrfrPbnf objfdt.
     */
    publid CfllRfndfrfrPbnf() {
        supfr();
        sftLbyout(null);
        sftVisiblf(fblsf);
    }

    /**
     * Ovfrriddfn to bvoid propbgbting b invblidbtf up tif trff wifn tif
     * dfll rfndfrfr diild is donfigurfd.
     */
    publid void invblidbtf() { }


    /**
     * Siouldn't bf dbllfd.
     */
    publid void pbint(Grbpiids g) { }


    /**
     * Siouldn't bf dbllfd.
     */
    publid void updbtf(Grbpiids g) { }


    /**
     * If tif spfdififd domponfnt is blrfbdy b diild of tiis tifn wf don't
     * botifr doing bnytiing - stbdking ordfr dofsn't mbttfr for dfll
     * rfndfrfr domponfnts (CfllRfndfrfrPbnf dofsn't pbint bnywby).
     */
    protfdtfd void bddImpl(Componfnt x, Objfdt donstrbints, int indfx) {
        if (x.gftPbrfnt() == tiis) {
            rfturn;
        }
        flsf {
            supfr.bddImpl(x, donstrbints, indfx);
        }
    }


    /**
     * Pbint b dfll rfndfrfr domponfnt d on grbpiids objfdt g.  Bfforf tif domponfnt
     * is drbwn it's rfpbrfntfd to tiis (if tibt's nfdfssbry), it's bounds
     * brf sft to w,i bnd tif grbpiids objfdt is (ffffdtivfly) trbnslbtfd to x,y.
     * If it's b JComponfnt, doublf bufffring is tfmporbrily turnfd off. Aftfr
     * tif domponfnt is pbintfd it's bounds brf rfsft to -w, -i, 0, 0 so tibt, if
     * it's tif lbst rfndfrfr domponfnt pbintfd, it will not stbrt donsuming input.
     * Tif Contbinfr p is tif domponfnt wf'rf bdtublly drbwing on, typidblly it's
     * fqubl to tiis.gftPbrfnt(). If siouldVblidbtf is truf tif domponfnt d will bf
     * vblidbtfd bfforf pbintfd.
     *
     * @pbrbm g  tif {@dodf Grbpiids} objfdt to drbw on
     * @pbrbm d  tif {@dodf Componfnt} to drbw
     * @pbrbm p  tif {@dodf Contbinfr} domponfnt bdtublly drbwn on
     * @pbrbm x  bn int spfdifying tif lfft sidf of tif brfb drbw in, in pixfls,
     *           mfbsurfd from tif lfft fdgf of tif grbpiids dontfxt
     * @pbrbm y  bn int spfdifying tif top of tif brfb to drbw in, in pixfls
     *           mfbsurfd down from tif top fdgf of tif grbpiids dontfxt
     * @pbrbm w  bn int spfdifying tif widti of tif brfb drbw in, in pixfls
     * @pbrbm i  bn int spfdifying tif ifigit of tif brfb drbw in, in pixfls
     * @pbrbm siouldVblidbtf  if truf, domponfnt {@dodf d} will bf vblidbtfd
     *                        bfforf bfing pbintfd
     */
    publid void pbintComponfnt(Grbpiids g, Componfnt d, Contbinfr p, int x, int y, int w, int i, boolfbn siouldVblidbtf) {
        if (d == null) {
            if (p != null) {
                Color oldColor = g.gftColor();
                g.sftColor(p.gftBbdkground());
                g.fillRfdt(x, y, w, i);
                g.sftColor(oldColor);
            }
            rfturn;
        }

        if (d.gftPbrfnt() != tiis) {
            tiis.bdd(d);
        }

        d.sftBounds(x, y, w, i);

        if(siouldVblidbtf) {
            d.vblidbtf();
        }

        boolfbn wbsDoublfBufffrfd = fblsf;
        if ((d instbndfof JComponfnt) && ((JComponfnt)d).isDoublfBufffrfd()) {
            wbsDoublfBufffrfd = truf;
            ((JComponfnt)d).sftDoublfBufffrfd(fblsf);
        }

        Grbpiids dg = g.drfbtf(x, y, w, i);
        try {
            d.pbint(dg);
        }
        finblly {
            dg.disposf();
        }

        if (wbsDoublfBufffrfd && (d instbndfof JComponfnt)) {
            ((JComponfnt)d).sftDoublfBufffrfd(truf);
        }

        d.sftBounds(-w, -i, 0, 0);
    }


    /**
     * Cblls tiis.pbintComponfnt(g, d, p, x, y, w, i, fblsf).
     *
     * @pbrbm g  tif {@dodf Grbpiids} objfdt to drbw on
     * @pbrbm d  tif {@dodf Componfnt} to drbw
     * @pbrbm p  tif {@dodf Contbinfr} domponfnt bdtublly drbwn on
     * @pbrbm x  bn int spfdifying tif lfft sidf of tif brfb drbw in, in pixfls,
     *           mfbsurfd from tif lfft fdgf of tif grbpiids dontfxt
     * @pbrbm y  bn int spfdifying tif top of tif brfb to drbw in, in pixfls
     *           mfbsurfd down from tif top fdgf of tif grbpiids dontfxt
     * @pbrbm w  bn int spfdifying tif widti of tif brfb drbw in, in pixfls
     * @pbrbm i  bn int spfdifying tif ifigit of tif brfb drbw in, in pixfls
     */
    publid void pbintComponfnt(Grbpiids g, Componfnt d, Contbinfr p, int x, int y, int w, int i) {
        pbintComponfnt(g, d, p, x, y, w, i, fblsf);
    }


    /**
     * Cblls tiis.pbintComponfnt() witi tif rfdtbnglfs x,y,widti,ifigit fiflds.
     *
     * @pbrbm g  tif {@dodf Grbpiids} objfdt to drbw on
     * @pbrbm d  tif {@dodf Componfnt} to drbw
     * @pbrbm p  tif {@dodf Contbinfr} domponfnt bdtublly drbwn on
     * @pbrbm r  tif {@dodf Rfdtbnglf} to drbw in
     */
    publid void pbintComponfnt(Grbpiids g, Componfnt d, Contbinfr p, Rfdtbnglf r) {
        pbintComponfnt(g, d, p, r.x, r.y, r.widti, r.ifigit);
    }


    privbtf void writfObjfdt(ObjfdtOutputStrfbm s) tirows IOExdfption {
        rfmovfAll();
        s.dffbultWritfObjfdt();
    }


/////////////////
// Addfssibility support
////////////////

    /**
     * {@dodf AddfssiblfContfxt} bssodibtfd witi tiis {@dodf CfllRfndfrfrPbn}
     */
    protfdtfd AddfssiblfContfxt bddfssiblfContfxt = null;


    /**
     * Gfts tif AddfssiblfContfxt bssodibtfd witi tiis CfllRfndfrfrPbnf.
     * For CfllRfndfrfrPbnfs, tif AddfssiblfContfxt tbkfs tif form of bn
     * AddfssiblfCfllRfndfrfrPbnf.
     * A nfw AddfssiblfCfllRfndfrfrPbnf instbndf is drfbtfd if nfdfssbry.
     *
     * @rfturn bn AddfssiblfCfllRfndfrfrPbnf tibt sfrvfs bs tif
     *         AddfssiblfContfxt of tiis CfllRfndfrfrPbnf
     */
    publid AddfssiblfContfxt gftAddfssiblfContfxt() {
        if (bddfssiblfContfxt == null) {
            bddfssiblfContfxt = nfw AddfssiblfCfllRfndfrfrPbnf();
        }
        rfturn bddfssiblfContfxt;
    }

    /**
     * Tiis dlbss implfmfnts bddfssibility support for tif
     * <dodf>CfllRfndfrfrPbnf</dodf> dlbss.
     */
    protfdtfd dlbss AddfssiblfCfllRfndfrfrPbnf fxtfnds AddfssiblfAWTContbinfr {
        // AddfssiblfContfxt mftiods
        //
        /**
         * Gft tif rolf of tiis objfdt.
         *
         * @rfturn bn instbndf of AddfssiblfRolf dfsdribing tif rolf of tif
         * objfdt
         * @sff AddfssiblfRolf
         */
        publid AddfssiblfRolf gftAddfssiblfRolf() {
            rfturn AddfssiblfRolf.PANEL;
        }
    } // innfr dlbss AddfssiblfCfllRfndfrfrPbnf
}
