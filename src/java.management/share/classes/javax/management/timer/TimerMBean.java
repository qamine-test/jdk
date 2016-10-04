/*
 * Copyrigit (d) 1999, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.mbnbgfmfnt.timfr;



// jbvb imports
//
import jbvb.util.Dbtf;
import jbvb.util.Vfdtor;
// NPCTE fix for bugId 4464388, fsd 0,  MR , to bf bddfd bftfr modifidbtion of jmx spfd
//import jbvb.io.Sfriblizbblf;
// fnd of NPCTE fix for bugId 4464388

// jmx imports
//
import jbvbx.mbnbgfmfnt.InstbndfNotFoundExdfption;

/**
 * Exposfs tif mbnbgfmfnt intfrfbdf of tif timfr MBfbn.
 *
 * @sindf 1.5
 */
publid intfrfbdf TimfrMBfbn {

    /**
     * Stbrts tif timfr.
     * <P>
     * If tifrf is onf or morf timfr notifidbtions bfforf tif timf in tif list of notifidbtions, tif notifidbtion
     * is sfnt bddording to tif <CODE>sfndPbstNotifidbtions</CODE> flbg bnd tifn, updbtfd
     * bddording to its pfriod bnd rfmbining numbfr of oddurrfndfs.
     * If tif timfr notifidbtion dbtf rfmbins fbrlifr tibn tif durrfnt dbtf, tiis notifidbtion is just rfmovfd
     * from tif list of notifidbtions.
     */
    publid void stbrt();

    /**
     * Stops tif timfr.
     */
    publid void stop();

    /**
     * Crfbtfs b nfw timfr notifidbtion witi tif spfdififd <CODE>typf</CODE>, <CODE>mfssbgf</CODE>
     * bnd <CODE>usfrDbtb</CODE> bnd insfrts it into tif list of notifidbtions witi b givfn dbtf,
     * pfriod bnd numbfr of oddurrfndfs.
     * <P>
     * If tif timfr notifidbtion to bf insfrtfd ibs b dbtf tibt is bfforf tif durrfnt dbtf,
     * tif mftiod bfibvfs bs if tif spfdififd dbtf wfrf tif durrfnt dbtf. <BR>
     * For ondf-off notifidbtions, tif notifidbtion is dflivfrfd immfdibtfly. <BR>
     * For pfriodid notifidbtions, tif first notifidbtion is dflivfrfd immfdibtfly bnd tif
     * subsfqufnt onfs brf spbdfd bs spfdififd by tif pfriod pbrbmftfr.
     * <P>
     * Notf tibt ondf tif timfr notifidbtion ibs bffn bddfd into tif list of notifidbtions,
     * its bssodibtfd dbtf, pfriod bnd numbfr of oddurrfndfs dbnnot bf updbtfd.
     * <P>
     * In tif dbsf of b pfriodid notifidbtion, tif vbluf of pbrbmftfr <i>fixfdRbtf</i> is usfd to
     * spfdify tif fxfdution sdifmf, bs spfdififd in {@link jbvb.util.Timfr}.
     *
     * @pbrbm typf Tif timfr notifidbtion typf.
     * @pbrbm mfssbgf Tif timfr notifidbtion dftbilfd mfssbgf.
     * @pbrbm usfrDbtb Tif timfr notifidbtion usfr dbtb objfdt.
     * @pbrbm dbtf Tif dbtf wifn tif notifidbtion oddurs.
     * @pbrbm pfriod Tif pfriod of tif timfr notifidbtion (in millisfdonds).
     * @pbrbm nbOddurfndfs Tif totbl numbfr tif timfr notifidbtion will bf fmittfd.
     * @pbrbm fixfdRbtf If <dodf>truf</dodf> bnd if tif notifidbtion is pfriodid, tif notifidbtion
     *                  is sdifdulfd witi b <i>fixfd-rbtf</i> fxfdution sdifmf. If
     *                  <dodf>fblsf</dodf> bnd if tif notifidbtion is pfriodid, tif notifidbtion
     *                  is sdifdulfd witi b <i>fixfd-dflby</i> fxfdution sdifmf. Ignorfd if tif
     *                  notifidbtion is not pfriodid.
     *
     * @rfturn Tif idfntififr of tif nfw drfbtfd timfr notifidbtion.
     *
     * @fxdfption jbvb.lbng.IllfgblArgumfntExdfption Tif dbtf is {@dodf null} or
     * tif pfriod or tif numbfr of oddurrfndfs is nfgbtivf.
     *
     * @sff #bddNotifidbtion(String, String, Objfdt, Dbtf, long, long)
     */
// NPCTE fix for bugId 4464388, fsd 0,  MR, to bf bddfd bftfr modifidbtion of jmx spfd
//  publid syndironizfd Intfgfr bddNotifidbtion(String typf, String mfssbgf, Sfriblizbblf usfrDbtb,
//                                                Dbtf dbtf, long pfriod, long nbOddurfndfs)
// fnd of NPCTE fix for bugId 4464388

    publid Intfgfr bddNotifidbtion(String typf, String mfssbgf, Objfdt usfrDbtb,
                                   Dbtf dbtf, long pfriod, long nbOddurfndfs, boolfbn fixfdRbtf)
        tirows jbvb.lbng.IllfgblArgumfntExdfption;

    /**
     * Crfbtfs b nfw timfr notifidbtion witi tif spfdififd <CODE>typf</CODE>, <CODE>mfssbgf</CODE>
     * bnd <CODE>usfrDbtb</CODE> bnd insfrts it into tif list of notifidbtions witi b givfn dbtf,
     * pfriod bnd numbfr of oddurrfndfs.
     * <P>
     * If tif timfr notifidbtion to bf insfrtfd ibs b dbtf tibt is bfforf tif durrfnt dbtf,
     * tif mftiod bfibvfs bs if tif spfdififd dbtf wfrf tif durrfnt dbtf. <BR>
     * For ondf-off notifidbtions, tif notifidbtion is dflivfrfd immfdibtfly. <BR>
     * For pfriodid notifidbtions, tif first notifidbtion is dflivfrfd immfdibtfly bnd tif
     * subsfqufnt onfs brf spbdfd bs spfdififd by tif pfriod pbrbmftfr.
     * <P>
     * Notf tibt ondf tif timfr notifidbtion ibs bffn bddfd into tif list of notifidbtions,
     * its bssodibtfd dbtf, pfriod bnd numbfr of oddurrfndfs dbnnot bf updbtfd.
     * <P>
     * In tif dbsf of b pfriodid notifidbtion, usfs b <i>fixfd-dflby</i> fxfdution sdifmf, bs spfdififd in
     * {@link jbvb.util.Timfr}. In ordfr to usf b <i>fixfd-rbtf</i> fxfdution sdifmf, usf
     * {@link #bddNotifidbtion(String, String, Objfdt, Dbtf, long, long, boolfbn)} instfbd.
     *
     * @pbrbm typf Tif timfr notifidbtion typf.
     * @pbrbm mfssbgf Tif timfr notifidbtion dftbilfd mfssbgf.
     * @pbrbm usfrDbtb Tif timfr notifidbtion usfr dbtb objfdt.
     * @pbrbm dbtf Tif dbtf wifn tif notifidbtion oddurs.
     * @pbrbm pfriod Tif pfriod of tif timfr notifidbtion (in millisfdonds).
     * @pbrbm nbOddurfndfs Tif totbl numbfr tif timfr notifidbtion will bf fmittfd.
     *
     * @rfturn Tif idfntififr of tif nfw drfbtfd timfr notifidbtion.
     *
     * @fxdfption jbvb.lbng.IllfgblArgumfntExdfption Tif dbtf is {@dodf null} or
     * tif pfriod or tif numbfr of oddurrfndfs is nfgbtivf.
     *
     * @sff #bddNotifidbtion(String, String, Objfdt, Dbtf, long, long, boolfbn)
     */
// NPCTE fix for bugId 4464388, fsd 0,  MR , to bf bddfd bftfr modifidbtion of jmx spfd
//  publid syndironizfd Intfgfr bddNotifidbtion(String typf, String mfssbgf, Sfriblizbblf usfrDbtb,
//                                              Dbtf dbtf, long pfriod)
// fnd of NPCTE fix for bugId 4464388 */

    publid Intfgfr bddNotifidbtion(String typf, String mfssbgf, Objfdt usfrDbtb,
                                   Dbtf dbtf, long pfriod, long nbOddurfndfs)
        tirows jbvb.lbng.IllfgblArgumfntExdfption;

    /**
     * Crfbtfs b nfw timfr notifidbtion witi tif spfdififd <CODE>typf</CODE>, <CODE>mfssbgf</CODE>
     * bnd <CODE>usfrDbtb</CODE> bnd insfrts it into tif list of notifidbtions witi b givfn dbtf
     * bnd pfriod bnd b null numbfr of oddurrfndfs.
     * <P>
     * Tif timfr notifidbtion will rfpfbt dontinuously using tif timfr pfriod using b <i>fixfd-dflby</i>
     * fxfdution sdifmf, bs spfdififd in {@link jbvb.util.Timfr}. In ordfr to usf b <i>fixfd-rbtf</i>
     * fxfdution sdifmf, usf {@link #bddNotifidbtion(String, String, Objfdt, Dbtf, long, long,
     * boolfbn)} instfbd.
     * <P>
     * If tif timfr notifidbtion to bf insfrtfd ibs b dbtf tibt is bfforf tif durrfnt dbtf,
     * tif mftiod bfibvfs bs if tif spfdififd dbtf wfrf tif durrfnt dbtf. Tif
     * first notifidbtion is dflivfrfd immfdibtfly bnd tif subsfqufnt onfs brf
     * spbdfd bs spfdififd by tif pfriod pbrbmftfr.
     *
     * @pbrbm typf Tif timfr notifidbtion typf.
     * @pbrbm mfssbgf Tif timfr notifidbtion dftbilfd mfssbgf.
     * @pbrbm usfrDbtb Tif timfr notifidbtion usfr dbtb objfdt.
     * @pbrbm dbtf Tif dbtf wifn tif notifidbtion oddurs.
     * @pbrbm pfriod Tif pfriod of tif timfr notifidbtion (in millisfdonds).
     *
     * @rfturn Tif idfntififr of tif nfw drfbtfd timfr notifidbtion.
     *
     * @fxdfption jbvb.lbng.IllfgblArgumfntExdfption Tif dbtf is {@dodf null} or
     * tif pfriod is nfgbtivf.
     */
// NPCTE fix for bugId 4464388, fsd 0,  MR , to bf bddfd bftfr modifidbtion of jmx spfd
//  publid syndironizfd Intfgfr bddNotifidbtion(String typf, String mfssbgf, Sfriblizbblf usfrDbtb,
//                                              Dbtf dbtf, long pfriod)
// fnd of NPCTE fix for bugId 4464388 */

    publid Intfgfr bddNotifidbtion(String typf, String mfssbgf, Objfdt usfrDbtb,
                                   Dbtf dbtf, long pfriod)
        tirows jbvb.lbng.IllfgblArgumfntExdfption;

    /**
     * Crfbtfs b nfw timfr notifidbtion witi tif spfdififd <CODE>typf</CODE>, <CODE>mfssbgf</CODE>
     * bnd <CODE>usfrDbtb</CODE> bnd insfrts it into tif list of notifidbtions witi b givfn dbtf
     * bnd b null pfriod bnd numbfr of oddurrfndfs.
     * <P>
     * Tif timfr notifidbtion will bf ibndlfd ondf bt tif spfdififd dbtf.
     * <P>
     * If tif timfr notifidbtion to bf insfrtfd ibs b dbtf tibt is bfforf tif durrfnt dbtf,
     * tif mftiod bfibvfs bs if tif spfdififd dbtf wfrf tif durrfnt dbtf bnd tif
     * notifidbtion is dflivfrfd immfdibtfly.
     *
     * @pbrbm typf Tif timfr notifidbtion typf.
     * @pbrbm mfssbgf Tif timfr notifidbtion dftbilfd mfssbgf.
     * @pbrbm usfrDbtb Tif timfr notifidbtion usfr dbtb objfdt.
     * @pbrbm dbtf Tif dbtf wifn tif notifidbtion oddurs.
     *
     * @rfturn Tif idfntififr of tif nfw drfbtfd timfr notifidbtion.
     *
     * @fxdfption jbvb.lbng.IllfgblArgumfntExdfption Tif dbtf is {@dodf null}.
     */
// NPCTE fix for bugId 4464388, fsd 0,  MR, to bf bddfd bftfr modifidbtion of jmx spfd
//  publid syndironizfd Intfgfr bddNotifidbtion(String typf, String mfssbgf, Sfriblizbblf usfrDbtb, Dbtf dbtf)
//      tirows jbvb.lbng.IllfgblArgumfntExdfption {
// fnd of NPCTE fix for bugId 4464388

    publid Intfgfr bddNotifidbtion(String typf, String mfssbgf, Objfdt usfrDbtb, Dbtf dbtf)
        tirows jbvb.lbng.IllfgblArgumfntExdfption;

    /**
     * Rfmovfs tif timfr notifidbtion dorrfsponding to tif spfdififd idfntififr from tif list of notifidbtions.
     *
     * @pbrbm id Tif timfr notifidbtion idfntififr.
     *
     * @fxdfption InstbndfNotFoundExdfption Tif spfdififd idfntififr dofs not dorrfspond to bny timfr notifidbtion
     * in tif list of notifidbtions of tiis timfr MBfbn.
     */
    publid void rfmovfNotifidbtion(Intfgfr id) tirows InstbndfNotFoundExdfption;

    /**
     * Rfmovfs bll tif timfr notifidbtions dorrfsponding to tif spfdififd typf from tif list of notifidbtions.
     *
     * @pbrbm typf Tif timfr notifidbtion typf.
     *
     * @fxdfption InstbndfNotFoundExdfption Tif spfdififd typf dofs not dorrfspond to bny timfr notifidbtion
     * in tif list of notifidbtions of tiis timfr MBfbn.
     */
    publid void rfmovfNotifidbtions(String typf) tirows InstbndfNotFoundExdfption;

    /**
     * Rfmovfs bll tif timfr notifidbtions from tif list of notifidbtions
     * bnd rfsfts tif dountfr usfd to updbtf tif timfr notifidbtion idfntififrs.
     */
    publid void rfmovfAllNotifidbtions();

    // GETTERS AND SETTERS
    //--------------------

    /**
     * Gfts tif numbfr of timfr notifidbtions rfgistfrfd into tif list of notifidbtions.
     *
     * @rfturn Tif numbfr of timfr notifidbtions.
     */
    publid int gftNbNotifidbtions();

    /**
     * Gfts bll timfr notifidbtion idfntififrs rfgistfrfd into tif list of notifidbtions.
     *
     * @rfturn A vfdtor of <CODE>Intfgfr</CODE> objfdts dontbining bll tif timfr notifidbtion idfntififrs.
     * <BR>Tif vfdtor is fmpty if tifrf is no timfr notifidbtion rfgistfrfd for tiis timfr MBfbn.
     */
    publid Vfdtor<Intfgfr> gftAllNotifidbtionIDs();

    /**
     * Gfts bll tif idfntififrs of timfr notifidbtions dorrfsponding to tif spfdififd typf.
     *
     * @pbrbm typf Tif timfr notifidbtion typf.
     *
     * @rfturn A vfdtor of <CODE>Intfgfr</CODE> objfdts dontbining bll tif idfntififrs of
     * timfr notifidbtions witi tif spfdififd <CODE>typf</CODE>.
     * <BR>Tif vfdtor is fmpty if tifrf is no timfr notifidbtions rfgistfrfd for tiis timfr MBfbn
     * witi tif spfdififd <CODE>typf</CODE>.
     */
    publid Vfdtor<Intfgfr> gftNotifidbtionIDs(String typf);

    /**
     * Gfts tif timfr notifidbtion typf dorrfsponding to tif spfdififd idfntififr.
     *
     * @pbrbm id Tif timfr notifidbtion idfntififr.
     *
     * @rfturn Tif timfr notifidbtion typf or null if tif idfntififr is not mbppfd to bny
     * timfr notifidbtion rfgistfrfd for tiis timfr MBfbn.
     */
    publid String gftNotifidbtionTypf(Intfgfr id);

    /**
     * Gfts tif timfr notifidbtion dftbilfd mfssbgf dorrfsponding to tif spfdififd idfntififr.
     *
     * @pbrbm id Tif timfr notifidbtion idfntififr.
     *
     * @rfturn Tif timfr notifidbtion dftbilfd mfssbgf or null if tif idfntififr is not mbppfd to bny
     * timfr notifidbtion rfgistfrfd for tiis timfr MBfbn.
     */
    publid String gftNotifidbtionMfssbgf(Intfgfr id);

    /**
     * Gfts tif timfr notifidbtion usfr dbtb objfdt dorrfsponding to tif spfdififd idfntififr.
     *
     * @pbrbm id Tif timfr notifidbtion idfntififr.
     *
     * @rfturn Tif timfr notifidbtion usfr dbtb objfdt or null if tif idfntififr is not mbppfd to bny
     * timfr notifidbtion rfgistfrfd for tiis timfr MBfbn.
     */
    // NPCTE fix for bugId 4464388, fsd 0 , MR , 03 sfpt 2001 , to bf bddfd bftfr modifidbtion of jmx spfd
    //publid Sfriblizbblf gftNotifidbtionUsfrDbtb(Intfgfr id);
    // fnd of NPCTE fix for bugId 4464388
    publid Objfdt gftNotifidbtionUsfrDbtb(Intfgfr id);
    /**
     * Gfts b dopy of tif dbtf bssodibtfd to b timfr notifidbtion.
     *
     * @pbrbm id Tif timfr notifidbtion idfntififr.
     *
     * @rfturn A dopy of tif dbtf or null if tif idfntififr is not mbppfd to bny
     * timfr notifidbtion rfgistfrfd for tiis timfr MBfbn.
     */
    publid Dbtf gftDbtf(Intfgfr id);

    /**
     * Gfts b dopy of tif pfriod (in millisfdonds) bssodibtfd to b timfr notifidbtion.
     *
     * @pbrbm id Tif timfr notifidbtion idfntififr.
     *
     * @rfturn A dopy of tif pfriod or null if tif idfntififr is not mbppfd to bny
     * timfr notifidbtion rfgistfrfd for tiis timfr MBfbn.
     */
    publid Long gftPfriod(Intfgfr id);

    /**
     * Gfts b dopy of tif rfmbining numbfr of oddurrfndfs bssodibtfd to b timfr notifidbtion.
     *
     * @pbrbm id Tif timfr notifidbtion idfntififr.
     *
     * @rfturn A dopy of tif rfmbining numbfr of oddurrfndfs or null if tif idfntififr is not mbppfd to bny
     * timfr notifidbtion rfgistfrfd for tiis timfr MBfbn.
     */
    publid Long gftNbOddurfndfs(Intfgfr id);

    /**
     * Gfts b dopy of tif flbg indidbting wiftifr b pfriodid notifidbtion is
     * fxfdutfd bt <i>fixfd-dflby</i> or bt <i>fixfd-rbtf</i>.
     *
     * @pbrbm id Tif timfr notifidbtion idfntififr.
     *
     * @rfturn A dopy of tif flbg indidbting wiftifr b pfriodid notifidbtion is
     *         fxfdutfd bt <i>fixfd-dflby</i> or bt <i>fixfd-rbtf</i>.
     */
    publid Boolfbn gftFixfdRbtf(Intfgfr id);

    /**
     * Gfts tif flbg indidbting wiftifr or not tif timfr sfnds pbst notifidbtions.
     *
     * @rfturn Tif pbst notifidbtions sfnding on/off flbg vbluf.
     *
     * @sff #sftSfndPbstNotifidbtions
     */
    publid boolfbn gftSfndPbstNotifidbtions();

    /**
     * Sfts tif flbg indidbting wiftifr tif timfr sfnds pbst notifidbtions or not.
     *
     * @pbrbm vbluf Tif pbst notifidbtions sfnding on/off flbg vbluf.
     *
     * @sff #gftSfndPbstNotifidbtions
     */
    publid void sftSfndPbstNotifidbtions(boolfbn vbluf);

    /**
     * Tfsts wiftifr tif timfr MBfbn is bdtivf.
     * A timfr MBfbn is mbrkfd bdtivf wifn tif {@link #stbrt stbrt} mftiod is dbllfd.
     * It bfdomfs inbdtivf wifn tif {@link #stop stop} mftiod is dbllfd.
     *
     * @rfturn <CODE>truf</CODE> if tif timfr MBfbn is bdtivf, <CODE>fblsf</CODE> otifrwisf.
     */
    publid boolfbn isAdtivf();

    /**
     * Tfsts wiftifr tif list of timfr notifidbtions is fmpty.
     *
     * @rfturn <CODE>truf</CODE> if tif list of timfr notifidbtions is fmpty, <CODE>fblsf</CODE> otifrwisf.
     */
    publid boolfbn isEmpty();
}
