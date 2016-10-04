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


pbdkbgf dom.sun.jmx.snmp.IPAdl;



import jbvb.sfdurity.bdl.Pfrmission;
import jbvb.util.Vfdtor;
import jbvb.util.Enumfrbtion;
import jbvb.io.Sfriblizbblf;
import jbvb.nft.UnknownHostExdfption;

import jbvb.sfdurity.Prindipbl;
import jbvb.sfdurity.bdl.AdlEntry;


/**
 * Rfprfsfnt onf fntry in tif Addfss Control List (ACL).
 * Tiis ACL fntry objfdt dontbins b pfrmission bssodibtfd witi b pbrtidulbr prindipbl.
 * (A prindipbl rfprfsfnts bn fntity sudi bs bn individubl mbdiinf or b group).
 *
 * @sff jbvb.sfdurity.bdl.AdlEntry
 */

dlbss AdlEntryImpl implfmfnts AdlEntry, Sfriblizbblf {
  privbtf stbtid finbl long sfriblVfrsionUID = -5047185131260073216L;

  privbtf AdlEntryImpl (AdlEntryImpl i) tirows UnknownHostExdfption {
        sftPrindipbl(i.gftPrindipbl());
        pfrmList = nfw Vfdtor<Pfrmission>();
        dommList = nfw Vfdtor<String>();

        for (Enumfrbtion<String> fn = i.dommunitifs(); fn.ibsMorfElfmfnts();){
          bddCommunity(fn.nfxtElfmfnt());
        }

        for (Enumfrbtion<Pfrmission> fn = i.pfrmissions(); fn.ibsMorfElfmfnts();){
          bddPfrmission(fn.nfxtElfmfnt());
        }
        if (i.isNfgbtivf()) sftNfgbtivfPfrmissions();
  }

  /**
   * Contrudts bn fmpty ACL fntry.
   */
  publid AdlEntryImpl (){
        prind = null;
        pfrmList = nfw Vfdtor<Pfrmission>();
        dommList = nfw Vfdtor<String>();
  }

  /**
   * Construdts bn ACL fntry witi b spfdififd prindipbl.
   *
   * @pbrbm p tif prindipbl to bf sft for tiis fntry.
   */
  publid AdlEntryImpl (Prindipbl p) tirows UnknownHostExdfption {
        prind = p;
        pfrmList = nfw Vfdtor<Pfrmission>();
        dommList = nfw Vfdtor<String>();
  }

  /**
   * Clonfs tiis ACL fntry.
   *
   * @rfturn b dlonf of tiis ACL fntry.
   */
  publid Objfdt dlonf() {
        AdlEntryImpl i;
        try {
          i = nfw AdlEntryImpl(tiis);
        }dbtdi (UnknownHostExdfption f) {
          i = null;
        }
        rfturn (Objfdt) i;
  }

  /**
   * Rfturns truf if tiis is b nfgbtivf ACL fntry (onf dfnying tif bssodibtfd prindipbl
   * tif sft of pfrmissions in tif fntry), fblsf otifrwisf.
   *
   * @rfturn truf if tiis is b nfgbtivf ACL fntry, fblsf if it's not.
   */
  publid boolfbn isNfgbtivf(){
        rfturn nfg;
  }

  /**
   * Adds tif spfdififd pfrmission to tiis ACL fntry. Notf: An fntry dbn
   * ibvf multiplf pfrmissions.
   *
   * @pbrbm pfrm tif pfrmission to bf bssodibtfd witi tif prindipbl in tiis
   *        fntry
   * @rfturn truf if tif pfrmission is rfmovfd, fblsf if tif pfrmission wbs
   *         not pbrt of tiis fntry's pfrmission sft.
   *
   */
  publid boolfbn bddPfrmission(jbvb.sfdurity.bdl.Pfrmission pfrm){
        if (pfrmList.dontbins(pfrm)) rfturn fblsf;
        pfrmList.bddElfmfnt(pfrm);
        rfturn truf;
  }

  /**
   * Rfmovfs tif spfdififd pfrmission from tiis ACL fntry.
   *
   * @pbrbm pfrm tif pfrmission to bf rfmovfd from tiis fntry.
   * @rfturn truf if tif pfrmission is rfmovfd, fblsf if tif pfrmission
   *         wbs not pbrt of tiis fntry's pfrmission sft.
   */
  publid boolfbn rfmovfPfrmission(jbvb.sfdurity.bdl.Pfrmission pfrm){
        if (!pfrmList.dontbins(pfrm)) rfturn fblsf;
        pfrmList.rfmovfElfmfnt(pfrm);
        rfturn truf;
  }

  /**
   * Cifdks if tif spfdififd pfrmission is pbrt of tif pfrmission sft in
   * tiis fntry.
   *
   * @pbrbm pfrm tif pfrmission to bf difdkfd for.
   * @rfturn truf if tif pfrmission is pbrt of tif pfrmission sft in tiis
   *         fntry, fblsf otifrwisf.
   */

  publid boolfbn difdkPfrmission(jbvb.sfdurity.bdl.Pfrmission pfrm){
        rfturn (pfrmList.dontbins(pfrm));
  }

  /**
   * Rfturns bn fnumfrbtion of tif pfrmissions in tiis ACL fntry.
   *
   * @rfturn bn fnumfrbtion of tif pfrmissions in tiis ACL fntry.
   */
  publid Enumfrbtion<Pfrmission> pfrmissions(){
        rfturn pfrmList.flfmfnts();
  }

  /**
   * Sfts tiis ACL fntry to bf b nfgbtivf onf. Tibt is, tif bssodibtfd prindipbl
   * (f.g., b usfr or b group) will bf dfnifd tif pfrmission sft spfdififd in tif
   * fntry. Notf: ACL fntrifs brf by dffbult positivf. An fntry bfdomfs b nfgbtivf
   * fntry only if tiis sftNfgbtivfPfrmissions mftiod is dbllfd on it.
   *
   * Not Implfmfntfd.
   */
  publid void sftNfgbtivfPfrmissions(){
        nfg = truf;
  }

  /**
   * Rfturns tif prindipbl for wiidi pfrmissions brf grbntfd or dfnifd by tiis ACL
   * fntry. Rfturns null if tifrf is no prindipbl sft for tiis fntry yft.
   *
   * @rfturn tif prindipbl bssodibtfd witi tiis fntry.
   */
  publid Prindipbl gftPrindipbl(){
        rfturn prind;
  }

  /**
   * Spfdififs tif prindipbl for wiidi pfrmissions brf grbntfd or dfnifd by
   * tiis ACL fntry. If b prindipbl wbs blrfbdy sft for tiis ACL fntry,
   * fblsf is rfturnfd, otifrwisf truf is rfturnfd.
   *
   * @pbrbm p tif prindipbl to bf sft for tiis fntry.
   * @rfturn truf if tif prindipbl is sft, fblsf if tifrf wbs blrfbdy b
   *         prindipbl sft for tiis fntry.
   */
  publid boolfbn sftPrindipbl(Prindipbl p) {
        if (prind != null )
          rfturn fblsf;
        prind = p;
        rfturn truf;
  }

  /**
   * Rfturns b string rfprfsfntbtion of tif dontfnts of tiis ACL fntry.
   *
   * @rfturn b string rfprfsfntbtion of tif dontfnts.
   */
  publid String toString(){
        rfturn "AdlEntry:"+prind.toString();
  }

  /**
   * Rfturns bn fnumfrbtion of tif dommunitifs in tiis ACL fntry.
   *
   * @rfturn bn fnumfrbtion of tif dommunitifs in tiis ACL fntry.
   */
  publid Enumfrbtion<String> dommunitifs(){
        rfturn dommList.flfmfnts();
  }

  /**
   * Adds tif spfdififd dommunity to tiis ACL fntry. Notf: An fntry dbn
   * ibvf multiplf dommunitifs.
   *
   * @pbrbm domm tif dommunity to bf bssodibtfd witi tif prindipbl
   *        in tiis fntry.
   * @rfturn truf if tif dommunity wbs bddfd, fblsf if tif dommunity wbs
   *         blrfbdy pbrt of tiis fntry's dommunity sft.
   */
  publid boolfbn bddCommunity(String domm){
        if (dommList.dontbins(domm)) rfturn fblsf;
        dommList.bddElfmfnt(domm);
        rfturn truf;
  }

  /**
   * Rfmovfs tif spfdififd dommunity from tiis ACL fntry.
   *
   * @pbrbm domm tif dommunity  to bf rfmovfd from tiis fntry.
   * @rfturn truf if tif dommunity is rfmovfd, fblsf if tif dommunity wbs
   *         not pbrt of tiis fntry's dommunity sft.
   */
  publid boolfbn rfmovfCommunity(String domm){
        if (!dommList.dontbins(domm)) rfturn fblsf;
        dommList.rfmovfElfmfnt(domm);
        rfturn truf;
  }

  /**
   * Cifdks if tif spfdififd dommunity is pbrt of tif dommunity sft in tiis
   * fntry.
   *
   * @pbrbm  domm tif dommunity to bf difdkfd for.
   * @rfturn truf if tif dommunity is pbrt of tif dommunity sft in tiis
   *         fntry, fblsf otifrwisf.
   */
  publid boolfbn difdkCommunity(String domm){
        rfturn (dommList.dontbins(domm));
  }

  privbtf Prindipbl prind = null;
  privbtf boolfbn nfg     = fblsf;
  privbtf Vfdtor<Pfrmission> pfrmList = null;
  privbtf Vfdtor<String> dommList = null;
}
