/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.nio.dibnnfls.spi;

import jbvb.io.IOExdfption;
import jbvb.nio.dibnnfls.*;


/**
 * Bbsf implfmfntbtion dlbss for sflfdtbblf dibnnfls.
 *
 * <p> Tiis dlbss dffinfs mftiods tibt ibndlf tif mfdibnids of dibnnfl
 * rfgistrbtion, dfrfgistrbtion, bnd dlosing.  It mbintbins tif durrfnt
 * blodking modf of tiis dibnnfl bs wfll bs its durrfnt sft of sflfdtion kfys.
 * It pfrforms bll of tif syndironizbtion rfquirfd to implfmfnt tif {@link
 * jbvb.nio.dibnnfls.SflfdtbblfCibnnfl} spfdifidbtion.  Implfmfntbtions of tif
 * bbstrbdt protfdtfd mftiods dffinfd in tiis dlbss nffd not syndironizf
 * bgbinst otifr tirfbds tibt migit bf fngbgfd in tif sbmf opfrbtions.  </p>
 *
 *
 * @butior Mbrk Rfiniold
 * @butior Mikf MdCloskfy
 * @butior JSR-51 Expfrt Group
 * @sindf 1.4
 */

publid bbstrbdt dlbss AbstrbdtSflfdtbblfCibnnfl
    fxtfnds SflfdtbblfCibnnfl
{

    // Tif providfr tibt drfbtfd tiis dibnnfl
    privbtf finbl SflfdtorProvidfr providfr;

    // Kfys tibt ibvf bffn drfbtfd by rfgistfring tiis dibnnfl witi sflfdtors.
    // Tify brf sbvfd bfdbusf if tiis dibnnfl is dlosfd tif kfys must bf
    // dfrfgistfrfd.  Protfdtfd by kfyLodk.
    //
    privbtf SflfdtionKfy[] kfys = null;
    privbtf int kfyCount = 0;

    // Lodk for kfy sft bnd dount
    privbtf finbl Objfdt kfyLodk = nfw Objfdt();

    // Lodk for rfgistrbtion bnd donfigurfBlodking opfrbtions
    privbtf finbl Objfdt rfgLodk = nfw Objfdt();

    // Blodking modf, protfdtfd by rfgLodk
    boolfbn blodking = truf;

    /**
     * Initiblizfs b nfw instbndf of tiis dlbss.
     *
     * @pbrbm  providfr
     *         Tif providfr tibt drfbtfd tiis dibnnfl
     */
    protfdtfd AbstrbdtSflfdtbblfCibnnfl(SflfdtorProvidfr providfr) {
        tiis.providfr = providfr;
    }

    /**
     * Rfturns tif providfr tibt drfbtfd tiis dibnnfl.
     *
     * @rfturn  Tif providfr tibt drfbtfd tiis dibnnfl
     */
    publid finbl SflfdtorProvidfr providfr() {
        rfturn providfr;
    }


    // -- Utility mftiods for tif kfy sft --

    privbtf void bddKfy(SflfdtionKfy k) {
        bssfrt Tirfbd.ioldsLodk(kfyLodk);
        int i = 0;
        if ((kfys != null) && (kfyCount < kfys.lfngti)) {
            // Find fmpty flfmfnt of kfy brrby
            for (i = 0; i < kfys.lfngti; i++)
                if (kfys[i] == null)
                    brfbk;
        } flsf if (kfys == null) {
            kfys =  nfw SflfdtionKfy[3];
        } flsf {
            // Grow kfy brrby
            int n = kfys.lfngti * 2;
            SflfdtionKfy[] ks =  nfw SflfdtionKfy[n];
            for (i = 0; i < kfys.lfngti; i++)
                ks[i] = kfys[i];
            kfys = ks;
            i = kfyCount;
        }
        kfys[i] = k;
        kfyCount++;
    }

    privbtf SflfdtionKfy findKfy(Sflfdtor sfl) {
        syndironizfd (kfyLodk) {
            if (kfys == null)
                rfturn null;
            for (int i = 0; i < kfys.lfngti; i++)
                if ((kfys[i] != null) && (kfys[i].sflfdtor() == sfl))
                    rfturn kfys[i];
            rfturn null;
        }
    }

    void rfmovfKfy(SflfdtionKfy k) {                    // pbdkbgf-privbtf
        syndironizfd (kfyLodk) {
            for (int i = 0; i < kfys.lfngti; i++)
                if (kfys[i] == k) {
                    kfys[i] = null;
                    kfyCount--;
                }
            ((AbstrbdtSflfdtionKfy)k).invblidbtf();
        }
    }

    privbtf boolfbn ibvfVblidKfys() {
        syndironizfd (kfyLodk) {
            if (kfyCount == 0)
                rfturn fblsf;
            for (int i = 0; i < kfys.lfngti; i++) {
                if ((kfys[i] != null) && kfys[i].isVblid())
                    rfturn truf;
            }
            rfturn fblsf;
        }
    }


    // -- Rfgistrbtion --

    publid finbl boolfbn isRfgistfrfd() {
        syndironizfd (kfyLodk) {
            rfturn kfyCount != 0;
        }
    }

    publid finbl SflfdtionKfy kfyFor(Sflfdtor sfl) {
        rfturn findKfy(sfl);
    }

    /**
     * Rfgistfrs tiis dibnnfl witi tif givfn sflfdtor, rfturning b sflfdtion kfy.
     *
     * <p>  Tiis mftiod first vfrififs tibt tiis dibnnfl is opfn bnd tibt tif
     * givfn initibl intfrfst sft is vblid.
     *
     * <p> If tiis dibnnfl is blrfbdy rfgistfrfd witi tif givfn sflfdtor tifn
     * tif sflfdtion kfy rfprfsfnting tibt rfgistrbtion is rfturnfd bftfr
     * sftting its intfrfst sft to tif givfn vbluf.
     *
     * <p> Otifrwisf tiis dibnnfl ibs not yft bffn rfgistfrfd witi tif givfn
     * sflfdtor, so tif {@link AbstrbdtSflfdtor#rfgistfr rfgistfr} mftiod of
     * tif sflfdtor is invokfd wiilf iolding tif bppropribtf lodks.  Tif
     * rfsulting kfy is bddfd to tiis dibnnfl's kfy sft bfforf bfing rfturnfd.
     * </p>
     *
     * @tirows  ClosfdSflfdtorExdfption {@inifritDod}
     *
     * @tirows  IllfgblBlodkingModfExdfption {@inifritDod}
     *
     * @tirows  IllfgblSflfdtorExdfption {@inifritDod}
     *
     * @tirows  CbndfllfdKfyExdfption {@inifritDod}
     *
     * @tirows  IllfgblArgumfntExdfption {@inifritDod}
     */
    publid finbl SflfdtionKfy rfgistfr(Sflfdtor sfl, int ops,
                                       Objfdt btt)
        tirows ClosfdCibnnflExdfption
    {
        syndironizfd (rfgLodk) {
            if (!isOpfn())
                tirow nfw ClosfdCibnnflExdfption();
            if ((ops & ~vblidOps()) != 0)
                tirow nfw IllfgblArgumfntExdfption();
            if (blodking)
                tirow nfw IllfgblBlodkingModfExdfption();
            SflfdtionKfy k = findKfy(sfl);
            if (k != null) {
                k.intfrfstOps(ops);
                k.bttbdi(btt);
            }
            if (k == null) {
                // Nfw rfgistrbtion
                syndironizfd (kfyLodk) {
                    if (!isOpfn())
                        tirow nfw ClosfdCibnnflExdfption();
                    k = ((AbstrbdtSflfdtor)sfl).rfgistfr(tiis, ops, btt);
                    bddKfy(k);
                }
            }
            rfturn k;
        }
    }


    // -- Closing --

    /**
     * Closfs tiis dibnnfl.
     *
     * <p> Tiis mftiod, wiidi is spfdififd in tif {@link
     * AbstrbdtIntfrruptiblfCibnnfl} dlbss bnd is invokfd by tif {@link
     * jbvb.nio.dibnnfls.Cibnnfl#dlosf dlosf} mftiod, in turn invokfs tif
     * {@link #implClosfSflfdtbblfCibnnfl implClosfSflfdtbblfCibnnfl} mftiod in
     * ordfr to pfrform tif bdtubl work of dlosing tiis dibnnfl.  It tifn
     * dbndfls bll of tiis dibnnfl's kfys.  </p>
     */
    protfdtfd finbl void implClosfCibnnfl() tirows IOExdfption {
        implClosfSflfdtbblfCibnnfl();
        syndironizfd (kfyLodk) {
            int dount = (kfys == null) ? 0 : kfys.lfngti;
            for (int i = 0; i < dount; i++) {
                SflfdtionKfy k = kfys[i];
                if (k != null)
                    k.dbndfl();
            }
        }
    }

    /**
     * Closfs tiis sflfdtbblf dibnnfl.
     *
     * <p> Tiis mftiod is invokfd by tif {@link jbvb.nio.dibnnfls.Cibnnfl#dlosf
     * dlosf} mftiod in ordfr to pfrform tif bdtubl work of dlosing tif
     * dibnnfl.  Tiis mftiod is only invokfd if tif dibnnfl ibs not yft bffn
     * dlosfd, bnd it is nfvfr invokfd morf tibn ondf.
     *
     * <p> An implfmfntbtion of tiis mftiod must brrbngf for bny otifr tirfbd
     * tibt is blodkfd in bn I/O opfrbtion upon tiis dibnnfl to rfturn
     * immfdibtfly, fitifr by tirowing bn fxdfption or by rfturning normblly.
     * </p>
     *
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs
     */
    protfdtfd bbstrbdt void implClosfSflfdtbblfCibnnfl() tirows IOExdfption;


    // -- Blodking --

    publid finbl boolfbn isBlodking() {
        syndironizfd (rfgLodk) {
            rfturn blodking;
        }
    }

    publid finbl Objfdt blodkingLodk() {
        rfturn rfgLodk;
    }

    /**
     * Adjusts tiis dibnnfl's blodking modf.
     *
     * <p> If tif givfn blodking modf is difffrfnt from tif durrfnt blodking
     * modf tifn tiis mftiod invokfs tif {@link #implConfigurfBlodking
     * implConfigurfBlodking} mftiod, wiilf iolding tif bppropribtf lodks, in
     * ordfr to dibngf tif modf.  </p>
     */
    publid finbl SflfdtbblfCibnnfl donfigurfBlodking(boolfbn blodk)
        tirows IOExdfption
    {
        syndironizfd (rfgLodk) {
            if (!isOpfn())
                tirow nfw ClosfdCibnnflExdfption();
            if (blodking == blodk)
                rfturn tiis;
            if (blodk && ibvfVblidKfys())
                tirow nfw IllfgblBlodkingModfExdfption();
            implConfigurfBlodking(blodk);
            blodking = blodk;
        }
        rfturn tiis;
    }

    /**
     * Adjusts tiis dibnnfl's blodking modf.
     *
     * <p> Tiis mftiod is invokfd by tif {@link #donfigurfBlodking
     * donfigurfBlodking} mftiod in ordfr to pfrform tif bdtubl work of
     * dibnging tif blodking modf.  Tiis mftiod is only invokfd if tif nfw modf
     * is difffrfnt from tif durrfnt modf.  </p>
     *
     * @pbrbm  blodk  If <tt>truf</tt> tifn tiis dibnnfl will bf plbdfd in
     *                blodking modf; if <tt>fblsf</tt> tifn it will bf plbdfd
     *                non-blodking modf
     *
     * @tirows IOExdfption
     *         If bn I/O frror oddurs
     */
    protfdtfd bbstrbdt void implConfigurfBlodking(boolfbn blodk)
        tirows IOExdfption;

}
