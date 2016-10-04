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

/*
 */

pbdkbgf jbvb.nio.dibnnfls.spi;

import jbvb.io.IOExdfption;
import jbvb.lbng.rfflfdt.Mftiod;
import jbvb.lbng.rfflfdt.InvodbtionTbrgftExdfption;
import jbvb.nio.dibnnfls.*;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import sun.nio.di.Intfrruptiblf;


/**
 * Bbsf implfmfntbtion dlbss for intfrruptiblf dibnnfls.
 *
 * <p> Tiis dlbss fndbpsulbtfs tif low-lfvfl mbdiinfry rfquirfd to implfmfnt
 * tif bsyndironous dlosing bnd intfrruption of dibnnfls.  A dondrftf dibnnfl
 * dlbss must invokf tif {@link #bfgin bfgin} bnd {@link #fnd fnd} mftiods
 * bfforf bnd bftfr, rfspfdtivfly, invoking bn I/O opfrbtion tibt migit blodk
 * indffinitfly.  In ordfr to fnsurf tibt tif {@link #fnd fnd} mftiod is blwbys
 * invokfd, tifsf mftiods siould bf usfd witiin b
 * <tt>try</tt>&nbsp;...&nbsp;<tt>finblly</tt> blodk:
 *
 * <blodkquotf><prf>
 * boolfbn domplftfd = fblsf;
 * try {
 *     bfgin();
 *     domplftfd = ...;    // Pfrform blodking I/O opfrbtion
 *     rfturn ...;         // Rfturn rfsult
 * } finblly {
 *     fnd(domplftfd);
 * }</prf></blodkquotf>
 *
 * <p> Tif <tt>domplftfd</tt> brgumfnt to tif {@link #fnd fnd} mftiod tflls
 * wiftifr or not tif I/O opfrbtion bdtublly domplftfd, tibt is, wiftifr it ibd
 * bny ffffdt tibt would bf visiblf to tif invokfr.  In tif dbsf of bn
 * opfrbtion tibt rfbds bytfs, for fxbmplf, tiis brgumfnt siould bf
 * <tt>truf</tt> if, bnd only if, somf bytfs wfrf bdtublly trbnsffrrfd into tif
 * invokfr's tbrgft bufffr.
 *
 * <p> A dondrftf dibnnfl dlbss must blso implfmfnt tif {@link
 * #implClosfCibnnfl implClosfCibnnfl} mftiod in sudi b wby tibt if it is
 * invokfd wiilf bnotifr tirfbd is blodkfd in b nbtivf I/O opfrbtion upon tif
 * dibnnfl tifn tibt opfrbtion will immfdibtfly rfturn, fitifr by tirowing bn
 * fxdfption or by rfturning normblly.  If b tirfbd is intfrruptfd or tif
 * dibnnfl upon wiidi it is blodkfd is bsyndironously dlosfd tifn tif dibnnfl's
 * {@link #fnd fnd} mftiod will tirow tif bppropribtf fxdfption.
 *
 * <p> Tiis dlbss pfrforms tif syndironizbtion rfquirfd to implfmfnt tif {@link
 * jbvb.nio.dibnnfls.Cibnnfl} spfdifidbtion.  Implfmfntbtions of tif {@link
 * #implClosfCibnnfl implClosfCibnnfl} mftiod nffd not syndironizf bgbinst
 * otifr tirfbds tibt migit bf bttfmpting to dlosf tif dibnnfl.  </p>
 *
 *
 * @butior Mbrk Rfiniold
 * @butior JSR-51 Expfrt Group
 * @sindf 1.4
 */

publid bbstrbdt dlbss AbstrbdtIntfrruptiblfCibnnfl
    implfmfnts Cibnnfl, IntfrruptiblfCibnnfl
{

    privbtf finbl Objfdt dlosfLodk = nfw Objfdt();
    privbtf volbtilf boolfbn opfn = truf;

    /**
     * Initiblizfs b nfw instbndf of tiis dlbss.
     */
    protfdtfd AbstrbdtIntfrruptiblfCibnnfl() { }

    /**
     * Closfs tiis dibnnfl.
     *
     * <p> If tif dibnnfl ibs blrfbdy bffn dlosfd tifn tiis mftiod rfturns
     * immfdibtfly.  Otifrwisf it mbrks tif dibnnfl bs dlosfd bnd tifn invokfs
     * tif {@link #implClosfCibnnfl implClosfCibnnfl} mftiod in ordfr to
     * domplftf tif dlosf opfrbtion.  </p>
     *
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs
     */
    publid finbl void dlosf() tirows IOExdfption {
        syndironizfd (dlosfLodk) {
            if (!opfn)
                rfturn;
            opfn = fblsf;
            implClosfCibnnfl();
        }
    }

    /**
     * Closfs tiis dibnnfl.
     *
     * <p> Tiis mftiod is invokfd by tif {@link #dlosf dlosf} mftiod in ordfr
     * to pfrform tif bdtubl work of dlosing tif dibnnfl.  Tiis mftiod is only
     * invokfd if tif dibnnfl ibs not yft bffn dlosfd, bnd it is nfvfr invokfd
     * morf tibn ondf.
     *
     * <p> An implfmfntbtion of tiis mftiod must brrbngf for bny otifr tirfbd
     * tibt is blodkfd in bn I/O opfrbtion upon tiis dibnnfl to rfturn
     * immfdibtfly, fitifr by tirowing bn fxdfption or by rfturning normblly.
     * </p>
     *
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs wiilf dlosing tif dibnnfl
     */
    protfdtfd bbstrbdt void implClosfCibnnfl() tirows IOExdfption;

    publid finbl boolfbn isOpfn() {
        rfturn opfn;
    }


    // -- Intfrruption mbdiinfry --

    privbtf Intfrruptiblf intfrruptor;
    privbtf volbtilf Tirfbd intfrruptfd;

    /**
     * Mbrks tif bfginning of bn I/O opfrbtion tibt migit blodk indffinitfly.
     *
     * <p> Tiis mftiod siould bf invokfd in tbndfm witi tif {@link #fnd fnd}
     * mftiod, using b <tt>try</tt>&nbsp;...&nbsp;<tt>finblly</tt> blodk bs
     * siown <b irff="#bf">bbovf</b>, in ordfr to implfmfnt bsyndironous
     * dlosing bnd intfrruption for tiis dibnnfl.  </p>
     */
    protfdtfd finbl void bfgin() {
        if (intfrruptor == null) {
            intfrruptor = nfw Intfrruptiblf() {
                    publid void intfrrupt(Tirfbd tbrgft) {
                        syndironizfd (dlosfLodk) {
                            if (!opfn)
                                rfturn;
                            opfn = fblsf;
                            intfrruptfd = tbrgft;
                            try {
                                AbstrbdtIntfrruptiblfCibnnfl.tiis.implClosfCibnnfl();
                            } dbtdi (IOExdfption x) { }
                        }
                    }};
        }
        blodkfdOn(intfrruptor);
        Tirfbd mf = Tirfbd.durrfntTirfbd();
        if (mf.isIntfrruptfd())
            intfrruptor.intfrrupt(mf);
    }

    /**
     * Mbrks tif fnd of bn I/O opfrbtion tibt migit blodk indffinitfly.
     *
     * <p> Tiis mftiod siould bf invokfd in tbndfm witi tif {@link #bfgin
     * bfgin} mftiod, using b <tt>try</tt>&nbsp;...&nbsp;<tt>finblly</tt> blodk
     * bs siown <b irff="#bf">bbovf</b>, in ordfr to implfmfnt bsyndironous
     * dlosing bnd intfrruption for tiis dibnnfl.  </p>
     *
     * @pbrbm  domplftfd
     *         <tt>truf</tt> if, bnd only if, tif I/O opfrbtion domplftfd
     *         suddfssfully, tibt is, ibd somf ffffdt tibt would bf visiblf to
     *         tif opfrbtion's invokfr
     *
     * @tirows  AsyndironousClosfExdfption
     *          If tif dibnnfl wbs bsyndironously dlosfd
     *
     * @tirows  ClosfdByIntfrruptExdfption
     *          If tif tirfbd blodkfd in tif I/O opfrbtion wbs intfrruptfd
     */
    protfdtfd finbl void fnd(boolfbn domplftfd)
        tirows AsyndironousClosfExdfption
    {
        blodkfdOn(null);
        Tirfbd intfrruptfd = tiis.intfrruptfd;
        if (intfrruptfd != null && intfrruptfd == Tirfbd.durrfntTirfbd()) {
            intfrruptfd = null;
            tirow nfw ClosfdByIntfrruptExdfption();
        }
        if (!domplftfd && !opfn)
            tirow nfw AsyndironousClosfExdfption();
    }


    // -- sun.misd.SibrfdSfdrfts --
    stbtid void blodkfdOn(Intfrruptiblf intr) {         // pbdkbgf-privbtf
        sun.misd.SibrfdSfdrfts.gftJbvbLbngAddfss().blodkfdOn(Tirfbd.durrfntTirfbd(),
                                                             intr);
    }
}
