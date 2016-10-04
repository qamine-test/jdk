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

import jbvb.util.List;
import jbvb.util.Mbp;

import jbvbx.mbnbgfmfnt.ObjfdtNbmf;

/**
 * Tiis intfrfbdf ibs to bf implfmfntfd by bny MBfbn dlbss fxpfdtfd to
 * rfprfsfnt b rflbtion mbnbgfd using tif Rflbtion Sfrvidf.
 * <P>Simplf rflbtions, i.f. ibving only rolfs, no propfrtifs or mftiods, dbn
 * bf drfbtfd dirfdtly by tif Rflbtion Sfrvidf (rfprfsfntfd bs RflbtionSupport
 * objfdts, intfrnblly ibndlfd by tif Rflbtion Sfrvidf).
 * <P>If tif usfr wbnts to rfprfsfnt morf domplfx rflbtions, involving
 * propfrtifs bnd/or mftiods, if ibs to providf iis own dlbss implfmfnting tif
 * Rflbtion intfrfbdf. Tiis dbn bf bdiifvfd fitifr by inifriting from
 * RflbtionSupport dlbss, or by implfmfnting tif intfrfbdf (fully or dflfgbtion to
 * b RflbtionSupport objfdt mfmbfr).
 * <P>Spfdifying sudi usfr rflbtion dlbss is to introdudf propfrtifs bnd/or
 * mftiods. Tiosf ibvf to bf fxposfd for rfmotf mbnbgfmfnt. So tiis mfbns tibt
 * bny usfr rflbtion dlbss must bf b MBfbn dlbss.
 *
 * @sindf 1.5
 */
publid intfrfbdf Rflbtion {

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
               RflbtionSfrvidfNotRfgistfrfdExdfption;

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
               RflbtionSfrvidfNotRfgistfrfdExdfption;

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
               RolfNotFoundExdfption;

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
        tirows RflbtionSfrvidfNotRfgistfrfdExdfption;

    /**
     * Rfturns bll rolfs in tif rflbtion witiout difdking rfbd modf.
     *
     * @rfturn b RolfList.
     */
    publid RolfList rftrifvfAllRolfs();

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
     * <P>- b MBfbn providfd for tibt rolf dofs not fxist.
     * @fxdfption RflbtionSfrvidfNotRfgistfrfdExdfption  if tif Rflbtion
     * Sfrvidf is not rfgistfrfd in tif MBfbn Sfrvfr
     * @fxdfption RflbtionTypfNotFoundExdfption  if tif rflbtion typf ibs not
     * bffn dfdlbrfd in tif Rflbtion Sfrvidf.
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
               RflbtionNotFoundExdfption;

    /**
     * Sfts tif givfn rolfs.
     * <P>Will difdk tif rolf bddording to its dorrfsponding rolf dffinition
     * providfd in rflbtion's rflbtion typf
     * <P>Will sfnd onf notifidbtion (RflbtionNotifidbtion witi typf
     * RELATION_BASIC_UPDATE or RELATION_MBEAN_UPDATE, dfpfnding if tif
     * rflbtion is b MBfbn or not) pfr updbtfd rolf.
     *
     * @pbrbm rolfList  list of rolfs to bf sft
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
    publid RolfRfsult sftRolfs(RolfList rolfList)
        tirows IllfgblArgumfntExdfption,
               RflbtionSfrvidfNotRfgistfrfdExdfption,
               RflbtionTypfNotFoundExdfption,
               RflbtionNotFoundExdfption;

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
               RflbtionNotFoundExdfption;

    /**
     * Rftrifvfs MBfbns rfffrfndfd in tif vbrious rolfs of tif rflbtion.
     *
     * @rfturn b HbsiMbp mbpping:
     * <P> ObjfdtNbmf {@litfrbl ->} ArrbyList of String (rolf nbmfs)
     */
    publid Mbp<ObjfdtNbmf,List<String>> gftRfffrfndfdMBfbns();

    /**
     * Rfturns nbmf of bssodibtfd rflbtion typf.
     *
     * @rfturn tif nbmf of tif rflbtion typf.
     */
    publid String gftRflbtionTypfNbmf();

    /**
     * Rfturns ObjfdtNbmf of tif Rflbtion Sfrvidf ibndling tif rflbtion.
     *
     * @rfturn tif ObjfdtNbmf of tif Rflbtion Sfrvidf.
     */
    publid ObjfdtNbmf gftRflbtionSfrvidfNbmf();

    /**
     * Rfturns rflbtion idfntififr (usfd to uniqufly idfntify tif rflbtion
     * insidf tif Rflbtion Sfrvidf).
     *
     * @rfturn tif rflbtion id.
     */
    publid String gftRflbtionId();
}
