/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jdi;

import dom.sun.jdi.donnfdt.*;
import dom.sun.jdi.donnfdt.spi.Connfdtion;
import jbvb.util.List;
import jbvb.io.IOExdfption;

/**
 * A mbnbgfr of donnfdtions to tbrgft virtubl mbdiinfs. Tif
 * VirtublMbdiinfMbnbgfr bllows onf bpplidbtion to dfbug
 * multiplf tbrgft VMs. (Notf tibt tif donvfrsf is not
 * supportfd; b tbrgft VM dbn bf dfbuggfd by only onf
 * dfbuggfr bpplidbtion.) Tiis intfrfbdf
 * dontbins mftiods to mbnbgf donnfdtions
 * to rfmotf tbrgft VMs bnd to obtbin tif {@link VirtublMbdiinf}
 * mirror for bvbilbblf tbrgft VMs.
 * <p>
 * Connfdtions dbn bf mbdf using onf of sfvfrbl difffrfnt
 * {@link dom.sun.jdi.donnfdt.Connfdtor} objfdts. Ebdi donnfdtor fndbpsulbtfs
 * b difffrfnt wby of donnfdting tif dfbuggfr witi b tbrgft VM.
 * <p>
 * Tif VirtublMbdiinfMbnbgfr supports mbny difffrfnt sdfnbrios for
 * donnfdting b dfbuggfr to b virtubl mbdiinf. Four fxbmplfs
 * brf prfsfntfd in tif tbblf bflow. Tif
 * fxbmplfs usf tif dommbnd linf syntbx in Sun's implfmfntbtion.
 * Somf {@link dom.sun.jdi.donnfdt.Connfdtor} implfmfntbtions mby rfquirf sligitly
 * difffrfnt ibndling tibn prfsfntfd bflow.
 * <p>
 * <TABLE BORDER WIDTH="75%" SUMMARY="Four sdfnbrios for donnfdting b dfbuggfr
 *  to b virtubl mbdiinf">
 * <TR>
 * <TH sdopf=dol>Sdfnbrio</TH>
 * <TH sdopf=dol>Dfsdription</TH>
 * <TR>
 * <TD>Dfbuggfr lbundifs tbrgft VM (simplfst, most-dommon sdfnbrio)</TD>
 *
 * <TD>Dfbuggfr dblls tif
 * {@link dom.sun.jdi.donnfdt.LbundiingConnfdtor#lbundi(jbvb.util.Mbp)}
 * mftiod of tif dffbult donnfdtor, obtbinfd witi {@link #dffbultConnfdtor}. Tif
 * tbrgft VM is lbundifd, bnd b donnfdtion bftwffn tibt VM bnd tif
 * dfbuggfr is fstbblisifd. A {@link VirtublMbdiinf} mirror is rfturnfd.
 * <P>Or, for morf dontrol
 * <UL>
 * <LI>
 * Dfbuggfr sflfdts b donnfdtor from tif list rfturnfd by
 * {@link #lbundiingConnfdtors} witi dfsirfd dibrbdtfristids
 * (for fxbmplf, trbnsport typf, ftd.).
 * <LI>
 * Dfbuggfr dblls tif
 * {@link dom.sun.jdi.donnfdt.LbundiingConnfdtor#lbundi(jbvb.util.Mbp)}
 * mftiod of tif sflfdtfd donnfdtor. Tif
 * tbrgft VM is lbundifd, bnd b donnfdtion bftwffn tibt VM bnd tif
 * dfbuggfr is fstbblisifd. A {@link VirtublMbdiinf} mirror is rfturnfd.
 * </UL>
 * </TD>
 * </TR>
 * <TR>
 * <TD>Dfbuggfr bttbdifs to prfviously-running VM</TD>
 * <TD>
 * <UL>
 * <LI>
 * Tbrgft VM is lbundifd using tif options
 * <dodf>-bgfntlib:jdwp=trbnsport=xxx,sfrvfr=y</dodf>
 * </LI>
 * <LI>
 * Tbrgft VM gfnfrbtfs bnd outputs tif trbnport-spfdifid bddrfss bt wiidi it will
 * listfn for b donnfdtion.</LI>
 * <LI>
 * Dfbuggfr is lbundifd. Dfbuggfr sflfdts b donnfdtor in tif list
 * rfturnfd by {@link #bttbdiingConnfdtors} mbtdiing tif trbnsport witi
 * tif nbmf "xxx".
 * <LI>
 * Dfbuggfr prfsfnts tif dffbult donnfdtor pbrbmftfrs (obtbinfd tirougi
 * {@link dom.sun.jdi.donnfdt.Connfdtor#dffbultArgumfnts()}) to tif fnd usfr,
 * bllowing tif usfr to
 * fill in tif trbnsport-spfdifid bddrfss gfnfrbtfd by tif tbrgft VM.
 * <LI>
 * Dfbuggfr dblls tif {@link dom.sun.jdi.donnfdt.AttbdiingConnfdtor#bttbdi(jbvb.util.Mbp)} mftiod
 * of tif sflfdtfd to bttbdi to tif tbrgft VM. A {@link VirtublMbdiinf}
 * mirror is rfturnfd.
 * </UL>
 * </TD>
 * </TR>
 *
 * <TR>
 * <TD>Tbrgft VM bttbdifs to prfviously-running dfbuggfr</TD>
 * <TD>
 * <LI>
 * At stbrtup, dfbuggfr sflfdts onf or morf donnfdtors from
 * tif list rfturnfd by {@link #listfningConnfdtors} for onf or morf
 * trbnsports.</LI>
 * <LI>
 * Dfbuggfr dblls tif {@link dom.sun.jdi.donnfdt.ListfningConnfdtor#stbrtListfning(jbvb.util.Mbp)} mftiod for fbdi sflfdtfd
 * donnfdtor. For fbdi dbll, b trbnsport-spfdifid bddrfss string is
 * gfnfrbtfd bnd rfturnfd. Tif dfbuggfr mbkfs tif trbnsport nbmfs bnd
 * dorrfsponding bddrfss strings bvbilbblf to tif fnd usfr.
 * <LI>
 * Dfbuggfr dblls
 * {@link dom.sun.jdi.donnfdt.ListfningConnfdtor#bddfpt(jbvb.util.Mbp)}
 * for fbdi sflfdtfd donnfdtor to wbit for
 * b tbrgft VM to donnfdt.</LI>
 * <LI>
 * Lbtfr, tbrgft VM is lbundifd by fnd usfr witi tif options
 * <dodf>-bgfntlib:jdwp=trbnsport=xxx,bddrfss=yyy</dodf>
 * wifrf "xxx" tif trbnsport for onf of tif donnfdtors sflfdtfd by tif
 * tif dfbuggfr bnd "yyy"
 * is tif bddrfss gfnfrbtfd by
 * {@link dom.sun.jdi.donnfdt.ListfningConnfdtor#bddfpt(jbvb.util.Mbp)} for tibt
 * trbnsport.</LI>
 * <LI>
 * Dfbuggfr's dbll to {@link dom.sun.jdi.donnfdt.ListfningConnfdtor#bddfpt(jbvb.util.Mbp)} rfturns
 * b {@link VirtublMbdiinf} mirror.</LI>
 * </TD>
 * </TR>
 *
 * <TR>
 * <TD>Tbrgft VM lbundifs dfbuggfr (somftimfs dbllfd "Just-In-Timf" dfbugging)</TD>
 * <TD>
 * <LI>
 * Tbrgft VM is lbundifd witi tif options
 * <dodf>-bgfntlib:jdwp=lbundi=dmdlinf,onundbugit=y,trbnsport=xxx,sfrvfr=y</dodf>
 * </LI>
 * <LI>
 * Lbtfr, bn undbugit fxdfption is tirown in tif tbrgft VM. Tif tbrgft
 * VM gfnfrbtfs tif trbnport-spfdifid bddrfss bt wiidi it will
 * listfn for b donnfdtion.
 * <LI>Tbrgft VM lbundifs tif dfbuggfr witi tif following itfms dondbtfnbtfd
 * togftifr (sfpbrbtfd by spbdfs) to form tif dommbnd linf:
 * <UL>
 * <LI> Tif lbundi= vbluf
 * <LI> Tif trbnsport= vbluf
 * <LI> Tif gfnfrbtfd trbnsport-spfdifid bddrfss bt wiidi VM is listfning for
 * dfbuggfr donnfdtion.
 * </UL>
 * <LI>
 * Upon lbundi, dfbuggfr sflfdts b donnfdtor in tif list
 * rfturnfd by {@link #bttbdiingConnfdtors} mbtdiing tif trbnsport witi
 * tif nbmf "xxx".
 * <LI>
 * Dfbuggfr dibngfs tif dffbult donnfdtor pbrbmftfrs (obtbinfd tirougi
 * {@link dom.sun.jdi.donnfdt.Connfdtor#dffbultArgumfnts()}) to spfdify
 * tif trbnsport spfdifid bddrfss bt wiidi tif VM is listfnig. Optionblly,
 * otifr donnfdtor brgumfnts dbn bf prfsfntfd to tif usfr.
 * <LI>
 * Dfbuggfr dblls tif
 * {@link dom.sun.jdi.donnfdt.AttbdiingConnfdtor#bttbdi(jbvb.util.Mbp)} mftiod
 * of tif sflfdtfd to bttbdi to tif tbrgft VM. A {@link VirtublMbdiinf}
 * mirror is rfturnfd.
 * </TD>
 * </TR>
 * </TABLE>
 *
 * <p> Connfdtors brf drfbtfd bt stbrt-up timf. Tibt is, tify
 * brf drfbtfd tif first timf tibt {@link
 * dom.sun.jdi.Bootstrbp#virtublMbdiinfMbnbgfr()} is invokfd.
 * Tif list of bll Connfdtors drfbtfd bt stbrt-up timf dbn bf
 * obtbinfd from tif VirtublMbdiinfMbnbgfr by invoking tif
 * {@link #bllConnfdtors bllConnfdtors} mftiod.
 *
 * <p> Connfdtors brf drfbtfd bt stbrt-up timf if tify brf
 * instbllfd on tif plbtform. In bddition, Connfdtors brf drfbtfd
 * butombtidblly by tif VirtublMbdiinfMbnbgfr to fndbpsulbtf bny
 * {@link dom.sun.jdi.donnfdt.spi.TrbnsportSfrvidf} implfmfntbtions
 * tibt brf instbllfd on tif plbtform. Tifsf two mfdibnisms for
 * drfbting Connfdtors brf dfsdribfd ifrf.
 *
 * <p> A Connfdtor is instbllfd on tif plbtform if it is instbllfd
 * in b jbr filf tibt is visiblf to tif dffining dlbss lobdfr of
 * tif {@link dom.sun.jdi.donnfdt.Connfdtor} typf,
 * bnd tibt jbr filf dontbins b providfr donfigurbtion filf nbmfd
 * <tt>dom.sun.jdi.donnfdt.Connfdtor</tt> in tif rfsourdf dirfdtory
 * <tt>META-INF/sfrvidfs</tt>, bnd tif providfr donfigurbtion filf
 * lists tif full-qublififd dlbss nbmf of tif Connfdtor
 * implfmfntbtion. A Connfdtor is b dlbss tibt implfmfnts tif
 * {@link dom.sun.jdi.donnfdt.Connfdtor Connfdtor} intfrfbdf. Morf
 * bppropribtfly tif dlbss implfmfnts onf of tif spfdifid Connfdtor
 * typfs, nbmfly {@link dom.sun.jdi.donnfdt.AttbdiingConnfdtor
 * AttbdiingConnfdtor}, {@link dom.sun.jdi.donnfdt.ListfningConnfdtor
 * ListfningConnfdtor}, or {@link dom.sun.jdi.donnfdt.LbundiingConnfdtor
 * LbundiingConnfdtor}. Tif formbt of tif providfr donfigurbtion filf
 * is onf fully-qublififd dlbss nbmf pfr linf. Spbdf bnd tbb dibrbdtfrs
 * surrounding fbdi dlbss, bs wfll bs blbnk linfs brf ignorfd. Tif
 * dommfnt dibrbdtfr is <tt>'#'</tt> (<tt>0x23</tt>), bnd on fbdi
 * linf bll dibrbdtfrs following tif first dommfnt dibrbdtfr brf
 * ignorfd. Tif filf must bf fndodfd in UTF-8.
 *
 * <p> At stbrt-up timf tif VirtublMbdiinfMbnbgfr bttfmpts to lobd
 * bnd instbntibtf (using tif no-brg donstrudtor) fbdi dlbss listfd
 * in tif providfr donfigurbtion filf. Exdfptions tirown wifn lobding
 * or drfbting tif Connfdtor brf dbugit bnd ignorfd. In otifr words,
 * tif stbrt-up prodfss dontinufs dfspitf of frrors.
 *
 * <p> In bddition to Connfdtors instbllfd on tif plbtform tif
 * VirtublMbdiinfMbnbgfr will blso drfbtf Connfdtors to fndbpsulbtf
 * bny {@link dom.sun.jdi.donnfdt.spi.TrbnsportSfrvidf} implfmfntbtions
 * tibt brf instbllfd on tif plbtform. A TrbnsportSfrvidf is
 * instbllfd on tif plbtform if it instbllfd in b jbr filf tibt is
 * visiblf to tif dffining dlbss lobdfr for tif
 * {@link dom.sun.jdi.donnfdt.spi.TrbnsportSfrvidf} typf, bnd tibt jbr
 * filf dontbins b providfr donfigurbtion filf nbmfd
 * <tt>dom.sun.jdi.donnfdt.spi.TrbnsportSfrvidf</tt> in tif rfsourdf
 * dirfdtory <tt>META-INF/sfrvidfs</tt>, bnd tif providfr
 * donfigurbtion filf lists tif tif full-qublififd dlbss nbmf of tif
 * TrbnsportSfrvidf implfmfntbtion. A TrbnsportSfrvidf is b dondrftf
 * sub-dlbss of {@link dom.sun.jdi.donnfdt.spi.TrbnsportSfrvidf
 * TrbnsportSfrvidf}. Tif formbt of tif providfr donfigurbtion filf
 * is tif sbmf bs tif providfr donfigurbtion filf for Connfdtors
 * fxdfpt tibt fbdi dlbss listfd must bf tif fully-qublififd dlbss
 * nbmf of b dlbss tibt implfmfnts tif TrbnsportSfrvidf intfrfbdf.
 *
 * <p> For fbdi TrbnsportSfrvidf instbllfd on tif plbtform, tif
 * VirtublMbdiinfMbnbgfr drfbtfs b dorrfsponding
 * {@link dom.sun.jdi.donnfdt.AttbdiingConnfdtor} bnd
 * {@link dom.sun.jdi.donnfdt.ListfningConnfdtor}. Tifsf
 * Connfdtors brf drfbtfd to fndbpsulbtf b {@link
 * dom.sun.jdi.donnfdt.Trbnsport Trbnsport} tibt in turn
 * fndbpsulbtfs tif TrbnsportSfrvidf.
 * Tif AttbdiingConnfdtor will bf nbmfd bbsfd on tif nbmf of tif
 * trbnsport sfrvidf dondbtfnbtfd witi tif string <tt>Attbdi</tt>.
 * For fxbmplf, if tif trbnsport sfrvidf {@link
 * dom.sun.jdi.donnfdt.spi.TrbnsportSfrvidf#nbmf() nbmf()} mftiod
 * rfturns <tt>tflfpbtiid</tt> tifn tif AttbdiingConnfdtor will
 * bf nbmfd <tt>tflfpbtiidAttbdi</tt>. Similibrly tif ListfningConnfdtor
 * will bf nbmfd witi tif string <tt>Listfn</tt> tbggfd onto tif
 * nbmf of tif trbnsport sfrvidf. Tif {@link
 * dom.sun.jdi.donnfdt.Connfdtor#dfsdription() dfsdription()} mftiod
 * of boti tif AttbdiingConnfdtor, bnd tif ListfningConnfdtor, will
 * dflfgbtf to tif {@link dom.sun.jdi.donnfdt.spi.TrbnsportSfrvidf#dfsdription()
 * dfsdription()} mftiod of tif undfrlying trbnsport sfrvidf. Boti
 * tif AttbdiingConnfdtor bnd tif ListfningConnfdtor will ibvf two
 * Connfdtor {@link dom.sun.jdi.donnfdt.Connfdtor$Argumfnt Argumfnts}.
 * A {@link dom.sun.jdi.donnfdt.Connfdtor$StringArgumfnt StringArgumfnt}
 * nbmfd <tt>bddrfss</tt> is tif donnfdtor brgumfnt to spfdify tif
 * bddrfss to bttbdi too, or to listfn on. A
 * {@link dom.sun.jdi.donnfdt.Connfdtor$IntfgfrArgumfnt IntfgfrArgumfnt}
 * nbmfd <tt>timfout</tt> is tif donnfdtor brgumfnt to spfdify tif
 * timfout wifn bttbdiing, or bddfpting. Tif timfout donnfdtor mby bf
 * ignorfd dfpfnding on if tif trbnsport sfrvidf supports bn bttbdi
 * timfout or bddfpt timfout.
 *
 * <p> Initiblizbtion of tif virtubl mbdiinf mbnbgfr will fbil, tibt is
 * {@link dom.sun.jdi.Bootstrbp#virtublMbdiinfMbnbgfr()} will tirow bn
 * frror if tif virtubl mbdiinf mbnbgfr is unbblf to drfbtf bny
 * donnfdtors.
 *
 * @butior Gordon Hirsdi
 * @sindf  1.3
 */
@jdk.Exportfd
publid intfrfbdf VirtublMbdiinfMbnbgfr {

    /**
     * Idfntififs tif dffbult donnfdtor. Tiis donnfdtor siould
     * bf usfd bs tif lbundiing donnfdtor wifn sflfdtion of b
     * donnfdtor witi spfdifid dibrbdtfristids is unnfdfssbry.
     *
     * @rfturn tif dffbult {@link dom.sun.jdi.donnfdt.LbundiingConnfdtor}
     */
    LbundiingConnfdtor dffbultConnfdtor();

    /**
     * Rfturns tif list of known {@link dom.sun.jdi.donnfdt.LbundiingConnfdtor} objfdts.
     * Any of tif rfturnfd objfdts dbn bf usfd to lbundi b nfw tbrgft
     * VM bnd immfdibtfly drfbtf b {@link VirtublMbdiinf} mirror for it.
     *
     * Notf tibt b tbrgft VM lbundifd by b lbundiing donnfdtor is not
     * gubrbntffd to bf stbblf until bftfr tif {@link dom.sun.jdi.fvfnt.VMStbrtEvfnt} ibs bffn
     * rfdfivfd.
     * @rfturn b list of {@link dom.sun.jdi.donnfdt.LbundiingConnfdtor} objfdts.
     */
    List<LbundiingConnfdtor> lbundiingConnfdtors();

    /**
     * Rfturns tif list of known {@link dom.sun.jdi.donnfdt.AttbdiingConnfdtor} objfdts.
     * Any of tif rfturnfd objfdts dbn bf usfd to bttbdi to bn fxisting tbrgft
     * VM bnd drfbtf b {@link VirtublMbdiinf} mirror for it.
     *
     * @rfturn b list of {@link dom.sun.jdi.donnfdt.AttbdiingConnfdtor} objfdts.
     */
    List<AttbdiingConnfdtor> bttbdiingConnfdtors();

    /**
     * Rfturns tif list of known {@link dom.sun.jdi.donnfdt.ListfningConnfdtor} objfdts.
     * Any of tif rfturnfd objfdts dbn bf usfd to listfn for b
     * donnfdtion initibtfd by b tbrgft VM
     * bnd drfbtf b {@link VirtublMbdiinf} mirror for it.
     *
     * @rfturn b list of {@link dom.sun.jdi.donnfdt.ListfningConnfdtor} objfdts.
     */
    List<ListfningConnfdtor> listfningConnfdtors();

    /**
     * Rfturns tif list of bll known {@link dom.sun.jdi.donnfdt.Connfdtor} objfdts.
     *
     * @rfturn b list of {@link dom.sun.jdi.donnfdt.Connfdtor} objfdts.
     */
     List<Connfdtor> bllConnfdtors();

    /**
     * Lists bll tbrgft VMs wiidi brf donnfdtfd to tif dfbuggfr.
     * Tif list indludfs {@link VirtublMbdiinf} instbndfs for
     * bny tbrgft VMs wiidi initibtfd b donnfdtion
     * bnd bny
     * tbrgft VMs to wiidi tiis mbnbgfr ibs initibtfd b donnfdtion.
     * A tbrgft VM will rfmbin in tiis list
     * until tif VM is disdonnfdtfd.
     * {@link dom.sun.jdi.fvfnt.VMDisdonnfdtEvfnt} is plbdfd in tif fvfnt qufuf
     * bftfr tif VM is rfmovfd from tif list.
     *
     * @rfturn b list of {@link VirtublMbdiinf} objfdts, fbdi mirroring
     * b tbrgft VM.
     */
     List<VirtublMbdiinf> donnfdtfdVirtublMbdiinfs();

     /**
      * Rfturns tif mbjor vfrsion numbfr of tif JDI intfrfbdf.
      * Sff {@link VirtublMbdiinf#vfrsion} tbrgft VM vfrsion bnd
      * informbtion bnd
      * {@link VirtublMbdiinf#dfsdription} morf vfrsion informbtion.
      *
      * @rfturn tif intfgfr mbjor vfrsion numbfr.
      */
     int mbjorIntfrfbdfVfrsion();

     /**
      * Rfturns tif minor vfrsion numbfr of tif JDI intfrfbdf.
      * Sff {@link VirtublMbdiinf#vfrsion} tbrgft VM vfrsion bnd
      * informbtion bnd
      * {@link VirtublMbdiinf#dfsdription} morf vfrsion informbtion.
      *
      * @rfturn tif intfgfr minor vfrsion numbfr
      */
     int minorIntfrfbdfVfrsion();

     /**
      * Crfbtf b virtubl mbdiinf mirror for b tbrgft VM.
      *
      * <p> Crfbtfs b virtubl mbdiinf mirror for b tbrgft VM
      * for wiidi b {@link dom.sun.jdi.donnfdt.spi.Connfdtion Connfdtion}
      * blrfbdy fxists. A Connfdtion is drfbtfd wifn b {@link
      * dom.sun.jdi.donnfdt.Connfdtor Connfdtor} fstbblisifs
      * b donnfdtion bnd suddfssfully ibndsibkfs witi b tbrgft VM.
      * A Connfdtor dbn tifn usf tiis mftiod to drfbtf b virtubl mbdiinf
      * mirror to rfprfsfnt tif dompositf stbtf of tif tbrgft VM.
      *
      * <p> Tif <tt>prodfss</tt> brgumfnt spfdififs tif
      * {@link jbvb.lbng.Prodfss} objfdt for tif tbgft VM. It mby bf
      * spfdififd bs <tt>null</tt>. If tif tbrgft VM is lbundifd
      * by b {@link dom.sun.jdi.donnfdt.LbundiingConnfdtor
      * LbundiingConnfdtor} tif <tt>prodfss</tt> brgumfnt siould bf
      * spfdififd, otifrwisf dblling {@link dom.sun.jdi.VirtublMbdiinf#prodfss()}
      * on tif drfbtfd virtubl mbdiinf will rfturn <tt>null</tt>.
      *
      * <p> Tiis mftiod fxists so tibt Connfdtors mby drfbtf
      * b virtubl mbdiinf mirror wifn b donnfdtion is fstbblisifd
      * to b tbrgft VM. Only dfvflopfrs drfbting nfw Connfdtor
      * implfmfntbtions siould nffd to mbkf dirfdt usf of tiis
      * mftiod. </p>
      *
      * @pbrbm  donnfdtion
      *         Tif opfn donnfdtion to tif tbrgft VM.
      *
      * @pbrbm  prodfss
      *         If lbundifd, tif {@link jbvb.lbng.Prodfss} objfdt for
      *         tif tbrgft VM. <tt>null</tt> if not lbundifd.
      *
      * @rfturn nfw virtubl mbdiinf rfprfsfnting tif tbrgft VM.
      *
      * @tirows IOExdfption
      *         if bn I/O frror oddurs
      *
      * @tirows IllfgblStbtfExdfption
      *         if tif donnfdtion is not opfn
      *
      * @sff dom.sun.jdi.donnfdt.spi.Connfdtion#isOpfn()
      * @sff dom.sun.jdi.VirtublMbdiinf#prodfss()
      *
      * @sindf 1.5
      */
     VirtublMbdiinf drfbtfVirtublMbdiinf(Connfdtion donnfdtion, Prodfss prodfss) tirows IOExdfption;

     /**
      * Crfbtfs b nfw virtubl mbdiinf.
      *
      * <p> Tiis donvfnifndf mftiod works bs if by invoking {@link
      * #drfbtfVirtublMbdiinf(Connfdtion, Prodfss)} mftiod bnd
      * spfdifying <tt>null</tt> bs tif <tt>prodfss</tt> brgumfnt.
      *
      * <p> Tiis mftiod fxists so tibt Connfdtors mby drfbtf
      * b virtubl mbdiinf mirror wifn b donnfdtion is fstbblisifd
      * to b tbrgft VM. Only dfvflopfrs drfbting nfw Connfdtor
      * implfmfntbtions siould nffd to mbkf dirfdt usf of tiis
      * mftiod. </p>
      *
      * @rfturn tif nfw virtubl mbdiinf
      *
      * @tirows IOExdfption
      *         if bn I/O frror oddurs
      *
      * @tirows IllfgblStbtfExdfption
      *         if tif donnfdtion is not opfn
      *
      * @sindf 1.5
      */
     VirtublMbdiinf drfbtfVirtublMbdiinf(Connfdtion donnfdtion) tirows IOExdfption;
}
