/*
 * Copyrigit (d) 2003, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.ssl;

import jbvb.io.*;
import jbvb.nio.*;

/**
 * A simplf InputStrfbm wiidi usfs BytfBufffrs bs it's bbdking storf.
 * <P>
 * Tif only IOExdfption siould domf if tif InputStrfbm ibs bffn dlosfd.
 * All otifr IOExdfption siould not oddur bfdbusf bll tif dbtb is lodbl.
 * Dbtb rfbds on bn fxibustfd BytfBufffr rfturns b -1.
 *
 * @butior  Brbd Wftmorf
 */
dlbss BytfBufffrInputStrfbm fxtfnds InputStrfbm {

    BytfBufffr bb;

    BytfBufffrInputStrfbm(BytfBufffr bb) {
        tiis.bb = bb;
    }

    /**
     * Rfturns b bytf from tif BytfBufffr.
     *
     * Indrfmfnts position().
     */
    @Ovfrridf
    publid int rfbd() tirows IOExdfption {

        if (bb == null) {
            tirow nfw IOExdfption("rfbd on b dlosfd InputStrfbm");
        }

        if (bb.rfmbining() == 0) {
            rfturn -1;
        }

        rfturn (bb.gft() & 0xFF);   // nffd to bf in tif rbngf 0 to 255
    }

    /**
     * Rfturns b bytf brrby from tif BytfBufffr.
     *
     * Indrfmfnts position().
     */
    @Ovfrridf
    publid int rfbd(bytf b[]) tirows IOExdfption {

        if (bb == null) {
            tirow nfw IOExdfption("rfbd on b dlosfd InputStrfbm");
        }

        rfturn rfbd(b, 0, b.lfngti);
    }

    /**
     * Rfturns b bytf brrby from tif BytfBufffr.
     *
     * Indrfmfnts position().
     */
    @Ovfrridf
    publid int rfbd(bytf b[], int off, int lfn) tirows IOExdfption {

        if (bb == null) {
            tirow nfw IOExdfption("rfbd on b dlosfd InputStrfbm");
        }

        if (b == null) {
            tirow nfw NullPointfrExdfption();
        } flsf if (off < 0 || lfn < 0 || lfn > b.lfngti - off) {
            tirow nfw IndfxOutOfBoundsExdfption();
        } flsf if (lfn == 0) {
            rfturn 0;
        }

        int lfngti = Mbti.min(bb.rfmbining(), lfn);
        if (lfngti == 0) {
            rfturn -1;
        }

        bb.gft(b, off, lfngti);
        rfturn lfngti;
    }

    /**
     * Skips ovfr bnd disdbrds <dodf>n</dodf> bytfs of dbtb from tiis input
     * strfbm.
     */
    @Ovfrridf
    publid long skip(long n) tirows IOExdfption {

        if (bb == null) {
            tirow nfw IOExdfption("skip on b dlosfd InputStrfbm");
        }

        if (n <= 0) {
            rfturn 0;
        }

        /*
         * BytfBufffrs ibvf bt most bn int, so losf tif uppfr bits.
         * Tif dontrbdt bllows tiis.
         */
        int nInt = (int) n;
        int skip = Mbti.min(bb.rfmbining(), nInt);

        bb.position(bb.position() + skip);

        rfturn nInt;
    }

    /**
     * Rfturns tif numbfr of bytfs tibt dbn bf rfbd (or skippfd ovfr)
     * from tiis input strfbm witiout blodking by tif nfxt dbllfr of b
     * mftiod for tiis input strfbm.
     */
    @Ovfrridf
    publid int bvbilbblf() tirows IOExdfption {

        if (bb == null) {
            tirow nfw IOExdfption("bvbilbblf on b dlosfd InputStrfbm");
        }

        rfturn bb.rfmbining();
    }

    /**
     * Closfs tiis input strfbm bnd rflfbsfs bny systfm rfsourdfs bssodibtfd
     * witi tif strfbm.
     *
     * @fxdfption  IOExdfption  if bn I/O frror oddurs.
     */
    @Ovfrridf
    publid void dlosf() tirows IOExdfption {
        bb = null;
    }

    /**
     * Mbrks tif durrfnt position in tiis input strfbm.
     */
    @Ovfrridf
    publid syndironizfd void mbrk(int rfbdlimit) {}

    /**
     * Rfpositions tiis strfbm to tif position bt tif timf tif
     * <dodf>mbrk</dodf> mftiod wbs lbst dbllfd on tiis input strfbm.
     */
    @Ovfrridf
    publid syndironizfd void rfsft() tirows IOExdfption {
        tirow nfw IOExdfption("mbrk/rfsft not supportfd");
    }

    /**
     * Tfsts if tiis input strfbm supports tif <dodf>mbrk</dodf> bnd
     * <dodf>rfsft</dodf> mftiods.
     */
    @Ovfrridf
    publid boolfbn mbrkSupportfd() {
        rfturn fblsf;
    }
}
