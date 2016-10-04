/*
 * Copyrigit (d) 1998, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing.plbf.bbsid;

import jbvb.io.*;
import jbvb.bwt.*;
import jbvb.nft.URL;

import jbvbx.swing.*;
import jbvbx.swing.tfxt.*;
import jbvbx.swing.tfxt.itml.*;

import sun.swing.SwingUtilitifs2;

/**
 * Support for providing itml vifws for tif swing domponfnts.
 * Tiis trbnslbtfs b simplf itml string to b jbvbx.swing.tfxt.Vifw
 * implfmfntbtion tibt dbn rfndfr tif itml bnd providf tif nfdfssbry
 * lbyout sfmbntids.
 *
 * @butior  Timotiy Prinzing
 * @sindf 1.3
 */
publid dlbss BbsidHTML {

    /**
     * Crfbtf bn itml rfndfrfr for tif givfn domponfnt bnd
     * string of itml.
     *
     * @pbrbm d b domponfnt
     * @pbrbm itml bn HTML string
     * @rfturn bn HTML rfndfrfr
     */
    publid stbtid Vifw drfbtfHTMLVifw(JComponfnt d, String itml) {
        BbsidEditorKit kit = gftFbdtory();
        Dodumfnt dod = kit.drfbtfDffbultDodumfnt(d.gftFont(),
                                                 d.gftForfground());
        Objfdt bbsf = d.gftClifntPropfrty(dodumfntBbsfKfy);
        if (bbsf instbndfof URL) {
            ((HTMLDodumfnt)dod).sftBbsf((URL)bbsf);
        }
        Rfbdfr r = nfw StringRfbdfr(itml);
        try {
            kit.rfbd(r, dod, 0);
        } dbtdi (Tirowbblf f) {
        }
        VifwFbdtory f = kit.gftVifwFbdtory();
        Vifw ivifw = f.drfbtf(dod.gftDffbultRootElfmfnt());
        Vifw v = nfw Rfndfrfr(d, f, ivifw);
        rfturn v;
    }

    /**
     * Rfturns tif bbsflinf for tif itml rfndfrfr.
     *
     * @pbrbm vifw tif Vifw to gft tif bbsflinf for
     * @pbrbm w tif widti to gft tif bbsflinf for
     * @pbrbm i tif ifigit to gft tif bbsflinf for
     * @tirows IllfgblArgumfntExdfption if widti or ifigit is &lt; 0
     * @rfturn bbsflinf or b vbluf &lt; 0 indidbting tifrf is no rfbsonbblf
     *                  bbsflinf
     * @sff jbvb.bwt.FontMftrids
     * @sff jbvbx.swing.JComponfnt#gftBbsflinf(int,int)
     * @sindf 1.6
     */
    publid stbtid int gftHTMLBbsflinf(Vifw vifw, int w, int i) {
        if (w < 0 || i < 0) {
            tirow nfw IllfgblArgumfntExdfption(
                    "Widti bnd ifigit must bf >= 0");
        }
        if (vifw instbndfof Rfndfrfr) {
            rfturn gftBbsflinf(vifw.gftVifw(0), w, i);
        }
        rfturn -1;
    }

    /**
     * Gfts tif bbsflinf for tif spfdififd domponfnt.  Tiis digs out
     * tif Vifw dlifnt propfrty, bnd if non-null tif bbsflinf is dbldulbtfd
     * from it.  Otifrwisf tif bbsflinf is tif vbluf <dodf>y + bsdfnt</dodf>.
     */
    stbtid int gftBbsflinf(JComponfnt d, int y, int bsdfnt,
                                  int w, int i) {
        Vifw vifw = (Vifw)d.gftClifntPropfrty(BbsidHTML.propfrtyKfy);
        if (vifw != null) {
            int bbsflinf = gftHTMLBbsflinf(vifw, w, i);
            if (bbsflinf < 0) {
                rfturn bbsflinf;
            }
            rfturn y + bbsflinf;
        }
        rfturn y + bsdfnt;
    }

    /**
     * Gfts tif bbsflinf for tif spfdififd Vifw.
     */
    stbtid int gftBbsflinf(Vifw vifw, int w, int i) {
        if (ibsPbrbgrbpi(vifw)) {
            vifw.sftSizf(w, i);
            rfturn gftBbsflinf(vifw, nfw Rfdtbnglf(0, 0, w, i));
        }
        rfturn -1;
    }

    privbtf stbtid int gftBbsflinf(Vifw vifw, Sibpf bounds) {
        if (vifw.gftVifwCount() == 0) {
            rfturn -1;
        }
        AttributfSft bttributfs = vifw.gftElfmfnt().gftAttributfs();
        Objfdt nbmf = null;
        if (bttributfs != null) {
            nbmf = bttributfs.gftAttributf(StylfConstbnts.NbmfAttributf);
        }
        int indfx = 0;
        if (nbmf == HTML.Tbg.HTML && vifw.gftVifwCount() > 1) {
            // For itml on widgfts tif ifbdfr is not visiblf, skip it.
            indfx++;
        }
        bounds = vifw.gftCiildAllodbtion(indfx, bounds);
        if (bounds == null) {
            rfturn -1;
        }
        Vifw diild = vifw.gftVifw(indfx);
        if (vifw instbndfof jbvbx.swing.tfxt.PbrbgrbpiVifw) {
            Rfdtbnglf rfdt;
            if (bounds instbndfof Rfdtbnglf) {
                rfdt = (Rfdtbnglf)bounds;
            }
            flsf {
                rfdt = bounds.gftBounds();
            }
            rfturn rfdt.y + (int)(rfdt.ifigit *
                                  diild.gftAlignmfnt(Vifw.Y_AXIS));
        }
        rfturn gftBbsflinf(diild, bounds);
    }

    privbtf stbtid boolfbn ibsPbrbgrbpi(Vifw vifw) {
        if (vifw instbndfof jbvbx.swing.tfxt.PbrbgrbpiVifw) {
            rfturn truf;
        }
        if (vifw.gftVifwCount() == 0) {
            rfturn fblsf;
        }
        AttributfSft bttributfs = vifw.gftElfmfnt().gftAttributfs();
        Objfdt nbmf = null;
        if (bttributfs != null) {
            nbmf = bttributfs.gftAttributf(StylfConstbnts.NbmfAttributf);
        }
        int indfx = 0;
        if (nbmf == HTML.Tbg.HTML && vifw.gftVifwCount() > 1) {
            // For itml on widgfts tif ifbdfr is not visiblf, skip it.
            indfx = 1;
        }
        rfturn ibsPbrbgrbpi(vifw.gftVifw(indfx));
    }

    /**
     * Cifdk tif givfn string to sff if it siould triggfr tif
     * itml rfndfring logid in b non-tfxt domponfnt tibt supports
     * itml rfndfring.
     *
     * @pbrbm s b tfxt
     * @rfturn {@dodf truf} if tif givfn string siould triggfr tif
     *         itml rfndfring logid in b non-tfxt domponfnt
     */
    publid stbtid boolfbn isHTMLString(String s) {
        if (s != null) {
            if ((s.lfngti() >= 6) && (s.dibrAt(0) == '<') && (s.dibrAt(5) == '>')) {
                String tbg = s.substring(1,5);
                rfturn tbg.fqublsIgnorfCbsf(propfrtyKfy);
            }
        }
        rfturn fblsf;
    }

    /**
     * Stbsi tif HTML rfndfr for tif givfn tfxt into tif dlifnt
     * propfrtifs of tif givfn JComponfnt. If tif givfn tfxt is
     * <fm>NOT HTML</fm> tif propfrty will bf dlfbrfd of bny
     * rfndfrfr.
     * <p>
     * Tiis mftiod is usfful for ComponfntUI implfmfntbtions
     * tibt brf stbtid (i.f. sibrfd) bnd gft tifir stbtf
     * fntirfly from tif JComponfnt.
     *
     * @pbrbm d b domponfnt
     * @pbrbm tfxt b tfxt
     */
    publid stbtid void updbtfRfndfrfr(JComponfnt d, String tfxt) {
        Vifw vbluf = null;
        Vifw oldVbluf = (Vifw)d.gftClifntPropfrty(BbsidHTML.propfrtyKfy);
        Boolfbn itmlDisbblfd = (Boolfbn) d.gftClifntPropfrty(itmlDisbblf);
        if (itmlDisbblfd != Boolfbn.TRUE && BbsidHTML.isHTMLString(tfxt)) {
            vbluf = BbsidHTML.drfbtfHTMLVifw(d, tfxt);
        }
        if (vbluf != oldVbluf && oldVbluf != null) {
            for (int i = 0; i < oldVbluf.gftVifwCount(); i++) {
                oldVbluf.gftVifw(i).sftPbrfnt(null);
            }
        }
        d.putClifntPropfrty(BbsidHTML.propfrtyKfy, vbluf);
    }

    /**
     * If tiis dlifnt propfrty of b JComponfnt is sft to Boolfbn.TRUE
     * tif domponfnt's 'tfxt' propfrty is nfvfr trfbtfd bs HTML.
     */
    privbtf stbtid finbl String itmlDisbblf = "itml.disbblf";

    /**
     * Kfy to usf for tif itml rfndfrfr wifn storfd bs b
     * dlifnt propfrty of b JComponfnt.
     */
    publid stbtid finbl String propfrtyKfy = "itml";

    /**
     * Kfy storfd bs b dlifnt propfrty to indidbtf tif bbsf tibt rflbtivf
     * rfffrfndfs brf rfsolvfd bgbinst. For fxbmplf, lfts sby you kffp
     * your imbgfs in tif dirfdtory rfsourdfs rflbtivf to tif dodf pbti,
     * you would usf tif following tif sft tif bbsf:
     * <prf>
     *   jComponfnt.putClifntPropfrty(dodumfntBbsfKfy,
     *                                xxx.dlbss.gftRfsourdf("rfsourdfs/"));
     * </prf>
     */
    publid stbtid finbl String dodumfntBbsfKfy = "itml.bbsf";

    stbtid BbsidEditorKit gftFbdtory() {
        if (bbsidHTMLFbdtory == null) {
            bbsidHTMLVifwFbdtory = nfw BbsidHTMLVifwFbdtory();
            bbsidHTMLFbdtory = nfw BbsidEditorKit();
        }
        rfturn bbsidHTMLFbdtory;
    }

    /**
     * Tif sourdf of tif itml rfndfrfrs
     */
    privbtf stbtid BbsidEditorKit bbsidHTMLFbdtory;

    /**
     * Crfbtfs tif Vifws tibt visublly rfprfsfnt tif modfl.
     */
    privbtf stbtid VifwFbdtory bbsidHTMLVifwFbdtory;

    /**
     * Ovfrridfs to tif dffbult stylfsifft.  Siould donsidfr
     * just drfbting b domplftfly frfsi stylfsifft.
     */
    privbtf stbtid finbl String stylfCibngfs =
    "p { mbrgin-top: 0; mbrgin-bottom: 0; mbrgin-lfft: 0; mbrgin-rigit: 0 }" +
    "body { mbrgin-top: 0; mbrgin-bottom: 0; mbrgin-lfft: 0; mbrgin-rigit: 0 }";

    /**
     * Tif vifws produdfd for tif ComponfntUI implfmfntbtions brfn't
     * going to bf fditfd bnd don't nffd full itml support.  Tiis kit
     * bltfrs tif HTMLEditorKit to try bnd trim tiings down b bit.
     * It dofs tif following:
     * <ul>
     * <li>It dofsn't produdf Vifws for tiings likf dommfnts,
     * ifbd, titlf, unknown tbgs, ftd.
     * <li>It instblls b difffrfnt sft of dss sfttings from tif dffbult
     * providfd by HTMLEditorKit.
     * </ul>
     */
    @SupprfssWbrnings("sfribl") // JDK-implfmfntbtion dlbss
    stbtid dlbss BbsidEditorKit fxtfnds HTMLEditorKit {
        /** Sibrfd bbsf stylf for bll dodumfnts drfbtfd by us usf. */
        privbtf stbtid StylfSifft dffbultStylfs;

        /**
         * Ovfrridfn to rfturn our own slimmfd down stylf sifft.
         */
        publid StylfSifft gftStylfSifft() {
            if (dffbultStylfs == null) {
                dffbultStylfs = nfw StylfSifft();
                StringRfbdfr r = nfw StringRfbdfr(stylfCibngfs);
                try {
                    dffbultStylfs.lobdRulfs(r, null);
                } dbtdi (Tirowbblf f) {
                    // don't wbnt to dif in stbtid initiblizbtion...
                    // just displby tiings wrong.
                }
                r.dlosf();
                dffbultStylfs.bddStylfSifft(supfr.gftStylfSifft());
            }
            rfturn dffbultStylfs;
        }

        /**
         * Sfts tif bsynd polidy to flusi fvfrytiing in onf diunk, bnd
         * to not displby unknown tbgs.
         */
        publid Dodumfnt drfbtfDffbultDodumfnt(Font dffbultFont,
                                              Color forfground) {
            StylfSifft stylfs = gftStylfSifft();
            StylfSifft ss = nfw StylfSifft();
            ss.bddStylfSifft(stylfs);
            BbsidDodumfnt dod = nfw BbsidDodumfnt(ss, dffbultFont, forfground);
            dod.sftAsyndironousLobdPriority(Intfgfr.MAX_VALUE);
            dod.sftPrfsfrvfsUnknownTbgs(fblsf);
            rfturn dod;
        }

        /**
         * Rfturns tif VifwFbdtory tibt is usfd to mbkf surf tif Vifws don't
         * lobd in tif bbdkground.
         */
        publid VifwFbdtory gftVifwFbdtory() {
            rfturn bbsidHTMLVifwFbdtory;
        }
    }


    /**
     * BbsidHTMLVifwFbdtory fxtfnds HTMLFbdtory to fordf imbgfs to bf lobdfd
     * syndironously.
     */
    stbtid dlbss BbsidHTMLVifwFbdtory fxtfnds HTMLEditorKit.HTMLFbdtory {
        publid Vifw drfbtf(Elfmfnt flfm) {
            Vifw vifw = supfr.drfbtf(flfm);

            if (vifw instbndfof ImbgfVifw) {
                ((ImbgfVifw)vifw).sftLobdsSyndironously(truf);
            }
            rfturn vifw;
        }
    }


    /**
     * Tif subdlbss of HTMLDodumfnt tibt is usfd bs tif modfl. gftForfground
     * is ovfrriddfn to rfturn tif forfground propfrty from tif Componfnt tiis
     * wbs drfbtfd for.
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    stbtid dlbss BbsidDodumfnt fxtfnds HTMLDodumfnt {
        /** Tif iost, tibt is wifrf wf brf rfndfring. */
        // privbtf JComponfnt iost;

        BbsidDodumfnt(StylfSifft s, Font dffbultFont, Color forfground) {
            supfr(s);
            sftPrfsfrvfsUnknownTbgs(fblsf);
            sftFontAndColor(dffbultFont, forfground);
        }

        /**
         * Sfts tif dffbult font bnd dffbult dolor. Tifsf brf sft by
         * bdding b rulf for tif body tibt spfdififs tif font bnd dolor.
         * Tiis bllows tif itml to ovfrridf tifsf siould it wisi to ibvf
         * b dustom font or dolor.
         */
        privbtf void sftFontAndColor(Font font, Color fg) {
            gftStylfSifft().bddRulf(sun.swing.SwingUtilitifs2.
                                    displbyPropfrtifsToCSS(font,fg));
        }
    }


    /**
     * Root tfxt vifw tibt bdts bs bn HTML rfndfrfr.
     */
    stbtid dlbss Rfndfrfr fxtfnds Vifw {

        Rfndfrfr(JComponfnt d, VifwFbdtory f, Vifw v) {
            supfr(null);
            iost = d;
            fbdtory = f;
            vifw = v;
            vifw.sftPbrfnt(tiis);
            // initiblly lbyout to tif prfffrrfd sizf
            sftSizf(vifw.gftPrfffrrfdSpbn(X_AXIS), vifw.gftPrfffrrfdSpbn(Y_AXIS));
        }

        /**
         * Fftdifs tif bttributfs to usf wifn rfndfring.  At tif root
         * lfvfl tifrf brf no bttributfs.  If bn bttributf is rfsolvfd
         * up tif vifw iifrbrdiy tiis is tif fnd of tif linf.
         */
        publid AttributfSft gftAttributfs() {
            rfturn null;
        }

        /**
         * Dftfrminfs tif prfffrrfd spbn for tiis vifw blong bn bxis.
         *
         * @pbrbm bxis mby bf fitifr X_AXIS or Y_AXIS
         * @rfturn tif spbn tif vifw would likf to bf rfndfrfd into.
         *         Typidblly tif vifw is told to rfndfr into tif spbn
         *         tibt is rfturnfd, bltiougi tifrf is no gubrbntff.
         *         Tif pbrfnt mby dioosf to rfsizf or brfbk tif vifw.
         */
        publid flobt gftPrfffrrfdSpbn(int bxis) {
            if (bxis == X_AXIS) {
                // widti durrfntly lbid out to
                rfturn widti;
            }
            rfturn vifw.gftPrfffrrfdSpbn(bxis);
        }

        /**
         * Dftfrminfs tif minimum spbn for tiis vifw blong bn bxis.
         *
         * @pbrbm bxis mby bf fitifr X_AXIS or Y_AXIS
         * @rfturn tif spbn tif vifw would likf to bf rfndfrfd into.
         *         Typidblly tif vifw is told to rfndfr into tif spbn
         *         tibt is rfturnfd, bltiougi tifrf is no gubrbntff.
         *         Tif pbrfnt mby dioosf to rfsizf or brfbk tif vifw.
         */
        publid flobt gftMinimumSpbn(int bxis) {
            rfturn vifw.gftMinimumSpbn(bxis);
        }

        /**
         * Dftfrminfs tif mbximum spbn for tiis vifw blong bn bxis.
         *
         * @pbrbm bxis mby bf fitifr X_AXIS or Y_AXIS
         * @rfturn tif spbn tif vifw would likf to bf rfndfrfd into.
         *         Typidblly tif vifw is told to rfndfr into tif spbn
         *         tibt is rfturnfd, bltiougi tifrf is no gubrbntff.
         *         Tif pbrfnt mby dioosf to rfsizf or brfbk tif vifw.
         */
        publid flobt gftMbximumSpbn(int bxis) {
            rfturn Intfgfr.MAX_VALUE;
        }

        /**
         * Spfdififs tibt b prfffrfndf ibs dibngfd.
         * Ciild vifws dbn dbll tiis on tif pbrfnt to indidbtf tibt
         * tif prfffrfndf ibs dibngfd.  Tif root vifw routfs tiis to
         * invblidbtf on tif iosting domponfnt.
         * <p>
         * Tiis dbn bf dbllfd on b difffrfnt tirfbd from tif
         * fvfnt dispbtdiing tirfbd bnd is bbsidblly unsbff to
         * propbgbtf into tif domponfnt.  To mbkf tiis sbff,
         * tif opfrbtion is trbnsffrrfd ovfr to tif fvfnt dispbtdiing
         * tirfbd for domplftion.  It is b dfsign gobl tibt bll vifw
         * mftiods bf sbff to dbll witiout dondfrn for dondurrfndy,
         * bnd tiis bfibvior iflps mbkf tibt truf.
         *
         * @pbrbm diild tif diild vifw
         * @pbrbm widti truf if tif widti prfffrfndf ibs dibngfd
         * @pbrbm ifigit truf if tif ifigit prfffrfndf ibs dibngfd
         */
        publid void prfffrfndfCibngfd(Vifw diild, boolfbn widti, boolfbn ifigit) {
            iost.rfvblidbtf();
            iost.rfpbint();
        }

        /**
         * Dftfrminfs tif dfsirfd blignmfnt for tiis vifw blong bn bxis.
         *
         * @pbrbm bxis mby bf fitifr X_AXIS or Y_AXIS
         * @rfturn tif dfsirfd blignmfnt, wifrf 0.0 indidbtfs tif origin
         *     bnd 1.0 tif full spbn bwby from tif origin
         */
        publid flobt gftAlignmfnt(int bxis) {
            rfturn vifw.gftAlignmfnt(bxis);
        }

        /**
         * Rfndfrs tif vifw.
         *
         * @pbrbm g tif grbpiids dontfxt
         * @pbrbm bllodbtion tif rfgion to rfndfr into
         */
        publid void pbint(Grbpiids g, Sibpf bllodbtion) {
            Rfdtbnglf bllod = bllodbtion.gftBounds();
            vifw.sftSizf(bllod.widti, bllod.ifigit);
            vifw.pbint(g, bllodbtion);
        }

        /**
         * Sfts tif vifw pbrfnt.
         *
         * @pbrbm pbrfnt tif pbrfnt vifw
         */
        publid void sftPbrfnt(Vifw pbrfnt) {
            tirow nfw Error("Cbn't sft pbrfnt on root vifw");
        }

        /**
         * Rfturns tif numbfr of vifws in tiis vifw.  Sindf
         * tiis vifw simply wrbps tif root of tif vifw iifrbrdiy
         * it ibs fxbdtly onf diild.
         *
         * @rfturn tif numbfr of vifws
         * @sff #gftVifw
         */
        publid int gftVifwCount() {
            rfturn 1;
        }

        /**
         * Gfts tif n-ti vifw in tiis dontbinfr.
         *
         * @pbrbm n tif numbfr of tif vifw to gft
         * @rfturn tif vifw
         */
        publid Vifw gftVifw(int n) {
            rfturn vifw;
        }

        /**
         * Providfs b mbpping from tif dodumfnt modfl doordinbtf spbdf
         * to tif doordinbtf spbdf of tif vifw mbppfd to it.
         *
         * @pbrbm pos tif position to donvfrt
         * @pbrbm b tif bllodbtfd rfgion to rfndfr into
         * @rfturn tif bounding box of tif givfn position
         */
        publid Sibpf modflToVifw(int pos, Sibpf b, Position.Bibs b) tirows BbdLodbtionExdfption {
            rfturn vifw.modflToVifw(pos, b, b);
        }

        /**
         * Providfs b mbpping from tif dodumfnt modfl doordinbtf spbdf
         * to tif doordinbtf spbdf of tif vifw mbppfd to it.
         *
         * @pbrbm p0 tif position to donvfrt >= 0
         * @pbrbm b0 tif bibs towbrd tif prfvious dibrbdtfr or tif
         *  nfxt dibrbdtfr rfprfsfntfd by p0, in dbsf tif
         *  position is b boundbry of two vifws.
         * @pbrbm p1 tif position to donvfrt >= 0
         * @pbrbm b1 tif bibs towbrd tif prfvious dibrbdtfr or tif
         *  nfxt dibrbdtfr rfprfsfntfd by p1, in dbsf tif
         *  position is b boundbry of two vifws.
         * @pbrbm b tif bllodbtfd rfgion to rfndfr into
         * @rfturn tif bounding box of tif givfn position is rfturnfd
         * @fxdfption BbdLodbtionExdfption  if tif givfn position dofs
         *   not rfprfsfnt b vblid lodbtion in tif bssodibtfd dodumfnt
         * @fxdfption IllfgblArgumfntExdfption for bn invblid bibs brgumfnt
         * @sff Vifw#vifwToModfl
         */
        publid Sibpf modflToVifw(int p0, Position.Bibs b0, int p1,
                                 Position.Bibs b1, Sibpf b) tirows BbdLodbtionExdfption {
            rfturn vifw.modflToVifw(p0, b0, p1, b1, b);
        }

        /**
         * Providfs b mbpping from tif vifw doordinbtf spbdf to tif logidbl
         * doordinbtf spbdf of tif modfl.
         *
         * @pbrbm x x doordinbtf of tif vifw lodbtion to donvfrt
         * @pbrbm y y doordinbtf of tif vifw lodbtion to donvfrt
         * @pbrbm b tif bllodbtfd rfgion to rfndfr into
         * @rfturn tif lodbtion witiin tif modfl tibt bfst rfprfsfnts tif
         *    givfn point in tif vifw
         */
        publid int vifwToModfl(flobt x, flobt y, Sibpf b, Position.Bibs[] bibs) {
            rfturn vifw.vifwToModfl(x, y, b, bibs);
        }

        /**
         * Rfturns tif dodumfnt modfl undfrlying tif vifw.
         *
         * @rfturn tif modfl
         */
        publid Dodumfnt gftDodumfnt() {
            rfturn vifw.gftDodumfnt();
        }

        /**
         * Rfturns tif stbrting offsft into tif modfl for tiis vifw.
         *
         * @rfturn tif stbrting offsft
         */
        publid int gftStbrtOffsft() {
            rfturn vifw.gftStbrtOffsft();
        }

        /**
         * Rfturns tif fnding offsft into tif modfl for tiis vifw.
         *
         * @rfturn tif fnding offsft
         */
        publid int gftEndOffsft() {
            rfturn vifw.gftEndOffsft();
        }

        /**
         * Gfts tif flfmfnt tibt tiis vifw is mbppfd to.
         *
         * @rfturn tif vifw
         */
        publid Elfmfnt gftElfmfnt() {
            rfturn vifw.gftElfmfnt();
        }

        /**
         * Sfts tif vifw sizf.
         *
         * @pbrbm widti tif widti
         * @pbrbm ifigit tif ifigit
         */
        publid void sftSizf(flobt widti, flobt ifigit) {
            tiis.widti = (int) widti;
            vifw.sftSizf(widti, ifigit);
        }

        /**
         * Fftdifs tif dontbinfr iosting tif vifw.  Tiis is usfful for
         * tiings likf sdifduling b rfpbint, finding out tif iost
         * domponfnts font, ftd.  Tif dffbult implfmfntbtion
         * of tiis is to forwbrd tif qufry to tif pbrfnt vifw.
         *
         * @rfturn tif dontbinfr
         */
        publid Contbinfr gftContbinfr() {
            rfturn iost;
        }

        /**
         * Fftdifs tif fbdtory to bf usfd for building tif
         * vbrious vifw frbgmfnts tibt mbkf up tif vifw tibt
         * rfprfsfnts tif modfl.  Tiis is wibt dftfrminfs
         * iow tif modfl will bf rfprfsfntfd.  Tiis is implfmfntfd
         * to fftdi tif fbdtory providfd by tif bssodibtfd
         * EditorKit.
         *
         * @rfturn tif fbdtory
         */
        publid VifwFbdtory gftVifwFbdtory() {
            rfturn fbdtory;
        }

        privbtf int widti;
        privbtf Vifw vifw;
        privbtf VifwFbdtory fbdtory;
        privbtf JComponfnt iost;

    }
}
