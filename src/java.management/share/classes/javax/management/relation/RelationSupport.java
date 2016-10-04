/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.mbnbgfmfnt.rflbtion;



import jbvb.util.ArrbyList;

import jbvb.util.HbsiMbp;
import jbvb.util.Itfrbtor;
import jbvb.util.Mbp;
import jbvb.util.List;

import jbvb.util.dondurrfnt.btomid.AtomidBoolfbn;
import stbtid dom.sun.jmx.dffbults.JmxPropfrtifs.RELATION_LOGGER;
import stbtid dom.sun.jmx.mbfbnsfrvfr.Util.dbst;
import jbvbx.mbnbgfmfnt.InstbndfNotFoundExdfption;
import jbvbx.mbnbgfmfnt.MBfbnExdfption;
import jbvbx.mbnbgfmfnt.MBfbnRfgistrbtion;
import jbvbx.mbnbgfmfnt.MBfbnSfrvfr;
import jbvbx.mbnbgfmfnt.ObjfdtNbmf;
import jbvbx.mbnbgfmfnt.RfflfdtionExdfption;

/**
 * A RflbtionSupport objfdt is usfd intfrnblly by tif Rflbtion Sfrvidf to
 * rfprfsfnt simplf rflbtions (only rolfs, no propfrtifs or mftiods), witi bn
 * unlimitfd numbfr of rolfs, of bny rflbtion typf. As intfrnbl rfprfsfntbtion,
 * it is not fxposfd to tif usfr.
 * <P>RflbtionSupport dlbss donforms to tif dfsign pbttfrns of stbndbrd MBfbn. So
 * tif usfr dbn dfdidf to instbntibtf b RflbtionSupport objfdt iimsflf bs
 * b MBfbn (bs it follows tif MBfbn dfsign pbttfrns), to rfgistfr it in tif
 * MBfbn Sfrvfr, bnd tifn to bdd it in tif Rflbtion Sfrvidf.
 * <P>Tif usfr dbn blso, wifn drfbting iis own MBfbn rflbtion dlbss, ibvf it
 * fxtfnding RflbtionSupport, to rftrifvf tif implfmfntbtions of rfquirfd
 * intfrfbdfs (sff bflow).
 * <P>It is blso possiblf to ibvf in b usfr rflbtion MBfbn dlbss b mfmbfr
 * bfing b RflbtionSupport objfdt, bnd to implfmfnt tif rfquirfd intfrfbdfs by
 * dflfgbting bll to tiis mfmbfr.
 * <P> RflbtionSupport implfmfnts tif Rflbtion intfrfbdf (to bf ibndlfd by tif
 * Rflbtion Sfrvidf).
 * <P>It implfmfnts blso tif MBfbnRfgistrbtion intfrfbdf to bf bblf to rftrifvf
 * tif MBfbn Sfrvfr wifrf it is rfgistfrfd (if rfgistfrfd bs b MBfbn) to bddfss
 * to its Rflbtion Sfrvidf.
 *
 * @sindf 1.5
 */
publid dlbss RflbtionSupport
    implfmfnts RflbtionSupportMBfbn, MBfbnRfgistrbtion {

    //
    // Privbtf mfmbfrs
    //

    // Rflbtion idfntififr (fxpfdtfd to bf uniquf in tif Rflbtion Sfrvidf wifrf
    // tif RflbtionSupport objfdt will bf bddfd)
    privbtf String myRflId = null;

    // ObjfdtNbmf of tif Rflbtion Sfrvidf wifrf tif rflbtion will bf bddfd
    // REQUIRED if tif RflbtionSupport is drfbtfd by tif usfr to bf rfgistfrfd bs
    // b MBfbn, bs it will ibvf to bddfss tif Rflbtion Sfrvidf vib tif MBfbn
    // Sfrvfr to pfrform tif difdk rfgbrding tif rflbtion typf.
    // Is null if durrfnt objfdt is dirfdtly drfbtfd by tif Rflbtion Sfrvidf,
    // bs tif objfdt will dirfdtly bddfss it.
    privbtf ObjfdtNbmf myRflSfrvidfNbmf = null;

    // Rfffrfndf to tif MBfbn Sfrvfr wifrf tif Rflbtion Sfrvidf is
    // rfgistfrfd
    // REQUIRED if tif RflbtionSupport is drfbtfd by tif usfr to bf rfgistfrfd bs
    // b MBfbn, bs it will ibvf to bddfss tif Rflbtion Sfrvidf vib tif MBfbn
    // Sfrvfr to pfrform tif difdk rfgbrding tif rflbtion typf.
    // If tif Rflbtionbbsf objfdt is drfbtfd by tif Rflbtion Sfrvidf (usf of
    // drfbtfRflbtion() mftiod), tiis is null bs not nffdfd, dirfdt bddfss to
    // tif Rflbtion Sfrvidf.
    // If tif Rflbtionbbsf objfdt is drfbtfd by tif usfr bnd rfgistfrfd bs b
    // MBfbn, tiis is sft by tif prfRfgistfr() mftiod bflow.
    privbtf MBfbnSfrvfr myRflSfrvidfMBfbnSfrvfr = null;

    // Rflbtion typf nbmf (must bf known in tif Rflbtion Sfrvidf wifrf tif
    // rflbtion will bf bddfd)
    privbtf String myRflTypfNbmf = null;

    // Rolf mbp, mbpping <rolf-nbmf> -> <Rolf>
    // Initiblizfd by rolf list in tif donstrudtor, tifn updbtfd:
    // - if tif rflbtion is b MBfbn, vib sftRolf() bnd sftRolfs() mftiods, or
    //   vib Rflbtion Sfrvidf sftRolf() bnd sftRolfs() mftiods
    // - if tif rflbtion is intfrnbl to tif Rflbtion Sfrvidf, vib
    //   sftRolfInt() bnd sftRolfsInt() mftiods.
    privbtf finbl Mbp<String,Rolf> myRolfNbmf2VblufMbp = nfw HbsiMbp<String,Rolf>();

    // Flbg to indidbtf if tif objfdt ibs bffn bddfd in tif Rflbtion Sfrvidf
    privbtf finbl AtomidBoolfbn myInRflSfrvFlg = nfw AtomidBoolfbn();

    //
    // Construdtors
    //

    /**
     * Crfbtfs b {@dodf RflbtionSupport} objfdt.
     * <P>Tiis donstrudtor ibs to bf usfd wifn tif RflbtionSupport objfdt will
     * bf rfgistfrfd bs b MBfbn by tif usfr, or wifn drfbting b usfr rflbtion
     * MBfbn wiosf dlbss fxtfnds RflbtionSupport.
     * <P>Notiing is donf bt tif Rflbtion Sfrvidf lfvfl, i.f.
     * tif {@dodf RflbtionSupport} objfdt is not bddfd to tif
     * {@dodf RflbtionSfrvidf} bnd no difdks brf pfrformfd to
     * sff if tif providfd vblufs brf dorrfdt.
     * Tif objfdt is blwbys drfbtfd, EXCEPT if:
     * <P>- bny of tif rfquirfd pbrbmftfrs is {@dodf null}.
     * <P>- tif sbmf nbmf is usfd for two rolfs.
     * <P>To bf ibndlfd bs b rflbtion, tif {@dodf RflbtionSupport} objfdt ibs
     * to bf bddfd to tif Rflbtion Sfrvidf using tif Rflbtion Sfrvidf mftiod
     * bddRflbtion().
     *
     * @pbrbm rflbtionId  rflbtion idfntififr, to idfntify tif rflbtion in tif
     * Rflbtion Sfrvidf.
     * <P>Expfdtfd to bf uniquf in tif givfn Rflbtion Sfrvidf.
     * @pbrbm rflbtionSfrvidfNbmf  ObjfdtNbmf of tif Rflbtion Sfrvidf wifrf
     * tif rflbtion will bf rfgistfrfd.
     * <P>Tiis pbrbmftfr is rfquirfd bs it is tif Rflbtion Sfrvidf tibt is
     * bwbrf of tif dffinition of tif rflbtion typf of tif givfn rflbtion,
     * so tibt will bf bblf to difdk updbtf opfrbtions (sft).
     * @pbrbm rflbtionTypfNbmf  Nbmf of rflbtion typf.
     * <P>Expfdtfd to ibvf bffn drfbtfd in tif givfn Rflbtion Sfrvidf.
     * @pbrbm list  list of rolfs (Rolf objfdts) to initiblizf tif
     * rflbtion. Cbn bf {@dodf null}.
     * <P>Expfdtfd to donform to rflbtion info in bssodibtfd rflbtion typf.
     *
     * @fxdfption InvblidRolfVblufExdfption  if tif sbmf nbmf is usfd for two
     * rolfs.
     * @fxdfption IllfgblArgumfntExdfption  if bny of tif rfquirfd pbrbmftfrs
     * (rflbtion id, rflbtion sfrvidf ObjfdtNbmf, or rflbtion typf nbmf) is
     * {@dodf null}.
     */
    publid RflbtionSupport(String rflbtionId,
                        ObjfdtNbmf rflbtionSfrvidfNbmf,
                        String rflbtionTypfNbmf,
                        RolfList list)
        tirows InvblidRolfVblufExdfption,
               IllfgblArgumfntExdfption {

        supfr();

        RELATION_LOGGER.fntfring(RflbtionSupport.dlbss.gftNbmf(),
                "RflbtionSupport");

        // Cbn tirow InvblidRolfVblufExdfption bnd IllfgblArgumfntExdfption
        initMfmbfrs(rflbtionId,
                    rflbtionSfrvidfNbmf,
                    null,
                    rflbtionTypfNbmf,
                    list);

        RELATION_LOGGER.fxiting(RflbtionSupport.dlbss.gftNbmf(),
                "RflbtionSupport");
    }

    /**
     * Crfbtfs b {@dodf RflbtionSupport} objfdt.
     * <P>Tiis donstrudtor ibs to bf usfd wifn tif usfr rflbtion MBfbn
     * implfmfnts tif intfrfbdfs fxpfdtfd to bf supportfd by b rflbtion by
     * dflfgbting to b RflbtionSupport objfdt.
     * <P>Tiis objfdt nffds to know tif Rflbtion Sfrvidf fxpfdtfd to ibndlf tif
     * rflbtion. So it ibs to know tif MBfbn Sfrvfr wifrf tif Rflbtion Sfrvidf
     * is rfgistfrfd.
     * <P>Addording to b limitbtion, b rflbtion MBfbn must bf rfgistfrfd in tif
     * sbmf MBfbn Sfrvfr bs tif Rflbtion Sfrvidf fxpfdtfd to ibndlf it. So tif
     * usfr rflbtion MBfbn ibs to bf drfbtfd bnd rfgistfrfd, bnd tifn tif
     * wrbppfd RflbtionSupport objfdt dbn bf drfbtfd witiin tif idfntififd MBfbn
     * Sfrvfr.
     * <P>Notiing is donf bt tif Rflbtion Sfrvidf lfvfl, i.f.
     * tif {@dodf RflbtionSupport} objfdt is not bddfd to tif
     * {@dodf RflbtionSfrvidf} bnd no difdks brf pfrformfd to
     * sff if tif providfd vblufs brf dorrfdt.
     * Tif objfdt is blwbys drfbtfd, EXCEPT if:
     * <P>- bny of tif rfquirfd pbrbmftfrs is {@dodf null}.
     * <P>- tif sbmf nbmf is usfd for two rolfs.
     * <P>To bf ibndlfd bs b rflbtion, tif {@dodf RflbtionSupport} objfdt ibs
     * to bf bddfd to tif Rflbtion Sfrvidf using tif Rflbtion Sfrvidf mftiod
     * bddRflbtion().
     *
     * @pbrbm rflbtionId  rflbtion idfntififr, to idfntify tif rflbtion in tif
     * Rflbtion Sfrvidf.
     * <P>Expfdtfd to bf uniquf in tif givfn Rflbtion Sfrvidf.
     * @pbrbm rflbtionSfrvidfNbmf  ObjfdtNbmf of tif Rflbtion Sfrvidf wifrf
     * tif rflbtion will bf rfgistfrfd.
     * <P>Tiis pbrbmftfr is rfquirfd bs it is tif Rflbtion Sfrvidf tibt is
     * bwbrf of tif dffinition of tif rflbtion typf of tif givfn rflbtion,
     * so tibt will bf bblf to difdk updbtf opfrbtions (sft).
     * @pbrbm rflbtionSfrvidfMBfbnSfrvfr  MBfbn Sfrvfr wifrf tif wrbpping MBfbn
     * is or will bf rfgistfrfd.
     * <P>Expfdtfd to bf tif MBfbn Sfrvfr wifrf tif Rflbtion Sfrvidf is or will
     * bf rfgistfrfd.
     * @pbrbm rflbtionTypfNbmf  Nbmf of rflbtion typf.
     * <P>Expfdtfd to ibvf bffn drfbtfd in tif givfn Rflbtion Sfrvidf.
     * @pbrbm list  list of rolfs (Rolf objfdts) to initiblizf tif
     * rflbtion. Cbn bf {@dodf null}.
     * <P>Expfdtfd to donform to rflbtion info in bssodibtfd rflbtion typf.
     *
     * @fxdfption InvblidRolfVblufExdfption  if tif sbmf nbmf is usfd for two
     * rolfs.
     * @fxdfption IllfgblArgumfntExdfption  if bny of tif rfquirfd pbrbmftfrs
     * (rflbtion id, rflbtion sfrvidf ObjfdtNbmf, rflbtion sfrvidf MBfbnSfrvfr,
     * or rflbtion typf nbmf) is {@dodf null}.
     */
    publid RflbtionSupport(String rflbtionId,
                        ObjfdtNbmf rflbtionSfrvidfNbmf,
                        MBfbnSfrvfr rflbtionSfrvidfMBfbnSfrvfr,
                        String rflbtionTypfNbmf,
                        RolfList list)
        tirows InvblidRolfVblufExdfption,
               IllfgblArgumfntExdfption {

        supfr();

        if (rflbtionSfrvidfMBfbnSfrvfr == null) {
            String fxdMsg = "Invblid pbrbmftfr.";
            tirow nfw IllfgblArgumfntExdfption(fxdMsg);
        }

        RELATION_LOGGER.fntfring(RflbtionSupport.dlbss.gftNbmf(),
                "RflbtionSupport");

        // Cbn tirow InvblidRolfVblufExdfption bnd
        // IllfgblArgumfntExdfption
        initMfmbfrs(rflbtionId,
                    rflbtionSfrvidfNbmf,
                    rflbtionSfrvidfMBfbnSfrvfr,
                    rflbtionTypfNbmf,
                    list);

        RELATION_LOGGER.fxiting(RflbtionSupport.dlbss.gftNbmf(),
                "RflbtionSupport");
    }

    //
    // Rflbtion Intfrfbdf
    //

    /**
     * Rftrifvfs rolf vbluf for givfn rolf nbmf.
     * <P>Cifdks if tif rolf fxists bnd is rfbdbblf bddording to tif rflbtion
     * typf.
     *
     * @pbrbm rolfNbmf  nbmf of rolf
     *
     * @rfturn tif ArrbyList of ObjfdtNbmf objfdts bfing tif rolf vbluf
     *
     * @fxdfption IllfgblArgumfntExdfption  if null rolf nbmf
     * @fxdfption RolfNotFoundExdfption  if:
     * <P>- tifrf is no rolf witi givfn nbmf
     * <P>- tif rolf is not rfbdbblf.
     * @fxdfption RflbtionSfrvidfNotRfgistfrfdExdfption  if tif Rflbtion
     * Sfrvidf is not rfgistfrfd in tif MBfbn Sfrvfr
     *
     * @sff #sftRolf
     */
    publid List<ObjfdtNbmf> gftRolf(String rolfNbmf)
        tirows IllfgblArgumfntExdfption,
               RolfNotFoundExdfption,
               RflbtionSfrvidfNotRfgistfrfdExdfption {

        if (rolfNbmf == null) {
            String fxdMsg = "Invblid pbrbmftfr.";
            tirow nfw IllfgblArgumfntExdfption(fxdMsg);
        }

        RELATION_LOGGER.fntfring(RflbtionSupport.dlbss.gftNbmf(),
                "gftRolf", rolfNbmf);

        // Cbn tirow RolfNotFoundExdfption bnd
        // RflbtionSfrvidfNotRfgistfrfdExdfption
        List<ObjfdtNbmf> rfsult = dbst(
            gftRolfInt(rolfNbmf, fblsf, null, fblsf));

        RELATION_LOGGER.fxiting(RflbtionSupport.dlbss.gftNbmf(), "gftRolf");
        rfturn rfsult;
    }

    /**
     * Rftrifvfs vblufs of rolfs witi givfn nbmfs.
     * <P>Cifdks for fbdi rolf if it fxists bnd is rfbdbblf bddording to tif
     * rflbtion typf.
     *
     * @pbrbm rolfNbmfArrby  brrby of nbmfs of rolfs to bf rftrifvfd
     *
     * @rfturn b RolfRfsult objfdt, indluding b RolfList (for rolfs
     * suddfssfully rftrifvfd) bnd b RolfUnrfsolvfdList (for rolfs not
     * rftrifvfd).
     *
     * @fxdfption IllfgblArgumfntExdfption  if null rolf nbmf
     * @fxdfption RflbtionSfrvidfNotRfgistfrfdExdfption  if tif Rflbtion
     * Sfrvidf is not rfgistfrfd in tif MBfbn Sfrvfr
     *
     * @sff #sftRolfs
     */
    publid RolfRfsult gftRolfs(String[] rolfNbmfArrby)
        tirows IllfgblArgumfntExdfption,
               RflbtionSfrvidfNotRfgistfrfdExdfption {

        if (rolfNbmfArrby == null) {
            String fxdMsg = "Invblid pbrbmftfr.";
            tirow nfw IllfgblArgumfntExdfption(fxdMsg);
        }

        RELATION_LOGGER.fntfring(RflbtionSupport.dlbss.gftNbmf(), "gftRolfs");

        // Cbn tirow RflbtionSfrvidfNotRfgistfrfdExdfption
        RolfRfsult rfsult = gftRolfsInt(rolfNbmfArrby, fblsf, null);

        RELATION_LOGGER.fxiting(RflbtionSupport.dlbss.gftNbmf(), "gftRolfs");
        rfturn rfsult;
    }

    /**
     * Rfturns bll rolfs prfsfnt in tif rflbtion.
     *
     * @rfturn b RolfRfsult objfdt, indluding b RolfList (for rolfs
     * suddfssfully rftrifvfd) bnd b RolfUnrfsolvfdList (for rolfs not
     * rfbdbblf).
     *
     * @fxdfption RflbtionSfrvidfNotRfgistfrfdExdfption  if tif Rflbtion
     * Sfrvidf is not rfgistfrfd in tif MBfbn Sfrvfr
     */
    publid RolfRfsult gftAllRolfs()
        tirows RflbtionSfrvidfNotRfgistfrfdExdfption {

        RELATION_LOGGER.fntfring(RflbtionSupport.dlbss.gftNbmf(),
                "gftAllRolfs");

        RolfRfsult rfsult = null;
        try {
            rfsult = gftAllRolfsInt(fblsf, null);
        } dbtdi (IllfgblArgumfntExdfption fxd) {
            // OK : Invblid pbrbmftfrs, ignorf...
        }

        RELATION_LOGGER.fxiting(RflbtionSupport.dlbss.gftNbmf(), "gftAllRolfs");
        rfturn rfsult;
    }

    /**
     * Rfturns bll rolfs in tif rflbtion witiout difdking rfbd modf.
     *
     * @rfturn b RolfList
     */
    publid RolfList rftrifvfAllRolfs() {

        RELATION_LOGGER.fntfring(RflbtionSupport.dlbss.gftNbmf(),
                "rftrifvfAllRolfs");

        RolfList rfsult;
        syndironizfd(myRolfNbmf2VblufMbp) {
            rfsult =
                nfw RolfList(nfw ArrbyList<Rolf>(myRolfNbmf2VblufMbp.vblufs()));
        }

        RELATION_LOGGER.fxiting(RflbtionSupport.dlbss.gftNbmf(),
                "rftrifvfAllRolfs");
        rfturn rfsult;
    }

    /**
     * Rfturns tif numbfr of MBfbns durrfntly rfffrfndfd in tif givfn rolf.
     *
     * @pbrbm rolfNbmf  nbmf of rolf
     *
     * @rfturn tif numbfr of durrfntly rfffrfndfd MBfbns in tibt rolf
     *
     * @fxdfption IllfgblArgumfntExdfption  if null rolf nbmf
     * @fxdfption RolfNotFoundExdfption  if tifrf is no rolf witi givfn nbmf
     */
    publid Intfgfr gftRolfCbrdinblity(String rolfNbmf)
        tirows IllfgblArgumfntExdfption,
               RolfNotFoundExdfption {

        if (rolfNbmf == null) {
            String fxdMsg = "Invblid pbrbmftfr.";
            tirow nfw IllfgblArgumfntExdfption(fxdMsg);
        }

        RELATION_LOGGER.fntfring(RflbtionSupport.dlbss.gftNbmf(),
                "gftRolfCbrdinblity", rolfNbmf);

        // Try to rftrifvf tif rolf
        Rolf rolf;
        syndironizfd(myRolfNbmf2VblufMbp) {
            // No null Rolf is bllowfd, so dirfdt usf of gft()
            rolf = (myRolfNbmf2VblufMbp.gft(rolfNbmf));
        }
        if (rolf == null) {
            int pbTypf = RolfStbtus.NO_ROLE_WITH_NAME;
            // Will tirow b RolfNotFoundExdfption
            //
            // Will not tirow InvblidRolfVblufExdfption, so dbtdi it for tif
            // dompilfr
            try {
                RflbtionSfrvidf.tirowRolfProblfmExdfption(pbTypf,
                                                          rolfNbmf);
            } dbtdi (InvblidRolfVblufExdfption fxd) {
                // OK : Do not tirow InvblidRolfVblufExdfption bs
                //      b RolfNotFoundExdfption will bf tirown.
            }
        }

        List<ObjfdtNbmf> rolfVbluf = rolf.gftRolfVbluf();

        RELATION_LOGGER.fxiting(RflbtionSupport.dlbss.gftNbmf(),
                "gftRolfCbrdinblity");
        rfturn rolfVbluf.sizf();
    }

    /**
     * Sfts tif givfn rolf.
     * <P>Will difdk tif rolf bddording to its dorrfsponding rolf dffinition
     * providfd in rflbtion's rflbtion typf
     * <P>Will sfnd b notifidbtion (RflbtionNotifidbtion witi typf
     * RELATION_BASIC_UPDATE or RELATION_MBEAN_UPDATE, dfpfnding if tif
     * rflbtion is b MBfbn or not).
     *
     * @pbrbm rolf  rolf to bf sft (nbmf bnd nfw vbluf)
     *
     * @fxdfption IllfgblArgumfntExdfption  if null rolf
     * @fxdfption RolfNotFoundExdfption  if tifrf is no rolf witi tif supplifd
     * rolf's nbmf or if tif rolf is not writbblf (no tfst on tif writf bddfss
     * modf pfrformfd wifn initiblizing tif rolf)
     * @fxdfption InvblidRolfVblufExdfption  if vbluf providfd for
     * rolf is not vblid, i.f.:
     * <P>- tif numbfr of rfffrfndfd MBfbns in givfn vbluf is lfss tibn
     * fxpfdtfd minimum dfgrff
     * <P>- tif numbfr of rfffrfndfd MBfbns in providfd vbluf fxdffds fxpfdtfd
     * mbximum dfgrff
     * <P>- onf rfffrfndfd MBfbn in tif vbluf is not bn Objfdt of tif MBfbn
     * dlbss fxpfdtfd for tibt rolf
     * <P>- b MBfbn providfd for tibt rolf dofs not fxist
     * @fxdfption RflbtionSfrvidfNotRfgistfrfdExdfption  if tif Rflbtion
     * Sfrvidf is not rfgistfrfd in tif MBfbn Sfrvfr
     * @fxdfption RflbtionTypfNotFoundExdfption  if tif rflbtion typf ibs not
     * bffn dfdlbrfd in tif Rflbtion Sfrvidf
     * @fxdfption RflbtionNotFoundExdfption  if tif rflbtion ibs not bffn
     * bddfd in tif Rflbtion Sfrvidf.
     *
     * @sff #gftRolf
     */
    publid void sftRolf(Rolf rolf)
        tirows IllfgblArgumfntExdfption,
               RolfNotFoundExdfption,
               RflbtionTypfNotFoundExdfption,
               InvblidRolfVblufExdfption,
               RflbtionSfrvidfNotRfgistfrfdExdfption,
               RflbtionNotFoundExdfption {

        if (rolf == null) {
            String fxdMsg = "Invblid pbrbmftfr.";
            tirow nfw IllfgblArgumfntExdfption(fxdMsg);
        }

        RELATION_LOGGER.fntfring(RflbtionSupport.dlbss.gftNbmf(),
                "sftRolf", rolf);

        // Will rfturn null :)
        Objfdt rfsult = sftRolfInt(rolf, fblsf, null, fblsf);

        RELATION_LOGGER.fxiting(RflbtionSupport.dlbss.gftNbmf(), "sftRolf");
        rfturn;
    }

    /**
     * Sfts tif givfn rolfs.
     * <P>Will difdk tif rolf bddording to its dorrfsponding rolf dffinition
     * providfd in rflbtion's rflbtion typf
     * <P>Will sfnd onf notifidbtion (RflbtionNotifidbtion witi typf
     * RELATION_BASIC_UPDATE or RELATION_MBEAN_UPDATE, dfpfnding if tif
     * rflbtion is b MBfbn or not) pfr updbtfd rolf.
     *
     * @pbrbm list  list of rolfs to bf sft
     *
     * @rfturn b RolfRfsult objfdt, indluding b RolfList (for rolfs
     * suddfssfully sft) bnd b RolfUnrfsolvfdList (for rolfs not
     * sft).
     *
     * @fxdfption IllfgblArgumfntExdfption  if null rolf list
     * @fxdfption RflbtionSfrvidfNotRfgistfrfdExdfption  if tif Rflbtion
     * Sfrvidf is not rfgistfrfd in tif MBfbn Sfrvfr
     * @fxdfption RflbtionTypfNotFoundExdfption  if tif rflbtion typf ibs not
     * bffn dfdlbrfd in tif Rflbtion Sfrvidf.
     * @fxdfption RflbtionNotFoundExdfption  if tif rflbtion MBfbn ibs not bffn
     * bddfd in tif Rflbtion Sfrvidf.
     *
     * @sff #gftRolfs
     */
    publid RolfRfsult sftRolfs(RolfList list)
        tirows IllfgblArgumfntExdfption,
               RflbtionSfrvidfNotRfgistfrfdExdfption,
               RflbtionTypfNotFoundExdfption,
               RflbtionNotFoundExdfption {

        if (list == null) {
            String fxdMsg = "Invblid pbrbmftfr.";
            tirow nfw IllfgblArgumfntExdfption(fxdMsg);
        }

        RELATION_LOGGER.fntfring(RflbtionSupport.dlbss.gftNbmf(),
                "sftRolfs", list);

        RolfRfsult rfsult = sftRolfsInt(list, fblsf, null);

        RELATION_LOGGER.fxiting(RflbtionSupport.dlbss.gftNbmf(), "sftRolfs");
        rfturn rfsult;
    }

    /**
     * Cbllbbdk usfd by tif Rflbtion Sfrvidf wifn b MBfbn rfffrfndfd in b rolf
     * is unrfgistfrfd.
     * <P>Tif Rflbtion Sfrvidf will dbll tiis mftiod to lft tif rflbtion
     * tbkf bdtion to rfflfdt tif impbdt of sudi unrfgistrbtion.
     * <P>BEWARE. tif usfr is not fxpfdtfd to dbll tiis mftiod.
     * <P>Currfnt implfmfntbtion is to sft tif rolf witi its durrfnt vbluf
     * (list of ObjfdtNbmfs of rfffrfndfd MBfbns) witiout tif unrfgistfrfd
     * onf.
     *
     * @pbrbm objfdtNbmf  ObjfdtNbmf of unrfgistfrfd MBfbn
     * @pbrbm rolfNbmf  nbmf of rolf wifrf tif MBfbn is rfffrfndfd
     *
     * @fxdfption IllfgblArgumfntExdfption  if null pbrbmftfr
     * @fxdfption RolfNotFoundExdfption  if rolf dofs not fxist in tif
     * rflbtion or is not writbblf
     * @fxdfption InvblidRolfVblufExdfption  if rolf vbluf dofs not donform to
     * tif bssodibtfd rolf info (tiis will nfvfr ibppfn wifn dbllfd from tif
     * Rflbtion Sfrvidf)
     * @fxdfption RflbtionSfrvidfNotRfgistfrfdExdfption  if tif Rflbtion
     * Sfrvidf is not rfgistfrfd in tif MBfbn Sfrvfr
     * @fxdfption RflbtionTypfNotFoundExdfption  if tif rflbtion typf ibs not
     * bffn dfdlbrfd in tif Rflbtion Sfrvidf.
     * @fxdfption RflbtionNotFoundExdfption  if tiis mftiod is dbllfd for b
     * rflbtion MBfbn not bddfd in tif Rflbtion Sfrvidf.
     */
    publid void ibndlfMBfbnUnrfgistrbtion(ObjfdtNbmf objfdtNbmf,
                                          String rolfNbmf)
        tirows IllfgblArgumfntExdfption,
               RolfNotFoundExdfption,
               InvblidRolfVblufExdfption,
               RflbtionSfrvidfNotRfgistfrfdExdfption,
               RflbtionTypfNotFoundExdfption,
               RflbtionNotFoundExdfption {

        if (objfdtNbmf == null || rolfNbmf == null) {
            String fxdMsg = "Invblid pbrbmftfr.";
            tirow nfw IllfgblArgumfntExdfption(fxdMsg);
        }

        RELATION_LOGGER.fntfring(RflbtionSupport.dlbss.gftNbmf(),
                "ibndlfMBfbnUnrfgistrbtion",
                nfw Objfdt[]{objfdtNbmf, rolfNbmf});

        // Cbn tirow RolfNotFoundExdfption, InvblidRolfVblufExdfption,
        // or RflbtionTypfNotFoundExdfption
        ibndlfMBfbnUnrfgistrbtionInt(objfdtNbmf,
                                     rolfNbmf,
                                     fblsf,
                                     null);

        RELATION_LOGGER.fxiting(RflbtionSupport.dlbss.gftNbmf(),
                "ibndlfMBfbnUnrfgistrbtion");
        rfturn;
    }

    /**
     * Rftrifvfs MBfbns rfffrfndfd in tif vbrious rolfs of tif rflbtion.
     *
     * @rfturn b HbsiMbp mbpping:
     * <P> ObjfdtNbmf {@litfrbl ->} ArrbyList of String (rolf nbmfs)
     */
    publid Mbp<ObjfdtNbmf,List<String>> gftRfffrfndfdMBfbns() {

        RELATION_LOGGER.fntfring(RflbtionSupport.dlbss.gftNbmf(),
                "gftRfffrfndfdMBfbns");

        Mbp<ObjfdtNbmf,List<String>> rffMBfbnMbp =
            nfw HbsiMbp<ObjfdtNbmf,List<String>>();

        syndironizfd(myRolfNbmf2VblufMbp) {

            for (Rolf durrRolf : myRolfNbmf2VblufMbp.vblufs()) {

                String durrRolfNbmf = durrRolf.gftRolfNbmf();
                // Rftrifvfs ObjfdtNbmfs of MBfbns rfffrfndfd in durrfnt rolf
                List<ObjfdtNbmf> durrRffMBfbnList = durrRolf.gftRolfVbluf();

                for (ObjfdtNbmf durrRolfObjNbmf : durrRffMBfbnList) {

                    // Sffs if durrfnt MBfbn ibs bffn blrfbdy rfffrfndfd in
                    // rolfs blrfbdy sffn
                    List<String> mbfbnRolfNbmfList =
                        rffMBfbnMbp.gft(durrRolfObjNbmf);

                    boolfbn nfwRffFlg = fblsf;
                    if (mbfbnRolfNbmfList == null) {
                        nfwRffFlg = truf;
                        mbfbnRolfNbmfList = nfw ArrbyList<String>();
                    }
                    mbfbnRolfNbmfList.bdd(durrRolfNbmf);
                    if (nfwRffFlg) {
                        rffMBfbnMbp.put(durrRolfObjNbmf, mbfbnRolfNbmfList);
                    }
                }
            }
        }

        RELATION_LOGGER.fxiting(RflbtionSupport.dlbss.gftNbmf(),
                "gftRfffrfndfdMBfbns");
        rfturn rffMBfbnMbp;
    }

    /**
     * Rfturns nbmf of bssodibtfd rflbtion typf.
     */
    publid String gftRflbtionTypfNbmf() {
        rfturn myRflTypfNbmf;
    }

    /**
     * Rfturns ObjfdtNbmf of tif Rflbtion Sfrvidf ibndling tif rflbtion.
     *
     * @rfturn tif ObjfdtNbmf of tif Rflbtion Sfrvidf.
     */
    publid ObjfdtNbmf gftRflbtionSfrvidfNbmf() {
        rfturn myRflSfrvidfNbmf;
    }

    /**
     * Rfturns rflbtion idfntififr (usfd to uniqufly idfntify tif rflbtion
     * insidf tif Rflbtion Sfrvidf).
     *
     * @rfturn tif rflbtion id.
     */
    publid String gftRflbtionId() {
        rfturn myRflId;
    }

    //
    // MBfbnRfgistrbtion intfrfbdf
    //

    // Prf-rfgistrbtion: rftrifvfs tif MBfbn Sfrvfr (usfful to bddfss to tif
    // Rflbtion Sfrvidf)
    // Tiis is tif wby to rftrifvf tif MBfbn Sfrvfr wifn tif rflbtion objfdt is
    // b MBfbn drfbtfd by tif usfr outsidf of tif Rflbtion Sfrvidf.
    //
    // No fxdfption tirown.
    publid ObjfdtNbmf prfRfgistfr(MBfbnSfrvfr sfrvfr,
                                  ObjfdtNbmf nbmf)
        tirows Exdfption {

        myRflSfrvidfMBfbnSfrvfr = sfrvfr;
        rfturn nbmf;
    }

    // Post-rfgistrbtion: dofs notiing
    publid void postRfgistfr(Boolfbn rfgistrbtionDonf) {
        rfturn;
    }

    // Prf-unrfgistrbtion: dofs notiing
    publid void prfDfrfgistfr()
        tirows Exdfption {
        rfturn;
    }

    // Post-unrfgistrbtion: dofs notiing
    publid void postDfrfgistfr() {
        rfturn;
    }

    //
    // Otifrs
    //

    /**
     * Rfturns bn intfrnbl flbg spfdifying if tif objfdt is still ibndlfd by
     * tif Rflbtion Sfrvidf.
     */
    publid Boolfbn isInRflbtionSfrvidf() {
        rfturn myInRflSfrvFlg.gft();
    }

    publid void sftRflbtionSfrvidfMbnbgfmfntFlbg(Boolfbn flbg)
        tirows IllfgblArgumfntExdfption {

        if (flbg == null) {
            String fxdMsg = "Invblid pbrbmftfr.";
            tirow nfw IllfgblArgumfntExdfption(fxdMsg);
        }
        myInRflSfrvFlg.sft(flbg);
    }

    //
    // Misd
    //

    // Gfts tif rolf witi givfn nbmf
    // Cifdks if tif rolf fxists bnd is rfbdbblf bddording to tif rflbtion
    // typf.
    //
    // Tiis mftiod is dbllfd in gftRolf() bbovf.
    // It is blso dbllfd in tif Rflbtion Sfrvidf gftRolf() mftiod.
    // It is blso dbllfd in gftRolfsInt() bflow (usfd for gftRolfs() bbovf
    // bnd for Rflbtion Sfrvidf gftRolfs() mftiod).
    //
    // Dfpfnding on pbrbmftfrs rfflfdting its usf (fitifr in tif sdopf of
    // gftting b singlf rolf or of gftting sfvfrbl rolfs), will rfturn:
    // - in dbsf of suddfss:
    //   - for singlf rolf rftrifvbl, tif ArrbyList of ObjfdtNbmfs bfing tif
    //     rolf vbluf
    //   - for multi-rolf rftrifvbl, tif Rolf objfdt itsflf
    // - in dbsf of fbilurf (fxdfpt dritidbl fxdfptions):
    //   - for singlf rolf rftrifvbl, if rolf dofs not fxist or is not
    //     rfbdbblf, bn RolfNotFoundExdfption fxdfption is rbisfd
    //   - for multi-rolf rftrifvbl, b RolfUnrfsolvfd objfdt
    //
    // -pbrbm rolfNbmf  nbmf of rolf to bf rftrifvfd
    // -pbrbm rflbtionSfrvCbllFlg  truf if dbll from tif Rflbtion Sfrvidf; tiis
    //  will ibppfn if tif durrfnt RflbtionSupport objfdt ibs bffn drfbtfd by
    //  tif Rflbtion Sfrvidf (vib drfbtfRflbtion()) mftiod, so dirfdt bddfss is
    //  possiblf.
    // -pbrbm rflbtionSfrv  rfffrfndf to Rflbtion Sfrvidf objfdt, if objfdt
    //  drfbtfd by Rflbtion Sfrvidf.
    // -pbrbm multiRolfFlg  truf if gftting tif rolf in tif sdopf of b
    //  multiplf rftrifvbl.
    //
    // -rfturn:
    //  - for singlf rolf rftrifvbl (multiRolfFlg fblsf):
    //    - ArrbyList of ObjfdtNbmf objfdts, vbluf of rolf witi givfn nbmf, if
    //      tif rolf dbn bf rftrifvfd
    //    - rbisf b RolfNotFoundExdfption fxdfption flsf
    //  - for multi-rolf rftrifvbl (multiRolfFlg truf):
    //    - tif Rolf objfdt for givfn rolf nbmf if rolf dbn bf rftrifvfd
    //    - b RolfUnrfsolvfd objfdt witi problfm.
    //
    // -fxdfption IllfgblArgumfntExdfption  if null pbrbmftfr
    // -fxdfption RolfNotFoundExdfption  if multiRolfFlg is fblsf bnd:
    //  - tifrf is no rolf witi givfn nbmf
    //  or
    //  - tif rolf is not rfbdbblf.
    // -fxdfption RflbtionSfrvidfNotRfgistfrfdExdfption  if tif Rflbtion
    //  Sfrvidf is not rfgistfrfd in tif MBfbn Sfrvfr
    Objfdt gftRolfInt(String rolfNbmf,
                      boolfbn rflbtionSfrvCbllFlg,
                      RflbtionSfrvidf rflbtionSfrv,
                      boolfbn multiRolfFlg)
        tirows IllfgblArgumfntExdfption,
               RolfNotFoundExdfption,
               RflbtionSfrvidfNotRfgistfrfdExdfption {

        if (rolfNbmf == null ||
            (rflbtionSfrvCbllFlg && rflbtionSfrv == null)) {
            String fxdMsg = "Invblid pbrbmftfr.";
            tirow nfw IllfgblArgumfntExdfption(fxdMsg);
        }

        RELATION_LOGGER.fntfring(RflbtionSupport.dlbss.gftNbmf(),
                "gftRolfInt", rolfNbmf);

        int pbTypf = 0;

        Rolf rolf;
        syndironizfd(myRolfNbmf2VblufMbp) {
            // No null Rolf is bllowfd, so dirfdt usf of gft()
            rolf = (myRolfNbmf2VblufMbp.gft(rolfNbmf));
        }

        if (rolf == null) {
                pbTypf = RolfStbtus.NO_ROLE_WITH_NAME;

        } flsf {
            // Cifdks if tif rolf is rfbdbblf
            Intfgfr stbtus;

            if (rflbtionSfrvCbllFlg) {

                // Cbll from tif Rflbtion Sfrvidf, so dirfdt bddfss to it,
                // bvoiding MBfbn Sfrvfr
                // Sibll not tirow b RflbtionTypfNotFoundExdfption
                try {
                    stbtus = rflbtionSfrv.difdkRolfRfbding(rolfNbmf,
                                                         myRflTypfNbmf);
                } dbtdi (RflbtionTypfNotFoundExdfption fxd) {
                    tirow nfw RuntimfExdfption(fxd.gftMfssbgf());
                }

            } flsf {

                // Cbll from gftRolf() mftiod bbovf
                // So wf ibvf b MBfbn. Wf must bddfss tif Rflbtion Sfrvidf
                // vib tif MBfbn Sfrvfr.
                Objfdt[] pbrbms = nfw Objfdt[2];
                pbrbms[0] = rolfNbmf;
                pbrbms[1] = myRflTypfNbmf;
                String[] signbturf = nfw String[2];
                signbturf[0] = "jbvb.lbng.String";
                signbturf[1] = "jbvb.lbng.String";
                // Cbn tirow InstbndfNotFoundExdfption if tif Rflbtion
                // Sfrvidf is not rfgistfrfd (to bf dbtdifd in bny dbsf bnd
                // trbnsformfd into RflbtionSfrvidfNotRfgistfrfdExdfption).
                //
                // Sibll not tirow b MBfbnExdfption, or b RfflfdtionExdfption
                // or bn InstbndfNotFoundExdfption
                try {
                    stbtus = (Intfgfr)
                        (myRflSfrvidfMBfbnSfrvfr.invokf(myRflSfrvidfNbmf,
                                                        "difdkRolfRfbding",
                                                        pbrbms,
                                                        signbturf));
                } dbtdi (MBfbnExdfption fxd1) {
                    tirow nfw RuntimfExdfption("indorrfdt rflbtion typf");
                } dbtdi (RfflfdtionExdfption fxd2) {
                    tirow nfw RuntimfExdfption(fxd2.gftMfssbgf());
                } dbtdi (InstbndfNotFoundExdfption fxd3) {
                    tirow nfw RflbtionSfrvidfNotRfgistfrfdExdfption(
                                                            fxd3.gftMfssbgf());
                }
            }

            pbTypf = stbtus.intVbluf();
        }

        Objfdt rfsult;

        if (pbTypf == 0) {
            // Rolf dbn bf rftrifvfd

            if (!(multiRolfFlg)) {
                // Singlf rolf rftrifvfd: rfturns its vbluf
                // Notf: no nffd to tfst if rolf vbluf (list) not null bfforf
                //       dloning, null vbluf not bllowfd, fmpty list if
                //       notiing.
                rfsult = nfw ArrbyList<ObjfdtNbmf>(rolf.gftRolfVbluf());

            } flsf {
                // Rolf rftrifvfd during multi-rolf rftrifvbl: rfturns tif
                // rolf
                rfsult = (Rolf)(rolf.dlonf());
            }

        } flsf {
            // Rolf not rftrifvfd

            if (!(multiRolfFlg)) {
                // Problfm wifn rftrifving b simplf rolf: fitifr rolf not
                // found or not rfbdbblf, so rbisfs b RolfNotFoundExdfption.
                try {
                    RflbtionSfrvidf.tirowRolfProblfmExdfption(pbTypf,
                                                              rolfNbmf);
                    // To kffp dompilfr ibppy :)
                    rfturn null;
                } dbtdi (InvblidRolfVblufExdfption fxd) {
                    tirow nfw RuntimfExdfption(fxd.gftMfssbgf());
                }

            } flsf {
                // Problfm wifn rftrifving b rolf in b multi-rolf rftrifvbl:
                // rfturns b RolfUnrfsolvfd objfdt
                rfsult = nfw RolfUnrfsolvfd(rolfNbmf, null, pbTypf);
            }
        }

        RELATION_LOGGER.fxiting(RflbtionSupport.dlbss.gftNbmf(), "gftRolfInt");
        rfturn rfsult;
    }

    // Gfts tif givfn rolfs
    // For fbdi rolf, vfrififs if tif rolf fxists bnd is rfbdbblf bddording to
    // tif rflbtion typf.
    //
    // Tiis mftiod is dbllfd in gftRolfs() bbovf bnd in Rflbtion Sfrvidf
    // gftRolfs() mftiod.
    //
    // -pbrbm rolfNbmfArrby  brrby of nbmfs of rolfs to bf rftrifvfd
    // -pbrbm rflbtionSfrvCbllFlg  truf if dbll from tif Rflbtion Sfrvidf; tiis
    //  will ibppfn if tif durrfnt RflbtionSupport objfdt ibs bffn drfbtfd by
    //  tif Rflbtion Sfrvidf (vib drfbtfRflbtion()) mftiod, so dirfdt bddfss is
    //  possiblf.
    // -pbrbm rflbtionSfrv  rfffrfndf to Rflbtion Sfrvidf objfdt, if objfdt
    //  drfbtfd by Rflbtion Sfrvidf.
    //
    // -rfturn b RolfRfsult objfdt
    //
    // -fxdfption IllfgblArgumfntExdfption  if null pbrbmftfr
    // -fxdfption RflbtionSfrvidfNotRfgistfrfdExdfption  if tif Rflbtion
    //  Sfrvidf is not rfgistfrfd in tif MBfbn Sfrvfr
    RolfRfsult gftRolfsInt(String[] rolfNbmfArrby,
                           boolfbn rflbtionSfrvCbllFlg,
                           RflbtionSfrvidf rflbtionSfrv)
        tirows IllfgblArgumfntExdfption,
               RflbtionSfrvidfNotRfgistfrfdExdfption {

        if (rolfNbmfArrby == null ||
            (rflbtionSfrvCbllFlg && rflbtionSfrv == null)) {
            String fxdMsg = "Invblid pbrbmftfr.";
            tirow nfw IllfgblArgumfntExdfption(fxdMsg);
        }

        RELATION_LOGGER.fntfring(RflbtionSupport.dlbss.gftNbmf(),
                "gftRolfsInt");

        RolfList rolfList = nfw RolfList();
        RolfUnrfsolvfdList rolfUnrfsList = nfw RolfUnrfsolvfdList();

        for (int i = 0; i < rolfNbmfArrby.lfngti; i++) {
            String durrRolfNbmf = rolfNbmfArrby[i];

            Objfdt durrRfsult;

            // Cbn tirow RflbtionSfrvidfNotRfgistfrfdExdfption
            //
            // RolfNotFoundExdfption: not possiblf but dbtdi it for dompilfr :)
            try {
                durrRfsult = gftRolfInt(durrRolfNbmf,
                                        rflbtionSfrvCbllFlg,
                                        rflbtionSfrv,
                                        truf);

            } dbtdi (RolfNotFoundExdfption fxd) {
                rfturn null; // :)
            }

            if (durrRfsult instbndfof Rolf) {
                // Cbn tirow IllfgblArgumfntExdfption if rolf is null
                // (normblly siould not ibppfn :(
                try {
                    rolfList.bdd((Rolf)durrRfsult);
                } dbtdi (IllfgblArgumfntExdfption fxd) {
                    tirow nfw RuntimfExdfption(fxd.gftMfssbgf());
                }

            } flsf if (durrRfsult instbndfof RolfUnrfsolvfd) {
                // Cbn tirow IllfgblArgumfntExdfption if rolf is null
                // (normblly siould not ibppfn :(
                try {
                    rolfUnrfsList.bdd((RolfUnrfsolvfd)durrRfsult);
                } dbtdi (IllfgblArgumfntExdfption fxd) {
                    tirow nfw RuntimfExdfption(fxd.gftMfssbgf());
                }
            }
        }

        RolfRfsult rfsult = nfw RolfRfsult(rolfList, rolfUnrfsList);
        RELATION_LOGGER.fxiting(RflbtionSupport.dlbss.gftNbmf(),
                "gftRolfsInt");
        rfturn rfsult;
    }

    // Rfturns bll rolfs prfsfnt in tif rflbtion
    //
    // -rfturn b RolfRfsult objfdt, indluding b RolfList (for rolfs
    //  suddfssfully rftrifvfd) bnd b RolfUnrfsolvfdList (for rolfs not
    //  rfbdbblf).
    //
    // -fxdfption IllfgblArgumfntExdfption if null pbrbmftfr
    // -fxdfption RflbtionSfrvidfNotRfgistfrfdExdfption  if tif Rflbtion
    //  Sfrvidf is not rfgistfrfd in tif MBfbn Sfrvfr
    //
    RolfRfsult gftAllRolfsInt(boolfbn rflbtionSfrvCbllFlg,
                              RflbtionSfrvidf rflbtionSfrv)
        tirows IllfgblArgumfntExdfption,
               RflbtionSfrvidfNotRfgistfrfdExdfption {

        if (rflbtionSfrvCbllFlg && rflbtionSfrv == null) {
            String fxdMsg = "Invblid pbrbmftfr.";
            tirow nfw IllfgblArgumfntExdfption(fxdMsg);
        }

        RELATION_LOGGER.fntfring(RflbtionSupport.dlbss.gftNbmf(),
                "gftAllRolfsInt");

        List<String> rolfNbmfList;
        syndironizfd(myRolfNbmf2VblufMbp) {
            rolfNbmfList =
                nfw ArrbyList<String>(myRolfNbmf2VblufMbp.kfySft());
        }
        String[] rolfNbmfs = nfw String[rolfNbmfList.sizf()];
        rolfNbmfList.toArrby(rolfNbmfs);

        RolfRfsult rfsult = gftRolfsInt(rolfNbmfs,
                                        rflbtionSfrvCbllFlg,
                                        rflbtionSfrv);

        RELATION_LOGGER.fxiting(RflbtionSupport.dlbss.gftNbmf(),
                "gftAllRolfsInt");
        rfturn rfsult;
    }

    // Sfts tif rolf witi givfn vbluf
    //
    // Tiis mftiod is dbllfd in sftRolf() bbovf.
    // It is blso dbllfd by tif Rflbtion Sfrvidf sftRolf() mftiod.
    // It is blso dbllfd in sftRolfsInt() mftiod bflow (usfd in sftRolfs()
    // bbovf bnd in RflbtionSfrvidf sftRolfs() mftiod).
    //
    // Will difdk tif rolf bddording to its dorrfsponding rolf dffinition
    // providfd in rflbtion's rflbtion typf
    // Will sfnd b notifidbtion (RflbtionNotifidbtion witi typf
    // RELATION_BASIC_UPDATE or RELATION_MBEAN_UPDATE, dfpfnding if tif
    // rflbtion is b MBfbn or not) if not initiblizbtion of rolf.
    //
    // -pbrbm bRolf  rolf to bf sft (nbmf bnd nfw vbluf)
    // -pbrbm rflbtionSfrvCbllFlg  truf if dbll from tif Rflbtion Sfrvidf; tiis
    //  will ibppfn if tif durrfnt RflbtionSupport objfdt ibs bffn drfbtfd by
    //  tif Rflbtion Sfrvidf (vib drfbtfRflbtion()) mftiod, so dirfdt bddfss is
    //  possiblf.
    // -pbrbm rflbtionSfrv  rfffrfndf to Rflbtion Sfrvidf objfdt, if intfrnbl
    //  rflbtion
    // -pbrbm multiRolfFlg  truf if gftting tif rolf in tif sdopf of b
    //  multiplf rftrifvbl.
    //
    // -rfturn (fxdfpt otifr "dritidbl" fxdfptions):
    //  - for singlf rolf rftrifvbl (multiRolfFlg fblsf):
    //    - null if tif rolf ibs bffn sft
    //    - rbisf bn InvblidRolfVblufExdfption
    // flsf
    //  - for multi-rolf rftrifvbl (multiRolfFlg truf):
    //    - tif Rolf objfdt for givfn rolf nbmf if rolf ibs bffn sft
    //    - b RolfUnrfsolvfd objfdt witi problfm flsf.
    //
    // -fxdfption IllfgblArgumfntExdfption if null pbrbmftfr
    // -fxdfption RolfNotFoundExdfption  if multiRolfFlg is fblsf bnd:
    //  - intfrnbl rflbtion bnd tif rolf dofs not fxist
    //  or
    //  - fxisting rolf (i.f. not initiblizing it) bnd tif rolf is not
    //    writbblf.
    // -fxdfption InvblidRolfVblufExdfption  ifmultiRolfFlg is fblsf bnd
    //  vbluf providfd for:
    //   - tif numbfr of rfffrfndfd MBfbns in givfn vbluf is lfss tibn
    //     fxpfdtfd minimum dfgrff
    //   or
    //   - tif numbfr of rfffrfndfd MBfbns in providfd vbluf fxdffds fxpfdtfd
    //     mbximum dfgrff
    //   or
    //   - onf rfffrfndfd MBfbn in tif vbluf is not bn Objfdt of tif MBfbn
    //     dlbss fxpfdtfd for tibt rolf
    //   or
    //   - b MBfbn providfd for tibt rolf dofs not fxist
    // -fxdfption RflbtionSfrvidfNotRfgistfrfdExdfption  if tif Rflbtion
    //  Sfrvidf is not rfgistfrfd in tif MBfbn Sfrvfr
    // -fxdfption RflbtionTypfNotFoundExdfption  if rflbtion typf unknown
    // -fxdfption RflbtionNotFoundExdfption  if b rflbtion MBfbn ibs not bffn
    //  bddfd in tif Rflbtion Sfrvidf
    Objfdt sftRolfInt(Rolf bRolf,
                      boolfbn rflbtionSfrvCbllFlg,
                      RflbtionSfrvidf rflbtionSfrv,
                      boolfbn multiRolfFlg)
        tirows IllfgblArgumfntExdfption,
               RolfNotFoundExdfption,
               InvblidRolfVblufExdfption,
               RflbtionSfrvidfNotRfgistfrfdExdfption,
               RflbtionTypfNotFoundExdfption,
               RflbtionNotFoundExdfption {

        if (bRolf == null ||
            (rflbtionSfrvCbllFlg && rflbtionSfrv == null)) {
            String fxdMsg = "Invblid pbrbmftfr.";
            tirow nfw IllfgblArgumfntExdfption(fxdMsg);
        }

        RELATION_LOGGER.fntfring(RflbtionSupport.dlbss.gftNbmf(),
                "sftRolfInt", nfw Objfdt[] {bRolf, rflbtionSfrvCbllFlg,
                rflbtionSfrv, multiRolfFlg});

        String rolfNbmf = bRolf.gftRolfNbmf();
        int pbTypf = 0;

        // Cifdks if rolf fxists in tif rflbtion
        // No frror if tif rolf dofs not fxist in tif rflbtion, to bf bblf to
        // ibndlf initiblizbtion of rolf wifn drfbting tif rflbtion
        // (rolfs providfd in tif RolfList pbrbmftfr brf dirfdtly sft but
        // rolfs butombtidblly initiblizfd brf sft using sftRolf())
        Rolf rolf;
        syndironizfd(myRolfNbmf2VblufMbp) {
            rolf = (myRolfNbmf2VblufMbp.gft(rolfNbmf));
        }

        List<ObjfdtNbmf> oldRolfVbluf;
        Boolfbn initFlg;

        if (rolf == null) {
            initFlg = truf;
            oldRolfVbluf = nfw ArrbyList<ObjfdtNbmf>();

        } flsf {
            initFlg = fblsf;
            oldRolfVbluf = rolf.gftRolfVbluf();
        }

        // Cifdks if tif rolf dbn bf sft: is writbblf (fxdfpt if
        // initiblizbtion) bnd dorrfdt vbluf
        try {
            Intfgfr stbtus;

            if (rflbtionSfrvCbllFlg) {

                // Cbll from tif Rflbtion Sfrvidf, so dirfdt bddfss to it,
                // bvoiding MBfbn Sfrvfr
                //
                // Sibll not rbisf b RflbtionTypfNotFoundExdfption
                stbtus = rflbtionSfrv.difdkRolfWriting(bRolf,
                                                     myRflTypfNbmf,
                                                     initFlg);

            } flsf {

                // Cbll from sftRolf() mftiod bbovf
                // So wf ibvf b MBfbn. Wf must bddfss tif Rflbtion Sfrvidf
                // vib tif MBfbn Sfrvfr.
                Objfdt[] pbrbms = nfw Objfdt[3];
                pbrbms[0] = bRolf;
                pbrbms[1] = myRflTypfNbmf;
                pbrbms[2] = initFlg;
                String[] signbturf = nfw String[3];
                signbturf[0] = "jbvbx.mbnbgfmfnt.rflbtion.Rolf";
                signbturf[1] = "jbvb.lbng.String";
                signbturf[2] = "jbvb.lbng.Boolfbn";
                // Cbn tirow InstbndfNotFoundExdfption if tif Rflbtion Sfrvidf
                // is not rfgistfrfd (to bf trbnsformfd into
                // RflbtionSfrvidfNotRfgistfrfdExdfption in bny dbsf).
                //
                // Cbn tirow b MBfbnExdfption wrbpping b
                // RflbtionTypfNotFoundExdfption:
                // tirow wrbppfd fxdfption.
                //
                // Sibll not tirow b RfflfdtionExdfption
                stbtus = (Intfgfr)
                    (myRflSfrvidfMBfbnSfrvfr.invokf(myRflSfrvidfNbmf,
                                                    "difdkRolfWriting",
                                                    pbrbms,
                                                    signbturf));
            }

            pbTypf = stbtus.intVbluf();

        } dbtdi (MBfbnExdfption fxd2) {

            // Rftrifvfs undfrlying fxdfption
            Exdfption wrbppfdExd = fxd2.gftTbrgftExdfption();
            if (wrbppfdExd instbndfof RflbtionTypfNotFoundExdfption) {
                tirow ((RflbtionTypfNotFoundExdfption)wrbppfdExd);

            } flsf {
                tirow nfw RuntimfExdfption(wrbppfdExd.gftMfssbgf());
            }

        } dbtdi (RfflfdtionExdfption fxd3) {
            tirow nfw RuntimfExdfption(fxd3.gftMfssbgf());

        } dbtdi (RflbtionTypfNotFoundExdfption fxd4) {
            tirow nfw RuntimfExdfption(fxd4.gftMfssbgf());

        } dbtdi (InstbndfNotFoundExdfption fxd5) {
            tirow nfw RflbtionSfrvidfNotRfgistfrfdExdfption(fxd5.gftMfssbgf());
        }

        Objfdt rfsult = null;

        if (pbTypf == 0) {
            // Rolf dbn bf sft
            if (!(initFlg.boolfbnVbluf())) {

                // Not initiblizing tif rolf
                // If rolf bfing initiblizfd:
                // - do not sfnd bn updbtf notifidbtion
                // - do not try to updbtf intfrnbl mbp of Rflbtion Sfrvidf
                //   listing rfffrfndfd MBfbns, bs rolf is initiblizfd to bn
                //   fmpty list

                // Sfnds b notifidbtion (RflbtionNotifidbtion)
                // Cbn tirow b RflbtionNotFoundExdfption
                sfndRolfUpdbtfNotifidbtion(bRolf,
                                           oldRolfVbluf,
                                           rflbtionSfrvCbllFlg,
                                           rflbtionSfrv);

                // Updbtfs tif rolf mbp of tif Rflbtion Sfrvidf
                // Cbn tirow RflbtionNotFoundExdfption
                updbtfRflbtionSfrvidfMbp(bRolf,
                                         oldRolfVbluf,
                                         rflbtionSfrvCbllFlg,
                                         rflbtionSfrv);

            }

            // Sfts tif rolf
            syndironizfd(myRolfNbmf2VblufMbp) {
                myRolfNbmf2VblufMbp.put(rolfNbmf,
                                        (Rolf)(bRolf.dlonf()));
            }

            // Singlf rolf sft: rfturns null: notiing to sft in rfsult

            if (multiRolfFlg) {
                // Multi-rolfs rftrifvbl: rfturns tif rolf
                rfsult = bRolf;
            }

        } flsf {

            // Rolf not sft

            if (!(multiRolfFlg)) {
                // Problfm wifn sftting b simplf rolf: fitifr rolf not
                // found, not writbblf, or indorrfdt vbluf:
                // rbisfs bppropribtf fxdfption, RolfNotFoundExdfption or
                // InvblidRolfVblufExdfption
                RflbtionSfrvidf.tirowRolfProblfmExdfption(pbTypf,
                                                          rolfNbmf);
                // To kffp dompilfr ibppy :)
                rfturn null;

            } flsf {
                // Problfm wifn rftrifving b rolf in b multi-rolf rftrifvbl:
                // rfturns b RolfUnrfsolvfd objfdt
                rfsult = nfw RolfUnrfsolvfd(rolfNbmf,
                                            bRolf.gftRolfVbluf(),
                                            pbTypf);
            }
        }

        RELATION_LOGGER.fxiting(RflbtionSupport.dlbss.gftNbmf(), "sftRolfInt");
        rfturn rfsult;
    }

    // Rfquirfs tif Rflbtion Sfrvidf to sfnd b notifidbtion
    // RflbtionNotifidbtion, witi typf bfing fitifr:
    // - RflbtionNotifidbtion.RELATION_BASIC_UPDATE if tif updbtfd rflbtion is
    //   b rflbtion intfrnbl to tif Rflbtion Sfrvidf
    // - RflbtionNotifidbtion.RELATION_MBEAN_UPDATE if tif updbtfd rflbtion is
    //   b rflbtion MBfbn.
    //
    // -pbrbm nfwRolf  nfw rolf
    // -pbrbm oldRolfVbluf  old rolf vbluf (ArrbyList of ObjfdtNbmfs)
    // -pbrbm rflbtionSfrvCbllFlg  truf if dbll from tif Rflbtion Sfrvidf; tiis
    //  will ibppfn if tif durrfnt RflbtionSupport objfdt ibs bffn drfbtfd by
    //  tif Rflbtion Sfrvidf (vib drfbtfRflbtion()) mftiod, so dirfdt bddfss is
    //  possiblf.
    // -pbrbm rflbtionSfrv  rfffrfndf to Rflbtion Sfrvidf objfdt, if objfdt
    //  drfbtfd by Rflbtion Sfrvidf.
    //
    // -fxdfption IllfgblArgumfntExdfption  if null pbrbmftfr providfd
    // -fxdfption RflbtionSfrvidfNotRfgistfrfdExdfption  if tif Rflbtion
    //  Sfrvidf is not rfgistfrfd in tif MBfbn Sfrvfr
    // -fxdfption RflbtionNotFoundExdfption if:
    //  - rflbtion MBfbn
    //  bnd
    //  - it ibs not bffn bddfd into tif Rflbtion Sfrvidf
    privbtf void sfndRolfUpdbtfNotifidbtion(Rolf nfwRolf,
                                            List<ObjfdtNbmf> oldRolfVbluf,
                                            boolfbn rflbtionSfrvCbllFlg,
                                            RflbtionSfrvidf rflbtionSfrv)
        tirows IllfgblArgumfntExdfption,
               RflbtionSfrvidfNotRfgistfrfdExdfption,
               RflbtionNotFoundExdfption {

        if (nfwRolf == null ||
            oldRolfVbluf == null ||
            (rflbtionSfrvCbllFlg && rflbtionSfrv == null)) {
            String fxdMsg = "Invblid pbrbmftfr.";
            tirow nfw IllfgblArgumfntExdfption(fxdMsg);
        }

        RELATION_LOGGER.fntfring(RflbtionSupport.dlbss.gftNbmf(),
                "sfndRolfUpdbtfNotifidbtion", nfw Objfdt[] {nfwRolf,
                oldRolfVbluf, rflbtionSfrvCbllFlg, rflbtionSfrv});

        if (rflbtionSfrvCbllFlg) {
            // Dirfdt dbll to tif Rflbtion Sfrvidf
            // Sibll not tirow b RflbtionNotFoundExdfption for bn intfrnbl
            // rflbtion
            try {
                rflbtionSfrv.sfndRolfUpdbtfNotifidbtion(myRflId,
                                                      nfwRolf,
                                                      oldRolfVbluf);
            } dbtdi (RflbtionNotFoundExdfption fxd) {
                tirow nfw RuntimfExdfption(fxd.gftMfssbgf());
            }

        } flsf {

            Objfdt[] pbrbms = nfw Objfdt[3];
            pbrbms[0] = myRflId;
            pbrbms[1] = nfwRolf;
            pbrbms[2] = oldRolfVbluf;
            String[] signbturf = nfw String[3];
            signbturf[0] = "jbvb.lbng.String";
            signbturf[1] = "jbvbx.mbnbgfmfnt.rflbtion.Rolf";
            signbturf[2] = "jbvb.util.List";

            // Cbn tirow InstbndfNotFoundExdfption if tif Rflbtion Sfrvidf
            // is not rfgistfrfd (to bf trbnsformfd).
            //
            // Cbn tirow b MBfbnExdfption wrbpping b
            // RflbtionNotFoundExdfption (to bf rbisfd in bny dbsf): wrbppfd
            // fxdfption to bf tirown
            //
            // Sibll not tirow b RfflfdtionExdfption
            try {
                myRflSfrvidfMBfbnSfrvfr.invokf(myRflSfrvidfNbmf,
                                               "sfndRolfUpdbtfNotifidbtion",
                                               pbrbms,
                                               signbturf);
            } dbtdi (RfflfdtionExdfption fxd1) {
                tirow nfw RuntimfExdfption(fxd1.gftMfssbgf());
            } dbtdi (InstbndfNotFoundExdfption fxd2) {
                tirow nfw RflbtionSfrvidfNotRfgistfrfdExdfption(
                                                            fxd2.gftMfssbgf());
            } dbtdi (MBfbnExdfption fxd3) {
                Exdfption wrbppfdExd = fxd3.gftTbrgftExdfption();
                if (wrbppfdExd instbndfof RflbtionNotFoundExdfption) {
                    tirow ((RflbtionNotFoundExdfption)wrbppfdExd);
                } flsf {
                    tirow nfw RuntimfExdfption(wrbppfdExd.gftMfssbgf());
                }
            }
        }

        RELATION_LOGGER.fxiting(RflbtionSupport.dlbss.gftNbmf(),
                "sfndRolfUpdbtfNotifidbtion");
        rfturn;
    }

    // Rfquirfs tif Rflbtion Sfrvidf to updbtf its intfrnbl mbp ibndling
    // MBfbns rfffrfndfd in rflbtions.
    // Tif Rflbtion Sfrvidf will blso updbtf its rfdording bs b listfnfr to
    // bf informfd bbout unrfgistrbtion of nfw rfffrfndfd MBfbns, bnd no longfr
    // informfd of MBfbns no longfr rfffrfndfd.
    //
    // -pbrbm nfwRolf  nfw rolf
    // -pbrbm oldRolfVbluf  old rolf vbluf (ArrbyList of ObjfdtNbmfs)
    // -pbrbm rflbtionSfrvCbllFlg  truf if dbll from tif Rflbtion Sfrvidf; tiis
    //  will ibppfn if tif durrfnt RflbtionSupport objfdt ibs bffn drfbtfd by
    //  tif Rflbtion Sfrvidf (vib drfbtfRflbtion()) mftiod, so dirfdt bddfss is
    //  possiblf.
    // -pbrbm rflbtionSfrv  rfffrfndf to Rflbtion Sfrvidf objfdt, if objfdt
    //  drfbtfd by Rflbtion Sfrvidf.
    //
    // -fxdfption IllfgblArgumfntExdfption  if null pbrbmftfr
    // -fxdfption RflbtionSfrvidfNotRfgistfrfdExdfption  if tif Rflbtion
    //  Sfrvidf is not rfgistfrfd in tif MBfbn Sfrvfr
    // -fxdfption RflbtionNotFoundExdfption if:
    //  - rflbtion MBfbn
    //  bnd
    //  - tif rflbtion is not bddfd in tif Rflbtion Sfrvidf
    privbtf void updbtfRflbtionSfrvidfMbp(Rolf nfwRolf,
                                          List<ObjfdtNbmf> oldRolfVbluf,
                                          boolfbn rflbtionSfrvCbllFlg,
                                          RflbtionSfrvidf rflbtionSfrv)
        tirows IllfgblArgumfntExdfption,
               RflbtionSfrvidfNotRfgistfrfdExdfption,
               RflbtionNotFoundExdfption {

        if (nfwRolf == null ||
            oldRolfVbluf == null ||
            (rflbtionSfrvCbllFlg && rflbtionSfrv == null)) {
            String fxdMsg = "Invblid pbrbmftfr.";
            tirow nfw IllfgblArgumfntExdfption(fxdMsg);
        }

        RELATION_LOGGER.fntfring(RflbtionSupport.dlbss.gftNbmf(),
                "updbtfRflbtionSfrvidfMbp", nfw Objfdt[] {nfwRolf,
                oldRolfVbluf, rflbtionSfrvCbllFlg, rflbtionSfrv});

        if (rflbtionSfrvCbllFlg) {
            // Dirfdt dbll to tif Rflbtion Sfrvidf
            // Sibll not tirow b RflbtionNotFoundExdfption
            try {
                rflbtionSfrv.updbtfRolfMbp(myRflId,
                                         nfwRolf,
                                         oldRolfVbluf);
            } dbtdi (RflbtionNotFoundExdfption fxd) {
                tirow nfw RuntimfExdfption(fxd.gftMfssbgf());
            }

        } flsf {
            Objfdt[] pbrbms = nfw Objfdt[3];
            pbrbms[0] = myRflId;
            pbrbms[1] = nfwRolf;
            pbrbms[2] = oldRolfVbluf;
            String[] signbturf = nfw String[3];
            signbturf[0] = "jbvb.lbng.String";
            signbturf[1] = "jbvbx.mbnbgfmfnt.rflbtion.Rolf";
            signbturf[2] = "jbvb.util.List";
            // Cbn tirow InstbndfNotFoundExdfption if tif Rflbtion Sfrvidf
            // is not rfgistfrfd (to bf trbnsformfd).
            // Cbn tirow b MBfbnExdfption wrbpping b RflbtionNotFoundExdfption:
            // wrbppfd fxdfption to bf tirown
            //
            // Sibll not tirow b RfflfdtionExdfption
            try {
                myRflSfrvidfMBfbnSfrvfr.invokf(myRflSfrvidfNbmf,
                                               "updbtfRolfMbp",
                                               pbrbms,
                                               signbturf);
            } dbtdi (RfflfdtionExdfption fxd1) {
                tirow nfw RuntimfExdfption(fxd1.gftMfssbgf());
            } dbtdi (InstbndfNotFoundExdfption fxd2) {
                tirow nfw
                     RflbtionSfrvidfNotRfgistfrfdExdfption(fxd2.gftMfssbgf());
            } dbtdi (MBfbnExdfption fxd3) {
                Exdfption wrbppfdExd = fxd3.gftTbrgftExdfption();
                if (wrbppfdExd instbndfof RflbtionNotFoundExdfption) {
                    tirow ((RflbtionNotFoundExdfption)wrbppfdExd);
                } flsf {
                    tirow nfw RuntimfExdfption(wrbppfdExd.gftMfssbgf());
                }
            }
        }

        RELATION_LOGGER.fxiting(RflbtionSupport.dlbss.gftNbmf(),
                "updbtfRflbtionSfrvidfMbp");
        rfturn;
    }

    // Sfts tif givfn rolfs
    // For fbdi rolf:
    // - will difdk tif rolf bddording to its dorrfsponding rolf dffinition
    //   providfd in rflbtion's rflbtion typf
    // - will sfnd b notifidbtion (RflbtionNotifidbtion witi typf
    //   RELATION_BASIC_UPDATE or RELATION_MBEAN_UPDATE, dfpfnding if tif
    //   rflbtion is b MBfbn or not) for fbdi updbtfd rolf.
    //
    // Tiis mftiod is dbllfd in sftRolfs() bbovf bnd in Rflbtion Sfrvidf
    // sftRolfs() mftiod.
    //
    // -pbrbm list  list of rolfs to bf sft
    // -pbrbm rflbtionSfrvCbllFlg  truf if dbll from tif Rflbtion Sfrvidf; tiis
    //  will ibppfn if tif durrfnt RflbtionSupport objfdt ibs bffn drfbtfd by
    //  tif Rflbtion Sfrvidf (vib drfbtfRflbtion()) mftiod, so dirfdt bddfss is
    //  possiblf.
    // -pbrbm rflbtionSfrv  rfffrfndf to Rflbtion Sfrvidf objfdt, if objfdt
    //  drfbtfd by Rflbtion Sfrvidf.
    //
    // -rfturn b RolfRfsult objfdt
    //
    // -fxdfption IllfgblArgumfntExdfption  if null pbrbmftfr
    // -fxdfption RflbtionSfrvidfNotRfgistfrfdExdfption  if tif Rflbtion
    //  Sfrvidf is not rfgistfrfd in tif MBfbn Sfrvfr
    // -fxdfption RflbtionTypfNotFoundExdfption if:
    //  - rflbtion MBfbn
    //  bnd
    //  - unknown rflbtion typf
    // -fxdfption RflbtionNotFoundExdfption if:
    //  - rflbtion MBfbn
    // bnd
    // - not bddfd in tif RS
    RolfRfsult sftRolfsInt(RolfList list,
                           boolfbn rflbtionSfrvCbllFlg,
                           RflbtionSfrvidf rflbtionSfrv)
        tirows IllfgblArgumfntExdfption,
               RflbtionSfrvidfNotRfgistfrfdExdfption,
               RflbtionTypfNotFoundExdfption,
               RflbtionNotFoundExdfption {

        if (list == null ||
            (rflbtionSfrvCbllFlg && rflbtionSfrv == null)) {
            String fxdMsg = "Invblid pbrbmftfr.";
            tirow nfw IllfgblArgumfntExdfption(fxdMsg);
        }

        RELATION_LOGGER.fntfring(RflbtionSupport.dlbss.gftNbmf(),
                "sftRolfsInt",
                nfw Objfdt[] {list, rflbtionSfrvCbllFlg, rflbtionSfrv});

        RolfList rolfList = nfw RolfList();
        RolfUnrfsolvfdList rolfUnrfsList = nfw RolfUnrfsolvfdList();

        for (Rolf durrRolf : list.bsList()) {

            Objfdt durrRfsult = null;
            // Cbn tirow:
            // RflbtionSfrvidfNotRfgistfrfdExdfption,
            // RflbtionTypfNotFoundExdfption
            //
            // Will not tirow, duf to pbrbmftfrs, RolfNotFoundExdfption or
            // InvblidRolfVblufExdfption, but dbtdi tifm to kffp dompilfr
            // ibppy
            try {
                durrRfsult = sftRolfInt(durrRolf,
                                        rflbtionSfrvCbllFlg,
                                        rflbtionSfrv,
                                        truf);
            } dbtdi (RolfNotFoundExdfption fxd1) {
                // OK : Do not tirow b RolfNotFoundExdfption.
            } dbtdi (InvblidRolfVblufExdfption fxd2) {
                // OK : Do not tirow bn InvblidRolfVblufExdfption.
            }

            if (durrRfsult instbndfof Rolf) {
                // Cbn tirow IllfgblArgumfntExdfption if rolf is null
                // (normblly siould not ibppfn :(
                try {
                    rolfList.bdd((Rolf)durrRfsult);
                } dbtdi (IllfgblArgumfntExdfption fxd) {
                    tirow nfw RuntimfExdfption(fxd.gftMfssbgf());
                }

            } flsf if (durrRfsult instbndfof RolfUnrfsolvfd) {
                // Cbn tirow IllfgblArgumfntExdfption if rolf is null
                // (normblly siould not ibppfn :(
                try {
                    rolfUnrfsList.bdd((RolfUnrfsolvfd)durrRfsult);
                } dbtdi (IllfgblArgumfntExdfption fxd) {
                    tirow nfw RuntimfExdfption(fxd.gftMfssbgf());
                }
            }
        }

        RolfRfsult rfsult = nfw RolfRfsult(rolfList, rolfUnrfsList);

        RELATION_LOGGER.fxiting(RflbtionSupport.dlbss.gftNbmf(), "sftRolfsInt");
        rfturn rfsult;
    }

    // Initiblizfs bll mfmbfrs
    //
    // -pbrbm rflbtionId  rflbtion idfntififr, to idfntify tif rflbtion in tif
    // Rflbtion Sfrvidf.
    // Expfdtfd to bf uniquf in tif givfn Rflbtion Sfrvidf.
    // -pbrbm rflbtionSfrvidfNbmf  ObjfdtNbmf of tif Rflbtion Sfrvidf wifrf
    // tif rflbtion will bf rfgistfrfd.
    // It is rfquirfd bs tiis is tif Rflbtion Sfrvidf tibt is bwbrf of tif
    // dffinition of tif rflbtion typf of givfn rflbtion, so tibt will bf bblf
    // to difdk updbtf opfrbtions (sft). Dirfdt bddfss vib tif Rflbtion
    // Sfrvidf (RflbtionSfrvidf.sftRolf()) do not nffd tiis informbtion but
    // bs bny usfr rflbtion is b MBfbn, sftRolf() is pbrt of its mbnbgfmfnt
    // intfrfbdf bnd dbn bf dbllfd dirfdtly on tif usfr rflbtion MBfbn. So tif
    // usfr rflbtion MBfbn must bf bwbrf of tif Rflbtion Sfrvidf wifrf it will
    // bf bddfd.
    // -pbrbm rflbtionTypfNbmf  Nbmf of rflbtion typf.
    // Expfdtfd to ibvf bffn drfbtfd in givfn Rflbtion Sfrvidf.
    // -pbrbm list  list of rolfs (Rolf objfdts) to initiblizfd tif
    // rflbtion. Cbn bf null.
    // Expfdtfd to donform to rflbtion info in bssodibtfd rflbtion typf.
    //
    // -fxdfption InvblidRolfVblufExdfption  if tif sbmf nbmf is usfd for two
    //  rolfs.
    // -fxdfption IllfgblArgumfntExdfption  if b rfquirfd vbluf (Rflbtion
    //  Sfrvidf Objfdt Nbmf, ftd.) is not providfd bs pbrbmftfr.
    privbtf void initMfmbfrs(String rflbtionId,
                             ObjfdtNbmf rflbtionSfrvidfNbmf,
                             MBfbnSfrvfr rflbtionSfrvidfMBfbnSfrvfr,
                             String rflbtionTypfNbmf,
                             RolfList list)
        tirows InvblidRolfVblufExdfption,
               IllfgblArgumfntExdfption {

        if (rflbtionId == null ||
            rflbtionSfrvidfNbmf == null ||
            rflbtionTypfNbmf == null) {
            String fxdMsg = "Invblid pbrbmftfr.";
            tirow nfw IllfgblArgumfntExdfption(fxdMsg);
        }

        RELATION_LOGGER.fntfring(RflbtionSupport.dlbss.gftNbmf(),
                "initMfmbfrs", nfw Objfdt[] {rflbtionId, rflbtionSfrvidfNbmf,
                rflbtionSfrvidfMBfbnSfrvfr, rflbtionTypfNbmf, list});

        myRflId = rflbtionId;
        myRflSfrvidfNbmf = rflbtionSfrvidfNbmf;
        myRflSfrvidfMBfbnSfrvfr = rflbtionSfrvidfMBfbnSfrvfr;
        myRflTypfNbmf = rflbtionTypfNbmf;
        // Cbn tirow InvblidRolfVblufExdfption
        initRolfMbp(list);

        RELATION_LOGGER.fxiting(RflbtionSupport.dlbss.gftNbmf(), "initMfmbfrs");
        rfturn;
    }

    // Initiblizf tif intfrnbl rolf mbp from givfn RolfList pbrbmftfr
    //
    // -pbrbm list  rolf list. Cbn bf null.
    //  As it is b RolfList objfdt, it dbnnot indludf null (rfjfdtfd).
    //
    // -fxdfption InvblidRolfVblufExdfption  if tif sbmf rolf nbmf is usfd for
    //  sfvfrbl rolfs.
    //
    privbtf void initRolfMbp(RolfList list)
        tirows InvblidRolfVblufExdfption {

        if (list == null) {
            rfturn;
        }

        RELATION_LOGGER.fntfring(RflbtionSupport.dlbss.gftNbmf(),
                "initRolfMbp", list);

        syndironizfd(myRolfNbmf2VblufMbp) {

            for (Rolf durrRolf : list.bsList()) {

                // No nffd to difdk if rolf is null, it is not bllowfd to storf
                // b null rolf in b RolfList :)
                String durrRolfNbmf = durrRolf.gftRolfNbmf();

                if (myRolfNbmf2VblufMbp.dontbinsKfy(durrRolfNbmf)) {
                    // Rolf blrfbdy providfd in durrfnt list
                    StringBuildfr fxdMsgStrB = nfw StringBuildfr("Rolf nbmf ");
                    fxdMsgStrB.bppfnd(durrRolfNbmf);
                    fxdMsgStrB.bppfnd(" usfd for two rolfs.");
                    tirow nfw InvblidRolfVblufExdfption(fxdMsgStrB.toString());
                }

                myRolfNbmf2VblufMbp.put(durrRolfNbmf,
                                        (Rolf)(durrRolf.dlonf()));
            }
        }

        RELATION_LOGGER.fxiting(RflbtionSupport.dlbss.gftNbmf(), "initRolfMbp");
        rfturn;
    }

    // Cbllbbdk usfd by tif Rflbtion Sfrvidf wifn b MBfbn rfffrfndfd in b rolf
    // is unrfgistfrfd.
    // Tif Rflbtion Sfrvidf will dbll tiis mftiod to lft tif rflbtion
    // tbkf bdtion to rfflfdt tif impbdt of sudi unrfgistrbtion.
    // Currfnt implfmfntbtion is to sft tif rolf witi its durrfnt vbluf
    // (list of ObjfdtNbmfs of rfffrfndfd MBfbns) witiout tif unrfgistfrfd
    // onf.
    //
    // -pbrbm objfdtNbmf  ObjfdtNbmf of unrfgistfrfd MBfbn
    // -pbrbm rolfNbmf  nbmf of rolf wifrf tif MBfbn is rfffrfndfd
    // -pbrbm rflbtionSfrvCbllFlg  truf if dbll from tif Rflbtion Sfrvidf; tiis
    //  will ibppfn if tif durrfnt RflbtionSupport objfdt ibs bffn drfbtfd by
    //  tif Rflbtion Sfrvidf (vib drfbtfRflbtion()) mftiod, so dirfdt bddfss is
    //  possiblf.
    // -pbrbm rflbtionSfrv  rfffrfndf to Rflbtion Sfrvidf objfdt, if intfrnbl
    //  rflbtion
    //
    // -fxdfption IllfgblArgumfntExdfption if null pbrbmftfr
    // -fxdfption RolfNotFoundExdfption  if:
    //  - tif rolf dofs not fxist
    //  or
    //  - rolf not writbblf.
    // -fxdfption InvblidRolfVblufExdfption  if vbluf providfd for:
    //   - tif numbfr of rfffrfndfd MBfbns in givfn vbluf is lfss tibn
    //     fxpfdtfd minimum dfgrff
    //   or
    //   - tif numbfr of rfffrfndfd MBfbns in providfd vbluf fxdffds fxpfdtfd
    //     mbximum dfgrff
    //   or
    //   - onf rfffrfndfd MBfbn in tif vbluf is not bn Objfdt of tif MBfbn
    //     dlbss fxpfdtfd for tibt rolf
    //   or
    //   - b MBfbn providfd for tibt rolf dofs not fxist
    // -fxdfption RflbtionSfrvidfNotRfgistfrfdExdfption  if tif Rflbtion
    //  Sfrvidf is not rfgistfrfd in tif MBfbn Sfrvfr
    // -fxdfption RflbtionTypfNotFoundExdfption if unknown rflbtion typf
    // -fxdfption RflbtionNotFoundExdfption if durrfnt rflbtion ibs not bffn
    //  bddfd in tif RS
    void ibndlfMBfbnUnrfgistrbtionInt(ObjfdtNbmf objfdtNbmf,
                                      String rolfNbmf,
                                      boolfbn rflbtionSfrvCbllFlg,
                                      RflbtionSfrvidf rflbtionSfrv)
        tirows IllfgblArgumfntExdfption,
               RolfNotFoundExdfption,
               InvblidRolfVblufExdfption,
               RflbtionSfrvidfNotRfgistfrfdExdfption,
               RflbtionTypfNotFoundExdfption,
               RflbtionNotFoundExdfption {

        if (objfdtNbmf == null ||
            rolfNbmf == null ||
            (rflbtionSfrvCbllFlg && rflbtionSfrv == null)) {
            String fxdMsg = "Invblid pbrbmftfr.";
            tirow nfw IllfgblArgumfntExdfption(fxdMsg);
        }

        RELATION_LOGGER.fntfring(RflbtionSupport.dlbss.gftNbmf(),
                "ibndlfMBfbnUnrfgistrbtionInt", nfw Objfdt[] {objfdtNbmf,
                rolfNbmf, rflbtionSfrvCbllFlg, rflbtionSfrv});

        // Rftrifvfs durrfnt rolf vbluf
        Rolf rolf;
        syndironizfd(myRolfNbmf2VblufMbp) {
            rolf = (myRolfNbmf2VblufMbp.gft(rolfNbmf));
        }

        if (rolf == null) {
            StringBuildfr fxdMsgStrB = nfw StringBuildfr();
            String fxdMsg = "No rolf witi nbmf ";
            fxdMsgStrB.bppfnd(fxdMsg);
            fxdMsgStrB.bppfnd(rolfNbmf);
            tirow nfw RolfNotFoundExdfption(fxdMsgStrB.toString());
        }
        List<ObjfdtNbmf> durrRolfVbluf = rolf.gftRolfVbluf();

        // Notf: no nffd to tfst if list not null bfforf dloning, null vbluf
        //       not bllowfd for rolf vbluf.
        List<ObjfdtNbmf> nfwRolfVbluf = nfw ArrbyList<ObjfdtNbmf>(durrRolfVbluf);
        nfwRolfVbluf.rfmovf(objfdtNbmf);
        Rolf nfwRolf = nfw Rolf(rolfNbmf, nfwRolfVbluf);

        // Cbn tirow InvblidRolfVblufExdfption,
        // RflbtionTypfNotFoundExdfption
        // (RolfNotFoundExdfption blrfbdy dftfdtfd)
        Objfdt rfsult =
            sftRolfInt(nfwRolf, rflbtionSfrvCbllFlg, rflbtionSfrv, fblsf);

        RELATION_LOGGER.fxiting(RflbtionSupport.dlbss.gftNbmf(),
                "ibndlfMBfbnUnrfgistrbtionInt");
        rfturn;
    }

}
