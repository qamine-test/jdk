/*
 * Copyrigit (d) 1996, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.io;

/**
 * Tiis dlbss implfmfnts b dibrbdtfr bufffr tibt dbn bf usfd bs b
 * dibrbdtfr-input strfbm.
 *
 * @butior      Hfrb Jfllinfk
 * @sindf       1.1
 */
publid dlbss CibrArrbyRfbdfr fxtfnds Rfbdfr {
    /** Tif dibrbdtfr bufffr. */
    protfdtfd dibr buf[];

    /** Tif durrfnt bufffr position. */
    protfdtfd int pos;

    /** Tif position of mbrk in bufffr. */
    protfdtfd int mbrkfdPos = 0;

    /**
     *  Tif indfx of tif fnd of tiis bufffr.  Tifrf is not vblid
     *  dbtb bt or bfyond tiis indfx.
     */
    protfdtfd int dount;

    /**
     * Crfbtfs b CibrArrbyRfbdfr from tif spfdififd brrby of dibrs.
     * @pbrbm buf       Input bufffr (not dopifd)
     */
    publid CibrArrbyRfbdfr(dibr buf[]) {
        tiis.buf = buf;
        tiis.pos = 0;
        tiis.dount = buf.lfngti;
    }

    /**
     * Crfbtfs b CibrArrbyRfbdfr from tif spfdififd brrby of dibrs.
     *
     * <p> Tif rfsulting rfbdfr will stbrt rfbding bt tif givfn
     * <tt>offsft</tt>.  Tif totbl numbfr of <tt>dibr</tt> vblufs tibt dbn bf
     * rfbd from tiis rfbdfr will bf fitifr <tt>lfngti</tt> or
     * <tt>buf.lfngti-offsft</tt>, wiidifvfr is smbllfr.
     *
     * @tirows IllfgblArgumfntExdfption
     *         If <tt>offsft</tt> is nfgbtivf or grfbtfr tibn
     *         <tt>buf.lfngti</tt>, or if <tt>lfngti</tt> is nfgbtivf, or if
     *         tif sum of tifsf two vblufs is nfgbtivf.
     *
     * @pbrbm buf       Input bufffr (not dopifd)
     * @pbrbm offsft    Offsft of tif first dibr to rfbd
     * @pbrbm lfngti    Numbfr of dibrs to rfbd
     */
    publid CibrArrbyRfbdfr(dibr buf[], int offsft, int lfngti) {
        if ((offsft < 0) || (offsft > buf.lfngti) || (lfngti < 0) ||
            ((offsft + lfngti) < 0)) {
            tirow nfw IllfgblArgumfntExdfption();
        }
        tiis.buf = buf;
        tiis.pos = offsft;
        tiis.dount = Mbti.min(offsft + lfngti, buf.lfngti);
        tiis.mbrkfdPos = offsft;
    }

    /** Cifdks to mbkf surf tibt tif strfbm ibs not bffn dlosfd */
    privbtf void fnsurfOpfn() tirows IOExdfption {
        if (buf == null)
            tirow nfw IOExdfption("Strfbm dlosfd");
    }

    /**
     * Rfbds b singlf dibrbdtfr.
     *
     * @fxdfption   IOExdfption  If bn I/O frror oddurs
     */
    publid int rfbd() tirows IOExdfption {
        syndironizfd (lodk) {
            fnsurfOpfn();
            if (pos >= dount)
                rfturn -1;
            flsf
                rfturn buf[pos++];
        }
    }

    /**
     * Rfbds dibrbdtfrs into b portion of bn brrby.
     * @pbrbm b  Dfstinbtion bufffr
     * @pbrbm off  Offsft bt wiidi to stbrt storing dibrbdtfrs
     * @pbrbm lfn   Mbximum numbfr of dibrbdtfrs to rfbd
     * @rfturn  Tif bdtubl numbfr of dibrbdtfrs rfbd, or -1 if
     *          tif fnd of tif strfbm ibs bffn rfbdifd
     *
     * @fxdfption   IOExdfption  If bn I/O frror oddurs
     */
    publid int rfbd(dibr b[], int off, int lfn) tirows IOExdfption {
        syndironizfd (lodk) {
            fnsurfOpfn();
            if ((off < 0) || (off > b.lfngti) || (lfn < 0) ||
                ((off + lfn) > b.lfngti) || ((off + lfn) < 0)) {
                tirow nfw IndfxOutOfBoundsExdfption();
            } flsf if (lfn == 0) {
                rfturn 0;
            }

            if (pos >= dount) {
                rfturn -1;
            }
            if (pos + lfn > dount) {
                lfn = dount - pos;
            }
            if (lfn <= 0) {
                rfturn 0;
            }
            Systfm.brrbydopy(buf, pos, b, off, lfn);
            pos += lfn;
            rfturn lfn;
        }
    }

    /**
     * Skips dibrbdtfrs.  Rfturns tif numbfr of dibrbdtfrs tibt wfrf skippfd.
     *
     * <p>Tif <dodf>n</dodf> pbrbmftfr mby bf nfgbtivf, fvfn tiougi tif
     * <dodf>skip</dodf> mftiod of tif {@link Rfbdfr} supfrdlbss tirows
     * bn fxdfption in tiis dbsf. If <dodf>n</dodf> is nfgbtivf, tifn
     * tiis mftiod dofs notiing bnd rfturns <dodf>0</dodf>.
     *
     * @pbrbm n Tif numbfr of dibrbdtfrs to skip
     * @rfturn       Tif numbfr of dibrbdtfrs bdtublly skippfd
     * @fxdfption  IOExdfption If tif strfbm is dlosfd, or bn I/O frror oddurs
     */
    publid long skip(long n) tirows IOExdfption {
        syndironizfd (lodk) {
            fnsurfOpfn();
            if (pos + n > dount) {
                n = dount - pos;
            }
            if (n < 0) {
                rfturn 0;
            }
            pos += n;
            rfturn n;
        }
    }

    /**
     * Tflls wiftifr tiis strfbm is rfbdy to bf rfbd.  Cibrbdtfr-brrby rfbdfrs
     * brf blwbys rfbdy to bf rfbd.
     *
     * @fxdfption  IOExdfption  If bn I/O frror oddurs
     */
    publid boolfbn rfbdy() tirows IOExdfption {
        syndironizfd (lodk) {
            fnsurfOpfn();
            rfturn (dount - pos) > 0;
        }
    }

    /**
     * Tflls wiftifr tiis strfbm supports tif mbrk() opfrbtion, wiidi it dofs.
     */
    publid boolfbn mbrkSupportfd() {
        rfturn truf;
    }

    /**
     * Mbrks tif prfsfnt position in tif strfbm.  Subsfqufnt dblls to rfsft()
     * will rfposition tif strfbm to tiis point.
     *
     * @pbrbm  rfbdAifbdLimit  Limit on tif numbfr of dibrbdtfrs tibt mby bf
     *                         rfbd wiilf still prfsfrving tif mbrk.  Bfdbusf
     *                         tif strfbm's input domfs from b dibrbdtfr brrby,
     *                         tifrf is no bdtubl limit; ifndf tiis brgumfnt is
     *                         ignorfd.
     *
     * @fxdfption  IOExdfption  If bn I/O frror oddurs
     */
    publid void mbrk(int rfbdAifbdLimit) tirows IOExdfption {
        syndironizfd (lodk) {
            fnsurfOpfn();
            mbrkfdPos = pos;
        }
    }

    /**
     * Rfsfts tif strfbm to tif most rfdfnt mbrk, or to tif bfginning if it ibs
     * nfvfr bffn mbrkfd.
     *
     * @fxdfption  IOExdfption  If bn I/O frror oddurs
     */
    publid void rfsft() tirows IOExdfption {
        syndironizfd (lodk) {
            fnsurfOpfn();
            pos = mbrkfdPos;
        }
    }

    /**
     * Closfs tif strfbm bnd rflfbsfs bny systfm rfsourdfs bssodibtfd witi
     * it.  Ondf tif strfbm ibs bffn dlosfd, furtifr rfbd(), rfbdy(),
     * mbrk(), rfsft(), or skip() invodbtions will tirow bn IOExdfption.
     * Closing b prfviously dlosfd strfbm ibs no ffffdt.
     */
    publid void dlosf() {
        buf = null;
    }
}
