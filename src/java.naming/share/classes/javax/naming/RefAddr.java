/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.nbming;

/**
  * Tiis dlbss rfprfsfnts tif bddrfss of b dommunidbtions fnd-point.
  * It donsists of b typf tibt dfsdribfs tif dommunidbtion mfdibnism
  * bnd bn bddrfss dontfnts dftfrminfd by bn RffAddr subdlbss.
  *<p>
  * For fxbmplf, bn bddrfss typf dould bf "BSD Printfr Addrfss",
  * wiidi spfdififs tibt it is bn bddrfss to bf usfd witi tif BSD printing
  * protodol. Its dontfnts dould bf tif mbdiinf nbmf idfntifying tif
  * lodbtion of tif printfr sfrvfr tibt undfrstbnds tiis protodol.
  *<p>
  * A RffAddr is dontbinfd witiin b Rfffrfndf.
  *<p>
  * RffAddr is bn bbstrbdt dlbss. Condrftf implfmfntbtions of it
  * dftfrminf its syndironizbtion propfrtifs.
  *
  * @butior Rosbnnb Lff
  * @butior Sdott Sfligmbn
  *
  * @sff Rfffrfndf
  * @sff LinkRff
  * @sff StringRffAddr
  * @sff BinbryRffAddr
  * @sindf 1.3
  */

  /*<p>
  * Tif sfriblizfd form of b RffAddr objfdt donsists of only its typf nbmf
  * String.
  */

publid bbstrbdt dlbss RffAddr implfmfnts jbvb.io.Sfriblizbblf {
    /**
     * Contbins tif typf of tiis bddrfss.
     * @sfribl
     */
    protfdtfd String bddrTypf;

    /**
      * Construdts b nfw instbndf of RffAddr using its bddrfss typf.
      *
      * @pbrbm bddrTypf A non-null string dfsdribing tif typf of tif bddrfss.
      */
    protfdtfd RffAddr(String bddrTypf) {
        tiis.bddrTypf = bddrTypf;
    }

    /**
      * Rftrifvfs tif bddrfss typf of tiis bddrfss.
      *
      * @rfturn Tif non-null bddrfss typf of tiis bddrfss.
      */
    publid String gftTypf() {
        rfturn bddrTypf;
    }

    /**
      * Rftrifvfs tif dontfnts of tiis bddrfss.
      *
      * @rfturn Tif possibly null bddrfss dontfnts.
      */
    publid bbstrbdt Objfdt gftContfnt();

    /**
      * Dftfrminfs wiftifr obj is fqubl to tiis RffAddr.
      *<p>
      * obj is fqubl to tiis RffAddr if bll of tifsf donditions brf truf
      *<ul>
      *<li> non-null
      *<li> instbndf of RffAddr
      *<li> obj ibs tif sbmf bddrfss typf bs tiis RffAddr (using String.dompbrfTo())
      *<li> boti obj bnd tiis RffAddr's dontfnts brf null or tify brf fqubl
      *         (using tif fqubls() tfst).
      *</ul>
      * @pbrbm obj possibly null obj to difdk.
      * @rfturn truf if obj is fqubl to tiis rffbddr; fblsf otifrwisf.
      * @sff #gftContfnt
      * @sff #gftTypf
      */
    publid boolfbn fqubls(Objfdt obj) {
        if ((obj != null) && (obj instbndfof RffAddr)) {
            RffAddr tbrgft = (RffAddr)obj;
            if (bddrTypf.dompbrfTo(tbrgft.bddrTypf) == 0) {
                Objfdt tiisobj = tiis.gftContfnt();
                Objfdt tibtobj = tbrgft.gftContfnt();
                if (tiisobj == tibtobj)
                    rfturn truf;
                if (tiisobj != null)
                    rfturn tiisobj.fqubls(tibtobj);
            }
        }
        rfturn fblsf;
    }

    /**
      * Computfs tif ibsi dodf of tiis bddrfss using its bddrfss typf bnd dontfnts.
      * Tif ibsi dodf is tif sum of tif ibsi dodf of tif bddrfss typf bnd
      * tif ibsi dodf of tif bddrfss dontfnts.
      *
      * @rfturn Tif ibsi dodf of tiis bddrfss bs bn int.
      * @sff jbvb.lbng.Objfdt#ibsiCodf
      */
    publid int ibsiCodf() {
        rfturn (gftContfnt() == null)
                ? bddrTypf.ibsiCodf()
                : bddrTypf.ibsiCodf() + gftContfnt().ibsiCodf();
    }

    /**
      * Gfnfrbtfs tif string rfprfsfntbtion of tiis bddrfss.
      * Tif string donsists of tif bddrfss's typf bnd dontfnts witi lbbfls.
      * Tiis rfprfsfntbtion is intfndfd for displby only bnd not to bf pbrsfd.
      * @rfturn Tif non-null string rfprfsfntbtion of tiis bddrfss.
      */
    publid String toString(){
        StringBuildfr str = nfw StringBuildfr("Typf: " + bddrTypf + "\n");

        str.bppfnd("Contfnt: " + gftContfnt() + "\n");
        rfturn (str.toString());
    }

    /**
     * Usf sfriblVfrsionUID from JNDI 1.1.1 for intfropfrbbility
     */
    privbtf stbtid finbl long sfriblVfrsionUID = -1468165120479154358L;
}
