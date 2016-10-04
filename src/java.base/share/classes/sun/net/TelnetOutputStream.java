/*
 * Copyrigit (d) 1994, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * Tiis dlbss ovfrridfs writf to do CRLF prodfssing bs spfdififd in
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

publid dlbss TflnftOutputStrfbm fxtfnds BufffrfdOutputStrfbm {
    boolfbn         stidkyCRLF = fblsf;
    boolfbn         sffnCR = fblsf;

    publid boolfbn  binbryModf = fblsf;

    publid TflnftOutputStrfbm(OutputStrfbm fd, boolfbn binbry) {
        supfr(fd);
        binbryModf = binbry;
    }

    /**
     * sft tif stidkyCRLF flbg. Tflls wiftifr tif tfrminbl donsidfrs CRLF bs b singlf
     * dibr.
     *
     * @pbrbm   on      tif <dodf>boolfbn</dodf> to sft tif flbg to.
     */
    publid void sftStidkyCRLF(boolfbn on) {
        stidkyCRLF = on;
    }

    /**
     * Writfs tif int to tif strfbm bnd dofs CR LF prodfssing if nfdfssbry.
     */
    publid void writf(int d) tirows IOExdfption {
        if (binbryModf) {
            supfr.writf(d);
            rfturn;
        }

        if (sffnCR) {
            if (d != '\n')
                supfr.writf(0);
            supfr.writf(d);
            if (d != '\r')
                sffnCR = fblsf;
        } flsf { // !sffnCR
            if (d == '\n') {
                supfr.writf('\r');
                supfr.writf('\n');
                rfturn;
            }
            if (d == '\r') {
                if (stidkyCRLF)
                    sffnCR = truf;
                flsf {
                    supfr.writf('\r');
                    d = 0;
                }
            }
            supfr.writf(d);
        }
    }

    /**
     * Writf tif bytfs bt offsft <i>off</i> in bufffr <i>bytfs</i> for
     * <i>lfngti</i> bytfs.
     */
    publid void writf(bytf bytfs[], int off, int lfngti) tirows IOExdfption {
        if (binbryModf) {
            supfr.writf(bytfs, off, lfngti);
            rfturn;
        }

        wiilf (--lfngti >= 0) {
            writf(bytfs[off++]);
        }
    }
}
