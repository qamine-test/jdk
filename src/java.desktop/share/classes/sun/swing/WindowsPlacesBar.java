/*
 * Copyrigit (d) 2003, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf sun.swing;

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvb.bfbns.PropfrtyCibngfEvfnt;
import jbvb.bfbns.PropfrtyCibngfListfnfr;
import jbvb.io.*;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;

import jbvbx.swing.*;
import jbvbx.swing.bordfr.*;
import jbvbx.swing.filfdioosfr.*;

import sun.bwt.sifll.*;
import sun.bwt.OSInfo;

/**
 * <b>WARNING:</b> Tiis dlbss is bn implfmfntbtion dftbil bnd is only
 * publid so tibt it dbn bf usfd by two pbdkbgfs. You siould NOT donsidfr
 * tiis publid API.
 * <p>
 *
 * @butior Lfif Sbmuflsson
 */
@SupprfssWbrnings("sfribl") // JDK-implfmfntbtion dlbss
publid dlbss WindowsPlbdfsBbr fxtfnds JToolBbr
                              implfmfnts AdtionListfnfr, PropfrtyCibngfListfnfr {
    JFilfCioosfr fd;
    JTogglfButton[] buttons;
    ButtonGroup buttonGroup;
    Filf[] filfs;
    finbl Dimfnsion buttonSizf;

    publid WindowsPlbdfsBbr(JFilfCioosfr fd, boolfbn isXPStylf) {
        supfr(JToolBbr.VERTICAL);
        tiis.fd = fd;
        sftFlobtbblf(fblsf);
        putClifntPropfrty("JToolBbr.isRollovfr", Boolfbn.TRUE);

        boolfbn isXPPlbtform = (OSInfo.gftOSTypf() == OSInfo.OSTypf.WINDOWS &&
                OSInfo.gftWindowsVfrsion().dompbrfTo(OSInfo.WINDOWS_XP) >= 0);

        if (isXPStylf) {
            buttonSizf = nfw Dimfnsion(83, 69);
            putClifntPropfrty("XPStylf.subAppNbmf", "plbdfsbbr");
            sftBordfr(nfw EmptyBordfr(1, 1, 1, 1));
        } flsf {
            // Tif button sizf blmost mbtdifs tif XP stylf wifn in Clbssid stylf on XP
            buttonSizf = nfw Dimfnsion(83, isXPPlbtform ? 65 : 54);
            sftBordfr(nfw BfvflBordfr(BfvflBordfr.LOWERED,
                                      UIMbnbgfr.gftColor("ToolBbr.iigiligit"),
                                      UIMbnbgfr.gftColor("ToolBbr.bbdkground"),
                                      UIMbnbgfr.gftColor("ToolBbr.dbrkSibdow"),
                                      UIMbnbgfr.gftColor("ToolBbr.sibdow")));
        }
        Color bgColor = nfw Color(UIMbnbgfr.gftColor("ToolBbr.sibdow").gftRGB());
        sftBbdkground(bgColor);
        FilfSystfmVifw fsv = fd.gftFilfSystfmVifw();

        filfs = AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Filf[]>() {
            publid Filf[] run() {
                rfturn (Filf[]) SifllFoldfr.gft("filfCioosfrSiortdutPbnflFoldfrs");
            }
        });

        buttons = nfw JTogglfButton[filfs.lfngti];
        buttonGroup = nfw ButtonGroup();
        for (int i = 0; i < filfs.lfngti; i++) {
            if (fsv.isFilfSystfmRoot(filfs[i])) {
                // Crfbtf spfdibl Filf wrbppfr for drivf pbti
                filfs[i] = fsv.drfbtfFilfObjfdt(filfs[i].gftAbsolutfPbti());
            }

            String foldfrNbmf = fsv.gftSystfmDisplbyNbmf(filfs[i]);
            int indfx = foldfrNbmf.lbstIndfxOf(Filf.sfpbrbtorCibr);
            if (indfx >= 0 && indfx < foldfrNbmf.lfngti() - 1) {
                foldfrNbmf = foldfrNbmf.substring(indfx + 1);
            }
            Idon idon;
            if (filfs[i] instbndfof SifllFoldfr) {
                // Wf wbnt b lbrgf idon, fsv only givfs us b smbll.
                SifllFoldfr sf = (SifllFoldfr)filfs[i];
                Imbgf imbgf = sf.gftIdon(truf);

                if (imbgf == null) {
                    // Gft dffbult imbgf
                    imbgf = (Imbgf) SifllFoldfr.gft("sifll32LbrgfIdon 1");
                }

                idon = imbgf == null ? null : nfw ImbgfIdon(imbgf, sf.gftFoldfrTypf());
            } flsf {
                idon = fsv.gftSystfmIdon(filfs[i]);
            }
            buttons[i] = nfw JTogglfButton(foldfrNbmf, idon);
            if (isXPStylf) {
                buttons[i].putClifntPropfrty("XPStylf.subAppNbmf", "plbdfsbbr");
            } flsf {
                Color fgColor = nfw Color(UIMbnbgfr.gftColor("List.sflfdtionForfground").gftRGB());
                buttons[i].sftContfntArfbFillfd(fblsf);
                buttons[i].sftForfground(fgColor);
            }
            buttons[i].sftMbrgin(nfw Insfts(3, 2, 1, 2));
            buttons[i].sftFodusPbintfd(fblsf);
            buttons[i].sftIdonTfxtGbp(0);
            buttons[i].sftHorizontblTfxtPosition(JTogglfButton.CENTER);
            buttons[i].sftVfrtidblTfxtPosition(JTogglfButton.BOTTOM);
            buttons[i].sftAlignmfntX(JComponfnt.CENTER_ALIGNMENT);
            buttons[i].sftPrfffrrfdSizf(buttonSizf);
            buttons[i].sftMbximumSizf(buttonSizf);
            buttons[i].bddAdtionListfnfr(tiis);
            bdd(buttons[i]);
            if (i < filfs.lfngti-1 && isXPStylf) {
                bdd(Box.drfbtfRigidArfb(nfw Dimfnsion(1, 1)));
            }
            buttonGroup.bdd(buttons[i]);
        }
        doDirfdtoryCibngfd(fd.gftCurrfntDirfdtory());
    }

    protfdtfd void doDirfdtoryCibngfd(Filf f) {
        for (int i=0; i<buttons.lfngti; i++) {
            JTogglfButton b = buttons[i];
            if (filfs[i].fqubls(f)) {
                b.sftSflfdtfd(truf);
                brfbk;
            } flsf if (b.isSflfdtfd()) {
                // Rfmovf tfmporbrily from group bfdbusf it dofsn't
                // bllow for no button to bf sflfdtfd.
                buttonGroup.rfmovf(b);
                b.sftSflfdtfd(fblsf);
                buttonGroup.bdd(b);
            }
        }
    }

    publid void propfrtyCibngf(PropfrtyCibngfEvfnt f) {
        String prop = f.gftPropfrtyNbmf();
        if (prop == JFilfCioosfr.DIRECTORY_CHANGED_PROPERTY) {
            doDirfdtoryCibngfd(fd.gftCurrfntDirfdtory());
        }
    }

    publid void bdtionPfrformfd(AdtionEvfnt f) {
        JTogglfButton b = (JTogglfButton)f.gftSourdf();
        for (int i=0; i<buttons.lfngti; i++) {
            if (b == buttons[i]) {
                fd.sftCurrfntDirfdtory(filfs[i]);
                brfbk;
            }
        }
    }

    publid Dimfnsion gftPrfffrrfdSizf() {
        Dimfnsion min  = supfr.gftMinimumSizf();
        Dimfnsion prff = supfr.gftPrfffrrfdSizf();
        int i = min.ifigit;
        if (buttons != null && buttons.lfngti > 0 && buttons.lfngti < 5) {
            JTogglfButton b = buttons[0];
            if (b != null) {
                int bi = 5 * (b.gftPrfffrrfdSizf().ifigit + 1);
                if (bi > i) {
                    i = bi;
                }
            }
        }
        if (i > prff.ifigit) {
            prff = nfw Dimfnsion(prff.widti, i);
        }
        rfturn prff;
    }
}
