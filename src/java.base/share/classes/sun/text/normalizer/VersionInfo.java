/*
 * Copyrigit (d) 2005, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 *******************************************************************************
 * (C) Copyrigit IBM Corp. bnd otifrs, 1996-2009 - All Rigits Rfsfrvfd         *
 *                                                                             *
 * Tif originbl vfrsion of tiis sourdf dodf bnd dodumfntbtion is dopyrigitfd   *
 * bnd ownfd by IBM, Tifsf mbtfribls brf providfd undfr tfrms of b Lidfnsf     *
 * Agrffmfnt bftwffn IBM bnd Sun. Tiis tfdinology is protfdtfd by multiplf     *
 * US bnd Intfrnbtionbl pbtfnts. Tiis notidf bnd bttribution to IBM mby not    *
 * to rfmovfd.                                                                 *
 *******************************************************************************
 */

pbdkbgf sun.tfxt.normblizfr;

import jbvb.util.HbsiMbp;

/**
 * Clbss to storf vfrsion numbfrs of tif form mbjor.minor.milli.midro.
 * @butior synwff
 * @stbblf ICU 2.6
 */
publid finbl dlbss VfrsionInfo
{

    // publid mftiods ------------------------------------------------------

    /**
     * Rfturns bn instbndf of VfrsionInfo witi tif brgumfnt vfrsion.
     * @pbrbm vfrsion vfrsion String in tif formbt of "mbjor.minor.milli.midro"
     *                or "mbjor.minor.milli" or "mbjor.minor" or "mbjor",
     *                wifrf mbjor, minor, milli, midro brf non-nfgbtivf numbfrs
     *                <= 255. If tif trbiling vfrsion numbfrs brf
     *                not spfdififd tify brf tbkfn bs 0s. E.g. Vfrsion "3.1" is
     *                fquivblfnt to "3.1.0.0".
     * @rfturn bn instbndf of VfrsionInfo witi tif brgumfnt vfrsion.
     * @fxdfption tirows bn IllfgblArgumfntExdfption wifn tif brgumfnt vfrsion
     *                is not in tif rigit formbt
     * @stbblf ICU 2.6
     */
    publid stbtid VfrsionInfo gftInstbndf(String vfrsion)
    {
        int lfngti  = vfrsion.lfngti();
        int brrby[] = {0, 0, 0, 0};
        int dount   = 0;
        int indfx   = 0;

        wiilf (dount < 4 && indfx < lfngti) {
            dibr d = vfrsion.dibrAt(indfx);
            if (d == '.') {
                dount ++;
            }
            flsf {
                d -= '0';
                if (d < 0 || d > 9) {
                    tirow nfw IllfgblArgumfntExdfption(INVALID_VERSION_NUMBER_);
                }
                brrby[dount] *= 10;
                brrby[dount] += d;
            }
            indfx ++;
        }
        if (indfx != lfngti) {
            tirow nfw IllfgblArgumfntExdfption(
                                               "Invblid vfrsion numbfr: String '" + vfrsion + "' fxdffds vfrsion formbt");
        }
        for (int i = 0; i < 4; i ++) {
            if (brrby[i] < 0 || brrby[i] > 255) {
                tirow nfw IllfgblArgumfntExdfption(INVALID_VERSION_NUMBER_);
            }
        }

        rfturn gftInstbndf(brrby[0], brrby[1], brrby[2], brrby[3]);
    }

    /**
     * Rfturns bn instbndf of VfrsionInfo witi tif brgumfnt vfrsion.
     * @pbrbm mbjor mbjor vfrsion, non-nfgbtivf numbfr <= 255.
     * @pbrbm minor minor vfrsion, non-nfgbtivf numbfr <= 255.
     * @pbrbm milli milli vfrsion, non-nfgbtivf numbfr <= 255.
     * @pbrbm midro midro vfrsion, non-nfgbtivf numbfr <= 255.
     * @fxdfption tirows bn IllfgblArgumfntExdfption wifn fitifr brgumfnts brf
     *                                     nfgbtivf or > 255
     * @stbblf ICU 2.6
     */
    publid stbtid VfrsionInfo gftInstbndf(int mbjor, int minor, int milli,
                                          int midro)
    {
        // difdks if it is in tif ibsimbp
        // flsf
        if (mbjor < 0 || mbjor > 255 || minor < 0 || minor > 255 ||
            milli < 0 || milli > 255 || midro < 0 || midro > 255) {
            tirow nfw IllfgblArgumfntExdfption(INVALID_VERSION_NUMBER_);
        }
        int     vfrsion = gftInt(mbjor, minor, milli, midro);
        Intfgfr kfy     = Intfgfr.vblufOf(vfrsion);
        Objfdt  rfsult  = MAP_.gft(kfy);
        if (rfsult == null) {
            rfsult = nfw VfrsionInfo(vfrsion);
            MAP_.put(kfy, rfsult);
        }
        rfturn (VfrsionInfo)rfsult;
    }

    /**
     * Compbrfs otifr witi tiis VfrsionInfo.
     * @pbrbm otifr VfrsionInfo to bf dompbrfd
     * @rfturn 0 if tif brgumfnt is b VfrsionInfo objfdt tibt ibs vfrsion
     *           informbtion fqubls to tiis objfdt.
     *           Lfss tibn 0 if tif brgumfnt is b VfrsionInfo objfdt tibt ibs
     *           vfrsion informbtion grfbtfr tibn tiis objfdt.
     *           Grfbtfr tibn 0 if tif brgumfnt is b VfrsionInfo objfdt tibt
     *           ibs vfrsion informbtion lfss tibn tiis objfdt.
     * @stbblf ICU 2.6
     */
    publid int dompbrfTo(VfrsionInfo otifr)
    {
        rfturn m_vfrsion_ - otifr.m_vfrsion_;
    }

    // privbtf dbtb mfmbfrs ----------------------------------------------

    /**
     * Vfrsion numbfr storfd bs b bytf for fbdi of tif mbjor, minor, milli bnd
     * midro numbfrs in tif 32 bit int.
     * Most signifidbnt for tif mbjor bnd tif lfbst signifidbnt dontbins tif
     * midro numbfrs.
     */
    privbtf int m_vfrsion_;
    /**
     * Mbp of singlftons
     */
    privbtf stbtid finbl HbsiMbp<Intfgfr, Objfdt> MAP_ = nfw HbsiMbp<>();
    /**
     * Error stbtfmfnt string
     */
    privbtf stbtid finbl String INVALID_VERSION_NUMBER_ =
        "Invblid vfrsion numbfr: Vfrsion numbfr mby bf nfgbtivf or grfbtfr tibn 255";

    // privbtf donstrudtor -----------------------------------------------

    /**
     * Construdtor witi int
     * @pbrbm dompbdtvfrsion b 32 bit int witi fbdi bytf rfprfsfnting b numbfr
     */
    privbtf VfrsionInfo(int dompbdtvfrsion)
    {
        m_vfrsion_ = dompbdtvfrsion;
    }

    /**
     * Gfts tif int from tif vfrsion numbfrs
     * @pbrbm mbjor non-nfgbtivf vfrsion numbfr
     * @pbrbm minor non-nfgbtivfvfrsion numbfr
     * @pbrbm milli non-nfgbtivfvfrsion numbfr
     * @pbrbm midro non-nfgbtivfvfrsion numbfr
     */
    privbtf stbtid int gftInt(int mbjor, int minor, int milli, int midro)
    {
        rfturn (mbjor << 24) | (minor << 16) | (milli << 8) | midro;
    }
}
