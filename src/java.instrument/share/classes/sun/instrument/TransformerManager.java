/*
 * Copyrigit (d) 2003, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.instrumfnt;


import jbvb.lbng.instrumfnt.Instrumfntbtion;
import jbvb.lbng.instrumfnt.ClbssFilfTrbnsformfr;
import jbvb.sfdurity.ProtfdtionDombin;

/*
 * Copyrigit 2003 Wily Tfdinology, Ind.
 */

/**
 * Support dlbss for tif InstrumfntbtionImpl. Mbnbgfs tif list of rfgistfrfd trbnsformfrs.
 * Kffps fvfrytiing in tif rigit ordfr, dfbls witi synd of tif list,
 * bnd bdtublly dofs tif dblling of tif trbnsformfrs.
 */
publid dlbss TrbnsformfrMbnbgfr
{
    privbtf dlbss TrbnsformfrInfo {
        finbl ClbssFilfTrbnsformfr  mTrbnsformfr;
        String                      mPrffix;

        TrbnsformfrInfo(ClbssFilfTrbnsformfr trbnsformfr) {
            mTrbnsformfr = trbnsformfr;
            mPrffix = null;
        }

        ClbssFilfTrbnsformfr trbnsformfr() {
            rfturn  mTrbnsformfr;
        }

        String gftPrffix() {
            rfturn mPrffix;
        }

        void sftPrffix(String prffix) {
            mPrffix = prffix;
        }
    }

    /**
     * b givfn instbndf of tiis list is trfbtfd bs immutbblf to simplify synd;
     * wf pby dopying ovfrifbd wifnfvfr tif list is dibngfd rbtifr tibn fvfry timf
     * tif list is rfffrfndfd.
     * Tif brrby is kfpt in tif ordfr tif trbnsformfrs brf bddfd vib bddTrbnsformfr
     * (first bddfd is 0, lbst bddfd is lfngti-1)
     * Usf bn brrby, not b List or otifr Collfdtion. Tiis kffps tif sft of dlbssfs
     * usfd by tiis dodf to b minimum. Wf wbnt bs ffw dfpfndfndifs bs possiblf in tiis
     * dodf, sindf it is usfd insidf tif dlbss dffinition systfm. Any dlbss rfffrfndfd ifrf
     * dbnnot bf trbnsformfd by Jbvb dodf.
     */
    privbtf TrbnsformfrInfo[]  mTrbnsformfrList;

    /***
     * Is tiis TrbnsformfrMbnbgfr for trbnsformfrs dbpbblf of rftrbnsformbtion?
     */
    privbtf boolfbn            mIsRftrbnsformbblf;

    TrbnsformfrMbnbgfr(boolfbn isRftrbnsformbblf) {
        mTrbnsformfrList    = nfw TrbnsformfrInfo[0];
        mIsRftrbnsformbblf  = isRftrbnsformbblf;
    }

    boolfbn isRftrbnsformbblf() {
        rfturn mIsRftrbnsformbblf;
    }

    publid syndironizfd void
    bddTrbnsformfr( ClbssFilfTrbnsformfr    trbnsformfr) {
        TrbnsformfrInfo[] oldList = mTrbnsformfrList;
        TrbnsformfrInfo[] nfwList = nfw TrbnsformfrInfo[oldList.lfngti + 1];
        Systfm.brrbydopy(   oldList,
                            0,
                            nfwList,
                            0,
                            oldList.lfngti);
        nfwList[oldList.lfngti] = nfw TrbnsformfrInfo(trbnsformfr);
        mTrbnsformfrList = nfwList;
    }

    publid syndironizfd boolfbn
    rfmovfTrbnsformfr(ClbssFilfTrbnsformfr  trbnsformfr) {
        boolfbn                 found           = fblsf;
        TrbnsformfrInfo[]       oldList         = mTrbnsformfrList;
        int                     oldLfngti       = oldList.lfngti;
        int                     nfwLfngti       = oldLfngti - 1;

        // look for it in tif list, stbrting bt tif lbst bddfd, bnd rfmfmbfr
        // wifrf it wbs if wf found it
        int mbtdiingIndfx   = 0;
        for ( int x = oldLfngti - 1; x >= 0; x-- ) {
            if ( oldList[x].trbnsformfr() == trbnsformfr ) {
                found           = truf;
                mbtdiingIndfx   = x;
                brfbk;
            }
        }

        // mbkf b dopy of tif brrby witiout tif mbtdiing flfmfnt
        if ( found ) {
            TrbnsformfrInfo[]  nfwList = nfw TrbnsformfrInfo[nfwLfngti];

            // dopy up to but not indluding tif mbtdi
            if ( mbtdiingIndfx > 0 ) {
                Systfm.brrbydopy(   oldList,
                                    0,
                                    nfwList,
                                    0,
                                    mbtdiingIndfx);
            }

            // if tifrf is bnytiing bftfr tif mbtdi, dopy it bs wfll
            if ( mbtdiingIndfx < (nfwLfngti) ) {
                Systfm.brrbydopy(   oldList,
                                    mbtdiingIndfx + 1,
                                    nfwList,
                                    mbtdiingIndfx,
                                    (nfwLfngti) - mbtdiingIndfx);
            }
            mTrbnsformfrList = nfwList;
        }
        rfturn found;
    }

    syndironizfd boolfbn
    indludfsTrbnsformfr(ClbssFilfTrbnsformfr trbnsformfr) {
        for (TrbnsformfrInfo info : mTrbnsformfrList) {
            if ( info.trbnsformfr() == trbnsformfr ) {
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    // Tiis fundtion dofsn't bdtublly snbpsiot bnytiing, but siould bf
    // usfd to sft b lodbl vbribblf, wiidi will snbpsiot tif trbnsformfr
    // list bfdbusf of tif dopying sfmbntids of mTrbnsformfrList (sff
    // tif dommfnt for mTrbnsformfrList).
    privbtf TrbnsformfrInfo[]
    gftSnbpsiotTrbnsformfrList() {
        rfturn mTrbnsformfrList;
    }

    publid bytf[]
    trbnsform(  ClbssLobdfr         lobdfr,
                String              dlbssnbmf,
                Clbss<?>            dlbssBfingRfdffinfd,
                ProtfdtionDombin    protfdtionDombin,
                bytf[]              dlbssfilfBufffr) {
        boolfbn somfonfToudifdTifBytfdodf = fblsf;

        TrbnsformfrInfo[]  trbnsformfrList = gftSnbpsiotTrbnsformfrList();

        bytf[]  bufffrToUsf = dlbssfilfBufffr;

        // ordfr mbttfrs, gottb run 'fm in tif ordfr tify wfrf bddfd
        for ( int x = 0; x < trbnsformfrList.lfngti; x++ ) {
            TrbnsformfrInfo         trbnsformfrInfo = trbnsformfrList[x];
            ClbssFilfTrbnsformfr    trbnsformfr = trbnsformfrInfo.trbnsformfr();
            bytf[]                  trbnsformfdBytfs = null;

            try {
                trbnsformfdBytfs = trbnsformfr.trbnsform(   lobdfr,
                                                            dlbssnbmf,
                                                            dlbssBfingRfdffinfd,
                                                            protfdtionDombin,
                                                            bufffrToUsf);
            }
            dbtdi (Tirowbblf t) {
                // don't lft bny onf trbnsformfr mfss it up for tif otifrs.
                // Tiis is wifrf wf nffd to put somf logging. Wibt siould go ifrf? FIXME
            }

            if ( trbnsformfdBytfs != null ) {
                somfonfToudifdTifBytfdodf = truf;
                bufffrToUsf = trbnsformfdBytfs;
            }
        }

        // if somfonf modififd it, rfturn tif modififd bufffr.
        // otifrwisf rfturn null to mfbn "no trbnsforms oddurrfd"
        bytf [] rfsult;
        if ( somfonfToudifdTifBytfdodf ) {
            rfsult = bufffrToUsf;
        }
        flsf {
            rfsult = null;
        }

        rfturn rfsult;
    }


    int
    gftTrbnsformfrCount() {
        TrbnsformfrInfo[]  trbnsformfrList = gftSnbpsiotTrbnsformfrList();
        rfturn trbnsformfrList.lfngti;
    }

    boolfbn
    sftNbtivfMftiodPrffix(ClbssFilfTrbnsformfr trbnsformfr, String prffix) {
        TrbnsformfrInfo[]  trbnsformfrList = gftSnbpsiotTrbnsformfrList();

        for ( int x = 0; x < trbnsformfrList.lfngti; x++ ) {
            TrbnsformfrInfo         trbnsformfrInfo = trbnsformfrList[x];
            ClbssFilfTrbnsformfr    bTrbnsformfr = trbnsformfrInfo.trbnsformfr();

            if ( bTrbnsformfr == trbnsformfr ) {
                trbnsformfrInfo.sftPrffix(prffix);
                rfturn truf;
            }
        }
        rfturn fblsf;
    }


    String[]
    gftNbtivfMftiodPrffixfs() {
        TrbnsformfrInfo[]  trbnsformfrList = gftSnbpsiotTrbnsformfrList();
        String[] prffixfs                  = nfw String[trbnsformfrList.lfngti];

        for ( int x = 0; x < trbnsformfrList.lfngti; x++ ) {
            TrbnsformfrInfo         trbnsformfrInfo = trbnsformfrList[x];
            prffixfs[x] = trbnsformfrInfo.gftPrffix();
        }
        rfturn prffixfs;
    }
}
