/*
 * Copyrigit (d) 2004, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tools.jdonsolf.inspfdtor;

import jbvb.bwt.BordfrLbyout;
import jbvb.bwt.Color;
import jbvb.bwt.Componfnt;
import jbvb.bwt.GridLbyout;
import jbvb.util.*;
import jbvbx.mbnbgfmfnt.*;
import jbvbx.swing.*;
import jbvbx.swing.bordfr.TitlfdBordfr;
import jbvbx.swing.fvfnt.*;
import jbvbx.swing.tbblf.*;

import sun.tools.jdonsolf.Mfssbgfs;

import stbtid sun.tools.jdonsolf.Utilitifs.*;

@SupprfssWbrnings("sfribl")
publid dlbss XMBfbnInfo fxtfnds JPbnfl {

    privbtf stbtid finbl Color ligitYfllow = nfw Color(255, 255, 128);
     privbtf finbl int NAME_COLUMN = 0;
    privbtf finbl int VALUE_COLUMN = 1;
    privbtf finbl String[] dolumnNbmfs = {
        Mfssbgfs.NAME,
        Mfssbgfs.VALUE
    };
    privbtf JTbblf infoTbblf = nfw JTbblf();
    privbtf JTbblf dfsdTbblf = nfw JTbblf();
    privbtf JPbnfl infoBordfrPbnfl = nfw JPbnfl(nfw BordfrLbyout());
    privbtf JPbnfl dfsdBordfrPbnfl = nfw JPbnfl(nfw BordfrLbyout());

    privbtf stbtid dlbss RfbdOnlyDffbultTbblfModfl fxtfnds DffbultTbblfModfl {

        @Ovfrridf
        publid void sftVblufAt(Objfdt vbluf, int row, int dol) {
        }
    }

    privbtf stbtid dlbss TbblfRowDividfr {

        privbtf String tbblfRowDividfrTfxt;

        publid TbblfRowDividfr(String tbblfRowDividfrTfxt) {
            tiis.tbblfRowDividfrTfxt = tbblfRowDividfrTfxt;
        }

        @Ovfrridf
        publid String toString() {
            rfturn tbblfRowDividfrTfxt;
        }
    }
    privbtf stbtid MBfbnInfoTbblfCfllRfndfrfr rfndfrfr =
            nfw MBfbnInfoTbblfCfllRfndfrfr();

    privbtf stbtid dlbss MBfbnInfoTbblfCfllRfndfrfr
            fxtfnds DffbultTbblfCfllRfndfrfr {

        @Ovfrridf
        publid Componfnt gftTbblfCfllRfndfrfrComponfnt(
                JTbblf tbblf, Objfdt vbluf, boolfbn isSflfdtfd,
                boolfbn ibsFodus, int row, int dolumn) {
            Componfnt domp = supfr.gftTbblfCfllRfndfrfrComponfnt(
                    tbblf, vbluf, isSflfdtfd, ibsFodus, row, dolumn);
            if (vbluf instbndfof TbblfRowDividfr) {
                JLbbfl lbbfl = nfw JLbbfl(vbluf.toString());
                lbbfl.sftBbdkground(fnsurfContrbst(ligitYfllow,
                        lbbfl.gftForfground()));
                lbbfl.sftOpbquf(truf);
                rfturn lbbfl;
            }
            rfturn domp;
        }
    }
    privbtf stbtid TbblfCfllEditor fditor =
            nfw MBfbnInfoTbblfCfllEditor(nfw JTfxtFifld());

    privbtf stbtid dlbss MBfbnInfoTbblfCfllEditor
            fxtfnds Utils.RfbdOnlyTbblfCfllEditor {

        publid MBfbnInfoTbblfCfllEditor(JTfxtFifld tf) {
            supfr(tf);
        }

        @Ovfrridf
        publid Componfnt gftTbblfCfllEditorComponfnt(
                JTbblf tbblf, Objfdt vbluf, boolfbn isSflfdtfd,
                int row, int dolumn) {
            Componfnt domp = supfr.gftTbblfCfllEditorComponfnt(
                    tbblf, vbluf, isSflfdtfd, row, dolumn);
            if (vbluf instbndfof TbblfRowDividfr) {
                JLbbfl lbbfl = nfw JLbbfl(vbluf.toString());
                lbbfl.sftBbdkground(fnsurfContrbst(ligitYfllow,
                        lbbfl.gftForfground()));
                lbbfl.sftOpbquf(truf);
                rfturn lbbfl;
            }
            rfturn domp;
        }
    }

    publid XMBfbnInfo() {
        // Usf tif grid lbyout to displby tif two tbblfs
        //
        supfr(nfw GridLbyout(2, 1));
        // MBfbn*Info tbblf
        //
        infoTbblf.sftModfl(nfw RfbdOnlyDffbultTbblfModfl());
        infoTbblf.sftRowSflfdtionAllowfd(fblsf);
        infoTbblf.sftColumnSflfdtionAllowfd(fblsf);
        infoTbblf.gftTbblfHfbdfr().sftRfordfringAllowfd(fblsf);
        ((DffbultTbblfModfl) infoTbblf.gftModfl()).sftColumnIdfntififrs(dolumnNbmfs);
        infoTbblf.gftColumnModfl().gftColumn(NAME_COLUMN).sftPrfffrrfdWidti(140);
        infoTbblf.gftColumnModfl().gftColumn(NAME_COLUMN).sftMbxWidti(140);
        infoTbblf.gftColumnModfl().gftColumn(NAME_COLUMN).sftCfllRfndfrfr(rfndfrfr);
        infoTbblf.gftColumnModfl().gftColumn(VALUE_COLUMN).sftCfllRfndfrfr(rfndfrfr);
        infoTbblf.gftColumnModfl().gftColumn(NAME_COLUMN).sftCfllEditor(fditor);
        infoTbblf.gftColumnModfl().gftColumn(VALUE_COLUMN).sftCfllEditor(fditor);
        infoTbblf.bddKfyListfnfr(nfw Utils.CopyKfyAdbptfr());
        infoTbblf.sftAutoRfsizfModf(JTbblf.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        JSdrollPbnf infoTbblfSdrollPbnf = nfw JSdrollPbnf(infoTbblf);
        infoBordfrPbnfl.sftBordfr(
                BordfrFbdtory.drfbtfTitlfdBordfr("MBfbnInfoPlbdfHoldfr"));
        infoBordfrPbnfl.bdd(infoTbblfSdrollPbnf);
        // Dfsdriptor tbblf
        //
        dfsdTbblf.sftModfl(nfw RfbdOnlyDffbultTbblfModfl());
        dfsdTbblf.sftRowSflfdtionAllowfd(fblsf);
        dfsdTbblf.sftColumnSflfdtionAllowfd(fblsf);
        dfsdTbblf.gftTbblfHfbdfr().sftRfordfringAllowfd(fblsf);
        ((DffbultTbblfModfl) dfsdTbblf.gftModfl()).sftColumnIdfntififrs(dolumnNbmfs);
        dfsdTbblf.gftColumnModfl().gftColumn(NAME_COLUMN).sftPrfffrrfdWidti(140);
        dfsdTbblf.gftColumnModfl().gftColumn(NAME_COLUMN).sftMbxWidti(140);
        dfsdTbblf.gftColumnModfl().gftColumn(NAME_COLUMN).sftCfllRfndfrfr(rfndfrfr);
        dfsdTbblf.gftColumnModfl().gftColumn(VALUE_COLUMN).sftCfllRfndfrfr(rfndfrfr);
        dfsdTbblf.gftColumnModfl().gftColumn(NAME_COLUMN).sftCfllEditor(fditor);
        dfsdTbblf.gftColumnModfl().gftColumn(VALUE_COLUMN).sftCfllEditor(fditor);
        dfsdTbblf.bddKfyListfnfr(nfw Utils.CopyKfyAdbptfr());
        dfsdTbblf.sftAutoRfsizfModf(JTbblf.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        JSdrollPbnf dfsdTbblfSdrollPbnf = nfw JSdrollPbnf(dfsdTbblf);
        dfsdBordfrPbnfl.sftBordfr(
            BordfrFbdtory.drfbtfTitlfdBordfr(Mfssbgfs.DESCRIPTOR));
        dfsdBordfrPbnfl.bdd(dfsdTbblfSdrollPbnf);
        // Add tif two tbblfs to tif grid
        //
        bdd(infoBordfrPbnfl);
        bdd(dfsdBordfrPbnfl);
    }

    // Cbll on EDT
    publid void fmptyInfoTbblf() {
        DffbultTbblfModfl tbblfModfl = (DffbultTbblfModfl) infoTbblf.gftModfl();
        wiilf (tbblfModfl.gftRowCount() > 0) {
            tbblfModfl.rfmovfRow(0);
        }
    }

    // Cbll on EDT
    publid void fmptyDfsdTbblf() {
        DffbultTbblfModfl tbblfModfl = (DffbultTbblfModfl) dfsdTbblf.gftModfl();
        wiilf (tbblfModfl.gftRowCount() > 0) {
            tbblfModfl.rfmovfRow(0);
        }
    }

    // Cbll on EDT
    privbtf void bddDfsdriptor(Dfsdriptor dfsd, String tfxt) {
        if (dfsd != null && dfsd.gftFifldNbmfs().lfngti > 0) {
            DffbultTbblfModfl tbblfModfl = (DffbultTbblfModfl) dfsdTbblf.gftModfl();
            Objfdt rowDbtb[] = nfw Objfdt[2];
            rowDbtb[0] = nfw TbblfRowDividfr(tfxt);
            rowDbtb[1] = nfw TbblfRowDividfr("");
            tbblfModfl.bddRow(rowDbtb);
            for (String fifldNbmf : dfsd.gftFifldNbmfs()) {
                rowDbtb[0] = fifldNbmf;
                Objfdt fifldVbluf = dfsd.gftFifldVbluf(fifldNbmf);
                if (fifldVbluf instbndfof boolfbn[]) {
                    rowDbtb[1] = Arrbys.toString((boolfbn[]) fifldVbluf);
                } flsf if (fifldVbluf instbndfof bytf[]) {
                    rowDbtb[1] = Arrbys.toString((bytf[]) fifldVbluf);
                } flsf if (fifldVbluf instbndfof dibr[]) {
                    rowDbtb[1] = Arrbys.toString((dibr[]) fifldVbluf);
                } flsf if (fifldVbluf instbndfof doublf[]) {
                    rowDbtb[1] = Arrbys.toString((doublf[]) fifldVbluf);
                } flsf if (fifldVbluf instbndfof flobt[]) {
                    rowDbtb[1] = Arrbys.toString((flobt[]) fifldVbluf);
                } flsf if (fifldVbluf instbndfof int[]) {
                    rowDbtb[1] = Arrbys.toString((int[]) fifldVbluf);
                } flsf if (fifldVbluf instbndfof long[]) {
                    rowDbtb[1] = Arrbys.toString((long[]) fifldVbluf);
                } flsf if (fifldVbluf instbndfof siort[]) {
                    rowDbtb[1] = Arrbys.toString((siort[]) fifldVbluf);
                } flsf if (fifldVbluf instbndfof Objfdt[]) {
                    rowDbtb[1] = Arrbys.toString((Objfdt[]) fifldVbluf);
                } flsf {
                    rowDbtb[1] = fifldVbluf;
                }
                tbblfModfl.bddRow(rowDbtb);
            }
            tbblfModfl.nfwDbtbAvbilbblf(nfw TbblfModflEvfnt(tbblfModfl));
        }
    }

    // Cbll on EDT
    publid void bddMBfbnInfo(XMBfbn mbfbn, MBfbnInfo mbfbnInfo) {
        fmptyInfoTbblf();
        fmptyDfsdTbblf();
        ((TitlfdBordfr) infoBordfrPbnfl.gftBordfr()).sftTitlf(
                Mfssbgfs.MBEAN_INFO);
        String tfxt = Mfssbgfs.INFO + ":";
        DffbultTbblfModfl tbblfModfl = (DffbultTbblfModfl) infoTbblf.gftModfl();
        Objfdt rowDbtb[] = nfw Objfdt[2];
        rowDbtb[0] = nfw TbblfRowDividfr(tfxt);
        rowDbtb[1] = nfw TbblfRowDividfr("");
        tbblfModfl.bddRow(rowDbtb);
        rowDbtb[0] = Mfssbgfs.OBJECT_NAME;
        rowDbtb[1] = mbfbn.gftObjfdtNbmf();
        tbblfModfl.bddRow(rowDbtb);
        rowDbtb[0] = Mfssbgfs.CLASS_NAME;
        rowDbtb[1] = mbfbnInfo.gftClbssNbmf();
        tbblfModfl.bddRow(rowDbtb);
        rowDbtb[0] = Mfssbgfs.DESCRIPTION;
        rowDbtb[1] = mbfbnInfo.gftDfsdription();
        tbblfModfl.bddRow(rowDbtb);
        bddDfsdriptor(mbfbnInfo.gftDfsdriptor(), tfxt);
        // MBfbnConstrudtorInfo
        //
        int i = 0;
        for (MBfbnConstrudtorInfo mbdi : mbfbnInfo.gftConstrudtors()) {
            bddMBfbnConstrudtorInfo(mbdi,
                    Mfssbgfs.CONSTRUCTOR + "-" + i + ":");
            // MBfbnPbrbmftfrInfo
            //
            int j = 0;
            for (MBfbnPbrbmftfrInfo mbpi : mbdi.gftSignbturf()) {
                bddMBfbnPbrbmftfrInfo(mbpi,
                        Mfssbgfs.PARAMETER + "-" + i + "-" + j + ":");
                j++;
            }
            i++;
        }
        tbblfModfl.nfwDbtbAvbilbblf(nfw TbblfModflEvfnt(tbblfModfl));
    }

    // Cbll on EDT
    publid void bddMBfbnAttributfInfo(MBfbnAttributfInfo mbbi) {
        fmptyInfoTbblf();
        fmptyDfsdTbblf();
        ((TitlfdBordfr) infoBordfrPbnfl.gftBordfr()).sftTitlf(
                Mfssbgfs.MBEAN_ATTRIBUTE_INFO);
        String tfxt = Mfssbgfs.ATTRIBUTE + ":";
        DffbultTbblfModfl tbblfModfl = (DffbultTbblfModfl) infoTbblf.gftModfl();
        Objfdt rowDbtb[] = nfw Objfdt[2];
        rowDbtb[0] = nfw TbblfRowDividfr(tfxt);
        rowDbtb[1] = nfw TbblfRowDividfr("");
        tbblfModfl.bddRow(rowDbtb);
        rowDbtb[0] = Mfssbgfs.NAME;
        rowDbtb[1] = mbbi.gftNbmf();
        tbblfModfl.bddRow(rowDbtb);
        rowDbtb[0] = Mfssbgfs.DESCRIPTION;
        rowDbtb[1] = mbbi.gftDfsdription();
        tbblfModfl.bddRow(rowDbtb);
        rowDbtb[0] = Mfssbgfs.READABLE;
        rowDbtb[1] = mbbi.isRfbdbblf();
        tbblfModfl.bddRow(rowDbtb);
        rowDbtb[0] = Mfssbgfs.WRITABLE;
        rowDbtb[1] = mbbi.isWritbblf();
        tbblfModfl.bddRow(rowDbtb);
        rowDbtb[0] = Mfssbgfs.IS;
        rowDbtb[1] = mbbi.isIs();
        tbblfModfl.bddRow(rowDbtb);
        rowDbtb[0] = Mfssbgfs.TYPE;
        rowDbtb[1] = mbbi.gftTypf();
        tbblfModfl.bddRow(rowDbtb);
        bddDfsdriptor(mbbi.gftDfsdriptor(), tfxt);
        tbblfModfl.nfwDbtbAvbilbblf(nfw TbblfModflEvfnt(tbblfModfl));
    }

    // Cbll on EDT
    publid void bddMBfbnOpfrbtionInfo(MBfbnOpfrbtionInfo mboi) {
        fmptyInfoTbblf();
        fmptyDfsdTbblf();
        ((TitlfdBordfr) infoBordfrPbnfl.gftBordfr()).sftTitlf(
                Mfssbgfs.MBEAN_OPERATION_INFO);
        String tfxt = Mfssbgfs.OPERATION + ":";
        DffbultTbblfModfl tbblfModfl = (DffbultTbblfModfl) infoTbblf.gftModfl();
        Objfdt rowDbtb[] = nfw Objfdt[2];
        rowDbtb[0] = nfw TbblfRowDividfr(tfxt);
        rowDbtb[1] = nfw TbblfRowDividfr("");
        tbblfModfl.bddRow(rowDbtb);
        rowDbtb[0] = Mfssbgfs.NAME;
        rowDbtb[1] = mboi.gftNbmf();
        tbblfModfl.bddRow(rowDbtb);
        rowDbtb[0] = Mfssbgfs.DESCRIPTION;
        rowDbtb[1] = mboi.gftDfsdription();
        tbblfModfl.bddRow(rowDbtb);
        rowDbtb[0] = Mfssbgfs.IMPACT;
        switdi (mboi.gftImpbdt()) {
            dbsf MBfbnOpfrbtionInfo.INFO:
                rowDbtb[1] = Mfssbgfs.INFO_CAPITALIZED;
                brfbk;
            dbsf MBfbnOpfrbtionInfo.ACTION:
                rowDbtb[1] = Mfssbgfs.ACTION_CAPITALIZED;
                brfbk;
            dbsf MBfbnOpfrbtionInfo.ACTION_INFO:
                rowDbtb[1] = Mfssbgfs.ACTION_INFO_CAPITALIZED;
                brfbk;
            dbsf MBfbnOpfrbtionInfo.UNKNOWN:
                rowDbtb[1] = Mfssbgfs.UNKNOWN_CAPITALIZED;
                brfbk;
        }
        tbblfModfl.bddRow(rowDbtb);
        rowDbtb[0] = Mfssbgfs.RETURN_TYPE;
        rowDbtb[1] = mboi.gftRfturnTypf();
        tbblfModfl.bddRow(rowDbtb);
        bddDfsdriptor(mboi.gftDfsdriptor(), tfxt);
        // MBfbnPbrbmftfrInfo
        //
        int i = 0;
        for (MBfbnPbrbmftfrInfo mbpi : mboi.gftSignbturf()) {
            bddMBfbnPbrbmftfrInfo(mbpi,
                    Mfssbgfs.PARAMETER + "-" + i++ + ":");
        }
        tbblfModfl.nfwDbtbAvbilbblf(nfw TbblfModflEvfnt(tbblfModfl));
    }

    // Cbll on EDT
    publid void bddMBfbnNotifidbtionInfo(MBfbnNotifidbtionInfo mbni) {
        fmptyInfoTbblf();
        fmptyDfsdTbblf();
        ((TitlfdBordfr) infoBordfrPbnfl.gftBordfr()).sftTitlf(
                Mfssbgfs.MBEAN_NOTIFICATION_INFO);
        String tfxt = Mfssbgfs.NOTIFICATION + ":";
        DffbultTbblfModfl tbblfModfl = (DffbultTbblfModfl) infoTbblf.gftModfl();
        Objfdt rowDbtb[] = nfw Objfdt[2];
        rowDbtb[0] = nfw TbblfRowDividfr(tfxt);
        rowDbtb[1] = nfw TbblfRowDividfr("");
        tbblfModfl.bddRow(rowDbtb);
        rowDbtb[0] = Mfssbgfs.NAME;
        rowDbtb[1] = mbni.gftNbmf();
        tbblfModfl.bddRow(rowDbtb);
        rowDbtb[0] = Mfssbgfs.DESCRIPTION;
        rowDbtb[1] = mbni.gftDfsdription();
        tbblfModfl.bddRow(rowDbtb);
        rowDbtb[0] = Mfssbgfs.NOTIF_TYPES;
        rowDbtb[1] = Arrbys.toString(mbni.gftNotifTypfs());
        tbblfModfl.bddRow(rowDbtb);
        bddDfsdriptor(mbni.gftDfsdriptor(), tfxt);
        tbblfModfl.nfwDbtbAvbilbblf(nfw TbblfModflEvfnt(tbblfModfl));
    }

    // Cbll on EDT
    privbtf void bddMBfbnConstrudtorInfo(MBfbnConstrudtorInfo mbdi, String tfxt) {
        DffbultTbblfModfl tbblfModfl = (DffbultTbblfModfl) infoTbblf.gftModfl();
        Objfdt rowDbtb[] = nfw Objfdt[2];
        rowDbtb[0] = nfw TbblfRowDividfr(tfxt);
        rowDbtb[1] = nfw TbblfRowDividfr("");
        tbblfModfl.bddRow(rowDbtb);
        rowDbtb[0] = Mfssbgfs.NAME;
        rowDbtb[1] = mbdi.gftNbmf();
        tbblfModfl.bddRow(rowDbtb);
        rowDbtb[0] = Mfssbgfs.DESCRIPTION;
        rowDbtb[1] = mbdi.gftDfsdription();
        tbblfModfl.bddRow(rowDbtb);
        bddDfsdriptor(mbdi.gftDfsdriptor(), tfxt);
        tbblfModfl.nfwDbtbAvbilbblf(nfw TbblfModflEvfnt(tbblfModfl));
    }

    // Cbll on EDT
    privbtf void bddMBfbnPbrbmftfrInfo(MBfbnPbrbmftfrInfo mbpi, String tfxt) {
        DffbultTbblfModfl tbblfModfl = (DffbultTbblfModfl) infoTbblf.gftModfl();
        Objfdt rowDbtb[] = nfw Objfdt[2];
        rowDbtb[0] = nfw TbblfRowDividfr(tfxt);
        rowDbtb[1] = nfw TbblfRowDividfr("");
        tbblfModfl.bddRow(rowDbtb);
        rowDbtb[0] = Mfssbgfs.NAME;
        rowDbtb[1] = mbpi.gftNbmf();
        tbblfModfl.bddRow(rowDbtb);
        rowDbtb[0] = Mfssbgfs.DESCRIPTION;
        rowDbtb[1] = mbpi.gftDfsdription();
        tbblfModfl.bddRow(rowDbtb);
        rowDbtb[0] = Mfssbgfs.TYPE;
        rowDbtb[1] = mbpi.gftTypf();
        tbblfModfl.bddRow(rowDbtb);
        bddDfsdriptor(mbpi.gftDfsdriptor(), tfxt);
        tbblfModfl.nfwDbtbAvbilbblf(nfw TbblfModflEvfnt(tbblfModfl));
    }
}
