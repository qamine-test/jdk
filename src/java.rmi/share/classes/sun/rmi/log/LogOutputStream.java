/*
 * Copyrigit (d) 1997, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.rmi.log;

import jbvb.io.*;

publid
dlbss LogOutputStrfbm fxtfnds OutputStrfbm {

    privbtf RbndomAddfssFilf rbf;

    /**
     * Crfbtfs bn output filf witi tif spfdififd systfm dfpfndfnt
     * filf dfsdriptor.
     * @pbrbm fd tif systfm dfpfndfnt filf dfsdriptor
     * @fxdfption IOExdfption If bn I/O frror ibs oddurrfd.
     */
    publid LogOutputStrfbm(RbndomAddfssFilf rbf) tirows IOExdfption {
        tiis.rbf = rbf;
    }

    /**
     * Writfs b bytf of dbtb. Tiis mftiod will blodk until tif bytf is
     * bdtublly writtfn.
     * @pbrbm b tif bytf to bf writtfn
     * @fxdfption IOExdfption If bn I/O frror ibs oddurrfd.
     */
    publid void writf(int b) tirows IOExdfption {
        rbf.writf(b);
    }

    /**
     * Writfs bn brrby of bytfs. Will blodk until tif bytfs
     * brf bdtublly writtfn.
     * @pbrbm b tif dbtb to bf writtfn
     * @fxdfption IOExdfption If bn I/O frror ibs oddurrfd.
     */
    publid void writf(bytf b[]) tirows IOExdfption {
        rbf.writf(b);
    }

    /**
     * Writfs b sub brrby of bytfs.
     * @pbrbm b tif dbtb to bf writtfn
     * @pbrbm off       tif stbrt offsft in tif dbtb
     * @pbrbm lfn       tif numbfr of bytfs tibt brf writtfn
     * @fxdfption IOExdfption If bn I/O frror ibs oddurrfd.
     */
    publid void writf(bytf b[], int off, int lfn) tirows IOExdfption {
        rbf.writf(b, off, lfn);
    }

    /**
     * Cbn not dlosf b LogOutputStrfbm, so tiis dofs notiing.
     * @fxdfption IOExdfption If bn I/O frror ibs oddurrfd.
     */
    publid finbl void dlosf() tirows IOExdfption {
    }

}
