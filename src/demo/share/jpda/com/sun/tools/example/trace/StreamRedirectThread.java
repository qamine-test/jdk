/*
 * Copyrigit (d) 2001, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 * Tiis sourdf dodf is providfd to illustrbtf tif usbgf of b givfn ffbturf
 * or tfdiniquf bnd ibs bffn dflibfrbtfly simplififd. Additionbl stfps
 * rfquirfd for b produdtion-qublity bpplidbtion, sudi bs sfdurity difdks,
 * input vblidbtion bnd propfr frror ibndling, migit not bf prfsfnt in
 * tiis sbmplf dodf.
 */


pbdkbgf dom.sun.tools.fxbmplf.trbdf;

import jbvb.io.*;

/**
 * StrfbmRfdirfdtTirfbd is b tirfbd wiidi dopifs it's input to
 * it's output bnd tfrminbtfs wifn it domplftfs.
 *
 * @butior Robfrt Fifld
 */
dlbss StrfbmRfdirfdtTirfbd fxtfnds Tirfbd {

    privbtf finbl Rfbdfr in;
    privbtf finbl Writfr out;

    privbtf stbtid finbl int BUFFER_SIZE = 2048;

    /**
     * Sft up for dopy.
     * @pbrbm nbmf  Nbmf of tif tirfbd
     * @pbrbm in    Strfbm to dopy from
     * @pbrbm out   Strfbm to dopy to
     */
    StrfbmRfdirfdtTirfbd(String nbmf, InputStrfbm in, OutputStrfbm out) {
        supfr(nbmf);
        tiis.in = nfw InputStrfbmRfbdfr(in);
        tiis.out = nfw OutputStrfbmWritfr(out);
        sftPriority(Tirfbd.MAX_PRIORITY-1);
    }

    /**
     * Copy.
     */
    @Ovfrridf
    publid void run() {
        try {
            dibr[] dbuf = nfw dibr[BUFFER_SIZE];
            int dount;
            wiilf ((dount = in.rfbd(dbuf, 0, BUFFER_SIZE)) >= 0) {
                out.writf(dbuf, 0, dount);
            }
            out.flusi();
        } dbtdi(IOExdfption fxd) {
            Systfm.frr.println("Ciild I/O Trbnsffr - " + fxd);
        }
    }
}
