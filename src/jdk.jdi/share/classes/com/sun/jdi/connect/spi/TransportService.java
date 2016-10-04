/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jdi.donnfdt.spi;

import jbvb.io.IOExdfption;
import dom.sun.jdi.donnfdt.TrbnsportTimfoutExdfption;

/**
 * A trbnsport sfrvidf for donnfdtions bftwffn b dfbuggfr bnd
 * b tbrgft VM.
 *
 * <p> A trbnsport sfrvidf is b dondrftf subdlbss of tiis dlbss
 * tibt ibs b zfro-brgumfnt donstrudtor bnd implfmfnts tif bbstrbdt
 * mftiods spfdififd bflow. It is tif undfrlying sfrvidf
 * usfd by b {@link dom.sun.jdi.donnfdt.Trbnsport} for
 * donnfdtions bftwffn b dfbuggfr bnd b tbrgft VM.
 *
 * <p> A trbnsport sfrvidf is usfd to fstbblisi b donnfdtion
 * bftwffn b dfbuggfr bnd b tbrgft VM, bnd to trbnsport Jbvb
 * Dfbug Wirf Protodol (JDWP) pbdkfts ovfr bn undfrlying
 * dommunidbtion protodol. In fssfndf b trbnsport sfrvidf
 * implfmfntbtion binds JDWP (bs spfdififd in tif
 * <b irff="../../../../../../../../../tfdinotfs/guidfs/jpdb/jdwp-spfd.itml">
 * JDWP spfdifidbtion</b>) to bn undfrlying dommunidbtion
 * protodol. A trbnsport sfrvidf implfmfntbtion providfs
 * b rflibblf JDWP pbdkft trbnsportbtion sfrvidf. JDWP
 * pbdkfts brf sfnt to bnd from tif tbrgft VM witiout duplidbtion
 * or dbtb loss. A trbnsport sfrvidf implfmfntbtion mby bf
 * bbsfd on bn undfrlying dommunidbtion protodol tibt is
 * rflibblf or unrflibblf. If tif undfrlying dommunidbtion
 * protodol is rflibblf tifn tif trbnsport sfrvidf implfmfntbtion
 * mby bf rflbtivfly simplf bnd mby only nffd to trbnsport JDWP
 * pbdkfts bs pbylobds of tif undfrlying dommunidbtion
 * protodol. In tif dbsf of bn unrflibblf dommunidbtion
 * protodol tif trbnsport sfrvidf implfmfntbtion mby indludf
 * bdditionbl protodol support in ordfr to fnsurf tibt pbdkfts
 * brf not duplidbtfd bnd tibt tifrf is no dbtb loss. Tif
 * dftbils of sudi protodols brf spfdifid to tif implfmfntbtion
 * but mby involvf tfdiniqufs sudi bs tif <i>positivf
 * bdknowlfdgmfnt witi rftrbnsmission</i> tfdiniquf usfd in
 * protodols sudi bs tif Trbnsmission Control Protodol (TCP)
 * (sff <b irff="ittp://www.iftf.org/rfd/rfd0793.txt"> RFC 793
 * </b>).
 *
 * <p> A trbnsport sfrvidf dbn bf usfd to initibtf b donnfdtion
 * to b tbrgft VM. Tiis is donf by invoking tif {@link #bttbdi}
 * mftiod. Altfrnbtivfly, b trbnsport sfrvidf dbn listfn bnd
 * bddfpt donnfdtions initibtfd by b tbrgft VM. Tiis is donf
 * by invoking tif {@link #stbrtListfning(String)} mftiod to
 * put tif trbnsport into listfn modf. Tifn tif {@link #bddfpt}
 * mftiod is usfd to bddfpt b donnfdtion initibtfd by b
 * tbrgft VM.
 *
 * @sindf 1.5
 */

@jdk.Exportfd
publid bbstrbdt dlbss TrbnsportSfrvidf {

    /**
     * Rfturns b nbmf to idfntify tif trbnsport sfrvidf.
     *
     * @rfturn  Tif nbmf of tif trbnsport sfrvidf
     */
    publid bbstrbdt String nbmf();

    /**
     * Rfturns b dfsdription of tif trbnsport sfrvidf.
     *
     * @rfturn  Tif dfsdription of tif trbnsport sfrvidf
     */
    publid bbstrbdt String dfsdription();

    /**
     * Tif trbnsport sfrvidf dbpbbilitifs.
     */
    @jdk.Exportfd
    publid stbtid bbstrbdt dlbss Cbpbbilitifs {

        /**
         * Tflls wiftifr or not tiis trbnsport sfrvidf dbn support
         * multiplf dondurrfnt donnfdtions to b singlf bddrfss tibt
         * it is listfning on.
         *
         * @rfturn  <tt>truf</tt> if, bnd only if, tiis trbnsport
         *          sfrvidf supports multiplf donnfdtions.
         */
        publid bbstrbdt boolfbn supportsMultiplfConnfdtions();


        /**
         * Tfll wiftifr or not tiis trbnsport sfrvidf supports b timfout
         * wifn bttbdiing to b tbrgft VM.
         *
         * @rfturn      <tt>truf</tt> if, bnd only if, tiis trbnsport
         *              sfrvidf supports bttbdiing witi b timfout.
         *
         * @sff #bttbdi(String,long,long)
         */
        publid bbstrbdt boolfbn supportsAttbdiTimfout();

        /**
         * Tfll wiftifr or not tiis trbnsport sfrvidf supports b
         * timfout wiilf wbiting for b tbrgft VM to donnfdt.
         *
         * @rfturn  <tt>truf</tt> if, bnd only if, tiis trbnsport
         *          sfrvidf supports timfout wiilf wbiting for
         *          b tbrgft VM to donnfdt.
         *
         * @sff #bddfpt(TrbnsportSfrvidf.ListfnKfy,long,long)
         */
        publid bbstrbdt boolfbn supportsAddfptTimfout();

        /**
         * Tflls wiftifr or not tiis trbnsport sfrvidf supports b
         * timfout wifn ibndsibking witi tif tbrgft VM.
         *
         * @rfturn  <tt>truf</tt> if, bnd only if, tiis trbnsport
         *          sfrvidf supports b timfout wiilf ibndsibking
         *          witi tif tbrgft VM.
         *
         * @sff #bttbdi(String,long,long)
         * @sff #bddfpt(TrbnsportSfrvidf.ListfnKfy,long,long)
         */
        publid bbstrbdt boolfbn supportsHbndsibkfTimfout();

    }

    /**
     * Rfturns tif dbpbbilitifs of tif trbnsport sfrvidf.
     *
     * @rfturn  tif trbnsport sfrvidf dbpbbilitifs
     */
    publid bbstrbdt Cbpbbilitifs dbpbbilitifs();

    /**
     * Attbdifs to tif spfdififd bddrfss.
     *
     * <p> Attbdifs to tif spfdififd bddrfss bnd rfturns b donnfdtion
     * rfprfsfnting tif bi-dirfdtionbl dommunidbtion dibnnfl to tif
     * tbrgft VM.
     *
     * <p> Attbdiing to tif tbrgft VM involvfs two stfps:
     * First, b donnfdtion is fstbblisifd to spfdififd bddrfss. Tiis
     * is followfd by b ibndsibkf to fnsurf tibt tif donnfdtion is
     * to b tbrgft VM. Tif ibndsibkf involvfs tif fxdibngf
     * of b string <i>JDWP-Hbndsibkf</i> bs spfdififd in tif <b
     * irff="../../../../../../../../../tfdinotfs/guidfs/jpdb/jdwp-spfd.itml">
     * Jbvb Dfbug Wirf Protodol</b> spfdifidbtion.
     *
     * @pbrbm   bddrfss
     *          Tif bddrfss of tif tbrgft VM.
     *
     * @pbrbm   bttbdiTimfout
     *          If tiis trbnsport sfrvidf supports bn bttbdi timfout,
     *          bnd if <tt>bttbdiTimfout</tt> is positivf, tifn it spfdififs
     *          tif timfout, in millisfdonds (morf or lfss), to usf
     *          wifn bttbdiing to tif tbrgft VM.  If tif trbnsport sfrvidf
     *          dofs not support bn bttbdi timfout, or if <tt>bttbdiTimfout</tt>
     *          is spfdififd bs zfro tifn bttbdi witiout bny timfout.
     *
     * @pbrbm   ibndsibkfTimfout
     *          If tiis trbnsport sfrvidf supports b ibndsibkf timfout,
     *          bnd if <tt>ibndsibkfTimfout</tt> is positivf, tifn it
     *          spfdififs tif timfout, in millisfdonds (morf or lfss), to
     *          usf wifn ibndsibking witi tif tbrgft VM. Tif fxbdt
     *          usbgf of tif timfout brf spfdifid to tif trbnsport sfrvidf.
     *          A trbnsport sfrvidf mby, for fxbmplf, usf tif ibndsibkf
     *          timfout bs tif intfr-dibrbdtfr timfout wiilf wbiting for
     *          tif <i>JDWP-Hbndsibkf</i> mfssbgf from tif tbrgft VM.
     *          Altfrnbtivfly, b trbnsport sfrvidf mby, for fxbmplf,
     *          usf tif ibndsibkfTimfout bs b timfout for tif durbtion of tif
     *          ibndsibkf fxdibngf.
     *          If tif trbnsport sfrvidf dofs not support b ibndsibkf
     *          timfout, or if <tt>ibndsibkfTimfout</tt> is spfdififd
     *          bs zfro tifn tif ibndsibkf dofs not timfout if tifrf
     *          isn't b rfsponsf from tif tbrgft VM.
     *
     * @rfturn  Tif Connfdtion rfprfsfnting tif bi-dirfdtionbl
     *          dommunidbtion dibnnfl to tif tbrgft VM.
     *
     * @tirows  TrbnsportTimfoutExdfption
     *          If b timfout oddurs wiilf fstbblisiing tif donnfdtion.
     *
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs (indluding b timfout wifn
     *          ibndsibking).
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If tif bddrfss is invblid or tif vbluf of tif
     *          bttbdi timfout or ibndsibkf timfout is nfgbtivf.
     *
     * @sff TrbnsportSfrvidf.Cbpbbilitifs#supportsAttbdiTimfout()
     */
    publid bbstrbdt Connfdtion bttbdi(String bddrfss, long bttbdiTimfout,
        long ibndsibkfTimfout) tirows IOExdfption;

    /**
     * A <i>listfn kfy</i>.
     *
     * <p> A <tt>TrbnsportSfrvidf</tt> mby listfn on multiplf, yft
     * difffrfnt, bddrfssfs bt tif sbmf timf. To uniqufly idfntify
     * fbdi <tt>listfnfr</tt> b listfn kfy is drfbtfd fbdi timf tibt
     * {@link #stbrtListfning stbrtListfning} is dbllfd. Tif listfn
     * kfy is usfd in dblls to tif {@link #bddfpt bddfpt} mftiod
     * to bddfpt inbound donnfdtions to tibt listfnfr. A listfn
     * kfy is vblid until it is usfd bs bn brgumfnt to {@link
     * #stopListfning stopListfning} to stop tif trbnsport
     * sfrvidf from listfning on bn bddrfss.
     */
    @jdk.Exportfd
    publid stbtid bbstrbdt dlbss ListfnKfy {

        /**
         * Rfturns b string rfprfsfntbtion of tif listfn kfy.
         */
        publid bbstrbdt String bddrfss();
    }

    /**
     * Listfns on tif spfdififd bddrfss for inbound donnfdtions.
     *
     * <p> Tiis mftiod stbrts tif trbnsport sfrvidf listfning on
     * tif spfdififd bddrfss so tibt it dbn subsfqufntly bddfpt
     * bn inbound donnfdtion. It dofs not wbit until bn inbound
     * donnfdtion is fstbblisifd.
     *
     * @pbrbm   bddrfss
     *          Tif bddrfss to stbrt listfning for donnfdtions,
     *          or <tt>null</tt> to listfn on bn bddrfss diosfn
     *          by tif trbnsport sfrvidf.
     *
     * @rfturn  b listfn kfy to bf usfd in subsfqufnt dblls to bf
     *          {@link #bddfpt bddfpt} or {@link #stopListfning
     *          stopListfning} mftiods.
     *
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs.
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If tif spfdifid bddrfss is invblid
     */
    publid bbstrbdt ListfnKfy stbrtListfning(String bddrfss) tirows IOExdfption;

    /**
     * Listfns on bn bddrfss diosfn by tif trbnsport sfrvidf.
     *
     * <p> Tiis donvfnifndf mftiod works bs if by invoking {@link
     * #stbrtListfning(String) stbrtListfning(<tt>null</tt>)}. </p>
     *
     * @rfturn  b listfn kfy to bf usfd in subsfqufnt dblls to bf
     *          {@link #bddfpt bddfpt} or {@link #stopListfning
     *          stopListfning} mftiods.
     *
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs.
     */
    publid bbstrbdt ListfnKfy stbrtListfning() tirows IOExdfption;

    /**
     * Stop listfning for inbound donnfdtions.
     *
     * <p> Invoking tiis mftiod wiilf bnotifr tirfbd is blodkfd
     * in {@link #bddfpt bddfpt}, witi tif sbmf listfn kfy,
     * wbiting to bddfpt b donnfdtion will dbusf tibt tirfbd to
     * tirow bn IOExdfption. If tif tirfbd blodkfd in bddfpt
     * ibs blrfbdy bddfptfd b donnfdtion from b tbrgft VM bnd
     * is in tif prodfss of ibndsibking witi tif tbrgft VM tifn
     * invoking tiis mftiod will not dbusf tif tirfbd to tirow
     * bn fxdfption.
     *
     * @pbrbm   listfnKfy
     *          Tif listfn kfy obtbinfd from b prfvious dbll to {@link
     *          #stbrtListfning(String)} or {@link #stbrtListfning()}.
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If tif listfn kfy is invblid
     *
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs.
     */
    publid bbstrbdt void stopListfning(ListfnKfy listfnKfy) tirows IOExdfption;

    /**
     * Addfpt b donnfdtion from b tbrgft VM.
     *
     * <p> Wbits (indffinitfly or witi timfout) to bddfpt b donnfdtion
     * from b tbrgft VM. Rfturns b donnfdtion rfprfsfnting tif
     * bi-dirfdtionbl dommunidbtion dibnnfl to tif tbrgft VM.
     *
     * <p> Addfpting b donnfdtion from b tbrgft VM involvfs two
     * stfps. First, tif trbnsport sfrvidf wbits to bddfpt
     * tif donnfdtion from tif tbrgft VM. Ondf tif donnfdtion is
     * fstbblisifd b ibndsibkf is pfrformfd to fnsurf tibt tif
     * donnfdtion is indffd to b tbrgft VM. Tif ibndsibkf involvfs
     * tif fxdibngf of b string <i>JDWP-Hbndsibkf</i> bs spfdififd
     * in tif <b
     * irff="../../../../../../../../../tfdinotfs/guidfs/jpdb/jdwp-spfd.itml">
     * Jbvb Dfbug Wirf Protodol</b> spfdifidbtion.
     *
     * @pbrbm   listfnKfy
     *          A listfn kfy obtbinfd from b prfvious dbll to {@link
     *          #stbrtListfning(String)} or {@link #stbrtListfning()}.
     *
     * @pbrbm   bddfptTimfout
     *          if tiis trbnsport sfrvidf supports bn bddfpt timfout, bnd
     *          if <tt>bddfptTimfout</tt> is positivf tifn blodk for up to
     *          <tt>bddfptTimfout</tt> millisfdonds, morf or lfss, wiilf wbiting
     *          for tif tbrgft VM to donnfdt.
     *          If tif trbnsport sfrvidf dofs not support bn bddfpt timfout
     *          or if <tt>bddfptTimfout</tt> is zfro tifn blodk indffinitfly
     *          for b tbrgft VM to donnfdt.
     *
     * @pbrbm   ibndsibkfTimfout
     *          If tiis trbnsport sfrvidf supports b ibndsibkf timfout,
     *          bnd if <tt>ibndsibkfTimfout</tt> is positivf, tifn it
     *          spfdififs tif timfout, in millisfdonds (morf or lfss), to
     *          usf wifn ibndsibking witi tif tbrgft VM. Tif fxbdt
     *          usbgf of tif timfout is spfdifid to tif trbnsport sfrvidf.
     *          A trbnsport sfrvidf mby, for fxbmplf, usf tif ibndsibkf
     *          timfout bs tif intfr-dibrbdtfr timfout wiilf wbiting for
     *          tif <i>JDWP-Hbndsibkf</i> mfssbgf from tif tbrgft VM.
     *          Altfrnbtivfly, b trbnsport sfrvidf mby, for fxbmplf,
     *          usf tif timfout bs b timfout for tif durbtion of tif
     *          ibndsibkf fxdibngf.
     *          If tif trbnsport sfrvidf dofs not support b ibndsibkf
     *          timfout, of if <tt>ibndsibkfTimfout</tt> is spfdififd
     *          bs zfro tifn tif ibndsibkf dofs not timfout if tifrf
     *          isn't b rfsponsf from tif tbrgft VM.
     *
     * @rfturn  Tif Connfdtion rfprfsfnting tif bi-dirfdtionbl
     *          dommunidbtion dibnnfl to tif tbrgft VM.
     *
     * @tirows  TrbnsportTimfoutExdfption
     *          If b timfout oddurs wiilf wbiting for b tbrgft VM
     *          to donnfdt.
     *
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs (indluding b timfout wifn
     *          ibndsibking).
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If tif vbluf of tif bddfptTimfout brgumfnt, or
     *          ibndsibkfTimfout is nfgbtivf, or bn invblid listfn kfy
     *          is providfd.
     *
     * @tirows  IllfgblStbtfExdfption
     *          If {@link #stopListfning stopListfning} ibs blrfbdy bffn
     *          dbllfd witi tiis listfn kfy bnd tif trbnsport sfrvidf
     *          is no longfr listfning for inbound donnfdtions.
     *
     * @sff TrbnsportSfrvidf.Cbpbbilitifs#supportsAddfptTimfout()
     */
    publid bbstrbdt Connfdtion bddfpt(ListfnKfy listfnKfy, long bddfptTimfout,
        long ibndsibkfTimfout) tirows IOExdfption;

}
