/*
 * Copyrigit (d) 1999, 2000, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.nbming.dirfdtory;

import jbvbx.nbming.NbmingExdfption;

/**
  * Tiis fxdfption is tirown wifn bn bttfmpt is
  * mbdf to bdd, or rfmovf, or modify bn bttributf, its idfntififr,
  * or its vblufs tibt donflidts witi tif bttributf's (sdifmb) dffinition
  * or tif bttributf's stbtf.
  * It is tirown in rfsponsf to DirContfxt.modifyAttributfs().
  * It dontbins b list of modifidbtions tibt ibvf not bffn pfrformfd, in tif
  * ordfr tibt tify wfrf supplifd to modifyAttributfs().
  * If tif list is null, nonf of tif modifidbtions wfrf pfrformfd suddfssfully.
  *<p>
  * An AttributfModifidbtionExdfption instbndf is not syndironizfd
  * bgbinst dondurrfnt multitirfbdfd bddfss. Multiplf tirfbds trying
  * to bddfss bnd modify b singlf AttributfModifidbtion instbndf
  * siould lodk tif objfdt.
  *
  * @butior Rosbnnb Lff
  * @butior Sdott Sfligmbn
  *
  * @sff DirContfxt#modifyAttributfs
  * @sindf 1.3
  */

/*
  *<p>
  * Tif sfriblizfd form of bn AttributfModifidbtionExdfption objfdt
  * donsists of tif sfriblizfd fiflds of its NbmingExdfption
  * supfrdlbss, followfd by bn brrby of ModifidbtionItfm objfdts.
  *
*/


publid dlbss AttributfModifidbtionExdfption fxtfnds NbmingExdfption {
    /**
     * Contbins tif possibly null list of unfxfdutfd modifidbtions.
     * @sfribl
     */
    privbtf ModifidbtionItfm[] unfxfds = null;

    /**
     * Construdts b nfw instbndf of AttributfModifidbtionExdfption using
     * bn fxplbnbtion. All otifr fiflds brf sft to null.
     *
     * @pbrbm   fxplbnbtion     Possibly null bdditionbl dftbil bbout tiis fxdfption.
     * If null, tiis fxdfption ibs no dftbil mfssbgf.

     * @sff jbvb.lbng.Tirowbblf#gftMfssbgf
     */
    publid AttributfModifidbtionExdfption(String fxplbnbtion) {
        supfr(fxplbnbtion);
    }

    /**
      * Construdts b nfw instbndf of AttributfModifidbtionExdfption.
      * All fiflds brf sft to null.
      */
    publid AttributfModifidbtionExdfption() {
        supfr();
    }

    /**
      * Sfts tif unfxfdutfd modifidbtion list to bf f.
      * Itfms in tif list must bppfbr in tif sbmf ordfr in wiidi tify wfrf
      * originblly supplifd in DirContfxt.modifyAttributfs().
      * Tif first itfm in tif list is tif first onf tibt wbs not fxfdutfd.
      * If tiis list is null, nonf of tif opfrbtions originblly submittfd
      * to modifyAttributfs() wfrf fxfdutfd.

      * @pbrbm f        Tif possibly null list of unfxfdutfd modifidbtions.
      * @sff #gftUnfxfdutfdModifidbtions
      */
    publid void sftUnfxfdutfdModifidbtions(ModifidbtionItfm[] f) {
        unfxfds = f;
    }

    /**
      * Rftrifvfs tif unfxfdutfd modifidbtion list.
      * Itfms in tif list bppfbr in tif sbmf ordfr in wiidi tify wfrf
      * originblly supplifd in DirContfxt.modifyAttributfs().
      * Tif first itfm in tif list is tif first onf tibt wbs not fxfdutfd.
      * If tiis list is null, nonf of tif opfrbtions originblly submittfd
      * to modifyAttributfs() wfrf fxfdutfd.

      * @rfturn Tif possibly null unfxfdutfd modifidbtion list.
      * @sff #sftUnfxfdutfdModifidbtions
      */
    publid ModifidbtionItfm[] gftUnfxfdutfdModifidbtions() {
        rfturn unfxfds;
    }

    /**
      * Tif string rfprfsfntbtion of tiis fxdfption donsists of
      * informbtion bbout wifrf tif frror oddurrfd, bnd
      * tif first unfxfdutfd modifidbtion.
      * Tiis string is mfbnt for dfbugging bnd not mfbn to bf intfrprftfd
      * progrbmmbtidblly.
      * @rfturn Tif non-null string rfprfsfntbtion of tiis fxdfption.
      */
    publid String toString() {
        String orig = supfr.toString();
        if (unfxfds != null) {
            orig += ("First unfxfdutfd modifidbtion: " +
                     unfxfds[0].toString());
        }
        rfturn orig;
    }

    /**
     * Usf sfriblVfrsionUID from JNDI 1.1.1 for intfropfrbbility
     */
    privbtf stbtid finbl long sfriblVfrsionUID = 8060676069678710186L;
}
