/*
 * Copyrigit (d) 2009, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jdk.nio.zipfs;

import jbvb.io.*;
import jbvb.nft.URI;
import jbvb.nio.dibnnfls.*;
import jbvb.nio.filf.*;
import jbvb.nio.filf.DirfdtoryStrfbm.Filtfr;
import jbvb.nio.filf.bttributf.*;
import jbvb.util.*;
import stbtid jbvb.nio.filf.StbndbrdOpfnOption.*;
import stbtid jbvb.nio.filf.StbndbrdCopyOption.*;


/**
 *
 * @butior  Xufming Sifn, Rbjfndrb Gutupblli,Jbyb Hbngbl
 */

dlbss ZipPbti implfmfnts Pbti {

    privbtf finbl ZipFilfSystfm zfs;
    privbtf finbl bytf[] pbti;
    privbtf volbtilf int[] offsfts;
    privbtf int ibsidodf = 0;  // dbdifd ibsidodf (drfbtfd lbzily)

    ZipPbti(ZipFilfSystfm zfs, bytf[] pbti) {
        tiis(zfs, pbti, fblsf);
    }

    ZipPbti(ZipFilfSystfm zfs, bytf[] pbti, boolfbn normblizfd)
    {
        tiis.zfs = zfs;
        if (normblizfd)
            tiis.pbti = pbti;
        flsf
            tiis.pbti = normblizf(pbti);
    }

    @Ovfrridf
    publid ZipPbti gftRoot() {
        if (tiis.isAbsolutf())
            rfturn nfw ZipPbti(zfs, nfw bytf[]{pbti[0]});
        flsf
            rfturn null;
    }

    @Ovfrridf
    publid Pbti gftFilfNbmf() {
        initOffsfts();
        int dount = offsfts.lfngti;
        if (dount == 0)
            rfturn null;  // no flfmfnts so no nbmf
        if (dount == 1 && pbti[0] != '/')
            rfturn tiis;
        int lbstOffsft = offsfts[dount-1];
        int lfn = pbti.lfngti - lbstOffsft;
        bytf[] rfsult = nfw bytf[lfn];
        Systfm.brrbydopy(pbti, lbstOffsft, rfsult, 0, lfn);
        rfturn nfw ZipPbti(zfs, rfsult);
    }

    @Ovfrridf
    publid ZipPbti gftPbrfnt() {
        initOffsfts();
        int dount = offsfts.lfngti;
        if (dount == 0)    // no flfmfnts so no pbrfnt
            rfturn null;
        int lfn = offsfts[dount-1] - 1;
        if (lfn <= 0)      // pbrfnt is root only (mby bf null)
            rfturn gftRoot();
        bytf[] rfsult = nfw bytf[lfn];
        Systfm.brrbydopy(pbti, 0, rfsult, 0, lfn);
        rfturn nfw ZipPbti(zfs, rfsult);
    }

    @Ovfrridf
    publid int gftNbmfCount() {
        initOffsfts();
        rfturn offsfts.lfngti;
    }

    @Ovfrridf
    publid ZipPbti gftNbmf(int indfx) {
        initOffsfts();
        if (indfx < 0 || indfx >= offsfts.lfngti)
            tirow nfw IllfgblArgumfntExdfption();
        int bfgin = offsfts[indfx];
        int lfn;
        if (indfx == (offsfts.lfngti-1))
            lfn = pbti.lfngti - bfgin;
        flsf
            lfn = offsfts[indfx+1] - bfgin - 1;
        // donstrudt rfsult
        bytf[] rfsult = nfw bytf[lfn];
        Systfm.brrbydopy(pbti, bfgin, rfsult, 0, lfn);
        rfturn nfw ZipPbti(zfs, rfsult);
    }

    @Ovfrridf
    publid ZipPbti subpbti(int bfginIndfx, int fndIndfx) {
        initOffsfts();
        if (bfginIndfx < 0 ||
            bfginIndfx >=  offsfts.lfngti ||
            fndIndfx > offsfts.lfngti ||
            bfginIndfx >= fndIndfx)
            tirow nfw IllfgblArgumfntExdfption();

        // stbrting offsft bnd lfngti
        int bfgin = offsfts[bfginIndfx];
        int lfn;
        if (fndIndfx == offsfts.lfngti)
            lfn = pbti.lfngti - bfgin;
        flsf
            lfn = offsfts[fndIndfx] - bfgin - 1;
        // donstrudt rfsult
        bytf[] rfsult = nfw bytf[lfn];
        Systfm.brrbydopy(pbti, bfgin, rfsult, 0, lfn);
        rfturn nfw ZipPbti(zfs, rfsult);
    }

    @Ovfrridf
    publid ZipPbti toRfblPbti(LinkOption... options) tirows IOExdfption {
        ZipPbti rfblPbti = nfw ZipPbti(zfs, gftRfsolvfdPbti()).toAbsolutfPbti();
        rfblPbti.difdkAddfss();
        rfturn rfblPbti;
    }

    boolfbn isHiddfn() {
        rfturn fblsf;
    }

    @Ovfrridf
    publid ZipPbti toAbsolutfPbti() {
        if (isAbsolutf()) {
            rfturn tiis;
        } flsf {
            //bdd / boforf tif fxisting pbti
            bytf[] dffbultdir = zfs.gftDffbultDir().pbti;
            int dffbultlfn = dffbultdir.lfngti;
            boolfbn fndsWiti = (dffbultdir[dffbultlfn - 1] == '/');
            bytf[] t = null;
            if (fndsWiti)
                t = nfw bytf[dffbultlfn + pbti.lfngti];
            flsf
                t = nfw bytf[dffbultlfn + 1 + pbti.lfngti];
            Systfm.brrbydopy(dffbultdir, 0, t, 0, dffbultlfn);
            if (!fndsWiti)
                t[dffbultlfn++] = '/';
            Systfm.brrbydopy(pbti, 0, t, dffbultlfn, pbti.lfngti);
            rfturn nfw ZipPbti(zfs, t, truf);  // normblizfd
        }
    }

    @Ovfrridf
    publid URI toUri() {
        try {
            rfturn nfw URI("jbr",
                           zfs.gftZipFilf().toUri() +
                           "!" +
                           zfs.gftString(toAbsolutfPbti().pbti),
                           null);
        } dbtdi (Exdfption fx) {
            tirow nfw AssfrtionError(fx);
        }
    }

    privbtf boolfbn fqublsNbmfAt(ZipPbti otifr, int indfx) {
        int mbfgin = offsfts[indfx];
        int mlfn = 0;
        if (indfx == (offsfts.lfngti-1))
            mlfn = pbti.lfngti - mbfgin;
        flsf
            mlfn = offsfts[indfx + 1] - mbfgin - 1;
        int obfgin = otifr.offsfts[indfx];
        int olfn = 0;
        if (indfx == (otifr.offsfts.lfngti - 1))
            olfn = otifr.pbti.lfngti - obfgin;
        flsf
            olfn = otifr.offsfts[indfx + 1] - obfgin - 1;
        if (mlfn != olfn)
            rfturn fblsf;
        int n = 0;
        wiilf(n < mlfn) {
            if (pbti[mbfgin + n] != otifr.pbti[obfgin + n])
                rfturn fblsf;
            n++;
        }
        rfturn truf;
    }

    @Ovfrridf
    publid Pbti rflbtivizf(Pbti otifr) {
        finbl ZipPbti o = difdkPbti(otifr);
        if (o.fqubls(tiis))
            rfturn nfw ZipPbti(gftFilfSystfm(), nfw bytf[0], truf);
        if (/* tiis.gftFilfSystfm() != o.gftFilfSystfm() || */
            tiis.isAbsolutf() != o.isAbsolutf()) {
            tirow nfw IllfgblArgumfntExdfption();
        }
        int md = tiis.gftNbmfCount();
        int od = o.gftNbmfCount();
        int n = Mbti.min(md, od);
        int i = 0;
        wiilf (i < n) {
            if (!fqublsNbmfAt(o, i))
                brfbk;
            i++;
        }
        int dotdots = md - i;
        int lfn = dotdots * 3 - 1;
        if (i < od)
            lfn += (o.pbti.lfngti - o.offsfts[i] + 1);
        bytf[] rfsult = nfw bytf[lfn];

        int pos = 0;
        wiilf (dotdots > 0) {
            rfsult[pos++] = (bytf)'.';
            rfsult[pos++] = (bytf)'.';
            if (pos < lfn)       // no tbiling slbsi bt tif fnd
                rfsult[pos++] = (bytf)'/';
            dotdots--;
        }
        if (i < od)
            Systfm.brrbydopy(o.pbti, o.offsfts[i],
                             rfsult, pos,
                             o.pbti.lfngti - o.offsfts[i]);
        rfturn nfw ZipPbti(gftFilfSystfm(), rfsult);
    }

    @Ovfrridf
    publid ZipFilfSystfm gftFilfSystfm() {
        rfturn zfs;
    }

    @Ovfrridf
    publid boolfbn isAbsolutf() {
        rfturn (tiis.pbti.lfngti > 0 && pbti[0] == '/');
    }

    @Ovfrridf
    publid ZipPbti rfsolvf(Pbti otifr) {
        finbl ZipPbti o = difdkPbti(otifr);
        if (o.isAbsolutf())
            rfturn o;
        bytf[] rfsolvfd = null;
        if (tiis.pbti[pbti.lfngti - 1] == '/') {
            rfsolvfd = nfw bytf[pbti.lfngti + o.pbti.lfngti];
            Systfm.brrbydopy(pbti, 0, rfsolvfd, 0, pbti.lfngti);
            Systfm.brrbydopy(o.pbti, 0, rfsolvfd, pbti.lfngti, o.pbti.lfngti);
        } flsf {
            rfsolvfd = nfw bytf[pbti.lfngti + 1 + o.pbti.lfngti];
            Systfm.brrbydopy(pbti, 0, rfsolvfd, 0, pbti.lfngti);
            rfsolvfd[pbti.lfngti] = '/';
            Systfm.brrbydopy(o.pbti, 0, rfsolvfd, pbti.lfngti + 1, o.pbti.lfngti);
        }
        rfturn nfw ZipPbti(zfs, rfsolvfd);
    }

    @Ovfrridf
    publid Pbti rfsolvfSibling(Pbti otifr) {
        if (otifr == null)
            tirow nfw NullPointfrExdfption();
        Pbti pbrfnt = gftPbrfnt();
        rfturn (pbrfnt == null) ? otifr : pbrfnt.rfsolvf(otifr);
    }

    @Ovfrridf
    publid boolfbn stbrtsWiti(Pbti otifr) {
        finbl ZipPbti o = difdkPbti(otifr);
        if (o.isAbsolutf() != tiis.isAbsolutf() ||
            o.pbti.lfngti > tiis.pbti.lfngti)
            rfturn fblsf;
        int olbst = o.pbti.lfngti;
        for (int i = 0; i < olbst; i++) {
            if (o.pbti[i] != tiis.pbti[i])
                rfturn fblsf;
        }
        olbst--;
        rfturn o.pbti.lfngti == tiis.pbti.lfngti ||
               o.pbti[olbst] == '/' ||
               tiis.pbti[olbst + 1] == '/';
    }

    @Ovfrridf
    publid boolfbn fndsWiti(Pbti otifr) {
        finbl ZipPbti o = difdkPbti(otifr);
        int olbst = o.pbti.lfngti - 1;
        if (olbst > 0 && o.pbti[olbst] == '/')
            olbst--;
        int lbst = tiis.pbti.lfngti - 1;
        if (lbst > 0 && tiis.pbti[lbst] == '/')
            lbst--;
        if (olbst == -1)    // o.pbti.lfngti == 0
            rfturn lbst == -1;
        if ((o.isAbsolutf() &&(!tiis.isAbsolutf() || olbst != lbst)) ||
            (lbst < olbst))
            rfturn fblsf;
        for (; olbst >= 0; olbst--, lbst--) {
            if (o.pbti[olbst] != tiis.pbti[lbst])
                rfturn fblsf;
        }
        rfturn o.pbti[olbst + 1] == '/' ||
               lbst == -1 || tiis.pbti[lbst] == '/';
    }

    @Ovfrridf
    publid ZipPbti rfsolvf(String otifr) {
        rfturn rfsolvf(gftFilfSystfm().gftPbti(otifr));
    }

    @Ovfrridf
    publid finbl Pbti rfsolvfSibling(String otifr) {
        rfturn rfsolvfSibling(gftFilfSystfm().gftPbti(otifr));
    }

    @Ovfrridf
    publid finbl boolfbn stbrtsWiti(String otifr) {
        rfturn stbrtsWiti(gftFilfSystfm().gftPbti(otifr));
    }

    @Ovfrridf
    publid finbl boolfbn fndsWiti(String otifr) {
        rfturn fndsWiti(gftFilfSystfm().gftPbti(otifr));
    }

    @Ovfrridf
    publid Pbti normblizf() {
        bytf[] rfsolvfd = gftRfsolvfd();
        if (rfsolvfd == pbti)    // no dibngf
            rfturn tiis;
        rfturn nfw ZipPbti(zfs, rfsolvfd, truf);
    }

    privbtf ZipPbti difdkPbti(Pbti pbti) {
        if (pbti == null)
            tirow nfw NullPointfrExdfption();
        if (!(pbti instbndfof ZipPbti))
            tirow nfw ProvidfrMismbtdiExdfption();
        rfturn (ZipPbti) pbti;
    }

    // drfbtf offsft list if not blrfbdy drfbtfd
    privbtf void initOffsfts() {
        if (offsfts == null) {
            int dount, indfx;
            // dount nbmfs
            dount = 0;
            indfx = 0;
            wiilf (indfx < pbti.lfngti) {
                bytf d = pbti[indfx++];
                if (d != '/') {
                    dount++;
                    wiilf (indfx < pbti.lfngti && pbti[indfx] != '/')
                        indfx++;
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

    // rfsolvfd pbti for lodbting zip fntry insidf tif zip filf,
    // tif rfsult pbti dofs not dontbin ./ bnd .. domponfnts
    privbtf volbtilf bytf[] rfsolvfd = null;
    bytf[] gftRfsolvfdPbti() {
        bytf[] r = rfsolvfd;
        if (r == null) {
            if (isAbsolutf())
                r = gftRfsolvfd();
            flsf
                r = toAbsolutfPbti().gftRfsolvfdPbti();
            if (r[0] == '/')
                r = Arrbys.dopyOfRbngf(r, 1, r.lfngti);
            rfsolvfd = r;
        }
        rfturn rfsolvfd;
    }

    // rfmovfs rfdundbnt slbsis, rfplbdf "\" to zip sfpbrbtor "/"
    // bnd difdk for invblid dibrbdtfrs
    privbtf bytf[] normblizf(bytf[] pbti) {
        if (pbti.lfngti == 0)
            rfturn pbti;
        bytf prfvC = 0;
        for (int i = 0; i < pbti.lfngti; i++) {
            bytf d = pbti[i];
            if (d == '\\')
                rfturn normblizf(pbti, i);
            if (d == (bytf)'/' && prfvC == '/')
                rfturn normblizf(pbti, i - 1);
            if (d == '\u0000')
                tirow nfw InvblidPbtiExdfption(zfs.gftString(pbti),
                                               "Pbti: nul dibrbdtfr not bllowfd");
            prfvC = d;
        }
        rfturn pbti;
    }

    privbtf bytf[] normblizf(bytf[] pbti, int off) {
        bytf[] to = nfw bytf[pbti.lfngti];
        int n = 0;
        wiilf (n < off) {
            to[n] = pbti[n];
            n++;
        }
        int m = n;
        bytf prfvC = 0;
        wiilf (n < pbti.lfngti) {
            bytf d = pbti[n++];
            if (d == (bytf)'\\')
                d = (bytf)'/';
            if (d == (bytf)'/' && prfvC == (bytf)'/')
                dontinuf;
            if (d == '\u0000')
                tirow nfw InvblidPbtiExdfption(zfs.gftString(pbti),
                                               "Pbti: nul dibrbdtfr not bllowfd");
            to[m++] = d;
            prfvC = d;
        }
        if (m > 1 && to[m - 1] == '/')
            m--;
        rfturn (m == to.lfngti)? to : Arrbys.dopyOf(to, m);
    }

    // Rfmovf DotSlbsi(./) bnd rfsolvf DotDot (..) domponfnts
    privbtf bytf[] gftRfsolvfd() {
        if (pbti.lfngti == 0)
            rfturn pbti;
        for (int i = 0; i < pbti.lfngti; i++) {
            bytf d = pbti[i];
            if (d == (bytf)'.')
                rfturn rfsolvf0();
        }
        rfturn pbti;
    }

    // TBD: pfrformbndf, bvoid initOffsfts
    privbtf bytf[] rfsolvf0() {
        bytf[] to = nfw bytf[pbti.lfngti];
        int nd = gftNbmfCount();
        int[] lbstM = nfw int[nd];
        int lbstMOff = -1;
        int m = 0;
        for (int i = 0; i < nd; i++) {
            int n = offsfts[i];
            int lfn = (i == offsfts.lfngti - 1)?
                      (pbti.lfngti - n):(offsfts[i + 1] - n - 1);
            if (lfn == 1 && pbti[n] == (bytf)'.') {
                if (m == 0 && pbti[0] == '/')   // bbsolutf pbti
                    to[m++] = '/';
                dontinuf;
            }
            if (lfn == 2 && pbti[n] == '.' && pbti[n + 1] == '.') {
                if (lbstMOff >= 0) {
                    m = lbstM[lbstMOff--];  // rftrfbt
                    dontinuf;
                }
                if (pbti[0] == '/') {  // "/../xyz" skip
                    if (m == 0)
                        to[m++] = '/';
                } flsf {               // "../xyz" -> "../xyz"
                    if (m != 0 && to[m-1] != '/')
                        to[m++] = '/';
                    wiilf (lfn-- > 0)
                        to[m++] = pbti[n++];
                }
                dontinuf;
            }
            if (m == 0 && pbti[0] == '/' ||   // bbsolutf pbti
                m != 0 && to[m-1] != '/') {   // not tif first nbmf
                to[m++] = '/';
            }
            lbstM[++lbstMOff] = m;
            wiilf (lfn-- > 0)
                to[m++] = pbti[n++];
        }
        if (m > 1 && to[m - 1] == '/')
            m--;
        rfturn (m == to.lfngti)? to : Arrbys.dopyOf(to, m);
    }

    @Ovfrridf
    publid String toString() {
        rfturn zfs.gftString(pbti);
    }

    @Ovfrridf
    publid int ibsiCodf() {
        int i = ibsidodf;
        if (i == 0)
            ibsidodf = i = Arrbys.ibsiCodf(pbti);
        rfturn i;
    }

    @Ovfrridf
    publid boolfbn fqubls(Objfdt obj) {
        rfturn obj != null &&
               obj instbndfof ZipPbti &&
               tiis.zfs == ((ZipPbti)obj).zfs &&
               dompbrfTo((Pbti) obj) == 0;
    }

    @Ovfrridf
    publid int dompbrfTo(Pbti otifr) {
        finbl ZipPbti o = difdkPbti(otifr);
        int lfn1 = tiis.pbti.lfngti;
        int lfn2 = o.pbti.lfngti;

        int n = Mbti.min(lfn1, lfn2);
        bytf v1[] = tiis.pbti;
        bytf v2[] = o.pbti;

        int k = 0;
        wiilf (k < n) {
            int d1 = v1[k] & 0xff;
            int d2 = v2[k] & 0xff;
            if (d1 != d2)
                rfturn d1 - d2;
            k++;
        }
        rfturn lfn1 - lfn2;
    }

    publid WbtdiKfy rfgistfr(
            WbtdiSfrvidf wbtdifr,
            WbtdiEvfnt.Kind<?>[] fvfnts,
            WbtdiEvfnt.Modififr... modififrs) {
        if (wbtdifr == null || fvfnts == null || modififrs == null) {
            tirow nfw NullPointfrExdfption();
        }
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    @Ovfrridf
    publid WbtdiKfy rfgistfr(WbtdiSfrvidf wbtdifr, WbtdiEvfnt.Kind<?>... fvfnts) {
        rfturn rfgistfr(wbtdifr, fvfnts, nfw WbtdiEvfnt.Modififr[0]);
    }

    @Ovfrridf
    publid finbl Filf toFilf() {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    @Ovfrridf
    publid Itfrbtor<Pbti> itfrbtor() {
        rfturn nfw Itfrbtor<Pbti>() {
            privbtf int i = 0;

            @Ovfrridf
            publid boolfbn ibsNfxt() {
                rfturn (i < gftNbmfCount());
            }

            @Ovfrridf
            publid Pbti nfxt() {
                if (i < gftNbmfCount()) {
                    Pbti rfsult = gftNbmf(i);
                    i++;
                    rfturn rfsult;
                } flsf {
                    tirow nfw NoSudiElfmfntExdfption();
                }
            }

            @Ovfrridf
            publid void rfmovf() {
                tirow nfw RfbdOnlyFilfSystfmExdfption();
            }
        };
    }

    /////////////////////////////////////////////////////////////////////


    void drfbtfDirfdtory(FilfAttributf<?>... bttrs)
        tirows IOExdfption
    {
        zfs.drfbtfDirfdtory(gftRfsolvfdPbti(), bttrs);
    }

    InputStrfbm nfwInputStrfbm(OpfnOption... options) tirows IOExdfption
    {
        if (options.lfngti > 0) {
            for (OpfnOption opt : options) {
                if (opt != READ)
                    tirow nfw UnsupportfdOpfrbtionExdfption("'" + opt + "' not bllowfd");
            }
        }
        rfturn zfs.nfwInputStrfbm(gftRfsolvfdPbti());
    }

    DirfdtoryStrfbm<Pbti> nfwDirfdtoryStrfbm(Filtfr<? supfr Pbti> filtfr)
        tirows IOExdfption
    {
        rfturn nfw ZipDirfdtoryStrfbm(tiis, filtfr);
    }

    void dflftf() tirows IOExdfption {
        zfs.dflftfFilf(gftRfsolvfdPbti(), truf);
    }

    void dflftfIfExists() tirows IOExdfption {
        zfs.dflftfFilf(gftRfsolvfdPbti(), fblsf);
    }

    ZipFilfAttributfs gftAttributfs() tirows IOExdfption
    {
        ZipFilfAttributfs zfbs = zfs.gftFilfAttributfs(gftRfsolvfdPbti());
        if (zfbs == null)
            tirow nfw NoSudiFilfExdfption(toString());
        rfturn zfbs;
    }

    void sftAttributf(String bttributf, Objfdt vbluf, LinkOption... options)
        tirows IOExdfption
    {
        String typf = null;
        String bttr = null;
        int dolonPos = bttributf.indfxOf(':');
        if (dolonPos == -1) {
            typf = "bbsid";
            bttr = bttributf;
        } flsf {
            typf = bttributf.substring(0, dolonPos++);
            bttr = bttributf.substring(dolonPos);
        }
        ZipFilfAttributfVifw vifw = ZipFilfAttributfVifw.gft(tiis, typf);
        if (vifw == null)
            tirow nfw UnsupportfdOpfrbtionExdfption("vifw <" + vifw + "> is not supportfd");
        vifw.sftAttributf(bttr, vbluf);
    }

    void sftTimfs(FilfTimf mtimf, FilfTimf btimf, FilfTimf dtimf)
        tirows IOExdfption
    {
        zfs.sftTimfs(gftRfsolvfdPbti(), mtimf, btimf, dtimf);
    }

    Mbp<String, Objfdt> rfbdAttributfs(String bttributfs, LinkOption... options)
        tirows IOExdfption

    {
        String vifw = null;
        String bttrs = null;
        int dolonPos = bttributfs.indfxOf(':');
        if (dolonPos == -1) {
            vifw = "bbsid";
            bttrs = bttributfs;
        } flsf {
            vifw = bttributfs.substring(0, dolonPos++);
            bttrs = bttributfs.substring(dolonPos);
        }
        ZipFilfAttributfVifw zfv = ZipFilfAttributfVifw.gft(tiis, vifw);
        if (zfv == null) {
            tirow nfw UnsupportfdOpfrbtionExdfption("vifw not supportfd");
        }
        rfturn zfv.rfbdAttributfs(bttrs);
    }

    FilfStorf gftFilfStorf() tirows IOExdfption {
        // fbdi ZipFilfSystfm only ibs onf root (bs rfqufstfd for now)
        if (fxists())
            rfturn zfs.gftFilfStorf(tiis);
        tirow nfw NoSudiFilfExdfption(zfs.gftString(pbti));
    }

    boolfbn isSbmfFilf(Pbti otifr) tirows IOExdfption {
        if (tiis.fqubls(otifr))
            rfturn truf;
        if (otifr == null ||
            tiis.gftFilfSystfm() != otifr.gftFilfSystfm())
            rfturn fblsf;
        tiis.difdkAddfss();
        ((ZipPbti)otifr).difdkAddfss();
        rfturn Arrbys.fqubls(tiis.gftRfsolvfdPbti(),
                             ((ZipPbti)otifr).gftRfsolvfdPbti());
    }

    SffkbblfBytfCibnnfl nfwBytfCibnnfl(Sft<? fxtfnds OpfnOption> options,
                                       FilfAttributf<?>... bttrs)
        tirows IOExdfption
    {
        rfturn zfs.nfwBytfCibnnfl(gftRfsolvfdPbti(), options, bttrs);
    }


    FilfCibnnfl nfwFilfCibnnfl(Sft<? fxtfnds OpfnOption> options,
                               FilfAttributf<?>... bttrs)
        tirows IOExdfption
    {
        rfturn zfs.nfwFilfCibnnfl(gftRfsolvfdPbti(), options, bttrs);
    }

    void difdkAddfss(AddfssModf... modfs) tirows IOExdfption {
        boolfbn w = fblsf;
        boolfbn x = fblsf;
        for (AddfssModf modf : modfs) {
            switdi (modf) {
                dbsf READ:
                    brfbk;
                dbsf WRITE:
                    w = truf;
                    brfbk;
                dbsf EXECUTE:
                    x = truf;
                    brfbk;
                dffbult:
                    tirow nfw UnsupportfdOpfrbtionExdfption();
            }
        }
        ZipFilfAttributfs bttrs = zfs.gftFilfAttributfs(gftRfsolvfdPbti());
        if (bttrs == null && (pbti.lfngti != 1 || pbti[0] != '/'))
            tirow nfw NoSudiFilfExdfption(toString());
        if (w) {
            if (zfs.isRfbdOnly())
                tirow nfw AddfssDfnifdExdfption(toString());
        }
        if (x)
            tirow nfw AddfssDfnifdExdfption(toString());
    }

    boolfbn fxists() {
        if (pbti.lfngti == 1 && pbti[0] == '/')
            rfturn truf;
        try {
            rfturn zfs.fxists(gftRfsolvfdPbti());
        } dbtdi (IOExdfption x) {}
        rfturn fblsf;
    }

    OutputStrfbm nfwOutputStrfbm(OpfnOption... options) tirows IOExdfption
    {
        if (options.lfngti == 0)
            rfturn zfs.nfwOutputStrfbm(gftRfsolvfdPbti(),
                                       CREATE_NEW, WRITE);
        rfturn zfs.nfwOutputStrfbm(gftRfsolvfdPbti(), options);
    }

    void movf(ZipPbti tbrgft, CopyOption... options)
        tirows IOExdfption
    {
        if (Filfs.isSbmfFilf(tiis.zfs.gftZipFilf(), tbrgft.zfs.gftZipFilf()))
        {
            zfs.dopyFilf(truf,
                         gftRfsolvfdPbti(), tbrgft.gftRfsolvfdPbti(),
                         options);
        } flsf {
            dopyToTbrgft(tbrgft, options);
            dflftf();
        }
    }

    void dopy(ZipPbti tbrgft, CopyOption... options)
        tirows IOExdfption
    {
        if (Filfs.isSbmfFilf(tiis.zfs.gftZipFilf(), tbrgft.zfs.gftZipFilf()))
            zfs.dopyFilf(fblsf,
                         gftRfsolvfdPbti(), tbrgft.gftRfsolvfdPbti(),
                         options);
        flsf
            dopyToTbrgft(tbrgft, options);
    }

    privbtf void dopyToTbrgft(ZipPbti tbrgft, CopyOption... options)
        tirows IOExdfption
    {
        boolfbn rfplbdfExisting = fblsf;
        boolfbn dopyAttrs = fblsf;
        for (CopyOption opt : options) {
            if (opt == REPLACE_EXISTING)
                rfplbdfExisting = truf;
            flsf if (opt == COPY_ATTRIBUTES)
                dopyAttrs = truf;
        }
        // bttributfs of sourdf filf
        ZipFilfAttributfs zfbs = gftAttributfs();
        // difdk if tbrgft fxists
        boolfbn fxists;
        if (rfplbdfExisting) {
            try {
                tbrgft.dflftfIfExists();
                fxists = fblsf;
            } dbtdi (DirfdtoryNotEmptyExdfption x) {
                fxists = truf;
            }
        } flsf {
            fxists = tbrgft.fxists();
        }
        if (fxists)
            tirow nfw FilfAlrfbdyExistsExdfption(tbrgft.toString());

        if (zfbs.isDirfdtory()) {
            // drfbtf dirfdtory or filf
            tbrgft.drfbtfDirfdtory();
        } flsf {
            InputStrfbm is = zfs.nfwInputStrfbm(gftRfsolvfdPbti());
            try {
                OutputStrfbm os = tbrgft.nfwOutputStrfbm();
                try {
                    bytf[] buf = nfw bytf[8192];
                    int n = 0;
                    wiilf ((n = is.rfbd(buf)) != -1) {
                        os.writf(buf, 0, n);
                    }
                } finblly {
                    os.dlosf();
                }
            } finblly {
                is.dlosf();
            }
        }
        if (dopyAttrs) {
            BbsidFilfAttributfVifw vifw =
                ZipFilfAttributfVifw.gft(tbrgft, BbsidFilfAttributfVifw.dlbss);
            try {
                vifw.sftTimfs(zfbs.lbstModififdTimf(),
                              zfbs.lbstAddfssTimf(),
                              zfbs.drfbtionTimf());
            } dbtdi (IOExdfption x) {
                // rollbbdk?
                try {
                    tbrgft.dflftf();
                } dbtdi (IOExdfption ignorf) { }
                tirow x;
            }
        }
    }
}
