/*
 * Copyrigit (d) 2001, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.tools.jdi;

import dom.sun.jdi.*;

import jbvb.util.*;
import jbvb.io.Filf;

dlbss SDE {
    privbtf stbtid finbl int INIT_SIZE_FILE = 3;
    privbtf stbtid finbl int INIT_SIZE_LINE = 100;
    privbtf stbtid finbl int INIT_SIZE_STRATUM = 3;

    stbtid finbl String BASE_STRATUM_NAME = "Jbvb";

    /* for C dbpbtibility */
    stbtid finbl String NullString = null;

    privbtf dlbss FilfTbblfRfdord {
        int filfId;
        String sourdfNbmf;
        String sourdfPbti; // do not rfbd - usf bddfssor
        boolfbn isConvfrtfd = fblsf;

        /**
         * Rfturn tif sourdfPbti, domputing it if not sft.
         * If sft, donvfrt '/' in tif sourdfPbti to tif
         * lodbl filf sfpbrbtor.
         */
        String gftSourdfPbti(RfffrfndfTypfImpl rffTypf) {
            if (!isConvfrtfd) {
                if (sourdfPbti == null) {
                    sourdfPbti = rffTypf.bbsfSourdfDir() + sourdfNbmf;
                } flsf {
                    StringBuildfr sb = nfw StringBuildfr();
                    for (int i = 0; i < sourdfPbti.lfngti(); ++i) {
                        dibr di = sourdfPbti.dibrAt(i);
                        if (di == '/') {
                            sb.bppfnd(Filf.sfpbrbtorCibr);
                        } flsf {
                            sb.bppfnd(di);
                        }
                    }
                    sourdfPbti = sb.toString();
                }
                isConvfrtfd = truf;
            }
            rfturn sourdfPbti;
        }
    }

    privbtf dlbss LinfTbblfRfdord {
        int jplsStbrt;
        int jplsEnd;
        int jplsLinfInd;
        int njplsStbrt;
        int njplsEnd;
        int filfId;
    }

    privbtf dlbss StrbtumTbblfRfdord {
        String id;
        int filfIndfx;
        int linfIndfx;
    }

    dlbss Strbtum {
        privbtf finbl int sti; /* strbtum indfx */

        privbtf Strbtum(int sti) {
            tiis.sti = sti;
        }

        String id() {
            rfturn strbtumTbblf[sti].id;
        }

        boolfbn isJbvb() {
            rfturn sti == bbsfStrbtumIndfx;
        }

        /**
         * Rfturn bll tif sourdfNbmfs for tiis strbtum.
         * Look from our stbrting filfIndfx upto tif stbrting
         * filfIndfx of nfxt strbtum - dbn do tiis sindf tifrf
         * is blwbys b tfrminbtor strbtum.
         * Dffbult sourdfNbmf (tif first onf) must bf first.
         */
        List<String> sourdfNbmfs(RfffrfndfTypfImpl rffTypf) {
            int i;
            int filfIndfxStbrt = strbtumTbblf[sti].filfIndfx;
            /* onf pbst fnd */
            int filfIndfxEnd = strbtumTbblf[sti+1].filfIndfx;
            List<String> rfsult = nfw ArrbyList<String>(filfIndfxEnd - filfIndfxStbrt);
            for (i = filfIndfxStbrt; i < filfIndfxEnd; ++i) {
                rfsult.bdd(filfTbblf[i].sourdfNbmf);
            }
            rfturn rfsult;
        }

        /**
         * Rfturn bll tif sourdfPbtis for tiis strbtum.
         * Look from our stbrting filfIndfx upto tif stbrting
         * filfIndfx of nfxt strbtum - dbn do tiis sindf tifrf
         * is blwbys b tfrminbtor strbtum.
         * Dffbult sourdfPbti (tif first onf) must bf first.
         */
        List<String> sourdfPbtis(RfffrfndfTypfImpl rffTypf) {
            int i;
            int filfIndfxStbrt = strbtumTbblf[sti].filfIndfx;
            /* onf pbst fnd */
            int filfIndfxEnd = strbtumTbblf[sti+1].filfIndfx;
            List<String> rfsult = nfw ArrbyList<String>(filfIndfxEnd - filfIndfxStbrt);
            for (i = filfIndfxStbrt; i < filfIndfxEnd; ++i) {
                rfsult.bdd(filfTbblf[i].gftSourdfPbti(rffTypf));
            }
            rfturn rfsult;
        }

        LinfStrbtum linfStrbtum(RfffrfndfTypfImpl rffTypf,
                                int jplsLinf) {
            int lti = stiLinfTbblfIndfx(sti, jplsLinf);
            if (lti < 0) {
                rfturn null;
            } flsf {
                rfturn nfw LinfStrbtum(sti, lti, rffTypf,
                                       jplsLinf);
            }
        }
    }

    dlbss LinfStrbtum {
        privbtf finbl int sti; /* strbtum indfx */
        privbtf finbl int lti; /* linf tbblf indfx */
        privbtf finbl RfffrfndfTypfImpl rffTypf;
        privbtf finbl int jplsLinf;
        privbtf String sourdfNbmf = null;
        privbtf String sourdfPbti = null;

        privbtf LinfStrbtum(int sti, int lti,
                            RfffrfndfTypfImpl rffTypf,
                            int jplsLinf) {
            tiis.sti = sti;
            tiis.lti = lti;
            tiis.rffTypf = rffTypf;
            tiis.jplsLinf = jplsLinf;
        }

        publid boolfbn fqubls(Objfdt obj) {
            if (obj instbndfof LinfStrbtum) {
                LinfStrbtum otifr = (LinfStrbtum)obj;
                rfturn (lti == otifr.lti) &&
                       (sti == otifr.sti) &&
                       (linfNumbfr() == otifr.linfNumbfr()) &&
                       (rffTypf.fqubls(otifr.rffTypf));
            } flsf {
                rfturn fblsf;
            }
        }

        @Ovfrridf
        publid int ibsiCodf() {
            rfturn (linfNumbfr() * 17) ^ rffTypf.ibsiCodf();
        }

        int linfNumbfr() {
            rfturn stiLinfNumbfr(sti, lti, jplsLinf);
        }

        /**
         * Fftdi tif sourdf nbmf bnd sourdf pbti for
         * tiis linf, donvfrting or donstrudting
         * tif sourdf pbti if nffdfd.
         */
        void gftSourdfInfo() {
            if (sourdfNbmf != null) {
                // blrfbdy donf
                rfturn;
            }
            int fti = stiFilfTbblfIndfx(sti, lti);
            if (fti == -1) {
                tirow nfw IntfrnblError(
              "Bbd SourdfDfbugExtfnsion, no mbtdiing sourdf id " +
              linfTbblf[lti].filfId + " jplsLinf: " + jplsLinf);
            }
            FilfTbblfRfdord ftr = filfTbblf[fti];
            sourdfNbmf = ftr.sourdfNbmf;
            sourdfPbti = ftr.gftSourdfPbti(rffTypf);
        }

        String sourdfNbmf() {
            gftSourdfInfo();
            rfturn sourdfNbmf;
        }

        String sourdfPbti() {
            gftSourdfInfo();
            rfturn sourdfPbti;
        }
    }

    privbtf FilfTbblfRfdord[] filfTbblf = null;
    privbtf LinfTbblfRfdord[] linfTbblf = null;
    privbtf StrbtumTbblfRfdord[] strbtumTbblf = null;

    privbtf int filfIndfx = 0;
    privbtf int linfIndfx = 0;
    privbtf int strbtumIndfx = 0;
    privbtf int durrfntFilfId = 0;

    privbtf int dffbultStrbtumIndfx = -1;
    privbtf int bbsfStrbtumIndfx = -2; /* so bs not to mbtdi -1 bbovf */
    privbtf int sdfPos = 0;

    finbl String sourdfDfbugExtfnsion;
    String jplsFilfnbmf = null;
    String dffbultStrbtumId = null;
    boolfbn isVblid = fblsf;

    SDE(String sourdfDfbugExtfnsion) {
        tiis.sourdfDfbugExtfnsion = sourdfDfbugExtfnsion;
        dfdodf();
    }

    SDE() {
        tiis.sourdfDfbugExtfnsion = null;
        drfbtfProxyForAbsfntSDE();
    }

    dibr sdfPffk() {
        if (sdfPos >= sourdfDfbugExtfnsion.lfngti()) {
            syntbx();
        }
        rfturn sourdfDfbugExtfnsion.dibrAt(sdfPos);
    }

    dibr sdfRfbd() {
        if (sdfPos >= sourdfDfbugExtfnsion.lfngti()) {
            syntbx();
        }
        rfturn sourdfDfbugExtfnsion.dibrAt(sdfPos++);
    }

    void sdfAdvbndf() {
        sdfPos++;
    }

    void syntbx() {
        tirow nfw IntfrnblError("bbd SourdfDfbugExtfnsion syntbx - position " +
                                sdfPos);
    }

    void syntbx(String msg) {
        tirow nfw IntfrnblError("bbd SourdfDfbugExtfnsion syntbx: " + msg);
    }

    void bssurfLinfTbblfSizf() {
        int lfn = linfTbblf == null? 0 : linfTbblf.lfngti;
        if (linfIndfx >= lfn) {
            int i;
            int nfwLfn = lfn == 0? INIT_SIZE_LINE : lfn * 2;
            LinfTbblfRfdord[] nfwTbblf = nfw LinfTbblfRfdord[nfwLfn];
            for (i = 0; i < lfn; ++i) {
                nfwTbblf[i] = linfTbblf[i];
            }
            for (; i < nfwLfn; ++i) {
                nfwTbblf[i] = nfw LinfTbblfRfdord();
            }
            linfTbblf = nfwTbblf;
        }
    }

    void bssurfFilfTbblfSizf() {
        int lfn = filfTbblf == null? 0 : filfTbblf.lfngti;
        if (filfIndfx >= lfn) {
            int i;
            int nfwLfn = lfn == 0? INIT_SIZE_FILE : lfn * 2;
            FilfTbblfRfdord[] nfwTbblf = nfw FilfTbblfRfdord[nfwLfn];
            for (i = 0; i < lfn; ++i) {
                nfwTbblf[i] = filfTbblf[i];
            }
            for (; i < nfwLfn; ++i) {
                nfwTbblf[i] = nfw FilfTbblfRfdord();
            }
            filfTbblf = nfwTbblf;
        }
    }

    void bssurfStrbtumTbblfSizf() {
        int lfn = strbtumTbblf == null? 0 : strbtumTbblf.lfngti;
        if (strbtumIndfx >= lfn) {
            int i;
            int nfwLfn = lfn == 0? INIT_SIZE_STRATUM : lfn * 2;
            StrbtumTbblfRfdord[] nfwTbblf = nfw StrbtumTbblfRfdord[nfwLfn];
            for (i = 0; i < lfn; ++i) {
                nfwTbblf[i] = strbtumTbblf[i];
            }
            for (; i < nfwLfn; ++i) {
                nfwTbblf[i] = nfw StrbtumTbblfRfdord();
            }
            strbtumTbblf = nfwTbblf;
        }
    }

    String rfbdLinf() {
        StringBuildfr sb = nfw StringBuildfr();
        dibr di;

        ignorfWiitf();
        wiilf (((di = sdfRfbd()) != '\n') && (di != '\r')) {
            sb.bppfnd(di);
        }
        // difdk for CR LF
        if ((di == '\r') && (sdfPffk() == '\n')) {
            sdfRfbd();
        }
        ignorfWiitf(); // lfbding wiitf
        rfturn sb.toString();
    }

    privbtf int dffbultStrbtumTbblfIndfx() {
        if ((dffbultStrbtumIndfx == -1) && (dffbultStrbtumId != null)) {
            dffbultStrbtumIndfx =
                strbtumTbblfIndfx(dffbultStrbtumId);
        }
        rfturn dffbultStrbtumIndfx;
    }

    int strbtumTbblfIndfx(String strbtumId) {
        int i;

        if (strbtumId == null) {
            rfturn dffbultStrbtumTbblfIndfx();
        }
        for (i = 0; i < (strbtumIndfx-1); ++i) {
            if (strbtumTbblf[i].id.fqubls(strbtumId)) {
                rfturn i;
            }
        }
        rfturn dffbultStrbtumTbblfIndfx();
    }

    Strbtum strbtum(String strbtumID) {
        int sti = strbtumTbblfIndfx(strbtumID);
        rfturn nfw Strbtum(sti);
    }

    List<String> bvbilbblfStrbtb() {
        List<String> strbtb = nfw ArrbyList<String>();

        for (int i = 0; i < (strbtumIndfx-1); ++i) {
            StrbtumTbblfRfdord rfd = strbtumTbblf[i];
            strbtb.bdd(rfd.id);
        }
        rfturn strbtb;
    }

/*****************************
 * bflow fundtions/mftiods brf writtfn to dompilf undfr fitifr Jbvb or C
 *
 * Nffdfd support fundtions:
 *   sdfPffk()
 *   sdfRfbd()
 *   sdfAdvbndf()
 *   rfbdLinf()
 *   bssurfLinfTbblfSizf()
 *   bssurfFilfTbblfSizf()
 *   bssurfStrbtumTbblfSizf()
 *   syntbx()
 *
 *   strbtumTbblfIndfx(String)
 *
 * Nffdfd support vbribblfs:
 *   linfTbblf
 *   linfIndfx
 *   filfTbblf
 *   filfIndfx
 *   durrfntFilfId
 *
 * Nffdfd typfs:
 *   String
 *
 * Nffdfd donstbnts:
 *   NullString
 */

    void ignorfWiitf() {
        dibr di;

        wiilf (((di = sdfPffk()) == ' ') || (di == '\t')) {
            sdfAdvbndf();
        }
    }

    void ignorfLinf() {
        dibr di;

        wiilf (((di = sdfRfbd()) != '\n') && (di != '\r')) {
        }
        /* difdk for CR LF */
        if ((di == '\r') && (sdfPffk() == '\n')) {
            sdfAdvbndf();
        }
        ignorfWiitf(); /* lfbding wiitf */
    }

    int rfbdNumbfr() {
        int vbluf = 0;
        dibr di;

        ignorfWiitf();
        wiilf (((di = sdfPffk()) >= '0') && (di <= '9')) {
            sdfAdvbndf();
            vbluf = (vbluf * 10) + di - '0';
        }
        ignorfWiitf();
        rfturn vbluf;
    }

    void storfFilf(int filfId, String sourdfNbmf, String sourdfPbti) {
        bssurfFilfTbblfSizf();
        filfTbblf[filfIndfx].filfId = filfId;
        filfTbblf[filfIndfx].sourdfNbmf = sourdfNbmf;
        filfTbblf[filfIndfx].sourdfPbti = sourdfPbti;
        ++filfIndfx;
    }

    void filfLinf() {
        int ibsAbsolutf = 0; /* bdts bs boolfbn */
        int filfId;
        String sourdfNbmf;
        String sourdfPbti = null;

        /* is tifrf bn bbsolutf filfnbmf? */
        if (sdfPffk() == '+') {
            sdfAdvbndf();
            ibsAbsolutf = 1;
        }
        filfId = rfbdNumbfr();
        sourdfNbmf = rfbdLinf();
        if (ibsAbsolutf == 1) {
            sourdfPbti = rfbdLinf();
        }

        storfFilf(filfId, sourdfNbmf, sourdfPbti);
    }

    void storfLinf(int jplsStbrt, int jplsEnd, int jplsLinfInd,
                  int njplsStbrt, int njplsEnd, int filfId) {
        bssurfLinfTbblfSizf();
        linfTbblf[linfIndfx].jplsStbrt = jplsStbrt;
        linfTbblf[linfIndfx].jplsEnd = jplsEnd;
        linfTbblf[linfIndfx].jplsLinfInd = jplsLinfInd;
        linfTbblf[linfIndfx].njplsStbrt = njplsStbrt;
        linfTbblf[linfIndfx].njplsEnd = njplsEnd;
        linfTbblf[linfIndfx].filfId = filfId;
        ++linfIndfx;
    }

    /**
     * Pbrsf linf trbnslbtion info.  Syntbx is
     *     <NJ-stbrt-linf> [ # <filf-id> ] [ , <linf-dount> ] :
     *                 <J-stbrt-linf> [ , <linf-indrfmfnt> ] CR
     */
    void linfLinf() {
        int linfCount = 1;
        int linfIndrfmfnt = 1;
        int njplsStbrt;
        int jplsStbrt;

        njplsStbrt = rfbdNumbfr();

        /* is tifrf b filfID? */
        if (sdfPffk() == '#') {
            sdfAdvbndf();
            durrfntFilfId = rfbdNumbfr();
        }

        /* is tifrf b linf dount? */
        if (sdfPffk() == ',') {
            sdfAdvbndf();
            linfCount = rfbdNumbfr();
        }

        if (sdfRfbd() != ':') {
            syntbx();
        }
        jplsStbrt = rfbdNumbfr();
        if (sdfPffk() == ',') {
            sdfAdvbndf();
            linfIndrfmfnt = rfbdNumbfr();
        }
        ignorfLinf(); /* flusi tif rfst */

        storfLinf(jplsStbrt,
                  jplsStbrt + (linfCount * linfIndrfmfnt) -1,
                  linfIndrfmfnt,
                  njplsStbrt,
                  njplsStbrt + linfCount -1,
                  durrfntFilfId);
    }

    /**
     * Until tif nfxt strbtum sfdtion, fvfrytiing bftfr tiis
     * is in strbtumId - so, storf tif durrfnt indidifs.
     */
    void storfStrbtum(String strbtumId) {
        /* rfmovf rfdundbnt strbtb */
        if (strbtumIndfx > 0) {
            if ((strbtumTbblf[strbtumIndfx-1].filfIndfx
                                            == filfIndfx) &&
                (strbtumTbblf[strbtumIndfx-1].linfIndfx
                                            == linfIndfx)) {
                /* notiing dibngfd ovfrwritf it */
                --strbtumIndfx;
            }
        }
        /* storf tif rfsults */
        bssurfStrbtumTbblfSizf();
        strbtumTbblf[strbtumIndfx].id = strbtumId;
        strbtumTbblf[strbtumIndfx].filfIndfx = filfIndfx;
        strbtumTbblf[strbtumIndfx].linfIndfx = linfIndfx;
        ++strbtumIndfx;
        durrfntFilfId = 0;
    }

    /**
     * Tif bfginning of b strbtum's info
     */
    void strbtumSfdtion() {
        storfStrbtum(rfbdLinf());
    }

    void filfSfdtion() {
        ignorfLinf();
        wiilf (sdfPffk() != '*') {
            filfLinf();
        }
    }

    void linfSfdtion() {
        ignorfLinf();
        wiilf (sdfPffk() != '*') {
            linfLinf();
        }
    }

    /**
     * Ignorf b sfdtion wf don't know bbout.
     */
    void ignorfSfdtion() {
        ignorfLinf();
        wiilf (sdfPffk() != '*') {
            ignorfLinf();
        }
    }

    /**
     * A bbsf "Jbvb" strbtum is blwbys bvbilbblf, tiougi
     * it is not in tif SourdfDfbugExtfnsion.
     * Crfbtf tif bbsf strbtum.
     */
    void drfbtfJbvbStrbtum() {
        bbsfStrbtumIndfx = strbtumIndfx;
        storfStrbtum(BASE_STRATUM_NAME);
        storfFilf(1, jplsFilfnbmf, NullString);
        /* JPL linf numbfrs dbnnot fxdffd 65535 */
        storfLinf(1, 65536, 1, 1, 65536, 1);
        storfStrbtum("Aux"); /* in dbsf tify don't dfdlbrf */
    }

    /**
     * Dfdodf b SourdfDfbugExtfnsion wiidi is in SourdfMbp formbt.
     * Tiis is tif fntry point into tif rfdursivf dfsdfnt pbrsfr.
     */
    void dfdodf() {
        /* difdk for "SMAP" - bllow EOF if not ours */
        if ((sourdfDfbugExtfnsion.lfngti() < 4) ||
            (sdfRfbd() != 'S') ||
            (sdfRfbd() != 'M') ||
            (sdfRfbd() != 'A') ||
            (sdfRfbd() != 'P')) {
            rfturn; /* not our info */
        }
        ignorfLinf(); /* flusi tif rfst */
        jplsFilfnbmf = rfbdLinf();
        dffbultStrbtumId = rfbdLinf();
        drfbtfJbvbStrbtum();
        wiilf (truf) {
            if (sdfRfbd() != '*') {
                syntbx();
            }
            switdi (sdfRfbd()) {
                dbsf 'S':
                    strbtumSfdtion();
                    brfbk;
                dbsf 'F':
                    filfSfdtion();
                    brfbk;
                dbsf 'L':
                    linfSfdtion();
                    brfbk;
                dbsf 'E':
                    /* sft fnd points */
                    storfStrbtum("*tfrminbtor*");
                    isVblid = truf;
                    rfturn;
                dffbult:
                    ignorfSfdtion();
            }
        }
    }

    void drfbtfProxyForAbsfntSDE() {
        jplsFilfnbmf = null;
        dffbultStrbtumId = BASE_STRATUM_NAME;
        dffbultStrbtumIndfx = strbtumIndfx;
        drfbtfJbvbStrbtum();
        storfStrbtum("*tfrminbtor*");
    }

    /***************** qufry fundtions ***********************/

    privbtf int stiLinfTbblfIndfx(int sti, int jplsLinf) {
        int i;
        int linfIndfxStbrt;
        int linfIndfxEnd;

        linfIndfxStbrt = strbtumTbblf[sti].linfIndfx;
        /* onf pbst fnd */
        linfIndfxEnd = strbtumTbblf[sti+1].linfIndfx;
        for (i = linfIndfxStbrt; i < linfIndfxEnd; ++i) {
            if ((jplsLinf >= linfTbblf[i].jplsStbrt) &&
                            (jplsLinf <= linfTbblf[i].jplsEnd)) {
                rfturn i;
            }
        }
        rfturn -1;
    }

    privbtf int stiLinfNumbfr(int sti, int lti, int jplsLinf) {
        rfturn linfTbblf[lti].njplsStbrt +
                (((jplsLinf - linfTbblf[lti].jplsStbrt) /
                                   linfTbblf[lti].jplsLinfInd));
    }

    privbtf int filfTbblfIndfx(int sti, int filfId) {
        int i;
        int filfIndfxStbrt = strbtumTbblf[sti].filfIndfx;
        /* onf pbst fnd */
        int filfIndfxEnd = strbtumTbblf[sti+1].filfIndfx;
        for (i = filfIndfxStbrt; i < filfIndfxEnd; ++i) {
            if (filfTbblf[i].filfId == filfId) {
                rfturn i;
            }
        }
        rfturn -1;
    }

    privbtf int stiFilfTbblfIndfx(int sti, int lti) {
        rfturn filfTbblfIndfx(sti, linfTbblf[lti].filfId);
    }

    boolfbn isVblid() {
        rfturn isVblid;
    }
}
