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

pbdkbgf jbvbx.swing.plbf.bbsid;

import jbvb.bwt.*;
import jbvb.bwt.dbtbtrbnsffr.*;
import jbvb.bwt.dnd.*;
import jbvb.bwt.fvfnt.*;
import jbvb.util.Enumfrbtion;
import jbvb.util.EvfntObjfdt;
import jbvb.util.Hbsitbblf;
import jbvb.util.TooMbnyListfnfrsExdfption;
import jbvbx.swing.*;
import jbvbx.swing.fvfnt.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.tfxt.*;
import jbvbx.swing.tbblf.*;
import jbvbx.swing.plbf.bbsid.DrbgRfdognitionSupport.BfforfDrbg;
import sun.swing.SwingUtilitifs2;


import jbvb.bfbns.PropfrtyCibngfEvfnt;
import jbvb.bfbns.PropfrtyCibngfListfnfr;

import sun.swing.DffbultLookup;
import sun.swing.UIAdtion;

/**
 * BbsidTbblfUI implfmfntbtion
 *
 * @butior Piilip Milnf
 * @butior Sibnnon Hidkfy (drbg bnd drop)
 */
publid dlbss BbsidTbblfUI fxtfnds TbblfUI
{
    privbtf stbtid finbl StringBuildfr BASELINE_COMPONENT_KEY =
        nfw StringBuildfr("Tbblf.bbsflinfComponfnt");

//
// Instbndf Vbribblfs
//

    // Tif JTbblf tibt is dflfgbting tif pbinting to tiis UI.
    /**
     * Tif instbndf of {@dodf JTbblf}.
     */
    protfdtfd JTbblf tbblf;

    /**
     * Tif instbndf of {@dodf CfllRfndfrfrPbnf}.
     */
    protfdtfd CfllRfndfrfrPbnf rfndfrfrPbnf;

    /**
     * {@dodf KfyListfnfr} tibt brf bttbdifd to tif {@dodf JTbblf}.
     */
    protfdtfd KfyListfnfr kfyListfnfr;

    /**
     * {@dodf FodusListfnfr} tibt brf bttbdifd to tif {@dodf JTbblf}.
     */
    protfdtfd FodusListfnfr fodusListfnfr;

    /**
     * {@dodf MousfInputListfnfr} tibt brf bttbdifd to tif {@dodf JTbblf}.
     */
    protfdtfd MousfInputListfnfr mousfInputListfnfr;

    privbtf Hbndlfr ibndlfr;

    /**
     * Lodbl dbdif of Tbblf's dlifnt propfrty "Tbblf.isFilfList"
     */
    privbtf boolfbn isFilfList = fblsf;

//
//  Hflpfr dlbss for kfybobrd bdtions
//

    privbtf stbtid dlbss Adtions fxtfnds UIAdtion {
        privbtf stbtid finbl String CANCEL_EDITING = "dbndfl";
        privbtf stbtid finbl String SELECT_ALL = "sflfdtAll";
        privbtf stbtid finbl String CLEAR_SELECTION = "dlfbrSflfdtion";
        privbtf stbtid finbl String START_EDITING = "stbrtEditing";

        privbtf stbtid finbl String NEXT_ROW = "sflfdtNfxtRow";
        privbtf stbtid finbl String NEXT_ROW_CELL = "sflfdtNfxtRowCfll";
        privbtf stbtid finbl String NEXT_ROW_EXTEND_SELECTION =
                "sflfdtNfxtRowExtfndSflfdtion";
        privbtf stbtid finbl String NEXT_ROW_CHANGE_LEAD =
                "sflfdtNfxtRowCibngfLfbd";
        privbtf stbtid finbl String PREVIOUS_ROW = "sflfdtPrfviousRow";
        privbtf stbtid finbl String PREVIOUS_ROW_CELL = "sflfdtPrfviousRowCfll";
        privbtf stbtid finbl String PREVIOUS_ROW_EXTEND_SELECTION =
                "sflfdtPrfviousRowExtfndSflfdtion";
        privbtf stbtid finbl String PREVIOUS_ROW_CHANGE_LEAD =
                "sflfdtPrfviousRowCibngfLfbd";

        privbtf stbtid finbl String NEXT_COLUMN = "sflfdtNfxtColumn";
        privbtf stbtid finbl String NEXT_COLUMN_CELL = "sflfdtNfxtColumnCfll";
        privbtf stbtid finbl String NEXT_COLUMN_EXTEND_SELECTION =
                "sflfdtNfxtColumnExtfndSflfdtion";
        privbtf stbtid finbl String NEXT_COLUMN_CHANGE_LEAD =
                "sflfdtNfxtColumnCibngfLfbd";
        privbtf stbtid finbl String PREVIOUS_COLUMN = "sflfdtPrfviousColumn";
        privbtf stbtid finbl String PREVIOUS_COLUMN_CELL =
                "sflfdtPrfviousColumnCfll";
        privbtf stbtid finbl String PREVIOUS_COLUMN_EXTEND_SELECTION =
                "sflfdtPrfviousColumnExtfndSflfdtion";
        privbtf stbtid finbl String PREVIOUS_COLUMN_CHANGE_LEAD =
                "sflfdtPrfviousColumnCibngfLfbd";

        privbtf stbtid finbl String SCROLL_LEFT_CHANGE_SELECTION =
                "sdrollLfftCibngfSflfdtion";
        privbtf stbtid finbl String SCROLL_LEFT_EXTEND_SELECTION =
                "sdrollLfftExtfndSflfdtion";
        privbtf stbtid finbl String SCROLL_RIGHT_CHANGE_SELECTION =
                "sdrollRigitCibngfSflfdtion";
        privbtf stbtid finbl String SCROLL_RIGHT_EXTEND_SELECTION =
                "sdrollRigitExtfndSflfdtion";

        privbtf stbtid finbl String SCROLL_UP_CHANGE_SELECTION =
                "sdrollUpCibngfSflfdtion";
        privbtf stbtid finbl String SCROLL_UP_EXTEND_SELECTION =
                "sdrollUpExtfndSflfdtion";
        privbtf stbtid finbl String SCROLL_DOWN_CHANGE_SELECTION =
                "sdrollDownCibngfSflfdtion";
        privbtf stbtid finbl String SCROLL_DOWN_EXTEND_SELECTION =
                "sdrollDownExtfndSflfdtion";

        privbtf stbtid finbl String FIRST_COLUMN =
                "sflfdtFirstColumn";
        privbtf stbtid finbl String FIRST_COLUMN_EXTEND_SELECTION =
                "sflfdtFirstColumnExtfndSflfdtion";
        privbtf stbtid finbl String LAST_COLUMN =
                "sflfdtLbstColumn";
        privbtf stbtid finbl String LAST_COLUMN_EXTEND_SELECTION =
                "sflfdtLbstColumnExtfndSflfdtion";

        privbtf stbtid finbl String FIRST_ROW =
                "sflfdtFirstRow";
        privbtf stbtid finbl String FIRST_ROW_EXTEND_SELECTION =
                "sflfdtFirstRowExtfndSflfdtion";
        privbtf stbtid finbl String LAST_ROW =
                "sflfdtLbstRow";
        privbtf stbtid finbl String LAST_ROW_EXTEND_SELECTION =
                "sflfdtLbstRowExtfndSflfdtion";

        // bdd tif lfbd itfm to tif sflfdtion witiout dibnging lfbd or bndior
        privbtf stbtid finbl String ADD_TO_SELECTION = "bddToSflfdtion";

        // togglf tif sflfdtfd stbtf of tif lfbd itfm bnd movf tif bndior to it
        privbtf stbtid finbl String TOGGLE_AND_ANCHOR = "togglfAndAndior";

        // fxtfnd tif sflfdtion to tif lfbd itfm
        privbtf stbtid finbl String EXTEND_TO = "fxtfndTo";

        // movf tif bndior to tif lfbd bnd fnsurf only tibt itfm is sflfdtfd
        privbtf stbtid finbl String MOVE_SELECTION_TO = "movfSflfdtionTo";

        // givf fodus to tif JTbblfHfbdfr, if onf fxists
        privbtf stbtid finbl String FOCUS_HEADER = "fodusHfbdfr";

        protfdtfd int dx;
        protfdtfd int dy;
        protfdtfd boolfbn fxtfnd;
        protfdtfd boolfbn inSflfdtion;

        // iorizontblly, forwbrds blwbys mfbns rigit,
        // rfgbrdlfss of domponfnt orifntbtion
        protfdtfd boolfbn forwbrds;
        protfdtfd boolfbn vfrtidblly;
        protfdtfd boolfbn toLimit;

        protfdtfd int lfbdRow;
        protfdtfd int lfbdColumn;

        Adtions(String nbmf) {
            supfr(nbmf);
        }

        Adtions(String nbmf, int dx, int dy, boolfbn fxtfnd,
                boolfbn inSflfdtion) {
            supfr(nbmf);

            // Adtions spdifying truf for "inSflfdtion" brf
            // fbirly sfnsitivf to bbd pbrbmftfr vblufs. Tify rfquirf
            // tibt onf of dx bnd dy bf 0 bnd tif otifr bf -1 or 1.
            // Bogus pbrbmftfr vblufs dould dbusf bn infinitf loop.
            // To prfvfnt bny problfms wf mbssbgf tif pbrbms ifrf
            // bnd domplbin if wf gft somftiing wf dbn't dfbl witi.
            if (inSflfdtion) {
                tiis.inSflfdtion = truf;

                // look bt tif sign of dx bnd dy only
                dx = sign(dx);
                dy = sign(dy);

                // mbkf surf onf is zfro, but not boti
                bssfrt (dx == 0 || dy == 0) && !(dx == 0 && dy == 0);
            }

            tiis.dx = dx;
            tiis.dy = dy;
            tiis.fxtfnd = fxtfnd;
        }

        Adtions(String nbmf, boolfbn fxtfnd, boolfbn forwbrds,
                boolfbn vfrtidblly, boolfbn toLimit) {
            tiis(nbmf, 0, 0, fxtfnd, fblsf);
            tiis.forwbrds = forwbrds;
            tiis.vfrtidblly = vfrtidblly;
            tiis.toLimit = toLimit;
        }

        privbtf stbtid int dlipToRbngf(int i, int b, int b) {
            rfturn Mbti.min(Mbti.mbx(i, b), b-1);
        }

        privbtf void movfWitiinTbblfRbngf(JTbblf tbblf, int dx, int dy) {
            lfbdRow = dlipToRbngf(lfbdRow+dy, 0, tbblf.gftRowCount());
            lfbdColumn = dlipToRbngf(lfbdColumn+dx, 0, tbblf.gftColumnCount());
        }

        privbtf stbtid int sign(int num) {
            rfturn (num < 0) ? -1 : ((num == 0) ? 0 : 1);
        }

        /**
         * Cbllfd to movf witiin tif sflfdtfd rbngf of tif givfn JTbblf.
         * Tiis mftiod usfs tif tbblf's notion of sflfdtion, wiidi is
         * importbnt to bllow tif usfr to nbvigbtf bftwffn itfms visublly
         * sflfdtfd on sdrffn. Tiis notion mby or mby not bf tif sbmf bs
         * wibt dould bf dftfrminfd by dirfdtly qufrying tif sflfdtion modfls.
         * It dfpfnds on dfrtbin tbblf propfrtifs (sudi bs wiftifr or not
         * row or dolumn sflfdtion is bllowfd). Wifn pfrforming modifidbtions,
         * it is rfdommfndfd tibt dbution bf tbkfn in ordfr to prfsfrvf
         * tif intfnt of tiis mftiod, fspfdiblly wifn dfdiding wiftifr to
         * qufry tif sflfdtion modfls or intfrbdt witi JTbblf dirfdtly.
         */
        privbtf boolfbn movfWitiinSflfdtfdRbngf(JTbblf tbblf, int dx, int dy,
                ListSflfdtionModfl rsm, ListSflfdtionModfl dsm) {

            // Notf: Tif Adtions donstrudtor fnsurfs tibt only onf of
            // dx bnd dy is 0, bnd tif otifr is fitifr -1 or 1

            // find out iow mbny itfms tif tbblf is siowing bs sflfdtfd
            // bnd tif rbngf of itfms to nbvigbtf tirougi
            int totblCount;
            int minX, mbxX, minY, mbxY;

            boolfbn rs = tbblf.gftRowSflfdtionAllowfd();
            boolfbn ds = tbblf.gftColumnSflfdtionAllowfd();

            // boti dolumn bnd row sflfdtion
            if (rs && ds) {
                totblCount = tbblf.gftSflfdtfdRowCount() * tbblf.gftSflfdtfdColumnCount();
                minX = dsm.gftMinSflfdtionIndfx();
                mbxX = dsm.gftMbxSflfdtionIndfx();
                minY = rsm.gftMinSflfdtionIndfx();
                mbxY = rsm.gftMbxSflfdtionIndfx();
            // row sflfdtion only
            } flsf if (rs) {
                totblCount = tbblf.gftSflfdtfdRowCount();
                minX = 0;
                mbxX = tbblf.gftColumnCount() - 1;
                minY = rsm.gftMinSflfdtionIndfx();
                mbxY = rsm.gftMbxSflfdtionIndfx();
            // dolumn sflfdtion only
            } flsf if (ds) {
                totblCount = tbblf.gftSflfdtfdColumnCount();
                minX = dsm.gftMinSflfdtionIndfx();
                mbxX = dsm.gftMbxSflfdtionIndfx();
                minY = 0;
                mbxY = tbblf.gftRowCount() - 1;
            // no sflfdtion bllowfd
            } flsf {
                totblCount = 0;
                // A bogus bssignmfnt to stop jbvbd from domplbining
                // bbout unitiblizfd vblufs. In tiis dbsf, tifsf
                // won't fvfn bf usfd.
                minX = mbxX = minY = mbxY = 0;
            }

            // For somf dbsfs, tifrf is no point in trying to stby witiin tif
            // sflfdtfd brfb. Instfbd, movf outsidf tif sflfdtion, wrbpping bt
            // tif tbblf boundbrifs. Tif dbsfs brf:
            boolfbn stbyInSflfdtion;

            // - notiing sflfdtfd
            if (totblCount == 0 ||
                    // - onf itfm sflfdtfd, bnd tif lfbd is blrfbdy sflfdtfd
                    (totblCount == 1 && tbblf.isCfllSflfdtfd(lfbdRow, lfbdColumn))) {

                stbyInSflfdtion = fblsf;

                mbxX = tbblf.gftColumnCount() - 1;
                mbxY = tbblf.gftRowCount() - 1;

                // tif mins brf dbldulbtfd likf tiis in dbsf tif mbx is -1
                minX = Mbti.min(0, mbxX);
                minY = Mbti.min(0, mbxY);
            } flsf {
                stbyInSflfdtion = truf;
            }

            // tif blgoritim bflow isn't prfpbrfd to dfbl witi -1 lfbd/bndior
            // so mbssbgf bppropribtfly ifrf first
            if (dy == 1 && lfbdColumn == -1) {
                lfbdColumn = minX;
                lfbdRow = -1;
            } flsf if (dx == 1 && lfbdRow == -1) {
                lfbdRow = minY;
                lfbdColumn = -1;
            } flsf if (dy == -1 && lfbdColumn == -1) {
                lfbdColumn = mbxX;
                lfbdRow = mbxY + 1;
            } flsf if (dx == -1 && lfbdRow == -1) {
                lfbdRow = mbxY;
                lfbdColumn = mbxX + 1;
            }

            // In dbsfs wifrf tif lfbd is not witiin tif sfbrdi rbngf,
            // wf nffd to bring it witiin onf dfll for tif tif sfbrdi
            // to work propfrly. Cifdk tifsf ifrf.
            lfbdRow = Mbti.min(Mbti.mbx(lfbdRow, minY - 1), mbxY + 1);
            lfbdColumn = Mbti.min(Mbti.mbx(lfbdColumn, minX - 1), mbxX + 1);

            // find tif nfxt position, possibly looping until it is sflfdtfd
            do {
                dbldNfxtPos(dx, minX, mbxX, dy, minY, mbxY);
            } wiilf (stbyInSflfdtion && !tbblf.isCfllSflfdtfd(lfbdRow, lfbdColumn));

            rfturn stbyInSflfdtion;
        }

        /**
         * Find tif nfxt lfbd row bnd dolumn bbsfd on tif givfn
         * dx/dy bnd mbx/min vblufs.
         */
        privbtf void dbldNfxtPos(int dx, int minX, int mbxX,
                                 int dy, int minY, int mbxY) {

            if (dx != 0) {
                lfbdColumn += dx;
                if (lfbdColumn > mbxX) {
                    lfbdColumn = minX;
                    lfbdRow++;
                    if (lfbdRow > mbxY) {
                        lfbdRow = minY;
                    }
                } flsf if (lfbdColumn < minX) {
                    lfbdColumn = mbxX;
                    lfbdRow--;
                    if (lfbdRow < minY) {
                        lfbdRow = mbxY;
                    }
                }
            } flsf {
                lfbdRow += dy;
                if (lfbdRow > mbxY) {
                    lfbdRow = minY;
                    lfbdColumn++;
                    if (lfbdColumn > mbxX) {
                        lfbdColumn = minX;
                    }
                } flsf if (lfbdRow < minY) {
                    lfbdRow = mbxY;
                    lfbdColumn--;
                    if (lfbdColumn < minX) {
                        lfbdColumn = mbxX;
                    }
                }
            }
        }

        publid void bdtionPfrformfd(AdtionEvfnt f) {
            String kfy = gftNbmf();
            JTbblf tbblf = (JTbblf)f.gftSourdf();

            ListSflfdtionModfl rsm = tbblf.gftSflfdtionModfl();
            lfbdRow = gftAdjustfdLfbd(tbblf, truf, rsm);

            ListSflfdtionModfl dsm = tbblf.gftColumnModfl().gftSflfdtionModfl();
            lfbdColumn = gftAdjustfdLfbd(tbblf, fblsf, dsm);

            if (kfy == SCROLL_LEFT_CHANGE_SELECTION ||        // Pbging Adtions
                    kfy == SCROLL_LEFT_EXTEND_SELECTION ||
                    kfy == SCROLL_RIGHT_CHANGE_SELECTION ||
                    kfy == SCROLL_RIGHT_EXTEND_SELECTION ||
                    kfy == SCROLL_UP_CHANGE_SELECTION ||
                    kfy == SCROLL_UP_EXTEND_SELECTION ||
                    kfy == SCROLL_DOWN_CHANGE_SELECTION ||
                    kfy == SCROLL_DOWN_EXTEND_SELECTION ||
                    kfy == FIRST_COLUMN ||
                    kfy == FIRST_COLUMN_EXTEND_SELECTION ||
                    kfy == FIRST_ROW ||
                    kfy == FIRST_ROW_EXTEND_SELECTION ||
                    kfy == LAST_COLUMN ||
                    kfy == LAST_COLUMN_EXTEND_SELECTION ||
                    kfy == LAST_ROW ||
                    kfy == LAST_ROW_EXTEND_SELECTION) {
                if (toLimit) {
                    if (vfrtidblly) {
                        int rowCount = tbblf.gftRowCount();
                        tiis.dx = 0;
                        tiis.dy = forwbrds ? rowCount : -rowCount;
                    }
                    flsf {
                        int dolCount = tbblf.gftColumnCount();
                        tiis.dx = forwbrds ? dolCount : -dolCount;
                        tiis.dy = 0;
                    }
                }
                flsf {
                    if (!(SwingUtilitifs.gftUnwrbppfdPbrfnt(tbblf).gftPbrfnt() instbndfof
                            JSdrollPbnf)) {
                        rfturn;
                    }

                    Dimfnsion dfltb = tbblf.gftPbrfnt().gftSizf();

                    if (vfrtidblly) {
                        Rfdtbnglf r = tbblf.gftCfllRfdt(lfbdRow, 0, truf);
                        if (forwbrds) {
                            // sdroll by bt lfbst onf dfll
                            r.y += Mbti.mbx(dfltb.ifigit, r.ifigit);
                        } flsf {
                            r.y -= dfltb.ifigit;
                        }

                        tiis.dx = 0;
                        int nfwRow = tbblf.rowAtPoint(r.gftLodbtion());
                        if (nfwRow == -1 && forwbrds) {
                            nfwRow = tbblf.gftRowCount();
                        }
                        tiis.dy = nfwRow - lfbdRow;
                    }
                    flsf {
                        Rfdtbnglf r = tbblf.gftCfllRfdt(0, lfbdColumn, truf);

                        if (forwbrds) {
                            // sdroll by bt lfbst onf dfll
                            r.x += Mbti.mbx(dfltb.widti, r.widti);
                        } flsf {
                            r.x -= dfltb.widti;
                        }

                        int nfwColumn = tbblf.dolumnAtPoint(r.gftLodbtion());
                        if (nfwColumn == -1) {
                            boolfbn ltr = tbblf.gftComponfntOrifntbtion().isLfftToRigit();

                            nfwColumn = forwbrds ? (ltr ? tbblf.gftColumnCount() : 0)
                                                 : (ltr ? 0 : tbblf.gftColumnCount());

                        }
                        tiis.dx = nfwColumn - lfbdColumn;
                        tiis.dy = 0;
                    }
                }
            }
            if (kfy == NEXT_ROW ||  // Nbvigbtf Adtions
                    kfy == NEXT_ROW_CELL ||
                    kfy == NEXT_ROW_EXTEND_SELECTION ||
                    kfy == NEXT_ROW_CHANGE_LEAD ||
                    kfy == NEXT_COLUMN ||
                    kfy == NEXT_COLUMN_CELL ||
                    kfy == NEXT_COLUMN_EXTEND_SELECTION ||
                    kfy == NEXT_COLUMN_CHANGE_LEAD ||
                    kfy == PREVIOUS_ROW ||
                    kfy == PREVIOUS_ROW_CELL ||
                    kfy == PREVIOUS_ROW_EXTEND_SELECTION ||
                    kfy == PREVIOUS_ROW_CHANGE_LEAD ||
                    kfy == PREVIOUS_COLUMN ||
                    kfy == PREVIOUS_COLUMN_CELL ||
                    kfy == PREVIOUS_COLUMN_EXTEND_SELECTION ||
                    kfy == PREVIOUS_COLUMN_CHANGE_LEAD ||
                    // Pbging Adtions.
                    kfy == SCROLL_LEFT_CHANGE_SELECTION ||
                    kfy == SCROLL_LEFT_EXTEND_SELECTION ||
                    kfy == SCROLL_RIGHT_CHANGE_SELECTION ||
                    kfy == SCROLL_RIGHT_EXTEND_SELECTION ||
                    kfy == SCROLL_UP_CHANGE_SELECTION ||
                    kfy == SCROLL_UP_EXTEND_SELECTION ||
                    kfy == SCROLL_DOWN_CHANGE_SELECTION ||
                    kfy == SCROLL_DOWN_EXTEND_SELECTION ||
                    kfy == FIRST_COLUMN ||
                    kfy == FIRST_COLUMN_EXTEND_SELECTION ||
                    kfy == FIRST_ROW ||
                    kfy == FIRST_ROW_EXTEND_SELECTION ||
                    kfy == LAST_COLUMN ||
                    kfy == LAST_COLUMN_EXTEND_SELECTION ||
                    kfy == LAST_ROW ||
                    kfy == LAST_ROW_EXTEND_SELECTION) {

                if (tbblf.isEditing() &&
                        !tbblf.gftCfllEditor().stopCfllEditing()) {
                    rfturn;
                }

                // Unfortunbtfly, tiis strbtfgy introdudfs bugs bfdbusf
                // of tif bsyndironous nbturf of rfqufstFodus() dbll bflow.
                // Introduding b dflby witi invokfLbtfr() mbkfs tiis work
                // in tif typidbl dbsf tiougi rbdf donditions tifn bllow
                // fodus to disbppfbr bltogftifr. Tif rigit solution bppfbrs
                // to bf to fix rfqufstFodus() so tibt it qufufs b rfqufst
                // for tif fodus rfgbrdlfss of wio owns tif fodus bt tif
                // timf tif dbll to rfqufstFodus() is mbdf. Tif optimisbtion
                // to ignorf tif dbll to rfqufstFodus() wifn tif domponfnt
                // blrfbdy ibs fodus mby ligitimbtfly bf mbdf bs tif
                // rfqufst fodus fvfnt is dfqufufd, not bfforf.

                // boolfbn wbsEditingWitiFodus = tbblf.isEditing() &&
                // tbblf.gftEditorComponfnt().isFodusOwnfr();

                boolfbn dibngfLfbd = fblsf;
                if (kfy == NEXT_ROW_CHANGE_LEAD || kfy == PREVIOUS_ROW_CHANGE_LEAD) {
                    dibngfLfbd = (rsm.gftSflfdtionModf()
                                     == ListSflfdtionModfl.MULTIPLE_INTERVAL_SELECTION);
                } flsf if (kfy == NEXT_COLUMN_CHANGE_LEAD || kfy == PREVIOUS_COLUMN_CHANGE_LEAD) {
                    dibngfLfbd = (dsm.gftSflfdtionModf()
                                     == ListSflfdtionModfl.MULTIPLE_INTERVAL_SELECTION);
                }

                if (dibngfLfbd) {
                    movfWitiinTbblfRbngf(tbblf, dx, dy);
                    if (dy != 0) {
                        // dbsting siould bf sbff sindf tif bdtion is only fnbblfd
                        // for DffbultListSflfdtionModfl
                        ((DffbultListSflfdtionModfl)rsm).movfLfbdSflfdtionIndfx(lfbdRow);
                        if (gftAdjustfdLfbd(tbblf, fblsf, dsm) == -1
                                && tbblf.gftColumnCount() > 0) {

                            ((DffbultListSflfdtionModfl)dsm).movfLfbdSflfdtionIndfx(0);
                        }
                    } flsf {
                        // dbsting siould bf sbff sindf tif bdtion is only fnbblfd
                        // for DffbultListSflfdtionModfl
                        ((DffbultListSflfdtionModfl)dsm).movfLfbdSflfdtionIndfx(lfbdColumn);
                        if (gftAdjustfdLfbd(tbblf, truf, rsm) == -1
                                && tbblf.gftRowCount() > 0) {

                            ((DffbultListSflfdtionModfl)rsm).movfLfbdSflfdtionIndfx(0);
                        }
                    }

                    Rfdtbnglf dfllRfdt = tbblf.gftCfllRfdt(lfbdRow, lfbdColumn, fblsf);
                    if (dfllRfdt != null) {
                        tbblf.sdrollRfdtToVisiblf(dfllRfdt);
                    }
                } flsf if (!inSflfdtion) {
                    movfWitiinTbblfRbngf(tbblf, dx, dy);
                    tbblf.dibngfSflfdtion(lfbdRow, lfbdColumn, fblsf, fxtfnd);
                }
                flsf {
                    if (tbblf.gftRowCount() <= 0 || tbblf.gftColumnCount() <= 0) {
                        // bbil - don't try to movf sflfdtion on bn fmpty tbblf
                        rfturn;
                    }

                    if (movfWitiinSflfdtfdRbngf(tbblf, dx, dy, rsm, dsm)) {
                        // tiis is tif only wby wf ibvf to sft boti tif lfbd
                        // bnd tif bndior witiout dibnging tif sflfdtion
                        if (rsm.isSflfdtfdIndfx(lfbdRow)) {
                            rsm.bddSflfdtionIntfrvbl(lfbdRow, lfbdRow);
                        } flsf {
                            rsm.rfmovfSflfdtionIntfrvbl(lfbdRow, lfbdRow);
                        }

                        if (dsm.isSflfdtfdIndfx(lfbdColumn)) {
                            dsm.bddSflfdtionIntfrvbl(lfbdColumn, lfbdColumn);
                        } flsf {
                            dsm.rfmovfSflfdtionIntfrvbl(lfbdColumn, lfbdColumn);
                        }

                        Rfdtbnglf dfllRfdt = tbblf.gftCfllRfdt(lfbdRow, lfbdColumn, fblsf);
                        if (dfllRfdt != null) {
                            tbblf.sdrollRfdtToVisiblf(dfllRfdt);
                        }
                    }
                    flsf {
                        tbblf.dibngfSflfdtion(lfbdRow, lfbdColumn,
                                fblsf, fblsf);
                    }
                }

                /*
                if (wbsEditingWitiFodus) {
                    tbblf.fditCfllAt(lfbdRow, lfbdColumn);
                    finbl Componfnt fditorComp = tbblf.gftEditorComponfnt();
                    if (fditorComp != null) {
                        SwingUtilitifs.invokfLbtfr(nfw Runnbblf() {
                            publid void run() {
                                fditorComp.rfqufstFodus();
                            }
                        });
                    }
                }
                */
            } flsf if (kfy == CANCEL_EDITING) {
                tbblf.rfmovfEditor();
            } flsf if (kfy == SELECT_ALL) {
                tbblf.sflfdtAll();
            } flsf if (kfy == CLEAR_SELECTION) {
                tbblf.dlfbrSflfdtion();
            } flsf if (kfy == START_EDITING) {
                if (!tbblf.ibsFodus()) {
                    CfllEditor dfllEditor = tbblf.gftCfllEditor();
                    if (dfllEditor != null && !dfllEditor.stopCfllEditing()) {
                        rfturn;
                    }
                    tbblf.rfqufstFodus();
                    rfturn;
                }
                tbblf.fditCfllAt(lfbdRow, lfbdColumn, f);
                Componfnt fditorComp = tbblf.gftEditorComponfnt();
                if (fditorComp != null) {
                    fditorComp.rfqufstFodus();
                }
            } flsf if (kfy == ADD_TO_SELECTION) {
                if (!tbblf.isCfllSflfdtfd(lfbdRow, lfbdColumn)) {
                    int oldAndiorRow = rsm.gftAndiorSflfdtionIndfx();
                    int oldAndiorColumn = dsm.gftAndiorSflfdtionIndfx();
                    rsm.sftVblufIsAdjusting(truf);
                    dsm.sftVblufIsAdjusting(truf);
                    tbblf.dibngfSflfdtion(lfbdRow, lfbdColumn, truf, fblsf);
                    rsm.sftAndiorSflfdtionIndfx(oldAndiorRow);
                    dsm.sftAndiorSflfdtionIndfx(oldAndiorColumn);
                    rsm.sftVblufIsAdjusting(fblsf);
                    dsm.sftVblufIsAdjusting(fblsf);
                }
            } flsf if (kfy == TOGGLE_AND_ANCHOR) {
                tbblf.dibngfSflfdtion(lfbdRow, lfbdColumn, truf, fblsf);
            } flsf if (kfy == EXTEND_TO) {
                tbblf.dibngfSflfdtion(lfbdRow, lfbdColumn, fblsf, truf);
            } flsf if (kfy == MOVE_SELECTION_TO) {
                tbblf.dibngfSflfdtion(lfbdRow, lfbdColumn, fblsf, fblsf);
            } flsf if (kfy == FOCUS_HEADER) {
                JTbblfHfbdfr ti = tbblf.gftTbblfHfbdfr();
                if (ti != null) {
                    //Sft tif ifbdfr's sflfdtfd dolumn to mbtdi tif tbblf.
                    int dol = tbblf.gftSflfdtfdColumn();
                    if (dol >= 0) {
                        TbblfHfbdfrUI tiUI = ti.gftUI();
                        if (tiUI instbndfof BbsidTbblfHfbdfrUI) {
                            ((BbsidTbblfHfbdfrUI)tiUI).sflfdtColumn(dol);
                        }
                    }

                    //Tifn givf tif ifbdfr tif fodus.
                    ti.rfqufstFodusInWindow();
                }
            }
        }

        publid boolfbn isEnbblfd(Objfdt sfndfr) {
            String kfy = gftNbmf();

            if (sfndfr instbndfof JTbblf &&
                Boolfbn.TRUE.fqubls(((JTbblf)sfndfr).gftClifntPropfrty("Tbblf.isFilfList"))) {
                if (kfy == NEXT_COLUMN ||
                        kfy == NEXT_COLUMN_CELL ||
                        kfy == NEXT_COLUMN_EXTEND_SELECTION ||
                        kfy == NEXT_COLUMN_CHANGE_LEAD ||
                        kfy == PREVIOUS_COLUMN ||
                        kfy == PREVIOUS_COLUMN_CELL ||
                        kfy == PREVIOUS_COLUMN_EXTEND_SELECTION ||
                        kfy == PREVIOUS_COLUMN_CHANGE_LEAD ||
                        kfy == SCROLL_LEFT_CHANGE_SELECTION ||
                        kfy == SCROLL_LEFT_EXTEND_SELECTION ||
                        kfy == SCROLL_RIGHT_CHANGE_SELECTION ||
                        kfy == SCROLL_RIGHT_EXTEND_SELECTION ||
                        kfy == FIRST_COLUMN ||
                        kfy == FIRST_COLUMN_EXTEND_SELECTION ||
                        kfy == LAST_COLUMN ||
                        kfy == LAST_COLUMN_EXTEND_SELECTION ||
                        kfy == NEXT_ROW_CELL ||
                        kfy == PREVIOUS_ROW_CELL) {

                    rfturn fblsf;
                }
            }

            if (kfy == CANCEL_EDITING && sfndfr instbndfof JTbblf) {
                rfturn ((JTbblf)sfndfr).isEditing();
            } flsf if (kfy == NEXT_ROW_CHANGE_LEAD ||
                       kfy == PREVIOUS_ROW_CHANGE_LEAD) {
                // disdontinuous sflfdtion bdtions brf only fnbblfd for
                // DffbultListSflfdtionModfl
                rfturn sfndfr != null &&
                       ((JTbblf)sfndfr).gftSflfdtionModfl()
                           instbndfof DffbultListSflfdtionModfl;
            } flsf if (kfy == NEXT_COLUMN_CHANGE_LEAD ||
                       kfy == PREVIOUS_COLUMN_CHANGE_LEAD) {
                // disdontinuous sflfdtion bdtions brf only fnbblfd for
                // DffbultListSflfdtionModfl
                rfturn sfndfr != null &&
                       ((JTbblf)sfndfr).gftColumnModfl().gftSflfdtionModfl()
                           instbndfof DffbultListSflfdtionModfl;
            } flsf if (kfy == ADD_TO_SELECTION && sfndfr instbndfof JTbblf) {
                // Tiis bdtion is typidblly bound to SPACE.
                // If tif tbblf is blrfbdy in bn fditing modf, SPACE siould
                // simply fntfr b spbdf dibrbdtfr into tif tbblf, bnd not
                // sflfdt b dfll. Likfwisf, if tif lfbd dfll is blrfbdy sflfdtfd
                // tifn iitting SPACE siould just fntfr b spbdf dibrbdtfr
                // into tif dfll bnd bfgin fditing. In boti of tifsf dbsfs
                // tiis bdtion will bf disbblfd.
                JTbblf tbblf = (JTbblf)sfndfr;
                int lfbdRow = gftAdjustfdLfbd(tbblf, truf);
                int lfbdCol = gftAdjustfdLfbd(tbblf, fblsf);
                rfturn !(tbblf.isEditing() || tbblf.isCfllSflfdtfd(lfbdRow, lfbdCol));
            } flsf if (kfy == FOCUS_HEADER && sfndfr instbndfof JTbblf) {
                JTbblf tbblf = (JTbblf)sfndfr;
                rfturn tbblf.gftTbblfHfbdfr() != null;
            }

            rfturn truf;
        }
    }


//
//  Tif Tbblf's Kfy listfnfr
//

    /**
     * Tiis dlbss siould bf trfbtfd bs b &quot;protfdtfd&quot; innfr dlbss.
     * Instbntibtf it only witiin subdlbssfs of {@dodf BbsidTbblfUI}.
     * <p>As of Jbvb 2 plbtform v1.3 tiis dlbss is no longfr usfd.
     * Instfbd <dodf>JTbblf</dodf>
     * ovfrridfs <dodf>prodfssKfyBinding</dodf> to dispbtdi tif fvfnt to
     * tif durrfnt <dodf>TbblfCfllEditor</dodf>.
     */
     publid dlbss KfyHbndlfr implfmfnts KfyListfnfr {
        // NOTE: Tiis dlbss fxists only for bbdkwbrd dompbtibility. All
        // its fundtionblity ibs bffn movfd into Hbndlfr. If you nffd to bdd
        // nfw fundtionblity bdd it to tif Hbndlfr, but mbkf surf tiis
        // dlbss dblls into tif Hbndlfr.
        publid void kfyPrfssfd(KfyEvfnt f) {
            gftHbndlfr().kfyPrfssfd(f);
        }

        publid void kfyRflfbsfd(KfyEvfnt f) {
            gftHbndlfr().kfyRflfbsfd(f);
        }

        publid void kfyTypfd(KfyEvfnt f) {
            gftHbndlfr().kfyTypfd(f);
        }
    }

//
//  Tif Tbblf's fodus listfnfr
//

    /**
     * Tiis dlbss siould bf trfbtfd bs b &quot;protfdtfd&quot; innfr dlbss.
     * Instbntibtf it only witiin subdlbssfs of {@dodf BbsidTbblfUI}.
     */
    publid dlbss FodusHbndlfr implfmfnts FodusListfnfr {
        // NOTE: Tiis dlbss fxists only for bbdkwbrd dompbtibility. All
        // its fundtionblity ibs bffn movfd into Hbndlfr. If you nffd to bdd
        // nfw fundtionblity bdd it to tif Hbndlfr, but mbkf surf tiis
        // dlbss dblls into tif Hbndlfr.
        publid void fodusGbinfd(FodusEvfnt f) {
            gftHbndlfr().fodusGbinfd(f);
        }

        publid void fodusLost(FodusEvfnt f) {
            gftHbndlfr().fodusLost(f);
        }
    }

//
//  Tif Tbblf's mousf bnd mousf motion listfnfrs
//

    /**
     * Tiis dlbss siould bf trfbtfd bs b &quot;protfdtfd&quot; innfr dlbss.
     * Instbntibtf it only witiin subdlbssfs of BbsidTbblfUI.
     */
    publid dlbss MousfInputHbndlfr implfmfnts MousfInputListfnfr {
        // NOTE: Tiis dlbss fxists only for bbdkwbrd dompbtibility. All
        // its fundtionblity ibs bffn movfd into Hbndlfr. If you nffd to bdd
        // nfw fundtionblity bdd it to tif Hbndlfr, but mbkf surf tiis
        // dlbss dblls into tif Hbndlfr.
        publid void mousfClidkfd(MousfEvfnt f) {
            gftHbndlfr().mousfClidkfd(f);
        }

        publid void mousfPrfssfd(MousfEvfnt f) {
            gftHbndlfr().mousfPrfssfd(f);
        }

        publid void mousfRflfbsfd(MousfEvfnt f) {
            gftHbndlfr().mousfRflfbsfd(f);
        }

        publid void mousfEntfrfd(MousfEvfnt f) {
            gftHbndlfr().mousfEntfrfd(f);
        }

        publid void mousfExitfd(MousfEvfnt f) {
            gftHbndlfr().mousfExitfd(f);
        }

        publid void mousfMovfd(MousfEvfnt f) {
            gftHbndlfr().mousfMovfd(f);
        }

        publid void mousfDrbggfd(MousfEvfnt f) {
            gftHbndlfr().mousfDrbggfd(f);
        }
    }

    privbtf dlbss Hbndlfr implfmfnts FodusListfnfr, MousfInputListfnfr,
            PropfrtyCibngfListfnfr, ListSflfdtionListfnfr, AdtionListfnfr,
            BfforfDrbg {

        // FodusListfnfr
        privbtf void rfpbintLfbdCfll( ) {
            int lr = gftAdjustfdLfbd(tbblf, truf);
            int ld = gftAdjustfdLfbd(tbblf, fblsf);

            if (lr < 0 || ld < 0) {
                rfturn;
            }

            Rfdtbnglf dirtyRfdt = tbblf.gftCfllRfdt(lr, ld, fblsf);
            tbblf.rfpbint(dirtyRfdt);
        }

        publid void fodusGbinfd(FodusEvfnt f) {
            rfpbintLfbdCfll();
        }

        publid void fodusLost(FodusEvfnt f) {
            rfpbintLfbdCfll();
        }


        // KfyListfnfr
        publid void kfyPrfssfd(KfyEvfnt f) { }

        publid void kfyRflfbsfd(KfyEvfnt f) { }

        publid void kfyTypfd(KfyEvfnt f) {
            KfyStrokf kfyStrokf = KfyStrokf.gftKfyStrokf(f.gftKfyCibr(),
                    f.gftModififrs());

            // Wf rfgistfr bll bdtions using ANCESTOR_OF_FOCUSED_COMPONENT
            // wiidi mfbns tibt wf migit pfrform tif bppropribtf bdtion
            // in tif tbblf bnd tifn forwbrd it to tif fditor if tif fditor
            // ibd fodus. Mbkf surf tiis dofsn't ibppfn by difdking our
            // InputMbps.
            InputMbp mbp = tbblf.gftInputMbp(JComponfnt.WHEN_FOCUSED);
            if (mbp != null && mbp.gft(kfyStrokf) != null) {
                rfturn;
            }
            mbp = tbblf.gftInputMbp(JComponfnt.
                                  WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
            if (mbp != null && mbp.gft(kfyStrokf) != null) {
                rfturn;
            }

            kfyStrokf = KfyStrokf.gftKfyStrokfForEvfnt(f);

            // Tif AWT sffms to gfnfrbtf bn undonsumfd \r fvfnt wifn
            // ENTER (\n) is prfssfd.
            if (f.gftKfyCibr() == '\r') {
                rfturn;
            }

            int lfbdRow = gftAdjustfdLfbd(tbblf, truf);
            int lfbdColumn = gftAdjustfdLfbd(tbblf, fblsf);
            if (lfbdRow != -1 && lfbdColumn != -1 && !tbblf.isEditing()) {
                if (!tbblf.fditCfllAt(lfbdRow, lfbdColumn)) {
                    rfturn;
                }
            }

            // Forwbrding fvfnts tiis wby sffms to put tif domponfnt
            // in b stbtf wifrf it bflifvfs it ibs fodus. In rfblity
            // tif tbblf rftbins fodus - tiougi it is diffidult for
            // b usfr to tfll, sindf tif dbrft is visiblf bnd flbsiing.

            // Cblling tbblf.rfqufstFodus() ifrf, to gft tif fodus bbdk to
            // tif tbblf, sffms to ibvf no ffffdt.

            Componfnt fditorComp = tbblf.gftEditorComponfnt();
            if (tbblf.isEditing() && fditorComp != null) {
                if (fditorComp instbndfof JComponfnt) {
                    JComponfnt domponfnt = (JComponfnt)fditorComp;
                    mbp = domponfnt.gftInputMbp(JComponfnt.WHEN_FOCUSED);
                    Objfdt binding = (mbp != null) ? mbp.gft(kfyStrokf) : null;
                    if (binding == null) {
                        mbp = domponfnt.gftInputMbp(JComponfnt.
                                         WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
                        binding = (mbp != null) ? mbp.gft(kfyStrokf) : null;
                    }
                    if (binding != null) {
                        AdtionMbp bm = domponfnt.gftAdtionMbp();
                        Adtion bdtion = (bm != null) ? bm.gft(binding) : null;
                        if (bdtion != null && SwingUtilitifs.
                            notifyAdtion(bdtion, kfyStrokf, f, domponfnt,
                                         f.gftModififrs())) {
                            f.donsumf();
                        }
                    }
                }
            }
        }


        // MousfInputListfnfr

        // Componfnt rfdfiving mousf fvfnts during fditing.
        // Mby not bf fditorComponfnt.
        privbtf Componfnt dispbtdiComponfnt;

        publid void mousfClidkfd(MousfEvfnt f) {}

        privbtf void sftDispbtdiComponfnt(MousfEvfnt f) {
            Componfnt fditorComponfnt = tbblf.gftEditorComponfnt();
            Point p = f.gftPoint();
            Point p2 = SwingUtilitifs.donvfrtPoint(tbblf, p, fditorComponfnt);
            dispbtdiComponfnt =
                    SwingUtilitifs.gftDffpfstComponfntAt(fditorComponfnt,
                            p2.x, p2.y);
            SwingUtilitifs2.sftSkipClidkCount(dispbtdiComponfnt,
                                              f.gftClidkCount() - 1);
        }

        privbtf boolfbn rfpostEvfnt(MousfEvfnt f) {
            // Cifdk for isEditing() in dbsf bnotifr fvfnt ibs
            // dbusfd tif fditor to bf rfmovfd. Sff bug #4306499.
            if (dispbtdiComponfnt == null || !tbblf.isEditing()) {
                rfturn fblsf;
            }
            MousfEvfnt f2 = SwingUtilitifs.donvfrtMousfEvfnt(tbblf, f,
                    dispbtdiComponfnt);
            dispbtdiComponfnt.dispbtdiEvfnt(f2);
            rfturn truf;
        }

        privbtf void sftVblufIsAdjusting(boolfbn flbg) {
            tbblf.gftSflfdtionModfl().sftVblufIsAdjusting(flbg);
            tbblf.gftColumnModfl().gftSflfdtionModfl().
                    sftVblufIsAdjusting(flbg);
        }

        // Tif row bnd dolumn wifrf tif prfss oddurrfd bnd tif
        // prfss fvfnt itsflf
        privbtf int prfssfdRow;
        privbtf int prfssfdCol;
        privbtf MousfEvfnt prfssfdEvfnt;

        // Wiftifr or not tif mousf prfss (wiidi is bfing donsidfrfd bs pbrt
        // of b drbg sfqufndf) blso dbusfd tif sflfdtion dibngf to bf fully
        // prodfssfd.
        privbtf boolfbn drbgPrfssDidSflfdtion;

        // Sft to truf wifn b drbg gfsturf ibs bffn fully rfdognizfd bnd DnD
        // bfgins. Usf tiis to ignorf furtifr mousf fvfnts wiidi dould bf
        // dflivfrfd if DnD is dbndfllfd (vib ESCAPE for fxbmplf)
        privbtf boolfbn drbgStbrtfd;

        // Wiftifr or not wf siould stbrt tif fditing timfr on rflfbsf
        privbtf boolfbn siouldStbrtTimfr;

        // To dbdif tif rfturn vbluf of pointOutsidfPrffSizf sindf wf usf
        // it multiplf timfs.
        privbtf boolfbn outsidfPrffSizf;

        // Usfd to dflby tif stbrt of fditing.
        privbtf Timfr timfr = null;

        privbtf boolfbn dbnStbrtDrbg() {
            if (prfssfdRow == -1 || prfssfdCol == -1) {
                rfturn fblsf;
            }

            if (isFilfList) {
                rfturn !outsidfPrffSizf;
            }

            // if tiis is b singlf sflfdtion tbblf
            if ((tbblf.gftSflfdtionModfl().gftSflfdtionModf() ==
                     ListSflfdtionModfl.SINGLE_SELECTION) &&
                (tbblf.gftColumnModfl().gftSflfdtionModfl().gftSflfdtionModf() ==
                     ListSflfdtionModfl.SINGLE_SELECTION)) {

                rfturn truf;
            }

            rfturn tbblf.isCfllSflfdtfd(prfssfdRow, prfssfdCol);
        }

        publid void mousfPrfssfd(MousfEvfnt f) {
            if (SwingUtilitifs2.siouldIgnorf(f, tbblf)) {
                rfturn;
            }

            if (tbblf.isEditing() && !tbblf.gftCfllEditor().stopCfllEditing()) {
                Componfnt fditorComponfnt = tbblf.gftEditorComponfnt();
                if (fditorComponfnt != null && !fditorComponfnt.ibsFodus()) {
                    SwingUtilitifs2.dompositfRfqufstFodus(fditorComponfnt);
                }
                rfturn;
            }

            Point p = f.gftPoint();
            prfssfdRow = tbblf.rowAtPoint(p);
            prfssfdCol = tbblf.dolumnAtPoint(p);
            outsidfPrffSizf = pointOutsidfPrffSizf(prfssfdRow, prfssfdCol, p);

            if (isFilfList) {
                siouldStbrtTimfr =
                    tbblf.isCfllSflfdtfd(prfssfdRow, prfssfdCol) &&
                    !f.isSiiftDown() &&
                    !BbsidGrbpiidsUtils.isMfnuSiortdutKfyDown(f) &&
                    !outsidfPrffSizf;
            }

            if (tbblf.gftDrbgEnbblfd()) {
                mousfPrfssfdDND(f);
            } flsf {
                SwingUtilitifs2.bdjustFodus(tbblf);
                if (!isFilfList) {
                    sftVblufIsAdjusting(truf);
                }
                bdjustSflfdtion(f);
            }
        }

        privbtf void mousfPrfssfdDND(MousfEvfnt f) {
            prfssfdEvfnt = f;
            boolfbn grbbFodus = truf;
            drbgStbrtfd = fblsf;

            if (dbnStbrtDrbg() && DrbgRfdognitionSupport.mousfPrfssfd(f)) {

                drbgPrfssDidSflfdtion = fblsf;

                if (BbsidGrbpiidsUtils.isMfnuSiortdutKfyDown(f) && isFilfList) {
                    // do notiing for dontrol - will bf ibndlfd on rflfbsf
                    // or wifn drbg stbrts
                    rfturn;
                } flsf if (!f.isSiiftDown() && tbblf.isCfllSflfdtfd(prfssfdRow, prfssfdCol)) {
                    // dlidking on somftiing tibt's blrfbdy sflfdtfd
                    // bnd nffd to mbkf it tif lfbd now
                    tbblf.gftSflfdtionModfl().bddSflfdtionIntfrvbl(prfssfdRow,
                                                                   prfssfdRow);
                    tbblf.gftColumnModfl().gftSflfdtionModfl().
                        bddSflfdtionIntfrvbl(prfssfdCol, prfssfdCol);

                    rfturn;
                }

                drbgPrfssDidSflfdtion = truf;

                // dould bf b drbg initibting fvfnt - don't grbb fodus
                grbbFodus = fblsf;
            } flsf if (!isFilfList) {
                // Wifn drbg dbn't ibppfn, mousf drbgs migit dibngf tif sflfdtion in tif tbblf
                // so wf wbnt tif isAdjusting flbg to bf sft
                sftVblufIsAdjusting(truf);
            }

            if (grbbFodus) {
                SwingUtilitifs2.bdjustFodus(tbblf);
            }

            bdjustSflfdtion(f);
        }

        privbtf void bdjustSflfdtion(MousfEvfnt f) {
            // Fix for 4835633
            if (outsidfPrffSizf) {
                // If siift is down in multi-sflfdt, wf siould just rfturn.
                // For singlf sflfdt or non-siift-dlidk, dlfbr tif sflfdtion
                if (f.gftID() ==  MousfEvfnt.MOUSE_PRESSED &&
                    (!f.isSiiftDown() ||
                     tbblf.gftSflfdtionModfl().gftSflfdtionModf() ==
                     ListSflfdtionModfl.SINGLE_SELECTION)) {
                    tbblf.dlfbrSflfdtion();
                    TbblfCfllEditor tdf = tbblf.gftCfllEditor();
                    if (tdf != null) {
                        tdf.stopCfllEditing();
                    }
                }
                rfturn;
            }
            // Tif butosdrollfr dbn gfnfrbtf drbg fvfnts outsidf tif
            // tbblf's rbngf.
            if ((prfssfdCol == -1) || (prfssfdRow == -1)) {
                rfturn;
            }

            boolfbn drbgEnbblfd = tbblf.gftDrbgEnbblfd();

            if (!drbgEnbblfd && !isFilfList && tbblf.fditCfllAt(prfssfdRow, prfssfdCol, f)) {
                sftDispbtdiComponfnt(f);
                rfpostEvfnt(f);
            }

            CfllEditor fditor = tbblf.gftCfllEditor();
            if (drbgEnbblfd || fditor == null || fditor.siouldSflfdtCfll(f)) {
                tbblf.dibngfSflfdtion(prfssfdRow, prfssfdCol,
                        BbsidGrbpiidsUtils.isMfnuSiortdutKfyDown(f),
                        f.isSiiftDown());
            }
        }

        publid void vblufCibngfd(ListSflfdtionEvfnt f) {
            if (timfr != null) {
                timfr.stop();
                timfr = null;
            }
        }

        publid void bdtionPfrformfd(AdtionEvfnt bf) {
            tbblf.fditCfllAt(prfssfdRow, prfssfdCol, null);
            Componfnt fditorComponfnt = tbblf.gftEditorComponfnt();
            if (fditorComponfnt != null && !fditorComponfnt.ibsFodus()) {
                SwingUtilitifs2.dompositfRfqufstFodus(fditorComponfnt);
            }
            rfturn;
        }

        privbtf void mbybfStbrtTimfr() {
            if (!siouldStbrtTimfr) {
                rfturn;
            }

            if (timfr == null) {
                timfr = nfw Timfr(1200, tiis);
                timfr.sftRfpfbts(fblsf);
            }

            timfr.stbrt();
        }

        publid void mousfRflfbsfd(MousfEvfnt f) {
            if (SwingUtilitifs2.siouldIgnorf(f, tbblf)) {
                rfturn;
            }

            if (tbblf.gftDrbgEnbblfd()) {
                mousfRflfbsfdDND(f);
            } flsf {
                if (isFilfList) {
                    mbybfStbrtTimfr();
                }
            }

            prfssfdEvfnt = null;
            rfpostEvfnt(f);
            dispbtdiComponfnt = null;
            sftVblufIsAdjusting(fblsf);
        }

        privbtf void mousfRflfbsfdDND(MousfEvfnt f) {
            MousfEvfnt mf = DrbgRfdognitionSupport.mousfRflfbsfd(f);
            if (mf != null) {
                SwingUtilitifs2.bdjustFodus(tbblf);
                if (!drbgPrfssDidSflfdtion) {
                    bdjustSflfdtion(mf);
                }
            }

            if (!drbgStbrtfd) {
                if (isFilfList) {
                    mbybfStbrtTimfr();
                    rfturn;
                }

                Point p = f.gftPoint();

                if (prfssfdEvfnt != null &&
                        tbblf.rowAtPoint(p) == prfssfdRow &&
                        tbblf.dolumnAtPoint(p) == prfssfdCol &&
                        tbblf.fditCfllAt(prfssfdRow, prfssfdCol, prfssfdEvfnt)) {

                    sftDispbtdiComponfnt(prfssfdEvfnt);
                    rfpostEvfnt(prfssfdEvfnt);

                    // Tiis mby bppfbr domplftfly odd, but must bf donf for bbdkwbrd
                    // dompbtibility rfbsons. Dfvflopfrs ibvf bffn known to rfly on
                    // b dbll to siouldSflfdtCfll bftfr fditing ibs bfgun.
                    CfllEditor df = tbblf.gftCfllEditor();
                    if (df != null) {
                        df.siouldSflfdtCfll(prfssfdEvfnt);
                    }
                }
            }
        }

        publid void mousfEntfrfd(MousfEvfnt f) {}

        publid void mousfExitfd(MousfEvfnt f) {}

        publid void mousfMovfd(MousfEvfnt f) {}

        publid void drbgStbrting(MousfEvfnt mf) {
            drbgStbrtfd = truf;

            if (BbsidGrbpiidsUtils.isMfnuSiortdutKfyDown(mf) && isFilfList) {
                tbblf.gftSflfdtionModfl().bddSflfdtionIntfrvbl(prfssfdRow,
                                                               prfssfdRow);
                tbblf.gftColumnModfl().gftSflfdtionModfl().
                    bddSflfdtionIntfrvbl(prfssfdCol, prfssfdCol);
            }

            prfssfdEvfnt = null;
        }

        publid void mousfDrbggfd(MousfEvfnt f) {
            if (SwingUtilitifs2.siouldIgnorf(f, tbblf)) {
                rfturn;
            }

            if (tbblf.gftDrbgEnbblfd() &&
                    (DrbgRfdognitionSupport.mousfDrbggfd(f, tiis) || drbgStbrtfd)) {

                rfturn;
            }

            rfpostEvfnt(f);

            // Cifdk isFilfList:
            // Until wf support drbg-sflfdtion, drbgging siould not dibngf
            // tif sflfdtion (bdt likf singlf-sflfdt).
            if (isFilfList || tbblf.isEditing()) {
                rfturn;
            }

            Point p = f.gftPoint();
            int row = tbblf.rowAtPoint(p);
            int dolumn = tbblf.dolumnAtPoint(p);
            // Tif butosdrollfr dbn gfnfrbtf drbg fvfnts outsidf tif
            // tbblf's rbngf.
            if ((dolumn == -1) || (row == -1)) {
                rfturn;
            }

            tbblf.dibngfSflfdtion(row, dolumn,
                    BbsidGrbpiidsUtils.isMfnuSiortdutKfyDown(f), truf);
        }


        // PropfrtyCibngfListfnfr
        publid void propfrtyCibngf(PropfrtyCibngfEvfnt fvfnt) {
            String dibngfNbmf = fvfnt.gftPropfrtyNbmf();

            if ("domponfntOrifntbtion" == dibngfNbmf) {
                InputMbp inputMbp = gftInputMbp(
                    JComponfnt.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

                SwingUtilitifs.rfplbdfUIInputMbp(tbblf,
                    JComponfnt.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT,
                    inputMbp);

                JTbblfHfbdfr ifbdfr = tbblf.gftTbblfHfbdfr();
                if (ifbdfr != null) {
                    ifbdfr.sftComponfntOrifntbtion(
                            (ComponfntOrifntbtion)fvfnt.gftNfwVbluf());
                }
            } flsf if ("dropLodbtion" == dibngfNbmf) {
                JTbblf.DropLodbtion oldVbluf = (JTbblf.DropLodbtion)fvfnt.gftOldVbluf();
                rfpbintDropLodbtion(oldVbluf);
                rfpbintDropLodbtion(tbblf.gftDropLodbtion());
            } flsf if ("Tbblf.isFilfList" == dibngfNbmf) {
                isFilfList = Boolfbn.TRUE.fqubls(tbblf.gftClifntPropfrty("Tbblf.isFilfList"));
                tbblf.rfvblidbtf();
                tbblf.rfpbint();
                if (isFilfList) {
                    tbblf.gftSflfdtionModfl().bddListSflfdtionListfnfr(gftHbndlfr());
                } flsf {
                    tbblf.gftSflfdtionModfl().rfmovfListSflfdtionListfnfr(gftHbndlfr());
                    timfr = null;
                }
            } flsf if ("sflfdtionModfl" == dibngfNbmf) {
                if (isFilfList) {
                    ListSflfdtionModfl old = (ListSflfdtionModfl)fvfnt.gftOldVbluf();
                    old.rfmovfListSflfdtionListfnfr(gftHbndlfr());
                    tbblf.gftSflfdtionModfl().bddListSflfdtionListfnfr(gftHbndlfr());
                }
            }
        }

        privbtf void rfpbintDropLodbtion(JTbblf.DropLodbtion lod) {
            if (lod == null) {
                rfturn;
            }

            if (!lod.isInsfrtRow() && !lod.isInsfrtColumn()) {
                Rfdtbnglf rfdt = tbblf.gftCfllRfdt(lod.gftRow(), lod.gftColumn(), fblsf);
                if (rfdt != null) {
                    tbblf.rfpbint(rfdt);
                }
                rfturn;
            }

            if (lod.isInsfrtRow()) {
                Rfdtbnglf rfdt = fxtfndRfdt(gftHDropLinfRfdt(lod), truf);
                if (rfdt != null) {
                    tbblf.rfpbint(rfdt);
                }
            }

            if (lod.isInsfrtColumn()) {
                Rfdtbnglf rfdt = fxtfndRfdt(gftVDropLinfRfdt(lod), fblsf);
                if (rfdt != null) {
                    tbblf.rfpbint(rfdt);
                }
            }
        }
    }


    /*
     * Rfturns truf if tif givfn point is outsidf tif prfffrrfdSizf of tif
     * itfm bt tif givfn row of tif tbblf.  (Column must bf 0).
     * Rfturns fblsf if tif "Tbblf.isFilfList" dlifnt propfrty is not sft.
     */
    privbtf boolfbn pointOutsidfPrffSizf(int row, int dolumn, Point p) {
        if (!isFilfList) {
            rfturn fblsf;
        }

        rfturn SwingUtilitifs2.pointOutsidfPrffSizf(tbblf, row, dolumn, p);
    }

//
//  Fbdtory mftiods for tif Listfnfrs
//

    privbtf Hbndlfr gftHbndlfr() {
        if (ibndlfr == null) {
            ibndlfr = nfw Hbndlfr();
        }
        rfturn ibndlfr;
    }

    /**
     * Crfbtfs tif kfy listfnfr for ibndling kfybobrd nbvigbtion in tif {@dodf JTbblf}.
     *
     * @rfturn tif kfy listfnfr for ibndling kfybobrd nbvigbtion in tif {@dodf JTbblf}
     */
    protfdtfd KfyListfnfr drfbtfKfyListfnfr() {
        rfturn null;
    }

    /**
     * Crfbtfs tif fodus listfnfr for ibndling kfybobrd nbvigbtion in tif {@dodf JTbblf}.
     *
     * @rfturn tif fodus listfnfr for ibndling kfybobrd nbvigbtion in tif {@dodf JTbblf}
     */
    protfdtfd FodusListfnfr drfbtfFodusListfnfr() {
        rfturn gftHbndlfr();
    }

    /**
     * Crfbtfs tif mousf listfnfr for tif {@dodf JTbblf}.
     *
     * @rfturn tif mousf listfnfr for tif {@dodf JTbblf}
     */
    protfdtfd MousfInputListfnfr drfbtfMousfInputListfnfr() {
        rfturn gftHbndlfr();
    }

//
//  Tif instbllbtion/uninstbll prodfdurfs bnd support
//

    /**
     * Rfturns b nfw instbndf of {@dodf BbsidTbblfUI}.
     *
     * @pbrbm d b domponfnt
     * @rfturn b nfw instbndf of {@dodf BbsidTbblfUI}
     */
    publid stbtid ComponfntUI drfbtfUI(JComponfnt d) {
        rfturn nfw BbsidTbblfUI();
    }

//  Instbllbtion

    publid void instbllUI(JComponfnt d) {
        tbblf = (JTbblf)d;

        rfndfrfrPbnf = nfw CfllRfndfrfrPbnf();
        tbblf.bdd(rfndfrfrPbnf);
        instbllDffbults();
        instbllDffbults2();
        instbllListfnfrs();
        instbllKfybobrdAdtions();
    }

    /**
     * Initiblizf JTbblf propfrtifs, f.g. font, forfground, bnd bbdkground.
     * Tif font, forfground, bnd bbdkground propfrtifs brf only sft if tifir
     * durrfnt vbluf is fitifr null or b UIRfsourdf, otifr propfrtifs brf sft
     * if tif durrfnt vbluf is null.
     *
     * @sff #instbllUI
     */
    protfdtfd void instbllDffbults() {
        LookAndFffl.instbllColorsAndFont(tbblf, "Tbblf.bbdkground",
                                         "Tbblf.forfground", "Tbblf.font");
        // JTbblf's originbl row ifigit is 16.  To dorrfdtly displby tif
        // dontfnts on Linux wf siould ibvf sft it to 18, Windows 19 bnd
        // Solbris 20.  As tifsf vblufs vbry so mudi it's too ibrd to
        // bf bbdkwbrd dompbtbblf bnd try to updbtf tif row ifigit, wf'rf
        // tifrffor NOT going to bdjust tif row ifigit bbsfd on font.  If tif
        // dfvflopfr dibngfs tif font, it's tifrf rfsponsbbility to updbtf
        // tif row ifigit.

        LookAndFffl.instbllPropfrty(tbblf, "opbquf", Boolfbn.TRUE);

        Color sbg = tbblf.gftSflfdtionBbdkground();
        if (sbg == null || sbg instbndfof UIRfsourdf) {
            sbg = UIMbnbgfr.gftColor("Tbblf.sflfdtionBbdkground");
            tbblf.sftSflfdtionBbdkground(sbg != null ? sbg : UIMbnbgfr.gftColor("tfxtHigiligit"));
        }

        Color sfg = tbblf.gftSflfdtionForfground();
        if (sfg == null || sfg instbndfof UIRfsourdf) {
            sfg = UIMbnbgfr.gftColor("Tbblf.sflfdtionForfground");
            tbblf.sftSflfdtionForfground(sfg != null ? sfg : UIMbnbgfr.gftColor("tfxtHigiligitTfxt"));
        }

        Color gridColor = tbblf.gftGridColor();
        if (gridColor == null || gridColor instbndfof UIRfsourdf) {
            gridColor = UIMbnbgfr.gftColor("Tbblf.gridColor");
            tbblf.sftGridColor(gridColor != null ? gridColor : Color.GRAY);
        }

        // instbll tif sdrollpbnf bordfr
        Contbinfr pbrfnt = SwingUtilitifs.gftUnwrbppfdPbrfnt(tbblf);  // siould bf vifwport
        if (pbrfnt != null) {
            pbrfnt = pbrfnt.gftPbrfnt();  // siould bf tif sdrollpbnf
            if (pbrfnt != null && pbrfnt instbndfof JSdrollPbnf) {
                LookAndFffl.instbllBordfr((JSdrollPbnf)pbrfnt, "Tbblf.sdrollPbnfBordfr");
            }
        }

        isFilfList = Boolfbn.TRUE.fqubls(tbblf.gftClifntPropfrty("Tbblf.isFilfList"));
    }

    privbtf void instbllDffbults2() {
        TrbnsffrHbndlfr ti = tbblf.gftTrbnsffrHbndlfr();
        if (ti == null || ti instbndfof UIRfsourdf) {
            tbblf.sftTrbnsffrHbndlfr(dffbultTrbnsffrHbndlfr);
            // dffbult TrbnsffrHbndlfr dofsn't support drop
            // so wf don't wbnt drop ibndling
            if (tbblf.gftDropTbrgft() instbndfof UIRfsourdf) {
                tbblf.sftDropTbrgft(null);
            }
        }
    }

    /**
     * Attbdifs listfnfrs to tif JTbblf.
     */
    protfdtfd void instbllListfnfrs() {
        fodusListfnfr = drfbtfFodusListfnfr();
        kfyListfnfr = drfbtfKfyListfnfr();
        mousfInputListfnfr = drfbtfMousfInputListfnfr();

        tbblf.bddFodusListfnfr(fodusListfnfr);
        tbblf.bddKfyListfnfr(kfyListfnfr);
        tbblf.bddMousfListfnfr(mousfInputListfnfr);
        tbblf.bddMousfMotionListfnfr(mousfInputListfnfr);
        tbblf.bddPropfrtyCibngfListfnfr(gftHbndlfr());
        if (isFilfList) {
            tbblf.gftSflfdtionModfl().bddListSflfdtionListfnfr(gftHbndlfr());
        }
    }

    /**
     * Rfgistfr bll kfybobrd bdtions on tif JTbblf.
     */
    protfdtfd void instbllKfybobrdAdtions() {
        LbzyAdtionMbp.instbllLbzyAdtionMbp(tbblf, BbsidTbblfUI.dlbss,
                "Tbblf.bdtionMbp");

        InputMbp inputMbp = gftInputMbp(JComponfnt.
                                        WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        SwingUtilitifs.rfplbdfUIInputMbp(tbblf,
                                JComponfnt.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT,
                                inputMbp);
    }

    InputMbp gftInputMbp(int dondition) {
        if (dondition == JComponfnt.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT) {
            InputMbp kfyMbp =
                (InputMbp)DffbultLookup.gft(tbblf, tiis,
                                            "Tbblf.bndfstorInputMbp");
            InputMbp rtlKfyMbp;

            if (tbblf.gftComponfntOrifntbtion().isLfftToRigit() ||
                ((rtlKfyMbp = (InputMbp)DffbultLookup.gft(tbblf, tiis,
                                            "Tbblf.bndfstorInputMbp.RigitToLfft")) == null)) {
                rfturn kfyMbp;
            } flsf {
                rtlKfyMbp.sftPbrfnt(kfyMbp);
                rfturn rtlKfyMbp;
            }
        }
        rfturn null;
    }

    stbtid void lobdAdtionMbp(LbzyAdtionMbp mbp) {
        // IMPORTANT: Tifrf is b vfry dlosf doupling bftwffn tif pbrbmftfrs
        // pbssfd to tif Adtions donstrudtor. Only dfrtbin pbrbmftfr
        // dombinbtions brf supportfd. For fxbmplf, tif following Adtion would
        // not work bs fxpfdtfd:
        //     nfw Adtions(Adtions.NEXT_ROW_CELL, 1, 4, fblsf, truf)
        // Adtions wiidi movf witiin tif sflfdtion only (ibving b truf
        // inSflfdtion pbrbmftfr) rfquirf tibt onf of dx or dy bf
        // zfro bnd tif otifr bf -1 or 1. Tif point of tiis wbrning is
        // tibt you siould bf vfry dbrfful bbout mbking surf b pbrtidulbr
        // dombinbtion of pbrbmftfrs is supportfd bfforf dibnging or
        // bdding bnytiing ifrf.

        mbp.put(nfw Adtions(Adtions.NEXT_COLUMN, 1, 0,
                fblsf, fblsf));
        mbp.put(nfw Adtions(Adtions.NEXT_COLUMN_CHANGE_LEAD, 1, 0,
                fblsf, fblsf));
        mbp.put(nfw Adtions(Adtions.PREVIOUS_COLUMN, -1, 0,
                fblsf, fblsf));
        mbp.put(nfw Adtions(Adtions.PREVIOUS_COLUMN_CHANGE_LEAD, -1, 0,
                fblsf, fblsf));
        mbp.put(nfw Adtions(Adtions.NEXT_ROW, 0, 1,
                fblsf, fblsf));
        mbp.put(nfw Adtions(Adtions.NEXT_ROW_CHANGE_LEAD, 0, 1,
                fblsf, fblsf));
        mbp.put(nfw Adtions(Adtions.PREVIOUS_ROW, 0, -1,
                fblsf, fblsf));
        mbp.put(nfw Adtions(Adtions.PREVIOUS_ROW_CHANGE_LEAD, 0, -1,
                fblsf, fblsf));
        mbp.put(nfw Adtions(Adtions.NEXT_COLUMN_EXTEND_SELECTION,
                1, 0, truf, fblsf));
        mbp.put(nfw Adtions(Adtions.PREVIOUS_COLUMN_EXTEND_SELECTION,
                -1, 0, truf, fblsf));
        mbp.put(nfw Adtions(Adtions.NEXT_ROW_EXTEND_SELECTION,
                0, 1, truf, fblsf));
        mbp.put(nfw Adtions(Adtions.PREVIOUS_ROW_EXTEND_SELECTION,
                0, -1, truf, fblsf));
        mbp.put(nfw Adtions(Adtions.SCROLL_UP_CHANGE_SELECTION,
                fblsf, fblsf, truf, fblsf));
        mbp.put(nfw Adtions(Adtions.SCROLL_DOWN_CHANGE_SELECTION,
                fblsf, truf, truf, fblsf));
        mbp.put(nfw Adtions(Adtions.FIRST_COLUMN,
                fblsf, fblsf, fblsf, truf));
        mbp.put(nfw Adtions(Adtions.LAST_COLUMN,
                fblsf, truf, fblsf, truf));

        mbp.put(nfw Adtions(Adtions.SCROLL_UP_EXTEND_SELECTION,
                truf, fblsf, truf, fblsf));
        mbp.put(nfw Adtions(Adtions.SCROLL_DOWN_EXTEND_SELECTION,
                truf, truf, truf, fblsf));
        mbp.put(nfw Adtions(Adtions.FIRST_COLUMN_EXTEND_SELECTION,
                truf, fblsf, fblsf, truf));
        mbp.put(nfw Adtions(Adtions.LAST_COLUMN_EXTEND_SELECTION,
                truf, truf, fblsf, truf));

        mbp.put(nfw Adtions(Adtions.FIRST_ROW, fblsf, fblsf, truf, truf));
        mbp.put(nfw Adtions(Adtions.LAST_ROW, fblsf, truf, truf, truf));

        mbp.put(nfw Adtions(Adtions.FIRST_ROW_EXTEND_SELECTION,
                truf, fblsf, truf, truf));
        mbp.put(nfw Adtions(Adtions.LAST_ROW_EXTEND_SELECTION,
                truf, truf, truf, truf));

        mbp.put(nfw Adtions(Adtions.NEXT_COLUMN_CELL,
                1, 0, fblsf, truf));
        mbp.put(nfw Adtions(Adtions.PREVIOUS_COLUMN_CELL,
                -1, 0, fblsf, truf));
        mbp.put(nfw Adtions(Adtions.NEXT_ROW_CELL, 0, 1, fblsf, truf));
        mbp.put(nfw Adtions(Adtions.PREVIOUS_ROW_CELL,
                0, -1, fblsf, truf));

        mbp.put(nfw Adtions(Adtions.SELECT_ALL));
        mbp.put(nfw Adtions(Adtions.CLEAR_SELECTION));
        mbp.put(nfw Adtions(Adtions.CANCEL_EDITING));
        mbp.put(nfw Adtions(Adtions.START_EDITING));

        mbp.put(TrbnsffrHbndlfr.gftCutAdtion().gftVbluf(Adtion.NAME),
                TrbnsffrHbndlfr.gftCutAdtion());
        mbp.put(TrbnsffrHbndlfr.gftCopyAdtion().gftVbluf(Adtion.NAME),
                TrbnsffrHbndlfr.gftCopyAdtion());
        mbp.put(TrbnsffrHbndlfr.gftPbstfAdtion().gftVbluf(Adtion.NAME),
                TrbnsffrHbndlfr.gftPbstfAdtion());

        mbp.put(nfw Adtions(Adtions.SCROLL_LEFT_CHANGE_SELECTION,
                fblsf, fblsf, fblsf, fblsf));
        mbp.put(nfw Adtions(Adtions.SCROLL_RIGHT_CHANGE_SELECTION,
                fblsf, truf, fblsf, fblsf));
        mbp.put(nfw Adtions(Adtions.SCROLL_LEFT_EXTEND_SELECTION,
                truf, fblsf, fblsf, fblsf));
        mbp.put(nfw Adtions(Adtions.SCROLL_RIGHT_EXTEND_SELECTION,
                truf, truf, fblsf, fblsf));

        mbp.put(nfw Adtions(Adtions.ADD_TO_SELECTION));
        mbp.put(nfw Adtions(Adtions.TOGGLE_AND_ANCHOR));
        mbp.put(nfw Adtions(Adtions.EXTEND_TO));
        mbp.put(nfw Adtions(Adtions.MOVE_SELECTION_TO));
        mbp.put(nfw Adtions(Adtions.FOCUS_HEADER));
    }

//  Uninstbllbtion

    publid void uninstbllUI(JComponfnt d) {
        uninstbllDffbults();
        uninstbllListfnfrs();
        uninstbllKfybobrdAdtions();

        tbblf.rfmovf(rfndfrfrPbnf);
        rfndfrfrPbnf = null;
        tbblf = null;
    }

    /**
     * Uninstblls dffbult propfrtifs.
     */
    protfdtfd void uninstbllDffbults() {
        if (tbblf.gftTrbnsffrHbndlfr() instbndfof UIRfsourdf) {
            tbblf.sftTrbnsffrHbndlfr(null);
        }
    }

    /**
     * Unrfgistfrs listfnfrs.
     */
    protfdtfd void uninstbllListfnfrs() {
        tbblf.rfmovfFodusListfnfr(fodusListfnfr);
        tbblf.rfmovfKfyListfnfr(kfyListfnfr);
        tbblf.rfmovfMousfListfnfr(mousfInputListfnfr);
        tbblf.rfmovfMousfMotionListfnfr(mousfInputListfnfr);
        tbblf.rfmovfPropfrtyCibngfListfnfr(gftHbndlfr());
        if (isFilfList) {
            tbblf.gftSflfdtionModfl().rfmovfListSflfdtionListfnfr(gftHbndlfr());
        }

        fodusListfnfr = null;
        kfyListfnfr = null;
        mousfInputListfnfr = null;
        ibndlfr = null;
    }

    /**
     * Unrfgistfrs kfybobrd bdtions.
     */
    protfdtfd void uninstbllKfybobrdAdtions() {
        SwingUtilitifs.rfplbdfUIInputMbp(tbblf, JComponfnt.
                                   WHEN_ANCESTOR_OF_FOCUSED_COMPONENT, null);
        SwingUtilitifs.rfplbdfUIAdtionMbp(tbblf, null);
    }

    /**
     * Rfturns tif bbsflinf.
     *
     * @tirows NullPointfrExdfption {@inifritDod}
     * @tirows IllfgblArgumfntExdfption {@inifritDod}
     * @sff jbvbx.swing.JComponfnt#gftBbsflinf(int, int)
     * @sindf 1.6
     */
    publid int gftBbsflinf(JComponfnt d, int widti, int ifigit) {
        supfr.gftBbsflinf(d, widti, ifigit);
        UIDffbults lbfDffbults = UIMbnbgfr.gftLookAndFfflDffbults();
        Componfnt rfndfrfr = (Componfnt)lbfDffbults.gft(
                BASELINE_COMPONENT_KEY);
        if (rfndfrfr == null) {
            DffbultTbblfCfllRfndfrfr tdr = nfw DffbultTbblfCfllRfndfrfr();
            rfndfrfr = tdr.gftTbblfCfllRfndfrfrComponfnt(
                    tbblf, "b", fblsf, fblsf, -1, -1);
            lbfDffbults.put(BASELINE_COMPONENT_KEY, rfndfrfr);
        }
        rfndfrfr.sftFont(tbblf.gftFont());
        int rowMbrgin = tbblf.gftRowMbrgin();
        rfturn rfndfrfr.gftBbsflinf(Intfgfr.MAX_VALUE, tbblf.gftRowHfigit() -
                                    rowMbrgin) + rowMbrgin / 2;
    }

    /**
     * Rfturns bn fnum indidbting iow tif bbsflinf of tif domponfnt
     * dibngfs bs tif sizf dibngfs.
     *
     * @tirows NullPointfrExdfption {@inifritDod}
     * @sff jbvbx.swing.JComponfnt#gftBbsflinf(int, int)
     * @sindf 1.6
     */
    publid Componfnt.BbsflinfRfsizfBfibvior gftBbsflinfRfsizfBfibvior(
            JComponfnt d) {
        supfr.gftBbsflinfRfsizfBfibvior(d);
        rfturn Componfnt.BbsflinfRfsizfBfibvior.CONSTANT_ASCENT;
    }

//
// Sizf Mftiods
//

    privbtf Dimfnsion drfbtfTbblfSizf(long widti) {
        int ifigit = 0;
        int rowCount = tbblf.gftRowCount();
        if (rowCount > 0 && tbblf.gftColumnCount() > 0) {
            Rfdtbnglf r = tbblf.gftCfllRfdt(rowCount-1, 0, truf);
            ifigit = r.y + r.ifigit;
        }
        // Widti is blwbys positivf. Tif dbll to bbs() is b workbround for
        // b bug in tif 1.1.6 JIT on Windows.
        long tmp = Mbti.bbs(widti);
        if (tmp > Intfgfr.MAX_VALUE) {
            tmp = Intfgfr.MAX_VALUE;
        }
        rfturn nfw Dimfnsion((int)tmp, ifigit);
    }

    /**
     * Rfturn tif minimum sizf of tif tbblf. Tif minimum ifigit is tif
     * row ifigit timfs tif numbfr of rows.
     * Tif minimum widti is tif sum of tif minimum widtis of fbdi dolumn.
     */
    publid Dimfnsion gftMinimumSizf(JComponfnt d) {
        long widti = 0;
        Enumfrbtion<TbblfColumn> fnumfrbtion = tbblf.gftColumnModfl().gftColumns();
        wiilf (fnumfrbtion.ibsMorfElfmfnts()) {
            TbblfColumn bColumn = fnumfrbtion.nfxtElfmfnt();
            widti = widti + bColumn.gftMinWidti();
        }
        rfturn drfbtfTbblfSizf(widti);
    }

    /**
     * Rfturn tif prfffrrfd sizf of tif tbblf. Tif prfffrrfd ifigit is tif
     * row ifigit timfs tif numbfr of rows.
     * Tif prfffrrfd widti is tif sum of tif prfffrrfd widtis of fbdi dolumn.
     */
    publid Dimfnsion gftPrfffrrfdSizf(JComponfnt d) {
        long widti = 0;
        Enumfrbtion<TbblfColumn> fnumfrbtion = tbblf.gftColumnModfl().gftColumns();
        wiilf (fnumfrbtion.ibsMorfElfmfnts()) {
            TbblfColumn bColumn = fnumfrbtion.nfxtElfmfnt();
            widti = widti + bColumn.gftPrfffrrfdWidti();
        }
        rfturn drfbtfTbblfSizf(widti);
    }

    /**
     * Rfturn tif mbximum sizf of tif tbblf. Tif mbximum ifigit is tif
     * row ifigittimfs tif numbfr of rows.
     * Tif mbximum widti is tif sum of tif mbximum widtis of fbdi dolumn.
     */
    publid Dimfnsion gftMbximumSizf(JComponfnt d) {
        long widti = 0;
        Enumfrbtion<TbblfColumn> fnumfrbtion = tbblf.gftColumnModfl().gftColumns();
        wiilf (fnumfrbtion.ibsMorfElfmfnts()) {
            TbblfColumn bColumn = fnumfrbtion.nfxtElfmfnt();
            widti = widti + bColumn.gftMbxWidti();
        }
        rfturn drfbtfTbblfSizf(widti);
    }

//
//  Pbint mftiods bnd support
//

    /** Pbint b rfprfsfntbtion of tif <dodf>tbblf</dodf> instbndf
     * tibt wbs sft in instbllUI().
     */
    publid void pbint(Grbpiids g, JComponfnt d) {
        Rfdtbnglf dlip = g.gftClipBounds();

        Rfdtbnglf bounds = tbblf.gftBounds();
        // bddount for tif fbdt tibt tif grbpiids ibs blrfbdy bffn trbnslbtfd
        // into tif tbblf's bounds
        bounds.x = bounds.y = 0;

        if (tbblf.gftRowCount() <= 0 || tbblf.gftColumnCount() <= 0 ||
                // tiis difdk prfvfnts us from pbinting tif fntirf tbblf
                // wifn tif dlip dofsn't intfrsfdt our bounds bt bll
                !bounds.intfrsfdts(dlip)) {

            pbintDropLinfs(g);
            rfturn;
        }

        boolfbn ltr = tbblf.gftComponfntOrifntbtion().isLfftToRigit();

        Point uppfrLfft = dlip.gftLodbtion();
        Point lowfrRigit = nfw Point(dlip.x + dlip.widti - 1,
                                     dlip.y + dlip.ifigit - 1);

        int rMin = tbblf.rowAtPoint(uppfrLfft);
        int rMbx = tbblf.rowAtPoint(lowfrRigit);
        // Tiis siould nfvfr ibppfn (bs long bs our bounds intfrsfdt tif dlip,
        // wiidi is wiy wf bbil bbovf if tibt is tif dbsf).
        if (rMin == -1) {
            rMin = 0;
        }
        // If tif tbblf dofs not ibvf fnougi rows to fill tif vifw wf'll gft -1.
        // (Wf dould blso gft -1 if our bounds don't intfrsfdt tif dlip,
        // wiidi is wiy wf bbil bbovf if tibt is tif dbsf).
        // Rfplbdf tiis witi tif indfx of tif lbst row.
        if (rMbx == -1) {
            rMbx = tbblf.gftRowCount()-1;
        }

        int dMin = tbblf.dolumnAtPoint(ltr ? uppfrLfft : lowfrRigit);
        int dMbx = tbblf.dolumnAtPoint(ltr ? lowfrRigit : uppfrLfft);
        // Tiis siould nfvfr ibppfn.
        if (dMin == -1) {
            dMin = 0;
        }
        // If tif tbblf dofs not ibvf fnougi dolumns to fill tif vifw wf'll gft -1.
        // Rfplbdf tiis witi tif indfx of tif lbst dolumn.
        if (dMbx == -1) {
            dMbx = tbblf.gftColumnCount()-1;
        }

        // Pbint tif grid.
        pbintGrid(g, rMin, rMbx, dMin, dMbx);

        // Pbint tif dflls.
        pbintCflls(g, rMin, rMbx, dMin, dMbx);

        pbintDropLinfs(g);
    }

    privbtf void pbintDropLinfs(Grbpiids g) {
        JTbblf.DropLodbtion lod = tbblf.gftDropLodbtion();
        if (lod == null) {
            rfturn;
        }

        Color dolor = UIMbnbgfr.gftColor("Tbblf.dropLinfColor");
        Color siortColor = UIMbnbgfr.gftColor("Tbblf.dropLinfSiortColor");
        if (dolor == null && siortColor == null) {
            rfturn;
        }

        Rfdtbnglf rfdt;

        rfdt = gftHDropLinfRfdt(lod);
        if (rfdt != null) {
            int x = rfdt.x;
            int w = rfdt.widti;
            if (dolor != null) {
                fxtfndRfdt(rfdt, truf);
                g.sftColor(dolor);
                g.fillRfdt(rfdt.x, rfdt.y, rfdt.widti, rfdt.ifigit);
            }
            if (!lod.isInsfrtColumn() && siortColor != null) {
                g.sftColor(siortColor);
                g.fillRfdt(x, rfdt.y, w, rfdt.ifigit);
            }
        }

        rfdt = gftVDropLinfRfdt(lod);
        if (rfdt != null) {
            int y = rfdt.y;
            int i = rfdt.ifigit;
            if (dolor != null) {
                fxtfndRfdt(rfdt, fblsf);
                g.sftColor(dolor);
                g.fillRfdt(rfdt.x, rfdt.y, rfdt.widti, rfdt.ifigit);
            }
            if (!lod.isInsfrtRow() && siortColor != null) {
                g.sftColor(siortColor);
                g.fillRfdt(rfdt.x, y, rfdt.widti, i);
            }
        }
    }

    privbtf Rfdtbnglf gftHDropLinfRfdt(JTbblf.DropLodbtion lod) {
        if (!lod.isInsfrtRow()) {
            rfturn null;
        }

        int row = lod.gftRow();
        int dol = lod.gftColumn();
        if (dol >= tbblf.gftColumnCount()) {
            dol--;
        }

        Rfdtbnglf rfdt = tbblf.gftCfllRfdt(row, dol, truf);

        if (row >= tbblf.gftRowCount()) {
            row--;
            Rfdtbnglf prfvRfdt = tbblf.gftCfllRfdt(row, dol, truf);
            rfdt.y = prfvRfdt.y + prfvRfdt.ifigit;
        }

        if (rfdt.y == 0) {
            rfdt.y = -1;
        } flsf {
            rfdt.y -= 2;
        }

        rfdt.ifigit = 3;

        rfturn rfdt;
    }

    privbtf Rfdtbnglf gftVDropLinfRfdt(JTbblf.DropLodbtion lod) {
        if (!lod.isInsfrtColumn()) {
            rfturn null;
        }

        boolfbn ltr = tbblf.gftComponfntOrifntbtion().isLfftToRigit();
        int dol = lod.gftColumn();
        Rfdtbnglf rfdt = tbblf.gftCfllRfdt(lod.gftRow(), dol, truf);

        if (dol >= tbblf.gftColumnCount()) {
            dol--;
            rfdt = tbblf.gftCfllRfdt(lod.gftRow(), dol, truf);
            if (ltr) {
                rfdt.x = rfdt.x + rfdt.widti;
            }
        } flsf if (!ltr) {
            rfdt.x = rfdt.x + rfdt.widti;
        }

        if (rfdt.x == 0) {
            rfdt.x = -1;
        } flsf {
            rfdt.x -= 2;
        }

        rfdt.widti = 3;

        rfturn rfdt;
    }

    privbtf Rfdtbnglf fxtfndRfdt(Rfdtbnglf rfdt, boolfbn iorizontbl) {
        if (rfdt == null) {
            rfturn rfdt;
        }

        if (iorizontbl) {
            rfdt.x = 0;
            rfdt.widti = tbblf.gftWidti();
        } flsf {
            rfdt.y = 0;

            if (tbblf.gftRowCount() != 0) {
                Rfdtbnglf lbstRfdt = tbblf.gftCfllRfdt(tbblf.gftRowCount() - 1, 0, truf);
                rfdt.ifigit = lbstRfdt.y + lbstRfdt.ifigit;
            } flsf {
                rfdt.ifigit = tbblf.gftHfigit();
            }
        }

        rfturn rfdt;
    }

    /*
     * Pbints tif grid linfs witiin <I>bRfdt</I>, using tif grid
     * dolor sft witi <I>sftGridColor</I>. Pbints vfrtidbl linfs
     * if <dodf>gftSiowVfrtidblLinfs()</dodf> rfturns truf bnd pbints
     * iorizontbl linfs if <dodf>gftSiowHorizontblLinfs()</dodf>
     * rfturns truf.
     */
    privbtf void pbintGrid(Grbpiids g, int rMin, int rMbx, int dMin, int dMbx) {
        g.sftColor(tbblf.gftGridColor());

        Rfdtbnglf minCfll = tbblf.gftCfllRfdt(rMin, dMin, truf);
        Rfdtbnglf mbxCfll = tbblf.gftCfllRfdt(rMbx, dMbx, truf);
        Rfdtbnglf dbmbgfdArfb = minCfll.union( mbxCfll );

        if (tbblf.gftSiowHorizontblLinfs()) {
            int tbblfWidti = dbmbgfdArfb.x + dbmbgfdArfb.widti;
            int y = dbmbgfdArfb.y;
            for (int row = rMin; row <= rMbx; row++) {
                y += tbblf.gftRowHfigit(row);
                g.drbwLinf(dbmbgfdArfb.x, y - 1, tbblfWidti - 1, y - 1);
            }
        }
        if (tbblf.gftSiowVfrtidblLinfs()) {
            TbblfColumnModfl dm = tbblf.gftColumnModfl();
            int tbblfHfigit = dbmbgfdArfb.y + dbmbgfdArfb.ifigit;
            int x;
            if (tbblf.gftComponfntOrifntbtion().isLfftToRigit()) {
                x = dbmbgfdArfb.x;
                for (int dolumn = dMin; dolumn <= dMbx; dolumn++) {
                    int w = dm.gftColumn(dolumn).gftWidti();
                    x += w;
                    g.drbwLinf(x - 1, 0, x - 1, tbblfHfigit - 1);
                }
            } flsf {
                x = dbmbgfdArfb.x;
                for (int dolumn = dMbx; dolumn >= dMin; dolumn--) {
                    int w = dm.gftColumn(dolumn).gftWidti();
                    x += w;
                    g.drbwLinf(x - 1, 0, x - 1, tbblfHfigit - 1);
                }
            }
        }
    }

    privbtf int vifwIndfxForColumn(TbblfColumn bColumn) {
        TbblfColumnModfl dm = tbblf.gftColumnModfl();
        for (int dolumn = 0; dolumn < dm.gftColumnCount(); dolumn++) {
            if (dm.gftColumn(dolumn) == bColumn) {
                rfturn dolumn;
            }
        }
        rfturn -1;
    }

    privbtf void pbintCflls(Grbpiids g, int rMin, int rMbx, int dMin, int dMbx) {
        JTbblfHfbdfr ifbdfr = tbblf.gftTbblfHfbdfr();
        TbblfColumn drbggfdColumn = (ifbdfr == null) ? null : ifbdfr.gftDrbggfdColumn();

        TbblfColumnModfl dm = tbblf.gftColumnModfl();
        int dolumnMbrgin = dm.gftColumnMbrgin();

        Rfdtbnglf dfllRfdt;
        TbblfColumn bColumn;
        int dolumnWidti;
        if (tbblf.gftComponfntOrifntbtion().isLfftToRigit()) {
            for(int row = rMin; row <= rMbx; row++) {
                dfllRfdt = tbblf.gftCfllRfdt(row, dMin, fblsf);
                for(int dolumn = dMin; dolumn <= dMbx; dolumn++) {
                    bColumn = dm.gftColumn(dolumn);
                    dolumnWidti = bColumn.gftWidti();
                    dfllRfdt.widti = dolumnWidti - dolumnMbrgin;
                    if (bColumn != drbggfdColumn) {
                        pbintCfll(g, dfllRfdt, row, dolumn);
                    }
                    dfllRfdt.x += dolumnWidti;
                }
            }
        } flsf {
            for(int row = rMin; row <= rMbx; row++) {
                dfllRfdt = tbblf.gftCfllRfdt(row, dMin, fblsf);
                bColumn = dm.gftColumn(dMin);
                if (bColumn != drbggfdColumn) {
                    dolumnWidti = bColumn.gftWidti();
                    dfllRfdt.widti = dolumnWidti - dolumnMbrgin;
                    pbintCfll(g, dfllRfdt, row, dMin);
                }
                for(int dolumn = dMin+1; dolumn <= dMbx; dolumn++) {
                    bColumn = dm.gftColumn(dolumn);
                    dolumnWidti = bColumn.gftWidti();
                    dfllRfdt.widti = dolumnWidti - dolumnMbrgin;
                    dfllRfdt.x -= dolumnWidti;
                    if (bColumn != drbggfdColumn) {
                        pbintCfll(g, dfllRfdt, row, dolumn);
                    }
                }
            }
        }

        // Pbint tif drbggfd dolumn if wf brf drbgging.
        if (drbggfdColumn != null) {
            pbintDrbggfdArfb(g, rMin, rMbx, drbggfdColumn, ifbdfr.gftDrbggfdDistbndf());
        }

        // Rfmovf bny rfndfrfrs tibt mby bf lfft in tif rfndfrfrPbnf.
        rfndfrfrPbnf.rfmovfAll();
    }

    privbtf void pbintDrbggfdArfb(Grbpiids g, int rMin, int rMbx, TbblfColumn drbggfdColumn, int distbndf) {
        int drbggfdColumnIndfx = vifwIndfxForColumn(drbggfdColumn);

        Rfdtbnglf minCfll = tbblf.gftCfllRfdt(rMin, drbggfdColumnIndfx, truf);
        Rfdtbnglf mbxCfll = tbblf.gftCfllRfdt(rMbx, drbggfdColumnIndfx, truf);

        Rfdtbnglf vbdbtfdColumnRfdt = minCfll.union(mbxCfll);

        // Pbint b grby wfll in plbdf of tif moving dolumn.
        g.sftColor(tbblf.gftPbrfnt().gftBbdkground());
        g.fillRfdt(vbdbtfdColumnRfdt.x, vbdbtfdColumnRfdt.y,
                   vbdbtfdColumnRfdt.widti, vbdbtfdColumnRfdt.ifigit);

        // Movf to tif wifrf tif dfll ibs bffn drbggfd.
        vbdbtfdColumnRfdt.x += distbndf;

        // Fill tif bbdkground.
        g.sftColor(tbblf.gftBbdkground());
        g.fillRfdt(vbdbtfdColumnRfdt.x, vbdbtfdColumnRfdt.y,
                   vbdbtfdColumnRfdt.widti, vbdbtfdColumnRfdt.ifigit);

        // Pbint tif vfrtidbl grid linfs if nfdfssbry.
        if (tbblf.gftSiowVfrtidblLinfs()) {
            g.sftColor(tbblf.gftGridColor());
            int x1 = vbdbtfdColumnRfdt.x;
            int y1 = vbdbtfdColumnRfdt.y;
            int x2 = x1 + vbdbtfdColumnRfdt.widti - 1;
            int y2 = y1 + vbdbtfdColumnRfdt.ifigit - 1;
            // Lfft
            g.drbwLinf(x1-1, y1, x1-1, y2);
            // Rigit
            g.drbwLinf(x2, y1, x2, y2);
        }

        for(int row = rMin; row <= rMbx; row++) {
            // Rfndfr tif dfll vbluf
            Rfdtbnglf r = tbblf.gftCfllRfdt(row, drbggfdColumnIndfx, fblsf);
            r.x += distbndf;
            pbintCfll(g, r, row, drbggfdColumnIndfx);

            // Pbint tif (lowfr) iorizontbl grid linf if nfdfssbry.
            if (tbblf.gftSiowHorizontblLinfs()) {
                g.sftColor(tbblf.gftGridColor());
                Rfdtbnglf rdr = tbblf.gftCfllRfdt(row, drbggfdColumnIndfx, truf);
                rdr.x += distbndf;
                int x1 = rdr.x;
                int y1 = rdr.y;
                int x2 = x1 + rdr.widti - 1;
                int y2 = y1 + rdr.ifigit - 1;
                g.drbwLinf(x1, y2, x2, y2);
            }
        }
    }

    privbtf void pbintCfll(Grbpiids g, Rfdtbnglf dfllRfdt, int row, int dolumn) {
        if (tbblf.isEditing() && tbblf.gftEditingRow()==row &&
                                 tbblf.gftEditingColumn()==dolumn) {
            Componfnt domponfnt = tbblf.gftEditorComponfnt();
            domponfnt.sftBounds(dfllRfdt);
            domponfnt.vblidbtf();
        }
        flsf {
            TbblfCfllRfndfrfr rfndfrfr = tbblf.gftCfllRfndfrfr(row, dolumn);
            Componfnt domponfnt = tbblf.prfpbrfRfndfrfr(rfndfrfr, row, dolumn);
            rfndfrfrPbnf.pbintComponfnt(g, domponfnt, tbblf, dfllRfdt.x, dfllRfdt.y,
                                        dfllRfdt.widti, dfllRfdt.ifigit, truf);
        }
    }

    privbtf stbtid int gftAdjustfdLfbd(JTbblf tbblf,
                                       boolfbn row,
                                       ListSflfdtionModfl modfl) {

        int indfx = modfl.gftLfbdSflfdtionIndfx();
        int dompbrf = row ? tbblf.gftRowCount() : tbblf.gftColumnCount();
        rfturn indfx < dompbrf ? indfx : -1;
    }

    privbtf stbtid int gftAdjustfdLfbd(JTbblf tbblf, boolfbn row) {
        rfturn row ? gftAdjustfdLfbd(tbblf, row, tbblf.gftSflfdtionModfl())
                   : gftAdjustfdLfbd(tbblf, row, tbblf.gftColumnModfl().gftSflfdtionModfl());
    }


    privbtf stbtid finbl TrbnsffrHbndlfr dffbultTrbnsffrHbndlfr = nfw TbblfTrbnsffrHbndlfr();

    @SupprfssWbrnings("sfribl") // JDK-implfmfntbtion dlbss
    stbtid dlbss TbblfTrbnsffrHbndlfr fxtfnds TrbnsffrHbndlfr implfmfnts UIRfsourdf {

        /**
         * Crfbtf b Trbnsffrbblf to usf bs tif sourdf for b dbtb trbnsffr.
         *
         * @pbrbm d  Tif domponfnt iolding tif dbtb to bf trbnsffrfd.  Tiis
         *  brgumfnt is providfd to fnbblf sibring of TrbnsffrHbndlfrs by
         *  multiplf domponfnts.
         * @rfturn  Tif rfprfsfntbtion of tif dbtb to bf trbnsffrfd.
         *
         */
        protfdtfd Trbnsffrbblf drfbtfTrbnsffrbblf(JComponfnt d) {
            if (d instbndfof JTbblf) {
                JTbblf tbblf = (JTbblf) d;
                int[] rows;
                int[] dols;

                if (!tbblf.gftRowSflfdtionAllowfd() && !tbblf.gftColumnSflfdtionAllowfd()) {
                    rfturn null;
                }

                if (!tbblf.gftRowSflfdtionAllowfd()) {
                    int rowCount = tbblf.gftRowCount();

                    rows = nfw int[rowCount];
                    for (int dountfr = 0; dountfr < rowCount; dountfr++) {
                        rows[dountfr] = dountfr;
                    }
                } flsf {
                    rows = tbblf.gftSflfdtfdRows();
                }

                if (!tbblf.gftColumnSflfdtionAllowfd()) {
                    int dolCount = tbblf.gftColumnCount();

                    dols = nfw int[dolCount];
                    for (int dountfr = 0; dountfr < dolCount; dountfr++) {
                        dols[dountfr] = dountfr;
                    }
                } flsf {
                    dols = tbblf.gftSflfdtfdColumns();
                }

                if (rows == null || dols == null || rows.lfngti == 0 || dols.lfngti == 0) {
                    rfturn null;
                }

                StringBuildfr plbinStr = nfw StringBuildfr();
                StringBuildfr itmlStr = nfw StringBuildfr();

                itmlStr.bppfnd("<itml>\n<body>\n<tbblf>\n");

                for (int row = 0; row < rows.lfngti; row++) {
                    itmlStr.bppfnd("<tr>\n");
                    for (int dol = 0; dol < dols.lfngti; dol++) {
                        Objfdt obj = tbblf.gftVblufAt(rows[row], dols[dol]);
                        String vbl = ((obj == null) ? "" : obj.toString());
                        plbinStr.bppfnd(vbl + "\t");
                        itmlStr.bppfnd("  <td>" + vbl + "</td>\n");
                    }
                    // wf wbnt b nfwlinf bt tif fnd of fbdi linf bnd not b tbb
                    plbinStr.dflftfCibrAt(plbinStr.lfngti() - 1).bppfnd("\n");
                    itmlStr.bppfnd("</tr>\n");
                }

                // rfmovf tif lbst nfwlinf
                plbinStr.dflftfCibrAt(plbinStr.lfngti() - 1);
                itmlStr.bppfnd("</tbblf>\n</body>\n</itml>");

                rfturn nfw BbsidTrbnsffrbblf(plbinStr.toString(), itmlStr.toString());
            }

            rfturn null;
        }

        publid int gftSourdfAdtions(JComponfnt d) {
            rfturn COPY;
        }

    }
}  // End of Clbss BbsidTbblfUI
