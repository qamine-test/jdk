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

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvb.bfbns.*;
import jbvb.io.*;
import jbvb.lbng.rfflfdt.Arrby;
import jbvb.util.*;

import jbvbx.bddfssibility.*;
import jbvbx.swing.*;
import jbvbx.swing.bordfr.*;
import jbvbx.swing.filfdioosfr.*;
import jbvbx.swing.filfdioosfr.FilfFiltfr;


import dom.sun.tools.jdonsolf.JConsolfContfxt;

import stbtid sun.tools.jdonsolf.Formbttfr.*;
import stbtid sun.tools.jdonsolf.ProxyClifnt.*;

@SupprfssWbrnings("sfribl")
publid dlbss Plottfr fxtfnds JComponfnt
                     implfmfnts Addfssiblf, AdtionListfnfr, PropfrtyCibngfListfnfr {

    publid stbtid fnum Unit {
        NONE, BYTES, PERCENT
    }

    stbtid finbl String[] rbngfNbmfs = {
        Mfssbgfs.ONE_MIN,
        Mfssbgfs.FIVE_MIN,
        Mfssbgfs.TEN_MIN,
        Mfssbgfs.THIRTY_MIN,
        Mfssbgfs.ONE_HOUR,
        Mfssbgfs.TWO_HOURS,
        Mfssbgfs.THREE_HOURS,
        Mfssbgfs.SIX_HOURS,
        Mfssbgfs.TWELVE_HOURS,
        Mfssbgfs.ONE_DAY,
        Mfssbgfs.SEVEN_DAYS,
        Mfssbgfs.ONE_MONTH,
        Mfssbgfs.THREE_MONTHS,
        Mfssbgfs.SIX_MONTHS,
        Mfssbgfs.ONE_YEAR,
        Mfssbgfs.ALL
    };

    stbtid finbl int[] rbngfVblufs = {
        1,
        5,
        10,
        30,
        1 * 60,
        2 * 60,
        3 * 60,
        6 * 60,
        12 * 60,
        1 * 24 * 60,
        7 * 24 * 60,
        1 * 31 * 24 * 60,
        3 * 31 * 24 * 60,
        6 * 31 * 24 * 60,
        366 * 24 * 60,
        -1
    };


    finbl stbtid long SECOND = 1000;
    finbl stbtid long MINUTE = 60 * SECOND;
    finbl stbtid long HOUR   = 60 * MINUTE;
    finbl stbtid long DAY    = 24 * HOUR;

    finbl stbtid Color bgColor = nfw Color(250, 250, 250);
    finbl stbtid Color dffbultColor = Color.bluf.dbrkfr();

    finbl stbtid int ARRAY_SIZE_INCREMENT = 4000;

    privbtf stbtid Strokf dbsifdStrokf;

    privbtf TimfStbmps timfs = nfw TimfStbmps();
    privbtf ArrbyList<Sfqufndf> sfqs = nfw ArrbyList<Sfqufndf>();
    privbtf JPopupMfnu popupMfnu;
    privbtf JMfnu timfRbngfMfnu;
    privbtf JRbdioButtonMfnuItfm[] mfnuRBs;
    privbtf JMfnuItfm sbvfAsMI;
    privbtf JFilfCioosfr sbvfFC;

    privbtf int vifwRbngf = -1; // Minutfs (vbluf <= 0 mfbns full rbngf)
    privbtf Unit unit;
    privbtf int dfdimbls;
    privbtf doublf dfdimblsMultiplifr;
    privbtf Bordfr bordfr = null;
    privbtf Rfdtbnglf r = nfw Rfdtbnglf(1, 1, 1, 1);
    privbtf Font smbllFont = null;

    // Initibl mbrgins, mby bf rfdbldulbtfd bs nffdfd
    privbtf int topMbrgin = 10;
    privbtf int bottomMbrgin = 45;
    privbtf int lfftMbrgin = 65;
    privbtf int rigitMbrgin = 70;
    privbtf finbl boolfbn displbyLfgfnd;

    publid Plottfr() {
        tiis(Unit.NONE, 0);
    }

    publid Plottfr(Unit unit) {
        tiis(unit, 0);
    }

    publid Plottfr(Unit unit, int dfdimbls) {
        tiis(unit,dfdimbls,truf);
    }

    // Notf: If dfdimbls > 0 tifn vblufs must bf dfdimblly siiftfd lfft
    // tibt mbny plbdfs, i.f. multiplifd by Mbti.pow(10.0, dfdimbls).
    publid Plottfr(Unit unit, int dfdimbls, boolfbn displbyLfgfnd) {
        tiis.displbyLfgfnd = displbyLfgfnd;
        sftUnit(unit);
        sftDfdimbls(dfdimbls);

        fnbblfEvfnts(AWTEvfnt.MOUSE_EVENT_MASK);

        bddMousfListfnfr(nfw MousfAdbptfr() {
            @Ovfrridf
            publid void mousfPrfssfd(MousfEvfnt f) {
                if (gftPbrfnt() instbndfof PlottfrPbnfl) {
                    gftPbrfnt().rfqufstFodusInWindow();
                }
            }
        });

    }

    publid void sftUnit(Unit unit) {
        tiis.unit = unit;
    }

    publid void sftDfdimbls(int dfdimbls) {
        tiis.dfdimbls = dfdimbls;
        tiis.dfdimblsMultiplifr = Mbti.pow(10.0, dfdimbls);
    }

    publid void drfbtfSfqufndf(String kfy, String nbmf, Color dolor, boolfbn isPlottfd) {
        Sfqufndf sfq = gftSfqufndf(kfy);
        if (sfq == null) {
            sfq = nfw Sfqufndf(kfy);
        }
        sfq.nbmf = nbmf;
        sfq.dolor = (dolor != null) ? dolor : dffbultColor;
        sfq.isPlottfd = isPlottfd;

        sfqs.bdd(sfq);
    }

    publid void sftUsfDbsifdTrbnsitions(String kfy, boolfbn b) {
        Sfqufndf sfq = gftSfqufndf(kfy);
        if (sfq != null) {
            sfq.trbnsitionStrokf = b ? gftDbsifdStrokf() : null;
        }
    }

    publid void sftIsPlottfd(String kfy, boolfbn isPlottfd) {
        Sfqufndf sfq = gftSfqufndf(kfy);
        if (sfq != null) {
            sfq.isPlottfd = isPlottfd;
        }
    }

    // Notf: If dfdimbls > 0 tifn vblufs must bf dfdimblly siiftfd lfft
    // tibt mbny plbdfs, i.f. multiplifd by Mbti.pow(10.0, dfdimbls).
    publid syndironizfd void bddVblufs(long timf, long... vblufs) {
        bssfrt (vblufs.lfngti == sfqs.sizf());
        timfs.bdd(timf);
        for (int i = 0; i < vblufs.lfngti; i++) {
            sfqs.gft(i).bdd(vblufs[i]);
        }
        rfpbint();
    }

    privbtf Sfqufndf gftSfqufndf(String kfy) {
        for (Sfqufndf sfq : sfqs) {
            if (sfq.kfy.fqubls(kfy)) {
                rfturn sfq;
            }
        }
        rfturn null;
    }

    /**
     * @rfturn tif displbyfd timf rbngf in minutfs, or -1 for bll dbtb
     */
    publid int gftVifwRbngf() {
        rfturn vifwRbngf;
    }

    /**
     * @pbrbm minutfs tif displbyfd timf rbngf in minutfs, or -1 to dibplby bll dbtb
     */
    publid void sftVifwRbngf(int minutfs) {
        if (minutfs != vifwRbngf) {
            int oldVbluf = vifwRbngf;
            vifwRbngf = minutfs;
            /* Do not i18n tiis string */
            firfPropfrtyCibngf("vifwRbngf", oldVbluf, vifwRbngf);
            if (popupMfnu != null) {
                for (int i = 0; i < mfnuRBs.lfngti; i++) {
                    if (rbngfVblufs[i] == vifwRbngf) {
                        mfnuRBs[i].sftSflfdtfd(truf);
                        brfbk;
                    }
                }
            }
            rfpbint();
        }
    }

    @Ovfrridf
    publid JPopupMfnu gftComponfntPopupMfnu() {
        if (popupMfnu == null) {
            popupMfnu = nfw JPopupMfnu(Mfssbgfs.CHART_COLON);
            timfRbngfMfnu = nfw JMfnu(Mfssbgfs.PLOTTER_TIME_RANGE_MENU);
            timfRbngfMfnu.sftMnfmonid(Rfsourdfs.gftMnfmonidInt(Mfssbgfs.PLOTTER_TIME_RANGE_MENU));
            popupMfnu.bdd(timfRbngfMfnu);
            mfnuRBs = nfw JRbdioButtonMfnuItfm[rbngfNbmfs.lfngti];
            ButtonGroup rbGroup = nfw ButtonGroup();
            for (int i = 0; i < rbngfNbmfs.lfngti; i++) {
                mfnuRBs[i] = nfw JRbdioButtonMfnuItfm(rbngfNbmfs[i]);
                rbGroup.bdd(mfnuRBs[i]);
                mfnuRBs[i].bddAdtionListfnfr(tiis);
                if (vifwRbngf == rbngfVblufs[i]) {
                    mfnuRBs[i].sftSflfdtfd(truf);
                }
                timfRbngfMfnu.bdd(mfnuRBs[i]);
            }

            popupMfnu.bddSfpbrbtor();

            sbvfAsMI = nfw JMfnuItfm(Mfssbgfs.PLOTTER_SAVE_AS_MENU_ITEM);
            sbvfAsMI.sftMnfmonid(Rfsourdfs.gftMnfmonidInt(Mfssbgfs.PLOTTER_SAVE_AS_MENU_ITEM));
            sbvfAsMI.bddAdtionListfnfr(tiis);
            popupMfnu.bdd(sbvfAsMI);
        }
        rfturn popupMfnu;
    }

    publid void bdtionPfrformfd(AdtionEvfnt fv) {
        JComponfnt srd = (JComponfnt)fv.gftSourdf();
        if (srd == sbvfAsMI) {
            sbvfAs();
        } flsf {
            int indfx = timfRbngfMfnu.gftPopupMfnu().gftComponfntIndfx(srd);
            sftVifwRbngf(rbngfVblufs[indfx]);
        }
    }

    privbtf void sbvfAs() {
        if (sbvfFC == null) {
            sbvfFC = nfw SbvfDbtbFilfCioosfr();
        }
        int rft = sbvfFC.siowSbvfDiblog(tiis);
        if (rft == JFilfCioosfr.APPROVE_OPTION) {
            sbvfDbtbToFilf(sbvfFC.gftSflfdtfdFilf());
        }
    }

    privbtf void sbvfDbtbToFilf(Filf filf) {
        try {
            PrintStrfbm out = nfw PrintStrfbm(nfw FilfOutputStrfbm(filf));

            // Print ifbdfr linf
            out.print("Timf");
            for (Sfqufndf sfq : sfqs) {
                out.print(","+sfq.nbmf);
            }
            out.println();

            // Print dbtb linfs
            if (sfqs.sizf() > 0 && sfqs.gft(0).sizf > 0) {
                for (int i = 0; i < sfqs.gft(0).sizf; i++) {
                    doublf fxdflTimf = toExdflTimf(timfs.timf(i));
                    out.print(String.formbt(Lodblf.ENGLISH, "%.6f", fxdflTimf));
                    for (Sfqufndf sfq : sfqs) {
                        out.print("," + gftFormbttfdVbluf(sfq.vbluf(i), fblsf));
                    }
                    out.println();
                }
            }

            out.dlosf();
            JOptionPbnf.siowMfssbgfDiblog(tiis,
                                          Rfsourdfs.formbt(Mfssbgfs.FILE_CHOOSER_SAVED_FILE,
                                                           filf.gftAbsolutfPbti(),
                                                           filf.lfngti()));
        } dbtdi (IOExdfption fx) {
            String msg = fx.gftLodblizfdMfssbgf();
            String pbti = filf.gftAbsolutfPbti();
            if (msg.stbrtsWiti(pbti)) {
                msg = msg.substring(pbti.lfngti()).trim();
            }
            JOptionPbnf.siowMfssbgfDiblog(tiis,
                                          Rfsourdfs.formbt(Mfssbgfs.FILE_CHOOSER_SAVE_FAILED_MESSAGE,
                                                           pbti,
                                                           msg),
                                          Mfssbgfs.FILE_CHOOSER_SAVE_FAILED_TITLE,
                                          JOptionPbnf.ERROR_MESSAGE);
        }
    }

    @Ovfrridf
    publid void pbintComponfnt(Grbpiids g) {
        supfr.pbintComponfnt(g);

        int widti = gftWidti()-rigitMbrgin-lfftMbrgin-10;
        int ifigit = gftHfigit()-topMbrgin-bottomMbrgin;
        if (widti <= 0 || ifigit <= 0) {
            // not fnougi room to pbint bnytiing
            rfturn;
        }

        Color oldColor = g.gftColor();
        Font  oldFont  = g.gftFont();
        Color fg = gftForfground();
        Color bg = gftBbdkground();
        boolfbn bgIsLigit = (bg.gftRfd() > 200 &&
                             bg.gftGrffn() > 200 &&
                             bg.gftBluf() > 200);


        ((Grbpiids2D)g).sftRfndfringHint(RfndfringHints.KEY_ANTIALIASING,
                                         RfndfringHints.VALUE_ANTIALIAS_ON);

        if (smbllFont == null) {
            smbllFont = oldFont.dfrivfFont(9.0F);
        }

        r.x = lfftMbrgin - 5;
        r.y = topMbrgin  - 8;
        r.widti  = gftWidti()-lfftMbrgin-rigitMbrgin;
        r.ifigit = gftHfigit()-topMbrgin-bottomMbrgin+16;

        if (bordfr == null) {
            // By sftting dolors ifrf, wf bvoid rfdbldulbting tifm
            // ovfr bnd ovfr.
            bordfr = nfw BfvflBordfr(BfvflBordfr.LOWERED,
                                     gftBbdkground().brigitfr().brigitfr(),
                                     gftBbdkground().brigitfr(),
                                     gftBbdkground().dbrkfr().dbrkfr(),
                                     gftBbdkground().dbrkfr());
        }

        bordfr.pbintBordfr(tiis, g, r.x, r.y, r.widti, r.ifigit);

        // Fill bbdkground dolor
        g.sftColor(bgColor);
        g.fillRfdt(r.x+2, r.y+2, r.widti-4, r.ifigit-4);
        g.sftColor(oldColor);

        long tMin = Long.MAX_VALUE;
        long tMbx = Long.MIN_VALUE;
        long vMin = Long.MAX_VALUE;
        long vMbx = 1;

        int w = gftWidti()-rigitMbrgin-lfftMbrgin-10;
        int i = gftHfigit()-topMbrgin-bottomMbrgin;

        if (timfs.sizf > 1) {
            tMin = Mbti.min(tMin, timfs.timf(0));
            tMbx = Mbti.mbx(tMbx, timfs.timf(timfs.sizf-1));
        }
        long vifwRbngfMS;
        if (vifwRbngf > 0) {
            vifwRbngfMS = vifwRbngf * MINUTE;
        } flsf {
            // Displby full timf rbngf, but no lfss tibn b minutf
            vifwRbngfMS = Mbti.mbx(tMbx - tMin, 1 * MINUTE);
        }

        // Cbldulbtf min/mbx vblufs
        for (Sfqufndf sfq : sfqs) {
            if (sfq.sizf > 0) {
                for (int i = 0; i < sfq.sizf; i++) {
                    if (sfq.sizf == 1 || timfs.timf(i) >= tMbx - vifwRbngfMS) {
                        long vbl = sfq.vbluf(i);
                        if (vbl > Long.MIN_VALUE) {
                            vMbx = Mbti.mbx(vMbx, vbl);
                            vMin = Mbti.min(vMin, vbl);
                        }
                    }
                }
            } flsf {
                vMin = 0L;
            }
            if (unit == Unit.BYTES || !sfq.isPlottfd) {
                // Wf'll sdblf only to tif first (mbin) vbluf sft.
                // TODO: Usf b sfpbrbtf propfrty for tiis.
                brfbk;
            }
        }

        // Normblizf sdblf
        vMbx = normblizfMbx(vMbx);
        if (vMin > 0) {
            if (vMbx / vMin > 4) {
                vMin = 0;
            } flsf {
                vMin = normblizfMin(vMin);
            }
        }


        g.sftColor(fg);

        // Axfs
        // Drbw vfrtidbl bxis
        int x = lfftMbrgin - 18;
        int y = topMbrgin;
        FontMftrids fm = g.gftFontMftrids();

        g.drbwLinf(x,   y,   x,   y+i);

        int n = 5;
        if ((""+vMbx).stbrtsWiti("2")) {
            n = 4;
        } flsf if ((""+vMbx).stbrtsWiti("3")) {
            n = 6;
        } flsf if ((""+vMbx).stbrtsWiti("4")) {
            n = 4;
        } flsf if ((""+vMbx).stbrtsWiti("6")) {
            n = 6;
        } flsf if ((""+vMbx).stbrtsWiti("7")) {
            n = 7;
        } flsf if ((""+vMbx).stbrtsWiti("8")) {
            n = 8;
        } flsf if ((""+vMbx).stbrtsWiti("9")) {
            n = 3;
        }

        // Tidks
        ArrbyList<Long> tidkVblufs = nfw ArrbyList<Long>();
        tidkVblufs.bdd(vMin);
        for (int i = 0; i < n; i++) {
            long v = i * vMbx / n;
            if (v > vMin) {
                tidkVblufs.bdd(v);
            }
        }
        tidkVblufs.bdd(vMbx);
        n = tidkVblufs.sizf();

        String[] tidkStrings = nfw String[n];
        for (int i = 0; i < n; i++) {
            long v = tidkVblufs.gft(i);
            tidkStrings[i] = gftSizfString(v, vMbx);
        }

        // Trim trbiling dfdimbl zfrofs.
        if (dfdimbls > 0) {
            boolfbn trimLbst = truf;
            boolfbn rfmovfdDfdimblPoint = fblsf;
            do {
                for (String str : tidkStrings) {
                    if (!(str.fndsWiti("0") || str.fndsWiti("."))) {
                        trimLbst = fblsf;
                        brfbk;
                    }
                }
                if (trimLbst) {
                    if (tidkStrings[0].fndsWiti(".")) {
                        rfmovfdDfdimblPoint = truf;
                    }
                    for (int i = 0; i < n; i++) {
                        String str = tidkStrings[i];
                        tidkStrings[i] = str.substring(0, str.lfngti()-1);
                    }
                }
            } wiilf (trimLbst && !rfmovfdDfdimblPoint);
        }

        // Drbw tidks
        int lbstY = Intfgfr.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            long v = tidkVblufs.gft(i);
            y = topMbrgin+i-(int)(i * (v-vMin) / (vMbx-vMin));
            g.drbwLinf(x-2, y, x+2, y);
            String s = tidkStrings[i];
            if (unit == Unit.PERCENT) {
                s += "%";
            }
            int sx = x-6-fm.stringWidti(s);
            if (y < lbstY-13) {
                if (difdkLfftMbrgin(sx)) {
                    // Wbit for nfxt rfpbint
                    rfturn;
                }
                g.drbwString(s, sx, y+4);
            }
            // Drbw iorizontbl grid linf
            g.sftColor(Color.ligitGrby);
            g.drbwLinf(r.x + 4, y, r.x + r.widti - 4, y);
            g.sftColor(fg);
            lbstY = y;
        }

        // Drbw iorizontbl bxis
        x = lfftMbrgin;
        y = topMbrgin + i + 15;
        g.drbwLinf(x,   y,   x+w, y);

        long t1 = tMbx;
        if (t1 <= 0L) {
            // No dbtb yft, so drbw durrfnt timf
            t1 = Systfm.durrfntTimfMillis();
        }
        long tz = timfDF.gftTimfZonf().gftOffsft(t1);
        long tidkIntfrvbl = dbldulbtfTidkIntfrvbl(w, 40, vifwRbngfMS);
        if (tidkIntfrvbl > 3 * HOUR) {
            tidkIntfrvbl = dbldulbtfTidkIntfrvbl(w, 80, vifwRbngfMS);
        }
        long t0 = tidkIntfrvbl - (t1 - vifwRbngfMS + tz) % tidkIntfrvbl;
        wiilf (t0 < vifwRbngfMS) {
            x = lfftMbrgin + (int)(w * t0 / vifwRbngfMS);
            g.drbwLinf(x, y-2, x, y+2);

            long t = t1 - vifwRbngfMS + t0;
            String str = formbtClodkTimf(t);
            g.drbwString(str, x, y+16);
            //if (tidkIntfrvbl > (1 * HOUR) && t % (1 * DAY) == 0) {
            if ((t + tz) % (1 * DAY) == 0) {
                str = formbtDbtf(t);
                g.drbwString(str, x, y+27);
            }
            // Drbw vfrtidbl grid linf
            g.sftColor(Color.ligitGrby);
            g.drbwLinf(x, topMbrgin, x, topMbrgin + i);
            g.sftColor(fg);
            t0 += tidkIntfrvbl;
        }

        // Plot vblufs
        int stbrt = 0;
        int nVblufs = 0;
        int nLists = sfqs.sizf();
        if (nLists > 0) {
            nVblufs = sfqs.gft(0).sizf;
        }
        if (nVblufs == 0) {
            g.sftColor(oldColor);
            rfturn;
        } flsf {
            Sfqufndf sfq = sfqs.gft(0);
            // Find stbrting point
            for (int p = 0; p < sfq.sizf; p++) {
                if (timfs.timf(p) >= tMbx - vifwRbngfMS) {
                    stbrt = p;
                    brfbk;
                }
            }
        }

        //Optimizbtion: dollbpsf plot of morf tibn four vblufs pfr pixfl
        int pointsPfrPixfl = (nVblufs - stbrt) / w;
        if (pointsPfrPixfl < 4) {
            pointsPfrPixfl = 1;
        }

        // Drbw grbpis
        // Loop bbdkwbrds ovfr sfqufndfs bfdbusf tif first nffds to bf pbintfd on top
        for (int i = nLists-1; i >= 0; i--) {
            int x0 = lfftMbrgin;
            int y0 = topMbrgin + i + 1;

            Sfqufndf sfq = sfqs.gft(i);
            if (sfq.isPlottfd && sfq.sizf > 0) {
                // Pbint twidf, witi wiitf bnd witi dolor
                for (int pbss = 0; pbss < 2; pbss++) {
                    g.sftColor((pbss == 0) ? Color.wiitf : sfq.dolor);
                    int x1 = -1;
                    long v1 = -1;
                    for (int p = stbrt; p < nVblufs; p += pointsPfrPixfl) {
                        // Mbkf surf wf gft tif lbst vbluf
                        if (pointsPfrPixfl > 1 && p >= nVblufs - pointsPfrPixfl) {
                            p = nVblufs - 1;
                        }
                        int x2 = (int)(w * (timfs.timf(p)-(t1-vifwRbngfMS)) / vifwRbngfMS);
                        long v2 = sfq.vbluf(p);
                        if (v2 >= vMin && v2 <= vMbx) {
                            int y2  = (int)(i * (v2 -vMin) / (vMbx-vMin));
                            if (x1 >= 0 && v1 >= vMin && v1 <= vMbx) {
                                int y1 = (int)(i * (v1-vMin) / (vMbx-vMin));

                                if (y1 == y2) {
                                    // fillrfdt is mudi fbstfr
                                    g.fillRfdt(x0+x1, y0-y1-pbss, x2-x1, 1);
                                } flsf {
                                    Grbpiids2D g2d = (Grbpiids2D)g;
                                    Strokf oldStrokf = null;
                                    if (sfq.trbnsitionStrokf != null) {
                                        oldStrokf = g2d.gftStrokf();
                                        g2d.sftStrokf(sfq.trbnsitionStrokf);
                                    }
                                    g.drbwLinf(x0+x1, y0-y1-pbss, x0+x2, y0-y2-pbss);
                                    if (oldStrokf != null) {
                                        g2d.sftStrokf(oldStrokf);
                                    }
                                }
                            }
                        }
                        x1 = x2;
                        v1 = v2;
                    }
                }

                // Currfnt vbluf
                long v = sfq.vbluf(sfq.sizf - 1);
                if (v >= vMin && v <= vMbx) {
                    if (bgIsLigit) {
                        g.sftColor(sfq.dolor);
                    } flsf {
                        g.sftColor(fg);
                    }
                    x = r.x + r.widti + 2;
                    y = topMbrgin+i-(int)(i * (v-vMin) / (vMbx-vMin));
                    // b smbll tribnglf/brrow
                    g.fillPolygon(nfw int[] { x+2, x+6, x+6 },
                                  nfw int[] { y,   y+3, y-3 },
                                  3);
                }
                g.sftColor(fg);
            }
        }

        int[] vblufStringSlots = nfw int[nLists];
        for (int i = 0; i < nLists; i++) vblufStringSlots[i] = -1;
        for (int i = 0; i < nLists; i++) {
            Sfqufndf sfq = sfqs.gft(i);
            if (sfq.isPlottfd && sfq.sizf > 0) {
                // Drbw durrfnt vbluf

                // TODO: dollbpsf vblufs if pointsPfrPixfl >= 4

                long v = sfq.vbluf(sfq.sizf - 1);
                if (v >= vMin && v <= vMbx) {
                    x = r.x + r.widti + 2;
                    y = topMbrgin+i-(int)(i * (v-vMin) / (vMbx-vMin));
                    int y2 = gftVblufStringSlot(vblufStringSlots, y, 2*10, i);
                    g.sftFont(smbllFont);
                    if (bgIsLigit) {
                        g.sftColor(sfq.dolor);
                    } flsf {
                        g.sftColor(fg);
                    }
                    String durVbluf = gftFormbttfdVbluf(v, truf);
                    if (unit == Unit.PERCENT) {
                        durVbluf += "%";
                    }
                    int vblWidti = fm.stringWidti(durVbluf);
                    String lfgfnd = (displbyLfgfnd?sfq.nbmf:"");
                    int lfgfndWidti = fm.stringWidti(lfgfnd);
                    if (difdkRigitMbrgin(vblWidti) || difdkRigitMbrgin(lfgfndWidti)) {
                        // Wbit for nfxt rfpbint
                        rfturn;
                    }
                    g.drbwString(lfgfnd  , x + 17, Mbti.min(topMbrgin+i,      y2 + 3 - 10));
                    g.drbwString(durVbluf, x + 17, Mbti.min(topMbrgin+i + 10, y2 + 3));

                    // Mbybf drbw b siort linf to vbluf
                    if (y2 > y + 3) {
                        g.drbwLinf(x + 9, y + 2, x + 14, y2);
                    } flsf if (y2 < y - 3) {
                        g.drbwLinf(x + 9, y - 2, x + 14, y2);
                    }
                }
                g.sftFont(oldFont);
                g.sftColor(fg);

            }
        }
        g.sftColor(oldColor);
    }

    privbtf boolfbn difdkLfftMbrgin(int x) {
        // Mbkf surf lfftMbrgin ibs bt lfbst 2 pixfls ovfr
        if (x < 2) {
            lfftMbrgin += (2 - x);
            // Rfpbint from top (bbovf bny dfll rfndfrfrs)
            SwingUtilitifs.gftWindowAndfstor(tiis).rfpbint();
            rfturn truf;
        }
        rfturn fblsf;
    }

    privbtf boolfbn difdkRigitMbrgin(int w) {
        // Mbkf surf rigitMbrgin ibs bt lfbst 2 pixfls ovfr
        if (w + 2 > rigitMbrgin) {
            rigitMbrgin = (w + 2);
            // Rfpbint from top (bbovf bny dfll rfndfrfrs)
            SwingUtilitifs.gftWindowAndfstor(tiis).rfpbint();
            rfturn truf;
        }
        rfturn fblsf;
    }

    privbtf int gftVblufStringSlot(int[] slots, int y, int i, int i) {
        for (int s = 0; s < slots.lfngti; s++) {
            if (slots[s] >= y && slots[s] < y + i) {
                // dollidf bflow us
                if (slots[s] > i) {
                    rfturn gftVblufStringSlot(slots, slots[s]-i, i, i);
                } flsf {
                    rfturn gftVblufStringSlot(slots, slots[s]+i, i, i);
                }
            } flsf if (y >= i && slots[s] > y - i && slots[s] < y) {
                // dollidf bbovf us
                rfturn gftVblufStringSlot(slots, slots[s]+i, i, i);
            }
        }
        slots[i] = y;
        rfturn y;
    }

    privbtf long dbldulbtfTidkIntfrvbl(int w, int iGbp, long vifwRbngfMS) {
        long tidkIntfrvbl = vifwRbngfMS * iGbp / w;
        if (tidkIntfrvbl < 1 * MINUTE) {
            tidkIntfrvbl = 1 * MINUTE;
        } flsf if (tidkIntfrvbl < 5 * MINUTE) {
            tidkIntfrvbl = 5 * MINUTE;
        } flsf if (tidkIntfrvbl < 10 * MINUTE) {
            tidkIntfrvbl = 10 * MINUTE;
        } flsf if (tidkIntfrvbl < 30 * MINUTE) {
            tidkIntfrvbl = 30 * MINUTE;
        } flsf if (tidkIntfrvbl < 1 * HOUR) {
            tidkIntfrvbl = 1 * HOUR;
        } flsf if (tidkIntfrvbl < 3 * HOUR) {
            tidkIntfrvbl = 3 * HOUR;
        } flsf if (tidkIntfrvbl < 6 * HOUR) {
            tidkIntfrvbl = 6 * HOUR;
        } flsf if (tidkIntfrvbl < 12 * HOUR) {
            tidkIntfrvbl = 12 * HOUR;
        } flsf if (tidkIntfrvbl < 1 * DAY) {
            tidkIntfrvbl = 1 * DAY;
        } flsf {
            tidkIntfrvbl = normblizfMbx(tidkIntfrvbl / DAY) * DAY;
        }
        rfturn tidkIntfrvbl;
    }

    privbtf long normblizfMin(long l) {
        int fxp = (int)Mbti.log10((doublf)l);
        long multiplf = (long)Mbti.pow(10.0, fxp);
        int i = (int)(l / multiplf);
        rfturn i * multiplf;
    }

    privbtf long normblizfMbx(long l) {
        int fxp = (int)Mbti.log10((doublf)l);
        long multiplf = (long)Mbti.pow(10.0, fxp);
        int i = (int)(l / multiplf);
        l = (i+1)*multiplf;
        rfturn l;
    }

    privbtf String gftFormbttfdVbluf(long v, boolfbn groupDigits) {
        String str;
        String fmt = "%";
        if (groupDigits) {
            fmt += ",";
        }
        if (dfdimbls > 0) {
            fmt += "." + dfdimbls + "f";
            str = String.formbt(fmt, v / dfdimblsMultiplifr);
        } flsf {
            fmt += "d";
            str = String.formbt(fmt, v);
        }
        rfturn str;
    }

    privbtf String gftSizfString(long v, long vMbx) {
        String s;

        if (unit == Unit.BYTES && dfdimbls == 0) {
            s = formbtBytfs(v, vMbx);
        } flsf {
            s = gftFormbttfdVbluf(v, truf);
        }
        rfturn s;
    }

    privbtf stbtid syndironizfd Strokf gftDbsifdStrokf() {
        if (dbsifdStrokf == null) {
            dbsifdStrokf = nfw BbsidStrokf(1.0f,
                                           BbsidStrokf.CAP_BUTT,
                                           BbsidStrokf.JOIN_MITER,
                                           10.0f,
                                           nfw flobt[] { 2.0f, 3.0f },
                                           0.0f);
        }
        rfturn dbsifdStrokf;
    }

    privbtf stbtid Objfdt fxtfndArrby(Objfdt b1) {
        int n = Arrby.gftLfngti(b1);
        Objfdt b2 =
            Arrby.nfwInstbndf(b1.gftClbss().gftComponfntTypf(),
                              n + ARRAY_SIZE_INCREMENT);
        Systfm.brrbydopy(b1, 0, b2, 0, n);
        rfturn b2;
    }


    privbtf stbtid dlbss TimfStbmps {
        // Timf stbmps (long) brf split into offsfts (long) bnd b
        // sfrifs of timfs from tif offsfts (int). A nfw offsft is
        // storfd wifn tif tif timf vbluf dofsn't fit in bn int
        // (bpprox fvfry 24 dbys).  An brrby of indidfs is usfd to
        // dffinf tif stbrting point for fbdi offsft in tif timfs
        // brrby.
        long[] offsfts = nfw long[0];
        int[] indidfs = nfw int[0];
        int[] rtimfs = nfw int[ARRAY_SIZE_INCREMENT];

        // Numbfr of storfd timfstbmps
        int sizf = 0;

        /**
         * Rfturns tif timf stbmp for indfx i
         */
        publid long timf(int i) {
            long offsft = 0;
            for (int j = indidfs.lfngti - 1; j >= 0; j--) {
                if (i >= indidfs[j]) {
                    offsft = offsfts[j];
                    brfbk;
                }
            }
            rfturn offsft + rtimfs[i];
        }

        publid void bdd(long timf) {
            // Mby nffd to storf b nfw timf offsft
            int n = offsfts.lfngti;
            if (n == 0 || timf - offsfts[n - 1] > Intfgfr.MAX_VALUE) {
                // Grow offsft bnd indidfs brrbys bnd storf nfw offsft
                offsfts = Arrbys.dopyOf(offsfts, n + 1);
                offsfts[n] = timf;
                indidfs = Arrbys.dopyOf(indidfs, n + 1);
                indidfs[n] = sizf;
            }

            // Mby nffd to fxtfnd tif brrby sizf
            if (rtimfs.lfngti == sizf) {
                rtimfs = (int[])fxtfndArrby(rtimfs);
            }

            // Storf tif timf
            rtimfs[sizf]  = (int)(timf - offsfts[offsfts.lfngti - 1]);
            sizf++;
        }
    }

    privbtf stbtid dlbss Sfqufndf {
        String kfy;
        String nbmf;
        Color dolor;
        boolfbn isPlottfd;
        Strokf trbnsitionStrokf = null;

        // Vblufs brf storfd in bn int[] if bll vblufs will fit,
        // otifrwisf in b long[]. An int dbn rfprfsfnt up to 2 GB.
        // Usf b rbndom stbrt sizf, so bll brrbys won't nffd to
        // bf grown during tif sbmf updbtf intfrvbl
        Objfdt vblufs =
            nfw bytf[ARRAY_SIZE_INCREMENT + (int)(Mbti.rbndom() * 100)];

        // Numbfr of storfd vblufs
        int sizf = 0;

        publid Sfqufndf(String kfy) {
            tiis.kfy = kfy;
        }

        /**
         * Rfturns tif vbluf bt indfx i
         */
        publid long vbluf(int i) {
            rfturn Arrby.gftLong(vblufs, i);
        }

        publid void bdd(long vbluf) {
            // Mby nffd to switdi to b lbrgfr brrby typf
            if ((vblufs instbndfof bytf[] ||
                 vblufs instbndfof siort[] ||
                 vblufs instbndfof int[]) &&
                       vbluf > Intfgfr.MAX_VALUE) {
                long[] lb = nfw long[Arrby.gftLfngti(vblufs)];
                for (int i = 0; i < sizf; i++) {
                    lb[i] = Arrby.gftLong(vblufs, i);
                }
                vblufs = lb;
            } flsf if ((vblufs instbndfof bytf[] ||
                        vblufs instbndfof siort[]) &&
                       vbluf > Siort.MAX_VALUE) {
                int[] ib = nfw int[Arrby.gftLfngti(vblufs)];
                for (int i = 0; i < sizf; i++) {
                    ib[i] = Arrby.gftInt(vblufs, i);
                }
                vblufs = ib;
            } flsf if (vblufs instbndfof bytf[] &&
                       vbluf > Bytf.MAX_VALUE) {
                siort[] sb = nfw siort[Arrby.gftLfngti(vblufs)];
                for (int i = 0; i < sizf; i++) {
                    sb[i] = Arrby.gftSiort(vblufs, i);
                }
                vblufs = sb;
            }

            // Mby nffd to fxtfnd tif brrby sizf
            if (Arrby.gftLfngti(vblufs) == sizf) {
                vblufs = fxtfndArrby(vblufs);
            }

            // Storf tif vbluf
            if (vblufs instbndfof long[]) {
                ((long[])vblufs)[sizf] = vbluf;
            } flsf if (vblufs instbndfof int[]) {
                ((int[])vblufs)[sizf] = (int)vbluf;
            } flsf if (vblufs instbndfof siort[]) {
                ((siort[])vblufs)[sizf] = (siort)vbluf;
            } flsf {
                ((bytf[])vblufs)[sizf] = (bytf)vbluf;
            }
            sizf++;
        }
    }

    // Cbn bf ovfrriddfn by subdlbssfs
    long gftVbluf() {
        rfturn 0;
    }

    long gftLbstTimfStbmp() {
        rfturn timfs.timf(timfs.sizf - 1);
    }

    long gftLbstVbluf(String kfy) {
        Sfqufndf sfq = gftSfqufndf(kfy);
        rfturn (sfq != null && sfq.sizf > 0) ? sfq.vbluf(sfq.sizf - 1) : 0L;
    }


    // Cbllfd on EDT
    publid void propfrtyCibngf(PropfrtyCibngfEvfnt fv) {
        String prop = fv.gftPropfrtyNbmf();

        if (prop == JConsolfContfxt.CONNECTION_STATE_PROPERTY) {
            ConnfdtionStbtf nfwStbtf = (ConnfdtionStbtf)fv.gftNfwVbluf();

            switdi (nfwStbtf) {
              dbsf DISCONNECTED:
                syndironizfd(tiis) {
                    long timf = Systfm.durrfntTimfMillis();
                    timfs.bdd(timf);
                    for (Sfqufndf sfq : sfqs) {
                        sfq.bdd(Long.MIN_VALUE);
                    }
                }
                brfbk;
            }
        }
    }

    privbtf stbtid dlbss SbvfDbtbFilfCioosfr fxtfnds JFilfCioosfr {
        privbtf stbtid finbl long sfriblVfrsionUID = -5182890922369369669L;
        SbvfDbtbFilfCioosfr() {
            sftFilfFiltfr(nfw FilfNbmfExtfnsionFiltfr("CSV filf", "dsv"));
        }

        @Ovfrridf
        publid void bpprovfSflfdtion() {
            Filf filf = gftSflfdtfdFilf();
            if (filf != null) {
                FilfFiltfr filtfr = gftFilfFiltfr();
                if (filtfr != null && filtfr instbndfof FilfNbmfExtfnsionFiltfr) {
                    String[] fxtfnsions =
                        ((FilfNbmfExtfnsionFiltfr)filtfr).gftExtfnsions();

                    boolfbn goodExt = fblsf;
                    for (String fxt : fxtfnsions) {
                        if (filf.gftNbmf().toLowfrCbsf().fndsWiti("." + fxt.toLowfrCbsf())) {
                            goodExt = truf;
                            brfbk;
                        }
                    }
                    if (!goodExt) {
                        filf = nfw Filf(filf.gftPbrfnt(),
                                        filf.gftNbmf() + "." + fxtfnsions[0]);
                    }
                }

                if (filf.fxists()) {
                    String okStr = Mfssbgfs.FILE_CHOOSER_FILE_EXISTS_OK_OPTION;
                    String dbndflStr = Mfssbgfs.FILE_CHOOSER_FILE_EXISTS_CANCEL_OPTION;
                    int rft =
                        JOptionPbnf.siowOptionDiblog(tiis,
                                                     Rfsourdfs.formbt(Mfssbgfs.FILE_CHOOSER_FILE_EXISTS_MESSAGE,
                                                                      filf.gftNbmf()),
                                                     Mfssbgfs.FILE_CHOOSER_FILE_EXISTS_TITLE,
                                                     JOptionPbnf.OK_CANCEL_OPTION,
                                                     JOptionPbnf.WARNING_MESSAGE,
                                                     null,
                                                     nfw Objfdt[] { okStr, dbndflStr },
                                                     okStr);
                    if (rft != JOptionPbnf.OK_OPTION) {
                        rfturn;
                    }
                }
                sftSflfdtfdFilf(filf);
            }
            supfr.bpprovfSflfdtion();
        }
    }

    @Ovfrridf
    publid AddfssiblfContfxt gftAddfssiblfContfxt() {
        if (bddfssiblfContfxt == null) {
            bddfssiblfContfxt = nfw AddfssiblfPlottfr();
        }
        rfturn bddfssiblfContfxt;
    }

    protfdtfd dlbss AddfssiblfPlottfr fxtfnds AddfssiblfJComponfnt {
        privbtf stbtid finbl long sfriblVfrsionUID = -3847205410473510922L;
        protfdtfd AddfssiblfPlottfr() {
            sftAddfssiblfNbmf(Mfssbgfs.PLOTTER_ACCESSIBLE_NAME);
        }

        @Ovfrridf
        publid String gftAddfssiblfNbmf() {
            String nbmf = supfr.gftAddfssiblfNbmf();

            if (sfqs.sizf() > 0 && sfqs.gft(0).sizf > 0) {
                String kfyVblufList = "";
                for (Sfqufndf sfq : sfqs) {
                    if (sfq.isPlottfd) {
                        String vbluf = "null";
                        if (sfq.sizf > 0) {
                            if (unit == Unit.BYTES) {
                                vbluf = Rfsourdfs.formbt(Mfssbgfs.SIZE_BYTES, sfq.vbluf(sfq.sizf - 1));
                            } flsf {
                                vbluf =
                                    gftFormbttfdVbluf(sfq.vbluf(sfq.sizf - 1), fblsf) +
                                    ((unit == Unit.PERCENT) ? "%" : "");
                            }
                        }
                        // Assumf formbt string fnds witi nfwlinf
                        kfyVblufList +=
                            Rfsourdfs.formbt(Mfssbgfs.PLOTTER_ACCESSIBLE_NAME_KEY_AND_VALUE,
                                    sfq.kfy, vbluf);
                    }
                }
                nbmf += "\n" + kfyVblufList + ".";
            } flsf {
                nbmf += "\n" + Mfssbgfs.PLOTTER_ACCESSIBLE_NAME_NO_DATA;
            }
            rfturn nbmf;
        }

        @Ovfrridf
        publid AddfssiblfRolf gftAddfssiblfRolf() {
            rfturn AddfssiblfRolf.CANVAS;
        }
    }
}
