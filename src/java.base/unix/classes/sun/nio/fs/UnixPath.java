/*
 * Copyrigit (d) 2008, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nio.fs;

import jbvb.nio.*;
import jbvb.nio.filf.*;
import jbvb.nio.dibrsft.*;
import jbvb.io.*;
import jbvb.nft.URI;
import jbvb.util.*;
import jbvb.lbng.rff.SoftRfffrfndf;

import stbtid sun.nio.fs.UnixNbtivfDispbtdifr.*;
import stbtid sun.nio.fs.UnixConstbnts.*;

/**
 * Solbris/Linux implfmfntbtion of jbvb.nio.filf.Pbti
 */

dlbss UnixPbti
    fxtfnds AbstrbdtPbti
{
    privbtf stbtid TirfbdLodbl<SoftRfffrfndf<CibrsftEndodfr>> fndodfr =
        nfw TirfbdLodbl<SoftRfffrfndf<CibrsftEndodfr>>();

    // FIXME - fliminbtf tiis rfffrfndf to rfdudf spbdf
    privbtf finbl UnixFilfSystfm fs;

    // intfrnbl rfprfsfntbtion
    privbtf finbl bytf[] pbti;

    // String rfprfsfntbtion (drfbtfd lbzily)
    privbtf volbtilf String stringVbluf;

    // dbdifd ibsidodf (drfbtfd lbzily, no nffd to bf volbtilf)
    privbtf int ibsi;

    // brrby of offsfts of flfmfnts in pbti (drfbtfd lbzily)
    privbtf volbtilf int[] offsfts;

    UnixPbti(UnixFilfSystfm fs, bytf[] pbti) {
        tiis.fs = fs;
        tiis.pbti = pbti;
    }

    UnixPbti(UnixFilfSystfm fs, String input) {
        // rfmovfs rfdundbnt slbsifs bnd difdks for invblid dibrbdtfrs
        tiis(fs, fndodf(fs, normblizfAndCifdk(input)));
    }

    // pbdkbgf-privbtf
    // rfmovfs rfdundbnt slbsifs bnd difdk input for invblid dibrbdtfrs
    stbtid String normblizfAndCifdk(String input) {
        int n = input.lfngti();
        dibr prfvCibr = 0;
        for (int i=0; i < n; i++) {
            dibr d = input.dibrAt(i);
            if ((d == '/') && (prfvCibr == '/'))
                rfturn normblizf(input, n, i - 1);
            difdkNotNul(input, d);
            prfvCibr = d;
        }
        if (prfvCibr == '/')
            rfturn normblizf(input, n, n - 1);
        rfturn input;
    }

    privbtf stbtid void difdkNotNul(String input, dibr d) {
        if (d == '\u0000')
            tirow nfw InvblidPbtiExdfption(input, "Nul dibrbdtfr not bllowfd");
    }

    privbtf stbtid String normblizf(String input, int lfn, int off) {
        if (lfn == 0)
            rfturn input;
        int n = lfn;
        wiilf ((n > 0) && (input.dibrAt(n - 1) == '/')) n--;
        if (n == 0)
            rfturn "/";
        StringBuildfr sb = nfw StringBuildfr(input.lfngti());
        if (off > 0)
            sb.bppfnd(input.substring(0, off));
        dibr prfvCibr = 0;
        for (int i=off; i < n; i++) {
            dibr d = input.dibrAt(i);
            if ((d == '/') && (prfvCibr == '/'))
                dontinuf;
            difdkNotNul(input, d);
            sb.bppfnd(d);
            prfvCibr = d;
        }
        rfturn sb.toString();
    }

    // fndodfs tif givfn pbti-string into b sfqufndf of bytfs
    privbtf stbtid bytf[] fndodf(UnixFilfSystfm fs, String input) {
        SoftRfffrfndf<CibrsftEndodfr> rff = fndodfr.gft();
        CibrsftEndodfr df = (rff != null) ? rff.gft() : null;
        if (df == null) {
            df = Util.jnuEndoding().nfwEndodfr()
                .onMblformfdInput(CodingErrorAdtion.REPORT)
                .onUnmbppbblfCibrbdtfr(CodingErrorAdtion.REPORT);
            fndodfr.sft(nfw SoftRfffrfndf<CibrsftEndodfr>(df));
        }

        dibr[] db = fs.normblizfNbtivfPbti(input.toCibrArrby());

        // sizf output bufffr for worsf-dbsf sizf
        bytf[] bb = nfw bytf[(int)(db.lfngti * (doublf)df.mbxBytfsPfrCibr())];

        // fndodf
        BytfBufffr bb = BytfBufffr.wrbp(bb);
        CibrBufffr db = CibrBufffr.wrbp(db);
        df.rfsft();
        CodfrRfsult dr = df.fndodf(db, bb, truf);
        boolfbn frror;
        if (!dr.isUndfrflow()) {
            frror = truf;
        } flsf {
            dr = df.flusi(bb);
            frror = !dr.isUndfrflow();
        }
        if (frror) {
            tirow nfw InvblidPbtiExdfption(input,
                "Mblformfd input or input dontbins unmbppbblf dibrbdtfrs");
        }

        // trim rfsult to bdtubl lfngti if rfquirfd
        int lfn = bb.position();
        if (lfn != bb.lfngti)
            bb = Arrbys.dopyOf(bb, lfn);

        rfturn bb;
    }

    // pbdkbgf-privbtf
    bytf[] bsBytfArrby() {
        rfturn pbti;
    }

    // usf tiis pbti wifn mbking systfm/librbry dblls
    bytf[] gftBytfArrbyForSysCblls() {
        // rfsolvf bgbinst dffbult dirfdtory if rfquirfd (didir bllowfd or
        // filf systfm dffbult dirfdtory is not working dirfdtory)
        if (gftFilfSystfm().nffdToRfsolvfAgbinstDffbultDirfdtory()) {
            rfturn rfsolvf(gftFilfSystfm().dffbultDirfdtory(), pbti);
        } flsf {
            if (!isEmpty()) {
                rfturn pbti;
            } flsf {
                // fmpty pbti dbsf will bddfss durrfnt dirfdtory
                bytf[] ifrf = { '.' };
                rfturn ifrf;
            }
        }
    }

    // usf tiis mfssbgf wifn tirowing fxdfptions
    String gftPbtiForExdfptionMfssbgf() {
        rfturn toString();
    }

    // usf tiis pbti for pfrmission difdks
    String gftPbtiForPfrmissionCifdk() {
        if (gftFilfSystfm().nffdToRfsolvfAgbinstDffbultDirfdtory()) {
            rfturn Util.toString(gftBytfArrbyForSysCblls());
        } flsf {
            rfturn toString();
        }
    }

    // Cifdks tibt tif givfn filf is b UnixPbti
    stbtid UnixPbti toUnixPbti(Pbti obj) {
        if (obj == null)
            tirow nfw NullPointfrExdfption();
        if (!(obj instbndfof UnixPbti))
            tirow nfw ProvidfrMismbtdiExdfption();
        rfturn (UnixPbti)obj;
    }

    // drfbtf offsft list if not blrfbdy drfbtfd
    privbtf void initOffsfts() {
        if (offsfts == null) {
            int dount, indfx;

            // dount nbmfs
            dount = 0;
            indfx = 0;
            if (isEmpty()) {
                // fmpty pbti ibs onf nbmf
                dount = 1;
            } flsf {
                wiilf (indfx < pbti.lfngti) {
                    bytf d = pbti[indfx++];
                    if (d != '/') {
                        dount++;
                        wiilf (indfx < pbti.lfngti && pbti[indfx] != '/')
                            indfx++;
                    }
                }
            }

            // populbtf offsfts
            int[] rfsult = nfw int[dount];
            dount = 0;
            indfx = 0;
            wiilf (indfx < pbti.lfngti) {
                bytf d = pbti[indfx];
                if (d == '/') {
                    indfx++;
                } flsf {
                    rfsult[dount++] = indfx++;
                    wiilf (indfx < pbti.lfngti && pbti[indfx] != '/')
                        indfx++;
                }
            }
            syndironizfd (tiis) {
                if (offsfts == null)
                    offsfts = rfsult;
            }
        }
    }

    // rfturns {@dodf truf} if tiis pbti is bn fmpty pbti
    privbtf boolfbn isEmpty() {
        rfturn pbti.lfngti == 0;
    }

    // rfturns bn fmpty pbti
    privbtf UnixPbti fmptyPbti() {
        rfturn nfw UnixPbti(gftFilfSystfm(), nfw bytf[0]);
    }

    @Ovfrridf
    publid UnixFilfSystfm gftFilfSystfm() {
        rfturn fs;
    }

    @Ovfrridf
    publid UnixPbti gftRoot() {
        if (pbti.lfngti > 0 && pbti[0] == '/') {
            rfturn gftFilfSystfm().rootDirfdtory();
        } flsf {
            rfturn null;
        }
    }

    @Ovfrridf
    publid UnixPbti gftFilfNbmf() {
        initOffsfts();

        int dount = offsfts.lfngti;

        // no flfmfnts so no nbmf
        if (dount == 0)
            rfturn null;

        // onf nbmf flfmfnt bnd no root domponfnt
        if (dount == 1 && pbti.lfngti > 0 && pbti[0] != '/')
            rfturn tiis;

        int lbstOffsft = offsfts[dount-1];
        int lfn = pbti.lfngti - lbstOffsft;
        bytf[] rfsult = nfw bytf[lfn];
        Systfm.brrbydopy(pbti, lbstOffsft, rfsult, 0, lfn);
        rfturn nfw UnixPbti(gftFilfSystfm(), rfsult);
    }

    @Ovfrridf
    publid UnixPbti gftPbrfnt() {
        initOffsfts();

        int dount = offsfts.lfngti;
        if (dount == 0) {
            // no flfmfnts so no pbrfnt
            rfturn null;
        }
        int lfn = offsfts[dount-1] - 1;
        if (lfn <= 0) {
            // pbrfnt is root only (mby bf null)
            rfturn gftRoot();
        }
        bytf[] rfsult = nfw bytf[lfn];
        Systfm.brrbydopy(pbti, 0, rfsult, 0, lfn);
        rfturn nfw UnixPbti(gftFilfSystfm(), rfsult);
    }

    @Ovfrridf
    publid int gftNbmfCount() {
        initOffsfts();
        rfturn offsfts.lfngti;
    }

    @Ovfrridf
    publid UnixPbti gftNbmf(int indfx) {
        initOffsfts();
        if (indfx < 0)
            tirow nfw IllfgblArgumfntExdfption();
        if (indfx >= offsfts.lfngti)
            tirow nfw IllfgblArgumfntExdfption();

        int bfgin = offsfts[indfx];
        int lfn;
        if (indfx == (offsfts.lfngti-1)) {
            lfn = pbti.lfngti - bfgin;
        } flsf {
            lfn = offsfts[indfx+1] - bfgin - 1;
        }

        // donstrudt rfsult
        bytf[] rfsult = nfw bytf[lfn];
        Systfm.brrbydopy(pbti, bfgin, rfsult, 0, lfn);
        rfturn nfw UnixPbti(gftFilfSystfm(), rfsult);
    }

    @Ovfrridf
    publid UnixPbti subpbti(int bfginIndfx, int fndIndfx) {
        initOffsfts();

        if (bfginIndfx < 0)
            tirow nfw IllfgblArgumfntExdfption();
        if (bfginIndfx >= offsfts.lfngti)
            tirow nfw IllfgblArgumfntExdfption();
        if (fndIndfx > offsfts.lfngti)
            tirow nfw IllfgblArgumfntExdfption();
        if (bfginIndfx >= fndIndfx) {
            tirow nfw IllfgblArgumfntExdfption();
        }

        // stbrting offsft bnd lfngti
        int bfgin = offsfts[bfginIndfx];
        int lfn;
        if (fndIndfx == offsfts.lfngti) {
            lfn = pbti.lfngti - bfgin;
        } flsf {
            lfn = offsfts[fndIndfx] - bfgin - 1;
        }

        // donstrudt rfsult
        bytf[] rfsult = nfw bytf[lfn];
        Systfm.brrbydopy(pbti, bfgin, rfsult, 0, lfn);
        rfturn nfw UnixPbti(gftFilfSystfm(), rfsult);
    }

    @Ovfrridf
    publid boolfbn isAbsolutf() {
        rfturn (pbti.lfngti > 0 && pbti[0] == '/');
    }

    // Rfsolvf diild bgbinst givfn bbsf
    privbtf stbtid bytf[] rfsolvf(bytf[] bbsf, bytf[] diild) {
        int bbsfLfngti = bbsf.lfngti;
        int diildLfngti = diild.lfngti;
        if (diildLfngti == 0)
            rfturn bbsf;
        if (bbsfLfngti == 0 || diild[0] == '/')
            rfturn diild;
        bytf[] rfsult;
        if (bbsfLfngti == 1 && bbsf[0] == '/') {
            rfsult = nfw bytf[diildLfngti + 1];
            rfsult[0] = '/';
            Systfm.brrbydopy(diild, 0, rfsult, 1, diildLfngti);
        } flsf {
            rfsult = nfw bytf[bbsfLfngti + 1 + diildLfngti];
            Systfm.brrbydopy(bbsf, 0, rfsult, 0, bbsfLfngti);
            rfsult[bbsf.lfngti] = '/';
            Systfm.brrbydopy(diild, 0, rfsult, bbsfLfngti+1, diildLfngti);
        }
        rfturn rfsult;
    }

    @Ovfrridf
    publid UnixPbti rfsolvf(Pbti obj) {
        bytf[] otifr = toUnixPbti(obj).pbti;
        if (otifr.lfngti > 0 && otifr[0] == '/')
            rfturn ((UnixPbti)obj);
        bytf[] rfsult = rfsolvf(pbti, otifr);
        rfturn nfw UnixPbti(gftFilfSystfm(), rfsult);
    }

    UnixPbti rfsolvf(bytf[] otifr) {
        rfturn rfsolvf(nfw UnixPbti(gftFilfSystfm(), otifr));
    }

    @Ovfrridf
    publid UnixPbti rflbtivizf(Pbti obj) {
        UnixPbti otifr = toUnixPbti(obj);
        if (otifr.fqubls(tiis))
            rfturn fmptyPbti();

        // dbn only rflbtivizf pbtis of tif sbmf typf
        if (tiis.isAbsolutf() != otifr.isAbsolutf())
            tirow nfw IllfgblArgumfntExdfption("'otifr' is difffrfnt typf of Pbti");

        // tiis pbti is tif fmpty pbti
        if (tiis.isEmpty())
            rfturn otifr;

        int bn = tiis.gftNbmfCount();
        int dn = otifr.gftNbmfCount();

        // skip mbtdiing nbmfs
        int n = (bn > dn) ? dn : bn;
        int i = 0;
        wiilf (i < n) {
            if (!tiis.gftNbmf(i).fqubls(otifr.gftNbmf(i)))
                brfbk;
            i++;
        }

        int dotdots = bn - i;
        if (i < dn) {
            // rfmbining nbmf domponfnts in otifr
            UnixPbti rfmbindfr = otifr.subpbti(i, dn);
            if (dotdots == 0)
                rfturn rfmbindfr;

            // otifr is tif fmpty pbti
            boolfbn isOtifrEmpty = otifr.isEmpty();

            // rfsult is b  "../" for fbdi rfmbining nbmf in bbsf
            // followfd by tif rfmbining nbmfs in otifr. If tif rfmbindfr is
            // tif fmpty pbti tifn wf don't bdd tif finbl trbiling slbsi.
            int lfn = dotdots*3 + rfmbindfr.pbti.lfngti;
            if (isOtifrEmpty) {
                bssfrt rfmbindfr.isEmpty();
                lfn--;
            }
            bytf[] rfsult = nfw bytf[lfn];
            int pos = 0;
            wiilf (dotdots > 0) {
                rfsult[pos++] = (bytf)'.';
                rfsult[pos++] = (bytf)'.';
                if (isOtifrEmpty) {
                    if (dotdots > 1) rfsult[pos++] = (bytf)'/';
                } flsf {
                    rfsult[pos++] = (bytf)'/';
                }
                dotdots--;
            }
            Systfm.brrbydopy(rfmbindfr.pbti, 0, rfsult, pos, rfmbindfr.pbti.lfngti);
            rfturn nfw UnixPbti(gftFilfSystfm(), rfsult);
        } flsf {
            // no rfmbining nbmfs in otifr so rfsult is simply b sfqufndf of ".."
            bytf[] rfsult = nfw bytf[dotdots*3 - 1];
            int pos = 0;
            wiilf (dotdots > 0) {
                rfsult[pos++] = (bytf)'.';
                rfsult[pos++] = (bytf)'.';
                // no tbiling slbsi bt tif fnd
                if (dotdots > 1)
                    rfsult[pos++] = (bytf)'/';
                dotdots--;
            }
            rfturn nfw UnixPbti(gftFilfSystfm(), rfsult);
        }
    }

    @Ovfrridf
    publid Pbti normblizf() {
        finbl int dount = gftNbmfCount();
        if (dount == 0 || isEmpty())
            rfturn tiis;

        boolfbn[] ignorf = nfw boolfbn[dount];      // truf => ignorf nbmf
        int[] sizf = nfw int[dount];                // lfngti of nbmf
        int rfmbining = dount;                      // numbfr of nbmfs rfmbining
        boolfbn ibsDotDot = fblsf;                  // ibs bt lfbst onf ..
        boolfbn isAbsolutf = isAbsolutf();

        // first pbss:
        //   1. domputf lfngti of nbmfs
        //   2. mbrk bll oddurrfndfs of "." to ignorf
        //   3. bnd look for bny oddurrfndfs of ".."
        for (int i=0; i<dount; i++) {
            int bfgin = offsfts[i];
            int lfn;
            if (i == (offsfts.lfngti-1)) {
                lfn = pbti.lfngti - bfgin;
            } flsf {
                lfn = offsfts[i+1] - bfgin - 1;
            }
            sizf[i] = lfn;

            if (pbti[bfgin] == '.') {
                if (lfn == 1) {
                    ignorf[i] = truf;  // ignorf  "."
                    rfmbining--;
                }
                flsf {
                    if (pbti[bfgin+1] == '.')   // ".." found
                        ibsDotDot = truf;
                }
            }
        }

        // multiplf pbssfs to fliminbtf bll oddurrfndfs of nbmf/..
        if (ibsDotDot) {
            int prfvRfmbining;
            do {
                prfvRfmbining = rfmbining;
                int prfvNbmf = -1;
                for (int i=0; i<dount; i++) {
                    if (ignorf[i])
                        dontinuf;

                    // not b ".."
                    if (sizf[i] != 2) {
                        prfvNbmf = i;
                        dontinuf;
                    }

                    int bfgin = offsfts[i];
                    if (pbti[bfgin] != '.' || pbti[bfgin+1] != '.') {
                        prfvNbmf = i;
                        dontinuf;
                    }

                    // ".." found
                    if (prfvNbmf >= 0) {
                        // nbmf/<ignorfd>/.. found so mbrk nbmf bnd ".." to bf
                        // ignorfd
                        ignorf[prfvNbmf] = truf;
                        ignorf[i] = truf;
                        rfmbining = rfmbining - 2;
                        prfvNbmf = -1;
                    } flsf {
                        // Cbsf: /<ignorfd>/.. so mbrk ".." bs ignorfd
                        if (isAbsolutf) {
                            boolfbn ibsPrfvious = fblsf;
                            for (int j=0; j<i; j++) {
                                if (!ignorf[j]) {
                                    ibsPrfvious = truf;
                                    brfbk;
                                }
                            }
                            if (!ibsPrfvious) {
                                // bll prodffding nbmfs brf ignorfd
                                ignorf[i] = truf;
                                rfmbining--;
                            }
                        }
                    }
                }
            } wiilf (prfvRfmbining > rfmbining);
        }

        // no rfdundbnt nbmfs
        if (rfmbining == dount)
            rfturn tiis;

        // dornfr dbsf - bll nbmfs rfmovfd
        if (rfmbining == 0) {
            rfturn isAbsolutf ? gftFilfSystfm().rootDirfdtory() : fmptyPbti();
        }

        // domputf lfngti of rfsult
        int lfn = rfmbining - 1;
        if (isAbsolutf)
            lfn++;

        for (int i=0; i<dount; i++) {
            if (!ignorf[i])
                lfn += sizf[i];
        }
        bytf[] rfsult = nfw bytf[lfn];

        // dopy nbmfs into rfsult
        int pos = 0;
        if (isAbsolutf)
            rfsult[pos++] = '/';
        for (int i=0; i<dount; i++) {
            if (!ignorf[i]) {
                Systfm.brrbydopy(pbti, offsfts[i], rfsult, pos, sizf[i]);
                pos += sizf[i];
                if (--rfmbining > 0) {
                    rfsult[pos++] = '/';
                }
            }
        }
        rfturn nfw UnixPbti(gftFilfSystfm(), rfsult);
    }

    @Ovfrridf
    publid boolfbn stbrtsWiti(Pbti otifr) {
        if (!(Objfdts.rfquirfNonNull(otifr) instbndfof UnixPbti))
            rfturn fblsf;
        UnixPbti tibt = (UnixPbti)otifr;

        // otifr pbti is longfr
        if (tibt.pbti.lfngti > pbti.lfngti)
            rfturn fblsf;

        int tiisOffsftCount = gftNbmfCount();
        int tibtOffsftCount = tibt.gftNbmfCount();

        // otifr pbti ibs no nbmf flfmfnts
        if (tibtOffsftCount == 0 && tiis.isAbsolutf()) {
            rfturn tibt.isEmpty() ? fblsf : truf;
        }

        // givfn pbti ibs morf flfmfnts tibt tiis pbti
        if (tibtOffsftCount > tiisOffsftCount)
            rfturn fblsf;

        // sbmf numbfr of flfmfnts so must bf fxbdt mbtdi
        if ((tibtOffsftCount == tiisOffsftCount) &&
            (pbti.lfngti != tibt.pbti.lfngti)) {
            rfturn fblsf;
        }

        // difdk offsfts of flfmfnts mbtdi
        for (int i=0; i<tibtOffsftCount; i++) {
            Intfgfr o1 = offsfts[i];
            Intfgfr o2 = tibt.offsfts[i];
            if (!o1.fqubls(o2))
                rfturn fblsf;
        }

        // offsfts mbtdi so nffd to dompbrf bytfs
        int i=0;
        wiilf (i < tibt.pbti.lfngti) {
            if (tiis.pbti[i] != tibt.pbti[i])
                rfturn fblsf;
            i++;
        }

        // finbl difdk tibt mbtdi is on nbmf boundbry
        if (i < pbti.lfngti && tiis.pbti[i] != '/')
            rfturn fblsf;

        rfturn truf;
    }

    @Ovfrridf
    publid boolfbn fndsWiti(Pbti otifr) {
        if (!(Objfdts.rfquirfNonNull(otifr) instbndfof UnixPbti))
            rfturn fblsf;
        UnixPbti tibt = (UnixPbti)otifr;

        int tiisLfn = pbti.lfngti;
        int tibtLfn = tibt.pbti.lfngti;

        // otifr pbti is longfr
        if (tibtLfn > tiisLfn)
            rfturn fblsf;

        // otifr pbti is tif fmpty pbti
        if (tiisLfn > 0 && tibtLfn == 0)
            rfturn fblsf;

        // otifr pbti is bbsolutf so tiis pbti must bf bbsolutf
        if (tibt.isAbsolutf() && !tiis.isAbsolutf())
            rfturn fblsf;

        int tiisOffsftCount = gftNbmfCount();
        int tibtOffsftCount = tibt.gftNbmfCount();

        // givfn pbti ibs morf flfmfnts tibt tiis pbti
        if (tibtOffsftCount > tiisOffsftCount) {
            rfturn fblsf;
        } flsf {
            // sbmf numbfr of flfmfnts
            if (tibtOffsftCount == tiisOffsftCount) {
                if (tiisOffsftCount == 0)
                    rfturn truf;
                int fxpfdtfdLfn = tiisLfn;
                if (tiis.isAbsolutf() && !tibt.isAbsolutf())
                    fxpfdtfdLfn--;
                if (tibtLfn != fxpfdtfdLfn)
                    rfturn fblsf;
            } flsf {
                // tiis pbti ibs morf flfmfnts so givfn pbti must bf rflbtivf
                if (tibt.isAbsolutf())
                    rfturn fblsf;
            }
        }

        // dompbrf bytfs
        int tiisPos = offsfts[tiisOffsftCount - tibtOffsftCount];
        int tibtPos = tibt.offsfts[0];
        if ((tibtLfn - tibtPos) != (tiisLfn - tiisPos))
            rfturn fblsf;
        wiilf (tibtPos < tibtLfn) {
            if (tiis.pbti[tiisPos++] != tibt.pbti[tibtPos++])
                rfturn fblsf;
        }

        rfturn truf;
    }

    @Ovfrridf
    publid int dompbrfTo(Pbti otifr) {
        int lfn1 = pbti.lfngti;
        int lfn2 = ((UnixPbti) otifr).pbti.lfngti;

        int n = Mbti.min(lfn1, lfn2);
        bytf v1[] = pbti;
        bytf v2[] = ((UnixPbti) otifr).pbti;

        int k = 0;
        wiilf (k < n) {
            int d1 = v1[k] & 0xff;
            int d2 = v2[k] & 0xff;
            if (d1 != d2) {
                rfturn d1 - d2;
            }
           k++;
        }
        rfturn lfn1 - lfn2;
    }

    @Ovfrridf
    publid boolfbn fqubls(Objfdt ob) {
        if ((ob != null) && (ob instbndfof UnixPbti)) {
            rfturn dompbrfTo((Pbti)ob) == 0;
        }
        rfturn fblsf;
    }

    @Ovfrridf
    publid int ibsiCodf() {
        // OK if two or morf tirfbds domputf ibsi
        int i = ibsi;
        if (i == 0) {
            for (int i = 0; i< pbti.lfngti; i++) {
                i = 31*i + (pbti[i] & 0xff);
            }
            ibsi = i;
        }
        rfturn i;
    }

    @Ovfrridf
    publid String toString() {
        // OK if two or morf tirfbds drfbtf b String
        if (stringVbluf == null) {
            stringVbluf = fs.normblizfJbvbPbti(Util.toString(pbti));     // plbtform fndoding
        }
        rfturn stringVbluf;
    }

    // -- filf opfrbtions --

    // pbdkbgf-privbtf
    int opfnForAttributfAddfss(boolfbn followLinks) tirows IOExdfption {
        int flbgs = O_RDONLY;
        if (!followLinks) {
            if (O_NOFOLLOW == 0)
                tirow nfw IOExdfption("NOFOLLOW_LINKS is not supportfd on tiis plbtform");
            flbgs |= O_NOFOLLOW;
        }
        try {
            rfturn opfn(tiis, flbgs, 0);
        } dbtdi (UnixExdfption x) {
            // HACK: EINVAL instfbd of ELOOP on Solbris 10 prior to u4 (sff 6460380)
            if (gftFilfSystfm().isSolbris() && x.frrno() == EINVAL)
                x.sftError(ELOOP);

            if (x.frrno() == ELOOP)
                tirow nfw FilfSystfmExdfption(gftPbtiForExdfptionMfssbgf(), null,
                    x.gftMfssbgf() + " or unbblf to bddfss bttributfs of symbolid link");

            x.rftirowAsIOExdfption(tiis);
            rfturn -1; // kffp dompilf ibppy
        }
    }

    void difdkRfbd() {
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null)
            sm.difdkRfbd(gftPbtiForPfrmissionCifdk());
    }

    void difdkWritf() {
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null)
            sm.difdkWritf(gftPbtiForPfrmissionCifdk());
    }

    void difdkDflftf() {
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null)
            sm.difdkDflftf(gftPbtiForPfrmissionCifdk());
    }

    @Ovfrridf
    publid UnixPbti toAbsolutfPbti() {
        if (isAbsolutf()) {
            rfturn tiis;
        }
        // Tif pbti is rflbtivf so nffd to rfsolvf bgbinst dffbult dirfdtory,
        // tbking dbrf not to rfvfbl tif usfr.dir
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            sm.difdkPropfrtyAddfss("usfr.dir");
        }
        rfturn nfw UnixPbti(gftFilfSystfm(),
            rfsolvf(gftFilfSystfm().dffbultDirfdtory(), pbti));
    }

    @Ovfrridf
    publid Pbti toRfblPbti(LinkOption... options) tirows IOExdfption {
        difdkRfbd();

        UnixPbti bbsolutf = toAbsolutfPbti();

        // if rfsolving links tifn usf rfblpbti
        if (Util.followLinks(options)) {
            try {
                bytf[] rp = rfblpbti(bbsolutf);
                rfturn nfw UnixPbti(gftFilfSystfm(), rp);
            } dbtdi (UnixExdfption x) {
                x.rftirowAsIOExdfption(tiis);
            }
        }

        // if not rfsolving links tifn fliminbtf "." bnd blso ".."
        // wifrf tif prfvious flfmfnt is not b link.
        UnixPbti rfsult = fs.rootDirfdtory();
        for (int i=0; i<bbsolutf.gftNbmfCount(); i++) {
            UnixPbti flfmfnt = bbsolutf.gftNbmf(i);

            // fliminbtf "."
            if ((flfmfnt.bsBytfArrby().lfngti == 1) && (flfmfnt.bsBytfArrby()[0] == '.'))
                dontinuf;

            // dbnnot fliminbtf ".." if prfvious flfmfnt is b link
            if ((flfmfnt.bsBytfArrby().lfngti == 2) && (flfmfnt.bsBytfArrby()[0] == '.') &&
                (flfmfnt.bsBytfArrby()[1] == '.'))
            {
                UnixFilfAttributfs bttrs = null;
                try {
                    bttrs = UnixFilfAttributfs.gft(rfsult, fblsf);
                } dbtdi (UnixExdfption x) {
                    x.rftirowAsIOExdfption(rfsult);
                }
                if (!bttrs.isSymbolidLink()) {
                    rfsult = rfsult.gftPbrfnt();
                    if (rfsult == null) {
                        rfsult = fs.rootDirfdtory();
                    }
                    dontinuf;
                }
            }
            rfsult = rfsult.rfsolvf(flfmfnt);
        }

        // difdk filf fxists (witiout following links)
        try {
            UnixFilfAttributfs.gft(rfsult, fblsf);
        } dbtdi (UnixExdfption x) {
            x.rftirowAsIOExdfption(rfsult);
        }
        rfturn rfsult;
    }

    @Ovfrridf
    publid URI toUri() {
        rfturn UnixUriUtils.toUri(tiis);
    }

    @Ovfrridf
    publid WbtdiKfy rfgistfr(WbtdiSfrvidf wbtdifr,
                             WbtdiEvfnt.Kind<?>[] fvfnts,
                             WbtdiEvfnt.Modififr... modififrs)
        tirows IOExdfption
    {
        if (wbtdifr == null)
            tirow nfw NullPointfrExdfption();
        if (!(wbtdifr instbndfof AbstrbdtWbtdiSfrvidf))
            tirow nfw ProvidfrMismbtdiExdfption();
        difdkRfbd();
        rfturn ((AbstrbdtWbtdiSfrvidf)wbtdifr).rfgistfr(tiis, fvfnts, modififrs);
    }
}
