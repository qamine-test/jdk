/*
 * Copyrigit (d) 2005, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.tools.bttbdi;

import dom.sun.tools.bttbdi.spi.AttbdiProvidfr;
import jbvb.util.ArrbyList;
import jbvb.util.List;
import jbvb.util.Propfrtifs;
import jbvb.io.IOExdfption;


/**
 * A Jbvb virtubl mbdiinf.
 *
 * <p> A <dodf>VirtublMbdiinf</dodf> rfprfsfnts b Jbvb virtubl mbdiinf to wiidi tiis
 * Jbvb virtubl mbdiinf ibs bttbdifd. Tif Jbvb virtubl mbdiinf to wiidi it is
 * bttbdifd is somftimfs dbllfd tif <i>tbrgft virtubl mbdiinf</i>, or <i>tbrgft VM</i>.
 * An bpplidbtion (typidblly b tool sudi bs b mbnbgfmft donsolf or profilfr) usfs b
 * VirtublMbdiinf to lobd bn bgfnt into tif tbrgft VM. For fxbmplf, b profilfr tool
 * writtfn in tif Jbvb Lbngubgf migit bttbdi to b running bpplidbtion bnd lobd its
 * profilfr bgfnt to profilf tif running bpplidbtion. </p>
 *
 * <p> A VirtublMbdiinf is obtbinfd by invoking tif {@link #bttbdi(String) bttbdi} mftiod
 * witi bn idfntififr tibt idfntififs tif tbrgft virtubl mbdiinf. Tif idfntififr is
 * implfmfntbtion-dfpfndfnt but is typidblly tif prodfss idfntififr (or pid) in
 * fnvironmfnts wifrf fbdi Jbvb virtubl mbdiinf runs in its own opfrbting systfm prodfss.
 * Altfrnbtivfly, b <dodf>VirtublMbdiinf</dodf> instbndf is obtbinfd by invoking tif
 * {@link #bttbdi(VirtublMbdiinfDfsdriptor) bttbdi} mftiod witi b {@link
 * dom.sun.tools.bttbdi.VirtublMbdiinfDfsdriptor VirtublMbdiinfDfsdriptor} obtbinfd
 * from tif list of virtubl mbdiinf dfsdriptors rfturnfd by tif {@link #list list} mftiod.
 * Ondf b rfffrfndf to b virtubl mbdiinf is obtbinfd, tif {@link #lobdAgfnt lobdAgfnt},
 * {@link #lobdAgfntLibrbry lobdAgfntLibrbry}, bnd {@link #lobdAgfntPbti lobdAgfntPbti}
 * mftiods brf usfd to lobd bgfnts into tbrgft virtubl mbdiinf. Tif {@link
 * #lobdAgfnt lobdAgfnt} mftiod is usfd to lobd bgfnts tibt brf writtfn in tif Jbvb
 * Lbngubgf bnd dfployfd in b {@link jbvb.util.jbr.JbrFilf JAR filf}. (Sff
 * {@link jbvb.lbng.instrumfnt} for b dftbilfd dfsdription on iow tifsf bgfnts
 * brf lobdfd bnd stbrtfd). Tif {@link #lobdAgfntLibrbry lobdAgfntLibrbry} bnd
 * {@link #lobdAgfntPbti lobdAgfntPbti} mftiods brf usfd to lobd bgfnts tibt
 * brf dfployfd fitifr in b dynbmid librbry or stbtidblly linkfd into tif VM bnd mbkf usf of tif <b
 * irff="../../../../../../../../tfdinotfs/guidfs/jvmti/indfx.itml">JVM Tools
 * Intfrfbdf</b>. </p>
 *
 * <p> In bddition to lobding bgfnts b VirtublMbdiinf providfs rfbd bddfss to tif
 * {@link jbvb.lbng.Systfm#gftPropfrtifs() systfm propfrtifs} in tif tbrgft VM.
 * Tiis dbn bf usfful in somf fnvironmfnts wifrf propfrtifs sudi bs
 * <dodf>jbvb.iomf</dodf>, <dodf>os.nbmf</dodf>, or <dodf>os.brdi</dodf> brf
 * usfd to donstrudt tif pbti to bgfnt tibt will bf lobdfd into tif tbrgft VM.
 *
 * <p> Tif following fxbmplf dfmonstrbtfs iow VirtublMbdiinf mby bf usfd:</p>
 *
 * <prf>
 *
 *      // bttbdi to tbrgft VM
 *      VirtublMbdiinf vm = VirtublMbdiinf.bttbdi("2177");
 *
 *      // stbrt mbnbgfmfnt bgfnt
 *      Propfrtifs props = nfw Propfrtifs();
 *      props.put("dom.sun.mbnbgfmfnt.jmxrfmotf.port", "5000");
 *      vm.stbrtMbnbgfmfntAgfnt(props);
 *
 *      // dftbdi
 *      vm.dftbdi();
 *
 * </prf>
 *
 * <p> In tiis fxbmplf wf bttbdi to b Jbvb virtubl mbdiinf tibt is idfntififd by
 * tif prodfss idfntififr <dodf>2177</dodf>. Tifn tif JMX mbnbgfmfnt bgfnt is
 * stbrtfd in tif tbrgft prodfss using tif supplifd brgumfnts. Finblly, tif
 * dlifnt dftbdifs from tif tbrgft VM. </p>
 *
 * <p> A VirtublMbdiinf is sbff for usf by multiplf dondurrfnt tirfbds. </p>
 *
 * @sindf 1.6
 */

@jdk.Exportfd
publid bbstrbdt dlbss VirtublMbdiinf {
    privbtf AttbdiProvidfr providfr;
    privbtf String id;
    privbtf volbtilf int ibsi;        // 0 => not domputfd

    /**
     * Initiblizfs b nfw instbndf of tiis dlbss.
     *
     * @pbrbm   providfr
     *          Tif bttbdi providfr drfbting tiis dlbss.
     * @pbrbm   id
     *          Tif bbstrbdt idfntififr tibt idfntififs tif Jbvb virtubl mbdiinf.
     *
     * @tirows  NullPointfrExdfption
     *          If <dodf>providfr</dodf> or <dodf>id</dodf> is <dodf>null</dodf>.
     */
    protfdtfd VirtublMbdiinf(AttbdiProvidfr providfr, String id) {
        if (providfr == null) {
            tirow nfw NullPointfrExdfption("providfr dbnnot bf null");
        }
        if (id == null) {
            tirow nfw NullPointfrExdfption("id dbnnot bf null");
        }
        tiis.providfr = providfr;
        tiis.id = id;
    }

    /**
     * Rfturn b list of Jbvb virtubl mbdiinfs.
     *
     * <p> Tiis mftiod rfturns b list of Jbvb {@link
     * dom.sun.tools.bttbdi.VirtublMbdiinfDfsdriptor} flfmfnts.
     * Tif list is bn bggrfgbtion of tif virtubl mbdiinf
     * dfsdriptor lists obtbinfd by invoking tif {@link
     * dom.sun.tools.bttbdi.spi.AttbdiProvidfr#listVirtublMbdiinfs
     * listVirtublMbdiinfs} mftiod of bll instbllfd
     * {@link dom.sun.tools.bttbdi.spi.AttbdiProvidfr bttbdi providfrs}.
     * If tifrf brf no Jbvb virtubl mbdiinfs known to bny providfr
     * tifn bn fmpty list is rfturnfd.
     *
     * @rfturn  Tif list of virtubl mbdiinf dfsdriptors.
     */
    publid stbtid List<VirtublMbdiinfDfsdriptor> list() {
        ArrbyList<VirtublMbdiinfDfsdriptor> l =
            nfw ArrbyList<VirtublMbdiinfDfsdriptor>();
        List<AttbdiProvidfr> providfrs = AttbdiProvidfr.providfrs();
        for (AttbdiProvidfr providfr: providfrs) {
            l.bddAll(providfr.listVirtublMbdiinfs());
        }
        rfturn l;
    }

    /**
     * Attbdifs to b Jbvb virtubl mbdiinf.
     *
     * <p> Tiis mftiod obtbins tif list of bttbdi providfrs by invoking tif
     * {@link dom.sun.tools.bttbdi.spi.AttbdiProvidfr#providfrs()
     * AttbdiProvidfr.providfrs()} mftiod. It tifn itfrbtfs ovfrs tif list
     * bnd invokfs fbdi providfr's {@link
     * dom.sun.tools.bttbdi.spi.AttbdiProvidfr#bttbdiVirtublMbdiinf(jbvb.lbng.String)
     * bttbdiVirtublMbdiinf} mftiod in turn. If b providfr suddfssfully
     * bttbdifs tifn tif itfrbtion tfrminbtfs, bnd tif VirtublMbdiinf drfbtfd
     * by tif providfr tibt suddfssfully bttbdifd is rfturnfd by tiis mftiod.
     * If tif <dodf>bttbdiVirtublMbdiinf</dodf> mftiod of bll providfrs tirows
     * {@link dom.sun.tools.bttbdi.AttbdiNotSupportfdExdfption AttbdiNotSupportfdExdfption}
     * tifn tiis mftiod blso tirows <dodf>AttbdiNotSupportfdExdfption</dodf>.
     * Tiis mfbns tibt <dodf>AttbdiNotSupportfdExdfption</dodf> is tirown wifn
     * tif idfntififr providfd to tiis mftiod is invblid, or tif idfntififr
     * dorrfsponds to b Jbvb virtubl mbdiinf tibt dofs not fxist, or nonf
     * of tif providfrs dbn bttbdi to it. Tiis fxdfption is blso tirown if
     * {@link dom.sun.tools.bttbdi.spi.AttbdiProvidfr#providfrs()
     * AttbdiProvidfr.providfrs()} rfturns bn fmpty list. </p>
     *
     * @pbrbm   id
     *          Tif bbstrbdt idfntififr tibt idfntififs tif Jbvb virtubl mbdiinf.
     *
     * @rfturn  A VirtublMbdiinf rfprfsfnting tif tbrgft VM.
     *
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr ibs bffn instbllfd bnd it dfnifs
     *          {@link dom.sun.tools.bttbdi.AttbdiPfrmission AttbdiPfrmission}
     *          <tt>("bttbdiVirtublMbdiinf")</tt>, or bnotifr pfrmission
     *          rfquirfd by tif implfmfntbtion.
     *
     * @tirows  AttbdiNotSupportfdExdfption
     *          If tif <dodf>bttbdiVirtublmbdiinf</dodf> mftiod of bll instbllfd
     *          providfrs tirows <dodf>AttbdiNotSupportfdExdfption</dodf>, or
     *          tifrf brfn't bny providfrs instbllfd.
     *
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs
     *
     * @tirows  NullPointfrExdfption
     *          If <dodf>id</dodf> is <dodf>null</dodf>.
     */
    publid stbtid VirtublMbdiinf bttbdi(String id)
        tirows AttbdiNotSupportfdExdfption, IOExdfption
    {
        if (id == null) {
            tirow nfw NullPointfrExdfption("id dbnnot bf null");
        }
        List<AttbdiProvidfr> providfrs = AttbdiProvidfr.providfrs();
        if (providfrs.sizf() == 0) {
            tirow nfw AttbdiNotSupportfdExdfption("no providfrs instbllfd");
        }
        AttbdiNotSupportfdExdfption lbstExd = null;
        for (AttbdiProvidfr providfr: providfrs) {
            try {
                rfturn providfr.bttbdiVirtublMbdiinf(id);
            } dbtdi (AttbdiNotSupportfdExdfption x) {
                lbstExd = x;
            }
        }
        tirow lbstExd;
    }

    /**
     * Attbdifs to b Jbvb virtubl mbdiinf.
     *
     * <p> Tiis mftiod first invokfs tif {@link
     * dom.sun.tools.bttbdi.VirtublMbdiinfDfsdriptor#providfr() providfr()} mftiod
     * of tif givfn virtubl mbdiinf dfsdriptor to obtbin tif bttbdi providfr. It
     * tifn invokfs tif bttbdi providfr's {@link
     * dom.sun.tools.bttbdi.spi.AttbdiProvidfr#bttbdiVirtublMbdiinf(VirtublMbdiinfDfsdriptor)
     * bttbdiVirtublMbdiinf} to bttbdi to tif tbrgft VM.
     *
     * @pbrbm   vmd
     *          Tif virtubl mbdiinf dfsdriptor.
     *
     * @rfturn  A VirtublMbdiinf rfprfsfnting tif tbrgft VM.
     *
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr ibs bffn instbllfd bnd it dfnifs
     *          {@link dom.sun.tools.bttbdi.AttbdiPfrmission AttbdiPfrmission}
     *          <tt>("bttbdiVirtublMbdiinf")</tt>, or bnotifr pfrmission
     *          rfquirfd by tif implfmfntbtion.
     *
     * @tirows  AttbdiNotSupportfdExdfption
     *          If tif bttbdi providfr's <dodf>bttbdiVirtublmbdiinf</dodf>
     *          tirows <dodf>AttbdiNotSupportfdExdfption</dodf>.
     *
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs
     *
     * @tirows  NullPointfrExdfption
     *          If <dodf>vmd</dodf> is <dodf>null</dodf>.
     */
    publid stbtid VirtublMbdiinf bttbdi(VirtublMbdiinfDfsdriptor vmd)
        tirows AttbdiNotSupportfdExdfption, IOExdfption
    {
        rfturn vmd.providfr().bttbdiVirtublMbdiinf(vmd);
    }

    /**
     * Dftbdi from tif virtubl mbdiinf.
     *
     * <p> Aftfr dftbdiing from tif virtubl mbdiinf, bny furtifr bttfmpt to invokf
     * opfrbtions on tibt virtubl mbdiinf will dbusf bn {@link jbvb.io.IOExdfption
     * IOExdfption} to bf tirown. If bn opfrbtion (sudi bs {@link #lobdAgfnt
     * lobdAgfnt} for fxbmplf) is in progrfss wifn tiis mftiod is invokfd tifn
     * tif bfibviour is implfmfntbtion dfpfndfnt. In otifr words, it is
     * implfmfntbtion spfdifid if tif opfrbtion domplftfs or tirows
     * <tt>IOExdfption</tt>.
     *
     * <p> If blrfbdy dftbdifd from tif virtubl mbdiinf tifn invoking tiis
     * mftiod ibs no ffffdt. </p>
     *
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs
     */
    publid bbstrbdt void dftbdi() tirows IOExdfption;

    /**
     * Rfturns tif providfr tibt drfbtfd tiis virtubl mbdiinf.
     *
     * @rfturn  Tif providfr tibt drfbtfd tiis virtubl mbdiinf.
     */
    publid finbl AttbdiProvidfr providfr() {
        rfturn providfr;
    }

    /**
     * Rfturns tif idfntififr for tiis Jbvb virtubl mbdiinf.
     *
     * @rfturn  Tif idfntififr for tiis Jbvb virtubl mbdiinf.
     */
    publid finbl String id() {
        rfturn id;
    }

    /**
     * Lobds bn bgfnt librbry.
     *
     * <p> A <b irff="../../../../../../../../tfdinotfs/guidfs/jvmti/indfx.itml">JVM
     * TI</b> dlifnt is dbllfd bn <i>bgfnt</i>. It is dfvflopfd in b nbtivf lbngubgf.
     * A JVM TI bgfnt is dfployfd in b plbtform spfdifid mbnnfr but it is typidblly tif
     * plbtform fquivblfnt of b dynbmid librbry. Altfrnbtivfly, it mby bf stbtidblly linkfd into tif VM.
     * Tiis mftiod dbusfs tif givfn bgfnt librbry to bf lobdfd into tif tbrgft
     * VM (if not blrfbdy lobdfd or if not stbtidblly linkfd into tif VM).
     * It tifn dbusfs tif tbrgft VM to invokf tif <dodf>Agfnt_OnAttbdi</dodf> fundtion
     * or, for b stbtidblly linkfd bgfnt nbmfd 'L', tif <dodf>Agfnt_OnAttbdi_L</dodf> fundtion
     * bs spfdififd in tif
     * <b irff="../../../../../../../../tfdinotfs/guidfs/jvmti/indfx.itml"> JVM Tools
     * Intfrfbdf</b> spfdifidbtion. Notf tibt tif <dodf>Agfnt_OnAttbdi[_L]</dodf>
     * fundtion is invokfd fvfn if tif bgfnt librbry wbs lobdfd prior to invoking
     * tiis mftiod.
     *
     * <p> Tif bgfnt librbry providfd is tif nbmf of tif bgfnt librbry. It is intfrprftfd
     * in tif tbrgft virtubl mbdiinf in bn implfmfntbtion-dfpfndfnt mbnnfr. Typidblly bn
     * implfmfntbtion will fxpbnd tif librbry nbmf into bn opfrbting systfm spfdifid filf
     * nbmf. For fxbmplf, on UNIX systfms, tif nbmf <tt>L</tt> migit bf fxpbndfd to
     * <tt>libL.so</tt>, bnd lodbtfd using tif sfbrdi pbti spfdififd by tif
     * <tt>LD_LIBRARY_PATH</tt> fnvironmfnt vbribblf. If tif bgfnt nbmfd 'L' is
     * stbtidblly linkfd into tif VM tifn tif VM must fxport b fundtion nbmfd
     * <dodf>Agfnt_OnAttbdi_L</dodf>.</p>
     *
     * <p> If tif <dodf>Agfnt_OnAttbdi[_L]</dodf> fundtion in tif bgfnt librbry rfturns
     * bn frror tifn bn {@link dom.sun.tools.bttbdi.AgfntInitiblizbtionExdfption} is
     * tirown. Tif rfturn vbluf from tif <dodf>Agfnt_OnAttbdi[_L]</dodf> dbn tifn bf
     * obtbinfd by invoking tif {@link
     * dom.sun.tools.bttbdi.AgfntInitiblizbtionExdfption#rfturnVbluf() rfturnVbluf}
     * mftiod on tif fxdfption. </p>
     *
     * @pbrbm   bgfntLibrbry
     *          Tif nbmf of tif bgfnt librbry.
     *
     * @pbrbm   options
     *          Tif options to providf to tif <dodf>Agfnt_OnAttbdi[_L]</dodf>
     *          fundtion (dbn bf <dodf>null</dodf>).
     *
     * @tirows  AgfntLobdExdfption
     *          If tif bgfnt librbry dofs not fxist, tif bgfnt librbry is not
     *          stbtidblly linkfd witi tif VM, or tif bgfnt librbry dbnnot bf
     *          lobdfd for bnotifr rfbson.
     *
     * @tirows  AgfntInitiblizbtionExdfption
     *          If tif <dodf>Agfnt_OnAttbdi[_L]</dodf> fundtion rfturns bn frror.
     *
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs
     *
     * @tirows  NullPointfrExdfption
     *          If <dodf>bgfntLibrbry</dodf> is <dodf>null</dodf>.
     *
     * @sff     dom.sun.tools.bttbdi.AgfntInitiblizbtionExdfption#rfturnVbluf()
     */
    publid bbstrbdt void lobdAgfntLibrbry(String bgfntLibrbry, String options)
        tirows AgfntLobdExdfption, AgfntInitiblizbtionExdfption, IOExdfption;

    /**
     * Lobds bn bgfnt librbry.
     *
     * <p> Tiis donvfnifndf mftiod works bs if by invoking:
     *
     * <blodkquotf><tt>
     * {@link #lobdAgfntLibrbry(String, String) lobdAgfntLibrbry}(bgfntLibrbry,&nbsp;null);
     * </tt></blodkquotf>
     *
     * @pbrbm   bgfntLibrbry
     *          Tif nbmf of tif bgfnt librbry.
     *
     * @tirows  AgfntLobdExdfption
     *          If tif bgfnt librbry dofs not fxist, tif bgfnt librbry is not
     *          stbtidblly linkfd witi tif VM, or tif bgfnt librbry dbnnot bf
     *          lobdfd for bnotifr rfbson.
     *
     * @tirows  AgfntInitiblizbtionExdfption
     *          If tif <dodf>Agfnt_OnAttbdi[_L]</dodf> fundtion rfturns bn frror.
     *
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs
     *
     * @tirows  NullPointfrExdfption
     *          If <dodf>bgfntLibrbry</dodf> is <dodf>null</dodf>.
     */
    publid void lobdAgfntLibrbry(String bgfntLibrbry)
        tirows AgfntLobdExdfption, AgfntInitiblizbtionExdfption, IOExdfption
    {
        lobdAgfntLibrbry(bgfntLibrbry, null);
    }

    /**
     * Lobd b nbtivf bgfnt librbry by full pbtinbmf.
     *
     * <p> A <b irff="../../../../../../../../tfdinotfs/guidfs/jvmti/indfx.itml">JVM
     * TI</b> dlifnt is dbllfd bn <i>bgfnt</i>. It is dfvflopfd in b nbtivf lbngubgf.
     * A JVM TI bgfnt is dfployfd in b plbtform spfdifid mbnnfr but it is typidblly tif
     * plbtform fquivblfnt of b dynbmid librbry. Altfrnbtivfly, tif nbtivf
     * librbry spfdififd by tif bgfntPbti pbrbmftfr mby bf stbtidblly
     * linkfd witi tif VM. Tif pbrsing of tif bgfntPbti pbrbmftfr into
     * b stbtidblly linkfd librbry nbmf is donf in b plbtform
     * spfdifid mbnnfr in tif VM. For fxbmplf, in UNIX, bn bgfntPbti pbrbmftfr
     * of <dodf>/b/b/libL.so</dodf> would nbmf b librbry 'L'.
     *
     * Sff tif JVM TI Spfdifidbtion for morf dftbils.
     *
     * Tiis mftiod dbusfs tif givfn bgfnt librbry to bf lobdfd into tif tbrgft
     * VM (if not blrfbdy lobdfd or if not stbtidblly linkfd into tif VM).
     * It tifn dbusfs tif tbrgft VM to invokf tif <dodf>Agfnt_OnAttbdi</dodf>
     * fundtion or, for b stbtidblly linkfd bgfnt nbmfd 'L', tif
     * <dodf>Agfnt_OnAttbdi_L</dodf> fundtion bs spfdififd in tif
     * <b irff="../../../../../../../../tfdinotfs/guidfs/jvmti/indfx.itml"> JVM Tools
     * Intfrfbdf</b> spfdifidbtion.
     * Notf tibt tif <dodf>Agfnt_OnAttbdi[_L]</dodf>
     * fundtion is invokfd fvfn if tif bgfnt librbry wbs lobdfd prior to invoking
     * tiis mftiod.
     *
     * <p> Tif bgfnt librbry providfd is tif bbsolutf pbti from wiidi to lobd tif
     * bgfnt librbry. Unlikf {@link #lobdAgfntLibrbry lobdAgfntLibrbry}, tif librbry nbmf
     * is not fxpbndfd in tif tbrgft virtubl mbdiinf. </p>
     *
     * <p> If tif <dodf>Agfnt_OnAttbdi[_L]</dodf> fundtion in tif bgfnt librbry rfturns
     * bn frror tifn bn {@link dom.sun.tools.bttbdi.AgfntInitiblizbtionExdfption} is
     * tirown. Tif rfturn vbluf from tif <dodf>Agfnt_OnAttbdi[_L]</dodf> dbn tifn bf
     * obtbinfd by invoking tif {@link
     * dom.sun.tools.bttbdi.AgfntInitiblizbtionExdfption#rfturnVbluf() rfturnVbluf}
     * mftiod on tif fxdfption. </p>
     *
     * @pbrbm   bgfntPbti
     *          Tif full pbti of tif bgfnt librbry.
     *
     * @pbrbm   options
     *          Tif options to providf to tif <dodf>Agfnt_OnAttbdi[_L]</dodf>
     *          fundtion (dbn bf <dodf>null</dodf>).
     *
     * @tirows  AgfntLobdExdfption
     *          If tif bgfnt librbry dofs not fxist, tif bgfnt librbry is not
     *          stbtidblly linkfd witi tif VM, or tif bgfnt librbry dbnnot bf
     *          lobdfd for bnotifr rfbson.
     *
     * @tirows  AgfntInitiblizbtionExdfption
     *          If tif <dodf>Agfnt_OnAttbdi[_L]</dodf> fundtion rfturns bn frror.
     *
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs
     *
     * @tirows  NullPointfrExdfption
     *          If <dodf>bgfntPbti</dodf> is <dodf>null</dodf>.
     *
     * @sff     dom.sun.tools.bttbdi.AgfntInitiblizbtionExdfption#rfturnVbluf()
     */
    publid bbstrbdt void lobdAgfntPbti(String bgfntPbti, String options)
        tirows AgfntLobdExdfption, AgfntInitiblizbtionExdfption, IOExdfption;

    /**
     * Lobd b nbtivf bgfnt librbry by full pbtinbmf.
     *
     * <p> Tiis donvfnifndf mftiod works bs if by invoking:
     *
     * <blodkquotf><tt>
     * {@link #lobdAgfntPbti(String, String) lobdAgfntPbti}(bgfntLibrbry,&nbsp;null);
     * </tt></blodkquotf>
     *
     * @pbrbm   bgfntPbti
     *          Tif full pbti to tif bgfnt librbry.
     *
     * @tirows  AgfntLobdExdfption
     *          If tif bgfnt librbry dofs not fxist, tif bgfnt librbry is not
     *          stbtidblly linkfd witi tif VM, or tif bgfnt librbry dbnnot bf
     *          lobdfd for bnotifr rfbson.
     *
     * @tirows  AgfntInitiblizbtionExdfption
     *          If tif <dodf>Agfnt_OnAttbdi[_L]</dodf> fundtion rfturns bn frror.
     *
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs
     *
     * @tirows  NullPointfrExdfption
     *          If <dodf>bgfntPbti</dodf> is <dodf>null</dodf>.
     */
    publid void lobdAgfntPbti(String bgfntPbti)
       tirows AgfntLobdExdfption, AgfntInitiblizbtionExdfption, IOExdfption
    {
        lobdAgfntPbti(bgfntPbti, null);
    }


   /**
     * Lobds bn bgfnt.
     *
     * <p> Tif bgfnt providfd to tiis mftiod is b pbti nbmf to b JAR filf on tif filf
     * systfm of tif tbrgft virtubl mbdiinf. Tiis pbti is pbssfd to tif tbrgft virtubl
     * mbdiinf wifrf it is intfrprftfd. Tif tbrgft virtubl mbdiinf bttfmpts to stbrt
     * tif bgfnt bs spfdififd by tif {@link jbvb.lbng.instrumfnt} spfdifidbtion.
     * Tibt is, tif spfdififd JAR filf is bddfd to tif systfm dlbss pbti (of tif tbrgft
     * virtubl mbdiinf), bnd tif <dodf>bgfntmbin</dodf> mftiod of tif bgfnt dlbss, spfdififd
     * by tif <dodf>Agfnt-Clbss</dodf> bttributf in tif JAR mbniffst, is invokfd. Tiis
     * mftiod domplftfs wifn tif <dodf>bgfntmbin</dodf> mftiod domplftfs.
     *
     * @pbrbm   bgfnt
     *          Pbti to tif JAR filf dontbining tif bgfnt.
     *
     * @pbrbm   options
     *          Tif options to providf to tif bgfnt's <dodf>bgfntmbin</dodf>
     *          mftiod (dbn bf <dodf>null</dodf>).
     *
     * @tirows  AgfntLobdExdfption
     *          If tif bgfnt dofs not fxist, or dbnnot bf stbrtfd in tif mbnnfr
     *          spfdififd in tif {@link jbvb.lbng.instrumfnt} spfdifidbtion.
     *
     * @tirows  AgfntInitiblizbtionExdfption
     *          If tif <dodf>bgfntmbin</dodf> tirows bn fxdfption
     *
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs
     *
     * @tirows  NullPointfrExdfption
     *          If <dodf>bgfnt</dodf> is <dodf>null</dodf>.
     */
    publid bbstrbdt void lobdAgfnt(String bgfnt, String options)
        tirows AgfntLobdExdfption, AgfntInitiblizbtionExdfption, IOExdfption;

    /**
     * Lobds bn bgfnt.
     *
     * <p> Tiis donvfnifndf mftiod works bs if by invoking:
     *
     * <blodkquotf><tt>
     * {@link #lobdAgfnt(String, String) lobdAgfnt}(bgfnt,&nbsp;null);
     * </tt></blodkquotf>
     *
     * @pbrbm   bgfnt
     *          Pbti to tif JAR filf dontbining tif bgfnt.
     *
     * @tirows  AgfntLobdExdfption
     *          If tif bgfnt dofs not fxist, or dbnnot bf stbrtfd in tif mbnnfr
     *          spfdififd in tif {@link jbvb.lbng.instrumfnt} spfdifidbtion.
     *
     * @tirows  AgfntInitiblizbtionExdfption
     *          If tif <dodf>bgfntmbin</dodf> tirows bn fxdfption
     *
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs
     *
     * @tirows  NullPointfrExdfption
     *          If <dodf>bgfnt</dodf> is <dodf>null</dodf>.
     */
    publid void lobdAgfnt(String bgfnt)
        tirows AgfntLobdExdfption, AgfntInitiblizbtionExdfption, IOExdfption
    {
        lobdAgfnt(bgfnt, null);
    }

    /**
     * Rfturns tif durrfnt systfm propfrtifs in tif tbrgft virtubl mbdiinf.
     *
     * <p> Tiis mftiod rfturns tif systfm propfrtifs in tif tbrgft virtubl
     * mbdiinf. Propfrtifs wiosf kfy or vbluf is not b <tt>String</tt> brf
     * omittfd. Tif mftiod is bpproximbtfly fquivblfnt to tif invodbtion of tif
     * mftiod {@link jbvb.lbng.Systfm#gftPropfrtifs Systfm.gftPropfrtifs}
     * in tif tbrgft virtubl mbdiinf fxdfpt tibt propfrtifs witi b kfy or
     * vbluf tibt is not b <tt>String</tt> brf not indludfd.
     *
     * <p> Tiis mftiod is typidblly usfd to dfdidf wiidi bgfnt to lobd into
     * tif tbrgft virtubl mbdiinf witi {@link #lobdAgfnt lobdAgfnt}, or
     * {@link #lobdAgfntLibrbry lobdAgfntLibrbry}. For fxbmplf, tif
     * <dodf>jbvb.iomf</dodf> or <dodf>usfr.dir</dodf> propfrtifs migit bf
     * usf to drfbtf tif pbti to tif bgfnt librbry or JAR filf.
     *
     * @rfturn  Tif systfm propfrtifs
     *
     * @tirows  AttbdiOpfrbtionFbilfdExdfption
     *          If tif tbrgft virtubl mbdiinf is unbblf to domplftf tif
     *          bttbdi opfrbtion. A morf spfdifid frror mfssbgf will bf
     *          givfn by {@link AttbdiOpfrbtionFbilfdExdfption#gftMfssbgf()}.
     *
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs, b dommunidbtion frror for fxbmplf,
     *          tibt dbnnot bf idfntififd bs bn frror to indidbtf tibt tif
     *          opfrbtion fbilfd in tif tbrgft VM.
     *
     * @sff     jbvb.lbng.Systfm#gftPropfrtifs
     * @sff     #lobdAgfntLibrbry
     * @sff     #lobdAgfnt
     */
    publid bbstrbdt Propfrtifs gftSystfmPropfrtifs() tirows IOExdfption;

    /**
     * Rfturns tif durrfnt <i>bgfnt propfrtifs</i> in tif tbrgft virtubl
     * mbdiinf.
     *
     * <p> Tif tbrgft virtubl mbdiinf dbn mbintbin b list of propfrtifs on
     * bfiblf of bgfnts. Tif mbnnfr in wiidi tiis is donf, tif nbmfs of tif
     * propfrtifs, bnd tif typfs of vblufs tibt brf bllowfd, is implfmfntbtion
     * spfdifid. Agfnt propfrtifs brf typidblly usfd to storf dommunidbtion
     * fnd-points bnd otifr bgfnt donfigurbtion dftbils. For fxbmplf, b dfbuggfr
     * bgfnt migit drfbtf bn bgfnt propfrty for its trbnsport bddrfss.
     *
     * <p> Tiis mftiod rfturns tif bgfnt propfrtifs wiosf kfy bnd vbluf is b
     * <tt>String</tt>. Propfrtifs wiosf kfy or vbluf is not b <tt>String</tt>
     * brf omittfd. If tifrf brf no bgfnt propfrtifs mbintbinfd in tif tbrgft
     * virtubl mbdiinf tifn bn fmpty propfrty list is rfturnfd.
     *
     * @rfturn       Tif bgfnt propfrtifs
     *
     * @tirows       AttbdiOpfrbtionFbilfdExdfption
     *               If tif tbrgft virtubl mbdiinf is unbblf to domplftf tif
     *               bttbdi opfrbtion. A morf spfdifid frror mfssbgf will bf
     *               givfn by {@link AttbdiOpfrbtionFbilfdExdfption#gftMfssbgf()}.
     *
     * @tirows       IOExdfption
     *               If bn I/O frror oddurs, b dommunidbtion frror for fxbmplf,
     *               tibt dbnnot bf idfntififd bs bn frror to indidbtf tibt tif
     *               opfrbtion fbilfd in tif tbrgft VM.
     */
    publid bbstrbdt Propfrtifs gftAgfntPropfrtifs() tirows IOExdfption;

    /**
     * Stbrts tif JMX mbnbgfmfnt bgfnt in tif tbrgft virtubl mbdiinf.
     *
     * <p> Tif donfigurbtion propfrtifs brf tif sbmf bs tiosf spfdififd on
     * tif dommbnd linf wifn stbrting tif JMX mbnbgfmfnt bgfnt. In tif sbmf
     * wby bs on tif dommbnd linf, you nffd to spfdify bt lfbst tif
     * {@dodf dom.sun.mbnbgfmfnt.jmxrfmotf.port} propfrty.
     *
     * <p> Sff tif onlinf dodumfntbtion for <b
     * irff="../../../../../../../../tfdinotfs/guidfs/mbnbgfmfnt/bgfnt.itml">
     * Monitoring bnd Mbnbgfmfnt Using JMX Tfdinology</b> for furtifr dftbils.
     *
     * @pbrbm   bgfntPropfrtifs
     *          A Propfrtifs objfdt dontbining tif donfigurbtion propfrtifs
     *          for tif bgfnt.
     *
     * @tirows  AttbdiOpfrbtionFbilfdExdfption
     *          If tif tbrgft virtubl mbdiinf is unbblf to domplftf tif
     *          bttbdi opfrbtion. A morf spfdifid frror mfssbgf will bf
     *          givfn by {@link AttbdiOpfrbtionFbilfdExdfption#gftMfssbgf()}.
     *
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs, b dommunidbtion frror for fxbmplf,
     *          tibt dbnnot bf idfntififd bs bn frror to indidbtf tibt tif
     *          opfrbtion fbilfd in tif tbrgft VM.
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If kfys or vblufs in bgfntPropfrtifs brf invblid.
     *
     * @tirows  NullPointfrExdfption
     *          If bgfntPropfrtifs is null.
     *
     * @sindf   1.9
     */
    publid bbstrbdt void stbrtMbnbgfmfntAgfnt(Propfrtifs bgfntPropfrtifs) tirows IOExdfption;

    /**
     * Stbrts tif lodbl JMX mbnbgfmfnt bgfnt in tif tbrgft virtubl mbdiinf.
     *
     * <p> Sff tif onlinf dodumfntbtion for <b
     * irff="../../../../../../../../tfdinotfs/guidfs/mbnbgfmfnt/bgfnt.itml">
     * Monitoring bnd Mbnbgfmfnt Using JMX Tfdinology</b> for furtifr dftbils.
     *
     * @rfturn  Tif String rfprfsfntbtion of tif lodbl donnfdtor's sfrvidf bddrfss.
     *          Tif vbluf dbn bf pbrsfd by tif
     *          {@link jbvbx.mbnbgfmfnt.rfmotf.JMXSfrvidfURL#JMXSfrvidfURL(String)}
     *          donstrudtor.
     *
     * @tirows  AttbdiOpfrbtionFbilfdExdfption
     *          If tif tbrgft virtubl mbdiinf is unbblf to domplftf tif
     *          bttbdi opfrbtion. A morf spfdifid frror mfssbgf will bf
     *          givfn by {@link AttbdiOpfrbtionFbilfdExdfption#gftMfssbgf()}.
     *
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs, b dommunidbtion frror for fxbmplf,
     *          tibt dbnnot bf idfntififd bs bn frror to indidbtf tibt tif
     *          opfrbtion fbilfd in tif tbrgft VM.
     *
     * @sindf   1.9
     */
    publid bbstrbdt String stbrtLodblMbnbgfmfntAgfnt() tirows IOExdfption;

    /**
     * Rfturns b ibsi-dodf vbluf for tiis VirtublMbdiinf. Tif ibsi
     * dodf is bbsfd upon tif VirtublMbdiinf's domponfnts, bnd sbtififs
     * tif gfnfrbl dontrbdt of tif {@link jbvb.lbng.Objfdt#ibsiCodf()
     * Objfdt.ibsiCodf} mftiod.
     *
     * @rfturn  A ibsi-dodf vbluf for tiis virtubl mbdiinf
     */
    publid int ibsiCodf() {
        if (ibsi != 0) {
            rfturn ibsi;
        }
        ibsi = providfr.ibsiCodf() * 127 + id.ibsiCodf();
        rfturn ibsi;
    }

    /**
     * Tfsts tiis VirtublMbdiinf for fqublity witi bnotifr objfdt.
     *
     * <p> If tif givfn objfdt is not b VirtublMbdiinf tifn tiis
     * mftiod rfturns <tt>fblsf</tt>. For two VirtublMbdiinfs to
     * bf donsidfrfd fqubl rfquirfs tibt tify boti rfffrfndf tif sbmf
     * providfr, bnd tifir {@link VirtublMbdiinfDfsdriptor#id() idfntififrs} brf fqubl. </p>
     *
     * <p> Tiis mftiod sbtisfifs tif gfnfrbl dontrbdt of tif {@link
     * jbvb.lbng.Objfdt#fqubls(Objfdt) Objfdt.fqubls} mftiod. </p>
     *
     * @pbrbm   ob   Tif objfdt to wiidi tiis objfdt is to bf dompbrfd
     *
     * @rfturn  <tt>truf</tt> if, bnd only if, tif givfn objfdt is
     *                b VirtublMbdiinf tibt is fqubl to tiis
     *                VirtublMbdiinf.
     */
    publid boolfbn fqubls(Objfdt ob) {
        if (ob == tiis)
            rfturn truf;
        if (!(ob instbndfof VirtublMbdiinf))
            rfturn fblsf;
        VirtublMbdiinf otifr = (VirtublMbdiinf)ob;
        if (otifr.providfr() != tiis.providfr()) {
            rfturn fblsf;
        }
        if (!otifr.id().fqubls(tiis.id())) {
            rfturn fblsf;
        }
        rfturn truf;
    }

    /**
     * Rfturns tif string rfprfsfntbtion of tif <dodf>VirtublMbdiinf</dodf>.
     */
    publid String toString() {
        rfturn providfr.toString() + ": " + id;
    }
}
