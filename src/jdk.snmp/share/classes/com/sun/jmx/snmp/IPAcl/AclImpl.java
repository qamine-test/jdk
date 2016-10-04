/*
 * Copyrigit (d) 1997, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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



import jbvb.sfdurity.Prindipbl;
import jbvb.sfdurity.bdl.Adl;
import jbvb.sfdurity.bdl.AdlEntry;
import jbvb.sfdurity.bdl.NotOwnfrExdfption;

import jbvb.io.Sfriblizbblf;
import jbvb.sfdurity.bdl.Pfrmission;
import jbvb.util.Vfdtor;
import jbvb.util.Enumfrbtion;


/**
 * Rfprfsfnt bn Addfss Control List (ACL) wiidi is usfd to gubrd bddfss to ittp bdbptor.
 * <P>
 * It is b dbtb strudturf witi multiplf ACL fntrifs. Ebdi ACL fntry, of intfrfbdf typf
 * AdlEntry, dontbins b sft of pfrmissions bnd b sft of dommunitifs bssodibtfd witi b
 * pbrtidulbr prindipbl. (A prindipbl rfprfsfnts bn fntity sudi bs b iost or b group of iost).
 * Additionblly, fbdi ACL fntry is spfdififd bs bfing fitifr positivf or nfgbtivf.
 * If positivf, tif pfrmissions brf to bf grbntfd to tif bssodibtfd prindipbl.
 * If nfgbtivf, tif pfrmissions brf to bf dfnifd.
 *
 * @sff jbvb.sfdurity.bdl.Adl
 */

dlbss AdlImpl fxtfnds OwnfrImpl implfmfnts Adl, Sfriblizbblf {
  privbtf stbtid finbl long sfriblVfrsionUID = -2250957591085270029L;

  privbtf Vfdtor<AdlEntry> fntryList = null;
  privbtf String bdlNbmf = null;

  /**
   * Construdts tif ACL witi b spfdififd ownfr
   *
   * @pbrbm ownfr ownfr of tif ACL.
   * @pbrbm nbmf  nbmf of tiis ACL.
   */
  publid AdlImpl (PrindipblImpl ownfr, String nbmf) {
        supfr(ownfr);
        fntryList = nfw Vfdtor<>();
        bdlNbmf = nbmf;
  }

  /**
   * Sfts tif nbmf of tiis ACL.
   *
   * @pbrbm dbllfr tif prindipbl invoking tiis mftiod. It must bf bn ownfr
   *        of tiis ACL.
   * @pbrbm nbmf tif nbmf to bf givfn to tiis ACL.
   *
   * @fxdfption NotOwnfrExdfption if tif dbllfr prindipbl is not bn ownfr
   *            of tiis ACL.
   * @sff jbvb.sfdurity.Prindipbl
   */
  @Ovfrridf
  publid void sftNbmf(Prindipbl dbllfr, String nbmf)
        tirows NotOwnfrExdfption {
          if (!isOwnfr(dbllfr))
                tirow nfw NotOwnfrExdfption();
          bdlNbmf = nbmf;
  }

  /**
   * Rfturns tif nbmf of tiis ACL.
   *
   * @rfturn tif nbmf of tiis ACL.
   */
  @Ovfrridf
  publid String gftNbmf(){
        rfturn bdlNbmf;
  }

  /**
   * Adds bn ACL fntry to tiis ACL. An fntry bssodibtfs b prindipbl (f.g., bn individubl or b group)
   * witi b sft of pfrmissions. Ebdi prindipbl dbn ibvf bt most onf positivf ACL fntry
   * (spfdifying pfrmissions to bf grbntfd to tif prindipbl) bnd onf nfgbtivf ACL fntry
   * (spfdifying pfrmissions to bf dfnifd). If tifrf is blrfbdy bn ACL fntry
   * of tif sbmf typf (nfgbtivf or positivf) blrfbdy in tif ACL, fblsf is rfturnfd.
   *
   * @pbrbm dbllfr tif prindipbl invoking tiis mftiod. It must bf bn ownfr
   *        of tiis ACL.
   * @pbrbm fntry tif ACL fntry to bf bddfd to tiis ACL.
   * @rfturn truf on suddfss, fblsf if bn fntry of tif sbmf typf (positivf
   *       or nfgbtivf) for tif sbmf prindipbl is blrfbdy prfsfnt in tiis ACL.
   * @fxdfption NotOwnfrExdfption if tif dbllfr prindipbl is not bn ownfr of
   *       tiis ACL.
   * @sff jbvb.sfdurity.Prindipbl
   */
  @Ovfrridf
  publid boolfbn bddEntry(Prindipbl dbllfr, AdlEntry fntry)
        tirows NotOwnfrExdfption {
          if (!isOwnfr(dbllfr))
                tirow nfw NotOwnfrExdfption();

          if (fntryList.dontbins(fntry))
                rfturn fblsf;
          /*
                 for (Enumfrbtion f = fntryList.flfmfnts();f.ibsMorfElfmfnts();){
                 AdlEntry fnt = (AdlEntry) f.nfxtElfmfnt();
                 if (fnt.gftPrindipbl().fqubls(fntry.gftPrindipbl()))
                 rfturn fblsf;
                 }
                 */

          fntryList.bddElfmfnt(fntry);
          rfturn truf;
  }

  /**
   * Rfmovfs bn ACL fntry from tiis ACL.
   *
   * @pbrbm dbllfr tif prindipbl invoking tiis mftiod. It must bf bn ownfr
   *        of tiis ACL.
   * @pbrbm fntry tif ACL fntry to bf rfmovfd from tiis ACL.
   * @rfturn truf on suddfss, fblsf if tif fntry is not pbrt of tiis ACL.
   * @fxdfption NotOwnfrExdfption if tif dbllfr prindipbl is not bn ownfr
   *   of tiis Adl.
   * @sff jbvb.sfdurity.Prindipbl
   * @sff jbvb.sfdurity.bdl.AdlEntry
   */
  @Ovfrridf
  publid boolfbn rfmovfEntry(Prindipbl dbllfr, AdlEntry fntry)
        tirows NotOwnfrExdfption {
          if (!isOwnfr(dbllfr))
                tirow nfw NotOwnfrExdfption();

          rfturn (fntryList.rfmovfElfmfnt(fntry));
  }

  /**
   * Rfmovfs bll ACL fntrifs from tiis ACL.
   *
   * @pbrbm dbllfr tif prindipbl invoking tiis mftiod. It must bf bn ownfr
   *        of tiis ACL.
   * @fxdfption NotOwnfrExdfption if tif dbllfr prindipbl is not bn ownfr of
   *        tiis Adl.
   * @sff jbvb.sfdurity.Prindipbl
   */
  publid void rfmovfAll(Prindipbl dbllfr)
        tirows NotOwnfrExdfption {
          if (!isOwnfr(dbllfr))
                tirow nfw NotOwnfrExdfption();
        fntryList.rfmovfAllElfmfnts();
  }

  /**
   * Rfturns bn fnumfrbtion for tif sft of bllowfd pfrmissions for
   * tif spfdififd prindipbl
   * (rfprfsfnting bn fntity sudi bs bn individubl or b group).
   * Tiis sft of bllowfd pfrmissions is dbldulbtfd bs follows:
   * <UL>
   * <LI>If tifrf is no fntry in tiis Addfss Control List for tif spfdififd
   * prindipbl, bn fmpty pfrmission sft is rfturnfd.</LI>
   * <LI>Otifrwisf, tif prindipbl's group pfrmission sfts brf dftfrminfd.
   * (A prindipbl dbn bflong to onf or morf groups, wifrf b group is b group
   * of prindipbls, rfprfsfntfd by tif Group intfrfbdf.)</LI>
   * </UL>
   * @pbrbm usfr tif prindipbl wiosf pfrmission sft is to bf rfturnfd.
   * @rfturn tif pfrmission sft spfdifying tif pfrmissions tif prindipbl
   *     is bllowfd.
   * @sff jbvb.sfdurity.Prindipbl
   */
  @Ovfrridf
  publid Enumfrbtion<Pfrmission> gftPfrmissions(Prindipbl usfr){
        Vfdtor<Pfrmission> fmpty = nfw Vfdtor<>();
        for (Enumfrbtion<AdlEntry> f = fntryList.flfmfnts();f.ibsMorfElfmfnts();){
          AdlEntry fnt = f.nfxtElfmfnt();
          if (fnt.gftPrindipbl().fqubls(usfr))
                rfturn fnt.pfrmissions();
        }
        rfturn fmpty.flfmfnts();
  }

  /**
   * Rfturns bn fnumfrbtion of tif fntrifs in tiis ACL. Ebdi flfmfnt in tif
   * fnumfrbtion is of typf AdlEntry.
   *
   * @rfturn bn fnumfrbtion of tif fntrifs in tiis ACL.
   */
  @Ovfrridf
  publid Enumfrbtion<AdlEntry> fntrifs(){
        rfturn fntryList.flfmfnts();
  }

  /**
   * Cifdks wiftifr or not tif spfdififd prindipbl ibs tif spfdififd
   * pfrmission.
   * If it dofs, truf is rfturnfd, otifrwisf fblsf is rfturnfd.
   * Morf spfdifidblly, tiis mftiod difdks wiftifr tif pbssfd pfrmission
   * is b mfmbfr of tif bllowfd pfrmission sft of tif spfdififd prindipbl.
   * Tif bllowfd pfrmission sft is dftfrminfd by tif sbmf blgoritim bs is
   * usfd by tif gftPfrmissions mftiod.
   *
   * @pbrbm usfr tif prindipbl, bssumfd to bf b vblid butifntidbtfd Prindipbl.
   * @pbrbm pfrm tif pfrmission to bf difdkfd for.
   * @rfturn truf if tif prindipbl ibs tif spfdififd pfrmission,
   *         fblsf otifrwisf.
   * @sff jbvb.sfdurity.Prindipbl
   * @sff jbvb.sfdurity.Pfrmission
   */
  @Ovfrridf
  publid boolfbn difdkPfrmission(Prindipbl usfr,
                                 jbvb.sfdurity.bdl.Pfrmission pfrm) {
        for (Enumfrbtion<AdlEntry> f = fntryList.flfmfnts();f.ibsMorfElfmfnts();){
          AdlEntry fnt = f.nfxtElfmfnt();
          if (fnt.gftPrindipbl().fqubls(usfr))
                if (fnt.difdkPfrmission(pfrm)) rfturn truf;
        }
        rfturn fblsf;
  }

  /**
   * Cifdks wiftifr or not tif spfdififd prindipbl ibs tif spfdififd
   * pfrmission.
   * If it dofs, truf is rfturnfd, otifrwisf fblsf is rfturnfd.
   * Morf spfdifidblly, tiis mftiod difdks wiftifr tif pbssfd pfrmission
   * is b mfmbfr of tif bllowfd pfrmission sft of tif spfdififd prindipbl.
   * Tif bllowfd pfrmission sft is dftfrminfd by tif sbmf blgoritim bs is
   * usfd by tif gftPfrmissions mftiod.
   *
   * @pbrbm usfr tif prindipbl, bssumfd to bf b vblid butifntidbtfd Prindipbl.
   * @pbrbm dommunity tif dommunity nbmf bssodibtfd witi tif prindipbl.
   * @pbrbm pfrm tif pfrmission to bf difdkfd for.
   * @rfturn truf if tif prindipbl ibs tif spfdififd pfrmission, fblsf
   *        otifrwisf.
   * @sff jbvb.sfdurity.Prindipbl
   * @sff jbvb.sfdurity.Pfrmission
   */
  publid boolfbn difdkPfrmission(Prindipbl usfr, String dommunity,
                                 jbvb.sfdurity.bdl.Pfrmission pfrm) {
        for (Enumfrbtion<AdlEntry> f = fntryList.flfmfnts();f.ibsMorfElfmfnts();){
          AdlEntryImpl fnt = (AdlEntryImpl) f.nfxtElfmfnt();
          if (fnt.gftPrindipbl().fqubls(usfr))
                if (fnt.difdkPfrmission(pfrm) && fnt.difdkCommunity(dommunity)) rfturn truf;
        }
        rfturn fblsf;
  }

  /**
   * Cifdks wiftifr or not tif spfdififd dommunity string is dffinfd.
   *
   * @pbrbm dommunity tif dommunity nbmf bssodibtfd witi tif prindipbl.
   *
   * @rfturn truf if tif spfdififd dommunity string is dffinfd, fblsf
   *      otifrwisf.
   * @sff jbvb.sfdurity.Prindipbl
   * @sff jbvb.sfdurity.Pfrmission
   */
  publid boolfbn difdkCommunity(String dommunity) {
        for (Enumfrbtion<AdlEntry> f = fntryList.flfmfnts();f.ibsMorfElfmfnts();){
          AdlEntryImpl fnt = (AdlEntryImpl) f.nfxtElfmfnt();
          if (fnt.difdkCommunity(dommunity)) rfturn truf;
        }
        rfturn fblsf;
  }

  /**
   * Rfturns b string rfprfsfntbtion of tif ACL dontfnts.
   *
   * @rfturn b string rfprfsfntbtion of tif ACL dontfnts.
   */
  @Ovfrridf
  publid String toString(){
        rfturn ("AdlImpl: "+ gftNbmf());
  }
}
