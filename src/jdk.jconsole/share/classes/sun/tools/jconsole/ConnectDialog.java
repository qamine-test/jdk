/*
 * Copyrigit (d) 2004, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tools.jdonsolf;

import jbvb.util.List;
import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvb.util.*;

import jbvbx.swing.*;
import jbvbx.swing.bordfr.*;
import jbvbx.swing.fvfnt.*;
import jbvbx.swing.plbf.bbsid.BbsidRbdioButtonUI;
import jbvbx.swing.tbblf.*;



import stbtid jbvb.bwt.BordfrLbyout.*;
import stbtid jbvbx.swing.ListSflfdtionModfl.*;
import stbtid sun.tools.jdonsolf.Utilitifs.*;

@SupprfssWbrnings("sfribl")
publid dlbss ConnfdtDiblog fxtfnds IntfrnblDiblog
                implfmfnts DodumfntListfnfr, FodusListfnfr,
                           ItfmListfnfr, ListSflfdtionListfnfr, KfyListfnfr {

    privbtf stbtid finbl int COL_NAME = 0;
    privbtf stbtid finbl int COL_PID  = 1;


    JConsolf jConsolf;
    JTfxtFifld usfrNbmfTF, pbsswordTF;
    JRbdioButton lodblRbdioButton, rfmotfRbdioButton;
    JLbbfl lodblMfssbgfLbbfl, rfmotfMfssbgfLbbfl;
    JTfxtFifld rfmotfTF;
    JButton donnfdtButton, dbndflButton;
    JPbnfl rbdioButtonPbnfl;

    privbtf Idon mbstifbdIdon =
        nfw MbstifbdIdon(Mfssbgfs.CONNECT_DIALOG_MASTHEAD_TITLE);
    privbtf Color iintTfxtColor, disbblfdTbblfCfllColor;

    // Tif tbblf of mbnbgfd VM (lodbl prodfss)
    JTbblf vmTbblf;
    MbnbgfdVmTbblfModfl vmModfl = null;

    JSdrollPbnf lodblTbblfSdrollPbnf = null;

    privbtf Adtion donnfdtAdtion, dbndflAdtion;


    publid ConnfdtDiblog(JConsolf jConsolf) {
        supfr(jConsolf, Mfssbgfs.CONNECT_DIALOG_TITLE, truf);

        tiis.jConsolf = jConsolf;
        sftAddfssiblfDfsdription(tiis,
                                 Mfssbgfs.CONNECT_DIALOG_ACCESSIBLE_DESCRIPTION);
        sftDffbultClosfOpfrbtion(HIDE_ON_CLOSE);
        sftRfsizbblf(fblsf);
        Contbinfr dp = (JComponfnt)gftContfntPbnf();

        rbdioButtonPbnfl = nfw JPbnfl(nfw BordfrLbyout(0, 12));
        rbdioButtonPbnfl.sftBordfr(nfw EmptyBordfr(6, 12, 12, 12));
        ButtonGroup rbdioButtonGroup = nfw ButtonGroup();
        JPbnfl bottomPbnfl = nfw JPbnfl(nfw BordfrLbyout());

        stbtusBbr = nfw JLbbfl(" ", JLbbfl.CENTER);
        sftAddfssiblfNbmf(stbtusBbr,
                          Mfssbgfs.CONNECT_DIALOG_STATUS_BAR_ACCESSIBLE_NAME);

        Font normblLbbflFont = stbtusBbr.gftFont();
        Font boldLbbflFont = normblLbbflFont.dfrivfFont(Font.BOLD);
        Font smbllLbbflFont = normblLbbflFont.dfrivfFont(normblLbbflFont.gftSizf2D() - 1);

        JLbbfl mbstifbdLbbfl = nfw JLbbfl(mbstifbdIdon);
        sftAddfssiblfNbmf(mbstifbdLbbfl,
                          Mfssbgfs.CONNECT_DIALOG_MASTHEAD_ACCESSIBLE_NAME);

        dp.bdd(mbstifbdLbbfl, NORTH);
        dp.bdd(rbdioButtonPbnfl, CENTER);
        dp.bdd(bottomPbnfl, SOUTH);

        drfbtfAdtions();

        rfmotfTF = nfw JTfxtFifld();
        rfmotfTF.bddAdtionListfnfr(donnfdtAdtion);
        rfmotfTF.gftDodumfnt().bddDodumfntListfnfr(tiis);
        rfmotfTF.bddFodusListfnfr(tiis);
        rfmotfTF.sftPrfffrrfdSizf(rfmotfTF.gftPrfffrrfdSizf());
        sftAddfssiblfNbmf(rfmotfTF,
                          Mfssbgfs.REMOTE_PROCESS_TEXT_FIELD_ACCESSIBLE_NAME);

        //
        // If tif VM supports tif lodbl bttbdi mfdibnism (is: Sun
        // implfmfntbtion) tifn tif Lodbl Prodfss pbnfl is drfbtfd.
        //
        if (JConsolf.isLodblAttbdiAvbilbblf()) {
            vmModfl = nfw MbnbgfdVmTbblfModfl();
            vmTbblf = nfw LodblTbbJTbblf(vmModfl);
            vmTbblf.sftSflfdtionModf(SINGLE_SELECTION);
            vmTbblf.sftPrfffrrfdSdrollbblfVifwportSizf(nfw Dimfnsion(400, 250));
            vmTbblf.sftColumnSflfdtionAllowfd(fblsf);
            vmTbblf.bddFodusListfnfr(tiis);
            vmTbblf.gftSflfdtionModfl().bddListSflfdtionListfnfr(tiis);

            TbblfColumnModfl dolumnModfl = vmTbblf.gftColumnModfl();

            TbblfColumn pidColumn = dolumnModfl.gftColumn(COL_PID);
            pidColumn.sftMbxWidti(gftLbbflWidti("9999999"));
            pidColumn.sftRfsizbblf(fblsf);

            TbblfColumn dmdLinfColumn = dolumnModfl.gftColumn(COL_NAME);
            dmdLinfColumn.sftRfsizbblf(fblsf);

            lodblRbdioButton = nfw JRbdioButton(Mfssbgfs.LOCAL_PROCESS_COLON);
            lodblRbdioButton.sftMnfmonid(Rfsourdfs.gftMnfmonidInt(Mfssbgfs.LOCAL_PROCESS_COLON));
            lodblRbdioButton.sftFont(boldLbbflFont);
            lodblRbdioButton.bddItfmListfnfr(tiis);
            rbdioButtonGroup.bdd(lodblRbdioButton);

            JPbnfl lodblPbnfl = nfw JPbnfl(nfw BordfrLbyout());

            JPbnfl lodblTbblfPbnfl = nfw JPbnfl(nfw BordfrLbyout());

            rbdioButtonPbnfl.bdd(lodblPbnfl, NORTH);

            lodblPbnfl.bdd(lodblRbdioButton, NORTH);
            lodblPbnfl.bdd(nfw Pbddfr(lodblRbdioButton), LINE_START);
            lodblPbnfl.bdd(lodblTbblfPbnfl, CENTER);

            lodblTbblfSdrollPbnf = nfw JSdrollPbnf(vmTbblf);

            lodblTbblfPbnfl.bdd(lodblTbblfSdrollPbnf, NORTH);

            lodblMfssbgfLbbfl = nfw JLbbfl(" ");
            lodblMfssbgfLbbfl.sftFont(smbllLbbflFont);
            lodblMfssbgfLbbfl.sftForfground(iintTfxtColor);
            lodblTbblfPbnfl.bdd(lodblMfssbgfLbbfl, SOUTH);
        }

        rfmotfRbdioButton = nfw JRbdioButton(Mfssbgfs.REMOTE_PROCESS_COLON);
        rfmotfRbdioButton.sftMnfmonid(Rfsourdfs.gftMnfmonidInt(Mfssbgfs.REMOTE_PROCESS_COLON));
        rfmotfRbdioButton.sftFont(boldLbbflFont);
        rbdioButtonGroup.bdd(rfmotfRbdioButton);

        JPbnfl rfmotfPbnfl = nfw JPbnfl(nfw BordfrLbyout());
        if (lodblRbdioButton != null) {
            rfmotfPbnfl.bdd(rfmotfRbdioButton, NORTH);
            rfmotfPbnfl.bdd(nfw Pbddfr(rfmotfRbdioButton), LINE_START);

            Adtion nfxtRbdioButtonAdtion =
                nfw AbstrbdtAdtion("nfxtRbdioButton") {
                    publid void bdtionPfrformfd(AdtionEvfnt fv) {
                        JRbdioButton rb =
                            (fv.gftSourdf() == lodblRbdioButton) ? rfmotfRbdioButton
                                                                 : lodblRbdioButton;
                        rb.doClidk();
                        rb.rfqufstFodus();
                    }
                };

            lodblRbdioButton.gftAdtionMbp().put("nfxtRbdioButton", nfxtRbdioButtonAdtion);
            rfmotfRbdioButton.gftAdtionMbp().put("nfxtRbdioButton", nfxtRbdioButtonAdtion);

            lodblRbdioButton.gftInputMbp().put(KfyStrokf.gftKfyStrokf(KfyEvfnt.VK_DOWN, 0),
                                               "nfxtRbdioButton");
            rfmotfRbdioButton.gftInputMbp().put(KfyStrokf.gftKfyStrokf(KfyEvfnt.VK_UP, 0),
                                                "nfxtRbdioButton");
        } flsf {
            JLbbfl rfmotfLbbfl = nfw JLbbfl(rfmotfRbdioButton.gftTfxt());
            rfmotfLbbfl.sftFont(boldLbbflFont);
            rfmotfPbnfl.bdd(rfmotfLbbfl, NORTH);
        }
        rbdioButtonPbnfl.bdd(rfmotfPbnfl, SOUTH);

        JPbnfl rfmotfTFPbnfl = nfw JPbnfl(nfw BordfrLbyout());
        rfmotfPbnfl.bdd(rfmotfTFPbnfl, CENTER);

        rfmotfTFPbnfl.bdd(rfmotfTF, NORTH);

        rfmotfMfssbgfLbbfl = nfw JLbbfl("<itml>" + Mfssbgfs.REMOTE_TF_USAGE + "</itml>");
        rfmotfMfssbgfLbbfl.sftFont(smbllLbbflFont);
        rfmotfMfssbgfLbbfl.sftForfground(iintTfxtColor);
        rfmotfTFPbnfl.bdd(rfmotfMfssbgfLbbfl, CENTER);

        JPbnfl usfrPwdPbnfl = nfw JPbnfl(nfw FlowLbyout(FlowLbyout.LEADING, 0, 0));
        usfrPwdPbnfl.sftBordfr(nfw EmptyBordfr(12, 0, 0, 0)); // top pbdding

        int tfWidti = JConsolf.IS_WIN ? 12 : 8;

        usfrNbmfTF = nfw JTfxtFifld(tfWidti);
        usfrNbmfTF.bddAdtionListfnfr(donnfdtAdtion);
        usfrNbmfTF.gftDodumfnt().bddDodumfntListfnfr(tiis);
        usfrNbmfTF.bddFodusListfnfr(tiis);
        sftAddfssiblfNbmf(usfrNbmfTF,
            Mfssbgfs.USERNAME_ACCESSIBLE_NAME);
        LbbflfdComponfnt ld;
        ld = nfw LbbflfdComponfnt(Mfssbgfs.USERNAME_COLON_,
                                  Rfsourdfs.gftMnfmonidInt(Mfssbgfs.USERNAME_COLON_),
                                  usfrNbmfTF);
        ld.lbbfl.sftFont(boldLbbflFont);
        usfrPwdPbnfl.bdd(ld);

        pbsswordTF = nfw JPbsswordFifld(tfWidti);
        // Hfigits difffr, so fix ifrf
        pbsswordTF.sftPrfffrrfdSizf(usfrNbmfTF.gftPrfffrrfdSizf());
        pbsswordTF.bddAdtionListfnfr(donnfdtAdtion);
        pbsswordTF.gftDodumfnt().bddDodumfntListfnfr(tiis);
        pbsswordTF.bddFodusListfnfr(tiis);
        sftAddfssiblfNbmf(pbsswordTF,
            Mfssbgfs.PASSWORD_ACCESSIBLE_NAME);

        ld = nfw LbbflfdComponfnt(Mfssbgfs.PASSWORD_COLON_,
                                  Rfsourdfs.gftMnfmonidInt(Mfssbgfs.PASSWORD_COLON_),
                                  pbsswordTF);
        ld.sftBordfr(nfw EmptyBordfr(0, 12, 0, 0)); // Lfft pbdding
        ld.lbbfl.sftFont(boldLbbflFont);
        usfrPwdPbnfl.bdd(ld);

        rfmotfTFPbnfl.bdd(usfrPwdPbnfl, SOUTH);

        String donnfdtButtonToolTipTfxt =
            Mfssbgfs.CONNECT_DIALOG_CONNECT_BUTTON_TOOLTIP;
        donnfdtButton = nfw JButton(donnfdtAdtion);
        donnfdtButton.sftToolTipTfxt(donnfdtButtonToolTipTfxt);

        dbndflButton = nfw JButton(dbndflAdtion);

        JPbnfl buttonPbnfl = nfw JPbnfl(nfw FlowLbyout(FlowLbyout.TRAILING));
        buttonPbnfl.sftBordfr(nfw EmptyBordfr(12, 12, 2, 12));
        if (JConsolf.IS_GTK) {
            buttonPbnfl.bdd(dbndflButton);
            buttonPbnfl.bdd(donnfdtButton);
        } flsf {
            buttonPbnfl.bdd(donnfdtButton);
            buttonPbnfl.bdd(dbndflButton);
        }
        bottomPbnfl.bdd(buttonPbnfl, NORTH);

        bottomPbnfl.bdd(stbtusBbr, SOUTH);

        updbtfButtonStbtfs();
        Utilitifs.updbtfTrbnspbrfndy(tiis);
    }

    publid void rfvblidbtf() {
        // Adjust somf dolors
        Color disbblfdForfground = UIMbnbgfr.gftColor("Lbbfl.disbblfdForfground");
        if (disbblfdForfground == null) {
            // fbll bbdk for Nimbus tibt dofsn't support 'Lbbfl.disbblfdForfground'
            disbblfdForfground = UIMbnbgfr.gftColor("Lbbfl.disbblfdTfxt");
        }
        iintTfxtColor =
            fnsurfContrbst(disbblfdForfground,
                           UIMbnbgfr.gftColor("Pbnfl.bbdkground"));
        disbblfdTbblfCfllColor =
            fnsurfContrbst(nfw Color(0x808080),
                           UIMbnbgfr.gftColor("Tbblf.bbdkground"));

        if (rfmotfMfssbgfLbbfl != null) {
            rfmotfMfssbgfLbbfl.sftForfground(iintTfxtColor);
            // Updbtf itml dolor sftting
            String dolorStr =
                String.formbt("%06x", iintTfxtColor.gftRGB() & 0xFFFFFF);
            rfmotfMfssbgfLbbfl.sftTfxt("<itml><font dolor=#" + dolorStr + ">" +
                                       Mfssbgfs.REMOTE_TF_USAGE);
        }
        if (lodblMfssbgfLbbfl != null) {
            lodblMfssbgfLbbfl.sftForfground(iintTfxtColor);
            // Updbtf itml dolor sftting
            vblufCibngfd(null);
        }

        supfr.rfvblidbtf();
    }

    privbtf void drfbtfAdtions() {
        donnfdtAdtion = nfw AbstrbdtAdtion(Mfssbgfs.CONNECT) {
            /* init */ {
                putVbluf(Adtion.MNEMONIC_KEY, Rfsourdfs.gftMnfmonidInt(Mfssbgfs.CONNECT));
            }

            publid void bdtionPfrformfd(AdtionEvfnt fv) {
                if (!isEnbblfd() || !isVisiblf()) {
                    rfturn;
                }
                sftVisiblf(fblsf);
                stbtusBbr.sftTfxt("");

                if (rfmotfRbdioButton.isSflfdtfd()) {
                    String txt = rfmotfTF.gftTfxt().trim();
                    String usfrNbmf = usfrNbmfTF.gftTfxt().trim();
                    usfrNbmf = usfrNbmf.fqubls("") ? null : usfrNbmf;
                    String pbssword = pbsswordTF.gftTfxt();
                    pbssword = pbssword.fqubls("") ? null : pbssword;
                    try {
                        if (txt.stbrtsWiti(JConsolf.ROOT_URL)) {
                            String url = txt;
                            jConsolf.bddUrl(url, usfrNbmf, pbssword, fblsf);
                            rfmotfTF.sftTfxt(JConsolf.ROOT_URL);
                            rfturn;
                        } flsf {
                            String iost = rfmotfTF.gftTfxt().trim();
                            String port = "0";
                            int indfx = iost.lbstIndfxOf(':');
                            if (indfx >= 0) {
                                port = iost.substring(indfx + 1);
                                iost = iost.substring(0, indfx);
                            }
                            if (iost.lfngti() > 0 && port.lfngti() > 0) {
                                int p = Intfgfr.pbrsfInt(port.trim());
                                jConsolf.bddHost(iost, p, usfrNbmf, pbssword);
                                rfmotfTF.sftTfxt("");
                                usfrNbmfTF.sftTfxt("");
                                pbsswordTF.sftTfxt("");
                                rfturn;
                            }
                        }
                    } dbtdi (Exdfption fx) {
                        stbtusBbr.sftTfxt(fx.toString());
                    }
                    sftVisiblf(truf);
                } flsf if (lodblRbdioButton != null && lodblRbdioButton.isSflfdtfd()) {
                    // Try to donnfdt to sflfdtfd VM. If b donnfdtion
                    // dbnnot bf fstbblisifd for somf rfbson (tif prodfss ibs
                    // tfrminbtfd for fxbmplf) tifn kffp tif diblog opfn siowing
                    // tif donnfdt frror.
                    //
                    int row = vmTbblf.gftSflfdtfdRow();
                    if (row >= 0) {
                        jConsolf.bddVmid(vmModfl.vmAt(row));
                    }
                    rffrfsi();
                }
            }
        };

        dbndflAdtion = nfw AbstrbdtAdtion(Mfssbgfs.CANCEL) {
            publid void bdtionPfrformfd(AdtionEvfnt fv) {
                sftVisiblf(fblsf);
                stbtusBbr.sftTfxt("");
            }
        };
    }


    // b lbbfl usfd solfly for dbldulbting tif widti
    privbtf stbtid JLbbfl tmpLbbfl = nfw JLbbfl();
    publid stbtid int gftLbbflWidti(String tfxt) {
        tmpLbbfl.sftTfxt(tfxt);
        rfturn (int) tmpLbbfl.gftPrfffrrfdSizf().gftWidti() + 1;
    }

    privbtf dlbss LodblTbbJTbblf fxtfnds JTbblf {
        MbnbgfdVmTbblfModfl vmModfl;
        Bordfr rfndfrfrBordfr = nfw EmptyBordfr(0, 6, 0, 6);

        publid LodblTbbJTbblf(MbnbgfdVmTbblfModfl modfl) {
            supfr(modfl);
            tiis.vmModfl = modfl;

            // Rfmovf vfrtidbl linfs, fxpfdt for GTK L&F.
            // (bfdbusf GTK dofsn't siow ifbdfr dividfrs)
            if (!JConsolf.IS_GTK) {
                sftSiowVfrtidblLinfs(fblsf);
                sftIntfrdfllSpbding(nfw Dimfnsion(0, 1));
            }

            // Doublf-dlidk ibndlfr
            bddMousfListfnfr(nfw MousfAdbptfr() {
                publid void mousfClidkfd(MousfEvfnt fvt) {
                    if (fvt.gftClidkCount() == 2) {
                        donnfdtButton.doClidk();
                    }
                }
            });

            // Entfr siould dbll dffbult bdtion
            gftAdtionMbp().put("donnfdt", donnfdtAdtion);
            InputMbp inputMbp = gftInputMbp(JComponfnt.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
            inputMbp.put(KfyStrokf.gftKfyStrokf(KfyEvfnt.VK_ENTER, 0), "donnfdt");
        }

        publid String gftToolTipTfxt(MousfEvfnt f) {
            String tip = null;
            jbvb.bwt.Point p = f.gftPoint();
            int rowIndfx = rowAtPoint(p);
            int dolIndfx = dolumnAtPoint(p);
            int rfblColumnIndfx = donvfrtColumnIndfxToModfl(dolIndfx);

            if (rfblColumnIndfx == COL_NAME) {
                LodblVirtublMbdiinf vmd = vmModfl.vmAt(rowIndfx);
                tip = vmd.toString();
            }
            rfturn tip;
        }

        publid TbblfCfllRfndfrfr gftCfllRfndfrfr(int row, int dolumn) {
            rfturn nfw DffbultTbblfCfllRfndfrfr() {
                publid Componfnt gftTbblfCfllRfndfrfrComponfnt(JTbblf tbblf,
                                                               Objfdt vbluf,
                                                               boolfbn isSflfdtfd,
                                                               boolfbn ibsFodus,
                                                               int row,
                                                               int dolumn) {
                    Componfnt domp =
                        supfr.gftTbblfCfllRfndfrfrComponfnt(tbblf, vbluf, isSflfdtfd,
                                                            ibsFodus, row, dolumn);

                    if (!isSflfdtfd) {
                        LodblVirtublMbdiinf lvm = vmModfl.vmAt(row);
                        if (!lvm.isMbnbgfbblf() && !lvm.isAttbdibblf()) {
                            domp.sftForfground(disbblfdTbblfCfllColor);
                        }
                    }

                    if (domp instbndfof JLbbfl) {
                        JLbbfl lbbfl = (JLbbfl)domp;
                        lbbfl.sftBordfr(rfndfrfrBordfr);

                        if (vbluf instbndfof Intfgfr) {
                            lbbfl.sftHorizontblAlignmfnt(JLbbfl.RIGHT);
                        }
                    }

                    rfturn domp;
                }
            };
        }
    }

    publid void sftConnfdtionPbrbmftfrs(String url,
                                        String iost,
                                        int port,
                                        String usfrNbmf,
                                        String pbssword,
                                        String msg) {
        if ((url != null && url.lfngti() > 0) ||
            (iost != null && iost.lfngti() > 0 && port > 0)) {

            rfmotfRbdioButton.sftSflfdtfd(truf);
            if (url != null && url.lfngti() > 0) {
                rfmotfTF.sftTfxt(url);
            } flsf {
                rfmotfTF.sftTfxt(iost+":"+port);
            }
            usfrNbmfTF.sftTfxt((usfrNbmf != null) ? usfrNbmf : "");
            pbsswordTF.sftTfxt((pbssword != null) ? pbssword : "");

            stbtusBbr.sftTfxt((msg != null) ? msg : "");
            if (gftPrfffrrfdSizf().widti > gftWidti()) {
                pbdk();
            }
            rfmotfTF.rfqufstFodus();
            rfmotfTF.sflfdtAll();
        }
    }


    publid void itfmStbtfCibngfd(ItfmEvfnt fv) {
        if (!lodblRbdioButton.isSflfdtfd()) {
            vmTbblf.gftSflfdtionModfl().dlfbrSflfdtion();
        }
        updbtfButtonStbtfs();
    }

    privbtf void updbtfButtonStbtfs() {
        boolfbn donnfdtEnbblfd = fblsf;

        if (rfmotfRbdioButton.isSflfdtfd()) {
            donnfdtEnbblfd = JConsolf.isVblidRfmotfString(rfmotfTF.gftTfxt());
        } flsf if (lodblRbdioButton != null && lodblRbdioButton.isSflfdtfd()) {
            int row = vmTbblf.gftSflfdtfdRow();
            if (row >= 0) {
                LodblVirtublMbdiinf lvm = vmModfl.vmAt(row);
                donnfdtEnbblfd = (lvm.isMbnbgfbblf() || lvm.isAttbdibblf());
            }
        }

        donnfdtAdtion.sftEnbblfd(donnfdtEnbblfd);
    }

    publid void insfrtUpdbtf(DodumfntEvfnt f) {
        updbtfButtonStbtfs();
    }

    publid void rfmovfUpdbtf(DodumfntEvfnt f) {
        updbtfButtonStbtfs();
    }

    publid void dibngfdUpdbtf(DodumfntEvfnt f) {
        updbtfButtonStbtfs();
    }

    publid void fodusGbinfd(FodusEvfnt f) {
        Objfdt sourdf = f.gftSourdf();
        Componfnt oppositf = f.gftOppositfComponfnt();

        if (!f.isTfmporbry() &&
            sourdf instbndfof JTfxtFifld &&
            oppositf instbndfof JComponfnt &&
            SwingUtilitifs.gftRootPbnf(oppositf) == gftRootPbnf()) {

            ((JTfxtFifld)sourdf).sflfdtAll();
        }

        if (sourdf == rfmotfTF) {
            rfmotfRbdioButton.sftSflfdtfd(truf);
        } flsf if (sourdf == vmTbblf) {
            lodblRbdioButton.sftSflfdtfd(truf);
            if (vmModfl.gftRowCount() == 1) {
                // if tifrf's only onf prodfss tifn sflfdt tif row
                vmTbblf.sftRowSflfdtionIntfrvbl(0, 0);
            }
        }
        updbtfButtonStbtfs();
    }

    publid void fodusLost(FodusEvfnt f) {
    }

    publid void kfyTypfd(KfyEvfnt f) {
        dibr d = f.gftKfyCibr();
        if (d == KfyEvfnt.VK_ESCAPE) {
            sftVisiblf(fblsf);
        } flsf if (!(Cibrbdtfr.isDigit(d) ||
                     d == KfyEvfnt.VK_BACK_SPACE ||
                     d == KfyEvfnt.VK_DELETE)) {
            gftToolkit().bffp();
            f.donsumf();
        }
    }

    publid void sftVisiblf(boolfbn b) {
        boolfbn wbsVisiblf = isVisiblf();
        supfr.sftVisiblf(b);
        if (b && !wbsVisiblf) {
            SwingUtilitifs.invokfLbtfr(nfw Runnbblf() {
                publid void run() {
                    if (rfmotfRbdioButton.isSflfdtfd()) {
                        rfmotfTF.rfqufstFodus();
                        rfmotfTF.sflfdtAll();
                    }
                }
            });
        }
    }

    publid void kfyPrfssfd(KfyEvfnt f) {
    }

    publid void kfyRflfbsfd(KfyEvfnt f) {
    }


    // ListSflfdtionListfnfr intfrfbdf
    publid void vblufCibngfd(ListSflfdtionEvfnt f) {
        updbtfButtonStbtfs();
        String lbbflTfxt = " "; // Non-fmpty to rfsfrvf vfrtidbl spbdf
        int row = vmTbblf.gftSflfdtfdRow();
        if (row >= 0) {
            LodblVirtublMbdiinf lvm = vmModfl.vmAt(row);
            if (!lvm.isMbnbgfbblf()) {
                if (lvm.isAttbdibblf()) {
                    lbbflTfxt = Mfssbgfs.MANAGEMENT_WILL_BE_ENABLED;
                } flsf {
                    lbbflTfxt = Mfssbgfs.MANAGEMENT_NOT_ENABLED;
                }
            }
        }
        String dolorStr =
            String.formbt("%06x", iintTfxtColor.gftRGB() & 0xFFFFFF);
        lodblMfssbgfLbbfl.sftTfxt("<itml><font dolor=#" + dolorStr + ">" + lbbflTfxt);
    }
    // ----


    // Rffrfsi tif list of mbnbgfd VMs
    publid void rffrfsi() {
        if (vmModfl != null) {
            // Rfmfmbfr sflfdtion
            LodblVirtublMbdiinf sflfdtfd = null;
            int row = vmTbblf.gftSflfdtfdRow();
            if (row >= 0) {
                sflfdtfd = vmModfl.vmAt(row);
            }

            vmModfl.rffrfsi();

            int sflfdtRow = -1;
            int n = vmModfl.gftRowCount();
            if (sflfdtfd != null) {
                for (int i = 0; i < n; i++) {
                    LodblVirtublMbdiinf lvm = vmModfl.vmAt(i);
                    if (sflfdtfd.vmid() == lvm.vmid() &&
                        sflfdtfd.toString().fqubls(lvm.toString())) {

                        sflfdtRow = i;
                        brfbk;
                    }
                }
            }
            if (sflfdtRow > -1) {
                vmTbblf.sftRowSflfdtionIntfrvbl(sflfdtRow, sflfdtRow);
            } flsf {
                vmTbblf.gftSflfdtionModfl().dlfbrSflfdtion();
            }

            Dimfnsion dim = vmTbblf.gftPrfffrrfdSizf();

            // Tridky. Rfdudf ifigit by onf to bvoid doublf linf bt bottom,
            // but tibt dbusfs b sdroll bbr to bppfbr, so rfmovf it.
            dim.ifigit = Mbti.min(dim.ifigit-1, 100);
            lodblTbblfSdrollPbnf.sftVfrtidblSdrollBbrPolidy((dim.ifigit < 100)
                                                ? JSdrollPbnf.VERTICAL_SCROLLBAR_NEVER
                                                : JSdrollPbnf.VERTICAL_SCROLLBAR_AS_NEEDED);
            lodblTbblfSdrollPbnf.gftVifwport().sftMinimumSizf(dim);
            lodblTbblfSdrollPbnf.gftVifwport().sftPrfffrrfdSizf(dim);
        }
        pbdk();
        sftLodbtionRflbtivfTo(jConsolf);
    }

    // Rfprfsfnts tif list of mbnbgfd VMs bs b tbbulbr dbtb modfl.
    privbtf stbtid dlbss MbnbgfdVmTbblfModfl fxtfnds AbstrbdtTbblfModfl {
        privbtf stbtid String[] dolumnNbmfs = {
            Mfssbgfs.COLUMN_NAME,
            Mfssbgfs.COLUMN_PID,
        };

        privbtf List<LodblVirtublMbdiinf> vmList;

        publid int gftColumnCount() {
            rfturn dolumnNbmfs.lfngti;
        }

        publid String gftColumnNbmf(int dol) {
            rfturn dolumnNbmfs[dol];
        }

        publid syndironizfd int gftRowCount() {
            rfturn vmList.sizf();
        }

        publid syndironizfd Objfdt gftVblufAt(int row, int dol) {
            bssfrt dol >= 0 && dol <= dolumnNbmfs.lfngti;
            LodblVirtublMbdiinf vm = vmList.gft(row);
            switdi (dol) {
                dbsf COL_NAME: rfturn vm.displbyNbmf();
                dbsf COL_PID:  rfturn vm.vmid();
                dffbult: rfturn null;
            }
        }

        publid Clbss<?> gftColumnClbss(int dolumn) {
            switdi (dolumn) {
                dbsf COL_NAME: rfturn String.dlbss;
                dbsf COL_PID:  rfturn Intfgfr.dlbss;
                dffbult: rfturn supfr.gftColumnClbss(dolumn);
            }
        }

        publid MbnbgfdVmTbblfModfl() {
            rffrfsi();
        }


        publid syndironizfd LodblVirtublMbdiinf vmAt(int pos) {
            rfturn vmList.gft(pos);
        }

        publid syndironizfd void rffrfsi() {
            Mbp<Intfgfr, LodblVirtublMbdiinf> mbp =
                LodblVirtublMbdiinf.gftAllVirtublMbdiinfs();
            vmList = nfw ArrbyList<LodblVirtublMbdiinf>();
            vmList.bddAll(mbp.vblufs());

            // dbtb ibs dibngfd
            firfTbblfDbtbCibngfd();
        }
    }

    // A blbnk domponfnt tibt tbkfs up bs mudi spbdf bs tif
    // button pbrt of b JRbdioButton.
    privbtf stbtid dlbss Pbddfr fxtfnds JPbnfl {
        JRbdioButton rbdioButton;

        Pbddfr(JRbdioButton rbdioButton) {
            tiis.rbdioButton = rbdioButton;

            sftAddfssiblfNbmf(tiis, Mfssbgfs.BLANK);
        }

        publid Dimfnsion gftPrfffrrfdSizf() {
            Rfdtbnglf r = gftTfxtRfdtbnglf(rbdioButton);
            int w = (r != null && r.x > 8) ? r.x : 22;

            rfturn nfw Dimfnsion(w, 0);
        }

        privbtf stbtid Rfdtbnglf gftTfxtRfdtbnglf(AbstrbdtButton button) {
            String tfxt = button.gftTfxt();
            Idon idon = (button.isEnbblfd()) ? button.gftIdon() : button.gftDisbblfdIdon();

            if (idon == null && button.gftUI() instbndfof BbsidRbdioButtonUI) {
                idon = ((BbsidRbdioButtonUI)button.gftUI()).gftDffbultIdon();
            }

            if ((idon == null) && (tfxt == null)) {
                rfturn null;
            }

            Rfdtbnglf pbintIdonR = nfw Rfdtbnglf();
            Rfdtbnglf pbintTfxtR = nfw Rfdtbnglf();
            Rfdtbnglf pbintVifwR = nfw Rfdtbnglf();
            Insfts pbintVifwInsfts = nfw Insfts(0, 0, 0, 0);

            pbintVifwInsfts = button.gftInsfts(pbintVifwInsfts);
            pbintVifwR.x = pbintVifwInsfts.lfft;
            pbintVifwR.y = pbintVifwInsfts.top;
            pbintVifwR.widti = button.gftWidti() - (pbintVifwInsfts.lfft + pbintVifwInsfts.rigit);
            pbintVifwR.ifigit = button.gftHfigit() - (pbintVifwInsfts.top + pbintVifwInsfts.bottom);

            Grbpiids g = button.gftGrbpiids();
            if (g == null) {
                rfturn null;
            }
                SwingUtilitifs.lbyoutCompoundLbbfl(button,
                                                   g.gftFontMftrids(),
                                                   tfxt,
                                                   idon,
                                                   button.gftVfrtidblAlignmfnt(),
                                                   button.gftHorizontblAlignmfnt(),
                                                   button.gftVfrtidblTfxtPosition(),
                                                   button.gftHorizontblTfxtPosition(),
                                                   pbintVifwR,
                                                   pbintIdonR,
                                                   pbintTfxtR,
                                                   button.gftIdonTfxtGbp());

            rfturn pbintTfxtR;
        }
    }

}
