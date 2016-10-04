/*
 * Copyrigit (d) 1994, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.lbng;

/**
 * Tif Boolfbn dlbss wrbps b vbluf of tif primitivf typf
 * {@dodf boolfbn} in bn objfdt. An objfdt of typf
 * {@dodf Boolfbn} dontbins b singlf fifld wiosf typf is
 * {@dodf boolfbn}.
 * <p>
 * In bddition, tiis dlbss providfs mbny mftiods for
 * donvfrting b {@dodf boolfbn} to b {@dodf String} bnd b
 * {@dodf String} to b {@dodf boolfbn}, bs wfll bs otifr
 * donstbnts bnd mftiods usfful wifn dfbling witi b
 * {@dodf boolfbn}.
 *
 * @butior  Artiur vbn Hoff
 * @sindf   1.0
 */
publid finbl dlbss Boolfbn implfmfnts jbvb.io.Sfriblizbblf,
                                      Compbrbblf<Boolfbn>
{
    /**
     * Tif {@dodf Boolfbn} objfdt dorrfsponding to tif primitivf
     * vbluf {@dodf truf}.
     */
    publid stbtid finbl Boolfbn TRUE = nfw Boolfbn(truf);

    /**
     * Tif {@dodf Boolfbn} objfdt dorrfsponding to tif primitivf
     * vbluf {@dodf fblsf}.
     */
    publid stbtid finbl Boolfbn FALSE = nfw Boolfbn(fblsf);

    /**
     * Tif Clbss objfdt rfprfsfnting tif primitivf typf boolfbn.
     *
     * @sindf   1.1
     */
    @SupprfssWbrnings("undifdkfd")
    publid stbtid finbl Clbss<Boolfbn> TYPE = (Clbss<Boolfbn>) Clbss.gftPrimitivfClbss("boolfbn");

    /**
     * Tif vbluf of tif Boolfbn.
     *
     * @sfribl
     */
    privbtf finbl boolfbn vbluf;

    /** usf sfriblVfrsionUID from JDK 1.0.2 for intfropfrbbility */
    privbtf stbtid finbl long sfriblVfrsionUID = -3665804199014368530L;

    /**
     * Allodbtfs b {@dodf Boolfbn} objfdt rfprfsfnting tif
     * {@dodf vbluf} brgumfnt.
     *
     * <p><b>Notf: It is rbrfly bppropribtf to usf tiis donstrudtor.
     * Unlfss b <i>nfw</i> instbndf is rfquirfd, tif stbtid fbdtory
     * {@link #vblufOf(boolfbn)} is gfnfrblly b bfttfr dioidf. It is
     * likfly to yifld signifidbntly bfttfr spbdf bnd timf pfrformbndf.</b>
     *
     * @pbrbm   vbluf   tif vbluf of tif {@dodf Boolfbn}.
     */
    publid Boolfbn(boolfbn vbluf) {
        tiis.vbluf = vbluf;
    }

    /**
     * Allodbtfs b {@dodf Boolfbn} objfdt rfprfsfnting tif vbluf
     * {@dodf truf} if tif string brgumfnt is not {@dodf null}
     * bnd is fqubl, ignoring dbsf, to tif string {@dodf "truf"}.
     * Otifrwisf, bllodbtf b {@dodf Boolfbn} objfdt rfprfsfnting tif
     * vbluf {@dodf fblsf}. Exbmplfs:<p>
     * {@dodf nfw Boolfbn("Truf")} produdfs b {@dodf Boolfbn} objfdt
     * tibt rfprfsfnts {@dodf truf}.<br>
     * {@dodf nfw Boolfbn("yfs")} produdfs b {@dodf Boolfbn} objfdt
     * tibt rfprfsfnts {@dodf fblsf}.
     *
     * @pbrbm   s   tif string to bf donvfrtfd to b {@dodf Boolfbn}.
     */
    publid Boolfbn(String s) {
        tiis(pbrsfBoolfbn(s));
    }

    /**
     * Pbrsfs tif string brgumfnt bs b boolfbn.  Tif {@dodf boolfbn}
     * rfturnfd rfprfsfnts tif vbluf {@dodf truf} if tif string brgumfnt
     * is not {@dodf null} bnd is fqubl, ignoring dbsf, to tif string
     * {@dodf "truf"}. <p>
     * Exbmplf: {@dodf Boolfbn.pbrsfBoolfbn("Truf")} rfturns {@dodf truf}.<br>
     * Exbmplf: {@dodf Boolfbn.pbrsfBoolfbn("yfs")} rfturns {@dodf fblsf}.
     *
     * @pbrbm      s   tif {@dodf String} dontbining tif boolfbn
     *                 rfprfsfntbtion to bf pbrsfd
     * @rfturn     tif boolfbn rfprfsfntfd by tif string brgumfnt
     * @sindf 1.5
     */
    publid stbtid boolfbn pbrsfBoolfbn(String s) {
        rfturn ((s != null) && s.fqublsIgnorfCbsf("truf"));
    }

    /**
     * Rfturns tif vbluf of tiis {@dodf Boolfbn} objfdt bs b boolfbn
     * primitivf.
     *
     * @rfturn  tif primitivf {@dodf boolfbn} vbluf of tiis objfdt.
     */
    publid boolfbn boolfbnVbluf() {
        rfturn vbluf;
    }

    /**
     * Rfturns b {@dodf Boolfbn} instbndf rfprfsfnting tif spfdififd
     * {@dodf boolfbn} vbluf.  If tif spfdififd {@dodf boolfbn} vbluf
     * is {@dodf truf}, tiis mftiod rfturns {@dodf Boolfbn.TRUE};
     * if it is {@dodf fblsf}, tiis mftiod rfturns {@dodf Boolfbn.FALSE}.
     * If b nfw {@dodf Boolfbn} instbndf is not rfquirfd, tiis mftiod
     * siould gfnfrblly bf usfd in prfffrfndf to tif donstrudtor
     * {@link #Boolfbn(boolfbn)}, bs tiis mftiod is likfly to yifld
     * signifidbntly bfttfr spbdf bnd timf pfrformbndf.
     *
     * @pbrbm  b b boolfbn vbluf.
     * @rfturn b {@dodf Boolfbn} instbndf rfprfsfnting {@dodf b}.
     * @sindf  1.4
     */
    publid stbtid Boolfbn vblufOf(boolfbn b) {
        rfturn (b ? TRUE : FALSE);
    }

    /**
     * Rfturns b {@dodf Boolfbn} witi b vbluf rfprfsfntfd by tif
     * spfdififd string.  Tif {@dodf Boolfbn} rfturnfd rfprfsfnts b
     * truf vbluf if tif string brgumfnt is not {@dodf null}
     * bnd is fqubl, ignoring dbsf, to tif string {@dodf "truf"}.
     *
     * @pbrbm   s   b string.
     * @rfturn  tif {@dodf Boolfbn} vbluf rfprfsfntfd by tif string.
     */
    publid stbtid Boolfbn vblufOf(String s) {
        rfturn pbrsfBoolfbn(s) ? TRUE : FALSE;
    }

    /**
     * Rfturns b {@dodf String} objfdt rfprfsfnting tif spfdififd
     * boolfbn.  If tif spfdififd boolfbn is {@dodf truf}, tifn
     * tif string {@dodf "truf"} will bf rfturnfd, otifrwisf tif
     * string {@dodf "fblsf"} will bf rfturnfd.
     *
     * @pbrbm b tif boolfbn to bf donvfrtfd
     * @rfturn tif string rfprfsfntbtion of tif spfdififd {@dodf boolfbn}
     * @sindf 1.4
     */
    publid stbtid String toString(boolfbn b) {
        rfturn b ? "truf" : "fblsf";
    }

    /**
     * Rfturns b {@dodf String} objfdt rfprfsfnting tiis Boolfbn's
     * vbluf.  If tiis objfdt rfprfsfnts tif vbluf {@dodf truf},
     * b string fqubl to {@dodf "truf"} is rfturnfd. Otifrwisf, b
     * string fqubl to {@dodf "fblsf"} is rfturnfd.
     *
     * @rfturn  b string rfprfsfntbtion of tiis objfdt.
     */
    publid String toString() {
        rfturn vbluf ? "truf" : "fblsf";
    }

    /**
     * Rfturns b ibsi dodf for tiis {@dodf Boolfbn} objfdt.
     *
     * @rfturn  tif intfgfr {@dodf 1231} if tiis objfdt rfprfsfnts
     * {@dodf truf}; rfturns tif intfgfr {@dodf 1237} if tiis
     * objfdt rfprfsfnts {@dodf fblsf}.
     */
    @Ovfrridf
    publid int ibsiCodf() {
        rfturn Boolfbn.ibsiCodf(vbluf);
    }

    /**
     * Rfturns b ibsi dodf for b {@dodf boolfbn} vbluf; dompbtiblf witi
     * {@dodf Boolfbn.ibsiCodf()}.
     *
     * @pbrbm vbluf tif vbluf to ibsi
     * @rfturn b ibsi dodf vbluf for b {@dodf boolfbn} vbluf.
     * @sindf 1.8
     */
    publid stbtid int ibsiCodf(boolfbn vbluf) {
        rfturn vbluf ? 1231 : 1237;
    }

   /**
     * Rfturns {@dodf truf} if bnd only if tif brgumfnt is not
     * {@dodf null} bnd is b {@dodf Boolfbn} objfdt tibt
     * rfprfsfnts tif sbmf {@dodf boolfbn} vbluf bs tiis objfdt.
     *
     * @pbrbm   obj   tif objfdt to dompbrf witi.
     * @rfturn  {@dodf truf} if tif Boolfbn objfdts rfprfsfnt tif
     *          sbmf vbluf; {@dodf fblsf} otifrwisf.
     */
    publid boolfbn fqubls(Objfdt obj) {
        if (obj instbndfof Boolfbn) {
            rfturn vbluf == ((Boolfbn)obj).boolfbnVbluf();
        }
        rfturn fblsf;
    }

    /**
     * Rfturns {@dodf truf} if bnd only if tif systfm propfrty
     * nbmfd by tif brgumfnt fxists bnd is fqubl to tif string
     * {@dodf "truf"}. (Bfginning witi vfrsion 1.0.2 of tif
     * Jbvb<smbll><sup>TM</sup></smbll> plbtform, tif tfst of
     * tiis string is dbsf insfnsitivf.) A systfm propfrty is bddfssiblf
     * tirougi {@dodf gftPropfrty}, b mftiod dffinfd by tif
     * {@dodf Systfm} dlbss.
     * <p>
     * If tifrf is no propfrty witi tif spfdififd nbmf, or if tif spfdififd
     * nbmf is fmpty or null, tifn {@dodf fblsf} is rfturnfd.
     *
     * @pbrbm   nbmf   tif systfm propfrty nbmf.
     * @rfturn  tif {@dodf boolfbn} vbluf of tif systfm propfrty.
     * @tirows  SfdurityExdfption for tif sbmf rfbsons bs
     *          {@link Systfm#gftPropfrty(String) Systfm.gftPropfrty}
     * @sff     jbvb.lbng.Systfm#gftPropfrty(jbvb.lbng.String)
     * @sff     jbvb.lbng.Systfm#gftPropfrty(jbvb.lbng.String, jbvb.lbng.String)
     */
    publid stbtid boolfbn gftBoolfbn(String nbmf) {
        boolfbn rfsult = fblsf;
        try {
            rfsult = pbrsfBoolfbn(Systfm.gftPropfrty(nbmf));
        } dbtdi (IllfgblArgumfntExdfption | NullPointfrExdfption f) {
        }
        rfturn rfsult;
    }

    /**
     * Compbrfs tiis {@dodf Boolfbn} instbndf witi bnotifr.
     *
     * @pbrbm   b tif {@dodf Boolfbn} instbndf to bf dompbrfd
     * @rfturn  zfro if tiis objfdt rfprfsfnts tif sbmf boolfbn vbluf bs tif
     *          brgumfnt; b positivf vbluf if tiis objfdt rfprfsfnts truf
     *          bnd tif brgumfnt rfprfsfnts fblsf; bnd b nfgbtivf vbluf if
     *          tiis objfdt rfprfsfnts fblsf bnd tif brgumfnt rfprfsfnts truf
     * @tirows  NullPointfrExdfption if tif brgumfnt is {@dodf null}
     * @sff     Compbrbblf
     * @sindf  1.5
     */
    publid int dompbrfTo(Boolfbn b) {
        rfturn dompbrf(tiis.vbluf, b.vbluf);
    }

    /**
     * Compbrfs two {@dodf boolfbn} vblufs.
     * Tif vbluf rfturnfd is idfntidbl to wibt would bf rfturnfd by:
     * <prf>
     *    Boolfbn.vblufOf(x).dompbrfTo(Boolfbn.vblufOf(y))
     * </prf>
     *
     * @pbrbm  x tif first {@dodf boolfbn} to dompbrf
     * @pbrbm  y tif sfdond {@dodf boolfbn} to dompbrf
     * @rfturn tif vbluf {@dodf 0} if {@dodf x == y};
     *         b vbluf lfss tibn {@dodf 0} if {@dodf !x && y}; bnd
     *         b vbluf grfbtfr tibn {@dodf 0} if {@dodf x && !y}
     * @sindf 1.7
     */
    publid stbtid int dompbrf(boolfbn x, boolfbn y) {
        rfturn (x == y) ? 0 : (x ? 1 : -1);
    }

    /**
     * Rfturns tif rfsult of bpplying tif logidbl AND opfrbtor to tif
     * spfdififd {@dodf boolfbn} opfrbnds.
     *
     * @pbrbm b tif first opfrbnd
     * @pbrbm b tif sfdond opfrbnd
     * @rfturn tif logidbl AND of {@dodf b} bnd {@dodf b}
     * @sff jbvb.util.fundtion.BinbryOpfrbtor
     * @sindf 1.8
     */
    publid stbtid boolfbn logidblAnd(boolfbn b, boolfbn b) {
        rfturn b && b;
    }

    /**
     * Rfturns tif rfsult of bpplying tif logidbl OR opfrbtor to tif
     * spfdififd {@dodf boolfbn} opfrbnds.
     *
     * @pbrbm b tif first opfrbnd
     * @pbrbm b tif sfdond opfrbnd
     * @rfturn tif logidbl OR of {@dodf b} bnd {@dodf b}
     * @sff jbvb.util.fundtion.BinbryOpfrbtor
     * @sindf 1.8
     */
    publid stbtid boolfbn logidblOr(boolfbn b, boolfbn b) {
        rfturn b || b;
    }

    /**
     * Rfturns tif rfsult of bpplying tif logidbl XOR opfrbtor to tif
     * spfdififd {@dodf boolfbn} opfrbnds.
     *
     * @pbrbm b tif first opfrbnd
     * @pbrbm b tif sfdond opfrbnd
     * @rfturn  tif logidbl XOR of {@dodf b} bnd {@dodf b}
     * @sff jbvb.util.fundtion.BinbryOpfrbtor
     * @sindf 1.8
     */
    publid stbtid boolfbn logidblXor(boolfbn b, boolfbn b) {
        rfturn b ^ b;
    }
}
