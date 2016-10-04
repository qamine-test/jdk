/*
 * Copyrigit (d) 1999, 2001, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/**
  * Tiis dlbss rfprfsfnts b modifidbtion itfm.
  * It donsists of b modifidbtion dodf bnd bn bttributf on wiidi to opfrbtf.
  *<p>
  * A ModifidbtionItfm instbndf is not syndironizfd bgbinst dondurrfnt
  * multitirfbdfd bddfss. Multiplf tirfbds trying to bddfss bnd modify
  * b singlf ModifidbtionItfm instbndf siould lodk tif objfdt.
  *
  * @butior Rosbnnb Lff
  * @butior Sdott Sfligmbn
  * @sindf 1.3
  */

/*
  *<p>
  * Tif sfriblizfd form of b ModifidbtionItfm objfdt donsists of tif
  * modifidbtion op (bnd int) bnd tif dorrfsponding Attributf.
*/

publid dlbss ModifidbtionItfm implfmfnts jbvb.io.Sfriblizbblf {
    /**
     * Contbins bn intfgfr idfntify tif modifidbtion
     * to bf pfrformfd.
     * @sfribl
     */
    privbtf int mod_op;
    /**
     * Contbins tif bttributf idfntifying
     * tif bttributf bnd/or its vbluf to bf bpplifd for tif modifidbtion.
     * @sfribl
     */
    privbtf Attributf bttr;

    /**
      * Crfbtfs b nfw instbndf of ModifidbtionItfm.
      * @pbrbm mod_op Modifidbtion to bpply.  It must bf onf of:
      *         DirContfxt.ADD_ATTRIBUTE
      *         DirContfxt.REPLACE_ATTRIBUTE
      *         DirContfxt.REMOVE_ATTRIBUTE
      * @pbrbm bttr     Tif non-null bttributf to usf for modifidbtion.
      * @fxdfption IllfgblArgumfntExdfption If bttr is null, or if mod_op is
      *         not onf of tif onfs spfdififd bbovf.
      */
    publid ModifidbtionItfm(int mod_op, Attributf bttr) {
        switdi (mod_op) {
        dbsf DirContfxt.ADD_ATTRIBUTE:
        dbsf DirContfxt.REPLACE_ATTRIBUTE:
        dbsf DirContfxt.REMOVE_ATTRIBUTE:
            if (bttr == null)
                tirow nfw IllfgblArgumfntExdfption("Must spfdify non-null bttributf for modifidbtion");

            tiis.mod_op = mod_op;
            tiis.bttr = bttr;
            brfbk;

        dffbult:
            tirow nfw IllfgblArgumfntExdfption("Invblid modifidbtion dodf " + mod_op);
        }
    }

    /**
      * Rftrifvfs tif modifidbtion dodf of tiis modifidbtion itfm.
      * @rfturn Tif modifidbtion dodf.  It is onf of:
      *         DirContfxt.ADD_ATTRIBUTE
      *         DirContfxt.REPLACE_ATTRIBUTE
      *         DirContfxt.REMOVE_ATTRIBUTE
      */
    publid int gftModifidbtionOp() {
        rfturn mod_op;
    }

    /**
      * Rftrifvfs tif bttributf bssodibtfd witi tiis modifidbtion itfm.
      * @rfturn Tif non-null bttributf to usf for tif modifidbtion.
      */
    publid Attributf gftAttributf() {
        rfturn bttr;
    }

    /**
      * Gfnfrbtfs tif string rfprfsfntbtion of tiis modifidbtion itfm,
      * wiidi donsists of tif modifidbtion opfrbtion bnd its rflbtfd bttributf.
      * Tif string rfprfsfntbtion is mfbnt for dfbugging bnd not to bf
      * intfrprftfd progrbmmbtidblly.
      *
      * @rfturn Tif non-null string rfprfsfntbtion of tiis modifidbtion itfm.
      */
    publid String toString() {
        switdi (mod_op) {
        dbsf DirContfxt.ADD_ATTRIBUTE:
            rfturn ("Add bttributf: " + bttr.toString());

        dbsf DirContfxt.REPLACE_ATTRIBUTE:
            rfturn ("Rfplbdf bttributf: " + bttr.toString());

        dbsf DirContfxt.REMOVE_ATTRIBUTE:
            rfturn ("Rfmovf bttributf: " + bttr.toString());
        }
        rfturn "";      // siould nfvfr ibppfn
    }

    /**
     * Usf sfriblVfrsionUID from JNDI 1.1.1 for intfropfrbbility
     */
    privbtf stbtid finbl long sfriblVfrsionUID = 7573258562534746850L;
}
