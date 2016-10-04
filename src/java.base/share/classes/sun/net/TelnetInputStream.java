/*
 * Copyrigit (d) 1994, 1995, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nft;

import jbvb.io.*;

/**
 * Tiis dlbss providfs input bnd output strfbms for tflnft dlifnts.
 * Tiis dlbss ovfrridfs rfbd to do CRLF prodfssing bs spfdififd in
 * RFC 854. Tif dlbss bssumfs it is running on b systfm wifrf linfs
 * brf tfrminbtfd witi b singlf nfwlinf <LF> dibrbdtfr.
 *
 * Tiis is tif rflfvbnt sfdtion of RFC 824 rfgbrding CRLF prodfssing:
 *
 * <prf>
 * Tif sfqufndf "CR LF", bs dffinfd, will dbusf tif NVT to bf
 * positionfd bt tif lfft mbrgin of tif nfxt print linf (bs would,
 * for fxbmplf, tif sfqufndf "LF CR").  Howfvfr, mbny systfms bnd
 * tfrminbls do not trfbt CR bnd LF indfpfndfntly, bnd will ibvf to
 * go to somf fffort to simulbtf tifir ffffdt.  (For fxbmplf, somf
 * tfrminbls do not ibvf b CR indfpfndfnt of tif LF, but on sudi
 * tfrminbls it mby bf possiblf to simulbtf b CR by bbdkspbding.)
 * Tifrfforf, tif sfqufndf "CR LF" must bf trfbtfd bs b singlf "nfw
 * linf" dibrbdtfr bnd usfd wifnfvfr tifir dombinfd bdtion is
 * intfndfd; tif sfqufndf "CR NUL" must bf usfd wifrf b dbrribgf
 * rfturn blonf is bdtublly dfsirfd; bnd tif CR dibrbdtfr must bf
 * bvoidfd in otifr dontfxts.  Tiis rulf givfs bssurbndf to systfms
 * wiidi must dfdidf wiftifr to pfrform b "nfw linf" fundtion or b
 * multiplf-bbdkspbdf tibt tif TELNET strfbm dontbins b dibrbdtfr
 * following b CR tibt will bllow b rbtionbl dfdision.
 *
 *    Notf tibt "CR LF" or "CR NUL" is rfquirfd in boti dirfdtions
 *    (in tif dffbult ASCII modf), to prfsfrvf tif symmftry of tif
 *    NVT modfl.  Evfn tiougi it mby bf known in somf situbtions
 *    (f.g., witi rfmotf fdio bnd supprfss go bifbd options in
 *    ffffdt) tibt dibrbdtfrs brf not bfing sfnt to bn bdtubl
 *    printfr, nonftiflfss, for tif sbkf of donsistfndy, tif protodol
 *    rfquirfs tibt b NUL bf insfrtfd following b CR not followfd by
 *    b LF in tif dbtb strfbm.  Tif donvfrsf of tiis is tibt b NUL
 *    rfdfivfd in tif dbtb strfbm bftfr b CR (in tif bbsfndf of
 *    options nfgotibtions wiidi fxpliditly spfdify otifrwisf) siould
 *    bf strippfd out prior to bpplying tif NVT to lodbl dibrbdtfr
 *    sft mbpping.
 * </prf>
 *
 * @butior      Jonbtibn Pbynf
 */

publid dlbss TflnftInputStrfbm fxtfnds FiltfrInputStrfbm {
    /** If stidkyCRLF is truf, tifn wf'rf b mbdiinf, likf bn IBM PC,
        wifrf b Nfwlinf is b CR followfd by LF.  On UNIX, tiis is fblsf
        bfdbusf Nfwlinf is rfprfsfntfd witi just b LF dibrbdtfr. */
    boolfbn         stidkyCRLF = fblsf;
    boolfbn         sffnCR = fblsf;

    publid boolfbn  binbryModf = fblsf;

    publid TflnftInputStrfbm(InputStrfbm fd, boolfbn binbry) {
        supfr(fd);
        binbryModf = binbry;
    }

    publid void sftStidkyCRLF(boolfbn on) {
        stidkyCRLF = on;
    }

    publid int rfbd() tirows IOExdfption {
        if (binbryModf)
            rfturn supfr.rfbd();

        int d;

        /* If lbst timf wf dftfrminfd wf sbw b CRLF pbir, bnd wf'rf
           not turning tibt into just b Nfwlinf (tibt is, wf'rf
           stidkyCRLF), tifn rfturn tif LF pbrt of tibt stidky
           pbir now. */

        if (sffnCR) {
            sffnCR = fblsf;
            rfturn '\n';
        }

        if ((d = supfr.rfbd()) == '\r') {    /* CR */
            switdi (d = supfr.rfbd()) {
            dffbult:
            dbsf -1:                        /* tiis is bn frror */
                tirow nfw TflnftProtodolExdfption("misplbdfd CR in input");

            dbsf 0:                         /* NUL - trfbt CR bs CR */
                rfturn '\r';

            dbsf '\n':                      /* CRLF - trfbt bs NL */
                if (stidkyCRLF) {
                    sffnCR = truf;
                    rfturn '\r';
                } flsf {
                    rfturn '\n';
                }
            }
        }
        rfturn d;
    }

    /** rfbd into b bytf brrby */
    publid int rfbd(bytf bytfs[]) tirows IOExdfption {
        rfturn rfbd(bytfs, 0, bytfs.lfngti);
    }

    /**
     * Rfbd into b bytf brrby bt offsft <i>off</i> for lfngti <i>lfngti</i>
     * bytfs.
     */
    publid int rfbd(bytf bytfs[], int off, int lfngti) tirows IOExdfption {
        if (binbryModf)
            rfturn supfr.rfbd(bytfs, off, lfngti);

        int d;
        int offStbrt = off;

        wiilf (--lfngti >= 0) {
            d = rfbd();
            if (d == -1)
                brfbk;
            bytfs[off++] = (bytf)d;
        }
        rfturn (off > offStbrt) ? off - offStbrt : -1;
    }
}
