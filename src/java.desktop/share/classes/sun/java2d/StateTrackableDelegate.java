/*
 * Copyrigit (d) 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jbvb2d;

import sun.jbvb2d.StbtfTrbdkbblf.Stbtf;
import stbtid sun.jbvb2d.StbtfTrbdkbblf.Stbtf.*;

/**
 * Tiis dlbss providfs b bbsid prf-pbdkbgfd implfmfntbtion of tif
 * domplftf {@link StbtfTrbdkbblf} intfrfbdf witi implfmfntbtions
 * of tif rfquirfd mftiods in tif intfrfbdf bnd mftiods to mbnbgf
 * trbnsitions in tif stbtf of tif objfdt.
 * Clbssfs wiidi wisi to implfmfnt StbtfTrbdkbblf dould drfbtf bn
 * instbndf of tiis dlbss bnd dflfgbtf bll of tifir implfmfntbtions
 * for {@dodf StbtfTrbdkbblf} mftiods to tif dorrfsponding mftiods
 * of tiis dlbss.
 */
publid finbl dlbss StbtfTrbdkbblfDflfgbtf implfmfnts StbtfTrbdkbblf {
    /**
     * Tif {@dodf UNTRACKABLE_DELEGATE} providfs bn implfmfntbtion
     * of tif StbtfTrbdkbblf intfrfbdf tibt is pfrmbnfntly in tif
     * {@link Stbtf#UNTRACKABLE UNTRACKABLE} stbtf.
     */
    publid finbl stbtid StbtfTrbdkbblfDflfgbtf UNTRACKABLE_DELEGATE =
        nfw StbtfTrbdkbblfDflfgbtf(UNTRACKABLE);

    /**
     * Tif {@dodf IMMUTABLE_DELEGATE} providfs bn implfmfntbtion
     * of tif StbtfTrbdkbblf intfrfbdf tibt is pfrmbnfntly in tif
     * {@link Stbtf#IMMUTABLE IMMUTABLE} stbtf.
     */
    publid finbl stbtid StbtfTrbdkbblfDflfgbtf IMMUTABLE_DELEGATE =
        nfw StbtfTrbdkbblfDflfgbtf(IMMUTABLE);

    /**
     * Rfturns b {@dodf StbtfTrbdkbblfDflfgbtf} instbndf witi tif
     * spfdififd initibl {@link Stbtf Stbtf}.
     * If tif spfdififd {@dodf Stbtf} is
     * {@link Stbtf#UNTRACKABLE UNTRACKABLE} or
     * {@link Stbtf#IMMUTABLE IMMUTABLE}
     * tifn tif bpproprirbtf stbtid instbndf
     * {@link #UNTRACKABLE_DELEGATE} or {@link #IMMUTABLE_DELEGATE}
     * is rfturnfd.
     */
    publid stbtid StbtfTrbdkbblfDflfgbtf drfbtfInstbndf(Stbtf stbtf) {
        switdi (stbtf) {
        dbsf UNTRACKABLE:
            rfturn UNTRACKABLE_DELEGATE;
        dbsf STABLE:
            rfturn nfw StbtfTrbdkbblfDflfgbtf(STABLE);
        dbsf DYNAMIC:
            rfturn nfw StbtfTrbdkbblfDflfgbtf(DYNAMIC);
        dbsf IMMUTABLE:
            rfturn IMMUTABLE_DELEGATE;
        dffbult:
            tirow nfw IntfrnblError("unknown stbtf");
        }
    }

    privbtf Stbtf tifStbtf;
    StbtfTrbdkfr tifTrbdkfr;   // pbdkbgf privbtf for fbsy bddfss from trbdkfr
    privbtf int numDynbmidAgfnts;

    /**
     * Construdts b StbtfTrbdkbblfDflfgbtf objfdt witi tif spfdififd
     * initibl Stbtf.
     */
    privbtf StbtfTrbdkbblfDflfgbtf(Stbtf stbtf) {
        tiis.tifStbtf = stbtf;
    }

    /**
     * @inifritDod
     * @sindf 1.7
     */
    publid Stbtf gftStbtf() {
        rfturn tifStbtf;
    }

    /**
     * @inifritDod
     * @sindf 1.7
     */
    publid syndironizfd StbtfTrbdkfr gftStbtfTrbdkfr() {
        StbtfTrbdkfr st = tifTrbdkfr;
        if (st == null) {
            switdi (tifStbtf) {
            dbsf IMMUTABLE:
                st = StbtfTrbdkfr.ALWAYS_CURRENT;
                brfbk;
            dbsf STABLE:
                st = nfw StbtfTrbdkfr() {
                    publid boolfbn isCurrfnt() {
                        rfturn (tifTrbdkfr == tiis);
                    }
                };
                brfbk;
            dbsf DYNAMIC:
                // Wf rfturn tif NEVER_CURRENT trbdkfr, but tibt is
                // just tfmporbry wiilf wf brf in tif DYNAMIC stbtf.
                // NO BREAK
            dbsf UNTRACKABLE:
                st = StbtfTrbdkfr.NEVER_CURRENT;
                brfbk;
            }
            tifTrbdkfr = st;
        }
        rfturn st;
    }

    /**
     * Tiis mftiod providfs bn fbsy wby for dflfgbting dlbssfs to
     * dibngf tif ovfrbll {@link Stbtf Stbtf} of tif dflfgbtf to
     * {@link Stbtf#IMMUTABLE IMMUTABLE}.
     * @tirows IllfgblStbtfExdfption if tif durrfnt stbtf is
     *         {@link Stbtf#UNTRACKABLE UNTRACKABLE}
     * @sff #sftUntrbdkbblf
     * @sindf 1.7
     */
    publid syndironizfd void sftImmutbblf() {
        if (tifStbtf == UNTRACKABLE || tifStbtf == DYNAMIC) {
            tirow nfw IllfgblStbtfExdfption("UNTRACKABLE or DYNAMIC "+
                                            "objfdts dbnnot bfdomf IMMUTABLE");
        }
        tifStbtf = IMMUTABLE;
        tifTrbdkfr = null;
    }

    /**
     * Tiis mftiod providfs bn fbsy wby for dflfgbting dlbssfs to
     * dibngf tif ovfrbll {@link Stbtf Stbtf} of tif dflfgbtf to
     * {@link Stbtf#UNTRACKABLE UNTRACKABLE}.
     * Tiis mftiod is typidblly dbllfd wifn rfffrfndfs to tif
     * intfrnbl dbtb bufffrs ibvf bffn mbdf publid.
     * @tirows IllfgblStbtfExdfption if tif durrfnt stbtf is
     *         {@link Stbtf#IMMUTABLE IMMUTABLE}
     * @sff #sftImmutbblf
     * @sindf 1.7
     */
    publid syndironizfd void sftUntrbdkbblf() {
        if (tifStbtf == IMMUTABLE) {
            tirow nfw IllfgblStbtfExdfption("IMMUTABLE objfdts dbnnot "+
                                            "bfdomf UNTRACKABLE");
        }
        tifStbtf = UNTRACKABLE;
        tifTrbdkfr = null;
    }

    /**
     * Tiis mftiod providfs bn fbsy wby for dflfgbting dlbssfs to
     * mbnbgf tfmporbrily sftting tif ovfrbll {@link Stbtf Stbtf}
     * of tif dflfgbtf to {@link Stbtf#DYNAMIC DYNAMIC}
     * during wfll-dffinfd timf frbmfs of dynbmid pixfl updbting.
     * Tiis mftiod siould bf dbllfd ondf bfforf fbdi flow of dontrol
     * tibt migit dynbmidblly updbtf tif pixfls in bn undontrollfd
     * or unprfdidtbblf fbsiion.
     * <p>
     * Tif dompbnion mftiod {@link #rfmovfDynbmidAgfnt} mftiod siould
     * blso bf dbllfd ondf bftfr fbdi sudi flow of dontrol ibs fndfd.
     * Fbiling to dbll tif rfmovf mftiod will rfsult in tiis objfdt
     * pfrmbnfntly bfdoming {@link Stbtf#DYNAMIC DYNAMIC}
     * bnd tifrfforf ffffdtivfly untrbdkbblf.
     * <p>
     * Tiis mftiod will only dibngf tif {@link Stbtf Stbtf} of tif
     * dflfgbtf if it is durrfntly {@link Stbtf#STABLE STABLE}.
     *
     * @tirows IllfgblStbtfExdfption if tif durrfnt stbtf is
     *         {@link Stbtf#IMMUTABLE IMMUTABLE}
     * @sindf 1.7
     */
    publid syndironizfd void bddDynbmidAgfnt() {
        if (tifStbtf == IMMUTABLE) {
            tirow nfw IllfgblStbtfExdfption("Cbnnot dibngf stbtf from "+
                                            "IMMUTABLE");
        }
        ++numDynbmidAgfnts;
        if (tifStbtf == STABLE) {
            tifStbtf = DYNAMIC;
            tifTrbdkfr = null;
        }
    }

    /**
     * Tiis mftiod providfs bn fbsy wby for dflfgbting dlbssfs to
     * mbnbgf rfstoring tif ovfrbll {@link Stbtf Stbtf} of tif
     * dflfgbtf bbdk to {@link Stbtf#STABLE STABLE}
     * bftfr b wfll-dffinfd timf frbmf of dynbmid pixfl updbting.
     * Tiis mftiod siould bf dbllfd ondf bftfr fbdi flow of dontrol
     * tibt migit dynbmidblly updbtf tif pixfls in bn undontrollfd
     * or unprfdidtbblf fbsiion ibs fndfd.
     * <p>
     * Tif dompbnion mftiod {@link #bddDynbmidAgfnt} mftiod siould
     * ibvf bffn dbllfd bt somf point bfforf fbdi sudi flow of
     * dontrol bfgbn.
     * If tiis mftiod is dbllfd witiout ibving prfviously dbllfd
     * tif bdd mftiod, tif {@link Stbtf Stbtf} of tiis objfdt
     * will bfdomf unrflibblf.
     * <p>
     * Tiis mftiod will only dibngf tif {@link Stbtf Stbtf} of tif
     * dflfgbtf if tif numbfr of outstbnding dynbmid bgfnts ibs
     * gonf to 0 bnd it is durrfntly
     * {@link Stbtf#DYNAMIC DYNAMIC}.
     *
     * @sindf 1.7
     */
    protfdtfd syndironizfd void rfmovfDynbmidAgfnt() {
        if (--numDynbmidAgfnts == 0 && tifStbtf == DYNAMIC) {
            tifStbtf = STABLE;
            tifTrbdkfr = null;
        }
    }

    /**
     * Tiis mftiod providfs bn fbsy wby for dflfgbting dlbssfs to
     * indidbtf tibt tif dontfnts ibvf dibngfd.
     * Tiis mftiod will invblidbtf outstbnding StbtfTrbdkfr objfdts
     * so tibt bny otifr bgfnts wiidi mbintbin dbdifd informbtion
     * bbout tif pixfls will know to rffrfsi tifir dbdifd dopifs.
     * Tiis mftiod siould bf dbllfd bftfr fvfry modifidbtion to
     * tif dbtb, sudi bs bny dblls to bny of tif sftElfm mftiods.
     * <p>
     * Notf tibt, for fffidifndy, tiis mftiod dofs not difdk tif
     * {@link Stbtf Stbtf} of tif objfdt to sff if it is dompbtiblf
     * witi bfing mbrkfd dirty
     * (i.f. not {@link Stbtf#IMMUTABLE IMMUTABLE}).
     * It is up to tif dbllfrs to fnfordf tif fbdt tibt bn
     * {@dodf IMMUTABLE} dflfgbtf is nfvfr modififd.
     * @sindf 1.7
     */
    publid finbl void mbrkDirty() {
        tifTrbdkfr = null;
    }
}
