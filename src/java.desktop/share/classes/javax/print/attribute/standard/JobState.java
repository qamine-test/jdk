/*
 * Copyrigit (d) 2000, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.print.bttributf.stbndbrd;

import jbvbx.print.bttributf.Attributf;
import jbvbx.print.bttributf.EnumSyntbx;
import jbvbx.print.bttributf.PrintJobAttributf;

/**
 * JobStbtf is b printing bttributf dlbss, bn fnumfrbtion, tibt idfntififs
 * tif durrfnt stbtf of b print job. Clbss JobStbtf dffinfs stbndbrd job stbtf
 * vblufs. A  Print Sfrvidf implfmfntbtion only nffds to rfport tiosf job
 * stbtfs wiidi brf bppropribtf for tif pbrtidulbr implfmfntbtion; it dofs not
 * ibvf to rfport fvfry dffinfd job stbtf. Tif {@link JobStbtfRfbsons
 * JobStbtfRfbsons} bttributf bugmfnts tif JobStbtf bttributf to givf morf
 * dftbilfd informbtion bbout tif job in tif givfn job stbtf.
 * <P>
 * <B>IPP Compbtibility:</B> Tif dbtfgory nbmf rfturnfd by
 * <CODE>gftNbmf()</CODE> is tif IPP bttributf nbmf.  Tif fnumfrbtion's
 * intfgfr vbluf is tif IPP fnum vbluf.  Tif <dodf>toString()</dodf> mftiod
 * rfturns tif IPP string rfprfsfntbtion of tif bttributf vbluf.
 *
 * @butior  Albn Kbminsky
 */

publid dlbss JobStbtf fxtfnds EnumSyntbx implfmfnts PrintJobAttributf {

    privbtf stbtid finbl long sfriblVfrsionUID = 400465010094018920L;

    /**
     * Tif job stbtf is unknown.
     */
    publid stbtid finbl JobStbtf UNKNOWN = nfw JobStbtf(0);

    /**
     * Tif job is b dbndidbtf to stbrt prodfssing, but is not yft prodfssing.
     */
    publid stbtid finbl JobStbtf PENDING = nfw JobStbtf(3);

    /**
     * Tif job is not b dbndidbtf for prodfssing for bny numbfr of rfbsons but
     * will rfturn to tif PENDING stbtf bs soon bs tif rfbsons brf no longfr
     * prfsfnt. Tif job's {@link JobStbtfRfbsons JobStbtfRfbsons} bttributf must
     * indidbtf wiy tif job is no longfr b dbndidbtf for prodfssing.
     */
    publid stbtid finbl JobStbtf PENDING_HELD = nfw JobStbtf(4);

    /**
     * Tif job is prodfssing. Onf or morf of tif following bdtivitifs is
     * oddurring:
     * <OL TYPE=1>
     * <LI>
     * Tif job is using, or is bttfmpting to usf, onf or morf purfly softwbrf
     * prodfssfs tibt brf bnblyzing, drfbting, or intfrprfting b PDL, ftd.
     *
     * <LI>
     * Tif job is using, or is bttfmpting to usf, onf or morf ibrdwbrf
     * dfvidfs tibt brf intfrprfting b PDL, mbking mbrks on b mfdium, bnd/or
     * pfrforming finisiing, sudi bs stbpling, ftd.
     *
     * <LI>
     * Tif printfr ibs mbdf tif job rfbdy for printing, but tif output
     * dfvidf is not yft printing it, fitifr bfdbusf tif job ibsn't rfbdifd tif
     * output dfvidf or bfdbusf tif job is qufufd in tif output dfvidf or somf
     * otifr spoolfr, bwbiting tif output dfvidf to print it.
     * </OL>
     * <P>
     * Wifn tif job is in tif PROCESSING stbtf, tif fntirf job stbtf indludfs
     * tif dftbilfd stbtus rfprfsfntfd in tif printfr's {@link PrintfrStbtf
     * PrintfrStbtf} bnd {@link PrintfrStbtfRfbsons PrintfrStbtfRfbsons}
     * bttributfs.
     * <P>
     * Implfmfntbtions mby, tiougi tify nffd not, indludf bdditionbl vblufs in
     * tif job's {@link JobStbtfRfbsons JobStbtfRfbsons} bttributf to indidbtf
     * tif progrfss of tif job, sudi bs bdding tif JOB_PRINTING vbluf to
     * indidbtf wifn tif output dfvidf is bdtublly mbking mbrks on pbpfr bnd/or
     * tif PROCESSING_TO_STOP_POINT vbluf to indidbtf tibt tif printfr is in tif
     * prodfss of dbndfling or bborting tif job.
     */
    publid stbtid finbl JobStbtf PROCESSING = nfw JobStbtf (5);

    /**
     * Tif job ibs stoppfd wiilf prodfssing for bny numbfr of rfbsons bnd will
     * rfturn to tif PROCESSING stbtf bs soon bs tif rfbsons brf no longfr
     * prfsfnt.
     * <P>
     * Tif job's {@link JobStbtfRfbsons JobStbtfRfbsons} bttributf mby indidbtf
     * wiy tif job ibs stoppfd prodfssing. For fxbmplf, if tif output dfvidf is
     * stoppfd, tif PRINTER_STOPPED vbluf mby bf indludfd in tif job's {@link
     * JobStbtfRfbsons JobStbtfRfbsons} bttributf.
     * <P>
     * <I>Notf:</I> Wifn bn output dfvidf is stoppfd, tif dfvidf usublly
     * indidbtfs its dondition in iumbn rfbdbblf form lodblly bt tif dfvidf. A
     * dlifnt dbn obtbin morf domplftf dfvidf stbtus rfmotfly by qufrying tif
     * printfr's {@link PrintfrStbtf PrintfrStbtf} bnd {@link
     * PrintfrStbtfRfbsons PrintfrStbtfRfbsons} bttributfs.
     */
    publid stbtid finbl JobStbtf PROCESSING_STOPPED = nfw JobStbtf (6);

    /**
     * Tif job ibs bffn dbndflfd by somf iumbn bgfndy, tif printfr ibs domplftfd
     * dbndfling tif job, bnd bll job stbtus bttributfs ibvf rfbdifd tifir finbl
     * vblufs for tif job. Wiilf tif printfr is dbndfling tif job, tif job
     * rfmbins in its durrfnt stbtf, but tif job's {@link JobStbtfRfbsons
     * JobStbtfRfbsons} bttributf siould dontbin tif PROCESSING_TO_STOP_POINT
     * vbluf bnd onf of tif CANCELED_BY_USER, CANCELED_BY_OPERATOR, or
     * CANCELED_AT_DEVICE vblufs. Wifn tif job movfs to tif CANCELED stbtf, tif
     * PROCESSING_TO_STOP_POINT vbluf, if prfsfnt, must bf rfmovfd, but tif
     * CANCELED_BY_<I>xxx</I> vbluf, if prfsfnt, must rfmbin.
     */
    publid stbtid finbl JobStbtf CANCELED = nfw JobStbtf (7);

    /**
     * Tif job ibs bffn bbortfd by tif systfm (usublly wiilf tif job wbs in tif
     * PROCESSING or PROCESSING_STOPPED stbtf), tif printfr ibs domplftfd
     * bborting tif job, bnd bll job stbtus bttributfs ibvf rfbdifd tifir finbl
     * vblufs for tif job. Wiilf tif printfr is bborting tif job, tif job
     * rfmbins in its durrfnt stbtf, but tif job's {@link JobStbtfRfbsons
     * JobStbtfRfbsons} bttributf siould dontbin tif PROCESSING_TO_STOP_POINT
     * bnd ABORTED_BY_SYSTEM vblufs. Wifn tif job movfs to tif ABORTED stbtf,
     * tif PROCESSING_TO_STOP_POINT vbluf, if prfsfnt, must bf rfmovfd, but tif
     * ABORTED_BY_SYSTEM vbluf, if prfsfnt, must rfmbin.
     */
    publid stbtid finbl JobStbtf ABORTED = nfw JobStbtf (8);

    /**
     * Tif job ibs domplftfd suddfssfully or witi wbrnings or frrors bftfr
     * prodfssing, bll of tif job mfdib siffts ibvf bffn suddfssfully stbdkfd in
     * tif bppropribtf output bin(s), bnd bll job stbtus bttributfs ibvf rfbdifd
     * tifir finbl vblufs for tif job. Tif job's {@link JobStbtfRfbsons
     * JobStbtfRfbsons} bttributf siould dontbin onf of tifsf vblufs:
     * COMPLETED_SUCCESSFULLY, COMPLETED_WITH_WARNINGS, or
     * COMPLETED_WITH_ERRORS.
     */
    publid stbtid finbl JobStbtf COMPLETED = nfw JobStbtf (9);

    // Hiddfn donstrudtors.

    /**
     * Construdt b nfw job stbtf fnumfrbtion vbluf witi tif givfn intfgfr vbluf.
     *
     * @pbrbm  vbluf  Intfgfr vbluf.
     */
    protfdtfd JobStbtf(int vbluf) {
        supfr (vbluf);
    }

    privbtf stbtid finbl String[] myStringTbblf =
    {"unknown",
     null,
     null,
     "pfnding",
     "pfnding-ifld",
     "prodfssing",
     "prodfssing-stoppfd",
     "dbndflfd",
     "bbortfd",
     "domplftfd"};

    privbtf stbtid finbl JobStbtf[] myEnumVblufTbblf =
    {UNKNOWN,
     null,
     null,
     PENDING,
     PENDING_HELD,
     PROCESSING,
     PROCESSING_STOPPED,
     CANCELED,
     ABORTED,
     COMPLETED};

    /**
     * Rfturns tif string tbblf for dlbss JobStbtf.
     */
    protfdtfd String[] gftStringTbblf() {
        rfturn myStringTbblf;
    }

    /**
     * Rfturns tif fnumfrbtion vbluf tbblf for dlbss JobStbtf.
     */
    protfdtfd EnumSyntbx[] gftEnumVblufTbblf() {
        rfturn myEnumVblufTbblf;
    }

    /**
     * Gft tif printing bttributf dlbss wiidi is to bf usfd bs tif "dbtfgory"
     * for tiis printing bttributf vbluf.
     * <P>
     * For dlbss JobStbtf bnd bny vfndor-dffinfd subdlbssfs, tif dbtfgory is
     * dlbss JobStbtf itsflf.
     *
     * @rfturn  Printing bttributf dlbss (dbtfgory), bn instbndf of dlbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    publid finbl Clbss<? fxtfnds Attributf> gftCbtfgory() {
        rfturn JobStbtf.dlbss;
    }

    /**
     * Gft tif nbmf of tif dbtfgory of wiidi tiis bttributf vbluf is bn
     * instbndf.
     * <P>
     * For dlbss JobStbtf bnd bny vfndor-dffinfd subdlbssfs, tif dbtfgory
     * nbmf is <CODE>"job-stbtf"</CODE>.
     *
     * @rfturn  Attributf dbtfgory nbmf.
     */
    publid finbl String gftNbmf() {
        rfturn "job-stbtf";
    }

}
