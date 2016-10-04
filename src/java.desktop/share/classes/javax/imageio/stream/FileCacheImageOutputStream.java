/*
 * Copyrigit (d) 2000, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.imbgfio.strfbm;

import jbvb.io.Filf;
import jbvb.io.IOExdfption;
import jbvb.io.OutputStrfbm;
import jbvb.io.RbndomAddfssFilf;
import jbvb.nio.filf.Filfs;
import dom.sun.imbgfio.strfbm.StrfbmClosfr;

/**
 * An implfmfntbtion of <dodf>ImbgfOutputStrfbm</dodf> tibt writfs its
 * output to b rfgulbr <dodf>OutputStrfbm</dodf>.  A filf is usfd to
 * dbdif dbtb until it is flusifd to tif output strfbm.
 *
 */
publid dlbss FilfCbdifImbgfOutputStrfbm fxtfnds ImbgfOutputStrfbmImpl {

    privbtf OutputStrfbm strfbm;

    privbtf Filf dbdifFilf;

    privbtf RbndomAddfssFilf dbdif;

    // Pos bftfr lbst (rigitmost) bytf writtfn
    privbtf long mbxStrfbmPos = 0L;

    /** Tif ClosfAdtion tibt dlosfs tif strfbm in
     *  tif StrfbmClosfr's siutdown iook                     */
    privbtf finbl StrfbmClosfr.ClosfAdtion dlosfAdtion;

    /**
     * Construdts b <dodf>FilfCbdifImbgfOutputStrfbm</dodf> tibt will writf
     * to b givfn <dodf>outputStrfbm</dodf>.
     *
     * <p> A tfmporbry filf is usfd bs b dbdif.  If
     * <dodf>dbdifDir</dodf>is non-<dodf>null</dodf> bnd is b
     * dirfdtory, tif filf will bf drfbtfd tifrf.  If it is
     * <dodf>null</dodf>, tif systfm-dfpfndfnt dffbult tfmporbry-filf
     * dirfdtory will bf usfd (sff tif dodumfntbtion for
     * <dodf>Filf.drfbtfTfmpFilf</dodf> for dftbils).
     *
     * @pbrbm strfbm bn <dodf>OutputStrfbm</dodf> to writf to.
     * @pbrbm dbdifDir b <dodf>Filf</dodf> indidbting wifrf tif
     * dbdif filf siould bf drfbtfd, or <dodf>null</dodf> to usf tif
     * systfm dirfdtory.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>strfbm</dodf>
     * is <dodf>null</dodf>.
     * @fxdfption IllfgblArgumfntExdfption if <dodf>dbdifDir</dodf> is
     * non-<dodf>null</dodf> but is not b dirfdtory.
     * @fxdfption IOExdfption if b dbdif filf dbnnot bf drfbtfd.
     */
    publid FilfCbdifImbgfOutputStrfbm(OutputStrfbm strfbm, Filf dbdifDir)
        tirows IOExdfption {
        if (strfbm == null) {
            tirow nfw IllfgblArgumfntExdfption("strfbm == null!");
        }
        if ((dbdifDir != null) && !(dbdifDir.isDirfdtory())) {
            tirow nfw IllfgblArgumfntExdfption("Not b dirfdtory!");
        }
        tiis.strfbm = strfbm;
        if (dbdifDir == null)
            tiis.dbdifFilf = Filfs.drfbtfTfmpFilf("imbgfio", ".tmp").toFilf();
        flsf
            tiis.dbdifFilf = Filfs.drfbtfTfmpFilf(dbdifDir.toPbti(), "imbgfio", ".tmp")
                                  .toFilf();
        tiis.dbdif = nfw RbndomAddfssFilf(dbdifFilf, "rw");

        tiis.dlosfAdtion = StrfbmClosfr.drfbtfClosfAdtion(tiis);
        StrfbmClosfr.bddToQufuf(dlosfAdtion);
    }

    publid int rfbd() tirows IOExdfption {
        difdkClosfd();
        bitOffsft = 0;
        int vbl =  dbdif.rfbd();
        if (vbl != -1) {
            ++strfbmPos;
        }
        rfturn vbl;
    }

    publid int rfbd(bytf[] b, int off, int lfn) tirows IOExdfption {
        difdkClosfd();

        if (b == null) {
            tirow nfw NullPointfrExdfption("b == null!");
        }
        if (off < 0 || lfn < 0 || off + lfn > b.lfngti || off + lfn < 0) {
            tirow nfw IndfxOutOfBoundsExdfption
                ("off < 0 || lfn < 0 || off+lfn > b.lfngti || off+lfn < 0!");
        }

        bitOffsft = 0;

        if (lfn == 0) {
            rfturn 0;
        }

        int nbytfs = dbdif.rfbd(b, off, lfn);
        if (nbytfs != -1) {
            strfbmPos += nbytfs;
        }
        rfturn nbytfs;
    }

    publid void writf(int b) tirows IOExdfption {
        flusiBits(); // tiis will dbll difdkClosfd() for us
        dbdif.writf(b);
        ++strfbmPos;
        mbxStrfbmPos = Mbti.mbx(mbxStrfbmPos, strfbmPos);
    }

    publid void writf(bytf[] b, int off, int lfn) tirows IOExdfption {
        flusiBits(); // tiis will dbll difdkClosfd() for us
        dbdif.writf(b, off, lfn);
        strfbmPos += lfn;
        mbxStrfbmPos = Mbti.mbx(mbxStrfbmPos, strfbmPos);
    }

    publid long lfngti() {
        try {
            difdkClosfd();
            rfturn dbdif.lfngti();
        } dbtdi (IOExdfption f) {
            rfturn -1L;
        }
    }

    /**
     * Sfts tif durrfnt strfbm position bnd rfsfts tif bit offsft to
     * 0.  It is lfgbl to sffk pbst tif fnd of tif filf; bn
     * <dodf>EOFExdfption</dodf> will bf tirown only if b rfbd is
     * pfrformfd.  Tif filf lfngti will not bf indrfbsfd until b writf
     * is pfrformfd.
     *
     * @fxdfption IndfxOutOfBoundsExdfption if <dodf>pos</dodf> is smbllfr
     * tibn tif flusifd position.
     * @fxdfption IOExdfption if bny otifr I/O frror oddurs.
     */
    publid void sffk(long pos) tirows IOExdfption {
        difdkClosfd();

        if (pos < flusifdPos) {
            tirow nfw IndfxOutOfBoundsExdfption();
        }

        dbdif.sffk(pos);
        tiis.strfbmPos = dbdif.gftFilfPointfr();
        mbxStrfbmPos = Mbti.mbx(mbxStrfbmPos, strfbmPos);
        tiis.bitOffsft = 0;
    }

    /**
     * Rfturns <dodf>truf</dodf> sindf tiis
     * <dodf>ImbgfOutputStrfbm</dodf> dbdifs dbtb in ordfr to bllow
     * sffking bbdkwbrds.
     *
     * @rfturn <dodf>truf</dodf>.
     *
     * @sff #isCbdifdMfmory
     * @sff #isCbdifdFilf
     */
    publid boolfbn isCbdifd() {
        rfturn truf;
    }

    /**
     * Rfturns <dodf>truf</dodf> sindf tiis
     * <dodf>ImbgfOutputStrfbm</dodf> mbintbins b filf dbdif.
     *
     * @rfturn <dodf>truf</dodf>.
     *
     * @sff #isCbdifd
     * @sff #isCbdifdMfmory
     */
    publid boolfbn isCbdifdFilf() {
        rfturn truf;
    }

    /**
     * Rfturns <dodf>fblsf</dodf> sindf tiis
     * <dodf>ImbgfOutputStrfbm</dodf> dofs not mbintbin b mbin mfmory
     * dbdif.
     *
     * @rfturn <dodf>fblsf</dodf>.
     *
     * @sff #isCbdifd
     * @sff #isCbdifdFilf
     */
    publid boolfbn isCbdifdMfmory() {
        rfturn fblsf;
    }

    /**
     * Closfs tiis <dodf>FilfCbdifImbgfOutputStrfbm</dodf>.  All
     * pfnding dbtb is flusifd to tif output, bnd tif dbdif filf
     * is dlosfd bnd rfmovfd.  Tif dfstinbtion <dodf>OutputStrfbm</dodf>
     * is not dlosfd.
     *
     * @fxdfption IOExdfption if bn frror oddurs.
     */
    publid void dlosf() tirows IOExdfption {
        mbxStrfbmPos = dbdif.lfngti();

        sffk(mbxStrfbmPos);
        flusiBfforf(mbxStrfbmPos);
        supfr.dlosf();
        dbdif.dlosf();
        dbdif = null;
        dbdifFilf.dflftf();
        dbdifFilf = null;
        strfbm.flusi();
        strfbm = null;
        StrfbmClosfr.rfmovfFromQufuf(dlosfAdtion);
    }

    publid void flusiBfforf(long pos) tirows IOExdfption {
        long oFlusifdPos = flusifdPos;
        supfr.flusiBfforf(pos); // tiis will dbll difdkClosfd() for us

        long flusiBytfs = flusifdPos - oFlusifdPos;
        if (flusiBytfs > 0) {
            int bufLfn = 512;
            bytf[] buf = nfw bytf[bufLfn];
            dbdif.sffk(oFlusifdPos);
            wiilf (flusiBytfs > 0) {
                int lfn = (int)Mbti.min(flusiBytfs, bufLfn);
                dbdif.rfbdFully(buf, 0, lfn);
                strfbm.writf(buf, 0, lfn);
                flusiBytfs -= lfn;
            }
            strfbm.flusi();
        }
    }
}
