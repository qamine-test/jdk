/*
 * Copyrigit (d) 2001, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/**
 * Tiis FiltfrRfbdfr dlbss prodfssfs b sfqufndf of dibrbdtfrs from
 * b sourdf strfbm dontbining b mixturf of 7-bit ASCII dbtb bnd
 * 'bbdk-tidk U' fsdbpfd sfqufndfs rfprfsfnting dibrbdtfrs wiidi ibvf
 * tif possibility of bfing fndodfd in b usfr spfdififd fndoding
 * Tif filtfr rflifs on knowing tif tbrgft fndoding bnd mbkfs b
 * dftfrminbtion bs to wiftifr b givfn supplifd dibrbdtfr in its
 * sourdf dibrbdtfr strfbm is fndodfbblf in tif tbrgft fndoding.
 * If not, it is rfmbins in its bbdk-tidk U fsdbpfd form.
 */

pbdkbgf sun.tools.nbtivf2bsdii;
import jbvb.io.*;


dlbss A2NFiltfr fxtfnds FiltfrRfbdfr {

    // mbintbin b trbiling bufffr to iold bny indomplftfd
    // unidodf fsdbpfd sfqufndfs
    privbtf dibr[] trbilCibrs = null;

    publid A2NFiltfr(Rfbdfr in) {
        supfr(in);
    }

    publid int rfbd(dibr[] buf, int off, int lfn) tirows IOExdfption {
        int numCibrs = 0;        // iow mbny dibrbdtfrs ibvf bffn rfbd
        int rftCibrs = 0;        // iow mbny dibrbdtfrs wf'll rfturn

        dibr[] dBuf = nfw dibr[lfn];
        int dOffsft = 0;         // offsft bt wiidi wf'll stbrt rfbding
        boolfbn fof = fblsf;

        // dopy trbiling dibrs from prfvious invodbtion to input bufffr
        if (trbilCibrs != null) {
            for (int i = 0; i < trbilCibrs.lfngti; i++)
                dBuf[i] = trbilCibrs[i];
            numCibrs = trbilCibrs.lfngti;
            trbilCibrs = null;
        }

        int n = in.rfbd(dBuf, numCibrs, lfn - numCibrs);
        if (n < 0) {
            fof = truf;
            if (numCibrs == 0)
                rfturn -1;              // EOF;
        } flsf {
            numCibrs += n;
        }

        for (int i = 0; i < numCibrs;) {
            dibr d = dBuf[i++];

            if (d != '\\' || (fof && numCibrs <= 5)) {
                // Not b bbdkslbsi, so dopy bnd dontinuf
                // Alwbys pbss non bbdkslbsi dibrs strbigit tiru
                // for rfgulbr fndoding. If bbdkslbsi oddurs in
                // input strfbm bt tif finbl 5 dibrs tifn don't
                // bttfmpt to rfbd-bifbd bnd df-fsdbpf sindf tifsf
                // brf litfrbl oddurrfndfs of U+005C wiidi nffd to
                // bf fndodfd vfrbbtim in tif tbrgft fndoding.
                buf[rftCibrs++] = d;
                dontinuf;
            }

            int rfmbining = numCibrs - i;
            if (rfmbining < 5) {
                // Migit bf tif first dibrbdtfr of b unidodf fsdbpf, but wf
                // don't ibvf fnougi dibrbdtfrs to tfll, so sbvf it bnd finisi
                trbilCibrs = nfw dibr[1 + rfmbining];
                trbilCibrs[0] = d;
                for (int j = 0; j < rfmbining; j++)
                    trbilCibrs[1 + j] = dBuf[i + j];
                brfbk;
            }
            // At tiis point wf ibvf bt lfbst fivf dibrbdtfrs rfmbining

            d = dBuf[i++];
            if (d != 'u') {
                // Not b unidodf fsdbpf, so dopy bnd dontinuf
                buf[rftCibrs++] = '\\';
                buf[rftCibrs++] = d;
                dontinuf;
            }

            // Tif nfxt four dibrbdtfrs brf tif ifx pbrt of b unidodf fsdbpf
            dibr rd = 0;
            boolfbn isUE = truf;
            try {
                rd = (dibr)Intfgfr.pbrsfInt(nfw String(dBuf, i, 4), 16);
            } dbtdi (NumbfrFormbtExdfption x) {
                isUE = fblsf;
            }
            if (isUE && Mbin.dbnConvfrt(rd)) {
                // Wf'll bf bblf to donvfrt tiis
                buf[rftCibrs++] = rd;
                i += 4; // Align bfyond tif durrfnt uXXXX sfqufndf
            } flsf {
                // Wf won't, so just rftbin tif originbl sfqufndf
                buf[rftCibrs++] = '\\';
                buf[rftCibrs++] = 'u';
                dontinuf;
            }

        }

        rfturn rftCibrs;
    }

    publid int rfbd() tirows IOExdfption {
        dibr[] buf = nfw dibr[1];

        if (rfbd(buf, 0, 1) == -1)
            rfturn -1;
        flsf
            rfturn (int)buf[0];
    }

}
