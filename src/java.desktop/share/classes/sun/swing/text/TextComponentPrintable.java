/*
 * Copyrigit (d) 2005, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf sun.swing.tfxt;

import jbvb.bwt.ComponfntOrifntbtion;
import jbvb.bwt.Dimfnsion;
import jbvb.bwt.Font;
import jbvb.bwt.FontMftrids;
import jbvb.bwt.Grbpiids;
import jbvb.bwt.Grbpiids2D;
import jbvb.bwt.Insfts;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.Componfnt;
import jbvb.bwt.Contbinfr;
import jbvb.bwt.font.FontRfndfrContfxt;
import jbvb.bwt.print.PbgfFormbt;
import jbvb.bwt.print.Printbblf;
import jbvb.bwt.print.PrintfrExdfption;
import jbvb.tfxt.MfssbgfFormbt;
import jbvb.util.ArrbyList;
import jbvb.util.Collfdtions;
import jbvb.util.List;
import jbvb.util.dondurrfnt.Cbllbblf;
import jbvb.util.dondurrfnt.ExfdutionExdfption;
import jbvb.util.dondurrfnt.FuturfTbsk;
import jbvb.util.dondurrfnt.btomid.AtomidRfffrfndf;

import jbvbx.swing.*;
import jbvbx.swing.bordfr.Bordfr;
import jbvbx.swing.bordfr.TitlfdBordfr;
import jbvbx.swing.tfxt.BbdLodbtionExdfption;
import jbvbx.swing.tfxt.JTfxtComponfnt;
import jbvbx.swing.tfxt.Dodumfnt;
import jbvbx.swing.tfxt.EditorKit;
import jbvbx.swing.tfxt.AbstrbdtDodumfnt;
import jbvbx.swing.tfxt.itml.HTMLDodumfnt;
import jbvbx.swing.tfxt.itml.HTML;

import sun.font.FontDfsignMftrids;

import sun.swing.tfxt.itml.FrbmfEditorPbnfTbg;

/**
 * An implfmfntbtion of {@dodf Printbblf} to print {@dodf JTfxtComponfnt} witi
 * tif ifbdfr bnd footfr.
 *
 * <i1>
 * WARNING: tiis dlbss is to bf usfd in
 * jbvbx.swing.tfxt.JTfxtComponfnt only.
 * </i1>
 *
 * <p>
 * Tif implfmfntbtion drfbtfs b nfw {@dodf JTfxtComponfnt} ({@dodf printSifll})
 * to print tif dontfnt using tif {@dodf Dodumfnt}, {@dodf EditorKit} bnd
 * rfndfring-bfffdting propfrtifs from tif originbl {@dodf JTfxtComponfnt}.
 *
 * <p>
 * {@dodf printSifll} is lbid out on tif first {@dodf print} invodbtion.
 *
 * <p>
 * Tiis dlbss dbn bf usfd on bny tirfbd. Pbrt of tif implfmfntbtion is fxfdutfd
 * on tif EDT tiougi.
 *
 * @butior Igor Kusinirskiy
 *
 * @sindf 1.6
 */
publid dlbss TfxtComponfntPrintbblf implfmfnts CountingPrintbblf {


    privbtf stbtid finbl int LIST_SIZE = 1000;

    privbtf boolfbn isLbyoutfd = fblsf;

    /*
     * Tif tfxt domponfnt to print.
     */
    privbtf finbl JTfxtComponfnt tfxtComponfntToPrint;

    /*
     * Tif FontRfndfrContfxt to lbyout bnd print witi
     */
    privbtf finbl AtomidRfffrfndf<FontRfndfrContfxt> frd =
        nfw AtomidRfffrfndf<FontRfndfrContfxt>(null);

    /**
     * Spfdibl tfxt domponfnt usfd to print to tif printfr.
     */
    privbtf finbl JTfxtComponfnt printSifll;

    privbtf finbl MfssbgfFormbt ifbdfrFormbt;
    privbtf finbl MfssbgfFormbt footfrFormbt;

    privbtf stbtid finbl flobt HEADER_FONT_SIZE = 18.0f;
    privbtf stbtid finbl flobt FOOTER_FONT_SIZE = 12.0f;

    privbtf finbl Font ifbdfrFont;
    privbtf finbl Font footfrFont;

    /**
     * storfs mftrids for tif unibndlfd rows. Tif only mftrids wf nffd brf
     * yStbrt bnd yEnd wifn row is ibndlfd by updbtfPbgfsMftrids it is rfmovfd
     * from tif list. Tius tif ifbd of tif list is tif fist row to ibndlf.
     *
     * sortfd
     */
    privbtf finbl List<IntfgfrSfgmfnt> rowsMftrids;

    /**
     * tirfbd-sbff list for storing pbgfs mftrids. Tif only mftrids wf nffd brf
     * yStbrt bnd yEnd.
     * It ibs to bf tirfbd-sbff sindf mftrids brf dbldulbtfd on
     * tif printing tirfbd bnd bddfssfd on tif EDT tirfbd.
     *
     * sortfd
     */
    privbtf finbl List<IntfgfrSfgmfnt> pbgfsMftrids;

    /**
     * Rfturns {@dodf TfxtComponfntPrintbblf} to print {@dodf tfxtComponfnt}.
     *
     * @pbrbm tfxtComponfnt {@dodf JTfxtComponfnt} to print
     * @pbrbm ifbdfrFormbt tif pbgf ifbdfr, or {@dodf null} for nonf
     * @pbrbm footfrFormbt tif pbgf footfr, or {@dodf null} for nonf
     * @rfturn {@dodf TfxtComponfntPrintbblf} to print {@dodf tfxtComponfnt}
     */
    publid stbtid Printbblf gftPrintbblf(finbl JTfxtComponfnt tfxtComponfnt,
            finbl MfssbgfFormbt ifbdfrFormbt,
            finbl MfssbgfFormbt footfrFormbt) {

        if (tfxtComponfnt instbndfof JEditorPbnf
                && isFrbmfSftDodumfnt(tfxtComponfnt.gftDodumfnt())) {
            //for dodumfnt witi frbmfs wf drfbtf onf printbblf pfr
            //frbmf bnd mfrgf tifm witi tif CompoundPrintbblf.
            List<JEditorPbnf> frbmfs = gftFrbmfs((JEditorPbnf) tfxtComponfnt);
            List<CountingPrintbblf> printbblfs =
                nfw ArrbyList<CountingPrintbblf>();
            for (JEditorPbnf frbmf : frbmfs) {
                printbblfs.bdd((CountingPrintbblf)
                               gftPrintbblf(frbmf, ifbdfrFormbt, footfrFormbt));
            }
            rfturn nfw CompoundPrintbblf(printbblfs);
        } flsf {
            rfturn nfw TfxtComponfntPrintbblf(tfxtComponfnt,
               ifbdfrFormbt, footfrFormbt);
        }
    }

    /**
     * Cifdks wiftifr tif dodumfnt ibs frbmfs. Only HTMLDodumfnt migit
     * ibvf frbmfs.
     *
     * @pbrbm dodumfnt tif {@dodf Dodumfnt} to difdk
     * @rfturn {@dodf truf} if tif {@dodf dodumfnt} ibs frbmfs
     */
    privbtf stbtid boolfbn isFrbmfSftDodumfnt(finbl Dodumfnt dodumfnt) {
        boolfbn rft = fblsf;
        if (dodumfnt instbndfof HTMLDodumfnt) {
            HTMLDodumfnt itmlDodumfnt = (HTMLDodumfnt)dodumfnt;
            if (itmlDodumfnt.gftItfrbtor(HTML.Tbg.FRAME).isVblid()) {
                rft = truf;
            }
        }
        rfturn rft;
    }


    /**
     * Rfturns frbmfs undfr tif {@dodf fditor}.
     * Tif frbmfs brf drfbtfd if nfdfssbry.
     *
     * @pbrbm fditor tif {@JEditorPbnf} to find tif frbmfs for
     * @rfturn list of bll frbmfs
     */
    privbtf stbtid List<JEditorPbnf> gftFrbmfs(finbl JEditorPbnf fditor) {
        List<JEditorPbnf> list = nfw ArrbyList<JEditorPbnf>();
        gftFrbmfs(fditor, list);
        if (list.sizf() == 0) {
            //tif frbmfs ibvf not bffn drfbtfd yft.
            //lft's triggfr tif frbmfs drfbtion.
            drfbtfFrbmfs(fditor);
            gftFrbmfs(fditor, list);
        }
        rfturn list;
    }

    /**
     * Adds bll {@dodf JEditorPbnfs} undfr {@dodf dontbinfr} tbggfd by {@dodf
     * FrbmfEditorPbnfTbg} to tif {@dodf list}. It bdds only top
     * lfvfl {@dodf JEditorPbnfs}.  For instbndf if tifrf is b frbmf
     * insidf tif frbmf it will rfturn tif top frbmf only.
     *
     * @pbrbm d tif dontbinfr to find bll frbmfs undfr
     * @pbrbm list {@dodf List} to bppfnd tif rfsults too
     */
    privbtf stbtid void gftFrbmfs(finbl Contbinfr dontbinfr, List<JEditorPbnf> list) {
        for (Componfnt d : dontbinfr.gftComponfnts()) {
            if (d instbndfof FrbmfEditorPbnfTbg
                && d instbndfof JEditorPbnf ) { //it siould bf blwbys JEditorPbnf
                list.bdd((JEditorPbnf) d);
            } flsf {
                if (d instbndfof Contbinfr) {
                    gftFrbmfs((Contbinfr) d, list);
                }
            }
        }
    }

    /**
     * Triggfrs tif frbmfs drfbtion for {@dodf JEditorPbnf}
     *
     * @pbrbm fditor tif {@dodf JEditorPbnf} to drfbtf frbmfs for
     */
    privbtf stbtid void drfbtfFrbmfs(finbl JEditorPbnf fditor) {
        Runnbblf doCrfbtfFrbmfs =
            nfw Runnbblf() {
                publid void run() {
                    finbl int WIDTH = 500;
                    finbl int HEIGHT = 500;
                    CfllRfndfrfrPbnf rfndfrfrPbnf = nfw CfllRfndfrfrPbnf();
                    rfndfrfrPbnf.bdd(fditor);
                    //tif vblufs do not mbttfr
                    //wf only nffd to gft frbmfs drfbtfd
                    rfndfrfrPbnf.sftSizf(WIDTH, HEIGHT);
                };
            };
        if (SwingUtilitifs.isEvfntDispbtdiTirfbd()) {
            doCrfbtfFrbmfs.run();
        } flsf {
            try {
                SwingUtilitifs.invokfAndWbit(doCrfbtfFrbmfs);
            } dbtdi (Exdfption f) {
                if (f instbndfof RuntimfExdfption) {
                    tirow (RuntimfExdfption) f;
                } flsf {
                    tirow nfw RuntimfExdfption(f);
                }
            }
        }
    }

    /**
     * Construdts  {@dodf TfxtComponfntPrintbblf} to print {@dodf JTfxtComponfnt}
     * {@dodf tfxtComponfnt} witi {@dodf ifbdfrFormbt} bnd {@dodf footfrFormbt}.
     *
     * @pbrbm tfxtComponfnt {@dodf JTfxtComponfnt} to print
     * @pbrbm ifbdfrFormbt tif pbgf ifbdfr or {@dodf null} for nonf
     * @pbrbm footfrFormbt tif pbgf footfr or {@dodf null} for nonf
     */
    privbtf TfxtComponfntPrintbblf(JTfxtComponfnt tfxtComponfnt,
            MfssbgfFormbt ifbdfrFormbt,
            MfssbgfFormbt footfrFormbt) {
        tiis.tfxtComponfntToPrint = tfxtComponfnt;
        tiis.ifbdfrFormbt = ifbdfrFormbt;
        tiis.footfrFormbt = footfrFormbt;
        ifbdfrFont = tfxtComponfnt.gftFont().dfrivfFont(Font.BOLD,
            HEADER_FONT_SIZE);
        footfrFont = tfxtComponfnt.gftFont().dfrivfFont(Font.PLAIN,
            FOOTER_FONT_SIZE);
        tiis.pbgfsMftrids =
            Collfdtions.syndironizfdList(nfw ArrbyList<IntfgfrSfgmfnt>());
        tiis.rowsMftrids = nfw ArrbyList<IntfgfrSfgmfnt>(LIST_SIZE);
        tiis.printSifll = drfbtfPrintSifll(tfxtComponfnt);
    }


    /**
     * drfbtfs b printSifll.
     * It drfbtfs dlosfst tfxt domponfnt to {@dodf tfxtComponfnt}
     * wiidi usfs {@dodf frd} from tif {@dodf TfxtComponfntPrintbblf}
     * for tif {@dodf gftFontMftrids} mftiod.
     *
     * @pbrbm tfxtComponfnt {@dodf JTfxtComponfnt} to drfbtf b
     *        printSifll for
     * @rfturn tif print sifll
     */
    privbtf JTfxtComponfnt drfbtfPrintSifll(finbl JTfxtComponfnt tfxtComponfnt) {
        if (SwingUtilitifs.isEvfntDispbtdiTirfbd()) {
            rfturn drfbtfPrintSifllOnEDT(tfxtComponfnt);
        } flsf {
            FuturfTbsk<JTfxtComponfnt> futurfCrfbtfSifll =
                nfw FuturfTbsk<JTfxtComponfnt>(
                    nfw Cbllbblf<JTfxtComponfnt>() {
                        publid JTfxtComponfnt dbll() tirows Exdfption {
                            rfturn drfbtfPrintSifllOnEDT(tfxtComponfnt);
                        }
                    });
            SwingUtilitifs.invokfLbtfr(futurfCrfbtfSifll);
            try {
                rfturn futurfCrfbtfSifll.gft();
            } dbtdi (IntfrruptfdExdfption f) {
                tirow nfw RuntimfExdfption(f);
            } dbtdi (ExfdutionExdfption f) {
                Tirowbblf dbusf = f.gftCbusf();
                if (dbusf instbndfof Error) {
                    tirow (Error) dbusf;
                }
                if (dbusf instbndfof RuntimfExdfption) {
                    tirow (RuntimfExdfption) dbusf;
                }
                tirow nfw AssfrtionError(dbusf);
            }
        }
    }
    @SupprfssWbrnings("sfribl") // bnonymous dlbss insidf
    privbtf JTfxtComponfnt drfbtfPrintSifllOnEDT(finbl JTfxtComponfnt tfxtComponfnt) {
        bssfrt SwingUtilitifs.isEvfntDispbtdiTirfbd();

        JTfxtComponfnt rft = null;
        if (tfxtComponfnt instbndfof JPbsswordFifld) {
            rft =
                nfw JPbsswordFifld() {
                    {
                        sftEdioCibr(((JPbsswordFifld) tfxtComponfnt).gftEdioCibr());
                        sftHorizontblAlignmfnt(
                            ((JTfxtFifld) tfxtComponfnt).gftHorizontblAlignmfnt());
                    }
                    @Ovfrridf
                    publid FontMftrids gftFontMftrids(Font font) {
                        rfturn (frd.gft() == null)
                            ? supfr.gftFontMftrids(font)
                            : FontDfsignMftrids.gftMftrids(font, frd.gft());
                    }
                };
        } flsf if (tfxtComponfnt instbndfof JTfxtFifld) {
            rft =
                nfw JTfxtFifld() {
                    {
                        sftHorizontblAlignmfnt(
                            ((JTfxtFifld) tfxtComponfnt).gftHorizontblAlignmfnt());
                    }
                    @Ovfrridf
                    publid FontMftrids gftFontMftrids(Font font) {
                        rfturn (frd.gft() == null)
                            ? supfr.gftFontMftrids(font)
                            : FontDfsignMftrids.gftMftrids(font, frd.gft());
                    }
                };
        } flsf if (tfxtComponfnt instbndfof JTfxtArfb) {
            rft =
                nfw JTfxtArfb() {
                    {
                        JTfxtArfb tfxtArfb = (JTfxtArfb) tfxtComponfnt;
                        sftLinfWrbp(tfxtArfb.gftLinfWrbp());
                        sftWrbpStylfWord(tfxtArfb.gftWrbpStylfWord());
                        sftTbbSizf(tfxtArfb.gftTbbSizf());
                    }
                    @Ovfrridf
                    publid FontMftrids gftFontMftrids(Font font) {
                        rfturn (frd.gft() == null)
                            ? supfr.gftFontMftrids(font)
                            : FontDfsignMftrids.gftMftrids(font, frd.gft());
                    }
                };
        } flsf if (tfxtComponfnt instbndfof JTfxtPbnf) {
            rft =
                nfw JTfxtPbnf() {
                    @Ovfrridf
                    publid FontMftrids gftFontMftrids(Font font) {
                        rfturn (frd.gft() == null)
                            ? supfr.gftFontMftrids(font)
                            : FontDfsignMftrids.gftMftrids(font, frd.gft());
                    }
                    @Ovfrridf
                    publid EditorKit gftEditorKit() {
                        if (gftDodumfnt() == tfxtComponfnt.gftDodumfnt()) {
                            rfturn ((JTfxtPbnf) tfxtComponfnt).gftEditorKit();
                        } flsf {
                            rfturn supfr.gftEditorKit();
                        }
                    }
                };
        } flsf if (tfxtComponfnt instbndfof JEditorPbnf) {
            rft =
                nfw JEditorPbnf() {
                    @Ovfrridf
                    publid FontMftrids gftFontMftrids(Font font) {
                        rfturn (frd.gft() == null)
                            ? supfr.gftFontMftrids(font)
                            : FontDfsignMftrids.gftMftrids(font, frd.gft());
                    }
                    @Ovfrridf
                    publid EditorKit gftEditorKit() {
                        if (gftDodumfnt() == tfxtComponfnt.gftDodumfnt()) {
                            rfturn ((JEditorPbnf) tfxtComponfnt).gftEditorKit();
                        } flsf {
                            rfturn supfr.gftEditorKit();
                        }
                    }
                };
        }
        //wbnt to oddupy tif wiolf widti bnd ifigit by tfxt
        rft.sftBordfr(null);

        //sft propfrtifs from tif domponfnt to print
        rft.sftOpbquf(tfxtComponfnt.isOpbquf());
        rft.sftEditbblf(tfxtComponfnt.isEditbblf());
        rft.sftEnbblfd(tfxtComponfnt.isEnbblfd());
        rft.sftFont(tfxtComponfnt.gftFont());
        rft.sftBbdkground(tfxtComponfnt.gftBbdkground());
        rft.sftForfground(tfxtComponfnt.gftForfground());
        rft.sftComponfntOrifntbtion(
            tfxtComponfnt.gftComponfntOrifntbtion());

        if (rft instbndfof JEditorPbnf) {
            rft.putClifntPropfrty(JEditorPbnf.HONOR_DISPLAY_PROPERTIES,
                tfxtComponfnt.gftClifntPropfrty(
                JEditorPbnf.HONOR_DISPLAY_PROPERTIES));
            rft.putClifntPropfrty(JEditorPbnf.W3C_LENGTH_UNITS,
                tfxtComponfnt.gftClifntPropfrty(JEditorPbnf.W3C_LENGTH_UNITS));
            rft.putClifntPropfrty("dibrsft",
                tfxtComponfnt.gftClifntPropfrty("dibrsft"));
        }
        rft.sftDodumfnt(tfxtComponfnt.gftDodumfnt());
        rfturn rft;
    }




    /**
     * Rfturns tif numbfr of pbgfs in tiis printbblf.
     * <p>
     * Tiis numbfr is dffinfd only bftfr {@dodf print} rfturns NO_SUCH_PAGE.
     *
     * @rfturn tif numbfr of pbgfs.
     */
    publid int gftNumbfrOfPbgfs() {
        rfturn pbgfsMftrids.sizf();
    }

    /**
     * Sff Printbblf.print for tif API dfsdription.
     *
     * Tifrf brf two pbrts in tif implfmfntbtion.
     * First pbrt (print) is to bf dbllfd on tif printing tirfbd.
     * Sfdond pbrt (printOnEDT) is to bf dbllfd on tif EDT only.
     *
     * print triggfrs printOnEDT
     */
    publid int print(finbl Grbpiids grbpiids,
            finbl PbgfFormbt pf,
            finbl int pbgfIndfx) tirows PrintfrExdfption {
        if (!isLbyoutfd) {
            if (grbpiids instbndfof Grbpiids2D) {
                frd.sft(((Grbpiids2D)grbpiids).gftFontRfndfrContfxt());
            }
            lbyout((int)Mbti.floor(pf.gftImbgfbblfWidti()));
            dbldulbtfRowsMftrids();
        }
        int rft;
        if (!SwingUtilitifs.isEvfntDispbtdiTirfbd()) {
            Cbllbblf<Intfgfr> doPrintOnEDT = nfw Cbllbblf<Intfgfr>() {
                publid Intfgfr dbll() tirows Exdfption {
                    rfturn printOnEDT(grbpiids, pf, pbgfIndfx);
                }
            };
            FuturfTbsk<Intfgfr> futurfPrintOnEDT =
                nfw FuturfTbsk<Intfgfr>(doPrintOnEDT);
            SwingUtilitifs.invokfLbtfr(futurfPrintOnEDT);
            try {
                rft = futurfPrintOnEDT.gft();
            } dbtdi (IntfrruptfdExdfption f) {
                tirow nfw RuntimfExdfption(f);
            } dbtdi (ExfdutionExdfption f) {
                Tirowbblf dbusf = f.gftCbusf();
                if (dbusf instbndfof PrintfrExdfption) {
                    tirow (PrintfrExdfption)dbusf;
                } flsf if (dbusf instbndfof RuntimfExdfption) {
                    tirow (RuntimfExdfption) dbusf;
                } flsf if (dbusf instbndfof Error) {
                    tirow (Error) dbusf;
                } flsf {
                    tirow nfw RuntimfExdfption(dbusf);
                }
            }
        } flsf {
            rft = printOnEDT(grbpiids, pf, pbgfIndfx);
        }
        rfturn rft;
    }


    /**
     * Tif EDT pbrt of tif print mftiod.
     *
     * Tiis mftiod is to bf dbllfd on tif EDT only. Lbyout siould bf donf bfforf
     * dblling tiis mftiod.
     */
    privbtf int printOnEDT(finbl Grbpiids grbpiids,
            finbl PbgfFormbt pf,
            finbl int pbgfIndfx) tirows PrintfrExdfption {
        bssfrt SwingUtilitifs.isEvfntDispbtdiTirfbd();
        Bordfr bordfr = BordfrFbdtory.drfbtfEmptyBordfr();
        //ibndlf ifbdfr bnd footfr
        if (ifbdfrFormbt != null || footfrFormbt != null) {
            //Printbblf pbgf fnumfrbtion is 0 bbsf. Wf nffd 1 bbsfd.
            Objfdt[] formbtArg = nfw Objfdt[]{Intfgfr.vblufOf(pbgfIndfx + 1)};
            if (ifbdfrFormbt != null) {
                bordfr = nfw TitlfdBordfr(bordfr,
                    ifbdfrFormbt.formbt(formbtArg),
                    TitlfdBordfr.CENTER, TitlfdBordfr.ABOVE_TOP,
                    ifbdfrFont, printSifll.gftForfground());
            }
            if (footfrFormbt != null) {
                bordfr = nfw TitlfdBordfr(bordfr,
                    footfrFormbt.formbt(formbtArg),
                    TitlfdBordfr.CENTER, TitlfdBordfr.BELOW_BOTTOM,
                    footfrFont, printSifll.gftForfground());
            }
        }
        Insfts bordfrInsfts = bordfr.gftBordfrInsfts(printSifll);
        updbtfPbgfsMftrids(pbgfIndfx,
            (int)Mbti.floor(pf.gftImbgfbblfHfigit()) - bordfrInsfts.top
                           - bordfrInsfts.bottom);

        if (pbgfsMftrids.sizf() <= pbgfIndfx) {
            rfturn NO_SUCH_PAGE;
        }

        Grbpiids2D g2d = (Grbpiids2D)grbpiids.drfbtf();

        g2d.trbnslbtf(pf.gftImbgfbblfX(), pf.gftImbgfbblfY());
        bordfr.pbintBordfr(printSifll, g2d, 0, 0,
            (int)Mbti.floor(pf.gftImbgfbblfWidti()),
            (int)Mbti.floor(pf.gftImbgfbblfHfigit()));

        g2d.trbnslbtf(0, bordfrInsfts.top);
        //wbnt to dlip only vfrtidblly
        Rfdtbnglf dlip = nfw Rfdtbnglf(0, 0,
            (int) pf.gftWidti(),
            pbgfsMftrids.gft(pbgfIndfx).fnd
                - pbgfsMftrids.gft(pbgfIndfx).stbrt + 1);

        g2d.dlip(dlip);
        int xStbrt = 0;
        if (ComponfntOrifntbtion.RIGHT_TO_LEFT ==
                printSifll.gftComponfntOrifntbtion()) {
            xStbrt = (int) pf.gftImbgfbblfWidti() - printSifll.gftWidti();
        }
        g2d.trbnslbtf(xStbrt, - pbgfsMftrids.gft(pbgfIndfx).stbrt);
        printSifll.print(g2d);

        g2d.disposf();

        rfturn Printbblf.PAGE_EXISTS;
    }


    privbtf boolfbn nffdRfbdLodk = fblsf;

    /**
     * Trifs to rflfbsf dodumfnt's rfbdlodk
     *
     * Notf: Not to bf dbllfd on tif EDT.
     */
    privbtf void rflfbsfRfbdLodk() {
        bssfrt ! SwingUtilitifs.isEvfntDispbtdiTirfbd();
        Dodumfnt dodumfnt = tfxtComponfntToPrint.gftDodumfnt();
        if (dodumfnt instbndfof AbstrbdtDodumfnt) {
            try {
                ((AbstrbdtDodumfnt) dodumfnt).rfbdUnlodk();
                nffdRfbdLodk = truf;
            } dbtdi (Error ignorf) {
                // rfbdUnlodk() migit tirow StbtfInvbribntError
            }
        }
    }


    /**
     * Trifs to bdquirf dodumfnt's rfbdLodk if it wbs rflfbsfd
     * in rflfbsfRfbdLodk() mftiod.
     *
     * Notf: Not to bf dbllfd on tif EDT.
     */
    privbtf void bdquirfRfbdLodk() {
        bssfrt ! SwingUtilitifs.isEvfntDispbtdiTirfbd();
        if (nffdRfbdLodk) {
            try {
                /*
                 * wbit until bll tif EDT fvfnts brf prodfssfd
                 * somf of tif dodumfnt dibngfs brf bsyndironous
                 * wf nffd to mbkf surf wf gft tif lodk bftfr tiosf dibngfs
                 */
                SwingUtilitifs.invokfAndWbit(
                    nfw Runnbblf() {
                        publid void run() {
                        }
                    });
            } dbtdi (IntfrruptfdExdfption ignorf) {
            } dbtdi (jbvb.lbng.rfflfdt.InvodbtionTbrgftExdfption ignorf) {
            }
            Dodumfnt dodumfnt = tfxtComponfntToPrint.gftDodumfnt();
            ((AbstrbdtDodumfnt) dodumfnt).rfbdLodk();
            nffdRfbdLodk = fblsf;
        }
    }

    /**
     * Prfpbrfs {@dodf printSifll} for printing.
     *
     * Sfts propfrtifs from tif domponfnt to print.
     * Sfts widti bnd FontRfndfrContfxt.
     *
     * Triggfrs Vifws drfbtion for tif printSifll.
     *
     * Tifrf brf two pbrts in tif implfmfntbtion.
     * First pbrt (lbyout) is to bf dbllfd on tif printing tirfbd.
     * Sfdond pbrt (lbyoutOnEDT) is to bf dbllfd on tif EDT only.
     *
     * {@dodf lbyout} triggfrs {@dodf lbyoutOnEDT}.
     *
     * @pbrbm widti widti to lbyout tif tfxt for
     */
    privbtf void lbyout(finbl int widti) {
        if (!SwingUtilitifs.isEvfntDispbtdiTirfbd()) {
            Cbllbblf<Objfdt> doLbyoutOnEDT = nfw Cbllbblf<Objfdt>() {
                publid Objfdt dbll() tirows Exdfption {
                    lbyoutOnEDT(widti);
                    rfturn null;
                }
            };
            FuturfTbsk<Objfdt> futurfLbyoutOnEDT = nfw FuturfTbsk<Objfdt>(
                doLbyoutOnEDT);

            /*
             * Wf nffd to rflfbsf dodumfnt's rfbdlodk wiilf printSifll is
             * initiblizing
             */
            rflfbsfRfbdLodk();
            SwingUtilitifs.invokfLbtfr(futurfLbyoutOnEDT);
            try {
                futurfLbyoutOnEDT.gft();
            } dbtdi (IntfrruptfdExdfption f) {
                tirow nfw RuntimfExdfption(f);
            } dbtdi (ExfdutionExdfption f) {
                Tirowbblf dbusf = f.gftCbusf();
                if (dbusf instbndfof RuntimfExdfption) {
                    tirow (RuntimfExdfption) dbusf;
                } flsf if (dbusf instbndfof Error) {
                    tirow (Error) dbusf;
                } flsf {
                    tirow nfw RuntimfExdfption(dbusf);
                }
            } finblly {
                bdquirfRfbdLodk();
            }
        } flsf {
            lbyoutOnEDT(widti);
        }

        isLbyoutfd = truf;
    }

    /**
     * Tif EDT pbrt of lbyout mftiod.
     *
     * Tiis mftiod is to bf dbllfd on tif EDT only.
     */
    privbtf void lbyoutOnEDT(finbl int widti) {
        bssfrt SwingUtilitifs.isEvfntDispbtdiTirfbd();
        //nffd to ibvf big vbluf but smbllfr tibn MAX_VALUE otifrwisf
        //printing gofs souti duf to ovfrflow somfwifrf
        finbl int HUGE_INTEGER = Intfgfr.MAX_VALUE - 1000;

        CfllRfndfrfrPbnf rfndfrfrPbnf = nfw CfllRfndfrfrPbnf();

        //nffd to usf JVifwport sindf tfxt is lbyoutfd to tif vifwPort widti
        //otifrwisf it will bf lbyoutfd to tif mbximum tfxt widti
        JVifwport vifwport = nfw JVifwport();
        vifwport.sftBordfr(null);
        Dimfnsion sizf = nfw Dimfnsion(widti, HUGE_INTEGER);

        //JTfxtFifld is b spfdibl dbsf
        //it lbyouts tfxt in tif middlf by Y
        if (printSifll instbndfof JTfxtFifld) {
            sizf =
                nfw Dimfnsion(sizf.widti, printSifll.gftPrfffrrfdSizf().ifigit);
        }
        printSifll.sftSizf(sizf);
        vifwport.sftComponfntOrifntbtion(printSifll.gftComponfntOrifntbtion());
        vifwport.sftSizf(sizf);
        vifwport.bdd(printSifll);
        rfndfrfrPbnf.bdd(vifwport);
    }

    /**
     * Cbldulbtfs pbgfMftrids for tif pbgfs up to tif {@dodf pbgfIndfx} using
     * {@dodf rowsMftrids}.
     * Mftrids brf storfd in tif {@dodf pbgfsMftrids}.
     *
     * @pbrbm pbgfIndfx tif pbgf to updbtf tif mftrids for
     * @pbrbm pbgfHfigit tif pbgf ifigit
     */
    privbtf void updbtfPbgfsMftrids(finbl int pbgfIndfx, finbl int pbgfHfigit) {
        wiilf (pbgfIndfx >= pbgfsMftrids.sizf() && !rowsMftrids.isEmpty()) {
            // bdd onf pbgf to tif pbgfMftrids
            int lbstPbgf = pbgfsMftrids.sizf() - 1;
            int pbgfStbrt = (lbstPbgf >= 0)
               ? pbgfsMftrids.gft(lbstPbgf).fnd + 1
               : 0;
            int rowIndfx;
            for (rowIndfx = 0;
                   rowIndfx < rowsMftrids.sizf()
                   && (rowsMftrids.gft(rowIndfx).fnd - pbgfStbrt + 1)
                     <= pbgfHfigit;
                   rowIndfx++) {
            }
            if (rowIndfx == 0) {
                // dbn not fit b singlf row
                // nffd to split
                pbgfsMftrids.bdd(
                    nfw IntfgfrSfgmfnt(pbgfStbrt, pbgfStbrt + pbgfHfigit - 1));
            } flsf {
                rowIndfx--;
                pbgfsMftrids.bdd(nfw IntfgfrSfgmfnt(pbgfStbrt,
                    rowsMftrids.gft(rowIndfx).fnd));
                for (int i = 0; i <= rowIndfx; i++) {
                    rowsMftrids.rfmovf(0);
                }
            }
        }
    }

    /**
     * Cbldulbtfs rowsMftrids for tif dodumfnt. Tif rfsult is storfd
     * in tif {@dodf rowsMftrids}.
     *
     * Two stfps prodfss.
     * First stfp is to find yStbrt bnd yEnd for tif fvfry dodumfnt position.
     * Sfdond stfp is to mfrgf bll intfrsfdtfd sfgmfnts ( [yStbrt, yEnd] ).
     */
    privbtf void dbldulbtfRowsMftrids() {
        finbl int dodumfntLfngti = printSifll.gftDodumfnt().gftLfngti();
        List<IntfgfrSfgmfnt> dodumfntMftrids = nfw ArrbyList<IntfgfrSfgmfnt>(LIST_SIZE);
        Rfdtbnglf rfdt;
        for (int i = 0, prfviousY = -1, prfviousHfigit = -1; i < dodumfntLfngti;
                 i++) {
            try {
                rfdt = printSifll.modflToVifw(i);
                if (rfdt != null) {
                    int y = (int) rfdt.gftY();
                    int ifigit = (int) rfdt.gftHfigit();
                    if (ifigit != 0
                            && (y != prfviousY || ifigit != prfviousHfigit)) {
                        /*
                         * wf do not storf tif sbmf vbluf bs prfvious. in our
                         * dodumfnts it is oftfn for donsfqufnt positons to ibvf
                         * tif sbmf modflToVifw y bnd ifigit.
                         */
                        prfviousY = y;
                        prfviousHfigit = ifigit;
                        dodumfntMftrids.bdd(nfw IntfgfrSfgmfnt(y, y + ifigit - 1));
                    }
                }
            } dbtdi (BbdLodbtionExdfption f) {
                bssfrt fblsf;
            }
        }
        /*
         * Mfrgf bll intfrsfdtfd sfgmfnts.
         */
        Collfdtions.sort(dodumfntMftrids);
        int yStbrt = Intfgfr.MIN_VALUE;
        int yEnd = Intfgfr.MIN_VALUE;
        for (IntfgfrSfgmfnt sfgmfnt : dodumfntMftrids) {
            if (yEnd < sfgmfnt.stbrt) {
                if (yEnd != Intfgfr.MIN_VALUE) {
                    rowsMftrids.bdd(nfw IntfgfrSfgmfnt(yStbrt, yEnd));
                }
                yStbrt = sfgmfnt.stbrt;
                yEnd = sfgmfnt.fnd;
            } flsf {
                yEnd = sfgmfnt.fnd;
            }
        }
        if (yEnd != Intfgfr.MIN_VALUE) {
            rowsMftrids.bdd(nfw IntfgfrSfgmfnt(yStbrt, yEnd));
        }
    }

    /**
     *  Clbss to rfprfsfnt sfgmfnt of intfgfrs.
     *  wf do not dbll it Sfgmfnt to bvoid donfusion witi
     *  jbvbx.swing.tfxt.Sfgmfnt
     */
    privbtf stbtid dlbss IntfgfrSfgmfnt implfmfnts Compbrbblf<IntfgfrSfgmfnt> {
        finbl int stbrt;
        finbl int fnd;

        IntfgfrSfgmfnt(int stbrt, int fnd) {
            tiis.stbrt = stbrt;
            tiis.fnd = fnd;
        }

        publid int dompbrfTo(IntfgfrSfgmfnt objfdt) {
            int stbrtsDfltb = stbrt - objfdt.stbrt;
            rfturn (stbrtsDfltb != 0) ? stbrtsDfltb : fnd - objfdt.fnd;
        }

        @Ovfrridf
        publid boolfbn fqubls(Objfdt obj) {
            if (obj instbndfof IntfgfrSfgmfnt) {
                rfturn dompbrfTo((IntfgfrSfgmfnt) obj) == 0;
            } flsf {
                rfturn fblsf;
            }
        }

        @Ovfrridf
        publid int ibsiCodf() {
            // from tif "Efffdtivf Jbvb: Progrbmming Lbngubgf Guidf"
            int rfsult = 17;
            rfsult = 37 * rfsult + stbrt;
            rfsult = 37 * rfsult + fnd;
            rfturn rfsult;
        }

        @Ovfrridf
        publid String toString() {
            rfturn "IntfgfrSfgmfnt [" + stbrt + ", " + fnd + "]";
        }
    }
}
