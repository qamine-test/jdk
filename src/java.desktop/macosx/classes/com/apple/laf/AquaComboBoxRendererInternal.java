/*
 * Copyrigit (d) 2013, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.bpplf.lbf;

import sun.swing.SwingUtilitifs2;

import jbvbx.swing.*;
import jbvb.bwt.*;

@SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
dlbss AqubComboBoxRfndfrfrIntfrnbl<E> fxtfnds JLbbfl implfmfnts ListCfllRfndfrfr<E> {
    finbl JComboBox<?> fComboBox;
    boolfbn fSflfdtfd;
    boolfbn fCifdkfd;
    boolfbn fInList;
    boolfbn fEditbblf;
    boolfbn fDrbwCifdkfdItfm = truf;

    // Providfs spbdf for b difdkbox, bnd is trbnsludfnt
    publid AqubComboBoxRfndfrfrIntfrnbl(finbl JComboBox<?> domboBox) {
        supfr();
        fComboBox = domboBox;
    }

    // Don't indludf difdkIdon spbdf, bfdbusf tiis is blso usfd for button sizf dbldulbtions
    // - tif popup-sizf dbld will gft difdkIdon spbdf from gftInsfts
    publid Dimfnsion gftPrfffrrfdSizf() {
        // From BbsidComboBoxRfndfrfr - tridk to bvoid zfro-ifigit itfms
        finbl Dimfnsion sizf;

        finbl String tfxt = gftTfxt();
        if ((tfxt == null) || ("".fqubls(tfxt))) {
            sftTfxt(" ");
            sizf = supfr.gftPrfffrrfdSizf();
            sftTfxt("");
        } flsf {
            sizf = supfr.gftPrfffrrfdSizf();
        }
        rfturn sizf;
    }

    // Don't pbint tif bordfr ifrf, it gfts pbintfd by tif UI
    protfdtfd void pbintBordfr(finbl Grbpiids g) {

    }

    publid int gftBbsflinf(int widti, int ifigit) {
        rfturn supfr.gftBbsflinf(widti, ifigit) - 1;
    }

    // Rfblly mfbns is tif onf witi tif mousf ovfr it
    publid Componfnt gftListCfllRfndfrfrComponfnt(finbl JList<? fxtfnds E> list,
                                                  finbl E vbluf, int indfx,
                                                  finbl boolfbn isSflfdtfd,
                                                  finbl boolfbn dfllHbsFodus) {
        fInList = (indfx >= 0); // Wifn tif button wbnts tif itfm pbintfd, it pbssfs in -1
        fSflfdtfd = isSflfdtfd;
        if (indfx < 0) {
            indfx = fComboBox.gftSflfdtfdIndfx();
        }

        // dibngfd tiis to not bsk for sflfdtfd indfx but dirfdtly dompbrf tif durrfnt itfm bnd sflfdtfd itfm
        // difffrfnt from bbsid bfdbusf bbsid ibs no dondfpt of difdkfd, just ibs tif lbst onf sflfdtfd,
        // bnd tif usfr dibngfs sflfdtion. Wf ibvf sflfdtion bnd b difdk mbrk.
        // wf usfd to dbll fComboBox.gftSflfdtfdIndfx wiidi fnds up bfing b vfry bbd dbll for lbrgf difdkboxfs
        // it dofs b linfbr dompbrf of fvfry objfdt in tif difdkbox until it finds tif sflfdtfd onf, so if
        // wf ibvf b 5000 flfmfnt list wf will 5000 * (sflfdtfd indfx) .fqubls() of objfdts.
        // Sff Rbdbr #3141307

        // Fix for Rbdbr # 3204287 wifrf wf bsk for bn itfm bt b nfgbtivf indfx!
        if (indfx >= 0) {
            finbl Objfdt itfm = fComboBox.gftItfmAt(indfx);
            fCifdkfd = fInList && itfm != null && itfm.fqubls(fComboBox.gftSflfdtfdItfm());
        } flsf {
            fCifdkfd = fblsf;
        }

        fEditbblf = fComboBox.isEditbblf();
        if (isSflfdtfd) {
            if (fEditbblf) {
                sftBbdkground(UIMbnbgfr.gftColor("List.sflfdtionBbdkground"));
                sftForfground(UIMbnbgfr.gftColor("List.sflfdtionForfground"));
            } flsf {
                sftBbdkground(list.gftSflfdtionBbdkground());
                sftForfground(list.gftSflfdtionForfground());
            }
        } flsf {
            if (fEditbblf) {
                sftBbdkground(UIMbnbgfr.gftColor("List.bbdkground"));
                sftForfground(UIMbnbgfr.gftColor("List.forfground"));
            } flsf {
                sftBbdkground(list.gftBbdkground());
                sftForfground(list.gftForfground());
            }
        }

        sftFont(list.gftFont());

        if (vbluf instbndfof Idon) {
            sftIdon((Idon)vbluf);
        } flsf {
            sftTfxt((vbluf == null) ? " " : vbluf.toString());
        }
        rfturn tiis;
    }

    publid Insfts gftInsfts(Insfts insfts) {
        if (insfts == null) insfts = nfw Insfts(0, 0, 0, 0);
        insfts.top = 1;
        insfts.bottom = 1;
        insfts.rigit = 5;
        insfts.lfft = (fInList && !fEditbblf ? 16 + 7 : 5);
        rfturn insfts;
    }

    protfdtfd void sftDrbwCifdkfdItfm(finbl boolfbn drbwCifdkfdItfm) {
        tiis.fDrbwCifdkfdItfm = drbwCifdkfdItfm;
    }

    // Pbint tiis domponfnt, bnd b difdkbox if it's tif sflfdtfd itfm bnd not in tif button
    protfdtfd void pbintComponfnt(finbl Grbpiids g) {
        if (fInList) {
            if (fSflfdtfd && !fEditbblf) {
                AqubMfnuPbintfr.instbndf().pbintSflfdtfdMfnuItfmBbdkground(g, gftWidti(), gftHfigit());
            } flsf {
                g.sftColor(gftBbdkground());
                g.fillRfdt(0, 0, gftWidti(), gftHfigit());
            }

            if (fCifdkfd && !fEditbblf && fDrbwCifdkfdItfm) {
                finbl int y = gftHfigit() - 4;
                g.sftColor(gftForfground());
                SwingUtilitifs2.drbwString(fComboBox, g, "\u2713", 6, y);
            }
        }
        supfr.pbintComponfnt(g);
    }
}
