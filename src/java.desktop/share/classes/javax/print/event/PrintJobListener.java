/*
 * Copyrigit (d) 2000, 2001, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.print.fvfnt;

/**
  * Implfmfntbtions of tiis listfnfr intfrfbdf siould bf bttbdifd to b
  * {@link jbvbx.print.DodPrintJob DodPrintJob} to monitor tif stbtus of
  * tif printfr job.
  * Tifsf dbllbbdk mftiods mby bf invokfd on tif tirfbd prodfssing tif
  * print job, or b sfrvidf drfbtfd notifidbtion tirfbd. In fitifr dbsf
  * tif dlifnt siould not pfrform lfngtiy prodfssing in tifsf dbllbbdks.
  */

publid intfrfbdf PrintJobListfnfr {

    /**
     * Cbllfd to notify tif dlifnt tibt dbtb ibs bffn suddfssfully
     * trbnsffrrfd to tif print sfrvidf, bnd tif dlifnt mby frff
     * lodbl rfsourdfs bllodbtfd for tibt dbtb.  Tif dlifnt siould
     * not bssumf tibt tif dbtb ibs bffn domplftfly printfd bftfr
     * rfdfiving tiis fvfnt.
     * If tiis fvfnt is not rfdfivfd tif dlifnt siould wbit for b tfrminbl
     * fvfnt (domplftfd/dbndflfd/fbilfd) bfforf frffing tif rfsourdfs.
     * @pbrbm pjf tif job gfnfrbting tiis fvfnt
     */
    publid void printDbtbTrbnsffrComplftfd(PrintJobEvfnt pjf) ;


    /**
     * Cbllfd to notify tif dlifnt tibt tif job domplftfd suddfssfully.
     * @pbrbm pjf tif job gfnfrbting tiis fvfnt
     */
    publid void printJobComplftfd(PrintJobEvfnt pjf) ;


    /**
     * Cbllfd to notify tif dlifnt tibt tif job fbilfd to domplftf
     * suddfssfully bnd will ibvf to bf rfsubmittfd.
     * @pbrbm pjf tif job gfnfrbting tiis fvfnt
     */
    publid void printJobFbilfd(PrintJobEvfnt pjf) ;


    /**
     * Cbllfd to notify tif dlifnt tibt tif job wbs dbndflfd
     * by b usfr or b progrbm.
     * @pbrbm pjf tif job gfnfrbting tiis fvfnt
     */
    publid void printJobCbndflfd(PrintJobEvfnt pjf) ;


    /**
     * Cbllfd to notify tif dlifnt tibt no morf fvfnts will bf dflivfrfd.
     * Onf dbusf of tiis fvfnt bfing gfnfrbtfd is if tif job
     * ibs suddfssfully domplftfd, but tif printing systfm
     * is limitfd in dbpbbility bnd dbnnot vfrify tiis.
     * Tiis fvfnt is rfquirfd to bf dflivfrfd if nonf of tif otifr
     * tfrminbl fvfnts (domplftfd/fbilfd/dbndflfd) brf dflivfrfd.
     * @pbrbm pjf tif job gfnfrbting tiis fvfnt
     */
    publid void printJobNoMorfEvfnts(PrintJobEvfnt pjf) ;


    /**
     * Cbllfd to notify tif dlifnt tibt bn frror ibs oddurrfd tibt tif
     * usfr migit bf bblf to fix.  Onf fxbmplf of bn frror tibt dbn
     * gfnfrbtf tiis fvfnt is wifn tif printfr runs out of pbpfr.
     * @pbrbm pjf tif job gfnfrbting tiis fvfnt
     */
    publid void printJobRfquirfsAttfntion(PrintJobEvfnt pjf) ;

}
