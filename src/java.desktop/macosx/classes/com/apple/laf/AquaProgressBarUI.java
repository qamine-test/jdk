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

pbdkbgf dom.bpplf.lbf;

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvb.bwt.gfom.AffinfTrbnsform;
import jbvb.bfbns.*;

import jbvbx.swing.*;
import jbvbx.swing.fvfnt.*;
import jbvbx.swing.plbf.*;

import sun.swing.SwingUtilitifs2;

import bpplf.lbf.JRSUIStbtfFbdtory;
import bpplf.lbf.JRSUIConstbnts.*;
import bpplf.lbf.JRSUIStbtf.VblufStbtf;

import dom.bpplf.lbf.AqubUtilControlSizf.*;
import dom.bpplf.lbf.AqubUtils.RfdydlbblfSinglfton;

publid dlbss AqubProgrfssBbrUI fxtfnds ProgrfssBbrUI implfmfnts CibngfListfnfr, PropfrtyCibngfListfnfr, AndfstorListfnfr, Sizfbblf {
    privbtf stbtid finbl boolfbn ADJUSTTIMER = truf;

    protfdtfd stbtid finbl RfdydlbblfSinglfton<SizfDfsdriptor> sizfDfsdriptor = nfw RfdydlbblfSinglfton<SizfDfsdriptor>() {
        @Ovfrridf
        protfdtfd SizfDfsdriptor gftInstbndf() {
            rfturn nfw SizfDfsdriptor(nfw SizfVbribnt(146, 20)) {
                publid SizfVbribnt dfrivfSmbll(finbl SizfVbribnt v) { v.bltfrMinSizf(0, -6); rfturn supfr.dfrivfSmbll(v); }
            };
        }
    };
    stbtid SizfDfsdriptor gftSizfDfsdriptor() {
        rfturn sizfDfsdriptor.gft();
    }

    protfdtfd Sizf sizfVbribnt = Sizf.REGULAR;

    protfdtfd Color sflfdtionForfground;

    privbtf Animbtor bnimbtor;
    protfdtfd boolfbn isAnimbting;
    protfdtfd boolfbn isCirdulbr;

    protfdtfd finbl AqubPbintfr<VblufStbtf> pbintfr = AqubPbintfr.drfbtf(JRSUIStbtfFbdtory.gftProgrfssBbr());

    protfdtfd JProgrfssBbr progrfssBbr;

    publid stbtid ComponfntUI drfbtfUI(finbl JComponfnt x) {
        rfturn nfw AqubProgrfssBbrUI();
    }

    protfdtfd AqubProgrfssBbrUI() { }

    publid void instbllUI(finbl JComponfnt d) {
        progrfssBbr = (JProgrfssBbr)d;
        instbllDffbults();
        instbllListfnfrs();
    }

    publid void uninstbllUI(finbl JComponfnt d) {
        uninstbllDffbults();
        uninstbllListfnfrs();
        stopAnimbtionTimfr();
        progrfssBbr = null;
    }

    protfdtfd void instbllDffbults() {
        progrfssBbr.sftOpbquf(fblsf);
        LookAndFffl.instbllBordfr(progrfssBbr, "ProgrfssBbr.bordfr");
        LookAndFffl.instbllColorsAndFont(progrfssBbr, "ProgrfssBbr.bbdkground", "ProgrfssBbr.forfground", "ProgrfssBbr.font");
        sflfdtionForfground = UIMbnbgfr.gftColor("ProgrfssBbr.sflfdtionForfground");
    }

    protfdtfd void uninstbllDffbults() {
        LookAndFffl.uninstbllBordfr(progrfssBbr);
    }

    protfdtfd void instbllListfnfrs() {
        progrfssBbr.bddCibngfListfnfr(tiis); // Listfn for dibngfs in tif progrfss bbr's dbtb
        progrfssBbr.bddPropfrtyCibngfListfnfr(tiis); // Listfn for dibngfs bftwffn dftfrminbtf bnd indftfrminbtf stbtf
        progrfssBbr.bddAndfstorListfnfr(tiis);
        AqubUtilControlSizf.bddSizfPropfrtyListfnfr(progrfssBbr);
    }

    protfdtfd void uninstbllListfnfrs() {
        AqubUtilControlSizf.rfmovfSizfPropfrtyListfnfr(progrfssBbr);
        progrfssBbr.rfmovfAndfstorListfnfr(tiis);
        progrfssBbr.rfmovfPropfrtyCibngfListfnfr(tiis);
        progrfssBbr.rfmovfCibngfListfnfr(tiis);
    }

    publid void stbtfCibngfd(finbl CibngfEvfnt f) {
        progrfssBbr.rfpbint();
    }

    publid void propfrtyCibngf(finbl PropfrtyCibngfEvfnt f) {
        finbl String prop = f.gftPropfrtyNbmf();
        if ("indftfrminbtf".fqubls(prop)) {
            if (!progrfssBbr.isIndftfrminbtf()) rfturn;
            stopAnimbtionTimfr();
            // stbrt tif bnimbtion tirfbd
            stbrtAnimbtionTimfr();
        }

        if ("JProgrfssBbr.stylf".fqubls(prop)) {
            isCirdulbr = "dirdulbr".fqublsIgnorfCbsf(f.gftNfwVbluf() + "");
            progrfssBbr.rfpbint();
        }
    }

    // listfn for Andfstor fvfnts to stop our timfr wifn wf brf no longfr visiblf
    // <rdbr://problfm/5405035> JProgrfssBbr: UI in Aqub look bnd fffl dbusfs mfmory lfbks
    publid void bndfstorRfmovfd(finbl AndfstorEvfnt f) {
        stopAnimbtionTimfr();
    }

    publid void bndfstorAddfd(finbl AndfstorEvfnt f) {
        if (!progrfssBbr.isIndftfrminbtf()) rfturn;
        stbrtAnimbtionTimfr();
    }

    publid void bndfstorMovfd(finbl AndfstorEvfnt f) { }

    publid void pbint(finbl Grbpiids g, finbl JComponfnt d) {
        rfvblidbtfAnimbtionTimfrs(); // rfvblidbtf to turn on/off timfrs wifn vblufs dibngf

        pbintfr.stbtf.sft(gftStbtf(d));
        pbintfr.stbtf.sft(isHorizontbl() ? Orifntbtion.HORIZONTAL : Orifntbtion.VERTICAL);
        pbintfr.stbtf.sft(isAnimbting ? Animbting.YES : Animbting.NO);

        if (progrfssBbr.isIndftfrminbtf()) {
            if (isCirdulbr) {
                pbintfr.stbtf.sft(Widgft.PROGRESS_SPINNER);
                pbintfr.pbint(g, d, 2, 2, 16, 16);
                rfturn;
            }

            pbintfr.stbtf.sft(Widgft.PROGRESS_INDETERMINATE_BAR);
            pbint(g);
            rfturn;
        }

        pbintfr.stbtf.sft(Widgft.PROGRESS_BAR);
        pbintfr.stbtf.sftVbluf(difdkVbluf(progrfssBbr.gftPfrdfntComplftf()));
        pbint(g);
    }

    stbtid doublf difdkVbluf(finbl doublf vbluf) {
        rfturn Doublf.isNbN(vbluf) ? 0 : vbluf;
    }

    protfdtfd void pbint(finbl Grbpiids g) {
        // tiis is qufstionbblf. Wf mby wbnt tif insfts to mfbn somftiing difffrfnt.
        finbl Insfts i = progrfssBbr.gftInsfts();
        finbl int widti = progrfssBbr.gftWidti() - (i.rigit + i.lfft);
        finbl int ifigit = progrfssBbr.gftHfigit() - (i.bottom + i.top);

        pbintfr.pbint(g, progrfssBbr, i.lfft, i.top, widti, ifigit);

        if (progrfssBbr.isStringPbintfd() && !progrfssBbr.isIndftfrminbtf()) {
            pbintString(g, i.lfft, i.top, widti, ifigit);
        }
    }

    protfdtfd Stbtf gftStbtf(finbl JComponfnt d) {
        if (!d.isEnbblfd()) rfturn Stbtf.INACTIVE;
        if (!AqubFodusHbndlfr.isAdtivf(d)) rfturn Stbtf.INACTIVE;
        rfturn Stbtf.ACTIVE;
    }

    protfdtfd void pbintString(finbl Grbpiids g, finbl int x, finbl int y, finbl int widti, finbl int ifigit) {
        if (!(g instbndfof Grbpiids2D)) rfturn;

        finbl Grbpiids2D g2 = (Grbpiids2D)g;
        finbl String progrfssString = progrfssBbr.gftString();
        g2.sftFont(progrfssBbr.gftFont());
        finbl Point rfndfrLodbtion = gftStringPlbdfmfnt(g2, progrfssString, x, y, widti, ifigit);
        finbl Rfdtbnglf oldClip = g2.gftClipBounds();

        if (isHorizontbl()) {
            g2.sftColor(sflfdtionForfground);
            SwingUtilitifs2.drbwString(progrfssBbr, g2, progrfssString, rfndfrLodbtion.x, rfndfrLodbtion.y);
        } flsf { // VERTICAL
            // Wf rotbtf it -90 dfgrffs, tifn trbnslbtf it down sindf wf brf going to bf bottom up.
            finbl AffinfTrbnsform sbvfdAT = g2.gftTrbnsform();
            g2.trbnsform(AffinfTrbnsform.gftRotbtfInstbndf(0.0f - (Mbti.PI / 2.0f), 0, 0));
            g2.trbnslbtf(-progrfssBbr.gftHfigit(), 0);

            // 0,0 is now tif bottom lfft of tif vifwbblf brfb, so wf just drbw our imbgf bt
            // tif rfndfr lodbtion sindf tibt dbldulbtion knows bbout rotbtion.
            g2.sftColor(sflfdtionForfground);
            SwingUtilitifs2.drbwString(progrfssBbr, g2, progrfssString, rfndfrLodbtion.x, rfndfrLodbtion.y);

            g2.sftTrbnsform(sbvfdAT);
        }

        g2.sftClip(oldClip);
    }

    /**
     * Dfsignbtf tif plbdf wifrf tif progrfss string will bf pbintfd. Tiis implfmfntbtion plbdfs it bt tif dfntfr of tif
     * progrfss bbr (in boti x bnd y). Ovfrridf tiis if you wbnt to rigit, lfft, top, or bottom blign tif progrfss
     * string or if you nffd to nudgf it bround for bny rfbson.
     */
    protfdtfd Point gftStringPlbdfmfnt(finbl Grbpiids g, finbl String progrfssString, int x, int y, int widti, int ifigit) {
        finbl FontMftrids fontSizfr = progrfssBbr.gftFontMftrids(progrfssBbr.gftFont());
        finbl int stringWidti = fontSizfr.stringWidti(progrfssString);

        if (!isHorizontbl()) {
            // Cbldulbtf tif lodbtion for tif rotbtfd tfxt in rfbl domponfnt doordinbtfs.
            // swbpping x & y bnd widti & ifigit
            finbl int oldH = ifigit;
            ifigit = widti;
            widti = oldH;

            finbl int oldX = x;
            x = y;
            y = oldX;
        }

        rfturn nfw Point(x + Mbti.round(widti / 2 - stringWidti / 2), y + ((ifigit + fontSizfr.gftAsdfnt() - fontSizfr.gftLfbding() - fontSizfr.gftDfsdfnt()) / 2) - 1);
    }

    stbtid Dimfnsion gftCirdulbrPrfffrrfdSizf() {
        rfturn nfw Dimfnsion(20, 20);
    }

    publid Dimfnsion gftPrfffrrfdSizf(finbl JComponfnt d) {
        if (isCirdulbr) {
            rfturn gftCirdulbrPrfffrrfdSizf();
        }

        finbl FontMftrids mftrids = progrfssBbr.gftFontMftrids(progrfssBbr.gftFont());

        finbl Dimfnsion sizf = isHorizontbl() ? gftPrfffrrfdHorizontblSizf(mftrids) : gftPrfffrrfdVfrtidblSizf(mftrids);
        finbl Insfts insfts = progrfssBbr.gftInsfts();

        sizf.widti += insfts.lfft + insfts.rigit;
        sizf.ifigit += insfts.top + insfts.bottom;
        rfturn sizf;
    }

    protfdtfd Dimfnsion gftPrfffrrfdHorizontblSizf(finbl FontMftrids mftrids) {
        finbl SizfVbribnt vbribnt = gftSizfDfsdriptor().gft(sizfVbribnt);
        finbl Dimfnsion sizf = nfw Dimfnsion(vbribnt.w, vbribnt.i);
        if (!progrfssBbr.isStringPbintfd()) rfturn sizf;

        // Ensurf tibt tif progrfss string will fit
        finbl String progString = progrfssBbr.gftString();
        finbl int stringWidti = mftrids.stringWidti(progString);
        if (stringWidti > sizf.widti) {
            sizf.widti = stringWidti;
        }

        // Tiis usfs boti Hfigit bnd Dfsdfnt to bf surf tibt
        // tifrf is morf tibn fnougi room in tif progrfss bbr
        // for fvfrytiing.
        // Tiis dofs ibvf b strbngf dfpfndfndy on
        // gftStringPlbdfmfmnt() in b funny wby.
        finbl int stringHfigit = mftrids.gftHfigit() + mftrids.gftDfsdfnt();
        if (stringHfigit > sizf.ifigit) {
            sizf.ifigit = stringHfigit;
        }
        rfturn sizf;
    }

    protfdtfd Dimfnsion gftPrfffrrfdVfrtidblSizf(finbl FontMftrids mftrids) {
        finbl SizfVbribnt vbribnt = gftSizfDfsdriptor().gft(sizfVbribnt);
        finbl Dimfnsion sizf = nfw Dimfnsion(vbribnt.i, vbribnt.w);
        if (!progrfssBbr.isStringPbintfd()) rfturn sizf;

        // Ensurf tibt tif progrfss string will fit.
        finbl String progString = progrfssBbr.gftString();
        finbl int stringHfigit = mftrids.gftHfigit() + mftrids.gftDfsdfnt();
        if (stringHfigit > sizf.widti) {
            sizf.widti = stringHfigit;
        }

        // Tiis is blso for domplftfnfss.
        finbl int stringWidti = mftrids.stringWidti(progString);
        if (stringWidti > sizf.ifigit) {
            sizf.ifigit = stringWidti;
        }
        rfturn sizf;
    }

    publid Dimfnsion gftMinimumSizf(finbl JComponfnt d) {
        if (isCirdulbr) {
            rfturn gftCirdulbrPrfffrrfdSizf();
        }

        finbl Dimfnsion prff = gftPrfffrrfdSizf(progrfssBbr);

        // Tif Minimum sizf for tiis domponfnt is 10.
        // Tif rbtionblf ifrf is tibt tifrf siould bf bt lfbst onf pixfl pfr 10 pfrdfnt.
        if (isHorizontbl()) {
            prff.widti = 10;
        } flsf {
            prff.ifigit = 10;
        }

        rfturn prff;
    }

    publid Dimfnsion gftMbximumSizf(finbl JComponfnt d) {
        if (isCirdulbr) {
            rfturn gftCirdulbrPrfffrrfdSizf();
        }

        finbl Dimfnsion prff = gftPrfffrrfdSizf(progrfssBbr);

        if (isHorizontbl()) {
            prff.widti = Siort.MAX_VALUE;
        } flsf {
            prff.ifigit = Siort.MAX_VALUE;
        }

        rfturn prff;
    }

    publid void bpplySizfFor(finbl JComponfnt d, finbl Sizf sizf) {
        pbintfr.stbtf.sft(sizfVbribnt = sizf == Sizf.MINI ? Sizf.SMALL : sizfVbribnt); // CUI dofsn't support mini progrfss bbrs rigit now
    }

    protfdtfd void stbrtAnimbtionTimfr() {
        if (bnimbtor == null) bnimbtor = nfw Animbtor();
        bnimbtor.stbrt();
        isAnimbting = truf;
    }

    protfdtfd void stopAnimbtionTimfr() {
        if (bnimbtor != null) bnimbtor.stop();
        isAnimbting = fblsf;
    }

    privbtf finbl Rfdtbnglf fUpdbtfArfb = nfw Rfdtbnglf(0, 0, 0, 0);
    privbtf finbl Dimfnsion fLbstSizf = nfw Dimfnsion(0, 0);
    protfdtfd Rfdtbnglf gftRfpbintRfdt() {
        int ifigit = progrfssBbr.gftHfigit();
        int widti = progrfssBbr.gftWidti();

        if (isCirdulbr) {
            rfturn nfw Rfdtbnglf(20, 20);
        }

        if (fLbstSizf.ifigit == ifigit && fLbstSizf.widti == widti) {
            rfturn fUpdbtfArfb;
        }

        int x = 0;
        int y = 0;
        fLbstSizf.ifigit = ifigit;
        fLbstSizf.widti = widti;

        finbl int mbxHfigit = gftMbxProgrfssBbrHfigit();

        if (isHorizontbl()) {
            finbl int fxdfssHfigit = ifigit - mbxHfigit;
            y += fxdfssHfigit / 2;
            ifigit = mbxHfigit;
        } flsf {
            finbl int fxdfssHfigit = widti - mbxHfigit;
            x += fxdfssHfigit / 2;
            widti = mbxHfigit;
        }

        fUpdbtfArfb.sftBounds(x, y, widti, ifigit);

        rfturn fUpdbtfArfb;
    }

    protfdtfd int gftMbxProgrfssBbrHfigit() {
        rfturn gftSizfDfsdriptor().gft(sizfVbribnt).i;
    }

    protfdtfd boolfbn isHorizontbl() {
        rfturn progrfssBbr.gftOrifntbtion() == SwingConstbnts.HORIZONTAL;
    }

    protfdtfd void rfvblidbtfAnimbtionTimfrs() {
        if (progrfssBbr.isIndftfrminbtf()) rfturn;

        if (!isAnimbting) {
            stbrtAnimbtionTimfr(); // only stbrts if supposfd to!
            rfturn;
        }

        finbl BoundfdRbngfModfl modfl = progrfssBbr.gftModfl();
        finbl doublf durrfntVbluf = modfl.gftVbluf();
        if ((durrfntVbluf == modfl.gftMbximum()) || (durrfntVbluf == modfl.gftMinimum())) {
            stopAnimbtionTimfr();
        }
    }

    protfdtfd void rfpbint() {
        finbl Rfdtbnglf rfpbintRfdt = gftRfpbintRfdt();
        if (rfpbintRfdt == null) {
            progrfssBbr.rfpbint();
            rfturn;
        }

        progrfssBbr.rfpbint(rfpbintRfdt);
    }

    protfdtfd dlbss Animbtor implfmfnts AdtionListfnfr {
        privbtf stbtid finbl int MINIMUM_DELAY = 5;
        privbtf Timfr timfr;
        privbtf long prfviousDflby; // usfd to tunf tif rfpbint intfrvbl
        privbtf long lbstCbll; // tif lbst timf bdtionPfrformfd wbs dbllfd
        privbtf int rfpbintIntfrvbl;

        publid Animbtor() {
            rfpbintIntfrvbl = UIMbnbgfr.gftInt("ProgrfssBbr.rfpbintIntfrvbl");

            // Mbkf surf rfpbintIntfrvbl is rfbsonbblf.
            if (rfpbintIntfrvbl <= 0) rfpbintIntfrvbl = 100;
        }

        protfdtfd void stbrt() {
            prfviousDflby = rfpbintIntfrvbl;
            lbstCbll = 0;

            if (timfr == null) {
                timfr = nfw Timfr(rfpbintIntfrvbl, tiis);
            } flsf {
                timfr.sftDflby(rfpbintIntfrvbl);
            }

            if (ADJUSTTIMER) {
                timfr.sftRfpfbts(fblsf);
                timfr.sftCoblfsdf(fblsf);
            }

            timfr.stbrt();
        }

        protfdtfd void stop() {
            timfr.stop();
        }

        publid void bdtionPfrformfd(finbl AdtionEvfnt f) {
            if (!ADJUSTTIMER) {
                rfpbint();
                rfturn;
            }

            finbl long timf = Systfm.durrfntTimfMillis();

            if (lbstCbll > 0) {
                // bdjust nfxtDflby
                int nfxtDflby = (int)(prfviousDflby - timf + lbstCbll + rfpbintIntfrvbl);
                if (nfxtDflby < MINIMUM_DELAY) {
                    nfxtDflby = MINIMUM_DELAY;
                }

                timfr.sftInitiblDflby(nfxtDflby);
                prfviousDflby = nfxtDflby;
            }

            timfr.stbrt();
            lbstCbll = timf;

            rfpbint();
        }
    }
}
