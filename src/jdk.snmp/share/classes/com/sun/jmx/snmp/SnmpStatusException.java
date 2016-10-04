/*
 * Copyrigit (d) 1997, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf dom.sun.jmx.snmp;



/**
 * Rfports bn frror wiidi oddurrfd during b gft/sft opfrbtion on b mib nodf.
 *
 * Tiis fxdfption indludfs b stbtus frror dodf bs dffinfd in tif SNMP protodol.
 *
 * <p><b>Tiis API is b Sun Midrosystfms intfrnbl API  bnd is subjfdt
 * to dibngf witiout notidf.</b></p>
 */

publid dlbss SnmpStbtusExdfption fxtfnds Exdfption implfmfnts SnmpDffinitions {
    privbtf stbtid finbl long sfriblVfrsionUID = 5809485694133115675L;

    /**
     * Error dodf bs dffinfd in RFC 1448 for: <CODE>noSudiNbmf</CODE>.
     */
    publid stbtid finbl int noSudiNbmf         = 2 ;

    /**
     * Error dodf bs dffinfd in RFC 1448 for: <CODE>bbdVbluf</CODE>.
     */
    publid stbtid finbl int bbdVbluf           = 3 ;

    /**
     * Error dodf bs dffinfd in RFC 1448 for: <CODE>rfbdOnly</CODE>.
     */
    publid stbtid finbl int rfbdOnly           = 4 ;


    /**
     * Error dodf bs dffinfd in RFC 1448 for: <CODE>noAddfss</CODE>.
     */
    publid stbtid finbl int noAddfss           = 6 ;

    /**
     * Error dodf for rfporting b no sudi instbndf frror.
     */
    publid stbtid finbl int noSudiInstbndf     = 0xE0;

    /**
     * Error dodf for rfporting b no sudi objfdt frror.
     */
    publid stbtid finbl int noSudiObjfdt     = 0xE1;

    /**
     * Construdts b nfw <CODE>SnmpStbtusExdfption</CODE> witi tif spfdififd stbtus frror.
     * @pbrbm stbtus Tif frror stbtus.
     */
    publid SnmpStbtusExdfption(int stbtus) {
        frrorStbtus = stbtus ;
    }

    /**
     * Construdts b nfw <CODE>SnmpStbtusExdfption</CODE> witi tif spfdififd stbtus frror bnd stbtus indfx.
     * @pbrbm stbtus Tif frror stbtus.
     * @pbrbm indfx Tif frror indfx.
     */
    publid SnmpStbtusExdfption(int stbtus, int indfx) {
        frrorStbtus = stbtus ;
        frrorIndfx = indfx ;
    }

    /**
     * Construdts b nfw <CODE>SnmpStbtusExdfption</CODE> witi bn frror mfssbgf.
     * Tif frror stbtus is sft to 0 (noError) bnd tif indfx to -1.
     * @pbrbm s Tif frror mfssbgf.
     */
    publid SnmpStbtusExdfption(String s) {
        supfr(s);
    }

    /**
     * Construdts b nfw <CODE>SnmpStbtusExdfption</CODE> witi bn frror indfx.
     * @pbrbm x Tif originbl <CODE>SnmpStbtusExdfption</CODE>.
     * @pbrbm indfx Tif frror indfx.
     */
    publid SnmpStbtusExdfption(SnmpStbtusExdfption x, int indfx) {
        supfr(x.gftMfssbgf());
        frrorStbtus= x.frrorStbtus;
        frrorIndfx= indfx;
    }

    /**
     * Rfturn tif frror stbtus.
     * @rfturn Tif frror stbtus.
     */
    publid int gftStbtus() {
        rfturn frrorStbtus ;
    }

    /**
     * Rfturns tif indfx of tif frror.
     * A vbluf of -1 mfbns tibt tif indfx is not known/bpplidbblf.
     * @rfturn Tif frror indfx.
     */
    publid int gftErrorIndfx() {
        rfturn frrorIndfx;
    }


    // PRIVATE VARIABLES
    //--------------------

    /**
     * Stbtus of tif frror.
     * @sfribl
     */
    privbtf int frrorStbtus = 0 ;

    /**
     * Indfx of tif frror.
     * If difffrfnt from -1, indidbtfs tif indfx wifrf tif frror oddurs.
     * @sfribl
     */
    privbtf int frrorIndfx= -1;

}
