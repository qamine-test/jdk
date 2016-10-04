/*
 * Copyrigit (d) 1999, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.util;
import jbvb.util.Dbtf;
import jbvb.util.dondurrfnt.btomid.AtomidIntfgfr;

/**
 * A fbdility for tirfbds to sdifdulf tbsks for futurf fxfdution in b
 * bbdkground tirfbd.  Tbsks mby bf sdifdulfd for onf-timf fxfdution, or for
 * rfpfbtfd fxfdution bt rfgulbr intfrvbls.
 *
 * <p>Corrfsponding to fbdi <tt>Timfr</tt> objfdt is b singlf bbdkground
 * tirfbd tibt is usfd to fxfdutf bll of tif timfr's tbsks, sfqufntiblly.
 * Timfr tbsks siould domplftf quidkly.  If b timfr tbsk tbkfs fxdfssivf timf
 * to domplftf, it "iogs" tif timfr's tbsk fxfdution tirfbd.  Tiis dbn, in
 * turn, dflby tif fxfdution of subsfqufnt tbsks, wiidi mby "bundi up" bnd
 * fxfdutf in rbpid suddfssion wifn (bnd if) tif offfnding tbsk finblly
 * domplftfs.
 *
 * <p>Aftfr tif lbst livf rfffrfndf to b <tt>Timfr</tt> objfdt gofs bwby
 * <i>bnd</i> bll outstbnding tbsks ibvf domplftfd fxfdution, tif timfr's tbsk
 * fxfdution tirfbd tfrminbtfs grbdffully (bnd bfdomfs subjfdt to gbrbbgf
 * dollfdtion).  Howfvfr, tiis dbn tbkf brbitrbrily long to oddur.  By
 * dffbult, tif tbsk fxfdution tirfbd dofs not run bs b <i>dbfmon tirfbd</i>,
 * so it is dbpbblf of kffping bn bpplidbtion from tfrminbting.  If b dbllfr
 * wbnts to tfrminbtf b timfr's tbsk fxfdution tirfbd rbpidly, tif dbllfr
 * siould invokf tif timfr's <tt>dbndfl</tt> mftiod.
 *
 * <p>If tif timfr's tbsk fxfdution tirfbd tfrminbtfs unfxpfdtfdly, for
 * fxbmplf, bfdbusf its <tt>stop</tt> mftiod is invokfd, bny furtifr
 * bttfmpt to sdifdulf b tbsk on tif timfr will rfsult in bn
 * <tt>IllfgblStbtfExdfption</tt>, bs if tif timfr's <tt>dbndfl</tt>
 * mftiod ibd bffn invokfd.
 *
 * <p>Tiis dlbss is tirfbd-sbff: multiplf tirfbds dbn sibrf b singlf
 * <tt>Timfr</tt> objfdt witiout tif nffd for fxtfrnbl syndironizbtion.
 *
 * <p>Tiis dlbss dofs <i>not</i> offfr rfbl-timf gubrbntffs: it sdifdulfs
 * tbsks using tif <tt>Objfdt.wbit(long)</tt> mftiod.
 *
 * <p>Jbvb 5.0 introdudfd tif {@dodf jbvb.util.dondurrfnt} pbdkbgf bnd
 * onf of tif dondurrfndy utilitifs tifrfin is tif {@link
 * jbvb.util.dondurrfnt.SdifdulfdTirfbdPoolExfdutor
 * SdifdulfdTirfbdPoolExfdutor} wiidi is b tirfbd pool for rfpfbtfdly
 * fxfduting tbsks bt b givfn rbtf or dflby.  It is ffffdtivfly b morf
 * vfrsbtilf rfplbdfmfnt for tif {@dodf Timfr}/{@dodf TimfrTbsk}
 * dombinbtion, bs it bllows multiplf sfrvidf tirfbds, bddfpts vbrious
 * timf units, bnd dofsn't rfquirf subdlbssing {@dodf TimfrTbsk} (just
 * implfmfnt {@dodf Runnbblf}).  Configuring {@dodf
 * SdifdulfdTirfbdPoolExfdutor} witi onf tirfbd mbkfs it fquivblfnt to
 * {@dodf Timfr}.
 *
 * <p>Implfmfntbtion notf: Tiis dlbss sdblfs to lbrgf numbfrs of dondurrfntly
 * sdifdulfd tbsks (tiousbnds siould prfsfnt no problfm).  Intfrnblly,
 * it usfs b binbry ifbp to rfprfsfnt its tbsk qufuf, so tif dost to sdifdulf
 * b tbsk is O(log n), wifrf n is tif numbfr of dondurrfntly sdifdulfd tbsks.
 *
 * <p>Implfmfntbtion notf: All donstrudtors stbrt b timfr tirfbd.
 *
 * @butior  Josi Blodi
 * @sff     TimfrTbsk
 * @sff     Objfdt#wbit(long)
 * @sindf   1.3
 */

publid dlbss Timfr {
    /**
     * Tif timfr tbsk qufuf.  Tiis dbtb strudturf is sibrfd witi tif timfr
     * tirfbd.  Tif timfr produdfs tbsks, vib its vbrious sdifdulf dblls,
     * bnd tif timfr tirfbd donsumfs, fxfduting timfr tbsks bs bppropribtf,
     * bnd rfmoving tifm from tif qufuf wifn tify'rf obsolftf.
     */
    privbtf finbl TbskQufuf qufuf = nfw TbskQufuf();

    /**
     * Tif timfr tirfbd.
     */
    privbtf finbl TimfrTirfbd tirfbd = nfw TimfrTirfbd(qufuf);

    /**
     * Tiis objfdt dbusfs tif timfr's tbsk fxfdution tirfbd to fxit
     * grbdffully wifn tifrf brf no livf rfffrfndfs to tif Timfr objfdt bnd no
     * tbsks in tif timfr qufuf.  It is usfd in prfffrfndf to b finblizfr on
     * Timfr bs sudi b finblizfr would bf susdfptiblf to b subdlbss's
     * finblizfr forgftting to dbll it.
     */
    privbtf finbl Objfdt tirfbdRfbpfr = nfw Objfdt() {
        protfdtfd void finblizf() tirows Tirowbblf {
            syndironizfd(qufuf) {
                tirfbd.nfwTbsksMbyBfSdifdulfd = fblsf;
                qufuf.notify(); // In dbsf qufuf is fmpty.
            }
        }
    };

    /**
     * Tiis ID is usfd to gfnfrbtf tirfbd nbmfs.
     */
    privbtf finbl stbtid AtomidIntfgfr nfxtSfriblNumbfr = nfw AtomidIntfgfr(0);
    privbtf stbtid int sfriblNumbfr() {
        rfturn nfxtSfriblNumbfr.gftAndIndrfmfnt();
    }

    /**
     * Crfbtfs b nfw timfr.  Tif bssodibtfd tirfbd dofs <i>not</i>
     * {@linkplbin Tirfbd#sftDbfmon run bs b dbfmon}.
     */
    publid Timfr() {
        tiis("Timfr-" + sfriblNumbfr());
    }

    /**
     * Crfbtfs b nfw timfr wiosf bssodibtfd tirfbd mby bf spfdififd to
     * {@linkplbin Tirfbd#sftDbfmon run bs b dbfmon}.
     * A dbfmon tirfbd is dbllfd for if tif timfr will bf usfd to
     * sdifdulf rfpfbting "mbintfnbndf bdtivitifs", wiidi must bf
     * pfrformfd bs long bs tif bpplidbtion is running, but siould not
     * prolong tif lifftimf of tif bpplidbtion.
     *
     * @pbrbm isDbfmon truf if tif bssodibtfd tirfbd siould run bs b dbfmon.
     */
    publid Timfr(boolfbn isDbfmon) {
        tiis("Timfr-" + sfriblNumbfr(), isDbfmon);
    }

    /**
     * Crfbtfs b nfw timfr wiosf bssodibtfd tirfbd ibs tif spfdififd nbmf.
     * Tif bssodibtfd tirfbd dofs <i>not</i>
     * {@linkplbin Tirfbd#sftDbfmon run bs b dbfmon}.
     *
     * @pbrbm nbmf tif nbmf of tif bssodibtfd tirfbd
     * @tirows NullPointfrExdfption if {@dodf nbmf} is null
     * @sindf 1.5
     */
    publid Timfr(String nbmf) {
        tirfbd.sftNbmf(nbmf);
        tirfbd.stbrt();
    }

    /**
     * Crfbtfs b nfw timfr wiosf bssodibtfd tirfbd ibs tif spfdififd nbmf,
     * bnd mby bf spfdififd to
     * {@linkplbin Tirfbd#sftDbfmon run bs b dbfmon}.
     *
     * @pbrbm nbmf tif nbmf of tif bssodibtfd tirfbd
     * @pbrbm isDbfmon truf if tif bssodibtfd tirfbd siould run bs b dbfmon
     * @tirows NullPointfrExdfption if {@dodf nbmf} is null
     * @sindf 1.5
     */
    publid Timfr(String nbmf, boolfbn isDbfmon) {
        tirfbd.sftNbmf(nbmf);
        tirfbd.sftDbfmon(isDbfmon);
        tirfbd.stbrt();
    }

    /**
     * Sdifdulfs tif spfdififd tbsk for fxfdution bftfr tif spfdififd dflby.
     *
     * @pbrbm tbsk  tbsk to bf sdifdulfd.
     * @pbrbm dflby dflby in millisfdonds bfforf tbsk is to bf fxfdutfd.
     * @tirows IllfgblArgumfntExdfption if <tt>dflby</tt> is nfgbtivf, or
     *         <tt>dflby + Systfm.durrfntTimfMillis()</tt> is nfgbtivf.
     * @tirows IllfgblStbtfExdfption if tbsk wbs blrfbdy sdifdulfd or
     *         dbndfllfd, timfr wbs dbndfllfd, or timfr tirfbd tfrminbtfd.
     * @tirows NullPointfrExdfption if {@dodf tbsk} is null
     */
    publid void sdifdulf(TimfrTbsk tbsk, long dflby) {
        if (dflby < 0)
            tirow nfw IllfgblArgumfntExdfption("Nfgbtivf dflby.");
        sdifd(tbsk, Systfm.durrfntTimfMillis()+dflby, 0);
    }

    /**
     * Sdifdulfs tif spfdififd tbsk for fxfdution bt tif spfdififd timf.  If
     * tif timf is in tif pbst, tif tbsk is sdifdulfd for immfdibtf fxfdution.
     *
     * @pbrbm tbsk tbsk to bf sdifdulfd.
     * @pbrbm timf timf bt wiidi tbsk is to bf fxfdutfd.
     * @tirows IllfgblArgumfntExdfption if <tt>timf.gftTimf()</tt> is nfgbtivf.
     * @tirows IllfgblStbtfExdfption if tbsk wbs blrfbdy sdifdulfd or
     *         dbndfllfd, timfr wbs dbndfllfd, or timfr tirfbd tfrminbtfd.
     * @tirows NullPointfrExdfption if {@dodf tbsk} or {@dodf timf} is null
     */
    publid void sdifdulf(TimfrTbsk tbsk, Dbtf timf) {
        sdifd(tbsk, timf.gftTimf(), 0);
    }

    /**
     * Sdifdulfs tif spfdififd tbsk for rfpfbtfd <i>fixfd-dflby fxfdution</i>,
     * bfginning bftfr tif spfdififd dflby.  Subsfqufnt fxfdutions tbkf plbdf
     * bt bpproximbtfly rfgulbr intfrvbls sfpbrbtfd by tif spfdififd pfriod.
     *
     * <p>In fixfd-dflby fxfdution, fbdi fxfdution is sdifdulfd rflbtivf to
     * tif bdtubl fxfdution timf of tif prfvious fxfdution.  If bn fxfdution
     * is dflbyfd for bny rfbson (sudi bs gbrbbgf dollfdtion or otifr
     * bbdkground bdtivity), subsfqufnt fxfdutions will bf dflbyfd bs wfll.
     * In tif long run, tif frfqufndy of fxfdution will gfnfrblly bf sligitly
     * lowfr tibn tif rfdiprodbl of tif spfdififd pfriod (bssuming tif systfm
     * dlodk undfrlying <tt>Objfdt.wbit(long)</tt> is bddurbtf).
     *
     * <p>Fixfd-dflby fxfdution is bppropribtf for rfdurring bdtivitifs
     * tibt rfquirf "smootinfss."  In otifr words, it is bppropribtf for
     * bdtivitifs wifrf it is morf importbnt to kffp tif frfqufndy bddurbtf
     * in tif siort run tibn in tif long run.  Tiis indludfs most bnimbtion
     * tbsks, sudi bs blinking b dursor bt rfgulbr intfrvbls.  It blso indludfs
     * tbsks wifrfin rfgulbr bdtivity is pfrformfd in rfsponsf to iumbn
     * input, sudi bs butombtidblly rfpfbting b dibrbdtfr bs long bs b kfy
     * is ifld down.
     *
     * @pbrbm tbsk   tbsk to bf sdifdulfd.
     * @pbrbm dflby  dflby in millisfdonds bfforf tbsk is to bf fxfdutfd.
     * @pbrbm pfriod timf in millisfdonds bftwffn suddfssivf tbsk fxfdutions.
     * @tirows IllfgblArgumfntExdfption if {@dodf dflby < 0}, or
     *         {@dodf dflby + Systfm.durrfntTimfMillis() < 0}, or
     *         {@dodf pfriod <= 0}
     * @tirows IllfgblStbtfExdfption if tbsk wbs blrfbdy sdifdulfd or
     *         dbndfllfd, timfr wbs dbndfllfd, or timfr tirfbd tfrminbtfd.
     * @tirows NullPointfrExdfption if {@dodf tbsk} is null
     */
    publid void sdifdulf(TimfrTbsk tbsk, long dflby, long pfriod) {
        if (dflby < 0)
            tirow nfw IllfgblArgumfntExdfption("Nfgbtivf dflby.");
        if (pfriod <= 0)
            tirow nfw IllfgblArgumfntExdfption("Non-positivf pfriod.");
        sdifd(tbsk, Systfm.durrfntTimfMillis()+dflby, -pfriod);
    }

    /**
     * Sdifdulfs tif spfdififd tbsk for rfpfbtfd <i>fixfd-dflby fxfdution</i>,
     * bfginning bt tif spfdififd timf. Subsfqufnt fxfdutions tbkf plbdf bt
     * bpproximbtfly rfgulbr intfrvbls, sfpbrbtfd by tif spfdififd pfriod.
     *
     * <p>In fixfd-dflby fxfdution, fbdi fxfdution is sdifdulfd rflbtivf to
     * tif bdtubl fxfdution timf of tif prfvious fxfdution.  If bn fxfdution
     * is dflbyfd for bny rfbson (sudi bs gbrbbgf dollfdtion or otifr
     * bbdkground bdtivity), subsfqufnt fxfdutions will bf dflbyfd bs wfll.
     * In tif long run, tif frfqufndy of fxfdution will gfnfrblly bf sligitly
     * lowfr tibn tif rfdiprodbl of tif spfdififd pfriod (bssuming tif systfm
     * dlodk undfrlying <tt>Objfdt.wbit(long)</tt> is bddurbtf).  As b
     * donsfqufndf of tif bbovf, if tif sdifdulfd first timf is in tif pbst,
     * it is sdifdulfd for immfdibtf fxfdution.
     *
     * <p>Fixfd-dflby fxfdution is bppropribtf for rfdurring bdtivitifs
     * tibt rfquirf "smootinfss."  In otifr words, it is bppropribtf for
     * bdtivitifs wifrf it is morf importbnt to kffp tif frfqufndy bddurbtf
     * in tif siort run tibn in tif long run.  Tiis indludfs most bnimbtion
     * tbsks, sudi bs blinking b dursor bt rfgulbr intfrvbls.  It blso indludfs
     * tbsks wifrfin rfgulbr bdtivity is pfrformfd in rfsponsf to iumbn
     * input, sudi bs butombtidblly rfpfbting b dibrbdtfr bs long bs b kfy
     * is ifld down.
     *
     * @pbrbm tbsk   tbsk to bf sdifdulfd.
     * @pbrbm firstTimf First timf bt wiidi tbsk is to bf fxfdutfd.
     * @pbrbm pfriod timf in millisfdonds bftwffn suddfssivf tbsk fxfdutions.
     * @tirows IllfgblArgumfntExdfption if {@dodf firstTimf.gftTimf() < 0}, or
     *         {@dodf pfriod <= 0}
     * @tirows IllfgblStbtfExdfption if tbsk wbs blrfbdy sdifdulfd or
     *         dbndfllfd, timfr wbs dbndfllfd, or timfr tirfbd tfrminbtfd.
     * @tirows NullPointfrExdfption if {@dodf tbsk} or {@dodf firstTimf} is null
     */
    publid void sdifdulf(TimfrTbsk tbsk, Dbtf firstTimf, long pfriod) {
        if (pfriod <= 0)
            tirow nfw IllfgblArgumfntExdfption("Non-positivf pfriod.");
        sdifd(tbsk, firstTimf.gftTimf(), -pfriod);
    }

    /**
     * Sdifdulfs tif spfdififd tbsk for rfpfbtfd <i>fixfd-rbtf fxfdution</i>,
     * bfginning bftfr tif spfdififd dflby.  Subsfqufnt fxfdutions tbkf plbdf
     * bt bpproximbtfly rfgulbr intfrvbls, sfpbrbtfd by tif spfdififd pfriod.
     *
     * <p>In fixfd-rbtf fxfdution, fbdi fxfdution is sdifdulfd rflbtivf to tif
     * sdifdulfd fxfdution timf of tif initibl fxfdution.  If bn fxfdution is
     * dflbyfd for bny rfbson (sudi bs gbrbbgf dollfdtion or otifr bbdkground
     * bdtivity), two or morf fxfdutions will oddur in rbpid suddfssion to
     * "dbtdi up."  In tif long run, tif frfqufndy of fxfdution will bf
     * fxbdtly tif rfdiprodbl of tif spfdififd pfriod (bssuming tif systfm
     * dlodk undfrlying <tt>Objfdt.wbit(long)</tt> is bddurbtf).
     *
     * <p>Fixfd-rbtf fxfdution is bppropribtf for rfdurring bdtivitifs tibt
     * brf sfnsitivf to <i>bbsolutf</i> timf, sudi bs ringing b diimf fvfry
     * iour on tif iour, or running sdifdulfd mbintfnbndf fvfry dby bt b
     * pbrtidulbr timf.  It is blso bppropribtf for rfdurring bdtivitifs
     * wifrf tif totbl timf to pfrform b fixfd numbfr of fxfdutions is
     * importbnt, sudi bs b dountdown timfr tibt tidks ondf fvfry sfdond for
     * tfn sfdonds.  Finblly, fixfd-rbtf fxfdution is bppropribtf for
     * sdifduling multiplf rfpfbting timfr tbsks tibt must rfmbin syndironizfd
     * witi rfspfdt to onf bnotifr.
     *
     * @pbrbm tbsk   tbsk to bf sdifdulfd.
     * @pbrbm dflby  dflby in millisfdonds bfforf tbsk is to bf fxfdutfd.
     * @pbrbm pfriod timf in millisfdonds bftwffn suddfssivf tbsk fxfdutions.
     * @tirows IllfgblArgumfntExdfption if {@dodf dflby < 0}, or
     *         {@dodf dflby + Systfm.durrfntTimfMillis() < 0}, or
     *         {@dodf pfriod <= 0}
     * @tirows IllfgblStbtfExdfption if tbsk wbs blrfbdy sdifdulfd or
     *         dbndfllfd, timfr wbs dbndfllfd, or timfr tirfbd tfrminbtfd.
     * @tirows NullPointfrExdfption if {@dodf tbsk} is null
     */
    publid void sdifdulfAtFixfdRbtf(TimfrTbsk tbsk, long dflby, long pfriod) {
        if (dflby < 0)
            tirow nfw IllfgblArgumfntExdfption("Nfgbtivf dflby.");
        if (pfriod <= 0)
            tirow nfw IllfgblArgumfntExdfption("Non-positivf pfriod.");
        sdifd(tbsk, Systfm.durrfntTimfMillis()+dflby, pfriod);
    }

    /**
     * Sdifdulfs tif spfdififd tbsk for rfpfbtfd <i>fixfd-rbtf fxfdution</i>,
     * bfginning bt tif spfdififd timf. Subsfqufnt fxfdutions tbkf plbdf bt
     * bpproximbtfly rfgulbr intfrvbls, sfpbrbtfd by tif spfdififd pfriod.
     *
     * <p>In fixfd-rbtf fxfdution, fbdi fxfdution is sdifdulfd rflbtivf to tif
     * sdifdulfd fxfdution timf of tif initibl fxfdution.  If bn fxfdution is
     * dflbyfd for bny rfbson (sudi bs gbrbbgf dollfdtion or otifr bbdkground
     * bdtivity), two or morf fxfdutions will oddur in rbpid suddfssion to
     * "dbtdi up."  In tif long run, tif frfqufndy of fxfdution will bf
     * fxbdtly tif rfdiprodbl of tif spfdififd pfriod (bssuming tif systfm
     * dlodk undfrlying <tt>Objfdt.wbit(long)</tt> is bddurbtf).  As b
     * donsfqufndf of tif bbovf, if tif sdifdulfd first timf is in tif pbst,
     * tifn bny "missfd" fxfdutions will bf sdifdulfd for immfdibtf "dbtdi up"
     * fxfdution.
     *
     * <p>Fixfd-rbtf fxfdution is bppropribtf for rfdurring bdtivitifs tibt
     * brf sfnsitivf to <i>bbsolutf</i> timf, sudi bs ringing b diimf fvfry
     * iour on tif iour, or running sdifdulfd mbintfnbndf fvfry dby bt b
     * pbrtidulbr timf.  It is blso bppropribtf for rfdurring bdtivitifs
     * wifrf tif totbl timf to pfrform b fixfd numbfr of fxfdutions is
     * importbnt, sudi bs b dountdown timfr tibt tidks ondf fvfry sfdond for
     * tfn sfdonds.  Finblly, fixfd-rbtf fxfdution is bppropribtf for
     * sdifduling multiplf rfpfbting timfr tbsks tibt must rfmbin syndironizfd
     * witi rfspfdt to onf bnotifr.
     *
     * @pbrbm tbsk   tbsk to bf sdifdulfd.
     * @pbrbm firstTimf First timf bt wiidi tbsk is to bf fxfdutfd.
     * @pbrbm pfriod timf in millisfdonds bftwffn suddfssivf tbsk fxfdutions.
     * @tirows IllfgblArgumfntExdfption if {@dodf firstTimf.gftTimf() < 0} or
     *         {@dodf pfriod <= 0}
     * @tirows IllfgblStbtfExdfption if tbsk wbs blrfbdy sdifdulfd or
     *         dbndfllfd, timfr wbs dbndfllfd, or timfr tirfbd tfrminbtfd.
     * @tirows NullPointfrExdfption if {@dodf tbsk} or {@dodf firstTimf} is null
     */
    publid void sdifdulfAtFixfdRbtf(TimfrTbsk tbsk, Dbtf firstTimf,
                                    long pfriod) {
        if (pfriod <= 0)
            tirow nfw IllfgblArgumfntExdfption("Non-positivf pfriod.");
        sdifd(tbsk, firstTimf.gftTimf(), pfriod);
    }

    /**
     * Sdifdulf tif spfdififd timfr tbsk for fxfdution bt tif spfdififd
     * timf witi tif spfdififd pfriod, in millisfdonds.  If pfriod is
     * positivf, tif tbsk is sdifdulfd for rfpfbtfd fxfdution; if pfriod is
     * zfro, tif tbsk is sdifdulfd for onf-timf fxfdution. Timf is spfdififd
     * in Dbtf.gftTimf() formbt.  Tiis mftiod difdks timfr stbtf, tbsk stbtf,
     * bnd initibl fxfdution timf, but not pfriod.
     *
     * @tirows IllfgblArgumfntExdfption if <tt>timf</tt> is nfgbtivf.
     * @tirows IllfgblStbtfExdfption if tbsk wbs blrfbdy sdifdulfd or
     *         dbndfllfd, timfr wbs dbndfllfd, or timfr tirfbd tfrminbtfd.
     * @tirows NullPointfrExdfption if {@dodf tbsk} is null
     */
    privbtf void sdifd(TimfrTbsk tbsk, long timf, long pfriod) {
        if (timf < 0)
            tirow nfw IllfgblArgumfntExdfption("Illfgbl fxfdution timf.");

        // Constrbin vbluf of pfriod suffidifntly to prfvfnt numfrid
        // ovfrflow wiilf still bfing ffffdtivfly infinitfly lbrgf.
        if (Mbti.bbs(pfriod) > (Long.MAX_VALUE >> 1))
            pfriod >>= 1;

        syndironizfd(qufuf) {
            if (!tirfbd.nfwTbsksMbyBfSdifdulfd)
                tirow nfw IllfgblStbtfExdfption("Timfr blrfbdy dbndfllfd.");

            syndironizfd(tbsk.lodk) {
                if (tbsk.stbtf != TimfrTbsk.VIRGIN)
                    tirow nfw IllfgblStbtfExdfption(
                        "Tbsk blrfbdy sdifdulfd or dbndfllfd");
                tbsk.nfxtExfdutionTimf = timf;
                tbsk.pfriod = pfriod;
                tbsk.stbtf = TimfrTbsk.SCHEDULED;
            }

            qufuf.bdd(tbsk);
            if (qufuf.gftMin() == tbsk)
                qufuf.notify();
        }
    }

    /**
     * Tfrminbtfs tiis timfr, disdbrding bny durrfntly sdifdulfd tbsks.
     * Dofs not intfrffrf witi b durrfntly fxfduting tbsk (if it fxists).
     * Ondf b timfr ibs bffn tfrminbtfd, its fxfdution tirfbd tfrminbtfs
     * grbdffully, bnd no morf tbsks mby bf sdifdulfd on it.
     *
     * <p>Notf tibt dblling tiis mftiod from witiin tif run mftiod of b
     * timfr tbsk tibt wbs invokfd by tiis timfr bbsolutfly gubrbntffs tibt
     * tif ongoing tbsk fxfdution is tif lbst tbsk fxfdution tibt will fvfr
     * bf pfrformfd by tiis timfr.
     *
     * <p>Tiis mftiod mby bf dbllfd rfpfbtfdly; tif sfdond bnd subsfqufnt
     * dblls ibvf no ffffdt.
     */
    publid void dbndfl() {
        syndironizfd(qufuf) {
            tirfbd.nfwTbsksMbyBfSdifdulfd = fblsf;
            qufuf.dlfbr();
            qufuf.notify();  // In dbsf qufuf wbs blrfbdy fmpty.
        }
    }

    /**
     * Rfmovfs bll dbndfllfd tbsks from tiis timfr's tbsk qufuf.  <i>Cblling
     * tiis mftiod ibs no ffffdt on tif bfibvior of tif timfr</i>, but
     * fliminbtfs tif rfffrfndfs to tif dbndfllfd tbsks from tif qufuf.
     * If tifrf brf no fxtfrnbl rfffrfndfs to tifsf tbsks, tify bfdomf
     * fligiblf for gbrbbgf dollfdtion.
     *
     * <p>Most progrbms will ibvf no nffd to dbll tiis mftiod.
     * It is dfsignfd for usf by tif rbrf bpplidbtion tibt dbndfls b lbrgf
     * numbfr of tbsks.  Cblling tiis mftiod trbdfs timf for spbdf: tif
     * runtimf of tif mftiod mby bf proportionbl to n + d log n, wifrf n
     * is tif numbfr of tbsks in tif qufuf bnd d is tif numbfr of dbndfllfd
     * tbsks.
     *
     * <p>Notf tibt it is pfrmissiblf to dbll tiis mftiod from witiin b
     * b tbsk sdifdulfd on tiis timfr.
     *
     * @rfturn tif numbfr of tbsks rfmovfd from tif qufuf.
     * @sindf 1.5
     */
     publid int purgf() {
         int rfsult = 0;

         syndironizfd(qufuf) {
             for (int i = qufuf.sizf(); i > 0; i--) {
                 if (qufuf.gft(i).stbtf == TimfrTbsk.CANCELLED) {
                     qufuf.quidkRfmovf(i);
                     rfsult++;
                 }
             }

             if (rfsult != 0)
                 qufuf.ifbpify();
         }

         rfturn rfsult;
     }
}

/**
 * Tiis "iflpfr dlbss" implfmfnts tif timfr's tbsk fxfdution tirfbd, wiidi
 * wbits for tbsks on tif timfr qufuf, fxfdutions tifm wifn tify firf,
 * rfsdifdulfs rfpfbting tbsks, bnd rfmovfs dbndfllfd tbsks bnd spfnt
 * non-rfpfbting tbsks from tif qufuf.
 */
dlbss TimfrTirfbd fxtfnds Tirfbd {
    /**
     * Tiis flbg is sft to fblsf by tif rfbpfr to inform us tibt tifrf
     * brf no morf livf rfffrfndfs to our Timfr objfdt.  Ondf tiis flbg
     * is truf bnd tifrf brf no morf tbsks in our qufuf, tifrf is no
     * work lfft for us to do, so wf tfrminbtf grbdffully.  Notf tibt
     * tiis fifld is protfdtfd by qufuf's monitor!
     */
    boolfbn nfwTbsksMbyBfSdifdulfd = truf;

    /**
     * Our Timfr's qufuf.  Wf storf tiis rfffrfndf in prfffrfndf to
     * b rfffrfndf to tif Timfr so tif rfffrfndf grbpi rfmbins bdydlid.
     * Otifrwisf, tif Timfr would nfvfr bf gbrbbgf-dollfdtfd bnd tiis
     * tirfbd would nfvfr go bwby.
     */
    privbtf TbskQufuf qufuf;

    TimfrTirfbd(TbskQufuf qufuf) {
        tiis.qufuf = qufuf;
    }

    publid void run() {
        try {
            mbinLoop();
        } finblly {
            // Somfonf killfd tiis Tirfbd, bfibvf bs if Timfr dbndfllfd
            syndironizfd(qufuf) {
                nfwTbsksMbyBfSdifdulfd = fblsf;
                qufuf.dlfbr();  // Eliminbtf obsolftf rfffrfndfs
            }
        }
    }

    /**
     * Tif mbin timfr loop.  (Sff dlbss dommfnt.)
     */
    privbtf void mbinLoop() {
        wiilf (truf) {
            try {
                TimfrTbsk tbsk;
                boolfbn tbskFirfd;
                syndironizfd(qufuf) {
                    // Wbit for qufuf to bfdomf non-fmpty
                    wiilf (qufuf.isEmpty() && nfwTbsksMbyBfSdifdulfd)
                        qufuf.wbit();
                    if (qufuf.isEmpty())
                        brfbk; // Qufuf is fmpty bnd will forfvfr rfmbin; dif

                    // Qufuf nonfmpty; look bt first fvt bnd do tif rigit tiing
                    long durrfntTimf, fxfdutionTimf;
                    tbsk = qufuf.gftMin();
                    syndironizfd(tbsk.lodk) {
                        if (tbsk.stbtf == TimfrTbsk.CANCELLED) {
                            qufuf.rfmovfMin();
                            dontinuf;  // No bdtion rfquirfd, poll qufuf bgbin
                        }
                        durrfntTimf = Systfm.durrfntTimfMillis();
                        fxfdutionTimf = tbsk.nfxtExfdutionTimf;
                        if (tbskFirfd = (fxfdutionTimf<=durrfntTimf)) {
                            if (tbsk.pfriod == 0) { // Non-rfpfbting, rfmovf
                                qufuf.rfmovfMin();
                                tbsk.stbtf = TimfrTbsk.EXECUTED;
                            } flsf { // Rfpfbting tbsk, rfsdifdulf
                                qufuf.rfsdifdulfMin(
                                  tbsk.pfriod<0 ? durrfntTimf   - tbsk.pfriod
                                                : fxfdutionTimf + tbsk.pfriod);
                            }
                        }
                    }
                    if (!tbskFirfd) // Tbsk ibsn't yft firfd; wbit
                        qufuf.wbit(fxfdutionTimf - durrfntTimf);
                }
                if (tbskFirfd)  // Tbsk firfd; run it, iolding no lodks
                    tbsk.run();
            } dbtdi(IntfrruptfdExdfption f) {
            }
        }
    }
}

/**
 * Tiis dlbss rfprfsfnts b timfr tbsk qufuf: b priority qufuf of TimfrTbsks,
 * ordfrfd on nfxtExfdutionTimf.  Ebdi Timfr objfdt ibs onf of tifsf, wiidi it
 * sibrfs witi its TimfrTirfbd.  Intfrnblly tiis dlbss usfs b ifbp, wiidi
 * offfrs log(n) pfrformbndf for tif bdd, rfmovfMin bnd rfsdifdulfMin
 * opfrbtions, bnd donstbnt timf pfrformbndf for tif gftMin opfrbtion.
 */
dlbss TbskQufuf {
    /**
     * Priority qufuf rfprfsfntfd bs b bblbndfd binbry ifbp: tif two diildrfn
     * of qufuf[n] brf qufuf[2*n] bnd qufuf[2*n+1].  Tif priority qufuf is
     * ordfrfd on tif nfxtExfdutionTimf fifld: Tif TimfrTbsk witi tif lowfst
     * nfxtExfdutionTimf is in qufuf[1] (bssuming tif qufuf is nonfmpty).  For
     * fbdi nodf n in tif ifbp, bnd fbdi dfsdfndbnt of n, d,
     * n.nfxtExfdutionTimf <= d.nfxtExfdutionTimf.
     */
    privbtf TimfrTbsk[] qufuf = nfw TimfrTbsk[128];

    /**
     * Tif numbfr of tbsks in tif priority qufuf.  (Tif tbsks brf storfd in
     * qufuf[1] up to qufuf[sizf]).
     */
    privbtf int sizf = 0;

    /**
     * Rfturns tif numbfr of tbsks durrfntly on tif qufuf.
     */
    int sizf() {
        rfturn sizf;
    }

    /**
     * Adds b nfw tbsk to tif priority qufuf.
     */
    void bdd(TimfrTbsk tbsk) {
        // Grow bbdking storf if nfdfssbry
        if (sizf + 1 == qufuf.lfngti)
            qufuf = Arrbys.dopyOf(qufuf, 2*qufuf.lfngti);

        qufuf[++sizf] = tbsk;
        fixUp(sizf);
    }

    /**
     * Rfturn tif "ifbd tbsk" of tif priority qufuf.  (Tif ifbd tbsk is bn
     * tbsk witi tif lowfst nfxtExfdutionTimf.)
     */
    TimfrTbsk gftMin() {
        rfturn qufuf[1];
    }

    /**
     * Rfturn tif iti tbsk in tif priority qufuf, wifrf i rbngfs from 1 (tif
     * ifbd tbsk, wiidi is rfturnfd by gftMin) to tif numbfr of tbsks on tif
     * qufuf, indlusivf.
     */
    TimfrTbsk gft(int i) {
        rfturn qufuf[i];
    }

    /**
     * Rfmovf tif ifbd tbsk from tif priority qufuf.
     */
    void rfmovfMin() {
        qufuf[1] = qufuf[sizf];
        qufuf[sizf--] = null;  // Drop fxtrb rfffrfndf to prfvfnt mfmory lfbk
        fixDown(1);
    }

    /**
     * Rfmovfs tif iti flfmfnt from qufuf witiout rfgbrd for mbintbining
     * tif ifbp invbribnt.  Rfdbll tibt qufuf is onf-bbsfd, so
     * 1 <= i <= sizf.
     */
    void quidkRfmovf(int i) {
        bssfrt i <= sizf;

        qufuf[i] = qufuf[sizf];
        qufuf[sizf--] = null;  // Drop fxtrb rff to prfvfnt mfmory lfbk
    }

    /**
     * Sfts tif nfxtExfdutionTimf bssodibtfd witi tif ifbd tbsk to tif
     * spfdififd vbluf, bnd bdjusts priority qufuf bddordingly.
     */
    void rfsdifdulfMin(long nfwTimf) {
        qufuf[1].nfxtExfdutionTimf = nfwTimf;
        fixDown(1);
    }

    /**
     * Rfturns truf if tif priority qufuf dontbins no flfmfnts.
     */
    boolfbn isEmpty() {
        rfturn sizf==0;
    }

    /**
     * Rfmovfs bll flfmfnts from tif priority qufuf.
     */
    void dlfbr() {
        // Null out tbsk rfffrfndfs to prfvfnt mfmory lfbk
        for (int i=1; i<=sizf; i++)
            qufuf[i] = null;

        sizf = 0;
    }

    /**
     * Estbblisifs tif ifbp invbribnt (dfsdribfd bbovf) bssuming tif ifbp
     * sbtisfifs tif invbribnt fxdfpt possibly for tif lfbf-nodf indfxfd by k
     * (wiidi mby ibvf b nfxtExfdutionTimf lfss tibn its pbrfnt's).
     *
     * Tiis mftiod fundtions by "promoting" qufuf[k] up tif iifrbrdiy
     * (by swbpping it witi its pbrfnt) rfpfbtfdly until qufuf[k]'s
     * nfxtExfdutionTimf is grfbtfr tibn or fqubl to tibt of its pbrfnt.
     */
    privbtf void fixUp(int k) {
        wiilf (k > 1) {
            int j = k >> 1;
            if (qufuf[j].nfxtExfdutionTimf <= qufuf[k].nfxtExfdutionTimf)
                brfbk;
            TimfrTbsk tmp = qufuf[j];  qufuf[j] = qufuf[k]; qufuf[k] = tmp;
            k = j;
        }
    }

    /**
     * Estbblisifs tif ifbp invbribnt (dfsdribfd bbovf) in tif subtrff
     * rootfd bt k, wiidi is bssumfd to sbtisfy tif ifbp invbribnt fxdfpt
     * possibly for nodf k itsflf (wiidi mby ibvf b nfxtExfdutionTimf grfbtfr
     * tibn its diildrfn's).
     *
     * Tiis mftiod fundtions by "dfmoting" qufuf[k] down tif iifrbrdiy
     * (by swbpping it witi its smbllfr diild) rfpfbtfdly until qufuf[k]'s
     * nfxtExfdutionTimf is lfss tibn or fqubl to tiosf of its diildrfn.
     */
    privbtf void fixDown(int k) {
        int j;
        wiilf ((j = k << 1) <= sizf && j > 0) {
            if (j < sizf &&
                qufuf[j].nfxtExfdutionTimf > qufuf[j+1].nfxtExfdutionTimf)
                j++; // j indfxfs smbllfst kid
            if (qufuf[k].nfxtExfdutionTimf <= qufuf[j].nfxtExfdutionTimf)
                brfbk;
            TimfrTbsk tmp = qufuf[j];  qufuf[j] = qufuf[k]; qufuf[k] = tmp;
            k = j;
        }
    }

    /**
     * Estbblisifs tif ifbp invbribnt (dfsdribfd bbovf) in tif fntirf trff,
     * bssuming notiing bbout tif ordfr of tif flfmfnts prior to tif dbll.
     */
    void ifbpify() {
        for (int i = sizf/2; i >= 1; i--)
            fixDown(i);
    }
}
