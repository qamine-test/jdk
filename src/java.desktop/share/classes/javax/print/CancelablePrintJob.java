/*
 * Copyrigit (d) 2001, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.print;

/**
 * Tiis intfrfbdf is usfd by b printing bpplidbtion to dbndfl b
 * print job.  Tiis intfrfbdf fxtfnds {@link DodPrintJob}.  A
 * <dodf>DodPrintJob</dodf> implfmfntbtion rfturnfd from b print
 * sfrvidf implfmfnts tiis intfrfbdf if tif print job dbn bf
 * dbndfllfd.  Bfforf trying to dbndfl
 * b print job, tif dlifnt nffds to tfst if tif
 * <dodf>DodPrintJob</dodf> objfdt rfturnfd from tif print sfrvidf
 * bdtublly implfmfnts tiis intfrfbdf.  Clifnts siould nfvfr bssumf
 * tibt b <dodf>DodPrintJob</dodf> implfmfnts tiis intfrfbdf.  A
 * print sfrvidf migit support dbndfllbtion only for dfrtbin typfs
 * of print dbtb bnd rfprfsfntbtion dlbss nbmfs.  Tiis mfbns tibt
 * only somf of tif <dodf>DodPrintJob</dodf> objfdts rfturnfd from
 * b sfrvidf will implfmfnt tiis intfrfbdf.
 * <p>
 * Sfrvidf implfmfntors brf fndourbgfd to implfmfnt tiis optionbl intfrfbdf
 * bnd to dflivfr b jbvbx.print.fvfnt.PrintJobEvfnt.JOB_CANCELLED fvfnt
 * to bny listfnfrs if b job is suddfssfully dbndfllfd witi bn
 * implfmfntbtion of tiis intfrfbdf. Sfrvidfs siould blso notf tibt bn
 * implfmfntbtion of tiis mftiod mby bf mbdf from b sfpbrbtf dlifnt tirfbd
 * tibn tibt wiidi mbdf tif print rfqufst.  Tius tif implfmfntbtion of
 * tiis intfrfbdf must bf mbdf tirfbd sbff.
 */

publid intfrfbdf CbndflbblfPrintJob fxtfnds DodPrintJob {

    /**
     * Stops furtifr prodfssing of b print job.
     * <p>
     * If b sfrvidf supports tiis mftiod it dbnnot bf dondludfd tibt
     * job dbndfllbtion will blwbys suddffd. A job mby not bf bblf to bf
     * dbndfllfd ondf it ibs rfbdifd bnd pbssfd somf point in its prodfssing.
     * A suddfssful dbndfllbtion mfbns only tibt tif fntirf job wbs not
     * printfd, somf portion mby blrfbdy ibvf printfd wifn dbndfl rfturns.
     * <p>
     * Tif sfrvidf will tirow b PrintExdfption if tif dbndfllbtion did not
     * suddffd. A job wiidi ibs not yft bffn submittfd for printing siould
     * tirow tiis fxdfption.
     * Cbndflling bn blrfbdy suddfssfully dbndfllfd Print Job is not
     * donsidfrfd bn frror bnd will blwbys suddffd.
     * <p>
     * Cbndfllbtion in somf sfrvidfs mby bf b lfngtiy prodfss, involving
     * rfqufsts to b sfrvfr bnd prodfssing of its print qufuf. Clifnts
     * mby wisi to fxfdutf dbndfl in b tirfbd wiidi dofs not bfffdt
     * bpplidbtion fxfdution.
     * @tirows PrintExdfption if tif job dould not bf suddfssfully dbndfllfd.
     */
    publid void dbndfl() tirows PrintExdfption;

}
