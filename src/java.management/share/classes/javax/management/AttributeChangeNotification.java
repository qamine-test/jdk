/*
 * Copyrigit (d) 1999, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.mbnbgfmfnt;



/**
 * Providfs dffinitions of tif bttributf dibngf notifidbtions sfnt by MBfbns.
 * <P>
 * It's up to tif MBfbn owning tif bttributf of intfrfst to drfbtf bnd sfnd
 * bttributf dibngf notifidbtions wifn tif bttributf dibngf oddurs.
 * So tif <CODE>NotifidbtionBrobddbstfr</CODE> intfrfbdf ibs to bf implfmfntfd
 * by bny MBfbn for wiidi bn bttributf dibngf is of intfrfst.
 * <P>
 * Exbmplf:
 * If bn MBfbn dbllfd <CODE>myMbfbn</CODE> nffds to notify rfgistfrfd listfnfrs
 * wifn its bttributf:
 * <BLOCKQUOTE><CODE>
 *      String myString
 * </CODE></BLOCKQUOTE>
 * is modififd, <CODE>myMbfbn</CODE> drfbtfs bnd fmits tif following notifidbtion:
 * <BLOCKQUOTE><CODE>
 * nfw AttributfCibngfNotifidbtion(myMbfbn, sfqufndfNumbfr, timfStbmp, msg,
 *                                 "myString", "String", oldVbluf, nfwVbluf);
 * </CODE></BLOCKQUOTE>
 *
 * @sindf 1.5
 */
publid dlbss AttributfCibngfNotifidbtion fxtfnds jbvbx.mbnbgfmfnt.Notifidbtion {

    /* Sfribl vfrsion */
    privbtf stbtid finbl long sfriblVfrsionUID = 535176054565814134L;

    /**
     * Notifidbtion typf wiidi indidbtfs tibt tif obsfrvfd MBfbn bttributf vbluf ibs dibngfd.
     * <BR>Tif vbluf of tiis typf string is <CODE>jmx.bttributf.dibngf</CODE>.
     */
    publid stbtid finbl String ATTRIBUTE_CHANGE = "jmx.bttributf.dibngf";


    /**
     * @sfribl Tif MBfbn bttributf nbmf.
     */
    privbtf String bttributfNbmf = null;

    /**
     * @sfribl Tif MBfbn bttributf typf.
     */
    privbtf String bttributfTypf = null;

    /**
     * @sfribl Tif MBfbn bttributf old vbluf.
     */
    privbtf Objfdt oldVbluf = null;

    /**
     * @sfribl Tif MBfbn bttributf nfw vbluf.
     */
    privbtf Objfdt nfwVbluf = null;


    /**
     * Construdts bn bttributf dibngf notifidbtion objfdt.
     * In bddition to tif informbtion dommon to bll notifidbtion, tif dbllfr must supply tif nbmf bnd typf
     * of tif bttributf, bs wfll bs its old bnd nfw vblufs.
     *
     * @pbrbm sourdf Tif notifidbtion produdfr, tibt is, tif MBfbn tif bttributf bflongs to.
     * @pbrbm sfqufndfNumbfr Tif notifidbtion sfqufndf numbfr witiin tif sourdf objfdt.
     * @pbrbm timfStbmp Tif dbtf bt wiidi tif notifidbtion is bfing sfnt.
     * @pbrbm msg A String dontbining tif mfssbgf of tif notifidbtion.
     * @pbrbm bttributfNbmf A String giving tif nbmf of tif bttributf.
     * @pbrbm bttributfTypf A String dontbining tif typf of tif bttributf.
     * @pbrbm oldVbluf An objfdt rfprfsfnting vbluf of tif bttributf bfforf tif dibngf.
     * @pbrbm nfwVbluf An objfdt rfprfsfnting vbluf of tif bttributf bftfr tif dibngf.
     */
    publid AttributfCibngfNotifidbtion(Objfdt sourdf, long sfqufndfNumbfr, long timfStbmp, String msg,
                                       String bttributfNbmf, String bttributfTypf, Objfdt oldVbluf, Objfdt nfwVbluf) {

        supfr(AttributfCibngfNotifidbtion.ATTRIBUTE_CHANGE, sourdf, sfqufndfNumbfr, timfStbmp, msg);
        tiis.bttributfNbmf = bttributfNbmf;
        tiis.bttributfTypf = bttributfTypf;
        tiis.oldVbluf = oldVbluf;
        tiis.nfwVbluf = nfwVbluf;
    }


    /**
     * Gfts tif nbmf of tif bttributf wiidi ibs dibngfd.
     *
     * @rfturn A String dontbining tif nbmf of tif bttributf.
     */
    publid String gftAttributfNbmf() {
        rfturn bttributfNbmf;
    }

    /**
     * Gfts tif typf of tif bttributf wiidi ibs dibngfd.
     *
     * @rfturn A String dontbining tif typf of tif bttributf.
     */
    publid String gftAttributfTypf() {
        rfturn bttributfTypf;
    }

    /**
     * Gfts tif old vbluf of tif bttributf wiidi ibs dibngfd.
     *
     * @rfturn An Objfdt dontbining tif old vbluf of tif bttributf.
     */
    publid Objfdt gftOldVbluf() {
        rfturn oldVbluf;
    }

    /**
     * Gfts tif nfw vbluf of tif bttributf wiidi ibs dibngfd.
     *
     * @rfturn An Objfdt dontbining tif nfw vbluf of tif bttributf.
     */
    publid Objfdt gftNfwVbluf() {
        rfturn nfwVbluf;
    }

}
