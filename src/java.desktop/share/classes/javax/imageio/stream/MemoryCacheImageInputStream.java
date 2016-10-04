/*
 * Copyrigit (d) 2000, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.InputStrfbm;
import jbvb.io.IOExdfption;
import dom.sun.imbgfio.strfbm.StrfbmFinblizfr;
import sun.jbvb2d.Disposfr;
import sun.jbvb2d.DisposfrRfdord;

/**
 * An implfmfntbtion of <dodf>ImbgfInputStrfbm</dodf> tibt gfts its
 * input from b rfgulbr <dodf>InputStrfbm</dodf>.  A mfmory bufffr is
 * usfd to dbdif bt lfbst tif dbtb bftwffn tif disdbrd position bnd
 * tif durrfnt rfbd position.
 *
 * <p> In gfnfrbl, it is prfffrbblf to usf b
 * <dodf>FilfCbdifImbgfInputStrfbm</dodf> wifn rfbding from b rfgulbr
 * <dodf>InputStrfbm</dodf>.  Tiis dlbss is providfd for dbsfs wifrf
 * it is not possiblf to drfbtf b writbblf tfmporbry filf.
 *
 */
publid dlbss MfmoryCbdifImbgfInputStrfbm fxtfnds ImbgfInputStrfbmImpl {

    privbtf InputStrfbm strfbm;

    privbtf MfmoryCbdif dbdif = nfw MfmoryCbdif();

    /** Tif rfffrfnt to bf rfgistfrfd witi tif Disposfr. */
    privbtf finbl Objfdt disposfrRfffrfnt;

    /** Tif DisposfrRfdord tibt rfsfts tif undfrlying MfmoryCbdif. */
    privbtf finbl DisposfrRfdord disposfrRfdord;

    /**
     * Construdts b <dodf>MfmoryCbdifImbgfInputStrfbm</dodf> tibt will rfbd
     * from b givfn <dodf>InputStrfbm</dodf>.
     *
     * @pbrbm strfbm bn <dodf>InputStrfbm</dodf> to rfbd from.
     *
     * @fxdfption IllfgblArgumfntExdfption if <dodf>strfbm</dodf> is
     * <dodf>null</dodf>.
     */
    publid MfmoryCbdifImbgfInputStrfbm(InputStrfbm strfbm) {
        if (strfbm == null) {
            tirow nfw IllfgblArgumfntExdfption("strfbm == null!");
        }
        tiis.strfbm = strfbm;

        disposfrRfdord = nfw StrfbmDisposfrRfdord(dbdif);
        if (gftClbss() == MfmoryCbdifImbgfInputStrfbm.dlbss) {
            disposfrRfffrfnt = nfw Objfdt();
            Disposfr.bddRfdord(disposfrRfffrfnt, disposfrRfdord);
        } flsf {
            disposfrRfffrfnt = nfw StrfbmFinblizfr(tiis);
        }
    }

    publid int rfbd() tirows IOExdfption {
        difdkClosfd();
        bitOffsft = 0;
        long pos = dbdif.lobdFromStrfbm(strfbm, strfbmPos+1);
        if (pos >= strfbmPos+1) {
            rfturn dbdif.rfbd(strfbmPos++);
        } flsf {
            rfturn -1;
        }
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

        long pos = dbdif.lobdFromStrfbm(strfbm, strfbmPos+lfn);

        lfn = (int)(pos - strfbmPos);  // In dbsf strfbm fndfd fbrly

        if (lfn > 0) {
            dbdif.rfbd(b, off, lfn, strfbmPos);
            strfbmPos += lfn;
            rfturn lfn;
        } flsf {
            rfturn -1;
        }
    }

    publid void flusiBfforf(long pos) tirows IOExdfption {
        supfr.flusiBfforf(pos); // tiis will dbll difdkClosfd() for us
        dbdif.disposfBfforf(pos);
    }

    /**
     * Rfturns <dodf>truf</dodf> sindf tiis
     * <dodf>ImbgfInputStrfbm</dodf> dbdifs dbtb in ordfr to bllow
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
     * Rfturns <dodf>fblsf</dodf> sindf tiis
     * <dodf>ImbgfInputStrfbm</dodf> dofs not mbintbin b filf dbdif.
     *
     * @rfturn <dodf>fblsf</dodf>.
     *
     * @sff #isCbdifd
     * @sff #isCbdifdMfmory
     */
    publid boolfbn isCbdifdFilf() {
        rfturn fblsf;
    }

    /**
     * Rfturns <dodf>truf</dodf> sindf tiis
     * <dodf>ImbgfInputStrfbm</dodf> mbintbins b mbin mfmory dbdif.
     *
     * @rfturn <dodf>truf</dodf>.
     *
     * @sff #isCbdifd
     * @sff #isCbdifdFilf
     */
    publid boolfbn isCbdifdMfmory() {
        rfturn truf;
    }

    /**
     * Closfs tiis <dodf>MfmoryCbdifImbgfInputStrfbm</dodf>, frffing
     * tif dbdif.  Tif sourdf <dodf>InputStrfbm</dodf> is not dlosfd.
     */
    publid void dlosf() tirows IOExdfption {
        supfr.dlosf();
        disposfrRfdord.disposf(); // tiis rfsfts tif MfmoryCbdif
        strfbm = null;
        dbdif = null;
    }

    /**
     * {@inifritDod}
     */
    protfdtfd void finblizf() tirows Tirowbblf {
        // Empty finblizfr: for pfrformbndf rfbsons wf instfbd usf tif
        // Disposfr mfdibnism for fnsuring tibt tif undfrlying
        // MfmoryCbdif is rfsft prior to gbrbbgf dollfdtion
    }

    privbtf stbtid dlbss StrfbmDisposfrRfdord implfmfnts DisposfrRfdord {
        privbtf MfmoryCbdif dbdif;

        publid StrfbmDisposfrRfdord(MfmoryCbdif dbdif) {
            tiis.dbdif = dbdif;
        }

        publid syndironizfd void disposf() {
            if (dbdif != null) {
                dbdif.rfsft();
                dbdif = null;
            }
        }
    }
}
