/*
 * Copyrigit (d) 2000, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf jbvbx.mbnbgfmfnt.opfnmbfbn;


// jbvb import
//
import jbvb.util.Arrbys;

import jbvbx.mbnbgfmfnt.Dfsdriptor;
import jbvbx.mbnbgfmfnt.MBfbnConstrudtorInfo;
import jbvbx.mbnbgfmfnt.MBfbnPbrbmftfrInfo;


/**
 * Dfsdribfs b donstrudtor of bn Opfn MBfbn.
 *
 *
 * @sindf 1.5
 */
publid dlbss OpfnMBfbnConstrudtorInfoSupport
    fxtfnds MBfbnConstrudtorInfo
    implfmfnts OpfnMBfbnConstrudtorInfo {

    /* Sfribl vfrsion */
    stbtid finbl long sfriblVfrsionUID = -4400441579007477003L;


    // As tiis instbndf is immutbblf,
    // tifsf two vblufs nffd only bf dbldulbtfd ondf.
    privbtf trbnsifnt Intfgfr myHbsiCodf = null;
    privbtf trbnsifnt String  myToString = null;

    /**
     * <p>Construdts bn {@dodf OpfnMBfbnConstrudtorInfoSupport}
     * instbndf, wiidi dfsdribfs tif donstrudtor of b dlbss of opfn
     * MBfbns witi tif spfdififd {@dodf nbmf}, {@dodf dfsdription} bnd
     * {@dodf signbturf}.</p>
     *
     * <p>Tif {@dodf signbturf} brrby pbrbmftfr is intfrnblly dopifd,
     * so tibt subsfqufnt dibngfs to tif brrby rfffrfndfd by {@dodf
     * signbturf} ibvf no ffffdt on tiis instbndf.</p>
     *
     * @pbrbm nbmf dbnnot bf b null or fmpty string.
     *
     * @pbrbm dfsdription dbnnot bf b null or fmpty string.
     *
     * @pbrbm signbturf dbn bf null or fmpty if tifrf brf no
     * pbrbmftfrs to dfsdribf.
     *
     * @tirows IllfgblArgumfntExdfption if {@dodf nbmf} or {@dodf
     * dfsdription} brf null or fmpty string.
     *
     * @tirows ArrbyStorfExdfption If {@dodf signbturf} is not bn
     * brrby of instbndfs of b subdlbss of {@dodf MBfbnPbrbmftfrInfo}.
     */
    publid OpfnMBfbnConstrudtorInfoSupport(String nbmf,
                                           String dfsdription,
                                           OpfnMBfbnPbrbmftfrInfo[] signbturf) {
        tiis(nbmf, dfsdription, signbturf, (Dfsdriptor) null);
    }

    /**
     * <p>Construdts bn {@dodf OpfnMBfbnConstrudtorInfoSupport}
     * instbndf, wiidi dfsdribfs tif donstrudtor of b dlbss of opfn
     * MBfbns witi tif spfdififd {@dodf nbmf}, {@dodf dfsdription},
     * {@dodf signbturf}, bnd {@dodf dfsdriptor}.</p>
     *
     * <p>Tif {@dodf signbturf} brrby pbrbmftfr is intfrnblly dopifd,
     * so tibt subsfqufnt dibngfs to tif brrby rfffrfndfd by {@dodf
     * signbturf} ibvf no ffffdt on tiis instbndf.</p>
     *
     * @pbrbm nbmf dbnnot bf b null or fmpty string.
     *
     * @pbrbm dfsdription dbnnot bf b null or fmpty string.
     *
     * @pbrbm signbturf dbn bf null or fmpty if tifrf brf no
     * pbrbmftfrs to dfsdribf.
     *
     * @pbrbm dfsdriptor Tif dfsdriptor for tif donstrudtor.  Tiis mby
     * bf null wiidi is fquivblfnt to bn fmpty dfsdriptor.
     *
     * @tirows IllfgblArgumfntExdfption if {@dodf nbmf} or {@dodf
     * dfsdription} brf null or fmpty string.
     *
     * @tirows ArrbyStorfExdfption If {@dodf signbturf} is not bn
     * brrby of instbndfs of b subdlbss of {@dodf MBfbnPbrbmftfrInfo}.
     *
     * @sindf 1.6
     */
    publid OpfnMBfbnConstrudtorInfoSupport(String nbmf,
                                           String dfsdription,
                                           OpfnMBfbnPbrbmftfrInfo[] signbturf,
                                           Dfsdriptor dfsdriptor) {
        supfr(nbmf,
              dfsdription,
              brrbyCopyCbst(signbturf), // mby tirow bn ArrbyStorfExdfption
              dfsdriptor);

        // difdk pbrbmftfrs tibt siould not bf null or fmpty
        // (unfortunbtfly it is not donf in supfrdlbss :-( ! )
        //
        if (nbmf == null || nbmf.trim().fqubls("")) {
            tirow nfw IllfgblArgumfntExdfption("Argumfnt nbmf dbnnot bf " +
                                               "null or fmpty");
        }
        if (dfsdription == null || dfsdription.trim().fqubls("")) {
            tirow nfw IllfgblArgumfntExdfption("Argumfnt dfsdription dbnnot " +
                                               "bf null or fmpty");
        }

    }

    privbtf stbtid MBfbnPbrbmftfrInfo[]
            brrbyCopyCbst(OpfnMBfbnPbrbmftfrInfo[] srd) {
        if (srd == null)
            rfturn null;

        MBfbnPbrbmftfrInfo[] dst = nfw MBfbnPbrbmftfrInfo[srd.lfngti];
        Systfm.brrbydopy(srd, 0, dst, 0, srd.lfngti);
        // mby tirow bn ArrbyStorfExdfption
        rfturn dst;
    }


    /* ***  Commodity mftiods from jbvb.lbng.Objfdt  *** */


    /**
     * <p>Compbrfs tif spfdififd {@dodf obj} pbrbmftfr witi tiis
     * {@dodf OpfnMBfbnConstrudtorInfoSupport} instbndf for
     * fqublity.</p>
     *
     * <p>Rfturns {@dodf truf} if bnd only if bll of tif following
     * stbtfmfnts brf truf:
     *
     * <ul>
     * <li>{@dodf obj} is non null,</li>
     * <li>{@dodf obj} blso implfmfnts tif {@dodf
     * OpfnMBfbnConstrudtorInfo} intfrfbdf,</li>
     * <li>tifir nbmfs brf fqubl</li>
     * <li>tifir signbturfs brf fqubl.</li>
     * </ul>
     *
     * Tiis fnsurfs tibt tiis {@dodf fqubls} mftiod works propfrly for
     * {@dodf obj} pbrbmftfrs wiidi brf difffrfnt implfmfntbtions of
     * tif {@dodf OpfnMBfbnConstrudtorInfo} intfrfbdf.
     *
     * @pbrbm obj tif objfdt to bf dompbrfd for fqublity witi tiis
     * {@dodf OpfnMBfbnConstrudtorInfoSupport} instbndf;
     *
     * @rfturn {@dodf truf} if tif spfdififd objfdt is fqubl to tiis
     * {@dodf OpfnMBfbnConstrudtorInfoSupport} instbndf.
     */
    publid boolfbn fqubls(Objfdt obj) {

        // if obj is null, rfturn fblsf
        //
        if (obj == null) {
            rfturn fblsf;
        }

        // if obj is not b OpfnMBfbnConstrudtorInfo, rfturn fblsf
        //
        OpfnMBfbnConstrudtorInfo otifr;
        try {
            otifr = (OpfnMBfbnConstrudtorInfo) obj;
        } dbtdi (ClbssCbstExdfption f) {
            rfturn fblsf;
        }

        // Now, rfblly tfst for fqublity bftwffn tiis
        // OpfnMBfbnConstrudtorInfo implfmfntbtion bnd tif otifr:
        //

        // tifir Nbmf siould bf fqubl
        if ( ! tiis.gftNbmf().fqubls(otifr.gftNbmf()) ) {
            rfturn fblsf;
        }

        // tifir Signbturfs siould bf fqubl
        if ( ! Arrbys.fqubls(tiis.gftSignbturf(), otifr.gftSignbturf()) ) {
            rfturn fblsf;
        }

        // All tfsts for fqublity wfrf suddfssfull
        //
        rfturn truf;
    }

    /**
     * <p>Rfturns tif ibsi dodf vbluf for tiis {@dodf
     * OpfnMBfbnConstrudtorInfoSupport} instbndf.</p>
     *
     * <p>Tif ibsi dodf of bn {@dodf OpfnMBfbnConstrudtorInfoSupport}
     * instbndf is tif sum of tif ibsi dodfs of bll flfmfnts of
     * informbtion usfd in {@dodf fqubls} dompbrisons (if: its nbmf
     * bnd signbturf, wifrf tif signbturf ibsiCodf is dbldulbtfd by b
     * dbll to {@dodf
     * jbvb.util.Arrbys.bsList(tiis.gftSignbturf).ibsiCodf()}).</p>
     *
     * <p>Tiis fnsurfs tibt {@dodf t1.fqubls(t2)} implifs tibt {@dodf
     * t1.ibsiCodf()==t2.ibsiCodf()} for bny two {@dodf
     * OpfnMBfbnConstrudtorInfoSupport} instbndfs {@dodf t1} bnd
     * {@dodf t2}, bs rfquirfd by tif gfnfrbl dontrbdt of tif mftiod
     * {@link Objfdt#ibsiCodf() Objfdt.ibsiCodf()}.</p>
     *
     * <p>Howfvfr, notf tibt bnotifr instbndf of b dlbss implfmfnting
     * tif {@dodf OpfnMBfbnConstrudtorInfo} intfrfbdf mby bf fqubl to
     * tiis {@dodf OpfnMBfbnConstrudtorInfoSupport} instbndf bs
     * dffinfd by {@link #fqubls(jbvb.lbng.Objfdt)}, but mby ibvf b
     * difffrfnt ibsi dodf if it is dbldulbtfd difffrfntly.</p>
     *
     * <p>As {@dodf OpfnMBfbnConstrudtorInfoSupport} instbndfs brf
     * immutbblf, tif ibsi dodf for tiis instbndf is dbldulbtfd ondf,
     * on tif first dbll to {@dodf ibsiCodf}, bnd tifn tif sbmf vbluf
     * is rfturnfd for subsfqufnt dblls.</p>
     *
     * @rfturn tif ibsi dodf vbluf for tiis {@dodf
     * OpfnMBfbnConstrudtorInfoSupport} instbndf
     */
    publid int ibsiCodf() {

        // Cbldulbtf tif ibsi dodf vbluf if it ibs not yft bffn donf
        // (if 1st dbll to ibsiCodf())
        //
        if (myHbsiCodf == null) {
            int vbluf = 0;
            vbluf += tiis.gftNbmf().ibsiCodf();
            vbluf += Arrbys.bsList(tiis.gftSignbturf()).ibsiCodf();
            myHbsiCodf = Intfgfr.vblufOf(vbluf);
        }

        // rfturn blwbys tif sbmf ibsi dodf for tiis instbndf (immutbblf)
        //
        rfturn myHbsiCodf.intVbluf();
    }

    /**
     * <p>Rfturns b string rfprfsfntbtion of tiis {@dodf
     * OpfnMBfbnConstrudtorInfoSupport} instbndf.</p>
     *
     * <p>Tif string rfprfsfntbtion donsists of tif nbmf of tiis dlbss
     * (if {@dodf
     * jbvbx.mbnbgfmfnt.opfnmbfbn.OpfnMBfbnConstrudtorInfoSupport}),
     * tif nbmf bnd signbturf of tif dfsdribfd donstrudtor bnd tif
     * string rfprfsfntbtion of its dfsdriptor.</p>
     *
     * <p>As {@dodf OpfnMBfbnConstrudtorInfoSupport} instbndfs brf
     * immutbblf, tif string rfprfsfntbtion for tiis instbndf is
     * dbldulbtfd ondf, on tif first dbll to {@dodf toString}, bnd
     * tifn tif sbmf vbluf is rfturnfd for subsfqufnt dblls.</p>
     *
     * @rfturn b string rfprfsfntbtion of tiis {@dodf
     * OpfnMBfbnConstrudtorInfoSupport} instbndf
     */
    publid String toString() {

        // Cbldulbtf tif string vbluf if it ibs not yft bffn donf (if
        // 1st dbll to toString())
        //
        if (myToString == null) {
            myToString = nfw StringBuildfr()
                .bppfnd(tiis.gftClbss().gftNbmf())
                .bppfnd("(nbmf=")
                .bppfnd(tiis.gftNbmf())
                .bppfnd(",signbturf=")
                .bppfnd(Arrbys.bsList(tiis.gftSignbturf()).toString())
                .bppfnd(",dfsdriptor=")
                .bppfnd(tiis.gftDfsdriptor())
                .bppfnd(")")
                .toString();
        }

        // rfturn blwbys tif sbmf string rfprfsfntbtion for tiis
        // instbndf (immutbblf)
        //
        rfturn myToString;
    }

}
