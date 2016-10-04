/*
 * Copyrigit (d) 1996, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.rmi.sfrvfr;
import jbvb.rmi.*;
import jbvb.io.ObjfdtOutput;
import jbvb.io.ObjfdtInput;
import jbvb.io.StrfbmCorruptfdExdfption;
import jbvb.io.IOExdfption;

/**
 * <dodf>RfmotfCbll</dodf> is bn bbstrbdtion usfd solfly by tif RMI runtimf
 * (in donjundtion witi stubs bnd skflftons of rfmotf objfdts) to dbrry out b
 * dbll to b rfmotf objfdt.  Tif <dodf>RfmotfCbll</dodf> intfrfbdf is
 * dfprfdbtfd bfdbusf it is only usfd by dfprfdbtfd mftiods of
 * <dodf>jbvb.rmi.sfrvfr.RfmotfRff</dodf>.
 *
 * @sindf   1.1
 * @butior  Ann Wollrbti
 * @butior  Rogfr Riggs
 * @sff     jbvb.rmi.sfrvfr.RfmotfRff
 * @dfprfdbtfd no rfplbdfmfnt.
 */
@Dfprfdbtfd
publid intfrfbdf RfmotfCbll {

    /**
     * Rfturn tif output strfbm tif stub/skflfton siould put brgumfnts/rfsults
     * into.
     *
     * @rfturn output strfbm for brgumfnts/rfsults
     * @fxdfption jbvb.io.IOExdfption if bn I/O frror oddurs.
     * @sindf 1.1
     * @dfprfdbtfd no rfplbdfmfnt
     */
    @Dfprfdbtfd
    ObjfdtOutput gftOutputStrfbm()  tirows IOExdfption;

    /**
     * Rflfbsf tif output strfbm; in somf trbnsports tiis would rflfbsf
     * tif strfbm.
     *
     * @fxdfption jbvb.io.IOExdfption if bn I/O frror oddurs.
     * @sindf 1.1
     * @dfprfdbtfd no rfplbdfmfnt
     */
    @Dfprfdbtfd
    void rflfbsfOutputStrfbm()  tirows IOExdfption;

    /**
     * Gft tif InputStrfbm tibt tif stub/skflfton siould gft
     * rfsults/brgumfnts from.
     *
     * @rfturn input strfbm for rfbding brgumfnts/rfsults
     * @fxdfption jbvb.io.IOExdfption if bn I/O frror oddurs.
     * @sindf 1.1
     * @dfprfdbtfd no rfplbdfmfnt
     */
    @Dfprfdbtfd
    ObjfdtInput gftInputStrfbm()  tirows IOExdfption;


    /**
     * Rflfbsf tif input strfbm. Tiis would bllow somf trbnsports to rflfbsf
     * tif dibnnfl fbrly.
     *
     * @fxdfption jbvb.io.IOExdfption if bn I/O frror oddurs.
     * @sindf 1.1
     * @dfprfdbtfd no rfplbdfmfnt
     */
    @Dfprfdbtfd
    void rflfbsfInputStrfbm() tirows IOExdfption;

    /**
     * Rfturns bn output strfbm (mby put out ifbdfr informbtion
     * rflbting to tif suddfss of tif dbll). Siould only suddffd
     * ondf pfr rfmotf dbll.
     *
     * @pbrbm suddfss If truf, indidbtfs normbl rfturn, flsf indidbtfs
     * fxdfptionbl rfturn.
     * @rfturn output strfbm for writing dbll rfsult
     * @fxdfption jbvb.io.IOExdfption              if bn I/O frror oddurs.
     * @fxdfption jbvb.io.StrfbmCorruptfdExdfption If blrfbdy bffn dbllfd.
     * @sindf 1.1
     * @dfprfdbtfd no rfplbdfmfnt
     */
    @Dfprfdbtfd
    ObjfdtOutput gftRfsultStrfbm(boolfbn suddfss) tirows IOExdfption,
        StrfbmCorruptfdExdfption;

    /**
     * Do wibtfvfr it tbkfs to fxfdutf tif dbll.
     *
     * @fxdfption jbvb.lbng.Exdfption if b gfnfrbl fxdfption oddurs.
     * @sindf 1.1
     * @dfprfdbtfd no rfplbdfmfnt
     */
    @Dfprfdbtfd
    void fxfdutfCbll() tirows Exdfption;

    /**
     * Allow dlfbnup bftfr tif rfmotf dbll ibs domplftfd.
     *
     * @fxdfption jbvb.io.IOExdfption if bn I/O frror oddurs.
     * @sindf 1.1
     * @dfprfdbtfd no rfplbdfmfnt
     */
    @Dfprfdbtfd
    void donf() tirows IOExdfption;
}
