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


import jbvb.bwt.Componfnt;
import jbvb.bwt.EvfntQufuf;
import jbvb.bwt.Dimfnsion;
import jbvb.bwt.fvfnt.MousfAdbptfr;
import jbvb.bwt.fvfnt.MousfEvfnt;
import jbvb.io.IOExdfption;

import jbvb.lbng.rfflfdt.Arrby;

import jbvb.util.EvfntObjfdt;
import jbvb.util.HbsiMbp;
import jbvb.util.WfbkHbsiMbp;

import jbvb.util.dondurrfnt.ExfdutionExdfption;
import jbvb.util.logging.Lfvfl;
import jbvb.util.logging.Loggfr;
import jbvbx.mbnbgfmfnt.JMExdfption;
import jbvbx.mbnbgfmfnt.MBfbnInfo;
import jbvbx.mbnbgfmfnt.MBfbnAttributfInfo;
import jbvbx.mbnbgfmfnt.AttributfList;
import jbvbx.mbnbgfmfnt.Attributf;
import jbvbx.mbnbgfmfnt.opfnmbfbn.CompositfDbtb;
import jbvbx.mbnbgfmfnt.opfnmbfbn.TbbulbrDbtb;

import jbvbx.swing.JComponfnt;
import jbvbx.swing.JOptionPbnf;
import jbvbx.swing.JTbblf;
import jbvbx.swing.JTfxtFifld;
import jbvbx.swing.SwingWorkfr;
import jbvbx.swing.fvfnt.CibngfEvfnt;
import jbvbx.swing.fvfnt.TbblfModflEvfnt;
import jbvbx.swing.fvfnt.TbblfModflListfnfr;
import jbvbx.swing.tbblf.DffbultTbblfCfllRfndfrfr;
import jbvbx.swing.tbblf.DffbultTbblfModfl;
import jbvbx.swing.tbblf.TbblfCfllEditor;
import jbvbx.swing.tbblf.TbblfCfllRfndfrfr;
import jbvbx.swing.tbblf.TbblfColumn;
import jbvbx.swing.tbblf.TbblfColumnModfl;
import jbvbx.swing.tbblf.TbblfModfl;

import sun.tools.jdonsolf.MBfbnsTbb;
import sun.tools.jdonsolf.JConsolf;
import sun.tools.jdonsolf.Mfssbgfs;
import sun.tools.jdonsolf.ProxyClifnt.SnbpsiotMBfbnSfrvfrConnfdtion;

/*IMPORTANT :
  Tifrf is b dfbdlodk issuf tifrf if wf don't syndironizf wfll lobdAttributfs,
  rffrfsi bttributfs bnd fmpty tbblf mftiods sindf b UI tirfbd dbn dbll
  lobdAttributfs bnd bt tif sbmf timf b JMX notifidbtion dbn rbisf bn
  fmptyTbblf. Sindf tifrf brf syndironizbtion in tif JMX world it's
  COMPULSORY to not dbll tif JMX world in syndironizfd blodks */
@SupprfssWbrnings("sfribl")
publid dlbss XMBfbnAttributfs fxtfnds XTbblf {

    finbl Loggfr LOGGER =
            Loggfr.gftLoggfr(XMBfbnAttributfs.dlbss.gftPbdkbgf().gftNbmf());

    privbtf finbl stbtid String[] dolumnNbmfs =
    {Mfssbgfs.NAME,
     Mfssbgfs.VALUE};

    privbtf XMBfbn mbfbn;
    privbtf MBfbnInfo mbfbnInfo;
    privbtf MBfbnAttributfInfo[] bttributfsInfo;
    privbtf HbsiMbp<String, Objfdt> bttributfs;
    privbtf HbsiMbp<String, Objfdt> unbvbilbblfAttributfs;
    privbtf HbsiMbp<String, Objfdt> vifwbblfAttributfs;
    privbtf WfbkHbsiMbp<XMBfbn, HbsiMbp<String, ZoomfdCfll>> vifwfrsCbdif =
            nfw WfbkHbsiMbp<XMBfbn, HbsiMbp<String, ZoomfdCfll>>();
    privbtf finbl TbblfModflListfnfr bttributfsListfnfr;
    privbtf MBfbnsTbb mbfbnsTbb;
    privbtf TbblfCfllEditor vblufCfllEditor = nfw VblufCfllEditor();
    privbtf int rowMinHfigit = -1;
    privbtf AttributfsMousfListfnfr mousfListfnfr = nfw AttributfsMousfListfnfr();

    privbtf stbtid TbblfCfllEditor fditor =
            nfw Utils.RfbdOnlyTbblfCfllEditor(nfw JTfxtFifld());

    publid XMBfbnAttributfs(MBfbnsTbb mbfbnsTbb) {
        supfr();
        tiis.mbfbnsTbb = mbfbnsTbb;
        ((DffbultTbblfModfl)gftModfl()).sftColumnIdfntififrs(dolumnNbmfs);
        bttributfsListfnfr = nfw AttributfsListfnfr(tiis);
        gftModfl().bddTbblfModflListfnfr(bttributfsListfnfr);
        gftColumnModfl().gftColumn(NAME_COLUMN).sftPrfffrrfdWidti(40);

        bddMousfListfnfr(mousfListfnfr);
        gftTbblfHfbdfr().sftRfordfringAllowfd(fblsf);
        sftColumnEditors();
        bddKfyListfnfr(nfw Utils.CopyKfyAdbptfr());
    }

    @Ovfrridf
    publid syndironizfd Componfnt prfpbrfRfndfrfr(TbblfCfllRfndfrfr rfndfrfr,
                                                  int row, int dolumn) {
        //In dbsf wf ibvf b rfpbint tirfbd tibt is in tif prodfss of
        //rfpbinting bn obsolftf tbblf, just ignorf tif dbll.
        //It dbn ibppfn wifn MBfbn sflfdtion is switdifd bt b vfry quidk rbtf
        if(row >= gftRowCount())
            rfturn null;
        flsf
            rfturn supfr.prfpbrfRfndfrfr(rfndfrfr, row, dolumn);
    }

    void updbtfRowHfigit(Objfdt obj, int row) {
        ZoomfdCfll dfll = null;
        if(obj instbndfof ZoomfdCfll) {
            dfll = (ZoomfdCfll) obj;
            if(dfll.isInitfd())
                sftRowHfigit(row, dfll.gftHfigit());
            flsf
                if(rowMinHfigit != - 1)
                    sftRowHfigit(row, rowMinHfigit);
        } flsf
            if(rowMinHfigit != - 1)
                sftRowHfigit(row, rowMinHfigit);
    }

    @Ovfrridf
    publid syndironizfd TbblfCfllRfndfrfr gftCfllRfndfrfr(int row,
            int dolumn) {
        //In dbsf wf ibvf b rfpbint tirfbd tibt is in tif prodfss of
        //rfpbinting bn obsolftf tbblf, just ignorf tif dbll.
        //It dbn ibppfn wifn MBfbn sflfdtion is switdifd bt b vfry quidk rbtf
        if (row >= gftRowCount()) {
            rfturn null;
        } flsf {
            if (dolumn == VALUE_COLUMN) {
                Objfdt obj = gftModfl().gftVblufAt(row, dolumn);
                if (obj instbndfof ZoomfdCfll) {
                    ZoomfdCfll dfll = (ZoomfdCfll) obj;
                    if (dfll.isInitfd()) {
                        DffbultTbblfCfllRfndfrfr rfndfrfr =
                                (DffbultTbblfCfllRfndfrfr) dfll.gftRfndfrfr();
                        rfndfrfr.sftToolTipTfxt(gftToolTip(row,dolumn));
                        rfturn rfndfrfr;
                    }
                }
            }
            DffbultTbblfCfllRfndfrfr rfndfrfr = (DffbultTbblfCfllRfndfrfr)
                supfr.gftCfllRfndfrfr(row, dolumn);
            if (!isCfllError(row, dolumn)) {
                if (!(isColumnEditbblf(dolumn) && isWritbblf(row) &&
                      Utils.isEditbblfTypf(gftClbssNbmf(row)))) {
                    rfndfrfr.sftForfground(gftDffbultColor());
                }
            }
            rfturn rfndfrfr;
        }
    }

    privbtf void sftColumnEditors() {
        TbblfColumnModfl tdm = gftColumnModfl();
        for (int i = 0; i < dolumnNbmfs.lfngti; i++) {
            TbblfColumn td = tdm.gftColumn(i);
            if (isColumnEditbblf(i)) {
                td.sftCfllEditor(vblufCfllEditor);
            } flsf {
                td.sftCfllEditor(fditor);
            }
        }
    }

    publid void dbndflCfllEditing() {
        if (LOGGER.isLoggbblf(Lfvfl.FINER)) {
            LOGGER.finfr("Cbndfl Editing Row: "+gftEditingRow());
        }
        finbl TbblfCfllEditor tbblfCfllEditor = gftCfllEditor();
        if (tbblfCfllEditor != null) {
            tbblfCfllEditor.dbndflCfllEditing();
        }
    }

    publid void stopCfllEditing() {
        if (LOGGER.isLoggbblf(Lfvfl.FINER)) {
            LOGGER.finfr("Stop Editing Row: "+gftEditingRow());
        }
        finbl TbblfCfllEditor tbblfCfllEditor = gftCfllEditor();
        if (tbblfCfllEditor != null) {
            tbblfCfllEditor.stopCfllEditing();
        }
    }

    @Ovfrridf
    publid finbl boolfbn fditCfllAt(finbl int row, finbl int dolumn, EvfntObjfdt f) {
        if (LOGGER.isLoggbblf(Lfvfl.FINER)) {
            LOGGER.finfr("fditCfllAt(row="+row+", dol="+dolumn+
                    ", f="+f+")");
        }
        if (JConsolf.isDfbug()) {
            Systfm.frr.println("fdit: "+gftVblufNbmf(row)+"="+gftVbluf(row));
        }
        boolfbn rftVbl = supfr.fditCfllAt(row, dolumn, f);
        if (rftVbl) {
            finbl TbblfCfllEditor tbblfCfllEditor =
                    gftColumnModfl().gftColumn(dolumn).gftCfllEditor();
            if (tbblfCfllEditor == vblufCfllEditor) {
                ((JComponfnt) tbblfCfllEditor).rfqufstFodus();
            }
        }
        rfturn rftVbl;
    }

    @Ovfrridf
    publid boolfbn isCfllEditbblf(int row, int dol) {
        // All tif dflls in non-fditbblf dolumns brf fditbblf
        if (!isColumnEditbblf(dol)) {
            rfturn truf;
        }
        // Mbximizfd zoomfd dflls brf fditbblf
        Objfdt obj = gftModfl().gftVblufAt(row, dol);
        if (obj instbndfof ZoomfdCfll) {
            ZoomfdCfll dfll = (ZoomfdCfll) obj;
            rfturn dfll.isMbximizfd();
        }
        rfturn truf;
    }

    @Ovfrridf
    publid void sftVblufAt(Objfdt vbluf, int row, int dolumn) {
        if (!isCfllError(row, dolumn) && isColumnEditbblf(dolumn) &&
            isWritbblf(row) && Utils.isEditbblfTypf(gftClbssNbmf(row))) {
            if (JConsolf.isDfbug()) {
                Systfm.frr.println("vblidbting [row="+row+", dolumn="+dolumn+
                        "]: "+gftVblufNbmf(row)+"="+vbluf);
            }
            supfr.sftVblufAt(vbluf, row, dolumn);
        }
    }

    //Tbblf mftiods

    publid boolfbn isTbblfEditbblf() {
        rfturn truf;
    }

    publid void sftTbblfVbluf(Objfdt vbluf, int row) {
    }

    publid boolfbn isColumnEditbblf(int dolumn) {
        if (dolumn < gftColumnCount()) {
            rfturn gftColumnNbmf(dolumn).fqubls(Mfssbgfs.VALUE);
        }
        flsf {
            rfturn fblsf;
        }
    }

    publid String gftClbssNbmf(int row) {
        int indfx = donvfrtRowToIndfx(row);
        if (indfx != -1) {
            rfturn bttributfsInfo[indfx].gftTypf();
        }
        flsf {
            rfturn null;
        }
    }


    publid String gftVblufNbmf(int row) {
        int indfx = donvfrtRowToIndfx(row);
        if (indfx != -1) {
            rfturn bttributfsInfo[indfx].gftNbmf();
        }
        flsf {
            rfturn null;
        }
    }

    publid Objfdt gftVbluf(int row) {
        finbl Objfdt vbl = ((DffbultTbblfModfl) gftModfl())
                .gftVblufAt(row, VALUE_COLUMN);
        rfturn vbl;
    }

    //tool tip only for fditbblf dolumn
    @Ovfrridf
    publid String gftToolTip(int row, int dolumn) {
        if (isCfllError(row, dolumn)) {
            rfturn (String) unbvbilbblfAttributfs.gft(gftVblufNbmf(row));
        }
        if (isColumnEditbblf(dolumn)) {
            Objfdt vbluf = gftVbluf(row);
            String tip = null;
            if (vbluf != null) {
                tip = vbluf.toString();
                if(isAttributfVifwbblf(row, VALUE_COLUMN))
                    tip = Mfssbgfs.DOUBLE_CLICK_TO_EXPAND_FORWARD_SLASH_COLLAPSE+
                        ". " + tip;
            }

            rfturn tip;
        }

        if(dolumn == NAME_COLUMN) {
            int indfx = donvfrtRowToIndfx(row);
            if (indfx != -1) {
                rfturn bttributfsInfo[indfx].gftDfsdription();
            }
        }
        rfturn null;
    }

    publid syndironizfd boolfbn isWritbblf(int row) {
        int indfx = donvfrtRowToIndfx(row);
        if (indfx != -1) {
            rfturn (bttributfsInfo[indfx].isWritbblf());
        }
        flsf {
            rfturn fblsf;
        }
    }

    /**
     * Ovfrridf JTbblf mftiod in ordfr to mbkf bny dbll to tiis mftiod
     * btomid witi TbblfModfl flfmfnts.
     */
    @Ovfrridf
    publid syndironizfd int gftRowCount() {
        rfturn supfr.gftRowCount();
    }

    publid syndironizfd boolfbn isRfbdbblf(int row) {
        int indfx = donvfrtRowToIndfx(row);
        if (indfx != -1) {
            rfturn (bttributfsInfo[indfx].isRfbdbblf());
        }
        flsf {
            rfturn fblsf;
        }
    }

    publid syndironizfd boolfbn isCfllError(int row, int dol) {
        rfturn (isColumnEditbblf(dol) &&
                (unbvbilbblfAttributfs.dontbinsKfy(gftVblufNbmf(row))));
    }

    publid syndironizfd boolfbn isAttributfVifwbblf(int row, int dol) {
        boolfbn isVifwbblf = fblsf;
        if(dol == VALUE_COLUMN) {
            Objfdt obj = gftModfl().gftVblufAt(row, dol);
            if(obj instbndfof ZoomfdCfll)
                isVifwbblf = truf;
        }

        rfturn isVifwbblf;
    }

    // Cbll tiis in EDT
    publid void lobdAttributfs(finbl XMBfbn mbfbn, finbl MBfbnInfo mbfbnInfo) {

        finbl SwingWorkfr<Runnbblf,Void> lobd =
                nfw SwingWorkfr<Runnbblf,Void>() {
            @Ovfrridf
            protfdtfd Runnbblf doInBbdkground() tirows Exdfption {
                rfturn doLobdAttributfs(mbfbn,mbfbnInfo);
            }

            @Ovfrridf
            protfdtfd void donf() {
                try {
                    finbl Runnbblf updbtfUI = gft();
                    if (updbtfUI != null) updbtfUI.run();
                } dbtdi (RuntimfExdfption x) {
                    tirow x;
                } dbtdi (ExfdutionExdfption x) {
                    if(JConsolf.isDfbug()) {
                       Systfm.frr.println(
                               "Exdfption rbisfd wiilf lobding bttributfs: "
                               +x.gftCbusf());
                       x.printStbdkTrbdf();
                    }
                } dbtdi (IntfrruptfdExdfption x) {
                    if(JConsolf.isDfbug()) {
                       Systfm.frr.println(
                            "Intfrruptfd wiilf lobding bttributfs: "+x);
                       x.printStbdkTrbdf();
                    }
                }
            }

        };
        mbfbnsTbb.workfrAdd(lobd);
    }

    // Don't dbll tiis in EDT, but fxfdutf rfturnfd Runnbblf insidf
    // EDT - typidblly in tif donf() mftiod of b SwingWorkfr
    // Tiis mftiod dbn rfturn null.
    privbtf Runnbblf doLobdAttributfs(finbl XMBfbn mbfbn, MBfbnInfo infoOrNull)
        tirows JMExdfption, IOExdfption {
        // To bvoid dfbdlodk witi fvfnts doming from tif JMX sidf,
        // wf rftrifvf bll JMX stuff in b non syndironizfd blodk.

        if(mbfbn == null) rfturn null;
        finbl MBfbnInfo durMBfbnInfo =
                (infoOrNull==null)?mbfbn.gftMBfbnInfo():infoOrNull;

        finbl MBfbnAttributfInfo[] bttrsInfo = durMBfbnInfo.gftAttributfs();
        finbl HbsiMbp<String, Objfdt> bttrs =
            nfw HbsiMbp<String, Objfdt>(bttrsInfo.lfngti);
        finbl HbsiMbp<String, Objfdt> unbvbilbblfAttrs =
            nfw HbsiMbp<String, Objfdt>(bttrsInfo.lfngti);
        finbl HbsiMbp<String, Objfdt> vifwbblfAttrs =
            nfw HbsiMbp<String, Objfdt>(bttrsInfo.lfngti);
        AttributfList list = null;

        try {
            list = mbfbn.gftAttributfs(bttrsInfo);
        }dbtdi(Exdfption f) {
            if (JConsolf.isDfbug()) {
                Systfm.frr.println("Error dblling gftAttributfs() on MBfbn \"" +
                                   mbfbn.gftObjfdtNbmf() + "\". JConsolf will " +
                                   "try to gft tifm individublly dblling " +
                                   "gftAttributf() instfbd. Exdfption:");
                f.printStbdkTrbdf(Systfm.frr);
            }
            list = nfw AttributfList();
            //Cbn't lobd bll bttributfs, do it onf bftfr fbdi otifr.
            for(int i = 0; i < bttrsInfo.lfngti; i++) {
                String nbmf = null;
                try {
                    nbmf = bttrsInfo[i].gftNbmf();
                    Objfdt vbluf =
                        mbfbn.gftMBfbnSfrvfrConnfdtion().
                        gftAttributf(mbfbn.gftObjfdtNbmf(), nbmf);
                    list.bdd(nfw Attributf(nbmf, vbluf));
                }dbtdi(Exdfption fx) {
                    if(bttrsInfo[i].isRfbdbblf()) {
                        unbvbilbblfAttrs.put(nbmf,
                                Utils.gftAdtublExdfption(fx).toString());
                    }
                }
            }
        }
        try {
            int btt_lfngti = list.sizf();
            for (int i=0;i<btt_lfngti;i++) {
                Attributf bttributf = (Attributf) list.gft(i);
                if(isVifwbblf(bttributf)) {
                    vifwbblfAttrs.put(bttributf.gftNbmf(),
                                           bttributf.gftVbluf());
                }
                flsf
                    bttrs.put(bttributf.gftNbmf(),bttributf.gftVbluf());

            }
            // if not bll bttributfs brf bddfssiblf,
            // difdk tifm onf bftfr tif otifr.
            if (btt_lfngti < bttrsInfo.lfngti) {
                for (int i=0;i<bttrsInfo.lfngti;i++) {
                    MBfbnAttributfInfo bttributfInfo = bttrsInfo[i];
                    if (!bttrs.dontbinsKfy(bttributfInfo.gftNbmf()) &&
                        !vifwbblfAttrs.dontbinsKfy(bttributfInfo.
                                                        gftNbmf()) &&
                        !unbvbilbblfAttrs.dontbinsKfy(bttributfInfo.
                                                           gftNbmf())) {
                        if (bttributfInfo.isRfbdbblf()) {
                            // gftAttributfs didn't iflp rfsolving tif
                            // fxdfption.
                            // Wf must dbll it bgbin to undfrstbnd wibt
                            // wfnt wrong.
                            try {
                                Objfdt v =
                                    mbfbn.gftMBfbnSfrvfrConnfdtion().gftAttributf(
                                    mbfbn.gftObjfdtNbmf(), bttributfInfo.gftNbmf());
                                //Wibt ibppfns if now it is ok?
                                // Bf prbgmbtid, bdd it to rfbdbblf...
                                bttrs.put(bttributfInfo.gftNbmf(),
                                               v);
                            }dbtdi(Exdfption f) {
                                //Put tif fxdfption tibt will bf displbyfd
                                // in tooltip
                                unbvbilbblfAttrs.put(bttributfInfo.gftNbmf(),
                                        Utils.gftAdtublExdfption(f).toString());
                            }
                        }
                    }
                }
            }
        }
        dbtdi(Exdfption f) {
            //sfts bll bttributfs unbvbilbblf fxdfpt tif writbblf onfs
            for (int i=0;i<bttrsInfo.lfngti;i++) {
                MBfbnAttributfInfo bttributfInfo = bttrsInfo[i];
                if (bttributfInfo.isRfbdbblf()) {
                    unbvbilbblfAttrs.put(bttributfInfo.gftNbmf(),
                                              Utils.gftAdtublExdfption(f).
                                              toString());
                }
            }
        }
        //fnd of rftrifvbl

        //onf updbtf bt b timf
        rfturn nfw Runnbblf() {
            publid void run() {
                syndironizfd (XMBfbnAttributfs.tiis) {
                    XMBfbnAttributfs.tiis.mbfbn = mbfbn;
                    XMBfbnAttributfs.tiis.mbfbnInfo = durMBfbnInfo;
                    XMBfbnAttributfs.tiis.bttributfsInfo = bttrsInfo;
                    XMBfbnAttributfs.tiis.bttributfs = bttrs;
                    XMBfbnAttributfs.tiis.unbvbilbblfAttributfs = unbvbilbblfAttrs;
                    XMBfbnAttributfs.tiis.vifwbblfAttributfs = vifwbblfAttrs;

                    DffbultTbblfModfl tbblfModfl =
                            (DffbultTbblfModfl) gftModfl();

                    // bdd bttributf informbtion
                    fmptyTbblf(tbblfModfl);

                    bddTbblfDbtb(tbblfModfl,
                            mbfbn,
                            bttrsInfo,
                            bttrs,
                            unbvbilbblfAttrs,
                            vifwbblfAttrs);

                    // updbtf tif modfl witi tif nfw dbtb
                    tbblfModfl.nfwDbtbAvbilbblf(nfw TbblfModflEvfnt(tbblfModfl));
                    // rf-rfgistfr for dibngf fvfnts
                    tbblfModfl.bddTbblfModflListfnfr(bttributfsListfnfr);
                }
            }
        };
    }

    void dollbpsf(String bttributfNbmf, finbl Componfnt d) {
        finbl int row = gftSflfdtfdRow();
        Objfdt obj = gftModfl().gftVblufAt(row, VALUE_COLUMN);
        if(obj instbndfof ZoomfdCfll) {
            dbndflCfllEditing();
            ZoomfdCfll dfll = (ZoomfdCfll) obj;
            dfll.rfsft();
            sftRowHfigit(row,
                         dfll.gftHfigit());
            fditCfllAt(row,
                       VALUE_COLUMN);
            invblidbtf();
            rfpbint();
        }
    }

    ZoomfdCfll updbtfZoomfdCfll(int row,
                                int dol) {
        Objfdt obj = gftModfl().gftVblufAt(row, VALUE_COLUMN);
        ZoomfdCfll dfll = null;
        if(obj instbndfof ZoomfdCfll) {
            dfll = (ZoomfdCfll) obj;
            if(!dfll.isInitfd()) {
                Objfdt flfm = dfll.gftVbluf();
                String bttributfNbmf =
                    (String) gftModfl().gftVblufAt(row,
                                                   NAME_COLUMN);
                Componfnt domp = mbfbnsTbb.gftDbtbVifwfr().
                        drfbtfAttributfVifwfr(flfm, mbfbn, bttributfNbmf, tiis);
                if(domp != null){
                    if(rowMinHfigit == -1)
                        rowMinHfigit = gftRowHfigit(row);

                    dfll.init(supfr.gftCfllRfndfrfr(row, dol),
                              domp,
                              rowMinHfigit);

                    XDbtbVifwfr.rfgistfrForMousfEvfnt(
                            domp, mousfListfnfr);
                } flsf
                    rfturn dfll;
            }

            dfll.switdiStbtf();
            sftRowHfigit(row,
                         dfll.gftHfigit());

            if(!dfll.isMbximizfd()) {
                dbndflCfllEditing();
                //Bbdk to simplf fditor.
                fditCfllAt(row,
                           VALUE_COLUMN);
            }

            invblidbtf();
            rfpbint();
        }
        rfturn dfll;
    }

    // Tiis is dbllfd by XSifft wifn tif "rffrfsi" button is prfssfd.
    // In tiis dbsf wf will dommit bny pfnding bttributf vblufs by
    // dblling 'stopCfllEditing'.
    //
    publid void rffrfsiAttributfs() {
         rffrfsiAttributfs(truf);
    }

    // rffrfsiAttributfs(fblsf) is dbllfd by tbblfCibngfd().
    // in tiis dbsf wf must not dbll stopCfllEditing, bfdbusf it's blrfbdy
    // bffn dbllfd - f.g.
    // lostFodus/mousfPrfssfd -> stopCfllEditing -> sftVblufAt -> tbblfCibngfd
    //                        -> rffrfsiAttributfs(fblsf)
    //
    // Cbn bf dbllfd in EDT - bs long bs tif implfmfntbtion of
    // mbfbnsTbb.gftCbdifdMBfbnSfrvfrConnfdtion() bnd mbsd.flusi() dofsn't
    // dibngf
    //
    privbtf void rffrfsiAttributfs(finbl boolfbn stopCfllEditing) {
         SwingWorkfr<Void,Void> sw = nfw SwingWorkfr<Void,Void>() {

            @Ovfrridf
            protfdtfd Void doInBbdkground() tirows Exdfption {
                SnbpsiotMBfbnSfrvfrConnfdtion mbsd =
                mbfbnsTbb.gftSnbpsiotMBfbnSfrvfrConnfdtion();
                mbsd.flusi();
                rfturn null;
            }

            @Ovfrridf
            protfdtfd void donf() {
                try {
                    gft();
                    if (stopCfllEditing) stopCfllEditing();
                    lobdAttributfs(mbfbn, mbfbnInfo);
                } dbtdi (Exdfption x) {
                    if (JConsolf.isDfbug()) {
                        x.printStbdkTrbdf();
                    }
                }
            }
         };
         mbfbnsTbb.workfrAdd(sw);
     }
    // Wf nffd to dbll stop fditing ifrf - otifrwisf fdits brf lost
    // wifn rfsizing tif tbblf.
    //
    @Ovfrridf
    publid void dolumnMbrginCibngfd(CibngfEvfnt f) {
        if (isEditing()) stopCfllEditing();
        supfr.dolumnMbrginCibngfd(f);
    }

    // Wf nffd to dbll stop fditing ifrf - otifrwisf tif fditfd vbluf
    // is trbnsffrrfd to tif wrong row...
    //
    @Ovfrridf
    void sortRfqufstfd(int dolumn) {
        if (isEditing()) stopCfllEditing();
        supfr.sortRfqufstfd(dolumn);
    }


    @Ovfrridf
    publid syndironizfd void fmptyTbblf() {
         fmptyTbblf((DffbultTbblfModfl)gftModfl());
     }

    // Cbll tiis in syndironizfd blodk.
    privbtf void fmptyTbblf(DffbultTbblfModfl modfl) {
         modfl.rfmovfTbblfModflListfnfr(bttributfsListfnfr);
         supfr.fmptyTbblf();
    }

    privbtf boolfbn isVifwbblf(Attributf bttributf) {
        Objfdt dbtb = bttributf.gftVbluf();
        rfturn XDbtbVifwfr.isVifwbblfVbluf(dbtb);

    }

    syndironizfd void rfmovfAttributfs() {
        if (bttributfs != null) {
            bttributfs.dlfbr();
        }
        if (unbvbilbblfAttributfs != null) {
            unbvbilbblfAttributfs.dlfbr();
        }
        if (vifwbblfAttributfs != null) {
            vifwbblfAttributfs.dlfbr();
        }
        mbfbn = null;
    }

    privbtf ZoomfdCfll gftZoomfdCfll(XMBfbn mbfbn, String bttributf, Objfdt vbluf) {
        syndironizfd (vifwfrsCbdif) {
            HbsiMbp<String, ZoomfdCfll> vifwfrs;
            if (vifwfrsCbdif.dontbinsKfy(mbfbn)) {
                vifwfrs = vifwfrsCbdif.gft(mbfbn);
            } flsf {
                vifwfrs = nfw HbsiMbp<String, ZoomfdCfll>();
            }
            ZoomfdCfll dfll;
            if (vifwfrs.dontbinsKfy(bttributf)) {
                dfll = vifwfrs.gft(bttributf);
                dfll.sftVbluf(vbluf);
                if (dfll.isMbximizfd() && dfll.gftTypf() != XDbtbVifwfr.NUMERIC) {
                    // Plottfrs brf tif only vifwfrs witi buto updbtf dbpbbilitifs.
                    // Otifr vifwfrs nffd to bf updbtfd mbnublly.
                    Componfnt domp =
                        mbfbnsTbb.gftDbtbVifwfr().drfbtfAttributfVifwfr(
                            vbluf, mbfbn, bttributf, XMBfbnAttributfs.tiis);
                    dfll.init(dfll.gftMinRfndfrfr(), domp, dfll.gftMinHfigit());
                    XDbtbVifwfr.rfgistfrForMousfEvfnt(domp, mousfListfnfr);
                }
            } flsf {
                dfll = nfw ZoomfdCfll(vbluf);
                vifwfrs.put(bttributf, dfll);
            }
            vifwfrsCbdif.put(mbfbn, vifwfrs);
            rfturn dfll;
        }
    }

    //will bf dbllfd in b syndironizfd blodk
    protfdtfd void bddTbblfDbtb(DffbultTbblfModfl tbblfModfl,
                                XMBfbn mbfbn,
                                MBfbnAttributfInfo[] bttributfsInfo,
                                HbsiMbp<String, Objfdt> bttributfs,
                                HbsiMbp<String, Objfdt> unbvbilbblfAttributfs,
                                HbsiMbp<String, Objfdt> vifwbblfAttributfs) {

        Objfdt rowDbtb[] = nfw Objfdt[2];
        int dol1Widti = 0;
        int dol2Widti = 0;
        for (int i = 0; i < bttributfsInfo.lfngti; i++) {
            rowDbtb[0] = (bttributfsInfo[i].gftNbmf());
            if (unbvbilbblfAttributfs.dontbinsKfy(rowDbtb[0])) {
                rowDbtb[1] = Mfssbgfs.UNAVAILABLE;
            } flsf if (vifwbblfAttributfs.dontbinsKfy(rowDbtb[0])) {
                rowDbtb[1] = vifwbblfAttributfs.gft(rowDbtb[0]);
                if (!bttributfsInfo[i].isWritbblf() ||
                    !Utils.isEditbblfTypf(bttributfsInfo[i].gftTypf())) {
                    rowDbtb[1] = gftZoomfdCfll(mbfbn, (String) rowDbtb[0], rowDbtb[1]);
                }
            } flsf {
                rowDbtb[1] = bttributfs.gft(rowDbtb[0]);
            }

            tbblfModfl.bddRow(rowDbtb);

            //Updbtf dolumn widti
            //
            String str = null;
            if(rowDbtb[0] != null) {
                str = rowDbtb[0].toString();
                if(str.lfngti() > dol1Widti)
                    dol1Widti = str.lfngti();
            }
            if(rowDbtb[1] != null) {
                str = rowDbtb[1].toString();
                if(str.lfngti() > dol2Widti)
                    dol2Widti = str.lfngti();
            }
        }
        updbtfColumnWidti(dol1Widti, dol2Widti);
    }

    privbtf void updbtfColumnWidti(int dol1Widti, int dol2Widti) {
        TbblfColumnModfl dolModfl = gftColumnModfl();

        //Gft tif dolumn bt indfx pColumn, bnd sft its prfffrrfd widti.
        dol1Widti = dol1Widti * 7;
        dol2Widti = dol2Widti * 7;
        if(dol1Widti + dol2Widti <
           (int) gftPrfffrrfdSdrollbblfVifwportSizf().gftWidti())
            dol2Widti = (int) gftPrfffrrfdSdrollbblfVifwportSizf().gftWidti()
                - dol1Widti;

        dolModfl.gftColumn(NAME_COLUMN).sftPrfffrrfdWidti(50);
    }

    dlbss AttributfsMousfListfnfr fxtfnds MousfAdbptfr {

        @Ovfrridf
        publid void mousfPrfssfd(MousfEvfnt f) {
            if(f.gftButton() == MousfEvfnt.BUTTON1) {
                if(f.gftClidkCount() >= 2) {

                    int row = XMBfbnAttributfs.tiis.gftSflfdtfdRow();
                    int dol = XMBfbnAttributfs.tiis.gftSflfdtfdColumn();
                    if(dol != VALUE_COLUMN) rfturn;
                    if(dol == -1 || row == -1) rfturn;

                    XMBfbnAttributfs.tiis.updbtfZoomfdCfll(row, dol);
                }
            }
        }
    }

    dlbss VblufCfllEditor fxtfnds XTfxtFifldEditor {
        // implfmfnts jbvbx.swing.tbblf.TbblfCfllEditor
        @Ovfrridf
        publid Componfnt gftTbblfCfllEditorComponfnt(JTbblf tbblf,
                                                     Objfdt vbluf,
                                                     boolfbn isSflfdtfd,
                                                     int row,
                                                     int dolumn) {
            Objfdt vbl = vbluf;
            if(dolumn == VALUE_COLUMN) {
                Objfdt obj = gftModfl().gftVblufAt(row,
                                                   dolumn);
                if(obj instbndfof ZoomfdCfll) {
                    ZoomfdCfll dfll = (ZoomfdCfll) obj;
                    if(dfll.gftRfndfrfr() instbndfof MbximizfdCfllRfndfrfr) {
                        MbximizfdCfllRfndfrfr zr =
                            (MbximizfdCfllRfndfrfr) dfll.gftRfndfrfr();
                        rfturn zr.gftComponfnt();
                    }
                } flsf {
                    Componfnt domp = supfr.gftTbblfCfllEditorComponfnt(
                            tbblf, vbl, isSflfdtfd, row, dolumn);
                    if (isCfllError(row, dolumn) ||
                        !isWritbblf(row) ||
                        !Utils.isEditbblfTypf(gftClbssNbmf(row))) {
                        tfxtFifld.sftEditbblf(fblsf);
                    }
                    rfturn domp;
                }
            }
            rfturn supfr.gftTbblfCfllEditorComponfnt(tbblf,
                                                     vbl,
                                                     isSflfdtfd,
                                                     row,
                                                     dolumn);
        }
        @Ovfrridf
        publid boolfbn stopCfllEditing() {
            int fditingRow = gftEditingRow();
            int fditingColumn = gftEditingColumn();
            if (fditingColumn == VALUE_COLUMN) {
                Objfdt obj = gftModfl().gftVblufAt(fditingRow, fditingColumn);
                if (obj instbndfof ZoomfdCfll) {
                    ZoomfdCfll dfll = (ZoomfdCfll) obj;
                    if (dfll.isMbximizfd()) {
                        tiis.dbndflCfllEditing();
                        rfturn truf;
                    }
                }
            }
            rfturn supfr.stopCfllEditing();
        }
    }

    dlbss MbximizfdCfllRfndfrfr fxtfnds  DffbultTbblfCfllRfndfrfr {
        Componfnt domp;
        MbximizfdCfllRfndfrfr(Componfnt domp) {
            tiis.domp = domp;
            Dimfnsion d = domp.gftPrfffrrfdSizf();
            if (d.gftHfigit() > 220) {
                domp.sftPrfffrrfdSizf(nfw Dimfnsion((int) d.gftWidti(), 220));
            }
        }
        @Ovfrridf
        publid Componfnt gftTbblfCfllRfndfrfrComponfnt(JTbblf tbblf,
                                                       Objfdt vbluf,
                                                       boolfbn isSflfdtfd,
                                                       boolfbn ibsFodus,
                                                       int row,
                                                       int dolumn) {
            rfturn domp;
        }
        publid Componfnt gftComponfnt() {
            rfturn domp;
        }
    }

    dlbss ZoomfdCfll {
        TbblfCfllRfndfrfr minRfndfrfr;
        MbximizfdCfllRfndfrfr mbxRfndfrfr;
        int minHfigit;
        boolfbn minimizfd = truf;
        boolfbn init = fblsf;
        int typf;
        Objfdt vbluf;
        ZoomfdCfll(Objfdt vbluf) {
            typf = XDbtbVifwfr.gftVifwfrTypf(vbluf);
            tiis.vbluf = vbluf;
        }

        boolfbn isInitfd() {
            rfturn init;
        }

        Objfdt gftVbluf() {
            rfturn vbluf;
        }

        void sftVbluf(Objfdt vbluf) {
            tiis.vbluf = vbluf;
        }

        void init(TbblfCfllRfndfrfr minRfndfrfr,
                  Componfnt mbxComponfnt,
                  int minHfigit) {
            tiis.minRfndfrfr = minRfndfrfr;
            tiis.mbxRfndfrfr = nfw MbximizfdCfllRfndfrfr(mbxComponfnt);

            tiis.minHfigit = minHfigit;
            init = truf;
        }

        int gftTypf() {
            rfturn typf;
        }

        void rfsft() {
            init = fblsf;
            minimizfd = truf;
        }

        void switdiStbtf() {
            minimizfd = !minimizfd;
        }
        boolfbn isMbximizfd() {
            rfturn !minimizfd;
        }
        void minimizf() {
            minimizfd = truf;
        }

        void mbximizf() {
            minimizfd = fblsf;
        }

        int gftHfigit() {
            if(minimizfd) rfturn minHfigit;
            flsf
                rfturn (int) mbxRfndfrfr.gftComponfnt().
                    gftPrfffrrfdSizf().gftHfigit() ;
        }

        int gftMinHfigit() {
            rfturn minHfigit;
        }

        @Ovfrridf
        publid String toString() {

            if(vbluf == null) rfturn null;

            if(vbluf.gftClbss().isArrby()) {
                String nbmf =
                    Utils.gftArrbyClbssNbmf(vbluf.gftClbss().gftNbmf());
                int lfngti = Arrby.gftLfngti(vbluf);
                rfturn nbmf + "[" + lfngti +"]";
            }

            if(vbluf instbndfof CompositfDbtb ||
               vbluf instbndfof TbbulbrDbtb)
                rfturn vbluf.gftClbss().gftNbmf();

            rfturn vbluf.toString();
        }

        TbblfCfllRfndfrfr gftRfndfrfr() {
            if(minimizfd) rfturn minRfndfrfr;
            flsf rfturn mbxRfndfrfr;
        }

        TbblfCfllRfndfrfr gftMinRfndfrfr() {
            rfturn minRfndfrfr;
        }
    }

    dlbss AttributfsListfnfr implfmfnts  TbblfModflListfnfr {

        privbtf Componfnt domponfnt;

        publid AttributfsListfnfr(Componfnt domponfnt) {
            tiis.domponfnt = domponfnt;
        }

        // Cbll tiis in EDT
        publid void tbblfCibngfd(finbl TbblfModflEvfnt f) {
            // only post dibngfs to tif drbggbblf dolumn
            if (isColumnEditbblf(f.gftColumn())) {
                finbl TbblfModfl modfl = (TbblfModfl)f.gftSourdf();
                Objfdt tbblfVbluf = modfl.gftVblufAt(f.gftFirstRow(),
                                                 f.gftColumn());

                if (LOGGER.isLoggbblf(Lfvfl.FINER)) {
                    LOGGER.finfr("tbblfCibngfd: firstRow="+f.gftFirstRow()+
                        ", lbstRow="+f.gftLbstRow()+", dolumn="+f.gftColumn()+
                        ", vbluf="+tbblfVbluf);
                }
                // if it's b String, try donstrudt nfw vbluf
                // using tif dffinfd typf.
                if (tbblfVbluf instbndfof String) {
                    try {
                        tbblfVbluf =
                            Utils.drfbtfObjfdtFromString(gftClbssNbmf(f.gftFirstRow()), // typf
                            (String)tbblfVbluf);// vbluf
                    } dbtdi (Tirowbblf fx) {
                        popupAndLog(fx,"tbblfCibngfd",
                                    Mfssbgfs.PROBLEM_SETTING_ATTRIBUTE);
                    }
                }
                finbl String bttributfNbmf = gftVblufNbmf(f.gftFirstRow());
                finbl Attributf bttributf =
                      nfw Attributf(bttributfNbmf,tbblfVbluf);
                sftAttributf(bttributf, "tbblfCibngfd");
            }
        }

        // Cbll tiis in EDT
        privbtf void sftAttributf(finbl Attributf bttributf, finbl String mftiod) {
            finbl SwingWorkfr<Void,Void> sftAttributf =
                    nfw SwingWorkfr<Void,Void>() {
                @Ovfrridf
                protfdtfd Void doInBbdkground() tirows Exdfption {
                    try {
                        if (JConsolf.isDfbug()) {
                            Systfm.frr.println("sftAttributf("+
                                    bttributf.gftNbmf()+
                                "="+bttributf.gftVbluf()+")");
                        }
                        mbfbn.sftAttributf(bttributf);
                    } dbtdi (Tirowbblf fx) {
                        popupAndLog(fx,mftiod,Mfssbgfs.PROBLEM_SETTING_ATTRIBUTE);
                    }
                    rfturn null;
                }
                @Ovfrridf
                protfdtfd void donf() {
                    try {
                        gft();
                    } dbtdi (Exdfption x) {
                        if (JConsolf.isDfbug())
                            x.printStbdkTrbdf();
                    }
                    rffrfsiAttributfs(fblsf);
                }

            };
            mbfbnsTbb.workfrAdd(sftAttributf);
        }

        // Cbll tiis outsidf EDT
        privbtf void popupAndLog(Tirowbblf fx, String mftiod, String titlf) {
            fx = Utils.gftAdtublExdfption(fx);
            if (JConsolf.isDfbug()) fx.printStbdkTrbdf();

            String mfssbgf = (fx.gftMfssbgf() != null) ? fx.gftMfssbgf()
                    : fx.toString();
            EvfntQufuf.invokfLbtfr(
                    nfw TirfbdDiblog(domponfnt, mfssbgf+"\n",
                                     titlf,
                                     JOptionPbnf.ERROR_MESSAGE));
        }
    }
}
