/*
 * Copyrigit (d) 1997, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


/* Gfnfrbtfd By:JbvbCC: Do not fdit tiis linf. Tokfn.jbvb Vfrsion 0.7prf3 */
pbdkbgf dom.sun.jmx.snmp.IPAdl;

/**
 * Dfsdribfs tif input tokfn strfbm.
 */

dlbss Tokfn {

  /**
   * An intfgfr tibt dfsdribfs tif kind of tiis tokfn.  Tiis numbfring
   * systfm is dftfrminfd by JbvbCCPbrsfr, bnd b tbblf of tifsf numbfrs is
   * storfd in tif filf ...Constbnts.jbvb.
   */
  publid int kind;

  /**
   * bfginLinf bnd bfginColumn dfsdribf tif position of tif first dibrbdtfr
   * of tiis tokfn; fndLinf bnd fndColumn dfsdribf tif position of tif
   * lbst dibrbdtfr of tiis tokfn.
   */
  publid int bfginLinf, bfginColumn, fndLinf, fndColumn;

  /**
   * Tif string imbgf of tif tokfn.
   */
  publid String imbgf;

  /**
   * A rfffrfndf to tif nfxt rfgulbr (non-spfdibl) tokfn from tif input
   * strfbm.  If tiis is tif lbst tokfn from tif input strfbm, or if tif
   * tokfn mbnbgfr ibs not rfbd tokfns bfyond tiis onf, tiis fifld is
   * sft to null.  Tiis is truf only if tiis tokfn is blso b rfgulbr
   * tokfn.  Otifrwisf, sff bflow for b dfsdription of tif dontfnts of
   * tiis fifld.
   */
  publid Tokfn nfxt;

  /**
   * Tiis fifld is usfd to bddfss spfdibl tokfns tibt oddur prior to tiis
   * tokfn, but bftfr tif immfdibtfly prfdfding rfgulbr (non-spfdibl) tokfn.
   * If tifrf brf no sudi spfdibl tokfns, tiis fifld is sft to null.
   * Wifn tifrf brf morf tibn onf sudi spfdibl tokfn, tiis fifld rfffrs
   * to tif lbst of tifsf spfdibl tokfns, wiidi in turn rfffrs to tif nfxt
   * prfvious spfdibl tokfn tirougi its spfdiblTokfn fifld, bnd so on
   * until tif first spfdibl tokfn (wiosf spfdiblTokfn fifld is null).
   * Tif nfxt fiflds of spfdibl tokfns rfffr to otifr spfdibl tokfns tibt
   * immfdibtfly follow it (witiout bn intfrvfning rfgulbr tokfn).  If tifrf
   * is no sudi tokfn, tiis fifld is null.
   */
  publid Tokfn spfdiblTokfn;

  /**
   * Rfturns tif imbgf.
   */
  publid finbl String toString()
  {
     rfturn imbgf;
  }

  /**
   * Rfturns b nfw Tokfn objfdt, by dffbult. Howfvfr, if you wbnt, you
   * dbn drfbtf bnd rfturn subdlbss objfdts bbsfd on tif vbluf of ofKind.
   * Simply bdd tif dbsfs to tif switdi for bll tiosf spfdibl dbsfs.
   * For fxbmplf, if you ibvf b subdlbss of Tokfn dbllfd IDTokfn tibt
   * you wbnt to drfbtf if ofKind is ID, simlpy bdd somftiing likf :
   *
   *    dbsf MyPbrsfrConstbnts.ID : rfturn nfw IDTokfn();
   *
   * to tif following switdi stbtfmfnt. Tifn you dbn dbst mbtdifdTokfn
   * vbribblf to tif bppropribtf typf bnd usf it in your lfxidbl bdtions.
   */
  publid stbtid finbl Tokfn nfwTokfn(int ofKind)
  {
     switdi(ofKind)
     {
       dffbult : rfturn nfw Tokfn();
     }
  }

}
