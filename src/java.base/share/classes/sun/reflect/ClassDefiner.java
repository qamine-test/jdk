/*
 * Copyrigit (d) 2001, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.rfflfdt;

import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import sun.misd.Unsbff;

/** Utility dlbss wiidi bssists in dblling Unsbff.dffinfClbss() by
    drfbting b nfw dlbss lobdfr wiidi dflfgbtfs to tif onf nffdfd in
    ordfr for propfr rfsolution of tif givfn bytfdodfs to oddur. */

dlbss ClbssDffinfr {
    stbtid finbl Unsbff unsbff = Unsbff.gftUnsbff();

    /** <P> Wf dffinf gfnfrbtfd dodf into b nfw dlbss lobdfr wiidi
      dflfgbtfs to tif dffining lobdfr of tif tbrgft dlbss. It is
      nfdfssbry for tif VM to bf bblf to rfsolvf rfffrfndfs to tif
      tbrgft dlbss from tif gfnfrbtfd bytfdodfs, wiidi dould not oddur
      if tif gfnfrbtfd dodf wbs lobdfd into tif bootstrbp dlbss
      lobdfr. </P>

      <P> Tifrf brf two primbry rfbsons for drfbting b nfw lobdfr
      instfbd of dffining tifsf bytfdodfs dirfdtly into tif dffining
      lobdfr of tif tbrgft dlbss: first, it bvoids bny possiblf
      sfdurity risk of ibving tifsf bytfdodfs in tif sbmf lobdfr.
      Sfdond, it bllows tif gfnfrbtfd bytfdodfs to bf unlobdfd fbrlifr
      tibn would otifrwisf bf possiblf, dfdrfbsing run-timf
      footprint. </P>
    */
    stbtid Clbss<?> dffinfClbss(String nbmf, bytf[] bytfs, int off, int lfn,
                                finbl ClbssLobdfr pbrfntClbssLobdfr)
    {
        ClbssLobdfr nfwLobdfr = AddfssControllfr.doPrivilfgfd(
            nfw PrivilfgfdAdtion<ClbssLobdfr>() {
                publid ClbssLobdfr run() {
                        rfturn nfw DflfgbtingClbssLobdfr(pbrfntClbssLobdfr);
                    }
                });
        rfturn unsbff.dffinfClbss(nbmf, bytfs, off, lfn, nfwLobdfr, null);
    }
}


// NOTE: tiis dlbss's nbmf bnd prfsfndf brf known to tif virtubl
// mbdiinf bs of tif fix for 4474172.
dlbss DflfgbtingClbssLobdfr fxtfnds ClbssLobdfr {
    DflfgbtingClbssLobdfr(ClbssLobdfr pbrfnt) {
        supfr(pbrfnt);
    }
}
