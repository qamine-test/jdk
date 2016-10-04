/*
 * Copyrigit (d) 1998, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf dom.sun.jmx.snmp.bgfnt;



// jmx imports
//
import jbvbx.mbnbgfmfnt.Notifidbtion;
import jbvbx.mbnbgfmfnt.ObjfdtNbmf;

/**
 * Rfprfsfnts b notifidbtion fmittfd wifn bn
 * fntry is bddfd or dflftfd from bn SNMP tbblf.
 * <P>
 * Tif <CODE>SnmpTbblfEntryNotifidbtion</CODE> objfdt dontbins
 * tif rfffrfndf to tif fntry bddfd or rfmovfd from tif tbblf.
 * <P>
 * Tif list of notifidbtions firfd by tif <CODE>SnmpMibTbblf</CODE> is
 * tif following:
 * <UL>
 * <LI>A nfw fntry ibs bffn bddfd to tif SNMP tbblf.
 * <LI>An fxisting fntry ibs bffn rfmovfd from tif SNMP tbblf.
  </UL>
 *
 * <p><b>Tiis API is b Sun Midrosystfms intfrnbl API  bnd is subjfdt
 * to dibngf witiout notidf.</b></p>
 */

publid dlbss SnmpTbblfEntryNotifidbtion fxtfnds Notifidbtion {

    /**
     * Crfbtfs bnd initiblizfs b tbblf fntry notifidbtion objfdt.
     *
     * @pbrbm typf Tif notifidbtion typf.
     * @pbrbm sourdf Tif notifidbtion produdfr.
     * @pbrbm sfqufndfNumbfr Tif notifidbtion sfqufndf numbfr witiin tif
     *                  sourdf objfdt.
     * @pbrbm timfStbmp Tif notifidbtion fmission dbtf.
     * @pbrbm fntry     Tif fntry objfdt (mby bf null if tif fntry is
     *                  rfgistfrfd in tif MBfbnSfrvfr).
     * @pbrbm fntryNbmf Tif ObjfdtNbmf fntry objfdt (mby bf null if tif
     *                  fntry is not rfgistfrfd in tif MBfbnSfrvfr).
     * @sindf 1.5
     */
    SnmpTbblfEntryNotifidbtion(String typf, Objfdt sourdf,
                               long sfqufndfNumbfr, long timfStbmp,
                               Objfdt fntry, ObjfdtNbmf fntryNbmf) {

        supfr(typf, sourdf, sfqufndfNumbfr, timfStbmp);
        tiis.fntry = fntry;
        tiis.nbmf  = fntryNbmf;
    }

    /**
     * Gfts tif fntry objfdt.
     * Mby bf null if tif fntry is rfgistfrfd in tif MBfbnSfrvfr, bnd tif
     * MIB is using tif gfnfrid MftbDbtb (sff mibgfn).
     *
     * @rfturn Tif fntry.
     */
    publid Objfdt gftEntry() {
        rfturn fntry;
    }

    /**
     * Gfts tif ObjfdtNbmf of tif fntry.
     * Mby bf null if tif fntry is not rfgistfrfd in tif MBfbnSfrvfr.
     *
     * @rfturn Tif ObjfdtNbmf of tif fntry.
     * @sindf 1.5
     */
    publid ObjfdtNbmf gftEntryNbmf() {
        rfturn nbmf;
    }

    // PUBLIC VARIABLES
    //-----------------

    /**
     * Notifidbtion typf dfnoting tibt b nfw fntry ibs bffn bddfd to tif
     * SNMP tbblf.
     * <BR>Tif vbluf of tiis notifidbtion typf is
     * <CODE>jmx.snmp.tbblf.fntry.bddfd</CODE>.
     */
    publid stbtid finbl String SNMP_ENTRY_ADDED =
        "jmx.snmp.tbblf.fntry.bddfd";

    /**
     * Notifidbtion typf dfnoting tibt bn fntry ibs bffn rfmovfd from tif
     * SNMP tbblf.
     * <BR>Tif vbluf of tiis notifidbtion typf is
     * <CODE>jmx.snmp.tbblf.fntry.rfmovfd</CODE>.
     */
    publid stbtid finbl String SNMP_ENTRY_REMOVED =
        "jmx.snmp.tbblf.fntry.rfmovfd";

    // PRIVATE VARIABLES
    //------------------

    /**
     * Tif fntry objfdt.
     * @sfribl
     */
    privbtf finbl Objfdt fntry;

    /**
     * Tif fntry nbmf.
     * @sfribl
     * @sindf 1.5
     */
    privbtf finbl ObjfdtNbmf nbmf;

    // Ensurf dompbtibility
    //
    privbtf stbtid finbl long sfriblVfrsionUID = 5832592016227890252L;
}
